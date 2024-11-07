package org.asynchttpclient.netty.ws;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.anyBoolean;
import static org.mockito.Mockito.anyInt;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import io.netty.buffer.AdaptiveByteBufAllocator;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.DuplicatedByteBuf;
import io.netty.buffer.EmptyByteBuf;
import io.netty.buffer.ReadOnlyByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.buffer.UnpooledDirectByteBuf;
import io.netty.buffer.UnpooledHeapByteBuf;
import io.netty.channel.Channel;
import io.netty.channel.DefaultChannelPromise;
import io.netty.channel.embedded.EmbeddedChannel;
import io.netty.handler.codec.http.DefaultHttpHeaders;
import io.netty.handler.codec.http.HttpHeaders;
import io.netty.handler.codec.http.websocketx.BinaryWebSocketFrame;
import io.netty.handler.codec.http.websocketx.CloseWebSocketFrame;
import io.netty.handler.codec.http.websocketx.ContinuationWebSocketFrame;
import io.netty.handler.codec.http.websocketx.PingWebSocketFrame;
import io.netty.handler.codec.http.websocketx.PongWebSocketFrame;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import io.netty.handler.codec.http.websocketx.WebSocketFrame;
import io.netty.util.concurrent.Future;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;
import org.asynchttpclient.AsyncHttpClientConfig;
import org.asynchttpclient.exception.ChannelClosedException;
import org.asynchttpclient.netty.channel.ChannelManager;
import org.asynchttpclient.netty.handler.HttpHandler;
import org.asynchttpclient.ws.WebSocket;
import org.asynchttpclient.ws.WebSocketListener;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

class NettyWebSocketDiffblueTest {
  /**
   * Test getters and setters.
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link NettyWebSocket#NettyWebSocket(Channel, HttpHeaders)}
   *   <li>{@link NettyWebSocket#toString()}
   *   <li>{@link NettyWebSocket#getUpgradeHeaders()}
   *   <li>{@link NettyWebSocket#isReady()}
   * </ul>
   */
  @Test
  @DisplayName("Test getters and setters")
  void testGettersAndSetters() {
    // Arrange
    EmbeddedChannel channel = new EmbeddedChannel();
    DefaultHttpHeaders upgradeHeaders = new DefaultHttpHeaders();

    // Act
    NettyWebSocket actualNettyWebSocket = new NettyWebSocket(channel, upgradeHeaders);
    String actualToStringResult = actualNettyWebSocket.toString();
    HttpHeaders actualUpgradeHeaders = actualNettyWebSocket.getUpgradeHeaders();

    // Assert
    assertEquals("NettyWebSocket{channel=[id: 0xembedded, L:embedded - R:embedded]}", actualToStringResult);
    assertFalse(actualNettyWebSocket.isReady());
    assertSame(upgradeHeaders, actualUpgradeHeaders);
  }

  /**
   * Test {@link NettyWebSocket#sendTextFrame(ByteBuf, boolean, int)} with
   * {@code ByteBuf}, {@code boolean}, {@code int}.
   * <p>
   * Method under test:
   * {@link NettyWebSocket#sendTextFrame(ByteBuf, boolean, int)}
   */
  @Test
  @DisplayName("Test sendTextFrame(ByteBuf, boolean, int) with 'ByteBuf', 'boolean', 'int'")
  void testSendTextFrameWithByteBufBooleanInt() throws InterruptedException, ExecutionException {
    // Arrange
    EmbeddedChannel channel = new EmbeddedChannel();
    NettyWebSocket nettyWebSocket = new NettyWebSocket(channel, new DefaultHttpHeaders());

    // Act
    Future<Void> actualSendTextFrameResult = nettyWebSocket
        .sendTextFrame(new DuplicatedByteBuf(Unpooled.compositeBuffer(3)), true, 1);

    // Assert
    assertTrue(actualSendTextFrameResult instanceof DefaultChannelPromise);
    assertNull(actualSendTextFrameResult.get());
    assertTrue(actualSendTextFrameResult.isDone());
  }

  /**
   * Test {@link NettyWebSocket#sendTextFrame(ByteBuf, boolean, int)} with
   * {@code ByteBuf}, {@code boolean}, {@code int}.
   * <p>
   * Method under test:
   * {@link NettyWebSocket#sendTextFrame(ByteBuf, boolean, int)}
   */
  @Test
  @DisplayName("Test sendTextFrame(ByteBuf, boolean, int) with 'ByteBuf', 'boolean', 'int'")
  void testSendTextFrameWithByteBufBooleanInt2() throws InterruptedException, ExecutionException {
    // Arrange
    EmbeddedChannel channel = new EmbeddedChannel();
    NettyWebSocket nettyWebSocket = new NettyWebSocket(channel, new DefaultHttpHeaders());

    // Act
    Future<Void> actualSendTextFrameResult = nettyWebSocket.sendTextFrame(new DuplicatedByteBuf(
        new ReadOnlyByteBuf(new DuplicatedByteBuf(new EmptyByteBuf(new AdaptiveByteBufAllocator())))), true, 1);

    // Assert
    assertTrue(actualSendTextFrameResult instanceof DefaultChannelPromise);
    assertNull(actualSendTextFrameResult.get());
    assertTrue(actualSendTextFrameResult.isDone());
  }

  /**
   * Test {@link NettyWebSocket#sendTextFrame(ByteBuf, boolean, int)} with
   * {@code ByteBuf}, {@code boolean}, {@code int}.
   * <ul>
   *   <li>Given {@code true}.</li>
   *   <li>Then calls {@link ByteBuf#capacity()}.</li>
   * </ul>
   * <p>
   * Method under test:
   * {@link NettyWebSocket#sendTextFrame(ByteBuf, boolean, int)}
   */
  @Test
  @DisplayName("Test sendTextFrame(ByteBuf, boolean, int) with 'ByteBuf', 'boolean', 'int'; given 'true'; then calls capacity()")
  void testSendTextFrameWithByteBufBooleanInt_givenTrue_thenCallsCapacity() {
    // Arrange
    EmbeddedChannel channel = new EmbeddedChannel();
    NettyWebSocket nettyWebSocket = new NettyWebSocket(channel, new DefaultHttpHeaders());
    ByteBuf buffer = mock(ByteBuf.class);
    when(buffer.release()).thenReturn(true);
    when(buffer.capacity()).thenReturn(3);
    when(buffer.maxCapacity()).thenReturn(3);
    when(buffer.readerIndex()).thenReturn(1);
    when(buffer.writerIndex()).thenReturn(1);
    when(buffer.refCnt()).thenReturn(1);
    when(buffer.touch(Mockito.<Object>any()))
        .thenReturn(new DuplicatedByteBuf(new EmptyByteBuf(new AdaptiveByteBufAllocator())));

    // Act
    Future<Void> actualSendTextFrameResult = nettyWebSocket
        .sendTextFrame(new DuplicatedByteBuf(new ReadOnlyByteBuf(new DuplicatedByteBuf(buffer))), true, 1);

    // Assert
    verify(buffer, atLeast(1)).capacity();
    verify(buffer).maxCapacity();
    verify(buffer).readerIndex();
    verify(buffer).touch(isA(Object.class));
    verify(buffer).writerIndex();
    verify(buffer).refCnt();
    verify(buffer).release();
    assertTrue(actualSendTextFrameResult instanceof DefaultChannelPromise);
    assertTrue(actualSendTextFrameResult.isDone());
  }

  /**
   * Test {@link NettyWebSocket#sendTextFrame(ByteBuf, boolean, int)} with
   * {@code ByteBuf}, {@code boolean}, {@code int}.
   * <ul>
   *   <li>Then return {@link java.util.concurrent.Future#get()} is
   * {@code null}.</li>
   * </ul>
   * <p>
   * Method under test:
   * {@link NettyWebSocket#sendTextFrame(ByteBuf, boolean, int)}
   */
  @Test
  @DisplayName("Test sendTextFrame(ByteBuf, boolean, int) with 'ByteBuf', 'boolean', 'int'; then return get() is 'null'")
  void testSendTextFrameWithByteBufBooleanInt_thenReturnGetIsNull() throws InterruptedException, ExecutionException {
    // Arrange
    EmbeddedChannel channel = new EmbeddedChannel();
    NettyWebSocket nettyWebSocket = new NettyWebSocket(channel, new DefaultHttpHeaders());

    // Act
    io.netty.util.concurrent.Future<Void> actualSendTextFrameResult = nettyWebSocket
        .sendTextFrame(new DuplicatedByteBuf(new EmptyByteBuf(new AdaptiveByteBufAllocator())), true, 1);

    // Assert
    assertTrue(actualSendTextFrameResult instanceof DefaultChannelPromise);
    assertNull(actualSendTextFrameResult.get());
    assertTrue(actualSendTextFrameResult.isDone());
  }

  /**
   * Test {@link NettyWebSocket#sendTextFrame(String, boolean, int)} with
   * {@code String}, {@code boolean}, {@code int}.
   * <ul>
   *   <li>Then return {@link DefaultChannelPromise}.</li>
   * </ul>
   * <p>
   * Method under test: {@link NettyWebSocket#sendTextFrame(String, boolean, int)}
   */
  @Test
  @DisplayName("Test sendTextFrame(String, boolean, int) with 'String', 'boolean', 'int'; then return DefaultChannelPromise")
  void testSendTextFrameWithStringBooleanInt_thenReturnDefaultChannelPromise()
      throws InterruptedException, ExecutionException {
    // Arrange
    EmbeddedChannel channel = new EmbeddedChannel();

    // Act
    Future<Void> actualSendTextFrameResult = (new NettyWebSocket(channel, new DefaultHttpHeaders()))
        .sendTextFrame("https://example.org/example", true, 1);

    // Assert
    assertTrue(actualSendTextFrameResult instanceof DefaultChannelPromise);
    assertNull(actualSendTextFrameResult.get());
    assertTrue(actualSendTextFrameResult.isDone());
  }

  /**
   * Test {@link NettyWebSocket#sendTextFrame(String, boolean, int)} with
   * {@code String}, {@code boolean}, {@code int}.
   * <ul>
   *   <li>When empty string.</li>
   * </ul>
   * <p>
   * Method under test: {@link NettyWebSocket#sendTextFrame(String, boolean, int)}
   */
  @Test
  @DisplayName("Test sendTextFrame(String, boolean, int) with 'String', 'boolean', 'int'; when empty string")
  void testSendTextFrameWithStringBooleanInt_whenEmptyString() throws InterruptedException, ExecutionException {
    // Arrange
    EmbeddedChannel channel = new EmbeddedChannel();

    // Act
    Future<Void> actualSendTextFrameResult = (new NettyWebSocket(channel, new DefaultHttpHeaders())).sendTextFrame("",
        true, 1);

    // Assert
    assertTrue(actualSendTextFrameResult instanceof DefaultChannelPromise);
    assertNull(actualSendTextFrameResult.get());
    assertTrue(actualSendTextFrameResult.isDone());
  }

  /**
   * Test {@link NettyWebSocket#sendTextFrame(String)} with {@code String}.
   * <ul>
   *   <li>Then return {@link DefaultChannelPromise}.</li>
   * </ul>
   * <p>
   * Method under test: {@link NettyWebSocket#sendTextFrame(String)}
   */
  @Test
  @DisplayName("Test sendTextFrame(String) with 'String'; then return DefaultChannelPromise")
  void testSendTextFrameWithString_thenReturnDefaultChannelPromise() throws InterruptedException, ExecutionException {
    // Arrange
    EmbeddedChannel channel = new EmbeddedChannel();

    // Act
    Future<Void> actualSendTextFrameResult = (new NettyWebSocket(channel, new DefaultHttpHeaders()))
        .sendTextFrame("https://example.org/example");

    // Assert
    assertTrue(actualSendTextFrameResult instanceof DefaultChannelPromise);
    assertNull(actualSendTextFrameResult.get());
    assertTrue(actualSendTextFrameResult.isDone());
  }

  /**
   * Test {@link NettyWebSocket#sendTextFrame(String)} with {@code String}.
   * <ul>
   *   <li>When empty string.</li>
   *   <li>Then return {@link DefaultChannelPromise}.</li>
   * </ul>
   * <p>
   * Method under test: {@link NettyWebSocket#sendTextFrame(String)}
   */
  @Test
  @DisplayName("Test sendTextFrame(String) with 'String'; when empty string; then return DefaultChannelPromise")
  void testSendTextFrameWithString_whenEmptyString_thenReturnDefaultChannelPromise()
      throws InterruptedException, ExecutionException {
    // Arrange
    EmbeddedChannel channel = new EmbeddedChannel();

    // Act
    Future<Void> actualSendTextFrameResult = (new NettyWebSocket(channel, new DefaultHttpHeaders())).sendTextFrame("");

    // Assert
    assertTrue(actualSendTextFrameResult instanceof DefaultChannelPromise);
    assertNull(actualSendTextFrameResult.get());
    assertTrue(actualSendTextFrameResult.isDone());
  }

  /**
   * Test {@link NettyWebSocket#sendBinaryFrame(byte[], boolean, int)} with
   * {@code byte[]}, {@code boolean}, {@code int}.
   * <ul>
   *   <li>Then return {@link DefaultChannelPromise}.</li>
   * </ul>
   * <p>
   * Method under test:
   * {@link NettyWebSocket#sendBinaryFrame(byte[], boolean, int)}
   */
  @Test
  @DisplayName("Test sendBinaryFrame(byte[], boolean, int) with 'byte[]', 'boolean', 'int'; then return DefaultChannelPromise")
  void testSendBinaryFrameWithByteBooleanInt_thenReturnDefaultChannelPromise()
      throws UnsupportedEncodingException, InterruptedException, ExecutionException {
    // Arrange
    EmbeddedChannel channel = new EmbeddedChannel();
    NettyWebSocket nettyWebSocket = new NettyWebSocket(channel, new DefaultHttpHeaders());

    // Act
    Future<Void> actualSendBinaryFrameResult = nettyWebSocket.sendBinaryFrame("AXAXAXAX".getBytes("UTF-8"), true, 1);

    // Assert
    assertTrue(actualSendBinaryFrameResult instanceof DefaultChannelPromise);
    assertNull(actualSendBinaryFrameResult.get());
    assertTrue(actualSendBinaryFrameResult.isDone());
  }

  /**
   * Test {@link NettyWebSocket#sendBinaryFrame(byte[], boolean, int)} with
   * {@code byte[]}, {@code boolean}, {@code int}.
   * <ul>
   *   <li>When empty array of {@code byte}.</li>
   * </ul>
   * <p>
   * Method under test:
   * {@link NettyWebSocket#sendBinaryFrame(byte[], boolean, int)}
   */
  @Test
  @DisplayName("Test sendBinaryFrame(byte[], boolean, int) with 'byte[]', 'boolean', 'int'; when empty array of byte")
  void testSendBinaryFrameWithByteBooleanInt_whenEmptyArrayOfByte() throws InterruptedException, ExecutionException {
    // Arrange
    EmbeddedChannel channel = new EmbeddedChannel();

    // Act
    Future<Void> actualSendBinaryFrameResult = (new NettyWebSocket(channel, new DefaultHttpHeaders()))
        .sendBinaryFrame(new byte[]{}, true, 1);

    // Assert
    assertTrue(actualSendBinaryFrameResult instanceof DefaultChannelPromise);
    assertNull(actualSendBinaryFrameResult.get());
    assertTrue(actualSendBinaryFrameResult.isDone());
  }

  /**
   * Test {@link NettyWebSocket#sendBinaryFrame(ByteBuf, boolean, int)} with
   * {@code ByteBuf}, {@code boolean}, {@code int}.
   * <p>
   * Method under test:
   * {@link NettyWebSocket#sendBinaryFrame(ByteBuf, boolean, int)}
   */
  @Test
  @DisplayName("Test sendBinaryFrame(ByteBuf, boolean, int) with 'ByteBuf', 'boolean', 'int'")
  void testSendBinaryFrameWithByteBufBooleanInt() throws InterruptedException, ExecutionException {
    // Arrange
    EmbeddedChannel channel = new EmbeddedChannel();
    NettyWebSocket nettyWebSocket = new NettyWebSocket(channel, new DefaultHttpHeaders());

    // Act
    Future<Void> actualSendBinaryFrameResult = nettyWebSocket
        .sendBinaryFrame(new DuplicatedByteBuf(Unpooled.compositeBuffer(3)), true, 1);

    // Assert
    assertTrue(actualSendBinaryFrameResult instanceof DefaultChannelPromise);
    assertNull(actualSendBinaryFrameResult.get());
    assertTrue(actualSendBinaryFrameResult.isDone());
  }

  /**
   * Test {@link NettyWebSocket#sendBinaryFrame(ByteBuf, boolean, int)} with
   * {@code ByteBuf}, {@code boolean}, {@code int}.
   * <p>
   * Method under test:
   * {@link NettyWebSocket#sendBinaryFrame(ByteBuf, boolean, int)}
   */
  @Test
  @DisplayName("Test sendBinaryFrame(ByteBuf, boolean, int) with 'ByteBuf', 'boolean', 'int'")
  void testSendBinaryFrameWithByteBufBooleanInt2() throws InterruptedException, ExecutionException {
    // Arrange
    EmbeddedChannel channel = new EmbeddedChannel();
    NettyWebSocket nettyWebSocket = new NettyWebSocket(channel, new DefaultHttpHeaders());

    // Act
    Future<Void> actualSendBinaryFrameResult = nettyWebSocket.sendBinaryFrame(new DuplicatedByteBuf(
        new ReadOnlyByteBuf(new DuplicatedByteBuf(new EmptyByteBuf(new AdaptiveByteBufAllocator())))), true, 1);

    // Assert
    assertTrue(actualSendBinaryFrameResult instanceof DefaultChannelPromise);
    assertNull(actualSendBinaryFrameResult.get());
    assertTrue(actualSendBinaryFrameResult.isDone());
  }

  /**
   * Test {@link NettyWebSocket#sendBinaryFrame(ByteBuf, boolean, int)} with
   * {@code ByteBuf}, {@code boolean}, {@code int}.
   * <ul>
   *   <li>Given {@code true}.</li>
   *   <li>Then calls {@link ByteBuf#capacity()}.</li>
   * </ul>
   * <p>
   * Method under test:
   * {@link NettyWebSocket#sendBinaryFrame(ByteBuf, boolean, int)}
   */
  @Test
  @DisplayName("Test sendBinaryFrame(ByteBuf, boolean, int) with 'ByteBuf', 'boolean', 'int'; given 'true'; then calls capacity()")
  void testSendBinaryFrameWithByteBufBooleanInt_givenTrue_thenCallsCapacity() {
    // Arrange
    EmbeddedChannel channel = new EmbeddedChannel();
    NettyWebSocket nettyWebSocket = new NettyWebSocket(channel, new DefaultHttpHeaders());
    ByteBuf buffer = mock(ByteBuf.class);
    when(buffer.release()).thenReturn(true);
    when(buffer.capacity()).thenReturn(3);
    when(buffer.maxCapacity()).thenReturn(3);
    when(buffer.readerIndex()).thenReturn(1);
    when(buffer.writerIndex()).thenReturn(1);
    when(buffer.refCnt()).thenReturn(1);
    when(buffer.touch(Mockito.<Object>any()))
        .thenReturn(new DuplicatedByteBuf(new EmptyByteBuf(new AdaptiveByteBufAllocator())));

    // Act
    Future<Void> actualSendBinaryFrameResult = nettyWebSocket
        .sendBinaryFrame(new DuplicatedByteBuf(new ReadOnlyByteBuf(new DuplicatedByteBuf(buffer))), true, 1);

    // Assert
    verify(buffer, atLeast(1)).capacity();
    verify(buffer).maxCapacity();
    verify(buffer).readerIndex();
    verify(buffer).touch(isA(Object.class));
    verify(buffer).writerIndex();
    verify(buffer).refCnt();
    verify(buffer).release();
    assertTrue(actualSendBinaryFrameResult instanceof DefaultChannelPromise);
    assertTrue(actualSendBinaryFrameResult.isDone());
  }

  /**
   * Test {@link NettyWebSocket#sendBinaryFrame(ByteBuf, boolean, int)} with
   * {@code ByteBuf}, {@code boolean}, {@code int}.
   * <ul>
   *   <li>Then return {@link java.util.concurrent.Future#get()} is
   * {@code null}.</li>
   * </ul>
   * <p>
   * Method under test:
   * {@link NettyWebSocket#sendBinaryFrame(ByteBuf, boolean, int)}
   */
  @Test
  @DisplayName("Test sendBinaryFrame(ByteBuf, boolean, int) with 'ByteBuf', 'boolean', 'int'; then return get() is 'null'")
  void testSendBinaryFrameWithByteBufBooleanInt_thenReturnGetIsNull() throws InterruptedException, ExecutionException {
    // Arrange
    EmbeddedChannel channel = new EmbeddedChannel();
    NettyWebSocket nettyWebSocket = new NettyWebSocket(channel, new DefaultHttpHeaders());

    // Act
    io.netty.util.concurrent.Future<Void> actualSendBinaryFrameResult = nettyWebSocket
        .sendBinaryFrame(new DuplicatedByteBuf(new EmptyByteBuf(new AdaptiveByteBufAllocator())), true, 1);

    // Assert
    assertTrue(actualSendBinaryFrameResult instanceof DefaultChannelPromise);
    assertNull(actualSendBinaryFrameResult.get());
    assertTrue(actualSendBinaryFrameResult.isDone());
  }

  /**
   * Test {@link NettyWebSocket#sendBinaryFrame(byte[])} with {@code byte[]}.
   * <ul>
   *   <li>Then return {@link DefaultChannelPromise}.</li>
   * </ul>
   * <p>
   * Method under test: {@link NettyWebSocket#sendBinaryFrame(byte[])}
   */
  @Test
  @DisplayName("Test sendBinaryFrame(byte[]) with 'byte[]'; then return DefaultChannelPromise")
  void testSendBinaryFrameWithByte_thenReturnDefaultChannelPromise()
      throws UnsupportedEncodingException, InterruptedException, ExecutionException {
    // Arrange
    EmbeddedChannel channel = new EmbeddedChannel();
    NettyWebSocket nettyWebSocket = new NettyWebSocket(channel, new DefaultHttpHeaders());

    // Act
    Future<Void> actualSendBinaryFrameResult = nettyWebSocket.sendBinaryFrame("AXAXAXAX".getBytes("UTF-8"));

    // Assert
    assertTrue(actualSendBinaryFrameResult instanceof DefaultChannelPromise);
    assertNull(actualSendBinaryFrameResult.get());
    assertTrue(actualSendBinaryFrameResult.isDone());
  }

  /**
   * Test {@link NettyWebSocket#sendBinaryFrame(byte[])} with {@code byte[]}.
   * <ul>
   *   <li>When empty array of {@code byte}.</li>
   *   <li>Then return {@link DefaultChannelPromise}.</li>
   * </ul>
   * <p>
   * Method under test: {@link NettyWebSocket#sendBinaryFrame(byte[])}
   */
  @Test
  @DisplayName("Test sendBinaryFrame(byte[]) with 'byte[]'; when empty array of byte; then return DefaultChannelPromise")
  void testSendBinaryFrameWithByte_whenEmptyArrayOfByte_thenReturnDefaultChannelPromise()
      throws InterruptedException, ExecutionException {
    // Arrange
    EmbeddedChannel channel = new EmbeddedChannel();

    // Act
    Future<Void> actualSendBinaryFrameResult = (new NettyWebSocket(channel, new DefaultHttpHeaders()))
        .sendBinaryFrame(new byte[]{});

    // Assert
    assertTrue(actualSendBinaryFrameResult instanceof DefaultChannelPromise);
    assertNull(actualSendBinaryFrameResult.get());
    assertTrue(actualSendBinaryFrameResult.isDone());
  }

  /**
   * Test {@link NettyWebSocket#sendContinuationFrame(byte[], boolean, int)} with
   * {@code byte[]}, {@code boolean}, {@code int}.
   * <ul>
   *   <li>Then return {@link DefaultChannelPromise}.</li>
   * </ul>
   * <p>
   * Method under test:
   * {@link NettyWebSocket#sendContinuationFrame(byte[], boolean, int)}
   */
  @Test
  @DisplayName("Test sendContinuationFrame(byte[], boolean, int) with 'byte[]', 'boolean', 'int'; then return DefaultChannelPromise")
  void testSendContinuationFrameWithByteBooleanInt_thenReturnDefaultChannelPromise()
      throws UnsupportedEncodingException, InterruptedException, ExecutionException {
    // Arrange
    EmbeddedChannel channel = new EmbeddedChannel();
    NettyWebSocket nettyWebSocket = new NettyWebSocket(channel, new DefaultHttpHeaders());

    // Act
    Future<Void> actualSendContinuationFrameResult = nettyWebSocket.sendContinuationFrame("AXAXAXAX".getBytes("UTF-8"),
        true, 1);

    // Assert
    assertTrue(actualSendContinuationFrameResult instanceof DefaultChannelPromise);
    assertNull(actualSendContinuationFrameResult.get());
    assertTrue(actualSendContinuationFrameResult.isDone());
  }

  /**
   * Test {@link NettyWebSocket#sendContinuationFrame(byte[], boolean, int)} with
   * {@code byte[]}, {@code boolean}, {@code int}.
   * <ul>
   *   <li>When empty array of {@code byte}.</li>
   * </ul>
   * <p>
   * Method under test:
   * {@link NettyWebSocket#sendContinuationFrame(byte[], boolean, int)}
   */
  @Test
  @DisplayName("Test sendContinuationFrame(byte[], boolean, int) with 'byte[]', 'boolean', 'int'; when empty array of byte")
  void testSendContinuationFrameWithByteBooleanInt_whenEmptyArrayOfByte()
      throws InterruptedException, ExecutionException {
    // Arrange
    EmbeddedChannel channel = new EmbeddedChannel();

    // Act
    Future<Void> actualSendContinuationFrameResult = (new NettyWebSocket(channel, new DefaultHttpHeaders()))
        .sendContinuationFrame(new byte[]{}, true, 1);

    // Assert
    assertTrue(actualSendContinuationFrameResult instanceof DefaultChannelPromise);
    assertNull(actualSendContinuationFrameResult.get());
    assertTrue(actualSendContinuationFrameResult.isDone());
  }

  /**
   * Test {@link NettyWebSocket#sendContinuationFrame(ByteBuf, boolean, int)} with
   * {@code ByteBuf}, {@code boolean}, {@code int}.
   * <p>
   * Method under test:
   * {@link NettyWebSocket#sendContinuationFrame(ByteBuf, boolean, int)}
   */
  @Test
  @DisplayName("Test sendContinuationFrame(ByteBuf, boolean, int) with 'ByteBuf', 'boolean', 'int'")
  void testSendContinuationFrameWithByteBufBooleanInt() throws InterruptedException, ExecutionException {
    // Arrange
    EmbeddedChannel channel = new EmbeddedChannel();
    NettyWebSocket nettyWebSocket = new NettyWebSocket(channel, new DefaultHttpHeaders());

    // Act
    Future<Void> actualSendContinuationFrameResult = nettyWebSocket
        .sendContinuationFrame(new DuplicatedByteBuf(Unpooled.compositeBuffer(3)), true, 1);

    // Assert
    assertTrue(actualSendContinuationFrameResult instanceof DefaultChannelPromise);
    assertNull(actualSendContinuationFrameResult.get());
    assertTrue(actualSendContinuationFrameResult.isDone());
  }

  /**
   * Test {@link NettyWebSocket#sendContinuationFrame(ByteBuf, boolean, int)} with
   * {@code ByteBuf}, {@code boolean}, {@code int}.
   * <p>
   * Method under test:
   * {@link NettyWebSocket#sendContinuationFrame(ByteBuf, boolean, int)}
   */
  @Test
  @DisplayName("Test sendContinuationFrame(ByteBuf, boolean, int) with 'ByteBuf', 'boolean', 'int'")
  void testSendContinuationFrameWithByteBufBooleanInt2() throws InterruptedException, ExecutionException {
    // Arrange
    EmbeddedChannel channel = new EmbeddedChannel();
    NettyWebSocket nettyWebSocket = new NettyWebSocket(channel, new DefaultHttpHeaders());

    // Act
    Future<Void> actualSendContinuationFrameResult = nettyWebSocket.sendContinuationFrame(new DuplicatedByteBuf(
        new ReadOnlyByteBuf(new DuplicatedByteBuf(new EmptyByteBuf(new AdaptiveByteBufAllocator())))), true, 1);

    // Assert
    assertTrue(actualSendContinuationFrameResult instanceof DefaultChannelPromise);
    assertNull(actualSendContinuationFrameResult.get());
    assertTrue(actualSendContinuationFrameResult.isDone());
  }

  /**
   * Test {@link NettyWebSocket#sendContinuationFrame(ByteBuf, boolean, int)} with
   * {@code ByteBuf}, {@code boolean}, {@code int}.
   * <ul>
   *   <li>Given {@code true}.</li>
   *   <li>Then calls {@link ByteBuf#capacity()}.</li>
   * </ul>
   * <p>
   * Method under test:
   * {@link NettyWebSocket#sendContinuationFrame(ByteBuf, boolean, int)}
   */
  @Test
  @DisplayName("Test sendContinuationFrame(ByteBuf, boolean, int) with 'ByteBuf', 'boolean', 'int'; given 'true'; then calls capacity()")
  void testSendContinuationFrameWithByteBufBooleanInt_givenTrue_thenCallsCapacity() {
    // Arrange
    EmbeddedChannel channel = new EmbeddedChannel();
    NettyWebSocket nettyWebSocket = new NettyWebSocket(channel, new DefaultHttpHeaders());
    ByteBuf buffer = mock(ByteBuf.class);
    when(buffer.release()).thenReturn(true);
    when(buffer.capacity()).thenReturn(3);
    when(buffer.maxCapacity()).thenReturn(3);
    when(buffer.readerIndex()).thenReturn(1);
    when(buffer.writerIndex()).thenReturn(1);
    when(buffer.refCnt()).thenReturn(1);
    when(buffer.touch(Mockito.<Object>any()))
        .thenReturn(new DuplicatedByteBuf(new EmptyByteBuf(new AdaptiveByteBufAllocator())));

    // Act
    Future<Void> actualSendContinuationFrameResult = nettyWebSocket
        .sendContinuationFrame(new DuplicatedByteBuf(new ReadOnlyByteBuf(new DuplicatedByteBuf(buffer))), true, 1);

    // Assert
    verify(buffer, atLeast(1)).capacity();
    verify(buffer).maxCapacity();
    verify(buffer).readerIndex();
    verify(buffer).touch(isA(Object.class));
    verify(buffer).writerIndex();
    verify(buffer).refCnt();
    verify(buffer).release();
    assertTrue(actualSendContinuationFrameResult instanceof DefaultChannelPromise);
    assertTrue(actualSendContinuationFrameResult.isDone());
  }

  /**
   * Test {@link NettyWebSocket#sendContinuationFrame(ByteBuf, boolean, int)} with
   * {@code ByteBuf}, {@code boolean}, {@code int}.
   * <ul>
   *   <li>Then return {@link java.util.concurrent.Future#get()} is
   * {@code null}.</li>
   * </ul>
   * <p>
   * Method under test:
   * {@link NettyWebSocket#sendContinuationFrame(ByteBuf, boolean, int)}
   */
  @Test
  @DisplayName("Test sendContinuationFrame(ByteBuf, boolean, int) with 'ByteBuf', 'boolean', 'int'; then return get() is 'null'")
  void testSendContinuationFrameWithByteBufBooleanInt_thenReturnGetIsNull()
      throws InterruptedException, ExecutionException {
    // Arrange
    EmbeddedChannel channel = new EmbeddedChannel();
    NettyWebSocket nettyWebSocket = new NettyWebSocket(channel, new DefaultHttpHeaders());

    // Act
    io.netty.util.concurrent.Future<Void> actualSendContinuationFrameResult = nettyWebSocket
        .sendContinuationFrame(new DuplicatedByteBuf(new EmptyByteBuf(new AdaptiveByteBufAllocator())), true, 1);

    // Assert
    assertTrue(actualSendContinuationFrameResult instanceof DefaultChannelPromise);
    assertNull(actualSendContinuationFrameResult.get());
    assertTrue(actualSendContinuationFrameResult.isDone());
  }

  /**
   * Test {@link NettyWebSocket#sendContinuationFrame(String, boolean, int)} with
   * {@code String}, {@code boolean}, {@code int}.
   * <ul>
   *   <li>Then return {@link DefaultChannelPromise}.</li>
   * </ul>
   * <p>
   * Method under test:
   * {@link NettyWebSocket#sendContinuationFrame(String, boolean, int)}
   */
  @Test
  @DisplayName("Test sendContinuationFrame(String, boolean, int) with 'String', 'boolean', 'int'; then return DefaultChannelPromise")
  void testSendContinuationFrameWithStringBooleanInt_thenReturnDefaultChannelPromise()
      throws InterruptedException, ExecutionException {
    // Arrange
    EmbeddedChannel channel = new EmbeddedChannel();

    // Act
    Future<Void> actualSendContinuationFrameResult = (new NettyWebSocket(channel, new DefaultHttpHeaders()))
        .sendContinuationFrame("https://example.org/example", true, 1);

    // Assert
    assertTrue(actualSendContinuationFrameResult instanceof DefaultChannelPromise);
    assertNull(actualSendContinuationFrameResult.get());
    assertTrue(actualSendContinuationFrameResult.isDone());
  }

  /**
   * Test {@link NettyWebSocket#sendContinuationFrame(String, boolean, int)} with
   * {@code String}, {@code boolean}, {@code int}.
   * <ul>
   *   <li>When empty string.</li>
   * </ul>
   * <p>
   * Method under test:
   * {@link NettyWebSocket#sendContinuationFrame(String, boolean, int)}
   */
  @Test
  @DisplayName("Test sendContinuationFrame(String, boolean, int) with 'String', 'boolean', 'int'; when empty string")
  void testSendContinuationFrameWithStringBooleanInt_whenEmptyString() throws InterruptedException, ExecutionException {
    // Arrange
    EmbeddedChannel channel = new EmbeddedChannel();

    // Act
    Future<Void> actualSendContinuationFrameResult = (new NettyWebSocket(channel, new DefaultHttpHeaders()))
        .sendContinuationFrame("", true, 1);

    // Assert
    assertTrue(actualSendContinuationFrameResult instanceof DefaultChannelPromise);
    assertNull(actualSendContinuationFrameResult.get());
    assertTrue(actualSendContinuationFrameResult.isDone());
  }

  /**
   * Test {@link NettyWebSocket#sendPingFrame(ByteBuf)} with {@code ByteBuf}.
   * <p>
   * Method under test: {@link NettyWebSocket#sendPingFrame(ByteBuf)}
   */
  @Test
  @DisplayName("Test sendPingFrame(ByteBuf) with 'ByteBuf'")
  void testSendPingFrameWithByteBuf() throws InterruptedException, ExecutionException {
    // Arrange
    EmbeddedChannel channel = new EmbeddedChannel();
    NettyWebSocket nettyWebSocket = new NettyWebSocket(channel, new DefaultHttpHeaders());

    // Act
    Future<Void> actualSendPingFrameResult = nettyWebSocket
        .sendPingFrame(new DuplicatedByteBuf(Unpooled.compositeBuffer(3)));

    // Assert
    assertTrue(actualSendPingFrameResult instanceof DefaultChannelPromise);
    assertNull(actualSendPingFrameResult.get());
    assertTrue(actualSendPingFrameResult.isDone());
  }

  /**
   * Test {@link NettyWebSocket#sendPingFrame(ByteBuf)} with {@code ByteBuf}.
   * <ul>
   *   <li>Given {@code true}.</li>
   *   <li>Then calls {@link ByteBuf#capacity()}.</li>
   * </ul>
   * <p>
   * Method under test: {@link NettyWebSocket#sendPingFrame(ByteBuf)}
   */
  @Test
  @DisplayName("Test sendPingFrame(ByteBuf) with 'ByteBuf'; given 'true'; then calls capacity()")
  void testSendPingFrameWithByteBuf_givenTrue_thenCallsCapacity() {
    // Arrange
    EmbeddedChannel channel = new EmbeddedChannel();
    NettyWebSocket nettyWebSocket = new NettyWebSocket(channel, new DefaultHttpHeaders());
    ByteBuf buffer = mock(ByteBuf.class);
    when(buffer.release()).thenReturn(true);
    when(buffer.capacity()).thenReturn(3);
    when(buffer.maxCapacity()).thenReturn(3);
    when(buffer.readerIndex()).thenReturn(1);
    when(buffer.writerIndex()).thenReturn(1);
    when(buffer.refCnt()).thenReturn(1);
    when(buffer.touch(Mockito.<Object>any()))
        .thenReturn(new DuplicatedByteBuf(new EmptyByteBuf(new AdaptiveByteBufAllocator())));

    // Act
    Future<Void> actualSendPingFrameResult = nettyWebSocket
        .sendPingFrame(new DuplicatedByteBuf(new ReadOnlyByteBuf(new DuplicatedByteBuf(buffer))));

    // Assert
    verify(buffer, atLeast(1)).capacity();
    verify(buffer).maxCapacity();
    verify(buffer).readerIndex();
    verify(buffer).touch(isA(Object.class));
    verify(buffer).writerIndex();
    verify(buffer).refCnt();
    verify(buffer).release();
    assertTrue(actualSendPingFrameResult instanceof DefaultChannelPromise);
    assertTrue(actualSendPingFrameResult.isDone());
  }

  /**
   * Test {@link NettyWebSocket#sendPingFrame(ByteBuf)} with {@code ByteBuf}.
   * <ul>
   *   <li>When {@link EmptyByteBuf#EmptyByteBuf(ByteBufAllocator)} with alloc is
   * {@link AdaptiveByteBufAllocator#AdaptiveByteBufAllocator()}.</li>
   * </ul>
   * <p>
   * Method under test: {@link NettyWebSocket#sendPingFrame(ByteBuf)}
   */
  @Test
  @DisplayName("Test sendPingFrame(ByteBuf) with 'ByteBuf'; when EmptyByteBuf(ByteBufAllocator) with alloc is AdaptiveByteBufAllocator()")
  void testSendPingFrameWithByteBuf_whenEmptyByteBufWithAllocIsAdaptiveByteBufAllocator()
      throws InterruptedException, ExecutionException {
    // Arrange
    EmbeddedChannel channel = new EmbeddedChannel();
    NettyWebSocket nettyWebSocket = new NettyWebSocket(channel, new DefaultHttpHeaders());

    // Act
    Future<Void> actualSendPingFrameResult = nettyWebSocket
        .sendPingFrame(new DuplicatedByteBuf(new EmptyByteBuf(new AdaptiveByteBufAllocator())));

    // Assert
    assertTrue(actualSendPingFrameResult instanceof DefaultChannelPromise);
    assertNull(actualSendPingFrameResult.get());
    assertTrue(actualSendPingFrameResult.isDone());
  }

  /**
   * Test {@link NettyWebSocket#sendPingFrame(ByteBuf)} with {@code ByteBuf}.
   * <ul>
   *   <li>When {@link EmptyByteBuf#EmptyByteBuf(ByteBufAllocator)} with alloc is
   * {@link AdaptiveByteBufAllocator#AdaptiveByteBufAllocator()}.</li>
   * </ul>
   * <p>
   * Method under test: {@link NettyWebSocket#sendPingFrame(ByteBuf)}
   */
  @Test
  @DisplayName("Test sendPingFrame(ByteBuf) with 'ByteBuf'; when EmptyByteBuf(ByteBufAllocator) with alloc is AdaptiveByteBufAllocator()")
  void testSendPingFrameWithByteBuf_whenEmptyByteBufWithAllocIsAdaptiveByteBufAllocator2()
      throws InterruptedException, ExecutionException {
    // Arrange
    EmbeddedChannel channel = new EmbeddedChannel();
    NettyWebSocket nettyWebSocket = new NettyWebSocket(channel, new DefaultHttpHeaders());

    // Act
    Future<Void> actualSendPingFrameResult = nettyWebSocket.sendPingFrame(new DuplicatedByteBuf(
        new ReadOnlyByteBuf(new DuplicatedByteBuf(new EmptyByteBuf(new AdaptiveByteBufAllocator())))));

    // Assert
    assertTrue(actualSendPingFrameResult instanceof DefaultChannelPromise);
    assertNull(actualSendPingFrameResult.get());
    assertTrue(actualSendPingFrameResult.isDone());
  }

  /**
   * Test {@link NettyWebSocket#sendPingFrame(byte[])} with {@code byte[]}.
   * <ul>
   *   <li>Then return {@link DefaultChannelPromise}.</li>
   * </ul>
   * <p>
   * Method under test: {@link NettyWebSocket#sendPingFrame(byte[])}
   */
  @Test
  @DisplayName("Test sendPingFrame(byte[]) with 'byte[]'; then return DefaultChannelPromise")
  void testSendPingFrameWithByte_thenReturnDefaultChannelPromise()
      throws UnsupportedEncodingException, InterruptedException, ExecutionException {
    // Arrange
    EmbeddedChannel channel = new EmbeddedChannel();
    NettyWebSocket nettyWebSocket = new NettyWebSocket(channel, new DefaultHttpHeaders());

    // Act
    Future<Void> actualSendPingFrameResult = nettyWebSocket.sendPingFrame("AXAXAXAX".getBytes("UTF-8"));

    // Assert
    assertTrue(actualSendPingFrameResult instanceof DefaultChannelPromise);
    assertNull(actualSendPingFrameResult.get());
    assertTrue(actualSendPingFrameResult.isDone());
  }

  /**
   * Test {@link NettyWebSocket#sendPingFrame(byte[])} with {@code byte[]}.
   * <ul>
   *   <li>When empty array of {@code byte}.</li>
   *   <li>Then return {@link DefaultChannelPromise}.</li>
   * </ul>
   * <p>
   * Method under test: {@link NettyWebSocket#sendPingFrame(byte[])}
   */
  @Test
  @DisplayName("Test sendPingFrame(byte[]) with 'byte[]'; when empty array of byte; then return DefaultChannelPromise")
  void testSendPingFrameWithByte_whenEmptyArrayOfByte_thenReturnDefaultChannelPromise()
      throws InterruptedException, ExecutionException {
    // Arrange
    EmbeddedChannel channel = new EmbeddedChannel();

    // Act
    Future<Void> actualSendPingFrameResult = (new NettyWebSocket(channel, new DefaultHttpHeaders()))
        .sendPingFrame(new byte[]{});

    // Assert
    assertTrue(actualSendPingFrameResult instanceof DefaultChannelPromise);
    assertNull(actualSendPingFrameResult.get());
    assertTrue(actualSendPingFrameResult.isDone());
  }

  /**
   * Test {@link NettyWebSocket#sendPingFrame()}.
   * <ul>
   *   <li>Then return {@link DefaultChannelPromise}.</li>
   * </ul>
   * <p>
   * Method under test: {@link NettyWebSocket#sendPingFrame()}
   */
  @Test
  @DisplayName("Test sendPingFrame(); then return DefaultChannelPromise")
  void testSendPingFrame_thenReturnDefaultChannelPromise() throws InterruptedException, ExecutionException {
    // Arrange
    EmbeddedChannel channel = new EmbeddedChannel();

    // Act
    Future<Void> actualSendPingFrameResult = (new NettyWebSocket(channel, new DefaultHttpHeaders())).sendPingFrame();

    // Assert
    assertTrue(actualSendPingFrameResult instanceof DefaultChannelPromise);
    assertNull(actualSendPingFrameResult.get());
    assertTrue(actualSendPingFrameResult.isDone());
  }

  /**
   * Test {@link NettyWebSocket#sendPongFrame(ByteBuf)} with {@code ByteBuf}.
   * <p>
   * Method under test: {@link NettyWebSocket#sendPongFrame(ByteBuf)}
   */
  @Test
  @DisplayName("Test sendPongFrame(ByteBuf) with 'ByteBuf'")
  void testSendPongFrameWithByteBuf() throws InterruptedException, ExecutionException {
    // Arrange
    EmbeddedChannel channel = new EmbeddedChannel();
    NettyWebSocket nettyWebSocket = new NettyWebSocket(channel, new DefaultHttpHeaders());
    DuplicatedByteBuf payload = new DuplicatedByteBuf(new EmptyByteBuf(new AdaptiveByteBufAllocator()));

    // Act
    Future<Void> actualSendPongFrameResult = nettyWebSocket.sendPongFrame(payload);

    // Assert
    assertTrue(actualSendPongFrameResult instanceof DefaultChannelPromise);
    assertNull(actualSendPongFrameResult.get());
    assertEquals(0, payload.capacity());
    assertFalse(payload.isWritable());
    assertTrue(actualSendPongFrameResult.isDone());
  }

  /**
   * Test {@link NettyWebSocket#sendPongFrame(ByteBuf)} with {@code ByteBuf}.
   * <p>
   * Method under test: {@link NettyWebSocket#sendPongFrame(ByteBuf)}
   */
  @Test
  @DisplayName("Test sendPongFrame(ByteBuf) with 'ByteBuf'")
  void testSendPongFrameWithByteBuf2() throws InterruptedException, ExecutionException {
    // Arrange
    EmbeddedChannel channel = new EmbeddedChannel();
    NettyWebSocket nettyWebSocket = new NettyWebSocket(channel, new DefaultHttpHeaders());
    DuplicatedByteBuf payload = new DuplicatedByteBuf(
        new ReadOnlyByteBuf(new DuplicatedByteBuf(new EmptyByteBuf(new AdaptiveByteBufAllocator()))));

    // Act
    Future<Void> actualSendPongFrameResult = nettyWebSocket.sendPongFrame(payload);

    // Assert
    assertTrue(actualSendPongFrameResult instanceof DefaultChannelPromise);
    assertNull(actualSendPongFrameResult.get());
    assertEquals(0, payload.capacity());
    assertFalse(payload.isWritable());
    assertTrue(actualSendPongFrameResult.isDone());
  }

  /**
   * Test {@link NettyWebSocket#sendPongFrame(ByteBuf)} with {@code ByteBuf}.
   * <p>
   * Method under test: {@link NettyWebSocket#sendPongFrame(ByteBuf)}
   */
  @Test
  @DisplayName("Test sendPongFrame(ByteBuf) with 'ByteBuf'")
  void testSendPongFrameWithByteBuf3() {
    // Arrange
    EmbeddedChannel channel = new EmbeddedChannel();
    NettyWebSocket nettyWebSocket = new NettyWebSocket(channel, new DefaultHttpHeaders());
    DuplicatedByteBuf payload = new DuplicatedByteBuf(new UnpooledDirectByteBuf(new AdaptiveByteBufAllocator(), 1, 3));

    // Act
    nettyWebSocket.sendPongFrame(payload);

    // Assert
    ByteBuf unwrapResult = payload.unwrap();
    assertTrue(unwrapResult instanceof UnpooledDirectByteBuf);
    assertEquals(0, unwrapResult.refCnt());
    assertEquals(1, payload.capacity());
    assertTrue(payload.isWritable());
    assertTrue(unwrapResult.isWritable());
  }

  /**
   * Test {@link NettyWebSocket#sendPongFrame(ByteBuf)} with {@code ByteBuf}.
   * <p>
   * Method under test: {@link NettyWebSocket#sendPongFrame(ByteBuf)}
   */
  @Test
  @DisplayName("Test sendPongFrame(ByteBuf) with 'ByteBuf'")
  void testSendPongFrameWithByteBuf4() {
    // Arrange
    EmbeddedChannel channel = new EmbeddedChannel();
    NettyWebSocket nettyWebSocket = new NettyWebSocket(channel, new DefaultHttpHeaders());
    DuplicatedByteBuf payload = new DuplicatedByteBuf(new UnpooledHeapByteBuf(new AdaptiveByteBufAllocator(), 1, 3));

    // Act
    nettyWebSocket.sendPongFrame(payload);

    // Assert
    ByteBuf unwrapResult = payload.unwrap();
    assertTrue(unwrapResult instanceof UnpooledHeapByteBuf);
    assertEquals(0, unwrapResult.capacity());
    assertEquals(0, payload.capacity());
    assertFalse(payload.isWritable());
    assertFalse(unwrapResult.isWritable());
  }

  /**
   * Test {@link NettyWebSocket#sendPongFrame(ByteBuf)} with {@code ByteBuf}.
   * <p>
   * Method under test: {@link NettyWebSocket#sendPongFrame(ByteBuf)}
   */
  @Test
  @DisplayName("Test sendPongFrame(ByteBuf) with 'ByteBuf'")
  void testSendPongFrameWithByteBuf5() throws InterruptedException, ExecutionException {
    // Arrange
    EmbeddedChannel channel = new EmbeddedChannel();
    NettyWebSocket nettyWebSocket = new NettyWebSocket(channel, new DefaultHttpHeaders());
    DuplicatedByteBuf payload = new DuplicatedByteBuf(new ReadOnlyByteBuf(new DuplicatedByteBuf(
        new ReadOnlyByteBuf(new DuplicatedByteBuf(new EmptyByteBuf(new AdaptiveByteBufAllocator()))))));

    // Act
    Future<Void> actualSendPongFrameResult = nettyWebSocket.sendPongFrame(payload);

    // Assert
    assertTrue(actualSendPongFrameResult instanceof DefaultChannelPromise);
    assertNull(actualSendPongFrameResult.get());
    assertEquals(0, payload.capacity());
    assertFalse(payload.isWritable());
    assertTrue(actualSendPongFrameResult.isDone());
  }

  /**
   * Test {@link NettyWebSocket#sendPongFrame(ByteBuf)} with {@code ByteBuf}.
   * <p>
   * Method under test: {@link NettyWebSocket#sendPongFrame(ByteBuf)}
   */
  @Test
  @DisplayName("Test sendPongFrame(ByteBuf) with 'ByteBuf'")
  void testSendPongFrameWithByteBuf6() throws InterruptedException, ExecutionException {
    // Arrange
    EmbeddedChannel channel = new EmbeddedChannel();
    NettyWebSocket nettyWebSocket = new NettyWebSocket(channel, new DefaultHttpHeaders());
    DuplicatedByteBuf payload = new DuplicatedByteBuf(
        new ReadOnlyByteBuf(new DuplicatedByteBuf(new UnpooledDirectByteBuf(new AdaptiveByteBufAllocator(), 1, 3))));

    // Act
    Future<Void> actualSendPongFrameResult = nettyWebSocket.sendPongFrame(payload);

    // Assert
    ByteBuf unwrapResult = payload.unwrap();
    assertTrue(unwrapResult instanceof ReadOnlyByteBuf);
    ByteBuf unwrapResult2 = unwrapResult.unwrap();
    assertTrue(unwrapResult2 instanceof UnpooledDirectByteBuf);
    assertTrue(actualSendPongFrameResult instanceof DefaultChannelPromise);
    assertNull(actualSendPongFrameResult.get());
    assertEquals(0, unwrapResult2.refCnt());
    assertTrue(actualSendPongFrameResult.isDone());
  }

  /**
   * Test {@link NettyWebSocket#sendPongFrame(byte[])} with {@code byte[]}.
   * <ul>
   *   <li>Then return {@link DefaultChannelPromise}.</li>
   * </ul>
   * <p>
   * Method under test: {@link NettyWebSocket#sendPongFrame(byte[])}
   */
  @Test
  @DisplayName("Test sendPongFrame(byte[]) with 'byte[]'; then return DefaultChannelPromise")
  void testSendPongFrameWithByte_thenReturnDefaultChannelPromise()
      throws UnsupportedEncodingException, InterruptedException, ExecutionException {
    // Arrange
    EmbeddedChannel channel = new EmbeddedChannel();
    NettyWebSocket nettyWebSocket = new NettyWebSocket(channel, new DefaultHttpHeaders());

    // Act
    Future<Void> actualSendPongFrameResult = nettyWebSocket.sendPongFrame("AXAXAXAX".getBytes("UTF-8"));

    // Assert
    assertTrue(actualSendPongFrameResult instanceof DefaultChannelPromise);
    assertNull(actualSendPongFrameResult.get());
    assertTrue(actualSendPongFrameResult.isDone());
  }

  /**
   * Test {@link NettyWebSocket#sendPongFrame(byte[])} with {@code byte[]}.
   * <ul>
   *   <li>When empty array of {@code byte}.</li>
   *   <li>Then return {@link DefaultChannelPromise}.</li>
   * </ul>
   * <p>
   * Method under test: {@link NettyWebSocket#sendPongFrame(byte[])}
   */
  @Test
  @DisplayName("Test sendPongFrame(byte[]) with 'byte[]'; when empty array of byte; then return DefaultChannelPromise")
  void testSendPongFrameWithByte_whenEmptyArrayOfByte_thenReturnDefaultChannelPromise()
      throws InterruptedException, ExecutionException {
    // Arrange
    EmbeddedChannel channel = new EmbeddedChannel();

    // Act
    Future<Void> actualSendPongFrameResult = (new NettyWebSocket(channel, new DefaultHttpHeaders()))
        .sendPongFrame(new byte[]{});

    // Assert
    assertTrue(actualSendPongFrameResult instanceof DefaultChannelPromise);
    assertNull(actualSendPongFrameResult.get());
    assertTrue(actualSendPongFrameResult.isDone());
  }

  /**
   * Test {@link NettyWebSocket#sendPongFrame()}.
   * <ul>
   *   <li>Then return {@link DefaultChannelPromise}.</li>
   * </ul>
   * <p>
   * Method under test: {@link NettyWebSocket#sendPongFrame()}
   */
  @Test
  @DisplayName("Test sendPongFrame(); then return DefaultChannelPromise")
  void testSendPongFrame_thenReturnDefaultChannelPromise() throws InterruptedException, ExecutionException {
    // Arrange
    EmbeddedChannel channel = new EmbeddedChannel();

    // Act
    Future<Void> actualSendPongFrameResult = (new NettyWebSocket(channel, new DefaultHttpHeaders())).sendPongFrame();

    // Assert
    assertTrue(actualSendPongFrameResult instanceof DefaultChannelPromise);
    assertNull(actualSendPongFrameResult.get());
    assertTrue(actualSendPongFrameResult.isDone());
  }

  /**
   * Test {@link NettyWebSocket#sendCloseFrame(int, String)} with {@code int},
   * {@code String}.
   * <ul>
   *   <li>When empty string.</li>
   *   <li>Then return {@link DefaultChannelPromise}.</li>
   * </ul>
   * <p>
   * Method under test: {@link NettyWebSocket#sendCloseFrame(int, String)}
   */
  @Test
  @DisplayName("Test sendCloseFrame(int, String) with 'int', 'String'; when empty string; then return DefaultChannelPromise")
  void testSendCloseFrameWithIntString_whenEmptyString_thenReturnDefaultChannelPromise()
      throws InterruptedException, ExecutionException {
    // Arrange
    EmbeddedChannel channel = new EmbeddedChannel();

    // Act
    Future<Void> actualSendCloseFrameResult = (new NettyWebSocket(channel, new DefaultHttpHeaders())).sendCloseFrame(-1,
        "");

    // Assert
    assertTrue(actualSendCloseFrameResult instanceof DefaultChannelPromise);
    assertNull(actualSendCloseFrameResult.get());
    assertTrue(actualSendCloseFrameResult.isDone());
  }

  /**
   * Test {@link NettyWebSocket#sendCloseFrame(int, String)} with {@code int},
   * {@code String}.
   * <ul>
   *   <li>When minus one.</li>
   *   <li>Then return {@link DefaultChannelPromise}.</li>
   * </ul>
   * <p>
   * Method under test: {@link NettyWebSocket#sendCloseFrame(int, String)}
   */
  @Test
  @DisplayName("Test sendCloseFrame(int, String) with 'int', 'String'; when minus one; then return DefaultChannelPromise")
  void testSendCloseFrameWithIntString_whenMinusOne_thenReturnDefaultChannelPromise()
      throws InterruptedException, ExecutionException {
    // Arrange
    EmbeddedChannel channel = new EmbeddedChannel();

    // Act
    Future<Void> actualSendCloseFrameResult = (new NettyWebSocket(channel, new DefaultHttpHeaders())).sendCloseFrame(-1,
        "https://example.org/example");

    // Assert
    assertTrue(actualSendCloseFrameResult instanceof DefaultChannelPromise);
    assertNull(actualSendCloseFrameResult.get());
    assertTrue(actualSendCloseFrameResult.isDone());
  }

  /**
   * Test {@link NettyWebSocket#sendCloseFrame(int, String)} with {@code int},
   * {@code String}.
   * <ul>
   *   <li>When {@code null}.</li>
   *   <li>Then return {@link DefaultChannelPromise}.</li>
   * </ul>
   * <p>
   * Method under test: {@link NettyWebSocket#sendCloseFrame(int, String)}
   */
  @Test
  @DisplayName("Test sendCloseFrame(int, String) with 'int', 'String'; when 'null'; then return DefaultChannelPromise")
  void testSendCloseFrameWithIntString_whenNull_thenReturnDefaultChannelPromise()
      throws InterruptedException, ExecutionException {
    // Arrange
    EmbeddedChannel channel = new EmbeddedChannel();

    // Act
    Future<Void> actualSendCloseFrameResult = (new NettyWebSocket(channel, new DefaultHttpHeaders())).sendCloseFrame(-1,
        null);

    // Assert
    assertTrue(actualSendCloseFrameResult instanceof DefaultChannelPromise);
    assertNull(actualSendCloseFrameResult.get());
    assertTrue(actualSendCloseFrameResult.isDone());
  }

  /**
   * Test {@link NettyWebSocket#sendCloseFrame()}.
   * <ul>
   *   <li>Then return {@link DefaultChannelPromise}.</li>
   * </ul>
   * <p>
   * Method under test: {@link NettyWebSocket#sendCloseFrame()}
   */
  @Test
  @DisplayName("Test sendCloseFrame(); then return DefaultChannelPromise")
  void testSendCloseFrame_thenReturnDefaultChannelPromise() throws InterruptedException, ExecutionException {
    // Arrange
    EmbeddedChannel channel = new EmbeddedChannel();

    // Act
    Future<Void> actualSendCloseFrameResult = (new NettyWebSocket(channel, new DefaultHttpHeaders())).sendCloseFrame();

    // Assert
    assertTrue(actualSendCloseFrameResult instanceof DefaultChannelPromise);
    assertNull(actualSendCloseFrameResult.get());
    assertTrue(actualSendCloseFrameResult.isDone());
  }

  /**
   * Test {@link NettyWebSocket#isOpen()}.
   * <ul>
   *   <li>Then return {@code true}.</li>
   * </ul>
   * <p>
   * Method under test: {@link NettyWebSocket#isOpen()}
   */
  @Test
  @DisplayName("Test isOpen(); then return 'true'")
  void testIsOpen_thenReturnTrue() {
    // Arrange
    EmbeddedChannel channel = new EmbeddedChannel();

    // Act and Assert
    assertTrue((new NettyWebSocket(channel, new DefaultHttpHeaders())).isOpen());
  }

  /**
   * Test {@link NettyWebSocket#bufferFrame(WebSocketFrame)}.
   * <ul>
   *   <li>Given {@link BinaryWebSocketFrame#BinaryWebSocketFrame()}.</li>
   *   <li>Then calls {@link BinaryWebSocketFrame#retain()}.</li>
   * </ul>
   * <p>
   * Method under test: {@link NettyWebSocket#bufferFrame(WebSocketFrame)}
   */
  @Test
  @DisplayName("Test bufferFrame(WebSocketFrame); given BinaryWebSocketFrame(); then calls retain()")
  void testBufferFrame_givenBinaryWebSocketFrame_thenCallsRetain() {
    // Arrange
    EmbeddedChannel channel = new EmbeddedChannel();
    NettyWebSocket nettyWebSocket = new NettyWebSocket(channel, new DefaultHttpHeaders());
    BinaryWebSocketFrame frame = mock(BinaryWebSocketFrame.class);
    when(frame.retain()).thenReturn(new BinaryWebSocketFrame());

    // Act
    nettyWebSocket.bufferFrame(frame);

    // Assert
    verify(frame).retain();
  }

  /**
   * Test {@link NettyWebSocket#processBufferedFrames()}.
   * <p>
   * Method under test: {@link NettyWebSocket#processBufferedFrames()}
   */
  @Test
  @DisplayName("Test processBufferedFrames()")
  void testProcessBufferedFrames() {
    // Arrange
    EmbeddedChannel channel = new EmbeddedChannel();
    NettyWebSocket nettyWebSocket = new NettyWebSocket(channel, new DefaultHttpHeaders());

    // Act
    nettyWebSocket.processBufferedFrames();

    // Assert
    assertTrue(nettyWebSocket.isReady());
  }

  /**
   * Test {@link NettyWebSocket#processBufferedFrames()}.
   * <p>
   * Method under test: {@link NettyWebSocket#processBufferedFrames()}
   */
  @Test
  @DisplayName("Test processBufferedFrames()")
  void testProcessBufferedFrames2() {
    // Arrange
    EmbeddedChannel channel = new EmbeddedChannel();

    NettyWebSocket nettyWebSocket = new NettyWebSocket(channel, new DefaultHttpHeaders());
    nettyWebSocket.bufferFrame(new BinaryWebSocketFrame());

    // Act
    nettyWebSocket.processBufferedFrames();

    // Assert
    assertTrue(nettyWebSocket.isReady());
  }

  /**
   * Test {@link NettyWebSocket#processBufferedFrames()}.
   * <p>
   * Method under test: {@link NettyWebSocket#processBufferedFrames()}
   */
  @Test
  @DisplayName("Test processBufferedFrames()")
  void testProcessBufferedFrames3() {
    // Arrange
    EmbeddedChannel channel = new EmbeddedChannel();

    NettyWebSocket nettyWebSocket = new NettyWebSocket(channel, new DefaultHttpHeaders());
    nettyWebSocket.bufferFrame(new TextWebSocketFrame());

    // Act
    nettyWebSocket.processBufferedFrames();

    // Assert
    assertTrue(nettyWebSocket.isReady());
  }

  /**
   * Test {@link NettyWebSocket#processBufferedFrames()}.
   * <p>
   * Method under test: {@link NettyWebSocket#processBufferedFrames()}
   */
  @Test
  @DisplayName("Test processBufferedFrames()")
  void testProcessBufferedFrames4() {
    // Arrange
    EmbeddedChannel channel = new EmbeddedChannel();

    NettyWebSocket nettyWebSocket = new NettyWebSocket(channel, new DefaultHttpHeaders());
    nettyWebSocket.bufferFrame(new CloseWebSocketFrame());

    // Act
    nettyWebSocket.processBufferedFrames();

    // Assert
    assertTrue(nettyWebSocket.isReady());
  }

  /**
   * Test {@link NettyWebSocket#processBufferedFrames()}.
   * <p>
   * Method under test: {@link NettyWebSocket#processBufferedFrames()}
   */
  @Test
  @DisplayName("Test processBufferedFrames()")
  void testProcessBufferedFrames5() {
    // Arrange
    EmbeddedChannel channel = new EmbeddedChannel();

    NettyWebSocket nettyWebSocket = new NettyWebSocket(channel, new DefaultHttpHeaders());
    nettyWebSocket.bufferFrame(new ContinuationWebSocketFrame());

    // Act
    nettyWebSocket.processBufferedFrames();

    // Assert
    assertTrue(nettyWebSocket.isReady());
  }

  /**
   * Test {@link NettyWebSocket#processBufferedFrames()}.
   * <p>
   * Method under test: {@link NettyWebSocket#processBufferedFrames()}
   */
  @Test
  @DisplayName("Test processBufferedFrames()")
  void testProcessBufferedFrames6() {
    // Arrange
    EmbeddedChannel channel = new EmbeddedChannel();

    NettyWebSocket nettyWebSocket = new NettyWebSocket(channel, new DefaultHttpHeaders());
    nettyWebSocket.bufferFrame(new PingWebSocketFrame());

    // Act
    nettyWebSocket.processBufferedFrames();

    // Assert
    assertTrue(nettyWebSocket.isReady());
  }

  /**
   * Test {@link NettyWebSocket#processBufferedFrames()}.
   * <p>
   * Method under test: {@link NettyWebSocket#processBufferedFrames()}
   */
  @Test
  @DisplayName("Test processBufferedFrames()")
  void testProcessBufferedFrames7() {
    // Arrange
    EmbeddedChannel channel = new EmbeddedChannel();

    NettyWebSocket nettyWebSocket = new NettyWebSocket(channel, new DefaultHttpHeaders());
    nettyWebSocket.bufferFrame(new PongWebSocketFrame());

    // Act
    nettyWebSocket.processBufferedFrames();

    // Assert
    assertTrue(nettyWebSocket.isReady());
  }

  /**
   * Test {@link NettyWebSocket#processBufferedFrames()}.
   * <p>
   * Method under test: {@link NettyWebSocket#processBufferedFrames()}
   */
  @Test
  @DisplayName("Test processBufferedFrames()")
  void testProcessBufferedFrames8() {
    // Arrange
    EmbeddedChannel channel = new EmbeddedChannel();

    NettyWebSocket nettyWebSocket = new NettyWebSocket(channel, new DefaultHttpHeaders());
    nettyWebSocket
        .bufferFrame(new PongWebSocketFrame(true, 1, new UnpooledDirectByteBuf(new AdaptiveByteBufAllocator(), 1, 3)));
    new IllegalArgumentException("foo");
    new IllegalArgumentException("foo");
    new IllegalArgumentException("foo");
    new IllegalArgumentException("foo");
    new IllegalArgumentException("foo");
    new IllegalArgumentException("foo");
    new IllegalArgumentException("foo");

    // Act
    nettyWebSocket.processBufferedFrames();

    // Assert
    assertTrue(nettyWebSocket.isReady());
  }

  /**
   * Test {@link NettyWebSocket#processBufferedFrames()}.
   * <ul>
   *   <li>Given {@link DuplicatedByteBuf#DuplicatedByteBuf(ByteBuf)} with buffer is
   * {@link EmptyByteBuf#EmptyByteBuf(ByteBufAllocator)}.</li>
   * </ul>
   * <p>
   * Method under test: {@link NettyWebSocket#processBufferedFrames()}
   */
  @Test
  @DisplayName("Test processBufferedFrames(); given DuplicatedByteBuf(ByteBuf) with buffer is EmptyByteBuf(ByteBufAllocator)")
  void testProcessBufferedFrames_givenDuplicatedByteBufWithBufferIsEmptyByteBuf() {
    // Arrange
    EmbeddedChannel channel = new EmbeddedChannel();

    NettyWebSocket nettyWebSocket = new NettyWebSocket(channel, new DefaultHttpHeaders());
    nettyWebSocket
        .bufferFrame(new BinaryWebSocketFrame(new DuplicatedByteBuf(new EmptyByteBuf(new AdaptiveByteBufAllocator()))));

    // Act
    nettyWebSocket.processBufferedFrames();

    // Assert
    assertTrue(nettyWebSocket.isReady());
  }

  /**
   * Test {@link NettyWebSocket#processBufferedFrames()}.
   * <ul>
   *   <li>Given {@link ReadOnlyByteBuf#ReadOnlyByteBuf(ByteBuf)} with buffer is
   * {@link EmptyByteBuf#EmptyByteBuf(ByteBufAllocator)}.</li>
   * </ul>
   * <p>
   * Method under test: {@link NettyWebSocket#processBufferedFrames()}
   */
  @Test
  @DisplayName("Test processBufferedFrames(); given ReadOnlyByteBuf(ByteBuf) with buffer is EmptyByteBuf(ByteBufAllocator)")
  void testProcessBufferedFrames_givenReadOnlyByteBufWithBufferIsEmptyByteBuf() {
    // Arrange
    EmbeddedChannel channel = new EmbeddedChannel();

    NettyWebSocket nettyWebSocket = new NettyWebSocket(channel, new DefaultHttpHeaders());
    nettyWebSocket.bufferFrame(
        new BinaryWebSocketFrame(true, 1, new ReadOnlyByteBuf(new EmptyByteBuf(new AdaptiveByteBufAllocator()))));

    // Act
    nettyWebSocket.processBufferedFrames();

    // Assert
    assertTrue(nettyWebSocket.isReady());
  }

  /**
   * Test {@link NettyWebSocket#handleFrame(WebSocketFrame)}.
   * <p>
   * Method under test: {@link NettyWebSocket#handleFrame(WebSocketFrame)}
   */
  @Test
  @DisplayName("Test handleFrame(WebSocketFrame)")
  void testHandleFrame() {
    // Arrange
    WebSocketListener l = mock(WebSocketListener.class);
    doThrow(new IllegalArgumentException("foo")).when(l).onTextFrame(Mockito.<String>any(), anyBoolean(), anyInt());
    WebSocketListener l2 = mock(WebSocketListener.class);
    doNothing().when(l2).onTextFrame(Mockito.<String>any(), anyBoolean(), anyInt());
    EmbeddedChannel channel = new EmbeddedChannel();

    NettyWebSocket nettyWebSocket = new NettyWebSocket(channel, new DefaultHttpHeaders());
    nettyWebSocket.addWebSocketListener(l2);
    nettyWebSocket.addWebSocketListener(l);

    // Act and Assert
    assertThrows(IllegalArgumentException.class, () -> nettyWebSocket.handleFrame(new TextWebSocketFrame()));
    verify(l2).onTextFrame(eq(""), eq(true), eq(0));
    verify(l).onTextFrame(eq(""), eq(true), eq(0));
  }

  /**
   * Test {@link NettyWebSocket#handleFrame(WebSocketFrame)}.
   * <p>
   * Method under test: {@link NettyWebSocket#handleFrame(WebSocketFrame)}
   */
  @Test
  @DisplayName("Test handleFrame(WebSocketFrame)")
  void testHandleFrame2() {
    // Arrange
    WebSocketListener l = mock(WebSocketListener.class);
    doThrow(new IllegalArgumentException("foo")).when(l).onError(Mockito.<Throwable>any());
    doThrow(new IllegalArgumentException("foo")).when(l)
        .onClose(Mockito.<WebSocket>any(), anyInt(), Mockito.<String>any());
    WebSocketListener l2 = mock(WebSocketListener.class);
    doNothing().when(l2).onClose(Mockito.<WebSocket>any(), anyInt(), Mockito.<String>any());
    EmbeddedChannel channel = new EmbeddedChannel();

    NettyWebSocket nettyWebSocket = new NettyWebSocket(channel, new DefaultHttpHeaders());
    nettyWebSocket.addWebSocketListener(l2);
    nettyWebSocket.addWebSocketListener(l);

    // Act and Assert
    assertThrows(IllegalArgumentException.class, () -> nettyWebSocket.handleFrame(new CloseWebSocketFrame()));
    verify(l2).onClose(isA(WebSocket.class), eq(-1), eq(""));
    verify(l).onClose(isA(WebSocket.class), eq(-1), eq(""));
    verify(l).onError(isA(Throwable.class));
  }

  /**
   * Test {@link NettyWebSocket#handleFrame(WebSocketFrame)}.
   * <p>
   * Method under test: {@link NettyWebSocket#handleFrame(WebSocketFrame)}
   */
  @Test
  @DisplayName("Test handleFrame(WebSocketFrame)")
  void testHandleFrame3() {
    // Arrange
    AsyncHttpClientConfig config = mock(AsyncHttpClientConfig.class);
    when(config.isUseLaxCookieEncoder()).thenReturn(true);
    when(config.getMaxRedirects()).thenReturn(3);
    when(config.getIoExceptionFilters()).thenReturn(new ArrayList<>());
    when(config.getResponseFilters()).thenReturn(new ArrayList<>());
    EmbeddedChannel channel = new EmbeddedChannel(new HttpHandler(config, mock(ChannelManager.class), null));
    WebSocketListener l = mock(WebSocketListener.class);
    doThrow(new IllegalArgumentException("foo")).when(l).onPingFrame(Mockito.<byte[]>any());
    WebSocketListener l2 = mock(WebSocketListener.class);
    doNothing().when(l2).onPingFrame(Mockito.<byte[]>any());

    NettyWebSocket nettyWebSocket = new NettyWebSocket(channel, new DefaultHttpHeaders());
    nettyWebSocket.addWebSocketListener(l2);
    nettyWebSocket.addWebSocketListener(l);

    // Act and Assert
    assertThrows(IllegalArgumentException.class, () -> nettyWebSocket.handleFrame(new PingWebSocketFrame()));
    verify(config).getIoExceptionFilters();
    verify(config).getMaxRedirects();
    verify(config).getResponseFilters();
    verify(config).isUseLaxCookieEncoder();
    verify(l2).onPingFrame(isA(byte[].class));
    verify(l).onPingFrame(isA(byte[].class));
  }

  /**
   * Test {@link NettyWebSocket#handleFrame(WebSocketFrame)}.
   * <p>
   * Method under test: {@link NettyWebSocket#handleFrame(WebSocketFrame)}
   */
  @Test
  @DisplayName("Test handleFrame(WebSocketFrame)")
  void testHandleFrame4() {
    // Arrange
    AsyncHttpClientConfig config = mock(AsyncHttpClientConfig.class);
    when(config.isUseLaxCookieEncoder()).thenReturn(true);
    when(config.getMaxRedirects()).thenReturn(3);
    when(config.getIoExceptionFilters()).thenReturn(new ArrayList<>());
    when(config.getResponseFilters()).thenReturn(new ArrayList<>());
    EmbeddedChannel channel = new EmbeddedChannel(new HttpHandler(config, mock(ChannelManager.class), null));
    WebSocketListener l = mock(WebSocketListener.class);
    doThrow(new IllegalArgumentException("foo")).when(l).onPongFrame(Mockito.<byte[]>any());
    WebSocketListener l2 = mock(WebSocketListener.class);
    doNothing().when(l2).onPongFrame(Mockito.<byte[]>any());

    NettyWebSocket nettyWebSocket = new NettyWebSocket(channel, new DefaultHttpHeaders());
    nettyWebSocket.addWebSocketListener(l2);
    nettyWebSocket.addWebSocketListener(l);

    // Act and Assert
    assertThrows(IllegalArgumentException.class, () -> nettyWebSocket.handleFrame(new PongWebSocketFrame()));
    verify(config).getIoExceptionFilters();
    verify(config).getMaxRedirects();
    verify(config).getResponseFilters();
    verify(config).isUseLaxCookieEncoder();
    verify(l2).onPongFrame(isA(byte[].class));
    verify(l).onPongFrame(isA(byte[].class));
  }

  /**
   * Test {@link NettyWebSocket#handleFrame(WebSocketFrame)}.
   * <ul>
   *   <li>Given {@link ChannelManager} {@link ChannelManager#closeChannel(Channel)}
   * does nothing.</li>
   *   <li>Then calls {@link ChannelManager#closeChannel(Channel)}.</li>
   * </ul>
   * <p>
   * Method under test: {@link NettyWebSocket#handleFrame(WebSocketFrame)}
   */
  @Test
  @DisplayName("Test handleFrame(WebSocketFrame); given ChannelManager closeChannel(Channel) does nothing; then calls closeChannel(Channel)")
  void testHandleFrame_givenChannelManagerCloseChannelDoesNothing_thenCallsCloseChannel() {
    // Arrange
    AsyncHttpClientConfig config = mock(AsyncHttpClientConfig.class);
    when(config.isUseLaxCookieEncoder()).thenReturn(true);
    when(config.getMaxRedirects()).thenReturn(3);
    when(config.getIoExceptionFilters()).thenReturn(new ArrayList<>());
    when(config.getResponseFilters()).thenReturn(new ArrayList<>());
    ChannelManager channelManager = mock(ChannelManager.class);
    doNothing().when(channelManager).closeChannel(Mockito.<Channel>any());
    EmbeddedChannel channel = new EmbeddedChannel(new HttpHandler(config, channelManager, null));
    WebSocketListener l = mock(WebSocketListener.class);
    doNothing().when(l).onError(Mockito.<Throwable>any());
    doThrow(new IllegalArgumentException("foo")).when(l)
        .onClose(Mockito.<WebSocket>any(), anyInt(), Mockito.<String>any());
    WebSocketListener l2 = mock(WebSocketListener.class);
    doNothing().when(l2).onClose(Mockito.<WebSocket>any(), anyInt(), Mockito.<String>any());

    NettyWebSocket nettyWebSocket = new NettyWebSocket(channel, new DefaultHttpHeaders());
    nettyWebSocket.addWebSocketListener(l2);
    nettyWebSocket.addWebSocketListener(l);

    // Act
    nettyWebSocket.handleFrame(new CloseWebSocketFrame());

    // Assert
    verify(config).getIoExceptionFilters();
    verify(config).getMaxRedirects();
    verify(config).getResponseFilters();
    verify(config).isUseLaxCookieEncoder();
    verify(channelManager).closeChannel(isA(Channel.class));
    verify(l2).onClose(isA(WebSocket.class), eq(-1), eq(""));
    verify(l).onClose(isA(WebSocket.class), eq(-1), eq(""));
    verify(l).onError(isA(Throwable.class));
  }

  /**
   * Test {@link NettyWebSocket#handleFrame(WebSocketFrame)}.
   * <ul>
   *   <li>Then calls {@link WebSocketListener#onPingFrame(byte[])}.</li>
   * </ul>
   * <p>
   * Method under test: {@link NettyWebSocket#handleFrame(WebSocketFrame)}
   */
  @Test
  @DisplayName("Test handleFrame(WebSocketFrame); then calls onPingFrame(byte[])")
  void testHandleFrame_thenCallsOnPingFrame() {
    // Arrange
    AsyncHttpClientConfig config = mock(AsyncHttpClientConfig.class);
    when(config.isUseLaxCookieEncoder()).thenReturn(true);
    when(config.getMaxRedirects()).thenReturn(3);
    when(config.getIoExceptionFilters()).thenReturn(new ArrayList<>());
    when(config.getResponseFilters()).thenReturn(new ArrayList<>());
    EmbeddedChannel channel = new EmbeddedChannel(new HttpHandler(config, mock(ChannelManager.class), null));
    WebSocketListener l = mock(WebSocketListener.class);
    doNothing().when(l).onPingFrame(Mockito.<byte[]>any());
    WebSocketListener l2 = mock(WebSocketListener.class);
    doNothing().when(l2).onPingFrame(Mockito.<byte[]>any());

    NettyWebSocket nettyWebSocket = new NettyWebSocket(channel, new DefaultHttpHeaders());
    nettyWebSocket.addWebSocketListener(l2);
    nettyWebSocket.addWebSocketListener(l);

    // Act
    nettyWebSocket.handleFrame(new PingWebSocketFrame());

    // Assert
    verify(config).getIoExceptionFilters();
    verify(config).getMaxRedirects();
    verify(config).getResponseFilters();
    verify(config).isUseLaxCookieEncoder();
    verify(l2).onPingFrame(isA(byte[].class));
    verify(l).onPingFrame(isA(byte[].class));
  }

  /**
   * Test {@link NettyWebSocket#handleFrame(WebSocketFrame)}.
   * <ul>
   *   <li>Then calls {@link WebSocketListener#onPongFrame(byte[])}.</li>
   * </ul>
   * <p>
   * Method under test: {@link NettyWebSocket#handleFrame(WebSocketFrame)}
   */
  @Test
  @DisplayName("Test handleFrame(WebSocketFrame); then calls onPongFrame(byte[])")
  void testHandleFrame_thenCallsOnPongFrame() {
    // Arrange
    AsyncHttpClientConfig config = mock(AsyncHttpClientConfig.class);
    when(config.isUseLaxCookieEncoder()).thenReturn(true);
    when(config.getMaxRedirects()).thenReturn(3);
    when(config.getIoExceptionFilters()).thenReturn(new ArrayList<>());
    when(config.getResponseFilters()).thenReturn(new ArrayList<>());
    EmbeddedChannel channel = new EmbeddedChannel(new HttpHandler(config, mock(ChannelManager.class), null));
    WebSocketListener l = mock(WebSocketListener.class);
    doNothing().when(l).onPongFrame(Mockito.<byte[]>any());
    WebSocketListener l2 = mock(WebSocketListener.class);
    doNothing().when(l2).onPongFrame(Mockito.<byte[]>any());

    NettyWebSocket nettyWebSocket = new NettyWebSocket(channel, new DefaultHttpHeaders());
    nettyWebSocket.addWebSocketListener(l2);
    nettyWebSocket.addWebSocketListener(l);

    // Act
    nettyWebSocket.handleFrame(new PongWebSocketFrame());

    // Assert
    verify(config).getIoExceptionFilters();
    verify(config).getMaxRedirects();
    verify(config).getResponseFilters();
    verify(config).isUseLaxCookieEncoder();
    verify(l2).onPongFrame(isA(byte[].class));
    verify(l).onPongFrame(isA(byte[].class));
  }

  /**
   * Test {@link NettyWebSocket#handleFrame(WebSocketFrame)}.
   * <ul>
   *   <li>When {@link BinaryWebSocketFrame#BinaryWebSocketFrame()}.</li>
   *   <li>Then calls
   * {@link WebSocketListener#onBinaryFrame(byte[], boolean, int)}.</li>
   * </ul>
   * <p>
   * Method under test: {@link NettyWebSocket#handleFrame(WebSocketFrame)}
   */
  @Test
  @DisplayName("Test handleFrame(WebSocketFrame); when BinaryWebSocketFrame(); then calls onBinaryFrame(byte[], boolean, int)")
  void testHandleFrame_whenBinaryWebSocketFrame_thenCallsOnBinaryFrame() {
    // Arrange
    WebSocketListener l = mock(WebSocketListener.class);
    doNothing().when(l).onBinaryFrame(Mockito.<byte[]>any(), anyBoolean(), anyInt());
    EmbeddedChannel channel = new EmbeddedChannel();

    NettyWebSocket nettyWebSocket = new NettyWebSocket(channel, new DefaultHttpHeaders());
    nettyWebSocket.addWebSocketListener(l);

    // Act
    nettyWebSocket.handleFrame(new BinaryWebSocketFrame());

    // Assert
    verify(l).onBinaryFrame(isA(byte[].class), eq(true), eq(0));
  }

  /**
   * Test {@link NettyWebSocket#handleFrame(WebSocketFrame)}.
   * <ul>
   *   <li>When {@link BinaryWebSocketFrame#BinaryWebSocketFrame()}.</li>
   *   <li>Then calls
   * {@link WebSocketListener#onBinaryFrame(byte[], boolean, int)}.</li>
   * </ul>
   * <p>
   * Method under test: {@link NettyWebSocket#handleFrame(WebSocketFrame)}
   */
  @Test
  @DisplayName("Test handleFrame(WebSocketFrame); when BinaryWebSocketFrame(); then calls onBinaryFrame(byte[], boolean, int)")
  void testHandleFrame_whenBinaryWebSocketFrame_thenCallsOnBinaryFrame2() {
    // Arrange
    WebSocketListener l = mock(WebSocketListener.class);
    doNothing().when(l).onBinaryFrame(Mockito.<byte[]>any(), anyBoolean(), anyInt());
    WebSocketListener l2 = mock(WebSocketListener.class);
    doThrow(new IllegalArgumentException("foo")).when(l2).onBinaryFrame(Mockito.<byte[]>any(), anyBoolean(), anyInt());
    EmbeddedChannel channel = new EmbeddedChannel();

    NettyWebSocket nettyWebSocket = new NettyWebSocket(channel, new DefaultHttpHeaders());
    nettyWebSocket.addWebSocketListener(l2);
    nettyWebSocket.addWebSocketListener(l);

    // Act and Assert
    assertThrows(IllegalArgumentException.class, () -> nettyWebSocket.handleFrame(new BinaryWebSocketFrame()));
    verify(l2).onBinaryFrame(isA(byte[].class), eq(true), eq(0));
  }

  /**
   * Test {@link NettyWebSocket#handleFrame(WebSocketFrame)}.
   * <ul>
   *   <li>When {@link CloseWebSocketFrame#CloseWebSocketFrame()}.</li>
   *   <li>Then calls
   * {@link WebSocketListener#onClose(WebSocket, int, String)}.</li>
   * </ul>
   * <p>
   * Method under test: {@link NettyWebSocket#handleFrame(WebSocketFrame)}
   */
  @Test
  @DisplayName("Test handleFrame(WebSocketFrame); when CloseWebSocketFrame(); then calls onClose(WebSocket, int, String)")
  void testHandleFrame_whenCloseWebSocketFrame_thenCallsOnClose() {
    // Arrange
    WebSocketListener l = mock(WebSocketListener.class);
    doNothing().when(l).onClose(Mockito.<WebSocket>any(), anyInt(), Mockito.<String>any());
    WebSocketListener l2 = mock(WebSocketListener.class);
    doNothing().when(l2).onClose(Mockito.<WebSocket>any(), anyInt(), Mockito.<String>any());
    EmbeddedChannel channel = new EmbeddedChannel();

    NettyWebSocket nettyWebSocket = new NettyWebSocket(channel, new DefaultHttpHeaders());
    nettyWebSocket.addWebSocketListener(l2);
    nettyWebSocket.addWebSocketListener(l);

    // Act
    nettyWebSocket.handleFrame(new CloseWebSocketFrame());

    // Assert
    verify(l2).onClose(isA(WebSocket.class), eq(-1), eq(""));
    verify(l).onClose(isA(WebSocket.class), eq(-1), eq(""));
  }

  /**
   * Test {@link NettyWebSocket#handleFrame(WebSocketFrame)}.
   * <ul>
   *   <li>When {@link CloseWebSocketFrame#CloseWebSocketFrame()}.</li>
   *   <li>Then calls {@link WebSocketListener#onError(Throwable)}.</li>
   * </ul>
   * <p>
   * Method under test: {@link NettyWebSocket#handleFrame(WebSocketFrame)}
   */
  @Test
  @DisplayName("Test handleFrame(WebSocketFrame); when CloseWebSocketFrame(); then calls onError(Throwable)")
  void testHandleFrame_whenCloseWebSocketFrame_thenCallsOnError() {
    // Arrange
    WebSocketListener l = mock(WebSocketListener.class);
    doNothing().when(l).onError(Mockito.<Throwable>any());
    doThrow(new IllegalArgumentException("foo")).when(l)
        .onClose(Mockito.<WebSocket>any(), anyInt(), Mockito.<String>any());
    WebSocketListener l2 = mock(WebSocketListener.class);
    doNothing().when(l2).onClose(Mockito.<WebSocket>any(), anyInt(), Mockito.<String>any());
    EmbeddedChannel channel = new EmbeddedChannel();

    NettyWebSocket nettyWebSocket = new NettyWebSocket(channel, new DefaultHttpHeaders());
    nettyWebSocket.addWebSocketListener(l2);
    nettyWebSocket.addWebSocketListener(l);

    // Act
    nettyWebSocket.handleFrame(new CloseWebSocketFrame());

    // Assert
    verify(l2).onClose(isA(WebSocket.class), eq(-1), eq(""));
    verify(l).onClose(isA(WebSocket.class), eq(-1), eq(""));
    verify(l).onError(isA(Throwable.class));
  }

  /**
   * Test {@link NettyWebSocket#handleFrame(WebSocketFrame)}.
   * <ul>
   *   <li>When
   * {@link ContinuationWebSocketFrame#ContinuationWebSocketFrame()}.</li>
   *   <li>Then calls {@link AsyncHttpClientConfig#getIoExceptionFilters()}.</li>
   * </ul>
   * <p>
   * Method under test: {@link NettyWebSocket#handleFrame(WebSocketFrame)}
   */
  @Test
  @DisplayName("Test handleFrame(WebSocketFrame); when ContinuationWebSocketFrame(); then calls getIoExceptionFilters()")
  void testHandleFrame_whenContinuationWebSocketFrame_thenCallsGetIoExceptionFilters() {
    // Arrange
    AsyncHttpClientConfig config = mock(AsyncHttpClientConfig.class);
    when(config.isUseLaxCookieEncoder()).thenReturn(true);
    when(config.getMaxRedirects()).thenReturn(3);
    when(config.getIoExceptionFilters()).thenReturn(new ArrayList<>());
    when(config.getResponseFilters()).thenReturn(new ArrayList<>());
    EmbeddedChannel channel = new EmbeddedChannel(new HttpHandler(config, mock(ChannelManager.class), null));

    NettyWebSocket nettyWebSocket = new NettyWebSocket(channel, new DefaultHttpHeaders());
    nettyWebSocket.addWebSocketListener(mock(WebSocketListener.class));
    nettyWebSocket.addWebSocketListener(mock(WebSocketListener.class));

    // Act
    nettyWebSocket.handleFrame(new ContinuationWebSocketFrame());

    // Assert that nothing has changed
    verify(config).getIoExceptionFilters();
    verify(config).getMaxRedirects();
    verify(config).getResponseFilters();
    verify(config).isUseLaxCookieEncoder();
  }

  /**
   * Test {@link NettyWebSocket#handleFrame(WebSocketFrame)}.
   * <ul>
   *   <li>When {@link DuplicatedByteBuf#DuplicatedByteBuf(ByteBuf)} with buffer is
   * {@link EmptyByteBuf#EmptyByteBuf(ByteBufAllocator)}.</li>
   * </ul>
   * <p>
   * Method under test: {@link NettyWebSocket#handleFrame(WebSocketFrame)}
   */
  @Test
  @DisplayName("Test handleFrame(WebSocketFrame); when DuplicatedByteBuf(ByteBuf) with buffer is EmptyByteBuf(ByteBufAllocator)")
  void testHandleFrame_whenDuplicatedByteBufWithBufferIsEmptyByteBuf() {
    // Arrange
    WebSocketListener l = mock(WebSocketListener.class);
    doNothing().when(l).onBinaryFrame(Mockito.<byte[]>any(), anyBoolean(), anyInt());
    WebSocketListener l2 = mock(WebSocketListener.class);
    doThrow(new IllegalArgumentException("foo")).when(l2).onBinaryFrame(Mockito.<byte[]>any(), anyBoolean(), anyInt());
    EmbeddedChannel channel = new EmbeddedChannel();

    NettyWebSocket nettyWebSocket = new NettyWebSocket(channel, new DefaultHttpHeaders());
    nettyWebSocket.addWebSocketListener(l2);
    nettyWebSocket.addWebSocketListener(l);

    // Act and Assert
    assertThrows(IllegalArgumentException.class, () -> nettyWebSocket.handleFrame(
        new BinaryWebSocketFrame(new DuplicatedByteBuf(new EmptyByteBuf(new AdaptiveByteBufAllocator())))));
    verify(l2).onBinaryFrame(isA(byte[].class), eq(true), eq(0));
  }

  /**
   * Test {@link NettyWebSocket#handleFrame(WebSocketFrame)}.
   * <ul>
   *   <li>When {@link ReadOnlyByteBuf#ReadOnlyByteBuf(ByteBuf)} with buffer is
   * {@link EmptyByteBuf#EmptyByteBuf(ByteBufAllocator)}.</li>
   * </ul>
   * <p>
   * Method under test: {@link NettyWebSocket#handleFrame(WebSocketFrame)}
   */
  @Test
  @DisplayName("Test handleFrame(WebSocketFrame); when ReadOnlyByteBuf(ByteBuf) with buffer is EmptyByteBuf(ByteBufAllocator)")
  void testHandleFrame_whenReadOnlyByteBufWithBufferIsEmptyByteBuf() {
    // Arrange
    AsyncHttpClientConfig config = mock(AsyncHttpClientConfig.class);
    when(config.isUseLaxCookieEncoder()).thenReturn(true);
    when(config.getMaxRedirects()).thenReturn(3);
    when(config.getIoExceptionFilters()).thenReturn(new ArrayList<>());
    when(config.getResponseFilters()).thenReturn(new ArrayList<>());
    EmbeddedChannel channel = new EmbeddedChannel(new HttpHandler(config, mock(ChannelManager.class), null));
    WebSocketListener l = mock(WebSocketListener.class);
    doNothing().when(l).onBinaryFrame(Mockito.<byte[]>any(), anyBoolean(), anyInt());
    WebSocketListener l2 = mock(WebSocketListener.class);
    doThrow(new IllegalArgumentException("foo")).when(l2).onBinaryFrame(Mockito.<byte[]>any(), anyBoolean(), anyInt());

    NettyWebSocket nettyWebSocket = new NettyWebSocket(channel, new DefaultHttpHeaders());
    nettyWebSocket.addWebSocketListener(l2);
    nettyWebSocket.addWebSocketListener(l);

    // Act and Assert
    assertThrows(IllegalArgumentException.class, () -> nettyWebSocket.handleFrame(
        new BinaryWebSocketFrame(true, 1, new ReadOnlyByteBuf(new EmptyByteBuf(new AdaptiveByteBufAllocator())))));
    verify(config).getIoExceptionFilters();
    verify(config).getMaxRedirects();
    verify(config).getResponseFilters();
    verify(config).isUseLaxCookieEncoder();
    verify(l2).onBinaryFrame(isA(byte[].class), eq(true), eq(1));
  }

  /**
   * Test {@link NettyWebSocket#handleFrame(WebSocketFrame)}.
   * <ul>
   *   <li>When {@link TextWebSocketFrame#TextWebSocketFrame(String)} with text is
   * {@code https://example.org/example}.</li>
   * </ul>
   * <p>
   * Method under test: {@link NettyWebSocket#handleFrame(WebSocketFrame)}
   */
  @Test
  @DisplayName("Test handleFrame(WebSocketFrame); when TextWebSocketFrame(String) with text is 'https://example.org/example'")
  void testHandleFrame_whenTextWebSocketFrameWithTextIsHttpsExampleOrgExample() {
    // Arrange
    WebSocketListener l = mock(WebSocketListener.class);
    doNothing().when(l).onTextFrame(Mockito.<String>any(), anyBoolean(), anyInt());
    WebSocketListener l2 = mock(WebSocketListener.class);
    doNothing().when(l2).onTextFrame(Mockito.<String>any(), anyBoolean(), anyInt());
    EmbeddedChannel channel = new EmbeddedChannel();

    NettyWebSocket nettyWebSocket = new NettyWebSocket(channel, new DefaultHttpHeaders());
    nettyWebSocket.addWebSocketListener(l2);
    nettyWebSocket.addWebSocketListener(l);

    // Act
    nettyWebSocket.handleFrame(new TextWebSocketFrame("https://example.org/example"));

    // Assert
    verify(l2).onTextFrame(eq("https://example.org/example"), eq(true), eq(0));
    verify(l).onTextFrame(eq("https://example.org/example"), eq(true), eq(0));
  }

  /**
   * Test {@link NettyWebSocket#handleFrame(WebSocketFrame)}.
   * <ul>
   *   <li>When {@link TextWebSocketFrame#TextWebSocketFrame()}.</li>
   *   <li>Then calls
   * {@link WebSocketListener#onTextFrame(String, boolean, int)}.</li>
   * </ul>
   * <p>
   * Method under test: {@link NettyWebSocket#handleFrame(WebSocketFrame)}
   */
  @Test
  @DisplayName("Test handleFrame(WebSocketFrame); when TextWebSocketFrame(); then calls onTextFrame(String, boolean, int)")
  void testHandleFrame_whenTextWebSocketFrame_thenCallsOnTextFrame() {
    // Arrange
    WebSocketListener l = mock(WebSocketListener.class);
    doNothing().when(l).onTextFrame(Mockito.<String>any(), anyBoolean(), anyInt());
    WebSocketListener l2 = mock(WebSocketListener.class);
    doNothing().when(l2).onTextFrame(Mockito.<String>any(), anyBoolean(), anyInt());
    EmbeddedChannel channel = new EmbeddedChannel();

    NettyWebSocket nettyWebSocket = new NettyWebSocket(channel, new DefaultHttpHeaders());
    nettyWebSocket.addWebSocketListener(l2);
    nettyWebSocket.addWebSocketListener(l);

    // Act
    nettyWebSocket.handleFrame(new TextWebSocketFrame());

    // Assert
    verify(l2).onTextFrame(eq(""), eq(true), eq(0));
    verify(l).onTextFrame(eq(""), eq(true), eq(0));
  }

  /**
   * Test {@link NettyWebSocket#onError(Throwable)}.
   * <p>
   * Method under test: {@link NettyWebSocket#onError(Throwable)}
   */
  @Test
  @DisplayName("Test onError(Throwable)")
  void testOnError() {
    // Arrange
    WebSocketListener l = mock(WebSocketListener.class);
    doNothing().when(l).onError(Mockito.<Throwable>any());
    WebSocketListener l2 = mock(WebSocketListener.class);
    doThrow(new IllegalArgumentException("foo")).when(l2).onError(Mockito.<Throwable>any());
    EmbeddedChannel channel = new EmbeddedChannel();

    NettyWebSocket nettyWebSocket = new NettyWebSocket(channel, new DefaultHttpHeaders());
    nettyWebSocket.bufferFrame(new BinaryWebSocketFrame());
    nettyWebSocket.addWebSocketListener(l2);
    nettyWebSocket.addWebSocketListener(l);

    // Act
    nettyWebSocket.onError(ChannelClosedException.INSTANCE);

    // Assert
    verify(l2).onError(isA(Throwable.class));
    verify(l).onError(isA(Throwable.class));
  }

  /**
   * Test {@link NettyWebSocket#onError(Throwable)}.
   * <p>
   * Method under test: {@link NettyWebSocket#onError(Throwable)}
   */
  @Test
  @DisplayName("Test onError(Throwable)")
  void testOnError2() {
    // Arrange
    WebSocketListener l = mock(WebSocketListener.class);
    doNothing().when(l).onError(Mockito.<Throwable>any());
    WebSocketListener l2 = mock(WebSocketListener.class);
    doThrow(new IllegalArgumentException("foo")).when(l2).onError(Mockito.<Throwable>any());
    ByteBuf buffer = mock(ByteBuf.class);
    when(buffer.release()).thenReturn(true);
    when(buffer.capacity()).thenReturn(3);
    when(buffer.maxCapacity()).thenReturn(3);
    when(buffer.readerIndex()).thenReturn(1);
    when(buffer.writerIndex()).thenReturn(1);
    when(buffer.retain()).thenReturn(new DuplicatedByteBuf(new EmptyByteBuf(new AdaptiveByteBufAllocator())));
    BinaryWebSocketFrame frame = new BinaryWebSocketFrame(true, 1, new DuplicatedByteBuf(buffer));

    EmbeddedChannel channel = new EmbeddedChannel();

    NettyWebSocket nettyWebSocket = new NettyWebSocket(channel, new DefaultHttpHeaders());
    nettyWebSocket.bufferFrame(frame);
    nettyWebSocket.addWebSocketListener(l2);
    nettyWebSocket.addWebSocketListener(l);

    // Act
    nettyWebSocket.onError(ChannelClosedException.INSTANCE);

    // Assert
    verify(buffer).capacity();
    verify(buffer).maxCapacity();
    verify(buffer).readerIndex();
    verify(buffer).retain();
    verify(buffer).writerIndex();
    verify(buffer).release();
    verify(l2).onError(isA(Throwable.class));
    verify(l).onError(isA(Throwable.class));
  }

  /**
   * Test {@link NettyWebSocket#onError(Throwable)}.
   * <ul>
   *   <li>Given {@link ReadOnlyByteBuf#ReadOnlyByteBuf(ByteBuf)} with buffer is
   * {@link DuplicatedByteBuf#DuplicatedByteBuf(ByteBuf)}.</li>
   *   <li>Then calls {@link ByteBuf#capacity()}.</li>
   * </ul>
   * <p>
   * Method under test: {@link NettyWebSocket#onError(Throwable)}
   */
  @Test
  @DisplayName("Test onError(Throwable); given ReadOnlyByteBuf(ByteBuf) with buffer is DuplicatedByteBuf(ByteBuf); then calls capacity()")
  void testOnError_givenReadOnlyByteBufWithBufferIsDuplicatedByteBuf_thenCallsCapacity() {
    // Arrange
    WebSocketListener l = mock(WebSocketListener.class);
    doNothing().when(l).onError(Mockito.<Throwable>any());
    WebSocketListener l2 = mock(WebSocketListener.class);
    doThrow(new IllegalArgumentException("foo")).when(l2).onError(Mockito.<Throwable>any());
    ByteBuf buffer = mock(ByteBuf.class);
    when(buffer.release()).thenReturn(true);
    when(buffer.capacity()).thenReturn(3);
    when(buffer.maxCapacity()).thenReturn(3);
    when(buffer.readerIndex()).thenReturn(1);
    when(buffer.writerIndex()).thenReturn(1);
    when(buffer.retain()).thenReturn(new DuplicatedByteBuf(new EmptyByteBuf(new AdaptiveByteBufAllocator())));
    BinaryWebSocketFrame frame = new BinaryWebSocketFrame(true, 1, new ReadOnlyByteBuf(new DuplicatedByteBuf(buffer)));

    EmbeddedChannel channel = new EmbeddedChannel();

    NettyWebSocket nettyWebSocket = new NettyWebSocket(channel, new DefaultHttpHeaders());
    nettyWebSocket.bufferFrame(frame);
    nettyWebSocket.addWebSocketListener(l2);
    nettyWebSocket.addWebSocketListener(l);

    // Act
    nettyWebSocket.onError(ChannelClosedException.INSTANCE);

    // Assert
    verify(buffer, atLeast(1)).capacity();
    verify(buffer).maxCapacity();
    verify(buffer).readerIndex();
    verify(buffer).retain();
    verify(buffer).writerIndex();
    verify(buffer).release();
    verify(l2).onError(isA(Throwable.class));
    verify(l).onError(isA(Throwable.class));
  }

  /**
   * Test {@link NettyWebSocket#onError(Throwable)}.
   * <ul>
   *   <li>Given {@link WebSocketListener}
   * {@link WebSocketListener#onError(Throwable)} does nothing.</li>
   *   <li>Then calls {@link WebSocketListener#onError(Throwable)}.</li>
   * </ul>
   * <p>
   * Method under test: {@link NettyWebSocket#onError(Throwable)}
   */
  @Test
  @DisplayName("Test onError(Throwable); given WebSocketListener onError(Throwable) does nothing; then calls onError(Throwable)")
  void testOnError_givenWebSocketListenerOnErrorDoesNothing_thenCallsOnError() {
    // Arrange
    WebSocketListener l = mock(WebSocketListener.class);
    doNothing().when(l).onError(Mockito.<Throwable>any());
    EmbeddedChannel channel = new EmbeddedChannel();

    NettyWebSocket nettyWebSocket = new NettyWebSocket(channel, new DefaultHttpHeaders());
    nettyWebSocket.addWebSocketListener(l);

    // Act
    nettyWebSocket.onError(ChannelClosedException.INSTANCE);

    // Assert
    verify(l).onError(isA(Throwable.class));
  }

  /**
   * Test {@link NettyWebSocket#onError(Throwable)}.
   * <ul>
   *   <li>Given {@link WebSocketListener}
   * {@link WebSocketListener#onError(Throwable)} throw
   * {@link IllegalArgumentException#IllegalArgumentException(String)} with
   * {@code foo}.</li>
   * </ul>
   * <p>
   * Method under test: {@link NettyWebSocket#onError(Throwable)}
   */
  @Test
  @DisplayName("Test onError(Throwable); given WebSocketListener onError(Throwable) throw IllegalArgumentException(String) with 'foo'")
  void testOnError_givenWebSocketListenerOnErrorThrowIllegalArgumentExceptionWithFoo() {
    // Arrange
    WebSocketListener l = mock(WebSocketListener.class);
    doNothing().when(l).onError(Mockito.<Throwable>any());
    WebSocketListener l2 = mock(WebSocketListener.class);
    doThrow(new IllegalArgumentException("foo")).when(l2).onError(Mockito.<Throwable>any());
    EmbeddedChannel channel = new EmbeddedChannel();

    NettyWebSocket nettyWebSocket = new NettyWebSocket(channel, new DefaultHttpHeaders());
    nettyWebSocket.addWebSocketListener(l2);
    nettyWebSocket.addWebSocketListener(l);

    // Act
    nettyWebSocket.onError(ChannelClosedException.INSTANCE);

    // Assert
    verify(l2).onError(isA(Throwable.class));
    verify(l).onError(isA(Throwable.class));
  }

  /**
   * Test {@link NettyWebSocket#onClose(int, String)}.
   * <p>
   * Method under test: {@link NettyWebSocket#onClose(int, String)}
   */
  @Test
  @DisplayName("Test onClose(int, String)")
  void testOnClose() {
    // Arrange
    WebSocketListener l = mock(WebSocketListener.class);
    doNothing().when(l).onClose(Mockito.<WebSocket>any(), anyInt(), Mockito.<String>any());
    WebSocketListener l2 = mock(WebSocketListener.class);
    doNothing().when(l2).onError(Mockito.<Throwable>any());
    doThrow(new IllegalArgumentException("foo")).when(l2)
        .onClose(Mockito.<WebSocket>any(), anyInt(), Mockito.<String>any());
    EmbeddedChannel channel = new EmbeddedChannel();

    NettyWebSocket nettyWebSocket = new NettyWebSocket(channel, new DefaultHttpHeaders());
    nettyWebSocket.bufferFrame(new BinaryWebSocketFrame());
    nettyWebSocket.addWebSocketListener(l2);
    nettyWebSocket.addWebSocketListener(l);

    // Act
    nettyWebSocket.onClose(1, "https://example.org/example");

    // Assert
    verify(l2).onClose(isA(WebSocket.class), eq(1), eq("https://example.org/example"));
    verify(l).onClose(isA(WebSocket.class), eq(1), eq("https://example.org/example"));
    verify(l2).onError(isA(Throwable.class));
  }

  /**
   * Test {@link NettyWebSocket#onClose(int, String)}.
   * <p>
   * Method under test: {@link NettyWebSocket#onClose(int, String)}
   */
  @Test
  @DisplayName("Test onClose(int, String)")
  void testOnClose2() {
    // Arrange
    WebSocketListener l = mock(WebSocketListener.class);
    doNothing().when(l).onClose(Mockito.<WebSocket>any(), anyInt(), Mockito.<String>any());
    WebSocketListener l2 = mock(WebSocketListener.class);
    doNothing().when(l2).onError(Mockito.<Throwable>any());
    doThrow(new IllegalArgumentException("foo")).when(l2)
        .onClose(Mockito.<WebSocket>any(), anyInt(), Mockito.<String>any());
    ByteBuf buffer = mock(ByteBuf.class);
    when(buffer.release()).thenReturn(true);
    when(buffer.capacity()).thenReturn(3);
    when(buffer.maxCapacity()).thenReturn(3);
    when(buffer.readerIndex()).thenReturn(1);
    when(buffer.writerIndex()).thenReturn(1);
    when(buffer.retain()).thenReturn(new DuplicatedByteBuf(new EmptyByteBuf(new AdaptiveByteBufAllocator())));
    BinaryWebSocketFrame frame = new BinaryWebSocketFrame(true, 1, new DuplicatedByteBuf(buffer));

    EmbeddedChannel channel = new EmbeddedChannel();

    NettyWebSocket nettyWebSocket = new NettyWebSocket(channel, new DefaultHttpHeaders());
    nettyWebSocket.bufferFrame(frame);
    nettyWebSocket.addWebSocketListener(l2);
    nettyWebSocket.addWebSocketListener(l);

    // Act
    nettyWebSocket.onClose(1, "https://example.org/example");

    // Assert
    verify(buffer).capacity();
    verify(buffer).maxCapacity();
    verify(buffer).readerIndex();
    verify(buffer).retain();
    verify(buffer).writerIndex();
    verify(buffer).release();
    verify(l2).onClose(isA(WebSocket.class), eq(1), eq("https://example.org/example"));
    verify(l).onClose(isA(WebSocket.class), eq(1), eq("https://example.org/example"));
    verify(l2).onError(isA(Throwable.class));
  }

  /**
   * Test {@link NettyWebSocket#onClose(int, String)}.
   * <ul>
   *   <li>Given {@link ReadOnlyByteBuf#ReadOnlyByteBuf(ByteBuf)} with buffer is
   * {@link DuplicatedByteBuf#DuplicatedByteBuf(ByteBuf)}.</li>
   *   <li>Then calls {@link ByteBuf#capacity()}.</li>
   * </ul>
   * <p>
   * Method under test: {@link NettyWebSocket#onClose(int, String)}
   */
  @Test
  @DisplayName("Test onClose(int, String); given ReadOnlyByteBuf(ByteBuf) with buffer is DuplicatedByteBuf(ByteBuf); then calls capacity()")
  void testOnClose_givenReadOnlyByteBufWithBufferIsDuplicatedByteBuf_thenCallsCapacity() {
    // Arrange
    WebSocketListener l = mock(WebSocketListener.class);
    doNothing().when(l).onClose(Mockito.<WebSocket>any(), anyInt(), Mockito.<String>any());
    WebSocketListener l2 = mock(WebSocketListener.class);
    doNothing().when(l2).onError(Mockito.<Throwable>any());
    doThrow(new IllegalArgumentException("foo")).when(l2)
        .onClose(Mockito.<WebSocket>any(), anyInt(), Mockito.<String>any());
    ByteBuf buffer = mock(ByteBuf.class);
    when(buffer.release()).thenReturn(true);
    when(buffer.capacity()).thenReturn(3);
    when(buffer.maxCapacity()).thenReturn(3);
    when(buffer.readerIndex()).thenReturn(1);
    when(buffer.writerIndex()).thenReturn(1);
    when(buffer.retain()).thenReturn(new DuplicatedByteBuf(new EmptyByteBuf(new AdaptiveByteBufAllocator())));
    BinaryWebSocketFrame frame = new BinaryWebSocketFrame(true, 1, new ReadOnlyByteBuf(new DuplicatedByteBuf(buffer)));

    EmbeddedChannel channel = new EmbeddedChannel();

    NettyWebSocket nettyWebSocket = new NettyWebSocket(channel, new DefaultHttpHeaders());
    nettyWebSocket.bufferFrame(frame);
    nettyWebSocket.addWebSocketListener(l2);
    nettyWebSocket.addWebSocketListener(l);

    // Act
    nettyWebSocket.onClose(1, "https://example.org/example");

    // Assert
    verify(buffer, atLeast(1)).capacity();
    verify(buffer).maxCapacity();
    verify(buffer).readerIndex();
    verify(buffer).retain();
    verify(buffer).writerIndex();
    verify(buffer).release();
    verify(l2).onClose(isA(WebSocket.class), eq(1), eq("https://example.org/example"));
    verify(l).onClose(isA(WebSocket.class), eq(1), eq("https://example.org/example"));
    verify(l2).onError(isA(Throwable.class));
  }

  /**
   * Test {@link NettyWebSocket#onClose(int, String)}.
   * <ul>
   *   <li>Given {@link WebSocketListener}
   * {@link WebSocketListener#onClose(WebSocket, int, String)} does nothing.</li>
   *   <li>Then calls
   * {@link WebSocketListener#onClose(WebSocket, int, String)}.</li>
   * </ul>
   * <p>
   * Method under test: {@link NettyWebSocket#onClose(int, String)}
   */
  @Test
  @DisplayName("Test onClose(int, String); given WebSocketListener onClose(WebSocket, int, String) does nothing; then calls onClose(WebSocket, int, String)")
  void testOnClose_givenWebSocketListenerOnCloseDoesNothing_thenCallsOnClose() {
    // Arrange
    WebSocketListener l = mock(WebSocketListener.class);
    doNothing().when(l).onClose(Mockito.<WebSocket>any(), anyInt(), Mockito.<String>any());
    EmbeddedChannel channel = new EmbeddedChannel();

    NettyWebSocket nettyWebSocket = new NettyWebSocket(channel, new DefaultHttpHeaders());
    nettyWebSocket.addWebSocketListener(l);

    // Act
    nettyWebSocket.onClose(1, "https://example.org/example");

    // Assert
    verify(l).onClose(isA(WebSocket.class), eq(1), eq("https://example.org/example"));
  }

  /**
   * Test {@link NettyWebSocket#onClose(int, String)}.
   * <ul>
   *   <li>Given {@link WebSocketListener}
   * {@link WebSocketListener#onError(Throwable)} does nothing.</li>
   *   <li>Then calls {@link WebSocketListener#onError(Throwable)}.</li>
   * </ul>
   * <p>
   * Method under test: {@link NettyWebSocket#onClose(int, String)}
   */
  @Test
  @DisplayName("Test onClose(int, String); given WebSocketListener onError(Throwable) does nothing; then calls onError(Throwable)")
  void testOnClose_givenWebSocketListenerOnErrorDoesNothing_thenCallsOnError() {
    // Arrange
    WebSocketListener l = mock(WebSocketListener.class);
    doNothing().when(l).onClose(Mockito.<WebSocket>any(), anyInt(), Mockito.<String>any());
    WebSocketListener l2 = mock(WebSocketListener.class);
    doNothing().when(l2).onError(Mockito.<Throwable>any());
    doThrow(new IllegalArgumentException("foo")).when(l2)
        .onClose(Mockito.<WebSocket>any(), anyInt(), Mockito.<String>any());
    EmbeddedChannel channel = new EmbeddedChannel();

    NettyWebSocket nettyWebSocket = new NettyWebSocket(channel, new DefaultHttpHeaders());
    nettyWebSocket.addWebSocketListener(l2);
    nettyWebSocket.addWebSocketListener(l);

    // Act
    nettyWebSocket.onClose(1, "https://example.org/example");

    // Assert
    verify(l2).onClose(isA(WebSocket.class), eq(1), eq("https://example.org/example"));
    verify(l).onClose(isA(WebSocket.class), eq(1), eq("https://example.org/example"));
    verify(l2).onError(isA(Throwable.class));
  }

  /**
   * Test {@link NettyWebSocket#onClose(int, String)}.
   * <ul>
   *   <li>Then throw {@link IllegalArgumentException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link NettyWebSocket#onClose(int, String)}
   */
  @Test
  @DisplayName("Test onClose(int, String); then throw IllegalArgumentException")
  void testOnClose_thenThrowIllegalArgumentException() {
    // Arrange
    WebSocketListener l = mock(WebSocketListener.class);
    doNothing().when(l).onClose(Mockito.<WebSocket>any(), anyInt(), Mockito.<String>any());
    WebSocketListener l2 = mock(WebSocketListener.class);
    doThrow(new IllegalArgumentException("foo")).when(l2).onError(Mockito.<Throwable>any());
    doThrow(new IllegalArgumentException("foo")).when(l2)
        .onClose(Mockito.<WebSocket>any(), anyInt(), Mockito.<String>any());
    EmbeddedChannel channel = new EmbeddedChannel();

    NettyWebSocket nettyWebSocket = new NettyWebSocket(channel, new DefaultHttpHeaders());
    nettyWebSocket.addWebSocketListener(l2);
    nettyWebSocket.addWebSocketListener(l);

    // Act and Assert
    assertThrows(IllegalArgumentException.class, () -> nettyWebSocket.onClose(1, "https://example.org/example"));
    verify(l2).onClose(isA(WebSocket.class), eq(1), eq("https://example.org/example"));
    verify(l2).onError(isA(Throwable.class));
  }
}
