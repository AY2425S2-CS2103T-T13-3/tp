package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Course in the address book.
 * Guarantees: immutable; name is valid as declared in {@link #isValidCourse(String)}
 */
public class Course {

    public static final String MESSAGE_CONSTRAINTS = "Courses should only contain alphanumeric characters and spaces, "
            + "and it should not be blank";

    /*
     * The first character of the course must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "[\\p{Alnum}][\\p{Alnum} ]*";

    public final String courseName;

    /**
     * Constructs a {@code Course}.
     *
     * @param courseName A valid course name.
     */
    public Course(String courseName) {
        requireNonNull(courseName);
        checkArgument(isValidCourse(courseName), MESSAGE_CONSTRAINTS);
        this.courseName = courseName;
    }

    /**
     * Returns true if a given string is a valid course name.
     */
    public static boolean isValidCourse(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return courseName;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Course // instanceof handles nulls
                && courseName.equals(((Course) other).courseName)); // state check
    }

    @Override
    public int hashCode() {
        return courseName.hashCode();
    }
} 