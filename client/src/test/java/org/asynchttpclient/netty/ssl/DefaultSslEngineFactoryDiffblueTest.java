package org.asynchttpclient.netty.ssl;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import io.netty.handler.ssl.JdkSslClientContext;
import io.netty.handler.ssl.SslContextBuilder;
import javax.net.ssl.SSLException;
import org.asynchttpclient.AsyncHttpClientConfig;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

class DefaultSslEngineFactoryDiffblueTest {
  /**
   * Test {@link DefaultSslEngineFactory#init(AsyncHttpClientConfig)}.
   *
   * <ul>
   *   <li>Given {@code false}.
   *   <li>Then calls {@link AsyncHttpClientConfig#getEnabledCipherSuites()}.
   * </ul>
   *
   * <p>Method under test: {@link DefaultSslEngineFactory#init(AsyncHttpClientConfig)}
   */
  @Test
  @DisplayName(
      "Test init(AsyncHttpClientConfig); given 'false'; then calls getEnabledCipherSuites()")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void DefaultSslEngineFactory.init(AsyncHttpClientConfig)"})
  void testInit_givenFalse_thenCallsGetEnabledCipherSuites() throws SSLException {
    // Arrange
    DefaultSslEngineFactory defaultSslEngineFactory = new DefaultSslEngineFactory();

    AsyncHttpClientConfig config = mock(AsyncHttpClientConfig.class);
    when(config.isUseInsecureTrustManager()).thenReturn(false);
    when(config.isUseOpenSsl()).thenReturn(false);
    when(config.getSslSessionCacheSize()).thenReturn(3);
    when(config.getSslSessionTimeout()).thenReturn(10);
    when(config.getSslContext()).thenReturn(null);
    when(config.getEnabledCipherSuites()).thenReturn(new String[] {"https://example.org/example"});
    when(config.getEnabledProtocols()).thenReturn(new String[] {"https://example.org/example"});

    // Act
    defaultSslEngineFactory.init(config);

    // Assert
    verify(config, atLeast(1)).getEnabledCipherSuites();
    verify(config, atLeast(1)).getEnabledProtocols();
    verify(config).getSslContext();
    verify(config).getSslSessionCacheSize();
    verify(config).getSslSessionTimeout();
    verify(config).isUseInsecureTrustManager();
    verify(config).isUseOpenSsl();
  }

  /**
   * Test {@link DefaultSslEngineFactory#init(AsyncHttpClientConfig)}.
   *
   * <ul>
   *   <li>Given {@link JdkSslClientContext#JdkSslClientContext()}.
   * </ul>
   *
   * <p>Method under test: {@link DefaultSslEngineFactory#init(AsyncHttpClientConfig)}
   */
  @Test
  @DisplayName("Test init(AsyncHttpClientConfig); given JdkSslClientContext()")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void DefaultSslEngineFactory.init(AsyncHttpClientConfig)"})
  void testInit_givenJdkSslClientContext() throws SSLException {
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
   * Test {@link DefaultSslEngineFactory#configureSslContextBuilder(SslContextBuilder)}.
   *
   * <p>Method under test: {@link
   * DefaultSslEngineFactory#configureSslContextBuilder(SslContextBuilder)}
   */
  @Test
  @DisplayName("Test configureSslContextBuilder(SslContextBuilder)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "SslContextBuilder DefaultSslEngineFactory.configureSslContextBuilder(SslContextBuilder)"
  })
  void testConfigureSslContextBuilder() {
    // Arrange
    DefaultSslEngineFactory defaultSslEngineFactory = new DefaultSslEngineFactory();
    SslContextBuilder builder = SslContextBuilder.forClient();

    // Act
    SslContextBuilder actualConfigureSslContextBuilderResult =
        defaultSslEngineFactory.configureSslContextBuilder(builder);

    // Assert
    assertSame(builder, actualConfigureSslContextBuilderResult);
  }
}
