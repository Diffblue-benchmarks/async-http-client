package org.asynchttpclient.request.body.multipart;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.aayushatharva.brotli4j.encoder.BrotliEncoderChannel;
import com.diffblue.cover.annotations.MethodsUnderTest;
import io.netty.buffer.AdaptiveByteBufAllocator;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.DuplicatedByteBuf;
import io.netty.buffer.EmptyByteBuf;
import io.netty.handler.codec.http.DefaultHttpHeaders;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.nio.channels.WritableByteChannel;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import org.asynchttpclient.Param;
import org.asynchttpclient.request.body.Body;
import org.asynchttpclient.request.body.Body.BodyState;
import org.asynchttpclient.request.body.multipart.part.ByteArrayMultipartPart;
import org.asynchttpclient.request.body.multipart.part.MultipartPart;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

class MultipartBodyDiffblueTest {
  /**
   * Test {@link MultipartBody#MultipartBody(List, String, byte[])}.
   * <ul>
   *   <li>Then return ContentLength is one hundred thirty-two.</li>
   * </ul>
   * <p>
   * Method under test: {@link MultipartBody#MultipartBody(List, String, byte[])}
   */
  @Test
  @DisplayName("Test new MultipartBody(List, String, byte[]); then return ContentLength is one hundred thirty-two")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void MultipartBody.<init>(List, String, byte[])"})
  void testNewMultipartBody_thenReturnContentLengthIsOneHundredThirtyTwo() throws UnsupportedEncodingException {
    // Arrange
    ArrayList<MultipartPart<? extends Part>> parts = new ArrayList<>();
    ByteArrayPart part = new ByteArrayPart("https://example.org/example", "AXAXAXAX".getBytes("UTF-8"));

    parts.add(new ByteArrayMultipartPart(part, "AXAXAXAX".getBytes("UTF-8")));

    // Act
    MultipartBody actualMultipartBody = new MultipartBody(parts, "https://example.org/example",
        "AXAXAXAX".getBytes("UTF-8"));

    // Assert
    assertEquals("https://example.org/example", actualMultipartBody.getContentType());
    assertEquals(132L, actualMultipartBody.getContentLength());
    byte[] expectedBoundary = "AXAXAXAX".getBytes("UTF-8");
    assertArrayEquals(expectedBoundary, actualMultipartBody.getBoundary());
  }

  /**
   * Test {@link MultipartBody#MultipartBody(List, String, byte[])}.
   * <ul>
   *   <li>Then return ContentLength is two hundred sixty-four.</li>
   * </ul>
   * <p>
   * Method under test: {@link MultipartBody#MultipartBody(List, String, byte[])}
   */
  @Test
  @DisplayName("Test new MultipartBody(List, String, byte[]); then return ContentLength is two hundred sixty-four")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void MultipartBody.<init>(List, String, byte[])"})
  void testNewMultipartBody_thenReturnContentLengthIsTwoHundredSixtyFour() throws UnsupportedEncodingException {
    // Arrange
    ArrayList<MultipartPart<? extends Part>> parts = new ArrayList<>();
    ByteArrayPart part = new ByteArrayPart("https://example.org/example", "AXAXAXAX".getBytes("UTF-8"));

    parts.add(new ByteArrayMultipartPart(part, "AXAXAXAX".getBytes("UTF-8")));
    ByteArrayPart part2 = new ByteArrayPart("https://example.org/example", "AXAXAXAX".getBytes("UTF-8"));

    parts.add(new ByteArrayMultipartPart(part2, "AXAXAXAX".getBytes("UTF-8")));

    // Act
    MultipartBody actualMultipartBody = new MultipartBody(parts, "https://example.org/example",
        "AXAXAXAX".getBytes("UTF-8"));

    // Assert
    assertEquals("https://example.org/example", actualMultipartBody.getContentType());
    assertEquals(264L, actualMultipartBody.getContentLength());
    byte[] expectedBoundary = "AXAXAXAX".getBytes("UTF-8");
    assertArrayEquals(expectedBoundary, actualMultipartBody.getBoundary());
  }

  /**
   * Test {@link MultipartBody#MultipartBody(List, String, byte[])}.
   * <ul>
   *   <li>When {@link ArrayList#ArrayList()}.</li>
   *   <li>Then return ContentLength is zero.</li>
   * </ul>
   * <p>
   * Method under test: {@link MultipartBody#MultipartBody(List, String, byte[])}
   */
  @Test
  @DisplayName("Test new MultipartBody(List, String, byte[]); when ArrayList(); then return ContentLength is zero")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void MultipartBody.<init>(List, String, byte[])"})
  void testNewMultipartBody_whenArrayList_thenReturnContentLengthIsZero() throws UnsupportedEncodingException {
    // Arrange
    ArrayList<MultipartPart<? extends Part>> parts = new ArrayList<>();

    // Act
    MultipartBody actualMultipartBody = new MultipartBody(parts, "https://example.org/example",
        "AXAXAXAX".getBytes("UTF-8"));

    // Assert
    assertEquals("https://example.org/example", actualMultipartBody.getContentType());
    assertEquals(0L, actualMultipartBody.getContentLength());
    byte[] expectedBoundary = "AXAXAXAX".getBytes("UTF-8");
    assertArrayEquals(expectedBoundary, actualMultipartBody.getBoundary());
  }

  /**
   * Test getters and setters.
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link MultipartBody#getBoundary()}
   *   <li>{@link MultipartBody#getContentLength()}
   *   <li>{@link MultipartBody#getContentType()}
   * </ul>
   */
  @Test
  @DisplayName("Test getters and setters")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"byte[] MultipartBody.getBoundary()", "long MultipartBody.getContentLength()",
      "String MultipartBody.getContentType()"})
  void testGettersAndSetters() throws UnsupportedEncodingException {
    // Arrange
    ArrayList<MultipartPart<? extends Part>> parts = new ArrayList<>();
    MultipartBody multipartBody = new MultipartBody(parts, "https://example.org/example", "AXAXAXAX".getBytes("UTF-8"));

    // Act
    byte[] actualBoundary = multipartBody.getBoundary();
    long actualContentLength = multipartBody.getContentLength();

    // Assert
    assertEquals("https://example.org/example", multipartBody.getContentType());
    assertEquals(0L, actualContentLength);
    assertArrayEquals("AXAXAXAX".getBytes("UTF-8"), actualBoundary);
  }

  /**
   * Test {@link MultipartBody#transferTo(ByteBuf)} with {@code ByteBuf}.
   * <ul>
   *   <li>Then return {@code CONTINUE}.</li>
   * </ul>
   * <p>
   * Method under test: {@link MultipartBody#transferTo(ByteBuf)}
   */
  @Test
  @DisplayName("Test transferTo(ByteBuf) with 'ByteBuf'; then return 'CONTINUE'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"BodyState MultipartBody.transferTo(ByteBuf)"})
  void testTransferToWithByteBuf_thenReturnContinue() throws IOException {
    // Arrange
    ArrayList<Part> parts = new ArrayList<>();
    MultipartBody newMultipartBodyResult = MultipartUtils.newMultipartBody(parts, new DefaultHttpHeaders());

    // Act and Assert
    assertEquals(BodyState.CONTINUE,
        newMultipartBodyResult.transferTo(new DuplicatedByteBuf(new EmptyByteBuf(new AdaptiveByteBufAllocator()))));
  }

  /**
   * Test {@link MultipartBody#transferTo(WritableByteChannel)} with {@code WritableByteChannel}.
   * <p>
   * Method under test: {@link MultipartBody#transferTo(WritableByteChannel)}
   */
  @Test
  @DisplayName("Test transferTo(WritableByteChannel) with 'WritableByteChannel'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"long MultipartBody.transferTo(WritableByteChannel)"})
  void testTransferToWithWritableByteChannel() throws IOException {
    // Arrange
    ArrayList<Param> paramList = new ArrayList<>();
    paramList.add(new Param("https://example.org/example", "https://example.org/example"));
    ByteArrayPart byteArrayPart = mock(ByteArrayPart.class);
    when(byteArrayPart.getCharset()).thenReturn(Charset.forName("UTF-8"));
    when(byteArrayPart.getBytes()).thenReturn("AXAXAXAX".getBytes("UTF-8"));
    when(byteArrayPart.getFileName()).thenReturn("https://example.org/example");
    when(byteArrayPart.getContentId()).thenReturn("https://example.org/example");
    when(byteArrayPart.getContentType()).thenReturn("text/plain");
    when(byteArrayPart.getDispositionType()).thenReturn("https://example.org/example");
    when(byteArrayPart.getName()).thenReturn("https://example.org/example");
    when(byteArrayPart.getTransferEncoding()).thenReturn("https://example.org/example");
    when(byteArrayPart.getCustomHeaders()).thenReturn(paramList);

    ArrayList<Part> parts = new ArrayList<>();
    parts.add(byteArrayPart);
    MultipartBody newMultipartBodyResult = MultipartUtils.newMultipartBody(parts, new DefaultHttpHeaders());
    BrotliEncoderChannel target = mock(BrotliEncoderChannel.class);
    when(target.write(Mockito.<ByteBuffer>any())).thenReturn(1);

    // Act
    long actualTransferToResult = newMultipartBodyResult.transferTo(target);

    // Assert
    verify(target).write(isA(ByteBuffer.class));
    verify(byteArrayPart, atLeast(1)).getBytes();
    verify(byteArrayPart, atLeast(1)).getFileName();
    verify(byteArrayPart, atLeast(1)).getCharset();
    verify(byteArrayPart, atLeast(1)).getContentId();
    verify(byteArrayPart, atLeast(1)).getContentType();
    verify(byteArrayPart, atLeast(1)).getCustomHeaders();
    verify(byteArrayPart, atLeast(1)).getDispositionType();
    verify(byteArrayPart, atLeast(1)).getName();
    verify(byteArrayPart, atLeast(1)).getTransferEncoding();
    assertEquals(1L, actualTransferToResult);
  }

  /**
   * Test {@link MultipartBody#transferTo(WritableByteChannel)} with {@code WritableByteChannel}.
   * <p>
   * Method under test: {@link MultipartBody#transferTo(WritableByteChannel)}
   */
  @Test
  @DisplayName("Test transferTo(WritableByteChannel) with 'WritableByteChannel'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"long MultipartBody.transferTo(WritableByteChannel)"})
  void testTransferToWithWritableByteChannel2() throws IOException {
    // Arrange
    ByteArrayPart byteArrayPart = mock(ByteArrayPart.class);
    when(byteArrayPart.getCharset()).thenReturn(Charset.forName("UTF-8"));
    when(byteArrayPart.getBytes()).thenReturn("AXAXAXAX".getBytes("UTF-8"));
    when(byteArrayPart.getFileName()).thenReturn("https://example.org/example");
    when(byteArrayPart.getContentId()).thenReturn("https://example.org/example");
    when(byteArrayPart.getContentType()).thenReturn("text/plain");
    when(byteArrayPart.getDispositionType()).thenReturn("https://example.org/example");
    when(byteArrayPart.getName()).thenReturn("https://example.org/example");
    when(byteArrayPart.getTransferEncoding()).thenReturn("https://example.org/example");
    when(byteArrayPart.getCustomHeaders()).thenReturn(new ArrayList<>());

    ArrayList<Part> parts = new ArrayList<>();
    parts.add(new ByteArrayPart("https://example.org/example", "AXAXAXAX".getBytes("UTF-8")));
    parts.add(byteArrayPart);
    MultipartBody newMultipartBodyResult = MultipartUtils.newMultipartBody(parts, new DefaultHttpHeaders());
    BrotliEncoderChannel target = mock(BrotliEncoderChannel.class);
    when(target.write(Mockito.<ByteBuffer>any())).thenReturn(1);

    // Act
    long actualTransferToResult = newMultipartBodyResult.transferTo(target);

    // Assert
    verify(target).write(isA(ByteBuffer.class));
    verify(byteArrayPart, atLeast(1)).getBytes();
    verify(byteArrayPart, atLeast(1)).getFileName();
    verify(byteArrayPart, atLeast(1)).getCharset();
    verify(byteArrayPart).getContentId();
    verify(byteArrayPart).getContentType();
    verify(byteArrayPart).getCustomHeaders();
    verify(byteArrayPart, atLeast(1)).getDispositionType();
    verify(byteArrayPart, atLeast(1)).getName();
    verify(byteArrayPart).getTransferEncoding();
    assertEquals(1L, actualTransferToResult);
  }

  /**
   * Test {@link MultipartBody#transferTo(WritableByteChannel)} with {@code WritableByteChannel}.
   * <ul>
   *   <li>Given one.</li>
   *   <li>Then calls {@link ByteArrayPart#getBytes()}.</li>
   * </ul>
   * <p>
   * Method under test: {@link MultipartBody#transferTo(WritableByteChannel)}
   */
  @Test
  @DisplayName("Test transferTo(WritableByteChannel) with 'WritableByteChannel'; given one; then calls getBytes()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"long MultipartBody.transferTo(WritableByteChannel)"})
  void testTransferToWithWritableByteChannel_givenOne_thenCallsGetBytes() throws IOException {
    // Arrange
    ByteArrayPart byteArrayPart = mock(ByteArrayPart.class);
    when(byteArrayPart.getCharset()).thenReturn(Charset.forName("UTF-8"));
    when(byteArrayPart.getBytes()).thenReturn("AXAXAXAX".getBytes("UTF-8"));
    when(byteArrayPart.getFileName()).thenReturn("https://example.org/example");
    when(byteArrayPart.getContentId()).thenReturn("https://example.org/example");
    when(byteArrayPart.getContentType()).thenReturn("text/plain");
    when(byteArrayPart.getDispositionType()).thenReturn("https://example.org/example");
    when(byteArrayPart.getName()).thenReturn("https://example.org/example");
    when(byteArrayPart.getTransferEncoding()).thenReturn("https://example.org/example");
    when(byteArrayPart.getCustomHeaders()).thenReturn(new ArrayList<>());

    ArrayList<Part> parts = new ArrayList<>();
    parts.add(byteArrayPart);
    MultipartBody newMultipartBodyResult = MultipartUtils.newMultipartBody(parts, new DefaultHttpHeaders());
    BrotliEncoderChannel target = mock(BrotliEncoderChannel.class);
    when(target.write(Mockito.<ByteBuffer>any())).thenReturn(1);

    // Act
    long actualTransferToResult = newMultipartBodyResult.transferTo(target);

    // Assert
    verify(target).write(isA(ByteBuffer.class));
    verify(byteArrayPart, atLeast(1)).getBytes();
    verify(byteArrayPart, atLeast(1)).getFileName();
    verify(byteArrayPart, atLeast(1)).getCharset();
    verify(byteArrayPart, atLeast(1)).getContentId();
    verify(byteArrayPart, atLeast(1)).getContentType();
    verify(byteArrayPart, atLeast(1)).getCustomHeaders();
    verify(byteArrayPart, atLeast(1)).getDispositionType();
    verify(byteArrayPart, atLeast(1)).getName();
    verify(byteArrayPart, atLeast(1)).getTransferEncoding();
    assertEquals(1L, actualTransferToResult);
  }

  /**
   * Test {@link MultipartBody#transferTo(WritableByteChannel)} with {@code WritableByteChannel}.
   * <ul>
   *   <li>Given one.</li>
   *   <li>Then return one.</li>
   * </ul>
   * <p>
   * Method under test: {@link MultipartBody#transferTo(WritableByteChannel)}
   */
  @Test
  @DisplayName("Test transferTo(WritableByteChannel) with 'WritableByteChannel'; given one; then return one")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"long MultipartBody.transferTo(WritableByteChannel)"})
  void testTransferToWithWritableByteChannel_givenOne_thenReturnOne() throws IOException {
    // Arrange
    ArrayList<Part> parts = new ArrayList<>();
    MultipartBody newMultipartBodyResult = MultipartUtils.newMultipartBody(parts, new DefaultHttpHeaders());
    BrotliEncoderChannel target = mock(BrotliEncoderChannel.class);
    when(target.write(Mockito.<ByteBuffer>any())).thenReturn(1);

    // Act
    long actualTransferToResult = newMultipartBodyResult.transferTo(target);

    // Assert
    verify(target).write(isA(ByteBuffer.class));
    assertEquals(1L, actualTransferToResult);
  }

  /**
   * Test {@link MultipartBody#transferTo(WritableByteChannel)} with {@code WritableByteChannel}.
   * <ul>
   *   <li>Given {@code X}.</li>
   *   <li>Then calls {@link ByteArrayPart#getBytes()}.</li>
   * </ul>
   * <p>
   * Method under test: {@link MultipartBody#transferTo(WritableByteChannel)}
   */
  @Test
  @DisplayName("Test transferTo(WritableByteChannel) with 'WritableByteChannel'; given 'X'; then calls getBytes()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"long MultipartBody.transferTo(WritableByteChannel)"})
  void testTransferToWithWritableByteChannel_givenX_thenCallsGetBytes() throws IOException {
    // Arrange
    ByteArrayPart byteArrayPart = mock(ByteArrayPart.class);
    when(byteArrayPart.getCharset()).thenReturn(Charset.forName("UTF-8"));
    when(byteArrayPart.getBytes()).thenReturn(new byte[]{'A', 'X', 'A', 'X', 'A', 'X', 'A', Byte.MAX_VALUE});
    when(byteArrayPart.getFileName()).thenReturn("https://example.org/example");
    when(byteArrayPart.getContentId()).thenReturn("https://example.org/example");
    when(byteArrayPart.getContentType()).thenReturn("text/plain");
    when(byteArrayPart.getDispositionType()).thenReturn("https://example.org/example");
    when(byteArrayPart.getName()).thenReturn("https://example.org/example");
    when(byteArrayPart.getTransferEncoding()).thenReturn("https://example.org/example");
    when(byteArrayPart.getCustomHeaders()).thenReturn(new ArrayList<>());

    ArrayList<Part> parts = new ArrayList<>();
    parts.add(byteArrayPart);
    MultipartBody newMultipartBodyResult = MultipartUtils.newMultipartBody(parts, new DefaultHttpHeaders());
    BrotliEncoderChannel target = mock(BrotliEncoderChannel.class);
    when(target.write(Mockito.<ByteBuffer>any())).thenReturn(1);

    // Act
    long actualTransferToResult = newMultipartBodyResult.transferTo(target);

    // Assert
    verify(target).write(isA(ByteBuffer.class));
    verify(byteArrayPart, atLeast(1)).getBytes();
    verify(byteArrayPart, atLeast(1)).getFileName();
    verify(byteArrayPart, atLeast(1)).getCharset();
    verify(byteArrayPart, atLeast(1)).getContentId();
    verify(byteArrayPart, atLeast(1)).getContentType();
    verify(byteArrayPart, atLeast(1)).getCustomHeaders();
    verify(byteArrayPart, atLeast(1)).getDispositionType();
    verify(byteArrayPart, atLeast(1)).getName();
    verify(byteArrayPart, atLeast(1)).getTransferEncoding();
    assertEquals(1L, actualTransferToResult);
  }
}
