package seedu.address.logic;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;


public class MessagesTest {

    @Test
    public void messagesInstance_creation_success() {
        Messages messages = new Messages();
        assertNotNull(messages);
    }

    @Test
    public void testErrorMessageForDuplicatePrefixes() {
        assertThrows(AssertionError.class, Messages::getErrorMessageForDuplicatePrefixes);
    }
}
