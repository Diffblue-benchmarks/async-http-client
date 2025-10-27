package org.asynchttpclient.util;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.Test;

class AssertionsDiffblueTest {
  /**
   * Method under test: {@link Assertions#assertNotEmpty(String, String)}
   */
  @Test
  void testAssertNotEmpty() {
    // Arrange, Act and Assert
    assertEquals("https://example.org/example",
        Assertions.assertNotEmpty("https://example.org/example", "https://example.org/example"));
    assertThrows(IllegalArgumentException.class, () -> Assertions.assertNotEmpty("", "https://example.org/example"));
  }
}
