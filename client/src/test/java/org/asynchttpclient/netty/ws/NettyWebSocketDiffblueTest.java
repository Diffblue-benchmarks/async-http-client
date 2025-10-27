package org.asynchttpclient.netty.ws;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.anyBoolean;
import static org.mockito.Mockito.anyInt;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import io.netty.buffer.AdaptiveByteBufAllocator;
import io.netty.buffer.DuplicatedByteBuf;
import io.netty.buffer.EmptyByteBuf;
import io.netty.buffer.ReadOnlyByteBuf;
import io.netty.channel.Channel;
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
import java.util.ArrayList;
import org.asynchttpclient.AsyncHttpClientConfig;
import org.asynchttpclient.netty.channel.ChannelManager;
import org.asynchttpclient.netty.handler.HttpHandler;
import org.asynchttpclient.ws.WebSocket;
import org.asynchttpclient.ws.WebSocketListener;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

class NettyWebSocketDiffblueTest {
  /**
   * Methods under test:
   * <ul>
   *   <li>{@link NettyWebSocket#NettyWebSocket(Channel, HttpHeaders)}
   *   <li>{@link NettyWebSocket#toString()}
   *   <li>{@link NettyWebSocket#getUpgradeHeaders()}
   *   <li>{@link NettyWebSocket#isReady()}
   * </ul>
   */
  @Test
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
   * Method under test: {@link NettyWebSocket#handleFrame(WebSocketFrame)}
   */
  @Test
  void testHandleFrame() {
    // Arrange
    AsyncHttpClientConfig config = mock(AsyncHttpClientConfig.class);
    when(config.isUseLaxCookieEncoder()).thenReturn(true);
    when(config.getMaxRedirects()).thenReturn(3);
    when(config.getIoExceptionFilters()).thenReturn(new ArrayList<>());
    when(config.getResponseFilters()).thenReturn(new ArrayList<>());
    EmbeddedChannel channel = new EmbeddedChannel(new HttpHandler(config, mock(ChannelManager.class), null));
    NettyWebSocket nettyWebSocket = new NettyWebSocket(channel, new DefaultHttpHeaders());

    // Act
    nettyWebSocket.handleFrame(new BinaryWebSocketFrame());

    // Assert that nothing has changed
    verify(config).getIoExceptionFilters();
    verify(config).getMaxRedirects();
    verify(config).getResponseFilters();
    verify(config).isUseLaxCookieEncoder();
  }

  /**
   * Method under test: {@link NettyWebSocket#handleFrame(WebSocketFrame)}
   */
  @Test
  void testHandleFrame2() {
    // Arrange
    AsyncHttpClientConfig config = mock(AsyncHttpClientConfig.class);
    when(config.isUseLaxCookieEncoder()).thenReturn(true);
    when(config.getMaxRedirects()).thenReturn(3);
    when(config.getIoExceptionFilters()).thenReturn(new ArrayList<>());
    when(config.getResponseFilters()).thenReturn(new ArrayList<>());
    EmbeddedChannel channel = new EmbeddedChannel(new HttpHandler(config, mock(ChannelManager.class), null));
    WebSocketListener l = mock(WebSocketListener.class);
    doNothing().when(l).onBinaryFrame(Mockito.<byte[]>any(), anyBoolean(), anyInt());

    NettyWebSocket nettyWebSocket = new NettyWebSocket(channel, new DefaultHttpHeaders());
    nettyWebSocket.addWebSocketListener(l);

    // Act
    nettyWebSocket.handleFrame(new BinaryWebSocketFrame());

    // Assert
    verify(config).getIoExceptionFilters();
    verify(config).getMaxRedirects();
    verify(config).getResponseFilters();
    verify(config).isUseLaxCookieEncoder();
    verify(l).onBinaryFrame(isA(byte[].class), eq(true), eq(0));
  }

  /**
   * Method under test: {@link NettyWebSocket#handleFrame(WebSocketFrame)}
   */
  @Test
  void testHandleFrame3() {
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
    assertThrows(IllegalArgumentException.class, () -> nettyWebSocket.handleFrame(new BinaryWebSocketFrame()));
    verify(config).getIoExceptionFilters();
    verify(config).getMaxRedirects();
    verify(config).getResponseFilters();
    verify(config).isUseLaxCookieEncoder();
    verify(l2).onBinaryFrame(isA(byte[].class), eq(true), eq(0));
  }

  /**
   * Method under test: {@link NettyWebSocket#handleFrame(WebSocketFrame)}
   */
  @Test
  void testHandleFrame4() {
    // Arrange
    AsyncHttpClientConfig config = mock(AsyncHttpClientConfig.class);
    when(config.isUseLaxCookieEncoder()).thenReturn(true);
    when(config.getMaxRedirects()).thenReturn(3);
    when(config.getIoExceptionFilters()).thenReturn(new ArrayList<>());
    when(config.getResponseFilters()).thenReturn(new ArrayList<>());
    EmbeddedChannel channel = new EmbeddedChannel(new HttpHandler(config, mock(ChannelManager.class), null));
    WebSocketListener l = mock(WebSocketListener.class);
    doNothing().when(l).onTextFrame(Mockito.<String>any(), anyBoolean(), anyInt());
    WebSocketListener l2 = mock(WebSocketListener.class);
    doNothing().when(l2).onTextFrame(Mockito.<String>any(), anyBoolean(), anyInt());

    NettyWebSocket nettyWebSocket = new NettyWebSocket(channel, new DefaultHttpHeaders());
    nettyWebSocket.addWebSocketListener(l2);
    nettyWebSocket.addWebSocketListener(l);

    // Act
    nettyWebSocket.handleFrame(new TextWebSocketFrame());

    // Assert
    verify(config).getIoExceptionFilters();
    verify(config).getMaxRedirects();
    verify(config).getResponseFilters();
    verify(config).isUseLaxCookieEncoder();
    verify(l2).onTextFrame(eq(""), eq(true), eq(0));
    verify(l).onTextFrame(eq(""), eq(true), eq(0));
  }

  /**
   * Method under test: {@link NettyWebSocket#handleFrame(WebSocketFrame)}
   */
  @Test
  void testHandleFrame5() {
    // Arrange
    AsyncHttpClientConfig config = mock(AsyncHttpClientConfig.class);
    when(config.isUseLaxCookieEncoder()).thenReturn(true);
    when(config.getMaxRedirects()).thenReturn(3);
    when(config.getIoExceptionFilters()).thenReturn(new ArrayList<>());
    when(config.getResponseFilters()).thenReturn(new ArrayList<>());
    EmbeddedChannel channel = new EmbeddedChannel(new HttpHandler(config, mock(ChannelManager.class), null));
    WebSocketListener l = mock(WebSocketListener.class);
    doThrow(new IllegalArgumentException("foo")).when(l).onTextFrame(Mockito.<String>any(), anyBoolean(), anyInt());
    WebSocketListener l2 = mock(WebSocketListener.class);
    doNothing().when(l2).onTextFrame(Mockito.<String>any(), anyBoolean(), anyInt());

    NettyWebSocket nettyWebSocket = new NettyWebSocket(channel, new DefaultHttpHeaders());
    nettyWebSocket.addWebSocketListener(l2);
    nettyWebSocket.addWebSocketListener(l);

    // Act and Assert
    assertThrows(IllegalArgumentException.class, () -> nettyWebSocket.handleFrame(new TextWebSocketFrame()));
    verify(config).getIoExceptionFilters();
    verify(config).getMaxRedirects();
    verify(config).getResponseFilters();
    verify(config).isUseLaxCookieEncoder();
    verify(l2).onTextFrame(eq(""), eq(true), eq(0));
    verify(l).onTextFrame(eq(""), eq(true), eq(0));
  }

  /**
   * Method under test: {@link NettyWebSocket#handleFrame(WebSocketFrame)}
   */
  @Test
  void testHandleFrame6() {
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
    nettyWebSocket.handleFrame(null);

    // Assert that nothing has changed
    verify(config).getIoExceptionFilters();
    verify(config).getMaxRedirects();
    verify(config).getResponseFilters();
    verify(config).isUseLaxCookieEncoder();
  }

  /**
   * Method under test: {@link NettyWebSocket#handleFrame(WebSocketFrame)}
   */
  @Test
  void testHandleFrame7() {
    // Arrange
    AsyncHttpClientConfig config = mock(AsyncHttpClientConfig.class);
    when(config.isUseLaxCookieEncoder()).thenReturn(true);
    when(config.getMaxRedirects()).thenReturn(3);
    when(config.getIoExceptionFilters()).thenReturn(new ArrayList<>());
    when(config.getResponseFilters()).thenReturn(new ArrayList<>());
    EmbeddedChannel channel = new EmbeddedChannel(new HttpHandler(config, mock(ChannelManager.class), null));
    WebSocketListener l = mock(WebSocketListener.class);
    doNothing().when(l).onTextFrame(Mockito.<String>any(), anyBoolean(), anyInt());
    WebSocketListener l2 = mock(WebSocketListener.class);
    doNothing().when(l2).onTextFrame(Mockito.<String>any(), anyBoolean(), anyInt());

    NettyWebSocket nettyWebSocket = new NettyWebSocket(channel, new DefaultHttpHeaders());
    nettyWebSocket.addWebSocketListener(l2);
    nettyWebSocket.addWebSocketListener(l);

    // Act
    nettyWebSocket.handleFrame(new TextWebSocketFrame("https://example.org/example"));

    // Assert
    verify(config).getIoExceptionFilters();
    verify(config).getMaxRedirects();
    verify(config).getResponseFilters();
    verify(config).isUseLaxCookieEncoder();
    verify(l2).onTextFrame(eq("https://example.org/example"), eq(true), eq(0));
    verify(l).onTextFrame(eq("https://example.org/example"), eq(true), eq(0));
  }

  /**
   * Method under test: {@link NettyWebSocket#handleFrame(WebSocketFrame)}
   */
  @Test
  void testHandleFrame8() {
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
        new BinaryWebSocketFrame(new DuplicatedByteBuf(new EmptyByteBuf(new AdaptiveByteBufAllocator())))));
    verify(config).getIoExceptionFilters();
    verify(config).getMaxRedirects();
    verify(config).getResponseFilters();
    verify(config).isUseLaxCookieEncoder();
    verify(l2).onBinaryFrame(isA(byte[].class), eq(true), eq(0));
  }

  /**
   * Method under test: {@link NettyWebSocket#handleFrame(WebSocketFrame)}
   */
  @Test
  void testHandleFrame9() {
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
    doNothing().when(l).onClose(Mockito.<WebSocket>any(), anyInt(), Mockito.<String>any());
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
  }

  /**
   * Method under test: {@link NettyWebSocket#handleFrame(WebSocketFrame)}
   */
  @Test
  void testHandleFrame10() {
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
    doThrow(new IllegalArgumentException("Unexpected I/O exception on channel {}")).when(l)
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
   * Method under test: {@link NettyWebSocket#handleFrame(WebSocketFrame)}
   */
  @Test
  void testHandleFrame11() {
    // Arrange
    AsyncHttpClientConfig config = mock(AsyncHttpClientConfig.class);
    when(config.isUseLaxCookieEncoder()).thenReturn(true);
    when(config.getMaxRedirects()).thenReturn(3);
    when(config.getIoExceptionFilters()).thenReturn(new ArrayList<>());
    when(config.getResponseFilters()).thenReturn(new ArrayList<>());
    EmbeddedChannel channel = new EmbeddedChannel(new HttpHandler(config, mock(ChannelManager.class), null));
    WebSocketListener l = mock(WebSocketListener.class);
    doThrow(new IllegalArgumentException("Unexpected I/O exception on channel {}")).when(l)
        .onError(Mockito.<Throwable>any());
    doThrow(new IllegalArgumentException("Unexpected I/O exception on channel {}")).when(l)
        .onClose(Mockito.<WebSocket>any(), anyInt(), Mockito.<String>any());
    WebSocketListener l2 = mock(WebSocketListener.class);
    doNothing().when(l2).onClose(Mockito.<WebSocket>any(), anyInt(), Mockito.<String>any());

    NettyWebSocket nettyWebSocket = new NettyWebSocket(channel, new DefaultHttpHeaders());
    nettyWebSocket.addWebSocketListener(l2);
    nettyWebSocket.addWebSocketListener(l);

    // Act and Assert
    assertThrows(IllegalArgumentException.class, () -> nettyWebSocket.handleFrame(new CloseWebSocketFrame()));
    verify(config).getIoExceptionFilters();
    verify(config).getMaxRedirects();
    verify(config).getResponseFilters();
    verify(config).isUseLaxCookieEncoder();
    verify(l2).onClose(isA(WebSocket.class), eq(-1), eq(""));
    verify(l).onClose(isA(WebSocket.class), eq(-1), eq(""));
    verify(l).onError(isA(Throwable.class));
  }

  /**
   * Method under test: {@link NettyWebSocket#handleFrame(WebSocketFrame)}
   */
  @Test
  void testHandleFrame12() {
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
   * Method under test: {@link NettyWebSocket#handleFrame(WebSocketFrame)}
   */
  @Test
  void testHandleFrame13() {
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
   * Method under test: {@link NettyWebSocket#handleFrame(WebSocketFrame)}
   */
  @Test
  void testHandleFrame14() {
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
   * Method under test: {@link NettyWebSocket#handleFrame(WebSocketFrame)}
   */
  @Test
  void testHandleFrame15() {
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
   * Method under test: {@link NettyWebSocket#handleFrame(WebSocketFrame)}
   */
  @Test
  void testHandleFrame16() {
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
   * Method under test: {@link NettyWebSocket#handleFrame(WebSocketFrame)}
   */
  @Test
  void testHandleFrame17() {
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
}
