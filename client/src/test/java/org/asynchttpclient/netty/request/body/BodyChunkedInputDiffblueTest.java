package org.asynchttpclient.netty.request.body;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.doNothing;
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
import org.junit.jupiter.api.Test;

class BodyChunkedInputDiffblueTest {
  /**
   * Method under test: {@link BodyChunkedInput#readChunk(ByteBufAllocator)}
   */
  @Test
  void testReadChunk() throws Exception {
    // Arrange
    BodyChunkedInput bodyChunkedInput = new BodyChunkedInput(new PushBody(new LinkedList<>()));

    // Act and Assert
    assertNull(bodyChunkedInput.readChunk(new AdaptiveByteBufAllocator()));
  }

  /**
   * Method under test: {@link BodyChunkedInput#readChunk(ByteBufAllocator)}
   */
  @Test
  void testReadChunk2() throws Exception {
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
   * Method under test: {@link BodyChunkedInput#readChunk(ChannelHandlerContext)}
   */
  @Test
  void testReadChunk3() throws Exception {
    // Arrange
    BodyChunkedInput bodyChunkedInput = new BodyChunkedInput(new PushBody(new LinkedList<>()));
    ChannelHandlerContext ctx = mock(ChannelHandlerContext.class);
    when(ctx.alloc()).thenReturn(new AdaptiveByteBufAllocator());

    // Act
    ByteBuf actualReadChunkResult = bodyChunkedInput.readChunk(ctx);

    // Assert
    verify(ctx).alloc();
    assertNull(actualReadChunkResult);
  }

  /**
   * Method under test: {@link BodyChunkedInput#readChunk(ChannelHandlerContext)}
   */
  @Test
  void testReadChunk4() throws Exception {
    // Arrange
    BodyChunkedInput bodyChunkedInput = new BodyChunkedInput(new PushBody(new LinkedList<>()));
    ChannelHandlerContext ctx = mock(ChannelHandlerContext.class);
    when(ctx.alloc()).thenThrow(new IllegalStateException("foo"));

    // Act and Assert
    assertThrows(IllegalStateException.class, () -> bodyChunkedInput.readChunk(ctx));
    verify(ctx).alloc();
  }

  /**
   * Method under test: {@link BodyChunkedInput#readChunk(ChannelHandlerContext)}
   */
  @Test
  void testReadChunk5() throws Exception {
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
   * Method under test: {@link BodyChunkedInput#close()}
   */
  @Test
  void testClose() throws Exception {
    // Arrange
    MultipartBody body = mock(MultipartBody.class);
    when(body.getContentLength()).thenReturn(3L);
    doNothing().when(body).close();

    // Act
    (new BodyChunkedInput(body)).close();

    // Assert
    verify(body).close();
    verify(body).getContentLength();
  }

  /**
   * Method under test: {@link BodyChunkedInput#length()}
   */
  @Test
  void testLength() {
    // Arrange, Act and Assert
    assertEquals(-1L, (new BodyChunkedInput(new PushBody(new LinkedList<>()))).length());
  }

  /**
   * Methods under test:
   * <ul>
   *   <li>{@link BodyChunkedInput#isEndOfInput()}
   *   <li>{@link BodyChunkedInput#progress()}
   * </ul>
   */
  @Test
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
   * Method under test: {@link BodyChunkedInput#BodyChunkedInput(Body)}
   */
  @Test
  void testNewBodyChunkedInput() {
    // Arrange and Act
    BodyChunkedInput actualBodyChunkedInput = new BodyChunkedInput(new PushBody(new LinkedList<>()));

    // Assert
    assertEquals(-1L, actualBodyChunkedInput.length());
    assertEquals(0L, actualBodyChunkedInput.progress());
    assertFalse(actualBodyChunkedInput.isEndOfInput());
  }

  /**
   * Method under test: {@link BodyChunkedInput#BodyChunkedInput(Body)}
   */
  @Test
  void testNewBodyChunkedInput2() throws UnsupportedEncodingException {
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
}
