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
import org.junit.jupiter.api.Test;

class QueueBasedFeedableBodyGeneratorDiffblueTest {
  /**
   * Method under test: {@link QueueBasedFeedableBodyGenerator#createBody()}
   */
  @Test
  void testCreateBody() {
    // Arrange and Act
    Body actualCreateBodyResult = (new UnboundedQueueFeedableBodyGenerator()).createBody();

    // Assert
    assertTrue(actualCreateBodyResult instanceof PushBody);
    assertEquals(-1L, actualCreateBodyResult.getContentLength());
  }

  /**
   * Method under test: {@link QueueBasedFeedableBodyGenerator#createBody()}
   */
  @Test
  void testCreateBody2() {
    // Arrange
    UnboundedQueueFeedableBodyGenerator unboundedQueueFeedableBodyGenerator = new UnboundedQueueFeedableBodyGenerator();
    unboundedQueueFeedableBodyGenerator.setListener(mock(FeedListener.class));

    // Act
    Body actualCreateBodyResult = unboundedQueueFeedableBodyGenerator.createBody();

    // Assert
    assertTrue(actualCreateBodyResult instanceof PushBody);
    assertEquals(-1L, actualCreateBodyResult.getContentLength());
  }

  /**
   * Method under test:
   * {@link QueueBasedFeedableBodyGenerator#feed(ByteBuf, boolean)}
   */
  @Test
  void testFeed() throws Exception {
    // Arrange
    UnboundedQueueFeedableBodyGenerator unboundedQueueFeedableBodyGenerator = new UnboundedQueueFeedableBodyGenerator();

    // Act
    boolean actualFeedResult = unboundedQueueFeedableBodyGenerator
        .feed(new DuplicatedByteBuf(new EmptyByteBuf(new AdaptiveByteBufAllocator())), true);

    // Assert
    assertEquals(1, unboundedQueueFeedableBodyGenerator.queue.size());
    assertTrue(actualFeedResult);
  }

  /**
   * Method under test:
   * {@link QueueBasedFeedableBodyGenerator#feed(ByteBuf, boolean)}
   */
  @Test
  void testFeed2() throws Exception {
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
}
