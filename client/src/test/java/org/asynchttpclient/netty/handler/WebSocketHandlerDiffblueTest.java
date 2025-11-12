package org.asynchttpclient.netty.handler;

import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import io.netty.channel.Channel;
import io.netty.channel.embedded.EmbeddedChannel;
import io.netty.handler.codec.http.DefaultLastHttpContent;
import io.netty.util.Timer;
import java.nio.file.Paths;
import java.util.ArrayList;
import org.asynchttpclient.AsyncHttpClientConfig;
import org.asynchttpclient.AsyncHttpClientState;
import org.asynchttpclient.netty.NettyResponseFuture;
import org.asynchttpclient.netty.channel.ChannelManager;
import org.asynchttpclient.netty.channel.ConnectionSemaphoreFactory;
import org.asynchttpclient.netty.channel.NoopConnectionSemaphore;
import org.asynchttpclient.netty.request.NettyRequestSender;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

class WebSocketHandlerDiffblueTest {
  /**
   * Test {@link WebSocketHandler#handleRead(Channel, NettyResponseFuture, Object)}.
   *
   * <ul>
   *   <li>When {@code 42}.
   *   <li>Then calls {@link AsyncHttpClientConfig#getConnectionSemaphoreFactory()}.
   * </ul>
   *
   * <p>Method under test: {@link WebSocketHandler#handleRead(Channel, NettyResponseFuture, Object)}
   */
  @Test
  @DisplayName(
      "Test handleRead(Channel, NettyResponseFuture, Object); when '42'; then calls getConnectionSemaphoreFactory()")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void WebSocketHandler.handleRead(Channel, NettyResponseFuture, Object)"})
  void testHandleRead_when42_thenCallsGetConnectionSemaphoreFactory() throws Exception {
    // Arrange
    AsyncHttpClientConfig config = mock(AsyncHttpClientConfig.class);
    when(config.isUseLaxCookieEncoder()).thenReturn(true);
    when(config.getMaxRedirects()).thenReturn(3);
    when(config.getIoExceptionFilters()).thenReturn(new ArrayList<>());
    when(config.getResponseFilters()).thenReturn(new ArrayList<>());

    ConnectionSemaphoreFactory connectionSemaphoreFactory = mock(ConnectionSemaphoreFactory.class);
    when(connectionSemaphoreFactory.newConnectionSemaphore(Mockito.<AsyncHttpClientConfig>any()))
        .thenReturn(new NoopConnectionSemaphore());

    AsyncHttpClientConfig config2 = mock(AsyncHttpClientConfig.class);
    when(config2.isUseLaxCookieEncoder()).thenReturn(true);
    when(config2.getConnectionSemaphoreFactory()).thenReturn(connectionSemaphoreFactory);
    NettyRequestSender requestSender =
        new NettyRequestSender(
            config2,
            mock(ChannelManager.class),
            mock(Timer.class),
            mock(AsyncHttpClientState.class));

    WebSocketHandler webSocketHandler =
        new WebSocketHandler(config, mock(ChannelManager.class), requestSender);
    Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");

    // Act
    webSocketHandler.handleRead(new EmbeddedChannel(), null, "42");

    // Assert
    verify(config2, atLeast(1)).getConnectionSemaphoreFactory();
    verify(config).getIoExceptionFilters();
    verify(config).getMaxRedirects();
    verify(config).getResponseFilters();
    verify(config).isUseLaxCookieEncoder();
    verify(config2).isUseLaxCookieEncoder();
    verify(connectionSemaphoreFactory).newConnectionSemaphore(isA(AsyncHttpClientConfig.class));
  }

  /**
   * Test {@link WebSocketHandler#handleRead(Channel, NettyResponseFuture, Object)}.
   *
   * <ul>
   *   <li>When {@link DefaultLastHttpContent#DefaultLastHttpContent()}.
   *   <li>Then calls {@link AsyncHttpClientConfig#getConnectionSemaphoreFactory()}.
   * </ul>
   *
   * <p>Method under test: {@link WebSocketHandler#handleRead(Channel, NettyResponseFuture, Object)}
   */
  @Test
  @DisplayName(
      "Test handleRead(Channel, NettyResponseFuture, Object); when DefaultLastHttpContent(); then calls getConnectionSemaphoreFactory()")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void WebSocketHandler.handleRead(Channel, NettyResponseFuture, Object)"})
  void testHandleRead_whenDefaultLastHttpContent_thenCallsGetConnectionSemaphoreFactory()
      throws Exception {
    // Arrange
    AsyncHttpClientConfig config = mock(AsyncHttpClientConfig.class);
    when(config.isUseLaxCookieEncoder()).thenReturn(true);
    when(config.getMaxRedirects()).thenReturn(3);
    when(config.getIoExceptionFilters()).thenReturn(new ArrayList<>());
    when(config.getResponseFilters()).thenReturn(new ArrayList<>());

    ConnectionSemaphoreFactory connectionSemaphoreFactory = mock(ConnectionSemaphoreFactory.class);
    when(connectionSemaphoreFactory.newConnectionSemaphore(Mockito.<AsyncHttpClientConfig>any()))
        .thenReturn(new NoopConnectionSemaphore());

    AsyncHttpClientConfig config2 = mock(AsyncHttpClientConfig.class);
    when(config2.isUseLaxCookieEncoder()).thenReturn(true);
    when(config2.getConnectionSemaphoreFactory()).thenReturn(connectionSemaphoreFactory);
    NettyRequestSender requestSender =
        new NettyRequestSender(
            config2,
            mock(ChannelManager.class),
            mock(Timer.class),
            mock(AsyncHttpClientState.class));

    WebSocketHandler webSocketHandler =
        new WebSocketHandler(config, mock(ChannelManager.class), requestSender);
    EmbeddedChannel channel = new EmbeddedChannel();
    Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");

    // Act
    webSocketHandler.handleRead(channel, null, new DefaultLastHttpContent());

    // Assert
    verify(config2, atLeast(1)).getConnectionSemaphoreFactory();
    verify(config).getIoExceptionFilters();
    verify(config).getMaxRedirects();
    verify(config).getResponseFilters();
    verify(config).isUseLaxCookieEncoder();
    verify(config2).isUseLaxCookieEncoder();
    verify(connectionSemaphoreFactory).newConnectionSemaphore(isA(AsyncHttpClientConfig.class));
  }
}
