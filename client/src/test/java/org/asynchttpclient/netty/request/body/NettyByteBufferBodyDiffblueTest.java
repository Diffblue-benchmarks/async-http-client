package org.asynchttpclient.netty.request.body;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.UnpooledByteBufAllocator;
import io.netty.buffer.UnpooledHeapByteBuf;
import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import org.asynchttpclient.config.AsyncHttpClientConfigDefaults;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

class NettyByteBufferBodyDiffblueTest {
  /**
   * Test {@link NettyByteBufferBody#NettyByteBufferBody(ByteBuffer, CharSequence)}.
   *
   * <p>Method under test: {@link NettyByteBufferBody#NettyByteBufferBody(ByteBuffer, CharSequence)}
   */
  @Test
  @DisplayName("Test new NettyByteBufferBody(ByteBuffer, CharSequence)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void NettyByteBufferBody.<init>(ByteBuffer, CharSequence)"})
  void testNewNettyByteBufferBody() throws UnsupportedEncodingException {
    // Arrange and Act
    NettyByteBufferBody actualNettyByteBufferBody =
        new NettyByteBufferBody(
            ByteBuffer.wrap("AXAXAXAX".getBytes("UTF-8")),
            AsyncHttpClientConfigDefaults.ACQUIRE_FREE_CHANNEL_TIMEOUT);

    // Assert
    assertEquals("acquireFreeChannelTimeout", actualNettyByteBufferBody.getContentTypeOverride());
    assertEquals(8L, actualNettyByteBufferBody.getContentLength());
  }

  /**
   * Test {@link NettyByteBufferBody#NettyByteBufferBody(ByteBuffer)}.
   *
   * <ul>
   *   <li>Then return ContentTypeOverride is {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link NettyByteBufferBody#NettyByteBufferBody(ByteBuffer)}
   */
  @Test
  @DisplayName(
      "Test new NettyByteBufferBody(ByteBuffer); then return ContentTypeOverride is 'null'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void NettyByteBufferBody.<init>(ByteBuffer)"})
  void testNewNettyByteBufferBody_thenReturnContentTypeOverrideIsNull()
      throws UnsupportedEncodingException {
    // Arrange and Act
    NettyByteBufferBody actualNettyByteBufferBody =
        new NettyByteBufferBody(ByteBuffer.wrap("AXAXAXAX".getBytes("UTF-8")));

    // Assert
    assertNull(actualNettyByteBufferBody.getContentTypeOverride());
    assertEquals(8L, actualNettyByteBufferBody.getContentLength());
  }

  /**
   * Test getters and setters.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link NettyByteBufferBody#getContentLength()}
   *   <li>{@link NettyByteBufferBody#getContentTypeOverride()}
   * </ul>
   */
  @Test
  @DisplayName("Test getters and setters")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "long NettyByteBufferBody.getContentLength()",
    "CharSequence NettyByteBufferBody.getContentTypeOverride()"
  })
  void testGettersAndSetters() throws UnsupportedEncodingException {
    // Arrange
    NettyByteBufferBody nettyByteBufferBody =
        new NettyByteBufferBody(ByteBuffer.wrap("AXAXAXAX".getBytes("UTF-8")));

    // Act
    long actualContentLength = nettyByteBufferBody.getContentLength();

    // Assert
    assertNull(nettyByteBufferBody.getContentTypeOverride());
    assertEquals(8L, actualContentLength);
  }

  /**
   * Test {@link NettyByteBufferBody#byteBuf()}.
   *
   * <ul>
   *   <li>Given {@link ByteBuffer#put(byte[], int, int)} with {@code AXAXAXAX} Bytes is {@code
   *       UTF-8} and one and one.
   * </ul>
   *
   * <p>Method under test: {@link NettyByteBufferBody#byteBuf()}
   */
  @Test
  @DisplayName(
      "Test byteBuf(); given put(byte[], int, int) with 'AXAXAXAX' Bytes is 'UTF-8' and one and one")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"ByteBuf NettyByteBufferBody.byteBuf()"})
  void testByteBuf_givenPutWithAxaxaxaxBytesIsUtf8AndOneAndOne()
      throws UnsupportedEncodingException {
    // Arrange
    ByteBuffer bb = ByteBuffer.wrap("AXAXAXAX".getBytes("UTF-8"));
    bb.put("AXAXAXAX".getBytes("UTF-8"), 1, 1);

    // Act and Assert
    assertTrue(new NettyByteBufferBody(bb).byteBuf().isContiguous());
  }

  /**
   * Test {@link NettyByteBufferBody#byteBuf()}.
   *
   * <ul>
   *   <li>Then return {@link UnpooledHeapByteBuf}.
   * </ul>
   *
   * <p>Method under test: {@link NettyByteBufferBody#byteBuf()}
   */
  @Test
  @DisplayName("Test byteBuf(); then return UnpooledHeapByteBuf")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"ByteBuf NettyByteBufferBody.byteBuf()"})
  void testByteBuf_thenReturnUnpooledHeapByteBuf() throws UnsupportedEncodingException {
    // Arrange and Act
    ByteBuf actualByteBufResult =
        new NettyByteBufferBody(ByteBuffer.wrap("AXAXAXAX".getBytes("UTF-8"))).byteBuf();

    // Assert
    assertTrue(actualByteBufResult instanceof UnpooledHeapByteBuf);
    assertTrue(actualByteBufResult.alloc() instanceof UnpooledByteBufAllocator);
    assertEquals(8, actualByteBufResult.capacity());
    assertTrue(actualByteBufResult.hasArray());
    assertFalse(actualByteBufResult.hasMemoryAddress());
    assertTrue(actualByteBufResult.isContiguous());
    assertFalse(actualByteBufResult.isDirect());
    assertFalse(actualByteBufResult.isReadOnly());
    assertTrue(actualByteBufResult.isReadable());
    assertFalse(actualByteBufResult.isWritable());
    assertEquals(8, actualByteBufResult.maxCapacity());
    assertTrue(actualByteBufResult.readBoolean());
    assertEquals(5783896, actualByteBufResult.readMedium());
    assertEquals((short) 65, actualByteBufResult.readUnsignedByte());
    assertEquals(1, actualByteBufResult.refCnt());
    assertNull(actualByteBufResult.unwrap());
    assertEquals(8, actualByteBufResult.writerIndex());
  }
}
