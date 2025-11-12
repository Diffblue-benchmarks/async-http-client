package org.asynchttpclient.request.body.multipart;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.nio.charset.Charset;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

class StringPartDiffblueTest {
  /**
   * Test {@link StringPart#StringPart(String, String)}.
   *
   * <p>Method under test: {@link StringPart#StringPart(String, String)}
   */
  @Test
  @DisplayName("Test new StringPart(String, String)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void StringPart.<init>(String, String)"})
  void testNewStringPart() {
    // Arrange and Act
    StringPart actualStringPart =
        new StringPart("https://example.org/example", "https://example.org/example");

    // Assert
    assertEquals("UTF-8", actualStringPart.getCharset().name());
    assertEquals("https://example.org/example", actualStringPart.getName());
    assertEquals("https://example.org/example", actualStringPart.getValue());
    assertNull(actualStringPart.getContentId());
    assertNull(actualStringPart.getContentType());
    assertNull(actualStringPart.getDispositionType());
    assertNull(actualStringPart.getTransferEncoding());
    assertNull(actualStringPart.getCustomHeaders());
  }

  /**
   * Test {@link StringPart#StringPart(String, String, String)}.
   *
   * <p>Method under test: {@link StringPart#StringPart(String, String, String)}
   */
  @Test
  @DisplayName("Test new StringPart(String, String, String)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void StringPart.<init>(String, String, String)"})
  void testNewStringPart2() {
    // Arrange and Act
    StringPart actualStringPart =
        new StringPart(
            "https://example.org/example",
            "https://example.org/example",
            "https://example.org/example");

    // Assert
    assertEquals("UTF-8", actualStringPart.getCharset().name());
    assertEquals("https://example.org/example", actualStringPart.getContentType());
    assertEquals("https://example.org/example", actualStringPart.getName());
    assertEquals("https://example.org/example", actualStringPart.getValue());
    assertNull(actualStringPart.getContentId());
    assertNull(actualStringPart.getDispositionType());
    assertNull(actualStringPart.getTransferEncoding());
    assertNull(actualStringPart.getCustomHeaders());
  }

  /**
   * Test {@link StringPart#StringPart(String, String, String, Charset)}.
   *
   * <ul>
   *   <li>When forName {@code UTF-8}.
   *   <li>Then return Charset is forName {@code UTF-8}.
   * </ul>
   *
   * <p>Method under test: {@link StringPart#StringPart(String, String, String, Charset)}
   */
  @Test
  @DisplayName(
      "Test new StringPart(String, String, String, Charset); when forName 'UTF-8'; then return Charset is forName 'UTF-8'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void StringPart.<init>(String, String, String, Charset)"})
  void testNewStringPart_whenForNameUtf8_thenReturnCharsetIsForNameUtf8() {
    // Arrange
    Charset charset = Charset.forName("UTF-8");

    // Act
    StringPart actualStringPart =
        new StringPart(
            "https://example.org/example",
            "https://example.org/example",
            "https://example.org/example",
            charset);

    // Assert
    assertEquals("https://example.org/example", actualStringPart.getContentType());
    assertEquals("https://example.org/example", actualStringPart.getName());
    assertEquals("https://example.org/example", actualStringPart.getValue());
    assertNull(actualStringPart.getContentId());
    assertNull(actualStringPart.getDispositionType());
    assertNull(actualStringPart.getTransferEncoding());
    assertNull(actualStringPart.getCustomHeaders());
    assertSame(charset, actualStringPart.getCharset());
  }

  /**
   * Test {@link StringPart#StringPart(String, String, String, Charset, String)}.
   *
   * <ul>
   *   <li>When forName {@code UTF-8}.
   *   <li>Then return Charset is forName {@code UTF-8}.
   * </ul>
   *
   * <p>Method under test: {@link StringPart#StringPart(String, String, String, Charset, String)}
   */
  @Test
  @DisplayName(
      "Test new StringPart(String, String, String, Charset, String); when forName 'UTF-8'; then return Charset is forName 'UTF-8'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void StringPart.<init>(String, String, String, Charset, String)"})
  void testNewStringPart_whenForNameUtf8_thenReturnCharsetIsForNameUtf82() {
    // Arrange
    Charset charset = Charset.forName("UTF-8");

    // Act
    StringPart actualStringPart =
        new StringPart(
            "https://example.org/example",
            "https://example.org/example",
            "https://example.org/example",
            charset,
            "https://example.org/example");

    // Assert
    assertEquals("https://example.org/example", actualStringPart.getContentId());
    assertEquals("https://example.org/example", actualStringPart.getContentType());
    assertEquals("https://example.org/example", actualStringPart.getName());
    assertEquals("https://example.org/example", actualStringPart.getValue());
    assertNull(actualStringPart.getDispositionType());
    assertNull(actualStringPart.getTransferEncoding());
    assertNull(actualStringPart.getCustomHeaders());
    assertSame(charset, actualStringPart.getCharset());
  }

  /**
   * Test {@link StringPart#StringPart(String, String, String, Charset, String, String)}.
   *
   * <ul>
   *   <li>When forName {@code UTF-8}.
   *   <li>Then return Charset is forName {@code UTF-8}.
   * </ul>
   *
   * <p>Method under test: {@link StringPart#StringPart(String, String, String, Charset, String,
   * String)}
   */
  @Test
  @DisplayName(
      "Test new StringPart(String, String, String, Charset, String, String); when forName 'UTF-8'; then return Charset is forName 'UTF-8'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void StringPart.<init>(String, String, String, Charset, String, String)"})
  void testNewStringPart_whenForNameUtf8_thenReturnCharsetIsForNameUtf83() {
    // Arrange
    Charset charset = Charset.forName("UTF-8");

    // Act
    StringPart actualStringPart =
        new StringPart(
            "https://example.org/example",
            "https://example.org/example",
            "https://example.org/example",
            charset,
            "https://example.org/example",
            "https://example.org/example");

    // Assert
    assertEquals("https://example.org/example", actualStringPart.getContentId());
    assertEquals("https://example.org/example", actualStringPart.getContentType());
    assertEquals("https://example.org/example", actualStringPart.getName());
    assertEquals("https://example.org/example", actualStringPart.getTransferEncoding());
    assertEquals("https://example.org/example", actualStringPart.getValue());
    assertNull(actualStringPart.getDispositionType());
    assertNull(actualStringPart.getCustomHeaders());
    assertSame(charset, actualStringPart.getCharset());
  }

  /**
   * Test {@link StringPart#StringPart(String, String, String, Charset)}.
   *
   * <ul>
   *   <li>When {@code null}.
   *   <li>Then return Charset name is {@code UTF-8}.
   * </ul>
   *
   * <p>Method under test: {@link StringPart#StringPart(String, String, String, Charset)}
   */
  @Test
  @DisplayName(
      "Test new StringPart(String, String, String, Charset); when 'null'; then return Charset name is 'UTF-8'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void StringPart.<init>(String, String, String, Charset)"})
  void testNewStringPart_whenNull_thenReturnCharsetNameIsUtf8() {
    // Arrange and Act
    StringPart actualStringPart =
        new StringPart(
            "https://example.org/example",
            "https://example.org/example",
            "https://example.org/example",
            null);

    // Assert
    assertEquals("UTF-8", actualStringPart.getCharset().name());
    assertEquals("https://example.org/example", actualStringPart.getContentType());
    assertEquals("https://example.org/example", actualStringPart.getName());
    assertEquals("https://example.org/example", actualStringPart.getValue());
    assertNull(actualStringPart.getContentId());
    assertNull(actualStringPart.getDispositionType());
    assertNull(actualStringPart.getTransferEncoding());
    assertNull(actualStringPart.getCustomHeaders());
  }

  /**
   * Test {@link StringPart#StringPart(String, String, String, Charset, String)}.
   *
   * <ul>
   *   <li>When {@code null}.
   *   <li>Then return Charset name is {@code UTF-8}.
   * </ul>
   *
   * <p>Method under test: {@link StringPart#StringPart(String, String, String, Charset, String)}
   */
  @Test
  @DisplayName(
      "Test new StringPart(String, String, String, Charset, String); when 'null'; then return Charset name is 'UTF-8'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void StringPart.<init>(String, String, String, Charset, String)"})
  void testNewStringPart_whenNull_thenReturnCharsetNameIsUtf82() {
    // Arrange and Act
    StringPart actualStringPart =
        new StringPart(
            "https://example.org/example",
            "https://example.org/example",
            "https://example.org/example",
            null,
            "https://example.org/example");

    // Assert
    assertEquals("UTF-8", actualStringPart.getCharset().name());
    assertEquals("https://example.org/example", actualStringPart.getContentId());
    assertEquals("https://example.org/example", actualStringPart.getContentType());
    assertEquals("https://example.org/example", actualStringPart.getName());
    assertEquals("https://example.org/example", actualStringPart.getValue());
    assertNull(actualStringPart.getDispositionType());
    assertNull(actualStringPart.getTransferEncoding());
    assertNull(actualStringPart.getCustomHeaders());
  }

  /**
   * Test {@link StringPart#StringPart(String, String, String, Charset, String, String)}.
   *
   * <ul>
   *   <li>When {@code null}.
   *   <li>Then return Charset name is {@code UTF-8}.
   * </ul>
   *
   * <p>Method under test: {@link StringPart#StringPart(String, String, String, Charset, String,
   * String)}
   */
  @Test
  @DisplayName(
      "Test new StringPart(String, String, String, Charset, String, String); when 'null'; then return Charset name is 'UTF-8'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void StringPart.<init>(String, String, String, Charset, String, String)"})
  void testNewStringPart_whenNull_thenReturnCharsetNameIsUtf83() {
    // Arrange and Act
    StringPart actualStringPart =
        new StringPart(
            "https://example.org/example",
            "https://example.org/example",
            "https://example.org/example",
            null,
            "https://example.org/example",
            "https://example.org/example");

    // Assert
    assertEquals("UTF-8", actualStringPart.getCharset().name());
    assertEquals("https://example.org/example", actualStringPart.getContentId());
    assertEquals("https://example.org/example", actualStringPart.getContentType());
    assertEquals("https://example.org/example", actualStringPart.getName());
    assertEquals("https://example.org/example", actualStringPart.getTransferEncoding());
    assertEquals("https://example.org/example", actualStringPart.getValue());
    assertNull(actualStringPart.getDispositionType());
    assertNull(actualStringPart.getCustomHeaders());
  }

  /**
   * Test {@link StringPart#getValue()}.
   *
   * <p>Method under test: {@link StringPart#getValue()}
   */
  @Test
  @DisplayName("Test getValue()")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"String StringPart.getValue()"})
  void testGetValue() {
    // Arrange, Act and Assert
    assertEquals(
        "https://example.org/example",
        new StringPart("https://example.org/example", "https://example.org/example").getValue());
  }
}
