package seedu.address.logic.parser;

import seedu.address.logic.commands.SortCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new SortCommand object
 */
public class SortCommandParser implements Parser<SortCommand> {

    @Override
    public SortCommand parse(String args) throws ParseException {
        // This command takes no arguments, so we can safely ignore args or throw if not empty
        String trimmedArgs = args.trim();
        if (!trimmedArgs.isEmpty()) {
            throw new ParseException("Sort command does not accept any arguments.\n" + SortCommand.MESSAGE_USAGE);
        }
        return new SortCommand();
    }
}
