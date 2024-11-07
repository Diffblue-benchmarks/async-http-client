package org.asynchttpclient.request.body.multipart;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import java.nio.charset.Charset;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class StringPartDiffblueTest {
  /**
   * Test {@link StringPart#StringPart(String, String)}.
   * <p>
   * Method under test: {@link StringPart#StringPart(String, String)}
   */
  @Test
  @DisplayName("Test new StringPart(String, String)")
  void testNewStringPart() {
    // Arrange and Act
    StringPart actualStringPart = new StringPart("https://example.org/example", "https://example.org/example");

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
   * <p>
   * Method under test: {@link StringPart#StringPart(String, String, String)}
   */
  @Test
  @DisplayName("Test new StringPart(String, String, String)")
  void testNewStringPart2() {
    // Arrange and Act
    StringPart actualStringPart = new StringPart("https://example.org/example", "https://example.org/example",
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
   * <ul>
   *   <li>When {@code null}.</li>
   *   <li>Then return Charset name is {@code UTF-8}.</li>
   * </ul>
   * <p>
   * Method under test:
   * {@link StringPart#StringPart(String, String, String, Charset)}
   */
  @Test
  @DisplayName("Test new StringPart(String, String, String, Charset); when 'null'; then return Charset name is 'UTF-8'")
  void testNewStringPart_whenNull_thenReturnCharsetNameIsUtf8() {
    // Arrange and Act
    StringPart actualStringPart = new StringPart("https://example.org/example", "https://example.org/example",
        "https://example.org/example", null);

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
   * <ul>
   *   <li>When {@code null}.</li>
   *   <li>Then return Charset name is {@code UTF-8}.</li>
   * </ul>
   * <p>
   * Method under test:
   * {@link StringPart#StringPart(String, String, String, Charset, String)}
   */
  @Test
  @DisplayName("Test new StringPart(String, String, String, Charset, String); when 'null'; then return Charset name is 'UTF-8'")
  void testNewStringPart_whenNull_thenReturnCharsetNameIsUtf82() {
    // Arrange and Act
    StringPart actualStringPart = new StringPart("https://example.org/example", "https://example.org/example",
        "https://example.org/example", null, "https://example.org/example");

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
   * Test
   * {@link StringPart#StringPart(String, String, String, Charset, String, String)}.
   * <ul>
   *   <li>When {@code null}.</li>
   *   <li>Then return Charset name is {@code UTF-8}.</li>
   * </ul>
   * <p>
   * Method under test:
   * {@link StringPart#StringPart(String, String, String, Charset, String, String)}
   */
  @Test
  @DisplayName("Test new StringPart(String, String, String, Charset, String, String); when 'null'; then return Charset name is 'UTF-8'")
  void testNewStringPart_whenNull_thenReturnCharsetNameIsUtf83() {
    // Arrange and Act
    StringPart actualStringPart = new StringPart("https://example.org/example", "https://example.org/example",
        "https://example.org/example", null, "https://example.org/example", "https://example.org/example");

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
   * <p>
   * Method under test: {@link StringPart#getValue()}
   */
  @Test
  @DisplayName("Test getValue()")
  void testGetValue() {
    // Arrange, Act and Assert
    assertEquals("https://example.org/example",
        (new StringPart("https://example.org/example", "https://example.org/example")).getValue());
  }
}
