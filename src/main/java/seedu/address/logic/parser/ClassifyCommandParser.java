package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.util.Arrays;

import seedu.address.logic.commands.ClassifyCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.TagsContainsKeywordsPredicate;

/**
 * Parses input arguments and creates a new ClassifyCommand object
 */
public class ClassifyCommandParser implements Parser<ClassifyCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the ClassifyCommand
     * and returns a ClassifyCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public ClassifyCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim();
        if (trimmedArgs.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, ClassifyCommand.MESSAGE_USAGE));
        }

        String[] tagsKeywords = trimmedArgs.split("\\s+");

        return new ClassifyCommand(new TagsContainsKeywordsPredicate(Arrays.asList(tagsKeywords)));
    }

}

