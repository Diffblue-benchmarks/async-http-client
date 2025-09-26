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

class UnboundedQueueFeedableBodyGeneratorDiffblueTest {
  /**
   * Test new {@link UnboundedQueueFeedableBodyGenerator} (default constructor).
   *
   * <p>Method under test: default or parameterless constructor of {@link
   * UnboundedQueueFeedableBodyGenerator}
   */
  @Test
  @DisplayName("Test new UnboundedQueueFeedableBodyGenerator (default constructor)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void UnboundedQueueFeedableBodyGenerator.<init>()"})
  void testNewUnboundedQueueFeedableBodyGenerator() {
    // Arrange, Act and Assert
    assertTrue(new UnboundedQueueFeedableBodyGenerator().queue.isEmpty());
  }

  /**
   * Test {@link UnboundedQueueFeedableBodyGenerator#offer(BodyChunk)}.
   *
   * <ul>
   *   <li>Then {@link UnboundedQueueFeedableBodyGenerator} (default constructor) {@link
   *       QueueBasedFeedableBodyGenerator#queue} size is one.
   * </ul>
   *
   * <p>Method under test: {@link UnboundedQueueFeedableBodyGenerator#offer(BodyChunk)}
   */
  @Test
  @DisplayName(
      "Test offer(BodyChunk); then UnboundedQueueFeedableBodyGenerator (default constructor) queue size is one")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean UnboundedQueueFeedableBodyGenerator.offer(BodyChunk)"})
  void testOffer_thenUnboundedQueueFeedableBodyGeneratorQueueSizeIsOne() {
    // Arrange
    UnboundedQueueFeedableBodyGenerator unboundedQueueFeedableBodyGenerator =
        new UnboundedQueueFeedableBodyGenerator();
    DuplicatedByteBuf buffer =
        new DuplicatedByteBuf(new EmptyByteBuf(new AdaptiveByteBufAllocator()));

    // Act
    boolean actualOfferResult =
        unboundedQueueFeedableBodyGenerator.offer(new BodyChunk(buffer, true));

    // Assert
    assertEquals(1, unboundedQueueFeedableBodyGenerator.queue.size());
    assertTrue(actualOfferResult);
  }
}
