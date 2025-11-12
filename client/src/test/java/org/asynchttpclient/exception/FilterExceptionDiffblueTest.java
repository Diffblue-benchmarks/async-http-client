package org.asynchttpclient.exception;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

class FilterExceptionDiffblueTest {
  /**
   * Test {@link FilterException#FilterException(String)}.
   *
   * <ul>
   *   <li>When {@code https://example.org/example}.
   *   <li>Then return Cause is {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link FilterException#FilterException(String)}
   */
  @Test
  @DisplayName(
      "Test new FilterException(String); when 'https://example.org/example'; then return Cause is 'null'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void FilterException.<init>(String)",
    "void FilterException.<init>(String, Throwable)"
  })
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
   *
   * <ul>
   *   <li>When {@link ChannelClosedException#INSTANCE}.
   *   <li>Then return Cause is {@link ChannelClosedException#INSTANCE}.
   * </ul>
   *
   * <p>Method under test: {@link FilterException#FilterException(String, Throwable)}
   */
  @Test
  @DisplayName(
      "Test new FilterException(String, Throwable); when INSTANCE; then return Cause is INSTANCE")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void FilterException.<init>(String)",
    "void FilterException.<init>(String, Throwable)"
  })
  void testNewFilterException_whenInstance_thenReturnCauseIsInstance() {
    // Arrange and Act
    FilterException actualFilterException =
        new FilterException("https://example.org/example", ChannelClosedException.INSTANCE);

    // Assert
    assertEquals("https://example.org/example", actualFilterException.getMessage());
    assertEquals(0, actualFilterException.getSuppressed().length);
    assertSame(ChannelClosedException.INSTANCE, actualFilterException.getCause());
  }
}
