package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.MarkScheduleCommand;
import seedu.address.model.schedule.Status;

/**
 * As we are only doing white-box testing, our test cases do not cover path variations
 * outside of the MarkScheduleCommand code. For example, inputs "1" and "1 abc" take the
 * same path through the MarkScheduleCommand, and therefore we test only one of them.
 * The path variation for those two cases occur inside the ParserUtil, and
 * therefore should be covered by the ParserUtilTest.
 */
public class MarkScheduleCommandParserTest {
    private static final String MESSAGE_VALID_SCHEDULE_STATUS = " m/0";

    private static final String MESSAGE_INVALID_FORMAT =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, MarkScheduleCommand.MESSAGE_USAGE);

    private MarkScheduleCommandParser parser = new MarkScheduleCommandParser();

    @Test
    public void parse_missingParts_failure() {
        // no index specified
        assertParseFailure(parser, MESSAGE_VALID_SCHEDULE_STATUS, MESSAGE_INVALID_FORMAT);

        // no field specified
        assertParseFailure(parser, "1", MESSAGE_INVALID_FORMAT);

        // no index and no field specified
        assertParseFailure(parser, "", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidPreamble_failure() {
        // negative index
        assertParseFailure(parser, "-5" + MESSAGE_VALID_SCHEDULE_STATUS, MESSAGE_INVALID_FORMAT);

        // zero index
        assertParseFailure(parser, "0" + MESSAGE_VALID_SCHEDULE_STATUS, MESSAGE_INVALID_FORMAT);

        // invalid arguments being parsed as preamble
        assertParseFailure(parser, "1 some random string", MESSAGE_INVALID_FORMAT);

        // invalid prefix being parsed as preamble
        assertParseFailure(parser, "1 i/" + MESSAGE_VALID_SCHEDULE_STATUS, MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidValue_failure() {
        // invalid Schedule status
        assertParseFailure(parser, "1 m/3", Status.MESSAGE_CONSTRAINTS);

        // invalid Schedule status
        assertParseFailure(parser, "1 m/a", Status.MESSAGE_CONSTRAINTS);
    }

    @Test
    public void parse_validArgs_success() {
        // valid Schedule status for Missed
        assertParseSuccess(parser, "1 m/0", new MarkScheduleCommand(INDEX_FIRST_PERSON, Status.MISSED));

        // valid Schedule status for Completed
        assertParseSuccess(parser, "1 m/1", new MarkScheduleCommand(INDEX_FIRST_PERSON, Status.COMPLETED));
    }
}
