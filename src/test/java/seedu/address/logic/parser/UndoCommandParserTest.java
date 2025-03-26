package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.UndoCommand;



public class UndoCommandParserTest {

    private UndoCommandParser parser = new UndoCommandParser();

    @Test
    public void parse_validArgs_returnsUndoCommand() {
        assertParseSuccess(parser, "", new UndoCommand());
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {
        // no index
        assertParseFailure(parser, "what ever user input",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, UndoCommand.MESSAGE_USAGE));

        // non-numeric index
        assertParseFailure(parser, "abc", String.format(MESSAGE_INVALID_COMMAND_FORMAT, UndoCommand.MESSAGE_USAGE));

        // negative index
        assertParseFailure(parser, "-1", String.format(MESSAGE_INVALID_COMMAND_FORMAT, UndoCommand.MESSAGE_USAGE));

        // zero index
        assertParseFailure(parser, "0", String.format(MESSAGE_INVALID_COMMAND_FORMAT, UndoCommand.MESSAGE_USAGE));
    }
}
