package org.asynchttpclient.request.body.multipart.part;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import io.netty.buffer.AdaptiveByteBufAllocator;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.DuplicatedByteBuf;
import io.netty.buffer.EmptyByteBuf;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.channels.WritableByteChannel;
import java.util.ArrayList;
import java.util.List;
import org.asynchttpclient.Param;
import org.asynchttpclient.request.body.multipart.InputStreamPart;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class InputStreamMultipartPartDiffblueTest {
  /**
   * Test
   * {@link InputStreamMultipartPart#InputStreamMultipartPart(InputStreamPart, byte[])}.
   * <p>
   * Method under test:
   * {@link InputStreamMultipartPart#InputStreamMultipartPart(InputStreamPart, byte[])}
   */
  @Test
  @DisplayName("Test new InputStreamMultipartPart(InputStreamPart, byte[])")
  void testNewInputStreamMultipartPart() throws UnsupportedEncodingException {
    // Arrange
    InputStreamPart part = new InputStreamPart("https://example.org/example",
        new ByteArrayInputStream("AXAXAXAX".getBytes("UTF-8")), "https://example.org/example");
    part.setDispositionType("https://example.org/example");
    part.addCustomHeader("https://example.org/example", "https://example.org/example");

    // Act and Assert
    InputStreamPart inputStreamPart = (new InputStreamMultipartPart(part, "AXAXAXAX".getBytes("UTF-8"))).part;
    List<Param> customHeaders = inputStreamPart.getCustomHeaders();
    assertEquals(1, customHeaders.size());
    Param getResult = customHeaders.get(0);
    assertEquals("https://example.org/example", getResult.getName());
    assertEquals("https://example.org/example", getResult.getValue());
    assertEquals("https://example.org/example", inputStreamPart.getDispositionType());
  }

  /**
   * Test
   * {@link InputStreamMultipartPart#InputStreamMultipartPart(InputStreamPart, byte[])}.
   * <ul>
   *   <li>Given {@link ArrayList#ArrayList()}.</li>
   *   <li>Then return {@link MultipartPart#part} CustomHeaders Empty.</li>
   * </ul>
   * <p>
   * Method under test:
   * {@link InputStreamMultipartPart#InputStreamMultipartPart(InputStreamPart, byte[])}
   */
  @Test
  @DisplayName("Test new InputStreamMultipartPart(InputStreamPart, byte[]); given ArrayList(); then return part CustomHeaders Empty")
  void testNewInputStreamMultipartPart_givenArrayList_thenReturnPartCustomHeadersEmpty() throws IOException {
    // Arrange
    InputStreamPart part = new InputStreamPart("https://example.org/example",
        new ByteArrayInputStream("AXAXAXAX".getBytes("UTF-8")), "https://example.org/example");
    part.setCustomHeaders(new ArrayList<>());

    // Act
    InputStreamMultipartPart actualInputStreamMultipartPart = new InputStreamMultipartPart(part,
        "AXAXAXAX".getBytes("UTF-8"));

    // Assert
    InputStreamPart inputStreamPart = actualInputStreamMultipartPart.part;
    assertEquals("application/octet-stream", inputStreamPart.getContentType());
    assertEquals("https://example.org/example", inputStreamPart.getFileName());
    assertEquals("https://example.org/example", inputStreamPart.getName());
    assertNull(inputStreamPart.getContentId());
    assertNull(inputStreamPart.getTransferEncoding());
    assertNull(inputStreamPart.getCharset());
    assertEquals(-1L, inputStreamPart.getContentLength());
    assertEquals(-1L, actualInputStreamMultipartPart.getContentLength());
    assertEquals(-1L, actualInputStreamMultipartPart.length());
    byte[] byteArray = new byte[8];
    assertEquals(8, inputStreamPart.getInputStream().read(byteArray));
    assertEquals(MultipartState.PRE_CONTENT, actualInputStreamMultipartPart.getState());
    assertFalse(actualInputStreamMultipartPart.isTargetSlow());
    assertTrue(inputStreamPart.getCustomHeaders().isEmpty());
    assertArrayEquals("AXAXAXAX".getBytes("UTF-8"), byteArray);
    assertArrayEquals("AXAXAXAX".getBytes("UTF-8"), actualInputStreamMultipartPart.boundary);
  }

  /**
   * Test
   * {@link InputStreamMultipartPart#InputStreamMultipartPart(InputStreamPart, byte[])}.
   * <ul>
   *   <li>Then return {@link MultipartPart#part} CustomHeaders is
   * {@code null}.</li>
   * </ul>
   * <p>
   * Method under test:
   * {@link InputStreamMultipartPart#InputStreamMultipartPart(InputStreamPart, byte[])}
   */
  @Test
  @DisplayName("Test new InputStreamMultipartPart(InputStreamPart, byte[]); then return part CustomHeaders is 'null'")
  void testNewInputStreamMultipartPart_thenReturnPartCustomHeadersIsNull() throws IOException {
    // Arrange
    InputStreamPart part = new InputStreamPart("https://example.org/example",
        new ByteArrayInputStream("AXAXAXAX".getBytes("UTF-8")), "https://example.org/example");

    // Act
    InputStreamMultipartPart actualInputStreamMultipartPart = new InputStreamMultipartPart(part,
        "AXAXAXAX".getBytes("UTF-8"));

    // Assert
    InputStreamPart inputStreamPart = actualInputStreamMultipartPart.part;
    assertEquals("application/octet-stream", inputStreamPart.getContentType());
    assertEquals("https://example.org/example", inputStreamPart.getFileName());
    assertEquals("https://example.org/example", inputStreamPart.getName());
    assertNull(inputStreamPart.getContentId());
    assertNull(inputStreamPart.getTransferEncoding());
    assertNull(inputStreamPart.getCharset());
    assertNull(inputStreamPart.getCustomHeaders());
    assertEquals(-1L, inputStreamPart.getContentLength());
    assertEquals(-1L, actualInputStreamMultipartPart.getContentLength());
    assertEquals(-1L, actualInputStreamMultipartPart.length());
    byte[] byteArray = new byte[8];
    assertEquals(8, inputStreamPart.getInputStream().read(byteArray));
    assertEquals(MultipartState.PRE_CONTENT, actualInputStreamMultipartPart.getState());
    assertFalse(actualInputStreamMultipartPart.isTargetSlow());
    assertArrayEquals("AXAXAXAX".getBytes("UTF-8"), byteArray);
    assertArrayEquals("AXAXAXAX".getBytes("UTF-8"), actualInputStreamMultipartPart.boundary);
  }

  /**
   * Test
   * {@link InputStreamMultipartPart#InputStreamMultipartPart(InputStreamPart, byte[])}.
   * <ul>
   *   <li>Then return {@link MultipartPart#part} DispositionType is
   * {@code null}.</li>
   * </ul>
   * <p>
   * Method under test:
   * {@link InputStreamMultipartPart#InputStreamMultipartPart(InputStreamPart, byte[])}
   */
  @Test
  @DisplayName("Test new InputStreamMultipartPart(InputStreamPart, byte[]); then return part DispositionType is 'null'")
  void testNewInputStreamMultipartPart_thenReturnPartDispositionTypeIsNull() throws UnsupportedEncodingException {
    // Arrange
    InputStreamPart part = new InputStreamPart("https://example.org/example",
        new ByteArrayInputStream("AXAXAXAX".getBytes("UTF-8")), "https://example.org/example");
    part.addCustomHeader("https://example.org/example", "https://example.org/example");

    // Act and Assert
    InputStreamPart inputStreamPart = (new InputStreamMultipartPart(part, "AXAXAXAX".getBytes("UTF-8"))).part;
    List<Param> customHeaders = inputStreamPart.getCustomHeaders();
    assertEquals(1, customHeaders.size());
    Param getResult = customHeaders.get(0);
    assertEquals("https://example.org/example", getResult.getName());
    assertEquals("https://example.org/example", getResult.getValue());
    assertNull(inputStreamPart.getDispositionType());
  }

  /**
   * Test {@link InputStreamMultipartPart#getContentLength()}.
   * <p>
   * Method under test: {@link InputStreamMultipartPart#getContentLength()}
   */
  @Test
  @DisplayName("Test getContentLength()")
  void testGetContentLength() throws UnsupportedEncodingException {
    // Arrange
    InputStreamPart part = new InputStreamPart("https://example.org/example",
        new ByteArrayInputStream("AXAXAXAX".getBytes("UTF-8")), "https://example.org/example");

    // Act and Assert
    assertEquals(-1L, (new InputStreamMultipartPart(part, "AXAXAXAX".getBytes("UTF-8"))).getContentLength());
  }

  /**
   * Test {@link InputStreamMultipartPart#transferContentTo(ByteBuf)} with
   * {@code ByteBuf}.
   * <ul>
   *   <li>Then return zero.</li>
   * </ul>
   * <p>
   * Method under test:
   * {@link InputStreamMultipartPart#transferContentTo(ByteBuf)}
   */
  @Test
  @DisplayName("Test transferContentTo(ByteBuf) with 'ByteBuf'; then return zero")
  void testTransferContentToWithByteBuf_thenReturnZero() throws IOException {
    // Arrange
    InputStreamPart part = new InputStreamPart("https://example.org/example",
        new ByteArrayInputStream("AXAXAXAX".getBytes("UTF-8")), "https://example.org/example");

    InputStreamMultipartPart inputStreamMultipartPart = new InputStreamMultipartPart(part,
        "AXAXAXAX".getBytes("UTF-8"));

    // Act and Assert
    assertEquals(0L, inputStreamMultipartPart.transferContentTo(new EmptyByteBuf(new AdaptiveByteBufAllocator())));
  }

  /**
   * Test {@link InputStreamMultipartPart#transferContentTo(ByteBuf)} with
   * {@code ByteBuf}.
   * <ul>
   *   <li>When {@link DuplicatedByteBuf#DuplicatedByteBuf(ByteBuf)} with buffer is
   * {@link EmptyByteBuf#EmptyByteBuf(ByteBufAllocator)}.</li>
   * </ul>
   * <p>
   * Method under test:
   * {@link InputStreamMultipartPart#transferContentTo(ByteBuf)}
   */
  @Test
  @DisplayName("Test transferContentTo(ByteBuf) with 'ByteBuf'; when DuplicatedByteBuf(ByteBuf) with buffer is EmptyByteBuf(ByteBufAllocator)")
  void testTransferContentToWithByteBuf_whenDuplicatedByteBufWithBufferIsEmptyByteBuf() throws IOException {
    // Arrange
    InputStreamPart part = new InputStreamPart("https://example.org/example",
        new ByteArrayInputStream("AXAXAXAX".getBytes("UTF-8")), "https://example.org/example");

    InputStreamMultipartPart inputStreamMultipartPart = new InputStreamMultipartPart(part,
        "AXAXAXAX".getBytes("UTF-8"));

    // Act and Assert
    assertEquals(0L, inputStreamMultipartPart
        .transferContentTo(new DuplicatedByteBuf(new EmptyByteBuf(new AdaptiveByteBufAllocator()))));
  }

  /**
   * Test {@link InputStreamMultipartPart#transferContentTo(WritableByteChannel)}
   * with {@code WritableByteChannel}.
   * <ul>
   *   <li>Then return zero.</li>
   * </ul>
   * <p>
   * Method under test:
   * {@link InputStreamMultipartPart#transferContentTo(WritableByteChannel)}
   */
  @Test
  @DisplayName("Test transferContentTo(WritableByteChannel) with 'WritableByteChannel'; then return zero")
  void testTransferContentToWithWritableByteChannel_thenReturnZero() throws IOException {
    // Arrange
    InputStreamPart part = new InputStreamPart("https://example.org/example", new ByteArrayInputStream(new byte[]{}),
        "https://example.org/example");

    InputStreamMultipartPart inputStreamMultipartPart = new InputStreamMultipartPart(part,
        "AXAXAXAX".getBytes("UTF-8"));

    // Act and Assert
    assertEquals(0L, inputStreamMultipartPart.transferContentTo((WritableByteChannel) null));
    assertEquals(MultipartState.POST_CONTENT, inputStreamMultipartPart.getState());
  }
}
