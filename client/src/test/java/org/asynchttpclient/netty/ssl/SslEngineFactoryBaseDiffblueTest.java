package org.asynchttpclient.netty.ssl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import io.netty.handler.ssl.JdkSslClientContext;
import javax.net.ssl.SSLException;
import org.asynchttpclient.AsyncHttpClientConfig;
import org.junit.jupiter.api.Test;

class SslEngineFactoryBaseDiffblueTest {
  /**
   * Method under test: {@link SslEngineFactoryBase#domain(String)}
   */
  @Test
  void testDomain() {
    // Arrange, Act and Assert
    assertEquals("https://example.org/example", (new DefaultSslEngineFactory()).domain("https://example.org/example"));
  }

  /**
   * Method under test: {@link SslEngineFactoryBase#domain(String)}
   */
  @Test
  void testDomain2() throws SSLException {
    // Arrange
    AsyncHttpClientConfig config = mock(AsyncHttpClientConfig.class);
    when(config.getSslContext()).thenReturn(new JdkSslClientContext());

    DefaultSslEngineFactory defaultSslEngineFactory = new DefaultSslEngineFactory();
    defaultSslEngineFactory.init(config);

    // Act
    String actualDomainResult = defaultSslEngineFactory.domain("https://example.org/example");

    // Assert
    verify(config, atLeast(1)).getSslContext();
    assertEquals("https://example.org/example", actualDomainResult);
  }
}
