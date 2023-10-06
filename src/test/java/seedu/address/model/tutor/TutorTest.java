package seedu.address.model.tutor;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalTutors.ALICE;
import static seedu.address.testutil.TypicalTutors.BOB;

import java.util.HashSet;
import java.util.Objects;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.TutorBuilder;

public class TutorTest {

    @Test
    public void asObservableList_modifyList_throwsUnsupportedOperationException() {
        Tutor tutor = new TutorBuilder().build();
        assertThrows(UnsupportedOperationException.class, () -> tutor.getTags().remove(0));
    }

    @Test
    public void isSameTutor() {
        // same object -> returns true
        assertTrue(ALICE.isSameTutor(ALICE));

        // null -> returns false
        assertFalse(ALICE.isSameTutor(null));

        // same name, all other attributes different -> returns true
        Tutor editedAlice = new TutorBuilder(ALICE).withPhone(VALID_PHONE_BOB).withEmail(VALID_EMAIL_BOB)
                .withTags(VALID_TAG_HUSBAND).build();
        assertTrue(ALICE.isSameTutor(editedAlice));

        // different name, all other attributes same -> returns false
        editedAlice = new TutorBuilder(ALICE).withName(VALID_NAME_BOB).build();
        assertFalse(ALICE.isSameTutor(editedAlice));

        // name differs in case, all other attributes same -> returns false
        Tutor editedBob = new TutorBuilder(BOB).withName(VALID_NAME_BOB.toLowerCase()).build();
        assertFalse(BOB.isSameTutor(editedBob));

        // name has trailing spaces, all other attributes same -> returns false
        String nameWithTrailingSpaces = VALID_NAME_BOB + " ";
        editedBob = new TutorBuilder(BOB).withName(nameWithTrailingSpaces).build();
        assertFalse(BOB.isSameTutor(editedBob));
    }

    @Test
    public void equals() {
        // same values -> returns true
        Tutor aliceCopy = new TutorBuilder(ALICE).build();
        assertTrue(ALICE.equals(aliceCopy));

        // same object -> returns true
        assertTrue(ALICE.equals(ALICE));

        // null -> returns false
        assertFalse(ALICE.equals(null));

        // different type -> returns false
        assertFalse(ALICE.equals(5));

        // different tutor -> returns false
        assertFalse(ALICE.equals(BOB));

        // different name -> returns false
        Tutor editedAlice = new TutorBuilder(ALICE).withName(VALID_NAME_BOB).build();
        assertFalse(ALICE.equals(editedAlice));

        // different phone -> returns false
        editedAlice = new TutorBuilder(ALICE).withPhone(VALID_PHONE_BOB).build();
        assertFalse(ALICE.equals(editedAlice));

        // different email -> returns false
        editedAlice = new TutorBuilder(ALICE).withEmail(VALID_EMAIL_BOB).build();
        assertFalse(ALICE.equals(editedAlice));

        // different tags -> returns false
        editedAlice = new TutorBuilder(ALICE).withTags(VALID_TAG_HUSBAND).build();
        assertFalse(ALICE.equals(editedAlice));
    }

    @Test
    public void toStringMethod() {
        String expected = Tutor.class.getCanonicalName() + "{name=" + ALICE.getName() + ", phone=" + ALICE.getPhone()
                + ", email=" + ALICE.getEmail() + ", tags=" + ALICE.getTags() + "}";
        assertEquals(expected, ALICE.toString());
    }

    @Test
    public void hashCode_validTutor_returnsHashCode() {
        Tutor tutor = new Tutor(
            new Name("John Doe"),
            new Phone("12345678"),
            new Email("johndoe@example.com"),
            new HashSet<>()
        );

        int expectedHashCode = Objects.hash(tutor.getName(),
            tutor.getPhone(),
            tutor.getEmail(),
            tutor.getTags());

        assertEquals(expectedHashCode, tutor.hashCode());
    }
}
