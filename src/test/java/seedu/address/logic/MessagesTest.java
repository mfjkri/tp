package seedu.address.logic;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;


public class MessagesTest {

    @Test
    public void messagesInstance_creation_success() {
        Messages messages = new Messages();
        assertNotNull(messages);
    }
}
