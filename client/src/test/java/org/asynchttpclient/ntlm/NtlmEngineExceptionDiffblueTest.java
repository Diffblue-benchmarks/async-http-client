package org.asynchttpclient.ntlm;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.asynchttpclient.exception.ChannelClosedException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

class NtlmEngineExceptionDiffblueTest {
  /**
   * Test {@link NtlmEngineException#NtlmEngineException(String)}.
   *
   * <ul>
   *   <li>When {@code https://example.org/example}.
   *   <li>Then return Cause is {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link NtlmEngineException#NtlmEngineException(String)}
   */
  @Test
  @DisplayName(
      "Test new NtlmEngineException(String); when 'https://example.org/example'; then return Cause is 'null'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void NtlmEngineException.<init>(String)",
    "void NtlmEngineException.<init>(String, Throwable)"
  })
  void testNewNtlmEngineException_whenHttpsExampleOrgExample_thenReturnCauseIsNull() {
    // Arrange and Act
    NtlmEngineException actualNtlmEngineException =
        new NtlmEngineException("https://example.org/example");

    // Assert
    assertEquals("https://example.org/example", actualNtlmEngineException.getMessage());
    assertNull(actualNtlmEngineException.getCause());
    assertEquals(0, actualNtlmEngineException.getSuppressed().length);
  }

  /**
   * Test {@link NtlmEngineException#NtlmEngineException(String, Throwable)}.
   *
   * <ul>
   *   <li>When {@link ChannelClosedException#INSTANCE}.
   *   <li>Then return Cause is {@link ChannelClosedException#INSTANCE}.
   * </ul>
   *
   * <p>Method under test: {@link NtlmEngineException#NtlmEngineException(String, Throwable)}
   */
  @Test
  @DisplayName(
      "Test new NtlmEngineException(String, Throwable); when INSTANCE; then return Cause is INSTANCE")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void NtlmEngineException.<init>(String)",
    "void NtlmEngineException.<init>(String, Throwable)"
  })
  void testNewNtlmEngineException_whenInstance_thenReturnCauseIsInstance() {
    // Arrange and Act
    NtlmEngineException actualNtlmEngineException =
        new NtlmEngineException("https://example.org/example", ChannelClosedException.INSTANCE);

    // Assert
    assertEquals("https://example.org/example", actualNtlmEngineException.getMessage());
    assertEquals(0, actualNtlmEngineException.getSuppressed().length);
    assertSame(ChannelClosedException.INSTANCE, actualNtlmEngineException.getCause());
  }
}
