package org.asynchttpclient.netty.request;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import io.netty.handler.codec.DefaultHeadersImpl;
import io.netty.handler.codec.Headers;
import io.netty.handler.codec.http.DefaultFullHttpRequest;
import io.netty.handler.codec.http.DefaultHttpHeaders;
import io.netty.handler.codec.http.EmptyHttpHeaders;
import io.netty.handler.codec.http.HttpHeaders;
import io.netty.handler.codec.http.HttpRequest;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import org.asynchttpclient.AsyncHttpClientConfig;
import org.asynchttpclient.Realm;
import org.asynchttpclient.Request;
import org.asynchttpclient.proxy.ProxyServer;
import org.asynchttpclient.proxy.ProxyType;
import org.asynchttpclient.uri.Uri;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

class NettyRequestFactoryDiffblueTest {
  /**
   * Test {@link NettyRequestFactory#NettyRequestFactory(AsyncHttpClientConfig)}.
   * <ul>
   *   <li>Given {@code false}.</li>
   * </ul>
   * <p>
   * Method under test:
   * {@link NettyRequestFactory#NettyRequestFactory(AsyncHttpClientConfig)}
   */
  @Test
  @DisplayName("Test new NettyRequestFactory(AsyncHttpClientConfig); given 'false'")
  void testNewNettyRequestFactory_givenFalse() {
    // Arrange
    AsyncHttpClientConfig config = mock(AsyncHttpClientConfig.class);
    when(config.isUseLaxCookieEncoder()).thenReturn(false);

    // Act
    new NettyRequestFactory(config);

    // Assert
    verify(config).isUseLaxCookieEncoder();
  }

  /**
   * Test {@link NettyRequestFactory#NettyRequestFactory(AsyncHttpClientConfig)}.
   * <ul>
   *   <li>Given {@code true}.</li>
   * </ul>
   * <p>
   * Method under test:
   * {@link NettyRequestFactory#NettyRequestFactory(AsyncHttpClientConfig)}
   */
  @Test
  @DisplayName("Test new NettyRequestFactory(AsyncHttpClientConfig); given 'true'")
  void testNewNettyRequestFactory_givenTrue() {
    // Arrange
    AsyncHttpClientConfig config = mock(AsyncHttpClientConfig.class);
    when(config.isUseLaxCookieEncoder()).thenReturn(true);

    // Act
    new NettyRequestFactory(config);

    // Assert
    verify(config).isUseLaxCookieEncoder();
  }

  /**
   * Test {@link NettyRequestFactory#addAuthorizationHeader(HttpHeaders, String)}.
   * <ul>
   *   <li>Given {@link DefaultHttpHeaders#DefaultHttpHeaders()}.</li>
   *   <li>Then calls {@link HttpHeaders#add(CharSequence, Object)}.</li>
   * </ul>
   * <p>
   * Method under test:
   * {@link NettyRequestFactory#addAuthorizationHeader(HttpHeaders, String)}
   */
  @Test
  @DisplayName("Test addAuthorizationHeader(HttpHeaders, String); given DefaultHttpHeaders(); then calls add(CharSequence, Object)")
  void testAddAuthorizationHeader_givenDefaultHttpHeaders_thenCallsAdd() {
    // Arrange
    AsyncHttpClientConfig config = mock(AsyncHttpClientConfig.class);
    when(config.isUseLaxCookieEncoder()).thenReturn(true);
    NettyRequestFactory nettyRequestFactory = new NettyRequestFactory(config);
    EmptyHttpHeaders headers = mock(EmptyHttpHeaders.class);
    when(headers.add(Mockito.<CharSequence>any(), Mockito.<Object>any())).thenReturn(new DefaultHttpHeaders());

    // Act
    nettyRequestFactory.addAuthorizationHeader(headers, "https://example.org/example");

    // Assert
    verify(headers).add(isA(CharSequence.class), isA(Object.class));
    verify(config).isUseLaxCookieEncoder();
  }

  /**
   * Test {@link NettyRequestFactory#addAuthorizationHeader(HttpHeaders, String)}.
   * <ul>
   *   <li>When {@link EmptyHttpHeaders}.</li>
   *   <li>Then calls {@link AsyncHttpClientConfig#isUseLaxCookieEncoder()}.</li>
   * </ul>
   * <p>
   * Method under test:
   * {@link NettyRequestFactory#addAuthorizationHeader(HttpHeaders, String)}
   */
  @Test
  @DisplayName("Test addAuthorizationHeader(HttpHeaders, String); when EmptyHttpHeaders; then calls isUseLaxCookieEncoder()")
  void testAddAuthorizationHeader_whenEmptyHttpHeaders_thenCallsIsUseLaxCookieEncoder() {
    // Arrange
    AsyncHttpClientConfig config = mock(AsyncHttpClientConfig.class);
    when(config.isUseLaxCookieEncoder()).thenReturn(true);

    // Act
    (new NettyRequestFactory(config)).addAuthorizationHeader(mock(EmptyHttpHeaders.class), null);

    // Assert that nothing has changed
    verify(config).isUseLaxCookieEncoder();
  }

  /**
   * Test
   * {@link NettyRequestFactory#setProxyAuthorizationHeader(HttpHeaders, String)}.
   * <ul>
   *   <li>Given {@link DefaultHttpHeaders#DefaultHttpHeaders()}.</li>
   *   <li>Then calls {@link HttpHeaders#set(CharSequence, Object)}.</li>
   * </ul>
   * <p>
   * Method under test:
   * {@link NettyRequestFactory#setProxyAuthorizationHeader(HttpHeaders, String)}
   */
  @Test
  @DisplayName("Test setProxyAuthorizationHeader(HttpHeaders, String); given DefaultHttpHeaders(); then calls set(CharSequence, Object)")
  void testSetProxyAuthorizationHeader_givenDefaultHttpHeaders_thenCallsSet() {
    // Arrange
    AsyncHttpClientConfig config = mock(AsyncHttpClientConfig.class);
    when(config.isUseLaxCookieEncoder()).thenReturn(true);
    NettyRequestFactory nettyRequestFactory = new NettyRequestFactory(config);
    EmptyHttpHeaders headers = mock(EmptyHttpHeaders.class);
    when(headers.set(Mockito.<CharSequence>any(), Mockito.<Object>any())).thenReturn(new DefaultHttpHeaders());

    // Act
    nettyRequestFactory.setProxyAuthorizationHeader(headers, "https://example.org/example");

    // Assert
    verify(headers).set(isA(CharSequence.class), isA(Object.class));
    verify(config).isUseLaxCookieEncoder();
  }

  /**
   * Test
   * {@link NettyRequestFactory#setProxyAuthorizationHeader(HttpHeaders, String)}.
   * <ul>
   *   <li>When {@link EmptyHttpHeaders}.</li>
   * </ul>
   * <p>
   * Method under test:
   * {@link NettyRequestFactory#setProxyAuthorizationHeader(HttpHeaders, String)}
   */
  @Test
  @DisplayName("Test setProxyAuthorizationHeader(HttpHeaders, String); when EmptyHttpHeaders")
  void testSetProxyAuthorizationHeader_whenEmptyHttpHeaders() {
    // Arrange
    AsyncHttpClientConfig config = mock(AsyncHttpClientConfig.class);
    when(config.isUseLaxCookieEncoder()).thenReturn(true);

    // Act
    (new NettyRequestFactory(config)).setProxyAuthorizationHeader(mock(EmptyHttpHeaders.class), null);

    // Assert that nothing has changed
    verify(config).isUseLaxCookieEncoder();
  }

  /**
   * Test
   * {@link NettyRequestFactory#newNettyRequest(Request, boolean, ProxyServer, Realm, Realm)}.
   * <p>
   * Method under test:
   * {@link NettyRequestFactory#newNettyRequest(Request, boolean, ProxyServer, Realm, Realm)}
   */
  @Test
  @DisplayName("Test newNettyRequest(Request, boolean, ProxyServer, Realm, Realm)")
  void testNewNettyRequest() {
    // Arrange
    AsyncHttpClientConfig config = mock(AsyncHttpClientConfig.class);
    when(config.isKeepAlive()).thenReturn(true);
    when(config.getUserAgent()).thenReturn("https://example.org/example");
    when(config.isUseLaxCookieEncoder()).thenReturn(true);
    NettyRequestFactory nettyRequestFactory = new NettyRequestFactory(config);
    EmptyHttpHeaders emptyHttpHeaders = mock(EmptyHttpHeaders.class);
    when(emptyHttpHeaders.getAll(Mockito.<CharSequence>any())).thenReturn(new ArrayList<>());
    Request request = mock(Request.class);
    when(request.getVirtualHost()).thenReturn("https://example.org/example");
    when(request.getHeaders()).thenReturn(emptyHttpHeaders);
    when(request.getUri()).thenReturn(new Uri(Uri.HTTPS, "https://example.org/example", "https://example.org/example",
        8080, "https://example.org/example", "https://example.org/example", "https://example.org/example"));
    ProxyServer proxyServer = mock(ProxyServer.class);
    Realm realm = mock(Realm.class);
    when(realm.getPassword()).thenReturn("https://example.org/example");
    when(realm.getPrincipal()).thenReturn("https://example.org/example");
    when(realm.getCharset()).thenReturn(Charset.forName("UTF-8"));
    when(realm.getScheme()).thenReturn(Realm.AuthScheme.BASIC);
    when(realm.isUsePreemptiveAuth()).thenReturn(true);
    Realm proxyRealm = mock(Realm.class);
    when(proxyRealm.getPassword()).thenReturn("https://example.org/example");
    when(proxyRealm.getPrincipal()).thenReturn("https://example.org/example");
    when(proxyRealm.getCharset()).thenReturn(Charset.forName("UTF-8"));
    when(proxyRealm.getScheme()).thenReturn(Realm.AuthScheme.BASIC);
    when(proxyRealm.isUsePreemptiveAuth()).thenReturn(true);

    // Act
    NettyRequest actualNewNettyRequestResult = nettyRequestFactory.newNettyRequest(request, true, proxyServer, realm,
        proxyRealm);

    // Assert
    verify(emptyHttpHeaders, atLeast(1)).getAll(Mockito.<CharSequence>any());
    verify(config, atLeast(1)).getUserAgent();
    verify(config).isKeepAlive();
    verify(config).isUseLaxCookieEncoder();
    verify(realm).getCharset();
    verify(proxyRealm).getCharset();
    verify(realm).getPassword();
    verify(proxyRealm).getPassword();
    verify(realm).getPrincipal();
    verify(proxyRealm).getPrincipal();
    verify(realm).getScheme();
    verify(proxyRealm).getScheme();
    verify(realm).isUsePreemptiveAuth();
    verify(proxyRealm).isUsePreemptiveAuth();
    verify(request, atLeast(1)).getHeaders();
    verify(request).getUri();
    verify(request).getVirtualHost();
    HttpRequest httpRequest = actualNewNettyRequestResult.getHttpRequest();
    HttpHeaders headersResult = httpRequest.headers();
    Headers<CharSequence, CharSequence, ?> unwrapResult = ((DefaultHttpHeaders) headersResult).unwrap();
    assertTrue(unwrapResult instanceof DefaultHeadersImpl);
    assertTrue(httpRequest instanceof DefaultFullHttpRequest);
    assertTrue(headersResult instanceof DefaultHttpHeaders);
    assertEquals("https://example.org/example:8080", httpRequest.getUri());
    assertEquals("https://example.org/example:8080", httpRequest.uri());
    assertEquals(5, unwrapResult.size());
    assertEquals(5, headersResult.size());
  }

  /**
   * Test
   * {@link NettyRequestFactory#newNettyRequest(Request, boolean, ProxyServer, Realm, Realm)}.
   * <ul>
   *   <li>Given {@link ArrayList#ArrayList()} add {@code foo}.</li>
   *   <li>When {@code true}.</li>
   *   <li>Then calls {@link HttpHeaders#getAll(CharSequence)}.</li>
   * </ul>
   * <p>
   * Method under test:
   * {@link NettyRequestFactory#newNettyRequest(Request, boolean, ProxyServer, Realm, Realm)}
   */
  @Test
  @DisplayName("Test newNettyRequest(Request, boolean, ProxyServer, Realm, Realm); given ArrayList() add 'foo'; when 'true'; then calls getAll(CharSequence)")
  void testNewNettyRequest_givenArrayListAddFoo_whenTrue_thenCallsGetAll() {
    // Arrange
    AsyncHttpClientConfig config = mock(AsyncHttpClientConfig.class);
    when(config.isKeepAlive()).thenReturn(true);
    when(config.isUseLaxCookieEncoder()).thenReturn(true);
    NettyRequestFactory nettyRequestFactory = new NettyRequestFactory(config);

    ArrayList<String> stringList = new ArrayList<>();
    stringList.add("foo");
    EmptyHttpHeaders emptyHttpHeaders = mock(EmptyHttpHeaders.class);
    when(emptyHttpHeaders.getAll(Mockito.<CharSequence>any())).thenReturn(stringList);
    Request request = mock(Request.class);
    when(request.getVirtualHost()).thenReturn("https://example.org/example");
    when(request.getHeaders()).thenReturn(emptyHttpHeaders);
    when(request.getUri())
        .thenReturn(new Uri("https://example.org/example", "https://example.org/example", "https://example.org/example",
            8080, "https://example.org/example", "https://example.org/example", "https://example.org/example"));
    ProxyServer proxyServer = mock(ProxyServer.class);
    Realm realm = mock(Realm.class);
    when(realm.getPassword()).thenReturn("https://example.org/example");
    when(realm.getPrincipal()).thenReturn("https://example.org/example");
    when(realm.getCharset()).thenReturn(Charset.forName("UTF-8"));
    when(realm.getScheme()).thenReturn(Realm.AuthScheme.BASIC);
    when(realm.isUsePreemptiveAuth()).thenReturn(true);
    Realm proxyRealm = mock(Realm.class);
    when(proxyRealm.getPassword()).thenReturn("https://example.org/example");
    when(proxyRealm.getPrincipal()).thenReturn("https://example.org/example");
    when(proxyRealm.getCharset()).thenReturn(Charset.forName("UTF-8"));
    when(proxyRealm.getScheme()).thenReturn(Realm.AuthScheme.BASIC);
    when(proxyRealm.isUsePreemptiveAuth()).thenReturn(true);

    // Act
    NettyRequest actualNewNettyRequestResult = nettyRequestFactory.newNettyRequest(request, true, proxyServer, realm,
        proxyRealm);

    // Assert
    verify(emptyHttpHeaders, atLeast(1)).getAll(Mockito.<CharSequence>any());
    verify(config).isKeepAlive();
    verify(config).isUseLaxCookieEncoder();
    verify(realm).getCharset();
    verify(proxyRealm).getCharset();
    verify(realm).getPassword();
    verify(proxyRealm).getPassword();
    verify(realm).getPrincipal();
    verify(proxyRealm).getPrincipal();
    verify(realm).getScheme();
    verify(proxyRealm).getScheme();
    verify(realm).isUsePreemptiveAuth();
    verify(proxyRealm).isUsePreemptiveAuth();
    verify(request, atLeast(1)).getHeaders();
    verify(request).getUri();
    verify(request).getVirtualHost();
    HttpRequest httpRequest = actualNewNettyRequestResult.getHttpRequest();
    HttpHeaders headersResult = httpRequest.headers();
    Headers<CharSequence, CharSequence, ?> unwrapResult = ((DefaultHttpHeaders) headersResult).unwrap();
    assertTrue(unwrapResult instanceof DefaultHeadersImpl);
    assertTrue(httpRequest instanceof DefaultFullHttpRequest);
    assertTrue(headersResult instanceof DefaultHttpHeaders);
    assertEquals("https://example.org/example:8080", httpRequest.getUri());
    assertEquals("https://example.org/example:8080", httpRequest.uri());
    assertEquals(5, unwrapResult.size());
    assertEquals(5, headersResult.size());
  }

  /**
   * Test
   * {@link NettyRequestFactory#newNettyRequest(Request, boolean, ProxyServer, Realm, Realm)}.
   * <ul>
   *   <li>Given {@link AsyncHttpClientConfig}
   * {@link AsyncHttpClientConfig#isCompressionEnforced()} return
   * {@code false}.</li>
   * </ul>
   * <p>
   * Method under test:
   * {@link NettyRequestFactory#newNettyRequest(Request, boolean, ProxyServer, Realm, Realm)}
   */
  @Test
  @DisplayName("Test newNettyRequest(Request, boolean, ProxyServer, Realm, Realm); given AsyncHttpClientConfig isCompressionEnforced() return 'false'")
  void testNewNettyRequest_givenAsyncHttpClientConfigIsCompressionEnforcedReturnFalse()
      throws UnsupportedEncodingException {
    // Arrange
    AsyncHttpClientConfig config = mock(AsyncHttpClientConfig.class);
    when(config.isCompressionEnforced()).thenReturn(false);
    when(config.isKeepAlive()).thenReturn(true);
    when(config.getUserAgent()).thenReturn("https://example.org/example");
    when(config.isUseLaxCookieEncoder()).thenReturn(true);
    NettyRequestFactory nettyRequestFactory = new NettyRequestFactory(config);
    EmptyHttpHeaders emptyHttpHeaders = mock(EmptyHttpHeaders.class);
    when(emptyHttpHeaders.isEmpty()).thenReturn(true);
    Request request = mock(Request.class);
    when(request.getCookies()).thenReturn(new ArrayList<>());
    when(request.getByteData()).thenReturn("AXAXAXAX".getBytes("UTF-8"));
    when(request.getCharset()).thenReturn(Charset.forName("UTF-8"));
    when(request.getMethod()).thenReturn("https://example.org/example");
    when(request.getVirtualHost()).thenReturn("https://example.org/example");
    when(request.getHeaders()).thenReturn(emptyHttpHeaders);
    when(request.getUri())
        .thenReturn(new Uri("https://example.org/example", "https://example.org/example", "https://example.org/example",
            8080, "https://example.org/example", "https://example.org/example", "https://example.org/example"));
    ProxyServer proxyServer = mock(ProxyServer.class);
    when(proxyServer.getProxyType()).thenReturn(ProxyType.HTTP);
    Realm realm = mock(Realm.class);
    when(realm.getPassword()).thenReturn("https://example.org/example");
    when(realm.getPrincipal()).thenReturn("https://example.org/example");
    when(realm.getCharset()).thenReturn(Charset.forName("UTF-8"));
    when(realm.getScheme()).thenReturn(Realm.AuthScheme.BASIC);
    when(realm.isUsePreemptiveAuth()).thenReturn(true);
    Realm proxyRealm = mock(Realm.class);
    when(proxyRealm.getPassword()).thenReturn("https://example.org/example");
    when(proxyRealm.getPrincipal()).thenReturn("https://example.org/example");
    when(proxyRealm.getCharset()).thenReturn(Charset.forName("UTF-8"));
    when(proxyRealm.getScheme()).thenReturn(Realm.AuthScheme.BASIC);
    when(proxyRealm.isUsePreemptiveAuth()).thenReturn(true);

    // Act
    NettyRequest actualNewNettyRequestResult = nettyRequestFactory.newNettyRequest(request, false, proxyServer, realm,
        proxyRealm);

    // Assert
    verify(emptyHttpHeaders).isEmpty();
    verify(config, atLeast(1)).getUserAgent();
    verify(config).isCompressionEnforced();
    verify(config).isKeepAlive();
    verify(config).isUseLaxCookieEncoder();
    verify(realm).getCharset();
    verify(proxyRealm).getCharset();
    verify(realm).getPassword();
    verify(proxyRealm).getPassword();
    verify(realm).getPrincipal();
    verify(proxyRealm).getPrincipal();
    verify(realm).getScheme();
    verify(proxyRealm).getScheme();
    verify(realm).isUsePreemptiveAuth();
    verify(proxyRealm).isUsePreemptiveAuth();
    verify(request, atLeast(1)).getByteData();
    verify(request).getCharset();
    verify(request).getCookies();
    verify(request).getHeaders();
    verify(request).getMethod();
    verify(request).getUri();
    verify(request).getVirtualHost();
    verify(proxyServer).getProxyType();
    HttpRequest httpRequest = actualNewNettyRequestResult.getHttpRequest();
    HttpHeaders headersResult = httpRequest.headers();
    Headers<CharSequence, CharSequence, ?> unwrapResult = ((DefaultHttpHeaders) headersResult).unwrap();
    assertTrue(unwrapResult instanceof DefaultHeadersImpl);
    assertTrue(httpRequest instanceof DefaultFullHttpRequest);
    assertTrue(headersResult instanceof DefaultHttpHeaders);
    assertEquals("https://example.org/example://https://example.org/example@https://example.org/example:8080https:/"
        + "/example.org/example?https://example.org/example", httpRequest.getUri());
    assertEquals("https://example.org/example://https://example.org/example@https://example.org/example:8080https:/"
        + "/example.org/example?https://example.org/example", httpRequest.uri());
    assertEquals(6, unwrapResult.size());
    assertEquals(6, headersResult.size());
  }

  /**
   * Test
   * {@link NettyRequestFactory#newNettyRequest(Request, boolean, ProxyServer, Realm, Realm)}.
   * <ul>
   *   <li>Given {@link AsyncHttpClientConfig}
   * {@link AsyncHttpClientConfig#isKeepAlive()} return {@code false}.</li>
   * </ul>
   * <p>
   * Method under test:
   * {@link NettyRequestFactory#newNettyRequest(Request, boolean, ProxyServer, Realm, Realm)}
   */
  @Test
  @DisplayName("Test newNettyRequest(Request, boolean, ProxyServer, Realm, Realm); given AsyncHttpClientConfig isKeepAlive() return 'false'")
  void testNewNettyRequest_givenAsyncHttpClientConfigIsKeepAliveReturnFalse() {
    // Arrange
    AsyncHttpClientConfig config = mock(AsyncHttpClientConfig.class);
    when(config.isKeepAlive()).thenReturn(false);
    when(config.getUserAgent()).thenReturn("https://example.org/example");
    when(config.isUseLaxCookieEncoder()).thenReturn(true);
    NettyRequestFactory nettyRequestFactory = new NettyRequestFactory(config);
    Request request = mock(Request.class);
    when(request.getVirtualHost()).thenReturn("https://example.org/example");
    when(request.getHeaders()).thenReturn(new DefaultHttpHeaders());
    when(request.getUri())
        .thenReturn(new Uri("https://example.org/example", "https://example.org/example", "https://example.org/example",
            8080, "https://example.org/example", "https://example.org/example", "https://example.org/example"));
    ProxyServer proxyServer = mock(ProxyServer.class);
    Realm realm = mock(Realm.class);
    when(realm.getPassword()).thenReturn("https://example.org/example");
    when(realm.getPrincipal()).thenReturn("https://example.org/example");
    when(realm.getCharset()).thenReturn(Charset.forName("UTF-8"));
    when(realm.getScheme()).thenReturn(Realm.AuthScheme.BASIC);
    when(realm.isUsePreemptiveAuth()).thenReturn(true);
    Realm proxyRealm = mock(Realm.class);
    when(proxyRealm.getPassword()).thenReturn("https://example.org/example");
    when(proxyRealm.getPrincipal()).thenReturn("https://example.org/example");
    when(proxyRealm.getCharset()).thenReturn(Charset.forName("UTF-8"));
    when(proxyRealm.getScheme()).thenReturn(Realm.AuthScheme.BASIC);
    when(proxyRealm.isUsePreemptiveAuth()).thenReturn(true);

    // Act
    NettyRequest actualNewNettyRequestResult = nettyRequestFactory.newNettyRequest(request, true, proxyServer, realm,
        proxyRealm);

    // Assert
    verify(config, atLeast(1)).getUserAgent();
    verify(config).isKeepAlive();
    verify(config).isUseLaxCookieEncoder();
    verify(realm).getCharset();
    verify(proxyRealm).getCharset();
    verify(realm).getPassword();
    verify(proxyRealm).getPassword();
    verify(realm).getPrincipal();
    verify(proxyRealm).getPrincipal();
    verify(realm).getScheme();
    verify(proxyRealm).getScheme();
    verify(realm).isUsePreemptiveAuth();
    verify(proxyRealm).isUsePreemptiveAuth();
    verify(request, atLeast(1)).getHeaders();
    verify(request).getUri();
    verify(request).getVirtualHost();
    HttpRequest httpRequest = actualNewNettyRequestResult.getHttpRequest();
    HttpHeaders headersResult = httpRequest.headers();
    Headers<CharSequence, CharSequence, ?> unwrapResult = ((DefaultHttpHeaders) headersResult).unwrap();
    assertTrue(unwrapResult instanceof DefaultHeadersImpl);
    assertTrue(httpRequest instanceof DefaultFullHttpRequest);
    assertTrue(headersResult instanceof DefaultHttpHeaders);
    assertEquals("https://example.org/example:8080", httpRequest.getUri());
    assertEquals("https://example.org/example:8080", httpRequest.uri());
    assertEquals(6, unwrapResult.size());
    assertEquals(6, headersResult.size());
  }

  /**
   * Test
   * {@link NettyRequestFactory#newNettyRequest(Request, boolean, ProxyServer, Realm, Realm)}.
   * <ul>
   *   <li>Given {@link DefaultHttpHeaders#DefaultHttpHeaders()}.</li>
   * </ul>
   * <p>
   * Method under test:
   * {@link NettyRequestFactory#newNettyRequest(Request, boolean, ProxyServer, Realm, Realm)}
   */
  @Test
  @DisplayName("Test newNettyRequest(Request, boolean, ProxyServer, Realm, Realm); given DefaultHttpHeaders()")
  void testNewNettyRequest_givenDefaultHttpHeaders() {
    // Arrange
    AsyncHttpClientConfig config = mock(AsyncHttpClientConfig.class);
    when(config.isKeepAlive()).thenReturn(true);
    when(config.getUserAgent()).thenReturn("https://example.org/example");
    when(config.isUseLaxCookieEncoder()).thenReturn(true);
    NettyRequestFactory nettyRequestFactory = new NettyRequestFactory(config);
    Request request = mock(Request.class);
    when(request.getVirtualHost()).thenReturn("https://example.org/example");
    when(request.getHeaders()).thenReturn(new DefaultHttpHeaders());
    when(request.getUri())
        .thenReturn(new Uri("https://example.org/example", "https://example.org/example", "https://example.org/example",
            8080, "https://example.org/example", "https://example.org/example", "https://example.org/example"));
    ProxyServer proxyServer = mock(ProxyServer.class);
    Realm realm = mock(Realm.class);
    when(realm.getPassword()).thenReturn("https://example.org/example");
    when(realm.getPrincipal()).thenReturn("https://example.org/example");
    when(realm.getCharset()).thenReturn(Charset.forName("UTF-8"));
    when(realm.getScheme()).thenReturn(Realm.AuthScheme.BASIC);
    when(realm.isUsePreemptiveAuth()).thenReturn(true);
    Realm proxyRealm = mock(Realm.class);
    when(proxyRealm.getPassword()).thenReturn("https://example.org/example");
    when(proxyRealm.getPrincipal()).thenReturn("https://example.org/example");
    when(proxyRealm.getCharset()).thenReturn(Charset.forName("UTF-8"));
    when(proxyRealm.getScheme()).thenReturn(Realm.AuthScheme.BASIC);
    when(proxyRealm.isUsePreemptiveAuth()).thenReturn(true);

    // Act
    NettyRequest actualNewNettyRequestResult = nettyRequestFactory.newNettyRequest(request, true, proxyServer, realm,
        proxyRealm);

    // Assert
    verify(config, atLeast(1)).getUserAgent();
    verify(config).isKeepAlive();
    verify(config).isUseLaxCookieEncoder();
    verify(realm).getCharset();
    verify(proxyRealm).getCharset();
    verify(realm).getPassword();
    verify(proxyRealm).getPassword();
    verify(realm).getPrincipal();
    verify(proxyRealm).getPrincipal();
    verify(realm).getScheme();
    verify(proxyRealm).getScheme();
    verify(realm).isUsePreemptiveAuth();
    verify(proxyRealm).isUsePreemptiveAuth();
    verify(request, atLeast(1)).getHeaders();
    verify(request).getUri();
    verify(request).getVirtualHost();
    HttpRequest httpRequest = actualNewNettyRequestResult.getHttpRequest();
    HttpHeaders headersResult = httpRequest.headers();
    Headers<CharSequence, CharSequence, ?> unwrapResult = ((DefaultHttpHeaders) headersResult).unwrap();
    assertTrue(unwrapResult instanceof DefaultHeadersImpl);
    assertTrue(httpRequest instanceof DefaultFullHttpRequest);
    assertTrue(headersResult instanceof DefaultHttpHeaders);
    assertEquals("https://example.org/example:8080", httpRequest.getUri());
    assertEquals("https://example.org/example:8080", httpRequest.uri());
    assertEquals(5, unwrapResult.size());
    assertEquals(5, headersResult.size());
  }

  /**
   * Test
   * {@link NettyRequestFactory#newNettyRequest(Request, boolean, ProxyServer, Realm, Realm)}.
   * <ul>
   *   <li>Given {@link DefaultHttpHeaders#DefaultHttpHeaders()}.</li>
   *   <li>When {@link Request} {@link Request#getVirtualHost()} return
   * {@code null}.</li>
   * </ul>
   * <p>
   * Method under test:
   * {@link NettyRequestFactory#newNettyRequest(Request, boolean, ProxyServer, Realm, Realm)}
   */
  @Test
  @DisplayName("Test newNettyRequest(Request, boolean, ProxyServer, Realm, Realm); given DefaultHttpHeaders(); when Request getVirtualHost() return 'null'")
  void testNewNettyRequest_givenDefaultHttpHeaders_whenRequestGetVirtualHostReturnNull() {
    // Arrange
    AsyncHttpClientConfig config = mock(AsyncHttpClientConfig.class);
    when(config.isKeepAlive()).thenReturn(true);
    when(config.getUserAgent()).thenReturn("https://example.org/example");
    when(config.isUseLaxCookieEncoder()).thenReturn(true);
    NettyRequestFactory nettyRequestFactory = new NettyRequestFactory(config);
    Request request = mock(Request.class);
    when(request.getVirtualHost()).thenReturn(null);
    when(request.getHeaders()).thenReturn(new DefaultHttpHeaders());
    when(request.getUri())
        .thenReturn(new Uri("https://example.org/example", "https://example.org/example", "https://example.org/example",
            8080, "https://example.org/example", "https://example.org/example", "https://example.org/example"));
    ProxyServer proxyServer = mock(ProxyServer.class);
    Realm realm = mock(Realm.class);
    when(realm.getPassword()).thenReturn("https://example.org/example");
    when(realm.getPrincipal()).thenReturn("https://example.org/example");
    when(realm.getCharset()).thenReturn(Charset.forName("UTF-8"));
    when(realm.getScheme()).thenReturn(Realm.AuthScheme.BASIC);
    when(realm.isUsePreemptiveAuth()).thenReturn(true);
    Realm proxyRealm = mock(Realm.class);
    when(proxyRealm.getPassword()).thenReturn("https://example.org/example");
    when(proxyRealm.getPrincipal()).thenReturn("https://example.org/example");
    when(proxyRealm.getCharset()).thenReturn(Charset.forName("UTF-8"));
    when(proxyRealm.getScheme()).thenReturn(Realm.AuthScheme.BASIC);
    when(proxyRealm.isUsePreemptiveAuth()).thenReturn(true);

    // Act
    NettyRequest actualNewNettyRequestResult = nettyRequestFactory.newNettyRequest(request, true, proxyServer, realm,
        proxyRealm);

    // Assert
    verify(config, atLeast(1)).getUserAgent();
    verify(config).isKeepAlive();
    verify(config).isUseLaxCookieEncoder();
    verify(realm).getCharset();
    verify(proxyRealm).getCharset();
    verify(realm).getPassword();
    verify(proxyRealm).getPassword();
    verify(realm).getPrincipal();
    verify(proxyRealm).getPrincipal();
    verify(realm).getScheme();
    verify(proxyRealm).getScheme();
    verify(realm).isUsePreemptiveAuth();
    verify(proxyRealm).isUsePreemptiveAuth();
    verify(request, atLeast(1)).getHeaders();
    verify(request).getUri();
    verify(request).getVirtualHost();
    HttpRequest httpRequest = actualNewNettyRequestResult.getHttpRequest();
    HttpHeaders headersResult = httpRequest.headers();
    Headers<CharSequence, CharSequence, ?> unwrapResult = ((DefaultHttpHeaders) headersResult).unwrap();
    assertTrue(unwrapResult instanceof DefaultHeadersImpl);
    assertTrue(httpRequest instanceof DefaultFullHttpRequest);
    assertTrue(headersResult instanceof DefaultHttpHeaders);
    assertEquals("https://example.org/example:8080", httpRequest.getUri());
    assertEquals("https://example.org/example:8080", httpRequest.uri());
    assertEquals(5, unwrapResult.size());
    assertEquals(5, headersResult.size());
  }

  /**
   * Test
   * {@link NettyRequestFactory#newNettyRequest(Request, boolean, ProxyServer, Realm, Realm)}.
   * <ul>
   *   <li>Then return HttpRequest headers unwrap size is four.</li>
   * </ul>
   * <p>
   * Method under test:
   * {@link NettyRequestFactory#newNettyRequest(Request, boolean, ProxyServer, Realm, Realm)}
   */
  @Test
  @DisplayName("Test newNettyRequest(Request, boolean, ProxyServer, Realm, Realm); then return HttpRequest headers unwrap size is four")
  void testNewNettyRequest_thenReturnHttpRequestHeadersUnwrapSizeIsFour() {
    // Arrange
    AsyncHttpClientConfig config = mock(AsyncHttpClientConfig.class);
    when(config.isKeepAlive()).thenReturn(true);
    when(config.getUserAgent()).thenReturn(null);
    when(config.isUseLaxCookieEncoder()).thenReturn(true);
    NettyRequestFactory nettyRequestFactory = new NettyRequestFactory(config);
    Request request = mock(Request.class);
    when(request.getVirtualHost()).thenReturn("https://example.org/example");
    when(request.getHeaders()).thenReturn(new DefaultHttpHeaders());
    when(request.getUri())
        .thenReturn(new Uri("https://example.org/example", "https://example.org/example", "https://example.org/example",
            8080, "https://example.org/example", "https://example.org/example", "https://example.org/example"));
    ProxyServer proxyServer = mock(ProxyServer.class);
    Realm realm = mock(Realm.class);
    when(realm.getPassword()).thenReturn("https://example.org/example");
    when(realm.getPrincipal()).thenReturn("https://example.org/example");
    when(realm.getCharset()).thenReturn(Charset.forName("UTF-8"));
    when(realm.getScheme()).thenReturn(Realm.AuthScheme.BASIC);
    when(realm.isUsePreemptiveAuth()).thenReturn(true);
    Realm proxyRealm = mock(Realm.class);
    when(proxyRealm.getPassword()).thenReturn("https://example.org/example");
    when(proxyRealm.getPrincipal()).thenReturn("https://example.org/example");
    when(proxyRealm.getCharset()).thenReturn(Charset.forName("UTF-8"));
    when(proxyRealm.getScheme()).thenReturn(Realm.AuthScheme.BASIC);
    when(proxyRealm.isUsePreemptiveAuth()).thenReturn(true);

    // Act
    NettyRequest actualNewNettyRequestResult = nettyRequestFactory.newNettyRequest(request, true, proxyServer, realm,
        proxyRealm);

    // Assert
    verify(config).getUserAgent();
    verify(config).isKeepAlive();
    verify(config).isUseLaxCookieEncoder();
    verify(realm).getCharset();
    verify(proxyRealm).getCharset();
    verify(realm).getPassword();
    verify(proxyRealm).getPassword();
    verify(realm).getPrincipal();
    verify(proxyRealm).getPrincipal();
    verify(realm).getScheme();
    verify(proxyRealm).getScheme();
    verify(realm).isUsePreemptiveAuth();
    verify(proxyRealm).isUsePreemptiveAuth();
    verify(request, atLeast(1)).getHeaders();
    verify(request).getUri();
    verify(request).getVirtualHost();
    HttpRequest httpRequest = actualNewNettyRequestResult.getHttpRequest();
    HttpHeaders headersResult = httpRequest.headers();
    Headers<CharSequence, CharSequence, ?> unwrapResult = ((DefaultHttpHeaders) headersResult).unwrap();
    assertTrue(unwrapResult instanceof DefaultHeadersImpl);
    assertTrue(httpRequest instanceof DefaultFullHttpRequest);
    assertTrue(headersResult instanceof DefaultHttpHeaders);
    assertEquals("https://example.org/example:8080", httpRequest.getUri());
    assertEquals("https://example.org/example:8080", httpRequest.uri());
    assertEquals(4, unwrapResult.size());
    assertEquals(4, headersResult.size());
  }

  /**
   * Test
   * {@link NettyRequestFactory#newNettyRequest(Request, boolean, ProxyServer, Realm, Realm)}.
   * <ul>
   *   <li>Then return HttpRequest Uri is
   * {@code https://example.org/example:80}.</li>
   * </ul>
   * <p>
   * Method under test:
   * {@link NettyRequestFactory#newNettyRequest(Request, boolean, ProxyServer, Realm, Realm)}
   */
  @Test
  @DisplayName("Test newNettyRequest(Request, boolean, ProxyServer, Realm, Realm); then return HttpRequest Uri is 'https://example.org/example:80'")
  void testNewNettyRequest_thenReturnHttpRequestUriIsHttpsExampleOrgExample80() {
    // Arrange
    AsyncHttpClientConfig config = mock(AsyncHttpClientConfig.class);
    when(config.isKeepAlive()).thenReturn(true);
    when(config.getUserAgent()).thenReturn("https://example.org/example");
    when(config.isUseLaxCookieEncoder()).thenReturn(true);
    NettyRequestFactory nettyRequestFactory = new NettyRequestFactory(config);
    EmptyHttpHeaders emptyHttpHeaders = mock(EmptyHttpHeaders.class);
    when(emptyHttpHeaders.getAll(Mockito.<CharSequence>any())).thenReturn(new ArrayList<>());
    Request request = mock(Request.class);
    when(request.getVirtualHost()).thenReturn("https://example.org/example");
    when(request.getHeaders()).thenReturn(emptyHttpHeaders);
    when(request.getUri())
        .thenReturn(new Uri("https://example.org/example", "https://example.org/example", "https://example.org/example",
            -1, "https://example.org/example", "https://example.org/example", "https://example.org/example"));
    ProxyServer proxyServer = mock(ProxyServer.class);
    Realm realm = mock(Realm.class);
    when(realm.getPassword()).thenReturn("https://example.org/example");
    when(realm.getPrincipal()).thenReturn("https://example.org/example");
    when(realm.getCharset()).thenReturn(Charset.forName("UTF-8"));
    when(realm.getScheme()).thenReturn(Realm.AuthScheme.BASIC);
    when(realm.isUsePreemptiveAuth()).thenReturn(true);
    Realm proxyRealm = mock(Realm.class);
    when(proxyRealm.getPassword()).thenReturn("https://example.org/example");
    when(proxyRealm.getPrincipal()).thenReturn("https://example.org/example");
    when(proxyRealm.getCharset()).thenReturn(Charset.forName("UTF-8"));
    when(proxyRealm.getScheme()).thenReturn(Realm.AuthScheme.BASIC);
    when(proxyRealm.isUsePreemptiveAuth()).thenReturn(true);

    // Act
    NettyRequest actualNewNettyRequestResult = nettyRequestFactory.newNettyRequest(request, true, proxyServer, realm,
        proxyRealm);

    // Assert
    verify(emptyHttpHeaders, atLeast(1)).getAll(Mockito.<CharSequence>any());
    verify(config, atLeast(1)).getUserAgent();
    verify(config).isKeepAlive();
    verify(config).isUseLaxCookieEncoder();
    verify(realm).getCharset();
    verify(proxyRealm).getCharset();
    verify(realm).getPassword();
    verify(proxyRealm).getPassword();
    verify(realm).getPrincipal();
    verify(proxyRealm).getPrincipal();
    verify(realm).getScheme();
    verify(proxyRealm).getScheme();
    verify(realm).isUsePreemptiveAuth();
    verify(proxyRealm).isUsePreemptiveAuth();
    verify(request, atLeast(1)).getHeaders();
    verify(request).getUri();
    verify(request).getVirtualHost();
    HttpRequest httpRequest = actualNewNettyRequestResult.getHttpRequest();
    HttpHeaders headersResult = httpRequest.headers();
    Headers<CharSequence, CharSequence, ?> unwrapResult = ((DefaultHttpHeaders) headersResult).unwrap();
    assertTrue(unwrapResult instanceof DefaultHeadersImpl);
    assertTrue(httpRequest instanceof DefaultFullHttpRequest);
    assertTrue(headersResult instanceof DefaultHttpHeaders);
    assertEquals("https://example.org/example:80", httpRequest.getUri());
    assertEquals("https://example.org/example:80", httpRequest.uri());
    assertEquals(5, unwrapResult.size());
    assertEquals(5, headersResult.size());
  }

  /**
   * Test
   * {@link NettyRequestFactory#newNettyRequest(Request, boolean, ProxyServer, Realm, Realm)}.
   * <ul>
   *   <li>When {@code true}.</li>
   *   <li>Then calls {@link HttpHeaders#getAll(CharSequence)}.</li>
   * </ul>
   * <p>
   * Method under test:
   * {@link NettyRequestFactory#newNettyRequest(Request, boolean, ProxyServer, Realm, Realm)}
   */
  @Test
  @DisplayName("Test newNettyRequest(Request, boolean, ProxyServer, Realm, Realm); when 'true'; then calls getAll(CharSequence)")
  void testNewNettyRequest_whenTrue_thenCallsGetAll() {
    // Arrange
    AsyncHttpClientConfig config = mock(AsyncHttpClientConfig.class);
    when(config.isKeepAlive()).thenReturn(true);
    when(config.getUserAgent()).thenReturn("https://example.org/example");
    when(config.isUseLaxCookieEncoder()).thenReturn(true);
    NettyRequestFactory nettyRequestFactory = new NettyRequestFactory(config);
    EmptyHttpHeaders emptyHttpHeaders = mock(EmptyHttpHeaders.class);
    when(emptyHttpHeaders.getAll(Mockito.<CharSequence>any())).thenReturn(new ArrayList<>());
    Request request = mock(Request.class);
    when(request.getVirtualHost()).thenReturn("https://example.org/example");
    when(request.getHeaders()).thenReturn(emptyHttpHeaders);
    when(request.getUri())
        .thenReturn(new Uri("https://example.org/example", "https://example.org/example", "https://example.org/example",
            8080, "https://example.org/example", "https://example.org/example", "https://example.org/example"));
    ProxyServer proxyServer = mock(ProxyServer.class);
    Realm realm = mock(Realm.class);
    when(realm.getPassword()).thenReturn("https://example.org/example");
    when(realm.getPrincipal()).thenReturn("https://example.org/example");
    when(realm.getCharset()).thenReturn(Charset.forName("UTF-8"));
    when(realm.getScheme()).thenReturn(Realm.AuthScheme.BASIC);
    when(realm.isUsePreemptiveAuth()).thenReturn(true);
    Realm proxyRealm = mock(Realm.class);
    when(proxyRealm.getPassword()).thenReturn("https://example.org/example");
    when(proxyRealm.getPrincipal()).thenReturn("https://example.org/example");
    when(proxyRealm.getCharset()).thenReturn(Charset.forName("UTF-8"));
    when(proxyRealm.getScheme()).thenReturn(Realm.AuthScheme.BASIC);
    when(proxyRealm.isUsePreemptiveAuth()).thenReturn(true);

    // Act
    NettyRequest actualNewNettyRequestResult = nettyRequestFactory.newNettyRequest(request, true, proxyServer, realm,
        proxyRealm);

    // Assert
    verify(emptyHttpHeaders, atLeast(1)).getAll(Mockito.<CharSequence>any());
    verify(config, atLeast(1)).getUserAgent();
    verify(config).isKeepAlive();
    verify(config).isUseLaxCookieEncoder();
    verify(realm).getCharset();
    verify(proxyRealm).getCharset();
    verify(realm).getPassword();
    verify(proxyRealm).getPassword();
    verify(realm).getPrincipal();
    verify(proxyRealm).getPrincipal();
    verify(realm).getScheme();
    verify(proxyRealm).getScheme();
    verify(realm).isUsePreemptiveAuth();
    verify(proxyRealm).isUsePreemptiveAuth();
    verify(request, atLeast(1)).getHeaders();
    verify(request).getUri();
    verify(request).getVirtualHost();
    HttpRequest httpRequest = actualNewNettyRequestResult.getHttpRequest();
    HttpHeaders headersResult = httpRequest.headers();
    Headers<CharSequence, CharSequence, ?> unwrapResult = ((DefaultHttpHeaders) headersResult).unwrap();
    assertTrue(unwrapResult instanceof DefaultHeadersImpl);
    assertTrue(httpRequest instanceof DefaultFullHttpRequest);
    assertTrue(headersResult instanceof DefaultHttpHeaders);
    assertEquals("https://example.org/example:8080", httpRequest.getUri());
    assertEquals("https://example.org/example:8080", httpRequest.uri());
    assertEquals(5, unwrapResult.size());
    assertEquals(5, headersResult.size());
  }
}
