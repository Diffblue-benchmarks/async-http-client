package org.asynchttpclient.ws;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import io.netty.buffer.AdaptiveByteBufAllocator;
import io.netty.buffer.DuplicatedByteBuf;
import io.netty.buffer.EmptyByteBuf;
import io.netty.channel.embedded.EmbeddedChannel;
import io.netty.handler.codec.http.DefaultFullHttpResponse;
import io.netty.handler.codec.http.DefaultHttpHeaders;
import io.netty.handler.codec.http.HttpHeaders;
import io.netty.handler.codec.http.HttpVersion;
import io.netty.handler.codec.http.websocketx.BinaryWebSocketFrame;
import io.netty.handler.codec.http.websocketx.CloseWebSocketFrame;
import io.netty.handler.codec.http.websocketx.ContinuationWebSocketFrame;
import io.netty.handler.codec.http.websocketx.PingWebSocketFrame;
import io.netty.handler.codec.http.websocketx.PongWebSocketFrame;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import java.util.ArrayList;
import org.asynchttpclient.AsyncHandler;
import org.asynchttpclient.HttpResponseBodyPart;
import org.asynchttpclient.exception.ChannelClosedException;
import org.asynchttpclient.netty.EagerResponseBodyPart;
import org.asynchttpclient.netty.NettyResponseStatus;
import org.asynchttpclient.netty.ws.NettyWebSocket;
import org.asynchttpclient.uri.Uri;
import org.asynchttpclient.ws.WebSocketUpgradeHandler.Builder;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

class WebSocketUpgradeHandlerDiffblueTest {
  /**
   * Test Builder {@link Builder#addWebSocketListener(WebSocketListener)}.
   * <p>
   * Method under test:
   * {@link WebSocketUpgradeHandler.Builder#addWebSocketListener(WebSocketListener)}
   */
  @Test
  @DisplayName("Test Builder addWebSocketListener(WebSocketListener)")
  void testBuilderAddWebSocketListener() {
    // Arrange
    WebSocketUpgradeHandler.Builder builder = new WebSocketUpgradeHandler.Builder();

    // Act and Assert
    assertSame(builder, builder.addWebSocketListener(null));
  }

  /**
   * Test Builder {@link Builder#removeWebSocketListener(WebSocketListener)}.
   * <p>
   * Method under test:
   * {@link WebSocketUpgradeHandler.Builder#removeWebSocketListener(WebSocketListener)}
   */
  @Test
  @DisplayName("Test Builder removeWebSocketListener(WebSocketListener)")
  void testBuilderRemoveWebSocketListener() {
    // Arrange
    WebSocketUpgradeHandler.Builder builder = new WebSocketUpgradeHandler.Builder();

    // Act and Assert
    assertSame(builder, builder.removeWebSocketListener(null));
  }

  /**
   * Test {@link WebSocketUpgradeHandler#onStatusReceived(HttpResponseStatus)}.
   * <ul>
   *   <li>Then return {@code ABORT}.</li>
   * </ul>
   * <p>
   * Method under test:
   * {@link WebSocketUpgradeHandler#onStatusReceived(org.asynchttpclient.HttpResponseStatus)}
   */
  @Test
  @DisplayName("Test onStatusReceived(HttpResponseStatus); then return 'ABORT'")
  void testOnStatusReceived_thenReturnAbort() throws Exception {
    // Arrange
    WebSocketUpgradeHandler webSocketUpgradeHandler = new WebSocketUpgradeHandler(new ArrayList<>());
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
   * Test {@link WebSocketUpgradeHandler#onStatusReceived(HttpResponseStatus)}.
   * <ul>
   *   <li>Then return {@code CONTINUE}.</li>
   * </ul>
   * <p>
   * Method under test:
   * {@link WebSocketUpgradeHandler#onStatusReceived(org.asynchttpclient.HttpResponseStatus)}
   */
  @Test
  @DisplayName("Test onStatusReceived(HttpResponseStatus); then return 'CONTINUE'")
  void testOnStatusReceived_thenReturnContinue() throws Exception {
    // Arrange
    WebSocketUpgradeHandler webSocketUpgradeHandler = new WebSocketUpgradeHandler(new ArrayList<>());
    Uri uri = new Uri("https://example.org/example", "https://example.org/example", "https://example.org/example", 8080,
        "https://example.org/example", "https://example.org/example", "https://example.org/example");

    HttpVersion version = new HttpVersion("https://example.org/example", 1, 1, true);

    DefaultFullHttpResponse response = new DefaultFullHttpResponse(version,
        io.netty.handler.codec.http.HttpResponseStatus.valueOf(101));

    // Act and Assert
    assertEquals(AsyncHandler.State.CONTINUE,
        webSocketUpgradeHandler.onStatusReceived(new NettyResponseStatus(uri, response, new EmbeddedChannel())));
  }

  /**
   * Test {@link WebSocketUpgradeHandler#onHeadersReceived(HttpHeaders)}.
   * <p>
   * Method under test:
   * {@link WebSocketUpgradeHandler#onHeadersReceived(HttpHeaders)}
   */
  @Test
  @DisplayName("Test onHeadersReceived(HttpHeaders)")
  void testOnHeadersReceived() throws Exception {
    // Arrange
    WebSocketUpgradeHandler webSocketUpgradeHandler = new WebSocketUpgradeHandler(new ArrayList<>());

    // Act and Assert
    assertEquals(AsyncHandler.State.CONTINUE, webSocketUpgradeHandler.onHeadersReceived(new DefaultHttpHeaders()));
  }

  /**
   * Test
   * {@link WebSocketUpgradeHandler#onBodyPartReceived(HttpResponseBodyPart)}.
   * <p>
   * Method under test:
   * {@link WebSocketUpgradeHandler#onBodyPartReceived(HttpResponseBodyPart)}
   */
  @Test
  @DisplayName("Test onBodyPartReceived(HttpResponseBodyPart)")
  void testOnBodyPartReceived() throws Exception {
    // Arrange
    WebSocketUpgradeHandler webSocketUpgradeHandler = new WebSocketUpgradeHandler(new ArrayList<>());

    // Act and Assert
    assertEquals(AsyncHandler.State.CONTINUE, webSocketUpgradeHandler.onBodyPartReceived(
        new EagerResponseBodyPart(new DuplicatedByteBuf(new EmptyByteBuf(new AdaptiveByteBufAllocator())), true)));
  }

  /**
   * Test {@link WebSocketUpgradeHandler#onCompleted()}.
   * <p>
   * Method under test: {@link WebSocketUpgradeHandler#onCompleted()}
   */
  @Test
  @DisplayName("Test onCompleted()")
  void testOnCompleted() throws Exception {
    // Arrange, Act and Assert
    assertNull((new WebSocketUpgradeHandler(new ArrayList<>())).onCompleted());
  }

  /**
   * Test {@link WebSocketUpgradeHandler#onThrowable(Throwable)}.
   * <p>
   * Method under test: {@link WebSocketUpgradeHandler#onThrowable(Throwable)}
   */
  @Test
  @DisplayName("Test onThrowable(Throwable)")
  void testOnThrowable() {
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
   * Test {@link WebSocketUpgradeHandler#onThrowable(Throwable)}.
   * <ul>
   *   <li>Given {@link WebSocketListener}
   * {@link WebSocketListener#onError(Throwable)} does nothing.</li>
   *   <li>Then calls {@link WebSocketListener#onError(Throwable)}.</li>
   * </ul>
   * <p>
   * Method under test: {@link WebSocketUpgradeHandler#onThrowable(Throwable)}
   */
  @Test
  @DisplayName("Test onThrowable(Throwable); given WebSocketListener onError(Throwable) does nothing; then calls onError(Throwable)")
  void testOnThrowable_givenWebSocketListenerOnErrorDoesNothing_thenCallsOnError() {
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
   * Test {@link WebSocketUpgradeHandler#setWebSocket(NettyWebSocket)}.
   * <p>
   * Method under test:
   * {@link WebSocketUpgradeHandler#setWebSocket(NettyWebSocket)}
   */
  @Test
  @DisplayName("Test setWebSocket(NettyWebSocket)")
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
   * Test {@link WebSocketUpgradeHandler#onOpen(NettyWebSocket)}.
   * <p>
   * Method under test: {@link WebSocketUpgradeHandler#onOpen(NettyWebSocket)}
   */
  @Test
  @DisplayName("Test onOpen(NettyWebSocket)")
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
   * Test {@link WebSocketUpgradeHandler#onOpen(NettyWebSocket)}.
   * <ul>
   *   <li>Given {@link BinaryWebSocketFrame#BinaryWebSocketFrame()}.</li>
   * </ul>
   * <p>
   * Method under test: {@link WebSocketUpgradeHandler#onOpen(NettyWebSocket)}
   */
  @Test
  @DisplayName("Test onOpen(NettyWebSocket); given BinaryWebSocketFrame()")
  void testOnOpen_givenBinaryWebSocketFrame() {
    // Arrange
    WebSocketUpgradeHandler webSocketUpgradeHandler = new WebSocketUpgradeHandler(new ArrayList<>());
    EmbeddedChannel channel = new EmbeddedChannel();

    NettyWebSocket webSocket = new NettyWebSocket(channel, new DefaultHttpHeaders());
    webSocket.bufferFrame(new BinaryWebSocketFrame());

    // Act
    webSocketUpgradeHandler.onOpen(webSocket);

    // Assert
    assertTrue(webSocket.isReady());
  }

  /**
   * Test {@link WebSocketUpgradeHandler#onOpen(NettyWebSocket)}.
   * <ul>
   *   <li>Given {@link CloseWebSocketFrame#CloseWebSocketFrame()}.</li>
   * </ul>
   * <p>
   * Method under test: {@link WebSocketUpgradeHandler#onOpen(NettyWebSocket)}
   */
  @Test
  @DisplayName("Test onOpen(NettyWebSocket); given CloseWebSocketFrame()")
  void testOnOpen_givenCloseWebSocketFrame() {
    // Arrange
    WebSocketUpgradeHandler webSocketUpgradeHandler = new WebSocketUpgradeHandler(new ArrayList<>());
    EmbeddedChannel channel = new EmbeddedChannel();

    NettyWebSocket webSocket = new NettyWebSocket(channel, new DefaultHttpHeaders());
    webSocket.bufferFrame(new CloseWebSocketFrame());

    // Act
    webSocketUpgradeHandler.onOpen(webSocket);

    // Assert
    assertTrue(webSocket.isReady());
  }

  /**
   * Test {@link WebSocketUpgradeHandler#onOpen(NettyWebSocket)}.
   * <ul>
   *   <li>Given {@link CloseWebSocketFrame#CloseWebSocketFrame(boolean, int)} with
   * finalFragment is {@code true} and rsv is one.</li>
   * </ul>
   * <p>
   * Method under test: {@link WebSocketUpgradeHandler#onOpen(NettyWebSocket)}
   */
  @Test
  @DisplayName("Test onOpen(NettyWebSocket); given CloseWebSocketFrame(boolean, int) with finalFragment is 'true' and rsv is one")
  void testOnOpen_givenCloseWebSocketFrameWithFinalFragmentIsTrueAndRsvIsOne() {
    // Arrange
    WebSocketUpgradeHandler webSocketUpgradeHandler = new WebSocketUpgradeHandler(new ArrayList<>());
    EmbeddedChannel channel = new EmbeddedChannel();

    NettyWebSocket webSocket = new NettyWebSocket(channel, new DefaultHttpHeaders());
    webSocket.bufferFrame(new CloseWebSocketFrame(true, 1));

    // Act
    webSocketUpgradeHandler.onOpen(webSocket);

    // Assert
    assertTrue(webSocket.isReady());
  }

  /**
   * Test {@link WebSocketUpgradeHandler#onOpen(NettyWebSocket)}.
   * <ul>
   *   <li>Given
   * {@link ContinuationWebSocketFrame#ContinuationWebSocketFrame()}.</li>
   * </ul>
   * <p>
   * Method under test: {@link WebSocketUpgradeHandler#onOpen(NettyWebSocket)}
   */
  @Test
  @DisplayName("Test onOpen(NettyWebSocket); given ContinuationWebSocketFrame()")
  void testOnOpen_givenContinuationWebSocketFrame() {
    // Arrange
    WebSocketUpgradeHandler webSocketUpgradeHandler = new WebSocketUpgradeHandler(new ArrayList<>());
    EmbeddedChannel channel = new EmbeddedChannel();

    NettyWebSocket webSocket = new NettyWebSocket(channel, new DefaultHttpHeaders());
    webSocket.bufferFrame(new ContinuationWebSocketFrame());

    // Act
    webSocketUpgradeHandler.onOpen(webSocket);

    // Assert
    assertTrue(webSocket.isReady());
  }

  /**
   * Test {@link WebSocketUpgradeHandler#onOpen(NettyWebSocket)}.
   * <ul>
   *   <li>Given {@link DuplicatedByteBuf#DuplicatedByteBuf(ByteBuf)} with buffer is
   * {@link EmptyByteBuf#EmptyByteBuf(ByteBufAllocator)}.</li>
   * </ul>
   * <p>
   * Method under test: {@link WebSocketUpgradeHandler#onOpen(NettyWebSocket)}
   */
  @Test
  @DisplayName("Test onOpen(NettyWebSocket); given DuplicatedByteBuf(ByteBuf) with buffer is EmptyByteBuf(ByteBufAllocator)")
  void testOnOpen_givenDuplicatedByteBufWithBufferIsEmptyByteBuf() {
    // Arrange
    WebSocketUpgradeHandler webSocketUpgradeHandler = new WebSocketUpgradeHandler(new ArrayList<>());
    EmbeddedChannel channel = new EmbeddedChannel();

    NettyWebSocket webSocket = new NettyWebSocket(channel, new DefaultHttpHeaders());
    webSocket
        .bufferFrame(new BinaryWebSocketFrame(new DuplicatedByteBuf(new EmptyByteBuf(new AdaptiveByteBufAllocator()))));

    // Act
    webSocketUpgradeHandler.onOpen(webSocket);

    // Assert
    assertTrue(webSocket.isReady());
  }

  /**
   * Test {@link WebSocketUpgradeHandler#onOpen(NettyWebSocket)}.
   * <ul>
   *   <li>Given {@link PingWebSocketFrame#PingWebSocketFrame()}.</li>
   * </ul>
   * <p>
   * Method under test: {@link WebSocketUpgradeHandler#onOpen(NettyWebSocket)}
   */
  @Test
  @DisplayName("Test onOpen(NettyWebSocket); given PingWebSocketFrame()")
  void testOnOpen_givenPingWebSocketFrame() {
    // Arrange
    WebSocketUpgradeHandler webSocketUpgradeHandler = new WebSocketUpgradeHandler(new ArrayList<>());
    EmbeddedChannel channel = new EmbeddedChannel();

    NettyWebSocket webSocket = new NettyWebSocket(channel, new DefaultHttpHeaders());
    webSocket.bufferFrame(new PingWebSocketFrame());

    // Act
    webSocketUpgradeHandler.onOpen(webSocket);

    // Assert
    assertTrue(webSocket.isReady());
  }

  /**
   * Test {@link WebSocketUpgradeHandler#onOpen(NettyWebSocket)}.
   * <ul>
   *   <li>Given {@link PongWebSocketFrame#PongWebSocketFrame()}.</li>
   * </ul>
   * <p>
   * Method under test: {@link WebSocketUpgradeHandler#onOpen(NettyWebSocket)}
   */
  @Test
  @DisplayName("Test onOpen(NettyWebSocket); given PongWebSocketFrame()")
  void testOnOpen_givenPongWebSocketFrame() {
    // Arrange
    WebSocketUpgradeHandler webSocketUpgradeHandler = new WebSocketUpgradeHandler(new ArrayList<>());
    EmbeddedChannel channel = new EmbeddedChannel();

    NettyWebSocket webSocket = new NettyWebSocket(channel, new DefaultHttpHeaders());
    webSocket.bufferFrame(new PongWebSocketFrame());

    // Act
    webSocketUpgradeHandler.onOpen(webSocket);

    // Assert
    assertTrue(webSocket.isReady());
  }

  /**
   * Test {@link WebSocketUpgradeHandler#onOpen(NettyWebSocket)}.
   * <ul>
   *   <li>Given {@link TextWebSocketFrame#TextWebSocketFrame()}.</li>
   * </ul>
   * <p>
   * Method under test: {@link WebSocketUpgradeHandler#onOpen(NettyWebSocket)}
   */
  @Test
  @DisplayName("Test onOpen(NettyWebSocket); given TextWebSocketFrame()")
  void testOnOpen_givenTextWebSocketFrame() {
    // Arrange
    WebSocketUpgradeHandler webSocketUpgradeHandler = new WebSocketUpgradeHandler(new ArrayList<>());
    EmbeddedChannel channel = new EmbeddedChannel();

    NettyWebSocket webSocket = new NettyWebSocket(channel, new DefaultHttpHeaders());
    webSocket.bufferFrame(new TextWebSocketFrame());

    // Act
    webSocketUpgradeHandler.onOpen(webSocket);

    // Assert
    assertTrue(webSocket.isReady());
  }
}
