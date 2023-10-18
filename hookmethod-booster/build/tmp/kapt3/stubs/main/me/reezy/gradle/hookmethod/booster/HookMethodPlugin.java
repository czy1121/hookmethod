package me.reezy.gradle.hookmethod.booster;

import java.lang.System;

@com.google.auto.service.AutoService(value = {com.didiglobal.booster.task.spi.VariantProcessor.class})
@kotlin.Metadata(mv = {1, 8, 0}, k = 1, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0007\u0018\u00002\u00020\u0001:\u0001\tB\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u0010\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0016\u00a8\u0006\n"}, d2 = {"Lme/reezy/gradle/hookmethod/booster/HookMethodPlugin;", "Lcom/didiglobal/booster/task/spi/VariantProcessor;", "project", "Lorg/gradle/api/Project;", "(Lorg/gradle/api/Project;)V", "process", "", "variant", "Lcom/android/build/gradle/api/BaseVariant;", "PluginExtension", "hookmethod-booster"})
public final class HookMethodPlugin implements com.didiglobal.booster.task.spi.VariantProcessor {
    
    public HookMethodPlugin(@org.jetbrains.annotations.NotNull()
    org.gradle.api.Project project) {
        super();
    }
    
    @java.lang.Override()
    public void process(@org.jetbrains.annotations.NotNull()
    com.android.build.gradle.api.BaseVariant variant) {
    }
    
    @kotlin.Metadata(mv = {1, 8, 0}, k = 1, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0002\b\u000b\b\u0016\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002R \u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0006\u0010\u0007\"\u0004\b\b\u0010\tR \u0010\n\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\u0007\"\u0004\b\f\u0010\tR \u0010\r\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u000e\u0010\u0007\"\u0004\b\u000f\u0010\t\u00a8\u0006\u0010"}, d2 = {"Lme/reezy/gradle/hookmethod/booster/HookMethodPlugin$PluginExtension;", "", "()V", "classes", "", "", "getClasses", "()Ljava/util/List;", "setClasses", "(Ljava/util/List;)V", "excludes", "getExcludes", "setExcludes", "includes", "getIncludes", "setIncludes", "hookmethod-booster"})
    public static class PluginExtension {
        @org.jetbrains.annotations.NotNull()
        private java.util.List<java.lang.String> classes;
        @org.jetbrains.annotations.NotNull()
        private java.util.List<java.lang.String> includes;
        @org.jetbrains.annotations.NotNull()
        private java.util.List<java.lang.String> excludes;
        
        public PluginExtension() {
            super();
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.util.List<java.lang.String> getClasses() {
            return null;
        }
        
        public final void setClasses(@org.jetbrains.annotations.NotNull()
        java.util.List<java.lang.String> p0) {
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.util.List<java.lang.String> getIncludes() {
            return null;
        }
        
        public final void setIncludes(@org.jetbrains.annotations.NotNull()
        java.util.List<java.lang.String> p0) {
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.util.List<java.lang.String> getExcludes() {
            return null;
        }
        
        public final void setExcludes(@org.jetbrains.annotations.NotNull()
        java.util.List<java.lang.String> p0) {
        }
    }
}