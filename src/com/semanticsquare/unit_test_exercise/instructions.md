This simple coding exercise simulates a feature of unit testing framework. A unit testing framework like JUnit checks the correctness of  the methods we write. When we run a JUnit Test, the JUnit framework  would run that JUnit Test and reports on things like number of failures, errors along with the stack trace details. In this exercise, we will implement  a simple unit testing framework (a class) that would process another  class (actual Test Case) and reports the number of failures . This exercise uses only the core reflection API part that we saw in the reflection demo lecture.

The unit testing framework is represented by the class SimpleUnitTester, which has a single method execute would take a single parameter whose type is Class. This single parameter is actually a Unit Test and is represented by a class called Reflection , which is provided. In this exercise you would implement the execute method and below is what it should do. Reflection.java does not require any changes.

+ Create an instance of the class that is provided as input to the method
+ Execute all methods of the instance whose names *start with* the keyword test and also return a boolean value.  Just keyword test appearing somewhere other than the beginning of  method name will not qualify that method for further processing. Take a  look at the Reflection class to see the different methods it has. execute method returns the number of qualified methods that returned a false value. Essentially, you can think that execute method is reporting number of failures.

JUnit framework would look for annotation @Test just like the way we are looking for methods starting with keyword 'test'.

testing 123 testing 123 testing
