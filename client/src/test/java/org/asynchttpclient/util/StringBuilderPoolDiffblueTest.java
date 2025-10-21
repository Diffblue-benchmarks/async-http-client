package org.asynchttpclient.util;

import static org.junit.jupiter.api.Assertions.assertEquals;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

class StringBuilderPoolDiffblueTest {
  /**
   * Test {@link StringBuilderPool#stringBuilder()}.
   * <p>
   * Method under test: {@link StringBuilderPool#stringBuilder()}
   */
  @Test
  @DisplayName("Test stringBuilder()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"java.lang.StringBuilder StringBuilderPool.stringBuilder()"})
  void testStringBuilder() {
    // Arrange, Act and Assert
    assertEquals("", StringBuilderPool.DEFAULT.stringBuilder().toString());
  }

  /**
   * Test new {@link StringBuilderPool} (default constructor).
   * <p>
   * Method under test: default or parameterless constructor of {@link StringBuilderPool}
   */
  @Test
  @DisplayName("Test new StringBuilderPool (default constructor)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void StringBuilderPool.<init>()"})
  void testNewStringBuilderPool() {
    // Arrange, Act and Assert
    assertEquals("", (new StringBuilderPool()).stringBuilder().toString());
  }
}
