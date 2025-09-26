package org.asynchttpclient.netty.channel;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import io.netty.buffer.ByteBufAllocator;
import io.netty.buffer.PooledByteBufAllocator;
import io.netty.channel.ChannelMetadata;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.DefaultChannelId;
import io.netty.channel.DefaultChannelPipeline;
import io.netty.channel.VoidChannelPromise;
import io.netty.channel.socket.nio.NioSocketChannel;
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
}
