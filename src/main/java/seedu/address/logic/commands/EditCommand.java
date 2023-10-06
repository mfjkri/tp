package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_TUTORS;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.CollectionUtil;
import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.tag.Tag;
import seedu.address.model.tutor.Email;
import seedu.address.model.tutor.Name;
import seedu.address.model.tutor.Phone;
import seedu.address.model.tutor.Tutor;

/**
 * Edits the details of an existing tutor in the address book.
 */
public class EditCommand extends Command {

    public static final String COMMAND_WORD = "edit";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits the details of the tutor identified "
            + "by the index number used in the displayed tutor list. "
            + "Existing values will be overwritten by the input values.\n"
            + "Parameters: INDEX (must be a positive integer) "
            + "[" + PREFIX_NAME + "NAME] "
            + "[" + PREFIX_PHONE + "PHONE] "
            + "[" + PREFIX_EMAIL + "EMAIL] "
            + "[" + PREFIX_TAG + "TAG]...\n"
            + "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_PHONE + "91234567 "
            + PREFIX_EMAIL + "johndoe@example.com";

    public static final String MESSAGE_EDIT_TUTOR_SUCCESS = "Edited Tutor: %1$s";
    public static final String MESSAGE_NOT_EDITED = "At least one field to edit must be provided.";
    public static final String MESSAGE_DUPLICATE_TUTOR = "This tutor already exists in the address book.";

    private final Index index;
    private final EditTutorDescriptor editTutorDescriptor;

    /**
     * @param index of the tutor in the filtered tutor list to edit
     * @param editTutorDescriptor details to edit the tutor with
     */
    public EditCommand(Index index, EditTutorDescriptor editTutorDescriptor) {
        requireNonNull(index);
        requireNonNull(editTutorDescriptor);

        this.index = index;
        this.editTutorDescriptor = new EditTutorDescriptor(editTutorDescriptor);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Tutor> lastShownList = model.getFilteredTutorList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_TUTOR_DISPLAYED_INDEX);
        }

        Tutor tutorToEdit = lastShownList.get(index.getZeroBased());
        Tutor editedTutor = createEditedTutor(tutorToEdit, editTutorDescriptor);

        if (!tutorToEdit.isSameTutor(editedTutor) && model.hasTutor(editedTutor)) {
            throw new CommandException(MESSAGE_DUPLICATE_TUTOR);
        }

        model.setTutor(tutorToEdit, editedTutor);
        model.updateFilteredTutorList(PREDICATE_SHOW_ALL_TUTORS);
        return new CommandResult(String.format(MESSAGE_EDIT_TUTOR_SUCCESS, Messages.format(editedTutor)));
    }

    /**
     * Creates and returns a {@code Tutor} with the details of {@code tutorToEdit}
     * edited with {@code editTutorDescriptor}.
     */
    private static Tutor createEditedTutor(Tutor tutorToEdit, EditTutorDescriptor editTutorDescriptor) {
        assert tutorToEdit != null;

        Name updatedName = editTutorDescriptor.getName().orElse(tutorToEdit.getName());
        Phone updatedPhone = editTutorDescriptor.getPhone().orElse(tutorToEdit.getPhone());
        Email updatedEmail = editTutorDescriptor.getEmail().orElse(tutorToEdit.getEmail());
        Set<Tag> updatedTags = editTutorDescriptor.getTags().orElse(tutorToEdit.getTags());

        return new Tutor(updatedName, updatedPhone, updatedEmail, updatedTags);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof EditCommand)) {
            return false;
        }

        EditCommand otherEditCommand = (EditCommand) other;
        return index.equals(otherEditCommand.index)
                && editTutorDescriptor.equals(otherEditCommand.editTutorDescriptor);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("index", index)
                .add("editTutorDescriptor", editTutorDescriptor)
                .toString();
    }

    /**
     * Stores the details to edit the tutor with. Each non-empty field value will replace the
     * corresponding field value of the tutor.
     */
    public static class EditTutorDescriptor {
        private Name name;
        private Phone phone;
        private Email email;
        private Set<Tag> tags;

        public EditTutorDescriptor() {}

        /**
         * Copy constructor.
         * A defensive copy of {@code tags} is used internally.
         */
        public EditTutorDescriptor(EditTutorDescriptor toCopy) {
            setName(toCopy.name);
            setPhone(toCopy.phone);
            setEmail(toCopy.email);
            setTags(toCopy.tags);
        }

        /**
         * Returns true if at least one field is edited.
         */
        public boolean isAnyFieldEdited() {
            return CollectionUtil.isAnyNonNull(name, phone, email, tags);
        }

        public void setName(Name name) {
            this.name = name;
        }

        public Optional<Name> getName() {
            return Optional.ofNullable(name);
        }

        public void setPhone(Phone phone) {
            this.phone = phone;
        }

        public Optional<Phone> getPhone() {
            return Optional.ofNullable(phone);
        }

        public void setEmail(Email email) {
            this.email = email;
        }

        public Optional<Email> getEmail() {
            return Optional.ofNullable(email);
        }

        /**
         * Sets {@code tags} to this object's {@code tags}.
         * A defensive copy of {@code tags} is used internally.
         */
        public void setTags(Set<Tag> tags) {
            this.tags = (tags != null) ? new HashSet<>(tags) : null;
        }

        /**
         * Returns an unmodifiable tag set, which throws {@code UnsupportedOperationException}
         * if modification is attempted.
         * Returns {@code Optional#empty()} if {@code tags} is null.
         */
        public Optional<Set<Tag>> getTags() {
            return (tags != null) ? Optional.of(Collections.unmodifiableSet(tags)) : Optional.empty();
        }

        @Override
        public boolean equals(Object other) {
            if (other == this) {
                return true;
            }

            // instanceof handles nulls
            if (!(other instanceof EditTutorDescriptor)) {
                return false;
            }

            EditTutorDescriptor otherEditTutorDescriptor = (EditTutorDescriptor) other;
            return Objects.equals(name, otherEditTutorDescriptor.name)
                    && Objects.equals(phone, otherEditTutorDescriptor.phone)
                    && Objects.equals(email, otherEditTutorDescriptor.email)
                    && Objects.equals(tags, otherEditTutorDescriptor.tags);
        }

        @Override
        public String toString() {
            return new ToStringBuilder(this)
                    .add("name", name)
                    .add("phone", phone)
                    .add("email", email)
                    .add("tags", tags)
                    .toString();
        }
    }
}
