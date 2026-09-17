// Top-level build file. Plugin versions are declared here (apply false)
// and applied without a version in the :app module build file.
//
// AGP is pinned to 9.0.0 because that's the newest version this Android
// Studio install can drive (Studio reported "Latest supported version is
// AGP 9.0.0" on sync). AGP 9.3.0 exists upstream but requires a newer
// Studio release than what's installed. If you later update Android
// Studio itself, you can raise this — see README.md.
//
// AGP 9.0+ ships built-in Kotlin support and provides its own matched
// Kotlin/Compose compiler internally — applying the standalone
// org.jetbrains.kotlin.android (and org.jetbrains.kotlin.plugin.compose)
// plugins on top of that conflicts with it ("Cannot add extension with
// name 'kotlin'"). Neither plugin is declared here for that reason.
plugins {
    id("com.android.application") version "9.0.1" apply false
    id("org.jetbrains.kotlin.plugin.compose") version "2.2.10" apply false
}