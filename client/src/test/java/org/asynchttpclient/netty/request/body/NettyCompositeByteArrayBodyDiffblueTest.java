package org.asynchttpclient.netty.request.body;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;
import io.netty.buffer.CompositeByteBuf;
import io.netty.buffer.UnpooledByteBufAllocator;
import io.netty.buffer.UnpooledHeapByteBuf;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class NettyCompositeByteArrayBodyDiffblueTest {
  /**
   * Test {@link NettyCompositeByteArrayBody#NettyCompositeByteArrayBody(List)}.
   * <ul>
   *   <li>Given {@code A}.</li>
   *   <li>Then return ContentLength is sixteen.</li>
   * </ul>
   * <p>
   * Method under test:
   * {@link NettyCompositeByteArrayBody#NettyCompositeByteArrayBody(List)}
   */
  @Test
  @DisplayName("Test new NettyCompositeByteArrayBody(List); given 'A'; then return ContentLength is sixteen")
  void testNewNettyCompositeByteArrayBody_givenA_thenReturnContentLengthIsSixteen()
      throws UnsupportedEncodingException {
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

  /**
   * Test {@link NettyCompositeByteArrayBody#NettyCompositeByteArrayBody(List)}.
   * <ul>
   *   <li>Then return ContentLength is eight.</li>
   * </ul>
   * <p>
   * Method under test:
   * {@link NettyCompositeByteArrayBody#NettyCompositeByteArrayBody(List)}
   */
  @Test
  @DisplayName("Test new NettyCompositeByteArrayBody(List); then return ContentLength is eight")
  void testNewNettyCompositeByteArrayBody_thenReturnContentLengthIsEight() throws UnsupportedEncodingException {
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
   * Test {@link NettyCompositeByteArrayBody#NettyCompositeByteArrayBody(List)}.
   * <ul>
   *   <li>When {@link ArrayList#ArrayList()}.</li>
   *   <li>Then return ContentLength is zero.</li>
   * </ul>
   * <p>
   * Method under test:
   * {@link NettyCompositeByteArrayBody#NettyCompositeByteArrayBody(List)}
   */
  @Test
  @DisplayName("Test new NettyCompositeByteArrayBody(List); when ArrayList(); then return ContentLength is zero")
  void testNewNettyCompositeByteArrayBody_whenArrayList_thenReturnContentLengthIsZero() {
    // Arrange and Act
    NettyCompositeByteArrayBody actualNettyCompositeByteArrayBody = new NettyCompositeByteArrayBody(new ArrayList<>());

    // Assert
    assertNull(actualNettyCompositeByteArrayBody.getContentTypeOverride());
    assertEquals(0L, actualNettyCompositeByteArrayBody.getContentLength());
  }

  /**
   * Test {@link NettyCompositeByteArrayBody#getContentLength()}.
   * <p>
   * Method under test: {@link NettyCompositeByteArrayBody#getContentLength()}
   */
  @Test
  @DisplayName("Test getContentLength()")
  void testGetContentLength() {
    // Arrange, Act and Assert
    assertEquals(0L, (new NettyCompositeByteArrayBody(new ArrayList<>())).getContentLength());
  }

  /**
   * Test {@link NettyCompositeByteArrayBody#byteBuf()}.
   * <ul>
   *   <li>Given {@link ArrayList#ArrayList()} add {@code AXAXAXAX} Bytes is
   * {@code UTF-8}.</li>
   *   <li>Then return {@link UnpooledHeapByteBuf}.</li>
   * </ul>
   * <p>
   * Method under test: {@link NettyCompositeByteArrayBody#byteBuf()}
   */
  @Test
  @DisplayName("Test byteBuf(); given ArrayList() add 'AXAXAXAX' Bytes is 'UTF-8'; then return UnpooledHeapByteBuf")
  void testByteBuf_givenArrayListAddAxaxaxaxBytesIsUtf8_thenReturnUnpooledHeapByteBuf()
      throws UnsupportedEncodingException {
    // Arrange
    ArrayList<byte[]> bytes = new ArrayList<>();
    bytes.add("AXAXAXAX".getBytes("UTF-8"));

    // Act
    ByteBuf actualByteBufResult = (new NettyCompositeByteArrayBody(bytes)).byteBuf();

    // Assert
    ByteBufAllocator allocResult = actualByteBufResult.alloc();
    assertTrue(allocResult instanceof UnpooledByteBufAllocator);
    assertTrue(actualByteBufResult instanceof UnpooledHeapByteBuf);
    assertNull(actualByteBufResult.unwrap());
    assertEquals(8, actualByteBufResult.capacity());
    assertEquals(8, actualByteBufResult.maxCapacity());
    assertFalse(actualByteBufResult.hasMemoryAddress());
    assertFalse(actualByteBufResult.isDirect());
    assertFalse(allocResult.isDirectBufferPooled());
    assertTrue(actualByteBufResult.hasArray());
    assertTrue(actualByteBufResult.isContiguous());
  }

  /**
   * Test {@link NettyCompositeByteArrayBody#byteBuf()}.
   * <ul>
   *   <li>Then return array is {@code AXAXAXAX} Bytes is {@code UTF-8}.</li>
   * </ul>
   * <p>
   * Method under test: {@link NettyCompositeByteArrayBody#byteBuf()}
   */
  @Test
  @DisplayName("Test byteBuf(); then return array is 'AXAXAXAX' Bytes is 'UTF-8'")
  void testByteBuf_thenReturnArrayIsAxaxaxaxBytesIsUtf8() throws UnsupportedEncodingException {
    // Arrange
    ArrayList<byte[]> bytes = new ArrayList<>();
    bytes.add(new byte[]{});
    bytes.add("AXAXAXAX".getBytes("UTF-8"));

    // Act
    ByteBuf actualByteBufResult = (new NettyCompositeByteArrayBody(bytes)).byteBuf();

    // Assert
    assertTrue(actualByteBufResult instanceof CompositeByteBuf);
    assertEquals(8, actualByteBufResult.capacity());
    assertTrue(actualByteBufResult.hasArray());
    byte[] expectedArrayResult = "AXAXAXAX".getBytes("UTF-8");
    assertArrayEquals(expectedArrayResult, actualByteBufResult.array());
  }

  /**
   * Test {@link NettyCompositeByteArrayBody#byteBuf()}.
   * <ul>
   *   <li>Then return readUnsignedInt is {@code 1096302936}.</li>
   * </ul>
   * <p>
   * Method under test: {@link NettyCompositeByteArrayBody#byteBuf()}
   */
  @Test
  @DisplayName("Test byteBuf(); then return readUnsignedInt is '1096302936'")
  void testByteBuf_thenReturnReadUnsignedIntIs1096302936() throws UnsupportedEncodingException {
    // Arrange
    ArrayList<byte[]> bytes = new ArrayList<>();
    bytes.add("AXAXAXAX".getBytes("UTF-8"));
    bytes.add("AXAXAXAX".getBytes("UTF-8"));

    // Act
    ByteBuf actualByteBufResult = (new NettyCompositeByteArrayBody(bytes)).byteBuf();

    // Assert
    assertTrue(actualByteBufResult instanceof CompositeByteBuf);
    assertEquals(1096302936L, actualByteBufResult.readUnsignedInt());
    assertEquals(16728, actualByteBufResult.readUnsignedShort());
    assertFalse(actualByteBufResult.hasArray());
    assertEquals(Short.SIZE, actualByteBufResult.capacity());
    assertEquals(Short.SIZE, actualByteBufResult.writerIndex());
  }
}
