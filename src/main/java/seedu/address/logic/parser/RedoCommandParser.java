package seedu.address.logic.parser;

import java.util.logging.Logger;

import seedu.address.commons.core.LogsCenter;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.RedoCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new RedoCommand object.
 */
public class RedoCommandParser implements Parser<RedoCommand> {
    private static final Logger logger = LogsCenter.getLogger(RedoCommandParser.class);

    /**
     * Parses the given {@code String} of arguments in the context of the RedoCommand
     * and returns a RedoCommand object for execution.
     *
     * @param args The arguments to parse.
     * @return A RedoCommand object for execution.
     * @throws ParseException If the user input does not conform the expected format.
     */
    public RedoCommand parse(String args) throws ParseException {
        logger.info("Parsing redo command");
        String trimmedArgs = args.trim();
        if (!trimmedArgs.isEmpty()) {
            logger.info("Invalid arguments provided for redo command");
            throw new ParseException(
                    String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT, RedoCommand.MESSAGE_USAGE));
        }
        return new RedoCommand();
    }
}
