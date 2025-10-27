package org.asynchttpclient.request.body.generator;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import io.netty.buffer.AdaptiveByteBufAllocator;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.DuplicatedByteBuf;
import io.netty.buffer.EmptyByteBuf;
import io.netty.buffer.ReadOnlyByteBuf;
import org.junit.jupiter.api.Test;

class BoundedQueueFeedableBodyGeneratorDiffblueTest {
  /**
   * Method under test:
   * {@link BoundedQueueFeedableBodyGenerator#BoundedQueueFeedableBodyGenerator(int)}
   */
  @Test
  void testNewBoundedQueueFeedableBodyGenerator() {
    // Arrange, Act and Assert
    assertTrue((new BoundedQueueFeedableBodyGenerator(3)).queue.isEmpty());
  }

  /**
   * Method under test: {@link BoundedQueueFeedableBodyGenerator#offer(BodyChunk)}
   */
  @Test
  void testOffer() throws InterruptedException {
    // Arrange
    BoundedQueueFeedableBodyGenerator boundedQueueFeedableBodyGenerator = new BoundedQueueFeedableBodyGenerator(3);

    // Act
    boolean actualOfferResult = boundedQueueFeedableBodyGenerator
        .offer(new BodyChunk(new DuplicatedByteBuf(new EmptyByteBuf(new AdaptiveByteBufAllocator())), true));

    // Assert
    assertEquals(1, boundedQueueFeedableBodyGenerator.queue.size());
    assertTrue(actualOfferResult);
  }

  /**
   * Method under test: {@link BoundedQueueFeedableBodyGenerator#offer(BodyChunk)}
   */
  @Test
  void testOffer2() throws InterruptedException {
    // Arrange
    BoundedQueueFeedableBodyGenerator boundedQueueFeedableBodyGenerator = new BoundedQueueFeedableBodyGenerator(3);
    ByteBuf buffer = mock(ByteBuf.class);
    when(buffer.capacity()).thenReturn(3);
    when(buffer.maxCapacity()).thenReturn(3);
    when(buffer.readerIndex()).thenReturn(1);
    when(buffer.writerIndex()).thenReturn(1);

    // Act
    boolean actualOfferResult = boundedQueueFeedableBodyGenerator
        .offer(new BodyChunk(new DuplicatedByteBuf(new ReadOnlyByteBuf(new DuplicatedByteBuf(buffer))), true));

    // Assert
    verify(buffer, atLeast(1)).capacity();
    verify(buffer).maxCapacity();
    verify(buffer).readerIndex();
    verify(buffer).writerIndex();
    assertEquals(1, boundedQueueFeedableBodyGenerator.queue.size());
    assertTrue(actualOfferResult);
  }
}
