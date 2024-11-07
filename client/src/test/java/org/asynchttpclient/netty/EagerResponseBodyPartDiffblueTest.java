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
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

class EagerResponseBodyPartDiffblueTest {
  /**
   * Test {@link EagerResponseBodyPart#EagerResponseBodyPart(ByteBuf, boolean)}.
   * <p>
   * Method under test:
   * {@link EagerResponseBodyPart#EagerResponseBodyPart(ByteBuf, boolean)}
   */
  @Test
  @DisplayName("Test new EagerResponseBodyPart(ByteBuf, boolean)")
  void testNewEagerResponseBodyPart() {
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
    assertEquals(0, actualEagerResponseBodyPart.length());
    byte[] bodyPartBytes = actualEagerResponseBodyPart.getBodyPartBytes();
    assertEquals(0, bodyPartBytes.length);
    assertFalse(bodyByteBuffer.hasRemaining());
    assertEquals(buffer, bodyByteBuf);
    assertSame(bodyPartBytes, bodyByteBuffer.array());
  }

  /**
   * Test {@link EagerResponseBodyPart#EagerResponseBodyPart(ByteBuf, boolean)}.
   * <p>
   * Method under test:
   * {@link EagerResponseBodyPart#EagerResponseBodyPart(ByteBuf, boolean)}
   */
  @Test
  @DisplayName("Test new EagerResponseBodyPart(ByteBuf, boolean)")
  void testNewEagerResponseBodyPart2() {
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
    assertEquals(0, actualEagerResponseBodyPart.length());
    byte[] bodyPartBytes = actualEagerResponseBodyPart.getBodyPartBytes();
    assertEquals(0, bodyPartBytes.length);
    assertFalse(bodyByteBuffer.hasRemaining());
    assertEquals(buffer, bodyByteBuf);
    assertSame(bodyPartBytes, bodyByteBuffer.array());
  }

  /**
   * Test {@link EagerResponseBodyPart#EagerResponseBodyPart(ByteBuf, boolean)}.
   * <ul>
   *   <li>Given three.</li>
   *   <li>Then calls {@link ByteBuf#capacity()}.</li>
   * </ul>
   * <p>
   * Method under test:
   * {@link EagerResponseBodyPart#EagerResponseBodyPart(ByteBuf, boolean)}
   */
  @Test
  @DisplayName("Test new EagerResponseBodyPart(ByteBuf, boolean); given three; then calls capacity()")
  void testNewEagerResponseBodyPart_givenThree_thenCallsCapacity() {
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
    assertEquals(0, actualEagerResponseBodyPart.length());
    byte[] bodyPartBytes = actualEagerResponseBodyPart.getBodyPartBytes();
    assertEquals(0, bodyPartBytes.length);
    assertFalse(bodyByteBuffer.hasRemaining());
    assertEquals(buffer2, bodyByteBuf);
    assertSame(bodyPartBytes, bodyByteBuffer.array());
  }

  /**
   * Test {@link EagerResponseBodyPart#EagerResponseBodyPart(ByteBuf, boolean)}.
   * <ul>
   *   <li>Then BodyByteBuf alloc return {@link UnpooledByteBufAllocator}.</li>
   * </ul>
   * <p>
   * Method under test:
   * {@link EagerResponseBodyPart#EagerResponseBodyPart(ByteBuf, boolean)}
   */
  @Test
  @DisplayName("Test new EagerResponseBodyPart(ByteBuf, boolean); then BodyByteBuf alloc return UnpooledByteBufAllocator")
  void testNewEagerResponseBodyPart_thenBodyByteBufAllocReturnUnpooledByteBufAllocator() {
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
    assertEquals(1, bodyByteBuf.capacity());
    assertEquals(1, bodyByteBuf.maxCapacity());
    assertEquals(1, bodyByteBuf.writerIndex());
    assertEquals(1, bodyByteBuf.refCnt());
    ByteBuffer bodyByteBuffer = actualEagerResponseBodyPart.getBodyByteBuffer();
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
    assertArrayEquals(new byte[]{'*'}, actualEagerResponseBodyPart.getBodyPartBytes());
  }

  /**
   * Test {@link EagerResponseBodyPart#EagerResponseBodyPart(ByteBuf, boolean)}.
   * <ul>
   *   <li>Then return BodyByteBuf is compositeBuffer three.</li>
   * </ul>
   * <p>
   * Method under test:
   * {@link EagerResponseBodyPart#EagerResponseBodyPart(ByteBuf, boolean)}
   */
  @Test
  @DisplayName("Test new EagerResponseBodyPart(ByteBuf, boolean); then return BodyByteBuf is compositeBuffer three")
  void testNewEagerResponseBodyPart_thenReturnBodyByteBufIsCompositeBufferThree() {
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
    assertEquals(0, actualEagerResponseBodyPart.length());
    byte[] bodyPartBytes = actualEagerResponseBodyPart.getBodyPartBytes();
    assertEquals(0, bodyPartBytes.length);
    assertFalse(bodyByteBuffer.hasRemaining());
    assertEquals(buffer, bodyByteBuf);
    assertSame(bodyPartBytes, bodyByteBuffer.array());
  }

  /**
   * Test {@link EagerResponseBodyPart#EagerResponseBodyPart(ByteBuf, boolean)}.
   * <ul>
   *   <li>When {@link EmptyByteBuf#EmptyByteBuf(ByteBufAllocator)} with alloc is
   * {@link AdaptiveByteBufAllocator#AdaptiveByteBufAllocator()}.</li>
   * </ul>
   * <p>
   * Method under test:
   * {@link EagerResponseBodyPart#EagerResponseBodyPart(ByteBuf, boolean)}
   */
  @Test
  @DisplayName("Test new EagerResponseBodyPart(ByteBuf, boolean); when EmptyByteBuf(ByteBufAllocator) with alloc is AdaptiveByteBufAllocator()")
  void testNewEagerResponseBodyPart_whenEmptyByteBufWithAllocIsAdaptiveByteBufAllocator() {
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
    assertEquals(0, actualEagerResponseBodyPart.length());
    byte[] bodyPartBytes = actualEagerResponseBodyPart.getBodyPartBytes();
    assertEquals(0, bodyPartBytes.length);
    assertFalse(bodyByteBuffer.hasRemaining());
    assertEquals(buffer, bodyByteBuf);
    assertSame(bodyPartBytes, bodyByteBuffer.array());
  }

  /**
   * Test {@link EagerResponseBodyPart#EagerResponseBodyPart(ByteBuf, boolean)}.
   * <ul>
   *   <li>When {@link EmptyByteBuf#EmptyByteBuf(ByteBufAllocator)} with alloc is
   * {@link AdaptiveByteBufAllocator#AdaptiveByteBufAllocator()}.</li>
   * </ul>
   * <p>
   * Method under test:
   * {@link EagerResponseBodyPart#EagerResponseBodyPart(ByteBuf, boolean)}
   */
  @Test
  @DisplayName("Test new EagerResponseBodyPart(ByteBuf, boolean); when EmptyByteBuf(ByteBufAllocator) with alloc is AdaptiveByteBufAllocator()")
  void testNewEagerResponseBodyPart_whenEmptyByteBufWithAllocIsAdaptiveByteBufAllocator2() {
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
    assertEquals(0, actualEagerResponseBodyPart.length());
    byte[] bodyPartBytes = actualEagerResponseBodyPart.getBodyPartBytes();
    assertEquals(0, bodyPartBytes.length);
    assertFalse(bodyByteBuffer.hasRemaining());
    assertEquals(buffer, bodyByteBuf);
    assertSame(bodyPartBytes, bodyByteBuffer.array());
  }

  /**
   * Test {@link EagerResponseBodyPart#EagerResponseBodyPart(ByteBuf, boolean)}.
   * <ul>
   *   <li>When {@link ReadOnlyByteBuf#ReadOnlyByteBuf(ByteBuf)} with buffer is
   * {@link UnpooledHeapByteBuf#UnpooledHeapByteBuf(ByteBufAllocator, int, int)}.</li>
   * </ul>
   * <p>
   * Method under test:
   * {@link EagerResponseBodyPart#EagerResponseBodyPart(ByteBuf, boolean)}
   */
  @Test
  @DisplayName("Test new EagerResponseBodyPart(ByteBuf, boolean); when ReadOnlyByteBuf(ByteBuf) with buffer is UnpooledHeapByteBuf(ByteBufAllocator, int, int)")
  void testNewEagerResponseBodyPart_whenReadOnlyByteBufWithBufferIsUnpooledHeapByteBuf() {
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
    assertEquals(0, actualEagerResponseBodyPart.length());
    byte[] bodyPartBytes = actualEagerResponseBodyPart.getBodyPartBytes();
    assertEquals(0, bodyPartBytes.length);
    assertFalse(bodyByteBuffer.hasRemaining());
    assertEquals(buffer, bodyByteBuf);
    assertSame(bodyPartBytes, bodyByteBuffer.array());
  }

  /**
   * Test {@link EagerResponseBodyPart#getBodyPartBytes()}.
   * <p>
   * Method under test: {@link EagerResponseBodyPart#getBodyPartBytes()}
   */
  @Test
  @DisplayName("Test getBodyPartBytes()")
  void testGetBodyPartBytes() {
    // Arrange, Act and Assert
    assertEquals(0,
        (new EagerResponseBodyPart(new DuplicatedByteBuf(new EmptyByteBuf(new AdaptiveByteBufAllocator())), true))
            .getBodyPartBytes().length);
  }

  /**
   * Test {@link EagerResponseBodyPart#length()}.
   * <p>
   * Method under test: {@link EagerResponseBodyPart#length()}
   */
  @Test
  @DisplayName("Test length()")
  void testLength() {
    // Arrange, Act and Assert
    assertEquals(0,
        (new EagerResponseBodyPart(new DuplicatedByteBuf(new EmptyByteBuf(new AdaptiveByteBufAllocator())), true))
            .length());
  }

  /**
   * Test {@link EagerResponseBodyPart#getBodyByteBuffer()}.
   * <p>
   * Method under test: {@link EagerResponseBodyPart#getBodyByteBuffer()}
   */
  @Test
  @DisplayName("Test getBodyByteBuffer()")
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
   * Test {@link EagerResponseBodyPart#getBodyByteBuf()}.
   * <p>
   * Method under test: {@link EagerResponseBodyPart#getBodyByteBuf()}
   */
  @Test
  @DisplayName("Test getBodyByteBuf()")
  void testGetBodyByteBuf() {
    // Arrange
    EmptyByteBuf buffer = new EmptyByteBuf(new AdaptiveByteBufAllocator());

    // Act
    ByteBuf actualBodyByteBuf = (new EagerResponseBodyPart(new DuplicatedByteBuf(buffer), true)).getBodyByteBuf();

    // Assert
    assertTrue(actualBodyByteBuf instanceof EmptyByteBuf);
    assertEquals(buffer, actualBodyByteBuf);
  }
}
