# Source: https://github.com/airbnb/epoxy/blob/6d4365138cfa60f431cd396ecb7064feef25bea5/kotlinsample/src/main/java/com/airbnb/epoxy/kotlinsample/helpers/ViewBindingKotlinModel.kt#L17
-keepclassmembers class * extends androidx.viewbinding.ViewBinding {
    public static *** bind(android.view.View);
}
