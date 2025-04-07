---
  layout: default.md
  title: "Developer Guide"
  pageNav: 3
---

# NUSMeet Developer Guide

<!-- * Table of Contents -->
<page-nav-print />

---

## **Table of Contents**

1. [Acknowledgements](#acknowledgements)
2. [Settting up, getting started](#setting-up-getting-started)
3. [Design](#design)
    * [Architecture](#architecture)
    * [UI Component](#ui-component)
    * [Logic Component](#logic-component)
    * [Model Component](#model-component)
    * [Storage Component](#storage-component)
4. [Implementation](#implementation)
    * [Automatic Courses Archival](#automatic-courses-archival)
    * [\[Proposed\] Undo/redo feature](#proposed-undo-redo-feature)
5. [Documentation, logging, testing, configuration, dev-ops](#documentation-logging-testing-configuration-dev-ops)
6. [Appendix: Requirements](#appendix-requirements)
    * [Product Scope](#product-scope)
    * [User Stories](#user-stories)
    * [Use Cases](#use-cases)
    * [Non-Functional Requirements](#non-functional-requirements)
    * [Glossary](#glossary)  
7. [Appendix: Instructions for manual testing](#appendix-instructions-for-manual-testing)
    * [Launch and Shutdown](#launch-and-shutdown)
    * [Deleting a Person](#deleting-a-person)
    * [Saving Data](#saving-data)
8. [Appendix: Planned Enhancements](#appendix-planned-enhancements)

---

## **Acknowledgements**
 
- This project is built upon [SE-Educations's AddressBook Level-3](https://se-education.org/addressbook-level3/).

---

## **Setting up, getting started**

Refer to the guide [_Setting up and getting started_](SettingUp.md).

---

## **Design**
<div markdown="span" class="alert alert-primary">

💡**Tip:** The `.puml` files used to create diagrams in this document `docs/diagrams` folder. Refer to the [_PlantUML Tutorial_ at se-edu/guides](https://se-education.org/guides/tutorials/plantUml.html) to learn how to create and edit diagrams.
</div>

---

### Architecture

<puml src="diagrams/ArchitectureDiagram.puml" width="280" />

The **_Architecture Diagram_** given above explains the high-level design of the App.

Given below is a quick overview of main components and how they interact with each other.

**Main components of the architecture**

**`Main`** (consisting of classes [`Main`](https://github.com/AY2425S2-CS2103T-T13-3/tp/blob/master/src/main/java/seedu/address/Main.java) and [`MainApp`](https://github.com/AY2425S2-CS2103T-T13-3/tp/blob/master/src/main/java/seedu/address/MainApp.java)) is in charge of the app launch and shut down.

- At app launch, it initializes the other components in the correct sequence, and connects them up with each other.
- At shut down, it shuts down the other components and invokes cleanup methods where necessary.

The bulk of the app's work is done by the following four components:

- [**`UI`**](#ui-component): The UI of the App.
- [**`Logic`**](#logic-component): The command executor.
- [**`Model`**](#model-component): Holds the data of the App in memory.
- [**`Storage`**](#storage-component): Reads data from, and writes data to, the hard disk.

[**`Commons`**](#common-classes) represents a collection of classes used by multiple other components.

**How the architecture components interact with each other**

The _Sequence Diagram_ below shows how the components interact with each other for the scenario where the user issues the command `delete 1`.

<puml src="diagrams/ArchitectureSequenceDiagram.puml" width="574" />

Each of the four main components (also shown in the diagram above),

- defines its _API_ in an `interface` with the same name as the Component.
- implements its functionality using a concrete `{Component Name}Manager` class (which follows the corresponding API `interface` mentioned in the previous point.

For example, the `Logic` component defines its API in the `Logic.java` interface and implements its functionality using the `LogicManager.java` class which follows the `Logic` interface. Other components interact with a given component through its interface rather than the concrete class (reason: to prevent outside component's being coupled to the implementation of a component), as illustrated in the (partial) class diagram below.

<puml src="diagrams/ComponentManagers.puml" width="300" />

The sections below give more details of each component.

### UI component

The **API** of this component is specified in [`Ui.java`](https://github.com/AY2425S2-CS2103T-T13-3/tp/blob/master/src/main/java/seedu/address/ui/Ui.java)

<puml src="diagrams/UiClassDiagram.puml" alt="Structure of the UI Component"/>

The UI consists of a `MainWindow` that is made up of parts e.g.`CommandBox`, `ResultDisplay`, `PersonListPanel`, `StatusBarFooter` etc. All these, including the `MainWindow`, inherit from the abstract `UiPart` class which captures the commonalities between classes that represent parts of the visible GUI.

The `UI` component uses the JavaFx UI framework. The layout of these UI parts are defined in matching `.fxml` files that are in the `src/main/resources/view` folder. For example, the layout of the [`MainWindow`](https://github.com/AY2425S2-CS2103T-T13-3/tp/blob/master/src/main/java/seedu/address/ui/MainWindow.java) is specified in [`MainWindow.fxml`](https://github.com/AY2425S2-CS2103T-T13-3/tp/blob/master/src/main/resources/view/MainWindow.fxml)

The `UI` component,

- executes user commands using the `Logic` component.
- listens for changes to `Model` data so that the UI can be updated with the modified data.
- keeps a reference to the `Logic` component, because the `UI` relies on the `Logic` to execute commands.
- depends on some classes in the `Model` component, as it displays `Person` object residing in the `Model`.

### Logic component

**API** : [`Logic.java`](https://github.com/AY2425S2-CS2103T-T13-3/tp/blob/master/src/main/java/seedu/address/logic/Logic.java)

Here's a (partial) class diagram of the `Logic` component:

<puml src="diagrams/LogicClassDiagram.puml" width="550"/>

The sequence diagram below illustrates the interactions within the `Logic` component, taking `execute("delete 1")` API call as an example.

<puml src="diagrams/DeleteSequenceDiagram.puml" alt="Interactions Inside the Logic Component for the `delete 1` Command" />

<box type="info" seamless>

**Note:** The lifeline for `DeleteCommandParser` should end at the destroy marker (X) but due to a limitation of PlantUML, the lifeline continues till the end of diagram.
</box>

How the `Logic` component works:

1. When `Logic` is called upon to execute a command, it is passed to an `AddressBookParser` object which in turn creates a parser that matches the command (e.g., `DeleteCommandParser`) and uses it to parse the command.
1. This results in a `Command` object (more precisely, an object of one of its subclasses e.g., `DeleteCommand`) which is executed by the `LogicManager`.
1. The command can communicate with the `Model` when it is executed (e.g. to delete a person).<br>
   Note that although this is shown as a single step in the diagram above (for simplicity), in the code it can take several interactions (between the command object and the `Model`) to achieve.
1. The result of the command execution is encapsulated as a `CommandResult` object which is returned back from `Logic`.

Here are the other classes in `Logic` (omitted from the class diagram above) that are used for parsing a user command:

<puml src="diagrams/ParserClasses.puml" width="600"/>

How the parsing works:

- When called upon to parse a user command, the `AddressBookParser` class creates an `XYZCommandParser` (`XYZ` is a placeholder for the specific command name e.g., `AddCommandParser`) which uses the other classes shown above to parse the user command and create a `XYZCommand` object (e.g., `AddCommand`) which the `AddressBookParser` returns back as a `Command` object.
- All `XYZCommandParser` classes (e.g., `AddCommandParser`, `DeleteCommandParser`, ...) inherit from the `Parser` interface so that they can be treated similarly where possible e.g, during testing.

### Model component

**API** : [`Model.java`](https://github.com/AY2425S2-CS2103T-T13-3/tp/blob/master/src/main/java/seedu/address/model/Model.java)

<puml src="diagrams/ModelClassDiagram.puml" width="450" />

The `Model` component,

- stores the NUSMeet data i.e., all `Person` objects (which are contained in a `UniquePersonList` object).
- stores the currently 'selected' `Person` objects (e.g., results of a search query) as a separate _filtered then sorted_ list which is exposed to outsiders as an unmodifiable `ObservableList<Person>` that can be 'observed' e.g. the UI can be bound to this list so that the UI automatically updates when the data in the list change.
- stores a `UserPref` object that represents the user’s preferences. This is exposed to the outside as a `ReadOnlyUserPref` objects.
- does not depend on any of the other three components (as the `Model` represents data entities of the domain, they should make sense on their own without depending on other components)

<box type="info" seamless>

**Note:** An alternative (arguably, a more OOP) model is given below. It has a `Tag` list in the `AddressBook`, which `Person` references. This allows `AddressBook` to only require one `Tag` object per unique tag, instead of each `Person` needing their own `Tag` objects.<br>

<puml src="diagrams/BetterModelClassDiagram.puml" width="450" />

</box>

### Storage component

**API** : [`Storage.java`](https://github.com/AY2425S2-CS2103T-T13-3/tp/blob/master/src/main/java/seedu/address/storage/Storage.java)

<puml src="diagrams/StorageClassDiagram.puml" width="550" />

The `Storage` component,

- can save NUSMeet, user preference and  user's last login data in JSON format, and read them back into corresponding objects.
- inherits from `AddressBookStorage`, `UserPrefStorage` and `LoginBookStorage`  which means it can be treated as either one (if only the functionality of only one is needed).
- depends on some classes in the `Model` component (because the `Storage` component's job is to save/retrieve objects that belong to the `Model`)

### Common classes

Classes used by multiple components are in the `seedu.address.commons` package.

---

## **Implementation**

This section describes some noteworthy details on how certain features are implemented.

### Automatic Courses Archival

NUSMeet keeps track of the user’s last login timestamp and stores it in a JSON file. Upon login, NUSMeet retrieves the current system date and time and the stored last login timestamp, it then determines which academic period the user is currently in (Semester 1, Semester 2, Winter Break, or Summer Break). After which, NUSMeet will check whether the user has crossed into a new semester since their last login. If the previous semester has ended, NUSMeet will automatically archive the courses listed under the "Current Courses" field and append them to the beginning of the "Previous Courses" field.

### \[Proposed\] Undo/redo feature
#### Proposed Implementation

The proposed undo/redo mechanism is facilitated by `VersionedAddressBook`. It extends `AddressBook` with an undo/redo history, stored internally as an `addressBookStateList` and `currentStatePointer`. Additionally, it implements the following operations:

- `VersionedAddressBook#commit()` — Saves the current NUSMeet state in its history.
- `VersionedAddressBook#undo()` — Restores the previous NUSMeet state from its history.
- `VersionedAddressBook#redo()` — Restores a previously undone NUSMeet state from its history.

These operations are exposed in the `Model` interface as `Model#commitAddressBook()`, `Model#undoAddressBook()` and `Model#redoAddressBook()` respectively.

Given below is an example usage scenario and how the undo/redo mechanism behaves at each step.

Step 1. The user launches the application for the first time. The `VersionedAddressBook` will be initialized with the initial NUSMeet state, and the `currentStatePointer` pointing to that single address book state.

<puml src="diagrams/UndoRedoState0.puml" alt="UndoRedoState0" />

Step 2. The user executes `delete 5` command to delete the 5th person in NUSMeet. The `delete` command calls `Model#commitAddressBook()`, causing the modified state of NUSMeet after the `delete 5` command executes to be saved in the `addressBookStateList`, and the `currentStatePointer` is shifted to the newly inserted address book state.

<puml src="diagrams/UndoRedoState1.puml" alt="UndoRedoState1" />

Step 3. The user executes `add n/David …​` to add a new person. The `add` command also calls `Model#commitAddressBook()`, causing another modified NUSMeet state to be saved into the `addressBookStateList`.

<puml src="diagrams/UndoRedoState2.puml" alt="UndoRedoState2" />

<box type="info" seamless>

**Note:** If a command fails its execution, it will not call `Model#commitAddressBook()`, so the NUSMeet state will not be saved into the `addressBookStateList`.

</box>

Step 4. The user now decides that adding the person was a mistake, and decides to undo that action by executing the `undo` command. The `undo` command will call `Model#undoAddressBook()`, which will shift the `currentStatePointer` once to the left, pointing it to the previous NUSMeet state, and restores the NUSMeet to that state.

<puml src="diagrams/UndoRedoState3.puml" alt="UndoRedoState3" />

<box type="info" seamless>

**Note:** If the `currentStatePointer` is at index 0, pointing to the initial AddressBook state, then there are no previous AddressBook states to restore. The `undo` command uses `Model#canUndoAddressBook()` to check if this is the case. If so, it will return an error to the user rather
than attempting to perform the undo.

</box>

The following sequence diagram shows how an undo operation goes through the `Logic` component:

<puml src="diagrams/UndoSequenceDiagram-Logic.puml" alt="UndoSequenceDiagram-Logic" />

<box type="info" seamless>

**Note:** The lifeline for `UndoCommand` should end at the destroy marker (X) but due to a limitation of PlantUML, the lifeline reaches the end of diagram.

</box>

Similarly, how an undo operation goes through the `Model` component is shown below:

<puml src="diagrams/UndoSequenceDiagram-Model.puml" alt="UndoSequenceDiagram-Model" />

The `redo` command does the opposite — it calls `Model#redoAddressBook()`, which shifts the `currentStatePointer` once to the right, pointing to the previously undone state, and restores the address book to that state.

<box type="info" seamless>

**Note:** If the `currentStatePointer` is at index `addressBookStateList.size() - 1`, pointing to the latest NUSMeet state, then there are no undone AddressBook states to restore. The `redo` command uses `Model#canRedoAddressBook()` to check if this is the case. If so, it will return an error to the user rather than attempting to perform the redo.

</box>

Step 5. The user then decides to execute the command `list`. Commands that do not modify NUSMeet, such as `list`, will usually not call `Model#commitAddressBook()`, `Model#undoAddressBook()` or `Model#redoAddressBook()`. Thus, the `addressBookStateList` remains unchanged.

<puml src="diagrams/UndoRedoState4.puml" alt="UndoRedoState4" />

Step 6. The user executes `clear`, which calls `Model#commitAddressBook()`. Since the `currentStatePointer` is not pointing at the end of the `addressBookStateList`, all NUSMeet states after the `currentStatePointer` will be purged. Reason: It no longer makes sense to redo the `add n/David …​` command. This is the behavior that most modern desktop applications follow.

<puml src="diagrams/UndoRedoState5.puml" alt="UndoRedoState5" />

The following activity diagram summarizes what happens when a user executes a new command:

<puml src="diagrams/CommitActivityDiagram.puml" width="250" />

#### Design considerations:

**Aspect: How undo & redo executes:**

- **Alternative 1 (current choice):** Saves the entire NUSMeet application.

  - Pros: Easy to implement.
  - Cons: May have performance issues in terms of memory usage.

- **Alternative 2:** Individual command knows how to undo/redo by
  itself.
  - Pros: Will use less memory (e.g. for `delete`, just save the person being deleted).
  - Cons: We must ensure that the implementation of each individual command are correct.

    
---

## **Documentation, logging, testing, configuration, dev-ops**

- [Documentation guide](Documentation.md)
- [Testing guide](Testing.md)
- [Logging guide](Logging.md)
- [Configuration guide](Configuration.md)
- [DevOps guide](DevOps.md)

---

## **Appendix: Requirements**

### Product scope

**Target user profile**:

1. looking for schoolmates to discuss schoolwork with

2. looking for a way to store schoolmate contacts

3. has a need to manage a significant number of contacts

4. prefer desktop apps over other types

5. can type fast

6. prefers typing than mouse interactions

**Value proposition**:

- Provide quick access to students who are taking similar courses
- Provide a friendly command line format for users who prefer CLI application
- Organise students’ contacts in a relevant & friendly format

### User stories

Priorities: High (must have) - `* * *`, Medium (nice to have) - `* *`, Low (unlikely to have) - `*`

| Priority | As a …​     | I want to …​                               | So that I can…​                                                                         |
| -------- | ----------- |--------------------------------------------|-----------------------------------------------------------------------------------------|
| `* * *`  | NUS student | Find relevant schoolmate's email           | I can contact them for study sessions                                                   |
| `* * *`  | NUS student | Find relevant schoolmate's phone numbers   | I can contact them for study sessions                                                   |
| `* * *`  | NUS student | Find relevant schoolmate's course          | I can know which schoolmates take the same course                                       |
| `* * *`  | NUS student | Add schoolmates                            | I can add schoolmate's contact to NUSMeet                                               |
| `* * *`  | NUS student | Delete schoolmates                         | I can delete unwanted schoolmates                                                       |
| `* * *`  | NUS Student | Add course tags to schoolmates             | I can easily track which course they are taking and contact them to discuss school work |
| `* * *`  | NUS Student | Add friendship tags to schoolmates         | I can organize schoolmates in a friendship priority order                               |
| `* * *`  | NUS Student | Add normal tags to schoolmates             | I can make remarks on schoolmates                                                       |
| `* * *`  | NUS Student | Add previous course to schoolmates         | I can know schoolmates previous course                                                  |
| `* * *`  | CLI lover   | I want to type most of the time            | I can perform tasks with minimal time                                                   |
| `* *`    | NUS student | Edit schoolmates name                      | I can correct them if there is a typo                                                   |
| `* *`    | NUS Student | Edit schoolmates phone                     | I can correct them if there is a typo                                                   |
| `* *`    | NUS Student | Edit schoolmates email                     | I can correct them if there is a typo                                                   |
| `* *`    | NUS Student | Edit schoolmates address                   | I can correct them if there is a typo                                                   |
| `* *`    | NUS Student | Edit schoolmates friendship tag            | I can correct them if there is a typo                                                   |
| `* *`    | NUS Student | Edit schoolmates course tag                | I can correct them if there is a typo                                                   |
| `* *`    | NUS Student | Edit schoolmates normal tag                | I can correct them if there is a typo                                                   |
| `*`      | NUS Student | Toggle the colour theme of the application | So that my eyes are comfortable while using the app                                     |
| `*`      | NUS Student | Import contacts from a spreadsheet         | I can quickly add contacts I have collected                                             |



### Use cases

(For all use cases below, the **System** is the `NUSMeet` and the **Actor** is the `user`, unless specified otherwise)

### Use case: UC01 – Add Schoolmate

**Actor:** NUS Student

**MSS:**

1. User types the `add` command to add a new schoolmate along with details.
2. NUSMeet validates the input and adds the schoolmate.
3. NUSMeet displays confirmation of successful addition.  
   **Use case ends.**

**Extensions:**

- **2a.** NUSMeet detects invalid or missing input.

  - **2a1.** NUSMeet displays an error message showing the correct command format.
  - **2a2.** User re-enters the corrected command with proper details.
    - Steps 2a1–2a2 repeat until input is valid.
    - **Use case resumes from step 2.**

- **\*a.** At any time, User cancels the operation by discarding typed input.
  - **Use case ends.**

---

### Use case: UC02 – List all Schoolmates

**Actor:** NUS Student

**MSS:**

1. User types the `list` command.
2. NUSMeet retrieves and displays all schoolmate in the contact book.
   **Use case ends.**

---

### Use case: UC03 – Find Schoolmates by Course

**Actor:** NUS Student

**MSS:**

1. User types the command to find schoolmates by course code(s).
2. NUSMeet validates the input and displays a list of matching schoolmates.  
   **Use case ends.**

**Extensions:**

- **2a.** NUSMeet detects invalid or incorrectly formatted course codes.

  - **2a1.** NUSMeet displays an error message indicating the correct format for the find command.
  - **2a2.** User re-enters the command with the corrected format.
    - Steps 2a1–2a2 repeat until input is valid.
    - **Use case resumes from step 2.**

- **\*a.** At any time, User cancels the operation by discarding typed input.
  - **Use case ends.**

---

### Use case: UC04 - Edit a Schoolmate

**Actor:** NUS Student

**MSS:**

1. User types the `edit` command with the respective parameters to edit a friend using the index in the contact book.
2. NUSMeet validates the inputs.
3. NUSMeet updates the schoolmate's details.
4. NUSMeet displays confirmation of successful update.
   **Use case ends.**
**Extensions:**
- **2a.** NUSMeet detects invalid or missing required fields

    - **2a1.** NUSMeet displays an error message indicating the correct format for the edit command.
    - **2a2.** User re-enters the command with the corrected format and details.
        - Steps 2a1–2a2 repeat until input is valid.
        - **Use case resumes from step 2.**
- **\*a.** At any time, User cancels the operation by discarding typed input.
    - **Use case ends.**

---
### Use case: UC05 - Delete a Schoolmate

**Actor:** NUS Student

**MSS:**

1. User types the `delete' command with the schoolmate's index.
2. NUSMeet validates the index.
3. NUSMeet removes the respective schoolmate.
4. NUSMeet displays confirmation of successful delete.

   **Use case ends.**

**Extensions:**
- **2a.** NUSMeet detects invalid index

    - **2a1.** NUSMeet displays an error message indicating the correct format for delete command.
    - **2a2.** User re-enters the command with the corrected format.
        - Steps 2a1–2a2 repeat until input is valid.
        - **Use case resumes from step 2.**
- **\*a.** At any time, User cancels the operation by discarding typed input.
    - **Use case ends.**

---

### Non-Functional Requirements

1.  Should work on any _mainstream OS_ as long as it has Java `17` or above installed.
2.  Should be able to hold up to 1000 persons without a noticeable sluggishness in performance for typical usage. 
3. The program should work on both 32-bit and 64-bit environments. 
4. The response to any use action should become visible within 5 seconds. 
5. The source code should be open source. 
6. The data should be stored locally and should be in a human editable text file. 
7. The program should be for a single user. 
8. The program should work on a computer that has version 17 of Java. 
9. The program should work without requiring an installer.



### Glossary

- **Mainstream OS**: Windows, Linux, Unix, MacOS
- **Private contact detail**: A contact detail that is not meant to be shared with others
- **Command Line Interface (CLI)**: A software mechanism used to interact with operating system using keyboard

---

## **Appendix: Instructions for manual testing**

Given below are instructions to test the app manually.

<box type="info" seamless>

**Note:** These instructions only provide a starting point for testers to work on;
testers are expected to do more _exploratory_ testing.

</box>

### Launch and shutdown

1. Initial launch

   1. Download the jar file and copy into an empty folder

   1. Double-click the jar file Expected: Shows the GUI with a set of sample contacts. The window size may not be optimum.

1. Saving window preferences

   1. Resize the window to an optimum size. Move the window to a different location. Close the window.

   1. Re-launch the app by double-clicking the jar file.<br>
      Expected: The most recent window size and location is retained.


### Deleting a person

1. Deleting a person while all persons are being shown

   1. Prerequisites: List all persons using the `list` command. Multiple persons in the list.

   1. Test case: `delete 1`<br>
      Expected: First contact is deleted from the list. Details of the deleted contact shown in the status message. Timestamp in the status bar is updated.

   1. Test case: `delete 0`<br>
      Expected: No person is deleted. Error details shown in the status message. Status bar remains the same.

   1. Other incorrect delete commands to try: `delete`, `delete x`, `...` (where x is larger than the list size)<br>
      Expected: Similar to previous.


### Saving data

1. Dealing with missing/corrupted data files

   1. Locate the JSON file and rename/delete it while the application is closed. Relaunch the application. 
   2. A new data file is generated with sample data.


## **Appendix: Planned Enhancements**

Team Size: 5

The following are features / improvements our team has planned to implement in the future due to lack of time.
1. **Implement validation logic to enforce prerequisite rules between courses**: The current courses input accepts prerequisites to previous courses. We plan to compile a list of courses and its prerequisites to be used as a validation check before accepting courses inputs.
2. **Implement stricter course code validation**: The current and previous courses input accepts any course codes that follows the regex, but may not be an actual course in NUS. We plan to use the compiled list of courses for validation checks.
3. **Edit specific tags**: The current editing tags feature requires the user to re-enter all of their tags, even though the user only wants to edit a specific tag. We plan to implement a new prefix that allows users to edit a specific tag, without having to re-enter the existing tags.
4. **Implement editing of previous courses**: The current edit feature does not support the editing of previous courses. We plan to include this feature in subsequent iterations.
5. **Implement stricter duplication checks for contacts**:The current application allows for multiple contacts with the same name (different case sensitivity) and phone number to be added to the contact book. We plan to enforce stricter duplication checks by combining the name and phone number as a primary key, such that the same name and phone number will be flagged as the same contact.
6. **Implement less restrictive name validation**: The current symbols allowed when adding a name are `.'-`. As of now, we do not allow `/` to be included in the name field as it affects the parsing logic of our application. We plan to allow for more symbols to be included without affecting the logic in subsequent iterations.

