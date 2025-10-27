package org.asynchttpclient;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import io.netty.buffer.AdaptiveByteBufAllocator;
import io.netty.channel.Channel;
import io.netty.channel.ChannelOption;
import io.netty.channel.DefaultEventLoop;
import io.netty.handler.ssl.JdkSslClientContext;
import io.netty.util.HashedWheelTimer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ThreadFactory;
import java.util.function.Consumer;
import javax.net.ssl.SSLException;
import org.asynchttpclient.channel.KeepAliveStrategy;
import org.asynchttpclient.channel.NoopChannelPool;
import org.asynchttpclient.cookie.ThreadSafeCookieStore;
import org.asynchttpclient.filter.IOExceptionFilter;
import org.asynchttpclient.filter.RequestFilter;
import org.asynchttpclient.filter.ResponseFilter;
import org.asynchttpclient.netty.channel.ConnectionSemaphoreFactory;
import org.asynchttpclient.proxy.ProxyServer;
import org.asynchttpclient.proxy.ProxyServerSelector;
import org.asynchttpclient.proxy.ProxyType;
import org.junit.jupiter.api.Test;

class DefaultAsyncHttpClientConfigDiffblueTest {
  /**
   * Method under test:
   * {@link DefaultAsyncHttpClientConfig.Builder#addChannelOption(ChannelOption, Object)}
   */
  @Test
  void testBuilderAddChannelOption() {
    // Arrange
    DefaultAsyncHttpClientConfig.Builder configResult = Dsl.config();

    // Act
    DefaultAsyncHttpClientConfig.Builder actualAddChannelOptionResult = configResult
        .addChannelOption(mock(ChannelOption.class), "Value");

    // Assert
    assertEquals(1, configResult.build().getChannelOptions().size());
    assertSame(configResult, actualAddChannelOptionResult);
  }

  /**
   * Method under test:
   * {@link DefaultAsyncHttpClientConfig.Builder#addIOExceptionFilter(IOExceptionFilter)}
   */
  @Test
  void testBuilderAddIOExceptionFilter() {
    // Arrange
    DefaultAsyncHttpClientConfig.Builder configResult = Dsl.config();
    IOExceptionFilter ioExceptionFilter = mock(IOExceptionFilter.class);

    // Act
    DefaultAsyncHttpClientConfig.Builder actualAddIOExceptionFilterResult = configResult
        .addIOExceptionFilter(ioExceptionFilter);

    // Assert
    List<IOExceptionFilter> ioExceptionFilters = configResult.build().getIoExceptionFilters();
    assertEquals(1, ioExceptionFilters.size());
    assertSame(configResult, actualAddIOExceptionFilterResult);
    assertSame(ioExceptionFilter, ioExceptionFilters.get(0));
  }

  /**
   * Method under test:
   * {@link DefaultAsyncHttpClientConfig.Builder#addRequestFilter(RequestFilter)}
   */
  @Test
  void testBuilderAddRequestFilter() {
    // Arrange
    DefaultAsyncHttpClientConfig.Builder configResult = Dsl.config();
    RequestFilter requestFilter = mock(RequestFilter.class);

    // Act
    DefaultAsyncHttpClientConfig.Builder actualAddRequestFilterResult = configResult.addRequestFilter(requestFilter);

    // Assert
    List<RequestFilter> requestFilters = configResult.build().getRequestFilters();
    assertEquals(1, requestFilters.size());
    assertSame(configResult, actualAddRequestFilterResult);
    assertSame(requestFilter, requestFilters.get(0));
  }

  /**
   * Method under test:
   * {@link DefaultAsyncHttpClientConfig.Builder#addResponseFilter(ResponseFilter)}
   */
  @Test
  void testBuilderAddResponseFilter() {
    // Arrange
    DefaultAsyncHttpClientConfig.Builder configResult = Dsl.config();
    ResponseFilter responseFilter = mock(ResponseFilter.class);

    // Act
    DefaultAsyncHttpClientConfig.Builder actualAddResponseFilterResult = configResult.addResponseFilter(responseFilter);

    // Assert
    List<ResponseFilter> responseFilters = configResult.build().getResponseFilters();
    assertEquals(1, responseFilters.size());
    assertSame(configResult, actualAddResponseFilterResult);
    assertSame(responseFilter, responseFilters.get(0));
  }

  /**
   * Method under test:
   * {@link DefaultAsyncHttpClientConfig.Builder#removeIOExceptionFilter(IOExceptionFilter)}
   */
  @Test
  void testBuilderRemoveIOExceptionFilter() {
    // Arrange
    DefaultAsyncHttpClientConfig.Builder configResult = Dsl.config();

    // Act and Assert
    assertSame(configResult, configResult.removeIOExceptionFilter(mock(IOExceptionFilter.class)));
  }

  /**
   * Method under test:
   * {@link DefaultAsyncHttpClientConfig.Builder#removeRequestFilter(RequestFilter)}
   */
  @Test
  void testBuilderRemoveRequestFilter() {
    // Arrange
    DefaultAsyncHttpClientConfig.Builder configResult = Dsl.config();

    // Act and Assert
    assertSame(configResult, configResult.removeRequestFilter(mock(RequestFilter.class)));
  }

  /**
   * Method under test:
   * {@link DefaultAsyncHttpClientConfig.Builder#removeResponseFilter(ResponseFilter)}
   */
  @Test
  void testBuilderRemoveResponseFilter() {
    // Arrange
    DefaultAsyncHttpClientConfig.Builder configResult = Dsl.config();

    // Act and Assert
    assertSame(configResult, configResult.removeResponseFilter(mock(ResponseFilter.class)));
  }

  /**
   * Method under test:
   * {@link DefaultAsyncHttpClientConfig.Builder#setProxyServer(ProxyServer.Builder)}
   */
  @Test
  void testBuilderSetProxyServer() {
    // Arrange
    DefaultAsyncHttpClientConfig.Builder configResult = Dsl.config();

    // Act and Assert
    assertSame(configResult, configResult.setProxyServer(Dsl.proxyServer("https://example.org/example", 8080)));
  }

  /**
   * Method under test:
   * {@link DefaultAsyncHttpClientConfig.Builder#setProxyServer(ProxyServer.Builder)}
   */
  @Test
  void testBuilderSetProxyServer2() {
    // Arrange
    DefaultAsyncHttpClientConfig.Builder configResult = Dsl.config();
    ProxyServer.Builder proxyServerBuilder = Dsl.proxyServer("https://example.org/example", 8080);
    proxyServerBuilder.setNonProxyHost(null);
    proxyServerBuilder.setProxyType(null);

    // Act and Assert
    assertSame(configResult, configResult.setProxyServer(proxyServerBuilder));
  }

  /**
   * Method under test:
   * {@link DefaultAsyncHttpClientConfig.Builder#setProxyServer(ProxyServer.Builder)}
   */
  @Test
  void testBuilderSetProxyServer3() {
    // Arrange
    DefaultAsyncHttpClientConfig.Builder configResult = Dsl.config();
    ProxyServer.Builder proxyServerBuilder = Dsl.proxyServer("https://example.org/example", 8080);
    proxyServerBuilder.setNonProxyHost(null);
    proxyServerBuilder.setProxyType(ProxyType.HTTP);

    // Act and Assert
    assertSame(configResult, configResult.setProxyServer(proxyServerBuilder));
  }

  /**
   * Method under test:
   * {@link DefaultAsyncHttpClientConfig.Builder#setProxyServer(ProxyServer.Builder)}
   */
  @Test
  void testBuilderSetProxyServer4() throws SSLException {
    // Arrange
    AsyncHttpClientConfig config = mock(AsyncHttpClientConfig.class);
    when(config.isAggregateWebSocketFrameFragments()).thenReturn(true);
    when(config.isCompressionEnforced()).thenReturn(true);
    when(config.isDisableHttpsEndpointIdentificationAlgorithm()).thenReturn(true);
    when(config.isDisableUrlEncodingForBoundRequests()).thenReturn(true);
    when(config.isDisableZeroCopy()).thenReturn(true);
    when(config.isEnableAutomaticDecompression()).thenReturn(true);
    when(config.isEnableWebSocketCompression()).thenReturn(true);
    when(config.isFilterInsecureCipherSuites()).thenReturn(true);
    when(config.isFollowRedirect()).thenReturn(true);
    when(config.isKeepAlive()).thenReturn(true);
    when(config.isKeepEncodingHeader()).thenReturn(true);
    when(config.isSoKeepAlive()).thenReturn(true);
    when(config.isSoReuseAddress()).thenReturn(true);
    when(config.isStrict302Handling()).thenReturn(true);
    when(config.isTcpNoDelay()).thenReturn(true);
    when(config.isUseInsecureTrustManager()).thenReturn(true);
    when(config.isUseLaxCookieEncoder()).thenReturn(true);
    when(config.isUseNativeTransport()).thenReturn(true);
    when(config.isUseOnlyEpollNativeTransport()).thenReturn(true);
    when(config.isUseOpenSsl()).thenReturn(true);
    when(config.isValidateResponseHeaders()).thenReturn(true);
    when(config.expiredCookieEvictionDelay()).thenReturn(1);
    when(config.getAcquireFreeChannelTimeout()).thenReturn(10);
    when(config.getChunkedFileChunkSize()).thenReturn(3);
    when(config.getHandshakeTimeout()).thenReturn(10);
    when(config.getHashedWheelTimerSize()).thenReturn(19088743);
    when(config.getHttpClientCodecInitialBufferSize()).thenReturn(3);
    when(config.getHttpClientCodecMaxChunkSize()).thenReturn(3);
    when(config.getHttpClientCodecMaxHeaderSize()).thenReturn(3);
    when(config.getHttpClientCodecMaxInitialLineLength()).thenReturn(3);
    when(config.getIoThreadsCount()).thenReturn(3);
    when(config.getMaxConnections()).thenReturn(3);
    when(config.getMaxConnectionsPerHost()).thenReturn(3);
    when(config.getMaxRedirects()).thenReturn(3);
    when(config.getMaxRequestRetry()).thenReturn(3);
    when(config.getSoLinger()).thenReturn(1);
    when(config.getSoRcvBuf()).thenReturn(1);
    when(config.getSoSndBuf()).thenReturn(1);
    when(config.getSslSessionCacheSize()).thenReturn(3);
    when(config.getSslSessionTimeout()).thenReturn(10);
    when(config.getWebSocketMaxBufferSize()).thenReturn(3);
    when(config.getWebSocketMaxFrameSize()).thenReturn(3);
    when(config.getAllocator()).thenReturn(new AdaptiveByteBufAllocator());
    when(config.getEventLoopGroup()).thenReturn(new DefaultEventLoop());
    when(config.getSslContext()).thenReturn(new JdkSslClientContext());
    when(config.getNettyTimer()).thenReturn(new HashedWheelTimer());
    when(config.getThreadPoolName()).thenReturn("https://example.org/example");
    when(config.getUserAgent()).thenReturn("https://example.org/example");
    when(config.getEnabledCipherSuites()).thenReturn(new String[]{"https://example.org/example"});
    when(config.getEnabledProtocols()).thenReturn(new String[]{"https://example.org/example"});
    when(config.getConnectTimeout()).thenReturn(null);
    when(config.getConnectionPoolCleanerPeriod()).thenReturn(null);
    when(config.getConnectionTtl()).thenReturn(null);
    when(config.getPooledConnectionIdleTimeout()).thenReturn(null);
    when(config.getReadTimeout()).thenReturn(null);
    when(config.getRequestTimeout()).thenReturn(null);
    when(config.getShutdownQuietPeriod()).thenReturn(null);
    when(config.getShutdownTimeout()).thenReturn(null);
    when(config.getIoExceptionFilters()).thenReturn(new ArrayList<>());
    when(config.getRequestFilters()).thenReturn(new ArrayList<>());
    when(config.getResponseFilters()).thenReturn(new ArrayList<>());
    when(config.getChannelOptions()).thenReturn(new HashMap<>());
    when(config.getThreadFactory()).thenReturn(mock(ThreadFactory.class));
    when(config.getHttpAdditionalChannelInitializer()).thenReturn(mock(Consumer.class));
    when(config.getWsAdditionalChannelInitializer()).thenReturn(mock(Consumer.class));
    when(config.getHashedWheelTimerTickDuration()).thenReturn(81985529216486895L);
    when(config.getResponseBodyPartFactory()).thenReturn(AsyncHttpClientConfig.ResponseBodyPartFactory.EAGER);
    when(config.getRealm()).thenReturn(mock(Realm.class));
    when(config.getSslEngineFactory()).thenReturn(mock(SslEngineFactory.class));
    when(config.getChannelPool()).thenReturn(NoopChannelPool.INSTANCE);
    when(config.getKeepAliveStrategy()).thenReturn(mock(KeepAliveStrategy.class));
    when(config.getCookieStore()).thenReturn(new ThreadSafeCookieStore());
    when(config.getConnectionSemaphoreFactory()).thenReturn(mock(ConnectionSemaphoreFactory.class));
    when(config.getProxyServerSelector()).thenReturn(mock(ProxyServerSelector.class));
    DefaultAsyncHttpClientConfig.Builder builder = new DefaultAsyncHttpClientConfig.Builder(config);

    // Act
    DefaultAsyncHttpClientConfig.Builder actualSetProxyServerResult = builder
        .setProxyServer(Dsl.proxyServer("https://example.org/example", 8080));

    // Assert
    verify(config).expiredCookieEvictionDelay();
    verify(config).getAcquireFreeChannelTimeout();
    verify(config).getAllocator();
    verify(config).getChannelOptions();
    verify(config).getChannelPool();
    verify(config).getChunkedFileChunkSize();
    verify(config).getConnectTimeout();
    verify(config).getConnectionPoolCleanerPeriod();
    verify(config).getConnectionSemaphoreFactory();
    verify(config).getConnectionTtl();
    verify(config).getCookieStore();
    verify(config).getEnabledCipherSuites();
    verify(config).getEnabledProtocols();
    verify(config).getEventLoopGroup();
    verify(config).getHandshakeTimeout();
    verify(config).getHashedWheelTimerSize();
    verify(config).getHashedWheelTimerTickDuration();
    verify(config).getHttpAdditionalChannelInitializer();
    verify(config).getHttpClientCodecInitialBufferSize();
    verify(config).getHttpClientCodecMaxChunkSize();
    verify(config).getHttpClientCodecMaxHeaderSize();
    verify(config).getHttpClientCodecMaxInitialLineLength();
    verify(config).getIoExceptionFilters();
    verify(config).getIoThreadsCount();
    verify(config).getKeepAliveStrategy();
    verify(config).getMaxConnections();
    verify(config).getMaxConnectionsPerHost();
    verify(config).getMaxRedirects();
    verify(config).getMaxRequestRetry();
    verify(config).getNettyTimer();
    verify(config).getPooledConnectionIdleTimeout();
    verify(config).getProxyServerSelector();
    verify(config).getReadTimeout();
    verify(config).getRealm();
    verify(config).getRequestFilters();
    verify(config).getRequestTimeout();
    verify(config).getResponseBodyPartFactory();
    verify(config).getResponseFilters();
    verify(config).getShutdownQuietPeriod();
    verify(config).getShutdownTimeout();
    verify(config).getSoLinger();
    verify(config).getSoRcvBuf();
    verify(config).getSoSndBuf();
    verify(config).getSslContext();
    verify(config).getSslEngineFactory();
    verify(config).getSslSessionCacheSize();
    verify(config).getSslSessionTimeout();
    verify(config).getThreadFactory();
    verify(config).getThreadPoolName();
    verify(config).getUserAgent();
    verify(config).getWebSocketMaxBufferSize();
    verify(config).getWebSocketMaxFrameSize();
    verify(config).getWsAdditionalChannelInitializer();
    verify(config).isAggregateWebSocketFrameFragments();
    verify(config).isCompressionEnforced();
    verify(config).isDisableHttpsEndpointIdentificationAlgorithm();
    verify(config).isDisableUrlEncodingForBoundRequests();
    verify(config).isDisableZeroCopy();
    verify(config).isEnableAutomaticDecompression();
    verify(config).isEnableWebSocketCompression();
    verify(config).isFilterInsecureCipherSuites();
    verify(config).isFollowRedirect();
    verify(config).isKeepAlive();
    verify(config).isKeepEncodingHeader();
    verify(config).isSoKeepAlive();
    verify(config).isSoReuseAddress();
    verify(config).isStrict302Handling();
    verify(config).isTcpNoDelay();
    verify(config).isUseInsecureTrustManager();
    verify(config).isUseLaxCookieEncoder();
    verify(config).isUseNativeTransport();
    verify(config).isUseOnlyEpollNativeTransport();
    verify(config).isUseOpenSsl();
    verify(config).isValidateResponseHeaders();
    assertSame(builder, actualSetProxyServerResult);
  }

  /**
   * Method under test:
   * {@link DefaultAsyncHttpClientConfig.Builder#setProxyServer(ProxyServer)}
   */
  @Test
  void testBuilderSetProxyServer5() {
    // Arrange
    DefaultAsyncHttpClientConfig.Builder configResult = Dsl.config();
    Realm realm = mock(Realm.class);

    // Act and Assert
    assertSame(configResult, configResult.setProxyServer(
        new ProxyServer("https://example.org/example", 8080, 8080, realm, new ArrayList<>(), ProxyType.HTTP)));
  }

  /**
   * Method under test:
   * {@link DefaultAsyncHttpClientConfig.Builder#setRealm(Realm.Builder)}
   */
  @Test
  void testBuilderSetRealm() throws SSLException {
    // Arrange
    AsyncHttpClientConfig config = mock(AsyncHttpClientConfig.class);
    when(config.isAggregateWebSocketFrameFragments()).thenReturn(true);
    when(config.isCompressionEnforced()).thenReturn(true);
    when(config.isDisableHttpsEndpointIdentificationAlgorithm()).thenReturn(true);
    when(config.isDisableUrlEncodingForBoundRequests()).thenReturn(true);
    when(config.isDisableZeroCopy()).thenReturn(true);
    when(config.isEnableAutomaticDecompression()).thenReturn(true);
    when(config.isEnableWebSocketCompression()).thenReturn(true);
    when(config.isFilterInsecureCipherSuites()).thenReturn(true);
    when(config.isFollowRedirect()).thenReturn(true);
    when(config.isKeepAlive()).thenReturn(true);
    when(config.isKeepEncodingHeader()).thenReturn(true);
    when(config.isSoKeepAlive()).thenReturn(true);
    when(config.isSoReuseAddress()).thenReturn(true);
    when(config.isStrict302Handling()).thenReturn(true);
    when(config.isTcpNoDelay()).thenReturn(true);
    when(config.isUseInsecureTrustManager()).thenReturn(true);
    when(config.isUseLaxCookieEncoder()).thenReturn(true);
    when(config.isUseNativeTransport()).thenReturn(true);
    when(config.isUseOnlyEpollNativeTransport()).thenReturn(true);
    when(config.isUseOpenSsl()).thenReturn(true);
    when(config.isValidateResponseHeaders()).thenReturn(true);
    when(config.expiredCookieEvictionDelay()).thenReturn(1);
    when(config.getAcquireFreeChannelTimeout()).thenReturn(10);
    when(config.getChunkedFileChunkSize()).thenReturn(3);
    when(config.getHandshakeTimeout()).thenReturn(10);
    when(config.getHashedWheelTimerSize()).thenReturn(19088743);
    when(config.getHttpClientCodecInitialBufferSize()).thenReturn(3);
    when(config.getHttpClientCodecMaxChunkSize()).thenReturn(3);
    when(config.getHttpClientCodecMaxHeaderSize()).thenReturn(3);
    when(config.getHttpClientCodecMaxInitialLineLength()).thenReturn(3);
    when(config.getIoThreadsCount()).thenReturn(3);
    when(config.getMaxConnections()).thenReturn(3);
    when(config.getMaxConnectionsPerHost()).thenReturn(3);
    when(config.getMaxRedirects()).thenReturn(3);
    when(config.getMaxRequestRetry()).thenReturn(3);
    when(config.getSoLinger()).thenReturn(1);
    when(config.getSoRcvBuf()).thenReturn(1);
    when(config.getSoSndBuf()).thenReturn(1);
    when(config.getSslSessionCacheSize()).thenReturn(3);
    when(config.getSslSessionTimeout()).thenReturn(10);
    when(config.getWebSocketMaxBufferSize()).thenReturn(3);
    when(config.getWebSocketMaxFrameSize()).thenReturn(3);
    when(config.getAllocator()).thenReturn(new AdaptiveByteBufAllocator());
    when(config.getEventLoopGroup()).thenReturn(new DefaultEventLoop());
    when(config.getSslContext()).thenReturn(new JdkSslClientContext());
    when(config.getNettyTimer()).thenReturn(new HashedWheelTimer());
    when(config.getThreadPoolName()).thenReturn("https://example.org/example");
    when(config.getUserAgent()).thenReturn("https://example.org/example");
    when(config.getEnabledCipherSuites()).thenReturn(new String[]{"https://example.org/example"});
    when(config.getEnabledProtocols()).thenReturn(new String[]{"https://example.org/example"});
    when(config.getConnectTimeout()).thenReturn(null);
    when(config.getConnectionPoolCleanerPeriod()).thenReturn(null);
    when(config.getConnectionTtl()).thenReturn(null);
    when(config.getPooledConnectionIdleTimeout()).thenReturn(null);
    when(config.getReadTimeout()).thenReturn(null);
    when(config.getRequestTimeout()).thenReturn(null);
    when(config.getShutdownQuietPeriod()).thenReturn(null);
    when(config.getShutdownTimeout()).thenReturn(null);
    when(config.getIoExceptionFilters()).thenReturn(new ArrayList<>());
    when(config.getRequestFilters()).thenReturn(new ArrayList<>());
    when(config.getResponseFilters()).thenReturn(new ArrayList<>());
    when(config.getChannelOptions()).thenReturn(new HashMap<>());
    when(config.getThreadFactory()).thenReturn(mock(ThreadFactory.class));
    when(config.getHttpAdditionalChannelInitializer()).thenReturn(mock(Consumer.class));
    when(config.getWsAdditionalChannelInitializer()).thenReturn(mock(Consumer.class));
    when(config.getHashedWheelTimerTickDuration()).thenReturn(81985529216486895L);
    when(config.getResponseBodyPartFactory()).thenReturn(AsyncHttpClientConfig.ResponseBodyPartFactory.EAGER);
    when(config.getRealm()).thenReturn(null);
    when(config.getSslEngineFactory()).thenReturn(mock(SslEngineFactory.class));
    when(config.getChannelPool()).thenReturn(NoopChannelPool.INSTANCE);
    when(config.getKeepAliveStrategy()).thenReturn(mock(KeepAliveStrategy.class));
    when(config.getCookieStore()).thenReturn(new ThreadSafeCookieStore());
    when(config.getConnectionSemaphoreFactory()).thenReturn(mock(ConnectionSemaphoreFactory.class));
    when(config.getProxyServerSelector()).thenReturn(mock(ProxyServerSelector.class));
    DefaultAsyncHttpClientConfig.Builder builder = new DefaultAsyncHttpClientConfig.Builder(config);
    Realm.Builder realmBuilder = mock(Realm.Builder.class);
    when(realmBuilder.build()).thenReturn(null);

    // Act
    DefaultAsyncHttpClientConfig.Builder actualSetRealmResult = builder.setRealm(realmBuilder);

    // Assert
    verify(config).expiredCookieEvictionDelay();
    verify(config).getAcquireFreeChannelTimeout();
    verify(config).getAllocator();
    verify(config).getChannelOptions();
    verify(config).getChannelPool();
    verify(config).getChunkedFileChunkSize();
    verify(config).getConnectTimeout();
    verify(config).getConnectionPoolCleanerPeriod();
    verify(config).getConnectionSemaphoreFactory();
    verify(config).getConnectionTtl();
    verify(config).getCookieStore();
    verify(config).getEnabledCipherSuites();
    verify(config).getEnabledProtocols();
    verify(config).getEventLoopGroup();
    verify(config).getHandshakeTimeout();
    verify(config).getHashedWheelTimerSize();
    verify(config).getHashedWheelTimerTickDuration();
    verify(config).getHttpAdditionalChannelInitializer();
    verify(config).getHttpClientCodecInitialBufferSize();
    verify(config).getHttpClientCodecMaxChunkSize();
    verify(config).getHttpClientCodecMaxHeaderSize();
    verify(config).getHttpClientCodecMaxInitialLineLength();
    verify(config).getIoExceptionFilters();
    verify(config).getIoThreadsCount();
    verify(config).getKeepAliveStrategy();
    verify(config).getMaxConnections();
    verify(config).getMaxConnectionsPerHost();
    verify(config).getMaxRedirects();
    verify(config).getMaxRequestRetry();
    verify(config).getNettyTimer();
    verify(config).getPooledConnectionIdleTimeout();
    verify(config).getProxyServerSelector();
    verify(config).getReadTimeout();
    verify(config).getRealm();
    verify(config).getRequestFilters();
    verify(config).getRequestTimeout();
    verify(config).getResponseBodyPartFactory();
    verify(config).getResponseFilters();
    verify(config).getShutdownQuietPeriod();
    verify(config).getShutdownTimeout();
    verify(config).getSoLinger();
    verify(config).getSoRcvBuf();
    verify(config).getSoSndBuf();
    verify(config).getSslContext();
    verify(config).getSslEngineFactory();
    verify(config).getSslSessionCacheSize();
    verify(config).getSslSessionTimeout();
    verify(config).getThreadFactory();
    verify(config).getThreadPoolName();
    verify(config).getUserAgent();
    verify(config).getWebSocketMaxBufferSize();
    verify(config).getWebSocketMaxFrameSize();
    verify(config).getWsAdditionalChannelInitializer();
    verify(config).isAggregateWebSocketFrameFragments();
    verify(config).isCompressionEnforced();
    verify(config).isDisableHttpsEndpointIdentificationAlgorithm();
    verify(config).isDisableUrlEncodingForBoundRequests();
    verify(config).isDisableZeroCopy();
    verify(config).isEnableAutomaticDecompression();
    verify(config).isEnableWebSocketCompression();
    verify(config).isFilterInsecureCipherSuites();
    verify(config).isFollowRedirect();
    verify(config).isKeepAlive();
    verify(config).isKeepEncodingHeader();
    verify(config).isSoKeepAlive();
    verify(config).isSoReuseAddress();
    verify(config).isStrict302Handling();
    verify(config).isTcpNoDelay();
    verify(config).isUseInsecureTrustManager();
    verify(config).isUseLaxCookieEncoder();
    verify(config).isUseNativeTransport();
    verify(config).isUseOnlyEpollNativeTransport();
    verify(config).isUseOpenSsl();
    verify(config).isValidateResponseHeaders();
    verify(realmBuilder).build();
    assertSame(builder, actualSetRealmResult);
  }

  /**
   * Method under test:
   * {@link DefaultAsyncHttpClientConfig.Builder#setSslSessionCacheSize(Integer)}
   */
  @Test
  void testBuilderSetSslSessionCacheSize() {
    // Arrange
    DefaultAsyncHttpClientConfig.Builder configResult = Dsl.config();

    // Act
    DefaultAsyncHttpClientConfig.Builder actualSetSslSessionCacheSizeResult = configResult.setSslSessionCacheSize(3);

    // Assert
    assertEquals(3, configResult.build().getSslSessionCacheSize());
    assertSame(configResult, actualSetSslSessionCacheSizeResult);
  }

  /**
   * Method under test:
   * {@link DefaultAsyncHttpClientConfig.Builder#setSslSessionCacheSize(Integer)}
   */
  @Test
  void testBuilderSetSslSessionCacheSize2() throws SSLException {
    // Arrange
    AsyncHttpClientConfig config = mock(AsyncHttpClientConfig.class);
    when(config.isAggregateWebSocketFrameFragments()).thenReturn(true);
    when(config.isCompressionEnforced()).thenReturn(true);
    when(config.isDisableHttpsEndpointIdentificationAlgorithm()).thenReturn(true);
    when(config.isDisableUrlEncodingForBoundRequests()).thenReturn(true);
    when(config.isDisableZeroCopy()).thenReturn(true);
    when(config.isEnableAutomaticDecompression()).thenReturn(true);
    when(config.isEnableWebSocketCompression()).thenReturn(true);
    when(config.isFilterInsecureCipherSuites()).thenReturn(true);
    when(config.isFollowRedirect()).thenReturn(true);
    when(config.isKeepAlive()).thenReturn(true);
    when(config.isKeepEncodingHeader()).thenReturn(true);
    when(config.isSoKeepAlive()).thenReturn(true);
    when(config.isSoReuseAddress()).thenReturn(true);
    when(config.isStrict302Handling()).thenReturn(true);
    when(config.isTcpNoDelay()).thenReturn(true);
    when(config.isUseInsecureTrustManager()).thenReturn(true);
    when(config.isUseLaxCookieEncoder()).thenReturn(true);
    when(config.isUseNativeTransport()).thenReturn(true);
    when(config.isUseOnlyEpollNativeTransport()).thenReturn(true);
    when(config.isUseOpenSsl()).thenReturn(true);
    when(config.isValidateResponseHeaders()).thenReturn(true);
    when(config.expiredCookieEvictionDelay()).thenReturn(1);
    when(config.getAcquireFreeChannelTimeout()).thenReturn(10);
    when(config.getChunkedFileChunkSize()).thenReturn(3);
    when(config.getHandshakeTimeout()).thenReturn(10);
    when(config.getHashedWheelTimerSize()).thenReturn(19088743);
    when(config.getHttpClientCodecInitialBufferSize()).thenReturn(3);
    when(config.getHttpClientCodecMaxChunkSize()).thenReturn(3);
    when(config.getHttpClientCodecMaxHeaderSize()).thenReturn(3);
    when(config.getHttpClientCodecMaxInitialLineLength()).thenReturn(3);
    when(config.getIoThreadsCount()).thenReturn(3);
    when(config.getMaxConnections()).thenReturn(3);
    when(config.getMaxConnectionsPerHost()).thenReturn(3);
    when(config.getMaxRedirects()).thenReturn(3);
    when(config.getMaxRequestRetry()).thenReturn(3);
    when(config.getSoLinger()).thenReturn(1);
    when(config.getSoRcvBuf()).thenReturn(1);
    when(config.getSoSndBuf()).thenReturn(1);
    when(config.getSslSessionCacheSize()).thenReturn(3);
    when(config.getSslSessionTimeout()).thenReturn(10);
    when(config.getWebSocketMaxBufferSize()).thenReturn(3);
    when(config.getWebSocketMaxFrameSize()).thenReturn(3);
    when(config.getAllocator()).thenReturn(new AdaptiveByteBufAllocator());
    when(config.getEventLoopGroup()).thenReturn(new DefaultEventLoop());
    when(config.getSslContext()).thenReturn(new JdkSslClientContext());
    when(config.getNettyTimer()).thenReturn(new HashedWheelTimer());
    when(config.getThreadPoolName()).thenReturn("https://example.org/example");
    when(config.getUserAgent()).thenReturn("https://example.org/example");
    when(config.getEnabledCipherSuites()).thenReturn(new String[]{"https://example.org/example"});
    when(config.getEnabledProtocols()).thenReturn(new String[]{"https://example.org/example"});
    when(config.getConnectTimeout()).thenReturn(null);
    when(config.getConnectionPoolCleanerPeriod()).thenReturn(null);
    when(config.getConnectionTtl()).thenReturn(null);
    when(config.getPooledConnectionIdleTimeout()).thenReturn(null);
    when(config.getReadTimeout()).thenReturn(null);
    when(config.getRequestTimeout()).thenReturn(null);
    when(config.getShutdownQuietPeriod()).thenReturn(null);
    when(config.getShutdownTimeout()).thenReturn(null);
    when(config.getIoExceptionFilters()).thenReturn(new ArrayList<>());
    when(config.getRequestFilters()).thenReturn(new ArrayList<>());
    when(config.getResponseFilters()).thenReturn(new ArrayList<>());
    when(config.getChannelOptions()).thenReturn(new HashMap<>());
    when(config.getThreadFactory()).thenReturn(mock(ThreadFactory.class));
    when(config.getHttpAdditionalChannelInitializer()).thenReturn(mock(Consumer.class));
    when(config.getWsAdditionalChannelInitializer()).thenReturn(mock(Consumer.class));
    when(config.getHashedWheelTimerTickDuration()).thenReturn(81985529216486895L);
    when(config.getResponseBodyPartFactory()).thenReturn(AsyncHttpClientConfig.ResponseBodyPartFactory.EAGER);
    when(config.getRealm()).thenReturn(mock(Realm.class));
    when(config.getSslEngineFactory()).thenReturn(mock(SslEngineFactory.class));
    when(config.getChannelPool()).thenReturn(NoopChannelPool.INSTANCE);
    when(config.getKeepAliveStrategy()).thenReturn(mock(KeepAliveStrategy.class));
    when(config.getCookieStore()).thenReturn(new ThreadSafeCookieStore());
    when(config.getConnectionSemaphoreFactory()).thenReturn(mock(ConnectionSemaphoreFactory.class));
    when(config.getProxyServerSelector()).thenReturn(mock(ProxyServerSelector.class));
    DefaultAsyncHttpClientConfig.Builder builder = new DefaultAsyncHttpClientConfig.Builder(config);

    // Act
    DefaultAsyncHttpClientConfig.Builder actualSetSslSessionCacheSizeResult = builder.setSslSessionCacheSize(3);

    // Assert
    verify(config).expiredCookieEvictionDelay();
    verify(config).getAcquireFreeChannelTimeout();
    verify(config).getAllocator();
    verify(config).getChannelOptions();
    verify(config).getChannelPool();
    verify(config).getChunkedFileChunkSize();
    verify(config).getConnectTimeout();
    verify(config).getConnectionPoolCleanerPeriod();
    verify(config).getConnectionSemaphoreFactory();
    verify(config).getConnectionTtl();
    verify(config).getCookieStore();
    verify(config).getEnabledCipherSuites();
    verify(config).getEnabledProtocols();
    verify(config).getEventLoopGroup();
    verify(config).getHandshakeTimeout();
    verify(config).getHashedWheelTimerSize();
    verify(config).getHashedWheelTimerTickDuration();
    verify(config).getHttpAdditionalChannelInitializer();
    verify(config).getHttpClientCodecInitialBufferSize();
    verify(config).getHttpClientCodecMaxChunkSize();
    verify(config).getHttpClientCodecMaxHeaderSize();
    verify(config).getHttpClientCodecMaxInitialLineLength();
    verify(config).getIoExceptionFilters();
    verify(config).getIoThreadsCount();
    verify(config).getKeepAliveStrategy();
    verify(config).getMaxConnections();
    verify(config).getMaxConnectionsPerHost();
    verify(config).getMaxRedirects();
    verify(config).getMaxRequestRetry();
    verify(config).getNettyTimer();
    verify(config).getPooledConnectionIdleTimeout();
    verify(config).getProxyServerSelector();
    verify(config).getReadTimeout();
    verify(config).getRealm();
    verify(config).getRequestFilters();
    verify(config).getRequestTimeout();
    verify(config).getResponseBodyPartFactory();
    verify(config).getResponseFilters();
    verify(config).getShutdownQuietPeriod();
    verify(config).getShutdownTimeout();
    verify(config).getSoLinger();
    verify(config).getSoRcvBuf();
    verify(config).getSoSndBuf();
    verify(config).getSslContext();
    verify(config).getSslEngineFactory();
    verify(config).getSslSessionCacheSize();
    verify(config).getSslSessionTimeout();
    verify(config).getThreadFactory();
    verify(config).getThreadPoolName();
    verify(config).getUserAgent();
    verify(config).getWebSocketMaxBufferSize();
    verify(config).getWebSocketMaxFrameSize();
    verify(config).getWsAdditionalChannelInitializer();
    verify(config).isAggregateWebSocketFrameFragments();
    verify(config).isCompressionEnforced();
    verify(config).isDisableHttpsEndpointIdentificationAlgorithm();
    verify(config).isDisableUrlEncodingForBoundRequests();
    verify(config).isDisableZeroCopy();
    verify(config).isEnableAutomaticDecompression();
    verify(config).isEnableWebSocketCompression();
    verify(config).isFilterInsecureCipherSuites();
    verify(config).isFollowRedirect();
    verify(config).isKeepAlive();
    verify(config).isKeepEncodingHeader();
    verify(config).isSoKeepAlive();
    verify(config).isSoReuseAddress();
    verify(config).isStrict302Handling();
    verify(config).isTcpNoDelay();
    verify(config).isUseInsecureTrustManager();
    verify(config).isUseLaxCookieEncoder();
    verify(config).isUseNativeTransport();
    verify(config).isUseOnlyEpollNativeTransport();
    verify(config).isUseOpenSsl();
    verify(config).isValidateResponseHeaders();
    assertSame(builder, actualSetSslSessionCacheSizeResult);
  }

  /**
   * Method under test:
   * {@link DefaultAsyncHttpClientConfig.Builder#setSslSessionTimeout(Integer)}
   */
  @Test
  void testBuilderSetSslSessionTimeout() {
    // Arrange
    DefaultAsyncHttpClientConfig.Builder configResult = Dsl.config();

    // Act
    DefaultAsyncHttpClientConfig.Builder actualSetSslSessionTimeoutResult = configResult.setSslSessionTimeout(10);

    // Assert
    assertEquals(10, configResult.build().getSslSessionTimeout());
    assertSame(configResult, actualSetSslSessionTimeoutResult);
  }

  /**
   * Method under test:
   * {@link DefaultAsyncHttpClientConfig.Builder#setSslSessionTimeout(Integer)}
   */
  @Test
  void testBuilderSetSslSessionTimeout2() throws SSLException {
    // Arrange
    AsyncHttpClientConfig config = mock(AsyncHttpClientConfig.class);
    when(config.isAggregateWebSocketFrameFragments()).thenReturn(true);
    when(config.isCompressionEnforced()).thenReturn(true);
    when(config.isDisableHttpsEndpointIdentificationAlgorithm()).thenReturn(true);
    when(config.isDisableUrlEncodingForBoundRequests()).thenReturn(true);
    when(config.isDisableZeroCopy()).thenReturn(true);
    when(config.isEnableAutomaticDecompression()).thenReturn(true);
    when(config.isEnableWebSocketCompression()).thenReturn(true);
    when(config.isFilterInsecureCipherSuites()).thenReturn(true);
    when(config.isFollowRedirect()).thenReturn(true);
    when(config.isKeepAlive()).thenReturn(true);
    when(config.isKeepEncodingHeader()).thenReturn(true);
    when(config.isSoKeepAlive()).thenReturn(true);
    when(config.isSoReuseAddress()).thenReturn(true);
    when(config.isStrict302Handling()).thenReturn(true);
    when(config.isTcpNoDelay()).thenReturn(true);
    when(config.isUseInsecureTrustManager()).thenReturn(true);
    when(config.isUseLaxCookieEncoder()).thenReturn(true);
    when(config.isUseNativeTransport()).thenReturn(true);
    when(config.isUseOnlyEpollNativeTransport()).thenReturn(true);
    when(config.isUseOpenSsl()).thenReturn(true);
    when(config.isValidateResponseHeaders()).thenReturn(true);
    when(config.expiredCookieEvictionDelay()).thenReturn(1);
    when(config.getAcquireFreeChannelTimeout()).thenReturn(10);
    when(config.getChunkedFileChunkSize()).thenReturn(3);
    when(config.getHandshakeTimeout()).thenReturn(10);
    when(config.getHashedWheelTimerSize()).thenReturn(19088743);
    when(config.getHttpClientCodecInitialBufferSize()).thenReturn(3);
    when(config.getHttpClientCodecMaxChunkSize()).thenReturn(3);
    when(config.getHttpClientCodecMaxHeaderSize()).thenReturn(3);
    when(config.getHttpClientCodecMaxInitialLineLength()).thenReturn(3);
    when(config.getIoThreadsCount()).thenReturn(3);
    when(config.getMaxConnections()).thenReturn(3);
    when(config.getMaxConnectionsPerHost()).thenReturn(3);
    when(config.getMaxRedirects()).thenReturn(3);
    when(config.getMaxRequestRetry()).thenReturn(3);
    when(config.getSoLinger()).thenReturn(1);
    when(config.getSoRcvBuf()).thenReturn(1);
    when(config.getSoSndBuf()).thenReturn(1);
    when(config.getSslSessionCacheSize()).thenReturn(3);
    when(config.getSslSessionTimeout()).thenReturn(10);
    when(config.getWebSocketMaxBufferSize()).thenReturn(3);
    when(config.getWebSocketMaxFrameSize()).thenReturn(3);
    when(config.getAllocator()).thenReturn(new AdaptiveByteBufAllocator());
    when(config.getEventLoopGroup()).thenReturn(new DefaultEventLoop());
    when(config.getSslContext()).thenReturn(new JdkSslClientContext());
    when(config.getNettyTimer()).thenReturn(new HashedWheelTimer());
    when(config.getThreadPoolName()).thenReturn("https://example.org/example");
    when(config.getUserAgent()).thenReturn("https://example.org/example");
    when(config.getEnabledCipherSuites()).thenReturn(new String[]{"https://example.org/example"});
    when(config.getEnabledProtocols()).thenReturn(new String[]{"https://example.org/example"});
    when(config.getConnectTimeout()).thenReturn(null);
    when(config.getConnectionPoolCleanerPeriod()).thenReturn(null);
    when(config.getConnectionTtl()).thenReturn(null);
    when(config.getPooledConnectionIdleTimeout()).thenReturn(null);
    when(config.getReadTimeout()).thenReturn(null);
    when(config.getRequestTimeout()).thenReturn(null);
    when(config.getShutdownQuietPeriod()).thenReturn(null);
    when(config.getShutdownTimeout()).thenReturn(null);
    when(config.getIoExceptionFilters()).thenReturn(new ArrayList<>());
    when(config.getRequestFilters()).thenReturn(new ArrayList<>());
    when(config.getResponseFilters()).thenReturn(new ArrayList<>());
    when(config.getChannelOptions()).thenReturn(new HashMap<>());
    when(config.getThreadFactory()).thenReturn(mock(ThreadFactory.class));
    when(config.getHttpAdditionalChannelInitializer()).thenReturn(mock(Consumer.class));
    when(config.getWsAdditionalChannelInitializer()).thenReturn(mock(Consumer.class));
    when(config.getHashedWheelTimerTickDuration()).thenReturn(81985529216486895L);
    when(config.getResponseBodyPartFactory()).thenReturn(AsyncHttpClientConfig.ResponseBodyPartFactory.EAGER);
    when(config.getRealm()).thenReturn(mock(Realm.class));
    when(config.getSslEngineFactory()).thenReturn(mock(SslEngineFactory.class));
    when(config.getChannelPool()).thenReturn(NoopChannelPool.INSTANCE);
    when(config.getKeepAliveStrategy()).thenReturn(mock(KeepAliveStrategy.class));
    when(config.getCookieStore()).thenReturn(new ThreadSafeCookieStore());
    when(config.getConnectionSemaphoreFactory()).thenReturn(mock(ConnectionSemaphoreFactory.class));
    when(config.getProxyServerSelector()).thenReturn(mock(ProxyServerSelector.class));
    DefaultAsyncHttpClientConfig.Builder builder = new DefaultAsyncHttpClientConfig.Builder(config);

    // Act
    DefaultAsyncHttpClientConfig.Builder actualSetSslSessionTimeoutResult = builder.setSslSessionTimeout(10);

    // Assert
    verify(config).expiredCookieEvictionDelay();
    verify(config).getAcquireFreeChannelTimeout();
    verify(config).getAllocator();
    verify(config).getChannelOptions();
    verify(config).getChannelPool();
    verify(config).getChunkedFileChunkSize();
    verify(config).getConnectTimeout();
    verify(config).getConnectionPoolCleanerPeriod();
    verify(config).getConnectionSemaphoreFactory();
    verify(config).getConnectionTtl();
    verify(config).getCookieStore();
    verify(config).getEnabledCipherSuites();
    verify(config).getEnabledProtocols();
    verify(config).getEventLoopGroup();
    verify(config).getHandshakeTimeout();
    verify(config).getHashedWheelTimerSize();
    verify(config).getHashedWheelTimerTickDuration();
    verify(config).getHttpAdditionalChannelInitializer();
    verify(config).getHttpClientCodecInitialBufferSize();
    verify(config).getHttpClientCodecMaxChunkSize();
    verify(config).getHttpClientCodecMaxHeaderSize();
    verify(config).getHttpClientCodecMaxInitialLineLength();
    verify(config).getIoExceptionFilters();
    verify(config).getIoThreadsCount();
    verify(config).getKeepAliveStrategy();
    verify(config).getMaxConnections();
    verify(config).getMaxConnectionsPerHost();
    verify(config).getMaxRedirects();
    verify(config).getMaxRequestRetry();
    verify(config).getNettyTimer();
    verify(config).getPooledConnectionIdleTimeout();
    verify(config).getProxyServerSelector();
    verify(config).getReadTimeout();
    verify(config).getRealm();
    verify(config).getRequestFilters();
    verify(config).getRequestTimeout();
    verify(config).getResponseBodyPartFactory();
    verify(config).getResponseFilters();
    verify(config).getShutdownQuietPeriod();
    verify(config).getShutdownTimeout();
    verify(config).getSoLinger();
    verify(config).getSoRcvBuf();
    verify(config).getSoSndBuf();
    verify(config).getSslContext();
    verify(config).getSslEngineFactory();
    verify(config).getSslSessionCacheSize();
    verify(config).getSslSessionTimeout();
    verify(config).getThreadFactory();
    verify(config).getThreadPoolName();
    verify(config).getUserAgent();
    verify(config).getWebSocketMaxBufferSize();
    verify(config).getWebSocketMaxFrameSize();
    verify(config).getWsAdditionalChannelInitializer();
    verify(config).isAggregateWebSocketFrameFragments();
    verify(config).isCompressionEnforced();
    verify(config).isDisableHttpsEndpointIdentificationAlgorithm();
    verify(config).isDisableUrlEncodingForBoundRequests();
    verify(config).isDisableZeroCopy();
    verify(config).isEnableAutomaticDecompression();
    verify(config).isEnableWebSocketCompression();
    verify(config).isFilterInsecureCipherSuites();
    verify(config).isFollowRedirect();
    verify(config).isKeepAlive();
    verify(config).isKeepEncodingHeader();
    verify(config).isSoKeepAlive();
    verify(config).isSoReuseAddress();
    verify(config).isStrict302Handling();
    verify(config).isTcpNoDelay();
    verify(config).isUseInsecureTrustManager();
    verify(config).isUseLaxCookieEncoder();
    verify(config).isUseNativeTransport();
    verify(config).isUseOnlyEpollNativeTransport();
    verify(config).isUseOpenSsl();
    verify(config).isValidateResponseHeaders();
    assertSame(builder, actualSetSslSessionTimeoutResult);
  }
}
