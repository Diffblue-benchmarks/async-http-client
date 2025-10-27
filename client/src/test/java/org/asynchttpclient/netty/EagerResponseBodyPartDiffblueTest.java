package org.asynchttpclient.netty;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
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
import io.netty.buffer.ByteBufAllocator;
import io.netty.buffer.CompositeByteBuf;
import io.netty.buffer.DuplicatedByteBuf;
import io.netty.buffer.EmptyByteBuf;
import io.netty.buffer.ReadOnlyByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.buffer.UnpooledByteBufAllocator;
import io.netty.buffer.UnpooledDirectByteBuf;
import io.netty.buffer.UnpooledHeapByteBuf;
import java.nio.ByteBuffer;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

class EagerResponseBodyPartDiffblueTest {
  /**
   * Method under test: {@link EagerResponseBodyPart#getBodyPartBytes()}
   */
  @Test
  void testGetBodyPartBytes() {
    // Arrange, Act and Assert
    assertEquals(0,
        (new EagerResponseBodyPart(new DuplicatedByteBuf(new EmptyByteBuf(new AdaptiveByteBufAllocator())), true))
            .getBodyPartBytes().length);
  }

  /**
   * Method under test: {@link EagerResponseBodyPart#length()}
   */
  @Test
  void testLength() {
    // Arrange, Act and Assert
    assertEquals(0,
        (new EagerResponseBodyPart(new DuplicatedByteBuf(new EmptyByteBuf(new AdaptiveByteBufAllocator())), true))
            .length());
  }

  /**
   * Method under test: {@link EagerResponseBodyPart#length()}
   */
  @Test
  void testLength2() {
    // Arrange
    ByteBuf buffer = mock(ByteBuf.class);
    when(buffer.capacity()).thenReturn(3);
    when(buffer.maxCapacity()).thenReturn(3);
    when(buffer.readerIndex()).thenReturn(1);
    when(buffer.writerIndex()).thenReturn(1);
    when(buffer.getBytes(anyInt(), Mockito.<byte[]>any(), anyInt(), anyInt()))
        .thenReturn(new DuplicatedByteBuf(new EmptyByteBuf(new AdaptiveByteBufAllocator())));

    // Act
    int actualLengthResult = (new EagerResponseBodyPart(
        new DuplicatedByteBuf(new ReadOnlyByteBuf(new DuplicatedByteBuf(buffer))), true)).length();

    // Assert
    verify(buffer, atLeast(1)).capacity();
    verify(buffer).getBytes(eq(1), isA(byte[].class), eq(0), eq(0));
    verify(buffer).maxCapacity();
    verify(buffer).readerIndex();
    verify(buffer).writerIndex();
    assertEquals(0, actualLengthResult);
  }

  /**
   * Method under test: {@link EagerResponseBodyPart#getBodyByteBuffer()}
   */
  @Test
  void testGetBodyByteBuffer() {
    // Arrange and Act
    ByteBuffer actualBodyByteBuffer = (new EagerResponseBodyPart(
        new DuplicatedByteBuf(new EmptyByteBuf(new AdaptiveByteBufAllocator())), true)).getBodyByteBuffer();

    // Assert
    assertEquals(0, actualBodyByteBuffer.capacity());
    assertEquals(0, actualBodyByteBuffer.limit());
    assertEquals(0, actualBodyByteBuffer.position());
    assertEquals(0, actualBodyByteBuffer.array().length);
    assertFalse(actualBodyByteBuffer.hasRemaining());
    assertTrue(actualBodyByteBuffer.hasArray());
  }

  /**
   * Method under test: {@link EagerResponseBodyPart#getBodyByteBuffer()}
   */
  @Test
  void testGetBodyByteBuffer2() {
    // Arrange
    ByteBuf buffer = mock(ByteBuf.class);
    when(buffer.capacity()).thenReturn(3);
    when(buffer.maxCapacity()).thenReturn(3);
    when(buffer.readerIndex()).thenReturn(1);
    when(buffer.writerIndex()).thenReturn(1);
    when(buffer.getBytes(anyInt(), Mockito.<byte[]>any(), anyInt(), anyInt()))
        .thenReturn(new DuplicatedByteBuf(new EmptyByteBuf(new AdaptiveByteBufAllocator())));

    // Act
    ByteBuffer actualBodyByteBuffer = (new EagerResponseBodyPart(
        new DuplicatedByteBuf(new ReadOnlyByteBuf(new DuplicatedByteBuf(buffer))), true)).getBodyByteBuffer();

    // Assert
    verify(buffer, atLeast(1)).capacity();
    verify(buffer).getBytes(eq(1), isA(byte[].class), eq(0), eq(0));
    verify(buffer).maxCapacity();
    verify(buffer).readerIndex();
    verify(buffer).writerIndex();
    assertEquals(0, actualBodyByteBuffer.capacity());
    assertEquals(0, actualBodyByteBuffer.limit());
    assertEquals(0, actualBodyByteBuffer.position());
    assertEquals(0, actualBodyByteBuffer.array().length);
    assertFalse(actualBodyByteBuffer.hasRemaining());
    assertTrue(actualBodyByteBuffer.hasArray());
  }

  /**
   * Method under test: {@link EagerResponseBodyPart#getBodyByteBuf()}
   */
  @Test
  void testGetBodyByteBuf() {
    // Arrange
    EmptyByteBuf buffer = new EmptyByteBuf(new AdaptiveByteBufAllocator());

    // Act
    ByteBuf actualBodyByteBuf = (new EagerResponseBodyPart(new DuplicatedByteBuf(buffer), true)).getBodyByteBuf();

    // Assert
    assertTrue(actualBodyByteBuf instanceof EmptyByteBuf);
    assertEquals(buffer, actualBodyByteBuf);
  }

  /**
   * Method under test: {@link EagerResponseBodyPart#getBodyByteBuf()}
   */
  @Test
  void testGetBodyByteBuf2() {
    // Arrange
    ByteBuf buffer = mock(ByteBuf.class);
    when(buffer.capacity()).thenReturn(3);
    when(buffer.maxCapacity()).thenReturn(3);
    when(buffer.readerIndex()).thenReturn(1);
    when(buffer.writerIndex()).thenReturn(1);
    EmptyByteBuf buffer2 = new EmptyByteBuf(new AdaptiveByteBufAllocator());
    when(buffer.getBytes(anyInt(), Mockito.<byte[]>any(), anyInt(), anyInt()))
        .thenReturn(new DuplicatedByteBuf(buffer2));

    // Act
    ByteBuf actualBodyByteBuf = (new EagerResponseBodyPart(
        new DuplicatedByteBuf(new ReadOnlyByteBuf(new DuplicatedByteBuf(buffer))), true)).getBodyByteBuf();

    // Assert
    verify(buffer, atLeast(1)).capacity();
    verify(buffer).getBytes(eq(1), isA(byte[].class), eq(0), eq(0));
    verify(buffer).maxCapacity();
    verify(buffer).readerIndex();
    verify(buffer).writerIndex();
    assertTrue(actualBodyByteBuf instanceof EmptyByteBuf);
    assertEquals(buffer2, actualBodyByteBuf);
  }

  /**
   * Method under test: {@link EagerResponseBodyPart#getBodyByteBuf()}
   */
  @Test
  void testGetBodyByteBuf3() {
    // Arrange
    ByteBuf buffer = mock(ByteBuf.class);
    when(buffer.capacity()).thenReturn(3);
    when(buffer.maxCapacity()).thenReturn(3);
    when(buffer.readerIndex()).thenReturn(0);
    when(buffer.writerIndex()).thenReturn(1);
    when(buffer.getBytes(anyInt(), Mockito.<byte[]>any(), anyInt(), anyInt()))
        .thenReturn(new DuplicatedByteBuf(new EmptyByteBuf(new AdaptiveByteBufAllocator())));
    DuplicatedByteBuf buffer2 = new DuplicatedByteBuf(buffer);

    // Act
    ByteBuf actualBodyByteBuf = (new EagerResponseBodyPart(new DuplicatedByteBuf(new ReadOnlyByteBuf(buffer2)), true))
        .getBodyByteBuf();

    // Assert
    verify(buffer, atLeast(1)).capacity();
    verify(buffer).getBytes(eq(0), isA(byte[].class), eq(0), eq(1));
    verify(buffer).maxCapacity();
    verify(buffer).readerIndex();
    verify(buffer).writerIndex();
    assertTrue(actualBodyByteBuf instanceof UnpooledHeapByteBuf);
    assertEquals(buffer2, actualBodyByteBuf);
  }

  /**
   * Method under test:
   * {@link EagerResponseBodyPart#EagerResponseBodyPart(ByteBuf, boolean)}
   */
  @Test
  void testNewEagerResponseBodyPart() {
    // Arrange
    EmptyByteBuf buffer = new EmptyByteBuf(new AdaptiveByteBufAllocator());

    // Act
    EagerResponseBodyPart actualEagerResponseBodyPart = new EagerResponseBodyPart(new DuplicatedByteBuf(buffer), true);

    // Assert
    ByteBuf bodyByteBuf = actualEagerResponseBodyPart.getBodyByteBuf();
    assertTrue(bodyByteBuf instanceof EmptyByteBuf);
    ByteBuffer bodyByteBuffer = actualEagerResponseBodyPart.getBodyByteBuffer();
    assertEquals(0, bodyByteBuffer.capacity());
    assertEquals(0, bodyByteBuffer.limit());
    assertEquals(0, bodyByteBuffer.position());
    assertEquals(0, actualEagerResponseBodyPart.length());
    byte[] bodyPartBytes = actualEagerResponseBodyPart.getBodyPartBytes();
    assertEquals(0, bodyPartBytes.length);
    assertFalse(bodyByteBuffer.hasRemaining());
    assertTrue(bodyByteBuffer.hasArray());
    assertTrue(actualEagerResponseBodyPart.isLast());
    assertEquals(buffer, bodyByteBuf);
    assertSame(bodyPartBytes, bodyByteBuffer.array());
  }

  /**
   * Method under test:
   * {@link EagerResponseBodyPart#EagerResponseBodyPart(ByteBuf, boolean)}
   */
  @Test
  void testNewEagerResponseBodyPart2() {
    // Arrange
    EmptyByteBuf buffer = new EmptyByteBuf(new AdaptiveByteBufAllocator());

    // Act
    EagerResponseBodyPart actualEagerResponseBodyPart = new EagerResponseBodyPart(
        new DuplicatedByteBuf(new ReadOnlyByteBuf(new DuplicatedByteBuf(buffer))), true);

    // Assert
    ByteBuf bodyByteBuf = actualEagerResponseBodyPart.getBodyByteBuf();
    assertTrue(bodyByteBuf instanceof EmptyByteBuf);
    ByteBuffer bodyByteBuffer = actualEagerResponseBodyPart.getBodyByteBuffer();
    assertEquals(0, bodyByteBuffer.capacity());
    assertEquals(0, bodyByteBuffer.limit());
    assertEquals(0, bodyByteBuffer.position());
    assertEquals(0, actualEagerResponseBodyPart.length());
    byte[] bodyPartBytes = actualEagerResponseBodyPart.getBodyPartBytes();
    assertEquals(0, bodyPartBytes.length);
    assertFalse(bodyByteBuffer.hasRemaining());
    assertTrue(bodyByteBuffer.hasArray());
    assertTrue(actualEagerResponseBodyPart.isLast());
    assertEquals(buffer, bodyByteBuf);
    assertSame(bodyPartBytes, bodyByteBuffer.array());
  }

  /**
   * Method under test:
   * {@link EagerResponseBodyPart#EagerResponseBodyPart(ByteBuf, boolean)}
   */
  @Test
  void testNewEagerResponseBodyPart3() {
    // Arrange
    ByteBuf buffer = mock(ByteBuf.class);
    when(buffer.capacity()).thenReturn(3);
    when(buffer.maxCapacity()).thenReturn(3);
    when(buffer.readerIndex()).thenReturn(1);
    when(buffer.writerIndex()).thenReturn(1);
    EmptyByteBuf buffer2 = new EmptyByteBuf(new AdaptiveByteBufAllocator());
    when(buffer.getBytes(anyInt(), Mockito.<byte[]>any(), anyInt(), anyInt()))
        .thenReturn(new DuplicatedByteBuf(buffer2));

    // Act
    EagerResponseBodyPart actualEagerResponseBodyPart = new EagerResponseBodyPart(
        new DuplicatedByteBuf(new ReadOnlyByteBuf(new DuplicatedByteBuf(buffer))), true);

    // Assert
    verify(buffer, atLeast(1)).capacity();
    verify(buffer).getBytes(eq(1), isA(byte[].class), eq(0), eq(0));
    verify(buffer).maxCapacity();
    verify(buffer).readerIndex();
    verify(buffer).writerIndex();
    ByteBuf bodyByteBuf = actualEagerResponseBodyPart.getBodyByteBuf();
    assertTrue(bodyByteBuf instanceof EmptyByteBuf);
    ByteBuffer bodyByteBuffer = actualEagerResponseBodyPart.getBodyByteBuffer();
    assertEquals(0, bodyByteBuffer.capacity());
    assertEquals(0, bodyByteBuffer.limit());
    assertEquals(0, bodyByteBuffer.position());
    assertEquals(0, actualEagerResponseBodyPart.length());
    byte[] bodyPartBytes = actualEagerResponseBodyPart.getBodyPartBytes();
    assertEquals(0, bodyPartBytes.length);
    assertFalse(bodyByteBuffer.hasRemaining());
    assertTrue(bodyByteBuffer.hasArray());
    assertTrue(actualEagerResponseBodyPart.isLast());
    assertEquals(buffer2, bodyByteBuf);
    assertSame(bodyPartBytes, bodyByteBuffer.array());
  }

  /**
   * Method under test:
   * {@link EagerResponseBodyPart#EagerResponseBodyPart(ByteBuf, boolean)}
   */
  @Test
  void testNewEagerResponseBodyPart4() {
    // Arrange
    CompositeByteBuf buffer = Unpooled.compositeBuffer(3);

    // Act
    EagerResponseBodyPart actualEagerResponseBodyPart = new EagerResponseBodyPart(
        new DuplicatedByteBuf(new ReadOnlyByteBuf(buffer)), true);

    // Assert
    ByteBuf bodyByteBuf = actualEagerResponseBodyPart.getBodyByteBuf();
    assertTrue(bodyByteBuf instanceof EmptyByteBuf);
    ByteBuffer bodyByteBuffer = actualEagerResponseBodyPart.getBodyByteBuffer();
    assertEquals(0, bodyByteBuffer.capacity());
    assertEquals(0, bodyByteBuffer.limit());
    assertEquals(0, bodyByteBuffer.position());
    assertEquals(0, actualEagerResponseBodyPart.length());
    byte[] bodyPartBytes = actualEagerResponseBodyPart.getBodyPartBytes();
    assertEquals(0, bodyPartBytes.length);
    assertFalse(bodyByteBuffer.hasRemaining());
    assertTrue(bodyByteBuffer.hasArray());
    assertTrue(actualEagerResponseBodyPart.isLast());
    assertEquals(buffer, bodyByteBuf);
    assertSame(bodyPartBytes, bodyByteBuffer.array());
  }

  /**
   * Method under test:
   * {@link EagerResponseBodyPart#EagerResponseBodyPart(ByteBuf, boolean)}
   */
  @Test
  void testNewEagerResponseBodyPart5() {
    // Arrange
    UnpooledDirectByteBuf buffer = new UnpooledDirectByteBuf(new AdaptiveByteBufAllocator(), 1, 3);

    // Act
    EagerResponseBodyPart actualEagerResponseBodyPart = new EagerResponseBodyPart(new DuplicatedByteBuf(buffer), true);

    // Assert
    ByteBuf bodyByteBuf = actualEagerResponseBodyPart.getBodyByteBuf();
    assertTrue(bodyByteBuf instanceof EmptyByteBuf);
    ByteBuffer bodyByteBuffer = actualEagerResponseBodyPart.getBodyByteBuffer();
    assertEquals(0, bodyByteBuffer.capacity());
    assertEquals(0, bodyByteBuffer.limit());
    assertEquals(0, bodyByteBuffer.position());
    assertEquals(0, actualEagerResponseBodyPart.length());
    byte[] bodyPartBytes = actualEagerResponseBodyPart.getBodyPartBytes();
    assertEquals(0, bodyPartBytes.length);
    assertFalse(bodyByteBuffer.hasRemaining());
    assertTrue(bodyByteBuffer.hasArray());
    assertTrue(actualEagerResponseBodyPart.isLast());
    assertEquals(buffer, bodyByteBuf);
    assertSame(bodyPartBytes, bodyByteBuffer.array());
  }

  /**
   * Method under test:
   * {@link EagerResponseBodyPart#EagerResponseBodyPart(ByteBuf, boolean)}
   */
  @Test
  void testNewEagerResponseBodyPart6() {
    // Arrange
    UnpooledHeapByteBuf buffer = new UnpooledHeapByteBuf(new AdaptiveByteBufAllocator(), 1, 3);

    // Act
    EagerResponseBodyPart actualEagerResponseBodyPart = new EagerResponseBodyPart(
        new DuplicatedByteBuf(new ReadOnlyByteBuf(buffer)), true);

    // Assert
    ByteBuf bodyByteBuf = actualEagerResponseBodyPart.getBodyByteBuf();
    assertTrue(bodyByteBuf instanceof EmptyByteBuf);
    ByteBuffer bodyByteBuffer = actualEagerResponseBodyPart.getBodyByteBuffer();
    assertEquals(0, bodyByteBuffer.capacity());
    assertEquals(0, bodyByteBuffer.limit());
    assertEquals(0, bodyByteBuffer.position());
    assertEquals(0, actualEagerResponseBodyPart.length());
    byte[] bodyPartBytes = actualEagerResponseBodyPart.getBodyPartBytes();
    assertEquals(0, bodyPartBytes.length);
    assertFalse(bodyByteBuffer.hasRemaining());
    assertTrue(bodyByteBuffer.hasArray());
    assertTrue(actualEagerResponseBodyPart.isLast());
    assertEquals(buffer, bodyByteBuf);
    assertSame(bodyPartBytes, bodyByteBuffer.array());
  }

  /**
   * Method under test:
   * {@link EagerResponseBodyPart#EagerResponseBodyPart(ByteBuf, boolean)}
   */
  @Test
  void testNewEagerResponseBodyPart7() {
    // Arrange
    UnpooledHeapByteBuf buffer = new UnpooledHeapByteBuf(new AdaptiveByteBufAllocator(), 1, 3);

    // Act
    EagerResponseBodyPart actualEagerResponseBodyPart = new EagerResponseBodyPart(new DuplicatedByteBuf(buffer), true);

    // Assert
    ByteBuf bodyByteBuf = actualEagerResponseBodyPart.getBodyByteBuf();
    assertTrue(bodyByteBuf instanceof EmptyByteBuf);
    ByteBuffer bodyByteBuffer = actualEagerResponseBodyPart.getBodyByteBuffer();
    assertEquals(0, bodyByteBuffer.capacity());
    assertEquals(0, bodyByteBuffer.limit());
    assertEquals(0, bodyByteBuffer.position());
    assertEquals(0, actualEagerResponseBodyPart.length());
    byte[] bodyPartBytes = actualEagerResponseBodyPart.getBodyPartBytes();
    assertEquals(0, bodyPartBytes.length);
    assertFalse(bodyByteBuffer.hasRemaining());
    assertTrue(bodyByteBuffer.hasArray());
    assertTrue(actualEagerResponseBodyPart.isLast());
    assertEquals(buffer, bodyByteBuf);
    assertSame(bodyPartBytes, bodyByteBuffer.array());
  }

  /**
   * Method under test:
   * {@link EagerResponseBodyPart#EagerResponseBodyPart(ByteBuf, boolean)}
   */
  @Test
  void testNewEagerResponseBodyPart8() {
    // Arrange
    CompositeByteBuf buffer = Unpooled.compositeBuffer(3);
    buffer.writeByte(42);

    // Act
    EagerResponseBodyPart actualEagerResponseBodyPart = new EagerResponseBodyPart(
        new DuplicatedByteBuf(new ReadOnlyByteBuf(buffer)), true);

    // Assert
    ByteBuf bodyByteBuf = actualEagerResponseBodyPart.getBodyByteBuf();
    ByteBufAllocator allocResult = bodyByteBuf.alloc();
    assertTrue(allocResult instanceof UnpooledByteBufAllocator);
    assertTrue(bodyByteBuf instanceof UnpooledHeapByteBuf);
    assertNull(bodyByteBuf.unwrap());
    ByteBuffer bodyByteBuffer = actualEagerResponseBodyPart.getBodyByteBuffer();
    assertEquals(0, bodyByteBuffer.position());
    assertEquals(1, bodyByteBuf.capacity());
    assertEquals(1, bodyByteBuf.maxCapacity());
    assertEquals(1, bodyByteBuf.writerIndex());
    assertEquals(1, bodyByteBuf.refCnt());
    assertEquals(1, bodyByteBuffer.capacity());
    assertEquals(1, bodyByteBuffer.limit());
    assertEquals(1, actualEagerResponseBodyPart.length());
    assertFalse(bodyByteBuf.hasMemoryAddress());
    assertFalse(bodyByteBuf.isDirect());
    assertFalse(bodyByteBuf.isReadOnly());
    assertFalse(bodyByteBuf.isWritable());
    assertFalse(allocResult.isDirectBufferPooled());
    assertTrue(bodyByteBuf.hasArray());
    assertTrue(bodyByteBuf.isContiguous());
    assertTrue(bodyByteBuf.isReadable());
    assertTrue(bodyByteBuf.readBoolean());
    assertTrue(bodyByteBuffer.hasRemaining());
    assertTrue(bodyByteBuffer.hasArray());
    assertTrue(actualEagerResponseBodyPart.isLast());
    byte[] bodyPartBytes = actualEagerResponseBodyPart.getBodyPartBytes();
    assertSame(bodyPartBytes, bodyByteBuffer.array());
    assertArrayEquals(new byte[]{'*'}, bodyPartBytes);
  }
}
