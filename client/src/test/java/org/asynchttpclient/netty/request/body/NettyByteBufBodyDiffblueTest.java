package org.asynchttpclient.netty.request.body;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import io.netty.buffer.AdaptiveByteBufAllocator;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.DuplicatedByteBuf;
import io.netty.buffer.EmptyByteBuf;
import org.asynchttpclient.config.AsyncHttpClientConfigDefaults;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

class NettyByteBufBodyDiffblueTest {
  /**
   * Test {@link NettyByteBufBody#NettyByteBufBody(ByteBuf, CharSequence)}.
   *
   * <ul>
   *   <li>Then return ContentTypeOverride is {@code acquireFreeChannelTimeout}.
   * </ul>
   *
   * <p>Method under test: {@link NettyByteBufBody#NettyByteBufBody(ByteBuf, CharSequence)}
   */
  @Test
  @DisplayName(
      "Test new NettyByteBufBody(ByteBuf, CharSequence); then return ContentTypeOverride is 'acquireFreeChannelTimeout'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void NettyByteBufBody.<init>(ByteBuf, CharSequence)"})
  void testNewNettyByteBufBody_thenReturnContentTypeOverrideIsAcquireFreeChannelTimeout() {
    // Arrange
    DuplicatedByteBuf bb = new DuplicatedByteBuf(new EmptyByteBuf(new AdaptiveByteBufAllocator()));

    // Act
    NettyByteBufBody actualNettyByteBufBody =
        new NettyByteBufBody(bb, AsyncHttpClientConfigDefaults.ACQUIRE_FREE_CHANNEL_TIMEOUT);

    // Assert
    assertEquals("acquireFreeChannelTimeout", actualNettyByteBufBody.getContentTypeOverride());
    assertEquals(0L, actualNettyByteBufBody.getContentLength());
  }

  /**
   * Test {@link NettyByteBufBody#NettyByteBufBody(ByteBuf)}.
   *
   * <ul>
   *   <li>Then return ContentTypeOverride is {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link NettyByteBufBody#NettyByteBufBody(ByteBuf)}
   */
  @Test
  @DisplayName("Test new NettyByteBufBody(ByteBuf); then return ContentTypeOverride is 'null'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void NettyByteBufBody.<init>(ByteBuf)"})
  void testNewNettyByteBufBody_thenReturnContentTypeOverrideIsNull() {
    // Arrange
    DuplicatedByteBuf bb = new DuplicatedByteBuf(new EmptyByteBuf(new AdaptiveByteBufAllocator()));

    // Act
    NettyByteBufBody actualNettyByteBufBody = new NettyByteBufBody(bb);

    // Assert
    assertNull(actualNettyByteBufBody.getContentTypeOverride());
    assertEquals(0L, actualNettyByteBufBody.getContentLength());
  }

  /**
   * Test getters and setters.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link NettyByteBufBody#getContentLength()}
   *   <li>{@link NettyByteBufBody#getContentTypeOverride()}
   * </ul>
   */
  @Test
  @DisplayName("Test getters and setters")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "long NettyByteBufBody.getContentLength()",
    "CharSequence NettyByteBufBody.getContentTypeOverride()"
  })
  void testGettersAndSetters() {
    // Arrange
    DuplicatedByteBuf bb = new DuplicatedByteBuf(new EmptyByteBuf(new AdaptiveByteBufAllocator()));
    NettyByteBufBody nettyByteBufBody = new NettyByteBufBody(bb);

    // Act
    long actualContentLength = nettyByteBufBody.getContentLength();

    // Assert
    assertNull(nettyByteBufBody.getContentTypeOverride());
    assertEquals(0L, actualContentLength);
  }

  /**
   * Test {@link NettyByteBufBody#byteBuf()}.
   *
   * <p>Method under test: {@link NettyByteBufBody#byteBuf()}
   */
  @Test
  @DisplayName("Test byteBuf()")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"ByteBuf NettyByteBufBody.byteBuf()"})
  void testByteBuf() {
    // Arrange
    DuplicatedByteBuf bb = new DuplicatedByteBuf(new EmptyByteBuf(new AdaptiveByteBufAllocator()));

    // Act and Assert
    assertSame(bb, new NettyByteBufBody(bb).byteBuf());
  }
}
