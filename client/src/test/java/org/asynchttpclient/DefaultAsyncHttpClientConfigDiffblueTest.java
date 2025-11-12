package org.asynchttpclient;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.ManagedByDiffblue;
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
import java.time.Duration;
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
   *
   * <p>Method under test: {@link Builder#addChannelOption(ChannelOption, Object)}
   */
  @Test
  @DisplayName("Test Builder addChannelOption(ChannelOption, Object)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"Builder Builder.addChannelOption(ChannelOption, Object)"})
  void testBuilderAddChannelOption() {
    // Arrange
    Builder configResult = Dsl.config();

    // Act
    Builder actualAddChannelOptionResult =
        configResult.addChannelOption(mock(ChannelOption.class), "Value");

    // Assert
    assertEquals(1, configResult.build().getChannelOptions().size());
    assertSame(configResult, actualAddChannelOptionResult);
  }

  /**
   * Test Builder {@link Builder#addIOExceptionFilter(IOExceptionFilter)}.
   *
   * <p>Method under test: {@link Builder#addIOExceptionFilter(IOExceptionFilter)}
   */
  @Test
  @DisplayName("Test Builder addIOExceptionFilter(IOExceptionFilter)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"Builder Builder.addIOExceptionFilter(IOExceptionFilter)"})
  void testBuilderAddIOExceptionFilter() {
    // Arrange
    Builder configResult = Dsl.config();

    // Act
    Builder actualAddIOExceptionFilterResult =
        configResult.addIOExceptionFilter(mock(IOExceptionFilter.class));

    // Assert
    assertEquals(1, configResult.build().getIoExceptionFilters().size());
    assertSame(configResult, actualAddIOExceptionFilterResult);
  }

  /**
   * Test Builder {@link Builder#addRequestFilter(RequestFilter)}.
   *
   * <p>Method under test: {@link Builder#addRequestFilter(RequestFilter)}
   */
  @Test
  @DisplayName("Test Builder addRequestFilter(RequestFilter)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
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
   *
   * <p>Method under test: {@link Builder#addResponseFilter(ResponseFilter)}
   */
  @Test
  @DisplayName("Test Builder addResponseFilter(ResponseFilter)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"Builder Builder.addResponseFilter(ResponseFilter)"})
  void testBuilderAddResponseFilter() {
    // Arrange
    Builder configResult = Dsl.config();

    // Act
    Builder actualAddResponseFilterResult =
        configResult.addResponseFilter(mock(ResponseFilter.class));

    // Assert
    assertEquals(1, configResult.build().getResponseFilters().size());
    assertSame(configResult, actualAddResponseFilterResult);
  }

  /**
   * Test Builder {@link Builder#Builder(AsyncHttpClientConfig)}.
   *
   * <p>Method under test: {@link Builder#Builder(AsyncHttpClientConfig)}
   */
  @Test
  @DisplayName("Test Builder new Builder(AsyncHttpClientConfig)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
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
    when(config.getEnabledCipherSuites()).thenReturn(new String[] {"https://example.org/example"});
    when(config.getEnabledProtocols()).thenReturn(new String[] {"https://example.org/example"});
    Duration ofSecondsResult = Duration.ofSeconds(1L);
    when(config.getConnectTimeout()).thenReturn(ofSecondsResult);
    Duration ofSecondsResult2 = Duration.ofSeconds(1L);
    when(config.getConnectionPoolCleanerPeriod()).thenReturn(ofSecondsResult2);
    Duration ofSecondsResult3 = Duration.ofSeconds(1L);
    when(config.getConnectionTtl()).thenReturn(ofSecondsResult3);
    Duration ofSecondsResult4 = Duration.ofSeconds(1L);
    when(config.getPooledConnectionIdleTimeout()).thenReturn(ofSecondsResult4);
    Duration ofSecondsResult5 = Duration.ofSeconds(1L);
    when(config.getReadTimeout()).thenReturn(ofSecondsResult5);
    Duration ofSecondsResult6 = Duration.ofSeconds(1L);
    when(config.getRequestTimeout()).thenReturn(ofSecondsResult6);
    Duration ofSecondsResult7 = Duration.ofSeconds(1L);
    when(config.getShutdownQuietPeriod()).thenReturn(ofSecondsResult7);
    Duration ofSecondsResult8 = Duration.ofSeconds(1L);
    when(config.getShutdownTimeout()).thenReturn(ofSecondsResult8);
    when(config.getIoExceptionFilters()).thenReturn(new ArrayList<>());
    when(config.getRequestFilters()).thenReturn(new ArrayList<>());
    when(config.getResponseFilters()).thenReturn(new ArrayList<>());
    when(config.getChannelOptions()).thenReturn(new HashMap<>());
    when(config.getThreadFactory()).thenReturn(mock(ThreadFactory.class));
    when(config.getHttpAdditionalChannelInitializer()).thenReturn(mock(Consumer.class));
    when(config.getWsAdditionalChannelInitializer()).thenReturn(mock(Consumer.class));
    when(config.getHashedWheelTimerTickDuration()).thenReturn(81985529216486895L);
    when(config.getResponseBodyPartFactory()).thenReturn(ResponseBodyPartFactory.EAGER);

    Realm.Builder setAlgorithmResult =
        new Realm.Builder().setAlgorithm("https://example.org/example");

    Realm.Builder setCharsetResult = setAlgorithmResult.setCharset(Charset.forName("UTF-8"));

    Realm.Builder setServicePrincipalNameResult =
        setCharsetResult
            .setCustomLoginConfig(new HashMap<>())
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
    Uri uri =
        new Uri(
            "https://example.org/example",
            "https://example.org/example",
            "https://example.org/example",
            8080,
            "https://example.org/example",
            "https://example.org/example",
            "https://example.org/example");
    when(config.getRealm())
        .thenReturn(
            setServicePrincipalNameResult
                .setUri(uri)
                .setUseAbsoluteURI(true)
                .setUseCanonicalHostname(true)
                .setUsePreemptiveAuth(true)
                .build());
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
    DefaultAsyncHttpClientConfig defaultAsyncHttpClientConfig = actualBuilder.build();
    ByteBufAllocator allocator = defaultAsyncHttpClientConfig.getAllocator();
    assertTrue(allocator instanceof AdaptiveByteBufAllocator);
    EventLoopGroup eventLoopGroup = defaultAsyncHttpClientConfig.getEventLoopGroup();
    assertTrue(eventLoopGroup instanceof DefaultEventLoop);
    SslContext sslContext = defaultAsyncHttpClientConfig.getSslContext();
    assertTrue(sslContext instanceof JdkSslClientContext);
    Timer nettyTimer = defaultAsyncHttpClientConfig.getNettyTimer();
    assertTrue(nettyTimer instanceof HashedWheelTimer);
    ChannelPool channelPool = defaultAsyncHttpClientConfig.getChannelPool();
    assertTrue(channelPool instanceof NoopChannelPool);
    CookieStore cookieStore = defaultAsyncHttpClientConfig.getCookieStore();
    assertTrue(cookieStore instanceof ThreadSafeCookieStore);
    assertEquals("${pom.version}", defaultAsyncHttpClientConfig.getAhcVersion());
    assertEquals("https://example.org/example", defaultAsyncHttpClientConfig.getThreadPoolName());
    assertEquals("https://example.org/example", defaultAsyncHttpClientConfig.getUserAgent());
    assertEquals(1, defaultAsyncHttpClientConfig.expiredCookieEvictionDelay());
    assertEquals(1, defaultAsyncHttpClientConfig.getSoLinger());
    assertEquals(1, defaultAsyncHttpClientConfig.getSoRcvBuf());
    assertEquals(1, defaultAsyncHttpClientConfig.getSoSndBuf());
    assertEquals(1, defaultAsyncHttpClientConfig.getEnabledCipherSuites().length);
    assertEquals(1, defaultAsyncHttpClientConfig.getEnabledProtocols().length);
    assertEquals(10, defaultAsyncHttpClientConfig.getAcquireFreeChannelTimeout());
    assertEquals(10, defaultAsyncHttpClientConfig.getHandshakeTimeout());
    assertEquals(10, defaultAsyncHttpClientConfig.getSslSessionTimeout());
    assertEquals(19088743, defaultAsyncHttpClientConfig.getHashedWheelTimerSize());
    assertEquals(3, defaultAsyncHttpClientConfig.getChunkedFileChunkSize());
    assertEquals(3, defaultAsyncHttpClientConfig.getHttpClientCodecInitialBufferSize());
    assertEquals(3, defaultAsyncHttpClientConfig.getHttpClientCodecMaxChunkSize());
    assertEquals(3, defaultAsyncHttpClientConfig.getHttpClientCodecMaxHeaderSize());
    assertEquals(3, defaultAsyncHttpClientConfig.getHttpClientCodecMaxInitialLineLength());
    assertEquals(3, defaultAsyncHttpClientConfig.getIoThreadsCount());
    assertEquals(3, defaultAsyncHttpClientConfig.getMaxConnections());
    assertEquals(3, defaultAsyncHttpClientConfig.getMaxConnectionsPerHost());
    assertEquals(3, defaultAsyncHttpClientConfig.getMaxRedirects());
    assertEquals(3, defaultAsyncHttpClientConfig.getMaxRequestRetry());
    assertEquals(3, defaultAsyncHttpClientConfig.getSslSessionCacheSize());
    assertEquals(3, defaultAsyncHttpClientConfig.getWebSocketMaxBufferSize());
    assertEquals(3, defaultAsyncHttpClientConfig.getWebSocketMaxFrameSize());
    assertEquals(
        81985529216486895L, defaultAsyncHttpClientConfig.getHashedWheelTimerTickDuration());
    assertEquals(
        ResponseBodyPartFactory.EAGER, defaultAsyncHttpClientConfig.getResponseBodyPartFactory());
    assertEquals(NoopChannelPool.INSTANCE, channelPool);
    List<IOExceptionFilter> ioExceptionFilters =
        defaultAsyncHttpClientConfig.getIoExceptionFilters();
    assertTrue(ioExceptionFilters.isEmpty());
    assertTrue(defaultAsyncHttpClientConfig.getChannelOptions().isEmpty());
    assertTrue(defaultAsyncHttpClientConfig.isAggregateWebSocketFrameFragments());
    assertTrue(defaultAsyncHttpClientConfig.isCompressionEnforced());
    assertTrue(defaultAsyncHttpClientConfig.isDisableHttpsEndpointIdentificationAlgorithm());
    assertTrue(defaultAsyncHttpClientConfig.isDisableUrlEncodingForBoundRequests());
    assertTrue(defaultAsyncHttpClientConfig.isDisableZeroCopy());
    assertTrue(defaultAsyncHttpClientConfig.isEnableAutomaticDecompression());
    assertTrue(defaultAsyncHttpClientConfig.isEnableWebSocketCompression());
    assertTrue(defaultAsyncHttpClientConfig.isFilterInsecureCipherSuites());
    assertTrue(defaultAsyncHttpClientConfig.isFollowRedirect());
    assertTrue(defaultAsyncHttpClientConfig.isKeepAlive());
    assertTrue(defaultAsyncHttpClientConfig.isKeepEncodingHeader());
    assertTrue(defaultAsyncHttpClientConfig.isSoKeepAlive());
    assertTrue(defaultAsyncHttpClientConfig.isSoReuseAddress());
    assertTrue(defaultAsyncHttpClientConfig.isStrict302Handling());
    assertTrue(defaultAsyncHttpClientConfig.isTcpNoDelay());
    assertTrue(defaultAsyncHttpClientConfig.isUseInsecureTrustManager());
    assertTrue(defaultAsyncHttpClientConfig.isUseLaxCookieEncoder());
    assertTrue(defaultAsyncHttpClientConfig.isUseNativeTransport());
    assertTrue(defaultAsyncHttpClientConfig.isUseOnlyEpollNativeTransport());
    assertTrue(defaultAsyncHttpClientConfig.isUseOpenSsl());
    assertTrue(defaultAsyncHttpClientConfig.isValidateResponseHeaders());
    assertSame(adaptiveByteBufAllocator, allocator);
    assertSame(defaultEventLoop, eventLoopGroup);
    assertSame(jdkSslClientContext, sslContext);
    assertSame(hashedWheelTimer, nettyTimer);
    assertSame(threadSafeCookieStore, cookieStore);
    assertSame(ioExceptionFilters, defaultAsyncHttpClientConfig.getRequestFilters());
    assertSame(ioExceptionFilters, defaultAsyncHttpClientConfig.getResponseFilters());
    assertSame(ofSecondsResult, defaultAsyncHttpClientConfig.getConnectTimeout());
    assertSame(ofSecondsResult2, defaultAsyncHttpClientConfig.getConnectionPoolCleanerPeriod());
    assertSame(ofSecondsResult3, defaultAsyncHttpClientConfig.getConnectionTtl());
    assertSame(ofSecondsResult4, defaultAsyncHttpClientConfig.getPooledConnectionIdleTimeout());
    assertSame(ofSecondsResult5, defaultAsyncHttpClientConfig.getReadTimeout());
    assertSame(ofSecondsResult6, defaultAsyncHttpClientConfig.getRequestTimeout());
    assertSame(ofSecondsResult7, defaultAsyncHttpClientConfig.getShutdownQuietPeriod());
    assertSame(ofSecondsResult8, defaultAsyncHttpClientConfig.getShutdownTimeout());
  }

  /**
   * Test Builder {@link Builder#removeIOExceptionFilter(IOExceptionFilter)}.
   *
   * <p>Method under test: {@link Builder#removeIOExceptionFilter(IOExceptionFilter)}
   */
  @Test
  @DisplayName("Test Builder removeIOExceptionFilter(IOExceptionFilter)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"Builder Builder.removeIOExceptionFilter(IOExceptionFilter)"})
  void testBuilderRemoveIOExceptionFilter() {
    // Arrange
    Builder configResult = Dsl.config();

    // Act
    Builder actualRemoveIOExceptionFilterResult =
        configResult.removeIOExceptionFilter(mock(IOExceptionFilter.class));

    // Assert
    assertSame(configResult, actualRemoveIOExceptionFilterResult);
  }

  /**
   * Test Builder {@link Builder#removeRequestFilter(RequestFilter)}.
   *
   * <p>Method under test: {@link Builder#removeRequestFilter(RequestFilter)}
   */
  @Test
  @DisplayName("Test Builder removeRequestFilter(RequestFilter)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"Builder Builder.removeRequestFilter(RequestFilter)"})
  void testBuilderRemoveRequestFilter() {
    // Arrange
    Builder configResult = Dsl.config();

    // Act
    Builder actualRemoveRequestFilterResult =
        configResult.removeRequestFilter(mock(RequestFilter.class));

    // Assert
    assertSame(configResult, actualRemoveRequestFilterResult);
  }

  /**
   * Test Builder {@link Builder#removeResponseFilter(ResponseFilter)}.
   *
   * <p>Method under test: {@link Builder#removeResponseFilter(ResponseFilter)}
   */
  @Test
  @DisplayName("Test Builder removeResponseFilter(ResponseFilter)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"Builder Builder.removeResponseFilter(ResponseFilter)"})
  void testBuilderRemoveResponseFilter() {
    // Arrange
    Builder configResult = Dsl.config();

    // Act
    Builder actualRemoveResponseFilterResult =
        configResult.removeResponseFilter(mock(ResponseFilter.class));

    // Assert
    assertSame(configResult, actualRemoveResponseFilterResult);
  }

  /**
   * Test Builder {@link Builder#setProxyServer(Builder)} with {@code proxyServerBuilder}.
   *
   * <p>Method under test: {@link Builder#setProxyServer(ProxyServer.Builder)}
   */
  @Test
  @DisplayName("Test Builder setProxyServer(Builder) with 'proxyServerBuilder'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"Builder Builder.setProxyServer(ProxyServer.Builder)"})
  void testBuilderSetProxyServerWithProxyServerBuilder() {
    // Arrange
    Builder configResult = Dsl.config();

    // Act
    Builder actualSetProxyServerResult =
        configResult.setProxyServer(Dsl.proxyServer("https://example.org/example", 8080));

    // Assert
    assertSame(configResult, actualSetProxyServerResult);
  }

  /**
   * Test Builder {@link Builder#setProxyServer(Builder)} with {@code proxyServerBuilder}.
   *
   * <ul>
   *   <li>Given {@code https://example.org/example}.
   * </ul>
   *
   * <p>Method under test: {@link Builder#setProxyServer(ProxyServer.Builder)}
   */
  @Test
  @DisplayName(
      "Test Builder setProxyServer(Builder) with 'proxyServerBuilder'; given 'https://example.org/example'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"Builder Builder.setProxyServer(ProxyServer.Builder)"})
  void testBuilderSetProxyServerWithProxyServerBuilder_givenHttpsExampleOrgExample() {
    // Arrange
    Builder configResult = Dsl.config();

    ProxyServer.Builder proxyServerBuilder =
        new ProxyServer.Builder("https://example.org/example", 8080);
    proxyServerBuilder.setNonProxyHost("https://example.org/example");
    proxyServerBuilder.setProxyType(ProxyType.HTTP);

    // Act
    Builder actualSetProxyServerResult = configResult.setProxyServer(proxyServerBuilder);

    // Assert
    assertSame(configResult, actualSetProxyServerResult);
  }

  /**
   * Test Builder {@link Builder#setProxyServer(ProxyServer)} with {@code proxyServer}.
   *
   * <ul>
   *   <li>Then return config.
   * </ul>
   *
   * <p>Method under test: {@link Builder#setProxyServer(ProxyServer)}
   */
  @Test
  @DisplayName("Test Builder setProxyServer(ProxyServer) with 'proxyServer'; then return config")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"Builder Builder.setProxyServer(ProxyServer)"})
  void testBuilderSetProxyServerWithProxyServer_thenReturnConfig() {
    // Arrange
    Builder configResult = Dsl.config();

    Realm.Builder setAlgorithmResult =
        new Realm.Builder().setAlgorithm("https://example.org/example");

    Realm.Builder setCharsetResult = setAlgorithmResult.setCharset(Charset.forName("UTF-8"));

    Realm.Builder setServicePrincipalNameResult =
        setCharsetResult
            .setCustomLoginConfig(new HashMap<>())
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
    Uri uri =
        new Uri(
            "https://example.org/example",
            "https://example.org/example",
            "https://example.org/example",
            8080,
            "https://example.org/example",
            "https://example.org/example",
            "https://example.org/example");
    Realm realm =
        setServicePrincipalNameResult
            .setUri(uri)
            .setUseAbsoluteURI(true)
            .setUseCanonicalHostname(true)
            .setUsePreemptiveAuth(true)
            .build();

    // Act
    Builder actualSetProxyServerResult =
        configResult.setProxyServer(
            new ProxyServer(
                "https://example.org/example",
                8080,
                8080,
                realm,
                new ArrayList<>(),
                ProxyType.HTTP));

    // Assert
    assertSame(configResult, actualSetProxyServerResult);
  }

  /**
   * Test Builder {@link Builder#setRealm(Builder)} with {@code realmBuilder}.
   *
   * <p>Method under test: {@link Builder#setRealm(Realm.Builder)}
   */
  @Test
  @DisplayName("Test Builder setRealm(Builder) with 'realmBuilder'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"Builder Builder.setRealm(Realm.Builder)"})
  void testBuilderSetRealmWithRealmBuilder() {
    // Arrange
    Builder configResult = Dsl.config();

    Realm.Builder realmBuilder =
        new Realm.Builder("https://example.org/example", "https://example.org/example");
    realmBuilder.setNonce("https://example.org/example");
    Uri uri =
        new Uri(
            "https://example.org/example",
            "https://example.org/example",
            "https://example.org/example",
            8080,
            "https://example.org/example",
            "https://example.org/example",
            "https://example.org/example");
    realmBuilder.setUri(uri);
    realmBuilder.setScheme(AuthScheme.BASIC);

    // Act and Assert
    Realm realm = configResult.setRealm(realmBuilder).build().getRealm();
    assertEquals("4d08e2ca0bbe3f97b2c090b95d3e62d9", realm.getResponse());
    assertSame(uri, realm.getUri());
  }

  /**
   * Test Builder {@link Builder#setRealm(Builder)} with {@code realmBuilder}.
   *
   * <p>Method under test: {@link Builder#setRealm(Realm.Builder)}
   */
  @Test
  @DisplayName("Test Builder setRealm(Builder) with 'realmBuilder'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"Builder Builder.setRealm(Realm.Builder)"})
  void testBuilderSetRealmWithRealmBuilder2() {
    // Arrange
    Builder configResult = Dsl.config();

    Realm.Builder realmBuilder =
        new Realm.Builder("https://example.org/example", "https://example.org/example");
    realmBuilder.setNonce("https://example.org/example");
    Uri uri =
        new Uri(
            "https://example.org/example",
            "https://example.org/example",
            "https://example.org/example",
            8080,
            "https://example.org/example",
            "",
            "https://example.org/example");
    realmBuilder.setUri(uri);
    realmBuilder.setScheme(AuthScheme.BASIC);

    // Act and Assert
    Realm realm = configResult.setRealm(realmBuilder).build().getRealm();
    assertEquals("ac8d21b2a82bc835e1a6e606bbfca2de", realm.getResponse());
    assertSame(uri, realm.getUri());
  }

  /**
   * Test Builder {@link Builder#setRealm(Builder)} with {@code realmBuilder}.
   *
   * <p>Method under test: {@link Builder#setRealm(Realm.Builder)}
   */
  @Test
  @DisplayName("Test Builder setRealm(Builder) with 'realmBuilder'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"Builder Builder.setRealm(Realm.Builder)"})
  void testBuilderSetRealmWithRealmBuilder3() {
    // Arrange
    Builder configResult = Dsl.config();

    Realm.Builder realmBuilder =
        new Realm.Builder("https://example.org/example", "https://example.org/example");
    realmBuilder.setNonce("https://example.org/example");
    realmBuilder.setUri(null);
    realmBuilder.setScheme(AuthScheme.BASIC);

    // Act and Assert
    Realm realm = configResult.setRealm(realmBuilder).build().getRealm();
    assertEquals("https://example.org/example", realm.getNonce());
    assertNull(realm.getAlgorithm());
    assertNull(realm.getQop());
    assertNull(realm.getUri());
  }

  /**
   * Test Builder {@link Builder#setRealm(Builder)} with {@code realmBuilder}.
   *
   * <p>Method under test: {@link Builder#setRealm(Realm.Builder)}
   */
  @Test
  @DisplayName("Test Builder setRealm(Builder) with 'realmBuilder'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"Builder Builder.setRealm(Realm.Builder)"})
  void testBuilderSetRealmWithRealmBuilder4() {
    // Arrange
    Builder configResult = Dsl.config();

    Realm.Builder realmBuilder =
        new Realm.Builder("https://example.org/example", "https://example.org/example");
    realmBuilder.setUseAbsoluteURI(true);
    realmBuilder.setNonce("https://example.org/example");
    Uri uri =
        new Uri(
            "https://example.org/example",
            "https://example.org/example",
            "https://example.org/example",
            8080,
            "https://example.org/example",
            "https://example.org/example",
            "https://example.org/example");
    realmBuilder.setUri(uri);
    realmBuilder.setScheme(AuthScheme.BASIC);

    // Act and Assert
    Realm realm = configResult.setRealm(realmBuilder).build().getRealm();
    assertEquals("b56797fdd55efbde380e18fc83f14e59", realm.getResponse());
    assertTrue(realm.isUseAbsoluteURI());
    assertSame(uri, realm.getUri());
  }

  /**
   * Test Builder {@link Builder#setRealm(Builder)} with {@code realmBuilder}.
   *
   * <ul>
   *   <li>Given {@code auth}.
   *   <li>Then return build Realm Qop is {@code auth}.
   * </ul>
   *
   * <p>Method under test: {@link Builder#setRealm(Realm.Builder)}
   */
  @Test
  @DisplayName(
      "Test Builder setRealm(Builder) with 'realmBuilder'; given 'auth'; then return build Realm Qop is 'auth'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"Builder Builder.setRealm(Realm.Builder)"})
  void testBuilderSetRealmWithRealmBuilder_givenAuth_thenReturnBuildRealmQopIsAuth() {
    // Arrange
    Builder configResult = Dsl.config();

    Realm.Builder realmBuilder =
        new Realm.Builder("https://example.org/example", "https://example.org/example");
    realmBuilder.setQop("auth");
    realmBuilder.setNonce("https://example.org/example");
    Uri uri =
        new Uri(
            "https://example.org/example",
            "https://example.org/example",
            "https://example.org/example",
            8080,
            "https://example.org/example",
            "https://example.org/example",
            "https://example.org/example");
    realmBuilder.setUri(uri);
    realmBuilder.setScheme(AuthScheme.BASIC);

    // Act and Assert
    Realm realm = configResult.setRealm(realmBuilder).build().getRealm();
    assertEquals("auth", realm.getQop());
    assertSame(uri, realm.getUri());
  }

  /**
   * Test Builder {@link Builder#setRealm(Builder)} with {@code realmBuilder}.
   *
   * <ul>
   *   <li>Given {@code MD5}.
   *   <li>Then return build Realm Algorithm is {@code MD5}.
   * </ul>
   *
   * <p>Method under test: {@link Builder#setRealm(Realm.Builder)}
   */
  @Test
  @DisplayName(
      "Test Builder setRealm(Builder) with 'realmBuilder'; given 'MD5'; then return build Realm Algorithm is 'MD5'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"Builder Builder.setRealm(Realm.Builder)"})
  void testBuilderSetRealmWithRealmBuilder_givenMd5_thenReturnBuildRealmAlgorithmIsMd5() {
    // Arrange
    Builder configResult = Dsl.config();

    Realm.Builder realmBuilder =
        new Realm.Builder("https://example.org/example", "https://example.org/example");
    realmBuilder.setAlgorithm("MD5");
    realmBuilder.setNonce("https://example.org/example");
    Uri uri =
        new Uri(
            "https://example.org/example",
            "https://example.org/example",
            "https://example.org/example",
            8080,
            "https://example.org/example",
            "https://example.org/example",
            "https://example.org/example");
    realmBuilder.setUri(uri);
    realmBuilder.setScheme(AuthScheme.BASIC);

    // Act and Assert
    Realm realm = configResult.setRealm(realmBuilder).build().getRealm();
    assertEquals("4d08e2ca0bbe3f97b2c090b95d3e62d9", realm.getResponse());
    assertEquals("MD5", realm.getAlgorithm());
    assertSame(uri, realm.getUri());
  }

  /**
   * Test Builder {@link Builder#setRealm(Builder)} with {@code realmBuilder}.
   *
   * <ul>
   *   <li>Then return build Realm Algorithm is {@code MD5-sess}.
   * </ul>
   *
   * <p>Method under test: {@link Builder#setRealm(Realm.Builder)}
   */
  @Test
  @DisplayName(
      "Test Builder setRealm(Builder) with 'realmBuilder'; then return build Realm Algorithm is 'MD5-sess'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"Builder Builder.setRealm(Realm.Builder)"})
  void testBuilderSetRealmWithRealmBuilder_thenReturnBuildRealmAlgorithmIsMd5Sess() {
    // Arrange
    Builder configResult = Dsl.config();

    Realm.Builder realmBuilder =
        new Realm.Builder("https://example.org/example", "https://example.org/example");
    realmBuilder.setAlgorithm("MD5-sess");
    realmBuilder.setNonce("https://example.org/example");
    Uri uri =
        new Uri(
            "https://example.org/example",
            "https://example.org/example",
            "https://example.org/example",
            8080,
            "https://example.org/example",
            "https://example.org/example",
            "https://example.org/example");
    realmBuilder.setUri(uri);
    realmBuilder.setScheme(AuthScheme.BASIC);

    // Act and Assert
    Realm realm = configResult.setRealm(realmBuilder).build().getRealm();
    assertEquals("MD5-sess", realm.getAlgorithm());
    assertSame(uri, realm.getUri());
  }

  /**
   * Test Builder {@link Builder#setRealm(Builder)} with {@code realmBuilder}.
   *
   * <ul>
   *   <li>Then return build Realm Nonce is empty string.
   * </ul>
   *
   * <p>Method under test: {@link Builder#setRealm(Realm.Builder)}
   */
  @Test
  @DisplayName(
      "Test Builder setRealm(Builder) with 'realmBuilder'; then return build Realm Nonce is empty string")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"Builder Builder.setRealm(Realm.Builder)"})
  void testBuilderSetRealmWithRealmBuilder_thenReturnBuildRealmNonceIsEmptyString() {
    // Arrange
    Builder configResult = Dsl.config();

    Realm.Builder realmBuilder =
        new Realm.Builder("https://example.org/example", "https://example.org/example");
    realmBuilder.setNonce("");
    Uri uri =
        new Uri(
            "https://example.org/example",
            "https://example.org/example",
            "https://example.org/example",
            8080,
            "https://example.org/example",
            "https://example.org/example",
            "https://example.org/example");
    realmBuilder.setUri(uri);
    realmBuilder.setScheme(AuthScheme.BASIC);

    // Act and Assert
    Realm realm = configResult.setRealm(realmBuilder).build().getRealm();
    assertEquals("", realm.getNonce());
    assertNull(realm.getCnonce());
    assertNull(realm.getResponse());
    assertSame(uri, realm.getUri());
  }

  /**
   * Test Builder {@link Builder#setRealm(Builder)} with {@code realmBuilder}.
   *
   * <ul>
   *   <li>Then return build Realm Nonce is {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link Builder#setRealm(Realm.Builder)}
   */
  @Test
  @DisplayName(
      "Test Builder setRealm(Builder) with 'realmBuilder'; then return build Realm Nonce is 'null'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"Builder Builder.setRealm(Realm.Builder)"})
  void testBuilderSetRealmWithRealmBuilder_thenReturnBuildRealmNonceIsNull() {
    // Arrange
    Builder configResult = Dsl.config();

    Realm.Builder realmBuilder = new Realm.Builder();
    realmBuilder.setScheme(AuthScheme.BASIC);

    // Act and Assert
    Realm realm = configResult.setRealm(realmBuilder).build().getRealm();
    assertNull(realm.getNonce());
    assertNull(realm.getPassword());
    assertNull(realm.getPrincipal());
  }

  /**
   * Test Builder {@link Builder#setRealm(Builder)} with {@code realmBuilder}.
   *
   * <ul>
   *   <li>Then return build Realm OmitQuery.
   * </ul>
   *
   * <p>Method under test: {@link Builder#setRealm(Realm.Builder)}
   */
  @Test
  @DisplayName(
      "Test Builder setRealm(Builder) with 'realmBuilder'; then return build Realm OmitQuery")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"Builder Builder.setRealm(Realm.Builder)"})
  void testBuilderSetRealmWithRealmBuilder_thenReturnBuildRealmOmitQuery() {
    // Arrange
    Builder configResult = Dsl.config();

    Realm.Builder realmBuilder =
        new Realm.Builder("https://example.org/example", "https://example.org/example");
    realmBuilder.setOmitQuery(true);
    realmBuilder.setNonce("https://example.org/example");
    Uri uri =
        new Uri(
            "https://example.org/example",
            "https://example.org/example",
            "https://example.org/example",
            8080,
            "https://example.org/example",
            "https://example.org/example",
            "https://example.org/example");
    realmBuilder.setUri(uri);
    realmBuilder.setScheme(AuthScheme.BASIC);

    // Act and Assert
    Realm realm = configResult.setRealm(realmBuilder).build().getRealm();
    assertEquals("ac8d21b2a82bc835e1a6e606bbfca2de", realm.getResponse());
    assertTrue(realm.isOmitQuery());
    assertSame(uri, realm.getUri());
  }

  /**
   * Test Builder {@link Builder#setRealm(Builder)} with {@code realmBuilder}.
   *
   * <ul>
   *   <li>Then return build Realm Qop is {@code auth-int}.
   * </ul>
   *
   * <p>Method under test: {@link Builder#setRealm(Realm.Builder)}
   */
  @Test
  @DisplayName(
      "Test Builder setRealm(Builder) with 'realmBuilder'; then return build Realm Qop is 'auth-int'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"Builder Builder.setRealm(Realm.Builder)"})
  void testBuilderSetRealmWithRealmBuilder_thenReturnBuildRealmQopIsAuthInt() {
    // Arrange
    Builder configResult = Dsl.config();

    Realm.Builder realmBuilder =
        new Realm.Builder("https://example.org/example", "https://example.org/example");
    realmBuilder.setQop("auth-int");
    realmBuilder.setNonce("https://example.org/example");
    Uri uri =
        new Uri(
            "https://example.org/example",
            "https://example.org/example",
            "https://example.org/example",
            8080,
            "https://example.org/example",
            "https://example.org/example",
            "https://example.org/example");
    realmBuilder.setUri(uri);
    realmBuilder.setScheme(AuthScheme.BASIC);

    // Act and Assert
    Realm realm = configResult.setRealm(realmBuilder).build().getRealm();
    assertEquals("auth-int", realm.getQop());
    assertSame(uri, realm.getUri());
  }

  /**
   * Test Builder {@link Builder#setSslSessionCacheSize(Integer)}.
   *
   * <p>Method under test: {@link Builder#setSslSessionCacheSize(Integer)}
   */
  @Test
  @DisplayName("Test Builder setSslSessionCacheSize(Integer)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
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
   *
   * <p>Method under test: {@link Builder#setSslSessionTimeout(Integer)}
   */
  @Test
  @DisplayName("Test Builder setSslSessionTimeout(Integer)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
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
