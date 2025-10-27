package org.asynchttpclient.netty.request.body;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;
import io.netty.buffer.CompositeByteBuf;
import io.netty.buffer.UnpooledByteBufAllocator;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;

class NettyCompositeByteArrayBodyDiffblueTest {
  /**
   * Method under test: {@link NettyCompositeByteArrayBody#getContentLength()}
   */
  @Test
  void testGetContentLength() {
    // Arrange, Act and Assert
    assertEquals(0L, (new NettyCompositeByteArrayBody(new ArrayList<>())).getContentLength());
  }

  /**
   * Method under test: {@link NettyCompositeByteArrayBody#byteBuf()}
   */
  @Test
  void testByteBuf() throws UnsupportedEncodingException {
    // Arrange
    ArrayList<byte[]> bytes = new ArrayList<>();
    bytes.add("AXAXAXAX".getBytes("UTF-8"));
    bytes.add("AXAXAXAX".getBytes("UTF-8"));

    // Act
    ByteBuf actualByteBufResult = (new NettyCompositeByteArrayBody(bytes)).byteBuf();

    // Assert
    assertTrue(actualByteBufResult instanceof CompositeByteBuf);
    ByteBufAllocator allocResult = actualByteBufResult.alloc();
    assertTrue(allocResult instanceof UnpooledByteBufAllocator);
    assertNull(actualByteBufResult.unwrap());
    assertEquals(1, actualByteBufResult.refCnt());
    assertEquals(2, ((CompositeByteBuf) actualByteBufResult).maxNumComponents());
    assertFalse(actualByteBufResult.hasArray());
    assertFalse(actualByteBufResult.hasMemoryAddress());
    assertFalse(actualByteBufResult.isContiguous());
    assertFalse(actualByteBufResult.isDirect());
    assertFalse(actualByteBufResult.isReadOnly());
    assertFalse(actualByteBufResult.isWritable());
    assertFalse(allocResult.isDirectBufferPooled());
    assertTrue(actualByteBufResult.isReadable());
    assertTrue(actualByteBufResult.readBoolean());
    assertEquals(Integer.MAX_VALUE, actualByteBufResult.maxCapacity());
    assertEquals(Short.SIZE, actualByteBufResult.capacity());
    assertEquals(Short.SIZE, actualByteBufResult.writerIndex());
  }

  /**
   * Method under test:
   * {@link NettyCompositeByteArrayBody#NettyCompositeByteArrayBody(List)}
   */
  @Test
  void testNewNettyCompositeByteArrayBody() {
    // Arrange and Act
    NettyCompositeByteArrayBody actualNettyCompositeByteArrayBody = new NettyCompositeByteArrayBody(new ArrayList<>());

    // Assert
    assertNull(actualNettyCompositeByteArrayBody.getContentTypeOverride());
    assertEquals(0L, actualNettyCompositeByteArrayBody.getContentLength());
  }

  /**
   * Method under test:
   * {@link NettyCompositeByteArrayBody#NettyCompositeByteArrayBody(List)}
   */
  @Test
  void testNewNettyCompositeByteArrayBody2() throws UnsupportedEncodingException {
    // Arrange
    ArrayList<byte[]> bytes = new ArrayList<>();
    bytes.add("AXAXAXAX".getBytes("UTF-8"));

    // Act
    NettyCompositeByteArrayBody actualNettyCompositeByteArrayBody = new NettyCompositeByteArrayBody(bytes);

    // Assert
    assertNull(actualNettyCompositeByteArrayBody.getContentTypeOverride());
    assertEquals(8L, actualNettyCompositeByteArrayBody.getContentLength());
  }

  /**
   * Method under test:
   * {@link NettyCompositeByteArrayBody#NettyCompositeByteArrayBody(List)}
   */
  @Test
  void testNewNettyCompositeByteArrayBody3() throws UnsupportedEncodingException {
    // Arrange
    ArrayList<byte[]> bytes = new ArrayList<>();
    bytes.add(new byte[]{'A', 1, 'A', 1, 'A', 1, 'A', 1});
    bytes.add("AXAXAXAX".getBytes("UTF-8"));

    // Act
    NettyCompositeByteArrayBody actualNettyCompositeByteArrayBody = new NettyCompositeByteArrayBody(bytes);

    // Assert
    assertNull(actualNettyCompositeByteArrayBody.getContentTypeOverride());
    assertEquals(16L, actualNettyCompositeByteArrayBody.getContentLength());
  }
}
