package org.asynchttpclient.netty;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.anyInt;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import io.netty.buffer.AdaptiveByteBufAllocator;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.DuplicatedByteBuf;
import io.netty.buffer.EmptyByteBuf;
import io.netty.buffer.ReadOnlyByteBuf;
import io.netty.buffer.Unpooled;
import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

class LazyResponseBodyPartDiffblueTest {
  /**
   * Method under test: {@link LazyResponseBodyPart#length()}
   */
  @Test
  void testLength() {
    // Arrange, Act and Assert
    assertEquals(0,
        (new LazyResponseBodyPart(new DuplicatedByteBuf(new EmptyByteBuf(new AdaptiveByteBufAllocator())), true))
            .length());
  }

  /**
   * Method under test: {@link LazyResponseBodyPart#length()}
   */
  @Test
  void testLength2() {
    // Arrange
    ByteBuf buffer = mock(ByteBuf.class);
    when(buffer.capacity()).thenReturn(3);
    when(buffer.maxCapacity()).thenReturn(3);
    when(buffer.readerIndex()).thenReturn(1);
    when(buffer.writerIndex()).thenReturn(1);

    // Act
    int actualLengthResult = (new LazyResponseBodyPart(
        new DuplicatedByteBuf(new ReadOnlyByteBuf(new DuplicatedByteBuf(buffer))), true)).length();

    // Assert
    verify(buffer, atLeast(1)).capacity();
    verify(buffer).maxCapacity();
    verify(buffer).readerIndex();
    verify(buffer).writerIndex();
    assertEquals(0, actualLengthResult);
  }

  /**
   * Method under test: {@link LazyResponseBodyPart#getBodyPartBytes()}
   */
  @Test
  void testGetBodyPartBytes() {
    // Arrange, Act and Assert
    assertEquals(0,
        (new LazyResponseBodyPart(new DuplicatedByteBuf(Unpooled.compositeBuffer(3)), true)).getBodyPartBytes().length);
    assertEquals(0,
        (new LazyResponseBodyPart(new DuplicatedByteBuf(
            new ReadOnlyByteBuf(new DuplicatedByteBuf(new EmptyByteBuf(new AdaptiveByteBufAllocator())))), true))
                .getBodyPartBytes().length);
    assertEquals(0,
        (new LazyResponseBodyPart(new DuplicatedByteBuf(new ReadOnlyByteBuf(Unpooled.compositeBuffer(3))), true))
            .getBodyPartBytes().length);
    assertEquals(0,
        (new LazyResponseBodyPart(new EmptyByteBuf(new AdaptiveByteBufAllocator()), true)).getBodyPartBytes().length);
  }

  /**
   * Method under test: {@link LazyResponseBodyPart#getBodyPartBytes()}
   */
  @Test
  void testGetBodyPartBytes2() {
    // Arrange
    ByteBuf buffer = mock(ByteBuf.class);
    when(buffer.capacity()).thenReturn(3);
    when(buffer.maxCapacity()).thenReturn(3);
    when(buffer.readerIndex()).thenReturn(1);
    when(buffer.writerIndex()).thenReturn(1);
    when(buffer.getBytes(anyInt(), Mockito.<byte[]>any(), anyInt(), anyInt()))
        .thenReturn(new DuplicatedByteBuf(new EmptyByteBuf(new AdaptiveByteBufAllocator())));

    // Act
    byte[] actualBodyPartBytes = (new LazyResponseBodyPart(new ReadOnlyByteBuf(new DuplicatedByteBuf(buffer)), true))
        .getBodyPartBytes();

    // Assert
    verify(buffer, atLeast(1)).capacity();
    verify(buffer).getBytes(eq(1), isA(byte[].class), eq(0), eq(0));
    verify(buffer).maxCapacity();
    verify(buffer).readerIndex();
    verify(buffer).writerIndex();
    assertEquals(0, actualBodyPartBytes.length);
  }

  /**
   * Method under test: {@link LazyResponseBodyPart#getBodyByteBuffer()}
   */
  @Test
  void testGetBodyByteBuffer() {
    // Arrange and Act
    ByteBuffer actualBodyByteBuffer = (new LazyResponseBodyPart(
        new DuplicatedByteBuf(new EmptyByteBuf(new AdaptiveByteBufAllocator())), true)).getBodyByteBuffer();

    // Assert
    assertEquals(0, actualBodyByteBuffer.capacity());
    assertEquals(0, actualBodyByteBuffer.limit());
    assertEquals(0, actualBodyByteBuffer.position());
    assertFalse(actualBodyByteBuffer.hasRemaining());
    assertFalse(actualBodyByteBuffer.hasArray());
  }

  /**
   * Method under test: {@link LazyResponseBodyPart#getBodyByteBuffer()}
   */
  @Test
  void testGetBodyByteBuffer2() {
    // Arrange and Act
    ByteBuffer actualBodyByteBuffer = (new LazyResponseBodyPart(new DuplicatedByteBuf(Unpooled.compositeBuffer(3)),
        true)).getBodyByteBuffer();

    // Assert
    assertEquals(0, actualBodyByteBuffer.capacity());
    assertEquals(0, actualBodyByteBuffer.limit());
    assertEquals(0, actualBodyByteBuffer.position());
    assertFalse(actualBodyByteBuffer.hasRemaining());
    assertFalse(actualBodyByteBuffer.hasArray());
  }

  /**
   * Method under test: {@link LazyResponseBodyPart#getBodyByteBuffer()}
   */
  @Test
  void testGetBodyByteBuffer3() throws UnsupportedEncodingException {
    // Arrange
    ByteBuf buffer = mock(ByteBuf.class);
    when(buffer.capacity()).thenReturn(3);
    when(buffer.maxCapacity()).thenReturn(3);
    when(buffer.readerIndex()).thenReturn(1);
    when(buffer.writerIndex()).thenReturn(1);
    ByteBuffer wrapResult = ByteBuffer.wrap("AXAXAXAX".getBytes("UTF-8"));
    when(buffer.nioBuffer(anyInt(), anyInt())).thenReturn(wrapResult);

    // Act
    ByteBuffer actualBodyByteBuffer = (new LazyResponseBodyPart(
        new DuplicatedByteBuf(new ReadOnlyByteBuf(new DuplicatedByteBuf(buffer))), true)).getBodyByteBuffer();

    // Assert
    verify(buffer, atLeast(1)).capacity();
    verify(buffer).maxCapacity();
    verify(buffer).nioBuffer(eq(1), eq(0));
    verify(buffer).readerIndex();
    verify(buffer).writerIndex();
    assertEquals(wrapResult, actualBodyByteBuffer);
  }

  /**
   * Methods under test:
   * <ul>
   *   <li>{@link LazyResponseBodyPart#LazyResponseBodyPart(ByteBuf, boolean)}
   *   <li>{@link LazyResponseBodyPart#getBodyByteBuf()}
   * </ul>
   */
  @Test
  void testGettersAndSetters() {
    // Arrange
    DuplicatedByteBuf buf = new DuplicatedByteBuf(new EmptyByteBuf(new AdaptiveByteBufAllocator()));

    // Act
    LazyResponseBodyPart actualLazyResponseBodyPart = new LazyResponseBodyPart(buf, true);
    ByteBuf actualBodyByteBuf = actualLazyResponseBodyPart.getBodyByteBuf();

    // Assert
    assertTrue(actualLazyResponseBodyPart.isLast());
    assertSame(buf, actualBodyByteBuf);
  }
}
