package org.asynchttpclient.request.body.multipart;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import java.nio.charset.Charset;
import org.junit.jupiter.api.Test;

class StringPartDiffblueTest {
  /**
   * Method under test: {@link StringPart#getValue()}
   */
  @Test
  void testGetValue() {
    // Arrange, Act and Assert
    assertEquals("https://example.org/example",
        (new StringPart("https://example.org/example", "https://example.org/example")).getValue());
  }

  /**
   * Method under test: {@link StringPart#StringPart(String, String)}
   */
  @Test
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
   * Method under test: {@link StringPart#StringPart(String, String, String)}
   */
  @Test
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
   * Method under test:
   * {@link StringPart#StringPart(String, String, String, Charset)}
   */
  @Test
  void testNewStringPart3() {
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
   * Method under test:
   * {@link StringPart#StringPart(String, String, String, Charset, String)}
   */
  @Test
  void testNewStringPart4() {
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
   * Method under test:
   * {@link StringPart#StringPart(String, String, String, Charset, String, String)}
   */
  @Test
  void testNewStringPart5() {
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
}
