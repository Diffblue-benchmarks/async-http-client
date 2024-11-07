package org.asynchttpclient;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.Mockito.mock;
import io.netty.channel.ChannelOption;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.asynchttpclient.DefaultAsyncHttpClientConfig.Builder;
import org.asynchttpclient.filter.IOExceptionFilter;
import org.asynchttpclient.filter.RequestFilter;
import org.asynchttpclient.filter.ResponseFilter;
import org.asynchttpclient.proxy.ProxyServer;
import org.asynchttpclient.proxy.ProxyType;
import org.asynchttpclient.uri.Uri;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class DefaultAsyncHttpClientConfigDiffblueTest {
  /**
   * Test Builder {@link Builder#addChannelOption(ChannelOption, Object)}.
   * <p>
   * Method under test:
   * {@link DefaultAsyncHttpClientConfig.Builder#addChannelOption(ChannelOption, Object)}
   */
  @Test
  @DisplayName("Test Builder addChannelOption(ChannelOption, Object)")
  void testBuilderAddChannelOption() {
    // Arrange
    DefaultAsyncHttpClientConfig.Builder configResult = Dsl.config();

    // Act
    DefaultAsyncHttpClientConfig.Builder actualAddChannelOptionResult = configResult
        .addChannelOption(mock(ChannelOption.class), "Value");

    // Assert
    assertEquals(1, configResult.build().getChannelOptions().size());
    assertSame(configResult, actualAddChannelOptionResult);
  }

  /**
   * Test Builder {@link Builder#addIOExceptionFilter(IOExceptionFilter)}.
   * <p>
   * Method under test:
   * {@link DefaultAsyncHttpClientConfig.Builder#addIOExceptionFilter(IOExceptionFilter)}
   */
  @Test
  @DisplayName("Test Builder addIOExceptionFilter(IOExceptionFilter)")
  void testBuilderAddIOExceptionFilter() {
    // Arrange
    DefaultAsyncHttpClientConfig.Builder configResult = Dsl.config();
    IOExceptionFilter ioExceptionFilter = mock(IOExceptionFilter.class);

    // Act
    DefaultAsyncHttpClientConfig.Builder actualAddIOExceptionFilterResult = configResult
        .addIOExceptionFilter(ioExceptionFilter);

    // Assert
    List<IOExceptionFilter> ioExceptionFilters = configResult.build().getIoExceptionFilters();
    assertEquals(1, ioExceptionFilters.size());
    assertSame(configResult, actualAddIOExceptionFilterResult);
    assertSame(ioExceptionFilter, ioExceptionFilters.get(0));
  }

  /**
   * Test Builder {@link Builder#addRequestFilter(RequestFilter)}.
   * <p>
   * Method under test:
   * {@link DefaultAsyncHttpClientConfig.Builder#addRequestFilter(RequestFilter)}
   */
  @Test
  @DisplayName("Test Builder addRequestFilter(RequestFilter)")
  void testBuilderAddRequestFilter() {
    // Arrange
    DefaultAsyncHttpClientConfig.Builder configResult = Dsl.config();
    RequestFilter requestFilter = mock(RequestFilter.class);

    // Act
    DefaultAsyncHttpClientConfig.Builder actualAddRequestFilterResult = configResult.addRequestFilter(requestFilter);

    // Assert
    List<RequestFilter> requestFilters = configResult.build().getRequestFilters();
    assertEquals(1, requestFilters.size());
    assertSame(configResult, actualAddRequestFilterResult);
    assertSame(requestFilter, requestFilters.get(0));
  }

  /**
   * Test Builder {@link Builder#addResponseFilter(ResponseFilter)}.
   * <p>
   * Method under test:
   * {@link DefaultAsyncHttpClientConfig.Builder#addResponseFilter(ResponseFilter)}
   */
  @Test
  @DisplayName("Test Builder addResponseFilter(ResponseFilter)")
  void testBuilderAddResponseFilter() {
    // Arrange
    DefaultAsyncHttpClientConfig.Builder configResult = Dsl.config();
    ResponseFilter responseFilter = mock(ResponseFilter.class);

    // Act
    DefaultAsyncHttpClientConfig.Builder actualAddResponseFilterResult = configResult.addResponseFilter(responseFilter);

    // Assert
    List<ResponseFilter> responseFilters = configResult.build().getResponseFilters();
    assertEquals(1, responseFilters.size());
    assertSame(configResult, actualAddResponseFilterResult);
    assertSame(responseFilter, responseFilters.get(0));
  }

  /**
   * Test Builder {@link Builder#removeIOExceptionFilter(IOExceptionFilter)}.
   * <p>
   * Method under test:
   * {@link DefaultAsyncHttpClientConfig.Builder#removeIOExceptionFilter(IOExceptionFilter)}
   */
  @Test
  @DisplayName("Test Builder removeIOExceptionFilter(IOExceptionFilter)")
  void testBuilderRemoveIOExceptionFilter() {
    // Arrange
    DefaultAsyncHttpClientConfig.Builder configResult = Dsl.config();

    // Act and Assert
    assertSame(configResult, configResult.removeIOExceptionFilter(mock(IOExceptionFilter.class)));
  }

  /**
   * Test Builder {@link Builder#removeRequestFilter(RequestFilter)}.
   * <p>
   * Method under test:
   * {@link DefaultAsyncHttpClientConfig.Builder#removeRequestFilter(RequestFilter)}
   */
  @Test
  @DisplayName("Test Builder removeRequestFilter(RequestFilter)")
  void testBuilderRemoveRequestFilter() {
    // Arrange
    DefaultAsyncHttpClientConfig.Builder configResult = Dsl.config();

    // Act and Assert
    assertSame(configResult, configResult.removeRequestFilter(mock(RequestFilter.class)));
  }

  /**
   * Test Builder {@link Builder#removeResponseFilter(ResponseFilter)}.
   * <p>
   * Method under test:
   * {@link DefaultAsyncHttpClientConfig.Builder#removeResponseFilter(ResponseFilter)}
   */
  @Test
  @DisplayName("Test Builder removeResponseFilter(ResponseFilter)")
  void testBuilderRemoveResponseFilter() {
    // Arrange
    DefaultAsyncHttpClientConfig.Builder configResult = Dsl.config();

    // Act and Assert
    assertSame(configResult, configResult.removeResponseFilter(mock(ResponseFilter.class)));
  }

  /**
   * Test Builder {@link Builder#setProxyServer(Builder)} with
   * {@code proxyServerBuilder}.
   * <p>
   * Method under test:
   * {@link DefaultAsyncHttpClientConfig.Builder#setProxyServer(ProxyServer.Builder)}
   */
  @Test
  @DisplayName("Test Builder setProxyServer(Builder) with 'proxyServerBuilder'")
  void testBuilderSetProxyServerWithProxyServerBuilder() {
    // Arrange
    DefaultAsyncHttpClientConfig.Builder configResult = Dsl.config();

    // Act and Assert
    assertSame(configResult, configResult.setProxyServer(Dsl.proxyServer("https://example.org/example", 8080)));
  }

  /**
   * Test Builder {@link Builder#setProxyServer(Builder)} with
   * {@code proxyServerBuilder}.
   * <p>
   * Method under test:
   * {@link DefaultAsyncHttpClientConfig.Builder#setProxyServer(ProxyServer.Builder)}
   */
  @Test
  @DisplayName("Test Builder setProxyServer(Builder) with 'proxyServerBuilder'")
  void testBuilderSetProxyServerWithProxyServerBuilder2() {
    // Arrange
    DefaultAsyncHttpClientConfig.Builder configResult = Dsl.config();
    ProxyServer.Builder proxyServerBuilder = Dsl.proxyServer("https://example.org/example", 8080);
    proxyServerBuilder.setNonProxyHost(null);
    proxyServerBuilder.setProxyType(null);

    // Act and Assert
    assertSame(configResult, configResult.setProxyServer(proxyServerBuilder));
  }

  /**
   * Test Builder {@link Builder#setProxyServer(Builder)} with
   * {@code proxyServerBuilder}.
   * <ul>
   *   <li>Given {@code HTTP}.</li>
   * </ul>
   * <p>
   * Method under test:
   * {@link DefaultAsyncHttpClientConfig.Builder#setProxyServer(ProxyServer.Builder)}
   */
  @Test
  @DisplayName("Test Builder setProxyServer(Builder) with 'proxyServerBuilder'; given 'HTTP'")
  void testBuilderSetProxyServerWithProxyServerBuilder_givenHttp() {
    // Arrange
    DefaultAsyncHttpClientConfig.Builder configResult = Dsl.config();
    ProxyServer.Builder proxyServerBuilder = Dsl.proxyServer("https://example.org/example", 8080);
    proxyServerBuilder.setNonProxyHost(null);
    proxyServerBuilder.setProxyType(ProxyType.HTTP);

    // Act and Assert
    assertSame(configResult, configResult.setProxyServer(proxyServerBuilder));
  }

  /**
   * Test Builder {@link Builder#setProxyServer(ProxyServer)} with
   * {@code proxyServer}.
   * <ul>
   *   <li>Then return config.</li>
   * </ul>
   * <p>
   * Method under test:
   * {@link DefaultAsyncHttpClientConfig.Builder#setProxyServer(ProxyServer)}
   */
  @Test
  @DisplayName("Test Builder setProxyServer(ProxyServer) with 'proxyServer'; then return config")
  void testBuilderSetProxyServerWithProxyServer_thenReturnConfig() {
    // Arrange
    DefaultAsyncHttpClientConfig.Builder configResult = Dsl.config();
    Realm.Builder setAlgorithmResult = (new Realm.Builder()).setAlgorithm("https://example.org/example");
    Realm.Builder setCharsetResult = setAlgorithmResult.setCharset(Charset.forName("UTF-8"));
    Realm.Builder setServicePrincipalNameResult = setCharsetResult.setCustomLoginConfig(new HashMap<>())
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
        .setScheme(Realm.AuthScheme.BASIC)
        .setServicePrincipalName("https://example.org/example");
    Realm realm = setServicePrincipalNameResult
        .setUri(new Uri("https://example.org/example", "https://example.org/example", "https://example.org/example",
            8080, "https://example.org/example", "https://example.org/example", "https://example.org/example"))
        .setUseAbsoluteURI(true)
        .setUseCanonicalHostname(true)
        .setUsePreemptiveAuth(true)
        .build();

    // Act and Assert
    assertSame(configResult, configResult.setProxyServer(
        new ProxyServer("https://example.org/example", 8080, 8080, realm, new ArrayList<>(), ProxyType.HTTP)));
  }

  /**
   * Test Builder {@link Builder#setRealm(Builder)} with {@code realmBuilder}.
   * <ul>
   *   <li>Given {@code BASIC}.</li>
   *   <li>Then return config.</li>
   * </ul>
   * <p>
   * Method under test:
   * {@link DefaultAsyncHttpClientConfig.Builder#setRealm(Realm.Builder)}
   */
  @Test
  @DisplayName("Test Builder setRealm(Builder) with 'realmBuilder'; given 'BASIC'; then return config")
  void testBuilderSetRealmWithRealmBuilder_givenBasic_thenReturnConfig() {
    // Arrange
    DefaultAsyncHttpClientConfig.Builder configResult = Dsl.config();

    Realm.Builder realmBuilder = new Realm.Builder();
    realmBuilder.setScheme(Realm.AuthScheme.BASIC);

    // Act and Assert
    assertSame(configResult, configResult.setRealm(realmBuilder));
  }

  /**
   * Test Builder {@link Builder#setSslSessionCacheSize(Integer)}.
   * <p>
   * Method under test:
   * {@link DefaultAsyncHttpClientConfig.Builder#setSslSessionCacheSize(Integer)}
   */
  @Test
  @DisplayName("Test Builder setSslSessionCacheSize(Integer)")
  void testBuilderSetSslSessionCacheSize() {
    // Arrange
    DefaultAsyncHttpClientConfig.Builder configResult = Dsl.config();

    // Act
    DefaultAsyncHttpClientConfig.Builder actualSetSslSessionCacheSizeResult = configResult.setSslSessionCacheSize(3);

    // Assert
    assertEquals(3, configResult.build().getSslSessionCacheSize());
    assertSame(configResult, actualSetSslSessionCacheSizeResult);
  }

  /**
   * Test Builder {@link Builder#setSslSessionTimeout(Integer)}.
   * <p>
   * Method under test:
   * {@link DefaultAsyncHttpClientConfig.Builder#setSslSessionTimeout(Integer)}
   */
  @Test
  @DisplayName("Test Builder setSslSessionTimeout(Integer)")
  void testBuilderSetSslSessionTimeout() {
    // Arrange
    DefaultAsyncHttpClientConfig.Builder configResult = Dsl.config();

    // Act
    DefaultAsyncHttpClientConfig.Builder actualSetSslSessionTimeoutResult = configResult.setSslSessionTimeout(10);

    // Assert
    assertEquals(10, configResult.build().getSslSessionTimeout());
    assertSame(configResult, actualSetSslSessionTimeoutResult);
  }
}
