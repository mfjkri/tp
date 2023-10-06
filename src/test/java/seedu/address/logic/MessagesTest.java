package seedu.address.logic;

import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;
import seedu.address.logic.parser.Prefix;


public class MessagesTest {

    @Test
    public void testErrorMessageForDuplicatePrefixes() {
        Prefix prefix1 = new Prefix("n/");
        Prefix[] duplicatePrefixes = { prefix1, prefix1 }; // Creating an array with duplicate prefixes

        // This assertion should throw an error if duplicatePrefixes.length <= 0
        assertThrows(AssertionError.class, () -> Messages.getErrorMessageForDuplicatePrefixes(duplicatePrefixes));
    }
}