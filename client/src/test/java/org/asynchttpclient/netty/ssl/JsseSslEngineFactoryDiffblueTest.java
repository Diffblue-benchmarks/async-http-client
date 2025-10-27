package org.asynchttpclient.netty.ssl;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import java.security.NoSuchAlgorithmException;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLEngine;
import javax.net.ssl.SSLParameters;
import org.asynchttpclient.AsyncHttpClientConfig;
import org.junit.jupiter.api.Test;

class JsseSslEngineFactoryDiffblueTest {
  /**
   * Method under test:
   * {@link JsseSslEngineFactory#newSslEngine(AsyncHttpClientConfig, String, int)}
   */
  @Test
  void testNewSslEngine() throws NoSuchAlgorithmException {
    // Arrange
    JsseSslEngineFactory jsseSslEngineFactory = new JsseSslEngineFactory(SSLContext.getDefault());
    AsyncHttpClientConfig config = mock(AsyncHttpClientConfig.class);
    when(config.isDisableHttpsEndpointIdentificationAlgorithm()).thenReturn(true);

    // Act
    SSLEngine actualNewSslEngineResult = jsseSslEngineFactory.newSslEngine(config, "https://example.org/example", 8080);

    // Assert
    verify(config).isDisableHttpsEndpointIdentificationAlgorithm();
    assertEquals("https://example.org/example", actualNewSslEngineResult.getPeerHost());
    assertNull(actualNewSslEngineResult.getApplicationProtocol());
    assertNull(actualNewSslEngineResult.getHandshakeApplicationProtocol());
    SSLParameters sSLParameters = actualNewSslEngineResult.getSSLParameters();
    assertNull(sSLParameters.getEndpointIdentificationAlgorithm());
    assertNull(sSLParameters.getSNIMatchers());
    assertNull(sSLParameters.getServerNames());
    assertNull(actualNewSslEngineResult.getHandshakeApplicationProtocolSelector());
    assertNull(actualNewSslEngineResult.getHandshakeSession());
    assertEquals(0, sSLParameters.getApplicationProtocols().length);
    assertEquals(8080, actualNewSslEngineResult.getPeerPort());
    assertFalse(sSLParameters.getNeedClientAuth());
    assertFalse(sSLParameters.getWantClientAuth());
    assertTrue(sSLParameters.getUseCipherSuitesOrder());
    assertArrayEquals(new String[]{"TLSv1.3", "TLSv1.2", "TLSv1.1", "TLSv1"}, sSLParameters.getProtocols());
    assertArrayEquals(new String[]{"TLS_AES_256_GCM_SHA384", "TLS_AES_128_GCM_SHA256", "TLS_CHACHA20_POLY1305_SHA256",
        "TLS_ECDHE_ECDSA_WITH_AES_256_GCM_SHA384", "TLS_ECDHE_ECDSA_WITH_AES_128_GCM_SHA256",
        "TLS_ECDHE_ECDSA_WITH_CHACHA20_POLY1305_SHA256", "TLS_ECDHE_RSA_WITH_AES_256_GCM_SHA384",
        "TLS_ECDHE_RSA_WITH_CHACHA20_POLY1305_SHA256", "TLS_ECDHE_RSA_WITH_AES_128_GCM_SHA256",
        "TLS_DHE_RSA_WITH_AES_256_GCM_SHA384", "TLS_DHE_RSA_WITH_CHACHA20_POLY1305_SHA256",
        "TLS_DHE_DSS_WITH_AES_256_GCM_SHA384", "TLS_DHE_RSA_WITH_AES_128_GCM_SHA256",
        "TLS_DHE_DSS_WITH_AES_128_GCM_SHA256", "TLS_ECDHE_ECDSA_WITH_AES_256_CBC_SHA384",
        "TLS_ECDHE_RSA_WITH_AES_256_CBC_SHA384", "TLS_ECDHE_ECDSA_WITH_AES_128_CBC_SHA256",
        "TLS_ECDHE_RSA_WITH_AES_128_CBC_SHA256", "TLS_DHE_RSA_WITH_AES_256_CBC_SHA256",
        "TLS_DHE_DSS_WITH_AES_256_CBC_SHA256", "TLS_DHE_RSA_WITH_AES_128_CBC_SHA256",
        "TLS_DHE_DSS_WITH_AES_128_CBC_SHA256", "TLS_ECDHE_ECDSA_WITH_AES_256_CBC_SHA",
        "TLS_ECDHE_RSA_WITH_AES_256_CBC_SHA", "TLS_ECDHE_ECDSA_WITH_AES_128_CBC_SHA",
        "TLS_ECDHE_RSA_WITH_AES_128_CBC_SHA", "TLS_DHE_RSA_WITH_AES_256_CBC_SHA", "TLS_DHE_DSS_WITH_AES_256_CBC_SHA",
        "TLS_DHE_RSA_WITH_AES_128_CBC_SHA", "TLS_DHE_DSS_WITH_AES_128_CBC_SHA", "TLS_RSA_WITH_AES_256_GCM_SHA384",
        "TLS_RSA_WITH_AES_128_GCM_SHA256", "TLS_RSA_WITH_AES_256_CBC_SHA256", "TLS_RSA_WITH_AES_128_CBC_SHA256",
        "TLS_RSA_WITH_AES_256_CBC_SHA", "TLS_RSA_WITH_AES_128_CBC_SHA", "TLS_EMPTY_RENEGOTIATION_INFO_SCSV"},
        sSLParameters.getCipherSuites());
  }
}
