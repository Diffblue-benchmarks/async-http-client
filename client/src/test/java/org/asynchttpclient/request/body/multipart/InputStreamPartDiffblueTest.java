package org.asynchttpclient.request.body.multipart;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

class InputStreamPartDiffblueTest {
  /**
   * Test {@link InputStreamPart#InputStreamPart(String, InputStream, String, long, String)}.
   * <ul>
   *   <li>Then return ContentType is {@code application/octet-stream}.</li>
   * </ul>
   * <p>
   * Method under test: {@link InputStreamPart#InputStreamPart(String, InputStream, String, long, String)}
   */
  @Test
  @DisplayName("Test new InputStreamPart(String, InputStream, String, long, String); then return ContentType is 'application/octet-stream'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void InputStreamPart.<init>(String, InputStream, String, long, String)"})
  void testNewInputStreamPart_thenReturnContentTypeIsApplicationOctetStream() throws IOException {
    // Arrange
    ByteArrayInputStream inputStream = new ByteArrayInputStream("AXAXAXAX".getBytes("UTF-8"));

    // Act
    InputStreamPart actualInputStreamPart = new InputStreamPart("https://example.org/example", inputStream,
        "https://example.org/example", 3L, null);

    // Assert
    assertNull(actualInputStreamPart.getCharset());
    assertNull(actualInputStreamPart.getContentId());
    assertEquals(3L, actualInputStreamPart.getContentLength());
    assertEquals("application/octet-stream", actualInputStreamPart.getContentType());
    assertNull(actualInputStreamPart.getCustomHeaders());
    assertNull(actualInputStreamPart.getDispositionType());
    assertEquals("https://example.org/example", actualInputStreamPart.getFileName());
    byte[] byteArray = new byte[8];
    assertEquals(8, actualInputStreamPart.getInputStream().read(byteArray));
    assertArrayEquals("AXAXAXAX".getBytes("UTF-8"), byteArray);
    assertEquals("https://example.org/example", actualInputStreamPart.getName());
    assertNull(actualInputStreamPart.getTransferEncoding());
    assertEquals(-1, inputStream.read(new byte[]{}));
  }

  /**
   * Test {@link InputStreamPart#InputStreamPart(String, InputStream, String, long, String)}.
   * <ul>
   *   <li>Then return ContentType is {@code https://example.org/example}.</li>
   * </ul>
   * <p>
   * Method under test: {@link InputStreamPart#InputStreamPart(String, InputStream, String, long, String)}
   */
  @Test
  @DisplayName("Test new InputStreamPart(String, InputStream, String, long, String); then return ContentType is 'https://example.org/example'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void InputStreamPart.<init>(String, InputStream, String, long, String)"})
  void testNewInputStreamPart_thenReturnContentTypeIsHttpsExampleOrgExample() throws IOException {
    // Arrange
    ByteArrayInputStream inputStream = new ByteArrayInputStream("AXAXAXAX".getBytes("UTF-8"));

    // Act
    InputStreamPart actualInputStreamPart = new InputStreamPart("https://example.org/example", inputStream,
        "https://example.org/example", 3L, "https://example.org/example");

    // Assert
    assertNull(actualInputStreamPart.getCharset());
    assertNull(actualInputStreamPart.getContentId());
    assertEquals(3L, actualInputStreamPart.getContentLength());
    assertEquals("https://example.org/example", actualInputStreamPart.getContentType());
    assertNull(actualInputStreamPart.getCustomHeaders());
    assertNull(actualInputStreamPart.getDispositionType());
    assertEquals("https://example.org/example", actualInputStreamPart.getFileName());
    byte[] byteArray = new byte[8];
    assertEquals(8, actualInputStreamPart.getInputStream().read(byteArray));
    assertArrayEquals("AXAXAXAX".getBytes("UTF-8"), byteArray);
    assertEquals("https://example.org/example", actualInputStreamPart.getName());
    assertNull(actualInputStreamPart.getTransferEncoding());
    assertEquals(-1, inputStream.read(new byte[]{}));
  }

  /**
   * Test {@link InputStreamPart#InputStreamPart(String, InputStream, String)}.
   * <ul>
   *   <li>Then return FileName is {@code https://example.org/example}.</li>
   * </ul>
   * <p>
   * Method under test: {@link InputStreamPart#InputStreamPart(String, InputStream, String)}
   */
  @Test
  @DisplayName("Test new InputStreamPart(String, InputStream, String); then return FileName is 'https://example.org/example'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void InputStreamPart.<init>(String, InputStream, String)"})
  void testNewInputStreamPart_thenReturnFileNameIsHttpsExampleOrgExample() throws IOException {
    // Arrange
    ByteArrayInputStream inputStream = new ByteArrayInputStream("AXAXAXAX".getBytes("UTF-8"));

    // Act
    InputStreamPart actualInputStreamPart = new InputStreamPart("https://example.org/example", inputStream,
        "https://example.org/example");

    // Assert
    assertNull(actualInputStreamPart.getCharset());
    assertNull(actualInputStreamPart.getContentId());
    assertEquals(-1L, actualInputStreamPart.getContentLength());
    assertEquals("application/octet-stream", actualInputStreamPart.getContentType());
    assertNull(actualInputStreamPart.getCustomHeaders());
    assertNull(actualInputStreamPart.getDispositionType());
    assertEquals("https://example.org/example", actualInputStreamPart.getFileName());
    byte[] byteArray = new byte[8];
    assertEquals(8, actualInputStreamPart.getInputStream().read(byteArray));
    assertArrayEquals("AXAXAXAX".getBytes("UTF-8"), byteArray);
    assertEquals("https://example.org/example", actualInputStreamPart.getName());
    assertNull(actualInputStreamPart.getTransferEncoding());
    assertEquals(-1, inputStream.read(new byte[]{}));
  }

  /**
   * Test {@link InputStreamPart#InputStreamPart(String, InputStream, String, long)}.
   * <ul>
   *   <li>Then return FileName is {@code https://example.org/example}.</li>
   * </ul>
   * <p>
   * Method under test: {@link InputStreamPart#InputStreamPart(String, InputStream, String, long)}
   */
  @Test
  @DisplayName("Test new InputStreamPart(String, InputStream, String, long); then return FileName is 'https://example.org/example'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void InputStreamPart.<init>(String, InputStream, String, long)"})
  void testNewInputStreamPart_thenReturnFileNameIsHttpsExampleOrgExample2() throws IOException {
    // Arrange
    ByteArrayInputStream inputStream = new ByteArrayInputStream("AXAXAXAX".getBytes("UTF-8"));

    // Act
    InputStreamPart actualInputStreamPart = new InputStreamPart("https://example.org/example", inputStream,
        "https://example.org/example", 3L);

    // Assert
    assertNull(actualInputStreamPart.getCharset());
    assertNull(actualInputStreamPart.getContentId());
    assertEquals(3L, actualInputStreamPart.getContentLength());
    assertEquals("application/octet-stream", actualInputStreamPart.getContentType());
    assertNull(actualInputStreamPart.getCustomHeaders());
    assertNull(actualInputStreamPart.getDispositionType());
    assertEquals("https://example.org/example", actualInputStreamPart.getFileName());
    byte[] byteArray = new byte[8];
    assertEquals(8, actualInputStreamPart.getInputStream().read(byteArray));
    assertArrayEquals("AXAXAXAX".getBytes("UTF-8"), byteArray);
    assertEquals("https://example.org/example", actualInputStreamPart.getName());
    assertNull(actualInputStreamPart.getTransferEncoding());
    assertEquals(-1, inputStream.read(new byte[]{}));
  }

  /**
   * Test {@link InputStreamPart#InputStreamPart(String, InputStream, String, long, String)}.
   * <ul>
   *   <li>Then return FileName is {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link InputStreamPart#InputStreamPart(String, InputStream, String, long, String)}
   */
  @Test
  @DisplayName("Test new InputStreamPart(String, InputStream, String, long, String); then return FileName is 'null'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void InputStreamPart.<init>(String, InputStream, String, long, String)"})
  void testNewInputStreamPart_thenReturnFileNameIsNull() throws IOException {
    // Arrange
    ByteArrayInputStream inputStream = new ByteArrayInputStream("AXAXAXAX".getBytes("UTF-8"));

    // Act
    InputStreamPart actualInputStreamPart = new InputStreamPart("https://example.org/example", inputStream, null, 3L,
        null);

    // Assert
    assertNull(actualInputStreamPart.getCharset());
    assertNull(actualInputStreamPart.getContentId());
    assertEquals(3L, actualInputStreamPart.getContentLength());
    assertEquals("application/octet-stream", actualInputStreamPart.getContentType());
    assertNull(actualInputStreamPart.getCustomHeaders());
    assertNull(actualInputStreamPart.getDispositionType());
    assertNull(actualInputStreamPart.getFileName());
    byte[] byteArray = new byte[8];
    assertEquals(8, actualInputStreamPart.getInputStream().read(byteArray));
    assertArrayEquals("AXAXAXAX".getBytes("UTF-8"), byteArray);
    assertEquals("https://example.org/example", actualInputStreamPart.getName());
    assertNull(actualInputStreamPart.getTransferEncoding());
    assertEquals(-1, inputStream.read(new byte[]{}));
  }

  /**
   * Test {@link InputStreamPart#InputStreamPart(String, InputStream, String)}.
   * <ul>
   *   <li>When {@code foo.txt}.</li>
   *   <li>Then return ContentType is {@code text/plain}.</li>
   * </ul>
   * <p>
   * Method under test: {@link InputStreamPart#InputStreamPart(String, InputStream, String)}
   */
  @Test
  @DisplayName("Test new InputStreamPart(String, InputStream, String); when 'foo.txt'; then return ContentType is 'text/plain'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void InputStreamPart.<init>(String, InputStream, String)"})
  void testNewInputStreamPart_whenFooTxt_thenReturnContentTypeIsTextPlain() throws IOException {
    // Arrange
    ByteArrayInputStream inputStream = new ByteArrayInputStream("AXAXAXAX".getBytes("UTF-8"));

    // Act
    InputStreamPart actualInputStreamPart = new InputStreamPart("https://example.org/example", inputStream, "foo.txt");

    // Assert
    assertNull(actualInputStreamPart.getCharset());
    assertNull(actualInputStreamPart.getContentId());
    assertEquals(-1L, actualInputStreamPart.getContentLength());
    assertEquals("text/plain", actualInputStreamPart.getContentType());
    assertNull(actualInputStreamPart.getCustomHeaders());
    assertNull(actualInputStreamPart.getDispositionType());
    assertEquals("foo.txt", actualInputStreamPart.getFileName());
    byte[] byteArray = new byte[8];
    assertEquals(8, actualInputStreamPart.getInputStream().read(byteArray));
    assertArrayEquals("AXAXAXAX".getBytes("UTF-8"), byteArray);
    assertEquals("https://example.org/example", actualInputStreamPart.getName());
    assertNull(actualInputStreamPart.getTransferEncoding());
    assertEquals(-1, inputStream.read(new byte[]{}));
  }

  /**
   * Test {@link InputStreamPart#InputStreamPart(String, InputStream, String, long)}.
   * <ul>
   *   <li>When {@code foo.txt}.</li>
   *   <li>Then return ContentType is {@code text/plain}.</li>
   * </ul>
   * <p>
   * Method under test: {@link InputStreamPart#InputStreamPart(String, InputStream, String, long)}
   */
  @Test
  @DisplayName("Test new InputStreamPart(String, InputStream, String, long); when 'foo.txt'; then return ContentType is 'text/plain'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void InputStreamPart.<init>(String, InputStream, String, long)"})
  void testNewInputStreamPart_whenFooTxt_thenReturnContentTypeIsTextPlain2() throws IOException {
    // Arrange
    ByteArrayInputStream inputStream = new ByteArrayInputStream("AXAXAXAX".getBytes("UTF-8"));

    // Act
    InputStreamPart actualInputStreamPart = new InputStreamPart("https://example.org/example", inputStream, "foo.txt",
        3L);

    // Assert
    assertNull(actualInputStreamPart.getCharset());
    assertNull(actualInputStreamPart.getContentId());
    assertEquals(3L, actualInputStreamPart.getContentLength());
    assertEquals("text/plain", actualInputStreamPart.getContentType());
    assertNull(actualInputStreamPart.getCustomHeaders());
    assertNull(actualInputStreamPart.getDispositionType());
    assertEquals("foo.txt", actualInputStreamPart.getFileName());
    byte[] byteArray = new byte[8];
    assertEquals(8, actualInputStreamPart.getInputStream().read(byteArray));
    assertArrayEquals("AXAXAXAX".getBytes("UTF-8"), byteArray);
    assertEquals("https://example.org/example", actualInputStreamPart.getName());
    assertNull(actualInputStreamPart.getTransferEncoding());
    assertEquals(-1, inputStream.read(new byte[]{}));
  }

  /**
   * Test {@link InputStreamPart#InputStreamPart(String, InputStream, String, long, String)}.
   * <ul>
   *   <li>When {@code foo.txt}.</li>
   *   <li>Then return ContentType is {@code text/plain}.</li>
   * </ul>
   * <p>
   * Method under test: {@link InputStreamPart#InputStreamPart(String, InputStream, String, long, String)}
   */
  @Test
  @DisplayName("Test new InputStreamPart(String, InputStream, String, long, String); when 'foo.txt'; then return ContentType is 'text/plain'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void InputStreamPart.<init>(String, InputStream, String, long, String)"})
  void testNewInputStreamPart_whenFooTxt_thenReturnContentTypeIsTextPlain3() throws IOException {
    // Arrange
    ByteArrayInputStream inputStream = new ByteArrayInputStream("AXAXAXAX".getBytes("UTF-8"));

    // Act
    InputStreamPart actualInputStreamPart = new InputStreamPart("https://example.org/example", inputStream, "foo.txt",
        3L, null);

    // Assert
    assertNull(actualInputStreamPart.getCharset());
    assertNull(actualInputStreamPart.getContentId());
    assertEquals(3L, actualInputStreamPart.getContentLength());
    assertEquals("text/plain", actualInputStreamPart.getContentType());
    assertNull(actualInputStreamPart.getCustomHeaders());
    assertNull(actualInputStreamPart.getDispositionType());
    assertEquals("foo.txt", actualInputStreamPart.getFileName());
    byte[] byteArray = new byte[8];
    assertEquals(8, actualInputStreamPart.getInputStream().read(byteArray));
    assertArrayEquals("AXAXAXAX".getBytes("UTF-8"), byteArray);
    assertEquals("https://example.org/example", actualInputStreamPart.getName());
    assertNull(actualInputStreamPart.getTransferEncoding());
    assertEquals(-1, inputStream.read(new byte[]{}));
  }

  /**
   * Test {@link InputStreamPart#InputStreamPart(String, InputStream, String)}.
   * <ul>
   *   <li>When {@code null}.</li>
   *   <li>Then return FileName is {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link InputStreamPart#InputStreamPart(String, InputStream, String)}
   */
  @Test
  @DisplayName("Test new InputStreamPart(String, InputStream, String); when 'null'; then return FileName is 'null'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void InputStreamPart.<init>(String, InputStream, String)"})
  void testNewInputStreamPart_whenNull_thenReturnFileNameIsNull() throws IOException {
    // Arrange
    ByteArrayInputStream inputStream = new ByteArrayInputStream("AXAXAXAX".getBytes("UTF-8"));

    // Act
    InputStreamPart actualInputStreamPart = new InputStreamPart("https://example.org/example", inputStream, null);

    // Assert
    assertNull(actualInputStreamPart.getCharset());
    assertNull(actualInputStreamPart.getContentId());
    assertEquals(-1L, actualInputStreamPart.getContentLength());
    assertEquals("application/octet-stream", actualInputStreamPart.getContentType());
    assertNull(actualInputStreamPart.getCustomHeaders());
    assertNull(actualInputStreamPart.getDispositionType());
    assertNull(actualInputStreamPart.getFileName());
    byte[] byteArray = new byte[8];
    assertEquals(8, actualInputStreamPart.getInputStream().read(byteArray));
    assertArrayEquals("AXAXAXAX".getBytes("UTF-8"), byteArray);
    assertEquals("https://example.org/example", actualInputStreamPart.getName());
    assertNull(actualInputStreamPart.getTransferEncoding());
    assertEquals(-1, inputStream.read(new byte[]{}));
  }

  /**
   * Test {@link InputStreamPart#InputStreamPart(String, InputStream, String, long)}.
   * <ul>
   *   <li>When {@code null}.</li>
   *   <li>Then return FileName is {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link InputStreamPart#InputStreamPart(String, InputStream, String, long)}
   */
  @Test
  @DisplayName("Test new InputStreamPart(String, InputStream, String, long); when 'null'; then return FileName is 'null'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void InputStreamPart.<init>(String, InputStream, String, long)"})
  void testNewInputStreamPart_whenNull_thenReturnFileNameIsNull2() throws IOException {
    // Arrange
    ByteArrayInputStream inputStream = new ByteArrayInputStream("AXAXAXAX".getBytes("UTF-8"));

    // Act
    InputStreamPart actualInputStreamPart = new InputStreamPart("https://example.org/example", inputStream, null, 3L);

    // Assert
    assertNull(actualInputStreamPart.getCharset());
    assertNull(actualInputStreamPart.getContentId());
    assertEquals(3L, actualInputStreamPart.getContentLength());
    assertEquals("application/octet-stream", actualInputStreamPart.getContentType());
    assertNull(actualInputStreamPart.getCustomHeaders());
    assertNull(actualInputStreamPart.getDispositionType());
    assertNull(actualInputStreamPart.getFileName());
    byte[] byteArray = new byte[8];
    assertEquals(8, actualInputStreamPart.getInputStream().read(byteArray));
    assertArrayEquals("AXAXAXAX".getBytes("UTF-8"), byteArray);
    assertEquals("https://example.org/example", actualInputStreamPart.getName());
    assertNull(actualInputStreamPart.getTransferEncoding());
    assertEquals(-1, inputStream.read(new byte[]{}));
  }

  /**
   * Test getters and setters.
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link InputStreamPart#getContentLength()}
   *   <li>{@link InputStreamPart#getInputStream()}
   * </ul>
   */
  @Test
  @DisplayName("Test getters and setters")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"long InputStreamPart.getContentLength()", "InputStream InputStreamPart.getInputStream()"})
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
}
