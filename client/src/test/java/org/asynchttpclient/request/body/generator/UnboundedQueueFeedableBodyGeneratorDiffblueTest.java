package org.asynchttpclient.request.body.generator;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import io.netty.buffer.AdaptiveByteBufAllocator;
import io.netty.buffer.DuplicatedByteBuf;
import io.netty.buffer.EmptyByteBuf;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class UnboundedQueueFeedableBodyGeneratorDiffblueTest {
  /**
   * Test new {@link UnboundedQueueFeedableBodyGenerator} (default constructor).
   * <p>
   * Method under test: default or parameterless constructor of
   * {@link UnboundedQueueFeedableBodyGenerator}
   */
  @Test
  @DisplayName("Test new UnboundedQueueFeedableBodyGenerator (default constructor)")
  void testNewUnboundedQueueFeedableBodyGenerator() {
    // Arrange, Act and Assert
    assertTrue((new UnboundedQueueFeedableBodyGenerator()).queue.isEmpty());
  }

  /**
   * Test {@link UnboundedQueueFeedableBodyGenerator#offer(BodyChunk)}.
   * <ul>
   *   <li>Then {@link UnboundedQueueFeedableBodyGenerator} (default constructor)
   * {@link QueueBasedFeedableBodyGenerator#queue} size is one.</li>
   * </ul>
   * <p>
   * Method under test:
   * {@link UnboundedQueueFeedableBodyGenerator#offer(BodyChunk)}
   */
  @Test
  @DisplayName("Test offer(BodyChunk); then UnboundedQueueFeedableBodyGenerator (default constructor) queue size is one")
  void testOffer_thenUnboundedQueueFeedableBodyGeneratorQueueSizeIsOne() {
    // Arrange
    UnboundedQueueFeedableBodyGenerator unboundedQueueFeedableBodyGenerator = new UnboundedQueueFeedableBodyGenerator();

    // Act
    boolean actualOfferResult = unboundedQueueFeedableBodyGenerator
        .offer(new BodyChunk(new DuplicatedByteBuf(new EmptyByteBuf(new AdaptiveByteBufAllocator())), true));

    // Assert
    assertEquals(1, unboundedQueueFeedableBodyGenerator.queue.size());
    assertTrue(actualOfferResult);
  }
}
