package org.asynchttpclient.request.body.multipart;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.ArgumentMatchers.isNull;
import static org.mockito.Mockito.anyInt;
import static org.mockito.Mockito.atLeast;
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
import io.netty.handler.codec.http.DefaultHttpHeaders;
import java.io.ByteArrayInputStream;
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
import org.asynchttpclient.request.body.multipart.part.InputStreamMultipartPart;
import org.asynchttpclient.request.body.multipart.part.MultipartPart;
import org.asynchttpclient.request.body.multipart.part.MultipartState;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class MultipartBodyDiffblueTest {
  @Mock private List<MultipartPart<? extends Part>> list;

  @InjectMocks private MultipartBody multipartBody;

  /**
   * Test {@link MultipartBody#MultipartBody(List, String, byte[])}.
   *
   * <ul>
   *   <li>Given {@code A}.
   *   <li>Then return ContentLength is minus one.
   * </ul>
   *
   * <p>Method under test: {@link MultipartBody#MultipartBody(List, String, byte[])}
   */
  @Test
  @DisplayName(
      "Test new MultipartBody(List, String, byte[]); given 'A'; then return ContentLength is minus one")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void MultipartBody.<init>(List, String, byte[])"})
  void testNewMultipartBody_givenA_thenReturnContentLengthIsMinusOne()
      throws UnsupportedEncodingException {
    // Arrange
    ArrayList<MultipartPart<? extends Part>> parts = new ArrayList<>();
    ByteArrayInputStream inputStream =
        new ByteArrayInputStream(new byte[] {'A', 1, 'A', 1, 'A', 1, 'A', 1});
    InputStreamPart part =
        new InputStreamPart(
            "https://example.org/example", inputStream, "https://example.org/example");
    InputStreamMultipartPart inputStreamMultipartPart =
        new InputStreamMultipartPart(part, new byte[] {'A', 1, 'A', 1, 'A', 1, 'A', 1});
    parts.add(inputStreamMultipartPart);

    // Act
    MultipartBody actualMultipartBody =
        new MultipartBody(parts, "https://example.org/example", "AXAXAXAX".getBytes("UTF-8"));

    // Assert
    assertEquals("https://example.org/example", actualMultipartBody.getContentType());
    assertEquals(-1L, actualMultipartBody.getContentLength());
    assertArrayEquals("AXAXAXAX".getBytes("UTF-8"), actualMultipartBody.getBoundary());
  }

  /**
   * Test {@link MultipartBody#MultipartBody(List, String, byte[])}.
   *
   * <ul>
   *   <li>Given {@code null}.
   *   <li>When {@link ArrayList#ArrayList()} add {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link MultipartBody#MultipartBody(List, String, byte[])}
   */
  @Test
  @DisplayName(
      "Test new MultipartBody(List, String, byte[]); given 'null'; when ArrayList() add 'null'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void MultipartBody.<init>(List, String, byte[])"})
  void testNewMultipartBody_givenNull_whenArrayListAddNull() throws UnsupportedEncodingException {
    // Arrange
    ArrayList<MultipartPart<? extends Part>> parts = new ArrayList<>();
    parts.add(null);

    // Act
    MultipartBody actualMultipartBody =
        new MultipartBody(parts, "https://example.org/example", "AXAXAXAX".getBytes("UTF-8"));

    // Assert
    assertEquals("https://example.org/example", actualMultipartBody.getContentType());
    assertEquals(0L, actualMultipartBody.getContentLength());
    assertArrayEquals("AXAXAXAX".getBytes("UTF-8"), actualMultipartBody.getBoundary());
  }

  /**
   * Test {@link MultipartBody#MultipartBody(List, String, byte[])}.
   *
   * <ul>
   *   <li>Then return ContentLength is one hundred thirty-two.
   * </ul>
   *
   * <p>Method under test: {@link MultipartBody#MultipartBody(List, String, byte[])}
   */
  @Test
  @DisplayName(
      "Test new MultipartBody(List, String, byte[]); then return ContentLength is one hundred thirty-two")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void MultipartBody.<init>(List, String, byte[])"})
  void testNewMultipartBody_thenReturnContentLengthIsOneHundredThirtyTwo()
      throws UnsupportedEncodingException {
    // Arrange
    ArrayList<MultipartPart<? extends Part>> parts = new ArrayList<>();
    ByteArrayPart part =
        new ByteArrayPart("https://example.org/example", "AXAXAXAX".getBytes("UTF-8"));
    ByteArrayMultipartPart byteArrayMultipartPart =
        new ByteArrayMultipartPart(part, "AXAXAXAX".getBytes("UTF-8"));
    parts.add(byteArrayMultipartPart);

    // Act
    MultipartBody actualMultipartBody =
        new MultipartBody(parts, "https://example.org/example", "AXAXAXAX".getBytes("UTF-8"));

    // Assert
    assertEquals("https://example.org/example", actualMultipartBody.getContentType());
    assertEquals(132L, actualMultipartBody.getContentLength());
    assertArrayEquals("AXAXAXAX".getBytes("UTF-8"), actualMultipartBody.getBoundary());
  }

  /**
   * Test {@link MultipartBody#MultipartBody(List, String, byte[])}.
   *
   * <ul>
   *   <li>Then return ContentLength is two hundred sixty-four.
   * </ul>
   *
   * <p>Method under test: {@link MultipartBody#MultipartBody(List, String, byte[])}
   */
  @Test
  @DisplayName(
      "Test new MultipartBody(List, String, byte[]); then return ContentLength is two hundred sixty-four")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void MultipartBody.<init>(List, String, byte[])"})
  void testNewMultipartBody_thenReturnContentLengthIsTwoHundredSixtyFour()
      throws UnsupportedEncodingException {
    // Arrange
    ArrayList<MultipartPart<? extends Part>> parts = new ArrayList<>();
    ByteArrayPart part =
        new ByteArrayPart("https://example.org/example", "AXAXAXAX".getBytes("UTF-8"));
    ByteArrayMultipartPart byteArrayMultipartPart =
        new ByteArrayMultipartPart(part, "AXAXAXAX".getBytes("UTF-8"));
    parts.add(byteArrayMultipartPart);
    ByteArrayPart part2 =
        new ByteArrayPart("https://example.org/example", "AXAXAXAX".getBytes("UTF-8"));
    ByteArrayMultipartPart byteArrayMultipartPart2 =
        new ByteArrayMultipartPart(part2, "AXAXAXAX".getBytes("UTF-8"));
    parts.add(byteArrayMultipartPart2);

    // Act
    MultipartBody actualMultipartBody =
        new MultipartBody(parts, "https://example.org/example", "AXAXAXAX".getBytes("UTF-8"));

    // Assert
    assertEquals("https://example.org/example", actualMultipartBody.getContentType());
    assertEquals(264L, actualMultipartBody.getContentLength());
    assertArrayEquals("AXAXAXAX".getBytes("UTF-8"), actualMultipartBody.getBoundary());
  }

  /**
   * Test {@link MultipartBody#MultipartBody(List, String, byte[])}.
   *
   * <ul>
   *   <li>When {@link ArrayList#ArrayList()}.
   *   <li>Then return ContentLength is zero.
   * </ul>
   *
   * <p>Method under test: {@link MultipartBody#MultipartBody(List, String, byte[])}
   */
  @Test
  @DisplayName(
      "Test new MultipartBody(List, String, byte[]); when ArrayList(); then return ContentLength is zero")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void MultipartBody.<init>(List, String, byte[])"})
  void testNewMultipartBody_whenArrayList_thenReturnContentLengthIsZero()
      throws UnsupportedEncodingException {
    // Arrange and Act
    MultipartBody actualMultipartBody =
        new MultipartBody(
            new ArrayList<>(), "https://example.org/example", "AXAXAXAX".getBytes("UTF-8"));

    // Assert
    assertEquals("https://example.org/example", actualMultipartBody.getContentType());
    assertEquals(0L, actualMultipartBody.getContentLength());
    assertArrayEquals("AXAXAXAX".getBytes("UTF-8"), actualMultipartBody.getBoundary());
  }

  /**
   * Test getters and setters.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link MultipartBody#getBoundary()}
   *   <li>{@link MultipartBody#getContentLength()}
   *   <li>{@link MultipartBody#getContentType()}
   * </ul>
   */
  @Test
  @DisplayName("Test getters and setters")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "byte[] MultipartBody.getBoundary()",
    "long MultipartBody.getContentLength()",
    "String MultipartBody.getContentType()"
  })
  void testGettersAndSetters() throws UnsupportedEncodingException {
    // Arrange
    MultipartBody multipartBody =
        new MultipartBody(
            new ArrayList<>(), "https://example.org/example", "AXAXAXAX".getBytes("UTF-8"));

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
   *
   * <p>Method under test: {@link MultipartBody#transferTo(ByteBuf)}
   */
  @Test
  @DisplayName("Test transferTo(ByteBuf) with 'ByteBuf'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"BodyState MultipartBody.transferTo(ByteBuf)"})
  void testTransferToWithByteBuf() throws IOException {
    // Arrange
    ArrayList<Part> parts = new ArrayList<>();
    MultipartBody newMultipartBodyResult =
        MultipartUtils.newMultipartBody(parts, new DefaultHttpHeaders());
    DuplicatedByteBuf target =
        new DuplicatedByteBuf(new EmptyByteBuf(new AdaptiveByteBufAllocator()));

    // Act
    BodyState actualTransferToResult = newMultipartBodyResult.transferTo(target);

    // Assert
    assertEquals(0, target.readerIndex());
    assertEquals(0, target.writerIndex());
    assertEquals(BodyState.CONTINUE, actualTransferToResult);
    assertFalse(target.isWritable());
  }

  /**
   * Test {@link MultipartBody#transferTo(WritableByteChannel)} with {@code WritableByteChannel}.
   *
   * <p>Method under test: {@link MultipartBody#transferTo(WritableByteChannel)}
   */
  @Test
  @DisplayName("Test transferTo(WritableByteChannel) with 'WritableByteChannel'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
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
    MultipartBody newMultipartBodyResult =
        MultipartUtils.newMultipartBody(parts, new DefaultHttpHeaders());

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
   *
   * <p>Method under test: {@link MultipartBody#transferTo(WritableByteChannel)}
   */
  @Test
  @DisplayName("Test transferTo(WritableByteChannel) with 'WritableByteChannel'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
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
    ByteArrayPart byteArrayPart2 =
        new ByteArrayPart("https://example.org/example", "AXAXAXAX".getBytes("UTF-8"));
    parts.add(byteArrayPart2);
    parts.add(byteArrayPart);
    MultipartBody newMultipartBodyResult =
        MultipartUtils.newMultipartBody(parts, new DefaultHttpHeaders());

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
   *
   * <p>Method under test: {@link MultipartBody#transferTo(WritableByteChannel)}
   */
  @Test
  @DisplayName("Test transferTo(WritableByteChannel) with 'WritableByteChannel'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"long MultipartBody.transferTo(WritableByteChannel)"})
  void testTransferToWithWritableByteChannel3() throws IOException {
    // Arrange
    ByteArrayMultipartPart byteArrayMultipartPart = mock(ByteArrayMultipartPart.class);
    when(byteArrayMultipartPart.isTargetSlow()).thenReturn(true);
    when(byteArrayMultipartPart.transferTo(Mockito.<WritableByteChannel>any())).thenReturn(1L);
    when(byteArrayMultipartPart.getState()).thenReturn(MultipartState.PRE_CONTENT);
    Mockito.<MultipartPart<? extends Part>>when(list.get(anyInt()))
        .thenReturn(byteArrayMultipartPart);

    // Act
    long actualTransferToResult = multipartBody.transferTo((WritableByteChannel) null);

    // Assert
    verify(list).get(0);
    verify(byteArrayMultipartPart).getState();
    verify(byteArrayMultipartPart).isTargetSlow();
    verify(byteArrayMultipartPart).transferTo((WritableByteChannel) isNull());
    assertEquals(1L, actualTransferToResult);
  }

  /**
   * Test {@link MultipartBody#transferTo(WritableByteChannel)} with {@code WritableByteChannel}.
   *
   * <ul>
   *   <li>Given {@link List} {@link List#size()} return one.
   *   <li>Then calls {@link List#size()}.
   * </ul>
   *
   * <p>Method under test: {@link MultipartBody#transferTo(WritableByteChannel)}
   */
  @Test
  @DisplayName(
      "Test transferTo(WritableByteChannel) with 'WritableByteChannel'; given List size() return one; then calls size()")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"long MultipartBody.transferTo(WritableByteChannel)"})
  void testTransferToWithWritableByteChannel_givenListSizeReturnOne_thenCallsSize()
      throws IOException {
    // Arrange
    ByteArrayMultipartPart byteArrayMultipartPart = mock(ByteArrayMultipartPart.class);
    when(byteArrayMultipartPart.isTargetSlow()).thenReturn(true);
    when(byteArrayMultipartPart.transferTo(Mockito.<WritableByteChannel>any())).thenReturn(1L);
    when(byteArrayMultipartPart.getState()).thenReturn(MultipartState.DONE);
    when(list.size()).thenReturn(1);
    Mockito.<MultipartPart<? extends Part>>when(list.get(anyInt()))
        .thenReturn(byteArrayMultipartPart);

    // Act
    long actualTransferToResult = multipartBody.transferTo((WritableByteChannel) null);

    // Assert
    verify(list).get(0);
    verify(list).size();
    verify(byteArrayMultipartPart).getState();
    verify(byteArrayMultipartPart).isTargetSlow();
    verify(byteArrayMultipartPart).transferTo((WritableByteChannel) isNull());
    assertEquals(1L, actualTransferToResult);
  }

  /**
   * Test {@link MultipartBody#transferTo(WritableByteChannel)} with {@code WritableByteChannel}.
   *
   * <ul>
   *   <li>Given {@link List} {@link List#size()} return three.
   *   <li>Then calls {@link List#size()}.
   * </ul>
   *
   * <p>Method under test: {@link MultipartBody#transferTo(WritableByteChannel)}
   */
  @Test
  @DisplayName(
      "Test transferTo(WritableByteChannel) with 'WritableByteChannel'; given List size() return three; then calls size()")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"long MultipartBody.transferTo(WritableByteChannel)"})
  void testTransferToWithWritableByteChannel_givenListSizeReturnThree_thenCallsSize()
      throws IOException {
    // Arrange
    ByteArrayMultipartPart byteArrayMultipartPart = mock(ByteArrayMultipartPart.class);
    when(byteArrayMultipartPart.isTargetSlow()).thenReturn(true);
    when(byteArrayMultipartPart.transferTo(Mockito.<WritableByteChannel>any())).thenReturn(1L);
    when(byteArrayMultipartPart.getState()).thenReturn(MultipartState.DONE);
    when(list.size()).thenReturn(3);
    Mockito.<MultipartPart<? extends Part>>when(list.get(anyInt()))
        .thenReturn(byteArrayMultipartPart);

    // Act
    long actualTransferToResult = multipartBody.transferTo((WritableByteChannel) null);

    // Assert
    verify(list).get(0);
    verify(list).size();
    verify(byteArrayMultipartPart).getState();
    verify(byteArrayMultipartPart).isTargetSlow();
    verify(byteArrayMultipartPart).transferTo((WritableByteChannel) isNull());
    assertEquals(1L, actualTransferToResult);
  }

  /**
   * Test {@link MultipartBody#transferTo(WritableByteChannel)} with {@code WritableByteChannel}.
   *
   * <ul>
   *   <li>Given one.
   * </ul>
   *
   * <p>Method under test: {@link MultipartBody#transferTo(WritableByteChannel)}
   */
  @Test
  @DisplayName("Test transferTo(WritableByteChannel) with 'WritableByteChannel'; given one")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"long MultipartBody.transferTo(WritableByteChannel)"})
  void testTransferToWithWritableByteChannel_givenOne() throws IOException {
    // Arrange
    ArrayList<Part> parts = new ArrayList<>();
    MultipartBody newMultipartBodyResult =
        MultipartUtils.newMultipartBody(parts, new DefaultHttpHeaders());

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
   *
   * <ul>
   *   <li>Given zero.
   *   <li>Then return zero.
   * </ul>
   *
   * <p>Method under test: {@link MultipartBody#transferTo(WritableByteChannel)}
   */
  @Test
  @DisplayName(
      "Test transferTo(WritableByteChannel) with 'WritableByteChannel'; given zero; then return zero")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"long MultipartBody.transferTo(WritableByteChannel)"})
  void testTransferToWithWritableByteChannel_givenZero_thenReturnZero() throws IOException {
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
    MultipartBody newMultipartBodyResult =
        MultipartUtils.newMultipartBody(parts, new DefaultHttpHeaders());

    BrotliEncoderChannel target = mock(BrotliEncoderChannel.class);
    when(target.write(Mockito.<ByteBuffer>any())).thenReturn(0);

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
    assertEquals(0L, actualTransferToResult);
  }

  /**
   * Test {@link MultipartBody#transferTo(WritableByteChannel)} with {@code WritableByteChannel}.
   *
   * <ul>
   *   <li>Then calls {@link ByteArrayPart#getBytes()}.
   * </ul>
   *
   * <p>Method under test: {@link MultipartBody#transferTo(WritableByteChannel)}
   */
  @Test
  @DisplayName(
      "Test transferTo(WritableByteChannel) with 'WritableByteChannel'; then calls getBytes()")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"long MultipartBody.transferTo(WritableByteChannel)"})
  void testTransferToWithWritableByteChannel_thenCallsGetBytes() throws IOException {
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
    MultipartBody newMultipartBodyResult =
        MultipartUtils.newMultipartBody(parts, new DefaultHttpHeaders());

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
   *
   * <ul>
   *   <li>Then return {@code 8192}.
   * </ul>
   *
   * <p>Method under test: {@link MultipartBody#transferTo(WritableByteChannel)}
   */
  @Test
  @DisplayName(
      "Test transferTo(WritableByteChannel) with 'WritableByteChannel'; then return '8192'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"long MultipartBody.transferTo(WritableByteChannel)"})
  void testTransferToWithWritableByteChannel_thenReturn8192() throws IOException {
    // Arrange
    ByteArrayMultipartPart byteArrayMultipartPart = mock(ByteArrayMultipartPart.class);
    when(byteArrayMultipartPart.isTargetSlow()).thenReturn(true);
    when(byteArrayMultipartPart.transferTo(Mockito.<WritableByteChannel>any())).thenReturn(8192L);
    when(byteArrayMultipartPart.getState()).thenReturn(MultipartState.PRE_CONTENT);
    Mockito.<MultipartPart<? extends Part>>when(list.get(anyInt()))
        .thenReturn(byteArrayMultipartPart);

    // Act
    long actualTransferToResult = multipartBody.transferTo((WritableByteChannel) null);

    // Assert
    verify(list).get(0);
    verify(byteArrayMultipartPart).getState();
    verify(byteArrayMultipartPart).isTargetSlow();
    verify(byteArrayMultipartPart).transferTo((WritableByteChannel) isNull());
    assertEquals(8192L, actualTransferToResult);
  }
}
