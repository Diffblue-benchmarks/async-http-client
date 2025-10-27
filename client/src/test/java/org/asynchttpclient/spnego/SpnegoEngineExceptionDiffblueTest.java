package org.asynchttpclient.spnego;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import org.asynchttpclient.exception.ChannelClosedException;
import org.junit.jupiter.api.Test;

class SpnegoEngineExceptionDiffblueTest {
  /**
   * Method under test:
   * {@link SpnegoEngineException#SpnegoEngineException(String)}
   */
  @Test
  void testNewSpnegoEngineException() {
    // Arrange and Act
    SpnegoEngineException actualSpnegoEngineException = new SpnegoEngineException("https://example.org/example");

    // Assert
    assertEquals("https://example.org/example", actualSpnegoEngineException.getMessage());
    assertNull(actualSpnegoEngineException.getCause());
    assertEquals(0, actualSpnegoEngineException.getSuppressed().length);
  }

  /**
   * Method under test:
   * {@link SpnegoEngineException#SpnegoEngineException(String, Throwable)}
   */
  @Test
  void testNewSpnegoEngineException2() {
    // Arrange
    ChannelClosedException cause = ChannelClosedException.INSTANCE;

    // Act
    SpnegoEngineException actualSpnegoEngineException = new SpnegoEngineException("https://example.org/example", cause);

    // Assert
    assertEquals("https://example.org/example", actualSpnegoEngineException.getMessage());
    assertEquals(0, actualSpnegoEngineException.getSuppressed().length);
    assertSame(cause.INSTANCE, actualSpnegoEngineException.getCause());
  }
}
