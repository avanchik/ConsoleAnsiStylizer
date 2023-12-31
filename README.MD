Add it to your build.gradle.kts with:

```
repositories {
    mavenCentral()
    maven { url = uri("https://jitpack.io") }
}
```

and:
```
dependencies {
    implementation("com.github.avanchik:ConsoleAnsiStylizer:master-SNAPSHOT")
}
```