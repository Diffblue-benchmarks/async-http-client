package org.asynchttpclient.request.body.generator;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import io.netty.buffer.AdaptiveByteBufAllocator;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.DuplicatedByteBuf;
import io.netty.buffer.EmptyByteBuf;
import io.netty.buffer.ReadOnlyByteBuf;
import java.util.LinkedList;
import java.util.Queue;
import org.asynchttpclient.request.body.Body;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PushBodyDiffblueTest {
  /**
   * Test {@link PushBody#PushBody(Queue)}.
   * <ul>
   *   <li>Given {@link EmptyByteBuf#EmptyByteBuf(ByteBufAllocator)} with alloc is
   * {@link AdaptiveByteBufAllocator#AdaptiveByteBufAllocator()}.</li>
   * </ul>
   * <p>
   * Method under test: {@link PushBody#PushBody(Queue)}
   */
  @Test
  @DisplayName("Test new PushBody(Queue); given EmptyByteBuf(ByteBufAllocator) with alloc is AdaptiveByteBufAllocator()")
  void testNewPushBody_givenEmptyByteBufWithAllocIsAdaptiveByteBufAllocator() {
    // Arrange
    LinkedList<BodyChunk> queue = new LinkedList<>();
    queue.add(new BodyChunk(new DuplicatedByteBuf(new EmptyByteBuf(new AdaptiveByteBufAllocator())), true));

    // Act and Assert
    assertEquals(-1L, (new PushBody(queue)).getContentLength());
  }

  /**
   * Test {@link PushBody#PushBody(Queue)}.
   * <ul>
   *   <li>Given {@link EmptyByteBuf#EmptyByteBuf(ByteBufAllocator)} with alloc is
   * {@link AdaptiveByteBufAllocator#AdaptiveByteBufAllocator()}.</li>
   * </ul>
   * <p>
   * Method under test: {@link PushBody#PushBody(Queue)}
   */
  @Test
  @DisplayName("Test new PushBody(Queue); given EmptyByteBuf(ByteBufAllocator) with alloc is AdaptiveByteBufAllocator()")
  void testNewPushBody_givenEmptyByteBufWithAllocIsAdaptiveByteBufAllocator2() {
    // Arrange
    LinkedList<BodyChunk> queue = new LinkedList<>();
    queue.add(new BodyChunk(new DuplicatedByteBuf(new EmptyByteBuf(new AdaptiveByteBufAllocator())), true));
    queue.add(new BodyChunk(new DuplicatedByteBuf(new EmptyByteBuf(new AdaptiveByteBufAllocator())), true));

    // Act and Assert
    assertEquals(-1L, (new PushBody(queue)).getContentLength());
  }

  /**
   * Test {@link PushBody#PushBody(Queue)}.
   * <ul>
   *   <li>When {@link LinkedList#LinkedList()}.</li>
   * </ul>
   * <p>
   * Method under test: {@link PushBody#PushBody(Queue)}
   */
  @Test
  @DisplayName("Test new PushBody(Queue); when LinkedList()")
  void testNewPushBody_whenLinkedList() {
    // Arrange, Act and Assert
    assertEquals(-1L, (new PushBody(new LinkedList<>())).getContentLength());
  }

  /**
   * Test {@link PushBody#transferTo(ByteBuf)}.
   * <ul>
   *   <li>Given three.</li>
   *   <li>When {@link ByteBuf} {@link ByteBuf#capacity()} return three.</li>
   *   <li>Then calls {@link ByteBuf#capacity()}.</li>
   * </ul>
   * <p>
   * Method under test: {@link PushBody#transferTo(ByteBuf)}
   */
  @Test
  @DisplayName("Test transferTo(ByteBuf); given three; when ByteBuf capacity() return three; then calls capacity()")
  void testTransferTo_givenThree_whenByteBufCapacityReturnThree_thenCallsCapacity() {
    // Arrange
    PushBody pushBody = new PushBody(new LinkedList<>());
    ByteBuf buffer = mock(ByteBuf.class);
    when(buffer.capacity()).thenReturn(3);
    when(buffer.maxCapacity()).thenReturn(3);
    when(buffer.readerIndex()).thenReturn(1);
    when(buffer.writerIndex()).thenReturn(1);

    // Act
    Body.BodyState actualTransferToResult = pushBody
        .transferTo(new DuplicatedByteBuf(new ReadOnlyByteBuf(new DuplicatedByteBuf(buffer))));

    // Assert
    verify(buffer, atLeast(1)).capacity();
    verify(buffer).maxCapacity();
    verify(buffer).readerIndex();
    verify(buffer).writerIndex();
    assertEquals(Body.BodyState.SUSPEND, actualTransferToResult);
  }

  /**
   * Test {@link PushBody#transferTo(ByteBuf)}.
   * <ul>
   *   <li>When {@link EmptyByteBuf#EmptyByteBuf(ByteBufAllocator)} with alloc is
   * {@link AdaptiveByteBufAllocator#AdaptiveByteBufAllocator()}.</li>
   * </ul>
   * <p>
   * Method under test: {@link PushBody#transferTo(ByteBuf)}
   */
  @Test
  @DisplayName("Test transferTo(ByteBuf); when EmptyByteBuf(ByteBufAllocator) with alloc is AdaptiveByteBufAllocator()")
  void testTransferTo_whenEmptyByteBufWithAllocIsAdaptiveByteBufAllocator() {
    // Arrange
    PushBody pushBody = new PushBody(new LinkedList<>());

    // Act and Assert
    assertEquals(Body.BodyState.SUSPEND,
        pushBody.transferTo(new DuplicatedByteBuf(new EmptyByteBuf(new AdaptiveByteBufAllocator()))));
  }

  /**
   * Test getters and setters.
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link PushBody#close()}
   *   <li>{@link PushBody#getContentLength()}
   * </ul>
   */
  @Test
  @DisplayName("Test getters and setters")
  void testGettersAndSetters() {
    // Arrange
    PushBody pushBody = new PushBody(new LinkedList<>());

    // Act
    pushBody.close();

    // Assert that nothing has changed
    assertEquals(-1L, pushBody.getContentLength());
  }
}
