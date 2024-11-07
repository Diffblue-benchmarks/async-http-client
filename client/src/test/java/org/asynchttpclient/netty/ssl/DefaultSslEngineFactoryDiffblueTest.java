package org.asynchttpclient.netty.ssl;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import io.netty.handler.ssl.JdkSslClientContext;
import io.netty.handler.ssl.SslContextBuilder;
import javax.net.ssl.SSLEngine;
import javax.net.ssl.SSLException;
import javax.net.ssl.SSLParameters;
import org.asynchttpclient.AsyncHttpClientConfig;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class DefaultSslEngineFactoryDiffblueTest {
  /**
   * Test
   * {@link DefaultSslEngineFactory#newSslEngine(AsyncHttpClientConfig, String, int)}.
   * <ul>
   *   <li>Given {@code true}.</li>
   *   <li>Then return PeerHost is {@code null}.</li>
   * </ul>
   * <p>
   * Method under test:
   * {@link DefaultSslEngineFactory#newSslEngine(AsyncHttpClientConfig, String, int)}
   */
  @Test
  @DisplayName("Test newSslEngine(AsyncHttpClientConfig, String, int); given 'true'; then return PeerHost is 'null'")
  void testNewSslEngine_givenTrue_thenReturnPeerHostIsNull() throws SSLException {
    // Arrange
    AsyncHttpClientConfig config = mock(AsyncHttpClientConfig.class);
    when(config.getSslContext()).thenReturn(new JdkSslClientContext());

    DefaultSslEngineFactory defaultSslEngineFactory = new DefaultSslEngineFactory();
    defaultSslEngineFactory.init(config);
    AsyncHttpClientConfig config2 = mock(AsyncHttpClientConfig.class);
    when(config2.isDisableHttpsEndpointIdentificationAlgorithm()).thenReturn(true);

    // Act
    SSLEngine actualNewSslEngineResult = defaultSslEngineFactory.newSslEngine(config2, "https://example.org/example",
        8080);

    // Assert
    verify(config, atLeast(1)).getSslContext();
    verify(config2, atLeast(1)).isDisableHttpsEndpointIdentificationAlgorithm();
    assertNull(actualNewSslEngineResult.getApplicationProtocol());
    assertNull(actualNewSslEngineResult.getHandshakeApplicationProtocol());
    assertNull(actualNewSslEngineResult.getPeerHost());
    SSLParameters sSLParameters = actualNewSslEngineResult.getSSLParameters();
    assertNull(sSLParameters.getEndpointIdentificationAlgorithm());
    assertNull(sSLParameters.getSNIMatchers());
    assertNull(sSLParameters.getServerNames());
    assertNull(actualNewSslEngineResult.getHandshakeApplicationProtocolSelector());
    assertNull(actualNewSslEngineResult.getHandshakeSession());
    assertEquals(-1, actualNewSslEngineResult.getPeerPort());
    assertEquals(0, sSLParameters.getApplicationProtocols().length);
    assertFalse(sSLParameters.getNeedClientAuth());
    assertFalse(sSLParameters.getWantClientAuth());
    assertTrue(sSLParameters.getUseCipherSuitesOrder());
    assertArrayEquals(new String[]{"TLSv1.3", "TLSv1.2"}, sSLParameters.getProtocols());
    assertArrayEquals(new String[]{"TLS_ECDHE_ECDSA_WITH_AES_256_GCM_SHA384", "TLS_ECDHE_ECDSA_WITH_AES_128_GCM_SHA256",
        "TLS_ECDHE_RSA_WITH_AES_128_GCM_SHA256", "TLS_ECDHE_RSA_WITH_AES_256_GCM_SHA384",
        "TLS_ECDHE_RSA_WITH_AES_128_CBC_SHA", "TLS_ECDHE_RSA_WITH_AES_256_CBC_SHA", "TLS_RSA_WITH_AES_128_GCM_SHA256",
        "TLS_RSA_WITH_AES_128_CBC_SHA", "TLS_RSA_WITH_AES_256_CBC_SHA", "TLS_AES_128_GCM_SHA256",
        "TLS_AES_256_GCM_SHA384"}, sSLParameters.getCipherSuites());
  }

  /**
   * Test
   * {@link DefaultSslEngineFactory#newSslEngine(AsyncHttpClientConfig, String, int)}.
   * <ul>
   *   <li>Then return SSLParameters EndpointIdentificationAlgorithm is
   * {@code HTTPS}.</li>
   * </ul>
   * <p>
   * Method under test:
   * {@link DefaultSslEngineFactory#newSslEngine(AsyncHttpClientConfig, String, int)}
   */
  @Test
  @DisplayName("Test newSslEngine(AsyncHttpClientConfig, String, int); then return SSLParameters EndpointIdentificationAlgorithm is 'HTTPS'")
  void testNewSslEngine_thenReturnSSLParametersEndpointIdentificationAlgorithmIsHttps() throws SSLException {
    // Arrange
    AsyncHttpClientConfig config = mock(AsyncHttpClientConfig.class);
    when(config.getSslContext()).thenReturn(new JdkSslClientContext());

    DefaultSslEngineFactory defaultSslEngineFactory = new DefaultSslEngineFactory();
    defaultSslEngineFactory.init(config);
    AsyncHttpClientConfig config2 = mock(AsyncHttpClientConfig.class);
    when(config2.isDisableHttpsEndpointIdentificationAlgorithm()).thenReturn(false);

    // Act
    SSLEngine actualNewSslEngineResult = defaultSslEngineFactory.newSslEngine(config2, "https://example.org/example",
        8080);

    // Assert
    verify(config, atLeast(1)).getSslContext();
    verify(config2, atLeast(1)).isDisableHttpsEndpointIdentificationAlgorithm();
    SSLParameters sSLParameters = actualNewSslEngineResult.getSSLParameters();
    assertEquals("HTTPS", sSLParameters.getEndpointIdentificationAlgorithm());
    assertEquals("https://example.org/example", actualNewSslEngineResult.getPeerHost());
    assertNull(actualNewSslEngineResult.getApplicationProtocol());
    assertNull(actualNewSslEngineResult.getHandshakeApplicationProtocol());
    assertNull(sSLParameters.getSNIMatchers());
    assertNull(sSLParameters.getServerNames());
    assertNull(actualNewSslEngineResult.getHandshakeApplicationProtocolSelector());
    assertNull(actualNewSslEngineResult.getHandshakeSession());
    assertEquals(0, sSLParameters.getApplicationProtocols().length);
    assertEquals(8080, actualNewSslEngineResult.getPeerPort());
    assertFalse(sSLParameters.getNeedClientAuth());
    assertFalse(sSLParameters.getWantClientAuth());
    assertTrue(sSLParameters.getUseCipherSuitesOrder());
    assertArrayEquals(new String[]{"TLSv1.3", "TLSv1.2"}, sSLParameters.getProtocols());
    assertArrayEquals(new String[]{"TLS_ECDHE_ECDSA_WITH_AES_256_GCM_SHA384", "TLS_ECDHE_ECDSA_WITH_AES_128_GCM_SHA256",
        "TLS_ECDHE_RSA_WITH_AES_128_GCM_SHA256", "TLS_ECDHE_RSA_WITH_AES_256_GCM_SHA384",
        "TLS_ECDHE_RSA_WITH_AES_128_CBC_SHA", "TLS_ECDHE_RSA_WITH_AES_256_CBC_SHA", "TLS_RSA_WITH_AES_128_GCM_SHA256",
        "TLS_RSA_WITH_AES_128_CBC_SHA", "TLS_RSA_WITH_AES_256_CBC_SHA", "TLS_AES_128_GCM_SHA256",
        "TLS_AES_256_GCM_SHA384"}, sSLParameters.getCipherSuites());
  }

  /**
   * Test {@link DefaultSslEngineFactory#init(AsyncHttpClientConfig)}.
   * <p>
   * Method under test:
   * {@link DefaultSslEngineFactory#init(AsyncHttpClientConfig)}
   */
  @Test
  @DisplayName("Test init(AsyncHttpClientConfig)")
  void testInit() throws SSLException {
    // Arrange
    DefaultSslEngineFactory defaultSslEngineFactory = new DefaultSslEngineFactory();
    AsyncHttpClientConfig config = mock(AsyncHttpClientConfig.class);
    when(config.getSslContext()).thenReturn(new JdkSslClientContext());

    // Act
    defaultSslEngineFactory.init(config);

    // Assert
    verify(config, atLeast(1)).getSslContext();
  }

  /**
   * Test {@link DefaultSslEngineFactory#destroy()}.
   * <ul>
   *   <li>Then calls {@link AsyncHttpClientConfig#getSslContext()}.</li>
   * </ul>
   * <p>
   * Method under test: {@link DefaultSslEngineFactory#destroy()}
   */
  @Test
  @DisplayName("Test destroy(); then calls getSslContext()")
  void testDestroy_thenCallsGetSslContext() throws SSLException {
    // Arrange
    AsyncHttpClientConfig config = mock(AsyncHttpClientConfig.class);
    when(config.getSslContext()).thenReturn(new JdkSslClientContext());

    DefaultSslEngineFactory defaultSslEngineFactory = new DefaultSslEngineFactory();
    defaultSslEngineFactory.init(config);

    // Act
    defaultSslEngineFactory.destroy();

    // Assert that nothing has changed
    verify(config, atLeast(1)).getSslContext();
  }

  /**
   * Test
   * {@link DefaultSslEngineFactory#configureSslContextBuilder(SslContextBuilder)}.
   * <p>
   * Method under test:
   * {@link DefaultSslEngineFactory#configureSslContextBuilder(SslContextBuilder)}
   */
  @Test
  @DisplayName("Test configureSslContextBuilder(SslContextBuilder)")
  void testConfigureSslContextBuilder() {
    // Arrange
    DefaultSslEngineFactory defaultSslEngineFactory = new DefaultSslEngineFactory();
    SslContextBuilder builder = SslContextBuilder.forClient();

    // Act and Assert
    assertSame(builder, defaultSslEngineFactory.configureSslContextBuilder(builder));
  }
}
