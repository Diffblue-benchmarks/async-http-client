package org.asynchttpclient.request.body.multipart;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.Mockito.mock;
import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import org.junit.jupiter.api.Test;

class InputStreamPartDiffblueTest {
  /**
   * Methods under test:
   * <ul>
   *   <li>{@link InputStreamPart#getContentLength()}
   *   <li>{@link InputStreamPart#getInputStream()}
   * </ul>
   */
  @Test
  void testGettersAndSetters() throws IOException {
    // Arrange
    ByteArrayInputStream inputStream = new ByteArrayInputStream("AXAXAXAX".getBytes("UTF-8"));
    InputStreamPart inputStreamPart = new InputStreamPart("https://example.org/example", inputStream,
        "https://example.org/example");

    // Act
    long actualContentLength = inputStreamPart.getContentLength();
    InputStream actualInputStream = inputStreamPart.getInputStream();

    // Assert
    assertEquals(-1L, actualContentLength);
    assertEquals(8, actualInputStream.read(new byte[8]));
    assertSame(inputStream, actualInputStream);
  }

  /**
   * Method under test:
   * {@link InputStreamPart#InputStreamPart(String, InputStream, String)}
   */
  @Test
  void testNewInputStreamPart() {
    // Arrange
    DataInputStream inputStream = mock(DataInputStream.class);

    // Act
    InputStreamPart actualInputStreamPart = new InputStreamPart("https://example.org/example", inputStream,
        "https://example.org/example");

    // Assert
    assertEquals("application/octet-stream", actualInputStreamPart.getContentType());
    assertEquals("https://example.org/example", actualInputStreamPart.getFileName());
    assertEquals("https://example.org/example", actualInputStreamPart.getName());
    assertNull(actualInputStreamPart.getContentId());
    assertNull(actualInputStreamPart.getDispositionType());
    assertNull(actualInputStreamPart.getTransferEncoding());
    assertNull(actualInputStreamPart.getCharset());
    assertNull(actualInputStreamPart.getCustomHeaders());
    assertEquals(-1L, actualInputStreamPart.getContentLength());
    assertSame(inputStream, actualInputStreamPart.getInputStream());
  }

  /**
   * Method under test:
   * {@link InputStreamPart#InputStreamPart(String, InputStream, String, long)}
   */
  @Test
  void testNewInputStreamPart2() {
    // Arrange
    DataInputStream inputStream = mock(DataInputStream.class);

    // Act
    InputStreamPart actualInputStreamPart = new InputStreamPart("https://example.org/example", inputStream,
        "https://example.org/example", 3L);

    // Assert
    assertEquals("application/octet-stream", actualInputStreamPart.getContentType());
    assertEquals("https://example.org/example", actualInputStreamPart.getFileName());
    assertEquals("https://example.org/example", actualInputStreamPart.getName());
    assertNull(actualInputStreamPart.getContentId());
    assertNull(actualInputStreamPart.getDispositionType());
    assertNull(actualInputStreamPart.getTransferEncoding());
    assertNull(actualInputStreamPart.getCharset());
    assertNull(actualInputStreamPart.getCustomHeaders());
    assertEquals(3L, actualInputStreamPart.getContentLength());
    assertSame(inputStream, actualInputStreamPart.getInputStream());
  }

  /**
   * Method under test:
   * {@link InputStreamPart#InputStreamPart(String, InputStream, String, long, String)}
   */
  @Test
  void testNewInputStreamPart3() {
    // Arrange
    DataInputStream inputStream = mock(DataInputStream.class);

    // Act
    InputStreamPart actualInputStreamPart = new InputStreamPart("https://example.org/example", inputStream,
        "https://example.org/example", 3L, "https://example.org/example");

    // Assert
    assertEquals("https://example.org/example", actualInputStreamPart.getFileName());
    assertEquals("https://example.org/example", actualInputStreamPart.getContentType());
    assertEquals("https://example.org/example", actualInputStreamPart.getName());
    assertNull(actualInputStreamPart.getContentId());
    assertNull(actualInputStreamPart.getDispositionType());
    assertNull(actualInputStreamPart.getTransferEncoding());
    assertNull(actualInputStreamPart.getCharset());
    assertNull(actualInputStreamPart.getCustomHeaders());
    assertEquals(3L, actualInputStreamPart.getContentLength());
    assertSame(inputStream, actualInputStreamPart.getInputStream());
  }

  /**
   * Method under test:
   * {@link InputStreamPart#InputStreamPart(String, InputStream, String, long, String, Charset)}
   */
  @Test
  void testNewInputStreamPart4() {
    // Arrange
    DataInputStream inputStream = mock(DataInputStream.class);

    // Act
    InputStreamPart actualInputStreamPart = new InputStreamPart("https://example.org/example", inputStream,
        "https://example.org/example", 3L, "https://example.org/example", null);

    // Assert
    assertEquals("https://example.org/example", actualInputStreamPart.getFileName());
    assertEquals("https://example.org/example", actualInputStreamPart.getContentType());
    assertEquals("https://example.org/example", actualInputStreamPart.getName());
    assertNull(actualInputStreamPart.getContentId());
    assertNull(actualInputStreamPart.getDispositionType());
    assertNull(actualInputStreamPart.getTransferEncoding());
    assertNull(actualInputStreamPart.getCharset());
    assertNull(actualInputStreamPart.getCustomHeaders());
    assertEquals(3L, actualInputStreamPart.getContentLength());
    assertSame(inputStream, actualInputStreamPart.getInputStream());
  }

  /**
   * Method under test:
   * {@link InputStreamPart#InputStreamPart(String, InputStream, String, long, String, Charset, String)}
   */
  @Test
  void testNewInputStreamPart5() {
    // Arrange
    DataInputStream inputStream = mock(DataInputStream.class);

    // Act
    InputStreamPart actualInputStreamPart = new InputStreamPart("https://example.org/example", inputStream,
        "https://example.org/example", 3L, "https://example.org/example", null, "https://example.org/example");

    // Assert
    assertEquals("https://example.org/example", actualInputStreamPart.getFileName());
    assertEquals("https://example.org/example", actualInputStreamPart.getContentId());
    assertEquals("https://example.org/example", actualInputStreamPart.getContentType());
    assertEquals("https://example.org/example", actualInputStreamPart.getName());
    assertNull(actualInputStreamPart.getDispositionType());
    assertNull(actualInputStreamPart.getTransferEncoding());
    assertNull(actualInputStreamPart.getCharset());
    assertNull(actualInputStreamPart.getCustomHeaders());
    assertEquals(3L, actualInputStreamPart.getContentLength());
    assertSame(inputStream, actualInputStreamPart.getInputStream());
  }

  /**
   * Method under test:
   * {@link InputStreamPart#InputStreamPart(String, InputStream, String, long, String, Charset, String, String)}
   */
  @Test
  void testNewInputStreamPart6() {
    // Arrange
    DataInputStream inputStream = mock(DataInputStream.class);

    // Act
    InputStreamPart actualInputStreamPart = new InputStreamPart("https://example.org/example", inputStream,
        "https://example.org/example", 3L, "https://example.org/example", null, "https://example.org/example",
        "https://example.org/example");

    // Assert
    assertEquals("https://example.org/example", actualInputStreamPart.getFileName());
    assertEquals("https://example.org/example", actualInputStreamPart.getContentId());
    assertEquals("https://example.org/example", actualInputStreamPart.getContentType());
    assertEquals("https://example.org/example", actualInputStreamPart.getName());
    assertEquals("https://example.org/example", actualInputStreamPart.getTransferEncoding());
    assertNull(actualInputStreamPart.getDispositionType());
    assertNull(actualInputStreamPart.getCharset());
    assertNull(actualInputStreamPart.getCustomHeaders());
    assertEquals(3L, actualInputStreamPart.getContentLength());
    assertSame(inputStream, actualInputStreamPart.getInputStream());
  }
}
