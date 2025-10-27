package org.asynchttpclient.ws;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
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
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.embedded.EmbeddedChannel;
import io.netty.handler.codec.http.DefaultFullHttpResponse;
import io.netty.handler.codec.http.DefaultHttpHeaders;
import io.netty.handler.codec.http.EmptyHttpHeaders;
import io.netty.handler.codec.http.HttpHeaders;
import io.netty.handler.codec.http.HttpVersion;
import io.netty.handler.codec.http.websocketx.BinaryWebSocketFrame;
import io.netty.handler.codec.http.websocketx.CloseWebSocketFrame;
import io.netty.handler.codec.http.websocketx.ContinuationWebSocketFrame;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import java.util.ArrayList;
import org.asynchttpclient.AsyncHandler;
import org.asynchttpclient.AsyncHttpClientConfig;
import org.asynchttpclient.HttpResponseBodyPart;
import org.asynchttpclient.exception.ChannelClosedException;
import org.asynchttpclient.netty.EagerResponseBodyPart;
import org.asynchttpclient.netty.NettyResponseStatus;
import org.asynchttpclient.netty.handler.HttpHandler;
import org.asynchttpclient.netty.ws.NettyWebSocket;
import org.asynchttpclient.uri.Uri;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

class WebSocketUpgradeHandlerDiffblueTest {
  /**
   * Method under test:
   * {@link WebSocketUpgradeHandler.Builder#addWebSocketListener(WebSocketListener)}
   */
  @Test
  void testBuilderAddWebSocketListener() {
    // Arrange
    WebSocketUpgradeHandler.Builder builder = new WebSocketUpgradeHandler.Builder();

    // Act and Assert
    assertSame(builder, builder.addWebSocketListener(null));
  }

  /**
   * Method under test:
   * {@link WebSocketUpgradeHandler.Builder#removeWebSocketListener(WebSocketListener)}
   */
  @Test
  void testBuilderRemoveWebSocketListener() {
    // Arrange
    WebSocketUpgradeHandler.Builder builder = new WebSocketUpgradeHandler.Builder();

    // Act and Assert
    assertSame(builder, builder.removeWebSocketListener(null));
  }

  /**
   * Method under test:
   * {@link WebSocketUpgradeHandler#onStatusReceived(org.asynchttpclient.HttpResponseStatus)}
   */
  @Test
  void testOnStatusReceived() throws Exception {
    // Arrange
    ArrayList<WebSocketListener> listeners = new ArrayList<>();
    listeners.add(mock(WebSocketListener.class));
    WebSocketUpgradeHandler webSocketUpgradeHandler = new WebSocketUpgradeHandler(listeners);
    Uri uri = new Uri("https://example.org/example", "https://example.org/example", "https://example.org/example", 8080,
        "https://example.org/example", "https://example.org/example", "https://example.org/example");

    HttpVersion version = new HttpVersion("https://example.org/example", 1, 1, true);

    DefaultFullHttpResponse response = new DefaultFullHttpResponse(version,
        io.netty.handler.codec.http.HttpResponseStatus.valueOf(1));

    // Act and Assert
    assertEquals(AsyncHandler.State.ABORT,
        webSocketUpgradeHandler.onStatusReceived(new NettyResponseStatus(uri, response, new EmbeddedChannel())));
  }

  /**
   * Method under test:
   * {@link WebSocketUpgradeHandler#onHeadersReceived(HttpHeaders)}
   */
  @Test
  void testOnHeadersReceived() throws Exception {
    // Arrange
    WebSocketUpgradeHandler webSocketUpgradeHandler = new WebSocketUpgradeHandler(new ArrayList<>());

    // Act and Assert
    assertEquals(AsyncHandler.State.CONTINUE, webSocketUpgradeHandler.onHeadersReceived(new DefaultHttpHeaders()));
  }

  /**
   * Method under test:
   * {@link WebSocketUpgradeHandler#onHeadersReceived(HttpHeaders)}
   */
  @Test
  void testOnHeadersReceived2() throws Exception {
    // Arrange, Act and Assert
    assertEquals(AsyncHandler.State.CONTINUE,
        (new WebSocketUpgradeHandler(new ArrayList<>())).onHeadersReceived(mock(EmptyHttpHeaders.class)));
  }

  /**
   * Method under test:
   * {@link WebSocketUpgradeHandler#onBodyPartReceived(HttpResponseBodyPart)}
   */
  @Test
  void testOnBodyPartReceived() throws Exception {
    // Arrange
    WebSocketUpgradeHandler webSocketUpgradeHandler = new WebSocketUpgradeHandler(new ArrayList<>());

    // Act and Assert
    assertEquals(AsyncHandler.State.CONTINUE, webSocketUpgradeHandler.onBodyPartReceived(
        new EagerResponseBodyPart(new DuplicatedByteBuf(new EmptyByteBuf(new AdaptiveByteBufAllocator())), true)));
  }

  /**
   * Method under test:
   * {@link WebSocketUpgradeHandler#onBodyPartReceived(HttpResponseBodyPart)}
   */
  @Test
  void testOnBodyPartReceived2() throws Exception {
    // Arrange
    WebSocketUpgradeHandler webSocketUpgradeHandler = new WebSocketUpgradeHandler(new ArrayList<>());
    ByteBuf buffer = mock(ByteBuf.class);
    when(buffer.capacity()).thenReturn(3);
    when(buffer.maxCapacity()).thenReturn(3);
    when(buffer.readerIndex()).thenReturn(1);
    when(buffer.writerIndex()).thenReturn(1);
    when(buffer.getBytes(anyInt(), Mockito.<byte[]>any(), anyInt(), anyInt()))
        .thenReturn(new DuplicatedByteBuf(new EmptyByteBuf(new AdaptiveByteBufAllocator())));

    // Act
    AsyncHandler.State actualOnBodyPartReceivedResult = webSocketUpgradeHandler.onBodyPartReceived(
        new EagerResponseBodyPart(new DuplicatedByteBuf(new ReadOnlyByteBuf(new DuplicatedByteBuf(buffer))), true));

    // Assert
    verify(buffer, atLeast(1)).capacity();
    verify(buffer).getBytes(eq(1), isA(byte[].class), eq(0), eq(0));
    verify(buffer).maxCapacity();
    verify(buffer).readerIndex();
    verify(buffer).writerIndex();
    assertEquals(AsyncHandler.State.CONTINUE, actualOnBodyPartReceivedResult);
  }

  /**
   * Method under test: {@link WebSocketUpgradeHandler#onCompleted()}
   */
  @Test
  void testOnCompleted() throws Exception {
    // Arrange, Act and Assert
    assertNull((new WebSocketUpgradeHandler(new ArrayList<>())).onCompleted());
  }

  /**
   * Method under test: {@link WebSocketUpgradeHandler#onCompleted()}
   */
  @Test
  void testOnCompleted2() throws Exception {
    // Arrange
    ArrayList<WebSocketListener> listeners = new ArrayList<>();
    listeners.add(mock(WebSocketListener.class));

    // Act and Assert
    assertNull((new WebSocketUpgradeHandler(listeners)).onCompleted());
  }

  /**
   * Method under test: {@link WebSocketUpgradeHandler#onThrowable(Throwable)}
   */
  @Test
  void testOnThrowable() {
    // Arrange
    WebSocketListener webSocketListener = mock(WebSocketListener.class);
    doNothing().when(webSocketListener).onError(Mockito.<Throwable>any());

    ArrayList<WebSocketListener> listeners = new ArrayList<>();
    listeners.add(webSocketListener);

    // Act
    (new WebSocketUpgradeHandler(listeners)).onThrowable(ChannelClosedException.INSTANCE);

    // Assert
    verify(webSocketListener).onError(isA(Throwable.class));
  }

  /**
   * Method under test: {@link WebSocketUpgradeHandler#onThrowable(Throwable)}
   */
  @Test
  void testOnThrowable2() {
    // Arrange
    WebSocketListener webSocketListener = mock(WebSocketListener.class);
    doNothing().when(webSocketListener).onError(Mockito.<Throwable>any());

    ArrayList<WebSocketListener> listeners = new ArrayList<>();
    listeners.add(webSocketListener);

    WebSocketUpgradeHandler webSocketUpgradeHandler = new WebSocketUpgradeHandler(listeners);
    EmbeddedChannel channel = new EmbeddedChannel();
    webSocketUpgradeHandler.setWebSocket(new NettyWebSocket(channel, new DefaultHttpHeaders()));

    // Act
    webSocketUpgradeHandler.onThrowable(ChannelClosedException.INSTANCE);

    // Assert
    verify(webSocketListener).onError(isA(Throwable.class));
  }

  /**
   * Method under test:
   * {@link WebSocketUpgradeHandler#setWebSocket(NettyWebSocket)}
   */
  @Test
  void testSetWebSocket() throws Exception {
    // Arrange
    WebSocketUpgradeHandler webSocketUpgradeHandler = new WebSocketUpgradeHandler(new ArrayList<>());
    EmbeddedChannel channel = new EmbeddedChannel();
    NettyWebSocket webSocket = new NettyWebSocket(channel, new DefaultHttpHeaders());

    // Act
    webSocketUpgradeHandler.setWebSocket(webSocket);

    // Assert
    assertSame(webSocket, webSocketUpgradeHandler.onCompleted());
  }

  /**
   * Method under test:
   * {@link WebSocketUpgradeHandler#setWebSocket(NettyWebSocket)}
   */
  @Test
  void testSetWebSocket2() throws Exception {
    // Arrange
    ArrayList<WebSocketListener> listeners = new ArrayList<>();
    listeners.add(mock(WebSocketListener.class));
    WebSocketUpgradeHandler webSocketUpgradeHandler = new WebSocketUpgradeHandler(listeners);
    EmbeddedChannel channel = new EmbeddedChannel();
    NettyWebSocket webSocket = new NettyWebSocket(channel, new DefaultHttpHeaders());

    // Act
    webSocketUpgradeHandler.setWebSocket(webSocket);

    // Assert
    assertSame(webSocket, webSocketUpgradeHandler.onCompleted());
  }

  /**
   * Method under test: {@link WebSocketUpgradeHandler#onOpen(NettyWebSocket)}
   */
  @Test
  void testOnOpen() {
    // Arrange
    WebSocketUpgradeHandler webSocketUpgradeHandler = new WebSocketUpgradeHandler(new ArrayList<>());
    EmbeddedChannel channel = new EmbeddedChannel();
    NettyWebSocket webSocket = new NettyWebSocket(channel, new DefaultHttpHeaders());

    // Act
    webSocketUpgradeHandler.onOpen(webSocket);

    // Assert
    assertTrue(webSocket.isReady());
  }

  /**
   * Method under test: {@link WebSocketUpgradeHandler#onOpen(NettyWebSocket)}
   */
  @Test
  void testOnOpen2() {
    // Arrange
    WebSocketListener webSocketListener = mock(WebSocketListener.class);
    doNothing().when(webSocketListener).onOpen(Mockito.<WebSocket>any());

    ArrayList<WebSocketListener> listeners = new ArrayList<>();
    listeners.add(webSocketListener);
    WebSocketUpgradeHandler webSocketUpgradeHandler = new WebSocketUpgradeHandler(listeners);
    EmbeddedChannel channel = new EmbeddedChannel();
    NettyWebSocket webSocket = new NettyWebSocket(channel, new DefaultHttpHeaders());

    // Act
    webSocketUpgradeHandler.onOpen(webSocket);

    // Assert
    verify(webSocketListener).onOpen(isA(WebSocket.class));
    assertTrue(webSocket.isReady());
  }

  /**
   * Method under test: {@link WebSocketUpgradeHandler#onOpen(NettyWebSocket)}
   */
  @Test
  void testOnOpen3() {
    // Arrange
    WebSocketListener webSocketListener = mock(WebSocketListener.class);
    doNothing().when(webSocketListener).onBinaryFrame(Mockito.<byte[]>any(), anyBoolean(), anyInt());
    doNothing().when(webSocketListener).onOpen(Mockito.<WebSocket>any());

    ArrayList<WebSocketListener> listeners = new ArrayList<>();
    listeners.add(webSocketListener);
    WebSocketUpgradeHandler webSocketUpgradeHandler = new WebSocketUpgradeHandler(listeners);
    EmbeddedChannel channel = new EmbeddedChannel();

    NettyWebSocket webSocket = new NettyWebSocket(channel, new DefaultHttpHeaders());
    webSocket.bufferFrame(new BinaryWebSocketFrame());

    // Act
    webSocketUpgradeHandler.onOpen(webSocket);

    // Assert
    verify(webSocketListener).onBinaryFrame(isA(byte[].class), eq(true), eq(0));
    verify(webSocketListener).onOpen(isA(WebSocket.class));
    assertTrue(webSocket.isReady());
  }

  /**
   * Method under test: {@link WebSocketUpgradeHandler#onOpen(NettyWebSocket)}
   */
  @Test
  void testOnOpen4() {
    // Arrange
    WebSocketListener webSocketListener = mock(WebSocketListener.class);
    doNothing().when(webSocketListener).onTextFrame(Mockito.<String>any(), anyBoolean(), anyInt());
    doNothing().when(webSocketListener).onOpen(Mockito.<WebSocket>any());

    ArrayList<WebSocketListener> listeners = new ArrayList<>();
    listeners.add(webSocketListener);
    WebSocketUpgradeHandler webSocketUpgradeHandler = new WebSocketUpgradeHandler(listeners);
    EmbeddedChannel channel = new EmbeddedChannel();

    NettyWebSocket webSocket = new NettyWebSocket(channel, new DefaultHttpHeaders());
    webSocket.bufferFrame(new TextWebSocketFrame());

    // Act
    webSocketUpgradeHandler.onOpen(webSocket);

    // Assert
    verify(webSocketListener).onOpen(isA(WebSocket.class));
    verify(webSocketListener).onTextFrame(eq(""), eq(true), eq(0));
    assertTrue(webSocket.isReady());
  }

  /**
   * Method under test: {@link WebSocketUpgradeHandler#onOpen(NettyWebSocket)}
   */
  @Test
  void testOnOpen5() {
    // Arrange
    WebSocketListener webSocketListener = mock(WebSocketListener.class);
    doNothing().when(webSocketListener).onBinaryFrame(Mockito.<byte[]>any(), anyBoolean(), anyInt());
    doNothing().when(webSocketListener).onOpen(Mockito.<WebSocket>any());

    ArrayList<WebSocketListener> listeners = new ArrayList<>();
    listeners.add(webSocketListener);
    WebSocketUpgradeHandler webSocketUpgradeHandler = new WebSocketUpgradeHandler(listeners);
    EmbeddedChannel channel = new EmbeddedChannel();

    NettyWebSocket webSocket = new NettyWebSocket(channel, new DefaultHttpHeaders());
    webSocket
        .bufferFrame(new BinaryWebSocketFrame(new DuplicatedByteBuf(new EmptyByteBuf(new AdaptiveByteBufAllocator()))));

    // Act
    webSocketUpgradeHandler.onOpen(webSocket);

    // Assert
    verify(webSocketListener).onBinaryFrame(isA(byte[].class), eq(true), eq(0));
    verify(webSocketListener).onOpen(isA(WebSocket.class));
    assertTrue(webSocket.isReady());
  }

  /**
   * Method under test: {@link WebSocketUpgradeHandler#onOpen(NettyWebSocket)}
   */
  @Test
  void testOnOpen6() {
    // Arrange
    WebSocketListener webSocketListener = mock(WebSocketListener.class);
    doNothing().when(webSocketListener).onTextFrame(Mockito.<String>any(), anyBoolean(), anyInt());
    doNothing().when(webSocketListener).onOpen(Mockito.<WebSocket>any());

    ArrayList<WebSocketListener> listeners = new ArrayList<>();
    listeners.add(webSocketListener);
    WebSocketUpgradeHandler webSocketUpgradeHandler = new WebSocketUpgradeHandler(listeners);
    EmbeddedChannel channel = new EmbeddedChannel();

    NettyWebSocket webSocket = new NettyWebSocket(channel, new DefaultHttpHeaders());
    webSocket.bufferFrame(new TextWebSocketFrame("https://example.org/example"));

    // Act
    webSocketUpgradeHandler.onOpen(webSocket);

    // Assert
    verify(webSocketListener).onOpen(isA(WebSocket.class));
    verify(webSocketListener).onTextFrame(eq("https://example.org/example"), eq(true), eq(0));
    assertTrue(webSocket.isReady());
  }

  /**
   * Method under test: {@link WebSocketUpgradeHandler#onOpen(NettyWebSocket)}
   */
  @Test
  void testOnOpen7() {
    // Arrange
    WebSocketListener webSocketListener = mock(WebSocketListener.class);
    doNothing().when(webSocketListener).onClose(Mockito.<WebSocket>any(), anyInt(), Mockito.<String>any());
    doNothing().when(webSocketListener).onOpen(Mockito.<WebSocket>any());

    ArrayList<WebSocketListener> listeners = new ArrayList<>();
    listeners.add(webSocketListener);
    WebSocketUpgradeHandler webSocketUpgradeHandler = new WebSocketUpgradeHandler(listeners);
    EmbeddedChannel channel = new EmbeddedChannel();

    NettyWebSocket webSocket = new NettyWebSocket(channel, new DefaultHttpHeaders());
    webSocket.bufferFrame(new CloseWebSocketFrame());

    // Act
    webSocketUpgradeHandler.onOpen(webSocket);

    // Assert
    verify(webSocketListener).onClose(isA(WebSocket.class), eq(-1), eq(""));
    verify(webSocketListener).onOpen(isA(WebSocket.class));
    assertTrue(webSocket.isReady());
  }

  /**
   * Method under test: {@link WebSocketUpgradeHandler#onOpen(NettyWebSocket)}
   */
  @Test
  void testOnOpen8() {
    // Arrange
    WebSocketListener webSocketListener = mock(WebSocketListener.class);
    doNothing().when(webSocketListener).onClose(Mockito.<WebSocket>any(), anyInt(), Mockito.<String>any());
    doNothing().when(webSocketListener).onOpen(Mockito.<WebSocket>any());

    ArrayList<WebSocketListener> listeners = new ArrayList<>();
    listeners.add(webSocketListener);
    WebSocketUpgradeHandler webSocketUpgradeHandler = new WebSocketUpgradeHandler(listeners);
    AsyncHttpClientConfig config = mock(AsyncHttpClientConfig.class);
    when(config.isUseLaxCookieEncoder()).thenReturn(true);
    when(config.getMaxRedirects()).thenReturn(3);
    when(config.getIoExceptionFilters()).thenReturn(new ArrayList<>());
    when(config.getResponseFilters()).thenReturn(new ArrayList<>());
    EmbeddedChannel channel = new EmbeddedChannel(new HttpHandler(config, null, null));

    NettyWebSocket webSocket = new NettyWebSocket(channel, new DefaultHttpHeaders());
    webSocket.bufferFrame(new CloseWebSocketFrame());

    // Act
    webSocketUpgradeHandler.onOpen(webSocket);

    // Assert
    verify(config).getIoExceptionFilters();
    verify(config).getMaxRedirects();
    verify(config).getResponseFilters();
    verify(config).isUseLaxCookieEncoder();
    verify(webSocketListener).onClose(isA(WebSocket.class), eq(-1), eq(""));
    verify(webSocketListener).onOpen(isA(WebSocket.class));
    assertTrue(webSocket.isReady());
  }

  /**
   * Method under test: {@link WebSocketUpgradeHandler#onOpen(NettyWebSocket)}
   */
  @Test
  void testOnOpen9() throws Exception {
    // Arrange
    WebSocketListener webSocketListener = mock(WebSocketListener.class);
    doNothing().when(webSocketListener).onClose(Mockito.<WebSocket>any(), anyInt(), Mockito.<String>any());
    doNothing().when(webSocketListener).onOpen(Mockito.<WebSocket>any());

    ArrayList<WebSocketListener> listeners = new ArrayList<>();
    listeners.add(webSocketListener);
    WebSocketUpgradeHandler webSocketUpgradeHandler = new WebSocketUpgradeHandler(listeners);
    ChannelHandler channelHandler = mock(ChannelHandler.class);
    doThrow(ChannelClosedException.INSTANCE).when(channelHandler).handlerAdded(Mockito.<ChannelHandlerContext>any());
    doThrow(ChannelClosedException.INSTANCE).when(channelHandler).handlerRemoved(Mockito.<ChannelHandlerContext>any());
    EmbeddedChannel channel = new EmbeddedChannel(channelHandler);

    NettyWebSocket webSocket = new NettyWebSocket(channel, new DefaultHttpHeaders());
    webSocket.bufferFrame(new CloseWebSocketFrame());

    // Act
    webSocketUpgradeHandler.onOpen(webSocket);

    // Assert
    verify(channelHandler).handlerAdded(isA(ChannelHandlerContext.class));
    verify(channelHandler).handlerRemoved(isA(ChannelHandlerContext.class));
    verify(webSocketListener).onClose(isA(WebSocket.class), eq(-1), eq(""));
    verify(webSocketListener).onOpen(isA(WebSocket.class));
    assertTrue(webSocket.isReady());
  }

  /**
   * Method under test: {@link WebSocketUpgradeHandler#onOpen(NettyWebSocket)}
   */
  @Test
  void testOnOpen10() {
    // Arrange
    WebSocketListener webSocketListener = mock(WebSocketListener.class);
    doNothing().when(webSocketListener).onOpen(Mockito.<WebSocket>any());

    ArrayList<WebSocketListener> listeners = new ArrayList<>();
    listeners.add(webSocketListener);
    WebSocketUpgradeHandler webSocketUpgradeHandler = new WebSocketUpgradeHandler(listeners);
    Channel channel = mock(Channel.class);

    NettyWebSocket webSocket = new NettyWebSocket(channel, new DefaultHttpHeaders());
    webSocket.bufferFrame(new ContinuationWebSocketFrame());

    // Act
    webSocketUpgradeHandler.onOpen(webSocket);

    // Assert
    verify(webSocketListener).onOpen(isA(WebSocket.class));
    assertTrue(webSocket.isReady());
  }

  /**
   * Method under test: {@link WebSocketUpgradeHandler#onOpen(NettyWebSocket)}
   */
  @Test
  void testOnOpen11() {
    // Arrange
    WebSocketListener webSocketListener = mock(WebSocketListener.class);
    doNothing().when(webSocketListener).onBinaryFrame(Mockito.<byte[]>any(), anyBoolean(), anyInt());
    doNothing().when(webSocketListener).onOpen(Mockito.<WebSocket>any());

    ArrayList<WebSocketListener> listeners = new ArrayList<>();
    listeners.add(webSocketListener);
    WebSocketUpgradeHandler webSocketUpgradeHandler = new WebSocketUpgradeHandler(listeners);
    BinaryWebSocketFrame frame = mock(BinaryWebSocketFrame.class);
    when(frame.rsv()).thenReturn(1);
    when(frame.release()).thenReturn(true);
    when(frame.isFinalFragment()).thenReturn(true);
    when(frame.content()).thenReturn(new DuplicatedByteBuf(new EmptyByteBuf(new AdaptiveByteBufAllocator())));
    when(frame.retain()).thenReturn(new BinaryWebSocketFrame());
    Channel channel = mock(Channel.class);

    NettyWebSocket webSocket = new NettyWebSocket(channel, new DefaultHttpHeaders());
    webSocket.bufferFrame(frame);

    // Act
    webSocketUpgradeHandler.onOpen(webSocket);

    // Assert
    verify(frame).content();
    verify(frame).release();
    verify(frame).retain();
    verify(frame, atLeast(1)).isFinalFragment();
    verify(frame).rsv();
    verify(webSocketListener).onBinaryFrame(isA(byte[].class), eq(true), eq(1));
    verify(webSocketListener).onOpen(isA(WebSocket.class));
    assertTrue(webSocket.isReady());
  }

  /**
   * Method under test: {@link WebSocketUpgradeHandler#onOpen(NettyWebSocket)}
   */
  @Test
  void testOnOpen12() {
    // Arrange
    WebSocketListener webSocketListener = mock(WebSocketListener.class);
    doNothing().when(webSocketListener).onBinaryFrame(Mockito.<byte[]>any(), anyBoolean(), anyInt());
    doNothing().when(webSocketListener).onOpen(Mockito.<WebSocket>any());

    ArrayList<WebSocketListener> listeners = new ArrayList<>();
    listeners.add(webSocketListener);
    WebSocketUpgradeHandler webSocketUpgradeHandler = new WebSocketUpgradeHandler(listeners);
    BinaryWebSocketFrame frame = mock(BinaryWebSocketFrame.class);
    when(frame.rsv()).thenReturn(1);
    when(frame.release()).thenReturn(true);
    when(frame.isFinalFragment()).thenReturn(false);
    when(frame.content()).thenReturn(new DuplicatedByteBuf(new EmptyByteBuf(new AdaptiveByteBufAllocator())));
    when(frame.retain()).thenReturn(new BinaryWebSocketFrame());
    Channel channel = mock(Channel.class);

    NettyWebSocket webSocket = new NettyWebSocket(channel, new DefaultHttpHeaders());
    webSocket.bufferFrame(frame);

    // Act
    webSocketUpgradeHandler.onOpen(webSocket);

    // Assert
    verify(frame).content();
    verify(frame).release();
    verify(frame).retain();
    verify(frame, atLeast(1)).isFinalFragment();
    verify(frame).rsv();
    verify(webSocketListener).onBinaryFrame(isA(byte[].class), eq(false), eq(1));
    verify(webSocketListener).onOpen(isA(WebSocket.class));
    assertTrue(webSocket.isReady());
  }

  /**
   * Method under test: {@link WebSocketUpgradeHandler#onOpen(NettyWebSocket)}
   */
  @Test
  void testOnOpen13() {
    // Arrange
    WebSocketListener webSocketListener = mock(WebSocketListener.class);
    doNothing().when(webSocketListener).onBinaryFrame(Mockito.<byte[]>any(), anyBoolean(), anyInt());
    doNothing().when(webSocketListener).onOpen(Mockito.<WebSocket>any());

    ArrayList<WebSocketListener> listeners = new ArrayList<>();
    listeners.add(webSocketListener);
    WebSocketUpgradeHandler webSocketUpgradeHandler = new WebSocketUpgradeHandler(listeners);
    ByteBuf buffer = mock(ByteBuf.class);
    when(buffer.hasArray()).thenReturn(false);
    when(buffer.capacity()).thenReturn(3);
    when(buffer.maxCapacity()).thenReturn(3);
    when(buffer.readerIndex()).thenReturn(1);
    when(buffer.writerIndex()).thenReturn(1);
    when(buffer.getBytes(anyInt(), Mockito.<byte[]>any(), anyInt(), anyInt()))
        .thenReturn(new DuplicatedByteBuf(new EmptyByteBuf(new AdaptiveByteBufAllocator())));
    DuplicatedByteBuf duplicatedByteBuf = new DuplicatedByteBuf(buffer);
    BinaryWebSocketFrame frame = mock(BinaryWebSocketFrame.class);
    when(frame.rsv()).thenReturn(1);
    when(frame.release()).thenReturn(true);
    when(frame.isFinalFragment()).thenReturn(true);
    when(frame.content()).thenReturn(duplicatedByteBuf);
    when(frame.retain()).thenReturn(new BinaryWebSocketFrame());
    Channel channel = mock(Channel.class);

    NettyWebSocket webSocket = new NettyWebSocket(channel, new DefaultHttpHeaders());
    webSocket.bufferFrame(frame);

    // Act
    webSocketUpgradeHandler.onOpen(webSocket);

    // Assert
    verify(buffer, atLeast(1)).capacity();
    verify(buffer).getBytes(eq(1), isA(byte[].class), eq(0), eq(0));
    verify(buffer).hasArray();
    verify(buffer).maxCapacity();
    verify(buffer).readerIndex();
    verify(buffer).writerIndex();
    verify(frame).content();
    verify(frame).release();
    verify(frame).retain();
    verify(frame, atLeast(1)).isFinalFragment();
    verify(frame).rsv();
    verify(webSocketListener).onBinaryFrame(isA(byte[].class), eq(true), eq(1));
    verify(webSocketListener).onOpen(isA(WebSocket.class));
    assertTrue(webSocket.isReady());
  }
}
