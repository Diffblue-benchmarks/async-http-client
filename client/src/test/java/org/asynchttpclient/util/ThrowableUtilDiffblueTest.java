package org.asynchttpclient.util;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.asynchttpclient.exception.ChannelClosedException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

class ThrowableUtilDiffblueTest {
  /**
   * Test {@link ThrowableUtil#unknownStackTrace(Throwable, Class, String)}.
   *
   * <ul>
   *   <li>When {@link ChannelClosedException#INSTANCE}.
   *   <li>Then return {@link ChannelClosedException}.
   * </ul>
   *
   * <p>Method under test: {@link ThrowableUtil#unknownStackTrace(Throwable, Class, String)}
   */
  @Test
  @DisplayName(
      "Test unknownStackTrace(Throwable, Class, String); when INSTANCE; then return ChannelClosedException")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"Throwable ThrowableUtil.unknownStackTrace(Throwable, Class, String)"})
  void testUnknownStackTrace_whenInstance_thenReturnChannelClosedException() {
    // Arrange
    Class<Object> clazz = Object.class;

    // Act
    Throwable actualUnknownStackTraceResult =
        ThrowableUtil.unknownStackTrace(
            ChannelClosedException.INSTANCE, clazz, "https://example.org/example");

    // Assert
    assertTrue(actualUnknownStackTraceResult instanceof ChannelClosedException);
    assertEquals("Channel closed", actualUnknownStackTraceResult.getLocalizedMessage());
    assertEquals("Channel closed", actualUnknownStackTraceResult.getMessage());
    assertNull(actualUnknownStackTraceResult.getCause());
    assertEquals(0, actualUnknownStackTraceResult.getSuppressed().length);
  }
}
