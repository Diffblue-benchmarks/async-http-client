package org.asynchttpclient.ntlm;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import org.asynchttpclient.exception.ChannelClosedException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class NtlmEngineExceptionDiffblueTest {
  /**
   * Test {@link NtlmEngineException#NtlmEngineException(String)}.
   * <ul>
   *   <li>When {@code https://example.org/example}.</li>
   *   <li>Then return Cause is {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link NtlmEngineException#NtlmEngineException(String)}
   */
  @Test
  @DisplayName("Test new NtlmEngineException(String); when 'https://example.org/example'; then return Cause is 'null'")
  void testNewNtlmEngineException_whenHttpsExampleOrgExample_thenReturnCauseIsNull() {
    // Arrange and Act
    NtlmEngineException actualNtlmEngineException = new NtlmEngineException("https://example.org/example");

    // Assert
    assertEquals("https://example.org/example", actualNtlmEngineException.getMessage());
    assertNull(actualNtlmEngineException.getCause());
    assertEquals(0, actualNtlmEngineException.getSuppressed().length);
  }

  /**
   * Test {@link NtlmEngineException#NtlmEngineException(String, Throwable)}.
   * <ul>
   *   <li>When {@link ChannelClosedException#INSTANCE}.</li>
   *   <li>Then return Cause is {@link ChannelClosedException#INSTANCE}
   * {@link ChannelClosedException#INSTANCE}.</li>
   * </ul>
   * <p>
   * Method under test:
   * {@link NtlmEngineException#NtlmEngineException(String, Throwable)}
   */
  @Test
  @DisplayName("Test new NtlmEngineException(String, Throwable); when INSTANCE; then return Cause is INSTANCE INSTANCE")
  void testNewNtlmEngineException_whenInstance_thenReturnCauseIsInstanceInstance() {
    // Arrange
    ChannelClosedException cause = ChannelClosedException.INSTANCE;

    // Act
    NtlmEngineException actualNtlmEngineException = new NtlmEngineException("https://example.org/example", cause);

    // Assert
    assertEquals("https://example.org/example", actualNtlmEngineException.getMessage());
    assertEquals(0, actualNtlmEngineException.getSuppressed().length);
    assertSame(cause.INSTANCE, actualNtlmEngineException.getCause());
  }
}
