package org.asynchttpclient.request.body.multipart.part;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.isA;
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
import java.util.ArrayList;
import org.apache.tomcat.util.net.NioChannel;
import org.apache.tomcat.util.net.SocketBufferHandler;
import org.asynchttpclient.request.body.multipart.ByteArrayPart;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

class MultipartPartDiffblueTest {
  /**
   * Method under test: {@link MultipartPart#length()}
   */
  @Test
  void testLength() throws UnsupportedEncodingException {
    // Arrange, Act and Assert
    assertEquals(14L, (new MessageEndMultipartPart("AXAXAXAX".getBytes("UTF-8"))).length());
  }

  /**
   * Method under test: {@link MultipartPart#getState()}
   */
  @Test
  void testGetState() throws UnsupportedEncodingException {
    // Arrange, Act and Assert
    assertEquals(MultipartState.PRE_CONTENT, (new MessageEndMultipartPart("AXAXAXAX".getBytes("UTF-8"))).getState());
  }

  /**
   * Method under test: {@link MultipartPart#isTargetSlow()}
   */
  @Test
  void testIsTargetSlow() throws UnsupportedEncodingException {
    // Arrange, Act and Assert
    assertFalse((new MessageEndMultipartPart("AXAXAXAX".getBytes("UTF-8"))).isTargetSlow());
  }

  /**
   * Method under test: {@link MultipartPart#transferTo(ByteBuf)}
   */
  @Test
  void testTransferTo() throws IOException {
    // Arrange
    ByteArrayPart part = new ByteArrayPart("https://example.org/example", "AXAXAXAX".getBytes("UTF-8"));

    ByteArrayMultipartPart byteArrayMultipartPart = new ByteArrayMultipartPart(part, "AXAXAXAX".getBytes("UTF-8"));

    // Act and Assert
    assertEquals(0L,
        byteArrayMultipartPart.transferTo(new DuplicatedByteBuf(new EmptyByteBuf(new AdaptiveByteBufAllocator()))));
  }

  /**
   * Method under test: {@link MultipartPart#transferTo(ByteBuf)}
   */
  @Test
  void testTransferTo2() throws IOException {
    // Arrange
    ByteArrayPart part = new ByteArrayPart(null, "AXAXAXAX".getBytes("UTF-8"));

    ByteArrayMultipartPart byteArrayMultipartPart = new ByteArrayMultipartPart(part, "AXAXAXAX".getBytes("UTF-8"));

    // Act and Assert
    assertEquals(0L,
        byteArrayMultipartPart.transferTo(new DuplicatedByteBuf(new EmptyByteBuf(new AdaptiveByteBufAllocator()))));
  }

  /**
   * Method under test: {@link MultipartPart#transferTo(ByteBuf)}
   */
  @Test
  void testTransferTo3() throws IOException {
    // Arrange
    ByteArrayPart part = new ByteArrayPart("https://example.org/example",
        new byte[]{'A', 'X', 'A', 'X', 'A', 'X', Byte.MIN_VALUE, 'X'});

    ByteArrayMultipartPart byteArrayMultipartPart = new ByteArrayMultipartPart(part, "AXAXAXAX".getBytes("UTF-8"));

    // Act and Assert
    assertEquals(0L,
        byteArrayMultipartPart.transferTo(new DuplicatedByteBuf(new EmptyByteBuf(new AdaptiveByteBufAllocator()))));
  }

  /**
   * Method under test: {@link MultipartPart#transferTo(ByteBuf)}
   */
  @Test
  void testTransferTo4() throws IOException {
    // Arrange
    ByteArrayPart part = new ByteArrayPart("https://example.org/example", "AXAXAXAX".getBytes("UTF-8"));
    part.addCustomHeader("https://example.org/example", "https://example.org/example");
    ByteArrayMultipartPart byteArrayMultipartPart = new ByteArrayMultipartPart(part, "AXAXAXAX".getBytes("UTF-8"));

    // Act and Assert
    assertEquals(0L,
        byteArrayMultipartPart.transferTo(new DuplicatedByteBuf(new EmptyByteBuf(new AdaptiveByteBufAllocator()))));
  }

  /**
   * Method under test: {@link MultipartPart#transferTo(ByteBuf)}
   */
  @Test
  void testTransferTo5() throws IOException {
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
   * Method under test: {@link MultipartPart#transferTo(ByteBuf)}
   */
  @Test
  void testTransferTo6() throws IOException {
    // Arrange
    ByteArrayPart part = new ByteArrayPart("https://example.org/example", "AXAXAXAX".getBytes("UTF-8"));

    ByteArrayMultipartPart byteArrayMultipartPart = new ByteArrayMultipartPart(part, "AXAXAXAX".getBytes("UTF-8"));

    // Act and Assert
    assertEquals(0L, byteArrayMultipartPart.transferTo(new EmptyByteBuf(new AdaptiveByteBufAllocator())));
  }

  /**
   * Method under test: {@link MultipartPart#transferTo(WritableByteChannel)}
   */
  @Test
  void testTransferTo7() throws IOException {
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
   * Method under test:
   * {@link MultipartPart#transfer(ByteBuf, ByteBuf, MultipartState)}
   */
  @Test
  void testTransfer() throws UnsupportedEncodingException {
    // Arrange
    MessageEndMultipartPart messageEndMultipartPart = new MessageEndMultipartPart("AXAXAXAX".getBytes("UTF-8"));
    DuplicatedByteBuf source = new DuplicatedByteBuf(new EmptyByteBuf(new AdaptiveByteBufAllocator()));

    // Act and Assert
    assertEquals(0L, messageEndMultipartPart.transfer(source,
        new DuplicatedByteBuf(new EmptyByteBuf(new AdaptiveByteBufAllocator())), MultipartState.PRE_CONTENT));
  }

  /**
   * Method under test:
   * {@link MultipartPart#transfer(ByteBuf, ByteBuf, MultipartState)}
   */
  @Test
  void testTransfer2() throws UnsupportedEncodingException {
    // Arrange
    MessageEndMultipartPart messageEndMultipartPart = new MessageEndMultipartPart("AXAXAXAX".getBytes("UTF-8"));
    ByteBuf buffer = mock(ByteBuf.class);
    when(buffer.capacity()).thenReturn(3);
    when(buffer.maxCapacity()).thenReturn(3);
    when(buffer.readerIndex()).thenReturn(1);
    when(buffer.writerIndex()).thenReturn(1);
    DuplicatedByteBuf source = new DuplicatedByteBuf(new ReadOnlyByteBuf(new DuplicatedByteBuf(buffer)));

    // Act
    long actualTransferResult = messageEndMultipartPart.transfer(source,
        new DuplicatedByteBuf(new EmptyByteBuf(new AdaptiveByteBufAllocator())), MultipartState.PRE_CONTENT);

    // Assert
    verify(buffer, atLeast(1)).capacity();
    verify(buffer).maxCapacity();
    verify(buffer).readerIndex();
    verify(buffer).writerIndex();
    assertEquals(0L, actualTransferResult);
  }

  /**
   * Method under test:
   * {@link MultipartPart#transfer(ByteBuf, ByteBuf, MultipartState)}
   */
  @Test
  void testTransfer3() throws UnsupportedEncodingException {
    // Arrange
    MessageEndMultipartPart messageEndMultipartPart = new MessageEndMultipartPart("AXAXAXAX".getBytes("UTF-8"));
    ByteBuf buffer = mock(ByteBuf.class);
    when(buffer.capacity()).thenReturn(3);
    when(buffer.maxCapacity()).thenReturn(3);
    when(buffer.readerIndex()).thenReturn(0);
    when(buffer.writerIndex()).thenReturn(1);
    DuplicatedByteBuf source = new DuplicatedByteBuf(new ReadOnlyByteBuf(new DuplicatedByteBuf(buffer)));

    // Act
    long actualTransferResult = messageEndMultipartPart.transfer(source,
        new DuplicatedByteBuf(new EmptyByteBuf(new AdaptiveByteBufAllocator())), MultipartState.PRE_CONTENT);

    // Assert
    verify(buffer, atLeast(1)).capacity();
    verify(buffer).maxCapacity();
    verify(buffer).readerIndex();
    verify(buffer).writerIndex();
    assertEquals(0L, actualTransferResult);
  }

  /**
   * Method under test:
   * {@link MultipartPart#transfer(ByteBuf, ByteBuf, MultipartState)}
   */
  @Test
  void testTransfer4() throws UnsupportedEncodingException {
    // Arrange
    MessageEndMultipartPart messageEndMultipartPart = new MessageEndMultipartPart("AXAXAXAX".getBytes("UTF-8"));
    ByteBuf buffer = mock(ByteBuf.class);
    when(buffer.capacity()).thenReturn(3);
    when(buffer.maxCapacity()).thenReturn(3);
    when(buffer.readerIndex()).thenReturn(1);
    when(buffer.writerIndex()).thenReturn(1);
    DuplicatedByteBuf source = new DuplicatedByteBuf(new ReadOnlyByteBuf(new DuplicatedByteBuf(buffer)));

    // Act
    long actualTransferResult = messageEndMultipartPart.transfer(source,
        new DuplicatedByteBuf(Unpooled.compositeBuffer(3)), MultipartState.PRE_CONTENT);

    // Assert
    verify(buffer, atLeast(1)).capacity();
    verify(buffer).maxCapacity();
    verify(buffer).readerIndex();
    verify(buffer).writerIndex();
    assertEquals(0L, actualTransferResult);
  }

  /**
   * Method under test:
   * {@link MultipartPart#transfer(ByteBuf, WritableByteChannel, MultipartState)}
   */
  @Test
  void testTransfer5() throws IOException {
    // Arrange
    MessageEndMultipartPart messageEndMultipartPart = new MessageEndMultipartPart("AXAXAXAX".getBytes("UTF-8"));
    DuplicatedByteBuf source = new DuplicatedByteBuf(new EmptyByteBuf(new AdaptiveByteBufAllocator()));

    // Act and Assert
    assertEquals(0L, messageEndMultipartPart.transfer(source, new NioChannel(new SocketBufferHandler(3, 3, true)),
        MultipartState.PRE_CONTENT));
  }

  /**
   * Method under test: {@link MultipartPart#computePreContentLength()}
   */
  @Test
  void testComputePreContentLength() throws UnsupportedEncodingException {
    // Arrange
    ByteArrayPart part = new ByteArrayPart("https://example.org/example", "AXAXAXAX".getBytes("UTF-8"));

    // Act and Assert
    assertEquals(122, (new ByteArrayMultipartPart(part, "AXAXAXAX".getBytes("UTF-8"))).computePreContentLength());
  }

  /**
   * Method under test: {@link MultipartPart#computePreContentLength()}
   */
  @Test
  void testComputePreContentLength2() throws UnsupportedEncodingException {
    // Arrange
    ByteArrayPart part = new ByteArrayPart(null, "AXAXAXAX".getBytes("UTF-8"));

    // Act and Assert
    assertEquals(86, (new ByteArrayMultipartPart(part, "AXAXAXAX".getBytes("UTF-8"))).computePreContentLength());
  }

  /**
   * Method under test: {@link MultipartPart#computePreContentLength()}
   */
  @Test
  void testComputePreContentLength3() throws UnsupportedEncodingException {
    // Arrange
    ByteArrayPart part = new ByteArrayPart("https://example.org/example", "AXAXAXAX".getBytes("UTF-8"));
    part.addCustomHeader("https://example.org/example", "https://example.org/example");

    // Act and Assert
    assertEquals(180, (new ByteArrayMultipartPart(part, "AXAXAXAX".getBytes("UTF-8"))).computePreContentLength());
  }

  /**
   * Method under test: {@link MultipartPart#computePreContentLength()}
   */
  @Test
  void testComputePreContentLength4() throws UnsupportedEncodingException {
    // Arrange
    ByteArrayPart part = new ByteArrayPart("https://example.org/example", "AXAXAXAX".getBytes("UTF-8"));
    part.setDispositionType("https://example.org/example");
    part.addCustomHeader("https://example.org/example", "https://example.org/example");

    // Act and Assert
    assertEquals(198, (new ByteArrayMultipartPart(part, "AXAXAXAX".getBytes("UTF-8"))).computePreContentLength());
  }

  /**
   * Method under test: {@link MultipartPart#computePreContentLength()}
   */
  @Test
  void testComputePreContentLength5() throws UnsupportedEncodingException {
    // Arrange
    ByteArrayPart part = new ByteArrayPart("https://example.org/example", "AXAXAXAX".getBytes("UTF-8"));
    part.setCustomHeaders(new ArrayList<>());

    // Act and Assert
    assertEquals(122, (new ByteArrayMultipartPart(part, "AXAXAXAX".getBytes("UTF-8"))).computePreContentLength());
  }

  /**
   * Method under test: {@link MultipartPart#computePreContentBytes(int)}
   */
  @Test
  void testComputePreContentBytes() throws UnsupportedEncodingException {
    // Arrange
    ByteArrayPart part = new ByteArrayPart("https://example.org/example", "AXAXAXAX".getBytes("UTF-8"));

    // Act and Assert
    assertTrue(
        (new ByteArrayMultipartPart(part, "AXAXAXAX".getBytes("UTF-8"))).computePreContentBytes(3).isContiguous());
  }

  /**
   * Method under test: {@link MultipartPart#computePreContentBytes(int)}
   */
  @Test
  void testComputePreContentBytes2() throws UnsupportedEncodingException {
    // Arrange
    ByteArrayPart part = new ByteArrayPart(null, "AXAXAXAX".getBytes("UTF-8"));

    // Act and Assert
    assertTrue(
        (new ByteArrayMultipartPart(part, "AXAXAXAX".getBytes("UTF-8"))).computePreContentBytes(3).isContiguous());
  }

  /**
   * Method under test: {@link MultipartPart#computePreContentBytes(int)}
   */
  @Test
  void testComputePreContentBytes3() throws UnsupportedEncodingException {
    // Arrange
    ByteArrayPart part = new ByteArrayPart("https://example.org/example", "AXAXAXAX".getBytes("UTF-8"));
    part.addCustomHeader("https://example.org/example", "https://example.org/example");

    // Act and Assert
    assertTrue(
        (new ByteArrayMultipartPart(part, "AXAXAXAX".getBytes("UTF-8"))).computePreContentBytes(3).isContiguous());
  }

  /**
   * Method under test: {@link MultipartPart#computePreContentBytes(int)}
   */
  @Test
  void testComputePreContentBytes4() throws UnsupportedEncodingException {
    // Arrange
    ByteArrayPart part = new ByteArrayPart("https://example.org/example", "AXAXAXAX".getBytes("UTF-8"));
    part.setDispositionType("https://example.org/example");
    part.addCustomHeader("https://example.org/example", "https://example.org/example");

    // Act and Assert
    assertTrue(
        (new ByteArrayMultipartPart(part, "AXAXAXAX".getBytes("UTF-8"))).computePreContentBytes(3).isContiguous());
  }

  /**
   * Method under test: {@link MultipartPart#computePreContentBytes(int)}
   */
  @Test
  void testComputePreContentBytes5() throws UnsupportedEncodingException {
    // Arrange, Act and Assert
    assertTrue((new ByteArrayMultipartPart(new ByteArrayPart(null, "AXAXAXAX".getBytes("UTF-8")),
        new byte[]{'A', 'X', Byte.MIN_VALUE, 'X', 'A', 'X', 'A', 'X'})).computePreContentBytes(3).isContiguous());
  }

  /**
   * Method under test: {@link MultipartPart#computePreContentBytes(int)}
   */
  @Test
  void testComputePreContentBytes6() throws UnsupportedEncodingException {
    // Arrange
    ByteArrayPart part = new ByteArrayPart("https://example.org/example", "AXAXAXAX".getBytes("UTF-8"));
    part.setCustomHeaders(new ArrayList<>());

    // Act and Assert
    assertTrue(
        (new ByteArrayMultipartPart(part, "AXAXAXAX".getBytes("UTF-8"))).computePreContentBytes(3).isContiguous());
  }

  /**
   * Method under test: {@link MultipartPart#computePostContentLength()}
   */
  @Test
  void testComputePostContentLength() throws UnsupportedEncodingException {
    // Arrange
    ByteArrayPart part = new ByteArrayPart("https://example.org/example", "AXAXAXAX".getBytes("UTF-8"));

    // Act and Assert
    assertEquals(2, (new ByteArrayMultipartPart(part, "AXAXAXAX".getBytes("UTF-8"))).computePostContentLength());
  }

  /**
   * Method under test: {@link MultipartPart#computePostContentBytes(int)}
   */
  @Test
  void testComputePostContentBytes() throws UnsupportedEncodingException {
    // Arrange
    ByteArrayPart part = new ByteArrayPart("https://example.org/example", "AXAXAXAX".getBytes("UTF-8"));

    // Act and Assert
    assertTrue(
        (new ByteArrayMultipartPart(part, "AXAXAXAX".getBytes("UTF-8"))).computePostContentBytes(3).isContiguous());
  }

  /**
   * Method under test: {@link MultipartPart#computePostContentBytes(int)}
   */
  @Test
  void testComputePostContentBytes2() throws UnsupportedEncodingException {
    // Arrange
    ByteArrayPart part = new ByteArrayPart("https://example.org/example", "AXAXAXAX".getBytes("UTF-8"));
    part.setCustomHeaders(new ArrayList<>());

    // Act and Assert
    assertTrue(
        (new ByteArrayMultipartPart(part, "AXAXAXAX".getBytes("UTF-8"))).computePostContentBytes(3).isContiguous());
  }

  /**
   * Method under test: {@link MultipartPart#visitStart(PartVisitor)}
   */
  @Test
  void testVisitStart() throws UnsupportedEncodingException {
    // Arrange
    MessageEndMultipartPart messageEndMultipartPart = new MessageEndMultipartPart("AXAXAXAX".getBytes("UTF-8"));
    PartVisitor.CounterPartVisitor visitor = new PartVisitor.CounterPartVisitor();

    // Act
    messageEndMultipartPart.visitStart(visitor);

    // Assert
    assertEquals(10, visitor.getCount());
  }

  /**
   * Method under test: {@link MultipartPart#visitEndOfHeaders(PartVisitor)}
   */
  @Test
  void testVisitEndOfHeaders() throws UnsupportedEncodingException {
    // Arrange
    MessageEndMultipartPart messageEndMultipartPart = new MessageEndMultipartPart("AXAXAXAX".getBytes("UTF-8"));
    PartVisitor.CounterPartVisitor visitor = new PartVisitor.CounterPartVisitor();

    // Act
    messageEndMultipartPart.visitEndOfHeaders(visitor);

    // Assert
    assertEquals(4, visitor.getCount());
  }

  /**
   * Method under test: {@link MultipartPart#visitPostContent(PartVisitor)}
   */
  @Test
  void testVisitPostContent() throws UnsupportedEncodingException {
    // Arrange
    MessageEndMultipartPart messageEndMultipartPart = new MessageEndMultipartPart("AXAXAXAX".getBytes("UTF-8"));
    PartVisitor.CounterPartVisitor visitor = new PartVisitor.CounterPartVisitor();

    // Act
    messageEndMultipartPart.visitPostContent(visitor);

    // Assert
    assertEquals(2, visitor.getCount());
  }
}
