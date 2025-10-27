package org.asynchttpclient.request.body.multipart.part;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import io.netty.buffer.AdaptiveByteBufAllocator;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.DuplicatedByteBuf;
import io.netty.buffer.EmptyByteBuf;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import org.asynchttpclient.Param;
import org.asynchttpclient.request.body.multipart.StringPart;
import org.junit.jupiter.api.Test;

class StringMultipartPartDiffblueTest {
  /**
   * Method under test: {@link StringMultipartPart#getContentLength()}
   */
  @Test
  void testGetContentLength() throws UnsupportedEncodingException {
    // Arrange
    StringPart part = new StringPart("https://example.org/example", "https://example.org/example");

    // Act and Assert
    assertEquals(27L, (new StringMultipartPart(part, "AXAXAXAX".getBytes("UTF-8"))).getContentLength());
  }

  /**
   * Method under test: {@link StringMultipartPart#getContentLength()}
   */
  @Test
  void testGetContentLength2() throws UnsupportedEncodingException {
    // Arrange
    StringPart part = new StringPart("https://example.org/example", "");

    // Act and Assert
    assertEquals(0L, (new StringMultipartPart(part, "AXAXAXAX".getBytes("UTF-8"))).getContentLength());
  }

  /**
   * Method under test: {@link StringMultipartPart#transferContentTo(ByteBuf)}
   */
  @Test
  void testTransferContentTo() throws UnsupportedEncodingException {
    // Arrange
    StringPart part = new StringPart("https://example.org/example", "https://example.org/example");

    StringMultipartPart stringMultipartPart = new StringMultipartPart(part, "AXAXAXAX".getBytes("UTF-8"));

    // Act and Assert
    assertEquals(0L,
        stringMultipartPart.transferContentTo(new DuplicatedByteBuf(new EmptyByteBuf(new AdaptiveByteBufAllocator()))));
  }

  /**
   * Method under test: {@link StringMultipartPart#transferContentTo(ByteBuf)}
   */
  @Test
  void testTransferContentTo2() throws UnsupportedEncodingException {
    // Arrange
    StringPart part = new StringPart("https://example.org/example", "");

    StringMultipartPart stringMultipartPart = new StringMultipartPart(part, "AXAXAXAX".getBytes("UTF-8"));

    // Act and Assert
    assertEquals(0L,
        stringMultipartPart.transferContentTo(new DuplicatedByteBuf(new EmptyByteBuf(new AdaptiveByteBufAllocator()))));
    assertEquals(MultipartState.POST_CONTENT, stringMultipartPart.getState());
  }

  /**
   * Method under test: {@link StringMultipartPart#close()}
   */
  @Test
  void testClose() throws UnsupportedEncodingException {
    // Arrange
    StringPart part = new StringPart("https://example.org/example", "https://example.org/example");

    StringMultipartPart stringMultipartPart = new StringMultipartPart(part, "AXAXAXAX".getBytes("UTF-8"));

    // Act
    stringMultipartPart.close();

    // Assert
    assertEquals(0L, stringMultipartPart.getContentLength());
    assertEquals(84L, stringMultipartPart.length());
  }

  /**
   * Method under test:
   * {@link StringMultipartPart#StringMultipartPart(StringPart, byte[])}
   */
  @Test
  void testNewStringMultipartPart() throws UnsupportedEncodingException {
    // Arrange
    StringPart part = new StringPart("https://example.org/example", "https://example.org/example");

    // Act
    StringMultipartPart actualStringMultipartPart = new StringMultipartPart(part, "AXAXAXAX".getBytes("UTF-8"));

    // Assert
    StringPart stringPart = actualStringMultipartPart.part;
    assertEquals("UTF-8", stringPart.getCharset().name());
    assertEquals("https://example.org/example", stringPart.getName());
    assertEquals("https://example.org/example", stringPart.getValue());
    assertNull(stringPart.getContentId());
    assertNull(stringPart.getContentType());
    assertNull(stringPart.getDispositionType());
    assertNull(stringPart.getTransferEncoding());
    assertNull(stringPart.getCustomHeaders());
    assertEquals(111L, actualStringMultipartPart.length());
    assertEquals(27L, actualStringMultipartPart.getContentLength());
    assertEquals(MultipartState.PRE_CONTENT, actualStringMultipartPart.getState());
    assertFalse(actualStringMultipartPart.isTargetSlow());
    assertArrayEquals("AXAXAXAX".getBytes("UTF-8"), actualStringMultipartPart.boundary);
  }

  /**
   * Method under test:
   * {@link StringMultipartPart#StringMultipartPart(StringPart, byte[])}
   */
  @Test
  void testNewStringMultipartPart2() throws UnsupportedEncodingException {
    // Arrange
    StringPart part = new StringPart("https://example.org/example", "");

    // Act
    StringMultipartPart actualStringMultipartPart = new StringMultipartPart(part, "AXAXAXAX".getBytes("UTF-8"));

    // Assert
    StringPart stringPart = actualStringMultipartPart.part;
    assertEquals("", stringPart.getValue());
    assertEquals("UTF-8", stringPart.getCharset().name());
    assertEquals("https://example.org/example", stringPart.getName());
    assertNull(stringPart.getContentId());
    assertNull(stringPart.getContentType());
    assertNull(stringPart.getDispositionType());
    assertNull(stringPart.getTransferEncoding());
    assertNull(stringPart.getCustomHeaders());
    assertEquals(0L, actualStringMultipartPart.getContentLength());
    assertEquals(84L, actualStringMultipartPart.length());
    assertEquals(MultipartState.PRE_CONTENT, actualStringMultipartPart.getState());
    assertFalse(actualStringMultipartPart.isTargetSlow());
    assertArrayEquals("AXAXAXAX".getBytes("UTF-8"), actualStringMultipartPart.boundary);
  }

  /**
   * Method under test:
   * {@link StringMultipartPart#StringMultipartPart(StringPart, byte[])}
   */
  @Test
  void testNewStringMultipartPart3() throws UnsupportedEncodingException {
    // Arrange
    StringPart part = new StringPart("https://example.org/example", "https://example.org/example",
        "https://example.org/example");

    // Act
    StringMultipartPart actualStringMultipartPart = new StringMultipartPart(part, "AXAXAXAX".getBytes("UTF-8"));

    // Assert
    StringPart stringPart = actualStringMultipartPart.part;
    assertEquals("UTF-8", stringPart.getCharset().name());
    assertEquals("https://example.org/example", stringPart.getContentType());
    assertEquals("https://example.org/example", stringPart.getName());
    assertEquals("https://example.org/example", stringPart.getValue());
    assertNull(stringPart.getContentId());
    assertNull(stringPart.getDispositionType());
    assertNull(stringPart.getTransferEncoding());
    assertNull(stringPart.getCustomHeaders());
    assertEquals(169L, actualStringMultipartPart.length());
    assertEquals(27L, actualStringMultipartPart.getContentLength());
    assertEquals(MultipartState.PRE_CONTENT, actualStringMultipartPart.getState());
    assertFalse(actualStringMultipartPart.isTargetSlow());
    assertArrayEquals("AXAXAXAX".getBytes("UTF-8"), actualStringMultipartPart.boundary);
  }

  /**
   * Method under test:
   * {@link StringMultipartPart#StringMultipartPart(StringPart, byte[])}
   */
  @Test
  void testNewStringMultipartPart4() throws UnsupportedEncodingException {
    // Arrange
    StringPart part = new StringPart("https://example.org/example", "https://example.org/example");
    part.addCustomHeader("https://example.org/example", "https://example.org/example");

    // Act
    StringMultipartPart actualStringMultipartPart = new StringMultipartPart(part, "AXAXAXAX".getBytes("UTF-8"));

    // Assert
    StringPart stringPart = actualStringMultipartPart.part;
    assertEquals("UTF-8", stringPart.getCharset().name());
    List<Param> customHeaders = stringPart.getCustomHeaders();
    assertEquals(1, customHeaders.size());
    Param getResult = customHeaders.get(0);
    assertEquals("https://example.org/example", getResult.getName());
    assertEquals("https://example.org/example", getResult.getValue());
    assertEquals("https://example.org/example", stringPart.getName());
    assertEquals("https://example.org/example", stringPart.getValue());
    assertNull(stringPart.getContentId());
    assertNull(stringPart.getContentType());
    assertNull(stringPart.getDispositionType());
    assertNull(stringPart.getTransferEncoding());
    assertEquals(169L, actualStringMultipartPart.length());
    assertEquals(27L, actualStringMultipartPart.getContentLength());
    assertEquals(MultipartState.PRE_CONTENT, actualStringMultipartPart.getState());
    assertFalse(actualStringMultipartPart.isTargetSlow());
    assertArrayEquals("AXAXAXAX".getBytes("UTF-8"), actualStringMultipartPart.boundary);
  }

  /**
   * Method under test:
   * {@link StringMultipartPart#StringMultipartPart(StringPart, byte[])}
   */
  @Test
  void testNewStringMultipartPart5() throws UnsupportedEncodingException {
    // Arrange
    StringPart part = new StringPart("https://example.org/example", "https://example.org/example");
    part.setDispositionType("https://example.org/example");
    part.addCustomHeader("https://example.org/example", "https://example.org/example");

    // Act
    StringMultipartPart actualStringMultipartPart = new StringMultipartPart(part, "AXAXAXAX".getBytes("UTF-8"));

    // Assert
    StringPart stringPart = actualStringMultipartPart.part;
    assertEquals("UTF-8", stringPart.getCharset().name());
    List<Param> customHeaders = stringPart.getCustomHeaders();
    assertEquals(1, customHeaders.size());
    Param getResult = customHeaders.get(0);
    assertEquals("https://example.org/example", getResult.getName());
    assertEquals("https://example.org/example", getResult.getValue());
    assertEquals("https://example.org/example", stringPart.getDispositionType());
    assertEquals("https://example.org/example", stringPart.getName());
    assertEquals("https://example.org/example", stringPart.getValue());
    assertNull(stringPart.getContentId());
    assertNull(stringPart.getContentType());
    assertNull(stringPart.getTransferEncoding());
    assertEquals(187L, actualStringMultipartPart.length());
    assertEquals(27L, actualStringMultipartPart.getContentLength());
    assertEquals(MultipartState.PRE_CONTENT, actualStringMultipartPart.getState());
    assertFalse(actualStringMultipartPart.isTargetSlow());
    assertArrayEquals("AXAXAXAX".getBytes("UTF-8"), actualStringMultipartPart.boundary);
  }

  /**
   * Method under test:
   * {@link StringMultipartPart#StringMultipartPart(StringPart, byte[])}
   */
  @Test
  void testNewStringMultipartPart6() throws UnsupportedEncodingException {
    // Arrange
    StringPart part = new StringPart("https://example.org/example", "https://example.org/example");
    ArrayList<Param> customHeaders = new ArrayList<>();
    part.setCustomHeaders(customHeaders);

    // Act
    StringMultipartPart actualStringMultipartPart = new StringMultipartPart(part, "AXAXAXAX".getBytes("UTF-8"));

    // Assert
    StringPart stringPart = actualStringMultipartPart.part;
    assertEquals("UTF-8", stringPart.getCharset().name());
    assertEquals("https://example.org/example", stringPart.getName());
    assertEquals("https://example.org/example", stringPart.getValue());
    assertNull(stringPart.getContentId());
    assertNull(stringPart.getContentType());
    assertNull(stringPart.getDispositionType());
    assertNull(stringPart.getTransferEncoding());
    assertEquals(111L, actualStringMultipartPart.length());
    assertEquals(27L, actualStringMultipartPart.getContentLength());
    assertEquals(MultipartState.PRE_CONTENT, actualStringMultipartPart.getState());
    assertFalse(actualStringMultipartPart.isTargetSlow());
    List<Param> customHeaders2 = stringPart.getCustomHeaders();
    assertTrue(customHeaders2.isEmpty());
    assertSame(customHeaders, customHeaders2);
    assertArrayEquals("AXAXAXAX".getBytes("UTF-8"), actualStringMultipartPart.boundary);
  }
}
