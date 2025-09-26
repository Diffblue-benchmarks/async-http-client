package org.asynchttpclient.ws;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import io.netty.buffer.AdaptiveByteBufAllocator;
import io.netty.buffer.DuplicatedByteBuf;
import io.netty.buffer.EmptyByteBuf;
import io.netty.buffer.ReadOnlyByteBuf;
import io.netty.channel.embedded.EmbeddedChannel;
import io.netty.handler.codec.http.DefaultFullHttpResponse;
import io.netty.handler.codec.http.DefaultHttpHeaders;
import io.netty.handler.codec.http.HttpHeaders;
import io.netty.handler.codec.http.HttpResponseStatus;
import io.netty.handler.codec.http.HttpVersion;
import io.netty.handler.codec.http.websocketx.BinaryWebSocketFrame;
import io.netty.handler.codec.http.websocketx.CloseWebSocketFrame;
import io.netty.handler.codec.http.websocketx.ContinuationWebSocketFrame;
import io.netty.handler.codec.http.websocketx.PingWebSocketFrame;
import io.netty.handler.codec.http.websocketx.PongWebSocketFrame;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import java.util.ArrayList;
import org.asynchttpclient.AsyncHandler;
import org.asynchttpclient.AsyncHandler.State;
import org.asynchttpclient.HttpResponseBodyPart;
import org.asynchttpclient.exception.ChannelClosedException;
import org.asynchttpclient.netty.EagerResponseBodyPart;
import org.asynchttpclient.netty.NettyResponseStatus;
import org.asynchttpclient.netty.ws.NettyWebSocket;
import org.asynchttpclient.uri.Uri;
import org.asynchttpclient.ws.WebSocketUpgradeHandler.Builder;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

class WebSocketUpgradeHandlerDiffblueTest {
  /**
   * Test Builder {@link Builder#addWebSocketListener(WebSocketListener)}.
   *
   * <p>Method under test: {@link Builder#addWebSocketListener(WebSocketListener)}
   */
  @Test
  @DisplayName("Test Builder addWebSocketListener(WebSocketListener)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"Builder Builder.addWebSocketListener(WebSocketListener)"})
  void testBuilderAddWebSocketListener() {
    // Arrange
    Builder builder = new Builder();

    // Act
    Builder actualAddWebSocketListenerResult = builder.addWebSocketListener(null);

    // Assert
    assertSame(builder, actualAddWebSocketListenerResult);
  }

  /**
   * Test Builder {@link Builder#removeWebSocketListener(WebSocketListener)}.
   *
   * <p>Method under test: {@link Builder#removeWebSocketListener(WebSocketListener)}
   */
  @Test
  @DisplayName("Test Builder removeWebSocketListener(WebSocketListener)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"Builder Builder.removeWebSocketListener(WebSocketListener)"})
  void testBuilderRemoveWebSocketListener() {
    // Arrange
    Builder builder = new Builder();

    // Act
    Builder actualRemoveWebSocketListenerResult = builder.removeWebSocketListener(null);

    // Assert
    assertSame(builder, actualRemoveWebSocketListenerResult);
  }

  /**
   * Test {@link WebSocketUpgradeHandler#onStatusReceived(HttpResponseStatus)}.
   *
   * <ul>
   *   <li>Then return {@code ABORT}.
   * </ul>
   *
   * <p>Method under test: {@link
   * WebSocketUpgradeHandler#onStatusReceived(org.asynchttpclient.HttpResponseStatus)}
   */
  @Test
  @DisplayName("Test onStatusReceived(HttpResponseStatus); then return 'ABORT'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "AsyncHandler.State WebSocketUpgradeHandler.onStatusReceived(org.asynchttpclient.HttpResponseStatus)"
  })
  void testOnStatusReceived_thenReturnAbort() throws Exception {
    // Arrange
    WebSocketUpgradeHandler webSocketUpgradeHandler =
        new WebSocketUpgradeHandler(new ArrayList<>());
    Uri uri =
        new Uri(
            "https://example.org/example",
            "https://example.org/example",
            "https://example.org/example",
            8080,
            "https://example.org/example",
            "https://example.org/example",
            "https://example.org/example");
    HttpVersion version = new HttpVersion("https://example.org/example", 1, 1, true);
    DefaultFullHttpResponse response =
        new DefaultFullHttpResponse(version, HttpResponseStatus.valueOf(1));

    NettyResponseStatus responseStatus =
        new NettyResponseStatus(uri, response, new EmbeddedChannel());

    // Act
    State actualOnStatusReceivedResult = webSocketUpgradeHandler.onStatusReceived(responseStatus);

    // Assert
    assertEquals(State.ABORT, actualOnStatusReceivedResult);
  }

  /**
   * Test {@link WebSocketUpgradeHandler#onStatusReceived(HttpResponseStatus)}.
   *
   * <ul>
   *   <li>Then return {@code CONTINUE}.
   * </ul>
   *
   * <p>Method under test: {@link
   * WebSocketUpgradeHandler#onStatusReceived(org.asynchttpclient.HttpResponseStatus)}
   */
  @Test
  @DisplayName("Test onStatusReceived(HttpResponseStatus); then return 'CONTINUE'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "AsyncHandler.State WebSocketUpgradeHandler.onStatusReceived(org.asynchttpclient.HttpResponseStatus)"
  })
  void testOnStatusReceived_thenReturnContinue() throws Exception {
    // Arrange
    WebSocketUpgradeHandler webSocketUpgradeHandler =
        new WebSocketUpgradeHandler(new ArrayList<>());
    Uri uri =
        new Uri(
            "https://example.org/example",
            "https://example.org/example",
            "https://example.org/example",
            8080,
            "https://example.org/example",
            "https://example.org/example",
            "https://example.org/example");
    HttpVersion version = new HttpVersion("https://example.org/example", 1, 1, true);
    DefaultFullHttpResponse response =
        new DefaultFullHttpResponse(
            version, HttpResponseStatus.valueOf(101, "https://example.org/example"));

    NettyResponseStatus responseStatus =
        new NettyResponseStatus(uri, response, new EmbeddedChannel());

    // Act
    State actualOnStatusReceivedResult = webSocketUpgradeHandler.onStatusReceived(responseStatus);

    // Assert
    assertEquals(State.CONTINUE, actualOnStatusReceivedResult);
  }

  /**
   * Test {@link WebSocketUpgradeHandler#onHeadersReceived(HttpHeaders)}.
   *
   * <p>Method under test: {@link WebSocketUpgradeHandler#onHeadersReceived(HttpHeaders)}
   */
  @Test
  @DisplayName("Test onHeadersReceived(HttpHeaders)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"AsyncHandler.State WebSocketUpgradeHandler.onHeadersReceived(HttpHeaders)"})
  void testOnHeadersReceived() throws Exception {
    // Arrange
    WebSocketUpgradeHandler webSocketUpgradeHandler =
        new WebSocketUpgradeHandler(new ArrayList<>());

    // Act and Assert
    assertEquals(
        State.CONTINUE, webSocketUpgradeHandler.onHeadersReceived(new DefaultHttpHeaders()));
  }

  /**
   * Test {@link WebSocketUpgradeHandler#onBodyPartReceived(HttpResponseBodyPart)}.
   *
   * <p>Method under test: {@link WebSocketUpgradeHandler#onBodyPartReceived(HttpResponseBodyPart)}
   */
  @Test
  @DisplayName("Test onBodyPartReceived(HttpResponseBodyPart)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "AsyncHandler.State WebSocketUpgradeHandler.onBodyPartReceived(HttpResponseBodyPart)"
  })
  void testOnBodyPartReceived() throws Exception {
    // Arrange
    WebSocketUpgradeHandler webSocketUpgradeHandler =
        new WebSocketUpgradeHandler(new ArrayList<>());
    DuplicatedByteBuf buf = new DuplicatedByteBuf(new EmptyByteBuf(new AdaptiveByteBufAllocator()));

    // Act
    State actualOnBodyPartReceivedResult =
        webSocketUpgradeHandler.onBodyPartReceived(new EagerResponseBodyPart(buf, true));

    // Assert
    assertEquals(State.CONTINUE, actualOnBodyPartReceivedResult);
  }

  /**
   * Test {@link WebSocketUpgradeHandler#onCompleted()}.
   *
   * <p>Method under test: {@link WebSocketUpgradeHandler#onCompleted()}
   */
  @Test
  @DisplayName("Test onCompleted()")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"NettyWebSocket WebSocketUpgradeHandler.onCompleted()"})
  void testOnCompleted() throws Exception {
    // Arrange, Act and Assert
    assertNull(new WebSocketUpgradeHandler(new ArrayList<>()).onCompleted());
  }

  /**
   * Test {@link WebSocketUpgradeHandler#onThrowable(Throwable)}.
   *
   * <ul>
   *   <li>Given {@link WebSocketListener} {@link WebSocketListener#onError(Throwable)} does
   *       nothing.
   *   <li>Then calls {@link WebSocketListener#onError(Throwable)}.
   * </ul>
   *
   * <p>Method under test: {@link WebSocketUpgradeHandler#onThrowable(Throwable)}
   */
  @Test
  @DisplayName(
      "Test onThrowable(Throwable); given WebSocketListener onError(Throwable) does nothing; then calls onError(Throwable)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void WebSocketUpgradeHandler.onThrowable(Throwable)"})
  void testOnThrowable_givenWebSocketListenerOnErrorDoesNothing_thenCallsOnError() {
    // Arrange
    WebSocketListener webSocketListener = mock(WebSocketListener.class);
    doNothing().when(webSocketListener).onError(Mockito.<Throwable>any());

    ArrayList<WebSocketListener> listeners = new ArrayList<>();
    listeners.add(webSocketListener);

    // Act
    new WebSocketUpgradeHandler(listeners).onThrowable(ChannelClosedException.INSTANCE);

    // Assert
    verify(webSocketListener).onError(isA(Throwable.class));
  }

  /**
   * Test {@link WebSocketUpgradeHandler#onThrowable(Throwable)}.
   *
   * <ul>
   *   <li>Given {@link WebSocketListener} {@link WebSocketListener#onError(Throwable)} does
   *       nothing.
   *   <li>Then calls {@link WebSocketListener#onError(Throwable)}.
   * </ul>
   *
   * <p>Method under test: {@link WebSocketUpgradeHandler#onThrowable(Throwable)}
   */
  @Test
  @DisplayName(
      "Test onThrowable(Throwable); given WebSocketListener onError(Throwable) does nothing; then calls onError(Throwable)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void WebSocketUpgradeHandler.onThrowable(Throwable)"})
  void testOnThrowable_givenWebSocketListenerOnErrorDoesNothing_thenCallsOnError2() {
    // Arrange
    WebSocketListener webSocketListener = mock(WebSocketListener.class);
    doNothing().when(webSocketListener).onError(Mockito.<Throwable>any());

    ArrayList<WebSocketListener> listeners = new ArrayList<>();
    listeners.add(webSocketListener);

    WebSocketUpgradeHandler webSocketUpgradeHandler = new WebSocketUpgradeHandler(listeners);
    EmbeddedChannel channel = new EmbeddedChannel();
    NettyWebSocket webSocket = new NettyWebSocket(channel, new DefaultHttpHeaders());
    webSocketUpgradeHandler.setWebSocket(webSocket);

    // Act
    webSocketUpgradeHandler.onThrowable(ChannelClosedException.INSTANCE);

    // Assert
    verify(webSocketListener).onError(isA(Throwable.class));
  }

  /**
   * Test {@link WebSocketUpgradeHandler#onOpen(NettyWebSocket)}.
   *
   * <p>Method under test: {@link WebSocketUpgradeHandler#onOpen(NettyWebSocket)}
   */
  @Test
  @DisplayName("Test onOpen(NettyWebSocket)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void WebSocketUpgradeHandler.onOpen(NettyWebSocket)"})
  void testOnOpen() {
    // Arrange
    WebSocketUpgradeHandler webSocketUpgradeHandler =
        new WebSocketUpgradeHandler(new ArrayList<>());
    EmbeddedChannel channel = new EmbeddedChannel();
    NettyWebSocket webSocket = new NettyWebSocket(channel, new DefaultHttpHeaders());

    // Act
    webSocketUpgradeHandler.onOpen(webSocket);

    // Assert
    assertTrue(webSocket.isReady());
  }

  /**
   * Test {@link WebSocketUpgradeHandler#onOpen(NettyWebSocket)}.
   *
   * <ul>
   *   <li>Given {@link BinaryWebSocketFrame#BinaryWebSocketFrame()}.
   * </ul>
   *
   * <p>Method under test: {@link WebSocketUpgradeHandler#onOpen(NettyWebSocket)}
   */
  @Test
  @DisplayName("Test onOpen(NettyWebSocket); given BinaryWebSocketFrame()")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void WebSocketUpgradeHandler.onOpen(NettyWebSocket)"})
  void testOnOpen_givenBinaryWebSocketFrame() {
    // Arrange
    WebSocketUpgradeHandler webSocketUpgradeHandler =
        new WebSocketUpgradeHandler(new ArrayList<>());
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
   *
   * <ul>
   *   <li>Given {@link BinaryWebSocketFrame#BinaryWebSocketFrame(ByteBuf)} with binaryData is
   *       {@link DuplicatedByteBuf#DuplicatedByteBuf(ByteBuf)}.
   * </ul>
   *
   * <p>Method under test: {@link WebSocketUpgradeHandler#onOpen(NettyWebSocket)}
   */
  @Test
  @DisplayName(
      "Test onOpen(NettyWebSocket); given BinaryWebSocketFrame(ByteBuf) with binaryData is DuplicatedByteBuf(ByteBuf)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void WebSocketUpgradeHandler.onOpen(NettyWebSocket)"})
  void testOnOpen_givenBinaryWebSocketFrameWithBinaryDataIsDuplicatedByteBuf() {
    // Arrange
    WebSocketUpgradeHandler webSocketUpgradeHandler =
        new WebSocketUpgradeHandler(new ArrayList<>());
    EmbeddedChannel channel = new EmbeddedChannel();

    NettyWebSocket webSocket = new NettyWebSocket(channel, new DefaultHttpHeaders());
    DuplicatedByteBuf binaryData =
        new DuplicatedByteBuf(new EmptyByteBuf(new AdaptiveByteBufAllocator()));
    webSocket.bufferFrame(new BinaryWebSocketFrame(binaryData));

    // Act
    webSocketUpgradeHandler.onOpen(webSocket);

    // Assert
    assertTrue(webSocket.isReady());
  }

  /**
   * Test {@link WebSocketUpgradeHandler#onOpen(NettyWebSocket)}.
   *
   * <ul>
   *   <li>Given {@link Builder} (default constructor) build.
   * </ul>
   *
   * <p>Method under test: {@link WebSocketUpgradeHandler#onOpen(NettyWebSocket)}
   */
  @Test
  @DisplayName("Test onOpen(NettyWebSocket); given Builder (default constructor) build")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void WebSocketUpgradeHandler.onOpen(NettyWebSocket)"})
  void testOnOpen_givenBuilderBuild() {
    // Arrange
    WebSocketUpgradeHandler webSocketUpgradeHandler = new Builder().build();
    EmbeddedChannel channel = new EmbeddedChannel();

    NettyWebSocket webSocket = new NettyWebSocket(channel, new DefaultHttpHeaders());
    DuplicatedByteBuf buffer =
        new DuplicatedByteBuf(new EmptyByteBuf(new AdaptiveByteBufAllocator()));
    ReadOnlyByteBuf binaryData = new ReadOnlyByteBuf(buffer);
    webSocket.bufferFrame(new PongWebSocketFrame(true, 1, binaryData));

    // Act
    webSocketUpgradeHandler.onOpen(webSocket);

    // Assert
    assertTrue(webSocket.isReady());
  }

  /**
   * Test {@link WebSocketUpgradeHandler#onOpen(NettyWebSocket)}.
   *
   * <ul>
   *   <li>Given {@link CloseWebSocketFrame#CloseWebSocketFrame()}.
   * </ul>
   *
   * <p>Method under test: {@link WebSocketUpgradeHandler#onOpen(NettyWebSocket)}
   */
  @Test
  @DisplayName("Test onOpen(NettyWebSocket); given CloseWebSocketFrame()")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void WebSocketUpgradeHandler.onOpen(NettyWebSocket)"})
  void testOnOpen_givenCloseWebSocketFrame() {
    // Arrange
    WebSocketUpgradeHandler webSocketUpgradeHandler =
        new WebSocketUpgradeHandler(new ArrayList<>());
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
   *
   * <ul>
   *   <li>Given {@link CloseWebSocketFrame#CloseWebSocketFrame(boolean, int)} with finalFragment is
   *       {@code true} and rsv is one.
   * </ul>
   *
   * <p>Method under test: {@link WebSocketUpgradeHandler#onOpen(NettyWebSocket)}
   */
  @Test
  @DisplayName(
      "Test onOpen(NettyWebSocket); given CloseWebSocketFrame(boolean, int) with finalFragment is 'true' and rsv is one")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void WebSocketUpgradeHandler.onOpen(NettyWebSocket)"})
  void testOnOpen_givenCloseWebSocketFrameWithFinalFragmentIsTrueAndRsvIsOne() {
    // Arrange
    WebSocketUpgradeHandler webSocketUpgradeHandler =
        new WebSocketUpgradeHandler(new ArrayList<>());
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
   *
   * <ul>
   *   <li>Given {@link ContinuationWebSocketFrame#ContinuationWebSocketFrame()}.
   * </ul>
   *
   * <p>Method under test: {@link WebSocketUpgradeHandler#onOpen(NettyWebSocket)}
   */
  @Test
  @DisplayName("Test onOpen(NettyWebSocket); given ContinuationWebSocketFrame()")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void WebSocketUpgradeHandler.onOpen(NettyWebSocket)"})
  void testOnOpen_givenContinuationWebSocketFrame() {
    // Arrange
    WebSocketUpgradeHandler webSocketUpgradeHandler =
        new WebSocketUpgradeHandler(new ArrayList<>());
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
   *
   * <ul>
   *   <li>Given {@link PingWebSocketFrame#PingWebSocketFrame()}.
   * </ul>
   *
   * <p>Method under test: {@link WebSocketUpgradeHandler#onOpen(NettyWebSocket)}
   */
  @Test
  @DisplayName("Test onOpen(NettyWebSocket); given PingWebSocketFrame()")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void WebSocketUpgradeHandler.onOpen(NettyWebSocket)"})
  void testOnOpen_givenPingWebSocketFrame() {
    // Arrange
    WebSocketUpgradeHandler webSocketUpgradeHandler =
        new WebSocketUpgradeHandler(new ArrayList<>());
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
   *
   * <ul>
   *   <li>Given {@link PongWebSocketFrame#PongWebSocketFrame()}.
   * </ul>
   *
   * <p>Method under test: {@link WebSocketUpgradeHandler#onOpen(NettyWebSocket)}
   */
  @Test
  @DisplayName("Test onOpen(NettyWebSocket); given PongWebSocketFrame()")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void WebSocketUpgradeHandler.onOpen(NettyWebSocket)"})
  void testOnOpen_givenPongWebSocketFrame() {
    // Arrange
    WebSocketUpgradeHandler webSocketUpgradeHandler =
        new WebSocketUpgradeHandler(new ArrayList<>());
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
   *
   * <ul>
   *   <li>Given {@link TextWebSocketFrame#TextWebSocketFrame()}.
   * </ul>
   *
   * <p>Method under test: {@link WebSocketUpgradeHandler#onOpen(NettyWebSocket)}
   */
  @Test
  @DisplayName("Test onOpen(NettyWebSocket); given TextWebSocketFrame()")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void WebSocketUpgradeHandler.onOpen(NettyWebSocket)"})
  void testOnOpen_givenTextWebSocketFrame() {
    // Arrange
    WebSocketUpgradeHandler webSocketUpgradeHandler =
        new WebSocketUpgradeHandler(new ArrayList<>());
    EmbeddedChannel channel = new EmbeddedChannel();

    NettyWebSocket webSocket = new NettyWebSocket(channel, new DefaultHttpHeaders());
    webSocket.bufferFrame(new TextWebSocketFrame());

    // Act
    webSocketUpgradeHandler.onOpen(webSocket);

    // Assert
    assertTrue(webSocket.isReady());
  }
}
