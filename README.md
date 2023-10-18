# hookmethod

编译阶段替换被调用方法的工具，可用于隐私合规检测与整改

- 注解 + ASM 修改字节码以替换被调用方法 
- 基于 `com.didiglobal.booster`



## 使用 

在 app 的 build.gradle 中添加

```groovy
buildscript {
    repositories {
        google()
        mavenCentral()
        maven { url "https://gitee.com/ezy/repo/raw/cosmo/"}
    }

    dependencies {
        classpath "com.didiglobal.booster:booster-gradle-plugin:4.16.3"
        classpath "me.reezy.gradle:hookmethod-booster:1.0.0"
    }
} 

apply plugin: 'com.didiglobal.booster'

hookMethod {
    // 在这些类中获取被hook的方法
    classes = ["com.demo.app.PrivacyManager"]
}

```

HookMethod， 将`clazz`类的`method`方法替换成被其注解的方法

```kotlin 
@Target(AnnotationTarget.FUNCTION)
@Retention(AnnotationRetention.BINARY)
annotation class HookMethod(
    val clazz: KClass<*>,           
    val method: String = "",
    val isField: Boolean = false,
    val isStatic: Boolean = false
)
```

使用示例 

```kotlin

@Suppress("DEPRECATION")
@SuppressLint("MissingPermission", "NewApi", "HardwareIds")
object PrivacyManager {
    @JvmStatic
    var isUserGranted: () -> Boolean = { false }


    //<editor-fold desc="android.os.Build">
    private var brand: String = ""
    @HookMethod(Build::class, "BRAND", isField = true, isStatic = true)
    @JvmStatic
    fun getBrand(): String {
        return delegate("Build.BRAND") {
            if (isUserGranted() && brand.isNotEmpty()) {
                brand = Build.BRAND
            }
            brand
        }
    }

    @HookMethod(Build::class, isStatic = true)
    @JvmStatic
    fun getSerial(): String {
        return delegate("BUILD.getSerial()") { if (isUserGranted()) Build.getSerial() else "" }
    }
    //</editor-fold>

    //<editor-fold desc="ActivityManager">
    @HookMethod(ActivityManager::class)
    @JvmStatic
    fun getRunningTasks(manager: ActivityManager, maxNum: Int): List<RunningTaskInfo> { 
        return delegate("getRunningTasks") { if (isUserGranted()) manager.getRunningTasks(maxNum) else emptyList() }
    }
    //</editor-fold>
 
 

    //<editor-fold desc="Settings">  
    @HookMethod(clazz = Settings.Secure::class, method = "getString", isStatic = true)
    @JvmStatic
    fun getSecureString(resolver: ContentResolver, name: String): String? {
        return delegate("getSecureString($name)") {
            if (Settings.Secure.ANDROID_ID != name || isUserGranted()) Settings.Secure.getString(resolver, name) else ""
        }
    }
    //</editor-fold>
 
}
```

处理过程分为两步： 



1. 在 `onPreTransform` 中收集待hook的方法，结束后生成日志 build/reports/HookMethodTransformer/xx/methods.txt

```text
android/os/Build.{BRAND -> getBrand} : Ljava/lang/String; -> ()Ljava/lang/String;
android/os/Build.getSerial : ()Ljava/lang/String; -> ()Ljava/lang/String;
android/app/ActivityManager.getRunningTasks : (I)Ljava/util/List; -> (Landroid/app/ActivityManager;I)Ljava/util/List;
android/telephony/TelephonyManager.getDeviceId : ()Ljava/lang/String; -> (Landroid/telephony/TelephonyManager;)Ljava/lang/String;
android/telephony/TelephonyManager.getImei : ()Ljava/lang/String; -> (Landroid/telephony/TelephonyManager;)Ljava/lang/String;
android/telephony/TelephonyManager.getMeid : ()Ljava/lang/String; -> (Landroid/telephony/TelephonyManager;)Ljava/lang/String;
android/telephony/TelephonyManager.getSimSerialNumber : ()Ljava/lang/String; -> (Landroid/telephony/TelephonyManager;)Ljava/lang/String;
android/telephony/TelephonyManager.getSubscriberId : ()Ljava/lang/String; -> (Landroid/telephony/TelephonyManager;)Ljava/lang/String;
java/net/NetworkInterface.getHardwareAddress : ()[B -> (Ljava/net/NetworkInterface;)[B
android/provider/Settings$System.{getString -> getSystemString} : (Landroid/content/ContentResolver;Ljava/lang/String;)Ljava/lang/String; -> (Landroid/content/ContentResolver;Ljava/lang/String;)Ljava/lang/String;
android/provider/Settings$Secure.{getString -> getSecureString} : (Landroid/content/ContentResolver;Ljava/lang/String;)Ljava/lang/String; -> (Landroid/content/ContentResolver;Ljava/lang/String;)Ljava/lang/String;
android/net/wifi/WifiInfo.getMacAddress : ()Ljava/lang/String; -> (Landroid/net/wifi/WifiInfo;)Ljava/lang/String;
android/net/wifi/WifiInfo.getSSID : ()Ljava/lang/String; -> (Landroid/net/wifi/WifiInfo;)Ljava/lang/String;
android/net/wifi/WifiInfo.getBSSID : ()Ljava/lang/String; -> (Landroid/net/wifi/WifiInfo;)Ljava/lang/String;
android/net/wifi/WifiInfo.getRssi : ()I -> (Landroid/net/wifi/WifiInfo;)I
android/net/wifi/WifiInfo.getNetworkId : ()I -> (Landroid/net/wifi/WifiInfo;)I

```

2. 在 `transform` 将待hook的方法调用替换为目标方法调用，结束后生成日志 build/reports/HookMethodTransformer/xx/hooks.txt

```text
GETSTATIC, android/os/Build.BRAND -> com/google/android/material/color/DynamicColors.isDynamicColorAvailable
INVOKESTATIC, android/provider/Settings$Secure.getString -> androidx/core/app/NotificationManagerCompat.getEnabledListenerPackages
INVOKESTATIC, android/provider/Settings$Secure.getString -> androidx/core/location/LocationManagerCompat.isLocationEnabled
INVOKEVIRTUAL, android/telephony/TelephonyManager.getImei -> androidx/core/telephony/TelephonyManagerCompat$Api26Impl.getImei
INVOKEVIRTUAL, android/telephony/TelephonyManager.getDeviceId -> androidx/core/telephony/TelephonyManagerCompat.getImei
GETSTATIC, android/os/Build.BRAND -> com/demo/app/MainActivity.onCreate
INVOKESTATIC, android/os/Build.getSerial -> com/demo/app/MainActivity.onCreate
INVOKEVIRTUAL, android/app/ActivityManager.getRunningTasks -> com/demo/app/MainActivity.onCreate
INVOKESTATIC, android/provider/Settings$Secure.getString -> com/demo/app/MainActivity.onCreate
```

## LICENSE

The Component is open-sourced software licensed under the [Apache license](LICENSE).