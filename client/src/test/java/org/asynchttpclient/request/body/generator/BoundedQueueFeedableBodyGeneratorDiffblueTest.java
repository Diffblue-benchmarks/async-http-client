package org.asynchttpclient.request.body.generator;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import io.netty.buffer.AdaptiveByteBufAllocator;
import io.netty.buffer.DuplicatedByteBuf;
import io.netty.buffer.EmptyByteBuf;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BoundedQueueFeedableBodyGeneratorDiffblueTest {
  /**
   * Test
   * {@link BoundedQueueFeedableBodyGenerator#BoundedQueueFeedableBodyGenerator(int)}.
   * <p>
   * Method under test:
   * {@link BoundedQueueFeedableBodyGenerator#BoundedQueueFeedableBodyGenerator(int)}
   */
  @Test
  @DisplayName("Test new BoundedQueueFeedableBodyGenerator(int)")
  void testNewBoundedQueueFeedableBodyGenerator() {
    // Arrange, Act and Assert
    assertTrue((new BoundedQueueFeedableBodyGenerator(3)).queue.isEmpty());
  }

  /**
   * Test {@link BoundedQueueFeedableBodyGenerator#offer(BodyChunk)}.
   * <ul>
   *   <li>Then
   * {@link BoundedQueueFeedableBodyGenerator#BoundedQueueFeedableBodyGenerator(int)}
   * with capacity is three {@link QueueBasedFeedableBodyGenerator#queue} size is
   * one.</li>
   * </ul>
   * <p>
   * Method under test: {@link BoundedQueueFeedableBodyGenerator#offer(BodyChunk)}
   */
  @Test
  @DisplayName("Test offer(BodyChunk); then BoundedQueueFeedableBodyGenerator(int) with capacity is three queue size is one")
  void testOffer_thenBoundedQueueFeedableBodyGeneratorWithCapacityIsThreeQueueSizeIsOne() throws InterruptedException {
    // Arrange
    BoundedQueueFeedableBodyGenerator boundedQueueFeedableBodyGenerator = new BoundedQueueFeedableBodyGenerator(3);

    // Act
    boolean actualOfferResult = boundedQueueFeedableBodyGenerator
        .offer(new BodyChunk(new DuplicatedByteBuf(new EmptyByteBuf(new AdaptiveByteBufAllocator())), true));

    // Assert
    assertEquals(1, boundedQueueFeedableBodyGenerator.queue.size());
    assertTrue(actualOfferResult);
  }
}
