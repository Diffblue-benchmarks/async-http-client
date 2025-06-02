package org.asynchttpclient.request.body.multipart;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.io.UnsupportedEncodingException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

class ByteArrayPartDiffblueTest {
  /**
   * Test {@link ByteArrayPart#ByteArrayPart(String, byte[])}.
   * <p>
   * Method under test: {@link ByteArrayPart#ByteArrayPart(String, byte[])}
   */
  @Test
  @DisplayName("Test new ByteArrayPart(String, byte[])")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void ByteArrayPart.<init>(String, byte[])"})
  void testNewByteArrayPart() throws UnsupportedEncodingException {
    // Arrange and Act
    ByteArrayPart actualByteArrayPart = new ByteArrayPart("https://example.org/example", "AXAXAXAX".getBytes("UTF-8"));

    // Assert
    assertEquals("application/octet-stream", actualByteArrayPart.getContentType());
    assertEquals("https://example.org/example", actualByteArrayPart.getName());
    assertNull(actualByteArrayPart.getFileName());
    assertNull(actualByteArrayPart.getContentId());
    assertNull(actualByteArrayPart.getDispositionType());
    assertNull(actualByteArrayPart.getTransferEncoding());
    assertNull(actualByteArrayPart.getCharset());
    assertNull(actualByteArrayPart.getCustomHeaders());
    byte[] expectedBytes = "AXAXAXAX".getBytes("UTF-8");
    assertArrayEquals(expectedBytes, actualByteArrayPart.getBytes());
  }

  /**
   * Test {@link ByteArrayPart#ByteArrayPart(String, byte[], String)}.
   * <ul>
   *   <li>Then return ContentType is {@code https://example.org/example}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ByteArrayPart#ByteArrayPart(String, byte[], String)}
   */
  @Test
  @DisplayName("Test new ByteArrayPart(String, byte[], String); then return ContentType is 'https://example.org/example'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void ByteArrayPart.<init>(String, byte[], String)"})
  void testNewByteArrayPart_thenReturnContentTypeIsHttpsExampleOrgExample() throws UnsupportedEncodingException {
    // Arrange and Act
    ByteArrayPart actualByteArrayPart = new ByteArrayPart("https://example.org/example", "AXAXAXAX".getBytes("UTF-8"),
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
    byte[] expectedBytes = "AXAXAXAX".getBytes("UTF-8");
    assertArrayEquals(expectedBytes, actualByteArrayPart.getBytes());
  }

  /**
   * Test {@link ByteArrayPart#ByteArrayPart(String, byte[], String)}.
   * <ul>
   *   <li>When {@code A}.</li>
   *   <li>Then return ContentType is {@code application/octet-stream}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ByteArrayPart#ByteArrayPart(String, byte[], String)}
   */
  @Test
  @DisplayName("Test new ByteArrayPart(String, byte[], String); when 'A'; then return ContentType is 'application/octet-stream'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void ByteArrayPart.<init>(String, byte[], String)"})
  void testNewByteArrayPart_whenA_thenReturnContentTypeIsApplicationOctetStream() throws UnsupportedEncodingException {
    // Arrange and Act
    ByteArrayPart actualByteArrayPart = new ByteArrayPart("https://example.org/example",
        new byte[]{'A', 'X', 'A', 'X', 'A', 'X', 'A', 'X'}, null);

    // Assert
    assertEquals("application/octet-stream", actualByteArrayPart.getContentType());
    assertEquals("https://example.org/example", actualByteArrayPart.getName());
    assertNull(actualByteArrayPart.getFileName());
    assertNull(actualByteArrayPart.getContentId());
    assertNull(actualByteArrayPart.getDispositionType());
    assertNull(actualByteArrayPart.getTransferEncoding());
    assertNull(actualByteArrayPart.getCharset());
    assertNull(actualByteArrayPart.getCustomHeaders());
    byte[] expectedBytes = "AXAXAXAX".getBytes("UTF-8");
    assertArrayEquals(expectedBytes, actualByteArrayPart.getBytes());
  }

  /**
   * Test {@link ByteArrayPart#getBytes()}.
   * <p>
   * Method under test: {@link ByteArrayPart#getBytes()}
   */
  @Test
  @DisplayName("Test getBytes()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"byte[] ByteArrayPart.getBytes()"})
  void testGetBytes() throws UnsupportedEncodingException {
    // Arrange and Act
    byte[] actualBytes = (new ByteArrayPart("https://example.org/example", "AXAXAXAX".getBytes("UTF-8"))).getBytes();

    // Assert
    assertArrayEquals("AXAXAXAX".getBytes("UTF-8"), actualBytes);
  }
}
