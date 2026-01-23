package org.asynchttpclient.ws;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import io.netty.buffer.AdaptiveByteBufAllocator;
import io.netty.buffer.DuplicatedByteBuf;
import io.netty.buffer.EmptyByteBuf;
import io.netty.buffer.Unpooled;
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
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicReference;
import org.asynchttpclient.AsyncHandler;
import org.asynchttpclient.AsyncHandler.State;
import org.asynchttpclient.HttpResponseBodyPart;
import org.asynchttpclient.netty.EagerResponseBodyPart;
import org.asynchttpclient.netty.NettyResponseStatus;
import org.asynchttpclient.netty.ws.NettyWebSocket;
import org.asynchttpclient.uri.Uri;
import org.asynchttpclient.ws.CloseCodeReasonMessageTest.Listener;
import org.asynchttpclient.ws.WebSocketUpgradeHandler.Builder;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

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
    CountDownLatch latch = new CountDownLatch(3);
    Listener listener = new Listener(latch, new AtomicReference<>());

    // Act
    Builder actualAddWebSocketListenerResult = builder.addWebSocketListener(listener);

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
    CountDownLatch latch = new CountDownLatch(3);
    Listener listener = new Listener(latch, new AtomicReference<>());

    // Act
    Builder actualRemoveWebSocketListenerResult = builder.removeWebSocketListener(listener);

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
   * Test {@link WebSocketUpgradeHandler#setWebSocket(NettyWebSocket)}.
   *
   * <p>Method under test: {@link WebSocketUpgradeHandler#setWebSocket(NettyWebSocket)}
   */
  @Test
  @DisplayName("Test setWebSocket(NettyWebSocket)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void WebSocketUpgradeHandler.setWebSocket(NettyWebSocket)"})
  void testSetWebSocket() throws Exception {
    // Arrange
    WebSocketUpgradeHandler webSocketUpgradeHandler =
        new WebSocketUpgradeHandler(new ArrayList<>());
    EmbeddedChannel channel = new EmbeddedChannel();
    NettyWebSocket webSocket = new NettyWebSocket(channel, new DefaultHttpHeaders());

    // Act
    webSocketUpgradeHandler.setWebSocket(webSocket);

    // Assert
    assertSame(webSocket, webSocketUpgradeHandler.onCompleted());
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
    assertTrue(webSocket.isOpen());
    assertTrue(webSocket.isReady());
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
  void testOnOpen2() {
    // Arrange
    ArrayList<WebSocketListener> listeners = new ArrayList<>();
    CountDownLatch latch = new CountDownLatch(3);
    Listener listener = new Listener(latch, new AtomicReference<>());
    listeners.add(listener);
    WebSocketUpgradeHandler webSocketUpgradeHandler = new WebSocketUpgradeHandler(listeners);
    EmbeddedChannel channel = new EmbeddedChannel();
    NettyWebSocket webSocket = new NettyWebSocket(channel, new DefaultHttpHeaders());

    // Act
    webSocketUpgradeHandler.onOpen(webSocket);

    // Assert
    assertTrue(webSocket.isOpen());
    assertTrue(webSocket.isReady());
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
  void testOnOpen3() {
    // Arrange
    WebSocketUpgradeHandler webSocketUpgradeHandler =
        new WebSocketUpgradeHandler(new ArrayList<>());
    EmbeddedChannel channel = new EmbeddedChannel();

    NettyWebSocket webSocket = new NettyWebSocket(channel, new DefaultHttpHeaders());
    webSocket.bufferFrame(new CloseWebSocketFrame());

    // Act
    webSocketUpgradeHandler.onOpen(webSocket);

    // Assert
    assertNull(webSocket.getLocalAddress());
    assertNull(webSocket.getRemoteAddress());
    assertFalse(webSocket.isOpen());
    assertTrue(webSocket.isReady());
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
  void testOnOpen4() {
    // Arrange
    ArrayList<WebSocketListener> listeners = new ArrayList<>();
    CountDownLatch latch = new CountDownLatch(3);
    Listener listener = new Listener(latch, new AtomicReference<>());
    listeners.add(listener);
    WebSocketUpgradeHandler webSocketUpgradeHandler = new WebSocketUpgradeHandler(listeners);
    EmbeddedChannel channel = new EmbeddedChannel();

    NettyWebSocket webSocket = new NettyWebSocket(channel, new DefaultHttpHeaders());
    webSocket.bufferFrame(new PongWebSocketFrame(true, 1, Unpooled.compositeBuffer(3)));

    // Act
    webSocketUpgradeHandler.onOpen(webSocket);

    // Assert
    assertTrue(webSocket.isOpen());
    assertTrue(webSocket.isReady());
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
  void testOnOpen5() {
    // Arrange
    ArrayList<WebSocketListener> listeners = new ArrayList<>();
    CountDownLatch latch = new CountDownLatch(3);
    Listener listener = new Listener(latch, new AtomicReference<>());
    listeners.add(listener);
    WebSocketUpgradeHandler webSocketUpgradeHandler = new WebSocketUpgradeHandler(listeners);
    EmbeddedChannel channel = new EmbeddedChannel();

    NettyWebSocket webSocket = new NettyWebSocket(channel, new DefaultHttpHeaders());
    webSocket.bufferFrame(new CloseWebSocketFrame());

    // Act
    webSocketUpgradeHandler.onOpen(webSocket);

    // Assert
    assertNull(webSocket.getLocalAddress());
    assertNull(webSocket.getRemoteAddress());
    assertFalse(webSocket.isOpen());
    assertTrue(webSocket.isReady());
  }

  /**
   * Test {@link WebSocketUpgradeHandler#onOpen(NettyWebSocket)}.
   *
   * <ul>
   *   <li>Given {@link ArrayList#ArrayList()} add {@link Listener#Listener(CountDownLatch,
   *       AtomicReference)} with latch is {@link CountDownLatch#CountDownLatch(int)} and text is
   *       {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link WebSocketUpgradeHandler#onOpen(NettyWebSocket)}
   */
  @Test
  @DisplayName(
      "Test onOpen(NettyWebSocket); given ArrayList() add Listener(CountDownLatch, AtomicReference) with latch is CountDownLatch(int) and text is 'null'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void WebSocketUpgradeHandler.onOpen(NettyWebSocket)"})
  void testOnOpen_givenArrayListAddListenerWithLatchIsCountDownLatchAndTextIsNull() {
    // Arrange
    ArrayList<WebSocketListener> listeners = new ArrayList<>();
    Listener listener = new Listener(new CountDownLatch(3), null);
    listeners.add(listener);
    WebSocketUpgradeHandler webSocketUpgradeHandler = new WebSocketUpgradeHandler(listeners);
    EmbeddedChannel channel = new EmbeddedChannel();

    NettyWebSocket webSocket = new NettyWebSocket(channel, new DefaultHttpHeaders());
    webSocket.bufferFrame(new CloseWebSocketFrame());

    // Act
    webSocketUpgradeHandler.onOpen(webSocket);

    // Assert
    assertNull(webSocket.getLocalAddress());
    assertNull(webSocket.getRemoteAddress());
    assertFalse(webSocket.isOpen());
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
    assertTrue(webSocket.isOpen());
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
  void testOnOpen_givenBinaryWebSocketFrame2() {
    // Arrange
    ArrayList<WebSocketListener> listeners = new ArrayList<>();
    CountDownLatch latch = new CountDownLatch(3);
    Listener listener = new Listener(latch, new AtomicReference<>());
    listeners.add(listener);
    WebSocketUpgradeHandler webSocketUpgradeHandler = new WebSocketUpgradeHandler(listeners);
    EmbeddedChannel channel = new EmbeddedChannel();

    NettyWebSocket webSocket = new NettyWebSocket(channel, new DefaultHttpHeaders());
    webSocket.bufferFrame(new BinaryWebSocketFrame());

    // Act
    webSocketUpgradeHandler.onOpen(webSocket);

    // Assert
    assertTrue(webSocket.isOpen());
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
    assertNull(webSocket.getLocalAddress());
    assertNull(webSocket.getRemoteAddress());
    assertFalse(webSocket.isOpen());
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
    assertTrue(webSocket.isOpen());
    assertTrue(webSocket.isReady());
  }

  /**
   * Test {@link WebSocketUpgradeHandler#onOpen(NettyWebSocket)}.
   *
   * <ul>
   *   <li>Given {@link DuplicatedByteBuf#DuplicatedByteBuf(ByteBuf)} with buffer is {@link
   *       EmptyByteBuf#EmptyByteBuf(ByteBufAllocator)}.
   * </ul>
   *
   * <p>Method under test: {@link WebSocketUpgradeHandler#onOpen(NettyWebSocket)}
   */
  @Test
  @DisplayName(
      "Test onOpen(NettyWebSocket); given DuplicatedByteBuf(ByteBuf) with buffer is EmptyByteBuf(ByteBufAllocator)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void WebSocketUpgradeHandler.onOpen(NettyWebSocket)"})
  void testOnOpen_givenDuplicatedByteBufWithBufferIsEmptyByteBuf() {
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
    assertTrue(webSocket.isOpen());
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
    assertTrue(webSocket.isOpen());
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
  void testOnOpen_givenPingWebSocketFrame2() {
    // Arrange
    ArrayList<WebSocketListener> listeners = new ArrayList<>();
    CountDownLatch latch = new CountDownLatch(3);
    Listener listener = new Listener(latch, new AtomicReference<>());
    listeners.add(listener);
    WebSocketUpgradeHandler webSocketUpgradeHandler = new WebSocketUpgradeHandler(listeners);
    EmbeddedChannel channel = new EmbeddedChannel();

    NettyWebSocket webSocket = new NettyWebSocket(channel, new DefaultHttpHeaders());
    webSocket.bufferFrame(new PingWebSocketFrame());

    // Act
    webSocketUpgradeHandler.onOpen(webSocket);

    // Assert
    assertTrue(webSocket.isOpen());
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
    assertTrue(webSocket.isOpen());
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
    assertTrue(webSocket.isOpen());
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
  void testOnOpen_givenTextWebSocketFrame2() {
    // Arrange
    ArrayList<WebSocketListener> listeners = new ArrayList<>();
    CountDownLatch latch = new CountDownLatch(3);
    Listener listener = new Listener(latch, new AtomicReference<>());
    listeners.add(listener);
    WebSocketUpgradeHandler webSocketUpgradeHandler = new WebSocketUpgradeHandler(listeners);
    EmbeddedChannel channel = new EmbeddedChannel();

    NettyWebSocket webSocket = new NettyWebSocket(channel, new DefaultHttpHeaders());
    webSocket.bufferFrame(new TextWebSocketFrame());

    // Act
    webSocketUpgradeHandler.onOpen(webSocket);

    // Assert
    assertTrue(webSocket.isOpen());
    assertTrue(webSocket.isReady());
  }

  /**
   * Test {@link WebSocketUpgradeHandler#onOpen(NettyWebSocket)}.
   *
   * <ul>
   *   <li>Given {@link TextWebSocketFrame#TextWebSocketFrame(String)} with text is empty string.
   * </ul>
   *
   * <p>Method under test: {@link WebSocketUpgradeHandler#onOpen(NettyWebSocket)}
   */
  @Test
  @DisplayName(
      "Test onOpen(NettyWebSocket); given TextWebSocketFrame(String) with text is empty string")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void WebSocketUpgradeHandler.onOpen(NettyWebSocket)"})
  void testOnOpen_givenTextWebSocketFrameWithTextIsEmptyString() {
    // Arrange
    ArrayList<WebSocketListener> listeners = new ArrayList<>();
    CountDownLatch latch = new CountDownLatch(3);
    Listener listener = new Listener(latch, new AtomicReference<>());
    listeners.add(listener);
    WebSocketUpgradeHandler webSocketUpgradeHandler = new WebSocketUpgradeHandler(listeners);
    EmbeddedChannel channel = new EmbeddedChannel();

    NettyWebSocket webSocket = new NettyWebSocket(channel, new DefaultHttpHeaders());
    webSocket.bufferFrame(new TextWebSocketFrame(""));

    // Act
    webSocketUpgradeHandler.onOpen(webSocket);

    // Assert
    assertTrue(webSocket.isOpen());
    assertTrue(webSocket.isReady());
  }

  /**
   * Test {@link WebSocketUpgradeHandler#onOpen(NettyWebSocket)}.
   *
   * <ul>
   *   <li>Given {@link TextWebSocketFrame#TextWebSocketFrame(String)} with text is {@code
   *       https://example.org/example}.
   * </ul>
   *
   * <p>Method under test: {@link WebSocketUpgradeHandler#onOpen(NettyWebSocket)}
   */
  @Test
  @DisplayName(
      "Test onOpen(NettyWebSocket); given TextWebSocketFrame(String) with text is 'https://example.org/example'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void WebSocketUpgradeHandler.onOpen(NettyWebSocket)"})
  void testOnOpen_givenTextWebSocketFrameWithTextIsHttpsExampleOrgExample() {
    // Arrange
    ArrayList<WebSocketListener> listeners = new ArrayList<>();
    CountDownLatch latch = new CountDownLatch(3);
    Listener listener = new Listener(latch, new AtomicReference<>());
    listeners.add(listener);
    WebSocketUpgradeHandler webSocketUpgradeHandler = new WebSocketUpgradeHandler(listeners);
    EmbeddedChannel channel = new EmbeddedChannel();

    NettyWebSocket webSocket = new NettyWebSocket(channel, new DefaultHttpHeaders());
    webSocket.bufferFrame(new TextWebSocketFrame("https://example.org/example"));

    // Act
    webSocketUpgradeHandler.onOpen(webSocket);

    // Assert
    assertTrue(webSocket.isOpen());
    assertTrue(webSocket.isReady());
  }
}
