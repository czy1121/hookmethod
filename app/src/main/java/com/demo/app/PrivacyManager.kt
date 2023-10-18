package com.demo.app

import android.annotation.SuppressLint
import android.app.ActivityManager
import android.app.ActivityManager.RunningTaskInfo
import android.content.ContentResolver
import android.net.wifi.WifiInfo
import android.os.Build
import android.provider.Settings
import android.telephony.TelephonyManager
import android.util.Log
import me.reezy.gradle.hookmethod.annotation.HookMethod
import java.net.NetworkInterface

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
//        return delegate("getRunningTasks") { emptyList() }
        return delegate("getRunningTasks") { if (isUserGranted()) manager.getRunningTasks(maxNum) else emptyList() }
    }
    //</editor-fold>

    //<editor-fold desc="TelephonyManager">
    @HookMethod(TelephonyManager::class)
    @JvmStatic
    fun getDeviceId(manager: TelephonyManager): String? {
        return delegate("TelephonyManager.deviceId") { if (isUserGranted()) manager.deviceId else "" }
    }

    @HookMethod(TelephonyManager::class)
    @JvmStatic
    fun getImei(manager: TelephonyManager): String? {
        return delegate("imei") { if (isUserGranted()) manager.imei else "" }
    }

    @HookMethod(TelephonyManager::class)
    @JvmStatic
    fun getMeid(manager: TelephonyManager): String? {
        return delegate("meid") { if (isUserGranted()) manager.meid else null }
    }

    @HookMethod(TelephonyManager::class)
    @JvmStatic
    fun getSimSerialNumber(manager: TelephonyManager): String? {
        return delegate("simSerialNumber") { if (isUserGranted()) manager.simSerialNumber else "" }
    }

    @HookMethod(TelephonyManager::class)
    @JvmStatic
    fun getSubscriberId(manager: TelephonyManager): String {
        return delegate("subscriberId") { if (isUserGranted()) manager.subscriberId else "" }
    }
    //</editor-fold>

    //<editor-fold desc="NetworkInterface">
    @HookMethod(NetworkInterface::class)
    @JvmStatic
    fun getHardwareAddress(network: NetworkInterface): ByteArray? {
//        return delegate("hardwareAddress") { ByteArray(0) }
        return delegate("hardwareAddress") {
            if (isUserGranted()) {
                network.hardwareAddress
            } else {
                ByteArray(0)
            }
        }
    }
    //</editor-fold>

    //<editor-fold desc="Settings">
    @HookMethod(clazz = Settings.System::class, method = "getString", isStatic = true)
    @JvmStatic
    fun getSystemString(resolver: ContentResolver, name: String): String? {
        return delegate("getSystemString($name)") {
            if (Settings.System.ANDROID_ID != name || isUserGranted()) Settings.System.getString(resolver, name) else ""
        }
    }

    @HookMethod(clazz = Settings.Secure::class, method = "getString", isStatic = true)
    @JvmStatic
    fun getSecureString(resolver: ContentResolver, name: String): String? {
        return delegate("getSecureString($name)") {
            if (Settings.Secure.ANDROID_ID != name || isUserGranted()) Settings.Secure.getString(resolver, name) else ""
        }
    }
    //</editor-fold>

    //<editor-fold desc="WifiInfo">
    @HookMethod(WifiInfo::class)
    @JvmStatic
    fun getMacAddress(wifiInfo: WifiInfo): String? {
//        return delegate("macAddress") { "02:00:00:00:00:00" }
        return delegate("macAddress") { if (isUserGranted()) wifiInfo.macAddress else "" }
    }

    @HookMethod(WifiInfo::class)
    @JvmStatic
    fun getSSID(wifiInfo: WifiInfo): String? {
        return delegate("SSID") { if (isUserGranted()) wifiInfo.ssid else "" }
    }

    @HookMethod(WifiInfo::class)
    @JvmStatic
    fun getBSSID(wifiInfo: WifiInfo): String? {
//        return delegate("BSSID") { "" }
        return delegate("BSSID") { if (isUserGranted()) wifiInfo.bssid else "" }
    }

    @HookMethod(WifiInfo::class)
    @JvmStatic
    fun getRssi(wifiInfo: WifiInfo): Int {
        return delegate("Rssi") { if (isUserGranted()) wifiInfo.rssi else 0 }
    }

    @HookMethod(WifiInfo::class)
    @JvmStatic
    fun getNetworkId(wifiInfo: WifiInfo): Int {
        return delegate("networkId") { if (isUserGranted()) wifiInfo.networkId else 0 }
    }
    //</editor-fold>

    //<editor-fold desc="PackageManager">
//    @HookMethod(PackageManager::class)
//    @JvmStatic
//    fun getPackageInfo(pm: PackageManager, packageName: String, flags: Int): PackageInfo? {
//        return delegate("getPackageInfo($packageName, $flags)") { if (isUserGranted()) pm.getPackageInfo(packageName, flags) else PackageInfo() }
//    }
    //</editor-fold>


    private fun <T> delegate(name: String, value: () -> T): T {
        val result = value()
        Log.e("OoO.privacy", "$name => $result, agreed = ${isUserGranted()}")
        return result
    }
}