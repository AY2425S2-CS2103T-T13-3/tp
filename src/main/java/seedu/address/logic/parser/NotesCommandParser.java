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

    /**
     * Parses the given {@code String} of arguments in the context of the NotesCommand
     * and returns a NotesCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public NotesCommand parse(String args) throws ParseException {
        requireNonNull(args);
        String trimmedArgs = args.trim();

        if (trimmedArgs.isEmpty()) {
            logger.info("Empty arguments provided for notes command");
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, NotesCommand.MESSAGE_USAGE));
        }

        String[] parts = trimmedArgs.split("\\s+", 2);
        if (parts.length < 2) {
            logger.info("Insufficient arguments provided for notes command");
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, NotesCommand.MESSAGE_USAGE));
        }

        Index index;
        try {
            index = ParserUtil.parseIndex(parts[0]);
            logger.fine("Index parsed successfully: " + index.getOneBased());
        } catch (ParseException pe) {
            logger.info("Failed to parse index: " + parts[0]);
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, NotesCommand.MESSAGE_USAGE), pe);
        }

        String notesText = parts[1].trim();
        if (notesText.length() > Notes.MAX_LENGTH) {
            logger.info("Notes exceed character limit: " + notesText.length() + " characters");
            throw new ParseException(
                    String.format(MESSAGE_NOTES_CHARACTER_LIMIT_EXCEEDED, notesText.length()));
        }

        Notes notes = new Notes(notesText);
        logger.fine("Notes parsed successfully: " + notes.value);

        return new NotesCommand(index, notes);
    }
}
