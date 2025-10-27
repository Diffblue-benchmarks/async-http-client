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
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

class MessageEndMultipartPartDiffblueTest {
  /**
   * Method under test: {@link MessageEndMultipartPart#transferTo(ByteBuf)}
   */
  @Test
  void testTransferTo() throws UnsupportedEncodingException {
    // Arrange
    MessageEndMultipartPart messageEndMultipartPart = new MessageEndMultipartPart("AXAXAXAX".getBytes("UTF-8"));

    // Act and Assert
    assertEquals(0L,
        messageEndMultipartPart.transferTo(new DuplicatedByteBuf(new EmptyByteBuf(new AdaptiveByteBufAllocator()))));
  }

  /**
   * Method under test: {@link MessageEndMultipartPart#transferTo(ByteBuf)}
   */
  @Test
  void testTransferTo2() throws UnsupportedEncodingException {
    // Arrange
    MessageEndMultipartPart messageEndMultipartPart = new MessageEndMultipartPart("AXAXAXAX".getBytes("UTF-8"));

    // Act and Assert
    assertEquals(0L, messageEndMultipartPart.transferTo(new DuplicatedByteBuf(Unpooled.compositeBuffer(3))));
  }

  /**
   * Method under test:
   * {@link MessageEndMultipartPart#transferTo(WritableByteChannel)}
   */
  @Test
  void testTransferTo3() throws IOException {
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
   * Method under test:
   * {@link MessageEndMultipartPart#transferTo(WritableByteChannel)}
   */
  @Test
  void testTransferTo4() throws IOException {
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
   * Method under test:
   * {@link MessageEndMultipartPart#transferTo(WritableByteChannel)}
   */
  @Test
  void testTransferTo5() throws IOException {
    // Arrange
    MessageEndMultipartPart messageEndMultipartPart = new MessageEndMultipartPart(new byte[]{});
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
   * Method under test: {@link MessageEndMultipartPart#computePreContentLength()}
   */
  @Test
  void testComputePreContentLength() throws UnsupportedEncodingException {
    // Arrange, Act and Assert
    assertEquals(0, (new MessageEndMultipartPart("AXAXAXAX".getBytes("UTF-8"))).computePreContentLength());
  }

  /**
   * Method under test: {@link MessageEndMultipartPart#computePostContentLength()}
   */
  @Test
  void testComputePostContentLength() throws UnsupportedEncodingException {
    // Arrange, Act and Assert
    assertEquals(0, (new MessageEndMultipartPart("AXAXAXAX".getBytes("UTF-8"))).computePostContentLength());
  }

  /**
   * Method under test: {@link MessageEndMultipartPart#getContentLength()}
   */
  @Test
  void testGetContentLength() throws UnsupportedEncodingException {
    // Arrange, Act and Assert
    assertEquals(14L, (new MessageEndMultipartPart("AXAXAXAX".getBytes("UTF-8"))).getContentLength());
  }

  /**
   * Method under test: {@link MessageEndMultipartPart#transferContentTo(ByteBuf)}
   */
  @Test
  void testTransferContentTo() throws UnsupportedEncodingException {
    // Arrange
    MessageEndMultipartPart messageEndMultipartPart = new MessageEndMultipartPart("AXAXAXAX".getBytes("UTF-8"));

    // Act and Assert
    assertThrows(UnsupportedOperationException.class, () -> messageEndMultipartPart
        .transferContentTo(new DuplicatedByteBuf(new EmptyByteBuf(new AdaptiveByteBufAllocator()))));
  }

  /**
   * Method under test:
   * {@link MessageEndMultipartPart#transferContentTo(WritableByteChannel)}
   */
  @Test
  void testTransferContentTo2() throws UnsupportedEncodingException {
    // Arrange, Act and Assert
    assertThrows(UnsupportedOperationException.class, () -> (new MessageEndMultipartPart("AXAXAXAX".getBytes("UTF-8")))
        .transferContentTo((WritableByteChannel) null));
  }

  /**
   * Method under test:
   * {@link MessageEndMultipartPart#MessageEndMultipartPart(byte[])}
   */
  @Test
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
}
