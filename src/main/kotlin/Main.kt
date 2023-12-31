import ConsoleAnsiStylize.Colors
import ConsoleAnsiStylize.Styles
import ConsoleAnsiStylize.stylizeText

fun main() {
    println("\nРазные стили текста:")
    Styles.entries.forEach { print(stylizeText(it.name, styles = arrayOf(it)) + " ") }

    println("\nРазные цвета текста:")
    Colors.entries.forEach {
        print(stylizeText(it.name, fgColor = it) + " ")
    }
    println()

    println("\nРазные цвета фона:")
    Colors.entries.forEach {
        print(stylizeText("${it.name} фон", bgColor = it) + " ")
    }
    println()
}