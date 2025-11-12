package org.asynchttpclient.netty.ssl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.security.NoSuchAlgorithmException;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLEngine;
import javax.net.ssl.SSLParameters;
import org.asynchttpclient.AsyncHttpClientConfig;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

class JsseSslEngineFactoryDiffblueTest {
  /**
   * Test {@link JsseSslEngineFactory#newSslEngine(AsyncHttpClientConfig, String, int)}.
   *
   * <ul>
   *   <li>Then return PeerHost is {@code https://example.org/example}.
   * </ul>
   *
   * <p>Method under test: {@link JsseSslEngineFactory#newSslEngine(AsyncHttpClientConfig, String,
   * int)}
   */
  @Test
  @DisplayName(
      "Test newSslEngine(AsyncHttpClientConfig, String, int); then return PeerHost is 'https://example.org/example'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "SSLEngine JsseSslEngineFactory.newSslEngine(AsyncHttpClientConfig, String, int)"
  })
  void testNewSslEngine_thenReturnPeerHostIsHttpsExampleOrgExample()
      throws NoSuchAlgorithmException {
    // Arrange
    JsseSslEngineFactory jsseSslEngineFactory = new JsseSslEngineFactory(SSLContext.getDefault());

    AsyncHttpClientConfig config = mock(AsyncHttpClientConfig.class);
    when(config.isDisableHttpsEndpointIdentificationAlgorithm()).thenReturn(true);

    // Act
    SSLEngine actualNewSslEngineResult =
        jsseSslEngineFactory.newSslEngine(config, "https://example.org/example", 8080);

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
    assertEquals(16709, sSLParameters.getMaximumPacketSize());
    assertEquals(37, sSLParameters.getCipherSuites().length);
    assertEquals(4, sSLParameters.getProtocols().length);
    assertEquals(8080, actualNewSslEngineResult.getPeerPort());
    assertFalse(sSLParameters.getEnableRetransmissions());
    assertFalse(sSLParameters.getNeedClientAuth());
    assertFalse(sSLParameters.getWantClientAuth());
    assertTrue(sSLParameters.getUseCipherSuitesOrder());
  }
}
