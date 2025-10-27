package org.asynchttpclient.exception;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import org.junit.jupiter.api.Test;

class FilterExceptionDiffblueTest {
  /**
   * Method under test: {@link FilterException#FilterException(String)}
   */
  @Test
  void testNewFilterException() {
    // Arrange and Act
    FilterException actualFilterException = new FilterException("https://example.org/example");

    // Assert
    assertEquals("https://example.org/example", actualFilterException.getMessage());
    assertNull(actualFilterException.getCause());
    assertEquals(0, actualFilterException.getSuppressed().length);
  }

  /**
   * Method under test: {@link FilterException#FilterException(String, Throwable)}
   */
  @Test
  void testNewFilterException2() {
    // Arrange
    ChannelClosedException cause = ChannelClosedException.INSTANCE;

    // Act
    FilterException actualFilterException = new FilterException("https://example.org/example", cause);

    // Assert
    assertEquals("https://example.org/example", actualFilterException.getMessage());
    assertEquals(0, actualFilterException.getSuppressed().length);
    assertSame(cause.INSTANCE, actualFilterException.getCause());
  }
}
