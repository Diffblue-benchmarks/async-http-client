package org.asynchttpclient.ntlm;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import org.asynchttpclient.exception.ChannelClosedException;
import org.junit.jupiter.api.Test;

class NtlmEngineExceptionDiffblueTest {
  /**
   * Method under test: {@link NtlmEngineException#NtlmEngineException(String)}
   */
  @Test
  void testNewNtlmEngineException() {
    // Arrange and Act
    NtlmEngineException actualNtlmEngineException = new NtlmEngineException("https://example.org/example");

    // Assert
    assertEquals("https://example.org/example", actualNtlmEngineException.getMessage());
    assertNull(actualNtlmEngineException.getCause());
    assertEquals(0, actualNtlmEngineException.getSuppressed().length);
  }

  /**
   * Method under test:
   * {@link NtlmEngineException#NtlmEngineException(String, Throwable)}
   */
  @Test
  void testNewNtlmEngineException2() {
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
