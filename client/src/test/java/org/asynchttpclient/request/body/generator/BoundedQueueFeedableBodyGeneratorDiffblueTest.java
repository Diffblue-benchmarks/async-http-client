package org.asynchttpclient.request.body.generator;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import io.netty.buffer.AdaptiveByteBufAllocator;
import io.netty.buffer.DuplicatedByteBuf;
import io.netty.buffer.EmptyByteBuf;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

class BoundedQueueFeedableBodyGeneratorDiffblueTest {
  /**
   * Test {@link BoundedQueueFeedableBodyGenerator#BoundedQueueFeedableBodyGenerator(int)}.
   *
   * <p>Method under test: {@link
   * BoundedQueueFeedableBodyGenerator#BoundedQueueFeedableBodyGenerator(int)}
   */
  @Test
  @DisplayName("Test new BoundedQueueFeedableBodyGenerator(int)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void BoundedQueueFeedableBodyGenerator.<init>(int)"})
  void testNewBoundedQueueFeedableBodyGenerator() {
    // Arrange, Act and Assert
    assertTrue(new BoundedQueueFeedableBodyGenerator(3).queue.isEmpty());
  }

  /**
   * Test {@link BoundedQueueFeedableBodyGenerator#offer(BodyChunk)}.
   *
   * <ul>
   *   <li>Then {@link BoundedQueueFeedableBodyGenerator#BoundedQueueFeedableBodyGenerator(int)}
   *       with capacity is three {@link QueueBasedFeedableBodyGenerator#queue} size is one.
   * </ul>
   *
   * <p>Method under test: {@link BoundedQueueFeedableBodyGenerator#offer(BodyChunk)}
   */
  @Test
  @DisplayName(
      "Test offer(BodyChunk); then BoundedQueueFeedableBodyGenerator(int) with capacity is three queue size is one")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean BoundedQueueFeedableBodyGenerator.offer(BodyChunk)"})
  void testOffer_thenBoundedQueueFeedableBodyGeneratorWithCapacityIsThreeQueueSizeIsOne()
      throws InterruptedException {
    // Arrange
    BoundedQueueFeedableBodyGenerator boundedQueueFeedableBodyGenerator =
        new BoundedQueueFeedableBodyGenerator(3);
    DuplicatedByteBuf buffer =
        new DuplicatedByteBuf(new EmptyByteBuf(new AdaptiveByteBufAllocator()));

    // Act
    boolean actualOfferResult =
        boundedQueueFeedableBodyGenerator.offer(new BodyChunk(buffer, true));

    // Assert
    assertEquals(1, boundedQueueFeedableBodyGenerator.queue.size());
    assertTrue(actualOfferResult);
  }
}
