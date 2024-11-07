package org.asynchttpclient.request.body.multipart.part;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.anyInt;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.aayushatharva.brotli4j.encoder.BrotliEncoderChannel;
import io.netty.buffer.AdaptiveByteBufAllocator;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.DuplicatedByteBuf;
import io.netty.buffer.EmptyByteBuf;
import io.netty.buffer.ReadOnlyByteBuf;
import io.netty.buffer.Unpooled;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.nio.channels.WritableByteChannel;
import java.nio.charset.Charset;
import java.util.ArrayList;
import org.apache.tomcat.util.net.NioChannel;
import org.apache.tomcat.util.net.SocketBufferHandler;
import org.asynchttpclient.request.body.multipart.ByteArrayPart;
import org.asynchttpclient.request.body.multipart.part.PartVisitor.CounterPartVisitor;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

class MultipartPartDiffblueTest {
  /**
   * Test {@link MultipartPart#length()}.
   * <p>
   * Method under test: {@link MultipartPart#length()}
   */
  @Test
  @DisplayName("Test length()")
  void testLength() throws UnsupportedEncodingException {
    // Arrange, Act and Assert
    assertEquals(14L, (new MessageEndMultipartPart("AXAXAXAX".getBytes("UTF-8"))).length());
  }

  /**
   * Test {@link MultipartPart#getState()}.
   * <p>
   * Method under test: {@link MultipartPart#getState()}
   */
  @Test
  @DisplayName("Test getState()")
  void testGetState() throws UnsupportedEncodingException {
    // Arrange, Act and Assert
    assertEquals(MultipartState.PRE_CONTENT, (new MessageEndMultipartPart("AXAXAXAX".getBytes("UTF-8"))).getState());
  }

  /**
   * Test {@link MultipartPart#isTargetSlow()}.
   * <p>
   * Method under test: {@link MultipartPart#isTargetSlow()}
   */
  @Test
  @DisplayName("Test isTargetSlow()")
  void testIsTargetSlow() throws UnsupportedEncodingException {
    // Arrange, Act and Assert
    assertFalse((new MessageEndMultipartPart("AXAXAXAX".getBytes("UTF-8"))).isTargetSlow());
  }

  /**
   * Test {@link MultipartPart#transferTo(ByteBuf)} with {@code ByteBuf}.
   * <p>
   * Method under test: {@link MultipartPart#transferTo(ByteBuf)}
   */
  @Test
  @DisplayName("Test transferTo(ByteBuf) with 'ByteBuf'")
  void testTransferToWithByteBuf() throws IOException {
    // Arrange
    ByteArrayPart part = new ByteArrayPart("https://example.org/example", "AXAXAXAX".getBytes("UTF-8"));

    ByteArrayMultipartPart byteArrayMultipartPart = new ByteArrayMultipartPart(part, "AXAXAXAX".getBytes("UTF-8"));

    // Act and Assert
    assertEquals(0L,
        byteArrayMultipartPart.transferTo(new DuplicatedByteBuf(new EmptyByteBuf(new AdaptiveByteBufAllocator()))));
  }

  /**
   * Test {@link MultipartPart#transferTo(ByteBuf)} with {@code ByteBuf}.
   * <p>
   * Method under test: {@link MultipartPart#transferTo(ByteBuf)}
   */
  @Test
  @DisplayName("Test transferTo(ByteBuf) with 'ByteBuf'")
  void testTransferToWithByteBuf2() throws IOException {
    // Arrange
    ByteArrayPart part = new ByteArrayPart(null, "AXAXAXAX".getBytes("UTF-8"));

    ByteArrayMultipartPart byteArrayMultipartPart = new ByteArrayMultipartPart(part, "AXAXAXAX".getBytes("UTF-8"));

    // Act and Assert
    assertEquals(0L,
        byteArrayMultipartPart.transferTo(new DuplicatedByteBuf(new EmptyByteBuf(new AdaptiveByteBufAllocator()))));
  }

  /**
   * Test {@link MultipartPart#transferTo(ByteBuf)} with {@code ByteBuf}.
   * <p>
   * Method under test: {@link MultipartPart#transferTo(ByteBuf)}
   */
  @Test
  @DisplayName("Test transferTo(ByteBuf) with 'ByteBuf'")
  void testTransferToWithByteBuf3() throws IOException {
    // Arrange
    ByteArrayPart part = new ByteArrayPart("https://example.org/example", "AXAXAXzX".getBytes("UTF-8"));

    ByteArrayMultipartPart byteArrayMultipartPart = new ByteArrayMultipartPart(part, "AXAXAXAX".getBytes("UTF-8"));

    // Act and Assert
    assertEquals(0L,
        byteArrayMultipartPart.transferTo(new DuplicatedByteBuf(new EmptyByteBuf(new AdaptiveByteBufAllocator()))));
  }

  /**
   * Test {@link MultipartPart#transferTo(ByteBuf)} with {@code ByteBuf}.
   * <p>
   * Method under test: {@link MultipartPart#transferTo(ByteBuf)}
   */
  @Test
  @DisplayName("Test transferTo(ByteBuf) with 'ByteBuf'")
  void testTransferToWithByteBuf4() throws IOException {
    // Arrange
    ByteArrayPart part = new ByteArrayPart("https://example.org/example", "AXAXAXAX".getBytes("UTF-8"));
    part.addCustomHeader("https://example.org/example", "https://example.org/example");
    ByteArrayMultipartPart byteArrayMultipartPart = new ByteArrayMultipartPart(part, "AXAXAXAX".getBytes("UTF-8"));

    // Act and Assert
    assertEquals(0L,
        byteArrayMultipartPart.transferTo(new DuplicatedByteBuf(new EmptyByteBuf(new AdaptiveByteBufAllocator()))));
  }

  /**
   * Test {@link MultipartPart#transferTo(ByteBuf)} with {@code ByteBuf}.
   * <p>
   * Method under test: {@link MultipartPart#transferTo(ByteBuf)}
   */
  @Test
  @DisplayName("Test transferTo(ByteBuf) with 'ByteBuf'")
  void testTransferToWithByteBuf5() throws IOException {
    // Arrange
    ByteArrayPart part = new ByteArrayPart("https://example.org/example", "AXAXAXAX".getBytes("UTF-8"));
    part.setDispositionType("https://example.org/example");
    part.addCustomHeader("https://example.org/example", "https://example.org/example");
    ByteArrayMultipartPart byteArrayMultipartPart = new ByteArrayMultipartPart(part, "AXAXAXAX".getBytes("UTF-8"));

    // Act and Assert
    assertEquals(0L,
        byteArrayMultipartPart.transferTo(new DuplicatedByteBuf(new EmptyByteBuf(new AdaptiveByteBufAllocator()))));
  }

  /**
   * Test {@link MultipartPart#transferTo(ByteBuf)} with {@code ByteBuf}.
   * <p>
   * Method under test: {@link MultipartPart#transferTo(ByteBuf)}
   */
  @Test
  @DisplayName("Test transferTo(ByteBuf) with 'ByteBuf'")
  void testTransferToWithByteBuf6() throws IOException {
    // Arrange
    ByteArrayPart part = new ByteArrayPart("https://example.org/example", "AXAXAXAX".getBytes("UTF-8"));

    ByteArrayMultipartPart byteArrayMultipartPart = new ByteArrayMultipartPart(part, "AXAXAXAX".getBytes("UTF-8"));

    // Act and Assert
    assertEquals(0L, byteArrayMultipartPart.transferTo(new EmptyByteBuf(new AdaptiveByteBufAllocator())));
  }

  /**
   * Test {@link MultipartPart#transferTo(ByteBuf)} with {@code ByteBuf}.
   * <ul>
   *   <li>Given {@code A}.</li>
   *   <li>When {@link DuplicatedByteBuf#DuplicatedByteBuf(ByteBuf)} with buffer is
   * {@link EmptyByteBuf#EmptyByteBuf(ByteBufAllocator)}.</li>
   * </ul>
   * <p>
   * Method under test: {@link MultipartPart#transferTo(ByteBuf)}
   */
  @Test
  @DisplayName("Test transferTo(ByteBuf) with 'ByteBuf'; given 'A'; when DuplicatedByteBuf(ByteBuf) with buffer is EmptyByteBuf(ByteBufAllocator)")
  void testTransferToWithByteBuf_givenA_whenDuplicatedByteBufWithBufferIsEmptyByteBuf() throws IOException {
    // Arrange
    ByteArrayPart part = new ByteArrayPart("https://example.org/example", new byte[]{'A', 1, 'A', 1, 'A', 1, 'A', 1},
        "https://example.org/example", Charset.forName("UTF-8"));

    ByteArrayMultipartPart byteArrayMultipartPart = new ByteArrayMultipartPart(part, "AXAXAXAX".getBytes("UTF-8"));

    // Act and Assert
    assertEquals(0L,
        byteArrayMultipartPart.transferTo(new DuplicatedByteBuf(new EmptyByteBuf(new AdaptiveByteBufAllocator()))));
  }

  /**
   * Test {@link MultipartPart#transferTo(WritableByteChannel)} with
   * {@code WritableByteChannel}.
   * <ul>
   *   <li>Given one.</li>
   *   <li>Then return one.</li>
   * </ul>
   * <p>
   * Method under test: {@link MultipartPart#transferTo(WritableByteChannel)}
   */
  @Test
  @DisplayName("Test transferTo(WritableByteChannel) with 'WritableByteChannel'; given one; then return one")
  void testTransferToWithWritableByteChannel_givenOne_thenReturnOne() throws IOException {
    // Arrange
    ByteArrayPart part = mock(ByteArrayPart.class);
    when(part.getCharset()).thenReturn(Charset.forName("UTF-8"));
    when(part.getBytes()).thenReturn("AXAXAXAX".getBytes("UTF-8"));
    when(part.getFileName()).thenReturn("https://example.org/example");
    when(part.getContentId()).thenReturn("https://example.org/example");
    when(part.getContentType()).thenReturn("text/plain");
    when(part.getDispositionType()).thenReturn("https://example.org/example");
    when(part.getName()).thenReturn("https://example.org/example");
    when(part.getTransferEncoding()).thenReturn("https://example.org/example");
    when(part.getCustomHeaders()).thenReturn(new ArrayList<>());
    ByteArrayMultipartPart byteArrayMultipartPart = new ByteArrayMultipartPart(part, "AXAXAXAX".getBytes("UTF-8"));
    BrotliEncoderChannel target = mock(BrotliEncoderChannel.class);
    when(target.write(Mockito.<ByteBuffer>any())).thenReturn(1);

    // Act
    long actualTransferToResult = byteArrayMultipartPart.transferTo(target);

    // Assert
    verify(target).write(isA(ByteBuffer.class));
    verify(part).getBytes();
    verify(part, atLeast(1)).getFileName();
    verify(part, atLeast(1)).getCharset();
    verify(part, atLeast(1)).getContentId();
    verify(part, atLeast(1)).getContentType();
    verify(part, atLeast(1)).getCustomHeaders();
    verify(part, atLeast(1)).getDispositionType();
    verify(part, atLeast(1)).getName();
    verify(part, atLeast(1)).getTransferEncoding();
    assertEquals(1L, actualTransferToResult);
    assertTrue(byteArrayMultipartPart.isTargetSlow());
  }

  /**
   * Test {@link MultipartPart#transfer(ByteBuf, ByteBuf, MultipartState)} with
   * {@code ByteBuf}, {@code ByteBuf}, {@code MultipartState}.
   * <p>
   * Method under test:
   * {@link MultipartPart#transfer(ByteBuf, ByteBuf, MultipartState)}
   */
  @Test
  @DisplayName("Test transfer(ByteBuf, ByteBuf, MultipartState) with 'ByteBuf', 'ByteBuf', 'MultipartState'")
  void testTransferWithByteBufByteBufMultipartState() throws UnsupportedEncodingException {
    // Arrange
    MessageEndMultipartPart messageEndMultipartPart = new MessageEndMultipartPart("AXAXAXAX".getBytes("UTF-8"));
    DuplicatedByteBuf source = new DuplicatedByteBuf(new EmptyByteBuf(new AdaptiveByteBufAllocator()));

    // Act and Assert
    assertEquals(0L, messageEndMultipartPart.transfer(source, new DuplicatedByteBuf(Unpooled.compositeBuffer(3)),
        MultipartState.PRE_CONTENT));
  }

  /**
   * Test {@link MultipartPart#transfer(ByteBuf, ByteBuf, MultipartState)} with
   * {@code ByteBuf}, {@code ByteBuf}, {@code MultipartState}.
   * <ul>
   *   <li>Then return zero.</li>
   * </ul>
   * <p>
   * Method under test:
   * {@link MultipartPart#transfer(ByteBuf, ByteBuf, MultipartState)}
   */
  @Test
  @DisplayName("Test transfer(ByteBuf, ByteBuf, MultipartState) with 'ByteBuf', 'ByteBuf', 'MultipartState'; then return zero")
  void testTransferWithByteBufByteBufMultipartState_thenReturnZero() throws UnsupportedEncodingException {
    // Arrange
    MessageEndMultipartPart messageEndMultipartPart = new MessageEndMultipartPart("AXAXAXAX".getBytes("UTF-8"));
    DuplicatedByteBuf source = new DuplicatedByteBuf(new EmptyByteBuf(new AdaptiveByteBufAllocator()));

    // Act and Assert
    assertEquals(0L, messageEndMultipartPart.transfer(source,
        new DuplicatedByteBuf(new EmptyByteBuf(new AdaptiveByteBufAllocator())), MultipartState.PRE_CONTENT));
  }

  /**
   * Test
   * {@link MultipartPart#transfer(ByteBuf, WritableByteChannel, MultipartState)}
   * with {@code ByteBuf}, {@code WritableByteChannel}, {@code MultipartState}.
   * <p>
   * Method under test:
   * {@link MultipartPart#transfer(ByteBuf, WritableByteChannel, MultipartState)}
   */
  @Test
  @DisplayName("Test transfer(ByteBuf, WritableByteChannel, MultipartState) with 'ByteBuf', 'WritableByteChannel', 'MultipartState'")
  void testTransferWithByteBufWritableByteChannelMultipartState() throws IOException {
    // Arrange
    MessageEndMultipartPart messageEndMultipartPart = new MessageEndMultipartPart("AXAXAXAX".getBytes("UTF-8"));
    DuplicatedByteBuf source = new DuplicatedByteBuf(new EmptyByteBuf(new AdaptiveByteBufAllocator()));

    // Act and Assert
    assertEquals(0L, messageEndMultipartPart.transfer(source, new NioChannel(new SocketBufferHandler(3, 3, true)),
        MultipartState.PRE_CONTENT));
  }

  /**
   * Test
   * {@link MultipartPart#transfer(ByteBuf, WritableByteChannel, MultipartState)}
   * with {@code ByteBuf}, {@code WritableByteChannel}, {@code MultipartState}.
   * <ul>
   *   <li>Then calls {@link ByteBuf#capacity()}.</li>
   * </ul>
   * <p>
   * Method under test:
   * {@link MultipartPart#transfer(ByteBuf, WritableByteChannel, MultipartState)}
   */
  @Test
  @DisplayName("Test transfer(ByteBuf, WritableByteChannel, MultipartState) with 'ByteBuf', 'WritableByteChannel', 'MultipartState'; then calls capacity()")
  void testTransferWithByteBufWritableByteChannelMultipartState_thenCallsCapacity() throws IOException {
    // Arrange
    MessageEndMultipartPart messageEndMultipartPart = new MessageEndMultipartPart("AXAXAXAX".getBytes("UTF-8"));
    ByteBuf buffer = mock(ByteBuf.class);
    when(buffer.capacity()).thenReturn(3);
    when(buffer.maxCapacity()).thenReturn(3);
    when(buffer.readerIndex()).thenReturn(1);
    when(buffer.writerIndex()).thenReturn(1);
    when(buffer.nioBuffers(anyInt(), anyInt())).thenReturn(new ByteBuffer[]{});

    // Act
    long actualTransferResult = messageEndMultipartPart.transfer(
        new DuplicatedByteBuf(new ReadOnlyByteBuf(new DuplicatedByteBuf(buffer))), (WritableByteChannel) null,
        MultipartState.PRE_CONTENT);

    // Assert
    verify(buffer, atLeast(1)).capacity();
    verify(buffer).maxCapacity();
    verify(buffer).nioBuffers(eq(1), eq(0));
    verify(buffer).readerIndex();
    verify(buffer).writerIndex();
    assertEquals(0L, actualTransferResult);
  }

  /**
   * Test {@link MultipartPart#computePreContentLength()}.
   * <p>
   * Method under test: {@link MultipartPart#computePreContentLength()}
   */
  @Test
  @DisplayName("Test computePreContentLength()")
  void testComputePreContentLength() throws UnsupportedEncodingException {
    // Arrange
    ByteArrayPart part = new ByteArrayPart("https://example.org/example", "AXAXAXAX".getBytes("UTF-8"));

    // Act and Assert
    assertEquals(122, (new ByteArrayMultipartPart(part, "AXAXAXAX".getBytes("UTF-8"))).computePreContentLength());
  }

  /**
   * Test {@link MultipartPart#computePreContentLength()}.
   * <p>
   * Method under test: {@link MultipartPart#computePreContentLength()}
   */
  @Test
  @DisplayName("Test computePreContentLength()")
  void testComputePreContentLength2() throws UnsupportedEncodingException {
    // Arrange
    ByteArrayPart part = new ByteArrayPart("https://example.org/example", "AXAXAXAX".getBytes("UTF-8"));
    part.setCustomHeaders(new ArrayList<>());

    // Act and Assert
    assertEquals(122, (new ByteArrayMultipartPart(part, "AXAXAXAX".getBytes("UTF-8"))).computePreContentLength());
  }

  /**
   * Test {@link MultipartPart#computePreContentLength()}.
   * <ul>
   *   <li>Then return eighty-six.</li>
   * </ul>
   * <p>
   * Method under test: {@link MultipartPart#computePreContentLength()}
   */
  @Test
  @DisplayName("Test computePreContentLength(); then return eighty-six")
  void testComputePreContentLength_thenReturnEightySix() throws UnsupportedEncodingException {
    // Arrange
    ByteArrayPart part = new ByteArrayPart(null, "AXAXAXAX".getBytes("UTF-8"));

    // Act and Assert
    assertEquals(86, (new ByteArrayMultipartPart(part, "AXAXAXAX".getBytes("UTF-8"))).computePreContentLength());
  }

  /**
   * Test {@link MultipartPart#computePreContentLength()}.
   * <ul>
   *   <li>Then return one hundred eighty.</li>
   * </ul>
   * <p>
   * Method under test: {@link MultipartPart#computePreContentLength()}
   */
  @Test
  @DisplayName("Test computePreContentLength(); then return one hundred eighty")
  void testComputePreContentLength_thenReturnOneHundredEighty() throws UnsupportedEncodingException {
    // Arrange
    ByteArrayPart part = new ByteArrayPart("https://example.org/example", "AXAXAXAX".getBytes("UTF-8"));
    part.addCustomHeader("https://example.org/example", "https://example.org/example");

    // Act and Assert
    assertEquals(180, (new ByteArrayMultipartPart(part, "AXAXAXAX".getBytes("UTF-8"))).computePreContentLength());
  }

  /**
   * Test {@link MultipartPart#computePreContentLength()}.
   * <ul>
   *   <li>Then return one hundred forty.</li>
   * </ul>
   * <p>
   * Method under test: {@link MultipartPart#computePreContentLength()}
   */
  @Test
  @DisplayName("Test computePreContentLength(); then return one hundred forty")
  void testComputePreContentLength_thenReturnOneHundredForty() throws UnsupportedEncodingException {
    // Arrange
    byte[] bytes = "A\"A\"A\"A\"".getBytes("UTF-8");
    ByteArrayPart part = new ByteArrayPart("https://example.org/example", bytes, "https://example.org/example",
        Charset.forName("UTF-8"));

    // Act and Assert
    assertEquals(140, (new ByteArrayMultipartPart(part, "AXAXAXAX".getBytes("UTF-8"))).computePreContentLength());
  }

  /**
   * Test {@link MultipartPart#computePreContentLength()}.
   * <ul>
   *   <li>Then return one hundred ninety-eight.</li>
   * </ul>
   * <p>
   * Method under test: {@link MultipartPart#computePreContentLength()}
   */
  @Test
  @DisplayName("Test computePreContentLength(); then return one hundred ninety-eight")
  void testComputePreContentLength_thenReturnOneHundredNinetyEight() throws UnsupportedEncodingException {
    // Arrange
    ByteArrayPart part = new ByteArrayPart("https://example.org/example", "AXAXAXAX".getBytes("UTF-8"));
    part.setDispositionType("https://example.org/example");
    part.addCustomHeader("https://example.org/example", "https://example.org/example");

    // Act and Assert
    assertEquals(198, (new ByteArrayMultipartPart(part, "AXAXAXAX".getBytes("UTF-8"))).computePreContentLength());
  }

  /**
   * Test {@link MultipartPart#computePreContentBytes(int)}.
   * <p>
   * Method under test: {@link MultipartPart#computePreContentBytes(int)}
   */
  @Test
  @DisplayName("Test computePreContentBytes(int)")
  void testComputePreContentBytes() throws UnsupportedEncodingException {
    // Arrange
    ByteArrayPart part = new ByteArrayPart("https://example.org/example", "AXAXAXAX".getBytes("UTF-8"));

    // Act and Assert
    assertTrue(
        (new ByteArrayMultipartPart(part, "AXAXAXAX".getBytes("UTF-8"))).computePreContentBytes(3).isContiguous());
  }

  /**
   * Test {@link MultipartPart#computePreContentBytes(int)}.
   * <p>
   * Method under test: {@link MultipartPart#computePreContentBytes(int)}
   */
  @Test
  @DisplayName("Test computePreContentBytes(int)")
  void testComputePreContentBytes2() throws UnsupportedEncodingException {
    // Arrange
    ByteArrayPart part = new ByteArrayPart(null, "AXAXAXAX".getBytes("UTF-8"));

    // Act and Assert
    assertTrue(
        (new ByteArrayMultipartPart(part, "AXAXAXAX".getBytes("UTF-8"))).computePreContentBytes(3).isContiguous());
  }

  /**
   * Test {@link MultipartPart#computePreContentBytes(int)}.
   * <p>
   * Method under test: {@link MultipartPart#computePreContentBytes(int)}
   */
  @Test
  @DisplayName("Test computePreContentBytes(int)")
  void testComputePreContentBytes3() throws UnsupportedEncodingException {
    // Arrange
    byte[] bytes = "A\"A\"A\"A\"".getBytes("UTF-8");
    ByteArrayPart part = new ByteArrayPart("https://example.org/example", bytes, "https://example.org/example",
        Charset.forName("UTF-8"));

    // Act and Assert
    assertTrue(
        (new ByteArrayMultipartPart(part, "AXAXAXAX".getBytes("UTF-8"))).computePreContentBytes(3).isContiguous());
  }

  /**
   * Test {@link MultipartPart#computePreContentBytes(int)}.
   * <p>
   * Method under test: {@link MultipartPart#computePreContentBytes(int)}
   */
  @Test
  @DisplayName("Test computePreContentBytes(int)")
  void testComputePreContentBytes4() throws UnsupportedEncodingException {
    // Arrange
    ByteArrayPart part = new ByteArrayPart("https://example.org/example", "AXAXAXAX".getBytes("UTF-8"));
    part.addCustomHeader("https://example.org/example", "https://example.org/example");

    // Act and Assert
    assertTrue(
        (new ByteArrayMultipartPart(part, "AXAXAXAX".getBytes("UTF-8"))).computePreContentBytes(3).isContiguous());
  }

  /**
   * Test {@link MultipartPart#computePreContentBytes(int)}.
   * <p>
   * Method under test: {@link MultipartPart#computePreContentBytes(int)}
   */
  @Test
  @DisplayName("Test computePreContentBytes(int)")
  void testComputePreContentBytes5() throws UnsupportedEncodingException {
    // Arrange
    ByteArrayPart part = new ByteArrayPart("https://example.org/example", "AXAXAXAX".getBytes("UTF-8"));
    part.setDispositionType("https://example.org/example");
    part.addCustomHeader("https://example.org/example", "https://example.org/example");

    // Act and Assert
    assertTrue(
        (new ByteArrayMultipartPart(part, "AXAXAXAX".getBytes("UTF-8"))).computePreContentBytes(3).isContiguous());
  }

  /**
   * Test {@link MultipartPart#computePreContentBytes(int)}.
   * <p>
   * Method under test: {@link MultipartPart#computePreContentBytes(int)}
   */
  @Test
  @DisplayName("Test computePreContentBytes(int)")
  void testComputePreContentBytes6() throws UnsupportedEncodingException {
    // Arrange
    ByteArrayPart part = new ByteArrayPart("https://example.org/example", "AXAXAXAX".getBytes("UTF-8"));
    part.setCustomHeaders(new ArrayList<>());

    // Act and Assert
    assertTrue(
        (new ByteArrayMultipartPart(part, "AXAXAXAX".getBytes("UTF-8"))).computePreContentBytes(3).isContiguous());
  }

  /**
   * Test {@link MultipartPart#computePreContentBytes(int)}.
   * <ul>
   *   <li>Given {@code A}.</li>
   *   <li>When three.</li>
   *   <li>Then return Contiguous.</li>
   * </ul>
   * <p>
   * Method under test: {@link MultipartPart#computePreContentBytes(int)}
   */
  @Test
  @DisplayName("Test computePreContentBytes(int); given 'A'; when three; then return Contiguous")
  void testComputePreContentBytes_givenA_whenThree_thenReturnContiguous() throws UnsupportedEncodingException {
    // Arrange
    ByteArrayPart part = new ByteArrayPart("https://example.org/example",
        new byte[]{'A', 'X', 'A', -1, 'A', 'X', 'A', 'X'});

    // Act and Assert
    assertTrue(
        (new ByteArrayMultipartPart(part, "AXAXAXAX".getBytes("UTF-8"))).computePreContentBytes(3).isContiguous());
  }

  /**
   * Test {@link MultipartPart#computePostContentLength()}.
   * <p>
   * Method under test: {@link MultipartPart#computePostContentLength()}
   */
  @Test
  @DisplayName("Test computePostContentLength()")
  void testComputePostContentLength() throws UnsupportedEncodingException {
    // Arrange
    ByteArrayPart part = new ByteArrayPart("https://example.org/example", "AXAXAXAX".getBytes("UTF-8"));

    // Act and Assert
    assertEquals(2, (new ByteArrayMultipartPart(part, "AXAXAXAX".getBytes("UTF-8"))).computePostContentLength());
  }

  /**
   * Test {@link MultipartPart#computePostContentBytes(int)}.
   * <ul>
   *   <li>Given {@code A}.</li>
   *   <li>When three.</li>
   *   <li>Then return Contiguous.</li>
   * </ul>
   * <p>
   * Method under test: {@link MultipartPart#computePostContentBytes(int)}
   */
  @Test
  @DisplayName("Test computePostContentBytes(int); given 'A'; when three; then return Contiguous")
  void testComputePostContentBytes_givenA_whenThree_thenReturnContiguous() throws UnsupportedEncodingException {
    // Arrange
    ByteArrayPart part = new ByteArrayPart("https://example.org/example",
        new byte[]{'A', 'X', 'A', 'X', Byte.MAX_VALUE, 'X', 'A', 'X'});

    // Act and Assert
    assertTrue(
        (new ByteArrayMultipartPart(part, "AXAXAXAX".getBytes("UTF-8"))).computePostContentBytes(3).isContiguous());
  }

  /**
   * Test {@link MultipartPart#computePostContentBytes(int)}.
   * <ul>
   *   <li>Then return Contiguous.</li>
   * </ul>
   * <p>
   * Method under test: {@link MultipartPart#computePostContentBytes(int)}
   */
  @Test
  @DisplayName("Test computePostContentBytes(int); then return Contiguous")
  void testComputePostContentBytes_thenReturnContiguous() throws UnsupportedEncodingException {
    // Arrange
    ByteArrayPart part = new ByteArrayPart("https://example.org/example", "AXAXAXAX".getBytes("UTF-8"));

    // Act and Assert
    assertTrue(
        (new ByteArrayMultipartPart(part, "AXAXAXAX".getBytes("UTF-8"))).computePostContentBytes(3).isContiguous());
  }

  /**
   * Test {@link MultipartPart#visitStart(PartVisitor)}.
   * <ul>
   *   <li>When {@link CounterPartVisitor} (default constructor).</li>
   *   <li>Then {@link CounterPartVisitor} (default constructor) Count is ten.</li>
   * </ul>
   * <p>
   * Method under test: {@link MultipartPart#visitStart(PartVisitor)}
   */
  @Test
  @DisplayName("Test visitStart(PartVisitor); when CounterPartVisitor (default constructor); then CounterPartVisitor (default constructor) Count is ten")
  void testVisitStart_whenCounterPartVisitor_thenCounterPartVisitorCountIsTen() throws UnsupportedEncodingException {
    // Arrange
    MessageEndMultipartPart messageEndMultipartPart = new MessageEndMultipartPart("AXAXAXAX".getBytes("UTF-8"));
    PartVisitor.CounterPartVisitor visitor = new PartVisitor.CounterPartVisitor();

    // Act
    messageEndMultipartPart.visitStart(visitor);

    // Assert
    assertEquals(10, visitor.getCount());
  }

  /**
   * Test {@link MultipartPart#visitEndOfHeaders(PartVisitor)}.
   * <ul>
   *   <li>When {@link CounterPartVisitor} (default constructor).</li>
   *   <li>Then {@link CounterPartVisitor} (default constructor) Count is four.</li>
   * </ul>
   * <p>
   * Method under test: {@link MultipartPart#visitEndOfHeaders(PartVisitor)}
   */
  @Test
  @DisplayName("Test visitEndOfHeaders(PartVisitor); when CounterPartVisitor (default constructor); then CounterPartVisitor (default constructor) Count is four")
  void testVisitEndOfHeaders_whenCounterPartVisitor_thenCounterPartVisitorCountIsFour()
      throws UnsupportedEncodingException {
    // Arrange
    MessageEndMultipartPart messageEndMultipartPart = new MessageEndMultipartPart("AXAXAXAX".getBytes("UTF-8"));
    PartVisitor.CounterPartVisitor visitor = new PartVisitor.CounterPartVisitor();

    // Act
    messageEndMultipartPart.visitEndOfHeaders(visitor);

    // Assert
    assertEquals(4, visitor.getCount());
  }

  /**
   * Test {@link MultipartPart#visitPostContent(PartVisitor)}.
   * <ul>
   *   <li>When {@link CounterPartVisitor} (default constructor).</li>
   *   <li>Then {@link CounterPartVisitor} (default constructor) Count is two.</li>
   * </ul>
   * <p>
   * Method under test: {@link MultipartPart#visitPostContent(PartVisitor)}
   */
  @Test
  @DisplayName("Test visitPostContent(PartVisitor); when CounterPartVisitor (default constructor); then CounterPartVisitor (default constructor) Count is two")
  void testVisitPostContent_whenCounterPartVisitor_thenCounterPartVisitorCountIsTwo()
      throws UnsupportedEncodingException {
    // Arrange
    MessageEndMultipartPart messageEndMultipartPart = new MessageEndMultipartPart("AXAXAXAX".getBytes("UTF-8"));
    PartVisitor.CounterPartVisitor visitor = new PartVisitor.CounterPartVisitor();

    // Act
    messageEndMultipartPart.visitPostContent(visitor);

    // Assert
    assertEquals(2, visitor.getCount());
  }
}
