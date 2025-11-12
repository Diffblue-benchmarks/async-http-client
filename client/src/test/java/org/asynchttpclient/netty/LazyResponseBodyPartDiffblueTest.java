package org.asynchttpclient.netty;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
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
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import io.netty.buffer.AdaptiveByteBufAllocator;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.CompositeByteBuf;
import io.netty.buffer.DuplicatedByteBuf;
import io.netty.buffer.EmptyByteBuf;
import io.netty.buffer.ReadOnlyByteBuf;
import io.netty.buffer.SwappedByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.buffer.UnpooledHeapByteBuf;
import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

class LazyResponseBodyPartDiffblueTest {
  /**
   * Test getters and setters.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link LazyResponseBodyPart#LazyResponseBodyPart(ByteBuf, boolean)}
   *   <li>{@link LazyResponseBodyPart#getBodyByteBuf()}
   * </ul>
   */
  @Test
  @DisplayName("Test getters and setters")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void LazyResponseBodyPart.<init>(ByteBuf, boolean)",
    "ByteBuf LazyResponseBodyPart.getBodyByteBuf()"
  })
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
   *
   * <ul>
   *   <li>Given {@link DuplicatedByteBuf#DuplicatedByteBuf(ByteBuf)} with buffer is {@link
   *       EmptyByteBuf#EmptyByteBuf(ByteBufAllocator)}.
   *   <li>Then return zero.
   * </ul>
   *
   * <p>Method under test: {@link LazyResponseBodyPart#length()}
   */
  @Test
  @DisplayName(
      "Test length(); given DuplicatedByteBuf(ByteBuf) with buffer is EmptyByteBuf(ByteBufAllocator); then return zero")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"int LazyResponseBodyPart.length()"})
  void testLength_givenDuplicatedByteBufWithBufferIsEmptyByteBuf_thenReturnZero() {
    // Arrange
    DuplicatedByteBuf buf = new DuplicatedByteBuf(new EmptyByteBuf(new AdaptiveByteBufAllocator()));

    // Act and Assert
    assertEquals(0, new LazyResponseBodyPart(buf, true).length());
  }

  /**
   * Test {@link LazyResponseBodyPart#length()}.
   *
   * <ul>
   *   <li>Given {@link LazyResponseBodyPart#LazyResponseBodyPart(ByteBuf, boolean)} with buf is
   *       {@link EmptyByteBuf#EmptyByteBuf(ByteBufAllocator)} and last is {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link LazyResponseBodyPart#length()}
   */
  @Test
  @DisplayName(
      "Test length(); given LazyResponseBodyPart(ByteBuf, boolean) with buf is EmptyByteBuf(ByteBufAllocator) and last is 'true'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"int LazyResponseBodyPart.length()"})
  void testLength_givenLazyResponseBodyPartWithBufIsEmptyByteBufAndLastIsTrue() {
    // Arrange, Act and Assert
    assertEquals(
        0,
        new LazyResponseBodyPart(new EmptyByteBuf(new AdaptiveByteBufAllocator()), true).length());
  }

  /**
   * Test {@link LazyResponseBodyPart#getBodyPartBytes()}.
   *
   * <p>Method under test: {@link LazyResponseBodyPart#getBodyPartBytes()}
   */
  @Test
  @DisplayName("Test getBodyPartBytes()")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"byte[] LazyResponseBodyPart.getBodyPartBytes()"})
  void testGetBodyPartBytes() {
    // Arrange
    CompositeByteBuf buf = Unpooled.compositeBuffer(3);

    // Act and Assert
    assertArrayEquals(new byte[] {}, new LazyResponseBodyPart(buf, true).getBodyPartBytes());
  }

  /**
   * Test {@link LazyResponseBodyPart#getBodyPartBytes()}.
   *
   * <p>Method under test: {@link LazyResponseBodyPart#getBodyPartBytes()}
   */
  @Test
  @DisplayName("Test getBodyPartBytes()")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"byte[] LazyResponseBodyPart.getBodyPartBytes()"})
  void testGetBodyPartBytes2() {
    // Arrange, Act and Assert
    assertArrayEquals(
        new byte[] {},
        new LazyResponseBodyPart(
                new UnpooledHeapByteBuf(new AdaptiveByteBufAllocator(), 1, 3), true)
            .getBodyPartBytes());
  }

  /**
   * Test {@link LazyResponseBodyPart#getBodyPartBytes()}.
   *
   * <ul>
   *   <li>Given {@link ByteBuf} {@link ByteBuf#capacity()} return three.
   *   <li>Then calls {@link ByteBuf#capacity()}.
   * </ul>
   *
   * <p>Method under test: {@link LazyResponseBodyPart#getBodyPartBytes()}
   */
  @Test
  @DisplayName(
      "Test getBodyPartBytes(); given ByteBuf capacity() return three; then calls capacity()")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"byte[] LazyResponseBodyPart.getBodyPartBytes()"})
  void testGetBodyPartBytes_givenByteBufCapacityReturnThree_thenCallsCapacity() {
    // Arrange
    ByteBuf buffer = mock(ByteBuf.class);
    when(buffer.capacity()).thenReturn(3);
    when(buffer.maxCapacity()).thenReturn(3);
    when(buffer.readerIndex()).thenReturn(1);
    when(buffer.writerIndex()).thenReturn(1);
    when(buffer.getBytes(anyInt(), Mockito.<byte[]>any(), anyInt(), anyInt()))
        .thenReturn(new DuplicatedByteBuf(new EmptyByteBuf(new AdaptiveByteBufAllocator())));
    DuplicatedByteBuf buffer2 = new DuplicatedByteBuf(buffer);
    ReadOnlyByteBuf buf = new ReadOnlyByteBuf(buffer2);

    // Act
    byte[] actualBodyPartBytes = new LazyResponseBodyPart(buf, true).getBodyPartBytes();

    // Assert
    verify(buffer, atLeast(1)).capacity();
    verify(buffer).getBytes(eq(1), isA(byte[].class), eq(0), eq(0));
    verify(buffer).maxCapacity();
    verify(buffer).readerIndex();
    verify(buffer).writerIndex();
    assertArrayEquals(new byte[] {}, actualBodyPartBytes);
  }

  /**
   * Test {@link LazyResponseBodyPart#getBodyPartBytes()}.
   *
   * <ul>
   *   <li>Given {@link DuplicatedByteBuf#DuplicatedByteBuf(ByteBuf)} with buffer is {@link
   *       EmptyByteBuf#EmptyByteBuf(ByteBufAllocator)}.
   * </ul>
   *
   * <p>Method under test: {@link LazyResponseBodyPart#getBodyPartBytes()}
   */
  @Test
  @DisplayName(
      "Test getBodyPartBytes(); given DuplicatedByteBuf(ByteBuf) with buffer is EmptyByteBuf(ByteBufAllocator)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"byte[] LazyResponseBodyPart.getBodyPartBytes()"})
  void testGetBodyPartBytes_givenDuplicatedByteBufWithBufferIsEmptyByteBuf() {
    // Arrange
    DuplicatedByteBuf buffer =
        new DuplicatedByteBuf(new EmptyByteBuf(new AdaptiveByteBufAllocator()));
    ReadOnlyByteBuf buf = new ReadOnlyByteBuf(buffer);

    // Act and Assert
    assertArrayEquals(new byte[] {}, new LazyResponseBodyPart(buf, true).getBodyPartBytes());
  }

  /**
   * Test {@link LazyResponseBodyPart#getBodyPartBytes()}.
   *
   * <ul>
   *   <li>Given {@link LazyResponseBodyPart#LazyResponseBodyPart(ByteBuf, boolean)} with buf is
   *       {@link EmptyByteBuf#EmptyByteBuf(ByteBufAllocator)} and last is {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link LazyResponseBodyPart#getBodyPartBytes()}
   */
  @Test
  @DisplayName(
      "Test getBodyPartBytes(); given LazyResponseBodyPart(ByteBuf, boolean) with buf is EmptyByteBuf(ByteBufAllocator) and last is 'true'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"byte[] LazyResponseBodyPart.getBodyPartBytes()"})
  void testGetBodyPartBytes_givenLazyResponseBodyPartWithBufIsEmptyByteBufAndLastIsTrue() {
    // Arrange, Act and Assert
    assertArrayEquals(
        new byte[] {},
        new LazyResponseBodyPart(new EmptyByteBuf(new AdaptiveByteBufAllocator()), true)
            .getBodyPartBytes());
  }

  /**
   * Test {@link LazyResponseBodyPart#getBodyPartBytes()}.
   *
   * <ul>
   *   <li>Given {@link ReadOnlyByteBuf#ReadOnlyByteBuf(ByteBuf)} with buffer is compositeBuffer
   *       three.
   * </ul>
   *
   * <p>Method under test: {@link LazyResponseBodyPart#getBodyPartBytes()}
   */
  @Test
  @DisplayName(
      "Test getBodyPartBytes(); given ReadOnlyByteBuf(ByteBuf) with buffer is compositeBuffer three")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"byte[] LazyResponseBodyPart.getBodyPartBytes()"})
  void testGetBodyPartBytes_givenReadOnlyByteBufWithBufferIsCompositeBufferThree() {
    // Arrange
    CompositeByteBuf buffer = Unpooled.compositeBuffer(3);
    ReadOnlyByteBuf buf = new ReadOnlyByteBuf(buffer);

    // Act and Assert
    assertArrayEquals(new byte[] {}, new LazyResponseBodyPart(buf, true).getBodyPartBytes());
  }

  /**
   * Test {@link LazyResponseBodyPart#getBodyPartBytes()}.
   *
   * <ul>
   *   <li>Given {@link SwappedByteBuf#SwappedByteBuf(ByteBuf)} with buf is compositeBuffer three.
   * </ul>
   *
   * <p>Method under test: {@link LazyResponseBodyPart#getBodyPartBytes()}
   */
  @Test
  @DisplayName(
      "Test getBodyPartBytes(); given SwappedByteBuf(ByteBuf) with buf is compositeBuffer three")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"byte[] LazyResponseBodyPart.getBodyPartBytes()"})
  void testGetBodyPartBytes_givenSwappedByteBufWithBufIsCompositeBufferThree() {
    // Arrange
    CompositeByteBuf buf = Unpooled.compositeBuffer(3);
    SwappedByteBuf buf2 = new SwappedByteBuf(buf);

    // Act and Assert
    assertArrayEquals(new byte[] {}, new LazyResponseBodyPart(buf2, true).getBodyPartBytes());
  }

  /**
   * Test {@link LazyResponseBodyPart#getBodyPartBytes()}.
   *
   * <ul>
   *   <li>Given {@link SwappedByteBuf#SwappedByteBuf(ByteBuf)} with buf is {@link
   *       EmptyByteBuf#EmptyByteBuf(ByteBufAllocator)}.
   * </ul>
   *
   * <p>Method under test: {@link LazyResponseBodyPart#getBodyPartBytes()}
   */
  @Test
  @DisplayName(
      "Test getBodyPartBytes(); given SwappedByteBuf(ByteBuf) with buf is EmptyByteBuf(ByteBufAllocator)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"byte[] LazyResponseBodyPart.getBodyPartBytes()"})
  void testGetBodyPartBytes_givenSwappedByteBufWithBufIsEmptyByteBuf() {
    // Arrange
    SwappedByteBuf buf = new SwappedByteBuf(new EmptyByteBuf(new AdaptiveByteBufAllocator()));

    // Act and Assert
    assertArrayEquals(new byte[] {}, new LazyResponseBodyPart(buf, true).getBodyPartBytes());
  }

  /**
   * Test {@link LazyResponseBodyPart#getBodyByteBuffer()}.
   *
   * <p>Method under test: {@link LazyResponseBodyPart#getBodyByteBuffer()}
   */
  @Test
  @DisplayName("Test getBodyByteBuffer()")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"ByteBuffer LazyResponseBodyPart.getBodyByteBuffer()"})
  void testGetBodyByteBuffer() {
    // Arrange
    CompositeByteBuf buf = Unpooled.compositeBuffer(3);

    // Act
    ByteBuffer actualBodyByteBuffer = new LazyResponseBodyPart(buf, true).getBodyByteBuffer();

    // Assert
    assertEquals(0, actualBodyByteBuffer.capacity());
    assertEquals(0, actualBodyByteBuffer.limit());
    assertEquals(0, actualBodyByteBuffer.position());
    assertFalse(actualBodyByteBuffer.hasRemaining());
    assertFalse(actualBodyByteBuffer.hasArray());
    ByteBuffer actualFlipResult = actualBodyByteBuffer.flip();
    assertSame(actualBodyByteBuffer, actualFlipResult);
  }

  /**
   * Test {@link LazyResponseBodyPart#getBodyByteBuffer()}.
   *
   * <p>Method under test: {@link LazyResponseBodyPart#getBodyByteBuffer()}
   */
  @Test
  @DisplayName("Test getBodyByteBuffer()")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"ByteBuffer LazyResponseBodyPart.getBodyByteBuffer()"})
  void testGetBodyByteBuffer2() {
    // Arrange and Act
    ByteBuffer actualBodyByteBuffer =
        new LazyResponseBodyPart(new EmptyByteBuf(new AdaptiveByteBufAllocator()), true)
            .getBodyByteBuffer();

    // Assert
    assertEquals(0, actualBodyByteBuffer.capacity());
    assertEquals(0, actualBodyByteBuffer.limit());
    assertEquals(0, actualBodyByteBuffer.position());
    assertFalse(actualBodyByteBuffer.hasRemaining());
    assertFalse(actualBodyByteBuffer.hasArray());
    ByteBuffer actualFlipResult = actualBodyByteBuffer.flip();
    assertSame(actualBodyByteBuffer, actualFlipResult);
  }

  /**
   * Test {@link LazyResponseBodyPart#getBodyByteBuffer()}.
   *
   * <p>Method under test: {@link LazyResponseBodyPart#getBodyByteBuffer()}
   */
  @Test
  @DisplayName("Test getBodyByteBuffer()")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"ByteBuffer LazyResponseBodyPart.getBodyByteBuffer()"})
  void testGetBodyByteBuffer3() {
    // Arrange and Act
    ByteBuffer actualBodyByteBuffer =
        new LazyResponseBodyPart(
                new ReadOnlyByteBuf(new UnpooledHeapByteBuf(new AdaptiveByteBufAllocator(), 1, 3)),
                true)
            .getBodyByteBuffer();

    // Assert
    assertEquals(0, actualBodyByteBuffer.capacity());
    assertEquals(0, actualBodyByteBuffer.limit());
    assertEquals(0, actualBodyByteBuffer.position());
    assertFalse(actualBodyByteBuffer.hasRemaining());
    assertFalse(actualBodyByteBuffer.hasArray());
    ByteBuffer actualFlipResult = actualBodyByteBuffer.flip();
    assertSame(actualBodyByteBuffer, actualFlipResult);
  }

  /**
   * Test {@link LazyResponseBodyPart#getBodyByteBuffer()}.
   *
   * <ul>
   *   <li>Given {@link DuplicatedByteBuf#DuplicatedByteBuf(ByteBuf)} with buffer is {@link
   *       EmptyByteBuf#EmptyByteBuf(ByteBufAllocator)}.
   * </ul>
   *
   * <p>Method under test: {@link LazyResponseBodyPart#getBodyByteBuffer()}
   */
  @Test
  @DisplayName(
      "Test getBodyByteBuffer(); given DuplicatedByteBuf(ByteBuf) with buffer is EmptyByteBuf(ByteBufAllocator)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"ByteBuffer LazyResponseBodyPart.getBodyByteBuffer()"})
  void testGetBodyByteBuffer_givenDuplicatedByteBufWithBufferIsEmptyByteBuf() {
    // Arrange
    DuplicatedByteBuf buf = new DuplicatedByteBuf(new EmptyByteBuf(new AdaptiveByteBufAllocator()));

    // Act
    ByteBuffer actualBodyByteBuffer = new LazyResponseBodyPart(buf, true).getBodyByteBuffer();

    // Assert
    assertEquals(0, actualBodyByteBuffer.capacity());
    assertEquals(0, actualBodyByteBuffer.limit());
    assertEquals(0, actualBodyByteBuffer.position());
    assertFalse(actualBodyByteBuffer.hasRemaining());
    assertFalse(actualBodyByteBuffer.hasArray());
    ByteBuffer actualFlipResult = actualBodyByteBuffer.flip();
    assertSame(actualBodyByteBuffer, actualFlipResult);
  }

  /**
   * Test {@link LazyResponseBodyPart#getBodyByteBuffer()}.
   *
   * <ul>
   *   <li>Then return wrap {@code AXAXAXAX} Bytes is {@code UTF-8}.
   * </ul>
   *
   * <p>Method under test: {@link LazyResponseBodyPart#getBodyByteBuffer()}
   */
  @Test
  @DisplayName("Test getBodyByteBuffer(); then return wrap 'AXAXAXAX' Bytes is 'UTF-8'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"ByteBuffer LazyResponseBodyPart.getBodyByteBuffer()"})
  void testGetBodyByteBuffer_thenReturnWrapAxaxaxaxBytesIsUtf8()
      throws UnsupportedEncodingException {
    // Arrange
    ByteBuf buffer = mock(ByteBuf.class);
    when(buffer.capacity()).thenReturn(3);
    when(buffer.maxCapacity()).thenReturn(3);
    when(buffer.readerIndex()).thenReturn(1);
    when(buffer.writerIndex()).thenReturn(1);
    ByteBuffer wrapResult = ByteBuffer.wrap("AXAXAXAX".getBytes("UTF-8"));
    when(buffer.nioBuffer(anyInt(), anyInt())).thenReturn(wrapResult);
    DuplicatedByteBuf buffer2 = new DuplicatedByteBuf(buffer);
    ReadOnlyByteBuf buf = new ReadOnlyByteBuf(buffer2);

    // Act
    ByteBuffer actualBodyByteBuffer = new LazyResponseBodyPart(buf, true).getBodyByteBuffer();

    // Assert
    verify(buffer, atLeast(1)).capacity();
    verify(buffer).maxCapacity();
    verify(buffer).nioBuffer(1, 0);
    verify(buffer).readerIndex();
    verify(buffer).writerIndex();
    assertEquals(wrapResult, actualBodyByteBuffer);
  }
}
