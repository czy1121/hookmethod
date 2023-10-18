package me.reezy.gradle.hookmethod.annotation;

import kotlin.reflect.KClass


@Target(AnnotationTarget.FUNCTION)
@Retention(AnnotationRetention.BINARY)
annotation class HookMethod(
    val clazz: KClass<*>,
    val method: String = "",
    val isField: Boolean = false,
    val isStatic: Boolean = false
)

