package dev.udayshetty.aiplatform.api;

import jakarta.validation.Validation;
import jakarta.validation.Validator;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ChatRequestTest {

    private final Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

    @Test
    void acceptsNonBlankMessage() {
        assertTrue(validator.validate(new ChatRequest("Explain dependency injection")).isEmpty());
    }

    @Test
    void rejectsBlankMessage() {
        assertFalse(validator.validate(new ChatRequest("  ")).isEmpty());
    }

    @Test
    void rejectsMessageLongerThanLimit() {
        assertFalse(validator.validate(new ChatRequest("x".repeat(12001))).isEmpty());
    }
}