package me.reezy.gradle.hookmethod.booster

import com.didiglobal.booster.transform.Supervisor
import com.didiglobal.booster.transform.TransformContext
import com.didiglobal.booster.transform.asm.ClassTransformer
import com.didiglobal.booster.transform.asm.asClassNode
import com.google.auto.service.AutoService
import me.reezy.gradle.hookmethod.annotation.HookMethod
import org.objectweb.asm.Opcodes
import org.objectweb.asm.tree.ClassNode
import org.objectweb.asm.tree.FieldInsnNode
import org.objectweb.asm.tree.MethodInsnNode
import org.objectweb.asm.util.Printer
import kotlin.reflect.jvm.jvmName

@AutoService(ClassTransformer::class)
class HookMethodTransformer : ClassTransformer {

    companion object {
        var hookClasses: List<String> = listOf()
        var hookIncludeScopes: List<String> = listOf()
        var hookExcludeScopes: List<String> = listOf()
    }

    data class HookItem(
        val newOwner: String,
        val newName: String,
        val newDesc: String,
        var hookOwner: String = "",
        var hookName: String = "",
        var hookDesc: String = "",
    )

//    private val excludes = listOf("android.", "androidx.", "java.", "javax.", "kotlin.", "kotlinx.", "dalvik.")

    private val hookItems = mutableListOf<HookItem>()


    override fun onPreTransform(context: TransformContext) {
        val reportFile = getReport(context, "methods.txt")
        reportFile.writeText("")
        getReport(context, "hooks.txt").delete()

        context.registerCollector(object : Supervisor {
            private val hookMethodDesc = "L${HookMethod::class.jvmName.replace(".", "/")};"

            override fun accept(name: String): Boolean {
                return name.endsWith(".class") && hookClasses.contains(name.substring(0, name.length - 6))
            }

            override fun collect(name: String, data: () -> ByteArray) {
                try {
                    val classNode = data().asClassNode()
                    classNode.methods.forEach { method ->
                        val annotation = method.invisibleAnnotations?.find { it.desc == hookMethodDesc } ?: return@forEach
                        val hook = HookItem(classNode.name, method.name, method.desc)
                        val values = annotation.values
                        var isField = false
                        var isStatic = false
                        (0 until values.size step 2).forEach {
                            if (values[it] == "clazz") {
                                val owner = values[it + 1].toString()
                                hook.hookOwner = owner.substring(1, owner.length - 1)
                            } else if (values[it] == "method") {
                                hook.hookName = values[it + 1].toString()
                            } else if (values[it] == "isField") {
                                isField = values[it + 1] == true
                            } else if (values[it] == "isStatic") {
                                isStatic = values[it + 1] == true
                            }
                        }

                        hook.hookDesc = when {
                            // 静态字段：
                            // android.os.Build.BRAND -> ()Ljava/lang/String; -> Ljava/lang/String;
                            isField && isStatic -> hook.newDesc.substring(2)

                            // 静态方法
                            // android.os.Build.getSerial -> ()Ljava/lang/String;
                            // android.provider.Settings.System.getString -> (Landroid/content/ContentResolver;Ljava/lang/String;)Ljava/lang/String;
                            isStatic -> hook.newDesc

                            // 实例字段
                            // (LhookOwner;)Ljava/lang/String; -> Ljava/lang/String;
                            isField -> hook.newDesc.replace("(L${hook.hookOwner};)", "")

                            // 实例方法
                            // android.app.ActivityManager.getRunningTasks -> (Landroid/app/ActivityManager;I)Ljava/util/List; -> (I)Ljava/util/List;
                            else -> hook.newDesc.replace("(L${hook.hookOwner};", "(")
                        }

                        if (hook.hookName == "") {
                            hook.hookName = hook.newName
                        }
                        hookItems.add(hook)

                        val hookName = if (hook.hookName == hook.newName) hook.hookName else "{${hook.hookName} -> ${hook.newName}}"

                        reportFile.appendText("${hook.hookOwner}.$hookName : ${hook.hookDesc} -> ${hook.newDesc}\n")
                    }
                } catch (ex: Throwable) {
                    reportFile.appendText("$name ==> $ex")
                }
            }
        })
    }

    private fun canHook(className: String): Boolean {
        return (hookClasses.isEmpty() || !hookClasses.any { className.startsWith(it) })
                && (hookIncludeScopes.isEmpty() || hookIncludeScopes.any { className.startsWith(it) })
                && (hookExcludeScopes.isEmpty() || !hookExcludeScopes.any { className.startsWith(it) })
    }

    override fun transform(context: TransformContext, klass: ClassNode): ClassNode {

        val className = klass.name
        if (canHook(className)) {
            val hooksFile = getReport(context, "hooks.txt")
            klass.methods.forEach { method ->
                method.instructions?.iterator()?.forEach { insn ->
                    if (insn is MethodInsnNode) {
                        hookItems.find { it.hookOwner == insn.owner && it.hookName == insn.name && it.hookDesc == insn.desc }?.let { item ->
                            hooksFile.appendText("${Printer.OPCODES[insn.opcode]}, ${item.hookOwner}.${item.hookName} -> ${klass.name}.${method.name}\n")
                            insn.opcode = Opcodes.INVOKESTATIC
                            insn.owner = item.newOwner
                            insn.name = item.newName
                            insn.desc = item.newDesc
                        }
                    } else if (insn is FieldInsnNode) {
                        hookItems.find { it.hookOwner == insn.owner && it.hookName == insn.name && it.hookDesc == insn.desc }?.let { item ->
                            hooksFile.appendText("${Printer.OPCODES[insn.opcode]}, ${item.hookOwner}.${item.hookName} -> ${klass.name}.${method.name}\n")
                            insn.opcode = Opcodes.INVOKESTATIC
                            insn.owner = item.newOwner
                            insn.name = item.newName
                            insn.desc = item.newDesc
                        }
                    }
                }
            }
        }
        return klass
    }
}