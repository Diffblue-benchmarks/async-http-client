package org.asynchttpclient.request.body.multipart.part;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import io.netty.buffer.AdaptiveByteBufAllocator;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.DuplicatedByteBuf;
import io.netty.buffer.EmptyByteBuf;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.channels.WritableByteChannel;
import java.util.List;
import org.apache.tomcat.util.net.NioChannel;
import org.apache.tomcat.util.net.SocketBufferHandler;
import org.asynchttpclient.Param;
import org.asynchttpclient.request.body.multipart.ByteArrayPart;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ByteArrayMultipartPartDiffblueTest {
  /**
   * Test
   * {@link ByteArrayMultipartPart#ByteArrayMultipartPart(ByteArrayPart, byte[])}.
   * <p>
   * Method under test:
   * {@link ByteArrayMultipartPart#ByteArrayMultipartPart(ByteArrayPart, byte[])}
   */
  @Test
  @DisplayName("Test new ByteArrayMultipartPart(ByteArrayPart, byte[])")
  void testNewByteArrayMultipartPart() throws UnsupportedEncodingException {
    // Arrange
    ByteArrayPart part = new ByteArrayPart("https://example.org/example", "AXAXAXAX".getBytes("UTF-8"));
    part.setDispositionType("https://example.org/example");
    part.addCustomHeader("https://example.org/example", "https://example.org/example");

    // Act
    ByteArrayMultipartPart actualByteArrayMultipartPart = new ByteArrayMultipartPart(part,
        "AXAXAXAX".getBytes("UTF-8"));

    // Assert
    ByteArrayPart byteArrayPart = actualByteArrayMultipartPart.part;
    List<Param> customHeaders = byteArrayPart.getCustomHeaders();
    assertEquals(1, customHeaders.size());
    Param getResult = customHeaders.get(0);
    assertEquals("https://example.org/example", getResult.getName());
    assertEquals("https://example.org/example", getResult.getValue());
    assertEquals("https://example.org/example", byteArrayPart.getDispositionType());
    assertEquals(208L, actualByteArrayMultipartPart.length());
  }

  /**
   * Test
   * {@link ByteArrayMultipartPart#ByteArrayMultipartPart(ByteArrayPart, byte[])}.
   * <ul>
   *   <li>Then return length is one hundred ninety.</li>
   * </ul>
   * <p>
   * Method under test:
   * {@link ByteArrayMultipartPart#ByteArrayMultipartPart(ByteArrayPart, byte[])}
   */
  @Test
  @DisplayName("Test new ByteArrayMultipartPart(ByteArrayPart, byte[]); then return length is one hundred ninety")
  void testNewByteArrayMultipartPart_thenReturnLengthIsOneHundredNinety() throws UnsupportedEncodingException {
    // Arrange
    ByteArrayPart part = new ByteArrayPart("https://example.org/example", "AXAXAXAX".getBytes("UTF-8"));
    part.addCustomHeader("https://example.org/example", "https://example.org/example");

    // Act
    ByteArrayMultipartPart actualByteArrayMultipartPart = new ByteArrayMultipartPart(part,
        "AXAXAXAX".getBytes("UTF-8"));

    // Assert
    List<Param> customHeaders = actualByteArrayMultipartPart.part.getCustomHeaders();
    assertEquals(1, customHeaders.size());
    Param getResult = customHeaders.get(0);
    assertEquals("https://example.org/example", getResult.getName());
    assertEquals("https://example.org/example", getResult.getValue());
    assertEquals(190L, actualByteArrayMultipartPart.length());
  }

  /**
   * Test
   * {@link ByteArrayMultipartPart#ByteArrayMultipartPart(ByteArrayPart, byte[])}.
   * <ul>
   *   <li>Then return length is one hundred thirty-two.</li>
   * </ul>
   * <p>
   * Method under test:
   * {@link ByteArrayMultipartPart#ByteArrayMultipartPart(ByteArrayPart, byte[])}
   */
  @Test
  @DisplayName("Test new ByteArrayMultipartPart(ByteArrayPart, byte[]); then return length is one hundred thirty-two")
  void testNewByteArrayMultipartPart_thenReturnLengthIsOneHundredThirtyTwo() throws UnsupportedEncodingException {
    // Arrange
    ByteArrayPart part = new ByteArrayPart("https://example.org/example", "AXAXAXAX".getBytes("UTF-8"));

    // Act
    ByteArrayMultipartPart actualByteArrayMultipartPart = new ByteArrayMultipartPart(part,
        "AXAXAXAX".getBytes("UTF-8"));

    // Assert
    assertEquals(132L, actualByteArrayMultipartPart.length());
    byte[] bytes = actualByteArrayMultipartPart.part.getBytes();
    assertEquals(8, bytes.length);
    assertEquals('X', bytes[1]);
    assertEquals('X', bytes[3]);
    assertEquals('X', bytes[5]);
    assertEquals('X', bytes[7]);
  }

  /**
   * Test
   * {@link ByteArrayMultipartPart#ByteArrayMultipartPart(ByteArrayPart, byte[])}.
   * <ul>
   *   <li>Then return {@link MultipartPart#part} CustomHeaders is
   * {@code null}.</li>
   * </ul>
   * <p>
   * Method under test:
   * {@link ByteArrayMultipartPart#ByteArrayMultipartPart(ByteArrayPart, byte[])}
   */
  @Test
  @DisplayName("Test new ByteArrayMultipartPart(ByteArrayPart, byte[]); then return part CustomHeaders is 'null'")
  void testNewByteArrayMultipartPart_thenReturnPartCustomHeadersIsNull() throws UnsupportedEncodingException {
    // Arrange
    ByteArrayPart part = new ByteArrayPart("https://example.org/example", new byte[]{});

    // Act
    ByteArrayMultipartPart actualByteArrayMultipartPart = new ByteArrayMultipartPart(part,
        "AXAXAXAX".getBytes("UTF-8"));

    // Assert
    ByteArrayPart byteArrayPart = actualByteArrayMultipartPart.part;
    assertNull(byteArrayPart.getCustomHeaders());
    assertEquals(0, byteArrayPart.getBytes().length);
    assertEquals(0L, actualByteArrayMultipartPart.getContentLength());
    assertEquals(124L, actualByteArrayMultipartPart.length());
  }

  /**
   * Test {@link ByteArrayMultipartPart#getContentLength()}.
   * <p>
   * Method under test: {@link ByteArrayMultipartPart#getContentLength()}
   */
  @Test
  @DisplayName("Test getContentLength()")
  void testGetContentLength() throws UnsupportedEncodingException {
    // Arrange
    ByteArrayPart part = new ByteArrayPart("https://example.org/example", "AXAXAXAX".getBytes("UTF-8"));

    // Act and Assert
    assertEquals(8L, (new ByteArrayMultipartPart(part, "AXAXAXAX".getBytes("UTF-8"))).getContentLength());
  }

  /**
   * Test {@link ByteArrayMultipartPart#transferContentTo(ByteBuf)} with
   * {@code ByteBuf}.
   * <p>
   * Method under test: {@link ByteArrayMultipartPart#transferContentTo(ByteBuf)}
   */
  @Test
  @DisplayName("Test transferContentTo(ByteBuf) with 'ByteBuf'")
  void testTransferContentToWithByteBuf() throws UnsupportedEncodingException {
    // Arrange
    ByteArrayPart part = new ByteArrayPart("https://example.org/example", "AXAXAXAX".getBytes("UTF-8"));

    ByteArrayMultipartPart byteArrayMultipartPart = new ByteArrayMultipartPart(part, "AXAXAXAX".getBytes("UTF-8"));

    // Act and Assert
    assertEquals(0L, byteArrayMultipartPart
        .transferContentTo(new DuplicatedByteBuf(new EmptyByteBuf(new AdaptiveByteBufAllocator()))));
    assertEquals(MultipartState.PRE_CONTENT, byteArrayMultipartPart.getState());
  }

  /**
   * Test {@link ByteArrayMultipartPart#transferContentTo(ByteBuf)} with
   * {@code ByteBuf}.
   * <p>
   * Method under test: {@link ByteArrayMultipartPart#transferContentTo(ByteBuf)}
   */
  @Test
  @DisplayName("Test transferContentTo(ByteBuf) with 'ByteBuf'")
  void testTransferContentToWithByteBuf2() throws UnsupportedEncodingException {
    // Arrange
    ByteArrayPart part = new ByteArrayPart("https://example.org/example", new byte[]{});

    ByteArrayMultipartPart byteArrayMultipartPart = new ByteArrayMultipartPart(part, "AXAXAXAX".getBytes("UTF-8"));

    // Act and Assert
    assertEquals(0L, byteArrayMultipartPart
        .transferContentTo(new DuplicatedByteBuf(new EmptyByteBuf(new AdaptiveByteBufAllocator()))));
    assertEquals(MultipartState.POST_CONTENT, byteArrayMultipartPart.getState());
  }

  /**
   * Test {@link ByteArrayMultipartPart#transferContentTo(WritableByteChannel)}
   * with {@code WritableByteChannel}.
   * <ul>
   *   <li>Then return zero.</li>
   * </ul>
   * <p>
   * Method under test:
   * {@link ByteArrayMultipartPart#transferContentTo(WritableByteChannel)}
   */
  @Test
  @DisplayName("Test transferContentTo(WritableByteChannel) with 'WritableByteChannel'; then return zero")
  void testTransferContentToWithWritableByteChannel_thenReturnZero() throws IOException {
    // Arrange
    ByteArrayPart part = new ByteArrayPart("https://example.org/example", new byte[]{});

    ByteArrayMultipartPart byteArrayMultipartPart = new ByteArrayMultipartPart(part, "AXAXAXAX".getBytes("UTF-8"));

    // Act and Assert
    assertEquals(0L, byteArrayMultipartPart.transferContentTo(new NioChannel(new SocketBufferHandler(3, 3, true))));
    assertEquals(MultipartState.POST_CONTENT, byteArrayMultipartPart.getState());
  }
}
