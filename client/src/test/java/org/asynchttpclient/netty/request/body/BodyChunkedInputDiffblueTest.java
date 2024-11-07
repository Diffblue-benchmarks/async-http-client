package org.asynchttpclient.netty.request.body;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import io.netty.buffer.AdaptiveByteBufAllocator;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;
import io.netty.channel.ChannelHandlerContext;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.LinkedList;
import org.asynchttpclient.request.body.Body;
import org.asynchttpclient.request.body.generator.PushBody;
import org.asynchttpclient.request.body.multipart.ByteArrayPart;
import org.asynchttpclient.request.body.multipart.MultipartBody;
import org.asynchttpclient.request.body.multipart.Part;
import org.asynchttpclient.request.body.multipart.part.ByteArrayMultipartPart;
import org.asynchttpclient.request.body.multipart.part.MultipartPart;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BodyChunkedInputDiffblueTest {
  /**
   * Test {@link BodyChunkedInput#BodyChunkedInput(Body)}.
   * <ul>
   *   <li>Then return length is minus one.</li>
   * </ul>
   * <p>
   * Method under test: {@link BodyChunkedInput#BodyChunkedInput(Body)}
   */
  @Test
  @DisplayName("Test new BodyChunkedInput(Body); then return length is minus one")
  void testNewBodyChunkedInput_thenReturnLengthIsMinusOne() {
    // Arrange and Act
    BodyChunkedInput actualBodyChunkedInput = new BodyChunkedInput(new PushBody(new LinkedList<>()));

    // Assert
    assertEquals(-1L, actualBodyChunkedInput.length());
    assertEquals(0L, actualBodyChunkedInput.progress());
    assertFalse(actualBodyChunkedInput.isEndOfInput());
  }

  /**
   * Test {@link BodyChunkedInput#BodyChunkedInput(Body)}.
   * <ul>
   *   <li>Then return length is one hundred thirty-two.</li>
   * </ul>
   * <p>
   * Method under test: {@link BodyChunkedInput#BodyChunkedInput(Body)}
   */
  @Test
  @DisplayName("Test new BodyChunkedInput(Body); then return length is one hundred thirty-two")
  void testNewBodyChunkedInput_thenReturnLengthIsOneHundredThirtyTwo() throws UnsupportedEncodingException {
    // Arrange
    ArrayList<MultipartPart<? extends Part>> parts = new ArrayList<>();
    ByteArrayPart part = new ByteArrayPart("https://example.org/example", "AXAXAXAX".getBytes("UTF-8"));

    parts.add(new ByteArrayMultipartPart(part, "AXAXAXAX".getBytes("UTF-8")));

    // Act
    BodyChunkedInput actualBodyChunkedInput = new BodyChunkedInput(
        new MultipartBody(parts, "https://example.org/example", new byte[]{'A', -1, 'A', -1, 'A', -1, 'A', -1}));

    // Assert
    assertEquals(0L, actualBodyChunkedInput.progress());
    assertEquals(132L, actualBodyChunkedInput.length());
    assertFalse(actualBodyChunkedInput.isEndOfInput());
  }

  /**
   * Test {@link BodyChunkedInput#readChunk(ByteBufAllocator)} with {@code alloc}.
   * <p>
   * Method under test: {@link BodyChunkedInput#readChunk(ByteBufAllocator)}
   */
  @Test
  @DisplayName("Test readChunk(ByteBufAllocator) with 'alloc'")
  void testReadChunkWithAlloc() throws Exception {
    // Arrange
    ArrayList<MultipartPart<? extends Part>> parts = new ArrayList<>();
    parts.add(new ByteArrayMultipartPart(
        new ByteArrayPart("https://example.org/example", new byte[]{'A', 1, 'A', 1, 'A', 1, 'A', 1}),
        new byte[]{'A', 1, 'A', 1, 'A', 1, 'A', 1}));
    BodyChunkedInput bodyChunkedInput = new BodyChunkedInput(
        new MultipartBody(parts, "https://example.org/example", new byte[]{'A', 1, 'A', 1, 'A', 1, 'A', 1}));

    // Act
    ByteBuf actualReadChunkResult = bodyChunkedInput.readChunk(new AdaptiveByteBufAllocator());

    // Assert
    assertEquals(132L, bodyChunkedInput.progress());
    assertTrue(actualReadChunkResult.isContiguous());
  }

  /**
   * Test {@link BodyChunkedInput#readChunk(ByteBufAllocator)} with {@code alloc}.
   * <ul>
   *   <li>Given {@link PushBody#PushBody(Queue)} with queue is
   * {@link LinkedList#LinkedList()}.</li>
   *   <li>Then return {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link BodyChunkedInput#readChunk(ByteBufAllocator)}
   */
  @Test
  @DisplayName("Test readChunk(ByteBufAllocator) with 'alloc'; given PushBody(Queue) with queue is LinkedList(); then return 'null'")
  void testReadChunkWithAlloc_givenPushBodyWithQueueIsLinkedList_thenReturnNull() throws Exception {
    // Arrange
    BodyChunkedInput bodyChunkedInput = new BodyChunkedInput(new PushBody(new LinkedList<>()));

    // Act and Assert
    assertNull(bodyChunkedInput.readChunk(new AdaptiveByteBufAllocator()));
    assertEquals(0L, bodyChunkedInput.progress());
  }

  /**
   * Test {@link BodyChunkedInput#readChunk(ChannelHandlerContext)} with
   * {@code ctx}.
   * <p>
   * Method under test: {@link BodyChunkedInput#readChunk(ChannelHandlerContext)}
   */
  @Test
  @DisplayName("Test readChunk(ChannelHandlerContext) with 'ctx'")
  void testReadChunkWithCtx() throws Exception {
    // Arrange
    ArrayList<MultipartPart<? extends Part>> parts = new ArrayList<>();
    parts.add(new ByteArrayMultipartPart(
        new ByteArrayPart("https://example.org/example", new byte[]{'A', 1, 'A', 1, 'A', 1, 'A', 1}),
        new byte[]{'A', 1, 'A', 1, 'A', 1, 'A', 1}));
    BodyChunkedInput bodyChunkedInput = new BodyChunkedInput(
        new MultipartBody(parts, "https://example.org/example", new byte[]{'A', 1, 'A', 1, 'A', 1, 'A', 1}));
    ChannelHandlerContext ctx = mock(ChannelHandlerContext.class);
    when(ctx.alloc()).thenReturn(new AdaptiveByteBufAllocator());

    // Act
    ByteBuf actualReadChunkResult = bodyChunkedInput.readChunk(ctx);

    // Assert
    verify(ctx).alloc();
    assertEquals(132L, bodyChunkedInput.progress());
    assertTrue(actualReadChunkResult.isContiguous());
  }

  /**
   * Test {@link BodyChunkedInput#readChunk(ChannelHandlerContext)} with
   * {@code ctx}.
   * <ul>
   *   <li>Given {@link PushBody#PushBody(Queue)} with queue is
   * {@link LinkedList#LinkedList()}.</li>
   *   <li>Then return {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link BodyChunkedInput#readChunk(ChannelHandlerContext)}
   */
  @Test
  @DisplayName("Test readChunk(ChannelHandlerContext) with 'ctx'; given PushBody(Queue) with queue is LinkedList(); then return 'null'")
  void testReadChunkWithCtx_givenPushBodyWithQueueIsLinkedList_thenReturnNull() throws Exception {
    // Arrange
    BodyChunkedInput bodyChunkedInput = new BodyChunkedInput(new PushBody(new LinkedList<>()));
    ChannelHandlerContext ctx = mock(ChannelHandlerContext.class);
    when(ctx.alloc()).thenReturn(new AdaptiveByteBufAllocator());

    // Act
    ByteBuf actualReadChunkResult = bodyChunkedInput.readChunk(ctx);

    // Assert
    verify(ctx).alloc();
    assertNull(actualReadChunkResult);
    assertEquals(0L, bodyChunkedInput.progress());
  }

  /**
   * Test {@link BodyChunkedInput#readChunk(ChannelHandlerContext)} with
   * {@code ctx}.
   * <ul>
   *   <li>Then throw {@link IllegalStateException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link BodyChunkedInput#readChunk(ChannelHandlerContext)}
   */
  @Test
  @DisplayName("Test readChunk(ChannelHandlerContext) with 'ctx'; then throw IllegalStateException")
  void testReadChunkWithCtx_thenThrowIllegalStateException() throws Exception {
    // Arrange
    BodyChunkedInput bodyChunkedInput = new BodyChunkedInput(new PushBody(new LinkedList<>()));
    ChannelHandlerContext ctx = mock(ChannelHandlerContext.class);
    when(ctx.alloc()).thenThrow(new IllegalStateException("foo"));

    // Act and Assert
    assertThrows(IllegalStateException.class, () -> bodyChunkedInput.readChunk(ctx));
    verify(ctx).alloc();
  }

  /**
   * Test getters and setters.
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link BodyChunkedInput#isEndOfInput()}
   *   <li>{@link BodyChunkedInput#progress()}
   * </ul>
   */
  @Test
  @DisplayName("Test getters and setters")
  void testGettersAndSetters() {
    // Arrange
    BodyChunkedInput bodyChunkedInput = new BodyChunkedInput(new PushBody(new LinkedList<>()));

    // Act
    boolean actualIsEndOfInputResult = bodyChunkedInput.isEndOfInput();

    // Assert
    assertEquals(0L, bodyChunkedInput.progress());
    assertFalse(actualIsEndOfInputResult);
  }

  /**
   * Test {@link BodyChunkedInput#length()}.
   * <p>
   * Method under test: {@link BodyChunkedInput#length()}
   */
  @Test
  @DisplayName("Test length()")
  void testLength() {
    // Arrange, Act and Assert
    assertEquals(-1L, (new BodyChunkedInput(new PushBody(new LinkedList<>()))).length());
  }
}
