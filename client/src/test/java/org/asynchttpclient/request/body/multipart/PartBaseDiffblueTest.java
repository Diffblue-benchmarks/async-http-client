package org.asynchttpclient.request.body.multipart;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import org.asynchttpclient.Param;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PartBaseDiffblueTest {
  /**
   * Test {@link PartBase#getName()}.
   * <p>
   * Method under test: {@link PartBase#getName()}
   */
  @Test
  @DisplayName("Test getName()")
  void testGetName() throws UnsupportedEncodingException {
    // Arrange, Act and Assert
    assertEquals("https://example.org/example",
        (new ByteArrayPart("https://example.org/example", "AXAXAXAX".getBytes("UTF-8"))).getName());
  }

  /**
   * Test {@link PartBase#getContentType()}.
   * <p>
   * Method under test: {@link PartBase#getContentType()}
   */
  @Test
  @DisplayName("Test getContentType()")
  void testGetContentType() throws UnsupportedEncodingException {
    // Arrange, Act and Assert
    assertEquals("application/octet-stream",
        (new ByteArrayPart("https://example.org/example", "AXAXAXAX".getBytes("UTF-8"))).getContentType());
  }

  /**
   * Test {@link PartBase#getCharset()}.
   * <p>
   * Method under test: {@link PartBase#getCharset()}
   */
  @Test
  @DisplayName("Test getCharset()")
  void testGetCharset() throws UnsupportedEncodingException {
    // Arrange, Act and Assert
    assertNull((new ByteArrayPart("https://example.org/example", "AXAXAXAX".getBytes("UTF-8"))).getCharset());
  }

  /**
   * Test {@link PartBase#getTransferEncoding()}.
   * <p>
   * Method under test: {@link PartBase#getTransferEncoding()}
   */
  @Test
  @DisplayName("Test getTransferEncoding()")
  void testGetTransferEncoding() throws UnsupportedEncodingException {
    // Arrange, Act and Assert
    assertNull((new ByteArrayPart("https://example.org/example", "AXAXAXAX".getBytes("UTF-8"))).getTransferEncoding());
  }

  /**
   * Test {@link PartBase#getContentId()}.
   * <p>
   * Method under test: {@link PartBase#getContentId()}
   */
  @Test
  @DisplayName("Test getContentId()")
  void testGetContentId() throws UnsupportedEncodingException {
    // Arrange, Act and Assert
    assertNull((new ByteArrayPart("https://example.org/example", "AXAXAXAX".getBytes("UTF-8"))).getContentId());
  }

  /**
   * Test {@link PartBase#getDispositionType()}.
   * <p>
   * Method under test: {@link PartBase#getDispositionType()}
   */
  @Test
  @DisplayName("Test getDispositionType()")
  void testGetDispositionType() throws UnsupportedEncodingException {
    // Arrange, Act and Assert
    assertNull((new ByteArrayPart("https://example.org/example", "AXAXAXAX".getBytes("UTF-8"))).getDispositionType());
  }

  /**
   * Test {@link PartBase#setDispositionType(String)}.
   * <p>
   * Method under test: {@link PartBase#setDispositionType(String)}
   */
  @Test
  @DisplayName("Test setDispositionType(String)")
  void testSetDispositionType() throws UnsupportedEncodingException {
    // Arrange
    ByteArrayPart byteArrayPart = new ByteArrayPart("https://example.org/example", "AXAXAXAX".getBytes("UTF-8"));

    // Act
    byteArrayPart.setDispositionType("https://example.org/example");

    // Assert
    assertEquals("https://example.org/example", byteArrayPart.getDispositionType());
  }

  /**
   * Test {@link PartBase#getCustomHeaders()}.
   * <p>
   * Method under test: {@link PartBase#getCustomHeaders()}
   */
  @Test
  @DisplayName("Test getCustomHeaders()")
  void testGetCustomHeaders() throws UnsupportedEncodingException {
    // Arrange, Act and Assert
    assertNull((new ByteArrayPart("https://example.org/example", "AXAXAXAX".getBytes("UTF-8"))).getCustomHeaders());
  }

  /**
   * Test {@link PartBase#setCustomHeaders(List)}.
   * <p>
   * Method under test: {@link PartBase#setCustomHeaders(List)}
   */
  @Test
  @DisplayName("Test setCustomHeaders(List)")
  void testSetCustomHeaders() throws UnsupportedEncodingException {
    // Arrange
    ByteArrayPart byteArrayPart = new ByteArrayPart("https://example.org/example", "AXAXAXAX".getBytes("UTF-8"));

    ArrayList<Param> customHeaders = new ArrayList<>();
    customHeaders.add(new Param("https://example.org/example", "https://example.org/example"));

    // Act
    byteArrayPart.setCustomHeaders(customHeaders);

    // Assert
    assertSame(customHeaders, byteArrayPart.getCustomHeaders());
  }

  /**
   * Test {@link PartBase#setCustomHeaders(List)}.
   * <p>
   * Method under test: {@link PartBase#setCustomHeaders(List)}
   */
  @Test
  @DisplayName("Test setCustomHeaders(List)")
  void testSetCustomHeaders2() throws UnsupportedEncodingException {
    // Arrange
    ByteArrayPart byteArrayPart = new ByteArrayPart("https://example.org/example", "AXAXAXAX".getBytes("UTF-8"));

    ArrayList<Param> customHeaders = new ArrayList<>();
    customHeaders.add(new Param("https://example.org/example", "https://example.org/example"));
    customHeaders.add(new Param("https://example.org/example", "https://example.org/example"));

    // Act
    byteArrayPart.setCustomHeaders(customHeaders);

    // Assert
    assertSame(customHeaders, byteArrayPart.getCustomHeaders());
  }

  /**
   * Test {@link PartBase#setCustomHeaders(List)}.
   * <ul>
   *   <li>When {@link ArrayList#ArrayList()}.</li>
   * </ul>
   * <p>
   * Method under test: {@link PartBase#setCustomHeaders(List)}
   */
  @Test
  @DisplayName("Test setCustomHeaders(List); when ArrayList()")
  void testSetCustomHeaders_whenArrayList() throws UnsupportedEncodingException {
    // Arrange
    ByteArrayPart byteArrayPart = new ByteArrayPart("https://example.org/example", "AXAXAXAX".getBytes("UTF-8"));
    ArrayList<Param> customHeaders = new ArrayList<>();

    // Act
    byteArrayPart.setCustomHeaders(customHeaders);

    // Assert
    assertSame(customHeaders, byteArrayPart.getCustomHeaders());
  }

  /**
   * Test {@link PartBase#addCustomHeader(String, String)}.
   * <p>
   * Method under test: {@link PartBase#addCustomHeader(String, String)}
   */
  @Test
  @DisplayName("Test addCustomHeader(String, String)")
  void testAddCustomHeader() throws UnsupportedEncodingException {
    // Arrange
    ByteArrayPart byteArrayPart = new ByteArrayPart("https://example.org/example", "AXAXAXAX".getBytes("UTF-8"));

    // Act
    byteArrayPart.addCustomHeader("https://example.org/example", "https://example.org/example");

    // Assert
    List<Param> customHeaders = byteArrayPart.getCustomHeaders();
    assertEquals(1, customHeaders.size());
    Param getResult = customHeaders.get(0);
    assertEquals("https://example.org/example", getResult.getName());
    assertEquals("https://example.org/example", getResult.getValue());
  }

  /**
   * Test {@link PartBase#addCustomHeader(String, String)}.
   * <p>
   * Method under test: {@link PartBase#addCustomHeader(String, String)}
   */
  @Test
  @DisplayName("Test addCustomHeader(String, String)")
  void testAddCustomHeader2() throws UnsupportedEncodingException {
    // Arrange
    ByteArrayPart byteArrayPart = new ByteArrayPart("https://example.org/example", "AXAXAXAX".getBytes("UTF-8"));
    ArrayList<Param> customHeaders = new ArrayList<>();
    byteArrayPart.setCustomHeaders(customHeaders);

    // Act
    byteArrayPart.addCustomHeader("https://example.org/example", "https://example.org/example");

    // Assert
    assertSame(customHeaders, byteArrayPart.getCustomHeaders());
  }

  /**
   * Test {@link PartBase#toString()}.
   * <p>
   * Method under test: {@link PartBase#toString()}
   */
  @Test
  @DisplayName("Test toString()")
  void testToString() {
    // Arrange, Act and Assert
    assertEquals(
        "StringPart name=https://example.org/example contentType=null charset=UTF-8 transferEncoding=null"
            + " contentId=null dispositionType=null",
        (new StringPart("https://example.org/example", "https://example.org/example")).toString());
  }
}
