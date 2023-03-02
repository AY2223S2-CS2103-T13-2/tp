---
layout: page
title: Developer Guide
---
* Table of Contents
  {:toc}

### User stories

Priorities: High (must have) - `* * *`, Medium (nice to have) - `* *`, Low (possible but unlikely to add) - `*`

| Priority | As a …​ | I want to …​                 | So that I can…​                                |
|----------|---------|------------------------------|------------------------------------------------|
| `* * *`  | user    | add a new recipe             | reference it in the future                     |
| `* * *`  | user    | list all my existing recipes | get an overview of my whole cook book          |
| `* * *`  | user    | view an existing recipe      | recall details of what I have previously added |
| `* * *`  | user    | delete an existing recipe    | remove recipes I no longer like                |

*{More to be added soon!}*

### Use cases

(For all use cases below, the **Book** is `RIZZipe` and the **Chef** is the `user`, unless specified otherwise)

**Use case: Delete a recipe**

**MSS**

1.  Chef requests to list recipes
2.  Book shows a list of ***all*** recipes
3.  Chef requests to delete a specific recipe in the list
4.  Book deletes the person

    Use case ends.

**Extensions**

* 2a. The list is empty.

  Use case ends.

* 3a. The given index is invalid.

    * 3a1. Book shows an error message.

      Use case resumes at step 2.

*{More to be added}*

### Non-Functional Requirements

1.  Should work on any _mainstream OS_ as long as it has Java `11` or above installed.
2.  Should be able to hold up to 1000 persons without a noticeable sluggishness in performance for typical usage.
3.  A user with above average typing speed for regular English text (i.e. not code, not system admin commands) should be able to accomplish most of the tasks faster using commands than using the mouse.

*{More to be added}*

### Glossary

* **Mainstream OS**: Windows, Linux, Unix, OS-X
* **Main Success Scenario (MSS)**: The main or optimal flow of a use case, representing the successful completion of the user's goal.
* **Use Case**: A description of a specific user goal or task and the steps required to achieve it.
* **Chef**: The user or operator of the application.
* **Recipe**: A set of instructions to prepare and cook a specific dish. Includes dish name, ingredients, serving size, preparation time and any dietary labels.
* **Book**: Refers to the application or system that manages the storage and retrieval of recipe data.
* **Storage file**: The file used by the application to store and retrieve recipe data.
* **Index**: Refers to the position of a specific recipe within a list of recipes, represented by a numerical value.
--------------------------------------------------------------------------------------------------------------------

## **Appendix: Instructions for manual testing**

Given below are instructions to test the app manually.

<div markdown="span" class="alert alert-info">:information_source: **Note:** These instructions only provide a starting point for testers to work on;
testers are expected to do more *exploratory* testing.

</div>

### Launch and shutdown

1. Initial launch

   1. Download the jar file and copy into an empty folder

   1. Double-click the jar file Expected: Shows the GUI with a set of sample contacts. The window size may not be optimum.

1. Saving window preferences

   1. Resize the window to an optimum size. Move the window to a different location. Close the window.

   1. Re-launch the app by double-clicking the jar file.<br>
       Expected: The most recent window size and location is retained.

1. _{ more test cases …​ }_

### Deleting a person

1. Deleting a person while all persons are being shown

   1. Prerequisites: List all persons using the `list` command. Multiple persons in the list.

   1. Test case: `delete 1`<br>
      Expected: First contact is deleted from the list. Details of the deleted contact shown in the status message. Timestamp in the status bar is updated.

   1. Test case: `delete 0`<br>
      Expected: No person is deleted. Error details shown in the status message. Status bar remains the same.

   1. Other incorrect delete commands to try: `delete`, `delete x`, `...` (where x is larger than the list size)<br>
      Expected: Similar to previous.

1. _{ more test cases …​ }_

### Saving data

1. Dealing with missing/corrupted data files

   1. _{explain how to simulate a missing/corrupted file, and the expected behavior}_

1. _{ more test cases …​ }
