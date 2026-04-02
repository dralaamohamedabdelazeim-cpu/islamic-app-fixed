# Keep Android framework classes
-keep class android.** { *; }
-keep class androidx.** { *; }

# Keep our app classes
-keep class com.islamic.app.** { *; }

# Keep JSON
-keep class org.json.** { *; }

# Keep native methods
-keepclasseswithmembernames class * {
    native <methods>;
}

# Optimization settings
-optimizationpasses 5
-dontusemixedcaseclassnames
-verbose

# Remove logging
-assumenosideeffects class android.util.Log {
    public static *** d(...);
    public static *** v(...);
    public static *** i(...);
}
