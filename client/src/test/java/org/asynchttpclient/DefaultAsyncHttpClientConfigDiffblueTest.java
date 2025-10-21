package org.asynchttpclient;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.MethodsUnderTest;
import io.netty.buffer.AdaptiveByteBufAllocator;
import io.netty.buffer.ByteBufAllocator;
import io.netty.channel.ChannelOption;
import io.netty.channel.DefaultEventLoop;
import io.netty.channel.EventLoopGroup;
import io.netty.handler.ssl.JdkSslClientContext;
import io.netty.handler.ssl.SslContext;
import io.netty.util.HashedWheelTimer;
import io.netty.util.Timer;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ThreadFactory;
import java.util.function.Consumer;
import javax.net.ssl.SSLException;
import org.asynchttpclient.AsyncHttpClientConfig.ResponseBodyPartFactory;
import org.asynchttpclient.DefaultAsyncHttpClientConfig.Builder;
import org.asynchttpclient.Realm.AuthScheme;
import org.asynchttpclient.channel.ChannelPool;
import org.asynchttpclient.channel.KeepAliveStrategy;
import org.asynchttpclient.channel.NoopChannelPool;
import org.asynchttpclient.cookie.CookieStore;
import org.asynchttpclient.cookie.ThreadSafeCookieStore;
import org.asynchttpclient.filter.IOExceptionFilter;
import org.asynchttpclient.filter.RequestFilter;
import org.asynchttpclient.filter.ResponseFilter;
import org.asynchttpclient.netty.channel.ConnectionSemaphoreFactory;
import org.asynchttpclient.proxy.ProxyServer;
import org.asynchttpclient.proxy.ProxyServerSelector;
import org.asynchttpclient.proxy.ProxyType;
import org.asynchttpclient.uri.Uri;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

class DefaultAsyncHttpClientConfigDiffblueTest {
  /**
   * Test Builder {@link Builder#addChannelOption(ChannelOption, Object)}.
   * <p>
   * Method under test: {@link Builder#addChannelOption(ChannelOption, Object)}
   */
  @Test
  @DisplayName("Test Builder addChannelOption(ChannelOption, Object)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Builder Builder.addChannelOption(ChannelOption, Object)"})
  void testBuilderAddChannelOption() {
    // Arrange
    Builder configResult = Dsl.config();

    // Act
    Builder actualAddChannelOptionResult = configResult.addChannelOption(mock(ChannelOption.class), "Value");

    // Assert
    assertEquals(1, configResult.build().getChannelOptions().size());
    assertSame(configResult, actualAddChannelOptionResult);
  }

  /**
   * Test Builder {@link Builder#addIOExceptionFilter(IOExceptionFilter)}.
   * <p>
   * Method under test: {@link Builder#addIOExceptionFilter(IOExceptionFilter)}
   */
  @Test
  @DisplayName("Test Builder addIOExceptionFilter(IOExceptionFilter)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Builder Builder.addIOExceptionFilter(IOExceptionFilter)"})
  void testBuilderAddIOExceptionFilter() {
    // Arrange
    Builder configResult = Dsl.config();

    // Act
    Builder actualAddIOExceptionFilterResult = configResult.addIOExceptionFilter(mock(IOExceptionFilter.class));

    // Assert
    assertEquals(1, configResult.build().getIoExceptionFilters().size());
    assertSame(configResult, actualAddIOExceptionFilterResult);
  }

  /**
   * Test Builder {@link Builder#addRequestFilter(RequestFilter)}.
   * <p>
   * Method under test: {@link Builder#addRequestFilter(RequestFilter)}
   */
  @Test
  @DisplayName("Test Builder addRequestFilter(RequestFilter)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Builder Builder.addRequestFilter(RequestFilter)"})
  void testBuilderAddRequestFilter() {
    // Arrange
    Builder configResult = Dsl.config();

    // Act
    Builder actualAddRequestFilterResult = configResult.addRequestFilter(mock(RequestFilter.class));

    // Assert
    assertEquals(1, configResult.build().getRequestFilters().size());
    assertSame(configResult, actualAddRequestFilterResult);
  }

  /**
   * Test Builder {@link Builder#addResponseFilter(ResponseFilter)}.
   * <p>
   * Method under test: {@link Builder#addResponseFilter(ResponseFilter)}
   */
  @Test
  @DisplayName("Test Builder addResponseFilter(ResponseFilter)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Builder Builder.addResponseFilter(ResponseFilter)"})
  void testBuilderAddResponseFilter() {
    // Arrange
    Builder configResult = Dsl.config();

    // Act
    Builder actualAddResponseFilterResult = configResult.addResponseFilter(mock(ResponseFilter.class));

    // Assert
    assertEquals(1, configResult.build().getResponseFilters().size());
    assertSame(configResult, actualAddResponseFilterResult);
  }

  /**
   * Test Builder {@link Builder#Builder(AsyncHttpClientConfig)}.
   * <p>
   * Method under test: {@link Builder#Builder(AsyncHttpClientConfig)}
   */
  @Test
  @DisplayName("Test Builder new Builder(AsyncHttpClientConfig)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void Builder.<init>(AsyncHttpClientConfig)"})
  void testBuilderNewBuilder() throws SSLException {
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
    AdaptiveByteBufAllocator adaptiveByteBufAllocator = new AdaptiveByteBufAllocator();
    when(config.getAllocator()).thenReturn(adaptiveByteBufAllocator);
    DefaultEventLoop defaultEventLoop = new DefaultEventLoop();
    when(config.getEventLoopGroup()).thenReturn(defaultEventLoop);
    JdkSslClientContext jdkSslClientContext = new JdkSslClientContext();
    when(config.getSslContext()).thenReturn(jdkSslClientContext);
    HashedWheelTimer hashedWheelTimer = new HashedWheelTimer();
    when(config.getNettyTimer()).thenReturn(hashedWheelTimer);
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
    when(config.getResponseBodyPartFactory()).thenReturn(ResponseBodyPartFactory.EAGER);
    Realm.Builder setAlgorithmResult = (new Realm.Builder()).setAlgorithm("https://example.org/example");
    Realm.Builder setCharsetResult = setAlgorithmResult.setCharset(Charset.forName("UTF-8"));
    Realm.Builder setServicePrincipalNameResult = setCharsetResult.setCustomLoginConfig(new HashMap<>())
        .setLoginContextName("https://example.org/example")
        .setMethodName("https://example.org/example")
        .setNc("https://example.org/example")
        .setNonce("")
        .setNtlmDomain("https://example.org/example")
        .setNtlmHost("https://example.org/example")
        .setOmitQuery(true)
        .setOpaque("https://example.org/example")
        .setQop("https://example.org/example")
        .setRealmName("https://example.org/example")
        .setResponse("https://example.org/example")
        .setScheme(AuthScheme.BASIC)
        .setServicePrincipalName("https://example.org/example");
    Realm buildResult = setServicePrincipalNameResult
        .setUri(new Uri("https://example.org/example", "https://example.org/example", "https://example.org/example",
            8080, "https://example.org/example", "https://example.org/example", "https://example.org/example"))
        .setUseAbsoluteURI(true)
        .setUseCanonicalHostname(true)
        .setUsePreemptiveAuth(true)
        .build();
    when(config.getRealm()).thenReturn(buildResult);
    when(config.getSslEngineFactory()).thenReturn(mock(SslEngineFactory.class));
    when(config.getChannelPool()).thenReturn(NoopChannelPool.INSTANCE);
    when(config.getKeepAliveStrategy()).thenReturn(mock(KeepAliveStrategy.class));
    ThreadSafeCookieStore threadSafeCookieStore = new ThreadSafeCookieStore();
    when(config.getCookieStore()).thenReturn(threadSafeCookieStore);
    when(config.getConnectionSemaphoreFactory()).thenReturn(mock(ConnectionSemaphoreFactory.class));
    when(config.getProxyServerSelector()).thenReturn(mock(ProxyServerSelector.class));

    // Act
    Builder actualBuilder = new Builder(config);

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
    DefaultAsyncHttpClientConfig buildResult2 = actualBuilder.build();
    ByteBufAllocator allocator = buildResult2.getAllocator();
    assertTrue(allocator instanceof AdaptiveByteBufAllocator);
    EventLoopGroup eventLoopGroup = buildResult2.getEventLoopGroup();
    assertTrue(eventLoopGroup instanceof DefaultEventLoop);
    SslContext sslContext = buildResult2.getSslContext();
    assertTrue(sslContext instanceof JdkSslClientContext);
    Timer nettyTimer = buildResult2.getNettyTimer();
    assertTrue(nettyTimer instanceof HashedWheelTimer);
    ChannelPool channelPool = buildResult2.getChannelPool();
    assertTrue(channelPool instanceof NoopChannelPool);
    CookieStore cookieStore = buildResult2.getCookieStore();
    assertTrue(cookieStore instanceof ThreadSafeCookieStore);
    assertEquals("${pom.version}", buildResult2.getAhcVersion());
    assertEquals("https://example.org/example", buildResult2.getThreadPoolName());
    assertEquals("https://example.org/example", buildResult2.getUserAgent());
    assertNull(buildResult2.getConnectTimeout());
    assertNull(buildResult2.getConnectionPoolCleanerPeriod());
    assertNull(buildResult2.getConnectionTtl());
    assertNull(buildResult2.getPooledConnectionIdleTimeout());
    assertNull(buildResult2.getReadTimeout());
    assertNull(buildResult2.getRequestTimeout());
    assertNull(buildResult2.getShutdownQuietPeriod());
    assertNull(buildResult2.getShutdownTimeout());
    assertEquals(1, buildResult2.expiredCookieEvictionDelay());
    assertEquals(1, buildResult2.getSoLinger());
    assertEquals(1, buildResult2.getSoRcvBuf());
    assertEquals(1, buildResult2.getSoSndBuf());
    assertEquals(1, buildResult2.getEnabledCipherSuites().length);
    assertEquals(1, buildResult2.getEnabledProtocols().length);
    assertEquals(10, buildResult2.getAcquireFreeChannelTimeout());
    assertEquals(10, buildResult2.getHandshakeTimeout());
    assertEquals(10, buildResult2.getSslSessionTimeout());
    assertEquals(19088743, buildResult2.getHashedWheelTimerSize());
    assertEquals(3, buildResult2.getChunkedFileChunkSize());
    assertEquals(3, buildResult2.getHttpClientCodecInitialBufferSize());
    assertEquals(3, buildResult2.getHttpClientCodecMaxChunkSize());
    assertEquals(3, buildResult2.getHttpClientCodecMaxHeaderSize());
    assertEquals(3, buildResult2.getHttpClientCodecMaxInitialLineLength());
    assertEquals(3, buildResult2.getIoThreadsCount());
    assertEquals(3, buildResult2.getMaxConnections());
    assertEquals(3, buildResult2.getMaxConnectionsPerHost());
    assertEquals(3, buildResult2.getMaxRedirects());
    assertEquals(3, buildResult2.getMaxRequestRetry());
    assertEquals(3, buildResult2.getSslSessionCacheSize());
    assertEquals(3, buildResult2.getWebSocketMaxBufferSize());
    assertEquals(3, buildResult2.getWebSocketMaxFrameSize());
    assertEquals(81985529216486895L, buildResult2.getHashedWheelTimerTickDuration());
    assertEquals(ResponseBodyPartFactory.EAGER, buildResult2.getResponseBodyPartFactory());
    assertEquals(NoopChannelPool.INSTANCE, channelPool);
    List<IOExceptionFilter> ioExceptionFilters = buildResult2.getIoExceptionFilters();
    assertTrue(ioExceptionFilters.isEmpty());
    assertTrue(buildResult2.getChannelOptions().isEmpty());
    assertTrue(buildResult2.isAggregateWebSocketFrameFragments());
    assertTrue(buildResult2.isCompressionEnforced());
    assertTrue(buildResult2.isDisableHttpsEndpointIdentificationAlgorithm());
    assertTrue(buildResult2.isDisableUrlEncodingForBoundRequests());
    assertTrue(buildResult2.isDisableZeroCopy());
    assertTrue(buildResult2.isEnableAutomaticDecompression());
    assertTrue(buildResult2.isEnableWebSocketCompression());
    assertTrue(buildResult2.isFilterInsecureCipherSuites());
    assertTrue(buildResult2.isFollowRedirect());
    assertTrue(buildResult2.isKeepAlive());
    assertTrue(buildResult2.isKeepEncodingHeader());
    assertTrue(buildResult2.isSoKeepAlive());
    assertTrue(buildResult2.isSoReuseAddress());
    assertTrue(buildResult2.isStrict302Handling());
    assertTrue(buildResult2.isTcpNoDelay());
    assertTrue(buildResult2.isUseInsecureTrustManager());
    assertTrue(buildResult2.isUseLaxCookieEncoder());
    assertTrue(buildResult2.isUseNativeTransport());
    assertTrue(buildResult2.isUseOnlyEpollNativeTransport());
    assertTrue(buildResult2.isUseOpenSsl());
    assertTrue(buildResult2.isValidateResponseHeaders());
    assertSame(adaptiveByteBufAllocator, allocator);
    assertSame(defaultEventLoop, eventLoopGroup);
    assertSame(jdkSslClientContext, sslContext);
    assertSame(hashedWheelTimer, nettyTimer);
    assertSame(threadSafeCookieStore, cookieStore);
    assertSame(ioExceptionFilters, buildResult2.getRequestFilters());
    assertSame(ioExceptionFilters, buildResult2.getResponseFilters());
  }

  /**
   * Test Builder {@link Builder#removeIOExceptionFilter(IOExceptionFilter)}.
   * <p>
   * Method under test: {@link Builder#removeIOExceptionFilter(IOExceptionFilter)}
   */
  @Test
  @DisplayName("Test Builder removeIOExceptionFilter(IOExceptionFilter)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Builder Builder.removeIOExceptionFilter(IOExceptionFilter)"})
  void testBuilderRemoveIOExceptionFilter() {
    // Arrange
    Builder configResult = Dsl.config();

    // Act and Assert
    assertSame(configResult, configResult.removeIOExceptionFilter(mock(IOExceptionFilter.class)));
  }

  /**
   * Test Builder {@link Builder#removeRequestFilter(RequestFilter)}.
   * <p>
   * Method under test: {@link Builder#removeRequestFilter(RequestFilter)}
   */
  @Test
  @DisplayName("Test Builder removeRequestFilter(RequestFilter)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Builder Builder.removeRequestFilter(RequestFilter)"})
  void testBuilderRemoveRequestFilter() {
    // Arrange
    Builder configResult = Dsl.config();

    // Act and Assert
    assertSame(configResult, configResult.removeRequestFilter(mock(RequestFilter.class)));
  }

  /**
   * Test Builder {@link Builder#removeResponseFilter(ResponseFilter)}.
   * <p>
   * Method under test: {@link Builder#removeResponseFilter(ResponseFilter)}
   */
  @Test
  @DisplayName("Test Builder removeResponseFilter(ResponseFilter)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Builder Builder.removeResponseFilter(ResponseFilter)"})
  void testBuilderRemoveResponseFilter() {
    // Arrange
    Builder configResult = Dsl.config();

    // Act and Assert
    assertSame(configResult, configResult.removeResponseFilter(mock(ResponseFilter.class)));
  }

  /**
   * Test Builder {@link Builder#setProxyServer(Builder)} with {@code proxyServerBuilder}.
   * <p>
   * Method under test: {@link Builder#setProxyServer(ProxyServer.Builder)}
   */
  @Test
  @DisplayName("Test Builder setProxyServer(Builder) with 'proxyServerBuilder'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Builder Builder.setProxyServer(ProxyServer.Builder)"})
  void testBuilderSetProxyServerWithProxyServerBuilder() {
    // Arrange
    Builder configResult = Dsl.config();

    // Act and Assert
    assertSame(configResult, configResult.setProxyServer(Dsl.proxyServer("https://example.org/example", 8080)));
  }

  /**
   * Test Builder {@link Builder#setProxyServer(Builder)} with {@code proxyServerBuilder}.
   * <p>
   * Method under test: {@link Builder#setProxyServer(ProxyServer.Builder)}
   */
  @Test
  @DisplayName("Test Builder setProxyServer(Builder) with 'proxyServerBuilder'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Builder Builder.setProxyServer(ProxyServer.Builder)"})
  void testBuilderSetProxyServerWithProxyServerBuilder2() {
    // Arrange
    Builder configResult = Dsl.config();
    ProxyServer.Builder proxyServerBuilder = Dsl.proxyServer("https://example.org/example", 8080);
    proxyServerBuilder.setNonProxyHost(null);
    proxyServerBuilder.setProxyType(null);

    // Act and Assert
    assertSame(configResult, configResult.setProxyServer(proxyServerBuilder));
  }

  /**
   * Test Builder {@link Builder#setProxyServer(Builder)} with {@code proxyServerBuilder}.
   * <ul>
   *   <li>Given {@code HTTP}.</li>
   * </ul>
   * <p>
   * Method under test: {@link Builder#setProxyServer(ProxyServer.Builder)}
   */
  @Test
  @DisplayName("Test Builder setProxyServer(Builder) with 'proxyServerBuilder'; given 'HTTP'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Builder Builder.setProxyServer(ProxyServer.Builder)"})
  void testBuilderSetProxyServerWithProxyServerBuilder_givenHttp() {
    // Arrange
    Builder configResult = Dsl.config();
    ProxyServer.Builder proxyServerBuilder = Dsl.proxyServer("https://example.org/example", 8080);
    proxyServerBuilder.setNonProxyHost(null);
    proxyServerBuilder.setProxyType(ProxyType.HTTP);

    // Act and Assert
    assertSame(configResult, configResult.setProxyServer(proxyServerBuilder));
  }

  /**
   * Test Builder {@link Builder#setProxyServer(ProxyServer)} with {@code proxyServer}.
   * <ul>
   *   <li>Then return config.</li>
   * </ul>
   * <p>
   * Method under test: {@link Builder#setProxyServer(ProxyServer)}
   */
  @Test
  @DisplayName("Test Builder setProxyServer(ProxyServer) with 'proxyServer'; then return config")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Builder Builder.setProxyServer(ProxyServer)"})
  void testBuilderSetProxyServerWithProxyServer_thenReturnConfig() {
    // Arrange
    Builder configResult = Dsl.config();
    Realm.Builder setAlgorithmResult = (new Realm.Builder()).setAlgorithm("https://example.org/example");
    Realm.Builder setCharsetResult = setAlgorithmResult.setCharset(Charset.forName("UTF-8"));
    Realm.Builder setServicePrincipalNameResult = setCharsetResult.setCustomLoginConfig(new HashMap<>())
        .setLoginContextName("https://example.org/example")
        .setMethodName("https://example.org/example")
        .setNc("https://example.org/example")
        .setNonce("")
        .setNtlmDomain("https://example.org/example")
        .setNtlmHost("https://example.org/example")
        .setOmitQuery(true)
        .setOpaque("https://example.org/example")
        .setQop("https://example.org/example")
        .setRealmName("https://example.org/example")
        .setResponse("https://example.org/example")
        .setScheme(AuthScheme.BASIC)
        .setServicePrincipalName("https://example.org/example");
    Realm realm = setServicePrincipalNameResult
        .setUri(new Uri("https://example.org/example", "https://example.org/example", "https://example.org/example",
            8080, "https://example.org/example", "https://example.org/example", "https://example.org/example"))
        .setUseAbsoluteURI(true)
        .setUseCanonicalHostname(true)
        .setUsePreemptiveAuth(true)
        .build();

    // Act and Assert
    assertSame(configResult, configResult.setProxyServer(
        new ProxyServer("https://example.org/example", 8080, 8080, realm, new ArrayList<>(), ProxyType.HTTP)));
  }

  /**
   * Test Builder {@link Builder#setRealm(Builder)} with {@code realmBuilder}.
   * <ul>
   *   <li>Given {@code BASIC}.</li>
   *   <li>Then return config.</li>
   * </ul>
   * <p>
   * Method under test: {@link Builder#setRealm(Realm.Builder)}
   */
  @Test
  @DisplayName("Test Builder setRealm(Builder) with 'realmBuilder'; given 'BASIC'; then return config")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Builder Builder.setRealm(Realm.Builder)"})
  void testBuilderSetRealmWithRealmBuilder_givenBasic_thenReturnConfig() {
    // Arrange
    Builder configResult = Dsl.config();

    Realm.Builder realmBuilder = new Realm.Builder();
    realmBuilder.setScheme(AuthScheme.BASIC);

    // Act and Assert
    assertSame(configResult, configResult.setRealm(realmBuilder));
  }

  /**
   * Test Builder {@link Builder#setSslSessionCacheSize(Integer)}.
   * <p>
   * Method under test: {@link Builder#setSslSessionCacheSize(Integer)}
   */
  @Test
  @DisplayName("Test Builder setSslSessionCacheSize(Integer)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Builder Builder.setSslSessionCacheSize(Integer)"})
  void testBuilderSetSslSessionCacheSize() {
    // Arrange
    Builder configResult = Dsl.config();

    // Act
    Builder actualSetSslSessionCacheSizeResult = configResult.setSslSessionCacheSize(3);

    // Assert
    assertEquals(3, configResult.build().getSslSessionCacheSize());
    assertSame(configResult, actualSetSslSessionCacheSizeResult);
  }

  /**
   * Test Builder {@link Builder#setSslSessionTimeout(Integer)}.
   * <p>
   * Method under test: {@link Builder#setSslSessionTimeout(Integer)}
   */
  @Test
  @DisplayName("Test Builder setSslSessionTimeout(Integer)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Builder Builder.setSslSessionTimeout(Integer)"})
  void testBuilderSetSslSessionTimeout() {
    // Arrange
    Builder configResult = Dsl.config();

    // Act
    Builder actualSetSslSessionTimeoutResult = configResult.setSslSessionTimeout(10);

    // Assert
    assertEquals(10, configResult.build().getSslSessionTimeout());
    assertSame(configResult, actualSetSslSessionTimeoutResult);
  }
}
