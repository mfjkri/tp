package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalTutors.getTypicalAddressBook;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.logic.Messages;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.tutor.Tutor;
import seedu.address.testutil.TutorBuilder;

/**
 * Contains integration tests (interaction with the Model) for {@code AddCommand}.
 */
public class AddCommandIntegrationTest {

    private Model model;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    }

    @Test
    public void execute_newTutor_success() {
        Tutor validTutor = new TutorBuilder().build();

        Model expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.addTutor(validTutor);

        assertCommandSuccess(new AddCommand(validTutor), model,
                String.format(AddCommand.MESSAGE_SUCCESS, Messages.format(validTutor)),
                expectedModel);
    }

    @Test
    public void execute_duplicateTutor_throwsCommandException() {
        Tutor tutorInList = model.getAddressBook().getTutorList().get(0);
        assertCommandFailure(new AddCommand(tutorInList), model,
                AddCommand.MESSAGE_DUPLICATE_TUTOR);
    }

}
