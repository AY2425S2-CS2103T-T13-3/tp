package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_JOB_POSITION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TEAM;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

import seedu.address.logic.commands.ClassifyCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.JobPositionContainsKeywordsPredicate;
import seedu.address.model.person.Person;
import seedu.address.model.person.TagsContainsKeywordsPredicate;
import seedu.address.model.person.TeamContainsKeywordsPredicate;

/**
 * Parses input arguments and creates a new ClassifyCommand object
 */
public class ClassifyCommandParser implements Parser<ClassifyCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the ClassifyCommand
     * and returns a ClassifyCommand object for execution.
     * @throws ParseException if the user input does not conform to the expected format
     */
    public ClassifyCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(
                args, PREFIX_TAG, PREFIX_TEAM, PREFIX_JOB_POSITION);

        // Check if at least one prefix is present
        if (!argMultimap.getValue(PREFIX_TAG).isPresent()
                && !argMultimap.getValue(PREFIX_TEAM).isPresent()
                && !argMultimap.getValue(PREFIX_JOB_POSITION).isPresent()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, ClassifyCommand.MESSAGE_USAGE));
        }

        List<Predicate<Person>> predicates = new ArrayList<>();

        // Add tag predicate if present
        Optional<String> tagValue = argMultimap.getValue(PREFIX_TAG);
        if (tagValue.isPresent()) {
            String trimmedTags = tagValue.get().trim();
            if (trimmedTags.isEmpty()) {
                throw new ParseException("Tag value cannot be empty");
            }
            String[] tagKeywords = trimmedTags.split("\\s+");
            predicates.add(new TagsContainsKeywordsPredicate(Arrays.asList(tagKeywords)));
        }

        // Add team predicate if present
        Optional<String> teamValue = argMultimap.getValue(PREFIX_TEAM);
        if (teamValue.isPresent()) {
            String trimmedTeam = teamValue.get().trim();
            if (trimmedTeam.isEmpty()) {
                throw new ParseException("Team value cannot be empty");
            }
            predicates.add(new TeamContainsKeywordsPredicate(List.of(trimmedTeam)));
        }

        // Add job position predicate if present
        Optional<String> jobValue = argMultimap.getValue(PREFIX_JOB_POSITION);
        if (jobValue.isPresent()) {
            String trimmedJob = jobValue.get().trim();
            if (trimmedJob.isEmpty()) {
                throw new ParseException("Job position value cannot be empty");
            }
            predicates.add(new JobPositionContainsKeywordsPredicate(List.of(trimmedJob)));
        }

        return new ClassifyCommand(predicates);
    }
}

