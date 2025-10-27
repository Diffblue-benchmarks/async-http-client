package org.asynchttpclient.request.body.multipart;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import org.asynchttpclient.Param;
import org.junit.jupiter.api.Test;

class PartBaseDiffblueTest {
  /**
   * Method under test: {@link PartBase#getName()}
   */
  @Test
  void testGetName() throws UnsupportedEncodingException {
    // Arrange, Act and Assert
    assertEquals("https://example.org/example",
        (new ByteArrayPart("https://example.org/example", "AXAXAXAX".getBytes("UTF-8"))).getName());
  }

  /**
   * Method under test: {@link PartBase#getContentType()}
   */
  @Test
  void testGetContentType() throws UnsupportedEncodingException {
    // Arrange, Act and Assert
    assertEquals("application/octet-stream",
        (new ByteArrayPart("https://example.org/example", "AXAXAXAX".getBytes("UTF-8"))).getContentType());
  }

  /**
   * Method under test: {@link PartBase#getCharset()}
   */
  @Test
  void testGetCharset() throws UnsupportedEncodingException {
    // Arrange, Act and Assert
    assertNull((new ByteArrayPart("https://example.org/example", "AXAXAXAX".getBytes("UTF-8"))).getCharset());
  }

  /**
   * Method under test: {@link PartBase#getTransferEncoding()}
   */
  @Test
  void testGetTransferEncoding() throws UnsupportedEncodingException {
    // Arrange, Act and Assert
    assertNull((new ByteArrayPart("https://example.org/example", "AXAXAXAX".getBytes("UTF-8"))).getTransferEncoding());
  }

  /**
   * Method under test: {@link PartBase#getContentId()}
   */
  @Test
  void testGetContentId() throws UnsupportedEncodingException {
    // Arrange, Act and Assert
    assertNull((new ByteArrayPart("https://example.org/example", "AXAXAXAX".getBytes("UTF-8"))).getContentId());
  }

  /**
   * Method under test: {@link PartBase#getDispositionType()}
   */
  @Test
  void testGetDispositionType() throws UnsupportedEncodingException {
    // Arrange, Act and Assert
    assertNull((new ByteArrayPart("https://example.org/example", "AXAXAXAX".getBytes("UTF-8"))).getDispositionType());
  }

  /**
   * Method under test: {@link PartBase#setDispositionType(String)}
   */
  @Test
  void testSetDispositionType() throws UnsupportedEncodingException {
    // Arrange
    ByteArrayPart byteArrayPart = new ByteArrayPart("https://example.org/example", "AXAXAXAX".getBytes("UTF-8"));

    // Act
    byteArrayPart.setDispositionType("https://example.org/example");

    // Assert
    assertEquals("https://example.org/example", byteArrayPart.getDispositionType());
  }

  /**
   * Method under test: {@link PartBase#getCustomHeaders()}
   */
  @Test
  void testGetCustomHeaders() throws UnsupportedEncodingException {
    // Arrange, Act and Assert
    assertNull((new ByteArrayPart("https://example.org/example", "AXAXAXAX".getBytes("UTF-8"))).getCustomHeaders());
  }

  /**
   * Method under test: {@link PartBase#setCustomHeaders(List)}
   */
  @Test
  void testSetCustomHeaders() throws UnsupportedEncodingException {
    // Arrange
    ByteArrayPart byteArrayPart = new ByteArrayPart("https://example.org/example", "AXAXAXAX".getBytes("UTF-8"));
    ArrayList<Param> customHeaders = new ArrayList<>();

    // Act
    byteArrayPart.setCustomHeaders(customHeaders);

    // Assert
    assertSame(customHeaders, byteArrayPart.getCustomHeaders());
  }

  /**
   * Method under test: {@link PartBase#setCustomHeaders(List)}
   */
  @Test
  void testSetCustomHeaders2() throws UnsupportedEncodingException {
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
   * Method under test: {@link PartBase#setCustomHeaders(List)}
   */
  @Test
  void testSetCustomHeaders3() throws UnsupportedEncodingException {
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
   * Method under test: {@link PartBase#addCustomHeader(String, String)}
   */
  @Test
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
   * Method under test: {@link PartBase#addCustomHeader(String, String)}
   */
  @Test
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
   * Method under test: {@link PartBase#toString()}
   */
  @Test
  void testToString() {
    // Arrange, Act and Assert
    assertEquals(
        "StringPart name=https://example.org/example contentType=null charset=UTF-8 transferEncoding=null"
            + " contentId=null dispositionType=null",
        (new StringPart("https://example.org/example", "https://example.org/example")).toString());
  }
}
