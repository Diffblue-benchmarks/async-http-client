package org.asynchttpclient.request.body.multipart.part;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.aayushatharva.brotli4j.encoder.BrotliEncoderChannel;
import io.netty.buffer.AdaptiveByteBufAllocator;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.DuplicatedByteBuf;
import io.netty.buffer.EmptyByteBuf;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.nio.channels.WritableByteChannel;
import java.util.ArrayList;
import java.util.List;
import org.asynchttpclient.Param;
import org.asynchttpclient.request.body.multipart.StringPart;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

class StringMultipartPartDiffblueTest {
  /**
   * Test {@link StringMultipartPart#StringMultipartPart(StringPart, byte[])}.
   * <p>
   * Method under test:
   * {@link StringMultipartPart#StringMultipartPart(StringPart, byte[])}
   */
  @Test
  @DisplayName("Test new StringMultipartPart(StringPart, byte[])")
  void testNewStringMultipartPart() throws UnsupportedEncodingException {
    // Arrange
    StringPart part = new StringPart("https://example.org/example", "https://example.org/example");

    // Act
    StringMultipartPart actualStringMultipartPart = new StringMultipartPart(part, "AXAXAXAX".getBytes("UTF-8"));

    // Assert
    StringPart stringPart = actualStringMultipartPart.part;
    assertEquals("https://example.org/example", stringPart.getValue());
    assertNull(stringPart.getContentType());
    assertNull(stringPart.getDispositionType());
    assertNull(stringPart.getCustomHeaders());
    assertEquals(111L, actualStringMultipartPart.length());
    assertEquals(27L, actualStringMultipartPart.getContentLength());
  }

  /**
   * Test {@link StringMultipartPart#StringMultipartPart(StringPart, byte[])}.
   * <ul>
   *   <li>Given {@link ArrayList#ArrayList()}.</li>
   *   <li>Then return {@link MultipartPart#part} CustomHeaders Empty.</li>
   * </ul>
   * <p>
   * Method under test:
   * {@link StringMultipartPart#StringMultipartPart(StringPart, byte[])}
   */
  @Test
  @DisplayName("Test new StringMultipartPart(StringPart, byte[]); given ArrayList(); then return part CustomHeaders Empty")
  void testNewStringMultipartPart_givenArrayList_thenReturnPartCustomHeadersEmpty()
      throws UnsupportedEncodingException {
    // Arrange
    StringPart part = new StringPart("https://example.org/example", "https://example.org/example");
    part.setCustomHeaders(new ArrayList<>());

    // Act
    StringMultipartPart actualStringMultipartPart = new StringMultipartPart(part, "AXAXAXAX".getBytes("UTF-8"));

    // Assert
    StringPart stringPart = actualStringMultipartPart.part;
    assertEquals("https://example.org/example", stringPart.getValue());
    assertNull(stringPart.getContentType());
    assertNull(stringPart.getDispositionType());
    assertEquals(111L, actualStringMultipartPart.length());
    assertEquals(27L, actualStringMultipartPart.getContentLength());
    assertTrue(stringPart.getCustomHeaders().isEmpty());
  }

  /**
   * Test {@link StringMultipartPart#StringMultipartPart(StringPart, byte[])}.
   * <ul>
   *   <li>Then return {@link MultipartPart#part} ContentType is
   * {@code https://example.org/example}.</li>
   * </ul>
   * <p>
   * Method under test:
   * {@link StringMultipartPart#StringMultipartPart(StringPart, byte[])}
   */
  @Test
  @DisplayName("Test new StringMultipartPart(StringPart, byte[]); then return part ContentType is 'https://example.org/example'")
  void testNewStringMultipartPart_thenReturnPartContentTypeIsHttpsExampleOrgExample()
      throws UnsupportedEncodingException {
    // Arrange
    StringPart part = new StringPart("https://example.org/example", "https://example.org/example",
        "https://example.org/example");

    // Act
    StringMultipartPart actualStringMultipartPart = new StringMultipartPart(part, "AXAXAXAX".getBytes("UTF-8"));

    // Assert
    StringPart stringPart = actualStringMultipartPart.part;
    assertEquals("https://example.org/example", stringPart.getContentType());
    assertEquals("https://example.org/example", stringPart.getValue());
    assertNull(stringPart.getDispositionType());
    assertNull(stringPart.getCustomHeaders());
    assertEquals(169L, actualStringMultipartPart.length());
    assertEquals(27L, actualStringMultipartPart.getContentLength());
  }

  /**
   * Test {@link StringMultipartPart#StringMultipartPart(StringPart, byte[])}.
   * <ul>
   *   <li>Then return {@link MultipartPart#part} CustomHeaders size is one.</li>
   * </ul>
   * <p>
   * Method under test:
   * {@link StringMultipartPart#StringMultipartPart(StringPart, byte[])}
   */
  @Test
  @DisplayName("Test new StringMultipartPart(StringPart, byte[]); then return part CustomHeaders size is one")
  void testNewStringMultipartPart_thenReturnPartCustomHeadersSizeIsOne() throws UnsupportedEncodingException {
    // Arrange
    StringPart part = new StringPart("https://example.org/example", "https://example.org/example");
    part.addCustomHeader("https://example.org/example", "https://example.org/example");

    // Act
    StringMultipartPart actualStringMultipartPart = new StringMultipartPart(part, "AXAXAXAX".getBytes("UTF-8"));

    // Assert
    List<Param> customHeaders = actualStringMultipartPart.part.getCustomHeaders();
    assertEquals(1, customHeaders.size());
    Param getResult = customHeaders.get(0);
    assertEquals("https://example.org/example", getResult.getName());
    assertEquals("https://example.org/example", getResult.getValue());
    assertEquals(169L, actualStringMultipartPart.length());
  }

  /**
   * Test {@link StringMultipartPart#StringMultipartPart(StringPart, byte[])}.
   * <ul>
   *   <li>Then return {@link MultipartPart#part} DispositionType is
   * {@code https://example.org/example}.</li>
   * </ul>
   * <p>
   * Method under test:
   * {@link StringMultipartPart#StringMultipartPart(StringPart, byte[])}
   */
  @Test
  @DisplayName("Test new StringMultipartPart(StringPart, byte[]); then return part DispositionType is 'https://example.org/example'")
  void testNewStringMultipartPart_thenReturnPartDispositionTypeIsHttpsExampleOrgExample()
      throws UnsupportedEncodingException {
    // Arrange
    StringPart part = new StringPart("https://example.org/example", "https://example.org/example");
    part.setDispositionType("https://example.org/example");
    part.addCustomHeader("https://example.org/example", "https://example.org/example");

    // Act
    StringMultipartPart actualStringMultipartPart = new StringMultipartPart(part, "AXAXAXAX".getBytes("UTF-8"));

    // Assert
    StringPart stringPart = actualStringMultipartPart.part;
    List<Param> customHeaders = stringPart.getCustomHeaders();
    assertEquals(1, customHeaders.size());
    Param getResult = customHeaders.get(0);
    assertEquals("https://example.org/example", getResult.getName());
    assertEquals("https://example.org/example", getResult.getValue());
    assertEquals("https://example.org/example", stringPart.getDispositionType());
    assertEquals(187L, actualStringMultipartPart.length());
  }

  /**
   * Test {@link StringMultipartPart#StringMultipartPart(StringPart, byte[])}.
   * <ul>
   *   <li>Then return {@link MultipartPart#part} Value is empty string.</li>
   * </ul>
   * <p>
   * Method under test:
   * {@link StringMultipartPart#StringMultipartPart(StringPart, byte[])}
   */
  @Test
  @DisplayName("Test new StringMultipartPart(StringPart, byte[]); then return part Value is empty string")
  void testNewStringMultipartPart_thenReturnPartValueIsEmptyString() throws UnsupportedEncodingException {
    // Arrange
    StringPart part = new StringPart("https://example.org/example", "");

    // Act
    StringMultipartPart actualStringMultipartPart = new StringMultipartPart(part, "AXAXAXAX".getBytes("UTF-8"));

    // Assert
    StringPart stringPart = actualStringMultipartPart.part;
    assertEquals("", stringPart.getValue());
    assertNull(stringPart.getCustomHeaders());
    assertEquals(0L, actualStringMultipartPart.getContentLength());
    assertEquals(84L, actualStringMultipartPart.length());
  }

  /**
   * Test {@link StringMultipartPart#getContentLength()}.
   * <ul>
   *   <li>Then return twenty-seven.</li>
   * </ul>
   * <p>
   * Method under test: {@link StringMultipartPart#getContentLength()}
   */
  @Test
  @DisplayName("Test getContentLength(); then return twenty-seven")
  void testGetContentLength_thenReturnTwentySeven() throws UnsupportedEncodingException {
    // Arrange
    StringPart part = new StringPart("https://example.org/example", "https://example.org/example");

    // Act and Assert
    assertEquals(27L, (new StringMultipartPart(part, "AXAXAXAX".getBytes("UTF-8"))).getContentLength());
  }

  /**
   * Test {@link StringMultipartPart#getContentLength()}.
   * <ul>
   *   <li>Then return zero.</li>
   * </ul>
   * <p>
   * Method under test: {@link StringMultipartPart#getContentLength()}
   */
  @Test
  @DisplayName("Test getContentLength(); then return zero")
  void testGetContentLength_thenReturnZero() throws UnsupportedEncodingException {
    // Arrange
    StringPart part = new StringPart("https://example.org/example", "");

    // Act and Assert
    assertEquals(0L, (new StringMultipartPart(part, "AXAXAXAX".getBytes("UTF-8"))).getContentLength());
  }

  /**
   * Test {@link StringMultipartPart#transferContentTo(ByteBuf)} with
   * {@code ByteBuf}.
   * <p>
   * Method under test: {@link StringMultipartPart#transferContentTo(ByteBuf)}
   */
  @Test
  @DisplayName("Test transferContentTo(ByteBuf) with 'ByteBuf'")
  void testTransferContentToWithByteBuf() throws UnsupportedEncodingException {
    // Arrange
    StringPart part = new StringPart("https://example.org/example", "https://example.org/example");

    StringMultipartPart stringMultipartPart = new StringMultipartPart(part, "AXAXAXAX".getBytes("UTF-8"));

    // Act and Assert
    assertEquals(0L,
        stringMultipartPart.transferContentTo(new DuplicatedByteBuf(new EmptyByteBuf(new AdaptiveByteBufAllocator()))));
    assertEquals(MultipartState.PRE_CONTENT, stringMultipartPart.getState());
  }

  /**
   * Test {@link StringMultipartPart#transferContentTo(ByteBuf)} with
   * {@code ByteBuf}.
   * <p>
   * Method under test: {@link StringMultipartPart#transferContentTo(ByteBuf)}
   */
  @Test
  @DisplayName("Test transferContentTo(ByteBuf) with 'ByteBuf'")
  void testTransferContentToWithByteBuf2() throws UnsupportedEncodingException {
    // Arrange
    StringPart part = new StringPart("https://example.org/example", "");

    StringMultipartPart stringMultipartPart = new StringMultipartPart(part, "AXAXAXAX".getBytes("UTF-8"));

    // Act and Assert
    assertEquals(0L,
        stringMultipartPart.transferContentTo(new DuplicatedByteBuf(new EmptyByteBuf(new AdaptiveByteBufAllocator()))));
    assertEquals(MultipartState.POST_CONTENT, stringMultipartPart.getState());
  }

  /**
   * Test {@link StringMultipartPart#transferContentTo(WritableByteChannel)} with
   * {@code WritableByteChannel}.
   * <ul>
   *   <li>Given one.</li>
   *   <li>Then return one.</li>
   * </ul>
   * <p>
   * Method under test:
   * {@link StringMultipartPart#transferContentTo(WritableByteChannel)}
   */
  @Test
  @DisplayName("Test transferContentTo(WritableByteChannel) with 'WritableByteChannel'; given one; then return one")
  void testTransferContentToWithWritableByteChannel_givenOne_thenReturnOne() throws IOException {
    // Arrange
    StringPart part = new StringPart("https://example.org/example", "https://example.org/example");

    StringMultipartPart stringMultipartPart = new StringMultipartPart(part, "AXAXAXAX".getBytes("UTF-8"));
    BrotliEncoderChannel target = mock(BrotliEncoderChannel.class);
    when(target.write(Mockito.<ByteBuffer>any())).thenReturn(1);

    // Act
    long actualTransferContentToResult = stringMultipartPart.transferContentTo(target);

    // Assert
    verify(target).write(isA(ByteBuffer.class));
    assertEquals(1L, actualTransferContentToResult);
    assertEquals(MultipartState.PRE_CONTENT, stringMultipartPart.getState());
    assertTrue(stringMultipartPart.isTargetSlow());
  }

  /**
   * Test {@link StringMultipartPart#transferContentTo(WritableByteChannel)} with
   * {@code WritableByteChannel}.
   * <ul>
   *   <li>Given zero.</li>
   *   <li>Then return zero.</li>
   * </ul>
   * <p>
   * Method under test:
   * {@link StringMultipartPart#transferContentTo(WritableByteChannel)}
   */
  @Test
  @DisplayName("Test transferContentTo(WritableByteChannel) with 'WritableByteChannel'; given zero; then return zero")
  void testTransferContentToWithWritableByteChannel_givenZero_thenReturnZero() throws IOException {
    // Arrange
    StringPart part = new StringPart("https://example.org/example", "");

    StringMultipartPart stringMultipartPart = new StringMultipartPart(part, "AXAXAXAX".getBytes("UTF-8"));
    BrotliEncoderChannel target = mock(BrotliEncoderChannel.class);
    when(target.write(Mockito.<ByteBuffer>any())).thenReturn(0);

    // Act
    long actualTransferContentToResult = stringMultipartPart.transferContentTo(target);

    // Assert
    verify(target).write(isA(ByteBuffer.class));
    assertEquals(0L, actualTransferContentToResult);
    assertEquals(MultipartState.POST_CONTENT, stringMultipartPart.getState());
    assertFalse(stringMultipartPart.isTargetSlow());
  }

  /**
   * Test {@link StringMultipartPart#transferContentTo(WritableByteChannel)} with
   * {@code WritableByteChannel}.
   * <ul>
   *   <li>Then return twenty-seven.</li>
   * </ul>
   * <p>
   * Method under test:
   * {@link StringMultipartPart#transferContentTo(WritableByteChannel)}
   */
  @Test
  @DisplayName("Test transferContentTo(WritableByteChannel) with 'WritableByteChannel'; then return twenty-seven")
  void testTransferContentToWithWritableByteChannel_thenReturnTwentySeven() throws IOException {
    // Arrange
    StringPart part = new StringPart("https://example.org/example", "https://example.org/example");

    StringMultipartPart stringMultipartPart = new StringMultipartPart(part, "AXAXAXAX".getBytes("UTF-8"));
    BrotliEncoderChannel target = mock(BrotliEncoderChannel.class);
    when(target.write(Mockito.<ByteBuffer>any())).thenReturn(27);

    // Act
    long actualTransferContentToResult = stringMultipartPart.transferContentTo(target);

    // Assert
    verify(target).write(isA(ByteBuffer.class));
    assertEquals(27L, actualTransferContentToResult);
    assertEquals(MultipartState.POST_CONTENT, stringMultipartPart.getState());
    assertFalse(stringMultipartPart.isTargetSlow());
  }

  /**
   * Test {@link StringMultipartPart#close()}.
   * <p>
   * Method under test: {@link StringMultipartPart#close()}
   */
  @Test
  @DisplayName("Test close()")
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
   * Test {@link StringMultipartPart#close()}.
   * <ul>
   *   <li>Given {@link StringPart#StringPart(String, String)} with name is
   * {@code https://example.org/example} and value is empty string.</li>
   * </ul>
   * <p>
   * Method under test: {@link StringMultipartPart#close()}
   */
  @Test
  @DisplayName("Test close(); given StringPart(String, String) with name is 'https://example.org/example' and value is empty string")
  void testClose_givenStringPartWithNameIsHttpsExampleOrgExampleAndValueIsEmptyString()
      throws UnsupportedEncodingException {
    // Arrange
    StringPart part = new StringPart("https://example.org/example", "");

    StringMultipartPart stringMultipartPart = new StringMultipartPart(part, "AXAXAXAX".getBytes("UTF-8"));

    // Act
    stringMultipartPart.close();

    // Assert that nothing has changed
    assertEquals(0L, stringMultipartPart.getContentLength());
    assertEquals(84L, stringMultipartPart.length());
  }
}
