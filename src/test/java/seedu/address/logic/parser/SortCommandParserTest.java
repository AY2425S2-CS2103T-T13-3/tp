package seedu.address.logic.parser;

import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.SortCommand;

public class SortCommandParserTest {

    private final SortCommandParser parser = new SortCommandParser();

    @Test
    public void parse_noArgs_returnsSortCommand() {
        assertParseSuccess(parser, "", new SortCommand());
    }

    @Test
    public void parse_extraArgs_throwsParseException() {
        assertParseFailure(parser, "extra",
                "Sort command does not accept any arguments.\n" + SortCommand.MESSAGE_USAGE);
    }
}
