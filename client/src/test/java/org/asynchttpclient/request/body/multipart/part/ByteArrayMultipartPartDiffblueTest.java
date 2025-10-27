package org.asynchttpclient.request.body.multipart.part;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import io.netty.buffer.AdaptiveByteBufAllocator;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.DuplicatedByteBuf;
import io.netty.buffer.EmptyByteBuf;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.channels.WritableByteChannel;
import java.util.ArrayList;
import java.util.List;
import org.apache.tomcat.util.net.NioChannel;
import org.apache.tomcat.util.net.SocketBufferHandler;
import org.asynchttpclient.Param;
import org.asynchttpclient.request.body.multipart.ByteArrayPart;
import org.junit.jupiter.api.Test;

class ByteArrayMultipartPartDiffblueTest {
  /**
   * Method under test: {@link ByteArrayMultipartPart#getContentLength()}
   */
  @Test
  void testGetContentLength() throws UnsupportedEncodingException {
    // Arrange
    ByteArrayPart part = new ByteArrayPart("https://example.org/example", "AXAXAXAX".getBytes("UTF-8"));

    // Act and Assert
    assertEquals(8L, (new ByteArrayMultipartPart(part, "AXAXAXAX".getBytes("UTF-8"))).getContentLength());
  }

  /**
   * Method under test: {@link ByteArrayMultipartPart#transferContentTo(ByteBuf)}
   */
  @Test
  void testTransferContentTo() throws UnsupportedEncodingException {
    // Arrange
    ByteArrayPart part = new ByteArrayPart("https://example.org/example", "AXAXAXAX".getBytes("UTF-8"));

    ByteArrayMultipartPart byteArrayMultipartPart = new ByteArrayMultipartPart(part, "AXAXAXAX".getBytes("UTF-8"));

    // Act and Assert
    assertEquals(0L, byteArrayMultipartPart
        .transferContentTo(new DuplicatedByteBuf(new EmptyByteBuf(new AdaptiveByteBufAllocator()))));
    assertEquals(MultipartState.PRE_CONTENT, byteArrayMultipartPart.getState());
  }

  /**
   * Method under test: {@link ByteArrayMultipartPart#transferContentTo(ByteBuf)}
   */
  @Test
  void testTransferContentTo2() throws UnsupportedEncodingException {
    // Arrange
    ByteArrayPart part = new ByteArrayPart("https://example.org/example", new byte[]{});

    ByteArrayMultipartPart byteArrayMultipartPart = new ByteArrayMultipartPart(part, "AXAXAXAX".getBytes("UTF-8"));

    // Act and Assert
    assertEquals(0L, byteArrayMultipartPart
        .transferContentTo(new DuplicatedByteBuf(new EmptyByteBuf(new AdaptiveByteBufAllocator()))));
    assertEquals(MultipartState.POST_CONTENT, byteArrayMultipartPart.getState());
  }

  /**
   * Method under test:
   * {@link ByteArrayMultipartPart#transferContentTo(WritableByteChannel)}
   */
  @Test
  void testTransferContentTo3() throws IOException {
    // Arrange
    ByteArrayPart part = new ByteArrayPart("https://example.org/example", new byte[]{});

    ByteArrayMultipartPart byteArrayMultipartPart = new ByteArrayMultipartPart(part, "AXAXAXAX".getBytes("UTF-8"));

    // Act and Assert
    assertEquals(0L, byteArrayMultipartPart.transferContentTo(new NioChannel(new SocketBufferHandler(3, 3, true))));
    assertEquals(MultipartState.POST_CONTENT, byteArrayMultipartPart.getState());
  }

  /**
   * Method under test: {@link ByteArrayMultipartPart#close()}
   */
  @Test
  void testClose() throws UnsupportedEncodingException {
    // Arrange
    ByteArrayPart part = mock(ByteArrayPart.class);
    when(part.getCharset()).thenReturn(null);
    when(part.getBytes()).thenReturn("AXAXAXAX".getBytes("UTF-8"));
    when(part.getFileName()).thenReturn("https://example.org/example");
    when(part.getContentId()).thenReturn("https://example.org/example");
    when(part.getContentType()).thenReturn("text/plain");
    when(part.getDispositionType()).thenReturn("https://example.org/example");
    when(part.getName()).thenReturn("https://example.org/example");
    when(part.getTransferEncoding()).thenReturn("https://example.org/example");
    when(part.getCustomHeaders()).thenReturn(new ArrayList<>());

    // Act
    (new ByteArrayMultipartPart(part, "AXAXAXAX".getBytes("UTF-8"))).close();

    // Assert
    verify(part).getBytes();
    verify(part, atLeast(1)).getFileName();
    verify(part, atLeast(1)).getCharset();
    verify(part).getContentId();
    verify(part).getContentType();
    verify(part).getCustomHeaders();
    verify(part, atLeast(1)).getDispositionType();
    verify(part, atLeast(1)).getName();
    verify(part).getTransferEncoding();
  }

  /**
   * Method under test:
   * {@link ByteArrayMultipartPart#ByteArrayMultipartPart(ByteArrayPart, byte[])}
   */
  @Test
  void testNewByteArrayMultipartPart() throws UnsupportedEncodingException {
    // Arrange
    ByteArrayPart part = new ByteArrayPart("https://example.org/example", "AXAXAXAX".getBytes("UTF-8"));

    // Act
    ByteArrayMultipartPart actualByteArrayMultipartPart = new ByteArrayMultipartPart(part,
        "AXAXAXAX".getBytes("UTF-8"));

    // Assert
    ByteArrayPart byteArrayPart = actualByteArrayMultipartPart.part;
    assertEquals("application/octet-stream", byteArrayPart.getContentType());
    assertEquals("https://example.org/example", byteArrayPart.getName());
    assertNull(byteArrayPart.getFileName());
    assertNull(byteArrayPart.getContentId());
    assertNull(byteArrayPart.getDispositionType());
    assertNull(byteArrayPart.getTransferEncoding());
    assertNull(byteArrayPart.getCharset());
    assertNull(byteArrayPart.getCustomHeaders());
    assertEquals(132L, actualByteArrayMultipartPart.length());
    assertEquals(8L, actualByteArrayMultipartPart.getContentLength());
    assertEquals(MultipartState.PRE_CONTENT, actualByteArrayMultipartPart.getState());
    assertFalse(actualByteArrayMultipartPart.isTargetSlow());
    byte[] expectedBytes = "AXAXAXAX".getBytes("UTF-8");
    assertArrayEquals(expectedBytes, byteArrayPart.getBytes());
    assertArrayEquals("AXAXAXAX".getBytes("UTF-8"), actualByteArrayMultipartPart.boundary);
  }

  /**
   * Method under test:
   * {@link ByteArrayMultipartPart#ByteArrayMultipartPart(ByteArrayPart, byte[])}
   */
  @Test
  void testNewByteArrayMultipartPart2() throws UnsupportedEncodingException {
    // Arrange
    ByteArrayPart part = new ByteArrayPart("https://example.org/example", new byte[]{});

    // Act
    ByteArrayMultipartPart actualByteArrayMultipartPart = new ByteArrayMultipartPart(part,
        "AXAXAXAX".getBytes("UTF-8"));

    // Assert
    ByteArrayPart byteArrayPart = actualByteArrayMultipartPart.part;
    assertEquals("application/octet-stream", byteArrayPart.getContentType());
    assertEquals("https://example.org/example", byteArrayPart.getName());
    assertNull(byteArrayPart.getFileName());
    assertNull(byteArrayPart.getContentId());
    assertNull(byteArrayPart.getDispositionType());
    assertNull(byteArrayPart.getTransferEncoding());
    assertNull(byteArrayPart.getCharset());
    assertNull(byteArrayPart.getCustomHeaders());
    assertEquals(0, byteArrayPart.getBytes().length);
    assertEquals(0L, actualByteArrayMultipartPart.getContentLength());
    assertEquals(124L, actualByteArrayMultipartPart.length());
    assertEquals(MultipartState.PRE_CONTENT, actualByteArrayMultipartPart.getState());
    assertFalse(actualByteArrayMultipartPart.isTargetSlow());
    assertArrayEquals("AXAXAXAX".getBytes("UTF-8"), actualByteArrayMultipartPart.boundary);
  }

  /**
   * Method under test:
   * {@link ByteArrayMultipartPart#ByteArrayMultipartPart(ByteArrayPart, byte[])}
   */
  @Test
  void testNewByteArrayMultipartPart3() throws UnsupportedEncodingException {
    // Arrange
    ByteArrayPart part = new ByteArrayPart("https://example.org/example", "AXAXAXAX".getBytes("UTF-8"));
    part.addCustomHeader("https://example.org/example", "https://example.org/example");

    // Act
    ByteArrayMultipartPart actualByteArrayMultipartPart = new ByteArrayMultipartPart(part,
        "AXAXAXAX".getBytes("UTF-8"));

    // Assert
    ByteArrayPart byteArrayPart = actualByteArrayMultipartPart.part;
    assertEquals("application/octet-stream", byteArrayPart.getContentType());
    List<Param> customHeaders = byteArrayPart.getCustomHeaders();
    assertEquals(1, customHeaders.size());
    Param getResult = customHeaders.get(0);
    assertEquals("https://example.org/example", getResult.getName());
    assertEquals("https://example.org/example", getResult.getValue());
    assertEquals("https://example.org/example", byteArrayPart.getName());
    assertNull(byteArrayPart.getFileName());
    assertNull(byteArrayPart.getContentId());
    assertNull(byteArrayPart.getDispositionType());
    assertNull(byteArrayPart.getTransferEncoding());
    assertNull(byteArrayPart.getCharset());
    assertEquals(190L, actualByteArrayMultipartPart.length());
    assertEquals(8L, actualByteArrayMultipartPart.getContentLength());
    assertEquals(MultipartState.PRE_CONTENT, actualByteArrayMultipartPart.getState());
    assertFalse(actualByteArrayMultipartPart.isTargetSlow());
    byte[] expectedBytes = "AXAXAXAX".getBytes("UTF-8");
    assertArrayEquals(expectedBytes, byteArrayPart.getBytes());
    assertArrayEquals("AXAXAXAX".getBytes("UTF-8"), actualByteArrayMultipartPart.boundary);
  }

  /**
   * Method under test:
   * {@link ByteArrayMultipartPart#ByteArrayMultipartPart(ByteArrayPart, byte[])}
   */
  @Test
  void testNewByteArrayMultipartPart4() throws UnsupportedEncodingException {
    // Arrange
    ByteArrayPart part = new ByteArrayPart("https://example.org/example", "AXAXAXAX".getBytes("UTF-8"));
    part.setDispositionType("https://example.org/example");
    part.addCustomHeader("https://example.org/example", "https://example.org/example");

    // Act
    ByteArrayMultipartPart actualByteArrayMultipartPart = new ByteArrayMultipartPart(part,
        "AXAXAXAX".getBytes("UTF-8"));

    // Assert
    ByteArrayPart byteArrayPart = actualByteArrayMultipartPart.part;
    assertEquals("application/octet-stream", byteArrayPart.getContentType());
    List<Param> customHeaders = byteArrayPart.getCustomHeaders();
    assertEquals(1, customHeaders.size());
    Param getResult = customHeaders.get(0);
    assertEquals("https://example.org/example", getResult.getName());
    assertEquals("https://example.org/example", getResult.getValue());
    assertEquals("https://example.org/example", byteArrayPart.getDispositionType());
    assertEquals("https://example.org/example", byteArrayPart.getName());
    assertNull(byteArrayPart.getFileName());
    assertNull(byteArrayPart.getContentId());
    assertNull(byteArrayPart.getTransferEncoding());
    assertNull(byteArrayPart.getCharset());
    assertEquals(208L, actualByteArrayMultipartPart.length());
    assertEquals(8L, actualByteArrayMultipartPart.getContentLength());
    assertEquals(MultipartState.PRE_CONTENT, actualByteArrayMultipartPart.getState());
    assertFalse(actualByteArrayMultipartPart.isTargetSlow());
    byte[] expectedBytes = "AXAXAXAX".getBytes("UTF-8");
    assertArrayEquals(expectedBytes, byteArrayPart.getBytes());
    assertArrayEquals("AXAXAXAX".getBytes("UTF-8"), actualByteArrayMultipartPart.boundary);
  }
}
