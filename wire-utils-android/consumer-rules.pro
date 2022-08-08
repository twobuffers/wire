# Rule required to make delegates Fragment.viewBinding and ViewGroup.viewBinding working properly
# Simlar to the case in Epoxy delegates:
# https://github.com/airbnb/epoxy/blob/6d4365138cfa60f431cd396ecb7064feef25bea5/kotlinsample/src/main/java/com/airbnb/epoxy/kotlinsample/helpers/ViewBindingKotlinModel.kt#L17
-keepclassmembers class * extends androidx.viewbinding.ViewBinding {
    public static *** bind(android.view.View);
    public static *** inflate(android.view.View);
}
