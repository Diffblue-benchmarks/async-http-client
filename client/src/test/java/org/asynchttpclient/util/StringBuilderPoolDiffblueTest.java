package org.asynchttpclient.util;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

class StringBuilderPoolDiffblueTest {
  /**
   * Method under test: default or parameterless constructor of
   * {@link StringBuilderPool}
   */
  @Test
  void testNewStringBuilderPool() {
    // Arrange, Act and Assert
    assertEquals("", (new StringBuilderPool()).stringBuilder().toString());
  }

  /**
   * Method under test: {@link StringBuilderPool#stringBuilder()}
   */
  @Test
  void testStringBuilder() {
    // Arrange, Act and Assert
    assertEquals("", StringBuilderPool.DEFAULT.stringBuilder().toString());
  }
}
