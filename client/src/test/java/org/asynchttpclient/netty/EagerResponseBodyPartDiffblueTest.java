package org.asynchttpclient.netty;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
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
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import io.netty.buffer.AdaptiveByteBufAllocator;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.CompositeByteBuf;
import io.netty.buffer.DuplicatedByteBuf;
import io.netty.buffer.EmptyByteBuf;
import io.netty.buffer.ReadOnlyByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.buffer.UnpooledDirectByteBuf;
import java.nio.ByteBuffer;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

class EagerResponseBodyPartDiffblueTest {
  /**
   * Test {@link EagerResponseBodyPart#EagerResponseBodyPart(ByteBuf, boolean)}.
   *
   * <p>Method under test: {@link EagerResponseBodyPart#EagerResponseBodyPart(ByteBuf, boolean)}
   */
  @Test
  @DisplayName("Test new EagerResponseBodyPart(ByteBuf, boolean)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void EagerResponseBodyPart.<init>(ByteBuf, boolean)"})
  void testNewEagerResponseBodyPart() {
    // Arrange
    UnpooledDirectByteBuf buf = new UnpooledDirectByteBuf(new AdaptiveByteBufAllocator(), 1, 3);

    // Act
    EagerResponseBodyPart actualEagerResponseBodyPart = new EagerResponseBodyPart(buf, true);

    // Assert
    ByteBuf bodyByteBuf = actualEagerResponseBodyPart.getBodyByteBuf();
    assertTrue(bodyByteBuf instanceof EmptyByteBuf);
    assertEquals(0, actualEagerResponseBodyPart.length());
    assertTrue(actualEagerResponseBodyPart.isLast());
    assertEquals(buf, bodyByteBuf);
    assertArrayEquals(new byte[] {}, actualEagerResponseBodyPart.getBodyPartBytes());
  }

  /**
   * Test {@link EagerResponseBodyPart#EagerResponseBodyPart(ByteBuf, boolean)}.
   *
   * <ul>
   *   <li>Given three.
   *   <li>Then calls {@link ByteBuf#capacity()}.
   * </ul>
   *
   * <p>Method under test: {@link EagerResponseBodyPart#EagerResponseBodyPart(ByteBuf, boolean)}
   */
  @Test
  @DisplayName(
      "Test new EagerResponseBodyPart(ByteBuf, boolean); given three; then calls capacity()")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void EagerResponseBodyPart.<init>(ByteBuf, boolean)"})
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
    DuplicatedByteBuf buffer3 = new DuplicatedByteBuf(buffer);
    ReadOnlyByteBuf buf = new ReadOnlyByteBuf(buffer3);

    // Act
    EagerResponseBodyPart actualEagerResponseBodyPart = new EagerResponseBodyPart(buf, true);

    // Assert
    verify(buffer, atLeast(1)).capacity();
    verify(buffer).getBytes(eq(1), isA(byte[].class), eq(0), eq(0));
    verify(buffer).maxCapacity();
    verify(buffer).readerIndex();
    verify(buffer).writerIndex();
    ByteBuf bodyByteBuf = actualEagerResponseBodyPart.getBodyByteBuf();
    assertTrue(bodyByteBuf instanceof EmptyByteBuf);
    assertEquals(0, actualEagerResponseBodyPart.length());
    assertTrue(actualEagerResponseBodyPart.isLast());
    assertEquals(buffer2, bodyByteBuf);
    assertArrayEquals(new byte[] {}, actualEagerResponseBodyPart.getBodyPartBytes());
  }

  /**
   * Test {@link EagerResponseBodyPart#EagerResponseBodyPart(ByteBuf, boolean)}.
   *
   * <ul>
   *   <li>Then return BodyByteBuf is compositeBuffer three.
   * </ul>
   *
   * <p>Method under test: {@link EagerResponseBodyPart#EagerResponseBodyPart(ByteBuf, boolean)}
   */
  @Test
  @DisplayName(
      "Test new EagerResponseBodyPart(ByteBuf, boolean); then return BodyByteBuf is compositeBuffer three")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void EagerResponseBodyPart.<init>(ByteBuf, boolean)"})
  void testNewEagerResponseBodyPart_thenReturnBodyByteBufIsCompositeBufferThree() {
    // Arrange
    CompositeByteBuf buffer = Unpooled.compositeBuffer(3);
    ReadOnlyByteBuf buf = new ReadOnlyByteBuf(buffer);

    // Act
    EagerResponseBodyPart actualEagerResponseBodyPart = new EagerResponseBodyPart(buf, true);

    // Assert
    ByteBuf bodyByteBuf = actualEagerResponseBodyPart.getBodyByteBuf();
    assertTrue(bodyByteBuf instanceof EmptyByteBuf);
    assertEquals(0, actualEagerResponseBodyPart.length());
    assertTrue(actualEagerResponseBodyPart.isLast());
    assertEquals(buffer, bodyByteBuf);
    assertArrayEquals(new byte[] {}, actualEagerResponseBodyPart.getBodyPartBytes());
  }

  /**
   * Test {@link EagerResponseBodyPart#EagerResponseBodyPart(ByteBuf, boolean)}.
   *
   * <ul>
   *   <li>When {@link EmptyByteBuf#EmptyByteBuf(ByteBufAllocator)} with alloc is {@link
   *       AdaptiveByteBufAllocator#AdaptiveByteBufAllocator()}.
   * </ul>
   *
   * <p>Method under test: {@link EagerResponseBodyPart#EagerResponseBodyPart(ByteBuf, boolean)}
   */
  @Test
  @DisplayName(
      "Test new EagerResponseBodyPart(ByteBuf, boolean); when EmptyByteBuf(ByteBufAllocator) with alloc is AdaptiveByteBufAllocator()")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void EagerResponseBodyPart.<init>(ByteBuf, boolean)"})
  void testNewEagerResponseBodyPart_whenEmptyByteBufWithAllocIsAdaptiveByteBufAllocator() {
    // Arrange
    EmptyByteBuf buffer = new EmptyByteBuf(new AdaptiveByteBufAllocator());
    DuplicatedByteBuf buf = new DuplicatedByteBuf(buffer);

    // Act
    EagerResponseBodyPart actualEagerResponseBodyPart = new EagerResponseBodyPart(buf, true);

    // Assert
    ByteBuf bodyByteBuf = actualEagerResponseBodyPart.getBodyByteBuf();
    assertTrue(bodyByteBuf instanceof EmptyByteBuf);
    assertEquals(0, actualEagerResponseBodyPart.length());
    assertTrue(actualEagerResponseBodyPart.isLast());
    assertEquals(buffer, bodyByteBuf);
    assertArrayEquals(new byte[] {}, actualEagerResponseBodyPart.getBodyPartBytes());
  }

  /**
   * Test {@link EagerResponseBodyPart#EagerResponseBodyPart(ByteBuf, boolean)}.
   *
   * <ul>
   *   <li>When {@link EmptyByteBuf#EmptyByteBuf(ByteBufAllocator)} with alloc is {@link
   *       AdaptiveByteBufAllocator#AdaptiveByteBufAllocator()}.
   * </ul>
   *
   * <p>Method under test: {@link EagerResponseBodyPart#EagerResponseBodyPart(ByteBuf, boolean)}
   */
  @Test
  @DisplayName(
      "Test new EagerResponseBodyPart(ByteBuf, boolean); when EmptyByteBuf(ByteBufAllocator) with alloc is AdaptiveByteBufAllocator()")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void EagerResponseBodyPart.<init>(ByteBuf, boolean)"})
  void testNewEagerResponseBodyPart_whenEmptyByteBufWithAllocIsAdaptiveByteBufAllocator2() {
    // Arrange
    EmptyByteBuf buffer = new EmptyByteBuf(new AdaptiveByteBufAllocator());
    DuplicatedByteBuf buffer2 = new DuplicatedByteBuf(buffer);
    ReadOnlyByteBuf buf = new ReadOnlyByteBuf(buffer2);

    // Act
    EagerResponseBodyPart actualEagerResponseBodyPart = new EagerResponseBodyPart(buf, true);

    // Assert
    ByteBuf bodyByteBuf = actualEagerResponseBodyPart.getBodyByteBuf();
    assertTrue(bodyByteBuf instanceof EmptyByteBuf);
    assertEquals(0, actualEagerResponseBodyPart.length());
    assertTrue(actualEagerResponseBodyPart.isLast());
    assertEquals(buffer, bodyByteBuf);
    assertArrayEquals(new byte[] {}, actualEagerResponseBodyPart.getBodyPartBytes());
  }

  /**
   * Test {@link EagerResponseBodyPart#getBodyPartBytes()}.
   *
   * <p>Method under test: {@link EagerResponseBodyPart#getBodyPartBytes()}
   */
  @Test
  @DisplayName("Test getBodyPartBytes()")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"byte[] EagerResponseBodyPart.getBodyPartBytes()"})
  void testGetBodyPartBytes() {
    // Arrange
    DuplicatedByteBuf buf = new DuplicatedByteBuf(new EmptyByteBuf(new AdaptiveByteBufAllocator()));

    // Act and Assert
    assertArrayEquals(new byte[] {}, new EagerResponseBodyPart(buf, true).getBodyPartBytes());
  }

  /**
   * Test {@link EagerResponseBodyPart#length()}.
   *
   * <p>Method under test: {@link EagerResponseBodyPart#length()}
   */
  @Test
  @DisplayName("Test length()")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"int EagerResponseBodyPart.length()"})
  void testLength() {
    // Arrange
    DuplicatedByteBuf buf = new DuplicatedByteBuf(new EmptyByteBuf(new AdaptiveByteBufAllocator()));

    // Act and Assert
    assertEquals(0, new EagerResponseBodyPart(buf, true).length());
  }

  /**
   * Test {@link EagerResponseBodyPart#getBodyByteBuffer()}.
   *
   * <p>Method under test: {@link EagerResponseBodyPart#getBodyByteBuffer()}
   */
  @Test
  @DisplayName("Test getBodyByteBuffer()")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"ByteBuffer EagerResponseBodyPart.getBodyByteBuffer()"})
  void testGetBodyByteBuffer() {
    // Arrange
    DuplicatedByteBuf buf = new DuplicatedByteBuf(new EmptyByteBuf(new AdaptiveByteBufAllocator()));

    // Act
    ByteBuffer actualBodyByteBuffer = new EagerResponseBodyPart(buf, true).getBodyByteBuffer();

    // Assert
    assertEquals(0, actualBodyByteBuffer.capacity());
    assertEquals(0, actualBodyByteBuffer.limit());
    assertEquals(0, actualBodyByteBuffer.position());
    assertFalse(actualBodyByteBuffer.hasRemaining());
    assertTrue(actualBodyByteBuffer.hasArray());
    assertArrayEquals(new byte[] {}, actualBodyByteBuffer.array());
  }

  /**
   * Test {@link EagerResponseBodyPart#getBodyByteBuf()}.
   *
   * <p>Method under test: {@link EagerResponseBodyPart#getBodyByteBuf()}
   */
  @Test
  @DisplayName("Test getBodyByteBuf()")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"ByteBuf EagerResponseBodyPart.getBodyByteBuf()"})
  void testGetBodyByteBuf() {
    // Arrange
    EmptyByteBuf buffer = new EmptyByteBuf(new AdaptiveByteBufAllocator());
    DuplicatedByteBuf buf = new DuplicatedByteBuf(buffer);

    // Act
    ByteBuf actualBodyByteBuf = new EagerResponseBodyPart(buf, true).getBodyByteBuf();

    // Assert
    assertTrue(actualBodyByteBuf instanceof EmptyByteBuf);
    assertEquals(buffer, actualBodyByteBuf);
  }
}
