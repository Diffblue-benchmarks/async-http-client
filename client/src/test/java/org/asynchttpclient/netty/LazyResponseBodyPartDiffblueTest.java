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
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

class LazyResponseBodyPartDiffblueTest {
  /**
   * Test getters and setters.
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link LazyResponseBodyPart#LazyResponseBodyPart(ByteBuf, boolean)}
   *   <li>{@link LazyResponseBodyPart#getBodyByteBuf()}
   * </ul>
   */
  @Test
  @DisplayName("Test getters and setters")
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

  /**
   * Test {@link LazyResponseBodyPart#length()}.
   * <ul>
   *   <li>Given {@link EmptyByteBuf#EmptyByteBuf(ByteBufAllocator)} with alloc is
   * {@link AdaptiveByteBufAllocator#AdaptiveByteBufAllocator()}.</li>
   *   <li>Then return zero.</li>
   * </ul>
   * <p>
   * Method under test: {@link LazyResponseBodyPart#length()}
   */
  @Test
  @DisplayName("Test length(); given EmptyByteBuf(ByteBufAllocator) with alloc is AdaptiveByteBufAllocator(); then return zero")
  void testLength_givenEmptyByteBufWithAllocIsAdaptiveByteBufAllocator_thenReturnZero() {
    // Arrange, Act and Assert
    assertEquals(0,
        (new LazyResponseBodyPart(new DuplicatedByteBuf(new EmptyByteBuf(new AdaptiveByteBufAllocator())), true))
            .length());
  }

  /**
   * Test {@link LazyResponseBodyPart#getBodyPartBytes()}.
   * <ul>
   *   <li>Given {@link DuplicatedByteBuf#DuplicatedByteBuf(ByteBuf)} with buffer is
   * compositeBuffer three.</li>
   * </ul>
   * <p>
   * Method under test: {@link LazyResponseBodyPart#getBodyPartBytes()}
   */
  @Test
  @DisplayName("Test getBodyPartBytes(); given DuplicatedByteBuf(ByteBuf) with buffer is compositeBuffer three")
  void testGetBodyPartBytes_givenDuplicatedByteBufWithBufferIsCompositeBufferThree() {
    // Arrange, Act and Assert
    assertEquals(0,
        (new LazyResponseBodyPart(new DuplicatedByteBuf(Unpooled.compositeBuffer(3)), true)).getBodyPartBytes().length);
  }

  /**
   * Test {@link LazyResponseBodyPart#getBodyPartBytes()}.
   * <ul>
   *   <li>Given {@link DuplicatedByteBuf#DuplicatedByteBuf(ByteBuf)} with buffer is
   * {@link EmptyByteBuf#EmptyByteBuf(ByteBufAllocator)}.</li>
   * </ul>
   * <p>
   * Method under test: {@link LazyResponseBodyPart#getBodyPartBytes()}
   */
  @Test
  @DisplayName("Test getBodyPartBytes(); given DuplicatedByteBuf(ByteBuf) with buffer is EmptyByteBuf(ByteBufAllocator)")
  void testGetBodyPartBytes_givenDuplicatedByteBufWithBufferIsEmptyByteBuf() {
    // Arrange, Act and Assert
    assertEquals(0,
        (new LazyResponseBodyPart(new DuplicatedByteBuf(
            new ReadOnlyByteBuf(new DuplicatedByteBuf(new EmptyByteBuf(new AdaptiveByteBufAllocator())))), true))
                .getBodyPartBytes().length);
  }

  /**
   * Test {@link LazyResponseBodyPart#getBodyPartBytes()}.
   * <ul>
   *   <li>Given {@link LazyResponseBodyPart#LazyResponseBodyPart(ByteBuf, boolean)}
   * with buf is {@link EmptyByteBuf#EmptyByteBuf(ByteBufAllocator)} and last is
   * {@code true}.</li>
   * </ul>
   * <p>
   * Method under test: {@link LazyResponseBodyPart#getBodyPartBytes()}
   */
  @Test
  @DisplayName("Test getBodyPartBytes(); given LazyResponseBodyPart(ByteBuf, boolean) with buf is EmptyByteBuf(ByteBufAllocator) and last is 'true'")
  void testGetBodyPartBytes_givenLazyResponseBodyPartWithBufIsEmptyByteBufAndLastIsTrue() {
    // Arrange, Act and Assert
    assertEquals(0,
        (new LazyResponseBodyPart(new EmptyByteBuf(new AdaptiveByteBufAllocator()), true)).getBodyPartBytes().length);
  }

  /**
   * Test {@link LazyResponseBodyPart#getBodyPartBytes()}.
   * <ul>
   *   <li>Given {@link ReadOnlyByteBuf#ReadOnlyByteBuf(ByteBuf)} with buffer is
   * compositeBuffer three.</li>
   * </ul>
   * <p>
   * Method under test: {@link LazyResponseBodyPart#getBodyPartBytes()}
   */
  @Test
  @DisplayName("Test getBodyPartBytes(); given ReadOnlyByteBuf(ByteBuf) with buffer is compositeBuffer three")
  void testGetBodyPartBytes_givenReadOnlyByteBufWithBufferIsCompositeBufferThree() {
    // Arrange, Act and Assert
    assertEquals(0,
        (new LazyResponseBodyPart(new DuplicatedByteBuf(new ReadOnlyByteBuf(Unpooled.compositeBuffer(3))), true))
            .getBodyPartBytes().length);
  }

  /**
   * Test {@link LazyResponseBodyPart#getBodyPartBytes()}.
   * <ul>
   *   <li>Then calls {@link ByteBuf#capacity()}.</li>
   * </ul>
   * <p>
   * Method under test: {@link LazyResponseBodyPart#getBodyPartBytes()}
   */
  @Test
  @DisplayName("Test getBodyPartBytes(); then calls capacity()")
  void testGetBodyPartBytes_thenCallsCapacity() {
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
   * Test {@link LazyResponseBodyPart#getBodyByteBuffer()}.
   * <ul>
   *   <li>Given {@link DuplicatedByteBuf#DuplicatedByteBuf(ByteBuf)} with buffer is
   * compositeBuffer three.</li>
   * </ul>
   * <p>
   * Method under test: {@link LazyResponseBodyPart#getBodyByteBuffer()}
   */
  @Test
  @DisplayName("Test getBodyByteBuffer(); given DuplicatedByteBuf(ByteBuf) with buffer is compositeBuffer three")
  void testGetBodyByteBuffer_givenDuplicatedByteBufWithBufferIsCompositeBufferThree() {
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
   * Test {@link LazyResponseBodyPart#getBodyByteBuffer()}.
   * <ul>
   *   <li>Given {@link EmptyByteBuf#EmptyByteBuf(ByteBufAllocator)} with alloc is
   * {@link AdaptiveByteBufAllocator#AdaptiveByteBufAllocator()}.</li>
   * </ul>
   * <p>
   * Method under test: {@link LazyResponseBodyPart#getBodyByteBuffer()}
   */
  @Test
  @DisplayName("Test getBodyByteBuffer(); given EmptyByteBuf(ByteBufAllocator) with alloc is AdaptiveByteBufAllocator()")
  void testGetBodyByteBuffer_givenEmptyByteBufWithAllocIsAdaptiveByteBufAllocator() {
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
   * Test {@link LazyResponseBodyPart#getBodyByteBuffer()}.
   * <ul>
   *   <li>Then return wrap {@code AXAXAXAX} Bytes is {@code UTF-8}.</li>
   * </ul>
   * <p>
   * Method under test: {@link LazyResponseBodyPart#getBodyByteBuffer()}
   */
  @Test
  @DisplayName("Test getBodyByteBuffer(); then return wrap 'AXAXAXAX' Bytes is 'UTF-8'")
  void testGetBodyByteBuffer_thenReturnWrapAxaxaxaxBytesIsUtf8() throws UnsupportedEncodingException {
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
}
