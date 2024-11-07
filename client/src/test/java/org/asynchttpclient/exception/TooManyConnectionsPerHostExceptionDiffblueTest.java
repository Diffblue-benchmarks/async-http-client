package org.asynchttpclient.exception;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class TooManyConnectionsPerHostExceptionDiffblueTest {
  /**
   * Test
   * {@link TooManyConnectionsPerHostException#TooManyConnectionsPerHostException(int)}.
   * <p>
   * Method under test:
   * {@link TooManyConnectionsPerHostException#TooManyConnectionsPerHostException(int)}
   */
  @Test
  @DisplayName("Test new TooManyConnectionsPerHostException(int)")
  void testNewTooManyConnectionsPerHostException() {
    // Arrange and Act
    TooManyConnectionsPerHostException actualTooManyConnectionsPerHostException = new TooManyConnectionsPerHostException(
        3);

    // Assert
    assertEquals("Too many connections: 3", actualTooManyConnectionsPerHostException.getLocalizedMessage());
    assertEquals("Too many connections: 3", actualTooManyConnectionsPerHostException.getMessage());
    assertNull(actualTooManyConnectionsPerHostException.getCause());
    assertEquals(0, actualTooManyConnectionsPerHostException.getSuppressed().length);
  }
}
