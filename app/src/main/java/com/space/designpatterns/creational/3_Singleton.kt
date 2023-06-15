package com.space.designpatterns.creational

/* Class for reference */
class ExampleReference

/* Singleton pattern for [ExampleReference] */
object SingletonExample {
    private var singletonExample: ExampleReference? = null
    fun getInstance(): ExampleReference {
        if (singletonExample == null) {
            singletonExample = ExampleReference()
        }
        return singletonExample!!
    }
}

fun main() {
    /* Create a reference using Singleton pattern. Comparing HashCodes will return true,
       because it does not create another object, but uses the old reference if it is created. */
    val singletonExample1 = SingletonExample.getInstance()
    val singletonExample2 = SingletonExample.getInstance()
    println(singletonExample1.hashCode() == singletonExample2.hashCode())

    /* Create a reference without Singleton pattern. Comparing HashCodes will return false,
       because it creates another object, despite it has the old reference or not.*/
    val nonSingletonExample1 = ExampleReference()
    val nonSingletonExample2 = ExampleReference()
    println(nonSingletonExample1.hashCode() == nonSingletonExample2.hashCode())
}

