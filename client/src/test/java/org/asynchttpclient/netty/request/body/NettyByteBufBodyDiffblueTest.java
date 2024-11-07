package org.asynchttpclient.netty.request.body;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import io.netty.buffer.AdaptiveByteBufAllocator;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.DuplicatedByteBuf;
import io.netty.buffer.EmptyByteBuf;
import org.asynchttpclient.config.AsyncHttpClientConfigDefaults;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class NettyByteBufBodyDiffblueTest {
  /**
   * Test {@link NettyByteBufBody#NettyByteBufBody(ByteBuf, CharSequence)}.
   * <ul>
   *   <li>Then return ContentTypeOverride is
   * {@code acquireFreeChannelTimeout}.</li>
   * </ul>
   * <p>
   * Method under test:
   * {@link NettyByteBufBody#NettyByteBufBody(ByteBuf, CharSequence)}
   */
  @Test
  @DisplayName("Test new NettyByteBufBody(ByteBuf, CharSequence); then return ContentTypeOverride is 'acquireFreeChannelTimeout'")
  void testNewNettyByteBufBody_thenReturnContentTypeOverrideIsAcquireFreeChannelTimeout() {
    // Arrange and Act
    NettyByteBufBody actualNettyByteBufBody = new NettyByteBufBody(
        new DuplicatedByteBuf(new EmptyByteBuf(new AdaptiveByteBufAllocator())),
        AsyncHttpClientConfigDefaults.ACQUIRE_FREE_CHANNEL_TIMEOUT);

    // Assert
    assertEquals("acquireFreeChannelTimeout", actualNettyByteBufBody.getContentTypeOverride());
    assertEquals(0L, actualNettyByteBufBody.getContentLength());
  }

  /**
   * Test {@link NettyByteBufBody#NettyByteBufBody(ByteBuf)}.
   * <ul>
   *   <li>Then return ContentTypeOverride is {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link NettyByteBufBody#NettyByteBufBody(ByteBuf)}
   */
  @Test
  @DisplayName("Test new NettyByteBufBody(ByteBuf); then return ContentTypeOverride is 'null'")
  void testNewNettyByteBufBody_thenReturnContentTypeOverrideIsNull() {
    // Arrange and Act
    NettyByteBufBody actualNettyByteBufBody = new NettyByteBufBody(
        new DuplicatedByteBuf(new EmptyByteBuf(new AdaptiveByteBufAllocator())));

    // Assert
    assertNull(actualNettyByteBufBody.getContentTypeOverride());
    assertEquals(0L, actualNettyByteBufBody.getContentLength());
  }

  /**
   * Test getters and setters.
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link NettyByteBufBody#getContentLength()}
   *   <li>{@link NettyByteBufBody#getContentTypeOverride()}
   * </ul>
   */
  @Test
  @DisplayName("Test getters and setters")
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
   * Test {@link NettyByteBufBody#byteBuf()}.
   * <p>
   * Method under test: {@link NettyByteBufBody#byteBuf()}
   */
  @Test
  @DisplayName("Test byteBuf()")
  void testByteBuf() {
    // Arrange
    DuplicatedByteBuf bb = new DuplicatedByteBuf(new EmptyByteBuf(new AdaptiveByteBufAllocator()));

    // Act and Assert
    assertSame(bb, (new NettyByteBufBody(bb)).byteBuf());
  }
}
