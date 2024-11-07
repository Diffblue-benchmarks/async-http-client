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
import io.netty.buffer.AdaptiveByteBufAllocator;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.DuplicatedByteBuf;
import io.netty.buffer.EmptyByteBuf;
import io.netty.buffer.Unpooled;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.nio.channels.WritableByteChannel;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

class MessageEndMultipartPartDiffblueTest {
  /**
   * Test {@link MessageEndMultipartPart#MessageEndMultipartPart(byte[])}.
   * <p>
   * Method under test:
   * {@link MessageEndMultipartPart#MessageEndMultipartPart(byte[])}
   */
  @Test
  @DisplayName("Test new MessageEndMultipartPart(byte[])")
  void testNewMessageEndMultipartPart() throws UnsupportedEncodingException {
    // Arrange and Act
    MessageEndMultipartPart actualMessageEndMultipartPart = new MessageEndMultipartPart("AXAXAXAX".getBytes("UTF-8"));

    // Assert
    assertNull(actualMessageEndMultipartPart.part);
    assertEquals(14L, actualMessageEndMultipartPart.getContentLength());
    assertEquals(14L, actualMessageEndMultipartPart.length());
    assertEquals(MultipartState.PRE_CONTENT, actualMessageEndMultipartPart.getState());
    assertFalse(actualMessageEndMultipartPart.isTargetSlow());
    assertArrayEquals("AXAXAXAX".getBytes("UTF-8"), actualMessageEndMultipartPart.boundary);
  }

  /**
   * Test {@link MessageEndMultipartPart#transferTo(ByteBuf)} with
   * {@code ByteBuf}.
   * <p>
   * Method under test: {@link MessageEndMultipartPart#transferTo(ByteBuf)}
   */
  @Test
  @DisplayName("Test transferTo(ByteBuf) with 'ByteBuf'")
  void testTransferToWithByteBuf() throws UnsupportedEncodingException {
    // Arrange
    MessageEndMultipartPart messageEndMultipartPart = new MessageEndMultipartPart("AXAXAXAX".getBytes("UTF-8"));
    DuplicatedByteBuf target = new DuplicatedByteBuf(new EmptyByteBuf(new AdaptiveByteBufAllocator()));

    // Act
    long actualTransferToResult = messageEndMultipartPart.transferTo(target);

    // Assert
    assertEquals(0, target.readerIndex());
    assertEquals(0, target.writerIndex());
    assertEquals(0L, actualTransferToResult);
  }

  /**
   * Test {@link MessageEndMultipartPart#transferTo(ByteBuf)} with
   * {@code ByteBuf}.
   * <p>
   * Method under test: {@link MessageEndMultipartPart#transferTo(ByteBuf)}
   */
  @Test
  @DisplayName("Test transferTo(ByteBuf) with 'ByteBuf'")
  void testTransferToWithByteBuf2() throws UnsupportedEncodingException {
    // Arrange
    MessageEndMultipartPart messageEndMultipartPart = new MessageEndMultipartPart("AXAXAXAX".getBytes("UTF-8"));
    DuplicatedByteBuf target = new DuplicatedByteBuf(Unpooled.compositeBuffer(3));

    // Act
    long actualTransferToResult = messageEndMultipartPart.transferTo(target);

    // Assert
    assertEquals(0, target.readerIndex());
    assertEquals(0, target.writerIndex());
    assertEquals(0L, actualTransferToResult);
  }

  /**
   * Test {@link MessageEndMultipartPart#transferTo(WritableByteChannel)} with
   * {@code WritableByteChannel}.
   * <ul>
   *   <li>Given fourteen.</li>
   *   <li>Then return fourteen.</li>
   * </ul>
   * <p>
   * Method under test:
   * {@link MessageEndMultipartPart#transferTo(WritableByteChannel)}
   */
  @Test
  @DisplayName("Test transferTo(WritableByteChannel) with 'WritableByteChannel'; given fourteen; then return fourteen")
  void testTransferToWithWritableByteChannel_givenFourteen_thenReturnFourteen() throws IOException {
    // Arrange
    MessageEndMultipartPart messageEndMultipartPart = new MessageEndMultipartPart("AXAXAXAX".getBytes("UTF-8"));
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
   * Test {@link MessageEndMultipartPart#transferTo(WritableByteChannel)} with
   * {@code WritableByteChannel}.
   * <ul>
   *   <li>Given one.</li>
   *   <li>Then return one.</li>
   * </ul>
   * <p>
   * Method under test:
   * {@link MessageEndMultipartPart#transferTo(WritableByteChannel)}
   */
  @Test
  @DisplayName("Test transferTo(WritableByteChannel) with 'WritableByteChannel'; given one; then return one")
  void testTransferToWithWritableByteChannel_givenOne_thenReturnOne() throws IOException {
    // Arrange
    MessageEndMultipartPart messageEndMultipartPart = new MessageEndMultipartPart("AXAXAXAX".getBytes("UTF-8"));
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
   * <p>
   * Method under test: {@link MessageEndMultipartPart#computePreContentLength()}
   */
  @Test
  @DisplayName("Test computePreContentLength()")
  void testComputePreContentLength() throws UnsupportedEncodingException {
    // Arrange, Act and Assert
    assertEquals(0, (new MessageEndMultipartPart("AXAXAXAX".getBytes("UTF-8"))).computePreContentLength());
  }

  /**
   * Test {@link MessageEndMultipartPart#computePostContentLength()}.
   * <p>
   * Method under test: {@link MessageEndMultipartPart#computePostContentLength()}
   */
  @Test
  @DisplayName("Test computePostContentLength()")
  void testComputePostContentLength() throws UnsupportedEncodingException {
    // Arrange, Act and Assert
    assertEquals(0, (new MessageEndMultipartPart("AXAXAXAX".getBytes("UTF-8"))).computePostContentLength());
  }

  /**
   * Test {@link MessageEndMultipartPart#getContentLength()}.
   * <p>
   * Method under test: {@link MessageEndMultipartPart#getContentLength()}
   */
  @Test
  @DisplayName("Test getContentLength()")
  void testGetContentLength() throws UnsupportedEncodingException {
    // Arrange, Act and Assert
    assertEquals(14L, (new MessageEndMultipartPart("AXAXAXAX".getBytes("UTF-8"))).getContentLength());
  }

  /**
   * Test {@link MessageEndMultipartPart#transferContentTo(ByteBuf)} with
   * {@code ByteBuf}.
   * <p>
   * Method under test: {@link MessageEndMultipartPart#transferContentTo(ByteBuf)}
   */
  @Test
  @DisplayName("Test transferContentTo(ByteBuf) with 'ByteBuf'")
  void testTransferContentToWithByteBuf() throws UnsupportedEncodingException {
    // Arrange
    MessageEndMultipartPart messageEndMultipartPart = new MessageEndMultipartPart("AXAXAXAX".getBytes("UTF-8"));

    // Act and Assert
    assertThrows(UnsupportedOperationException.class, () -> messageEndMultipartPart
        .transferContentTo(new DuplicatedByteBuf(new EmptyByteBuf(new AdaptiveByteBufAllocator()))));
  }

  /**
   * Test {@link MessageEndMultipartPart#transferContentTo(WritableByteChannel)}
   * with {@code WritableByteChannel}.
   * <p>
   * Method under test:
   * {@link MessageEndMultipartPart#transferContentTo(WritableByteChannel)}
   */
  @Test
  @DisplayName("Test transferContentTo(WritableByteChannel) with 'WritableByteChannel'")
  void testTransferContentToWithWritableByteChannel() throws UnsupportedEncodingException {
    // Arrange, Act and Assert
    assertThrows(UnsupportedOperationException.class, () -> (new MessageEndMultipartPart("AXAXAXAX".getBytes("UTF-8")))
        .transferContentTo((WritableByteChannel) null));
  }
}
