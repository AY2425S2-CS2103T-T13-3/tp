package seedu.address.model.person;

import static java.util.Objects.requireNonNull;

public class Course {
    private final String courseName;

    public Course(String course) {
        requireNonNull(course);
        this.courseName = course ;
    }

    public String getCourseName() {
        return courseName;
    }

    @Override
    public String toString() {
        return courseName;
    }
}
