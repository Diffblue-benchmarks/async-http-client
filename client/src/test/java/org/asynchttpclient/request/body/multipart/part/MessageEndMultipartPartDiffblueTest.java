package org.asynchttpclient.request.body.multipart.part;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
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
import io.netty.buffer.CompositeByteBuf;
import io.netty.buffer.DuplicatedByteBuf;
import io.netty.buffer.EmptyByteBuf;
import io.netty.buffer.Unpooled;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.nio.channels.WritableByteChannel;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class MessageEndMultipartPartDiffblueTest {
  @InjectMocks private MessageEndMultipartPart messageEndMultipartPart;

  /**
   * Test {@link MessageEndMultipartPart#MessageEndMultipartPart(byte[])}.
   *
   * <p>Method under test: {@link MessageEndMultipartPart#MessageEndMultipartPart(byte[])}
   */
  @Test
  @DisplayName("Test new MessageEndMultipartPart(byte[])")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void MessageEndMultipartPart.<init>(byte[])"})
  void testNewMessageEndMultipartPart() throws UnsupportedEncodingException {
    // Arrange and Act
    MessageEndMultipartPart actualMessageEndMultipartPart =
        new MessageEndMultipartPart("AXAXAXAX".getBytes("UTF-8"));

    // Assert
    assertNull(actualMessageEndMultipartPart.part);
    assertEquals(14L, actualMessageEndMultipartPart.getContentLength());
    assertEquals(14L, actualMessageEndMultipartPart.length());
    assertEquals(MultipartState.PRE_CONTENT, actualMessageEndMultipartPart.getState());
    assertFalse(actualMessageEndMultipartPart.isTargetSlow());
    assertArrayEquals("AXAXAXAX".getBytes("UTF-8"), actualMessageEndMultipartPart.boundary);
  }

  /**
   * Test {@link MessageEndMultipartPart#transferTo(ByteBuf)} with {@code ByteBuf}.
   *
   * <p>Method under test: {@link MessageEndMultipartPart#transferTo(ByteBuf)}
   */
  @Test
  @DisplayName("Test transferTo(ByteBuf) with 'ByteBuf'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"long MessageEndMultipartPart.transferTo(ByteBuf)"})
  void testTransferToWithByteBuf() throws UnsupportedEncodingException {
    // Arrange
    MessageEndMultipartPart messageEndMultipartPart =
        new MessageEndMultipartPart("AXAXAXAX".getBytes("UTF-8"));
    DuplicatedByteBuf target =
        new DuplicatedByteBuf(new EmptyByteBuf(new AdaptiveByteBufAllocator()));

    // Act
    long actualTransferToResult = messageEndMultipartPart.transferTo(target);

    // Assert
    assertEquals(0, target.writerIndex());
    assertEquals(0L, actualTransferToResult);
    assertEquals(MultipartState.PRE_CONTENT, messageEndMultipartPart.getState());
  }

  /**
   * Test {@link MessageEndMultipartPart#transferTo(ByteBuf)} with {@code ByteBuf}.
   *
   * <ul>
   *   <li>Given forty-two.
   *   <li>Then return fourteen.
   * </ul>
   *
   * <p>Method under test: {@link MessageEndMultipartPart#transferTo(ByteBuf)}
   */
  @Test
  @DisplayName("Test transferTo(ByteBuf) with 'ByteBuf'; given forty-two; then return fourteen")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"long MessageEndMultipartPart.transferTo(ByteBuf)"})
  void testTransferToWithByteBuf_givenFortyTwo_thenReturnFourteen()
      throws UnsupportedEncodingException {
    // Arrange
    MessageEndMultipartPart messageEndMultipartPart =
        new MessageEndMultipartPart("AXAXAXAX".getBytes("UTF-8"));

    CompositeByteBuf target = Unpooled.compositeBuffer(3);
    target.writeByte(42);

    // Act and Assert
    assertEquals(14L, messageEndMultipartPart.transferTo(target));
    assertEquals(15, target.writerIndex());
    assertEquals(MultipartState.DONE, messageEndMultipartPart.getState());
    assertTrue(target.readBoolean());
    assertArrayEquals(
        new byte[] {
          '*', '-', '-', 'A', 'X', 'A', 'X', 'A', 'X', 'A', 'X', '-', '-', '\r', '\n', 0, 0, 0, 0,
          0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
          0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0
        },
        target.array());
  }

  /**
   * Test {@link MessageEndMultipartPart#transferTo(ByteBuf)} with {@code ByteBuf}.
   *
   * <ul>
   *   <li>Then compositeBuffer three writerIndex is zero.
   * </ul>
   *
   * <p>Method under test: {@link MessageEndMultipartPart#transferTo(ByteBuf)}
   */
  @Test
  @DisplayName(
      "Test transferTo(ByteBuf) with 'ByteBuf'; then compositeBuffer three writerIndex is zero")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"long MessageEndMultipartPart.transferTo(ByteBuf)"})
  void testTransferToWithByteBuf_thenCompositeBufferThreeWriterIndexIsZero()
      throws UnsupportedEncodingException {
    // Arrange
    MessageEndMultipartPart messageEndMultipartPart =
        new MessageEndMultipartPart("AXAXAXAX".getBytes("UTF-8"));
    CompositeByteBuf target = Unpooled.compositeBuffer(3);

    // Act
    long actualTransferToResult = messageEndMultipartPart.transferTo(target);

    // Assert
    assertEquals(0, target.writerIndex());
    assertEquals(0L, actualTransferToResult);
    assertEquals(MultipartState.PRE_CONTENT, messageEndMultipartPart.getState());
    assertArrayEquals(new byte[] {}, target.array());
  }

  /**
   * Test {@link MessageEndMultipartPart#transferTo(WritableByteChannel)} with {@code
   * WritableByteChannel}.
   *
   * <ul>
   *   <li>Given fourteen.
   *   <li>Then return fourteen.
   * </ul>
   *
   * <p>Method under test: {@link MessageEndMultipartPart#transferTo(WritableByteChannel)}
   */
  @Test
  @DisplayName(
      "Test transferTo(WritableByteChannel) with 'WritableByteChannel'; given fourteen; then return fourteen")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"long MessageEndMultipartPart.transferTo(WritableByteChannel)"})
  void testTransferToWithWritableByteChannel_givenFourteen_thenReturnFourteen() throws IOException {
    // Arrange
    MessageEndMultipartPart messageEndMultipartPart =
        new MessageEndMultipartPart("AXAXAXAX".getBytes("UTF-8"));

    BrotliEncoderChannel target = mock(BrotliEncoderChannel.class);
    when(target.write(Mockito.<ByteBuffer>any())).thenReturn(14);

    // Act
    long actualTransferToResult = messageEndMultipartPart.transferTo(target);

    // Assert
    verify(target).write(isA(ByteBuffer.class));
    assertEquals(14L, actualTransferToResult);
    assertEquals(MultipartState.DONE, messageEndMultipartPart.getState());
    assertFalse(messageEndMultipartPart.isTargetSlow());
  }

  /**
   * Test {@link MessageEndMultipartPart#transferTo(WritableByteChannel)} with {@code
   * WritableByteChannel}.
   *
   * <ul>
   *   <li>Given one.
   *   <li>Then return one.
   * </ul>
   *
   * <p>Method under test: {@link MessageEndMultipartPart#transferTo(WritableByteChannel)}
   */
  @Test
  @DisplayName(
      "Test transferTo(WritableByteChannel) with 'WritableByteChannel'; given one; then return one")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"long MessageEndMultipartPart.transferTo(WritableByteChannel)"})
  void testTransferToWithWritableByteChannel_givenOne_thenReturnOne() throws IOException {
    // Arrange
    MessageEndMultipartPart messageEndMultipartPart =
        new MessageEndMultipartPart("AXAXAXAX".getBytes("UTF-8"));

    BrotliEncoderChannel target = mock(BrotliEncoderChannel.class);
    when(target.write(Mockito.<ByteBuffer>any())).thenReturn(1);

    // Act
    long actualTransferToResult = messageEndMultipartPart.transferTo(target);

    // Assert
    verify(target).write(isA(ByteBuffer.class));
    assertEquals(1L, actualTransferToResult);
    assertEquals(MultipartState.PRE_CONTENT, messageEndMultipartPart.getState());
    assertTrue(messageEndMultipartPart.isTargetSlow());
  }

  /**
   * Test {@link MessageEndMultipartPart#computePreContentLength()}.
   *
   * <p>Method under test: {@link MessageEndMultipartPart#computePreContentLength()}
   */
  @Test
  @DisplayName("Test computePreContentLength()")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"int MessageEndMultipartPart.computePreContentLength()"})
  void testComputePreContentLength() throws UnsupportedEncodingException {
    // Arrange, Act and Assert
    assertEquals(
        0, new MessageEndMultipartPart("AXAXAXAX".getBytes("UTF-8")).computePreContentLength());
  }

  /**
   * Test {@link MessageEndMultipartPart#computePostContentLength()}.
   *
   * <p>Method under test: {@link MessageEndMultipartPart#computePostContentLength()}
   */
  @Test
  @DisplayName("Test computePostContentLength()")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"int MessageEndMultipartPart.computePostContentLength()"})
  void testComputePostContentLength() throws UnsupportedEncodingException {
    // Arrange, Act and Assert
    assertEquals(
        0, new MessageEndMultipartPart("AXAXAXAX".getBytes("UTF-8")).computePostContentLength());
  }

  /**
   * Test {@link MessageEndMultipartPart#getContentLength()}.
   *
   * <p>Method under test: {@link MessageEndMultipartPart#getContentLength()}
   */
  @Test
  @DisplayName("Test getContentLength()")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"long MessageEndMultipartPart.getContentLength()"})
  void testGetContentLength() throws UnsupportedEncodingException {
    // Arrange, Act and Assert
    assertEquals(14L, new MessageEndMultipartPart("AXAXAXAX".getBytes("UTF-8")).getContentLength());
  }

  /**
   * Test {@link MessageEndMultipartPart#transferContentTo(ByteBuf)} with {@code ByteBuf}.
   *
   * <p>Method under test: {@link MessageEndMultipartPart#transferContentTo(ByteBuf)}
   */
  @Test
  @DisplayName("Test transferContentTo(ByteBuf) with 'ByteBuf'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"long MessageEndMultipartPart.transferContentTo(ByteBuf)"})
  void testTransferContentToWithByteBuf() throws UnsupportedEncodingException {
    // Arrange
    MessageEndMultipartPart messageEndMultipartPart =
        new MessageEndMultipartPart("AXAXAXAX".getBytes("UTF-8"));

    // Act and Assert
    assertThrows(
        UnsupportedOperationException.class,
        () ->
            messageEndMultipartPart.transferContentTo(
                new DuplicatedByteBuf(new EmptyByteBuf(new AdaptiveByteBufAllocator()))));
  }

  /**
   * Test {@link MessageEndMultipartPart#transferContentTo(WritableByteChannel)} with {@code
   * WritableByteChannel}.
   *
   * <p>Method under test: {@link MessageEndMultipartPart#transferContentTo(WritableByteChannel)}
   */
  @Test
  @DisplayName("Test transferContentTo(WritableByteChannel) with 'WritableByteChannel'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"long MessageEndMultipartPart.transferContentTo(WritableByteChannel)"})
  void testTransferContentToWithWritableByteChannel() {
    // Arrange, Act and Assert
    assertThrows(
        UnsupportedOperationException.class,
        () -> messageEndMultipartPart.transferContentTo((WritableByteChannel) null));
  }

  /**
   * Test {@link MessageEndMultipartPart#close()}.
   *
   * <ul>
   *   <li>Given {@link BrotliEncoderChannel} {@link BrotliEncoderChannel#write(ByteBuffer)} return
   *       one.
   *   <li>Then calls {@link BrotliEncoderChannel#write(ByteBuffer)}.
   * </ul>
   *
   * <p>Method under test: {@link MessageEndMultipartPart#close()}
   */
  @Test
  @DisplayName(
      "Test close(); given BrotliEncoderChannel write(ByteBuffer) return one; then calls write(ByteBuffer)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void MessageEndMultipartPart.close()"})
  void testClose_givenBrotliEncoderChannelWriteReturnOne_thenCallsWrite() throws IOException {
    // Arrange
    BrotliEncoderChannel target = mock(BrotliEncoderChannel.class);
    when(target.write(Mockito.<ByteBuffer>any())).thenReturn(1);

    try (MessageEndMultipartPart messageEndMultipartPart =
        new MessageEndMultipartPart("AXAXAXAX".getBytes("UTF-8"))) {
      messageEndMultipartPart.transferTo(target);
    }

    // Act and Assert
    verify(target).write(isA(ByteBuffer.class));
  }
}
