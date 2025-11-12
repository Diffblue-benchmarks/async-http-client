package org.asynchttpclient.netty.handler;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.ArgumentMatchers.isNull;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.embedded.EmbeddedChannel;
import io.netty.channel.local.LocalChannel;
import io.netty.handler.codec.PrematureChannelClosureException;
import io.netty.handler.codec.http.DefaultLastHttpContent;
import io.netty.util.Timer;
import java.util.ArrayList;
import org.asynchttpclient.AsyncHttpClientConfig;
import org.asynchttpclient.AsyncHttpClientState;
import org.asynchttpclient.exception.ChannelClosedException;
import org.asynchttpclient.netty.channel.ChannelManager;
import org.asynchttpclient.netty.channel.ConnectionSemaphoreFactory;
import org.asynchttpclient.netty.channel.NoopConnectionSemaphore;
import org.asynchttpclient.netty.request.NettyRequestSender;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

class AsyncHttpClientHandlerDiffblueTest {
  /**
   * Test {@link AsyncHttpClientHandler#channelRead(ChannelHandlerContext, Object)}.
   *
   * <ul>
   *   <li>Given {@link EmbeddedChannel#EmbeddedChannel()}.
   * </ul>
   *
   * <p>Method under test: {@link AsyncHttpClientHandler#channelRead(ChannelHandlerContext, Object)}
   */
  @Test
  @DisplayName("Test channelRead(ChannelHandlerContext, Object); given EmbeddedChannel()")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void AsyncHttpClientHandler.channelRead(ChannelHandlerContext, Object)"})
  void testChannelRead_givenEmbeddedChannel() throws Exception {
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

    HttpHandler httpHandler = new HttpHandler(config, mock(ChannelManager.class), requestSender);

    ChannelHandlerContext ctx = mock(ChannelHandlerContext.class);
    when(ctx.channel()).thenReturn(new EmbeddedChannel());

    // Act
    httpHandler.channelRead(ctx, "Msg");

    // Assert
    verify(ctx).channel();
    verify(config2, atLeast(1)).getConnectionSemaphoreFactory();
    verify(config).getIoExceptionFilters();
    verify(config).getMaxRedirects();
    verify(config).getResponseFilters();
    verify(config).isUseLaxCookieEncoder();
    verify(config2).isUseLaxCookieEncoder();
    verify(connectionSemaphoreFactory).newConnectionSemaphore(isA(AsyncHttpClientConfig.class));
  }

  /**
   * Test {@link AsyncHttpClientHandler#channelRead(ChannelHandlerContext, Object)}.
   *
   * <ul>
   *   <li>Given {@link EmbeddedChannel#EmbeddedChannel()} write {@code Msg}.
   * </ul>
   *
   * <p>Method under test: {@link AsyncHttpClientHandler#channelRead(ChannelHandlerContext, Object)}
   */
  @Test
  @DisplayName(
      "Test channelRead(ChannelHandlerContext, Object); given EmbeddedChannel() write 'Msg'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void AsyncHttpClientHandler.channelRead(ChannelHandlerContext, Object)"})
  void testChannelRead_givenEmbeddedChannelWriteMsg() throws Exception {
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

    HttpHandler httpHandler = new HttpHandler(config, mock(ChannelManager.class), requestSender);

    EmbeddedChannel embeddedChannel = new EmbeddedChannel();
    embeddedChannel.write("Msg");

    ChannelHandlerContext ctx = mock(ChannelHandlerContext.class);
    when(ctx.channel()).thenReturn(embeddedChannel);

    // Act
    httpHandler.channelRead(ctx, "Msg");

    // Assert
    verify(ctx).channel();
    verify(config2, atLeast(1)).getConnectionSemaphoreFactory();
    verify(config).getIoExceptionFilters();
    verify(config).getMaxRedirects();
    verify(config).getResponseFilters();
    verify(config).isUseLaxCookieEncoder();
    verify(config2).isUseLaxCookieEncoder();
    verify(connectionSemaphoreFactory).newConnectionSemaphore(isA(AsyncHttpClientConfig.class));
  }

  /**
   * Test {@link AsyncHttpClientHandler#channelRead(ChannelHandlerContext, Object)}.
   *
   * <ul>
   *   <li>Given {@link EmbeddedChannel#EmbeddedChannel()}.
   *   <li>When {@link DefaultLastHttpContent#DefaultLastHttpContent()}.
   *   <li>Then calls {@link ChannelHandlerContext#channel()}.
   * </ul>
   *
   * <p>Method under test: {@link AsyncHttpClientHandler#channelRead(ChannelHandlerContext, Object)}
   */
  @Test
  @DisplayName(
      "Test channelRead(ChannelHandlerContext, Object); given EmbeddedChannel(); when DefaultLastHttpContent(); then calls channel()")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void AsyncHttpClientHandler.channelRead(ChannelHandlerContext, Object)"})
  void testChannelRead_givenEmbeddedChannel_whenDefaultLastHttpContent_thenCallsChannel()
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

    HttpHandler httpHandler = new HttpHandler(config, mock(ChannelManager.class), requestSender);

    ChannelHandlerContext ctx = mock(ChannelHandlerContext.class);
    when(ctx.channel()).thenReturn(new EmbeddedChannel());

    // Act
    httpHandler.channelRead(ctx, new DefaultLastHttpContent());

    // Assert
    verify(ctx).channel();
    verify(config2, atLeast(1)).getConnectionSemaphoreFactory();
    verify(config).getIoExceptionFilters();
    verify(config).getMaxRedirects();
    verify(config).getResponseFilters();
    verify(config).isUseLaxCookieEncoder();
    verify(config2).isUseLaxCookieEncoder();
    verify(connectionSemaphoreFactory).newConnectionSemaphore(isA(AsyncHttpClientConfig.class));
  }

  /**
   * Test {@link AsyncHttpClientHandler#channelRead(ChannelHandlerContext, Object)}.
   *
   * <ul>
   *   <li>Given {@link LocalChannel#LocalChannel()}.
   * </ul>
   *
   * <p>Method under test: {@link AsyncHttpClientHandler#channelRead(ChannelHandlerContext, Object)}
   */
  @Test
  @DisplayName("Test channelRead(ChannelHandlerContext, Object); given LocalChannel()")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void AsyncHttpClientHandler.channelRead(ChannelHandlerContext, Object)"})
  void testChannelRead_givenLocalChannel() throws Exception {
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

    HttpHandler httpHandler = new HttpHandler(config, mock(ChannelManager.class), requestSender);

    ChannelHandlerContext ctx = mock(ChannelHandlerContext.class);
    when(ctx.channel()).thenReturn(new LocalChannel());

    // Act
    httpHandler.channelRead(ctx, "Msg");

    // Assert
    verify(ctx).channel();
    verify(config2, atLeast(1)).getConnectionSemaphoreFactory();
    verify(config).getIoExceptionFilters();
    verify(config).getMaxRedirects();
    verify(config).getResponseFilters();
    verify(config).isUseLaxCookieEncoder();
    verify(config2).isUseLaxCookieEncoder();
    verify(connectionSemaphoreFactory).newConnectionSemaphore(isA(AsyncHttpClientConfig.class));
  }

  /**
   * Test {@link AsyncHttpClientHandler#channelInactive(ChannelHandlerContext)}.
   *
   * <ul>
   *   <li>Given {@link AsyncHttpClientState} {@link AsyncHttpClientState#isClosed()} return {@code
   *       true}.
   * </ul>
   *
   * <p>Method under test: {@link AsyncHttpClientHandler#channelInactive(ChannelHandlerContext)}
   */
  @Test
  @DisplayName(
      "Test channelInactive(ChannelHandlerContext); given AsyncHttpClientState isClosed() return 'true'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void AsyncHttpClientHandler.channelInactive(ChannelHandlerContext)"})
  void testChannelInactive_givenAsyncHttpClientStateIsClosedReturnTrue() throws Exception {
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

    AsyncHttpClientState clientState = mock(AsyncHttpClientState.class);
    when(clientState.isClosed()).thenReturn(true);

    NettyRequestSender requestSender =
        new NettyRequestSender(config2, mock(ChannelManager.class), mock(Timer.class), clientState);

    HttpHandler httpHandler = new HttpHandler(config, mock(ChannelManager.class), requestSender);

    // Act
    httpHandler.channelInactive(mock(ChannelHandlerContext.class));

    // Assert
    verify(config2, atLeast(1)).getConnectionSemaphoreFactory();
    verify(config).getIoExceptionFilters();
    verify(config).getMaxRedirects();
    verify(config).getResponseFilters();
    verify(config).isUseLaxCookieEncoder();
    verify(config2).isUseLaxCookieEncoder();
    verify(clientState).isClosed();
    verify(connectionSemaphoreFactory).newConnectionSemaphore(isA(AsyncHttpClientConfig.class));
  }

  /**
   * Test {@link AsyncHttpClientHandler#channelInactive(ChannelHandlerContext)}.
   *
   * <ul>
   *   <li>Given {@link EmbeddedChannel#EmbeddedChannel()}.
   *   <li>Then calls {@link ChannelManager#removeAll(Channel)}.
   * </ul>
   *
   * <p>Method under test: {@link AsyncHttpClientHandler#channelInactive(ChannelHandlerContext)}
   */
  @Test
  @DisplayName(
      "Test channelInactive(ChannelHandlerContext); given EmbeddedChannel(); then calls removeAll(Channel)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void AsyncHttpClientHandler.channelInactive(ChannelHandlerContext)"})
  void testChannelInactive_givenEmbeddedChannel_thenCallsRemoveAll() throws Exception {
    // Arrange
    AsyncHttpClientConfig config = mock(AsyncHttpClientConfig.class);
    when(config.isUseLaxCookieEncoder()).thenReturn(true);
    when(config.getMaxRedirects()).thenReturn(3);
    when(config.getIoExceptionFilters()).thenReturn(new ArrayList<>());
    when(config.getResponseFilters()).thenReturn(new ArrayList<>());

    ChannelManager channelManager = mock(ChannelManager.class);
    doNothing().when(channelManager).removeAll(Mockito.<Channel>any());

    ConnectionSemaphoreFactory connectionSemaphoreFactory = mock(ConnectionSemaphoreFactory.class);
    when(connectionSemaphoreFactory.newConnectionSemaphore(Mockito.<AsyncHttpClientConfig>any()))
        .thenReturn(new NoopConnectionSemaphore());

    AsyncHttpClientConfig config2 = mock(AsyncHttpClientConfig.class);
    when(config2.isUseLaxCookieEncoder()).thenReturn(true);
    when(config2.getConnectionSemaphoreFactory()).thenReturn(connectionSemaphoreFactory);

    AsyncHttpClientState clientState = mock(AsyncHttpClientState.class);
    when(clientState.isClosed()).thenReturn(false);

    NettyRequestSender requestSender =
        new NettyRequestSender(config2, mock(ChannelManager.class), mock(Timer.class), clientState);

    HttpHandler httpHandler = new HttpHandler(config, channelManager, requestSender);

    ChannelHandlerContext ctx = mock(ChannelHandlerContext.class);
    when(ctx.channel()).thenReturn(new EmbeddedChannel());

    // Act
    httpHandler.channelInactive(ctx);

    // Assert
    verify(ctx).channel();
    verify(config2, atLeast(1)).getConnectionSemaphoreFactory();
    verify(config).getIoExceptionFilters();
    verify(config).getMaxRedirects();
    verify(config).getResponseFilters();
    verify(config).isUseLaxCookieEncoder();
    verify(config2).isUseLaxCookieEncoder();
    verify(clientState).isClosed();
    verify(channelManager).removeAll(isA(Channel.class));
    verify(connectionSemaphoreFactory).newConnectionSemaphore(isA(AsyncHttpClientConfig.class));
  }

  /**
   * Test {@link AsyncHttpClientHandler#channelInactive(ChannelHandlerContext)}.
   *
   * <ul>
   *   <li>Then throw {@link PrematureChannelClosureException}.
   * </ul>
   *
   * <p>Method under test: {@link AsyncHttpClientHandler#channelInactive(ChannelHandlerContext)}
   */
  @Test
  @DisplayName(
      "Test channelInactive(ChannelHandlerContext); then throw PrematureChannelClosureException")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void AsyncHttpClientHandler.channelInactive(ChannelHandlerContext)"})
  void testChannelInactive_thenThrowPrematureChannelClosureException() throws Exception {
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

    AsyncHttpClientState clientState = mock(AsyncHttpClientState.class);
    when(clientState.isClosed()).thenReturn(false);

    NettyRequestSender requestSender =
        new NettyRequestSender(config2, mock(ChannelManager.class), mock(Timer.class), clientState);

    HttpHandler httpHandler = new HttpHandler(config, mock(ChannelManager.class), requestSender);

    ChannelHandlerContext ctx = mock(ChannelHandlerContext.class);
    when(ctx.channel()).thenThrow(new PrematureChannelClosureException());

    // Act and Assert
    assertThrows(PrematureChannelClosureException.class, () -> httpHandler.channelInactive(ctx));
    verify(ctx).channel();
    verify(config2, atLeast(1)).getConnectionSemaphoreFactory();
    verify(config).getIoExceptionFilters();
    verify(config).getMaxRedirects();
    verify(config).getResponseFilters();
    verify(config).isUseLaxCookieEncoder();
    verify(config2).isUseLaxCookieEncoder();
    verify(clientState).isClosed();
    verify(connectionSemaphoreFactory).newConnectionSemaphore(isA(AsyncHttpClientConfig.class));
  }

  /**
   * Test {@link AsyncHttpClientHandler#exceptionCaught(ChannelHandlerContext, Throwable)}.
   *
   * <ul>
   *   <li>Given {@link EmbeddedChannel#EmbeddedChannel()}.
   * </ul>
   *
   * <p>Method under test: {@link AsyncHttpClientHandler#exceptionCaught(ChannelHandlerContext,
   * Throwable)}
   */
  @Test
  @DisplayName("Test exceptionCaught(ChannelHandlerContext, Throwable); given EmbeddedChannel()")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void AsyncHttpClientHandler.exceptionCaught(ChannelHandlerContext, Throwable)"
  })
  void testExceptionCaught_givenEmbeddedChannel() {
    // Arrange
    AsyncHttpClientConfig config = mock(AsyncHttpClientConfig.class);
    when(config.isUseLaxCookieEncoder()).thenReturn(true);
    when(config.getMaxRedirects()).thenReturn(3);
    when(config.getIoExceptionFilters()).thenReturn(new ArrayList<>());
    when(config.getResponseFilters()).thenReturn(new ArrayList<>());

    ChannelManager channelManager = mock(ChannelManager.class);
    doNothing().when(channelManager).closeChannel(Mockito.<Channel>any());

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

    HttpHandler httpHandler = new HttpHandler(config, channelManager, requestSender);

    ChannelHandlerContext ctx = mock(ChannelHandlerContext.class);
    when(ctx.channel()).thenReturn(new EmbeddedChannel());

    // Act
    httpHandler.exceptionCaught(ctx, ChannelClosedException.INSTANCE);

    // Assert
    verify(ctx).channel();
    verify(config2, atLeast(1)).getConnectionSemaphoreFactory();
    verify(config).getIoExceptionFilters();
    verify(config).getMaxRedirects();
    verify(config).getResponseFilters();
    verify(config).isUseLaxCookieEncoder();
    verify(config2).isUseLaxCookieEncoder();
    verify(channelManager).closeChannel(isA(Channel.class));
    verify(connectionSemaphoreFactory).newConnectionSemaphore(isA(AsyncHttpClientConfig.class));
  }

  /**
   * Test {@link AsyncHttpClientHandler#exceptionCaught(ChannelHandlerContext, Throwable)}.
   *
   * <ul>
   *   <li>Given {@link EmbeddedChannel#EmbeddedChannel()} write {@code Msg}.
   * </ul>
   *
   * <p>Method under test: {@link AsyncHttpClientHandler#exceptionCaught(ChannelHandlerContext,
   * Throwable)}
   */
  @Test
  @DisplayName(
      "Test exceptionCaught(ChannelHandlerContext, Throwable); given EmbeddedChannel() write 'Msg'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void AsyncHttpClientHandler.exceptionCaught(ChannelHandlerContext, Throwable)"
  })
  void testExceptionCaught_givenEmbeddedChannelWriteMsg() {
    // Arrange
    AsyncHttpClientConfig config = mock(AsyncHttpClientConfig.class);
    when(config.isUseLaxCookieEncoder()).thenReturn(true);
    when(config.getMaxRedirects()).thenReturn(3);
    when(config.getIoExceptionFilters()).thenReturn(new ArrayList<>());
    when(config.getResponseFilters()).thenReturn(new ArrayList<>());

    ChannelManager channelManager = mock(ChannelManager.class);
    doNothing().when(channelManager).closeChannel(Mockito.<Channel>any());

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

    HttpHandler httpHandler = new HttpHandler(config, channelManager, requestSender);

    EmbeddedChannel embeddedChannel = new EmbeddedChannel();
    embeddedChannel.write("Msg");

    ChannelHandlerContext ctx = mock(ChannelHandlerContext.class);
    when(ctx.channel()).thenReturn(embeddedChannel);

    // Act
    httpHandler.exceptionCaught(ctx, ChannelClosedException.INSTANCE);

    // Assert
    verify(ctx).channel();
    verify(config2, atLeast(1)).getConnectionSemaphoreFactory();
    verify(config).getIoExceptionFilters();
    verify(config).getMaxRedirects();
    verify(config).getResponseFilters();
    verify(config).isUseLaxCookieEncoder();
    verify(config2).isUseLaxCookieEncoder();
    verify(channelManager).closeChannel(isA(Channel.class));
    verify(connectionSemaphoreFactory).newConnectionSemaphore(isA(AsyncHttpClientConfig.class));
  }

  /**
   * Test {@link AsyncHttpClientHandler#exceptionCaught(ChannelHandlerContext, Throwable)}.
   *
   * <ul>
   *   <li>Given {@link LocalChannel#LocalChannel()}.
   * </ul>
   *
   * <p>Method under test: {@link AsyncHttpClientHandler#exceptionCaught(ChannelHandlerContext,
   * Throwable)}
   */
  @Test
  @DisplayName("Test exceptionCaught(ChannelHandlerContext, Throwable); given LocalChannel()")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void AsyncHttpClientHandler.exceptionCaught(ChannelHandlerContext, Throwable)"
  })
  void testExceptionCaught_givenLocalChannel() {
    // Arrange
    AsyncHttpClientConfig config = mock(AsyncHttpClientConfig.class);
    when(config.isUseLaxCookieEncoder()).thenReturn(true);
    when(config.getMaxRedirects()).thenReturn(3);
    when(config.getIoExceptionFilters()).thenReturn(new ArrayList<>());
    when(config.getResponseFilters()).thenReturn(new ArrayList<>());

    ChannelManager channelManager = mock(ChannelManager.class);
    doNothing().when(channelManager).closeChannel(Mockito.<Channel>any());

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

    HttpHandler httpHandler = new HttpHandler(config, channelManager, requestSender);

    ChannelHandlerContext ctx = mock(ChannelHandlerContext.class);
    when(ctx.channel()).thenReturn(new LocalChannel());

    // Act
    httpHandler.exceptionCaught(ctx, ChannelClosedException.INSTANCE);

    // Assert
    verify(ctx).channel();
    verify(config2, atLeast(1)).getConnectionSemaphoreFactory();
    verify(config).getIoExceptionFilters();
    verify(config).getMaxRedirects();
    verify(config).getResponseFilters();
    verify(config).isUseLaxCookieEncoder();
    verify(config2).isUseLaxCookieEncoder();
    verify(channelManager).closeChannel(isA(Channel.class));
    verify(connectionSemaphoreFactory).newConnectionSemaphore(isA(AsyncHttpClientConfig.class));
  }

  /**
   * Test {@link AsyncHttpClientHandler#exceptionCaught(ChannelHandlerContext, Throwable)}.
   *
   * <ul>
   *   <li>Given {@code null}.
   *   <li>When {@link ChannelHandlerContext} {@link ChannelHandlerContext#channel()} return {@code
   *       null}.
   * </ul>
   *
   * <p>Method under test: {@link AsyncHttpClientHandler#exceptionCaught(ChannelHandlerContext,
   * Throwable)}
   */
  @Test
  @DisplayName(
      "Test exceptionCaught(ChannelHandlerContext, Throwable); given 'null'; when ChannelHandlerContext channel() return 'null'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void AsyncHttpClientHandler.exceptionCaught(ChannelHandlerContext, Throwable)"
  })
  void testExceptionCaught_givenNull_whenChannelHandlerContextChannelReturnNull() {
    // Arrange
    AsyncHttpClientConfig config = mock(AsyncHttpClientConfig.class);
    when(config.isUseLaxCookieEncoder()).thenReturn(true);
    when(config.getMaxRedirects()).thenReturn(3);
    when(config.getIoExceptionFilters()).thenReturn(new ArrayList<>());
    when(config.getResponseFilters()).thenReturn(new ArrayList<>());

    ChannelManager channelManager = mock(ChannelManager.class);
    doNothing().when(channelManager).closeChannel(Mockito.<Channel>any());

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

    HttpHandler httpHandler = new HttpHandler(config, channelManager, requestSender);

    ChannelHandlerContext ctx = mock(ChannelHandlerContext.class);
    when(ctx.channel()).thenReturn(null);

    // Act
    httpHandler.exceptionCaught(ctx, ChannelClosedException.INSTANCE);

    // Assert
    verify(ctx).channel();
    verify(config2, atLeast(1)).getConnectionSemaphoreFactory();
    verify(config).getIoExceptionFilters();
    verify(config).getMaxRedirects();
    verify(config).getResponseFilters();
    verify(config).isUseLaxCookieEncoder();
    verify(config2).isUseLaxCookieEncoder();
    verify(channelManager).closeChannel(isNull());
    verify(connectionSemaphoreFactory).newConnectionSemaphore(isA(AsyncHttpClientConfig.class));
  }

  /**
   * Test {@link AsyncHttpClientHandler#exceptionCaught(ChannelHandlerContext, Throwable)}.
   *
   * <ul>
   *   <li>When {@link Throwable#Throwable(String, Throwable)} with {@code Not all who wander are
   *       lost} and {@link ChannelClosedException#INSTANCE}.
   * </ul>
   *
   * <p>Method under test: {@link AsyncHttpClientHandler#exceptionCaught(ChannelHandlerContext,
   * Throwable)}
   */
  @Test
  @DisplayName(
      "Test exceptionCaught(ChannelHandlerContext, Throwable); when Throwable(String, Throwable) with 'Not all who wander are lost' and INSTANCE")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void AsyncHttpClientHandler.exceptionCaught(ChannelHandlerContext, Throwable)"
  })
  void testExceptionCaught_whenThrowableWithNotAllWhoWanderAreLostAndInstance() {
    // Arrange
    AsyncHttpClientConfig config = mock(AsyncHttpClientConfig.class);
    when(config.isUseLaxCookieEncoder()).thenReturn(true);
    when(config.getMaxRedirects()).thenReturn(3);
    when(config.getIoExceptionFilters()).thenReturn(new ArrayList<>());
    when(config.getResponseFilters()).thenReturn(new ArrayList<>());

    ChannelManager channelManager = mock(ChannelManager.class);
    doNothing().when(channelManager).closeChannel(Mockito.<Channel>any());

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

    HttpHandler httpHandler = new HttpHandler(config, channelManager, requestSender);

    ChannelHandlerContext ctx = mock(ChannelHandlerContext.class);
    when(ctx.channel()).thenReturn(new EmbeddedChannel());

    // Act
    httpHandler.exceptionCaught(
        ctx, new Throwable("Not all who wander are lost", ChannelClosedException.INSTANCE));

    // Assert
    verify(ctx).channel();
    verify(config2, atLeast(1)).getConnectionSemaphoreFactory();
    verify(config).getIoExceptionFilters();
    verify(config).getMaxRedirects();
    verify(config).getResponseFilters();
    verify(config).isUseLaxCookieEncoder();
    verify(config2).isUseLaxCookieEncoder();
    verify(channelManager).closeChannel(isA(Channel.class));
    verify(connectionSemaphoreFactory).newConnectionSemaphore(isA(AsyncHttpClientConfig.class));
  }

  /**
   * Test {@link AsyncHttpClientHandler#channelActive(ChannelHandlerContext)}.
   *
   * <p>Method under test: {@link AsyncHttpClientHandler#channelActive(ChannelHandlerContext)}
   */
  @Test
  @DisplayName("Test channelActive(ChannelHandlerContext)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void AsyncHttpClientHandler.channelActive(ChannelHandlerContext)"})
  void testChannelActive() {
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

    HttpHandler httpHandler = new HttpHandler(config, mock(ChannelManager.class), requestSender);

    ChannelHandlerContext ctx = mock(ChannelHandlerContext.class);
    when(ctx.read()).thenReturn(mock(ChannelHandlerContext.class));

    // Act
    httpHandler.channelActive(ctx);

    // Assert
    verify(ctx).read();
    verify(config2, atLeast(1)).getConnectionSemaphoreFactory();
    verify(config).getIoExceptionFilters();
    verify(config).getMaxRedirects();
    verify(config).getResponseFilters();
    verify(config).isUseLaxCookieEncoder();
    verify(config2).isUseLaxCookieEncoder();
    verify(connectionSemaphoreFactory).newConnectionSemaphore(isA(AsyncHttpClientConfig.class));
  }

  /**
   * Test {@link AsyncHttpClientHandler#channelReadComplete(ChannelHandlerContext)}.
   *
   * <p>Method under test: {@link AsyncHttpClientHandler#channelReadComplete(ChannelHandlerContext)}
   */
  @Test
  @DisplayName("Test channelReadComplete(ChannelHandlerContext)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void AsyncHttpClientHandler.channelReadComplete(ChannelHandlerContext)"})
  void testChannelReadComplete() {
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

    HttpHandler httpHandler = new HttpHandler(config, mock(ChannelManager.class), requestSender);

    ChannelHandlerContext ctx = mock(ChannelHandlerContext.class);
    when(ctx.read()).thenReturn(mock(ChannelHandlerContext.class));

    // Act
    httpHandler.channelReadComplete(ctx);

    // Assert
    verify(ctx).read();
    verify(config2, atLeast(1)).getConnectionSemaphoreFactory();
    verify(config).getIoExceptionFilters();
    verify(config).getMaxRedirects();
    verify(config).getResponseFilters();
    verify(config).isUseLaxCookieEncoder();
    verify(config2).isUseLaxCookieEncoder();
    verify(connectionSemaphoreFactory).newConnectionSemaphore(isA(AsyncHttpClientConfig.class));
  }
}
