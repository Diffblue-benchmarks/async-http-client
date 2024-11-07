package org.asynchttpclient.spnego;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import org.asynchttpclient.exception.ChannelClosedException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class SpnegoEngineExceptionDiffblueTest {
  /**
   * Test {@link SpnegoEngineException#SpnegoEngineException(String)}.
   * <ul>
   *   <li>When {@code https://example.org/example}.</li>
   *   <li>Then return Cause is {@code null}.</li>
   * </ul>
   * <p>
   * Method under test:
   * {@link SpnegoEngineException#SpnegoEngineException(String)}
   */
  @Test
  @DisplayName("Test new SpnegoEngineException(String); when 'https://example.org/example'; then return Cause is 'null'")
  void testNewSpnegoEngineException_whenHttpsExampleOrgExample_thenReturnCauseIsNull() {
    // Arrange and Act
    SpnegoEngineException actualSpnegoEngineException = new SpnegoEngineException("https://example.org/example");

    // Assert
    assertEquals("https://example.org/example", actualSpnegoEngineException.getMessage());
    assertNull(actualSpnegoEngineException.getCause());
    assertEquals(0, actualSpnegoEngineException.getSuppressed().length);
  }

  /**
   * Test {@link SpnegoEngineException#SpnegoEngineException(String, Throwable)}.
   * <ul>
   *   <li>When {@link ChannelClosedException#INSTANCE}.</li>
   *   <li>Then return Cause is {@link ChannelClosedException#INSTANCE}
   * {@link ChannelClosedException#INSTANCE}.</li>
   * </ul>
   * <p>
   * Method under test:
   * {@link SpnegoEngineException#SpnegoEngineException(String, Throwable)}
   */
  @Test
  @DisplayName("Test new SpnegoEngineException(String, Throwable); when INSTANCE; then return Cause is INSTANCE INSTANCE")
  void testNewSpnegoEngineException_whenInstance_thenReturnCauseIsInstanceInstance() {
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
