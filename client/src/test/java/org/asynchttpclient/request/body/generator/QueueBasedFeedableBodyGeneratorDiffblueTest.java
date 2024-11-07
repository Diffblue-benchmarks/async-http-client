package org.asynchttpclient.request.body.generator;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import io.netty.buffer.AdaptiveByteBufAllocator;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.DuplicatedByteBuf;
import io.netty.buffer.EmptyByteBuf;
import org.asynchttpclient.request.body.Body;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class QueueBasedFeedableBodyGeneratorDiffblueTest {
  /**
   * Test {@link QueueBasedFeedableBodyGenerator#createBody()}.
   * <p>
   * Method under test: {@link QueueBasedFeedableBodyGenerator#createBody()}
   */
  @Test
  @DisplayName("Test createBody()")
  void testCreateBody() {
    // Arrange and Act
    Body actualCreateBodyResult = (new UnboundedQueueFeedableBodyGenerator()).createBody();

    // Assert
    assertTrue(actualCreateBodyResult instanceof PushBody);
    assertEquals(-1L, actualCreateBodyResult.getContentLength());
  }

  /**
   * Test {@link QueueBasedFeedableBodyGenerator#feed(ByteBuf, boolean)}.
   * <ul>
   *   <li>Given {@link FeedListener} {@link FeedListener#onContentAdded()} does
   * nothing.</li>
   *   <li>Then calls {@link FeedListener#onContentAdded()}.</li>
   * </ul>
   * <p>
   * Method under test:
   * {@link QueueBasedFeedableBodyGenerator#feed(ByteBuf, boolean)}
   */
  @Test
  @DisplayName("Test feed(ByteBuf, boolean); given FeedListener onContentAdded() does nothing; then calls onContentAdded()")
  void testFeed_givenFeedListenerOnContentAddedDoesNothing_thenCallsOnContentAdded() throws Exception {
    // Arrange
    FeedListener listener = mock(FeedListener.class);
    doNothing().when(listener).onContentAdded();

    UnboundedQueueFeedableBodyGenerator unboundedQueueFeedableBodyGenerator = new UnboundedQueueFeedableBodyGenerator();
    unboundedQueueFeedableBodyGenerator.setListener(listener);

    // Act
    boolean actualFeedResult = unboundedQueueFeedableBodyGenerator
        .feed(new DuplicatedByteBuf(new EmptyByteBuf(new AdaptiveByteBufAllocator())), true);

    // Assert
    verify(listener).onContentAdded();
    assertEquals(1, unboundedQueueFeedableBodyGenerator.queue.size());
    assertTrue(actualFeedResult);
  }

  /**
   * Test {@link QueueBasedFeedableBodyGenerator#feed(ByteBuf, boolean)}.
   * <ul>
   *   <li>Given {@link UnboundedQueueFeedableBodyGenerator} (default
   * constructor).</li>
   * </ul>
   * <p>
   * Method under test:
   * {@link QueueBasedFeedableBodyGenerator#feed(ByteBuf, boolean)}
   */
  @Test
  @DisplayName("Test feed(ByteBuf, boolean); given UnboundedQueueFeedableBodyGenerator (default constructor)")
  void testFeed_givenUnboundedQueueFeedableBodyGenerator() throws Exception {
    // Arrange
    UnboundedQueueFeedableBodyGenerator unboundedQueueFeedableBodyGenerator = new UnboundedQueueFeedableBodyGenerator();

    // Act
    boolean actualFeedResult = unboundedQueueFeedableBodyGenerator
        .feed(new DuplicatedByteBuf(new EmptyByteBuf(new AdaptiveByteBufAllocator())), true);

    // Assert
    assertEquals(1, unboundedQueueFeedableBodyGenerator.queue.size());
    assertTrue(actualFeedResult);
  }
}
