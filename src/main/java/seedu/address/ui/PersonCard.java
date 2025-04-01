package seedu.address.ui;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import seedu.address.model.person.Person;

/**
 * An UI component that displays information of a {@code Person}.
 */
public class PersonCard extends UiPart<Region> {

    private static final String FXML = "PersonListCard.fxml";
    private static final DateTimeFormatter INPUT_FORMATTER =
            DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    private static final DateTimeFormatter DISPLAY_FORMATTER =
            DateTimeFormatter.ofPattern("d MMM yyyy, h:mm a");

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on AddressBook level 4</a>
     */

    public final Person person;

    @FXML
    private HBox cardPane;
    @FXML
    private Label name;
    @FXML
    private Label id;
    @FXML
    private Label phone;
    @FXML
    private Label address;
    @FXML
    private Label email;
    @FXML
    private Label jobPositionLabel;
    @FXML
    private Label jobPosition;
    @FXML
    private Label teamLabel;
    @FXML
    private Label team;
    @FXML
    private Label startTimeLabel;
    @FXML
    private Label startTime;
    @FXML
    private Label durationLabel;
    @FXML
    private Label duration;
    @FXML
    private FlowPane tags;
    @FXML
    private Label interviewerNotes;
    @FXML
    private VBox interviewDetailsBox;

    /**
     * Creates a {@code PersonCode} with the given {@code Person} and index to display.
     */
    public PersonCard(Person person, int displayedIndex) {
        super(FXML);
        this.person = person;
        id.setText(displayedIndex + ". ");
        name.setText(person.getName().fullName);
        phone.setText(person.getPhone().value);
        address.setText(person.getAddress().value);
        email.setText(person.getEmail().value);
        jobPosition.setText(person.getJobPosition().value);
        team.setText(person.getTeam().value);
        person.getTags().stream()
                .sorted(Comparator.comparing(tag -> tag.tagName))
                .forEach(tag -> {
                    Label tagLabel = new Label(tag.tagName);
                    tags.getChildren().add(tagLabel);
                });

        // Only show interview details if both start time and duration are not empty
        boolean hasInterview = !person.getStartTime().value.isEmpty() && !person.getDuration().value.isEmpty();
        interviewDetailsBox.setVisible(hasInterview);
        interviewDetailsBox.setManaged(hasInterview);

        if (hasInterview) {
            // Parse the input date time string and format it for display
            LocalDateTime startDateTime = LocalDateTime.parse(person.getStartTime().value, INPUT_FORMATTER);
            startTime.setText(startDateTime.format(DISPLAY_FORMATTER));

            // Format the duration to include "minutes"
            duration.setText(person.getDuration().value + " minutes");
        }

        interviewerNotes.setText(person.getNotes().value);
    }

}
