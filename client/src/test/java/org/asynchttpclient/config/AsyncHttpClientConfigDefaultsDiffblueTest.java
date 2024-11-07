package org.asynchttpclient.config;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class AsyncHttpClientConfigDefaultsDiffblueTest {
  /**
   * Test {@link AsyncHttpClientConfigDefaults#defaultThreadPoolName()}.
   * <p>
   * Method under test:
   * {@link AsyncHttpClientConfigDefaults#defaultThreadPoolName()}
   */
  @Test
  @DisplayName("Test defaultThreadPoolName()")
  void testDefaultThreadPoolName() {
    // Arrange, Act and Assert
    assertEquals("AsyncHttpClient", AsyncHttpClientConfigDefaults.defaultThreadPoolName());
  }

  /**
   * Test {@link AsyncHttpClientConfigDefaults#defaultMaxConnections()}.
   * <p>
   * Method under test:
   * {@link AsyncHttpClientConfigDefaults#defaultMaxConnections()}
   */
  @Test
  @DisplayName("Test defaultMaxConnections()")
  void testDefaultMaxConnections() {
    // Arrange, Act and Assert
    assertEquals(-1, AsyncHttpClientConfigDefaults.defaultMaxConnections());
  }

  /**
   * Test {@link AsyncHttpClientConfigDefaults#defaultMaxConnectionsPerHost()}.
   * <p>
   * Method under test:
   * {@link AsyncHttpClientConfigDefaults#defaultMaxConnectionsPerHost()}
   */
  @Test
  @DisplayName("Test defaultMaxConnectionsPerHost()")
  void testDefaultMaxConnectionsPerHost() {
    // Arrange, Act and Assert
    assertEquals(-1, AsyncHttpClientConfigDefaults.defaultMaxConnectionsPerHost());
  }

  /**
   * Test
   * {@link AsyncHttpClientConfigDefaults#defaultAcquireFreeChannelTimeout()}.
   * <p>
   * Method under test:
   * {@link AsyncHttpClientConfigDefaults#defaultAcquireFreeChannelTimeout()}
   */
  @Test
  @DisplayName("Test defaultAcquireFreeChannelTimeout()")
  void testDefaultAcquireFreeChannelTimeout() {
    // Arrange, Act and Assert
    assertEquals(0, AsyncHttpClientConfigDefaults.defaultAcquireFreeChannelTimeout());
  }

  /**
   * Test {@link AsyncHttpClientConfigDefaults#defaultConnectTimeout()}.
   * <p>
   * Method under test:
   * {@link AsyncHttpClientConfigDefaults#defaultConnectTimeout()}
   */
  @Test
  @DisplayName("Test defaultConnectTimeout()")
  void testDefaultConnectTimeout() {
    // Arrange, Act and Assert
    assertEquals(5000000000L, AsyncHttpClientConfigDefaults.defaultConnectTimeout().toNanos());
  }

  /**
   * Test
   * {@link AsyncHttpClientConfigDefaults#defaultPooledConnectionIdleTimeout()}.
   * <p>
   * Method under test:
   * {@link AsyncHttpClientConfigDefaults#defaultPooledConnectionIdleTimeout()}
   */
  @Test
  @DisplayName("Test defaultPooledConnectionIdleTimeout()")
  void testDefaultPooledConnectionIdleTimeout() {
    // Arrange, Act and Assert
    assertEquals(60000000000L, AsyncHttpClientConfigDefaults.defaultPooledConnectionIdleTimeout().toNanos());
  }

  /**
   * Test
   * {@link AsyncHttpClientConfigDefaults#defaultConnectionPoolCleanerPeriod()}.
   * <p>
   * Method under test:
   * {@link AsyncHttpClientConfigDefaults#defaultConnectionPoolCleanerPeriod()}
   */
  @Test
  @DisplayName("Test defaultConnectionPoolCleanerPeriod()")
  void testDefaultConnectionPoolCleanerPeriod() {
    // Arrange, Act and Assert
    assertEquals(100000000L, AsyncHttpClientConfigDefaults.defaultConnectionPoolCleanerPeriod().toNanos());
  }

  /**
   * Test {@link AsyncHttpClientConfigDefaults#defaultReadTimeout()}.
   * <p>
   * Method under test: {@link AsyncHttpClientConfigDefaults#defaultReadTimeout()}
   */
  @Test
  @DisplayName("Test defaultReadTimeout()")
  void testDefaultReadTimeout() {
    // Arrange, Act and Assert
    assertEquals(60000000000L, AsyncHttpClientConfigDefaults.defaultReadTimeout().toNanos());
  }

  /**
   * Test {@link AsyncHttpClientConfigDefaults#defaultRequestTimeout()}.
   * <p>
   * Method under test:
   * {@link AsyncHttpClientConfigDefaults#defaultRequestTimeout()}
   */
  @Test
  @DisplayName("Test defaultRequestTimeout()")
  void testDefaultRequestTimeout() {
    // Arrange, Act and Assert
    assertEquals(60000000000L, AsyncHttpClientConfigDefaults.defaultRequestTimeout().toNanos());
  }

  /**
   * Test {@link AsyncHttpClientConfigDefaults#defaultConnectionTtl()}.
   * <p>
   * Method under test:
   * {@link AsyncHttpClientConfigDefaults#defaultConnectionTtl()}
   */
  @Test
  @DisplayName("Test defaultConnectionTtl()")
  void testDefaultConnectionTtl() {
    // Arrange, Act and Assert
    assertEquals(-1000000L, AsyncHttpClientConfigDefaults.defaultConnectionTtl().toNanos());
  }

  /**
   * Test {@link AsyncHttpClientConfigDefaults#defaultFollowRedirect()}.
   * <p>
   * Method under test:
   * {@link AsyncHttpClientConfigDefaults#defaultFollowRedirect()}
   */
  @Test
  @DisplayName("Test defaultFollowRedirect()")
  void testDefaultFollowRedirect() {
    // Arrange, Act and Assert
    assertFalse(AsyncHttpClientConfigDefaults.defaultFollowRedirect());
  }

  /**
   * Test {@link AsyncHttpClientConfigDefaults#defaultMaxRedirects()}.
   * <p>
   * Method under test:
   * {@link AsyncHttpClientConfigDefaults#defaultMaxRedirects()}
   */
  @Test
  @DisplayName("Test defaultMaxRedirects()")
  void testDefaultMaxRedirects() {
    // Arrange, Act and Assert
    assertEquals(5, AsyncHttpClientConfigDefaults.defaultMaxRedirects());
  }

  /**
   * Test {@link AsyncHttpClientConfigDefaults#defaultCompressionEnforced()}.
   * <p>
   * Method under test:
   * {@link AsyncHttpClientConfigDefaults#defaultCompressionEnforced()}
   */
  @Test
  @DisplayName("Test defaultCompressionEnforced()")
  void testDefaultCompressionEnforced() {
    // Arrange, Act and Assert
    assertFalse(AsyncHttpClientConfigDefaults.defaultCompressionEnforced());
  }

  /**
   * Test
   * {@link AsyncHttpClientConfigDefaults#defaultEnableAutomaticDecompression()}.
   * <p>
   * Method under test:
   * {@link AsyncHttpClientConfigDefaults#defaultEnableAutomaticDecompression()}
   */
  @Test
  @DisplayName("Test defaultEnableAutomaticDecompression()")
  void testDefaultEnableAutomaticDecompression() {
    // Arrange, Act and Assert
    assertTrue(AsyncHttpClientConfigDefaults.defaultEnableAutomaticDecompression());
  }

  /**
   * Test {@link AsyncHttpClientConfigDefaults#defaultUserAgent()}.
   * <p>
   * Method under test: {@link AsyncHttpClientConfigDefaults#defaultUserAgent()}
   */
  @Test
  @DisplayName("Test defaultUserAgent()")
  void testDefaultUserAgent() {
    // Arrange, Act and Assert
    assertEquals("AHC/2.1", AsyncHttpClientConfigDefaults.defaultUserAgent());
  }

  /**
   * Test {@link AsyncHttpClientConfigDefaults#defaultEnabledProtocols()}.
   * <p>
   * Method under test:
   * {@link AsyncHttpClientConfigDefaults#defaultEnabledProtocols()}
   */
  @Test
  @DisplayName("Test defaultEnabledProtocols()")
  void testDefaultEnabledProtocols() {
    // Arrange, Act and Assert
    assertArrayEquals(new String[]{"TLSv1.3", "TLSv1.2"}, AsyncHttpClientConfigDefaults.defaultEnabledProtocols());
  }

  /**
   * Test {@link AsyncHttpClientConfigDefaults#defaultEnabledCipherSuites()}.
   * <p>
   * Method under test:
   * {@link AsyncHttpClientConfigDefaults#defaultEnabledCipherSuites()}
   */
  @Test
  @DisplayName("Test defaultEnabledCipherSuites()")
  void testDefaultEnabledCipherSuites() {
    // Arrange, Act and Assert
    assertNull(AsyncHttpClientConfigDefaults.defaultEnabledCipherSuites());
  }

  /**
   * Test
   * {@link AsyncHttpClientConfigDefaults#defaultFilterInsecureCipherSuites()}.
   * <p>
   * Method under test:
   * {@link AsyncHttpClientConfigDefaults#defaultFilterInsecureCipherSuites()}
   */
  @Test
  @DisplayName("Test defaultFilterInsecureCipherSuites()")
  void testDefaultFilterInsecureCipherSuites() {
    // Arrange, Act and Assert
    assertTrue(AsyncHttpClientConfigDefaults.defaultFilterInsecureCipherSuites());
  }

  /**
   * Test {@link AsyncHttpClientConfigDefaults#defaultUseProxySelector()}.
   * <p>
   * Method under test:
   * {@link AsyncHttpClientConfigDefaults#defaultUseProxySelector()}
   */
  @Test
  @DisplayName("Test defaultUseProxySelector()")
  void testDefaultUseProxySelector() {
    // Arrange, Act and Assert
    assertFalse(AsyncHttpClientConfigDefaults.defaultUseProxySelector());
  }

  /**
   * Test {@link AsyncHttpClientConfigDefaults#defaultUseProxyProperties()}.
   * <p>
   * Method under test:
   * {@link AsyncHttpClientConfigDefaults#defaultUseProxyProperties()}
   */
  @Test
  @DisplayName("Test defaultUseProxyProperties()")
  void testDefaultUseProxyProperties() {
    // Arrange, Act and Assert
    assertFalse(AsyncHttpClientConfigDefaults.defaultUseProxyProperties());
  }

  /**
   * Test {@link AsyncHttpClientConfigDefaults#defaultValidateResponseHeaders()}.
   * <p>
   * Method under test:
   * {@link AsyncHttpClientConfigDefaults#defaultValidateResponseHeaders()}
   */
  @Test
  @DisplayName("Test defaultValidateResponseHeaders()")
  void testDefaultValidateResponseHeaders() {
    // Arrange, Act and Assert
    assertTrue(AsyncHttpClientConfigDefaults.defaultValidateResponseHeaders());
  }

  /**
   * Test
   * {@link AsyncHttpClientConfigDefaults#defaultAggregateWebSocketFrameFragments()}.
   * <p>
   * Method under test:
   * {@link AsyncHttpClientConfigDefaults#defaultAggregateWebSocketFrameFragments()}
   */
  @Test
  @DisplayName("Test defaultAggregateWebSocketFrameFragments()")
  void testDefaultAggregateWebSocketFrameFragments() {
    // Arrange, Act and Assert
    assertTrue(AsyncHttpClientConfigDefaults.defaultAggregateWebSocketFrameFragments());
  }

  /**
   * Test
   * {@link AsyncHttpClientConfigDefaults#defaultEnableWebSocketCompression()}.
   * <p>
   * Method under test:
   * {@link AsyncHttpClientConfigDefaults#defaultEnableWebSocketCompression()}
   */
  @Test
  @DisplayName("Test defaultEnableWebSocketCompression()")
  void testDefaultEnableWebSocketCompression() {
    // Arrange, Act and Assert
    assertFalse(AsyncHttpClientConfigDefaults.defaultEnableWebSocketCompression());
  }

  /**
   * Test {@link AsyncHttpClientConfigDefaults#defaultStrict302Handling()}.
   * <p>
   * Method under test:
   * {@link AsyncHttpClientConfigDefaults#defaultStrict302Handling()}
   */
  @Test
  @DisplayName("Test defaultStrict302Handling()")
  void testDefaultStrict302Handling() {
    // Arrange, Act and Assert
    assertFalse(AsyncHttpClientConfigDefaults.defaultStrict302Handling());
  }

  /**
   * Test {@link AsyncHttpClientConfigDefaults#defaultKeepAlive()}.
   * <p>
   * Method under test: {@link AsyncHttpClientConfigDefaults#defaultKeepAlive()}
   */
  @Test
  @DisplayName("Test defaultKeepAlive()")
  void testDefaultKeepAlive() {
    // Arrange, Act and Assert
    assertTrue(AsyncHttpClientConfigDefaults.defaultKeepAlive());
  }

  /**
   * Test {@link AsyncHttpClientConfigDefaults#defaultMaxRequestRetry()}.
   * <p>
   * Method under test:
   * {@link AsyncHttpClientConfigDefaults#defaultMaxRequestRetry()}
   */
  @Test
  @DisplayName("Test defaultMaxRequestRetry()")
  void testDefaultMaxRequestRetry() {
    // Arrange, Act and Assert
    assertEquals(5, AsyncHttpClientConfigDefaults.defaultMaxRequestRetry());
  }

  /**
   * Test
   * {@link AsyncHttpClientConfigDefaults#defaultDisableUrlEncodingForBoundRequests()}.
   * <p>
   * Method under test:
   * {@link AsyncHttpClientConfigDefaults#defaultDisableUrlEncodingForBoundRequests()}
   */
  @Test
  @DisplayName("Test defaultDisableUrlEncodingForBoundRequests()")
  void testDefaultDisableUrlEncodingForBoundRequests() {
    // Arrange, Act and Assert
    assertFalse(AsyncHttpClientConfigDefaults.defaultDisableUrlEncodingForBoundRequests());
  }

  /**
   * Test {@link AsyncHttpClientConfigDefaults#defaultUseLaxCookieEncoder()}.
   * <p>
   * Method under test:
   * {@link AsyncHttpClientConfigDefaults#defaultUseLaxCookieEncoder()}
   */
  @Test
  @DisplayName("Test defaultUseLaxCookieEncoder()")
  void testDefaultUseLaxCookieEncoder() {
    // Arrange, Act and Assert
    assertFalse(AsyncHttpClientConfigDefaults.defaultUseLaxCookieEncoder());
  }

  /**
   * Test {@link AsyncHttpClientConfigDefaults#defaultUseOpenSsl()}.
   * <p>
   * Method under test: {@link AsyncHttpClientConfigDefaults#defaultUseOpenSsl()}
   */
  @Test
  @DisplayName("Test defaultUseOpenSsl()")
  void testDefaultUseOpenSsl() {
    // Arrange, Act and Assert
    assertFalse(AsyncHttpClientConfigDefaults.defaultUseOpenSsl());
  }

  /**
   * Test {@link AsyncHttpClientConfigDefaults#defaultUseInsecureTrustManager()}.
   * <p>
   * Method under test:
   * {@link AsyncHttpClientConfigDefaults#defaultUseInsecureTrustManager()}
   */
  @Test
  @DisplayName("Test defaultUseInsecureTrustManager()")
  void testDefaultUseInsecureTrustManager() {
    // Arrange, Act and Assert
    assertFalse(AsyncHttpClientConfigDefaults.defaultUseInsecureTrustManager());
  }

  /**
   * Test
   * {@link AsyncHttpClientConfigDefaults#defaultDisableHttpsEndpointIdentificationAlgorithm()}.
   * <p>
   * Method under test:
   * {@link AsyncHttpClientConfigDefaults#defaultDisableHttpsEndpointIdentificationAlgorithm()}
   */
  @Test
  @DisplayName("Test defaultDisableHttpsEndpointIdentificationAlgorithm()")
  void testDefaultDisableHttpsEndpointIdentificationAlgorithm() {
    // Arrange, Act and Assert
    assertFalse(AsyncHttpClientConfigDefaults.defaultDisableHttpsEndpointIdentificationAlgorithm());
  }

  /**
   * Test {@link AsyncHttpClientConfigDefaults#defaultSslSessionCacheSize()}.
   * <p>
   * Method under test:
   * {@link AsyncHttpClientConfigDefaults#defaultSslSessionCacheSize()}
   */
  @Test
  @DisplayName("Test defaultSslSessionCacheSize()")
  void testDefaultSslSessionCacheSize() {
    // Arrange, Act and Assert
    assertEquals(0, AsyncHttpClientConfigDefaults.defaultSslSessionCacheSize());
  }

  /**
   * Test {@link AsyncHttpClientConfigDefaults#defaultSslSessionTimeout()}.
   * <p>
   * Method under test:
   * {@link AsyncHttpClientConfigDefaults#defaultSslSessionTimeout()}
   */
  @Test
  @DisplayName("Test defaultSslSessionTimeout()")
  void testDefaultSslSessionTimeout() {
    // Arrange, Act and Assert
    assertEquals(0, AsyncHttpClientConfigDefaults.defaultSslSessionTimeout());
  }

  /**
   * Test {@link AsyncHttpClientConfigDefaults#defaultTcpNoDelay()}.
   * <p>
   * Method under test: {@link AsyncHttpClientConfigDefaults#defaultTcpNoDelay()}
   */
  @Test
  @DisplayName("Test defaultTcpNoDelay()")
  void testDefaultTcpNoDelay() {
    // Arrange, Act and Assert
    assertTrue(AsyncHttpClientConfigDefaults.defaultTcpNoDelay());
  }

  /**
   * Test {@link AsyncHttpClientConfigDefaults#defaultSoReuseAddress()}.
   * <p>
   * Method under test:
   * {@link AsyncHttpClientConfigDefaults#defaultSoReuseAddress()}
   */
  @Test
  @DisplayName("Test defaultSoReuseAddress()")
  void testDefaultSoReuseAddress() {
    // Arrange, Act and Assert
    assertFalse(AsyncHttpClientConfigDefaults.defaultSoReuseAddress());
  }

  /**
   * Test {@link AsyncHttpClientConfigDefaults#defaultSoKeepAlive()}.
   * <p>
   * Method under test: {@link AsyncHttpClientConfigDefaults#defaultSoKeepAlive()}
   */
  @Test
  @DisplayName("Test defaultSoKeepAlive()")
  void testDefaultSoKeepAlive() {
    // Arrange, Act and Assert
    assertTrue(AsyncHttpClientConfigDefaults.defaultSoKeepAlive());
  }

  /**
   * Test {@link AsyncHttpClientConfigDefaults#defaultSoLinger()}.
   * <p>
   * Method under test: {@link AsyncHttpClientConfigDefaults#defaultSoLinger()}
   */
  @Test
  @DisplayName("Test defaultSoLinger()")
  void testDefaultSoLinger() {
    // Arrange, Act and Assert
    assertEquals(-1, AsyncHttpClientConfigDefaults.defaultSoLinger());
  }

  /**
   * Test {@link AsyncHttpClientConfigDefaults#defaultSoSndBuf()}.
   * <p>
   * Method under test: {@link AsyncHttpClientConfigDefaults#defaultSoSndBuf()}
   */
  @Test
  @DisplayName("Test defaultSoSndBuf()")
  void testDefaultSoSndBuf() {
    // Arrange, Act and Assert
    assertEquals(-1, AsyncHttpClientConfigDefaults.defaultSoSndBuf());
  }

  /**
   * Test {@link AsyncHttpClientConfigDefaults#defaultSoRcvBuf()}.
   * <p>
   * Method under test: {@link AsyncHttpClientConfigDefaults#defaultSoRcvBuf()}
   */
  @Test
  @DisplayName("Test defaultSoRcvBuf()")
  void testDefaultSoRcvBuf() {
    // Arrange, Act and Assert
    assertEquals(-1, AsyncHttpClientConfigDefaults.defaultSoRcvBuf());
  }

  /**
   * Test
   * {@link AsyncHttpClientConfigDefaults#defaultHttpClientCodecMaxInitialLineLength()}.
   * <p>
   * Method under test:
   * {@link AsyncHttpClientConfigDefaults#defaultHttpClientCodecMaxInitialLineLength()}
   */
  @Test
  @DisplayName("Test defaultHttpClientCodecMaxInitialLineLength()")
  void testDefaultHttpClientCodecMaxInitialLineLength() {
    // Arrange, Act and Assert
    assertEquals(4096, AsyncHttpClientConfigDefaults.defaultHttpClientCodecMaxInitialLineLength());
  }

  /**
   * Test
   * {@link AsyncHttpClientConfigDefaults#defaultHttpClientCodecMaxHeaderSize()}.
   * <p>
   * Method under test:
   * {@link AsyncHttpClientConfigDefaults#defaultHttpClientCodecMaxHeaderSize()}
   */
  @Test
  @DisplayName("Test defaultHttpClientCodecMaxHeaderSize()")
  void testDefaultHttpClientCodecMaxHeaderSize() {
    // Arrange, Act and Assert
    assertEquals(8192, AsyncHttpClientConfigDefaults.defaultHttpClientCodecMaxHeaderSize());
  }

  /**
   * Test
   * {@link AsyncHttpClientConfigDefaults#defaultHttpClientCodecMaxChunkSize()}.
   * <p>
   * Method under test:
   * {@link AsyncHttpClientConfigDefaults#defaultHttpClientCodecMaxChunkSize()}
   */
  @Test
  @DisplayName("Test defaultHttpClientCodecMaxChunkSize()")
  void testDefaultHttpClientCodecMaxChunkSize() {
    // Arrange, Act and Assert
    assertEquals(8192, AsyncHttpClientConfigDefaults.defaultHttpClientCodecMaxChunkSize());
  }

  /**
   * Test
   * {@link AsyncHttpClientConfigDefaults#defaultHttpClientCodecInitialBufferSize()}.
   * <p>
   * Method under test:
   * {@link AsyncHttpClientConfigDefaults#defaultHttpClientCodecInitialBufferSize()}
   */
  @Test
  @DisplayName("Test defaultHttpClientCodecInitialBufferSize()")
  void testDefaultHttpClientCodecInitialBufferSize() {
    // Arrange, Act and Assert
    assertEquals(128, AsyncHttpClientConfigDefaults.defaultHttpClientCodecInitialBufferSize());
  }

  /**
   * Test {@link AsyncHttpClientConfigDefaults#defaultDisableZeroCopy()}.
   * <p>
   * Method under test:
   * {@link AsyncHttpClientConfigDefaults#defaultDisableZeroCopy()}
   */
  @Test
  @DisplayName("Test defaultDisableZeroCopy()")
  void testDefaultDisableZeroCopy() {
    // Arrange, Act and Assert
    assertFalse(AsyncHttpClientConfigDefaults.defaultDisableZeroCopy());
  }

  /**
   * Test {@link AsyncHttpClientConfigDefaults#defaultHandshakeTimeout()}.
   * <p>
   * Method under test:
   * {@link AsyncHttpClientConfigDefaults#defaultHandshakeTimeout()}
   */
  @Test
  @DisplayName("Test defaultHandshakeTimeout()")
  void testDefaultHandshakeTimeout() {
    // Arrange, Act and Assert
    assertEquals(10000, AsyncHttpClientConfigDefaults.defaultHandshakeTimeout());
  }

  /**
   * Test {@link AsyncHttpClientConfigDefaults#defaultChunkedFileChunkSize()}.
   * <p>
   * Method under test:
   * {@link AsyncHttpClientConfigDefaults#defaultChunkedFileChunkSize()}
   */
  @Test
  @DisplayName("Test defaultChunkedFileChunkSize()")
  void testDefaultChunkedFileChunkSize() {
    // Arrange, Act and Assert
    assertEquals(8192, AsyncHttpClientConfigDefaults.defaultChunkedFileChunkSize());
  }

  /**
   * Test {@link AsyncHttpClientConfigDefaults#defaultWebSocketMaxBufferSize()}.
   * <p>
   * Method under test:
   * {@link AsyncHttpClientConfigDefaults#defaultWebSocketMaxBufferSize()}
   */
  @Test
  @DisplayName("Test defaultWebSocketMaxBufferSize()")
  void testDefaultWebSocketMaxBufferSize() {
    // Arrange, Act and Assert
    assertEquals(128000000, AsyncHttpClientConfigDefaults.defaultWebSocketMaxBufferSize());
  }

  /**
   * Test {@link AsyncHttpClientConfigDefaults#defaultWebSocketMaxFrameSize()}.
   * <p>
   * Method under test:
   * {@link AsyncHttpClientConfigDefaults#defaultWebSocketMaxFrameSize()}
   */
  @Test
  @DisplayName("Test defaultWebSocketMaxFrameSize()")
  void testDefaultWebSocketMaxFrameSize() {
    // Arrange, Act and Assert
    assertEquals(10240, AsyncHttpClientConfigDefaults.defaultWebSocketMaxFrameSize());
  }

  /**
   * Test {@link AsyncHttpClientConfigDefaults#defaultKeepEncodingHeader()}.
   * <p>
   * Method under test:
   * {@link AsyncHttpClientConfigDefaults#defaultKeepEncodingHeader()}
   */
  @Test
  @DisplayName("Test defaultKeepEncodingHeader()")
  void testDefaultKeepEncodingHeader() {
    // Arrange, Act and Assert
    assertFalse(AsyncHttpClientConfigDefaults.defaultKeepEncodingHeader());
  }

  /**
   * Test {@link AsyncHttpClientConfigDefaults#defaultShutdownQuietPeriod()}.
   * <p>
   * Method under test:
   * {@link AsyncHttpClientConfigDefaults#defaultShutdownQuietPeriod()}
   */
  @Test
  @DisplayName("Test defaultShutdownQuietPeriod()")
  void testDefaultShutdownQuietPeriod() {
    // Arrange, Act and Assert
    assertEquals(2000000000L, AsyncHttpClientConfigDefaults.defaultShutdownQuietPeriod().toNanos());
  }

  /**
   * Test {@link AsyncHttpClientConfigDefaults#defaultShutdownTimeout()}.
   * <p>
   * Method under test:
   * {@link AsyncHttpClientConfigDefaults#defaultShutdownTimeout()}
   */
  @Test
  @DisplayName("Test defaultShutdownTimeout()")
  void testDefaultShutdownTimeout() {
    // Arrange, Act and Assert
    assertEquals(15000000000L, AsyncHttpClientConfigDefaults.defaultShutdownTimeout().toNanos());
  }

  /**
   * Test {@link AsyncHttpClientConfigDefaults#defaultUseNativeTransport()}.
   * <p>
   * Method under test:
   * {@link AsyncHttpClientConfigDefaults#defaultUseNativeTransport()}
   */
  @Test
  @DisplayName("Test defaultUseNativeTransport()")
  void testDefaultUseNativeTransport() {
    // Arrange, Act and Assert
    assertFalse(AsyncHttpClientConfigDefaults.defaultUseNativeTransport());
  }

  /**
   * Test
   * {@link AsyncHttpClientConfigDefaults#defaultUseOnlyEpollNativeTransport()}.
   * <p>
   * Method under test:
   * {@link AsyncHttpClientConfigDefaults#defaultUseOnlyEpollNativeTransport()}
   */
  @Test
  @DisplayName("Test defaultUseOnlyEpollNativeTransport()")
  void testDefaultUseOnlyEpollNativeTransport() {
    // Arrange, Act and Assert
    assertFalse(AsyncHttpClientConfigDefaults.defaultUseOnlyEpollNativeTransport());
  }

  /**
   * Test {@link AsyncHttpClientConfigDefaults#defaultIoThreadsCount()}.
   * <p>
   * Method under test:
   * {@link AsyncHttpClientConfigDefaults#defaultIoThreadsCount()}
   */
  @Test
  @DisplayName("Test defaultIoThreadsCount()")
  void testDefaultIoThreadsCount() {
    // Arrange, Act and Assert
    assertEquals(Short.SIZE, AsyncHttpClientConfigDefaults.defaultIoThreadsCount());
  }

  /**
   * Test
   * {@link AsyncHttpClientConfigDefaults#defaultHashedWheelTimerTickDuration()}.
   * <p>
   * Method under test:
   * {@link AsyncHttpClientConfigDefaults#defaultHashedWheelTimerTickDuration()}
   */
  @Test
  @DisplayName("Test defaultHashedWheelTimerTickDuration()")
  void testDefaultHashedWheelTimerTickDuration() {
    // Arrange, Act and Assert
    assertEquals(100, AsyncHttpClientConfigDefaults.defaultHashedWheelTimerTickDuration());
  }

  /**
   * Test {@link AsyncHttpClientConfigDefaults#defaultHashedWheelTimerSize()}.
   * <p>
   * Method under test:
   * {@link AsyncHttpClientConfigDefaults#defaultHashedWheelTimerSize()}
   */
  @Test
  @DisplayName("Test defaultHashedWheelTimerSize()")
  void testDefaultHashedWheelTimerSize() {
    // Arrange, Act and Assert
    assertEquals(512, AsyncHttpClientConfigDefaults.defaultHashedWheelTimerSize());
  }

  /**
   * Test
   * {@link AsyncHttpClientConfigDefaults#defaultExpiredCookieEvictionDelay()}.
   * <p>
   * Method under test:
   * {@link AsyncHttpClientConfigDefaults#defaultExpiredCookieEvictionDelay()}
   */
  @Test
  @DisplayName("Test defaultExpiredCookieEvictionDelay()")
  void testDefaultExpiredCookieEvictionDelay() {
    // Arrange, Act and Assert
    assertEquals(30000, AsyncHttpClientConfigDefaults.defaultExpiredCookieEvictionDelay());
  }
}
