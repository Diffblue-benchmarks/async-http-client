package org.asynchttpclient.netty.request.body;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.UnpooledByteBufAllocator;
import io.netty.buffer.UnpooledHeapByteBuf;
import java.io.UnsupportedEncodingException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

class NettyByteArrayBodyDiffblueTest {
  /**
   * Test {@link NettyByteArrayBody#NettyByteArrayBody(byte[])}.
   * <p>
   * Method under test: {@link NettyByteArrayBody#NettyByteArrayBody(byte[])}
   */
  @Test
  @DisplayName("Test new NettyByteArrayBody(byte[])")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void NettyByteArrayBody.<init>(byte[])"})
  void testNewNettyByteArrayBody() throws UnsupportedEncodingException {
    // Arrange and Act
    NettyByteArrayBody actualNettyByteArrayBody = new NettyByteArrayBody("AXAXAXAX".getBytes("UTF-8"));

    // Assert
    assertNull(actualNettyByteArrayBody.getContentTypeOverride());
    assertEquals(8L, actualNettyByteArrayBody.getContentLength());
  }

  /**
   * Test {@link NettyByteArrayBody#getContentLength()}.
   * <p>
   * Method under test: {@link NettyByteArrayBody#getContentLength()}
   */
  @Test
  @DisplayName("Test getContentLength()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"long NettyByteArrayBody.getContentLength()"})
  void testGetContentLength() throws UnsupportedEncodingException {
    // Arrange, Act and Assert
    assertEquals(8L, (new NettyByteArrayBody("AXAXAXAX".getBytes("UTF-8"))).getContentLength());
  }

  /**
   * Test {@link NettyByteArrayBody#byteBuf()}.
   * <ul>
   *   <li>Then return {@link UnpooledHeapByteBuf}.</li>
   * </ul>
   * <p>
   * Method under test: {@link NettyByteArrayBody#byteBuf()}
   */
  @Test
  @DisplayName("Test byteBuf(); then return UnpooledHeapByteBuf")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"ByteBuf NettyByteArrayBody.byteBuf()"})
  void testByteBuf_thenReturnUnpooledHeapByteBuf() throws UnsupportedEncodingException {
    // Arrange and Act
    ByteBuf actualByteBufResult = (new NettyByteArrayBody("AXAXAXAX".getBytes("UTF-8"))).byteBuf();

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
