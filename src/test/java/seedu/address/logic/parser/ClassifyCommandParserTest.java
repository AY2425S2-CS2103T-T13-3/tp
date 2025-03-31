package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.ClassifyCommand;
import seedu.address.model.person.Person;
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
        List<Predicate<Person>> predicates = new ArrayList<>();
        predicates.add(new TagsContainsKeywordsPredicate(Arrays.asList("python", "java")));
        ClassifyCommand expectedClassifyCommand = new ClassifyCommand(predicates);
        assertParseSuccess(parser, " t/python java", expectedClassifyCommand);

        // multiple whitespaces between keywords
        assertParseSuccess(parser, " t/python    java", expectedClassifyCommand);
    }
}
