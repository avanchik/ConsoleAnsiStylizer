import ConsoleAnsiStylize.Colors
import ConsoleAnsiStylize.Styles
import ConsoleAnsiStylize.generateAnsiCode
import ConsoleAnsiStylize.stylizeText
import java.time.Instant

fun main() {
    forEachAllStylize()
    println()
    showAnsiStylesExample2()
}

fun forEachAllStylize() {
    println("\nDifferent text styles:")
    Styles.entries.forEach { print(stylizeText(it.name, styles = arrayOf(it)) + " ") }

    println("\nDifferent text colors:")
    Colors.entries.forEach {
        print(stylizeText(it.name, fgColor = it) + " ")
    }
    println()
    println("\nDifferent background colors:")
    Colors.entries.forEach {
        print(stylizeText("${it.name} bg", bgColor = it) + " ")
    }
    println()
}

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
                "with regular text and a link to ${a}127.0.0.1:8080$t in the middle.\nMore text to demonstrate" +
                " ANSI styles.$descriptionStyle //And this is text with a style like the comments in the code." +
                "\n$timeStyle${Instant.now()}$t ${logTagStyle}LOG_TAG$t #>\tRegular text again and some more text."
    )
}
