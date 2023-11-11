package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.Messages.MESSAGE_UNKNOWN_THEME;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.ThemeCommand;

public class ThemeCommandParserTest {

    private ThemeCommandParser parser = new ThemeCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ", String.format(MESSAGE_INVALID_COMMAND_FORMAT, ThemeCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_unknownTheme_throwsParseException() {
        assertParseFailure(parser, "invalidTheme", String.format(MESSAGE_UNKNOWN_THEME,
            ThemeCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validArgs_returnsThemeCommand() {
        ThemeCommand expectedThemeCommand1 =
            new ThemeCommand("/view/DarkTheme.css", "dark");
        assertParseSuccess(parser, "dark", expectedThemeCommand1);

        ThemeCommand expectedThemeCommand2 =
            new ThemeCommand("/view/LightTheme.css", "light");
        assertParseSuccess(parser, "light", expectedThemeCommand2);

        ThemeCommand expectedThemeCommand3 =
            new ThemeCommand("/view/BlueTheme.css", "blue");
        assertParseSuccess(parser, "blue", expectedThemeCommand3);
    }
}
