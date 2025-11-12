package org.asynchttpclient;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import io.netty.buffer.AdaptiveByteBufAllocator;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.handler.codec.http.DefaultHttpHeaders;
import io.netty.resolver.DefaultNameResolver;
import io.netty.util.HashedWheelTimer;
import java.net.URISyntaxException;
import java.nio.charset.Charset;
import java.time.Duration;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.ThreadFactory;
import javax.net.ssl.SSLException;
import org.asynchttpclient.DefaultAsyncHttpClientConfig.Builder;
import org.asynchttpclient.Realm.AuthScheme;
import org.asynchttpclient.channel.ChannelPool;
import org.asynchttpclient.channel.ChannelPoolPartitioning;
import org.asynchttpclient.channel.ChannelPoolPartitioning.PerHostChannelPoolPartitioning;
import org.asynchttpclient.channel.NoopChannelPool;
import org.asynchttpclient.cookie.ThreadSafeCookieStore;
import org.asynchttpclient.netty.channel.ConnectionSemaphoreFactory;
import org.asynchttpclient.netty.channel.DefaultChannelPool;
import org.asynchttpclient.netty.channel.NoopConnectionSemaphore;
import org.asynchttpclient.proxy.ProxyServer;
import org.asynchttpclient.proxy.ProxyType;
import org.asynchttpclient.uri.Uri;
import org.asynchttpclient.util.UriEncoder;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

class DslDiffblueTest {
  /**
   * Test {@link Dsl#asyncHttpClient()}.
   *
   * <p>Method under test: {@link Dsl#asyncHttpClient()}
   */
  @Test
  @DisplayName("Test asyncHttpClient()")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"AsyncHttpClient Dsl.asyncHttpClient()"})
  void testAsyncHttpClient() {
    // Arrange and Act
    AsyncHttpClient actualAsyncHttpClientResult = Dsl.asyncHttpClient();

    // Assert
    assertTrue(
        ((DefaultAsyncHttpClient) actualAsyncHttpClientResult).getEventLoopGroup()
            instanceof NioEventLoopGroup);
    assertTrue(actualAsyncHttpClientResult instanceof DefaultAsyncHttpClient);
    assertTrue(actualAsyncHttpClientResult.getConfig() instanceof DefaultAsyncHttpClientConfig);
    assertTrue(
        ((DefaultAsyncHttpClient) actualAsyncHttpClientResult).getChannelPool()
            instanceof DefaultChannelPool);
    assertFalse(actualAsyncHttpClientResult.isClosed());
  }

  /**
   * Test {@link Dsl#asyncHttpClient(Builder)} with {@code configBuilder}.
   *
   * <p>Method under test: {@link Dsl#asyncHttpClient(Builder)}
   */
  @Test
  @DisplayName("Test asyncHttpClient(Builder) with 'configBuilder'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"AsyncHttpClient Dsl.asyncHttpClient(Builder)"})
  void testAsyncHttpClientWithConfigBuilder() {
    // Arrange and Act
    AsyncHttpClient actualAsyncHttpClientResult = Dsl.asyncHttpClient(Dsl.config());

    // Assert
    assertTrue(
        ((DefaultAsyncHttpClient) actualAsyncHttpClientResult).getEventLoopGroup()
            instanceof NioEventLoopGroup);
    assertTrue(actualAsyncHttpClientResult instanceof DefaultAsyncHttpClient);
    assertTrue(actualAsyncHttpClientResult.getConfig() instanceof DefaultAsyncHttpClientConfig);
    assertTrue(
        ((DefaultAsyncHttpClient) actualAsyncHttpClientResult).getChannelPool()
            instanceof DefaultChannelPool);
    assertFalse(actualAsyncHttpClientResult.isClosed());
  }

  /**
   * Test {@link Dsl#asyncHttpClient(AsyncHttpClientConfig)} with {@code config}.
   *
   * <ul>
   *   <li>Given {@link ThreadSafeCookieStore} (default constructor) incrementAndGet.
   * </ul>
   *
   * <p>Method under test: {@link Dsl#asyncHttpClient(AsyncHttpClientConfig)}
   */
  @Test
  @DisplayName(
      "Test asyncHttpClient(AsyncHttpClientConfig) with 'config'; given ThreadSafeCookieStore (default constructor) incrementAndGet")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"AsyncHttpClient Dsl.asyncHttpClient(AsyncHttpClientConfig)"})
  void testAsyncHttpClientWithConfig_givenThreadSafeCookieStoreIncrementAndGet()
      throws SSLException {
    // Arrange
    SslEngineFactory sslEngineFactory = mock(SslEngineFactory.class);
    doNothing().when(sslEngineFactory).init(Mockito.<AsyncHttpClientConfig>any());

    ThreadSafeCookieStore threadSafeCookieStore = new ThreadSafeCookieStore();
    threadSafeCookieStore.incrementAndGet();

    ConnectionSemaphoreFactory connectionSemaphoreFactory = mock(ConnectionSemaphoreFactory.class);
    when(connectionSemaphoreFactory.newConnectionSemaphore(Mockito.<AsyncHttpClientConfig>any()))
        .thenReturn(new NoopConnectionSemaphore());

    AsyncHttpClientConfig config = mock(AsyncHttpClientConfig.class);
    when(config.isSoKeepAlive()).thenReturn(true);
    when(config.isSoReuseAddress()).thenReturn(true);
    when(config.isTcpNoDelay()).thenReturn(true);
    when(config.isUseLaxCookieEncoder()).thenReturn(true);
    when(config.getHandshakeTimeout()).thenReturn(10);
    when(config.getMaxRedirects()).thenReturn(3);
    when(config.getSoLinger()).thenReturn(1);
    when(config.getSoRcvBuf()).thenReturn(1);
    when(config.getSoSndBuf()).thenReturn(1);
    when(config.getAllocator()).thenReturn(new AdaptiveByteBufAllocator());
    NioEventLoopGroup nioEventLoopGroup = new NioEventLoopGroup();
    when(config.getEventLoopGroup()).thenReturn(nioEventLoopGroup);
    when(config.getConnectTimeout()).thenReturn(Duration.ofSeconds(1L));
    when(config.getIoExceptionFilters()).thenReturn(new ArrayList<>());
    when(config.getResponseFilters()).thenReturn(new ArrayList<>());
    when(config.getChannelOptions()).thenReturn(new HashMap<>());
    when(config.getSslEngineFactory()).thenReturn(sslEngineFactory);
    when(config.getChannelPool()).thenReturn(NoopChannelPool.INSTANCE);
    when(config.getCookieStore()).thenReturn(threadSafeCookieStore);
    when(config.getConnectionSemaphoreFactory()).thenReturn(connectionSemaphoreFactory);
    when(config.getNettyTimer()).thenReturn(new HashedWheelTimer());
    when(config.getRequestFilters()).thenReturn(new ArrayList<>());
    when(config.getThreadFactory()).thenReturn(mock(ThreadFactory.class));

    // Act
    AsyncHttpClient actualAsyncHttpClientResult = Dsl.asyncHttpClient(config);

    // Assert
    verify(config, atLeast(1)).getAllocator();
    verify(config, atLeast(1)).getChannelOptions();
    verify(config).getChannelPool();
    verify(config, atLeast(1)).getConnectTimeout();
    verify(config, atLeast(1)).getConnectionSemaphoreFactory();
    verify(config, atLeast(1)).getCookieStore();
    verify(config, atLeast(1)).getEventLoopGroup();
    verify(config).getHandshakeTimeout();
    verify(config, atLeast(1)).getIoExceptionFilters();
    verify(config, atLeast(1)).getMaxRedirects();
    verify(config).getNettyTimer();
    verify(config).getRequestFilters();
    verify(config, atLeast(1)).getResponseFilters();
    verify(config, atLeast(1)).getSoLinger();
    verify(config, atLeast(1)).getSoRcvBuf();
    verify(config, atLeast(1)).getSoSndBuf();
    verify(config, atLeast(1)).getSslEngineFactory();
    verify(config, atLeast(1)).getThreadFactory();
    verify(config, atLeast(1)).isSoKeepAlive();
    verify(config, atLeast(1)).isSoReuseAddress();
    verify(config, atLeast(1)).isTcpNoDelay();
    verify(config, atLeast(1)).isUseLaxCookieEncoder();
    verify(sslEngineFactory).init(isA(AsyncHttpClientConfig.class));
    verify(connectionSemaphoreFactory).newConnectionSemaphore(isA(AsyncHttpClientConfig.class));
    EventLoopGroup eventLoopGroup =
        ((DefaultAsyncHttpClient) actualAsyncHttpClientResult).getEventLoopGroup();
    assertTrue(eventLoopGroup instanceof NioEventLoopGroup);
    assertTrue(actualAsyncHttpClientResult instanceof DefaultAsyncHttpClient);
    ChannelPool channelPool =
        ((DefaultAsyncHttpClient) actualAsyncHttpClientResult).getChannelPool();
    assertTrue(channelPool instanceof NoopChannelPool);
    assertEquals(NoopChannelPool.INSTANCE, channelPool);
    assertFalse(actualAsyncHttpClientResult.isClosed());
    assertSame(nioEventLoopGroup, eventLoopGroup);
    assertSame(config, actualAsyncHttpClientResult.getConfig());
  }

  /**
   * Test {@link Dsl#asyncHttpClient(AsyncHttpClientConfig)} with {@code config}.
   *
   * <ul>
   *   <li>Then calls {@link AsyncHttpClientConfig#expiredCookieEvictionDelay()}.
   * </ul>
   *
   * <p>Method under test: {@link Dsl#asyncHttpClient(AsyncHttpClientConfig)}
   */
  @Test
  @DisplayName(
      "Test asyncHttpClient(AsyncHttpClientConfig) with 'config'; then calls expiredCookieEvictionDelay()")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"AsyncHttpClient Dsl.asyncHttpClient(AsyncHttpClientConfig)"})
  void testAsyncHttpClientWithConfig_thenCallsExpiredCookieEvictionDelay() throws SSLException {
    // Arrange
    SslEngineFactory sslEngineFactory = mock(SslEngineFactory.class);
    doNothing().when(sslEngineFactory).init(Mockito.<AsyncHttpClientConfig>any());

    ConnectionSemaphoreFactory connectionSemaphoreFactory = mock(ConnectionSemaphoreFactory.class);
    when(connectionSemaphoreFactory.newConnectionSemaphore(Mockito.<AsyncHttpClientConfig>any()))
        .thenReturn(new NoopConnectionSemaphore());

    AsyncHttpClientConfig config = mock(AsyncHttpClientConfig.class);
    when(config.expiredCookieEvictionDelay()).thenReturn(1);
    when(config.isSoKeepAlive()).thenReturn(true);
    when(config.isSoReuseAddress()).thenReturn(true);
    when(config.isTcpNoDelay()).thenReturn(true);
    when(config.isUseLaxCookieEncoder()).thenReturn(true);
    when(config.getHandshakeTimeout()).thenReturn(10);
    when(config.getMaxRedirects()).thenReturn(3);
    when(config.getSoLinger()).thenReturn(1);
    when(config.getSoRcvBuf()).thenReturn(1);
    when(config.getSoSndBuf()).thenReturn(1);
    when(config.getAllocator()).thenReturn(new AdaptiveByteBufAllocator());
    NioEventLoopGroup nioEventLoopGroup = new NioEventLoopGroup();
    when(config.getEventLoopGroup()).thenReturn(nioEventLoopGroup);
    when(config.getConnectTimeout()).thenReturn(Duration.ofSeconds(1L));
    when(config.getIoExceptionFilters()).thenReturn(new ArrayList<>());
    when(config.getResponseFilters()).thenReturn(new ArrayList<>());
    when(config.getChannelOptions()).thenReturn(new HashMap<>());
    when(config.getSslEngineFactory()).thenReturn(sslEngineFactory);
    when(config.getChannelPool()).thenReturn(NoopChannelPool.INSTANCE);
    when(config.getCookieStore()).thenReturn(new ThreadSafeCookieStore());
    when(config.getConnectionSemaphoreFactory()).thenReturn(connectionSemaphoreFactory);
    when(config.getNettyTimer()).thenReturn(new HashedWheelTimer());
    when(config.getRequestFilters()).thenReturn(new ArrayList<>());
    when(config.getThreadFactory()).thenReturn(mock(ThreadFactory.class));

    // Act
    AsyncHttpClient actualAsyncHttpClientResult = Dsl.asyncHttpClient(config);

    // Assert
    verify(config, atLeast(1)).expiredCookieEvictionDelay();
    verify(config, atLeast(1)).getAllocator();
    verify(config, atLeast(1)).getChannelOptions();
    verify(config).getChannelPool();
    verify(config, atLeast(1)).getConnectTimeout();
    verify(config, atLeast(1)).getConnectionSemaphoreFactory();
    verify(config, atLeast(1)).getCookieStore();
    verify(config, atLeast(1)).getEventLoopGroup();
    verify(config).getHandshakeTimeout();
    verify(config, atLeast(1)).getIoExceptionFilters();
    verify(config, atLeast(1)).getMaxRedirects();
    verify(config).getNettyTimer();
    verify(config).getRequestFilters();
    verify(config, atLeast(1)).getResponseFilters();
    verify(config, atLeast(1)).getSoLinger();
    verify(config, atLeast(1)).getSoRcvBuf();
    verify(config, atLeast(1)).getSoSndBuf();
    verify(config, atLeast(1)).getSslEngineFactory();
    verify(config, atLeast(1)).getThreadFactory();
    verify(config, atLeast(1)).isSoKeepAlive();
    verify(config, atLeast(1)).isSoReuseAddress();
    verify(config, atLeast(1)).isTcpNoDelay();
    verify(config, atLeast(1)).isUseLaxCookieEncoder();
    verify(sslEngineFactory).init(isA(AsyncHttpClientConfig.class));
    verify(connectionSemaphoreFactory).newConnectionSemaphore(isA(AsyncHttpClientConfig.class));
    EventLoopGroup eventLoopGroup =
        ((DefaultAsyncHttpClient) actualAsyncHttpClientResult).getEventLoopGroup();
    assertTrue(eventLoopGroup instanceof NioEventLoopGroup);
    assertTrue(actualAsyncHttpClientResult instanceof DefaultAsyncHttpClient);
    ChannelPool channelPool =
        ((DefaultAsyncHttpClient) actualAsyncHttpClientResult).getChannelPool();
    assertTrue(channelPool instanceof NoopChannelPool);
    assertEquals(NoopChannelPool.INSTANCE, channelPool);
    assertFalse(actualAsyncHttpClientResult.isClosed());
    assertSame(nioEventLoopGroup, eventLoopGroup);
    assertSame(config, actualAsyncHttpClientResult.getConfig());
  }

  /**
   * Test {@link Dsl#asyncHttpClient(AsyncHttpClientConfig)} with {@code config}.
   *
   * <ul>
   *   <li>When {@link AsyncHttpClientConfig} {@link AsyncHttpClientConfig#getCookieStore()} return
   *       {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link Dsl#asyncHttpClient(AsyncHttpClientConfig)}
   */
  @Test
  @DisplayName(
      "Test asyncHttpClient(AsyncHttpClientConfig) with 'config'; when AsyncHttpClientConfig getCookieStore() return 'null'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"AsyncHttpClient Dsl.asyncHttpClient(AsyncHttpClientConfig)"})
  void testAsyncHttpClientWithConfig_whenAsyncHttpClientConfigGetCookieStoreReturnNull()
      throws SSLException {
    // Arrange
    SslEngineFactory sslEngineFactory = mock(SslEngineFactory.class);
    doNothing().when(sslEngineFactory).init(Mockito.<AsyncHttpClientConfig>any());

    ConnectionSemaphoreFactory connectionSemaphoreFactory = mock(ConnectionSemaphoreFactory.class);
    when(connectionSemaphoreFactory.newConnectionSemaphore(Mockito.<AsyncHttpClientConfig>any()))
        .thenReturn(new NoopConnectionSemaphore());

    AsyncHttpClientConfig config = mock(AsyncHttpClientConfig.class);
    when(config.isSoKeepAlive()).thenReturn(true);
    when(config.isSoReuseAddress()).thenReturn(true);
    when(config.isTcpNoDelay()).thenReturn(true);
    when(config.isUseLaxCookieEncoder()).thenReturn(true);
    when(config.getHandshakeTimeout()).thenReturn(10);
    when(config.getMaxRedirects()).thenReturn(3);
    when(config.getSoLinger()).thenReturn(1);
    when(config.getSoRcvBuf()).thenReturn(1);
    when(config.getSoSndBuf()).thenReturn(1);
    when(config.getAllocator()).thenReturn(new AdaptiveByteBufAllocator());
    NioEventLoopGroup nioEventLoopGroup = new NioEventLoopGroup();
    when(config.getEventLoopGroup()).thenReturn(nioEventLoopGroup);
    when(config.getConnectTimeout()).thenReturn(Duration.ofSeconds(1L));
    when(config.getIoExceptionFilters()).thenReturn(new ArrayList<>());
    when(config.getResponseFilters()).thenReturn(new ArrayList<>());
    when(config.getChannelOptions()).thenReturn(new HashMap<>());
    when(config.getSslEngineFactory()).thenReturn(sslEngineFactory);
    when(config.getChannelPool()).thenReturn(NoopChannelPool.INSTANCE);
    when(config.getCookieStore()).thenReturn(null);
    when(config.getConnectionSemaphoreFactory()).thenReturn(connectionSemaphoreFactory);
    when(config.getNettyTimer()).thenReturn(new HashedWheelTimer());
    when(config.getRequestFilters()).thenReturn(new ArrayList<>());
    when(config.getThreadFactory()).thenReturn(mock(ThreadFactory.class));

    // Act
    AsyncHttpClient actualAsyncHttpClientResult = Dsl.asyncHttpClient(config);

    // Assert
    verify(config, atLeast(1)).getAllocator();
    verify(config, atLeast(1)).getChannelOptions();
    verify(config).getChannelPool();
    verify(config, atLeast(1)).getConnectTimeout();
    verify(config, atLeast(1)).getConnectionSemaphoreFactory();
    verify(config).getCookieStore();
    verify(config, atLeast(1)).getEventLoopGroup();
    verify(config).getHandshakeTimeout();
    verify(config, atLeast(1)).getIoExceptionFilters();
    verify(config, atLeast(1)).getMaxRedirects();
    verify(config).getNettyTimer();
    verify(config).getRequestFilters();
    verify(config, atLeast(1)).getResponseFilters();
    verify(config, atLeast(1)).getSoLinger();
    verify(config, atLeast(1)).getSoRcvBuf();
    verify(config, atLeast(1)).getSoSndBuf();
    verify(config, atLeast(1)).getSslEngineFactory();
    verify(config, atLeast(1)).getThreadFactory();
    verify(config, atLeast(1)).isSoKeepAlive();
    verify(config, atLeast(1)).isSoReuseAddress();
    verify(config, atLeast(1)).isTcpNoDelay();
    verify(config, atLeast(1)).isUseLaxCookieEncoder();
    verify(sslEngineFactory).init(isA(AsyncHttpClientConfig.class));
    verify(connectionSemaphoreFactory).newConnectionSemaphore(isA(AsyncHttpClientConfig.class));
    EventLoopGroup eventLoopGroup =
        ((DefaultAsyncHttpClient) actualAsyncHttpClientResult).getEventLoopGroup();
    assertTrue(eventLoopGroup instanceof NioEventLoopGroup);
    assertTrue(actualAsyncHttpClientResult instanceof DefaultAsyncHttpClient);
    ChannelPool channelPool =
        ((DefaultAsyncHttpClient) actualAsyncHttpClientResult).getChannelPool();
    assertTrue(channelPool instanceof NoopChannelPool);
    assertEquals(NoopChannelPool.INSTANCE, channelPool);
    assertFalse(actualAsyncHttpClientResult.isClosed());
    assertSame(nioEventLoopGroup, eventLoopGroup);
    assertSame(config, actualAsyncHttpClientResult.getConfig());
  }

  /**
   * Test {@link Dsl#get(String)}.
   *
   * <ul>
   *   <li>When {@code https://example.org/example}.
   *   <li>Then {@link RequestBuilderBase#headers} return {@link DefaultHttpHeaders}.
   * </ul>
   *
   * <p>Method under test: {@link Dsl#get(String)}
   */
  @Test
  @DisplayName(
      "Test get(String); when 'https://example.org/example'; then headers return DefaultHttpHeaders")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"RequestBuilder Dsl.get(String)"})
  void testGet_whenHttpsExampleOrgExample_thenHeadersReturnDefaultHttpHeaders() {
    // Arrange and Act
    RequestBuilder actualGetResult = Dsl.get("https://example.org/example");

    // Assert
    assertTrue(actualGetResult.headers instanceof DefaultHttpHeaders);
    assertTrue(actualGetResult.nameResolver instanceof DefaultNameResolver);
    ChannelPoolPartitioning channelPoolPartitioning = actualGetResult.channelPoolPartitioning;
    assertTrue(channelPoolPartitioning instanceof PerHostChannelPoolPartitioning);
    assertEquals("GET", actualGetResult.method);
    assertNull(actualGetResult.byteData);
    assertNull(actualGetResult.byteBufData);
    assertNull(actualGetResult.file);
    assertNull(actualGetResult.streamData);
    assertNull(actualGetResult.followRedirect);
    assertNull(actualGetResult.stringData);
    assertNull(actualGetResult.virtualHost);
    assertNull(actualGetResult.address);
    assertNull(actualGetResult.localAddress);
    assertNull(actualGetResult.byteBufferData);
    assertNull(actualGetResult.charset);
    assertNull(actualGetResult.readTimeout);
    assertNull(actualGetResult.requestTimeout);
    assertNull(actualGetResult.cookies);
    assertNull(actualGetResult.compositeByteData);
    assertNull(actualGetResult.formParams);
    assertNull(actualGetResult.queryParams);
    assertNull(actualGetResult.bodyParts);
    assertNull(actualGetResult.realm);
    assertNull(actualGetResult.signatureCalculator);
    assertNull(actualGetResult.proxyServer);
    assertNull(actualGetResult.bodyGenerator);
    assertEquals(0L, actualGetResult.rangeOffset);
    assertEquals(PerHostChannelPoolPartitioning.INSTANCE, channelPoolPartitioning);
    assertEquals(UriEncoder.FIXING, actualGetResult.uriEncoder);
  }

  /**
   * Test {@link Dsl#put(String)}.
   *
   * <ul>
   *   <li>When {@code https://example.org/example}.
   *   <li>Then {@link RequestBuilderBase#headers} return {@link DefaultHttpHeaders}.
   * </ul>
   *
   * <p>Method under test: {@link Dsl#put(String)}
   */
  @Test
  @DisplayName(
      "Test put(String); when 'https://example.org/example'; then headers return DefaultHttpHeaders")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"RequestBuilder Dsl.put(String)"})
  void testPut_whenHttpsExampleOrgExample_thenHeadersReturnDefaultHttpHeaders() {
    // Arrange and Act
    RequestBuilder actualPutResult = Dsl.put("https://example.org/example");

    // Assert
    assertTrue(actualPutResult.headers instanceof DefaultHttpHeaders);
    assertTrue(actualPutResult.nameResolver instanceof DefaultNameResolver);
    ChannelPoolPartitioning channelPoolPartitioning = actualPutResult.channelPoolPartitioning;
    assertTrue(channelPoolPartitioning instanceof PerHostChannelPoolPartitioning);
    assertEquals("PUT", actualPutResult.method);
    assertNull(actualPutResult.byteData);
    assertNull(actualPutResult.byteBufData);
    assertNull(actualPutResult.file);
    assertNull(actualPutResult.streamData);
    assertNull(actualPutResult.followRedirect);
    assertNull(actualPutResult.stringData);
    assertNull(actualPutResult.virtualHost);
    assertNull(actualPutResult.address);
    assertNull(actualPutResult.localAddress);
    assertNull(actualPutResult.byteBufferData);
    assertNull(actualPutResult.charset);
    assertNull(actualPutResult.readTimeout);
    assertNull(actualPutResult.requestTimeout);
    assertNull(actualPutResult.cookies);
    assertNull(actualPutResult.compositeByteData);
    assertNull(actualPutResult.formParams);
    assertNull(actualPutResult.queryParams);
    assertNull(actualPutResult.bodyParts);
    assertNull(actualPutResult.realm);
    assertNull(actualPutResult.signatureCalculator);
    assertNull(actualPutResult.proxyServer);
    assertNull(actualPutResult.bodyGenerator);
    assertEquals(0L, actualPutResult.rangeOffset);
    assertEquals(PerHostChannelPoolPartitioning.INSTANCE, channelPoolPartitioning);
    assertEquals(UriEncoder.FIXING, actualPutResult.uriEncoder);
  }

  /**
   * Test {@link Dsl#post(String)}.
   *
   * <ul>
   *   <li>When {@code https://example.org/example}.
   *   <li>Then {@link RequestBuilderBase#headers} return {@link DefaultHttpHeaders}.
   * </ul>
   *
   * <p>Method under test: {@link Dsl#post(String)}
   */
  @Test
  @DisplayName(
      "Test post(String); when 'https://example.org/example'; then headers return DefaultHttpHeaders")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"RequestBuilder Dsl.post(String)"})
  void testPost_whenHttpsExampleOrgExample_thenHeadersReturnDefaultHttpHeaders() {
    // Arrange and Act
    RequestBuilder actualPostResult = Dsl.post("https://example.org/example");

    // Assert
    assertTrue(actualPostResult.headers instanceof DefaultHttpHeaders);
    assertTrue(actualPostResult.nameResolver instanceof DefaultNameResolver);
    ChannelPoolPartitioning channelPoolPartitioning = actualPostResult.channelPoolPartitioning;
    assertTrue(channelPoolPartitioning instanceof PerHostChannelPoolPartitioning);
    assertEquals("POST", actualPostResult.method);
    assertNull(actualPostResult.byteData);
    assertNull(actualPostResult.byteBufData);
    assertNull(actualPostResult.file);
    assertNull(actualPostResult.streamData);
    assertNull(actualPostResult.followRedirect);
    assertNull(actualPostResult.stringData);
    assertNull(actualPostResult.virtualHost);
    assertNull(actualPostResult.address);
    assertNull(actualPostResult.localAddress);
    assertNull(actualPostResult.byteBufferData);
    assertNull(actualPostResult.charset);
    assertNull(actualPostResult.readTimeout);
    assertNull(actualPostResult.requestTimeout);
    assertNull(actualPostResult.cookies);
    assertNull(actualPostResult.compositeByteData);
    assertNull(actualPostResult.formParams);
    assertNull(actualPostResult.queryParams);
    assertNull(actualPostResult.bodyParts);
    assertNull(actualPostResult.realm);
    assertNull(actualPostResult.signatureCalculator);
    assertNull(actualPostResult.proxyServer);
    assertNull(actualPostResult.bodyGenerator);
    assertEquals(0L, actualPostResult.rangeOffset);
    assertEquals(PerHostChannelPoolPartitioning.INSTANCE, channelPoolPartitioning);
    assertEquals(UriEncoder.FIXING, actualPostResult.uriEncoder);
  }

  /**
   * Test {@link Dsl#delete(String)}.
   *
   * <p>Method under test: {@link Dsl#delete(String)}
   */
  @Test
  @DisplayName("Test delete(String)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"RequestBuilder Dsl.delete(String)"})
  void testDelete() {
    // Arrange and Act
    RequestBuilder actualDeleteResult =
        Dsl.delete(
            "https://example.org/examplehttps://example.org/examplehttps://example.org/example");

    // Assert
    assertTrue(actualDeleteResult.headers instanceof DefaultHttpHeaders);
    assertTrue(actualDeleteResult.nameResolver instanceof DefaultNameResolver);
    ChannelPoolPartitioning channelPoolPartitioning = actualDeleteResult.channelPoolPartitioning;
    assertTrue(channelPoolPartitioning instanceof PerHostChannelPoolPartitioning);
    assertEquals("DELETE", actualDeleteResult.method);
    assertNull(actualDeleteResult.byteData);
    assertNull(actualDeleteResult.byteBufData);
    assertNull(actualDeleteResult.file);
    assertNull(actualDeleteResult.streamData);
    assertNull(actualDeleteResult.followRedirect);
    assertNull(actualDeleteResult.stringData);
    assertNull(actualDeleteResult.virtualHost);
    assertNull(actualDeleteResult.address);
    assertNull(actualDeleteResult.localAddress);
    assertNull(actualDeleteResult.byteBufferData);
    assertNull(actualDeleteResult.charset);
    assertNull(actualDeleteResult.readTimeout);
    assertNull(actualDeleteResult.requestTimeout);
    assertNull(actualDeleteResult.cookies);
    assertNull(actualDeleteResult.compositeByteData);
    assertNull(actualDeleteResult.formParams);
    assertNull(actualDeleteResult.queryParams);
    assertNull(actualDeleteResult.bodyParts);
    assertNull(actualDeleteResult.realm);
    assertNull(actualDeleteResult.signatureCalculator);
    assertNull(actualDeleteResult.proxyServer);
    assertNull(actualDeleteResult.bodyGenerator);
    assertEquals(0L, actualDeleteResult.rangeOffset);
    assertEquals(PerHostChannelPoolPartitioning.INSTANCE, channelPoolPartitioning);
    assertEquals(UriEncoder.FIXING, actualDeleteResult.uriEncoder);
  }

  /**
   * Test {@link Dsl#delete(String)}.
   *
   * <ul>
   *   <li>When {@code https://example.org/example}.
   *   <li>Then {@link RequestBuilderBase#headers} return {@link DefaultHttpHeaders}.
   * </ul>
   *
   * <p>Method under test: {@link Dsl#delete(String)}
   */
  @Test
  @DisplayName(
      "Test delete(String); when 'https://example.org/example'; then headers return DefaultHttpHeaders")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"RequestBuilder Dsl.delete(String)"})
  void testDelete_whenHttpsExampleOrgExample_thenHeadersReturnDefaultHttpHeaders() {
    // Arrange and Act
    RequestBuilder actualDeleteResult = Dsl.delete("https://example.org/example");

    // Assert
    assertTrue(actualDeleteResult.headers instanceof DefaultHttpHeaders);
    assertTrue(actualDeleteResult.nameResolver instanceof DefaultNameResolver);
    ChannelPoolPartitioning channelPoolPartitioning = actualDeleteResult.channelPoolPartitioning;
    assertTrue(channelPoolPartitioning instanceof PerHostChannelPoolPartitioning);
    assertEquals("DELETE", actualDeleteResult.method);
    assertNull(actualDeleteResult.byteData);
    assertNull(actualDeleteResult.byteBufData);
    assertNull(actualDeleteResult.file);
    assertNull(actualDeleteResult.streamData);
    assertNull(actualDeleteResult.followRedirect);
    assertNull(actualDeleteResult.stringData);
    assertNull(actualDeleteResult.virtualHost);
    assertNull(actualDeleteResult.address);
    assertNull(actualDeleteResult.localAddress);
    assertNull(actualDeleteResult.byteBufferData);
    assertNull(actualDeleteResult.charset);
    assertNull(actualDeleteResult.readTimeout);
    assertNull(actualDeleteResult.requestTimeout);
    assertNull(actualDeleteResult.cookies);
    assertNull(actualDeleteResult.compositeByteData);
    assertNull(actualDeleteResult.formParams);
    assertNull(actualDeleteResult.queryParams);
    assertNull(actualDeleteResult.bodyParts);
    assertNull(actualDeleteResult.realm);
    assertNull(actualDeleteResult.signatureCalculator);
    assertNull(actualDeleteResult.proxyServer);
    assertNull(actualDeleteResult.bodyGenerator);
    assertEquals(0L, actualDeleteResult.rangeOffset);
    assertEquals(PerHostChannelPoolPartitioning.INSTANCE, channelPoolPartitioning);
    assertEquals(UriEncoder.FIXING, actualDeleteResult.uriEncoder);
  }

  /**
   * Test {@link Dsl#head(String)}.
   *
   * <ul>
   *   <li>Then return {@link RequestBuilderBase#uri} NonEmptyPath is {@code /examplewss}.
   * </ul>
   *
   * <p>Method under test: {@link Dsl#head(String)}
   */
  @Test
  @DisplayName("Test head(String); then return uri NonEmptyPath is '/examplewss'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"RequestBuilder Dsl.head(String)"})
  void testHead_thenReturnUriNonEmptyPathIsExamplewss() throws URISyntaxException {
    // Arrange, Act and Assert
    Uri uri = Dsl.head("originalUrlhttps://example.org/examplewss").uri;
    assertEquals("/examplewss", uri.getNonEmptyPath());
    assertEquals("/examplewss", uri.getPath());
    assertEquals("example.org:80", uri.getAuthority());
    assertEquals("originalurlhttps", uri.getScheme());
    assertEquals("originalurlhttps://example.org/examplewss", uri.toJavaNetURI().toString());
    assertEquals("originalurlhttps://example.org:80", uri.getBaseUrl());
    assertEquals(80, uri.getExplicitPort());
    assertEquals(80, uri.getSchemeDefaultPort());
    assertFalse(uri.isSecured());
  }

  /**
   * Test {@link Dsl#head(String)}.
   *
   * <ul>
   *   <li>When {@code https://example.org/example}.
   *   <li>Then return {@link RequestBuilderBase#uri} NonEmptyPath is {@code /example}.
   * </ul>
   *
   * <p>Method under test: {@link Dsl#head(String)}
   */
  @Test
  @DisplayName(
      "Test head(String); when 'https://example.org/example'; then return uri NonEmptyPath is '/example'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"RequestBuilder Dsl.head(String)"})
  void testHead_whenHttpsExampleOrgExample_thenReturnUriNonEmptyPathIsExample()
      throws URISyntaxException {
    // Arrange, Act and Assert
    Uri uri = Dsl.head("https://example.org/example").uri;
    assertEquals("/example", uri.getNonEmptyPath());
    assertEquals("/example", uri.getPath());
    assertEquals("example.org:443", uri.getAuthority());
    assertEquals("https", uri.getScheme());
    assertEquals("https://example.org/example", uri.toJavaNetURI().toString());
    assertEquals("https://example.org:443", uri.getBaseUrl());
    assertEquals(443, uri.getExplicitPort());
    assertEquals(443, uri.getSchemeDefaultPort());
    assertTrue(uri.isSecured());
  }

  /**
   * Test {@link Dsl#options(String)}.
   *
   * <ul>
   *   <li>When {@code https://example.org/example}.
   *   <li>Then {@link RequestBuilderBase#headers} return {@link DefaultHttpHeaders}.
   * </ul>
   *
   * <p>Method under test: {@link Dsl#options(String)}
   */
  @Test
  @DisplayName(
      "Test options(String); when 'https://example.org/example'; then headers return DefaultHttpHeaders")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"RequestBuilder Dsl.options(String)"})
  void testOptions_whenHttpsExampleOrgExample_thenHeadersReturnDefaultHttpHeaders() {
    // Arrange and Act
    RequestBuilder actualOptionsResult = Dsl.options("https://example.org/example");

    // Assert
    assertTrue(actualOptionsResult.headers instanceof DefaultHttpHeaders);
    assertTrue(actualOptionsResult.nameResolver instanceof DefaultNameResolver);
    ChannelPoolPartitioning channelPoolPartitioning = actualOptionsResult.channelPoolPartitioning;
    assertTrue(channelPoolPartitioning instanceof PerHostChannelPoolPartitioning);
    assertEquals("OPTIONS", actualOptionsResult.method);
    assertNull(actualOptionsResult.byteData);
    assertNull(actualOptionsResult.byteBufData);
    assertNull(actualOptionsResult.file);
    assertNull(actualOptionsResult.streamData);
    assertNull(actualOptionsResult.followRedirect);
    assertNull(actualOptionsResult.stringData);
    assertNull(actualOptionsResult.virtualHost);
    assertNull(actualOptionsResult.address);
    assertNull(actualOptionsResult.localAddress);
    assertNull(actualOptionsResult.byteBufferData);
    assertNull(actualOptionsResult.charset);
    assertNull(actualOptionsResult.readTimeout);
    assertNull(actualOptionsResult.requestTimeout);
    assertNull(actualOptionsResult.cookies);
    assertNull(actualOptionsResult.compositeByteData);
    assertNull(actualOptionsResult.formParams);
    assertNull(actualOptionsResult.queryParams);
    assertNull(actualOptionsResult.bodyParts);
    assertNull(actualOptionsResult.realm);
    assertNull(actualOptionsResult.signatureCalculator);
    assertNull(actualOptionsResult.proxyServer);
    assertNull(actualOptionsResult.bodyGenerator);
    assertEquals(0L, actualOptionsResult.rangeOffset);
    assertEquals(PerHostChannelPoolPartitioning.INSTANCE, channelPoolPartitioning);
    assertEquals(UriEncoder.FIXING, actualOptionsResult.uriEncoder);
  }

  /**
   * Test {@link Dsl#options(String)}.
   *
   * <ul>
   *   <li>When {@code https://example.org/examplehost//}.
   *   <li>Then {@link RequestBuilderBase#headers} return {@link DefaultHttpHeaders}.
   * </ul>
   *
   * <p>Method under test: {@link Dsl#options(String)}
   */
  @Test
  @DisplayName(
      "Test options(String); when 'https://example.org/examplehost//'; then headers return DefaultHttpHeaders")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"RequestBuilder Dsl.options(String)"})
  void testOptions_whenHttpsExampleOrgExamplehost_thenHeadersReturnDefaultHttpHeaders() {
    // Arrange and Act
    RequestBuilder actualOptionsResult = Dsl.options("https://example.org/examplehost//");

    // Assert
    assertTrue(actualOptionsResult.headers instanceof DefaultHttpHeaders);
    assertTrue(actualOptionsResult.nameResolver instanceof DefaultNameResolver);
    ChannelPoolPartitioning channelPoolPartitioning = actualOptionsResult.channelPoolPartitioning;
    assertTrue(channelPoolPartitioning instanceof PerHostChannelPoolPartitioning);
    assertEquals("OPTIONS", actualOptionsResult.method);
    assertNull(actualOptionsResult.byteData);
    assertNull(actualOptionsResult.byteBufData);
    assertNull(actualOptionsResult.file);
    assertNull(actualOptionsResult.streamData);
    assertNull(actualOptionsResult.followRedirect);
    assertNull(actualOptionsResult.stringData);
    assertNull(actualOptionsResult.virtualHost);
    assertNull(actualOptionsResult.address);
    assertNull(actualOptionsResult.localAddress);
    assertNull(actualOptionsResult.byteBufferData);
    assertNull(actualOptionsResult.charset);
    assertNull(actualOptionsResult.readTimeout);
    assertNull(actualOptionsResult.requestTimeout);
    assertNull(actualOptionsResult.cookies);
    assertNull(actualOptionsResult.compositeByteData);
    assertNull(actualOptionsResult.formParams);
    assertNull(actualOptionsResult.queryParams);
    assertNull(actualOptionsResult.bodyParts);
    assertNull(actualOptionsResult.realm);
    assertNull(actualOptionsResult.signatureCalculator);
    assertNull(actualOptionsResult.proxyServer);
    assertNull(actualOptionsResult.bodyGenerator);
    assertEquals(0L, actualOptionsResult.rangeOffset);
    assertEquals(PerHostChannelPoolPartitioning.INSTANCE, channelPoolPartitioning);
    assertEquals(UriEncoder.FIXING, actualOptionsResult.uriEncoder);
  }

  /**
   * Test {@link Dsl#patch(String)}.
   *
   * <ul>
   *   <li>When {@code https://example.org/example}.
   *   <li>Then {@link RequestBuilderBase#headers} return {@link DefaultHttpHeaders}.
   * </ul>
   *
   * <p>Method under test: {@link Dsl#patch(String)}
   */
  @Test
  @DisplayName(
      "Test patch(String); when 'https://example.org/example'; then headers return DefaultHttpHeaders")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"RequestBuilder Dsl.patch(String)"})
  void testPatch_whenHttpsExampleOrgExample_thenHeadersReturnDefaultHttpHeaders() {
    // Arrange and Act
    RequestBuilder actualPatchResult = Dsl.patch("https://example.org/example");

    // Assert
    assertTrue(actualPatchResult.headers instanceof DefaultHttpHeaders);
    assertTrue(actualPatchResult.nameResolver instanceof DefaultNameResolver);
    ChannelPoolPartitioning channelPoolPartitioning = actualPatchResult.channelPoolPartitioning;
    assertTrue(channelPoolPartitioning instanceof PerHostChannelPoolPartitioning);
    assertEquals("PATCH", actualPatchResult.method);
    assertNull(actualPatchResult.byteData);
    assertNull(actualPatchResult.byteBufData);
    assertNull(actualPatchResult.file);
    assertNull(actualPatchResult.streamData);
    assertNull(actualPatchResult.followRedirect);
    assertNull(actualPatchResult.stringData);
    assertNull(actualPatchResult.virtualHost);
    assertNull(actualPatchResult.address);
    assertNull(actualPatchResult.localAddress);
    assertNull(actualPatchResult.byteBufferData);
    assertNull(actualPatchResult.charset);
    assertNull(actualPatchResult.readTimeout);
    assertNull(actualPatchResult.requestTimeout);
    assertNull(actualPatchResult.cookies);
    assertNull(actualPatchResult.compositeByteData);
    assertNull(actualPatchResult.formParams);
    assertNull(actualPatchResult.queryParams);
    assertNull(actualPatchResult.bodyParts);
    assertNull(actualPatchResult.realm);
    assertNull(actualPatchResult.signatureCalculator);
    assertNull(actualPatchResult.proxyServer);
    assertNull(actualPatchResult.bodyGenerator);
    assertEquals(0L, actualPatchResult.rangeOffset);
    assertEquals(PerHostChannelPoolPartitioning.INSTANCE, channelPoolPartitioning);
    assertEquals(UriEncoder.FIXING, actualPatchResult.uriEncoder);
  }

  /**
   * Test {@link Dsl#trace(String)}.
   *
   * <ul>
   *   <li>When {@code https://example.org/example}.
   *   <li>Then {@link RequestBuilderBase#headers} return {@link DefaultHttpHeaders}.
   * </ul>
   *
   * <p>Method under test: {@link Dsl#trace(String)}
   */
  @Test
  @DisplayName(
      "Test trace(String); when 'https://example.org/example'; then headers return DefaultHttpHeaders")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"RequestBuilder Dsl.trace(String)"})
  void testTrace_whenHttpsExampleOrgExample_thenHeadersReturnDefaultHttpHeaders() {
    // Arrange and Act
    RequestBuilder actualTraceResult = Dsl.trace("https://example.org/example");

    // Assert
    assertTrue(actualTraceResult.headers instanceof DefaultHttpHeaders);
    assertTrue(actualTraceResult.nameResolver instanceof DefaultNameResolver);
    ChannelPoolPartitioning channelPoolPartitioning = actualTraceResult.channelPoolPartitioning;
    assertTrue(channelPoolPartitioning instanceof PerHostChannelPoolPartitioning);
    assertEquals("TRACE", actualTraceResult.method);
    assertNull(actualTraceResult.byteData);
    assertNull(actualTraceResult.byteBufData);
    assertNull(actualTraceResult.file);
    assertNull(actualTraceResult.streamData);
    assertNull(actualTraceResult.followRedirect);
    assertNull(actualTraceResult.stringData);
    assertNull(actualTraceResult.virtualHost);
    assertNull(actualTraceResult.address);
    assertNull(actualTraceResult.localAddress);
    assertNull(actualTraceResult.byteBufferData);
    assertNull(actualTraceResult.charset);
    assertNull(actualTraceResult.readTimeout);
    assertNull(actualTraceResult.requestTimeout);
    assertNull(actualTraceResult.cookies);
    assertNull(actualTraceResult.compositeByteData);
    assertNull(actualTraceResult.formParams);
    assertNull(actualTraceResult.queryParams);
    assertNull(actualTraceResult.bodyParts);
    assertNull(actualTraceResult.realm);
    assertNull(actualTraceResult.signatureCalculator);
    assertNull(actualTraceResult.proxyServer);
    assertNull(actualTraceResult.bodyGenerator);
    assertEquals(0L, actualTraceResult.rangeOffset);
    assertEquals(PerHostChannelPoolPartitioning.INSTANCE, channelPoolPartitioning);
    assertEquals(UriEncoder.FIXING, actualTraceResult.uriEncoder);
  }

  /**
   * Test {@link Dsl#request(String, String)}.
   *
   * <ul>
   *   <li>When {@code https://example.org/example}.
   *   <li>Then {@link RequestBuilderBase#headers} return {@link DefaultHttpHeaders}.
   * </ul>
   *
   * <p>Method under test: {@link Dsl#request(String, String)}
   */
  @Test
  @DisplayName(
      "Test request(String, String); when 'https://example.org/example'; then headers return DefaultHttpHeaders")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"RequestBuilder Dsl.request(String, String)"})
  void testRequest_whenHttpsExampleOrgExample_thenHeadersReturnDefaultHttpHeaders() {
    // Arrange and Act
    RequestBuilder actualRequestResult =
        Dsl.request("https://example.org/example", "https://example.org/example");

    // Assert
    assertTrue(actualRequestResult.headers instanceof DefaultHttpHeaders);
    assertTrue(actualRequestResult.nameResolver instanceof DefaultNameResolver);
    ChannelPoolPartitioning channelPoolPartitioning = actualRequestResult.channelPoolPartitioning;
    assertTrue(channelPoolPartitioning instanceof PerHostChannelPoolPartitioning);
    assertEquals("https://example.org/example", actualRequestResult.method);
    assertNull(actualRequestResult.byteData);
    assertNull(actualRequestResult.byteBufData);
    assertNull(actualRequestResult.file);
    assertNull(actualRequestResult.streamData);
    assertNull(actualRequestResult.followRedirect);
    assertNull(actualRequestResult.stringData);
    assertNull(actualRequestResult.virtualHost);
    assertNull(actualRequestResult.address);
    assertNull(actualRequestResult.localAddress);
    assertNull(actualRequestResult.byteBufferData);
    assertNull(actualRequestResult.charset);
    assertNull(actualRequestResult.readTimeout);
    assertNull(actualRequestResult.requestTimeout);
    assertNull(actualRequestResult.cookies);
    assertNull(actualRequestResult.compositeByteData);
    assertNull(actualRequestResult.formParams);
    assertNull(actualRequestResult.queryParams);
    assertNull(actualRequestResult.bodyParts);
    assertNull(actualRequestResult.realm);
    assertNull(actualRequestResult.signatureCalculator);
    assertNull(actualRequestResult.proxyServer);
    assertNull(actualRequestResult.bodyGenerator);
    assertEquals(0L, actualRequestResult.rangeOffset);
    assertEquals(PerHostChannelPoolPartitioning.INSTANCE, channelPoolPartitioning);
    assertEquals(UriEncoder.FIXING, actualRequestResult.uriEncoder);
  }

  /**
   * Test {@link Dsl#proxyServer(String, int)}.
   *
   * <p>Method under test: {@link Dsl#proxyServer(String, int)}
   */
  @Test
  @DisplayName("Test proxyServer(String, int)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"ProxyServer.Builder Dsl.proxyServer(String, int)"})
  void testProxyServer() {
    // Arrange, Act and Assert
    ProxyServer proxyServer = Dsl.proxyServer("https://example.org/example", 8080).build();
    assertEquals("https://example.org/example", proxyServer.getHost());
    assertNull(proxyServer.getCustomHeaders());
    assertNull(proxyServer.getRealm());
    assertEquals(8080, proxyServer.getPort());
    assertEquals(8080, proxyServer.getSecuredPort());
    assertEquals(ProxyType.HTTP, proxyServer.getProxyType());
    assertTrue(proxyServer.getNonProxyHosts().isEmpty());
  }

  /**
   * Test {@link Dsl#realm(Realm)} with {@code prototype}.
   *
   * <ul>
   *   <li>Given empty string.
   *   <li>When {@link Realm} {@link Realm#getQop()} return empty string.
   * </ul>
   *
   * <p>Method under test: {@link Dsl#realm(Realm)}
   */
  @Test
  @DisplayName(
      "Test realm(Realm) with 'prototype'; given empty string; when Realm getQop() return empty string")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"Realm.Builder Dsl.realm(Realm)"})
  void testRealmWithPrototype_givenEmptyString_whenRealmGetQopReturnEmptyString() {
    // Arrange
    Realm prototype = mock(Realm.class);
    when(prototype.isOmitQuery()).thenReturn(true);
    when(prototype.isUseAbsoluteURI()).thenReturn(true);
    when(prototype.isUseCanonicalHostname()).thenReturn(true);
    when(prototype.isUsePreemptiveAuth()).thenReturn(true);
    when(prototype.getAlgorithm()).thenReturn("https://example.org/example");
    when(prototype.getLoginContextName()).thenReturn("https://example.org/example");
    when(prototype.getNc()).thenReturn("https://example.org/example");
    when(prototype.getNonce()).thenReturn("https://example.org/example");
    when(prototype.getNtlmDomain()).thenReturn("https://example.org/example");
    when(prototype.getNtlmHost()).thenReturn("https://example.org/example");
    when(prototype.getOpaque()).thenReturn("https://example.org/example");
    when(prototype.getPassword()).thenReturn("https://example.org/example");
    when(prototype.getPrincipal()).thenReturn("https://example.org/example");
    when(prototype.getQop()).thenReturn("");
    when(prototype.getRealmName()).thenReturn("https://example.org/example");
    when(prototype.getServicePrincipalName()).thenReturn("https://example.org/example");
    when(prototype.getCharset()).thenReturn(Charset.forName("UTF-8"));
    when(prototype.getCustomLoginConfig()).thenReturn(new HashMap<>());
    when(prototype.getScheme()).thenReturn(AuthScheme.BASIC);
    Uri uri =
        new Uri(
            "https://example.org/example",
            "https://example.org/example",
            "https://example.org/example",
            8080,
            "https://example.org/example",
            "https://example.org/example",
            "https://example.org/example");
    when(prototype.getUri()).thenReturn(uri);

    // Act
    Dsl.realm(prototype);

    // Assert
    verify(prototype).getAlgorithm();
    verify(prototype).getCharset();
    verify(prototype).getCustomLoginConfig();
    verify(prototype).getLoginContextName();
    verify(prototype).getNc();
    verify(prototype).getNonce();
    verify(prototype).getNtlmDomain();
    verify(prototype).getNtlmHost();
    verify(prototype).getOpaque();
    verify(prototype).getPassword();
    verify(prototype).getPrincipal();
    verify(prototype).getQop();
    verify(prototype).getRealmName();
    verify(prototype).getScheme();
    verify(prototype).getServicePrincipalName();
    verify(prototype).getUri();
    verify(prototype).isOmitQuery();
    verify(prototype).isUseAbsoluteURI();
    verify(prototype).isUseCanonicalHostname();
    verify(prototype).isUsePreemptiveAuth();
  }

  /**
   * Test {@link Dsl#realm(Realm)} with {@code prototype}.
   *
   * <ul>
   *   <li>Given {@code http.auth.ntlm.domain}.
   * </ul>
   *
   * <p>Method under test: {@link Dsl#realm(Realm)}
   */
  @Test
  @DisplayName("Test realm(Realm) with 'prototype'; given 'http.auth.ntlm.domain'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"Realm.Builder Dsl.realm(Realm)"})
  void testRealmWithPrototype_givenHttpAuthNtlmDomain() {
    // Arrange
    Realm prototype = mock(Realm.class);
    when(prototype.isOmitQuery()).thenReturn(true);
    when(prototype.isUseAbsoluteURI()).thenReturn(true);
    when(prototype.isUseCanonicalHostname()).thenReturn(true);
    when(prototype.isUsePreemptiveAuth()).thenReturn(true);
    when(prototype.getAlgorithm()).thenReturn("https://example.org/example");
    when(prototype.getLoginContextName()).thenReturn("https://example.org/example");
    when(prototype.getNc()).thenReturn("https://example.org/example");
    when(prototype.getNonce()).thenReturn("https://example.org/example");
    when(prototype.getNtlmDomain()).thenReturn("http.auth.ntlm.domain");
    when(prototype.getNtlmHost()).thenReturn("https://example.org/example");
    when(prototype.getOpaque()).thenReturn("https://example.org/example");
    when(prototype.getPassword()).thenReturn("https://example.org/example");
    when(prototype.getPrincipal()).thenReturn("https://example.org/example");
    when(prototype.getQop()).thenReturn("https://example.org/example");
    when(prototype.getRealmName()).thenReturn("https://example.org/example");
    when(prototype.getServicePrincipalName()).thenReturn("https://example.org/example");
    when(prototype.getCharset()).thenReturn(Charset.forName("UTF-8"));
    when(prototype.getCustomLoginConfig()).thenReturn(new HashMap<>());
    when(prototype.getScheme()).thenReturn(AuthScheme.BASIC);
    Uri uri =
        new Uri(
            "https://example.org/example",
            "https://example.org/example",
            "https://example.org/example",
            8080,
            "https://example.org/example",
            "https://example.org/example",
            "https://example.org/example");
    when(prototype.getUri()).thenReturn(uri);

    // Act
    Dsl.realm(prototype);

    // Assert
    verify(prototype).getAlgorithm();
    verify(prototype).getCharset();
    verify(prototype).getCustomLoginConfig();
    verify(prototype).getLoginContextName();
    verify(prototype).getNc();
    verify(prototype).getNonce();
    verify(prototype).getNtlmDomain();
    verify(prototype).getNtlmHost();
    verify(prototype).getOpaque();
    verify(prototype).getPassword();
    verify(prototype).getPrincipal();
    verify(prototype).getQop();
    verify(prototype).getRealmName();
    verify(prototype).getScheme();
    verify(prototype).getServicePrincipalName();
    verify(prototype).getUri();
    verify(prototype).isOmitQuery();
    verify(prototype).isUseAbsoluteURI();
    verify(prototype).isUseCanonicalHostname();
    verify(prototype).isUsePreemptiveAuth();
  }

  /**
   * Test {@link Dsl#realm(Realm)} with {@code prototype}.
   *
   * <ul>
   *   <li>When {@link Realm} {@link Realm#getNtlmDomain()} return {@code
   *       https://example.org/example}.
   * </ul>
   *
   * <p>Method under test: {@link Dsl#realm(Realm)}
   */
  @Test
  @DisplayName(
      "Test realm(Realm) with 'prototype'; when Realm getNtlmDomain() return 'https://example.org/example'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"Realm.Builder Dsl.realm(Realm)"})
  void testRealmWithPrototype_whenRealmGetNtlmDomainReturnHttpsExampleOrgExample() {
    // Arrange
    Realm prototype = mock(Realm.class);
    when(prototype.isOmitQuery()).thenReturn(true);
    when(prototype.isUseAbsoluteURI()).thenReturn(true);
    when(prototype.isUseCanonicalHostname()).thenReturn(true);
    when(prototype.isUsePreemptiveAuth()).thenReturn(true);
    when(prototype.getAlgorithm()).thenReturn("https://example.org/example");
    when(prototype.getLoginContextName()).thenReturn("https://example.org/example");
    when(prototype.getNc()).thenReturn("https://example.org/example");
    when(prototype.getNonce()).thenReturn("https://example.org/example");
    when(prototype.getNtlmDomain()).thenReturn("https://example.org/example");
    when(prototype.getNtlmHost()).thenReturn("https://example.org/example");
    when(prototype.getOpaque()).thenReturn("https://example.org/example");
    when(prototype.getPassword()).thenReturn("https://example.org/example");
    when(prototype.getPrincipal()).thenReturn("https://example.org/example");
    when(prototype.getQop()).thenReturn("https://example.org/example");
    when(prototype.getRealmName()).thenReturn("https://example.org/example");
    when(prototype.getServicePrincipalName()).thenReturn("https://example.org/example");
    when(prototype.getCharset()).thenReturn(Charset.forName("UTF-8"));
    when(prototype.getCustomLoginConfig()).thenReturn(new HashMap<>());
    when(prototype.getScheme()).thenReturn(AuthScheme.BASIC);
    Uri uri =
        new Uri(
            "https://example.org/example",
            "https://example.org/example",
            "https://example.org/example",
            8080,
            "https://example.org/example",
            "https://example.org/example",
            "https://example.org/example");
    when(prototype.getUri()).thenReturn(uri);

    // Act
    Dsl.realm(prototype);

    // Assert
    verify(prototype).getAlgorithm();
    verify(prototype).getCharset();
    verify(prototype).getCustomLoginConfig();
    verify(prototype).getLoginContextName();
    verify(prototype).getNc();
    verify(prototype).getNonce();
    verify(prototype).getNtlmDomain();
    verify(prototype).getNtlmHost();
    verify(prototype).getOpaque();
    verify(prototype).getPassword();
    verify(prototype).getPrincipal();
    verify(prototype).getQop();
    verify(prototype).getRealmName();
    verify(prototype).getScheme();
    verify(prototype).getServicePrincipalName();
    verify(prototype).getUri();
    verify(prototype).isOmitQuery();
    verify(prototype).isUseAbsoluteURI();
    verify(prototype).isUseCanonicalHostname();
    verify(prototype).isUsePreemptiveAuth();
  }
}
