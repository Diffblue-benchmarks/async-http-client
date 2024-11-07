package org.asynchttpclient.handler;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class MaxRedirectExceptionDiffblueTest {
  /**
   * Test {@link MaxRedirectException#MaxRedirectException(String)}.
   * <p>
   * Method under test: {@link MaxRedirectException#MaxRedirectException(String)}
   */
  @Test
  @DisplayName("Test new MaxRedirectException(String)")
  void testNewMaxRedirectException() {
    // Arrange and Act
    MaxRedirectException actualMaxRedirectException = new MaxRedirectException("https://example.org/example");

    // Assert
    assertEquals("https://example.org/example", actualMaxRedirectException.getMessage());
    assertNull(actualMaxRedirectException.getCause());
    assertEquals(0, actualMaxRedirectException.getSuppressed().length);
  }
}
