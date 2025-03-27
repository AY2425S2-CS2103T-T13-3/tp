package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Duration;
import seedu.address.model.person.Person;
import seedu.address.model.person.StartTime;

/**
 * Sets the interview time (start time + duration) for a candidate in RecruitIntel.
 */
public class InterviewCommand extends Command {

    public static final String COMMAND_WORD = "interview";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Sets interview time for the candidate identified "
            + "by the index number used in the displayed candidate list.\n"
            + "Parameters: INDEX (must be a positive integer) START_TIME (yyyy-MM-dd HH:mm) DURATION (multiple of 5)\n"
            + "Example: " + COMMAND_WORD + " 1 2025-04-01 14:00 30";

    public static final String MESSAGE_SET_INTERVIEW_SUCCESS = "Interview set for candidate #%1$d: "
            + "Start = %2$s, Duration = %3$d minutes";

    private final Index targetIndex;
    private StartTime startTime;
    private Duration duration;

    private Person targetPerson;
    private StartTime previousStartTime;
    private Duration previousDuration;

    /**
     * @param targetIndex index of candidate to set interview for
     * @param startTime start time of the interview
     * @param duration duration of the interview
     */
    public InterviewCommand(Index targetIndex, StartTime startTime, Duration duration) {
        requireNonNull(targetIndex);
        requireNonNull(startTime);
        requireNonNull(duration);

        this.targetIndex = targetIndex;
        this.startTime = startTime;
        this.duration = duration;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Person> lastShownList = model.getFilteredPersonList();

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Person personToUpdate = lastShownList.get(targetIndex.getZeroBased());
        targetPerson = personToUpdate;

        // Save previous values for undo
        previousStartTime = personToUpdate.getStartTime();
        previousDuration = personToUpdate.getDuration();

        // Construct updated person
        Person updatedPerson = new Person(
                personToUpdate.getName(),
                personToUpdate.getPhone(),
                personToUpdate.getEmail(),
                personToUpdate.getAddress(),
                personToUpdate.getJobPosition(),
                personToUpdate.getTeam(),
                personToUpdate.getTags(),
                personToUpdate.getNotes(),
                startTime,
                duration
        );

        model.setPerson(personToUpdate, updatedPerson);
        targetPerson = updatedPerson;

        return new CommandResult(String.format(MESSAGE_SET_INTERVIEW_SUCCESS,
                targetIndex.getOneBased(), startTime.value, duration.getDurationInMinutes()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this
                || (other instanceof InterviewCommand
                && targetIndex.equals(((InterviewCommand) other).targetIndex)
                && startTime.equals(((InterviewCommand) other).startTime)
                && duration.equals(((InterviewCommand) other).duration));
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("targetIndex", targetIndex)
                .add("startTime", startTime)
                .add("duration", duration)
                .toString();
    }

    @Override
    public CommandResult undo(Model model) throws CommandException {
        requireNonNull(model);
        Person personToRestore = targetPerson;

        // Restore the old StartTime and Duration
        Person updatedPerson = new Person(
                personToRestore.getName(),
                personToRestore.getPhone(),
                personToRestore.getEmail(),
                personToRestore.getAddress(),
                personToRestore.getJobPosition(),
                personToRestore.getTeam(),
                personToRestore.getTags(),
                personToRestore.getNotes(),
                previousStartTime,
                previousDuration
        );

        // Swap current and previous for redo
        StartTime tempStart = previousStartTime;
        Duration tempDuration = previousDuration;
        previousStartTime = startTime;
        previousDuration = duration;
        startTime = tempStart;
        duration = tempDuration;

        model.setPerson(personToRestore, updatedPerson);
        targetPerson = updatedPerson;
        lastCommand = this;

        return new CommandResult(String.format(InterviewCommand.MESSAGE_SET_INTERVIEW_SUCCESS,
                targetIndex.getOneBased(),
                startTime != null ? startTime.value : "None",
                duration != null ? duration.getDurationInMinutes() : 0));
    }

}
