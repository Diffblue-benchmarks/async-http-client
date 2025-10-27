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

class UnboundedQueueFeedableBodyGeneratorDiffblueTest {
  /**
   * Method under test: default or parameterless constructor of
   * {@link UnboundedQueueFeedableBodyGenerator}
   */
  @Test
  void testNewUnboundedQueueFeedableBodyGenerator() {
    // Arrange, Act and Assert
    assertTrue((new UnboundedQueueFeedableBodyGenerator()).queue.isEmpty());
  }

  /**
   * Method under test:
   * {@link UnboundedQueueFeedableBodyGenerator#offer(BodyChunk)}
   */
  @Test
  void testOffer() {
    // Arrange
    UnboundedQueueFeedableBodyGenerator unboundedQueueFeedableBodyGenerator = new UnboundedQueueFeedableBodyGenerator();

    // Act
    boolean actualOfferResult = unboundedQueueFeedableBodyGenerator
        .offer(new BodyChunk(new DuplicatedByteBuf(new EmptyByteBuf(new AdaptiveByteBufAllocator())), true));

    // Assert
    assertEquals(1, unboundedQueueFeedableBodyGenerator.queue.size());
    assertTrue(actualOfferResult);
  }

  /**
   * Method under test:
   * {@link UnboundedQueueFeedableBodyGenerator#offer(BodyChunk)}
   */
  @Test
  void testOffer2() {
    // Arrange
    UnboundedQueueFeedableBodyGenerator unboundedQueueFeedableBodyGenerator = new UnboundedQueueFeedableBodyGenerator();
    ByteBuf buffer = mock(ByteBuf.class);
    when(buffer.capacity()).thenReturn(3);
    when(buffer.maxCapacity()).thenReturn(3);
    when(buffer.readerIndex()).thenReturn(1);
    when(buffer.writerIndex()).thenReturn(1);

    // Act
    boolean actualOfferResult = unboundedQueueFeedableBodyGenerator
        .offer(new BodyChunk(new DuplicatedByteBuf(new ReadOnlyByteBuf(new DuplicatedByteBuf(buffer))), true));

    // Assert
    verify(buffer, atLeast(1)).capacity();
    verify(buffer).maxCapacity();
    verify(buffer).readerIndex();
    verify(buffer).writerIndex();
    assertEquals(1, unboundedQueueFeedableBodyGenerator.queue.size());
    assertTrue(actualOfferResult);
  }
}
