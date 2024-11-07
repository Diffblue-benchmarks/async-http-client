package org.asynchttpclient.util;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class StringBuilderPoolDiffblueTest {
  /**
   * Test {@link StringBuilderPool#stringBuilder()}.
   * <p>
   * Method under test: {@link StringBuilderPool#stringBuilder()}
   */
  @Test
  @DisplayName("Test stringBuilder()")
  void testStringBuilder() {
    // Arrange, Act and Assert
    assertEquals("", StringBuilderPool.DEFAULT.stringBuilder().toString());
  }

  /**
   * Test new {@link StringBuilderPool} (default constructor).
   * <p>
   * Method under test: default or parameterless constructor of
   * {@link StringBuilderPool}
   */
  @Test
  @DisplayName("Test new StringBuilderPool (default constructor)")
  void testNewStringBuilderPool() {
    // Arrange, Act and Assert
    assertEquals("", (new StringBuilderPool()).stringBuilder().toString());
  }
}
