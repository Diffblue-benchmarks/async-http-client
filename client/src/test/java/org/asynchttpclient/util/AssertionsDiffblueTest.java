package org.asynchttpclient.util;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

class AssertionsDiffblueTest {
  /**
   * Test {@link Assertions#assertNotEmpty(String, String)}.
   *
   * <ul>
   *   <li>When empty string.
   *   <li>Then throw {@link IllegalArgumentException}.
   * </ul>
   *
   * <p>Method under test: {@link Assertions#assertNotEmpty(String, String)}
   */
  @Test
  @DisplayName(
      "Test assertNotEmpty(String, String); when empty string; then throw IllegalArgumentException")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"String Assertions.assertNotEmpty(String, String)"})
  void testAssertNotEmpty_whenEmptyString_thenThrowIllegalArgumentException() {
    // Arrange, Act and Assert
    assertThrows(
        IllegalArgumentException.class,
        () -> Assertions.assertNotEmpty("", "https://example.org/example"));
  }

  /**
   * Test {@link Assertions#assertNotEmpty(String, String)}.
   *
   * <ul>
   *   <li>When {@code https://example.org/example}.
   *   <li>Then return {@code https://example.org/example}.
   * </ul>
   *
   * <p>Method under test: {@link Assertions#assertNotEmpty(String, String)}
   */
  @Test
  @DisplayName(
      "Test assertNotEmpty(String, String); when 'https://example.org/example'; then return 'https://example.org/example'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"String Assertions.assertNotEmpty(String, String)"})
  void testAssertNotEmpty_whenHttpsExampleOrgExample_thenReturnHttpsExampleOrgExample() {
    // Arrange, Act and Assert
    assertEquals(
        "https://example.org/example",
        Assertions.assertNotEmpty("https://example.org/example", "https://example.org/example"));
  }
}
