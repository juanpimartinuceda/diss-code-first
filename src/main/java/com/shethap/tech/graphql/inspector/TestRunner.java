package com.shethap.tech.graphql.inspector;

import com.shethap.tech.graphql.model.Test;
import com.shethap.tech.graphql.model.TestResult;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.HashMap;

import static java.lang.Double.parseDouble;

public class TestRunner {

    private HashMap<String, URLClassLoader> classLoaders = new HashMap<>();
    private HashMap<String, Class> classes = new HashMap<>();
    private HashMap<String, Method> methods = new HashMap<>();

    public TestResult runTest(Test test){
        URLClassLoader classLoader = classLoaders.get(test.getJarName());
        if (classLoader == null) {
            classLoader = getClassLoader(test.getJarName());
            classLoaders.put(test.getJarName(), classLoader);
        }
        Class _class = classes.get(test.getClassName());
        if (_class == null) {
            _class = getClassToLoad(test.getClassName(), classLoader);
            classes.put(test.getClassName(), _class);
        }
        Method _method = methods.get(test.getMethodName());
        if (_method == null) {
            _method = getMethodToLoad(test.getMethodName(), _class);
            methods.put(test.getMethodName(), _method);
        }
        Object _instance = getInstance(_class, test.getConstructorArguments());
        Object _result = getResult(_method, _instance, test.getMethodArguments());
        boolean isExpected = _result.equals(test.getExpectedOutput());
        TestResult testResult = new TestResult(_result, test.getExpectedOutput(), isExpected);
        return testResult;
    }

    private URLClassLoader getClassLoader(String jarName) {
        String jarPath = jarName + ".jar";
        File f = new File(jarPath);
        try {
            return new URLClassLoader(
            new URL[] {f.toURI().toURL()},
            this.getClass().getClassLoader()
    );
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }

    private Class getClassToLoad(String className, URLClassLoader classLoader) {
        Class classToLoad;
        {
            try {
                classToLoad = Class.forName(className, true, classLoader);
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        }
        return classToLoad;
    }

    private Method getMethodToLoad(String methodName, Class classToLoad) {
        Method method;
        {
            try {
                method = classToLoad.getDeclaredMethod(methodName);
            } catch (NoSuchMethodException e) {
                throw new RuntimeException(e);
            }
        }
        return method;
    }

    private Object getInstance(Class classToLoad, Object[] constructorArguments) {
        Object instance;
        Class[] parameterTypes = {double.class, double.class};
        for (int i = 0; i< constructorArguments.length; i++) {
            constructorArguments[i] = Double.parseDouble(constructorArguments[i].toString());
        }
        {
            try {
                instance = classToLoad.getDeclaredConstructor(parameterTypes).newInstance(constructorArguments);
            } catch (InstantiationException e) {
                throw new RuntimeException(e);
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            } catch (InvocationTargetException e) {
                throw new RuntimeException(e);
            } catch (NoSuchMethodException e) {
                throw new RuntimeException(e);
            }
        }
        return instance;
    }

    private Object getResult(Method method, Object instance, Object[] methodArguments) {
        Object result;
        {
            try {
                result = method.invoke(instance, methodArguments);
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            } catch (InvocationTargetException e) {
                throw new RuntimeException(e);
            }
        }
        return result;
    }
}
