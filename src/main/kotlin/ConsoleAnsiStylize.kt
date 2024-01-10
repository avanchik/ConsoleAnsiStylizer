object ConsoleAnsiStylize {
    /**
     * ANSI escape sequence prefix. This is used to initiate ANSI styling in console output.
     */
    const val ANSI_ESC_PREF = "\u001B["

    /**
     * ANSI escape sequence suffix. This is used to terminate ANSI styling in console output.
     */
    const val ANSI_ESC_SUFF = "m"

    /**
     * ANSI escape sequence to cancel all styles and colors. It resets the text to the default style.
     */
    const val RESET = "${ANSI_ESC_PREF}0$ANSI_ESC_SUFF"


    enum class Styles(val code: Int, val endCode: Int) {
        Bold(1, 22),
        Dim(2, 22),
        Italic(3, 23),
        Underline(4, 24),
        SlowBlink(5, 25),
        RapidBlink(6, 25),
        Reverse(7, 27),
        Hide(8, 28),
        Strikethrough(9, 29),
        Framed(51, 54),
        Encircled(52, 54),
        Overlined(53, 55)
    }

    enum class Colors(val code: Int) {
        Default(39),
        Black(30),
        Red(31),
        Green(32),
        Yellow(33),
        Blue(34),
        Magenta(35),
        Cyan(36),
        White(37),
        BrightBlack(90),
        BrightRed(91),
        BrightGreen(92),
        BrightYellow(93),
        BrightBlue(94),
        BrightMagenta(95),
        BrightCyan(96),
        BrightWhite(97)
    }

    enum class ColorCodeOffset(val offset: Int) {
        FgColors(0),
        BgColors(10),
        BrightColors(60),
    }

    fun generateAnsiCode(
        fgColor: Colors = Colors.Default,
        bgColor: Colors = Colors.Default,
        vararg styles: Styles,
        isEndCodes: Boolean = false
    ): String {
        val styleCodes = styles.map { it.code }.toSet()
        val styleEndCodes = styles.map { it.endCode }.toSet()
        val colorCodes = setOf(fgColor.code, bgColor.code + ColorCodeOffset.BgColors.offset)

        val codes = (styleCodes + colorCodes).joinToString(";")
        val endCodes = (styleEndCodes + colorCodes).joinToString(";")

        return if (isEndCodes) "$ANSI_ESC_PREF$endCodes$ANSI_ESC_SUFF" else "$ANSI_ESC_PREF$codes$ANSI_ESC_SUFF"
    }

    fun stylizeText(
        text: String,
        fgColor: Colors = Colors.Default,
        bgColor: Colors = Colors.Default,
        vararg styles: Styles
    ): String {
        val code = generateAnsiCode(
            isEndCodes = false,
            fgColor = fgColor,
            bgColor = bgColor,
            styles = styles
        )

        val endCode = generateAnsiCode(
            isEndCodes = true,
            styles = styles
        )
        return "$code$text$endCode"
    }

    data class AnsiStyles(
        var fgColor: Colors = Colors.Default,
        var bgColor: Colors = Colors.Default,
        val styles: MutableSet<Styles>
    ) {
        var code: String = ""
            private set
            get() {
                if (isChanged) updateCodes()
                return field
            }

        var endCode: String = ""
            private set
            get() {
                if (isChanged) updateCodes()
                return field
            }

        private var isChanged = true

        private fun generateAnsiCode(
            isEndCodes: Boolean = false
        ): String {
            val styleCodes = styles.map { it.code }.toSet()
            val styleEndCodes = styles.map { it.endCode }.toSet()
            val colorCodes = setOf(fgColor.code, bgColor.code + ColorCodeOffset.BgColors.offset)

            val codes = (styleCodes + colorCodes).joinToString(";")
            val endCodes = (styleEndCodes + colorCodes).joinToString(";")

            return if (isEndCodes) "$ANSI_ESC_PREF$endCodes$ANSI_ESC_SUFF" else "$ANSI_ESC_PREF$codes$ANSI_ESC_SUFF"
        }

        private fun updateCodes() {
            code = generateAnsiCode()
            endCode = generateAnsiCode(true)
            isChanged = false
        }
    }
}