package me.reezy.gradle.hookmethod.booster

import com.android.build.gradle.api.BaseVariant
import com.didiglobal.booster.gradle.project
import com.didiglobal.booster.task.spi.VariantProcessor
import com.google.auto.service.AutoService
import org.gradle.api.Project

@AutoService(VariantProcessor::class)
class HookMethodPlugin(project: Project) : VariantProcessor {

    open class PluginExtension {
        var classes: List<String> = listOf()
        var includes: List<String> = listOf()
        var excludes: List<String> = listOf()
    }

    init {
        project.extensions.create("hookMethod", PluginExtension::class.java)
    }

    override fun process(variant: BaseVariant) {
        val project = variant.project

        val config = project.extensions.getByName("hookMethod") as PluginExtension

        HookMethodTransformer.hookClasses = config.classes.map { it.replace(".", "/") }
        HookMethodTransformer.hookIncludeScopes = config.includes.map { it.replace(".", "/") }
        HookMethodTransformer.hookExcludeScopes = config.excludes.map { it.replace(".", "/") }

        project.dependencies.add("implementation", "me.reezy.gradle:hookmethod-annotation:1.0.0")

    }

}