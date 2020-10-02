package org.example.services;

import org.junit.jupiter.api.Test;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class ParserTest {

    @Test
    public void should_return_key_value_pair_when_one_pair_passed() throws UnsupportedEncodingException {
        // Arrange
        String formData = "abc=cda";
        Map<String, String> expected = new HashMap<>();
        expected.put("abc", "cda");
        // Act
        Map<String, String> actual = Parser.parseFormData(formData);
        // Assert
        assertEquals(expected, actual);
    }

    @Test
    public void should_return_multiple_value_pair_when_multiple_pair_passed() throws UnsupportedEncodingException {
        // Arrange
        String formData = "abc=cda&making=applePie";
        Map<String, String> expected = new HashMap<>();
        expected.put("abc", "cda");
        expected.put("making", "applePie");
        // Act
        Map<String, String> actual = Parser.parseFormData(formData);
        // Assert
        assertEquals(expected, actual);
    }

    @Test
    public void should_throw_IllegalArgumentException_when_null_passed() {
        // Arrange
        String formData = null;
        // Act
        // Assert
        assertThrows(IllegalArgumentException.class, () -> Parser.parseFormData(formData));
    }
}