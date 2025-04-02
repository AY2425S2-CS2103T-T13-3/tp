package seedu.address.ui;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Comparator;
import java.util.logging.Logger;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.person.Person;

/**
 * An UI component that displays information of a {@code Person}.
 */
public class PersonCard extends UiPart<Region> {
    private static final Logger logger = LogsCenter.getLogger(PersonCard.class);

    private static final String FXML = "PersonListCard.fxml";
    private static final String INPUT_DATE_FORMAT = "yyyy-MM-dd HH:mm";
    private static final String DISPLAY_DATE_FORMAT = "d MMM yyyy, h:mm a";
    private static final String MINUTES_SUFFIX = " minutes";
    private static final DateTimeFormatter INPUT_FORMATTER = DateTimeFormatter.ofPattern(INPUT_DATE_FORMAT);
    private static final DateTimeFormatter DISPLAY_FORMATTER = DateTimeFormatter.ofPattern(DISPLAY_DATE_FORMAT);

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
    private Label phoneLabel;
    @FXML
    private Label phone;
    @FXML
    private Label addressLabel;
    @FXML
    private Label address;
    @FXML
    private Label emailLabel;
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
        if (person == null) {
            logger.warning("Attempt to create PersonCard with null person");
            throw new IllegalArgumentException("Person cannot be null");
        }
        if (displayedIndex < 0) {
            logger.warning("Attempt to create PersonCard with invalid index: " + displayedIndex);
            throw new IllegalArgumentException("Display index must be non-negative");
        }

        this.person = person;
        logger.fine("Creating PersonCard for " + person.getName().fullName + " at index " + displayedIndex);

        try {
            initializeBasicInfo(displayedIndex);
            initializeTags();
            setupInterviewDetails();
        } catch (Exception e) {
            logger.warning("Error initializing PersonCard: " + e.getMessage());
            throw new IllegalStateException("Failed to initialize PersonCard", e);
        }
    }

    /**
     * Initializes the basic information fields of the person card.
     */
    private void initializeBasicInfo(int displayedIndex) {
        id.setText(displayedIndex + ". ");
        name.setText(person.getName().fullName);
        phone.setText(person.getPhone().value);
        address.setText(person.getAddress().value);
        email.setText(person.getEmail().value);
        jobPosition.setText(person.getJobPosition().value);
        team.setText(person.getTeam().value);
        interviewerNotes.setText(person.getNotes().value);
        logger.fine("Basic information initialized for " + person.getName().fullName);
    }

    /**
     * Initializes the tags section of the person card.
     */
    private void initializeTags() {
        person.getTags().stream()
                .sorted(Comparator.comparing(tag -> tag.tagName))
                .forEach(tag -> tags.getChildren().add(new Label(tag.tagName)));
        logger.fine("Tags initialized for " + person.getName().fullName);
    }

    /**
     * Sets up the interview details section if start time and duration are present.
     * @throws DateTimeParseException if the start time is in an invalid format
     */
    private void setupInterviewDetails() {
        boolean hasInterview = !person.getStartTime().value.isEmpty()
                && !person.getDuration().value.isEmpty();

        interviewDetailsBox.setVisible(hasInterview);
        interviewDetailsBox.setManaged(hasInterview);

        if (hasInterview) {
            try {
                LocalDateTime startDateTime = LocalDateTime.parse(
                        person.getStartTime().value,
                        INPUT_FORMATTER
                );
                startTime.setText(startDateTime.format(DISPLAY_FORMATTER));
                duration.setText(person.getDuration().value + MINUTES_SUFFIX);
                logger.fine("Interview details set for " + person.getName().fullName);
            } catch (DateTimeParseException e) {
                logger.warning("Invalid date format for " + person.getName().fullName + ": "
                        + person.getStartTime().value);
                startTime.setText("Invalid date format");
                duration.setText("N/A");
            }
        }
    }
}
