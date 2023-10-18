package me.reezy.gradle.hookmethod.booster;

import java.lang.System;

@com.google.auto.service.AutoService(value = {com.didiglobal.booster.transform.asm.ClassTransformer.class})
@kotlin.Metadata(mv = {1, 8, 0}, k = 1, d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0007\u0018\u0000 \u00112\u00020\u0001:\u0002\u0011\u0012B\u0005\u00a2\u0006\u0002\u0010\u0002J\u0010\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\tH\u0002J\u0010\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\rH\u0016J\u0018\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u0010\u001a\u00020\u000fH\u0016R\u0014\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0013"}, d2 = {"Lme/reezy/gradle/hookmethod/booster/HookMethodTransformer;", "Lcom/didiglobal/booster/transform/asm/ClassTransformer;", "()V", "hookItems", "", "Lme/reezy/gradle/hookmethod/booster/HookMethodTransformer$HookItem;", "canHook", "", "className", "", "onPreTransform", "", "context", "Lcom/didiglobal/booster/transform/TransformContext;", "transform", "Lorg/objectweb/asm/tree/ClassNode;", "klass", "Companion", "HookItem", "hookmethod-booster"})
public final class HookMethodTransformer implements com.didiglobal.booster.transform.asm.ClassTransformer {
    @org.jetbrains.annotations.NotNull()
    public static final me.reezy.gradle.hookmethod.booster.HookMethodTransformer.Companion Companion = null;
    @org.jetbrains.annotations.NotNull()
    private static java.util.List<java.lang.String> hookClasses;
    @org.jetbrains.annotations.NotNull()
    private static java.util.List<java.lang.String> hookIncludeScopes;
    @org.jetbrains.annotations.NotNull()
    private static java.util.List<java.lang.String> hookExcludeScopes;
    private final java.util.List<me.reezy.gradle.hookmethod.booster.HookMethodTransformer.HookItem> hookItems = null;
    
    public HookMethodTransformer() {
        super();
    }
    
    @java.lang.Override()
    public void onPreTransform(@org.jetbrains.annotations.NotNull()
    com.didiglobal.booster.transform.TransformContext context) {
    }
    
    private final boolean canHook(java.lang.String className) {
        return false;
    }
    
    @org.jetbrains.annotations.NotNull()
    @java.lang.Override()
    public org.objectweb.asm.tree.ClassNode transform(@org.jetbrains.annotations.NotNull()
    com.didiglobal.booster.transform.TransformContext context, @org.jetbrains.annotations.NotNull()
    org.objectweb.asm.tree.ClassNode klass) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public java.lang.String getName() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public java.io.File getReport(@org.jetbrains.annotations.NotNull()
    com.didiglobal.booster.transform.TransformContext context, @org.jetbrains.annotations.NotNull()
    java.lang.String name) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public java.io.File getReportDir(@org.jetbrains.annotations.NotNull()
    com.didiglobal.booster.transform.TransformContext context) {
        return null;
    }
    
    @java.lang.Override()
    public void onPostTransform(@org.jetbrains.annotations.NotNull()
    com.didiglobal.booster.transform.TransformContext context) {
    }
    
    @kotlin.Metadata(mv = {1, 8, 0}, k = 1, d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0019\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B;\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0007\u001a\u00020\u0003\u0012\b\b\u0002\u0010\b\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\tJ\t\u0010\u0015\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u0016\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u0017\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u0018\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u0019\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u001a\u001a\u00020\u0003H\u00c6\u0003JE\u0010\u001b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u00032\b\b\u0002\u0010\u0007\u001a\u00020\u00032\b\b\u0002\u0010\b\u001a\u00020\u0003H\u00c6\u0001J\u0013\u0010\u001c\u001a\u00020\u001d2\b\u0010\u001e\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010\u001f\u001a\u00020 H\u00d6\u0001J\t\u0010!\u001a\u00020\u0003H\u00d6\u0001R\u001a\u0010\b\u001a\u00020\u0003X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\n\u0010\u000b\"\u0004\b\f\u0010\rR\u001a\u0010\u0007\u001a\u00020\u0003X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u000e\u0010\u000b\"\u0004\b\u000f\u0010\rR\u001a\u0010\u0006\u001a\u00020\u0003X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0010\u0010\u000b\"\u0004\b\u0011\u0010\rR\u0011\u0010\u0005\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u000bR\u0011\u0010\u0004\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u000bR\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u000b\u00a8\u0006\""}, d2 = {"Lme/reezy/gradle/hookmethod/booster/HookMethodTransformer$HookItem;", "", "newOwner", "", "newName", "newDesc", "hookOwner", "hookName", "hookDesc", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "getHookDesc", "()Ljava/lang/String;", "setHookDesc", "(Ljava/lang/String;)V", "getHookName", "setHookName", "getHookOwner", "setHookOwner", "getNewDesc", "getNewName", "getNewOwner", "component1", "component2", "component3", "component4", "component5", "component6", "copy", "equals", "", "other", "hashCode", "", "toString", "hookmethod-booster"})
    public static final class HookItem {
        @org.jetbrains.annotations.NotNull()
        private final java.lang.String newOwner = null;
        @org.jetbrains.annotations.NotNull()
        private final java.lang.String newName = null;
        @org.jetbrains.annotations.NotNull()
        private final java.lang.String newDesc = null;
        @org.jetbrains.annotations.NotNull()
        private java.lang.String hookOwner;
        @org.jetbrains.annotations.NotNull()
        private java.lang.String hookName;
        @org.jetbrains.annotations.NotNull()
        private java.lang.String hookDesc;
        
        @org.jetbrains.annotations.NotNull()
        public final me.reezy.gradle.hookmethod.booster.HookMethodTransformer.HookItem copy(@org.jetbrains.annotations.NotNull()
        java.lang.String newOwner, @org.jetbrains.annotations.NotNull()
        java.lang.String newName, @org.jetbrains.annotations.NotNull()
        java.lang.String newDesc, @org.jetbrains.annotations.NotNull()
        java.lang.String hookOwner, @org.jetbrains.annotations.NotNull()
        java.lang.String hookName, @org.jetbrains.annotations.NotNull()
        java.lang.String hookDesc) {
            return null;
        }
        
        @java.lang.Override()
        public boolean equals(@org.jetbrains.annotations.Nullable()
        java.lang.Object other) {
            return false;
        }
        
        @java.lang.Override()
        public int hashCode() {
            return 0;
        }
        
        @org.jetbrains.annotations.NotNull()
        @java.lang.Override()
        public java.lang.String toString() {
            return null;
        }
        
        public HookItem(@org.jetbrains.annotations.NotNull()
        java.lang.String newOwner, @org.jetbrains.annotations.NotNull()
        java.lang.String newName, @org.jetbrains.annotations.NotNull()
        java.lang.String newDesc, @org.jetbrains.annotations.NotNull()
        java.lang.String hookOwner, @org.jetbrains.annotations.NotNull()
        java.lang.String hookName, @org.jetbrains.annotations.NotNull()
        java.lang.String hookDesc) {
            super();
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.lang.String component1() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.lang.String getNewOwner() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.lang.String component2() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.lang.String getNewName() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.lang.String component3() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.lang.String getNewDesc() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.lang.String component4() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.lang.String getHookOwner() {
            return null;
        }
        
        public final void setHookOwner(@org.jetbrains.annotations.NotNull()
        java.lang.String p0) {
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.lang.String component5() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.lang.String getHookName() {
            return null;
        }
        
        public final void setHookName(@org.jetbrains.annotations.NotNull()
        java.lang.String p0) {
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.lang.String component6() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.lang.String getHookDesc() {
            return null;
        }
        
        public final void setHookDesc(@org.jetbrains.annotations.NotNull()
        java.lang.String p0) {
        }
    }
    
    @kotlin.Metadata(mv = {1, 8, 0}, k = 1, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0002\b\u000b\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R \u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0006\u0010\u0007\"\u0004\b\b\u0010\tR \u0010\n\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\u0007\"\u0004\b\f\u0010\tR \u0010\r\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u000e\u0010\u0007\"\u0004\b\u000f\u0010\t\u00a8\u0006\u0010"}, d2 = {"Lme/reezy/gradle/hookmethod/booster/HookMethodTransformer$Companion;", "", "()V", "hookClasses", "", "", "getHookClasses", "()Ljava/util/List;", "setHookClasses", "(Ljava/util/List;)V", "hookExcludeScopes", "getHookExcludeScopes", "setHookExcludeScopes", "hookIncludeScopes", "getHookIncludeScopes", "setHookIncludeScopes", "hookmethod-booster"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.util.List<java.lang.String> getHookClasses() {
            return null;
        }
        
        public final void setHookClasses(@org.jetbrains.annotations.NotNull()
        java.util.List<java.lang.String> p0) {
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.util.List<java.lang.String> getHookIncludeScopes() {
            return null;
        }
        
        public final void setHookIncludeScopes(@org.jetbrains.annotations.NotNull()
        java.util.List<java.lang.String> p0) {
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.util.List<java.lang.String> getHookExcludeScopes() {
            return null;
        }
        
        public final void setHookExcludeScopes(@org.jetbrains.annotations.NotNull()
        java.util.List<java.lang.String> p0) {
        }
    }
}