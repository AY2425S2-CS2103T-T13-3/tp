package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.Messages.MESSAGE_NOTES_CHARACTER_LIMIT_EXCEEDED;

import java.util.logging.Logger;

import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.NotesCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.Notes;

/**
 * Parses input arguments and creates a new NotesCommand object
 */
public class NotesCommandParser implements Parser<NotesCommand> {

    private static final Logger logger = LogsCenter.getLogger(NotesCommandParser.class);
    private static final int EXPECTED_PARTS = 2;
    private static final int INDEX_PART = 0;
    private static final int NOTES_PART = 1;

    /**
     * Parses the given {@code String} of arguments in the context of the NotesCommand
     * and returns a NotesCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public NotesCommand parse(String args) throws ParseException {
        requireNonNull(args);
        String trimmedArgs = args.trim();
        validateNonEmptyInput(trimmedArgs);

        String[] parts = splitInput(trimmedArgs);
        Index index = parseIndex(parts[INDEX_PART]);
        Notes notes = parseNotes(parts[NOTES_PART]);

        logger.fine("Created NotesCommand with index: " + index.getOneBased() + " and notes: " + notes.value);
        return new NotesCommand(index, notes);
    }

    /**
     * Validates that the input is not empty.
     * @throws ParseException if the input is empty
     */
    private void validateNonEmptyInput(String trimmedArgs) throws ParseException {
        if (trimmedArgs.isEmpty()) {
            logger.info("Empty arguments provided for notes command");
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, NotesCommand.MESSAGE_USAGE));
        }
    }

    /**
     * Splits the input into index and notes parts.
     * @throws ParseException if the input cannot be split into exactly two parts
     */
    private String[] splitInput(String trimmedArgs) throws ParseException {
        String[] parts = trimmedArgs.split("\\s+", EXPECTED_PARTS);
        if (parts.length < EXPECTED_PARTS) {
            logger.info("Insufficient arguments provided for notes command");
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, NotesCommand.MESSAGE_USAGE));
        }
        return parts;
    }

    /**
     * Parses the index string into an Index object.
     * @throws ParseException if the index string is invalid
     */
    private Index parseIndex(String indexStr) throws ParseException {
        try {
            Index index = ParserUtil.parseIndex(indexStr);
            logger.fine("Index parsed successfully: " + index.getOneBased());
            return index;
        } catch (ParseException pe) {
            logger.info("Failed to parse index: " + indexStr);
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, NotesCommand.MESSAGE_USAGE), pe);
        }
    }

    /**
     * Parses the notes string into a Notes object.
     * @throws ParseException if the notes exceed the character limit
     */
    private Notes parseNotes(String notesText) throws ParseException {
        String trimmedNotes = notesText.trim();
        validateNotesLength(trimmedNotes);
        Notes notes = new Notes(trimmedNotes);
        logger.fine("Notes parsed successfully: " + notes.value);
        return notes;
    }

    /**
     * Validates that the notes do not exceed the maximum length.
     * @throws ParseException if the notes exceed the character limit
     */
    private void validateNotesLength(String notesText) throws ParseException {
        if (notesText.length() > Notes.MAX_LENGTH) {
            logger.info("Notes exceed character limit: " + notesText.length() + " characters");
            throw new ParseException(
                    String.format(MESSAGE_NOTES_CHARACTER_LIMIT_EXCEEDED, notesText.length()));
        }
    }
}
