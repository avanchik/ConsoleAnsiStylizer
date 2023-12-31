import ConsoleAnsiStylize.Colors
import ConsoleAnsiStylize.Styles
import ConsoleAnsiStylize.stylizeText

fun main() {
    // Демонстрация разных стилей текста
    println("\nРазные стили текста:")
    Styles.entries.forEach { print(stylizeText(it.name, styles = arrayOf(it)) + " ") }

    // Демонстрация разных цветов текста
    println("\nРазные цвета текста:")
    Colors.entries.forEach {
        print(stylizeText(it.name, fgColor = it) + " ")
    }
    println()

    // Демонстрация разных цветов фона
    println("\nРазные цвета фона:")
    Colors.entries.forEach {
        print(stylizeText("${it.name} фон", bgColor = it) + " ")
    }
    println()
}