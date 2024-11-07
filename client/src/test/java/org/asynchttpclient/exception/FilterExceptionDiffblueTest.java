package org.asynchttpclient.exception;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class FilterExceptionDiffblueTest {
  /**
   * Test {@link FilterException#FilterException(String)}.
   * <ul>
   *   <li>When {@code https://example.org/example}.</li>
   *   <li>Then return Cause is {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link FilterException#FilterException(String)}
   */
  @Test
  @DisplayName("Test new FilterException(String); when 'https://example.org/example'; then return Cause is 'null'")
  void testNewFilterException_whenHttpsExampleOrgExample_thenReturnCauseIsNull() {
    // Arrange and Act
    FilterException actualFilterException = new FilterException("https://example.org/example");

    // Assert
    assertEquals("https://example.org/example", actualFilterException.getMessage());
    assertNull(actualFilterException.getCause());
    assertEquals(0, actualFilterException.getSuppressed().length);
  }

  /**
   * Test {@link FilterException#FilterException(String, Throwable)}.
   * <ul>
   *   <li>When {@link ChannelClosedException#INSTANCE}.</li>
   *   <li>Then return Cause is {@link ChannelClosedException#INSTANCE}
   * {@link ChannelClosedException#INSTANCE}.</li>
   * </ul>
   * <p>
   * Method under test: {@link FilterException#FilterException(String, Throwable)}
   */
  @Test
  @DisplayName("Test new FilterException(String, Throwable); when INSTANCE; then return Cause is INSTANCE INSTANCE")
  void testNewFilterException_whenInstance_thenReturnCauseIsInstanceInstance() {
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
