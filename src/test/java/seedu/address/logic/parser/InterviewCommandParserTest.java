package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.InterviewCommand;
import seedu.address.model.person.Duration;
import seedu.address.model.person.StartTime;

public class InterviewCommandParserTest {

    private InterviewCommandParser parser = new InterviewCommandParser();

    @Test
    public void parse_validArgs_returnsInterviewCommand() {
        assertParseSuccess(parser, "1 2025-04-01 10:00 30",
                new InterviewCommand(INDEX_FIRST_PERSON, new StartTime("2025-04-01 10:00"), new Duration("30")));
    }

    @Test
    public void parse_extraSpaces_returnsInterviewCommand() {
        assertParseSuccess(parser, "  1   2025-04-01    10:15    45 ",
                new InterviewCommand(INDEX_FIRST_PERSON, new StartTime("2025-04-01 10:15"), new Duration("45")));
    }

    @Test
    public void parse_missingArgs_failure() {
        // no index
        assertParseFailure(parser, "2025-04-01 10:00 30",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, InterviewCommand.MESSAGE_USAGE));

        // no date
        assertParseFailure(parser, "1 10-00 30",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, InterviewCommand.MESSAGE_USAGE));

        // no time
        assertParseFailure(parser, "1 2025-04-01 30",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, InterviewCommand.MESSAGE_USAGE));

        // no duration
        assertParseFailure(parser, "1 2025-04-01 10-00",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, InterviewCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_invalidIndex_failure() {
        assertParseFailure(parser, "0 25-04-01 10-00 30",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, InterviewCommand.MESSAGE_USAGE));
        assertParseFailure(parser, "-1 25-04-01 10-00 30",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, InterviewCommand.MESSAGE_USAGE));
        assertParseFailure(parser, "abc 25-04-01 10-00 30",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, InterviewCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_invalidStartTime_failure() {
        assertParseFailure(parser, "1 invalid-date 10-00 30",
                StartTime.MESSAGE_CONSTRAINTS);

        assertParseFailure(parser, "1 2025-04-01 invalid 30",
                StartTime.MESSAGE_CONSTRAINTS);
    }

    @Test
    public void parse_invalidDuration_failure() {
        assertParseFailure(parser, "1 2025-04-01 10:00 -30",
                Duration.MESSAGE_CONSTRAINTS);
        assertParseFailure(parser, "1 2025-04-01 10:00 notanumber",
                Duration.MESSAGE_CONSTRAINTS);
        assertParseFailure(parser, "1 2025-04-01 10:00 17", // not a multiple of 5
                Duration.MESSAGE_CONSTRAINTS);
    }
}
