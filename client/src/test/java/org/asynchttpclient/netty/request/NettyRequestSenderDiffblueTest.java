package org.asynchttpclient.netty.request;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import io.netty.channel.Channel;
import io.netty.channel.embedded.EmbeddedChannel;
import io.netty.util.Attribute;
import io.netty.util.AttributeKey;
import io.netty.util.Timer;
import io.netty.util.concurrent.Future;
import java.nio.file.Paths;
import org.asynchttpclient.AsyncHandler;
import org.asynchttpclient.AsyncHttpClientConfig;
import org.asynchttpclient.AsyncHttpClientState;
import org.asynchttpclient.Request;
import org.asynchttpclient.exception.ChannelClosedException;
import org.asynchttpclient.netty.NettyResponseFuture;
import org.asynchttpclient.netty.channel.ChannelManager;
import org.asynchttpclient.netty.channel.ConnectionSemaphoreFactory;
import org.asynchttpclient.netty.channel.NoopConnectionSemaphore;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

class NettyRequestSenderDiffblueTest {
  /**
   * Test {@link NettyRequestSender#sendRequest(Request, AsyncHandler, NettyResponseFuture)}.
   *
   * <ul>
   *   <li>Given {@link AsyncHttpClientState} {@link AsyncHttpClientState#isClosed()} return {@code
   *       true}.
   * </ul>
   *
   * <p>Method under test: {@link NettyRequestSender#sendRequest(Request, AsyncHandler,
   * NettyResponseFuture)}
   */
  @Test
  @DisplayName(
      "Test sendRequest(Request, AsyncHandler, NettyResponseFuture); given AsyncHttpClientState isClosed() return 'true'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "org.asynchttpclient.ListenableFuture NettyRequestSender.sendRequest(Request, AsyncHandler, NettyResponseFuture)"
  })
  void testSendRequest_givenAsyncHttpClientStateIsClosedReturnTrue() {
    // Arrange
    ConnectionSemaphoreFactory connectionSemaphoreFactory = mock(ConnectionSemaphoreFactory.class);
    when(connectionSemaphoreFactory.newConnectionSemaphore(Mockito.<AsyncHttpClientConfig>any()))
        .thenReturn(new NoopConnectionSemaphore());

    AsyncHttpClientConfig config = mock(AsyncHttpClientConfig.class);
    when(config.isUseLaxCookieEncoder()).thenReturn(true);
    when(config.getConnectionSemaphoreFactory()).thenReturn(connectionSemaphoreFactory);

    AsyncHttpClientState clientState = mock(AsyncHttpClientState.class);
    when(clientState.isClosed()).thenReturn(true);

    NettyRequestSender nettyRequestSender =
        new NettyRequestSender(config, mock(ChannelManager.class), mock(Timer.class), clientState);
    Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");

    // Act and Assert
    assertThrows(
        IllegalStateException.class,
        () -> nettyRequestSender.sendRequest(mock(Request.class), mock(AsyncHandler.class), null));
    verify(config, atLeast(1)).getConnectionSemaphoreFactory();
    verify(config).isUseLaxCookieEncoder();
    verify(clientState).isClosed();
    verify(connectionSemaphoreFactory).newConnectionSemaphore(isA(AsyncHttpClientConfig.class));
  }

  /**
   * Test {@link NettyRequestSender#sendRequest(Request, AsyncHandler, NettyResponseFuture)}.
   *
   * <ul>
   *   <li>Given {@link AsyncHttpClientState} {@link AsyncHttpClientState#isClosed()} throw {@link
   *       IllegalStateException#IllegalStateException()}.
   * </ul>
   *
   * <p>Method under test: {@link NettyRequestSender#sendRequest(Request, AsyncHandler,
   * NettyResponseFuture)}
   */
  @Test
  @DisplayName(
      "Test sendRequest(Request, AsyncHandler, NettyResponseFuture); given AsyncHttpClientState isClosed() throw IllegalStateException()")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "org.asynchttpclient.ListenableFuture NettyRequestSender.sendRequest(Request, AsyncHandler, NettyResponseFuture)"
  })
  void testSendRequest_givenAsyncHttpClientStateIsClosedThrowIllegalStateException() {
    // Arrange
    ConnectionSemaphoreFactory connectionSemaphoreFactory = mock(ConnectionSemaphoreFactory.class);
    when(connectionSemaphoreFactory.newConnectionSemaphore(Mockito.<AsyncHttpClientConfig>any()))
        .thenReturn(new NoopConnectionSemaphore());

    AsyncHttpClientConfig config = mock(AsyncHttpClientConfig.class);
    when(config.isUseLaxCookieEncoder()).thenReturn(true);
    when(config.getConnectionSemaphoreFactory()).thenReturn(connectionSemaphoreFactory);

    AsyncHttpClientState clientState = mock(AsyncHttpClientState.class);
    when(clientState.isClosed()).thenThrow(new IllegalStateException());

    NettyRequestSender nettyRequestSender =
        new NettyRequestSender(config, mock(ChannelManager.class), mock(Timer.class), clientState);
    Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");

    // Act and Assert
    assertThrows(
        IllegalStateException.class,
        () -> nettyRequestSender.sendRequest(mock(Request.class), mock(AsyncHandler.class), null));
    verify(config, atLeast(1)).getConnectionSemaphoreFactory();
    verify(config).isUseLaxCookieEncoder();
    verify(clientState).isClosed();
    verify(connectionSemaphoreFactory).newConnectionSemaphore(isA(AsyncHttpClientConfig.class));
  }

  /**
   * Test {@link NettyRequestSender#sendRequest(Request, AsyncHandler, NettyResponseFuture)}.
   *
   * <ul>
   *   <li>When {@link Request} {@link Request#getUri()} throw {@link
   *       IllegalStateException#IllegalStateException()}.
   *   <li>Then calls {@link Request#getUri()}.
   * </ul>
   *
   * <p>Method under test: {@link NettyRequestSender#sendRequest(Request, AsyncHandler,
   * NettyResponseFuture)}
   */
  @Test
  @DisplayName(
      "Test sendRequest(Request, AsyncHandler, NettyResponseFuture); when Request getUri() throw IllegalStateException(); then calls getUri()")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "org.asynchttpclient.ListenableFuture NettyRequestSender.sendRequest(Request, AsyncHandler, NettyResponseFuture)"
  })
  void testSendRequest_whenRequestGetUriThrowIllegalStateException_thenCallsGetUri() {
    // Arrange
    ConnectionSemaphoreFactory connectionSemaphoreFactory = mock(ConnectionSemaphoreFactory.class);
    when(connectionSemaphoreFactory.newConnectionSemaphore(Mockito.<AsyncHttpClientConfig>any()))
        .thenReturn(new NoopConnectionSemaphore());

    AsyncHttpClientConfig config = mock(AsyncHttpClientConfig.class);
    when(config.isUseLaxCookieEncoder()).thenReturn(true);
    when(config.getConnectionSemaphoreFactory()).thenReturn(connectionSemaphoreFactory);

    AsyncHttpClientState clientState = mock(AsyncHttpClientState.class);
    when(clientState.isClosed()).thenReturn(false);

    NettyRequestSender nettyRequestSender =
        new NettyRequestSender(config, mock(ChannelManager.class), mock(Timer.class), clientState);

    Request request = mock(Request.class);
    when(request.getUri()).thenThrow(new IllegalStateException());
    Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");

    // Act and Assert
    assertThrows(
        IllegalStateException.class,
        () -> nettyRequestSender.sendRequest(request, mock(AsyncHandler.class), null));
    verify(config, atLeast(1)).getConnectionSemaphoreFactory();
    verify(config).isUseLaxCookieEncoder();
    verify(clientState).isClosed();
    verify(request).getUri();
    verify(connectionSemaphoreFactory).newConnectionSemaphore(isA(AsyncHttpClientConfig.class));
  }

  /**
   * Test {@link NettyRequestSender#abort(Channel, NettyResponseFuture, Throwable)}.
   *
   * <ul>
   *   <li>Then throw {@link IllegalStateException}.
   * </ul>
   *
   * <p>Method under test: {@link NettyRequestSender#abort(Channel, NettyResponseFuture, Throwable)}
   */
  @Test
  @DisplayName(
      "Test abort(Channel, NettyResponseFuture, Throwable); then throw IllegalStateException")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void NettyRequestSender.abort(Channel, NettyResponseFuture, Throwable)"})
  void testAbort_thenThrowIllegalStateException() {
    // Arrange
    ConnectionSemaphoreFactory connectionSemaphoreFactory = mock(ConnectionSemaphoreFactory.class);
    when(connectionSemaphoreFactory.newConnectionSemaphore(Mockito.<AsyncHttpClientConfig>any()))
        .thenReturn(new NoopConnectionSemaphore());

    AsyncHttpClientConfig config = mock(AsyncHttpClientConfig.class);
    when(config.isUseLaxCookieEncoder()).thenReturn(true);
    when(config.getConnectionSemaphoreFactory()).thenReturn(connectionSemaphoreFactory);

    ChannelManager channelManager = mock(ChannelManager.class);
    doThrow(new IllegalStateException()).when(channelManager).closeChannel(Mockito.<Channel>any());

    NettyRequestSender nettyRequestSender =
        new NettyRequestSender(
            config, channelManager, mock(Timer.class), mock(AsyncHttpClientState.class));
    Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");

    // Act and Assert
    assertThrows(
        IllegalStateException.class,
        () ->
            nettyRequestSender.abort(new EmbeddedChannel(), null, ChannelClosedException.INSTANCE));
    verify(config, atLeast(1)).getConnectionSemaphoreFactory();
    verify(config).isUseLaxCookieEncoder();
    verify(channelManager).closeChannel(isA(Channel.class));
    verify(connectionSemaphoreFactory).newConnectionSemaphore(isA(AsyncHttpClientConfig.class));
  }

  /**
   * Test {@link NettyRequestSender#handleUnexpectedClosedChannel(Channel, NettyResponseFuture)}.
   *
   * <ul>
   *   <li>Then calls {@link AsyncHttpClientConfig#getConnectionSemaphoreFactory()}.
   * </ul>
   *
   * <p>Method under test: {@link NettyRequestSender#handleUnexpectedClosedChannel(Channel,
   * NettyResponseFuture)}
   */
  @Test
  @DisplayName(
      "Test handleUnexpectedClosedChannel(Channel, NettyResponseFuture); then calls getConnectionSemaphoreFactory()")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void NettyRequestSender.handleUnexpectedClosedChannel(Channel, NettyResponseFuture)"
  })
  void testHandleUnexpectedClosedChannel_thenCallsGetConnectionSemaphoreFactory() {
    // Arrange
    ConnectionSemaphoreFactory connectionSemaphoreFactory = mock(ConnectionSemaphoreFactory.class);
    when(connectionSemaphoreFactory.newConnectionSemaphore(Mockito.<AsyncHttpClientConfig>any()))
        .thenReturn(new NoopConnectionSemaphore());

    AsyncHttpClientConfig config = mock(AsyncHttpClientConfig.class);
    when(config.isUseLaxCookieEncoder()).thenReturn(true);
    when(config.getConnectionSemaphoreFactory()).thenReturn(connectionSemaphoreFactory);
    NettyRequestSender nettyRequestSender =
        new NettyRequestSender(
            config,
            mock(ChannelManager.class),
            mock(Timer.class),
            mock(AsyncHttpClientState.class));
    Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");

    // Act
    nettyRequestSender.handleUnexpectedClosedChannel(new EmbeddedChannel(), null);

    // Assert
    verify(config, atLeast(1)).getConnectionSemaphoreFactory();
    verify(config).isUseLaxCookieEncoder();
    verify(connectionSemaphoreFactory).newConnectionSemaphore(isA(AsyncHttpClientConfig.class));
  }

  /**
   * Test {@link NettyRequestSender#handleUnexpectedClosedChannel(Channel, NettyResponseFuture)}.
   *
   * <ul>
   *   <li>Then calls {@link AsyncHttpClientConfig#getConnectionSemaphoreFactory()}.
   * </ul>
   *
   * <p>Method under test: {@link NettyRequestSender#handleUnexpectedClosedChannel(Channel,
   * NettyResponseFuture)}
   */
  @Test
  @DisplayName(
      "Test handleUnexpectedClosedChannel(Channel, NettyResponseFuture); then calls getConnectionSemaphoreFactory()")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void NettyRequestSender.handleUnexpectedClosedChannel(Channel, NettyResponseFuture)"
  })
  void testHandleUnexpectedClosedChannel_thenCallsGetConnectionSemaphoreFactory2() {
    // Arrange
    ConnectionSemaphoreFactory connectionSemaphoreFactory = mock(ConnectionSemaphoreFactory.class);
    when(connectionSemaphoreFactory.newConnectionSemaphore(Mockito.<AsyncHttpClientConfig>any()))
        .thenReturn(new NoopConnectionSemaphore());

    AsyncHttpClientConfig config = mock(AsyncHttpClientConfig.class);
    when(config.isUseLaxCookieEncoder()).thenReturn(true);
    when(config.getConnectionSemaphoreFactory()).thenReturn(connectionSemaphoreFactory);
    NettyRequestSender nettyRequestSender =
        new NettyRequestSender(
            config,
            mock(ChannelManager.class),
            mock(Timer.class),
            mock(AsyncHttpClientState.class));
    Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");

    // Act
    nettyRequestSender.handleUnexpectedClosedChannel(null, null);

    // Assert
    verify(config, atLeast(1)).getConnectionSemaphoreFactory();
    verify(config).isUseLaxCookieEncoder();
    verify(connectionSemaphoreFactory).newConnectionSemaphore(isA(AsyncHttpClientConfig.class));
  }

  /**
   * Test {@link NettyRequestSender#retry(NettyResponseFuture)}.
   *
   * <ul>
   *   <li>Given {@link AsyncHttpClientState} {@link AsyncHttpClientState#isClosed()} return {@code
   *       true}.
   *   <li>When {@code null}.
   *   <li>Then return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link NettyRequestSender#retry(NettyResponseFuture)}
   */
  @Test
  @DisplayName(
      "Test retry(NettyResponseFuture); given AsyncHttpClientState isClosed() return 'true'; when 'null'; then return 'false'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean NettyRequestSender.retry(NettyResponseFuture)"})
  void testRetry_givenAsyncHttpClientStateIsClosedReturnTrue_whenNull_thenReturnFalse() {
    // Arrange
    ConnectionSemaphoreFactory connectionSemaphoreFactory = mock(ConnectionSemaphoreFactory.class);
    when(connectionSemaphoreFactory.newConnectionSemaphore(Mockito.<AsyncHttpClientConfig>any()))
        .thenReturn(new NoopConnectionSemaphore());

    AsyncHttpClientConfig config = mock(AsyncHttpClientConfig.class);
    when(config.isUseLaxCookieEncoder()).thenReturn(true);
    when(config.getConnectionSemaphoreFactory()).thenReturn(connectionSemaphoreFactory);

    AsyncHttpClientState clientState = mock(AsyncHttpClientState.class);
    when(clientState.isClosed()).thenReturn(true);

    NettyRequestSender nettyRequestSender =
        new NettyRequestSender(config, mock(ChannelManager.class), mock(Timer.class), clientState);
    Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");

    // Act
    boolean actualRetryResult = nettyRequestSender.retry(null);

    // Assert
    verify(config, atLeast(1)).getConnectionSemaphoreFactory();
    verify(config).isUseLaxCookieEncoder();
    verify(clientState).isClosed();
    verify(connectionSemaphoreFactory).newConnectionSemaphore(isA(AsyncHttpClientConfig.class));
    assertFalse(actualRetryResult);
  }

  /**
   * Test {@link NettyRequestSender#retry(NettyResponseFuture)}.
   *
   * <ul>
   *   <li>Then throw {@link IllegalStateException}.
   * </ul>
   *
   * <p>Method under test: {@link NettyRequestSender#retry(NettyResponseFuture)}
   */
  @Test
  @DisplayName("Test retry(NettyResponseFuture); then throw IllegalStateException")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean NettyRequestSender.retry(NettyResponseFuture)"})
  void testRetry_thenThrowIllegalStateException() {
    // Arrange
    ConnectionSemaphoreFactory connectionSemaphoreFactory = mock(ConnectionSemaphoreFactory.class);
    when(connectionSemaphoreFactory.newConnectionSemaphore(Mockito.<AsyncHttpClientConfig>any()))
        .thenReturn(new NoopConnectionSemaphore());

    AsyncHttpClientConfig config = mock(AsyncHttpClientConfig.class);
    when(config.isUseLaxCookieEncoder()).thenReturn(true);
    when(config.getConnectionSemaphoreFactory()).thenReturn(connectionSemaphoreFactory);

    AsyncHttpClientState clientState = mock(AsyncHttpClientState.class);
    when(clientState.isClosed()).thenThrow(new IllegalStateException());

    NettyRequestSender nettyRequestSender =
        new NettyRequestSender(config, mock(ChannelManager.class), mock(Timer.class), clientState);
    Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");

    // Act and Assert
    assertThrows(IllegalStateException.class, () -> nettyRequestSender.retry(null));
    verify(config, atLeast(1)).getConnectionSemaphoreFactory();
    verify(config).isUseLaxCookieEncoder();
    verify(clientState).isClosed();
    verify(connectionSemaphoreFactory).newConnectionSemaphore(isA(AsyncHttpClientConfig.class));
  }

  /**
   * Test {@link NettyRequestSender#isClosed()}.
   *
   * <ul>
   *   <li>Given {@link AsyncHttpClientState} {@link AsyncHttpClientState#isClosed()} return {@code
   *       false}.
   *   <li>Then return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link NettyRequestSender#isClosed()}
   */
  @Test
  @DisplayName(
      "Test isClosed(); given AsyncHttpClientState isClosed() return 'false'; then return 'false'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean NettyRequestSender.isClosed()"})
  void testIsClosed_givenAsyncHttpClientStateIsClosedReturnFalse_thenReturnFalse() {
    // Arrange
    ConnectionSemaphoreFactory connectionSemaphoreFactory = mock(ConnectionSemaphoreFactory.class);
    when(connectionSemaphoreFactory.newConnectionSemaphore(Mockito.<AsyncHttpClientConfig>any()))
        .thenReturn(new NoopConnectionSemaphore());

    AsyncHttpClientConfig config = mock(AsyncHttpClientConfig.class);
    when(config.isUseLaxCookieEncoder()).thenReturn(true);
    when(config.getConnectionSemaphoreFactory()).thenReturn(connectionSemaphoreFactory);

    AsyncHttpClientState clientState = mock(AsyncHttpClientState.class);
    when(clientState.isClosed()).thenReturn(false);

    NettyRequestSender nettyRequestSender =
        new NettyRequestSender(config, mock(ChannelManager.class), mock(Timer.class), clientState);

    // Act
    boolean actualIsClosedResult = nettyRequestSender.isClosed();

    // Assert
    verify(config, atLeast(1)).getConnectionSemaphoreFactory();
    verify(config).isUseLaxCookieEncoder();
    verify(clientState).isClosed();
    verify(connectionSemaphoreFactory).newConnectionSemaphore(isA(AsyncHttpClientConfig.class));
    assertFalse(actualIsClosedResult);
  }

  /**
   * Test {@link NettyRequestSender#isClosed()}.
   *
   * <ul>
   *   <li>Given {@link AsyncHttpClientState} {@link AsyncHttpClientState#isClosed()} return {@code
   *       true}.
   *   <li>Then return {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link NettyRequestSender#isClosed()}
   */
  @Test
  @DisplayName(
      "Test isClosed(); given AsyncHttpClientState isClosed() return 'true'; then return 'true'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean NettyRequestSender.isClosed()"})
  void testIsClosed_givenAsyncHttpClientStateIsClosedReturnTrue_thenReturnTrue() {
    // Arrange
    ConnectionSemaphoreFactory connectionSemaphoreFactory = mock(ConnectionSemaphoreFactory.class);
    when(connectionSemaphoreFactory.newConnectionSemaphore(Mockito.<AsyncHttpClientConfig>any()))
        .thenReturn(new NoopConnectionSemaphore());

    AsyncHttpClientConfig config = mock(AsyncHttpClientConfig.class);
    when(config.isUseLaxCookieEncoder()).thenReturn(true);
    when(config.getConnectionSemaphoreFactory()).thenReturn(connectionSemaphoreFactory);

    AsyncHttpClientState clientState = mock(AsyncHttpClientState.class);
    when(clientState.isClosed()).thenReturn(true);

    NettyRequestSender nettyRequestSender =
        new NettyRequestSender(config, mock(ChannelManager.class), mock(Timer.class), clientState);

    // Act
    boolean actualIsClosedResult = nettyRequestSender.isClosed();

    // Assert
    verify(config, atLeast(1)).getConnectionSemaphoreFactory();
    verify(config).isUseLaxCookieEncoder();
    verify(clientState).isClosed();
    verify(connectionSemaphoreFactory).newConnectionSemaphore(isA(AsyncHttpClientConfig.class));
    assertTrue(actualIsClosedResult);
  }

  /**
   * Test {@link NettyRequestSender#drainChannelAndExecuteNextRequest(Channel, NettyResponseFuture,
   * Request, Future)} with {@code channel}, {@code future}, {@code nextRequest}, {@code
   * whenHandshaked}.
   *
   * <p>Method under test: {@link NettyRequestSender#drainChannelAndExecuteNextRequest(Channel,
   * NettyResponseFuture, Request, Future)}
   */
  @Test
  @DisplayName(
      "Test drainChannelAndExecuteNextRequest(Channel, NettyResponseFuture, Request, Future) with 'channel', 'future', 'nextRequest', 'whenHandshaked'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void NettyRequestSender.drainChannelAndExecuteNextRequest(Channel, NettyResponseFuture, Request, Future)"
  })
  void testDrainChannelAndExecuteNextRequestWithChannelFutureNextRequestWhenHandshaked() {
    // Arrange
    ConnectionSemaphoreFactory connectionSemaphoreFactory = mock(ConnectionSemaphoreFactory.class);
    when(connectionSemaphoreFactory.newConnectionSemaphore(Mockito.<AsyncHttpClientConfig>any()))
        .thenReturn(new NoopConnectionSemaphore());

    AsyncHttpClientConfig config = mock(AsyncHttpClientConfig.class);
    when(config.isUseLaxCookieEncoder()).thenReturn(true);
    when(config.getConnectionSemaphoreFactory()).thenReturn(connectionSemaphoreFactory);
    NettyRequestSender nettyRequestSender =
        new NettyRequestSender(
            config,
            mock(ChannelManager.class),
            mock(Timer.class),
            mock(AsyncHttpClientState.class));

    Attribute<Object> attribute = mock(Attribute.class);
    doNothing().when(attribute).set(Mockito.<Object>any());

    Channel channel = mock(Channel.class);
    when(channel.attr(Mockito.<AttributeKey<Object>>any())).thenReturn(attribute);
    Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");

    // Act
    nettyRequestSender.drainChannelAndExecuteNextRequest(
        channel, null, mock(Request.class), mock(Future.class));

    // Assert
    verify(attribute).set(isA(Object.class));
    verify(channel).attr(isA(AttributeKey.class));
    verify(config, atLeast(1)).getConnectionSemaphoreFactory();
    verify(config).isUseLaxCookieEncoder();
    verify(connectionSemaphoreFactory).newConnectionSemaphore(isA(AsyncHttpClientConfig.class));
  }

  /**
   * Test {@link NettyRequestSender#drainChannelAndExecuteNextRequest(Channel, NettyResponseFuture,
   * Request)} with {@code channel}, {@code future}, {@code nextRequest}.
   *
   * <ul>
   *   <li>Then calls {@link Attribute#set(Object)}.
   * </ul>
   *
   * <p>Method under test: {@link NettyRequestSender#drainChannelAndExecuteNextRequest(Channel,
   * NettyResponseFuture, Request)}
   */
  @Test
  @DisplayName(
      "Test drainChannelAndExecuteNextRequest(Channel, NettyResponseFuture, Request) with 'channel', 'future', 'nextRequest'; then calls set(Object)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void NettyRequestSender.drainChannelAndExecuteNextRequest(Channel, NettyResponseFuture, Request)"
  })
  void testDrainChannelAndExecuteNextRequestWithChannelFutureNextRequest_thenCallsSet() {
    // Arrange
    ConnectionSemaphoreFactory connectionSemaphoreFactory = mock(ConnectionSemaphoreFactory.class);
    when(connectionSemaphoreFactory.newConnectionSemaphore(Mockito.<AsyncHttpClientConfig>any()))
        .thenReturn(new NoopConnectionSemaphore());

    AsyncHttpClientConfig config = mock(AsyncHttpClientConfig.class);
    when(config.isUseLaxCookieEncoder()).thenReturn(true);
    when(config.getConnectionSemaphoreFactory()).thenReturn(connectionSemaphoreFactory);
    NettyRequestSender nettyRequestSender =
        new NettyRequestSender(
            config,
            mock(ChannelManager.class),
            mock(Timer.class),
            mock(AsyncHttpClientState.class));

    Attribute<Object> attribute = mock(Attribute.class);
    doNothing().when(attribute).set(Mockito.<Object>any());

    Channel channel = mock(Channel.class);
    when(channel.attr(Mockito.<AttributeKey<Object>>any())).thenReturn(attribute);
    Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");

    // Act
    nettyRequestSender.drainChannelAndExecuteNextRequest(channel, null, mock(Request.class));

    // Assert
    verify(attribute).set(isA(Object.class));
    verify(channel).attr(isA(AttributeKey.class));
    verify(config, atLeast(1)).getConnectionSemaphoreFactory();
    verify(config).isUseLaxCookieEncoder();
    verify(connectionSemaphoreFactory).newConnectionSemaphore(isA(AsyncHttpClientConfig.class));
  }
}
