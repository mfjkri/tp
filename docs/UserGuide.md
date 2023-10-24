---
layout: page
title: User Guide
---

## Welcome to TutorConnect

TutorConnect is an address book made for **tuition centre coordinators** to easily track and schedule tuition classes.

Here’s an overview of what you can do with TutorConnect:
* Store and edit information about your tutors
* Create and plan your tutor availability and schedule
* View upcoming schedules

On top of these functionalities, we believe that tuition centre management must be efficient. Therefore, TutorConnect is **optimised for users who can type fast** and utilise the Command Line Interface (CLI) to complete tasks using the keyboard faster than using the mouse.

* Table of Contents
{:toc}

--------------------------------------------------------------------------------------------------------------------

## Common Notation

<div markdown="block" class="alert alert-success">

**:bulb: Tip**<br>

`Tips` are used to provide helpful advice, suggestions, or best practices to enhance your experience by making a task easier or more efficient.

</div>

<div markdown="block" class="alert alert-warning">

**:warning: Warning**<br>

`Warnings` are used to alert you about potential issues, errors, or risks associated with a task or action. They are essential for preventing mistakes and ensuring safety.

</div>

<div markdown="block" class="alert alert-info">

**:information_source: Information**<br>

`Information` sections provide additional background knowledge or context to help you understand a topic better.

</div>

--------------------------------------------------------------------------------------------------------------------

## Quick Start

1. Ensure you have Java `11` installed in your Computer. To verify, perform the following steps:
    1. Open a terminal. Refer to the following guides on how:
        1. [MacOS](https://support.apple.com/en-sg/guide/terminal/apd5265185d-f365-44cb-8b09-71a064a42125/mac#:~:text=Terminal%20for%20me-,Open%20Terminal,%2C%20then%20double%2Dclick%20Terminal)
        2. [Windows](https://www.howtogeek.com/235101/10-ways-to-open-the-command-prompt-in-windows-10/#:~:text=anywhere%20you%20like.-,Open%20Command%20Prompt%20from%20the%20Run%20Box,open%20an%20administrator%20Command%20Prompt)
    2. Type `java -version` and press Enter.
    3. If it says a version other than 11 or `command not found`, please install Java 11 by following this [guide](https://www.java.com/en/download/help/download_options.html).

2. Download the latest jar file (tutorconnect.jar) from our [Github Releases](https://github.com/AY2324S1-CS2103T-T17-3/tp/releases).

3. Move the jar file into a new folder called “TutorConnect”.

4. Double-click the jar file to launch the application.

    <div markdown="block" class="alert alert-info">

   **:information_source: Mac Users**<br>

   If you are a Mac user, you may encounter a warning that says
   the jar file cannot be opened because it is from an unidentified developer. To continue:

    1. Right-click on the jar file and select `Open With > JavaLauncher (default)`
    2. Press Open when prompted

   </div>

5. Refer to the [Features](#features) below for details of each command.

--------------------------------------------------------------------------------------------------------------------

## Navigating the Interface

TutorConnect comes equipped with an interface that provides visual feedback to you.
Below is a quick overview of the various components of our interface.

### Quick Orientation

![ui overview](images/uiOverview.png)

| Component      | Description                                                                                | 
|----------------|--------------------------------------------------------------------------------------------|
| Command Box    | You will enter your commands here.                                                         | 
| Result Display | Displays the results of your commands.<br/>Any error messages will also be displayed here. | 
| Tutor Details  | Contains information related to the tutor like name, phone number etc.                     |

--------------------------------------------------------------------------------------------------------------------

## TutorConnect Tutorial (for new users)
More details coming soon...

--------------------------------------------------------------------------------------------------------------------

## Features

<div markdown="block" class="alert alert-info">

**:information_source: Command Format:**<br>

* Words in `UPPER_CASE` are the parameters to be supplied by the user.<br>
  e.g. in `add n/NAME`, `NAME` is a parameter which can be used as `add n/John Doe`.
* Items in square brackets are optional.<br>
  e.g `n/NAME [t/TAG]` can be used as `n/John Doe t/friend` or as `n/John Doe`.
* Items with `…`​ after them can be used multiple times including zero times.<br>
  e.g. `[t/TAG]…​` can be used as ` ` (i.e. 0 times), `t/friend`, `t/friend t/family` etc.
* Parameters can be in any order.<br>
  e.g. if the command specifies `n/NAME p/PHONE_NUMBER`, `p/PHONE_NUMBER n/NAME` is also acceptable.
* Extraneous parameters for commands that do not take in parameters (such as `help`, `list`, `exit` and `clear`)
  will be ignored.<br>
  e.g. if the command specifies `help 123`, it will be interpreted as `help`.
* If you are using a PDF version of this document, be careful when copying and pasting commands that span multiple
  lines as space characters surrounding line-breaks may be omitted when copied over to the application.

</div>

### Adding a tutor: `add-t`

Adds a tutor to the address book.

![add tutor](images/addTutor.png)

**Format**: `add-t n/NAME p/PHONE NUMBER e/EMAIL`

**Example**:
* `add-t n/John Doe p/98765432 e/johnd@example.com`
* `add-t n/Betsy Crowe p/91234567 e/betsycrowe@example.com`

**Acceptable values for each parameter**:
* `NAME`: Only contain alphanumeric characters and spaces, and should not be blank
* `PHONE NUMBER`: Only contain numbers, and should be at least 3 digits long
* `EMAIL`: Of the format local-part@domain

    <div markdown="block" class="alert alert-info">

    **:information_source: Email Format**<br>

    1. The local-part should only contain alphanumeric characters and these special characters, excluding the parentheses, (+_.-). The local-part may not start or end with any special characters.
    2. This is followed by a '@' and then a domain name. The domain name is made up of domain labels separated by periods.

    The domain name must:
    * end with a domain label at least 2 characters long
    * have each domain label start and end with alphanumeric characters
    * have each domain label consist of alphanumeric characters, separated only by hyphens, if any.
    </div>

**Expected output**:
* `New tutor John Doe; Phone: 98765432; Email: johnd@example.com has been added.`

**Error messages**:
* `Invalid command format!`: One or more of the tags `n/`, `p/`, `e/` is missing. 
* `Names should only contain alphanumeric characters and spaces, and it should not be blank`: Tutor name input was 
  either invalid or blank. 
* `Phone numbers should only contain numbers, and it should be at least 3 digits long`: Tutor phone number input was
  either invalid or blank.
* `Emails should be of the format local-part@domain and adhere to the following constraints:`: Tutor email input was
  either invalid or blank.

### Listing all tutors: `list-t`

Displays a list of all tutors in the address book in a table format.

![list tutor](images/listTutor.png)

**Format**: `list-t`

**Example**:
* `list-t`

**Expected Output**:
* `Listed all tutors`

    <div markdown="block" class="alert alert-info">

    **:information_source: Information**<br>
    
    * list-t command does not take in any parameters.
    * Any extraneous parameters after `list-t` will be ignored.
      e.g. if the command specifies `list-t 123`, it will be interpreted as `list-t`.
    * No error messages as anything typed behind is ignored.
    
    </div>


### Locating tutors by name: `find`

Find tutors whose names contain any of the given keywords.

![find tutor](images/findTutor.png)

**Format**: `find KEYWORD [MORE_KEYWORDS]`

**Examples**:
* `find John` returns `John Smith` and `John Doe`
* `find Alex David Li` returns `Alex David Li`

**Acceptable values for each parameter**:
* `KEYWORD`: Any input accepted

    <div markdown="block" class="alert alert-info">

    **:information_source: Search behaviour**<br>

    * Search is case-insensitive. e.g. `hans` will match `Hans`
    * The order of the keywords does not matter. e.g. `Hans Bo` will match `Bo Hans`
    * Only the tutor name is searched.
    * Only full words will be matched. e.g. `Han` will not match `Hans`
    * Tutors matching at least one keyword will be returned (i.e. OR search). e.g. `Hans Bo` will return `Hans Gruber`, `Bo Yang`

    </div>

**Expected output**:
* `2 tutors listed!`
* `0 tutors listed!`

**Error messages**:
* `Invalid command format!`: No search keyword provided.

### Deleting a tutor: `delete-t`

Deletes a tutor in the address book based on their index number in the table.

![delete tutor](images/deleteTutor.png)

**Format**: `delete-t TUTOR_INDEX`

**Examples**:
* `delete-t 5` deletes the tutor that is indexed as 5 in the address book.
* `list` followed by `delete-t 2` deletes the 2nd person in the address book.

**Acceptable values for each parameter**:
* `TUTOR_INDEX`: Only accepts numerical value

**Expected output**:
* `Tutor has been deleted: Alex Yeoh; Phone: 87438807;
  Email: alexyeoh@example.com; Address: Blk 30 Geylang Street 29, #06-40`

**Error messages**:
* `Invalid command format!`: No tutor index provided.
* `Index number given is out of range`: Given tutor index is out of range.
* `Invalid parameter given`: Parameter given is not a numerical value.

### Adding a schedule: `add-s`

Adds a schedule to a specified tutor.

![add schedule](images/addSchedule.png)

**Format**: `add-s TUTOR_INDEX s/START_TIME e/END_TIME`

**Examples**:
* `add-s 1 s/2023-09-15T09:00:00 e/2023-09-15T11:00:00`
* `add-s 2 s/2023-09-16T17:00:00 e/2023-09-16T19:00:00`

**Acceptable values for each parameter**:
* `TUTOR_INDEX`: Only number input accepted, starting from 1 to the last tutor index shown in the list of tutors.
* `START_TIME`: Only datetime in `yyyy-MM-ddTHH:mm:ss` format is accepted
* `END_TIME`: Only datetime in `yyyy-MM-ddTHH:mm:ss` format is accepted

    <div markdown="block" class="alert alert-info">

  **:information_source: Information**<br>

    * `yyyy` represents the **year** (e.g., 2023).
    * `MM` represents the **month** with a leading zero (e.g., 09 for September, 10 for October).
    * `dd` represents the **day** of the month with a leading zero (e.g., 03, 15).
    * `T` is a **separator** indicating the start of the time portion.
    * `HH` represents the **hour** in **24-hour format** with a leading zero (e.g., 09 for 9 AM, 21 for 9 PM).
    * `mm` represents the **minutes** with a leading zero (e.g., 05).
    * `ss` represents the **seconds** with a leading zero (e.g., 05).

    </div>

**Expected output**:
* `New schedule John Doe; Start Time Sep 15 2023 09:00; End Time: Sep 15  2023 11:00 has been added.`

**Error messages**:
* `Invalid command format!`: Invalid TUTOR_INDEX or some of the tags `s/`, `e/` is missing.
* `EndTime should only contain a valid date and time in the format "yyyy-MM-ddTHH:mm:ss", and it should not be blank`: 
  The start time entered is not in the correct datetime format.
* `StartTime should only contain a valid date and time in the format "yyyy-MM-ddTHH:mm:ss", and it should not be blank`: The end time entered is not in the correct datetime format.
* `Multiple values specified for the following single-valued field(s): s/`: More than 1 s/ was given in the command
* `Multiple values specified for the following single-valued field(s): e/`: More than 1 e/ was given in the command
* `This schedule already exists in the address book`: There is a schedule for the same tutor with the same start and end time in the address book.
* `This tutor has a clashing schedule in the address book`: There is a schedule for the same tutor with overlapping times in the address book.

### Editing a schedule: `edit-s`

Edits an existing schedule in the addressbook.

![edit schedule](images/editSchedule.png)

**Format**: `edit-s SCHEDULE_INDEX s/START_TIME e/END_TIME`

**Examples**:
* `edit-s 1 s/2023-09-15T13:00:00`
* `edit-s 2 e/2023-09-16T19:00:00`

**Acceptable values for each parameter**:
* `SCHEDULE_INDEX`: Only number input accepted, starting from 1 to the last schedule index shown in the list of 
  schedules.
* `START_TIME`: Only datetime in `yyyy-MM-ddTHH:mm:ss` format is accepted
* `END_TIME`: Only datetime in `yyyy-MM-ddTHH:mm:ss` format is accepted

**Expected output**:
* `Edited Schedule: Alex Yeoh; Start Time: Sep 15 2023 09:00; End Time: Sep 15 2023 13:00`

**Error messages**:
* `Invalid command format!`: Invalid or missing SCHEDULE_INDEX.
* `EndTime should only contain a valid date and time in the format "yyyy-MM-ddTHH:mm:ss", and it should not be blank`:
  The end time entered is not in the correct datetime format.
* `StartTime should only contain a valid date and time in the format "yyyy-MM-ddTHH:mm:ss", and it should not be 
  blank`: The start time entered is not in the correct datetime format.
* `Multiple values specified for the following single-valued field(s): s/`: More than 1 `s/` was given in the command
* `Multiple values specified for the following single-valued field(s): e/`: More than 1 `e/` was given in the command
* `This schedule already exists in the address book`: There is a schedule for the same tutor with the same start and end time in the address book.
* `This tutor has a clashing schedule in the address book`: There is a schedule for the same tutor with overlapping times in the address book.
* `At least one field to edit must be provided.`: There is no `s/` or `e/` tag provided to edit a field.

### List all schedules: `list-s`

Displays a list of all schedules in the address book in a table format.


![view schedule](images/viewSchedule.png)

**Format:** `list-s`

**Example:**
* `list-s` shows all recorded schedules in the address book.

**Expected output:**
* Displays a table of schedules with columns for List number, Tutor Name, Start Time, and End Time
* If there are no schedules in the address book, displays a message telling the user to add a tutor with `add-s`.

![empty schedule list](images/emptyScheduleList.png)


<div markdown="block" class="alert alert-info">

**:information_source: Information**<br>
    
* list-s command does not take in any parameters.
* Any extraneous parameters after `list-s` will be ignored.
    e.g. if the command specifies `list-s 123`, it will be interpreted as `list-s`.
* No error messages as anything typed behind is ignored.
    
</div>

### Deleting a schedule: `delete-s`

Deletes a schedule in the address book based on their index number in the table of schedules listed.

![delete schedule](images/deleteSchedule.png)

**Format:** `delete-s SCHEDULE_INDEX`

**Example:**
* `delete-s 5` deletes the schedule that is indexed as 5 in the schedule list.
* `list` followed by `delete-s 2` deletes the 2nd schedule in the schedule list.

**Acceptable values for each parameter:**
* `SCHEDULE_INDEX`: Only numerical input that ranges from 1 to the last schedule shown in the list of schedules.

**Expected output:**
* `Schedule has been deleted: Tutor: John Doe; Start date: Sep 15, 2023 09:00; End date: Sep 15, 2023 11:00;`

**Error messages:**
* `Index number given is out of range`: Given index is out of range.
* `Invalid value in parameter SCHEDULE_INDEX`: Parameter given is not a numerical value.
* `Missing parameter SCHEDULE_INDEX`: A numerical value is not provided when calling the command `delete-s`.


--------------------------------------------------------------------------------------------------------------------

## FAQ

**Q**: How do I transfer my data to another Computer?<br>
**A**: Install the app in the other computer and overwrite the empty data file it creates with the file that contains the data of your previous AddressBook home folder.

**Q**: What if I make a mistake when typing a command?<br>
**A**: Don't worry! If you make a mistake while typing a command, TutorConnect will provide an error message to guide you. Simply correct the command and try again.

**Q**: Can I view schedules in a different time zone?<br>
**A**: Currently, TutorConnect displays schedules in your local time zone. If you need to coordinate with tutors in different time zones, you may consider using a world clock or time zone converter alongside the application.

**Q**: Is there a way to export my data from TutorConnect?<br>
**A**: Currently, TutorConnect doesn't have an export feature. However, you can manually copy the `data.json` file from the "TutorConnect" folder for backup or transfer purposes.

**Q**: How do I report a bug or suggest a feature?<br>
**A**: We value your feedback! Please visit our [Github repository](https://github.com/AY2324S1-CS2103T-T17-3/tp) and open an issue to report a bug or make a feature request.

**Q**: I'm encountering an issue not covered here. What should I do?<br>
**A**: Feel free to reach out to our support team at [support@tutorconnect.com](mailto:support@tutorconnect.com). We'll do our best to assist you.

--------------------------------------------------------------------------------------------------------------------

## Known issues

1. **When using multiple screens**, if you move the application to a secondary screen, and later switch to using only the primary screen, the GUI will open off-screen. The remedy is to delete the `preferences.json` file created by the application before running the application again.

--------------------------------------------------------------------------------------------------------------------

## Glossary

### Definitions
Here are some descriptions of the words you might come across in the User Guide:

| Term                | Definition                                                                                                                                                                                                                                                 |
|---------------------|------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| Parameter           | Parameters are like fields in a form you are required to fill up. They include details you provide to execute a command. <br> For example, in the command `add-t n/NAME p/PHONE NUMBER e/EMAIL`, `n/NAME`, `p/PHONE NUMBER`, and `e/EMAIL` are parameters. |
| Command             | A command is an instruction given to the application to perform a specific action. For example, `list-t` is a command to list all tutors in the address book.                                                                                              |
| GUI                 | GUI is a user interface that allows users to interact with the application using graphical elements like text fields, buttons, and menus.                                                                                                                  |
| CLI                 | CLI is a text-based user interface that allows users to interact with the application by typing commands.                                                                                                                                                  |


### Parameter Information
Here are some parameters you might come across in the User Guide:

| Parameter        | Description                                     | Limitations                                                                                  |
|------------------|-------------------------------------------------|----------------------------------------------------------------------------------------------|
| `n/NAME`         | Refers to the name of the tutor.                | Only contain alphanumeric characters and spaces, and <br/>should <br/>not be blank.          |
| `p/PHONE NUMBER` | Refers to the phone number of the tutor.        | Only contain numbers, and should be at least 3 <br/>digits long.                             |
| `e/EMAIL`        | Refers to the email address of the tutor.       | Of the format local-part@domain<sup>1</sup>.                                                 |
| `s/START_TIME`   | Refers to the start time of the schedule.       | Only datetime in `yyyy-MM-ddTHH:mm:ss`<sup>2</sup> format is accepted.                       |
| `e/END_TIME`     | Refers to the end time of the schedule.         | Only datetime in `yyyy-MM-ddTHH:mm:ss` format is accepted.                                   |
| `TUTOR_INDEX`    | Refers to the position of tutor in the list.    | Only numerical input that ranges from 1 to the last tutor shown in the list of tutors.       |
| `SCHEDULE_INDEX` | Refers to the position of schedule in the list. | Only numerical input that ranges from 1 to the last schedule shown in the list of schedules. |


### Parameter Format
This section consists of more details of format limitations mentioned above.

**<sup>1</sup> Email Format**

    1. The local-part should only contain alphanumeric characters and these special characters, excluding the parentheses, (+_.-). The local-part may not start or end with any special characters.
    2. This is followed by a '@' and then a domain name. The domain name is made up of domain labels separated by periods.

    The domain name must:
    * end with a domain label at least 2 characters long
    * have each domain label start and end with alphanumeric characters
    * have each domain label consist of alphanumeric characters, separated only by hyphens, if any.

**<sup>2</sup> `yyyy-MM-ddTHH:mm:ss`**

    * `yyyy` represents the **year** (e.g., 2023).
    * `MM` represents the **month** with a leading zero (e.g., 09 for September, 10 for October).
    * `dd` represents the **day** of the month with a leading zero (e.g., 03, 15).
    * `T` is a **separator** indicating the start of the time portion.
    * `HH` represents the **hour** in **24-hour format** with a leading zero (e.g., 09 for 9 AM, 21 for 9 PM).
    * `mm` represents the **minutes** with a leading zero (e.g., 05).
    * `ss` represents the **seconds** with a leading zero (e.g., 05).


--------------------------------------------------------------------------------------------------------------------

## Command summary

| Action              | Format, Examples                                                                                                            |
|---------------------|-----------------------------------------------------------------------------------------------------------------------------|
| **Add Tutor**       | `add-t n/NAME p/PHONE NUMBER e/EMAIL` <br> e.g., `add-t n/John Doe p/98765432 e/johnd@example.com`                          |
| **List Tutor**      | `list-t`                                                                                                                    |
| **Delete Tutor**    | `delete-t TUTOR_INDEX`<br> e.g., `delete-t 3`                                                                               |
| **Add Schedule**    | `add-s ti/TUTOR_INDEX s/START_TIME e/END_TIME` <br> e.g., `add-s ti/1 s/2023-09-15T09:00:00 e/2023-09-15T11:00:00`          |
| **Edit Schedule**   | `edit-s SCHEDULE_INDEX s/START_TIME e/END_TIME` <br> e.g., `edit-s 1 s/2023-09-15T13:00:00`                                 |
| **List Schedule**   | `list-s`                                                                                                                    |
| **Delete Schedule** | `delete-s INDEX_NO`<br> e.g., `delete-s 3`                                                                                  |
| **Clear**           | `clear`                                                                                                                     |
| **Edit**            | `edit INDEX [n/NAME] [p/PHONE_NUMBER] [e/EMAIL] [a/ADDRESS] [t/TAG]…​`<br> e.g.,`edit 2 n/James Lee e/jameslee@example.com` |
| **Find**            | `find KEYWORD [MORE_KEYWORDS]`<br> e.g., `find James Jake`                                                                  |
