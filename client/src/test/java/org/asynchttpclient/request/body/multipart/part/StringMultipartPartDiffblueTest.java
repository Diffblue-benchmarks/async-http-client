package org.asynchttpclient.request.body.multipart.part;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.aayushatharva.brotli4j.encoder.BrotliEncoderChannel;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
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
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

class StringMultipartPartDiffblueTest {
  /**
   * Test {@link StringMultipartPart#StringMultipartPart(StringPart, byte[])}.
   *
   * <p>Method under test: {@link StringMultipartPart#StringMultipartPart(StringPart, byte[])}
   */
  @Test
  @DisplayName("Test new StringMultipartPart(StringPart, byte[])")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void StringMultipartPart.<init>(StringPart, byte[])"})
  void testNewStringMultipartPart() throws UnsupportedEncodingException {
    // Arrange and Act
    StringMultipartPart actualStringMultipartPart =
        new StringMultipartPart(
            new StringPart("https://example.org/example", "https://example.org/example"),
            "AXAXAXAX".getBytes("UTF-8"));

    // Assert
    StringPart stringPart = actualStringMultipartPart.part;
    assertEquals("https://example.org/example", stringPart.getValue());
    assertNull(stringPart.getContentType());
    assertNull(stringPart.getDispositionType());
    assertNull(stringPart.getCustomHeaders());
    assertEquals(111L, actualStringMultipartPart.length());
    assertEquals(27L, actualStringMultipartPart.getContentLength());
    assertArrayEquals("AXAXAXAX".getBytes("UTF-8"), actualStringMultipartPart.boundary);
  }

  /**
   * Test {@link StringMultipartPart#StringMultipartPart(StringPart, byte[])}.
   *
   * <ul>
   *   <li>Given {@link ArrayList#ArrayList()}.
   *   <li>Then return {@link MultipartPart#part} CustomHeaders Empty.
   * </ul>
   *
   * <p>Method under test: {@link StringMultipartPart#StringMultipartPart(StringPart, byte[])}
   */
  @Test
  @DisplayName(
      "Test new StringMultipartPart(StringPart, byte[]); given ArrayList(); then return part CustomHeaders Empty")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void StringMultipartPart.<init>(StringPart, byte[])"})
  void testNewStringMultipartPart_givenArrayList_thenReturnPartCustomHeadersEmpty()
      throws UnsupportedEncodingException {
    // Arrange
    StringPart part = new StringPart("https://example.org/example", "https://example.org/example");
    part.setCustomHeaders(new ArrayList<>());

    // Act
    StringMultipartPart actualStringMultipartPart =
        new StringMultipartPart(part, "AXAXAXAX".getBytes("UTF-8"));

    // Assert
    StringPart stringPart = actualStringMultipartPart.part;
    assertEquals("https://example.org/example", stringPart.getValue());
    assertNull(stringPart.getContentType());
    assertNull(stringPart.getDispositionType());
    assertEquals(111L, actualStringMultipartPart.length());
    assertEquals(27L, actualStringMultipartPart.getContentLength());
    assertTrue(stringPart.getCustomHeaders().isEmpty());
    assertArrayEquals("AXAXAXAX".getBytes("UTF-8"), actualStringMultipartPart.boundary);
  }

  /**
   * Test {@link StringMultipartPart#StringMultipartPart(StringPart, byte[])}.
   *
   * <ul>
   *   <li>Then return {@link MultipartPart#part} ContentType is {@code
   *       https://example.org/example}.
   * </ul>
   *
   * <p>Method under test: {@link StringMultipartPart#StringMultipartPart(StringPart, byte[])}
   */
  @Test
  @DisplayName(
      "Test new StringMultipartPart(StringPart, byte[]); then return part ContentType is 'https://example.org/example'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void StringMultipartPart.<init>(StringPart, byte[])"})
  void testNewStringMultipartPart_thenReturnPartContentTypeIsHttpsExampleOrgExample()
      throws UnsupportedEncodingException {
    // Arrange
    StringPart part =
        new StringPart(
            "https://example.org/example",
            "https://example.org/example",
            "https://example.org/example");

    // Act
    StringMultipartPart actualStringMultipartPart =
        new StringMultipartPart(part, "AXAXAXAX".getBytes("UTF-8"));

    // Assert
    StringPart stringPart = actualStringMultipartPart.part;
    assertEquals("https://example.org/example", stringPart.getContentType());
    assertNull(stringPart.getCustomHeaders());
    assertEquals(169L, actualStringMultipartPart.length());
    assertArrayEquals("AXAXAXAX".getBytes("UTF-8"), actualStringMultipartPart.boundary);
  }

  /**
   * Test {@link StringMultipartPart#StringMultipartPart(StringPart, byte[])}.
   *
   * <ul>
   *   <li>Then return {@link MultipartPart#part} CustomHeaders size is one.
   * </ul>
   *
   * <p>Method under test: {@link StringMultipartPart#StringMultipartPart(StringPart, byte[])}
   */
  @Test
  @DisplayName(
      "Test new StringMultipartPart(StringPart, byte[]); then return part CustomHeaders size is one")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void StringMultipartPart.<init>(StringPart, byte[])"})
  void testNewStringMultipartPart_thenReturnPartCustomHeadersSizeIsOne()
      throws UnsupportedEncodingException {
    // Arrange
    StringPart part = new StringPart("https://example.org/example", "https://example.org/example");
    part.addCustomHeader("https://example.org/example", "https://example.org/example");

    // Act
    StringMultipartPart actualStringMultipartPart =
        new StringMultipartPart(part, "AXAXAXAX".getBytes("UTF-8"));

    // Assert
    List<Param> customHeaders = actualStringMultipartPart.part.getCustomHeaders();
    assertEquals(1, customHeaders.size());
    Param getResult = customHeaders.get(0);
    assertEquals("https://example.org/example", getResult.getName());
    assertEquals("https://example.org/example", getResult.getValue());
    assertEquals(169L, actualStringMultipartPart.length());
    assertArrayEquals("AXAXAXAX".getBytes("UTF-8"), actualStringMultipartPart.boundary);
  }

  /**
   * Test {@link StringMultipartPart#StringMultipartPart(StringPart, byte[])}.
   *
   * <ul>
   *   <li>Then return {@link MultipartPart#part} DispositionType is {@code
   *       https://example.org/example}.
   * </ul>
   *
   * <p>Method under test: {@link StringMultipartPart#StringMultipartPart(StringPart, byte[])}
   */
  @Test
  @DisplayName(
      "Test new StringMultipartPart(StringPart, byte[]); then return part DispositionType is 'https://example.org/example'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void StringMultipartPart.<init>(StringPart, byte[])"})
  void testNewStringMultipartPart_thenReturnPartDispositionTypeIsHttpsExampleOrgExample()
      throws UnsupportedEncodingException {
    // Arrange
    StringPart part = new StringPart("https://example.org/example", "https://example.org/example");
    part.setDispositionType("https://example.org/example");
    part.addCustomHeader("https://example.org/example", "https://example.org/example");

    // Act
    StringMultipartPart actualStringMultipartPart =
        new StringMultipartPart(part, "AXAXAXAX".getBytes("UTF-8"));

    // Assert
    StringPart stringPart = actualStringMultipartPart.part;
    List<Param> customHeaders = stringPart.getCustomHeaders();
    assertEquals(1, customHeaders.size());
    Param getResult = customHeaders.get(0);
    assertEquals("https://example.org/example", getResult.getName());
    assertEquals("https://example.org/example", getResult.getValue());
    assertEquals("https://example.org/example", stringPart.getDispositionType());
    assertEquals(187L, actualStringMultipartPart.length());
    assertArrayEquals("AXAXAXAX".getBytes("UTF-8"), actualStringMultipartPart.boundary);
  }

  /**
   * Test {@link StringMultipartPart#StringMultipartPart(StringPart, byte[])}.
   *
   * <ul>
   *   <li>Then return {@link MultipartPart#part} Value is empty string.
   * </ul>
   *
   * <p>Method under test: {@link StringMultipartPart#StringMultipartPart(StringPart, byte[])}
   */
  @Test
  @DisplayName(
      "Test new StringMultipartPart(StringPart, byte[]); then return part Value is empty string")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void StringMultipartPart.<init>(StringPart, byte[])"})
  void testNewStringMultipartPart_thenReturnPartValueIsEmptyString()
      throws UnsupportedEncodingException {
    // Arrange and Act
    StringMultipartPart actualStringMultipartPart =
        new StringMultipartPart(
            new StringPart("https://example.org/example", ""), "AXAXAXAX".getBytes("UTF-8"));

    // Assert
    assertEquals("", actualStringMultipartPart.part.getValue());
    assertEquals(0L, actualStringMultipartPart.getContentLength());
    assertEquals(84L, actualStringMultipartPart.length());
    assertArrayEquals("AXAXAXAX".getBytes("UTF-8"), actualStringMultipartPart.boundary);
  }

  /**
   * Test {@link StringMultipartPart#getContentLength()}.
   *
   * <p>Method under test: {@link StringMultipartPart#getContentLength()}
   */
  @Test
  @DisplayName("Test getContentLength()")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"long StringMultipartPart.getContentLength()"})
  void testGetContentLength() throws UnsupportedEncodingException {
    // Arrange
    StringMultipartPart stringMultipartPart =
        new StringMultipartPart(
            new StringPart("https://example.org/example", "https://example.org/example"),
            "AXAXAXAX".getBytes("UTF-8"));

    // Act and Assert
    assertEquals(27L, stringMultipartPart.getContentLength());
  }

  /**
   * Test {@link StringMultipartPart#transferContentTo(ByteBuf)} with {@code ByteBuf}.
   *
   * <p>Method under test: {@link StringMultipartPart#transferContentTo(ByteBuf)}
   */
  @Test
  @DisplayName("Test transferContentTo(ByteBuf) with 'ByteBuf'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"long StringMultipartPart.transferContentTo(ByteBuf)"})
  void testTransferContentToWithByteBuf() throws UnsupportedEncodingException {
    // Arrange
    StringMultipartPart stringMultipartPart =
        new StringMultipartPart(
            new StringPart("https://example.org/example", "https://example.org/example"),
            "AXAXAXAX".getBytes("UTF-8"));

    // Act
    long actualTransferContentToResult =
        stringMultipartPart.transferContentTo(
            new DuplicatedByteBuf(new EmptyByteBuf(new AdaptiveByteBufAllocator())));

    // Assert
    assertEquals(0L, actualTransferContentToResult);
    assertEquals(MultipartState.PRE_CONTENT, stringMultipartPart.getState());
  }

  /**
   * Test {@link StringMultipartPart#transferContentTo(ByteBuf)} with {@code ByteBuf}.
   *
   * <p>Method under test: {@link StringMultipartPart#transferContentTo(ByteBuf)}
   */
  @Test
  @DisplayName("Test transferContentTo(ByteBuf) with 'ByteBuf'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"long StringMultipartPart.transferContentTo(ByteBuf)"})
  void testTransferContentToWithByteBuf2() throws UnsupportedEncodingException {
    // Arrange
    StringMultipartPart stringMultipartPart =
        new StringMultipartPart(
            new StringPart("https://example.org/example", ""), "AXAXAXAX".getBytes("UTF-8"));

    // Act
    long actualTransferContentToResult =
        stringMultipartPart.transferContentTo(
            new DuplicatedByteBuf(new EmptyByteBuf(new AdaptiveByteBufAllocator())));

    // Assert
    assertEquals(0L, actualTransferContentToResult);
    assertEquals(MultipartState.POST_CONTENT, stringMultipartPart.getState());
  }

  /**
   * Test {@link StringMultipartPart#transferContentTo(WritableByteChannel)} with {@code
   * WritableByteChannel}.
   *
   * <ul>
   *   <li>Given one.
   *   <li>Then return one.
   * </ul>
   *
   * <p>Method under test: {@link StringMultipartPart#transferContentTo(WritableByteChannel)}
   */
  @Test
  @DisplayName(
      "Test transferContentTo(WritableByteChannel) with 'WritableByteChannel'; given one; then return one")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"long StringMultipartPart.transferContentTo(WritableByteChannel)"})
  void testTransferContentToWithWritableByteChannel_givenOne_thenReturnOne() throws IOException {
    // Arrange
    StringMultipartPart stringMultipartPart =
        new StringMultipartPart(
            new StringPart("https://example.org/example", "https://example.org/example"),
            "AXAXAXAX".getBytes("UTF-8"));

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
   * Test {@link StringMultipartPart#transferContentTo(WritableByteChannel)} with {@code
   * WritableByteChannel}.
   *
   * <ul>
   *   <li>Then return twenty-seven.
   * </ul>
   *
   * <p>Method under test: {@link StringMultipartPart#transferContentTo(WritableByteChannel)}
   */
  @Test
  @DisplayName(
      "Test transferContentTo(WritableByteChannel) with 'WritableByteChannel'; then return twenty-seven")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"long StringMultipartPart.transferContentTo(WritableByteChannel)"})
  void testTransferContentToWithWritableByteChannel_thenReturnTwentySeven() throws IOException {
    // Arrange
    StringMultipartPart stringMultipartPart =
        new StringMultipartPart(
            new StringPart("https://example.org/example", "https://example.org/example"),
            "AXAXAXAX".getBytes("UTF-8"));

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
   *
   * <p>Method under test: {@link StringMultipartPart#close()}
   */
  @Test
  @DisplayName("Test close()")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void StringMultipartPart.close()"})
  void testClose() throws UnsupportedEncodingException {
    // Arrange
    StringMultipartPart stringMultipartPart =
        new StringMultipartPart(
            new StringPart("https://example.org/example", "https://example.org/example"),
            "AXAXAXAX".getBytes("UTF-8"));

    // Act
    stringMultipartPart.close();

    // Assert
    assertEquals(0L, stringMultipartPart.getContentLength());
    assertEquals(84L, stringMultipartPart.length());
  }
}
