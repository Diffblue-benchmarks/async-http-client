package org.asynchttpclient.util;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.asynchttpclient.exception.ChannelClosedException;
import org.junit.jupiter.api.Test;

class ThrowableUtilDiffblueTest {
  /**
   * Method under test:
   * {@link ThrowableUtil#unknownStackTrace(Throwable, Class, String)}
   */
  @Test
  void testUnknownStackTrace() {
    // Arrange
    Class<Object> clazz = Object.class;

    // Act
    Throwable actualUnknownStackTraceResult = ThrowableUtil.unknownStackTrace(ChannelClosedException.INSTANCE, clazz,
        "https://example.org/example");

    // Assert
    assertTrue(actualUnknownStackTraceResult instanceof ChannelClosedException);
    assertEquals("Channel closed", actualUnknownStackTraceResult.getLocalizedMessage());
    assertEquals("Channel closed", actualUnknownStackTraceResult.getMessage());
    assertNull(actualUnknownStackTraceResult.getCause());
    assertEquals(0, actualUnknownStackTraceResult.getSuppressed().length);
  }
}
