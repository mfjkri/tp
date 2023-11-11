package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.Messages.MESSAGE_UNKNOWN_THEME;

import seedu.address.logic.commands.ThemeCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new ThemeCommand object
 */
public class ThemeCommandParser implements Parser<ThemeCommand> {

    /**
     * Parses the given {@code String} in the context of the ThemeCommand
     * and returns a ThemeCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public ThemeCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim();
        if (trimmedArgs.isEmpty()) {
            throw new ParseException(
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, ThemeCommand.MESSAGE_USAGE));
        }

        String theme = trimmedArgs.split("\\s+")[0];

        switch(theme) {

        case "dark":
            return new ThemeCommand("/view/DarkTheme.css", theme);

        case "light":
            return new ThemeCommand("/view/LightTheme.css", theme);

        case "blue":
            return new ThemeCommand("/view/BlueTheme.css", theme);

        default:
            throw new ParseException(String.format(MESSAGE_UNKNOWN_THEME,
                ThemeCommand.MESSAGE_USAGE));
        }
    }
}
