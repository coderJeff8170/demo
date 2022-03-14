package com.semanticsquare.unit_test_exercise;

import com.semanticsquare.jvm.User;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

public class SimpleUnitTester {

    //will return the number of methods in clazz whose name starts with "test" and return false (numFailures).
    public int execute(Class clazz) throws Exception {
        int failedCount = 0;

        // your code
        // Create an instance of the class that is provided as input to the method
        //invoke constructor on class under test
//        Constructor< Reflection > reflectionConstructor = clazz.getDeclaredConstructor();
        //create an instance using that constructor instance
//        Reflection instance = reflectionConstructor.newInstance();
//        System.out.println(reflection);
        Object object = null;
        try {
            object = clazz.newInstance(); // MUST HAVE DEFAULT CONSTRUCTOR
        } catch(InstantiationException e) {
            System.out.println("Can't instantiate ...");
        } catch (IllegalAccessException e) {
            System.out.println("Can't access ...");
        }

        for(Method m : clazz.getDeclaredMethods()) {
            //only invoke if the name starts with test, and increment failed count if return value is false
            if(m.getName().startsWith("test")) {
                Object result = m.invoke(object);
                if(result.equals(false)) {
                    failedCount++;
                }
            }
        }


        //Execute all methods of the instance whose names start with the keyword test and also return a boolean value
        return failedCount;
    }

    public static void main(String[] args) throws Exception {
        SimpleUnitTester tester = new SimpleUnitTester();
//        tester.execute(Reflection.class);
        int result = tester.execute(Class.forName("com.semanticsquare.unit_test_exercise.Reflection"));
        System.out.println(result);
    }
}
