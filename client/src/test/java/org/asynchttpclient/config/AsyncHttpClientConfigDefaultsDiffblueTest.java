package org.asynchttpclient.config;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

class AsyncHttpClientConfigDefaultsDiffblueTest {
  /**
   * Method under test:
   * {@link AsyncHttpClientConfigDefaults#defaultThreadPoolName()}
   */
  @Test
  void testDefaultThreadPoolName() {
    // Arrange, Act and Assert
    assertEquals("AsyncHttpClient", AsyncHttpClientConfigDefaults.defaultThreadPoolName());
  }

  /**
   * Method under test:
   * {@link AsyncHttpClientConfigDefaults#defaultMaxConnections()}
   */
  @Test
  void testDefaultMaxConnections() {
    // Arrange, Act and Assert
    assertEquals(-1, AsyncHttpClientConfigDefaults.defaultMaxConnections());
  }

  /**
   * Method under test:
   * {@link AsyncHttpClientConfigDefaults#defaultMaxConnectionsPerHost()}
   */
  @Test
  void testDefaultMaxConnectionsPerHost() {
    // Arrange, Act and Assert
    assertEquals(-1, AsyncHttpClientConfigDefaults.defaultMaxConnectionsPerHost());
  }

  /**
   * Method under test:
   * {@link AsyncHttpClientConfigDefaults#defaultAcquireFreeChannelTimeout()}
   */
  @Test
  void testDefaultAcquireFreeChannelTimeout() {
    // Arrange, Act and Assert
    assertEquals(0, AsyncHttpClientConfigDefaults.defaultAcquireFreeChannelTimeout());
  }

  /**
   * Method under test:
   * {@link AsyncHttpClientConfigDefaults#defaultConnectTimeout()}
   */
  @Test
  void testDefaultConnectTimeout() {
    // Arrange, Act and Assert
    assertEquals(5000000000L, AsyncHttpClientConfigDefaults.defaultConnectTimeout().toNanos());
  }

  /**
   * Method under test:
   * {@link AsyncHttpClientConfigDefaults#defaultPooledConnectionIdleTimeout()}
   */
  @Test
  void testDefaultPooledConnectionIdleTimeout() {
    // Arrange, Act and Assert
    assertEquals(60000000000L, AsyncHttpClientConfigDefaults.defaultPooledConnectionIdleTimeout().toNanos());
  }

  /**
   * Method under test:
   * {@link AsyncHttpClientConfigDefaults#defaultConnectionPoolCleanerPeriod()}
   */
  @Test
  void testDefaultConnectionPoolCleanerPeriod() {
    // Arrange, Act and Assert
    assertEquals(100000000L, AsyncHttpClientConfigDefaults.defaultConnectionPoolCleanerPeriod().toNanos());
  }

  /**
   * Method under test: {@link AsyncHttpClientConfigDefaults#defaultReadTimeout()}
   */
  @Test
  void testDefaultReadTimeout() {
    // Arrange, Act and Assert
    assertEquals(60000000000L, AsyncHttpClientConfigDefaults.defaultReadTimeout().toNanos());
  }

  /**
   * Method under test:
   * {@link AsyncHttpClientConfigDefaults#defaultRequestTimeout()}
   */
  @Test
  void testDefaultRequestTimeout() {
    // Arrange, Act and Assert
    assertEquals(60000000000L, AsyncHttpClientConfigDefaults.defaultRequestTimeout().toNanos());
  }

  /**
   * Method under test:
   * {@link AsyncHttpClientConfigDefaults#defaultConnectionTtl()}
   */
  @Test
  void testDefaultConnectionTtl() {
    // Arrange, Act and Assert
    assertEquals(-1000000L, AsyncHttpClientConfigDefaults.defaultConnectionTtl().toNanos());
  }

  /**
   * Method under test:
   * {@link AsyncHttpClientConfigDefaults#defaultMaxRedirects()}
   */
  @Test
  void testDefaultMaxRedirects() {
    // Arrange, Act and Assert
    assertEquals(5, AsyncHttpClientConfigDefaults.defaultMaxRedirects());
  }

  /**
   * Method under test:
   * {@link AsyncHttpClientConfigDefaults#defaultCompressionEnforced()}
   */
  @Test
  void testDefaultCompressionEnforced() {
    // Arrange, Act and Assert
    assertFalse(AsyncHttpClientConfigDefaults.defaultCompressionEnforced());
  }

  /**
   * Method under test:
   * {@link AsyncHttpClientConfigDefaults#defaultEnableAutomaticDecompression()}
   */
  @Test
  void testDefaultEnableAutomaticDecompression() {
    // Arrange, Act and Assert
    assertTrue(AsyncHttpClientConfigDefaults.defaultEnableAutomaticDecompression());
  }

  /**
   * Method under test: {@link AsyncHttpClientConfigDefaults#defaultUserAgent()}
   */
  @Test
  void testDefaultUserAgent() {
    // Arrange, Act and Assert
    assertEquals("AHC/2.1", AsyncHttpClientConfigDefaults.defaultUserAgent());
  }

  /**
   * Method under test:
   * {@link AsyncHttpClientConfigDefaults#defaultEnabledProtocols()}
   */
  @Test
  void testDefaultEnabledProtocols() {
    // Arrange, Act and Assert
    assertArrayEquals(new String[]{"TLSv1.3", "TLSv1.2"}, AsyncHttpClientConfigDefaults.defaultEnabledProtocols());
  }

  /**
   * Method under test:
   * {@link AsyncHttpClientConfigDefaults#defaultEnabledCipherSuites()}
   */
  @Test
  void testDefaultEnabledCipherSuites() {
    // Arrange, Act and Assert
    assertNull(AsyncHttpClientConfigDefaults.defaultEnabledCipherSuites());
  }

  /**
   * Method under test:
   * {@link AsyncHttpClientConfigDefaults#defaultFilterInsecureCipherSuites()}
   */
  @Test
  void testDefaultFilterInsecureCipherSuites() {
    // Arrange, Act and Assert
    assertTrue(AsyncHttpClientConfigDefaults.defaultFilterInsecureCipherSuites());
  }

  /**
   * Method under test:
   * {@link AsyncHttpClientConfigDefaults#defaultUseProxySelector()}
   */
  @Test
  void testDefaultUseProxySelector() {
    // Arrange, Act and Assert
    assertFalse(AsyncHttpClientConfigDefaults.defaultUseProxySelector());
  }

  /**
   * Method under test:
   * {@link AsyncHttpClientConfigDefaults#defaultUseProxyProperties()}
   */
  @Test
  void testDefaultUseProxyProperties() {
    // Arrange, Act and Assert
    assertFalse(AsyncHttpClientConfigDefaults.defaultUseProxyProperties());
  }

  /**
   * Method under test:
   * {@link AsyncHttpClientConfigDefaults#defaultValidateResponseHeaders()}
   */
  @Test
  void testDefaultValidateResponseHeaders() {
    // Arrange, Act and Assert
    assertTrue(AsyncHttpClientConfigDefaults.defaultValidateResponseHeaders());
  }

  /**
   * Method under test:
   * {@link AsyncHttpClientConfigDefaults#defaultAggregateWebSocketFrameFragments()}
   */
  @Test
  void testDefaultAggregateWebSocketFrameFragments() {
    // Arrange, Act and Assert
    assertTrue(AsyncHttpClientConfigDefaults.defaultAggregateWebSocketFrameFragments());
  }

  /**
   * Method under test:
   * {@link AsyncHttpClientConfigDefaults#defaultEnableWebSocketCompression()}
   */
  @Test
  void testDefaultEnableWebSocketCompression() {
    // Arrange, Act and Assert
    assertFalse(AsyncHttpClientConfigDefaults.defaultEnableWebSocketCompression());
  }

  /**
   * Method under test:
   * {@link AsyncHttpClientConfigDefaults#defaultStrict302Handling()}
   */
  @Test
  void testDefaultStrict302Handling() {
    // Arrange, Act and Assert
    assertFalse(AsyncHttpClientConfigDefaults.defaultStrict302Handling());
  }

  /**
   * Method under test: {@link AsyncHttpClientConfigDefaults#defaultKeepAlive()}
   */
  @Test
  void testDefaultKeepAlive() {
    // Arrange, Act and Assert
    assertTrue(AsyncHttpClientConfigDefaults.defaultKeepAlive());
  }

  /**
   * Method under test:
   * {@link AsyncHttpClientConfigDefaults#defaultMaxRequestRetry()}
   */
  @Test
  void testDefaultMaxRequestRetry() {
    // Arrange, Act and Assert
    assertEquals(5, AsyncHttpClientConfigDefaults.defaultMaxRequestRetry());
  }

  /**
   * Method under test:
   * {@link AsyncHttpClientConfigDefaults#defaultDisableUrlEncodingForBoundRequests()}
   */
  @Test
  void testDefaultDisableUrlEncodingForBoundRequests() {
    // Arrange, Act and Assert
    assertFalse(AsyncHttpClientConfigDefaults.defaultDisableUrlEncodingForBoundRequests());
  }

  /**
   * Method under test:
   * {@link AsyncHttpClientConfigDefaults#defaultUseLaxCookieEncoder()}
   */
  @Test
  void testDefaultUseLaxCookieEncoder() {
    // Arrange, Act and Assert
    assertFalse(AsyncHttpClientConfigDefaults.defaultUseLaxCookieEncoder());
  }

  /**
   * Method under test: {@link AsyncHttpClientConfigDefaults#defaultUseOpenSsl()}
   */
  @Test
  void testDefaultUseOpenSsl() {
    // Arrange, Act and Assert
    assertFalse(AsyncHttpClientConfigDefaults.defaultUseOpenSsl());
  }

  /**
   * Method under test:
   * {@link AsyncHttpClientConfigDefaults#defaultUseInsecureTrustManager()}
   */
  @Test
  void testDefaultUseInsecureTrustManager() {
    // Arrange, Act and Assert
    assertFalse(AsyncHttpClientConfigDefaults.defaultUseInsecureTrustManager());
  }

  /**
   * Method under test:
   * {@link AsyncHttpClientConfigDefaults#defaultDisableHttpsEndpointIdentificationAlgorithm()}
   */
  @Test
  void testDefaultDisableHttpsEndpointIdentificationAlgorithm() {
    // Arrange, Act and Assert
    assertFalse(AsyncHttpClientConfigDefaults.defaultDisableHttpsEndpointIdentificationAlgorithm());
  }

  /**
   * Method under test:
   * {@link AsyncHttpClientConfigDefaults#defaultSslSessionCacheSize()}
   */
  @Test
  void testDefaultSslSessionCacheSize() {
    // Arrange, Act and Assert
    assertEquals(0, AsyncHttpClientConfigDefaults.defaultSslSessionCacheSize());
  }

  /**
   * Method under test:
   * {@link AsyncHttpClientConfigDefaults#defaultSslSessionTimeout()}
   */
  @Test
  void testDefaultSslSessionTimeout() {
    // Arrange, Act and Assert
    assertEquals(0, AsyncHttpClientConfigDefaults.defaultSslSessionTimeout());
  }

  /**
   * Method under test: {@link AsyncHttpClientConfigDefaults#defaultTcpNoDelay()}
   */
  @Test
  void testDefaultTcpNoDelay() {
    // Arrange, Act and Assert
    assertTrue(AsyncHttpClientConfigDefaults.defaultTcpNoDelay());
  }

  /**
   * Method under test:
   * {@link AsyncHttpClientConfigDefaults#defaultSoReuseAddress()}
   */
  @Test
  void testDefaultSoReuseAddress() {
    // Arrange, Act and Assert
    assertFalse(AsyncHttpClientConfigDefaults.defaultSoReuseAddress());
  }

  /**
   * Method under test: {@link AsyncHttpClientConfigDefaults#defaultSoKeepAlive()}
   */
  @Test
  void testDefaultSoKeepAlive() {
    // Arrange, Act and Assert
    assertTrue(AsyncHttpClientConfigDefaults.defaultSoKeepAlive());
  }

  /**
   * Method under test: {@link AsyncHttpClientConfigDefaults#defaultSoLinger()}
   */
  @Test
  void testDefaultSoLinger() {
    // Arrange, Act and Assert
    assertEquals(-1, AsyncHttpClientConfigDefaults.defaultSoLinger());
  }

  /**
   * Method under test: {@link AsyncHttpClientConfigDefaults#defaultSoSndBuf()}
   */
  @Test
  void testDefaultSoSndBuf() {
    // Arrange, Act and Assert
    assertEquals(-1, AsyncHttpClientConfigDefaults.defaultSoSndBuf());
  }

  /**
   * Method under test: {@link AsyncHttpClientConfigDefaults#defaultSoRcvBuf()}
   */
  @Test
  void testDefaultSoRcvBuf() {
    // Arrange, Act and Assert
    assertEquals(-1, AsyncHttpClientConfigDefaults.defaultSoRcvBuf());
  }

  /**
   * Method under test:
   * {@link AsyncHttpClientConfigDefaults#defaultHttpClientCodecMaxInitialLineLength()}
   */
  @Test
  void testDefaultHttpClientCodecMaxInitialLineLength() {
    // Arrange, Act and Assert
    assertEquals(4096, AsyncHttpClientConfigDefaults.defaultHttpClientCodecMaxInitialLineLength());
  }

  /**
   * Method under test:
   * {@link AsyncHttpClientConfigDefaults#defaultHttpClientCodecMaxHeaderSize()}
   */
  @Test
  void testDefaultHttpClientCodecMaxHeaderSize() {
    // Arrange, Act and Assert
    assertEquals(8192, AsyncHttpClientConfigDefaults.defaultHttpClientCodecMaxHeaderSize());
  }

  /**
   * Method under test:
   * {@link AsyncHttpClientConfigDefaults#defaultHttpClientCodecMaxChunkSize()}
   */
  @Test
  void testDefaultHttpClientCodecMaxChunkSize() {
    // Arrange, Act and Assert
    assertEquals(8192, AsyncHttpClientConfigDefaults.defaultHttpClientCodecMaxChunkSize());
  }

  /**
   * Method under test:
   * {@link AsyncHttpClientConfigDefaults#defaultHttpClientCodecInitialBufferSize()}
   */
  @Test
  void testDefaultHttpClientCodecInitialBufferSize() {
    // Arrange, Act and Assert
    assertEquals(128, AsyncHttpClientConfigDefaults.defaultHttpClientCodecInitialBufferSize());
  }

  /**
   * Method under test:
   * {@link AsyncHttpClientConfigDefaults#defaultDisableZeroCopy()}
   */
  @Test
  void testDefaultDisableZeroCopy() {
    // Arrange, Act and Assert
    assertFalse(AsyncHttpClientConfigDefaults.defaultDisableZeroCopy());
  }

  /**
   * Method under test:
   * {@link AsyncHttpClientConfigDefaults#defaultHandshakeTimeout()}
   */
  @Test
  void testDefaultHandshakeTimeout() {
    // Arrange, Act and Assert
    assertEquals(10000, AsyncHttpClientConfigDefaults.defaultHandshakeTimeout());
  }

  /**
   * Method under test:
   * {@link AsyncHttpClientConfigDefaults#defaultChunkedFileChunkSize()}
   */
  @Test
  void testDefaultChunkedFileChunkSize() {
    // Arrange, Act and Assert
    assertEquals(8192, AsyncHttpClientConfigDefaults.defaultChunkedFileChunkSize());
  }

  /**
   * Method under test:
   * {@link AsyncHttpClientConfigDefaults#defaultWebSocketMaxBufferSize()}
   */
  @Test
  void testDefaultWebSocketMaxBufferSize() {
    // Arrange, Act and Assert
    assertEquals(128000000, AsyncHttpClientConfigDefaults.defaultWebSocketMaxBufferSize());
  }

  /**
   * Method under test:
   * {@link AsyncHttpClientConfigDefaults#defaultWebSocketMaxFrameSize()}
   */
  @Test
  void testDefaultWebSocketMaxFrameSize() {
    // Arrange, Act and Assert
    assertEquals(10240, AsyncHttpClientConfigDefaults.defaultWebSocketMaxFrameSize());
  }

  /**
   * Method under test:
   * {@link AsyncHttpClientConfigDefaults#defaultKeepEncodingHeader()}
   */
  @Test
  void testDefaultKeepEncodingHeader() {
    // Arrange, Act and Assert
    assertFalse(AsyncHttpClientConfigDefaults.defaultKeepEncodingHeader());
  }

  /**
   * Method under test:
   * {@link AsyncHttpClientConfigDefaults#defaultShutdownQuietPeriod()}
   */
  @Test
  void testDefaultShutdownQuietPeriod() {
    // Arrange, Act and Assert
    assertEquals(2000000000L, AsyncHttpClientConfigDefaults.defaultShutdownQuietPeriod().toNanos());
  }

  /**
   * Method under test:
   * {@link AsyncHttpClientConfigDefaults#defaultShutdownTimeout()}
   */
  @Test
  void testDefaultShutdownTimeout() {
    // Arrange, Act and Assert
    assertEquals(15000000000L, AsyncHttpClientConfigDefaults.defaultShutdownTimeout().toNanos());
  }

  /**
   * Method under test:
   * {@link AsyncHttpClientConfigDefaults#defaultUseNativeTransport()}
   */
  @Test
  void testDefaultUseNativeTransport() {
    // Arrange, Act and Assert
    assertFalse(AsyncHttpClientConfigDefaults.defaultUseNativeTransport());
  }

  /**
   * Method under test:
   * {@link AsyncHttpClientConfigDefaults#defaultUseOnlyEpollNativeTransport()}
   */
  @Test
  void testDefaultUseOnlyEpollNativeTransport() {
    // Arrange, Act and Assert
    assertFalse(AsyncHttpClientConfigDefaults.defaultUseOnlyEpollNativeTransport());
  }

  /**
   * Method under test:
   * {@link AsyncHttpClientConfigDefaults#defaultIoThreadsCount()}
   */
  @Test
  void testDefaultIoThreadsCount() {
    // Arrange, Act and Assert
    assertEquals(8, AsyncHttpClientConfigDefaults.defaultIoThreadsCount());
  }

  /**
   * Method under test:
   * {@link AsyncHttpClientConfigDefaults#defaultHashedWheelTimerTickDuration()}
   */
  @Test
  void testDefaultHashedWheelTimerTickDuration() {
    // Arrange, Act and Assert
    assertEquals(100, AsyncHttpClientConfigDefaults.defaultHashedWheelTimerTickDuration());
  }

  /**
   * Method under test:
   * {@link AsyncHttpClientConfigDefaults#defaultHashedWheelTimerSize()}
   */
  @Test
  void testDefaultHashedWheelTimerSize() {
    // Arrange, Act and Assert
    assertEquals(512, AsyncHttpClientConfigDefaults.defaultHashedWheelTimerSize());
  }

  /**
   * Method under test:
   * {@link AsyncHttpClientConfigDefaults#defaultExpiredCookieEvictionDelay()}
   */
  @Test
  void testDefaultExpiredCookieEvictionDelay() {
    // Arrange, Act and Assert
    assertEquals(30000, AsyncHttpClientConfigDefaults.defaultExpiredCookieEvictionDelay());
  }
}
