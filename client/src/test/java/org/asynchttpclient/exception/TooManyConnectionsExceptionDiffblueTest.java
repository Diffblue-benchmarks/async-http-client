package org.asynchttpclient.exception;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import org.junit.jupiter.api.Test;

class TooManyConnectionsExceptionDiffblueTest {
  /**
   * Method under test:
   * {@link TooManyConnectionsException#TooManyConnectionsException(int)}
   */
  @Test
  void testNewTooManyConnectionsException() {
    // Arrange and Act
    TooManyConnectionsException actualTooManyConnectionsException = new TooManyConnectionsException(3);

    // Assert
    assertEquals("Too many connections: 3", actualTooManyConnectionsException.getLocalizedMessage());
    assertEquals("Too many connections: 3", actualTooManyConnectionsException.getMessage());
    assertNull(actualTooManyConnectionsException.getCause());
    assertEquals(0, actualTooManyConnectionsException.getSuppressed().length);
  }
}
