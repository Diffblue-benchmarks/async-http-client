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
   * <p>Method under test: {@link DefaultSslEngineFactory#init(AsyncHttpClientConfig)}
   */
  @Test
  @DisplayName("Test init(AsyncHttpClientConfig)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void DefaultSslEngineFactory.init(AsyncHttpClientConfig)"})
  void testInit() throws SSLException {
    // Arrange
    DefaultSslEngineFactory defaultSslEngineFactory = new DefaultSslEngineFactory();

    AsyncHttpClientConfig config = mock(AsyncHttpClientConfig.class);
    when(config.isUseInsecureTrustManager()).thenReturn(false);
    when(config.isUseOpenSsl()).thenReturn(false);
    when(config.getSslContext()).thenReturn(null);
    when(config.getEnabledCipherSuites()).thenReturn(new String[] {"https://example.org/example"});
    when(config.getEnabledProtocols()).thenReturn(null);
    when(config.getSslSessionCacheSize()).thenReturn(3);
    when(config.getSslSessionTimeout()).thenReturn(10);

    // Act
    defaultSslEngineFactory.init(config);

    // Assert
    verify(config, atLeast(1)).getEnabledCipherSuites();
    verify(config).getEnabledProtocols();
    verify(config).getSslContext();
    verify(config).getSslSessionCacheSize();
    verify(config).getSslSessionTimeout();
    verify(config).isUseInsecureTrustManager();
    verify(config).isUseOpenSsl();
  }

  /**
   * Test {@link DefaultSslEngineFactory#init(AsyncHttpClientConfig)}.
   *
   * <p>Method under test: {@link DefaultSslEngineFactory#init(AsyncHttpClientConfig)}
   */
  @Test
  @DisplayName("Test init(AsyncHttpClientConfig)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void DefaultSslEngineFactory.init(AsyncHttpClientConfig)"})
  void testInit2() throws SSLException {
    // Arrange
    DefaultSslEngineFactory defaultSslEngineFactory = new DefaultSslEngineFactory();

    AsyncHttpClientConfig config = mock(AsyncHttpClientConfig.class);
    when(config.isFilterInsecureCipherSuites()).thenReturn(false);
    when(config.isUseInsecureTrustManager()).thenReturn(false);
    when(config.isUseOpenSsl()).thenReturn(false);
    when(config.getSslContext()).thenReturn(null);
    when(config.getEnabledCipherSuites()).thenReturn(null);
    when(config.getEnabledProtocols()).thenReturn(new String[] {"https://example.org/example"});
    when(config.getSslSessionCacheSize()).thenReturn(3);
    when(config.getSslSessionTimeout()).thenReturn(10);

    // Act
    defaultSslEngineFactory.init(config);

    // Assert
    verify(config).getEnabledCipherSuites();
    verify(config, atLeast(1)).getEnabledProtocols();
    verify(config).getSslContext();
    verify(config).getSslSessionCacheSize();
    verify(config).getSslSessionTimeout();
    verify(config).isFilterInsecureCipherSuites();
    verify(config).isUseInsecureTrustManager();
    verify(config).isUseOpenSsl();
  }

  /**
   * Test {@link DefaultSslEngineFactory#init(AsyncHttpClientConfig)}.
   *
   * <ul>
   *   <li>Given empty array of {@link String}.
   * </ul>
   *
   * <p>Method under test: {@link DefaultSslEngineFactory#init(AsyncHttpClientConfig)}
   */
  @Test
  @DisplayName("Test init(AsyncHttpClientConfig); given empty array of String")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void DefaultSslEngineFactory.init(AsyncHttpClientConfig)"})
  void testInit_givenEmptyArrayOfString() throws SSLException {
    // Arrange
    DefaultSslEngineFactory defaultSslEngineFactory = new DefaultSslEngineFactory();

    AsyncHttpClientConfig config = mock(AsyncHttpClientConfig.class);
    when(config.isFilterInsecureCipherSuites()).thenReturn(false);
    when(config.isUseInsecureTrustManager()).thenReturn(false);
    when(config.isUseOpenSsl()).thenReturn(false);
    when(config.getSslContext()).thenReturn(null);
    when(config.getEnabledCipherSuites()).thenReturn(new String[] {});
    when(config.getEnabledProtocols()).thenReturn(null);
    when(config.getSslSessionCacheSize()).thenReturn(3);
    when(config.getSslSessionTimeout()).thenReturn(10);

    // Act
    defaultSslEngineFactory.init(config);

    // Assert
    verify(config).getEnabledCipherSuites();
    verify(config).getEnabledProtocols();
    verify(config).getSslContext();
    verify(config).getSslSessionCacheSize();
    verify(config).getSslSessionTimeout();
    verify(config).isFilterInsecureCipherSuites();
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
   * Test {@link DefaultSslEngineFactory#init(AsyncHttpClientConfig)}.
   *
   * <ul>
   *   <li>Given {@code true}.
   *   <li>When {@link AsyncHttpClientConfig} {@link
   *       AsyncHttpClientConfig#isUseInsecureTrustManager()} return {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link DefaultSslEngineFactory#init(AsyncHttpClientConfig)}
   */
  @Test
  @DisplayName(
      "Test init(AsyncHttpClientConfig); given 'true'; when AsyncHttpClientConfig isUseInsecureTrustManager() return 'true'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void DefaultSslEngineFactory.init(AsyncHttpClientConfig)"})
  void testInit_givenTrue_whenAsyncHttpClientConfigIsUseInsecureTrustManagerReturnTrue()
      throws SSLException {
    // Arrange
    DefaultSslEngineFactory defaultSslEngineFactory = new DefaultSslEngineFactory();

    AsyncHttpClientConfig config = mock(AsyncHttpClientConfig.class);
    when(config.isFilterInsecureCipherSuites()).thenReturn(false);
    when(config.isUseInsecureTrustManager()).thenReturn(true);
    when(config.isUseOpenSsl()).thenReturn(false);
    when(config.getSslContext()).thenReturn(null);
    when(config.getEnabledCipherSuites()).thenReturn(null);
    when(config.getEnabledProtocols()).thenReturn(null);
    when(config.getSslSessionCacheSize()).thenReturn(3);
    when(config.getSslSessionTimeout()).thenReturn(10);

    // Act
    defaultSslEngineFactory.init(config);

    // Assert
    verify(config).getEnabledCipherSuites();
    verify(config).getEnabledProtocols();
    verify(config).getSslContext();
    verify(config).getSslSessionCacheSize();
    verify(config).getSslSessionTimeout();
    verify(config).isFilterInsecureCipherSuites();
    verify(config).isUseInsecureTrustManager();
    verify(config).isUseOpenSsl();
  }

  /**
   * Test {@link DefaultSslEngineFactory#init(AsyncHttpClientConfig)}.
   *
   * <ul>
   *   <li>When {@link AsyncHttpClientConfig} {@link
   *       AsyncHttpClientConfig#isFilterInsecureCipherSuites()} return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link DefaultSslEngineFactory#init(AsyncHttpClientConfig)}
   */
  @Test
  @DisplayName(
      "Test init(AsyncHttpClientConfig); when AsyncHttpClientConfig isFilterInsecureCipherSuites() return 'false'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void DefaultSslEngineFactory.init(AsyncHttpClientConfig)"})
  void testInit_whenAsyncHttpClientConfigIsFilterInsecureCipherSuitesReturnFalse()
      throws SSLException {
    // Arrange
    DefaultSslEngineFactory defaultSslEngineFactory = new DefaultSslEngineFactory();

    AsyncHttpClientConfig config = mock(AsyncHttpClientConfig.class);
    when(config.isFilterInsecureCipherSuites()).thenReturn(false);
    when(config.isUseInsecureTrustManager()).thenReturn(false);
    when(config.isUseOpenSsl()).thenReturn(false);
    when(config.getSslContext()).thenReturn(null);
    when(config.getEnabledCipherSuites()).thenReturn(null);
    when(config.getEnabledProtocols()).thenReturn(null);
    when(config.getSslSessionCacheSize()).thenReturn(3);
    when(config.getSslSessionTimeout()).thenReturn(10);

    // Act
    defaultSslEngineFactory.init(config);

    // Assert
    verify(config).getEnabledCipherSuites();
    verify(config).getEnabledProtocols();
    verify(config).getSslContext();
    verify(config).getSslSessionCacheSize();
    verify(config).getSslSessionTimeout();
    verify(config).isFilterInsecureCipherSuites();
    verify(config).isUseInsecureTrustManager();
    verify(config).isUseOpenSsl();
  }

  /**
   * Test {@link DefaultSslEngineFactory#init(AsyncHttpClientConfig)}.
   *
   * <ul>
   *   <li>When {@link AsyncHttpClientConfig} {@link
   *       AsyncHttpClientConfig#isFilterInsecureCipherSuites()} return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link DefaultSslEngineFactory#init(AsyncHttpClientConfig)}
   */
  @Test
  @DisplayName(
      "Test init(AsyncHttpClientConfig); when AsyncHttpClientConfig isFilterInsecureCipherSuites() return 'false'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void DefaultSslEngineFactory.init(AsyncHttpClientConfig)"})
  void testInit_whenAsyncHttpClientConfigIsFilterInsecureCipherSuitesReturnFalse2()
      throws SSLException {
    // Arrange
    DefaultSslEngineFactory defaultSslEngineFactory = new DefaultSslEngineFactory();

    AsyncHttpClientConfig config = mock(AsyncHttpClientConfig.class);
    when(config.isFilterInsecureCipherSuites()).thenReturn(false);
    when(config.isUseInsecureTrustManager()).thenReturn(false);
    when(config.isUseOpenSsl()).thenReturn(false);
    when(config.getSslContext()).thenReturn(null);
    when(config.getEnabledCipherSuites()).thenReturn(null);
    when(config.getEnabledProtocols()).thenReturn(null);
    when(config.getSslSessionCacheSize()).thenReturn(3);
    when(config.getSslSessionTimeout()).thenReturn(10);

    // Act
    defaultSslEngineFactory.init(config);

    // Assert
    verify(config).getEnabledCipherSuites();
    verify(config).getEnabledProtocols();
    verify(config).getSslContext();
    verify(config).getSslSessionCacheSize();
    verify(config).getSslSessionTimeout();
    verify(config).isFilterInsecureCipherSuites();
    verify(config).isUseInsecureTrustManager();
    verify(config).isUseOpenSsl();
  }

  /**
   * Test {@link DefaultSslEngineFactory#init(AsyncHttpClientConfig)}.
   *
   * <ul>
   *   <li>When {@link AsyncHttpClientConfig} {@link
   *       AsyncHttpClientConfig#isFilterInsecureCipherSuites()} return {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link DefaultSslEngineFactory#init(AsyncHttpClientConfig)}
   */
  @Test
  @DisplayName(
      "Test init(AsyncHttpClientConfig); when AsyncHttpClientConfig isFilterInsecureCipherSuites() return 'true'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void DefaultSslEngineFactory.init(AsyncHttpClientConfig)"})
  void testInit_whenAsyncHttpClientConfigIsFilterInsecureCipherSuitesReturnTrue()
      throws SSLException {
    // Arrange
    DefaultSslEngineFactory defaultSslEngineFactory = new DefaultSslEngineFactory();

    AsyncHttpClientConfig config = mock(AsyncHttpClientConfig.class);
    when(config.isFilterInsecureCipherSuites()).thenReturn(true);
    when(config.isUseInsecureTrustManager()).thenReturn(false);
    when(config.isUseOpenSsl()).thenReturn(false);
    when(config.getSslContext()).thenReturn(null);
    when(config.getEnabledCipherSuites()).thenReturn(null);
    when(config.getEnabledProtocols()).thenReturn(null);
    when(config.getSslSessionCacheSize()).thenReturn(3);
    when(config.getSslSessionTimeout()).thenReturn(10);

    // Act
    defaultSslEngineFactory.init(config);

    // Assert
    verify(config).getEnabledCipherSuites();
    verify(config).getEnabledProtocols();
    verify(config).getSslContext();
    verify(config).getSslSessionCacheSize();
    verify(config).getSslSessionTimeout();
    verify(config).isFilterInsecureCipherSuites();
    verify(config).isUseInsecureTrustManager();
    verify(config).isUseOpenSsl();
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
