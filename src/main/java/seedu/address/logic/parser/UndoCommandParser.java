package seedu.address.logic.parser;

import java.util.logging.Logger;

import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.UndoCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new UndoCommand object
 */
public class UndoCommandParser implements Parser<UndoCommand>{
    private static final Logger logger = LogsCenter.getLogger(UndoCommandParser.class);

    /**
     * Parses the given {@code String} of arguments in the context of the UndoCommand
     * and returns an UndoCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public UndoCommand parse(String args) throws ParseException {
        logger.info("Parsing undo command");
        String trimmedArgs = args.trim();
        if (!trimmedArgs.isEmpty()) {
            logger.info("Invalid arguments provided for undo command");
            throw new ParseException(
                    String.format(UndoCommand.MESSAGE_USAGE));
        }
        return new UndoCommand();
    }
}
