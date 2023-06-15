package com.space.designpatterns.creational

import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.Dispatchers.Main
import org.koin.dsl.module
import org.koin.java.KoinJavaComponent.get
import javax.inject.Inject
import javax.inject.Singleton

/** We have [ExampleClassWithDependency] class with [Dependency] dependency on it. */
class ExampleClassWithDependency(val dependency: Dependency)
class Dependency

fun main() {
    /** Manual Injection without Singleton Pattern */
    val dependency = Dependency()
    val exampleClassWithDependency = ExampleClassWithDependency(dependency)


    /** Dependency Injection using Koin with Singleton Pattern */
//    val dependencyWithKoin = get<Dependency>(Dependency::class.java)
//    val exampleClassWithDependencyKoin = ExampleClassWithDependency(dependencyWithKoin)


    /** Dependency Injection using hilt is not available to local variables,
    instead you have to inject it to class property using field or constructor injection. */
//    val main = Main()
//    val dependencyWithHilt = main.dependency
//    val exampleClassWithDependencyHilt = ExampleClassWithDependency(dependencyWithHilt)
}


/** Koin DI module to provide dependency as a singleton */
val module = module {
    single { Dependency() }
}

/** Dagger Hilt DI module to provide dependency as a singleton*/
//@dagger.Module
//@InstallIn(SingletonComponent::class)
//object Module {
//
//    @Provides
//    @Singleton
//    fun provideDependency() = Dependency()
//}
//
//class Main {
//    @Inject
//    lateinit var dependency: Dependency
//}


