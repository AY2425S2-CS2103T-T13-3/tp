package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.Messages.MESSAGE_NOTES_CHARACTER_LIMIT_EXCEEDED;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.NotesCommand;
import seedu.address.model.person.Notes;

public class NotesCommandParserTest {

    private NotesCommandParser parser = new NotesCommandParser();

    @Test
    public void parse_validNotesText_returnsNotesCommand() {
        // with notes
        assertParseSuccess(parser, "1 Test notes",
                new NotesCommand(INDEX_FIRST_PERSON, new Notes("Test notes")));

        // with notes containing multiple spaces
        assertParseSuccess(parser, "1 Test   notes  with   spaces",
                new NotesCommand(INDEX_FIRST_PERSON, new Notes("Test   notes  with   spaces")));
    }

    @Test
    public void parse_missingIndex_throwsParseException() {
        assertParseFailure(parser, "Test notes",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, NotesCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_missingNotes_throwsParseException() {
        assertParseFailure(parser, "1",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, NotesCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_invalidIndex_throwsParseException() {
        // negative index
        assertParseFailure(parser, "-1 Test notes",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, NotesCommand.MESSAGE_USAGE));

        // zero index
        assertParseFailure(parser, "0 Test notes",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, NotesCommand.MESSAGE_USAGE));

        // non-numeric index
        assertParseFailure(parser, "abc Test notes",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, NotesCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_notesExceedsMaxLength_throwsParseException() {
        String longNotes = "a".repeat(Notes.MAX_LENGTH + 1);
        assertParseFailure(parser, "1 " + longNotes,
                String.format(MESSAGE_NOTES_CHARACTER_LIMIT_EXCEEDED, longNotes.length()));
    }
}
