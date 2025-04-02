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

    private static final String MESSAGE_EMPTY_TAG = "Tag value cannot be empty";
    private static final String MESSAGE_EMPTY_TEAM = "Team value cannot be empty";
    private static final String MESSAGE_EMPTY_JOB = "Job position value cannot be empty";

    /**
     * Parses the given {@code String} of arguments in the context of the ClassifyCommand
     * and returns a ClassifyCommand object for execution.
     * @throws ParseException if the user input does not conform to the expected format
     */
    public ClassifyCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap = tokenizeArguments(args);
        validateAtLeastOnePrefix(argMultimap);
        List<Predicate<Person>> predicates = createPredicates(argMultimap);
        return new ClassifyCommand(predicates);
    }

    /**
     * Tokenizes the command arguments with the required prefixes.
     */
    private ArgumentMultimap tokenizeArguments(String args) {
        return ArgumentTokenizer.tokenize(args, PREFIX_TAG, PREFIX_TEAM, PREFIX_JOB_POSITION);
    }

    /**
     * Validates that at least one prefix is present in the ArgumentMultimap.
     * @throws ParseException if no prefixes are present
     */
    private void validateAtLeastOnePrefix(ArgumentMultimap argMultimap) throws ParseException {
        if (!argMultimap.getValue(PREFIX_TAG).isPresent()
                && !argMultimap.getValue(PREFIX_TEAM).isPresent()
                && !argMultimap.getValue(PREFIX_JOB_POSITION).isPresent()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, ClassifyCommand.MESSAGE_USAGE));
        }
    }

    /**
     * Creates a list of predicates based on the provided arguments.
     * @throws ParseException if any of the provided values are empty
     */
    private List<Predicate<Person>> createPredicates(ArgumentMultimap argMultimap) throws ParseException {
        List<Predicate<Person>> predicates = new ArrayList<>();
        addTagPredicateIfPresent(argMultimap, predicates);
        addTeamPredicateIfPresent(argMultimap, predicates);
        addJobPredicateIfPresent(argMultimap, predicates);
        return predicates;
    }

    /**
     * Adds a tag predicate to the list if tag prefix is present.
     */
    private void addTagPredicateIfPresent(ArgumentMultimap argMultimap, List<Predicate<Person>> predicates)
            throws ParseException {
        Optional<String> tagValue = argMultimap.getValue(PREFIX_TAG);
        if (tagValue.isPresent()) {
            String trimmedTags = tagValue.get().trim();
            if (trimmedTags.isEmpty()) {
                throw new ParseException(MESSAGE_EMPTY_TAG);
            }
            String[] tagKeywords = trimmedTags.split("\\s+");
            predicates.add(new TagsContainsKeywordsPredicate(Arrays.asList(tagKeywords)));
        }
    }

    /**
     * Adds a team predicate to the list if team prefix is present.
     */
    private void addTeamPredicateIfPresent(ArgumentMultimap argMultimap, List<Predicate<Person>> predicates)
            throws ParseException {
        Optional<String> teamValue = argMultimap.getValue(PREFIX_TEAM);
        if (teamValue.isPresent()) {
            String trimmedTeam = teamValue.get().trim();
            if (trimmedTeam.isEmpty()) {
                throw new ParseException(MESSAGE_EMPTY_TEAM);
            }
            predicates.add(new TeamContainsKeywordsPredicate(List.of(trimmedTeam)));
        }
    }

    /**
     * Adds a job position predicate to the list if job position prefix is present.
     */
    private void addJobPredicateIfPresent(ArgumentMultimap argMultimap, List<Predicate<Person>> predicates)
            throws ParseException {
        Optional<String> jobValue = argMultimap.getValue(PREFIX_JOB_POSITION);
        if (jobValue.isPresent()) {
            String trimmedJob = jobValue.get().trim();
            if (trimmedJob.isEmpty()) {
                throw new ParseException(MESSAGE_EMPTY_JOB);
            }
            predicates.add(new JobPositionContainsKeywordsPredicate(List.of(trimmedJob)));
        }
    }
}
