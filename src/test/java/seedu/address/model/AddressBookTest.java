package seedu.address.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalTutors.ALICE;
import static seedu.address.testutil.TypicalTutors.getTypicalAddressBook;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.tutor.Tutor;
import seedu.address.model.tutor.exceptions.DuplicateTutorException;
import seedu.address.testutil.TutorBuilder;

public class AddressBookTest {

    private final AddressBook addressBook = new AddressBook();

    @Test
    public void constructor() {
        assertEquals(Collections.emptyList(), addressBook.getTutorList());
    }

    @Test
    public void resetData_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> addressBook.resetData(null));
    }

    @Test
    public void resetData_withValidReadOnlyAddressBook_replacesData() {
        AddressBook newData = getTypicalAddressBook();
        addressBook.resetData(newData);
        assertEquals(newData, addressBook);
    }

    @Test
    public void resetData_withDuplicateTutors_throwsDuplicateTutorException() {
        // Two tutors with the same identity fields
        Tutor editedAlice = new TutorBuilder(ALICE).withTags(VALID_TAG_HUSBAND)
                .build();
        List<Tutor> newTutors = Arrays.asList(ALICE, editedAlice);
        AddressBookStub newData = new AddressBookStub(newTutors);

        assertThrows(DuplicateTutorException.class, () -> addressBook.resetData(newData));
    }

    @Test
    public void hasTutor_nullTutor_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> addressBook.hasTutor(null));
    }

    @Test
    public void hasTutor_tutorNotInAddressBook_returnsFalse() {
        assertFalse(addressBook.hasTutor(ALICE));
    }

    @Test
    public void hasTutor_tutorInAddressBook_returnsTrue() {
        addressBook.addTutor(ALICE);
        assertTrue(addressBook.hasTutor(ALICE));
    }

    @Test
    public void hasTutor_tutorWithSameIdentityFieldsInAddressBook_returnsTrue() {
        addressBook.addTutor(ALICE);
        Tutor editedAlice = new TutorBuilder(ALICE).withTags(VALID_TAG_HUSBAND)
                .build();
        assertTrue(addressBook.hasTutor(editedAlice));
    }

    @Test
    public void getTutorList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> addressBook.getTutorList().remove(0));
    }

    @Test
    public void toStringMethod() {
        String expected = AddressBook.class.getCanonicalName() + "{tutors=" + addressBook.getTutorList() + "}";
        assertEquals(expected, addressBook.toString());
    }

    /**
     * A stub ReadOnlyAddressBook whose tutors list can violate interface constraints.
     */
    private static class AddressBookStub implements ReadOnlyAddressBook {
        private final ObservableList<Tutor> tutors = FXCollections.observableArrayList();

        AddressBookStub(Collection<Tutor> tutors) {
            this.tutors.setAll(tutors);
        }

        @Override
        public ObservableList<Tutor> getTutorList() {
            return tutors;
        }
    }

}
