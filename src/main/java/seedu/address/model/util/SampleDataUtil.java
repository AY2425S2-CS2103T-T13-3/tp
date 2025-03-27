package seedu.address.model.util;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.address.model.AddressBook;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.person.Address;
import seedu.address.model.person.Duration;
import seedu.address.model.person.Email;
import seedu.address.model.person.JobPosition;
import seedu.address.model.person.Name;
import seedu.address.model.person.Notes;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.person.StartTime;
import seedu.address.model.person.Team;
import seedu.address.model.tag.Tag;

/**
 * Contains utility methods for populating {@code AddressBook} with sample data.
 */
public class SampleDataUtil {
    public static Person[] getSamplePersons() {
        return new Person[] {
            new Person(new Name("Alex Yeoh"), new Phone("87438807"), new Email("alexyeoh@example.com"),
                    new Address("Blk 30 Geylang Street 29, #06-40"),
                    new JobPosition("Software Engineer"), new Team("iOS Development"),
                    getTagSet("swift", "senior"),
                    new Notes("Strong in iOS development with 8 years of experience. Demonstrated excellent "
                            + "knowledge of Swift and SwiftUI. "
                            + "Led multiple App Store releases with 4.8+ ratings. Potential tech lead material. "
                            + "Particularly impressed with his system design solutions during the technical interview. "
                            + "Shows great mentorship capabilities with junior developers."),
                    new StartTime("2025-03-01 12:00"), new Duration("90")),
            new Person(new Name("Bernice Yu"), new Phone("99272758"), new Email("berniceyu@example.com"),
                    new Address("Blk 30 Lorong 3 Serangoon Gardens, #07-18"),
                    new JobPosition("UI/UX Designer"), new Team("Design"),
                    getTagSet("figma", "experienced"),
                    new Notes("Strong in iOS development with 8 years of experience. Demonstrated excellent "
                    + "knowledge of Swift and SwiftUI. "
                    + "Led multiple App Store releases with 4.8+ ratings. Potential tech lead material. "
                    + "Particularly impressed with his system design solutions during the technical interview. "
                    + "Shows great mentorship capabilities with junior developers."),
                    new StartTime("2025-03-10 12:00"), new Duration("90")),
            new Person(new Name("Charlotte Oliveiro"), new Phone("93210283"), new Email("charlotte@example.com"),
                    new Address("Blk 11 Ang Mo Kio Street 74, #11-04"),
                    new JobPosition("Hardware Engineer"), new Team("Chip Design"),
                    getTagSet("verilog", "fresh"),
                    new Notes("Portfolio shows strong visual design skills and user-centric thinking. "
                            + "Great presentation on improving the Apple Store app UX. Highly collaborative. "
                            + "Previous experience at top design agencies is evident in her work quality."),
                    new StartTime("2025-03-22 14:15"), new Duration("60")),
            new Person(new Name("Charlotte Oliveiro"), new Phone("93210283"), new Email("charlotte@example.com"),
                    new Address("Blk 11 Ang Mo Kio Street 74, #11-04"),
                    new JobPosition("Hardware Engineer"), new Team("Chip Design"),
                    getTagSet("verilog", "fresh"),
                    new Notes("Recent graduate with impressive academic projects in FPGA design. "
                            + "Shows promise in chip architecture concepts. "
                            + "Needs mentoring but eager to learn."),
                    new StartTime("2025-04-01 12:00"), new Duration("60")),
            new Person(new Name("David Li"), new Phone("91031282"), new Email("lidavid@example.com"),
                    new Address("Blk 436 Serangoon Gardens Street 26, #16-43"),
                    new JobPosition("Security Specialist"), new Team("Apple Store"),
                    getTagSet("security", "senior"),
                    new Notes("Expert in iOS security protocols and penetration testing. "
                            + "Previously led security at a major tech firm. "
                            + "Identified critical vulnerabilities in App Store submission process. "
                            + "Strong security mindset. "
                            + "Published research on mobile app security. "
                            + "Excellent communicator when explaining complex security concepts."),
                    new StartTime("2025-04-01 12:00"), new Duration("60")),
            new Person(new Name("Irfan Ibrahim"), new Phone("92492021"), new Email("irfan@example.com"),
                    new Address("Blk 47 Tampines Street 20, #17-35"),
                    new JobPosition("Software Engineer"), new Team("macOS"),
                    getTagSet("cpp", "junior"),
                    new Notes("Expert in iOS security protocols and penetration testing. "
                            + "Previously led security at a major tech firm. "
                            + "Identified critical vulnerabilities in App Store submission process. "
                            + "Strong security mindset. "
                            + "Published research on mobile app security. "
                            + "Excellent communicator when explaining complex security concepts."),
                    new StartTime("2025-04-05 12:00"), new Duration("45")),
            new Person(new Name("Irfan Ibrahim"), new Phone("92492021"), new Email("irfan@example.com"),
                    new Address("Blk 47 Tampines Street 20, #17-35"),
                    new JobPosition("Software Engineer"), new Team("macOS"),
                    getTagSet("cpp", "junior"),
                    new Notes("Good foundation in C++. First interview showed promising problem-solving skills. "
                            + "Could benefit from more exposure to large-scale systems."),
                    new StartTime("2025-03-30 12:00"), new Duration("90")),
            new Person(new Name("Roy Balakrishnan"), new Phone("92624417"), new Email("royb@example.com"),
                    new Address("Blk 45 Aljunied Street 85, #11-31"),
                    new JobPosition("Product Manager"), new Team("iOS Development"),
                    getTagSet("agile", "experienced"),
                    new Notes("10 years of product management in mobile apps. Strong track record of launches. "
                            + "Excellent stakeholder management skills. Deep understanding of the iOS ecosystem. "
                            + "Advocates for user privacy and accessibility in product decisions."),
                    new StartTime("2025-03-30 12:00"), new Duration("90"))
        };
    }

    public static ReadOnlyAddressBook getSampleAddressBook() {
        AddressBook sampleAb = new AddressBook();
        for (Person samplePerson : getSamplePersons()) {
            sampleAb.addPerson(samplePerson);
        }
        return sampleAb;
    }

    /**
     * Returns a tag set containing the list of strings given.
     */
    public static Set<Tag> getTagSet(String... strings) {
        return Arrays.stream(strings)
                .map(Tag::new)
                .collect(Collectors.toSet());
    }
}
