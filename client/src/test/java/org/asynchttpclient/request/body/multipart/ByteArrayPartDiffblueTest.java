package org.asynchttpclient.request.body.multipart;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import org.junit.jupiter.api.Test;

class ByteArrayPartDiffblueTest {
  /**
   * Method under test: {@link ByteArrayPart#getBytes()}
   */
  @Test
  void testGetBytes() throws UnsupportedEncodingException {
    // Arrange and Act
    byte[] actualBytes = (new ByteArrayPart("https://example.org/example", "AXAXAXAX".getBytes("UTF-8"))).getBytes();

    // Assert
    assertArrayEquals("AXAXAXAX".getBytes("UTF-8"), actualBytes);
  }

  /**
   * Method under test: {@link ByteArrayPart#ByteArrayPart(String, byte[])}
   */
  @Test
  void testNewByteArrayPart() throws UnsupportedEncodingException {
    // Arrange
    byte[] bytes = "AXAXAXAX".getBytes("UTF-8");

    // Act
    ByteArrayPart actualByteArrayPart = new ByteArrayPart("https://example.org/example", bytes);

    // Assert
    assertEquals("application/octet-stream", actualByteArrayPart.getContentType());
    assertEquals("https://example.org/example", actualByteArrayPart.getName());
    assertNull(actualByteArrayPart.getFileName());
    assertNull(actualByteArrayPart.getContentId());
    assertNull(actualByteArrayPart.getDispositionType());
    assertNull(actualByteArrayPart.getTransferEncoding());
    assertNull(actualByteArrayPart.getCharset());
    assertNull(actualByteArrayPart.getCustomHeaders());
    assertSame(bytes, actualByteArrayPart.getBytes());
  }

  /**
   * Method under test:
   * {@link ByteArrayPart#ByteArrayPart(String, byte[], String)}
   */
  @Test
  void testNewByteArrayPart2() throws UnsupportedEncodingException {
    // Arrange
    byte[] bytes = "AXAXAXAX".getBytes("UTF-8");

    // Act
    ByteArrayPart actualByteArrayPart = new ByteArrayPart("https://example.org/example", bytes,
        "https://example.org/example");

    // Assert
    assertEquals("https://example.org/example", actualByteArrayPart.getContentType());
    assertEquals("https://example.org/example", actualByteArrayPart.getName());
    assertNull(actualByteArrayPart.getFileName());
    assertNull(actualByteArrayPart.getContentId());
    assertNull(actualByteArrayPart.getDispositionType());
    assertNull(actualByteArrayPart.getTransferEncoding());
    assertNull(actualByteArrayPart.getCharset());
    assertNull(actualByteArrayPart.getCustomHeaders());
    assertSame(bytes, actualByteArrayPart.getBytes());
  }

  /**
   * Method under test:
   * {@link ByteArrayPart#ByteArrayPart(String, byte[], String)}
   */
  @Test
  void testNewByteArrayPart3() {
    // Arrange
    byte[] bytes = new byte[]{'A', 'X', 'A', 'X', 'A', 'X', 'A', 'X'};

    // Act
    ByteArrayPart actualByteArrayPart = new ByteArrayPart("https://example.org/example", bytes, null);

    // Assert
    assertEquals("application/octet-stream", actualByteArrayPart.getContentType());
    assertEquals("https://example.org/example", actualByteArrayPart.getName());
    assertNull(actualByteArrayPart.getFileName());
    assertNull(actualByteArrayPart.getContentId());
    assertNull(actualByteArrayPart.getDispositionType());
    assertNull(actualByteArrayPart.getTransferEncoding());
    assertNull(actualByteArrayPart.getCharset());
    assertNull(actualByteArrayPart.getCustomHeaders());
    assertSame(bytes, actualByteArrayPart.getBytes());
  }

  /**
   * Method under test:
   * {@link ByteArrayPart#ByteArrayPart(String, byte[], String, Charset)}
   */
  @Test
  void testNewByteArrayPart4() throws UnsupportedEncodingException {
    // Arrange
    byte[] bytes = "AXAXAXAX".getBytes("UTF-8");

    // Act
    ByteArrayPart actualByteArrayPart = new ByteArrayPart("https://example.org/example", bytes,
        "https://example.org/example", null);

    // Assert
    assertEquals("https://example.org/example", actualByteArrayPart.getContentType());
    assertEquals("https://example.org/example", actualByteArrayPart.getName());
    assertNull(actualByteArrayPart.getFileName());
    assertNull(actualByteArrayPart.getContentId());
    assertNull(actualByteArrayPart.getDispositionType());
    assertNull(actualByteArrayPart.getTransferEncoding());
    assertNull(actualByteArrayPart.getCharset());
    assertNull(actualByteArrayPart.getCustomHeaders());
    assertSame(bytes, actualByteArrayPart.getBytes());
  }

  /**
   * Method under test:
   * {@link ByteArrayPart#ByteArrayPart(String, byte[], String, Charset)}
   */
  @Test
  void testNewByteArrayPart5() {
    // Arrange
    byte[] bytes = new byte[]{'A', 'X', 'A', 'X', 'A', 'X', 'A', 'X'};

    // Act
    ByteArrayPart actualByteArrayPart = new ByteArrayPart("https://example.org/example", bytes, null, null);

    // Assert
    assertEquals("application/octet-stream", actualByteArrayPart.getContentType());
    assertEquals("https://example.org/example", actualByteArrayPart.getName());
    assertNull(actualByteArrayPart.getFileName());
    assertNull(actualByteArrayPart.getContentId());
    assertNull(actualByteArrayPart.getDispositionType());
    assertNull(actualByteArrayPart.getTransferEncoding());
    assertNull(actualByteArrayPart.getCharset());
    assertNull(actualByteArrayPart.getCustomHeaders());
    assertSame(bytes, actualByteArrayPart.getBytes());
  }

  /**
   * Method under test:
   * {@link ByteArrayPart#ByteArrayPart(String, byte[], String, Charset, String)}
   */
  @Test
  void testNewByteArrayPart6() throws UnsupportedEncodingException {
    // Arrange
    byte[] bytes = "AXAXAXAX".getBytes("UTF-8");

    // Act
    ByteArrayPart actualByteArrayPart = new ByteArrayPart("https://example.org/example", bytes,
        "https://example.org/example", null, "https://example.org/example");

    // Assert
    assertEquals("https://example.org/example", actualByteArrayPart.getFileName());
    assertEquals("https://example.org/example", actualByteArrayPart.getContentType());
    assertEquals("https://example.org/example", actualByteArrayPart.getName());
    assertNull(actualByteArrayPart.getContentId());
    assertNull(actualByteArrayPart.getDispositionType());
    assertNull(actualByteArrayPart.getTransferEncoding());
    assertNull(actualByteArrayPart.getCharset());
    assertNull(actualByteArrayPart.getCustomHeaders());
    assertSame(bytes, actualByteArrayPart.getBytes());
  }

  /**
   * Method under test:
   * {@link ByteArrayPart#ByteArrayPart(String, byte[], String, Charset, String)}
   */
  @Test
  void testNewByteArrayPart7() {
    // Arrange
    byte[] bytes = new byte[]{'A', 'X', 'A', 'X', 'A', 'X', 'A', 'X'};

    // Act
    ByteArrayPart actualByteArrayPart = new ByteArrayPart("https://example.org/example", bytes, null, null, null);

    // Assert
    assertEquals("application/octet-stream", actualByteArrayPart.getContentType());
    assertEquals("https://example.org/example", actualByteArrayPart.getName());
    assertNull(actualByteArrayPart.getFileName());
    assertNull(actualByteArrayPart.getContentId());
    assertNull(actualByteArrayPart.getDispositionType());
    assertNull(actualByteArrayPart.getTransferEncoding());
    assertNull(actualByteArrayPart.getCharset());
    assertNull(actualByteArrayPart.getCustomHeaders());
    assertSame(bytes, actualByteArrayPart.getBytes());
  }

  /**
   * Method under test:
   * {@link ByteArrayPart#ByteArrayPart(String, byte[], String, Charset, String)}
   */
  @Test
  void testNewByteArrayPart8() {
    // Arrange
    byte[] bytes = new byte[]{'A', 'X', 'A', 'X', 'A', 'X', 'A', 'X'};

    // Act
    ByteArrayPart actualByteArrayPart = new ByteArrayPart("https://example.org/example", bytes, null, null,
        "https://example.org/example");

    // Assert
    assertEquals("application/octet-stream", actualByteArrayPart.getContentType());
    assertEquals("https://example.org/example", actualByteArrayPart.getFileName());
    assertEquals("https://example.org/example", actualByteArrayPart.getName());
    assertNull(actualByteArrayPart.getContentId());
    assertNull(actualByteArrayPart.getDispositionType());
    assertNull(actualByteArrayPart.getTransferEncoding());
    assertNull(actualByteArrayPart.getCharset());
    assertNull(actualByteArrayPart.getCustomHeaders());
    assertSame(bytes, actualByteArrayPart.getBytes());
  }

  /**
   * Method under test:
   * {@link ByteArrayPart#ByteArrayPart(String, byte[], String, Charset, String)}
   */
  @Test
  void testNewByteArrayPart9() {
    // Arrange
    byte[] bytes = new byte[]{'A', 'X', 'A', 'X', 'A', 'X', 'A', 'X'};

    // Act
    ByteArrayPart actualByteArrayPart = new ByteArrayPart("https://example.org/example", bytes, null, null, "foo.txt");

    // Assert
    assertEquals("foo.txt", actualByteArrayPart.getFileName());
    assertEquals("https://example.org/example", actualByteArrayPart.getName());
    assertEquals("text/plain", actualByteArrayPart.getContentType());
    assertNull(actualByteArrayPart.getContentId());
    assertNull(actualByteArrayPart.getDispositionType());
    assertNull(actualByteArrayPart.getTransferEncoding());
    assertNull(actualByteArrayPart.getCharset());
    assertNull(actualByteArrayPart.getCustomHeaders());
    assertSame(bytes, actualByteArrayPart.getBytes());
  }

  /**
   * Method under test:
   * {@link ByteArrayPart#ByteArrayPart(String, byte[], String, Charset, String, String)}
   */
  @Test
  void testNewByteArrayPart10() throws UnsupportedEncodingException {
    // Arrange
    byte[] bytes = "AXAXAXAX".getBytes("UTF-8");

    // Act
    ByteArrayPart actualByteArrayPart = new ByteArrayPart("https://example.org/example", bytes,
        "https://example.org/example", null, "https://example.org/example", "https://example.org/example");

    // Assert
    assertEquals("https://example.org/example", actualByteArrayPart.getFileName());
    assertEquals("https://example.org/example", actualByteArrayPart.getContentId());
    assertEquals("https://example.org/example", actualByteArrayPart.getContentType());
    assertEquals("https://example.org/example", actualByteArrayPart.getName());
    assertNull(actualByteArrayPart.getDispositionType());
    assertNull(actualByteArrayPart.getTransferEncoding());
    assertNull(actualByteArrayPart.getCharset());
    assertNull(actualByteArrayPart.getCustomHeaders());
    assertSame(bytes, actualByteArrayPart.getBytes());
  }

  /**
   * Method under test:
   * {@link ByteArrayPart#ByteArrayPart(String, byte[], String, Charset, String, String)}
   */
  @Test
  void testNewByteArrayPart11() {
    // Arrange
    byte[] bytes = new byte[]{'A', 'X', 'A', 'X', 'A', 'X', 'A', 'X'};

    // Act
    ByteArrayPart actualByteArrayPart = new ByteArrayPart("https://example.org/example", bytes, null, null, null,
        "https://example.org/example");

    // Assert
    assertEquals("application/octet-stream", actualByteArrayPart.getContentType());
    assertEquals("https://example.org/example", actualByteArrayPart.getContentId());
    assertEquals("https://example.org/example", actualByteArrayPart.getName());
    assertNull(actualByteArrayPart.getFileName());
    assertNull(actualByteArrayPart.getDispositionType());
    assertNull(actualByteArrayPart.getTransferEncoding());
    assertNull(actualByteArrayPart.getCharset());
    assertNull(actualByteArrayPart.getCustomHeaders());
    assertSame(bytes, actualByteArrayPart.getBytes());
  }

  /**
   * Method under test:
   * {@link ByteArrayPart#ByteArrayPart(String, byte[], String, Charset, String, String)}
   */
  @Test
  void testNewByteArrayPart12() {
    // Arrange
    byte[] bytes = new byte[]{'A', 'X', 'A', 'X', 'A', 'X', 'A', 'X'};

    // Act
    ByteArrayPart actualByteArrayPart = new ByteArrayPart("https://example.org/example", bytes, null, null,
        "https://example.org/example", "https://example.org/example");

    // Assert
    assertEquals("application/octet-stream", actualByteArrayPart.getContentType());
    assertEquals("https://example.org/example", actualByteArrayPart.getFileName());
    assertEquals("https://example.org/example", actualByteArrayPart.getContentId());
    assertEquals("https://example.org/example", actualByteArrayPart.getName());
    assertNull(actualByteArrayPart.getDispositionType());
    assertNull(actualByteArrayPart.getTransferEncoding());
    assertNull(actualByteArrayPart.getCharset());
    assertNull(actualByteArrayPart.getCustomHeaders());
    assertSame(bytes, actualByteArrayPart.getBytes());
  }

  /**
   * Method under test:
   * {@link ByteArrayPart#ByteArrayPart(String, byte[], String, Charset, String, String)}
   */
  @Test
  void testNewByteArrayPart13() {
    // Arrange
    byte[] bytes = new byte[]{'A', 'X', 'A', 'X', 'A', 'X', 'A', 'X'};

    // Act
    ByteArrayPart actualByteArrayPart = new ByteArrayPart("https://example.org/example", bytes, null, null, "foo.txt",
        "https://example.org/example");

    // Assert
    assertEquals("foo.txt", actualByteArrayPart.getFileName());
    assertEquals("https://example.org/example", actualByteArrayPart.getContentId());
    assertEquals("https://example.org/example", actualByteArrayPart.getName());
    assertEquals("text/plain", actualByteArrayPart.getContentType());
    assertNull(actualByteArrayPart.getDispositionType());
    assertNull(actualByteArrayPart.getTransferEncoding());
    assertNull(actualByteArrayPart.getCharset());
    assertNull(actualByteArrayPart.getCustomHeaders());
    assertSame(bytes, actualByteArrayPart.getBytes());
  }

  /**
   * Method under test:
   * {@link ByteArrayPart#ByteArrayPart(String, byte[], String, Charset, String, String, String)}
   */
  @Test
  void testNewByteArrayPart14() throws UnsupportedEncodingException {
    // Arrange
    byte[] bytes = "AXAXAXAX".getBytes("UTF-8");

    // Act
    ByteArrayPart actualByteArrayPart = new ByteArrayPart("https://example.org/example", bytes,
        "https://example.org/example", null, "https://example.org/example", "https://example.org/example",
        "https://example.org/example");

    // Assert
    assertEquals("https://example.org/example", actualByteArrayPart.getFileName());
    assertEquals("https://example.org/example", actualByteArrayPart.getContentId());
    assertEquals("https://example.org/example", actualByteArrayPart.getContentType());
    assertEquals("https://example.org/example", actualByteArrayPart.getName());
    assertEquals("https://example.org/example", actualByteArrayPart.getTransferEncoding());
    assertNull(actualByteArrayPart.getDispositionType());
    assertNull(actualByteArrayPart.getCharset());
    assertNull(actualByteArrayPart.getCustomHeaders());
    assertSame(bytes, actualByteArrayPart.getBytes());
  }

  /**
   * Method under test:
   * {@link ByteArrayPart#ByteArrayPart(String, byte[], String, Charset, String, String, String)}
   */
  @Test
  void testNewByteArrayPart15() {
    // Arrange
    byte[] bytes = new byte[]{'A', 'X', 'A', 'X', 'A', 'X', 'A', 'X'};

    // Act
    ByteArrayPart actualByteArrayPart = new ByteArrayPart("https://example.org/example", bytes, null, null, null,
        "https://example.org/example", "https://example.org/example");

    // Assert
    assertEquals("application/octet-stream", actualByteArrayPart.getContentType());
    assertEquals("https://example.org/example", actualByteArrayPart.getContentId());
    assertEquals("https://example.org/example", actualByteArrayPart.getName());
    assertEquals("https://example.org/example", actualByteArrayPart.getTransferEncoding());
    assertNull(actualByteArrayPart.getFileName());
    assertNull(actualByteArrayPart.getDispositionType());
    assertNull(actualByteArrayPart.getCharset());
    assertNull(actualByteArrayPart.getCustomHeaders());
    assertSame(bytes, actualByteArrayPart.getBytes());
  }

  /**
   * Method under test:
   * {@link ByteArrayPart#ByteArrayPart(String, byte[], String, Charset, String, String, String)}
   */
  @Test
  void testNewByteArrayPart16() {
    // Arrange
    byte[] bytes = new byte[]{'A', 'X', 'A', 'X', 'A', 'X', 'A', 'X'};

    // Act
    ByteArrayPart actualByteArrayPart = new ByteArrayPart("https://example.org/example", bytes, null, null,
        "https://example.org/example", "https://example.org/example", "https://example.org/example");

    // Assert
    assertEquals("application/octet-stream", actualByteArrayPart.getContentType());
    assertEquals("https://example.org/example", actualByteArrayPart.getFileName());
    assertEquals("https://example.org/example", actualByteArrayPart.getContentId());
    assertEquals("https://example.org/example", actualByteArrayPart.getName());
    assertEquals("https://example.org/example", actualByteArrayPart.getTransferEncoding());
    assertNull(actualByteArrayPart.getDispositionType());
    assertNull(actualByteArrayPart.getCharset());
    assertNull(actualByteArrayPart.getCustomHeaders());
    assertSame(bytes, actualByteArrayPart.getBytes());
  }

  /**
   * Method under test:
   * {@link ByteArrayPart#ByteArrayPart(String, byte[], String, Charset, String, String, String)}
   */
  @Test
  void testNewByteArrayPart17() {
    // Arrange
    byte[] bytes = new byte[]{'A', 'X', 'A', 'X', 'A', 'X', 'A', 'X'};

    // Act
    ByteArrayPart actualByteArrayPart = new ByteArrayPart("https://example.org/example", bytes, null, null, "foo.txt",
        "https://example.org/example", "https://example.org/example");

    // Assert
    assertEquals("foo.txt", actualByteArrayPart.getFileName());
    assertEquals("https://example.org/example", actualByteArrayPart.getContentId());
    assertEquals("https://example.org/example", actualByteArrayPart.getName());
    assertEquals("https://example.org/example", actualByteArrayPart.getTransferEncoding());
    assertEquals("text/plain", actualByteArrayPart.getContentType());
    assertNull(actualByteArrayPart.getDispositionType());
    assertNull(actualByteArrayPart.getCharset());
    assertNull(actualByteArrayPart.getCustomHeaders());
    assertSame(bytes, actualByteArrayPart.getBytes());
  }
}
