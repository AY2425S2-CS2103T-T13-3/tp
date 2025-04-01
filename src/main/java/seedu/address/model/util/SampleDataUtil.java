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
                    getTagSet("swift", "senior", "mobile"),
                    new Notes("Strong in iOS development with 8 years of experience. Led multiple successful App Store "
                            + "releases. Expert in Swift, SwiftUI, and iOS architecture. Mentors junior developers and "
                            + "contributes to architectural decisions. "
                            + "Excellent problem-solving skills and attention to detail."),
                    new StartTime("2025-05-01 12:00"), new Duration("90")),
            new Person(new Name("Bernice Yu"), new Phone("99272758"), new Email("berniceyu@example.com"),
                    new Address("Blk 30 Lorong 3 Serangoon Gardens, #07-18"),
                    new JobPosition("UI/UX Designer"), new Team("Design"),
                    getTagSet("figma", "experienced", "design"),
                    new Notes("Expert in user interface design with strong portfolio. Proficient in Figma and Adobe "
                            + "Creative Suite. Has redesigned multiple "
                            + "high-traffic applications improving user satisfaction "
                            + "by 40%. Strong advocate for accessibility and inclusive design principles."),
                    new StartTime("2025-05-10 12:00"), new Duration("90")),
            new Person(new Name("Charlie Tan"), new Phone("93210283"), new Email("charlie@example.com"),
                    new Address("Blk 11 Ang Mo Kio Street 74, #11-04"),
                    new JobPosition("Software Engineer"), new Team("Android Development"),
                    getTagSet("kotlin", "junior", "mobile"),
                    new Notes("Passionate about Android development with 2 years of experience. Strong foundation in "
                            + "Kotlin and Android SDK. Quick learner who has already contributed to several feature "
                            + "releases. Particularly interested in app performance optimization."),
                    new StartTime("2025-03-22 14:15"), new Duration("60")),
            new Person(new Name("David Li"), new Phone("91031282"), new Email("lidavid@example.com"),
                    new Address("Blk 436 Serangoon Gardens Street 26, #16-43"),
                    new JobPosition("Security Engineer"), new Team("Security"),
                    getTagSet("security", "senior", "cloud"),
                    new Notes("Expert in cloud security and penetration testing with 10 years of experience. CISSP "
                            + "certified. Led security audits for major cloud platforms. Strong background in threat "
                            + "modeling and security architecture. Regular speaker at security conferences."),
                    new StartTime("2025-04-01 12:00"), new Duration("60")),
            new Person(new Name("Emma Wong"), new Phone("92492021"), new Email("emma@example.com"),
                    new Address("Blk 47 Tampines Street 20, #17-35"),
                    new JobPosition("Software Engineer"), new Team("Backend"),
                    getTagSet("java", "spring", "experienced"),
                    new Notes("Specializes in Java backend development with 5 years of experience. "
                            + "Expert in Spring Boot "
                            + "and microservices architecture. Has successfully led the migration of monolithic "
                            + "applications to microservices. Strong advocate for clean code "
                            + "and test-driven development."),
                    new StartTime("2025-04-05 12:00"), new Duration("45")),
            new Person(new Name("Fiona Tan"), new Phone("92624417"), new Email("fiona@example.com"),
                    new Address("Blk 45 Aljunied Street 85, #11-31"),
                    new JobPosition("Product Manager"), new Team("iOS Development"),
                    getTagSet("agile", "experienced", "mobile"),
                    new Notes("Strong track record in mobile app product management with 6 years of experience. "
                            + "Successfully launched multiple iOS apps with millions of downloads. "
                            + "Expert in agile methodologies and "
                            + "user-centered design. Great at stakeholder management."),
                    new StartTime("2025-03-30 12:00"), new Duration("90")),
            new Person(new Name("George Zhang"), new Phone("92624418"), new Email("george@example.com"),
                    new Address("Blk 123 Clementi Street 11, #12-21"),
                    new JobPosition("Data Scientist"), new Team("Data"),
                    getTagSet("python", "ml", "experienced"),
                    new Notes("Expert in machine learning and data analytics with 5 years of experience. "
                            + "PhD in Computer Science. "
                            + "Specializes in natural language processing and computer vision. Has published "
                            + "several papers in top ML conferences. Strong Python and TensorFlow skills."),
                    new StartTime("2025-04-10 14:00"), new Duration("60")),
            new Person(new Name("Hannah Lee"), new Phone("92624419"), new Email("hannah@example.com"),
                    new Address("Blk 789 Yishun Ring Road, #05-12"),
                    new JobPosition("UI/UX Designer"), new Team("Design"),
                    getTagSet("design", "junior", "figma"),
                    new Notes("Creative designer with fresh perspective and 1 year of experience. Strong portfolio of "
                            + "mobile and web designs. "
                            + "Proficient in Figma and prototyping tools. Passionate about user "
                            + "research and has conducted several successful usability studies."),
                    new StartTime("2025-04-15 10:00"), new Duration("45")),
            new Person(new Name("Ian Lim"), new Phone("92624420"), new Email("ian@example.com"),
                    new Address("Blk 456 Hougang Ave 10, #08-88"),
                    new JobPosition("DevOps Engineer"), new Team("Infrastructure"),
                    getTagSet("kubernetes", "aws", "senior"),
                    new Notes("Experienced in cloud infrastructure and CI/CD with 9 years of experience. "
                            + "Expert in AWS, Kubernetes, and Terraform. "
                            + "Has successfully led multiple cloud migrations. Strong advocate "
                            + "for infrastructure as code and automated testing. AWS certified solutions architect."),
                    new StartTime("2025-04-20 11:30"), new Duration("60")),
            new Person(new Name("Julia Chen"), new Phone("92624421"), new Email("julia@example.com"),
                    new Address("Blk 789 Jurong East Street 42, #15-33"),
                    new JobPosition("Software Engineer"), new Team("Backend"),
                    getTagSet("python", "django", "junior"),
                    new Notes("Strong foundation in Python backend development with 2 years of experience. "
                            + "Proficient in Django and REST APIs. "
                            + "Quick learner who has already taken ownership of several key "
                            + "features. Passionate about writing clean, maintainable code and documentation."),
                    new StartTime("2025-04-25 15:00"), new Duration("45")),
            new Person(new Name("Kevin Patel"), new Phone("92624422"), new Email("kevin@example.com"),
                    new Address("Blk 147 Bishan Street 13, #09-77"),
                    new JobPosition("Product Manager"), new Team("Android Development"),
                    getTagSet("agile", "mobile", "senior"),
                    new Notes("Experienced in Android app product strategy with 8 years of experience. Has launched "
                            + "multiple successful apps with over 10M+ downloads. Strong understanding of the Android "
                            + "ecosystem and Material Design. Expert in data-driven decision making and A/B testing."),
                    new StartTime("2025-05-01 13:00"), new Duration("90")),
            new Person(new Name("Linda Kim"), new Phone("92624423"), new Email("linda@example.com"),
                    new Address("Blk 258 Pasir Ris Street 21, #14-55"),
                    new JobPosition("Software Engineer"), new Team("iOS Development"),
                    getTagSet("swift", "junior", "mobile"),
                    new Notes("Enthusiastic about iOS development with 1.5 years of experience. Strong foundation in "
                            + "Swift and UIKit. Fast learner who has already contributed to several feature releases. "
                            + "Passionate about mobile UI animations and smooth user experiences."),
                    new StartTime("2025-05-05 16:00"), new Duration("60"))
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
