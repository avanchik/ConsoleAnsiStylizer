<hr/>

### To get this project into your build:

<details><summary>Gradle Kotlin DSL</summary>
Step 1. Add the JitPack repository to your build file

```kotlin
repositories {
    mavenCentral()
    maven { url = uri("https://jitpack.io") }
}
```

Step 2. Add the dependency

```kotlin
dependencies {
    implementation("com.github.avanchik:ConsoleAnsiStylizer:master-SNAPSHOT")
}
```

</details><details><summary> Gradle Groovy DSL</summary>

Step 1. Add the JitPack repository to your build file
```groovy
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        mavenCentral()
        maven { url 'https://jitpack.io' }
    }
}
```

Step 2. Add the dependency
```groovy
dependencies {
    implementation 'com.github.avanchik:ConsoleAnsiStylizer:master-SNAPSHOT'
}
```
</details><details><summary>Maven</summary>

Step 1. Add the JitPack repository to your build file
```xml
<repositories>
    <repository>
        <id>jitpack.io</id>
        <url>https://jitpack.io</url>
    </repository>
</repositories>
```
Step 2. Add the dependency
```xml
<dependency>
    <groupId>com.github.avanchik</groupId>
    <artifactId>ConsoleAnsiStylizer</artifactId>
    <version>master-SNAPSHOT</version>
</dependency>
```
</details><hr>

### Examples

#### Example 1
```kotlin
fun forEachAllStylize() {
    println("\n\nDifferent text styles:")
    Styles.entries.forEachIndexed { i, style ->
        print(stylizeText(style.name, styles = arrayOf(style)) + if ((i + 1) % 7 == 0) "\n" else " ")
    }
    println("\n\nDifferent text colors:")
    Colors.entries.forEachIndexed { i, color ->
        print(stylizeText(color.name, fgColor = color) + if ((i + 1) % 6 == 0) "\n" else " ")
    }
    println("\n\nDifferent background colors:")
    Colors.entries.forEachIndexed { i, color ->
        print(stylizeText(" ${color.name} bg ", fgColor = Colors.Black, bgColor = color)
                + if ((i + 1) % 6 == 0) "\n" else " ")
    }
    println()
}
```
The output:
#### intellij terminal
![1.intellij.png](https://github.com/avanchik/ConsoleAnsiStylizer/blob/master/readme_imgs/1.intellij.png)
#### MacOs terminal
![1.macos.png](https://github.com/avanchik/ConsoleAnsiStylizer/blob/master/readme_imgs/1.macos.png)
<hr>

#### Example 2
```kotlin
fun showAnsiStylesExample2() {
    val h1 = generateAnsiCode(
        fgColor = Colors.BrightWhite,
        styles = arrayOf(Styles.Bold, Styles.Underline)
    )
    val t = ConsoleAnsiStylize.RESET
    val b = generateAnsiCode(styles = arrayOf(Styles.Bold))
    val a = generateAnsiCode(
        fgColor = Colors.BrightBlue,
        styles = arrayOf(Styles.Dim, Styles.Underline)
    )
    val timeStyle = generateAnsiCode(Colors.Green, Colors.Black)
    val descriptionStyle = generateAnsiCode(
        fgColor = Colors.BrightBlack,
        bgColor = Colors.Default,
        Styles.Italic
    )
    val logTagStyle = generateAnsiCode(
        fgColor = Colors.Black,
        bgColor = Colors.Yellow,
        Styles.Bold
    )

    println(
        "\n$h1 This is an example of an h1 heading $t\nThis is regular text$b, and this is bold text$t in the middle " +
                "of regular text.\n\n$timeStyle${Instant.now()}$t #>\tThis is the current timestamp " +
                "with regular text\n and a link to ${a}127.0.0.1:8080$t in the middle.\nMore text to demonstrate" +
                " ANSI styles.$descriptionStyle //Style like the comments in the code." +
                "\n$timeStyle${Instant.now()}$t ${logTagStyle}LOG_TAG$t #>\tRegular text again and some more text.\n"
    )
}
```
The output:
![2.macos.png](https://github.com/avanchik/ConsoleAnsiStylizer/blob/master/readme_imgs/2.macos.png)
<hr>