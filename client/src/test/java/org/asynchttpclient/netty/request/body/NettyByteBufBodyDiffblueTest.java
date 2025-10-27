package org.asynchttpclient.netty.request.body;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import io.netty.buffer.AdaptiveByteBufAllocator;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.DuplicatedByteBuf;
import io.netty.buffer.EmptyByteBuf;
import io.netty.buffer.ReadOnlyByteBuf;
import org.asynchttpclient.config.AsyncHttpClientConfigDefaults;
import org.junit.jupiter.api.Test;

class NettyByteBufBodyDiffblueTest {
  /**
   * Method under test: {@link NettyByteBufBody#byteBuf()}
   */
  @Test
  void testByteBuf() {
    // Arrange
    DuplicatedByteBuf bb = new DuplicatedByteBuf(new EmptyByteBuf(new AdaptiveByteBufAllocator()));

    // Act and Assert
    assertSame(bb, (new NettyByteBufBody(bb)).byteBuf());
  }

  /**
   * Method under test: {@link NettyByteBufBody#byteBuf()}
   */
  @Test
  void testByteBuf2() {
    // Arrange
    ByteBuf buffer = mock(ByteBuf.class);
    when(buffer.capacity()).thenReturn(3);
    when(buffer.maxCapacity()).thenReturn(3);
    when(buffer.readerIndex()).thenReturn(1);
    when(buffer.writerIndex()).thenReturn(1);
    DuplicatedByteBuf bb = new DuplicatedByteBuf(new ReadOnlyByteBuf(new DuplicatedByteBuf(buffer)));

    // Act
    ByteBuf actualByteBufResult = (new NettyByteBufBody(bb)).byteBuf();

    // Assert
    verify(buffer, atLeast(1)).capacity();
    verify(buffer).maxCapacity();
    verify(buffer).readerIndex();
    verify(buffer).writerIndex();
    assertSame(bb, actualByteBufResult);
  }

  /**
   * Methods under test:
   * <ul>
   *   <li>{@link NettyByteBufBody#getContentLength()}
   *   <li>{@link NettyByteBufBody#getContentTypeOverride()}
   * </ul>
   */
  @Test
  void testGettersAndSetters() {
    // Arrange
    NettyByteBufBody nettyByteBufBody = new NettyByteBufBody(
        new DuplicatedByteBuf(new EmptyByteBuf(new AdaptiveByteBufAllocator())));

    // Act
    long actualContentLength = nettyByteBufBody.getContentLength();

    // Assert
    assertNull(nettyByteBufBody.getContentTypeOverride());
    assertEquals(0L, actualContentLength);
  }

  /**
   * Method under test: {@link NettyByteBufBody#NettyByteBufBody(ByteBuf)}
   */
  @Test
  void testNewNettyByteBufBody() {
    // Arrange and Act
    NettyByteBufBody actualNettyByteBufBody = new NettyByteBufBody(
        new DuplicatedByteBuf(new EmptyByteBuf(new AdaptiveByteBufAllocator())));

    // Assert
    assertNull(actualNettyByteBufBody.getContentTypeOverride());
    assertEquals(0L, actualNettyByteBufBody.getContentLength());
  }

  /**
   * Method under test: {@link NettyByteBufBody#NettyByteBufBody(ByteBuf)}
   */
  @Test
  void testNewNettyByteBufBody2() {
    // Arrange
    ByteBuf buffer = mock(ByteBuf.class);
    when(buffer.capacity()).thenReturn(3);
    when(buffer.maxCapacity()).thenReturn(3);
    when(buffer.readerIndex()).thenReturn(1);
    when(buffer.writerIndex()).thenReturn(1);

    // Act
    NettyByteBufBody actualNettyByteBufBody = new NettyByteBufBody(
        new DuplicatedByteBuf(new ReadOnlyByteBuf(new DuplicatedByteBuf(buffer))));

    // Assert
    verify(buffer, atLeast(1)).capacity();
    verify(buffer).maxCapacity();
    verify(buffer).readerIndex();
    verify(buffer).writerIndex();
    assertNull(actualNettyByteBufBody.getContentTypeOverride());
    assertEquals(0L, actualNettyByteBufBody.getContentLength());
  }

  /**
   * Method under test:
   * {@link NettyByteBufBody#NettyByteBufBody(ByteBuf, CharSequence)}
   */
  @Test
  void testNewNettyByteBufBody3() {
    // Arrange and Act
    NettyByteBufBody actualNettyByteBufBody = new NettyByteBufBody(
        new DuplicatedByteBuf(new EmptyByteBuf(new AdaptiveByteBufAllocator())),
        AsyncHttpClientConfigDefaults.ACQUIRE_FREE_CHANNEL_TIMEOUT);

    // Assert
    assertEquals("acquireFreeChannelTimeout", actualNettyByteBufBody.getContentTypeOverride());
    assertEquals(0L, actualNettyByteBufBody.getContentLength());
  }

  /**
   * Method under test:
   * {@link NettyByteBufBody#NettyByteBufBody(ByteBuf, CharSequence)}
   */
  @Test
  void testNewNettyByteBufBody4() {
    // Arrange
    ByteBuf buffer = mock(ByteBuf.class);
    when(buffer.capacity()).thenReturn(3);
    when(buffer.maxCapacity()).thenReturn(3);
    when(buffer.readerIndex()).thenReturn(1);
    when(buffer.writerIndex()).thenReturn(1);

    // Act
    NettyByteBufBody actualNettyByteBufBody = new NettyByteBufBody(
        new DuplicatedByteBuf(new ReadOnlyByteBuf(new DuplicatedByteBuf(buffer))),
        AsyncHttpClientConfigDefaults.ACQUIRE_FREE_CHANNEL_TIMEOUT);

    // Assert
    verify(buffer, atLeast(1)).capacity();
    verify(buffer).maxCapacity();
    verify(buffer).readerIndex();
    verify(buffer).writerIndex();
    assertEquals("acquireFreeChannelTimeout", actualNettyByteBufBody.getContentTypeOverride());
    assertEquals(0L, actualNettyByteBufBody.getContentLength());
  }
}
