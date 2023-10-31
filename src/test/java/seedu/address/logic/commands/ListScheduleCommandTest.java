package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showPersonAtIndex;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_PERSON;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.Messages;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Person;
import seedu.address.model.schedule.TutorPredicate;
import seedu.address.testutil.TypicalSchedules;

/**
 * Contains integration tests (interaction with the Model) and unit tests for ListTutorCommand.
 */
public class ListScheduleCommandTest {

    private Model model;
    private Model expectedModel;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(TypicalSchedules.getTypicalAddressBook(), new UserPrefs());
        expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
    }

    @Test
    public void execute_listIsNotFiltered_showsSameList() {
        assertCommandSuccess(new ListScheduleCommand(null), model,
            ListScheduleCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_validIndexUnfilteredList_success() {
        Person tutor = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        TutorPredicate predicate = new TutorPredicate(tutor);
        ListScheduleCommand listScheduleCommand = new ListScheduleCommand(INDEX_FIRST_PERSON);

        String expectedMessage = String.format(Messages.MESSAGE_SCHEDULES_LISTED_OVERVIEW, 2);
        ModelManager expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.updateFilteredScheduleList(predicate);
        assertCommandSuccess(listScheduleCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredPersonList().size() + 1);
        ListScheduleCommand listScheduleCommand = new ListScheduleCommand(outOfBoundIndex);

        assertCommandFailure(listScheduleCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    @Test
    public void execute_validIndexFilteredList_success() {
        showPersonAtIndex(model, INDEX_FIRST_PERSON);

        Person tutor = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        TutorPredicate predicate = new TutorPredicate(tutor);
        ListScheduleCommand listScheduleCommand = new ListScheduleCommand(INDEX_FIRST_PERSON);

        Model expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.updateFilteredScheduleList(predicate);
        showPersonAtIndex(expectedModel, INDEX_FIRST_PERSON);

        String expectedMessage = String.format(Messages.MESSAGE_SCHEDULES_LISTED_OVERVIEW,
            expectedModel.getFilteredScheduleList().size());

        assertCommandSuccess(listScheduleCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexFilteredList_throwsCommandException() {
        showPersonAtIndex(model, INDEX_FIRST_PERSON);

        Index outOfBoundIndex = INDEX_SECOND_PERSON;
        // ensures that outOfBoundIndex is still in bounds of address book list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getAddressBook().getPersonList().size());

        ListScheduleCommand listScheduleCommand = new ListScheduleCommand(outOfBoundIndex);

        assertCommandFailure(listScheduleCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        ListScheduleCommand listFirstCommand = new ListScheduleCommand(INDEX_FIRST_PERSON);
        ListScheduleCommand listSecondCommand = new ListScheduleCommand(INDEX_SECOND_PERSON);

        // same object -> returns true
        assertTrue(listFirstCommand.equals(listFirstCommand));

        // same values -> returns true
        ListScheduleCommand listFirstCommandCopy = new ListScheduleCommand(INDEX_FIRST_PERSON);
        assertTrue(listFirstCommand.equals(listFirstCommandCopy));

        // different types -> returns false
        assertFalse(listFirstCommand.equals(1));

        // null -> returns false
        assertFalse(listFirstCommand.equals(null));

        // different tutor -> returns false
        assertFalse(listFirstCommand.equals(listSecondCommand));
    }
}
