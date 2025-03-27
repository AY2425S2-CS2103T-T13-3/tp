package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.ClassifyCommand;
import seedu.address.model.person.TagsContainsKeywordsPredicate;

public class ClassifyCommandParserTest {

    private ClassifyCommandParser parser = new ClassifyCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, ClassifyCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validArgs_returnsClassifyCommand() {
        // no leading and trailing whitespaces
        ClassifyCommand expectedClassifyCommand =
                new ClassifyCommand(new TagsContainsKeywordsPredicate(Arrays.asList("python", "java")));
        assertParseSuccess(parser, "python java", expectedClassifyCommand);

        // multiple whitespaces between keywords
        assertParseSuccess(parser, " \n python \n \t java  \t", expectedClassifyCommand);
    }

}
