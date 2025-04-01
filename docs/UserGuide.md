# NUSMeet User Guide

**NUSMeet** is your go-to **Command Line Interface (CLI)** application for **keeping track** of your friends and schoolmates at NUS. No more scrambling to remember who’s taking the same module as you — just **_tag_**, **_filter_**, and **_find_** the right people to study with in seconds.

Designed for NUS students (by NUS students!), NUSMeet is perfect for those with an already established network of contacts who want to meet others to discuss schoolwork.  NUSMeet makes **organizing your contacts effortless** so you can focus on what really matters — acing your studies with the right people by your side! 👫👫

---

## 🛠️ Quick start

Follow these steps to install and run NUSMeet on your computer!

1. Before running NUSMeet, ensure that you have **Java `17` or above** installed in your Computer.<br>

   - **Windows users:** Install the precise JDK version prescribed [here](https://se-education.org/guides/tutorials/javaInstallationWindows.html).
   - **Mac users:** Install the precise JDK version prescribed [here](https://se-education.org/guides/tutorials/javaInstallationMac.html).
   - **Linux users** Install the precise JDK version prescribed [here](https://se-education.org/guides/tutorials/javaInstallationLinux.html).

2. Download the latest `.jar` file [here](https://github.com/AY2425S2-CS2103T-T13-3/tp/releases).

3. Move the downloaded file to a folder of your choice to be used as the _home folder_ for your NUSMeet application.

4. Open a command terminal and navigate to the folder where you placed the `.jar` file using `cd /path/to/your/folder`. Type the `java -jar NUSMeet.jar` command to run the application.<br>
   A **Graphical User Interface (GUI)** similar to the one shown below should appear in a few seconds.<br>
   ![Ui](images/Ui.png)

5. Type the [command](#-command-overview) in the command box and press Enter to execute it. e.g. typing **`help`** and pressing Enter will open the help window.<br>

6. Refer to the [Features](#-features) below for details of each command.

---

## ⌨️ Command Overview

Now that you have set up NUSMeet, let’s explore the key commands you can use!

| Action                                      | Format, Examples                                                                                                                                                                                        |
| ------------------------------------------- | ------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- |
| **[Add](#2-adding-a-friend-add)**           | `add n/NAME p/PHONE_NUMBER e/EMAIL a/ADDRESS [c/COURSE]…​ [t/TAG]…​ [f/FRIENDSHIP_LEVEL]` <br> e.g., `add n/James Ho p/22224444 e/jamesho@example.com a/123, Clementi Rd, 1234665 t/friend t/colleague` |
| **[Clear](#7-clearing-all-entries--clear)** | `clear`                                                                                                                                                                                                 |
| **[Delete](#6-deleting-a-friend--delete)**  | `delete INDEX`<br> e.g., `delete 3`                                                                                                                                                                     |
| **[Edit](#4-editing-a-friend--edit)**       | `edit INDEX [n/NAME] [p/PHONE] [e/EMAIL] [a/ADDRESS] [c/COURSE]…​ [f/FRIENDSHIP] [t/TAG]…​`<br> e.g.,`edit 2 n/James Lee e/jameslee@example.com`                                                        |
| **[Find](#5-finding-friends-find)**         | `find [n/KEYWORD [MORE_KEYWORDS]] [c/KEYWORD [MORE_KEYWORDS]]`<br> e.g., `find n/James Jake c/CS2101 ST2334`                                                                                            |
| **[List](#3-listing-all-friends--list)**    | `list`                                                                                                                                                                                                  |
| **[Help](#1-viewing-help--help)**           | `help`                                                                                                                                                                                                  |

<box type="info" seamless>

**Notes about the command format:**<br>

#### 1. Upper Case

- Words in `UPPER_CASE` are the parameters you should supply!<br>
  e.g. in `add n/NAME`, `NAME` is a parameter which can be used as `add n/John Doe`.

#### 2. Square Brackets `[ ]`

- Items in square brackets are _optional_.<br>
  e.g `n/NAME [t/TAG]` can be used as `n/John Doe t/friend` or as `n/John Doe`.

#### 3. Triple Dots `…​`

- Items with `…`​ after them can be used _multiple times_ including zero times.<br>
  e.g. `[t/TAG]…​` can be used as ` ` (i.e. 0 times), `t/friend`, `t/friend t/family` etc.

#### 4. Ordering

- Parameters can be in _any order_.<br>
  e.g. if the command specifies `n/NAME p/PHONE_NUMBER`, `p/PHONE_NUMBER n/NAME` is also acceptable.

#### 5. Parameters

- Extraneous parameters for commands that do not take in parameters (such as `help`, `list`, `exit` and `clear`) will be ignored.<br>
  e.g. if the command specifies `help 123`, it will be interpreted as `help`.

#### 6. PDF

- If you are using a PDF version of this document, be careful when copying and pasting commands that span multiple lines as space characters surrounding line-breaks may be omitted when copied over to the application.
  </box>

## 🚀 Features

### 1. Viewing help : `help`

Shows a message explaining how to access the help page.

![help message](images/helpMessage.png)

Format: `help`

### 2. Adding a friend: `add`

Adds a friend to your contact book.

Format: `add n/NAME p/PHONE_NUMBER e/EMAIL a/ADDRESS [c/COURSE]…​ [t/TAG]…​ [f/FRIENDSHIP_LEVEL]`

<box type="tip" seamless>

**Note:**

- The Friend Tag can only be one of the following `CLOSE_FRIEND`, `FRIEND`, `ACQUAINTANCES`
- A friend can have any number of tags & courses
  </box>

Examples:

- `add n/John Doe p/98765432 e/johnd@example.com a/John street, block 123, #01-01 c/CS2103T c/CS2101 t/owesMoney f/CLOSE_FRIEND`
- `add n/Betsy Crowe e/betsycrowe@example.com a/Newgate Prison p/1234567 f/FRIEND`

![help message](images/addFeatureExampleResult.png)

### 3. Listing all friends : `list`

Shows a list of all your friends in your contact book.

Format: `list`

### 4. Editing a friend : `edit`

Edits an existing friend's details in your contact book.

Format: `edit INDEX [n/NAME] [p/PHONE] [e/EMAIL] [a/ADDRESS] [c/COURSE]…​ [f/FRIENDSHIP] [t/TAG]…​`

- Edits the friend at the specified `INDEX`. The index refers to the index number shown in the displayed friend list. The index **must be a positive integer** 1, 2, 3, …​
- At least one of the optional fields must be provided.
- Existing values will be updated to the input values.
- When editing tags, the existing tags of the friend will be removed i.e adding of tags is not cumulative.
- You can remove all the friend’s tags by typing `t/` without
  specifying any tags after it.

Examples:

- `edit 1 p/91234567 e/johndoe@example.com` Edits the phone number and email address of the 1st friend to be `91234567` and `johndoe@example.com` respectively.
- `edit 2 n/Betsy Crower` Edits the name of the 2nd friend to be `Betsy Crower`.

![help message](images/editFeatureExampleResult.png)

### 5. Finding friends: `find`

Finds friends who contain any of the given keywords and courses.

Format: `find [n/KEYWORD [MORE_KEYWORDS]] [c/KEYWORD [MORE_KEYWORDS]]`

- The search is case-insensitive. e.g `hans` will match `Hans`
- The order of the keywords does not matter. e.g. `Hans Bo` will match `Bo Hans`
- At least one of the optional fields must be provided.
- The name (`n/`) or courses (`c/`) or both is searched.
- Only full words will be matched e.g. `Han` will not match `Hans`, `2103` will not match `2103T`
- Persons matching at least one keyword will be returned (i.e. `OR` search).
  e.g. `Hans Bo` will return `Hans Gruber`, `Bo Yang`

Examples:

- `find n/John Baker` returns `John Doe` and `Alice Baker` and `John Baker`

![result for 'find john baker'](images/findJohnBakerResult.png)

### 6. Deleting a friend : `delete`

Deletes the specified friend from your contact book.

Format: `delete INDEX`

- Deletes the friend at the specified `INDEX`.
- The index refers to the index number shown in the displayed friend list.
- The index **must be a positive integer** 1, 2, 3, …​

Examples:

- `list` followed by `delete 2` deletes the 2nd friend in the address book.
- `find Betsy` followed by `delete 1` deletes the 1st friend in the results of the `find` command.

![help message](images/deleteFeatureExampleResult.png)
### 7. Clearing all entries : `clear`

Clears all entries from your contact book.

Format: `clear`

![help message](images/clearFeatureExampleResult.png)

### 8. Exiting the program : `exit`

Exits the program.

Format: `exit`

### 9. Saving your data

NUSMeet automatically saves all changes to disk. There is no need to manually save your data.

### 10. Editing the data file

NUSMeet automatically stores your contact as a JSON file at: `[JAR file location]/data/addressbook.json`.
You can manually edit this file.

**⚠️Caution:**

- If file format is **invalid**, NUSMeet will **discard** all data and start with an empty data file at the next run.
- Certain **incorrect** edits can cause **unexpected** behavior (e.g., invalid values).
- Recommended to **backup** the file before making any changes

### 11. Automatic Courses Archival

At the end of each semester, NUSMeet will automatically update all entries in your contact book. A friend's current courses will be cleared and moved to the "Previous Courses" section. This saves you time and effort, as you no longer need to manually update each contact.

---

## 📝 FAQ

**Q**: How do I transfer my data to another Computer?<br>
**A**: Install the app in the other computer and overwrite the empty data file it creates with the file that contains the data of your previous NUSMeet home folder.

---

## ❗Known issues

1. **When using multiple screens**, if you move the application to a secondary screen, and later switch to using only the primary screen, the GUI will open off-screen. <br> **Solution:** Delete the `preferences.json` file created by the application before running the application again.
2. **If you minimize the Help Window** and then run the `help` command (or use the `Help` menu, or the keyboard shortcut `F1`) again, the original Help Window will remain minimized, and no new Help Window will appear. <br> **Solution:** Manually restore the minimized Help Window.

---

## 📖 Glossary

| Terms                | Definitions                                                                                    |
| -------------------- | ---------------------------------------------------------------------------------------------- |
| **CLI**              | A text-based interface where users interact with an application                                |
| **Command Terminal** | A console application where users enter commands.                                              |
| **Java**             | A programming language required to run an application.                                         |
| **JDK**              | A software package needed to run Java applications.                                            |
| **JSON**             | JavaScript Object Notation (JSON) is a lightweight data format used to store and exchange data |
| **GUI**              | A visual interface that allows users to interact with an application.                          |
