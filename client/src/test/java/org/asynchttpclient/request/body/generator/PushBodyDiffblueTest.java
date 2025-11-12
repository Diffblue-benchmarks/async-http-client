package org.asynchttpclient.request.body.generator;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import io.netty.buffer.AdaptiveByteBufAllocator;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.DuplicatedByteBuf;
import io.netty.buffer.EmptyByteBuf;
import io.netty.buffer.UnpooledDirectByteBuf;
import java.util.LinkedList;
import java.util.Queue;
import org.asynchttpclient.request.body.Body;
import org.asynchttpclient.request.body.Body.BodyState;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

class PushBodyDiffblueTest {
  /**
   * Test {@link PushBody#PushBody(Queue)}.
   *
   * <ul>
   *   <li>Given {@link EmptyByteBuf#EmptyByteBuf(ByteBufAllocator)} with alloc is {@link
   *       AdaptiveByteBufAllocator#AdaptiveByteBufAllocator()}.
   * </ul>
   *
   * <p>Method under test: {@link PushBody#PushBody(Queue)}
   */
  @Test
  @DisplayName(
      "Test new PushBody(Queue); given EmptyByteBuf(ByteBufAllocator) with alloc is AdaptiveByteBufAllocator()")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void PushBody.<init>(Queue)"})
  void testNewPushBody_givenEmptyByteBufWithAllocIsAdaptiveByteBufAllocator() {
    // Arrange
    LinkedList<BodyChunk> queue = new LinkedList<>();
    DuplicatedByteBuf buffer =
        new DuplicatedByteBuf(new EmptyByteBuf(new AdaptiveByteBufAllocator()));
    queue.add(new BodyChunk(buffer, true));

    // Act and Assert
    assertEquals(-1L, new PushBody(queue).getContentLength());
  }

  /**
   * Test {@link PushBody#PushBody(Queue)}.
   *
   * <ul>
   *   <li>Given {@link EmptyByteBuf#EmptyByteBuf(ByteBufAllocator)} with alloc is {@link
   *       AdaptiveByteBufAllocator#AdaptiveByteBufAllocator()}.
   * </ul>
   *
   * <p>Method under test: {@link PushBody#PushBody(Queue)}
   */
  @Test
  @DisplayName(
      "Test new PushBody(Queue); given EmptyByteBuf(ByteBufAllocator) with alloc is AdaptiveByteBufAllocator()")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void PushBody.<init>(Queue)"})
  void testNewPushBody_givenEmptyByteBufWithAllocIsAdaptiveByteBufAllocator2() {
    // Arrange
    LinkedList<BodyChunk> queue = new LinkedList<>();
    DuplicatedByteBuf buffer =
        new DuplicatedByteBuf(new EmptyByteBuf(new AdaptiveByteBufAllocator()));
    queue.add(new BodyChunk(buffer, true));
    DuplicatedByteBuf buffer2 =
        new DuplicatedByteBuf(new EmptyByteBuf(new AdaptiveByteBufAllocator()));
    queue.add(new BodyChunk(buffer2, true));

    // Act and Assert
    assertEquals(-1L, new PushBody(queue).getContentLength());
  }

  /**
   * Test {@link PushBody#PushBody(Queue)}.
   *
   * <ul>
   *   <li>When {@link LinkedList#LinkedList()}.
   * </ul>
   *
   * <p>Method under test: {@link PushBody#PushBody(Queue)}
   */
  @Test
  @DisplayName("Test new PushBody(Queue); when LinkedList()")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void PushBody.<init>(Queue)"})
  void testNewPushBody_whenLinkedList() {
    // Arrange, Act and Assert
    assertEquals(-1L, new PushBody(new LinkedList<>()).getContentLength());
  }

  /**
   * Test {@link PushBody#transferTo(ByteBuf)}.
   *
   * <p>Method under test: {@link PushBody#transferTo(ByteBuf)}
   */
  @Test
  @DisplayName("Test transferTo(ByteBuf)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"BodyState PushBody.transferTo(ByteBuf)"})
  void testTransferTo() {
    // Arrange
    PushBody pushBody = new PushBody(new LinkedList<>());
    UnpooledDirectByteBuf target = new UnpooledDirectByteBuf(new AdaptiveByteBufAllocator(), 1, 3);

    // Act
    BodyState actualTransferToResult = pushBody.transferTo(target);

    // Assert
    assertEquals(0, target.readerIndex());
    assertEquals(0, target.writerIndex());
    assertEquals(BodyState.SUSPEND, actualTransferToResult);
    assertTrue(target.isWritable());
  }

  /**
   * Test {@link PushBody#transferTo(ByteBuf)}.
   *
   * <p>Method under test: {@link PushBody#transferTo(ByteBuf)}
   */
  @Test
  @DisplayName("Test transferTo(ByteBuf)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"BodyState PushBody.transferTo(ByteBuf)"})
  void testTransferTo2() {
    // Arrange
    LinkedList<BodyChunk> queue = new LinkedList<>();
    DuplicatedByteBuf buffer =
        new DuplicatedByteBuf(new EmptyByteBuf(new AdaptiveByteBufAllocator()));
    queue.add(new BodyChunk(buffer, true));
    PushBody pushBody = new PushBody(queue);
    UnpooledDirectByteBuf target = new UnpooledDirectByteBuf(new AdaptiveByteBufAllocator(), 1, 3);

    // Act
    BodyState actualTransferToResult = pushBody.transferTo(target);

    // Assert
    assertEquals(0, target.readerIndex());
    assertEquals(0, target.writerIndex());
    assertEquals(BodyState.CONTINUE, actualTransferToResult);
    assertTrue(target.isWritable());
  }

  /**
   * Test {@link PushBody#transferTo(ByteBuf)}.
   *
   * <p>Method under test: {@link PushBody#transferTo(ByteBuf)}
   */
  @Test
  @DisplayName("Test transferTo(ByteBuf)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"BodyState PushBody.transferTo(ByteBuf)"})
  void testTransferTo3() {
    // Arrange
    LinkedList<BodyChunk> queue = new LinkedList<>();
    DuplicatedByteBuf buffer =
        new DuplicatedByteBuf(new EmptyByteBuf(new AdaptiveByteBufAllocator()));
    queue.add(new BodyChunk(buffer, false));
    PushBody pushBody = new PushBody(queue);
    UnpooledDirectByteBuf target = new UnpooledDirectByteBuf(new AdaptiveByteBufAllocator(), 1, 3);

    // Act
    BodyState actualTransferToResult = pushBody.transferTo(target);

    // Assert
    assertEquals(0, target.readerIndex());
    assertEquals(0, target.writerIndex());
    assertEquals(BodyState.SUSPEND, actualTransferToResult);
    assertTrue(target.isWritable());
  }

  /**
   * Test {@link PushBody#transferTo(ByteBuf)}.
   *
   * <ul>
   *   <li>Then {@link DuplicatedByteBuf#DuplicatedByteBuf(ByteBuf)} with buffer is {@link
   *       EmptyByteBuf#EmptyByteBuf(ByteBufAllocator)} readerIndex is zero.
   * </ul>
   *
   * <p>Method under test: {@link PushBody#transferTo(ByteBuf)}
   */
  @Test
  @DisplayName(
      "Test transferTo(ByteBuf); then DuplicatedByteBuf(ByteBuf) with buffer is EmptyByteBuf(ByteBufAllocator) readerIndex is zero")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"BodyState PushBody.transferTo(ByteBuf)"})
  void testTransferTo_thenDuplicatedByteBufWithBufferIsEmptyByteBufReaderIndexIsZero() {
    // Arrange
    PushBody pushBody = new PushBody(new LinkedList<>());
    DuplicatedByteBuf target =
        new DuplicatedByteBuf(new EmptyByteBuf(new AdaptiveByteBufAllocator()));

    // Act
    BodyState actualTransferToResult = pushBody.transferTo(target);

    // Assert
    assertEquals(0, target.readerIndex());
    assertEquals(0, target.writerIndex());
    assertEquals(BodyState.SUSPEND, actualTransferToResult);
    assertFalse(target.isWritable());
  }

  /**
   * Test getters and setters.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link PushBody#close()}
   *   <li>{@link PushBody#getContentLength()}
   * </ul>
   */
  @Test
  @DisplayName("Test getters and setters")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void PushBody.close()", "long PushBody.getContentLength()"})
  void testGettersAndSetters() {
    // Arrange
    PushBody pushBody = new PushBody(new LinkedList<>());

    // Act
    pushBody.close();

    // Assert
    assertEquals(-1L, pushBody.getContentLength());
  }
}
