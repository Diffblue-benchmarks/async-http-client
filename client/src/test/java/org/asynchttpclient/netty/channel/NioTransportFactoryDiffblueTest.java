package org.asynchttpclient.netty.channel;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import io.netty.buffer.ByteBufAllocator;
import io.netty.buffer.PooledByteBufAllocator;
import io.netty.channel.ChannelMetadata;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.DefaultChannelId;
import io.netty.channel.DefaultChannelPipeline;
import io.netty.channel.EventLoop;
import io.netty.channel.VoidChannelPromise;
import io.netty.channel.nio.NioEventLoop;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.util.concurrent.EventExecutor;
import java.util.Iterator;
import java.util.concurrent.ThreadFactory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

class NioTransportFactoryDiffblueTest {
  /**
   * Test {@link NioTransportFactory#newChannel()}.
   *
   * <p>Method under test: {@link NioTransportFactory#newChannel()}
   */
  @Test
  @DisplayName("Test newChannel()")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"NioSocketChannel NioTransportFactory.newChannel()"})
  void testNewChannel() {
    // Arrange and Act
    NioSocketChannel actualNewChannelResult = NioTransportFactory.INSTANCE.newChannel();

    // Assert
    ByteBufAllocator allocResult = actualNewChannelResult.alloc();
    assertTrue(allocResult instanceof PooledByteBufAllocator);
    assertTrue(actualNewChannelResult.id() instanceof DefaultChannelId);
    ChannelPipeline pipelineResult = actualNewChannelResult.pipeline();
    assertTrue(pipelineResult instanceof DefaultChannelPipeline);
    assertTrue(pipelineResult.voidPromise() instanceof VoidChannelPromise);
    assertNull(actualNewChannelResult.parent());
    assertEquals(256, ((PooledByteBufAllocator) allocResult).smallCacheSize());
    assertEquals(4194304, ((PooledByteBufAllocator) allocResult).chunkSize());
    assertFalse(((PooledByteBufAllocator) allocResult).hasThreadLocalCache());
    assertFalse(actualNewChannelResult.isRegistered());
    ChannelMetadata metadataResult = actualNewChannelResult.metadata();
    assertFalse(metadataResult.hasDisconnect());
    assertTrue(allocResult.isDirectBufferPooled());
    assertTrue(actualNewChannelResult.isOpen());
    assertEquals(Double.SIZE, ((PooledByteBufAllocator) allocResult).normalCacheSize());
    assertEquals(Short.SIZE, metadataResult.defaultMaxMessagesPerRead());
    assertEquals(Short.SIZE, ((PooledByteBufAllocator) allocResult).directArenas().size());
    assertEquals(Short.SIZE, ((PooledByteBufAllocator) allocResult).heapArenas().size());
    assertSame(actualNewChannelResult, pipelineResult.channel());
  }

  /**
   * Test {@link NioTransportFactory#newEventLoopGroup(int, ThreadFactory)}.
   *
   * <ul>
   *   <li>When {@code null}.
   *   <li>Then return iterator hasNext.
   * </ul>
   *
   * <p>Method under test: {@link NioTransportFactory#newEventLoopGroup(int, ThreadFactory)}
   */
  @Test
  @DisplayName(
      "Test newEventLoopGroup(int, ThreadFactory); when 'null'; then return iterator hasNext")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"NioEventLoopGroup NioTransportFactory.newEventLoopGroup(int, ThreadFactory)"})
  void testNewEventLoopGroup_whenNull_thenReturnIteratorHasNext() {
    // Arrange and Act
    NioEventLoopGroup actualNewEventLoopGroupResult =
        NioTransportFactory.INSTANCE.newEventLoopGroup(0, null);

    // Assert
    Iterator<EventExecutor> iteratorResult = actualNewEventLoopGroupResult.iterator();
    EventExecutor actualNextResult = iteratorResult.next();
    EventExecutor nextResult = iteratorResult.next();
    EventExecutor nextResult2 = iteratorResult.next();
    boolean actualHasNextResult = iteratorResult.hasNext();
    EventLoop nextResult3 = actualNewEventLoopGroupResult.next();
    assertTrue(nextResult3 instanceof NioEventLoop);
    assertTrue(nextResult instanceof NioEventLoop);
    assertTrue(nextResult2 instanceof NioEventLoop);
    assertTrue(actualHasNextResult);
    assertSame(actualNewEventLoopGroupResult, nextResult3.parent());
    assertSame(nextResult3, nextResult3.next());
    assertSame(nextResult3, actualNextResult);
  }

  /**
   * Test {@link NioTransportFactory#newEventLoopGroup(int, ThreadFactory)}.
   *
   * <ul>
   *   <li>When zero.
   *   <li>Then return iterator hasNext.
   * </ul>
   *
   * <p>Method under test: {@link NioTransportFactory#newEventLoopGroup(int, ThreadFactory)}
   */
  @Test
  @DisplayName(
      "Test newEventLoopGroup(int, ThreadFactory); when zero; then return iterator hasNext")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"NioEventLoopGroup NioTransportFactory.newEventLoopGroup(int, ThreadFactory)"})
  void testNewEventLoopGroup_whenZero_thenReturnIteratorHasNext() {
    // Arrange and Act
    NioEventLoopGroup actualNewEventLoopGroupResult =
        NioTransportFactory.INSTANCE.newEventLoopGroup(0, mock(ThreadFactory.class));

    // Assert
    Iterator<EventExecutor> iteratorResult = actualNewEventLoopGroupResult.iterator();
    EventExecutor actualNextResult = iteratorResult.next();
    EventExecutor nextResult = iteratorResult.next();
    EventExecutor nextResult2 = iteratorResult.next();
    boolean actualHasNextResult = iteratorResult.hasNext();
    EventLoop nextResult3 = actualNewEventLoopGroupResult.next();
    assertTrue(nextResult3 instanceof NioEventLoop);
    assertTrue(nextResult instanceof NioEventLoop);
    assertTrue(nextResult2 instanceof NioEventLoop);
    assertTrue(actualHasNextResult);
    assertSame(actualNewEventLoopGroupResult, nextResult3.parent());
    assertSame(nextResult3, nextResult3.next());
    assertSame(nextResult3, actualNextResult);
  }
}
