package org.asynchttpclient;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import io.netty.handler.codec.DefaultHeadersImpl;
import io.netty.handler.codec.Headers;
import io.netty.handler.codec.http.DefaultCookie;
import io.netty.handler.codec.http.DefaultHttpHeaders;
import io.netty.handler.codec.http.HttpHeaders;
import io.netty.handler.codec.http.cookie.Cookie;
import io.netty.resolver.NameResolver;
import io.netty.util.AsciiString;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.InetAddress;
import java.net.URISyntaxException;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.nio.file.Paths;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.asynchttpclient.channel.ChannelPoolPartitioning;
import org.asynchttpclient.config.AsyncHttpClientConfigDefaults;
import org.asynchttpclient.proxy.ProxyServer;
import org.asynchttpclient.proxy.ProxyType;
import org.asynchttpclient.request.body.generator.BodyGenerator;
import org.asynchttpclient.request.body.multipart.Part;
import org.asynchttpclient.uri.Uri;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class RequestBuilderBaseDiffblueTest {
  /**
   * Test {@link RequestBuilderBase#setUrl(String)}.
   * <ul>
   *   <li>When {@code http://localhost}.</li>
   *   <li>Then {@link RequestBuilder#RequestBuilder()}
   * {@link RequestBuilderBase#uri} Path is empty string.</li>
   * </ul>
   * <p>
   * Method under test: {@link RequestBuilderBase#setUrl(String)}
   */
  @Test
  @DisplayName("Test setUrl(String); when 'http://localhost'; then RequestBuilder() uri Path is empty string")
  void testSetUrl_whenHttpLocalhost_thenRequestBuilderUriPathIsEmptyString() throws URISyntaxException {
    // Arrange
    RequestBuilder requestBuilder = new RequestBuilder();

    // Act
    RequestBuilder actualSetUrlResult = requestBuilder.setUrl("http://localhost");

    // Assert
    Uri uri = requestBuilder.uri;
    assertEquals("", uri.getPath());
    Uri uri2 = actualSetUrlResult.uri;
    assertEquals("", uri2.getPath());
    assertEquals("/", uri.getNonEmptyPath());
    assertEquals("/", uri2.getNonEmptyPath());
    assertEquals("http", uri.getScheme());
    assertEquals("http", uri2.getScheme());
    assertEquals("http://localhost", uri.toJavaNetURI().toString());
    assertEquals("http://localhost", uri2.toJavaNetURI().toString());
    assertEquals("http://localhost:80", uri.getBaseUrl());
    assertEquals("http://localhost:80", uri2.getBaseUrl());
    assertEquals("localhost", uri.getHost());
    assertEquals("localhost", uri2.getHost());
    assertEquals("localhost:80", uri.getAuthority());
    assertEquals("localhost:80", uri2.getAuthority());
    assertEquals(80, uri.getExplicitPort());
    assertEquals(80, uri2.getExplicitPort());
    assertEquals(80, uri.getSchemeDefaultPort());
    assertEquals(80, uri2.getSchemeDefaultPort());
    assertFalse(uri.isSecured());
    assertFalse(uri2.isSecured());
  }

  /**
   * Test {@link RequestBuilderBase#setUrl(String)}.
   * <ul>
   *   <li>When {@code https://example.org/example}.</li>
   *   <li>Then {@link RequestBuilder#RequestBuilder()}
   * {@link RequestBuilderBase#uri} NonEmptyPath is {@code /example}.</li>
   * </ul>
   * <p>
   * Method under test: {@link RequestBuilderBase#setUrl(String)}
   */
  @Test
  @DisplayName("Test setUrl(String); when 'https://example.org/example'; then RequestBuilder() uri NonEmptyPath is '/example'")
  void testSetUrl_whenHttpsExampleOrgExample_thenRequestBuilderUriNonEmptyPathIsExample() throws URISyntaxException {
    // Arrange
    RequestBuilder requestBuilder = new RequestBuilder();

    // Act
    RequestBuilder actualSetUrlResult = requestBuilder.setUrl("https://example.org/example");

    // Assert
    Uri uri = requestBuilder.uri;
    assertEquals("/example", uri.getNonEmptyPath());
    Uri uri2 = actualSetUrlResult.uri;
    assertEquals("/example", uri2.getNonEmptyPath());
    assertEquals("/example", uri.getPath());
    assertEquals("/example", uri2.getPath());
    assertEquals("example.org", uri.getHost());
    assertEquals("example.org", uri2.getHost());
    assertEquals("example.org:443", uri.getAuthority());
    assertEquals("example.org:443", uri2.getAuthority());
    assertEquals("https", uri.getScheme());
    assertEquals("https", uri2.getScheme());
    assertEquals("https://example.org/example", uri.toJavaNetURI().toString());
    assertEquals("https://example.org/example", uri2.toJavaNetURI().toString());
    assertEquals("https://example.org:443", uri.getBaseUrl());
    assertEquals("https://example.org:443", uri2.getBaseUrl());
    assertEquals(443, uri.getExplicitPort());
    assertEquals(443, uri2.getExplicitPort());
    assertEquals(443, uri.getSchemeDefaultPort());
    assertEquals(443, uri2.getSchemeDefaultPort());
    assertTrue(uri.isSecured());
    assertTrue(uri2.isSecured());
  }

  /**
   * Test {@link RequestBuilderBase#setUri(Uri)}.
   * <p>
   * Method under test: {@link RequestBuilderBase#setUri(Uri)}
   */
  @Test
  @DisplayName("Test setUri(Uri)")
  void testSetUri() throws URISyntaxException {
    // Arrange
    RequestBuilder requestBuilder = new RequestBuilder();

    // Act
    RequestBuilder actualSetUriResult = requestBuilder
        .setUri(new Uri("https://example.org/example", "https://example.org/example", "https://example.org/example",
            8080, "https://example.org/example", "https://example.org/example", "https://example.org/example"));

    // Assert
    Uri uri = requestBuilder.uri;
    assertEquals("https://example.org/example", uri.getFragment());
    assertEquals("https://example.org/example", uri.getHost());
    assertEquals("https://example.org/example", uri.getNonEmptyPath());
    assertEquals("https://example.org/example", uri.getPath());
    assertEquals("https://example.org/example", uri.getQuery());
    assertEquals("https://example.org/example", uri.getScheme());
    assertEquals("https://example.org/example", uri.getUserInfo());
    assertEquals("https://example.org/example://https://example.org/example:8080", uri.getBaseUrl());
    assertEquals("https://example.org/example://https://example.org/example@https://example.org/example:8080https:/"
        + "/example.org/example?https://example.org/example", uri.toJavaNetURI().toString());
    assertEquals("https://example.org/example:8080", uri.getAuthority());
    assertEquals(80, uri.getSchemeDefaultPort());
    assertEquals(8080, uri.getExplicitPort());
    assertEquals(8080, uri.getPort());
    assertFalse(uri.isSecured());
    assertFalse(uri.isWebSocket());
    assertSame(requestBuilder, actualSetUriResult);
  }

  /**
   * Test {@link RequestBuilderBase#setAddress(InetAddress)}.
   * <p>
   * Method under test: {@link RequestBuilderBase#setAddress(InetAddress)}
   */
  @Test
  @DisplayName("Test setAddress(InetAddress)")
  void testSetAddress() {
    // Arrange
    RequestBuilder requestBuilder = new RequestBuilder();

    // Act and Assert
    assertSame(requestBuilder, requestBuilder.setAddress(mock(InetAddress.class)));
  }

  /**
   * Test {@link RequestBuilderBase#setLocalAddress(InetAddress)}.
   * <p>
   * Method under test: {@link RequestBuilderBase#setLocalAddress(InetAddress)}
   */
  @Test
  @DisplayName("Test setLocalAddress(InetAddress)")
  void testSetLocalAddress() {
    // Arrange
    RequestBuilder requestBuilder = new RequestBuilder();

    // Act and Assert
    assertSame(requestBuilder, requestBuilder.setLocalAddress(mock(InetAddress.class)));
  }

  /**
   * Test {@link RequestBuilderBase#clearHeaders()}.
   * <p>
   * Method under test: {@link RequestBuilderBase#clearHeaders()}
   */
  @Test
  @DisplayName("Test clearHeaders()")
  void testClearHeaders() {
    // Arrange
    RequestBuilder requestBuilder = new RequestBuilder();

    // Act and Assert
    assertSame(requestBuilder, requestBuilder.clearHeaders());
  }

  /**
   * Test {@link RequestBuilderBase#setHeader(CharSequence, Iterable)} with
   * {@code CharSequence}, {@code Iterable}.
   * <p>
   * Method under test:
   * {@link RequestBuilderBase#setHeader(CharSequence, Iterable)}
   */
  @Test
  @DisplayName("Test setHeader(CharSequence, Iterable) with 'CharSequence', 'Iterable'")
  void testSetHeaderWithCharSequenceIterable() {
    // Arrange
    RequestBuilder requestBuilder = new RequestBuilder();
    requestBuilder.addHeader(AsyncHttpClientConfigDefaults.ACQUIRE_FREE_CHANNEL_TIMEOUT, "https://example.org/example");

    // Act
    RequestBuilder actualSetHeaderResult = requestBuilder
        .setHeader(AsyncHttpClientConfigDefaults.ACQUIRE_FREE_CHANNEL_TIMEOUT, (Iterable<?>) new ArrayList<>());

    // Assert
    HttpHeaders httpHeaders = requestBuilder.headers;
    assertTrue(httpHeaders instanceof DefaultHttpHeaders);
    assertEquals(0, httpHeaders.size());
    assertTrue(httpHeaders.isEmpty());
    assertSame(requestBuilder, actualSetHeaderResult);
  }

  /**
   * Test {@link RequestBuilderBase#setHeader(CharSequence, Iterable)} with
   * {@code CharSequence}, {@code Iterable}.
   * <p>
   * Method under test:
   * {@link RequestBuilderBase#setHeader(CharSequence, Iterable)}
   */
  @Test
  @DisplayName("Test setHeader(CharSequence, Iterable) with 'CharSequence', 'Iterable'")
  void testSetHeaderWithCharSequenceIterable2() {
    // Arrange
    RequestBuilder requestBuilder = new RequestBuilder();
    requestBuilder.addHeader(AsyncHttpClientConfigDefaults.ACQUIRE_FREE_CHANNEL_TIMEOUT, "42");
    requestBuilder.addHeader(AsyncHttpClientConfigDefaults.ACQUIRE_FREE_CHANNEL_TIMEOUT, "https://example.org/example");

    // Act
    RequestBuilder actualSetHeaderResult = requestBuilder
        .setHeader(AsyncHttpClientConfigDefaults.ACQUIRE_FREE_CHANNEL_TIMEOUT, (Iterable<?>) new ArrayList<>());

    // Assert
    HttpHeaders httpHeaders = requestBuilder.headers;
    assertTrue(httpHeaders instanceof DefaultHttpHeaders);
    assertEquals(0, httpHeaders.size());
    assertTrue(httpHeaders.isEmpty());
    assertSame(requestBuilder, actualSetHeaderResult);
  }

  /**
   * Test {@link RequestBuilderBase#setHeader(CharSequence, Iterable)} with
   * {@code CharSequence}, {@code Iterable}.
   * <ul>
   *   <li>Given {@link RequestBuilder#RequestBuilder()}.</li>
   * </ul>
   * <p>
   * Method under test:
   * {@link RequestBuilderBase#setHeader(CharSequence, Iterable)}
   */
  @Test
  @DisplayName("Test setHeader(CharSequence, Iterable) with 'CharSequence', 'Iterable'; given RequestBuilder()")
  void testSetHeaderWithCharSequenceIterable_givenRequestBuilder() {
    // Arrange
    RequestBuilder requestBuilder = new RequestBuilder();

    // Act
    RequestBuilder actualSetHeaderResult = requestBuilder
        .setHeader(AsyncHttpClientConfigDefaults.ACQUIRE_FREE_CHANNEL_TIMEOUT, (Iterable<?>) new ArrayList<>());

    // Assert
    HttpHeaders httpHeaders = requestBuilder.headers;
    assertTrue(httpHeaders instanceof DefaultHttpHeaders);
    assertEquals(0, httpHeaders.size());
    assertTrue(httpHeaders.isEmpty());
    assertSame(requestBuilder, actualSetHeaderResult);
  }

  /**
   * Test {@link RequestBuilderBase#setHeader(CharSequence, Object)} with
   * {@code CharSequence}, {@code Object}.
   * <p>
   * Method under test: {@link RequestBuilderBase#setHeader(CharSequence, Object)}
   */
  @Test
  @DisplayName("Test setHeader(CharSequence, Object) with 'CharSequence', 'Object'")
  void testSetHeaderWithCharSequenceObject() {
    // Arrange
    RequestBuilder requestBuilder = new RequestBuilder();
    requestBuilder.addHeader(AsyncHttpClientConfigDefaults.ACQUIRE_FREE_CHANNEL_TIMEOUT, "https://example.org/example");

    // Act
    RequestBuilder actualSetHeaderResult = requestBuilder
        .setHeader(AsyncHttpClientConfigDefaults.ACQUIRE_FREE_CHANNEL_TIMEOUT, (Object) "Value");

    // Assert
    HttpHeaders httpHeaders = requestBuilder.headers;
    assertTrue(httpHeaders instanceof DefaultHttpHeaders);
    assertEquals(1, httpHeaders.size());
    assertFalse(httpHeaders.isEmpty());
    assertSame(requestBuilder, actualSetHeaderResult);
  }

  /**
   * Test {@link RequestBuilderBase#setHeader(CharSequence, Object)} with
   * {@code CharSequence}, {@code Object}.
   * <p>
   * Method under test: {@link RequestBuilderBase#setHeader(CharSequence, Object)}
   */
  @Test
  @DisplayName("Test setHeader(CharSequence, Object) with 'CharSequence', 'Object'")
  void testSetHeaderWithCharSequenceObject2() {
    // Arrange
    RequestBuilder requestBuilder = new RequestBuilder();
    requestBuilder.addHeader(AsyncHttpClientConfigDefaults.ACQUIRE_FREE_CHANNEL_TIMEOUT, "42");
    requestBuilder.addHeader(AsyncHttpClientConfigDefaults.ACQUIRE_FREE_CHANNEL_TIMEOUT, "https://example.org/example");

    // Act
    RequestBuilder actualSetHeaderResult = requestBuilder
        .setHeader(AsyncHttpClientConfigDefaults.ACQUIRE_FREE_CHANNEL_TIMEOUT, (Object) "Value");

    // Assert
    HttpHeaders httpHeaders = requestBuilder.headers;
    assertTrue(httpHeaders instanceof DefaultHttpHeaders);
    assertEquals(1, httpHeaders.size());
    assertFalse(httpHeaders.isEmpty());
    assertSame(requestBuilder, actualSetHeaderResult);
  }

  /**
   * Test {@link RequestBuilderBase#setHeader(CharSequence, Object)} with
   * {@code CharSequence}, {@code Object}.
   * <ul>
   *   <li>Given {@link RequestBuilder#RequestBuilder()}.</li>
   * </ul>
   * <p>
   * Method under test: {@link RequestBuilderBase#setHeader(CharSequence, Object)}
   */
  @Test
  @DisplayName("Test setHeader(CharSequence, Object) with 'CharSequence', 'Object'; given RequestBuilder()")
  void testSetHeaderWithCharSequenceObject_givenRequestBuilder() {
    // Arrange
    RequestBuilder requestBuilder = new RequestBuilder();

    // Act
    RequestBuilder actualSetHeaderResult = requestBuilder
        .setHeader(AsyncHttpClientConfigDefaults.ACQUIRE_FREE_CHANNEL_TIMEOUT, (Object) "Value");

    // Assert
    HttpHeaders httpHeaders = requestBuilder.headers;
    assertTrue(httpHeaders instanceof DefaultHttpHeaders);
    assertEquals(1, httpHeaders.size());
    assertFalse(httpHeaders.isEmpty());
    assertSame(requestBuilder, actualSetHeaderResult);
  }

  /**
   * Test {@link RequestBuilderBase#setHeader(CharSequence, String)} with
   * {@code CharSequence}, {@code String}.
   * <p>
   * Method under test: {@link RequestBuilderBase#setHeader(CharSequence, String)}
   */
  @Test
  @DisplayName("Test setHeader(CharSequence, String) with 'CharSequence', 'String'")
  void testSetHeaderWithCharSequenceString() {
    // Arrange
    RequestBuilder requestBuilder = new RequestBuilder();
    requestBuilder.addHeader(AsyncHttpClientConfigDefaults.ACQUIRE_FREE_CHANNEL_TIMEOUT, "https://example.org/example");

    // Act
    RequestBuilder actualSetHeaderResult = requestBuilder
        .setHeader(AsyncHttpClientConfigDefaults.ACQUIRE_FREE_CHANNEL_TIMEOUT, "https://example.org/example");

    // Assert
    HttpHeaders httpHeaders = requestBuilder.headers;
    assertTrue(httpHeaders instanceof DefaultHttpHeaders);
    assertEquals(1, httpHeaders.size());
    assertFalse(httpHeaders.isEmpty());
    assertSame(requestBuilder, actualSetHeaderResult);
  }

  /**
   * Test {@link RequestBuilderBase#setHeader(CharSequence, String)} with
   * {@code CharSequence}, {@code String}.
   * <p>
   * Method under test: {@link RequestBuilderBase#setHeader(CharSequence, String)}
   */
  @Test
  @DisplayName("Test setHeader(CharSequence, String) with 'CharSequence', 'String'")
  void testSetHeaderWithCharSequenceString2() {
    // Arrange
    RequestBuilder requestBuilder = new RequestBuilder();
    requestBuilder.addHeader(AsyncHttpClientConfigDefaults.ACQUIRE_FREE_CHANNEL_TIMEOUT, "42");
    requestBuilder.addHeader(AsyncHttpClientConfigDefaults.ACQUIRE_FREE_CHANNEL_TIMEOUT, "https://example.org/example");

    // Act
    RequestBuilder actualSetHeaderResult = requestBuilder
        .setHeader(AsyncHttpClientConfigDefaults.ACQUIRE_FREE_CHANNEL_TIMEOUT, "https://example.org/example");

    // Assert
    HttpHeaders httpHeaders = requestBuilder.headers;
    assertTrue(httpHeaders instanceof DefaultHttpHeaders);
    assertEquals(1, httpHeaders.size());
    assertFalse(httpHeaders.isEmpty());
    assertSame(requestBuilder, actualSetHeaderResult);
  }

  /**
   * Test {@link RequestBuilderBase#setHeader(CharSequence, String)} with
   * {@code CharSequence}, {@code String}.
   * <ul>
   *   <li>Given {@link RequestBuilder#RequestBuilder()}.</li>
   * </ul>
   * <p>
   * Method under test: {@link RequestBuilderBase#setHeader(CharSequence, String)}
   */
  @Test
  @DisplayName("Test setHeader(CharSequence, String) with 'CharSequence', 'String'; given RequestBuilder()")
  void testSetHeaderWithCharSequenceString_givenRequestBuilder() {
    // Arrange
    RequestBuilder requestBuilder = new RequestBuilder();

    // Act
    RequestBuilder actualSetHeaderResult = requestBuilder
        .setHeader(AsyncHttpClientConfigDefaults.ACQUIRE_FREE_CHANNEL_TIMEOUT, "https://example.org/example");

    // Assert
    HttpHeaders httpHeaders = requestBuilder.headers;
    assertTrue(httpHeaders instanceof DefaultHttpHeaders);
    assertEquals(1, httpHeaders.size());
    assertFalse(httpHeaders.isEmpty());
    assertSame(requestBuilder, actualSetHeaderResult);
  }

  /**
   * Test {@link RequestBuilderBase#addHeader(CharSequence, Iterable)} with
   * {@code CharSequence}, {@code Iterable}.
   * <ul>
   *   <li>Then return {@link RequestBuilderBase#headers} unwrap size is zero.</li>
   * </ul>
   * <p>
   * Method under test:
   * {@link RequestBuilderBase#addHeader(CharSequence, Iterable)}
   */
  @Test
  @DisplayName("Test addHeader(CharSequence, Iterable) with 'CharSequence', 'Iterable'; then return headers unwrap size is zero")
  void testAddHeaderWithCharSequenceIterable_thenReturnHeadersUnwrapSizeIsZero() {
    // Arrange
    RequestBuilder requestBuilder = new RequestBuilder();

    // Act and Assert
    HttpHeaders httpHeaders = requestBuilder.addHeader(AsyncHttpClientConfigDefaults.ACQUIRE_FREE_CHANNEL_TIMEOUT,
        (Iterable<?>) new ArrayList<>()).headers;
    Headers<CharSequence, CharSequence, ?> unwrapResult = ((DefaultHttpHeaders) httpHeaders).unwrap();
    assertTrue(unwrapResult instanceof DefaultHeadersImpl);
    HttpHeaders httpHeaders2 = requestBuilder.headers;
    assertTrue(httpHeaders2 instanceof DefaultHttpHeaders);
    assertTrue(httpHeaders instanceof DefaultHttpHeaders);
    assertEquals(0, unwrapResult.size());
    assertEquals(0, httpHeaders2.size());
    assertEquals(0, httpHeaders.size());
    assertFalse(unwrapResult.iterator().hasNext());
    assertTrue(unwrapResult.isEmpty());
    assertTrue(httpHeaders.isEmpty());
  }

  /**
   * Test {@link RequestBuilderBase#addHeader(CharSequence, Object)} with
   * {@code CharSequence}, {@code Object}.
   * <ul>
   *   <li>When
   * {@link AsyncHttpClientConfigDefaults#ACQUIRE_FREE_CHANNEL_TIMEOUT}.</li>
   * </ul>
   * <p>
   * Method under test: {@link RequestBuilderBase#addHeader(CharSequence, Object)}
   */
  @Test
  @DisplayName("Test addHeader(CharSequence, Object) with 'CharSequence', 'Object'; when ACQUIRE_FREE_CHANNEL_TIMEOUT")
  void testAddHeaderWithCharSequenceObject_whenAcquire_free_channel_timeout() {
    // Arrange
    RequestBuilder requestBuilder = new RequestBuilder();

    // Act
    RequestBuilder actualAddHeaderResult = requestBuilder
        .addHeader(AsyncHttpClientConfigDefaults.ACQUIRE_FREE_CHANNEL_TIMEOUT, (Object) "Value");

    // Assert
    HttpHeaders httpHeaders = requestBuilder.headers;
    assertTrue(httpHeaders instanceof DefaultHttpHeaders);
    assertEquals(1, httpHeaders.size());
    assertFalse(httpHeaders.isEmpty());
    assertSame(requestBuilder, actualAddHeaderResult);
  }

  /**
   * Test {@link RequestBuilderBase#addHeader(CharSequence, Object)} with
   * {@code CharSequence}, {@code Object}.
   * <ul>
   *   <li>When cached {@code String}.</li>
   * </ul>
   * <p>
   * Method under test: {@link RequestBuilderBase#addHeader(CharSequence, Object)}
   */
  @Test
  @DisplayName("Test addHeader(CharSequence, Object) with 'CharSequence', 'Object'; when cached 'String'")
  void testAddHeaderWithCharSequenceObject_whenCachedString() {
    // Arrange
    RequestBuilder requestBuilder = new RequestBuilder();

    // Act
    RequestBuilder actualAddHeaderResult = requestBuilder.addHeader(AsciiString.cached("String"), (Object) "Value");

    // Assert
    HttpHeaders httpHeaders = requestBuilder.headers;
    assertTrue(httpHeaders instanceof DefaultHttpHeaders);
    assertEquals(1, httpHeaders.size());
    assertFalse(httpHeaders.isEmpty());
    assertSame(requestBuilder, actualAddHeaderResult);
  }

  /**
   * Test {@link RequestBuilderBase#addHeader(CharSequence, Object)} with
   * {@code CharSequence}, {@code Object}.
   * <ul>
   *   <li>When {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link RequestBuilderBase#addHeader(CharSequence, Object)}
   */
  @Test
  @DisplayName("Test addHeader(CharSequence, Object) with 'CharSequence', 'Object'; when 'null'")
  void testAddHeaderWithCharSequenceObject_whenNull() {
    // Arrange
    RequestBuilder requestBuilder = new RequestBuilder();

    // Act
    RequestBuilder actualAddHeaderResult = requestBuilder
        .addHeader(AsyncHttpClientConfigDefaults.ACQUIRE_FREE_CHANNEL_TIMEOUT, (Object) null);

    // Assert
    HttpHeaders httpHeaders = requestBuilder.headers;
    assertTrue(httpHeaders instanceof DefaultHttpHeaders);
    assertEquals(1, httpHeaders.size());
    assertFalse(httpHeaders.isEmpty());
    assertSame(requestBuilder, actualAddHeaderResult);
  }

  /**
   * Test {@link RequestBuilderBase#addHeader(CharSequence, String)} with
   * {@code CharSequence}, {@code String}.
   * <ul>
   *   <li>When
   * {@link AsyncHttpClientConfigDefaults#ACQUIRE_FREE_CHANNEL_TIMEOUT}.</li>
   * </ul>
   * <p>
   * Method under test: {@link RequestBuilderBase#addHeader(CharSequence, String)}
   */
  @Test
  @DisplayName("Test addHeader(CharSequence, String) with 'CharSequence', 'String'; when ACQUIRE_FREE_CHANNEL_TIMEOUT")
  void testAddHeaderWithCharSequenceString_whenAcquire_free_channel_timeout() {
    // Arrange
    RequestBuilder requestBuilder = new RequestBuilder();

    // Act
    RequestBuilder actualAddHeaderResult = requestBuilder
        .addHeader(AsyncHttpClientConfigDefaults.ACQUIRE_FREE_CHANNEL_TIMEOUT, "https://example.org/example");

    // Assert
    HttpHeaders httpHeaders = requestBuilder.headers;
    assertTrue(httpHeaders instanceof DefaultHttpHeaders);
    assertEquals(1, httpHeaders.size());
    assertFalse(httpHeaders.isEmpty());
    assertSame(requestBuilder, actualAddHeaderResult);
  }

  /**
   * Test {@link RequestBuilderBase#addHeader(CharSequence, String)} with
   * {@code CharSequence}, {@code String}.
   * <ul>
   *   <li>When cached {@code String}.</li>
   * </ul>
   * <p>
   * Method under test: {@link RequestBuilderBase#addHeader(CharSequence, String)}
   */
  @Test
  @DisplayName("Test addHeader(CharSequence, String) with 'CharSequence', 'String'; when cached 'String'")
  void testAddHeaderWithCharSequenceString_whenCachedString() {
    // Arrange
    RequestBuilder requestBuilder = new RequestBuilder();

    // Act
    RequestBuilder actualAddHeaderResult = requestBuilder.addHeader(AsciiString.cached("String"),
        "https://example.org/example");

    // Assert
    HttpHeaders httpHeaders = requestBuilder.headers;
    assertTrue(httpHeaders instanceof DefaultHttpHeaders);
    assertEquals(1, httpHeaders.size());
    assertFalse(httpHeaders.isEmpty());
    assertSame(requestBuilder, actualAddHeaderResult);
  }

  /**
   * Test {@link RequestBuilderBase#addHeader(CharSequence, String)} with
   * {@code CharSequence}, {@code String}.
   * <ul>
   *   <li>When {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link RequestBuilderBase#addHeader(CharSequence, String)}
   */
  @Test
  @DisplayName("Test addHeader(CharSequence, String) with 'CharSequence', 'String'; when 'null'")
  void testAddHeaderWithCharSequenceString_whenNull() {
    // Arrange
    RequestBuilder requestBuilder = new RequestBuilder();

    // Act
    RequestBuilder actualAddHeaderResult = requestBuilder
        .addHeader(AsyncHttpClientConfigDefaults.ACQUIRE_FREE_CHANNEL_TIMEOUT, (String) null);

    // Assert
    HttpHeaders httpHeaders = requestBuilder.headers;
    assertTrue(httpHeaders instanceof DefaultHttpHeaders);
    assertEquals(1, httpHeaders.size());
    assertFalse(httpHeaders.isEmpty());
    assertSame(requestBuilder, actualAddHeaderResult);
  }

  /**
   * Test {@link RequestBuilderBase#setHeaders(HttpHeaders)} with
   * {@code HttpHeaders}.
   * <ul>
   *   <li>When {@link DefaultHttpHeaders#DefaultHttpHeaders()}.</li>
   * </ul>
   * <p>
   * Method under test: {@link RequestBuilderBase#setHeaders(HttpHeaders)}
   */
  @Test
  @DisplayName("Test setHeaders(HttpHeaders) with 'HttpHeaders'; when DefaultHttpHeaders()")
  void testSetHeadersWithHttpHeaders_whenDefaultHttpHeaders() {
    // Arrange
    RequestBuilder requestBuilder = new RequestBuilder();

    // Act and Assert
    assertSame(requestBuilder, requestBuilder.setHeaders(new DefaultHttpHeaders()));
  }

  /**
   * Test {@link RequestBuilderBase#setHeaders(HttpHeaders)} with
   * {@code HttpHeaders}.
   * <ul>
   *   <li>When {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link RequestBuilderBase#setHeaders(HttpHeaders)}
   */
  @Test
  @DisplayName("Test setHeaders(HttpHeaders) with 'HttpHeaders'; when 'null'")
  void testSetHeadersWithHttpHeaders_whenNull() {
    // Arrange
    RequestBuilder requestBuilder = new RequestBuilder();

    // Act and Assert
    assertSame(requestBuilder, requestBuilder.setHeaders((HttpHeaders) null));
  }

  /**
   * Test {@link RequestBuilderBase#setHeaders(Map)} with {@code Map}.
   * <ul>
   *   <li>Given {@link ArrayList#ArrayList()}.</li>
   *   <li>Then return {@link RequestBuilderBase#headers} unwrap size is zero.</li>
   * </ul>
   * <p>
   * Method under test: {@link RequestBuilderBase#setHeaders(Map)}
   */
  @Test
  @DisplayName("Test setHeaders(Map) with 'Map'; given ArrayList(); then return headers unwrap size is zero")
  void testSetHeadersWithMap_givenArrayList_thenReturnHeadersUnwrapSizeIsZero() {
    // Arrange
    RequestBuilder requestBuilder = new RequestBuilder();

    HashMap<CharSequence, Iterable<?>> headers = new HashMap<>();
    headers.put(AsyncHttpClientConfigDefaults.ACQUIRE_FREE_CHANNEL_TIMEOUT, new ArrayList<>());

    // Act and Assert
    HttpHeaders httpHeaders = requestBuilder.setHeaders(headers).headers;
    Headers<CharSequence, CharSequence, ?> unwrapResult = ((DefaultHttpHeaders) httpHeaders).unwrap();
    assertTrue(unwrapResult instanceof DefaultHeadersImpl);
    HttpHeaders httpHeaders2 = requestBuilder.headers;
    assertTrue(httpHeaders2 instanceof DefaultHttpHeaders);
    assertTrue(httpHeaders instanceof DefaultHttpHeaders);
    assertEquals(0, unwrapResult.size());
    assertEquals(0, httpHeaders2.size());
    assertEquals(0, httpHeaders.size());
    assertFalse(unwrapResult.iterator().hasNext());
    assertTrue(unwrapResult.isEmpty());
    assertTrue(httpHeaders2.isEmpty());
    assertTrue(httpHeaders.isEmpty());
  }

  /**
   * Test {@link RequestBuilderBase#setHeaders(Map)} with {@code Map}.
   * <ul>
   *   <li>When {@link HashMap#HashMap()}.</li>
   *   <li>Then return {@link RequestBuilderBase#headers} unwrap size is zero.</li>
   * </ul>
   * <p>
   * Method under test: {@link RequestBuilderBase#setHeaders(Map)}
   */
  @Test
  @DisplayName("Test setHeaders(Map) with 'Map'; when HashMap(); then return headers unwrap size is zero")
  void testSetHeadersWithMap_whenHashMap_thenReturnHeadersUnwrapSizeIsZero() {
    // Arrange
    RequestBuilder requestBuilder = new RequestBuilder();

    // Act and Assert
    HttpHeaders httpHeaders = requestBuilder.setHeaders(new HashMap<>()).headers;
    Headers<CharSequence, CharSequence, ?> unwrapResult = ((DefaultHttpHeaders) httpHeaders).unwrap();
    assertTrue(unwrapResult instanceof DefaultHeadersImpl);
    HttpHeaders httpHeaders2 = requestBuilder.headers;
    assertTrue(httpHeaders2 instanceof DefaultHttpHeaders);
    assertTrue(httpHeaders instanceof DefaultHttpHeaders);
    assertEquals(0, unwrapResult.size());
    assertEquals(0, httpHeaders2.size());
    assertEquals(0, httpHeaders.size());
    assertFalse(unwrapResult.iterator().hasNext());
    assertTrue(unwrapResult.isEmpty());
    assertTrue(httpHeaders2.isEmpty());
    assertTrue(httpHeaders.isEmpty());
  }

  /**
   * Test {@link RequestBuilderBase#setHeaders(Map)} with {@code Map}.
   * <ul>
   *   <li>When {@code null}.</li>
   *   <li>Then return {@link RequestBuilderBase#headers} unwrap size is zero.</li>
   * </ul>
   * <p>
   * Method under test: {@link RequestBuilderBase#setHeaders(Map)}
   */
  @Test
  @DisplayName("Test setHeaders(Map) with 'Map'; when 'null'; then return headers unwrap size is zero")
  void testSetHeadersWithMap_whenNull_thenReturnHeadersUnwrapSizeIsZero() {
    // Arrange
    RequestBuilder requestBuilder = new RequestBuilder();

    // Act and Assert
    HttpHeaders httpHeaders = requestBuilder
        .setHeaders((Map<? extends CharSequence, ? extends Iterable<?>>) null).headers;
    Headers<CharSequence, CharSequence, ?> unwrapResult = ((DefaultHttpHeaders) httpHeaders).unwrap();
    assertTrue(unwrapResult instanceof DefaultHeadersImpl);
    HttpHeaders httpHeaders2 = requestBuilder.headers;
    assertTrue(httpHeaders2 instanceof DefaultHttpHeaders);
    assertTrue(httpHeaders instanceof DefaultHttpHeaders);
    assertEquals(0, unwrapResult.size());
    assertEquals(0, httpHeaders2.size());
    assertEquals(0, httpHeaders.size());
    assertFalse(unwrapResult.iterator().hasNext());
    assertTrue(unwrapResult.isEmpty());
    assertTrue(httpHeaders2.isEmpty());
    assertTrue(httpHeaders.isEmpty());
  }

  /**
   * Test {@link RequestBuilderBase#setSingleHeaders(Map)}.
   * <ul>
   *   <li>When {@link HashMap#HashMap()}.</li>
   *   <li>Then return {@link RequestBuilderBase#headers} unwrap size is zero.</li>
   * </ul>
   * <p>
   * Method under test: {@link RequestBuilderBase#setSingleHeaders(Map)}
   */
  @Test
  @DisplayName("Test setSingleHeaders(Map); when HashMap(); then return headers unwrap size is zero")
  void testSetSingleHeaders_whenHashMap_thenReturnHeadersUnwrapSizeIsZero() {
    // Arrange
    RequestBuilder requestBuilder = new RequestBuilder();

    // Act and Assert
    HttpHeaders httpHeaders = requestBuilder.setSingleHeaders(new HashMap<>()).headers;
    Headers<CharSequence, CharSequence, ?> unwrapResult = ((DefaultHttpHeaders) httpHeaders).unwrap();
    assertTrue(unwrapResult instanceof DefaultHeadersImpl);
    HttpHeaders httpHeaders2 = requestBuilder.headers;
    assertTrue(httpHeaders2 instanceof DefaultHttpHeaders);
    assertTrue(httpHeaders instanceof DefaultHttpHeaders);
    assertEquals(0, unwrapResult.size());
    assertEquals(0, httpHeaders2.size());
    assertEquals(0, httpHeaders.size());
    assertFalse(unwrapResult.iterator().hasNext());
    assertTrue(unwrapResult.isEmpty());
    assertTrue(httpHeaders2.isEmpty());
    assertTrue(httpHeaders.isEmpty());
  }

  /**
   * Test {@link RequestBuilderBase#setSingleHeaders(Map)}.
   * <ul>
   *   <li>When {@code null}.</li>
   *   <li>Then return {@link RequestBuilderBase#headers} unwrap size is zero.</li>
   * </ul>
   * <p>
   * Method under test: {@link RequestBuilderBase#setSingleHeaders(Map)}
   */
  @Test
  @DisplayName("Test setSingleHeaders(Map); when 'null'; then return headers unwrap size is zero")
  void testSetSingleHeaders_whenNull_thenReturnHeadersUnwrapSizeIsZero() {
    // Arrange
    RequestBuilder requestBuilder = new RequestBuilder();

    // Act and Assert
    HttpHeaders httpHeaders = requestBuilder.setSingleHeaders(null).headers;
    Headers<CharSequence, CharSequence, ?> unwrapResult = ((DefaultHttpHeaders) httpHeaders).unwrap();
    assertTrue(unwrapResult instanceof DefaultHeadersImpl);
    HttpHeaders httpHeaders2 = requestBuilder.headers;
    assertTrue(httpHeaders2 instanceof DefaultHttpHeaders);
    assertTrue(httpHeaders instanceof DefaultHttpHeaders);
    assertEquals(0, unwrapResult.size());
    assertEquals(0, httpHeaders2.size());
    assertEquals(0, httpHeaders.size());
    assertFalse(unwrapResult.iterator().hasNext());
    assertTrue(unwrapResult.isEmpty());
    assertTrue(httpHeaders2.isEmpty());
    assertTrue(httpHeaders.isEmpty());
  }

  /**
   * Test {@link RequestBuilderBase#setCookies(Collection)}.
   * <ul>
   *   <li>Then {@link RequestBuilder#RequestBuilder()}
   * {@link RequestBuilderBase#cookies} size is one.</li>
   * </ul>
   * <p>
   * Method under test: {@link RequestBuilderBase#setCookies(Collection)}
   */
  @Test
  @DisplayName("Test setCookies(Collection); then RequestBuilder() cookies size is one")
  void testSetCookies_thenRequestBuilderCookiesSizeIsOne() {
    // Arrange
    RequestBuilder requestBuilder = new RequestBuilder();

    ArrayList<Cookie> cookies = new ArrayList<>();
    DefaultCookie defaultCookie = new DefaultCookie("https://example.org/example", "https://example.org/example");

    cookies.add(defaultCookie);

    // Act
    RequestBuilder actualSetCookiesResult = requestBuilder.setCookies(cookies);

    // Assert
    ArrayList<Cookie> cookieList = requestBuilder.cookies;
    assertEquals(1, cookieList.size());
    ArrayList<Cookie> cookieList2 = actualSetCookiesResult.cookies;
    assertEquals(1, cookieList2.size());
    assertSame(defaultCookie, cookieList.get(0));
    assertSame(defaultCookie, cookieList2.get(0));
  }

  /**
   * Test {@link RequestBuilderBase#setCookies(Collection)}.
   * <ul>
   *   <li>Then {@link RequestBuilder#RequestBuilder()}
   * {@link RequestBuilderBase#cookies} size is two.</li>
   * </ul>
   * <p>
   * Method under test: {@link RequestBuilderBase#setCookies(Collection)}
   */
  @Test
  @DisplayName("Test setCookies(Collection); then RequestBuilder() cookies size is two")
  void testSetCookies_thenRequestBuilderCookiesSizeIsTwo() {
    // Arrange
    RequestBuilder requestBuilder = new RequestBuilder();

    ArrayList<Cookie> cookies = new ArrayList<>();
    cookies.add(new DefaultCookie("https://example.org/example", "https://example.org/example"));
    DefaultCookie defaultCookie = new DefaultCookie("https://example.org/example", "https://example.org/example");

    cookies.add(defaultCookie);

    // Act
    RequestBuilder actualSetCookiesResult = requestBuilder.setCookies(cookies);

    // Assert
    ArrayList<Cookie> cookieList = requestBuilder.cookies;
    assertEquals(2, cookieList.size());
    ArrayList<Cookie> cookieList2 = actualSetCookiesResult.cookies;
    assertEquals(2, cookieList2.size());
    assertSame(defaultCookie, cookieList.get(1));
    assertSame(defaultCookie, cookieList2.get(1));
  }

  /**
   * Test {@link RequestBuilderBase#setCookies(Collection)}.
   * <ul>
   *   <li>When {@link ArrayList#ArrayList()}.</li>
   *   <li>Then {@link ArrayList#ArrayList()} Empty.</li>
   * </ul>
   * <p>
   * Method under test: {@link RequestBuilderBase#setCookies(Collection)}
   */
  @Test
  @DisplayName("Test setCookies(Collection); when ArrayList(); then ArrayList() Empty")
  void testSetCookies_whenArrayList_thenArrayListEmpty() {
    // Arrange
    RequestBuilder requestBuilder = new RequestBuilder();
    ArrayList<Cookie> cookies = new ArrayList<>();

    // Act
    RequestBuilder actualSetCookiesResult = requestBuilder.setCookies(cookies);

    // Assert
    assertTrue(cookies.isEmpty());
    assertTrue(requestBuilder.cookies.isEmpty());
    assertSame(requestBuilder, actualSetCookiesResult);
  }

  /**
   * Test {@link RequestBuilderBase#addCookie(Cookie)}.
   * <ul>
   *   <li>Given {@link RequestBuilder#RequestBuilder()}.</li>
   * </ul>
   * <p>
   * Method under test: {@link RequestBuilderBase#addCookie(Cookie)}
   */
  @Test
  @DisplayName("Test addCookie(Cookie); given RequestBuilder()")
  void testAddCookie_givenRequestBuilder() {
    // Arrange
    RequestBuilder requestBuilder = new RequestBuilder();
    DefaultCookie cookie = new DefaultCookie("https://example.org/example", "https://example.org/example");

    // Act
    RequestBuilder actualAddCookieResult = requestBuilder.addCookie(cookie);

    // Assert
    ArrayList<Cookie> cookieList = requestBuilder.cookies;
    assertEquals(1, cookieList.size());
    assertSame(cookie, cookieList.get(0));
    assertSame(requestBuilder, actualAddCookieResult);
  }

  /**
   * Test {@link RequestBuilderBase#addCookie(Cookie)}.
   * <ul>
   *   <li>Given {@link RequestBuilder#RequestBuilder()} Cookies is
   * {@link ArrayList#ArrayList()}.</li>
   * </ul>
   * <p>
   * Method under test: {@link RequestBuilderBase#addCookie(Cookie)}
   */
  @Test
  @DisplayName("Test addCookie(Cookie); given RequestBuilder() Cookies is ArrayList()")
  void testAddCookie_givenRequestBuilderCookiesIsArrayList() {
    // Arrange
    RequestBuilder requestBuilder = new RequestBuilder();
    requestBuilder.setCookies(new ArrayList<>());
    DefaultCookie cookie = new DefaultCookie("https://example.org/example", "https://example.org/example");

    // Act
    RequestBuilder actualAddCookieResult = requestBuilder.addCookie(cookie);

    // Assert
    ArrayList<Cookie> cookieList = requestBuilder.cookies;
    assertEquals(1, cookieList.size());
    assertSame(cookie, cookieList.get(0));
    assertSame(requestBuilder, actualAddCookieResult);
  }

  /**
   * Test {@link RequestBuilderBase#addOrReplaceCookie(Cookie)}.
   * <ul>
   *   <li>Given {@link RequestBuilder#RequestBuilder()}.</li>
   *   <li>Then {@link RequestBuilder#RequestBuilder()}
   * {@link RequestBuilderBase#cookies} size is one.</li>
   * </ul>
   * <p>
   * Method under test: {@link RequestBuilderBase#addOrReplaceCookie(Cookie)}
   */
  @Test
  @DisplayName("Test addOrReplaceCookie(Cookie); given RequestBuilder(); then RequestBuilder() cookies size is one")
  void testAddOrReplaceCookie_givenRequestBuilder_thenRequestBuilderCookiesSizeIsOne() {
    // Arrange
    RequestBuilder requestBuilder = new RequestBuilder();
    DefaultCookie cookie = new DefaultCookie("https://example.org/example", "https://example.org/example");

    // Act
    RequestBuilder actualAddOrReplaceCookieResult = requestBuilder.addOrReplaceCookie(cookie);

    // Assert
    ArrayList<Cookie> cookieList = requestBuilder.cookies;
    assertEquals(1, cookieList.size());
    ArrayList<Cookie> cookieList2 = actualAddOrReplaceCookieResult.cookies;
    assertEquals(1, cookieList2.size());
    assertSame(cookie, cookieList.get(0));
    assertSame(cookie, cookieList2.get(0));
  }

  /**
   * Test {@link RequestBuilderBase#addOrReplaceCookie(Cookie)}.
   * <ul>
   *   <li>Then {@link RequestBuilderBase#cookies} first return
   * {@link DefaultCookie}.</li>
   * </ul>
   * <p>
   * Method under test: {@link RequestBuilderBase#addOrReplaceCookie(Cookie)}
   */
  @Test
  @DisplayName("Test addOrReplaceCookie(Cookie); then cookies first return DefaultCookie")
  void testAddOrReplaceCookie_thenCookiesFirstReturnDefaultCookie() {
    // Arrange
    RequestBuilder requestBuilder = new RequestBuilder();
    DefaultCookie cookie = new DefaultCookie("https://example.org/example", "https://example.org/example");

    requestBuilder.addCookie(cookie);
    DefaultCookie cookie2 = new DefaultCookie("https://example.org/example", "https://example.org/example");

    // Act and Assert
    ArrayList<Cookie> cookieList = requestBuilder.addOrReplaceCookie(cookie2).cookies;
    assertEquals(1, cookieList.size());
    Cookie getResult = cookieList.get(0);
    assertTrue(getResult instanceof DefaultCookie);
    ArrayList<Cookie> cookieList2 = requestBuilder.cookies;
    assertEquals(1, cookieList2.size());
    assertEquals(cookie, getResult);
    assertSame(cookie2, cookieList2.get(0));
  }

  /**
   * Test {@link RequestBuilderBase#addOrReplaceCookie(Cookie)}.
   * <ul>
   *   <li>Then {@link RequestBuilder#RequestBuilder()}
   * {@link RequestBuilderBase#cookies} size is two.</li>
   * </ul>
   * <p>
   * Method under test: {@link RequestBuilderBase#addOrReplaceCookie(Cookie)}
   */
  @Test
  @DisplayName("Test addOrReplaceCookie(Cookie); then RequestBuilder() cookies size is two")
  void testAddOrReplaceCookie_thenRequestBuilderCookiesSizeIsTwo() {
    // Arrange
    RequestBuilder requestBuilder = new RequestBuilder();
    DefaultCookie cookie = new DefaultCookie("Name", "https://example.org/example");

    requestBuilder.addCookie(cookie);
    DefaultCookie cookie2 = new DefaultCookie("https://example.org/example", "https://example.org/example");

    // Act
    RequestBuilder actualAddOrReplaceCookieResult = requestBuilder.addOrReplaceCookie(cookie2);

    // Assert
    ArrayList<Cookie> cookieList = requestBuilder.cookies;
    assertEquals(2, cookieList.size());
    ArrayList<Cookie> cookieList2 = actualAddOrReplaceCookieResult.cookies;
    assertEquals(2, cookieList2.size());
    assertSame(cookie, cookieList2.get(0));
    assertSame(cookie2, cookieList.get(1));
    assertSame(cookie2, cookieList2.get(1));
  }

  /**
   * Test {@link RequestBuilderBase#resetQuery()}.
   * <p>
   * Method under test: {@link RequestBuilderBase#resetQuery()}
   */
  @Test
  @DisplayName("Test resetQuery()")
  void testResetQuery() throws URISyntaxException {
    // Arrange
    RequestBuilder requestBuilder = new RequestBuilder();
    requestBuilder.setUri(new Uri(Uri.HTTPS, "https://example.org/example", "https://example.org/example", 8080,
        "https://example.org/example", "https://example.org/example", "https://example.org/example"));

    // Act
    requestBuilder.resetQuery();

    // Assert
    Uri uri = requestBuilder.uri;
    assertEquals("https://https://example.org/example@https://example.org/example:8080https://example.org/example",
        uri.toJavaNetURI().toString());
    assertNull(uri.getQuery());
  }

  /**
   * Test {@link RequestBuilderBase#resetQuery()}.
   * <p>
   * Method under test: {@link RequestBuilderBase#resetQuery()}
   */
  @Test
  @DisplayName("Test resetQuery()")
  void testResetQuery2() throws URISyntaxException {
    // Arrange
    RequestBuilder requestBuilder = new RequestBuilder();
    requestBuilder.setUri(new Uri(Uri.WSS, "https://example.org/example", "https://example.org/example", 8080,
        "https://example.org/example", "https://example.org/example", "https://example.org/example"));

    // Act
    requestBuilder.resetQuery();

    // Assert
    Uri uri = requestBuilder.uri;
    assertEquals("wss://https://example.org/example@https://example.org/example:8080https://example.org/example",
        uri.toJavaNetURI().toString());
    assertNull(uri.getQuery());
  }

  /**
   * Test {@link RequestBuilderBase#resetQuery()}.
   * <p>
   * Method under test: {@link RequestBuilderBase#resetQuery()}
   */
  @Test
  @DisplayName("Test resetQuery()")
  void testResetQuery3() throws URISyntaxException {
    // Arrange
    RequestBuilder requestBuilder = new RequestBuilder();
    requestBuilder.setUri(new Uri(Uri.WS, "https://example.org/example", "https://example.org/example", 8080,
        "https://example.org/example", "https://example.org/example", "https://example.org/example"));

    // Act
    requestBuilder.resetQuery();

    // Assert
    Uri uri = requestBuilder.uri;
    assertEquals("ws://https://example.org/example@https://example.org/example:8080https://example.org/example",
        uri.toJavaNetURI().toString());
    assertNull(uri.getQuery());
  }

  /**
   * Test {@link RequestBuilderBase#resetQuery()}.
   * <ul>
   *   <li>Then {@link RequestBuilder#RequestBuilder()}
   * {@link RequestBuilderBase#uri} toJavaNetURI toString is a string.</li>
   * </ul>
   * <p>
   * Method under test: {@link RequestBuilderBase#resetQuery()}
   */
  @Test
  @DisplayName("Test resetQuery(); then RequestBuilder() uri toJavaNetURI toString is a string")
  void testResetQuery_thenRequestBuilderUriToJavaNetURIToStringIsAString() throws URISyntaxException {
    // Arrange
    RequestBuilder requestBuilder = new RequestBuilder();
    requestBuilder
        .setUri(new Uri("https://example.org/example", "https://example.org/example", "https://example.org/example",
            8080, "https://example.org/example", "https://example.org/example", "https://example.org/example"));

    // Act
    requestBuilder.resetQuery();

    // Assert
    Uri uri = requestBuilder.uri;
    assertEquals("https://example.org/example://https://example.org/example@https://example.org/example:8080https:/"
        + "/example.org/example", uri.toJavaNetURI().toString());
    assertNull(uri.getQuery());
  }

  /**
   * Test {@link RequestBuilderBase#setBody(BodyGenerator)} with
   * {@code BodyGenerator}.
   * <p>
   * Method under test: {@link RequestBuilderBase#setBody(BodyGenerator)}
   */
  @Test
  @DisplayName("Test setBody(BodyGenerator) with 'BodyGenerator'")
  void testSetBodyWithBodyGenerator() {
    // Arrange
    RequestBuilder requestBuilder = new RequestBuilder();

    // Act and Assert
    assertSame(requestBuilder, requestBuilder.setBody(mock(BodyGenerator.class)));
  }

  /**
   * Test {@link RequestBuilderBase#setBody(byte[])} with {@code byte[]}.
   * <p>
   * Method under test: {@link RequestBuilderBase#setBody(byte[])}
   */
  @Test
  @DisplayName("Test setBody(byte[]) with 'byte[]'")
  void testSetBodyWithByte() throws UnsupportedEncodingException {
    // Arrange
    RequestBuilder requestBuilder = new RequestBuilder();

    // Act and Assert
    assertSame(requestBuilder, requestBuilder.setBody("AXAXAXAX".getBytes("UTF-8")));
    assertArrayEquals("AXAXAXAX".getBytes("UTF-8"), requestBuilder.byteData);
  }

  /**
   * Test {@link RequestBuilderBase#setBody(ByteBuffer)} with {@code ByteBuffer}.
   * <p>
   * Method under test: {@link RequestBuilderBase#setBody(ByteBuffer)}
   */
  @Test
  @DisplayName("Test setBody(ByteBuffer) with 'ByteBuffer'")
  void testSetBodyWithByteBuffer() throws UnsupportedEncodingException {
    // Arrange
    RequestBuilder requestBuilder = new RequestBuilder();

    // Act
    RequestBuilder actualSetBodyResult = requestBuilder.setBody(ByteBuffer.wrap("AXAXAXAX".getBytes("UTF-8")));

    // Assert
    ByteBuffer byteBuffer = requestBuilder.byteBufferData;
    assertEquals(0, byteBuffer.position());
    assertEquals(8, byteBuffer.capacity());
    assertEquals(8, byteBuffer.limit());
    assertTrue(byteBuffer.hasRemaining());
    assertTrue(byteBuffer.hasArray());
    assertSame(requestBuilder, actualSetBodyResult);
  }

  /**
   * Test {@link RequestBuilderBase#setBody(File)} with {@code File}.
   * <p>
   * Method under test: {@link RequestBuilderBase#setBody(File)}
   */
  @Test
  @DisplayName("Test setBody(File) with 'File'")
  void testSetBodyWithFile() {
    // Arrange
    RequestBuilder requestBuilder = new RequestBuilder();

    // Act
    RequestBuilder actualSetBodyResult = requestBuilder
        .setBody(Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toFile());

    // Assert
    File file = requestBuilder.file;
    assertEquals("test.txt", file.getName());
    assertTrue(file.isAbsolute());
    assertSame(requestBuilder, actualSetBodyResult);
  }

  /**
   * Test {@link RequestBuilderBase#setBody(InputStream)} with
   * {@code InputStream}.
   * <p>
   * Method under test: {@link RequestBuilderBase#setBody(InputStream)}
   */
  @Test
  @DisplayName("Test setBody(InputStream) with 'InputStream'")
  void testSetBodyWithInputStream() throws UnsupportedEncodingException {
    // Arrange
    RequestBuilder requestBuilder = new RequestBuilder();

    // Act and Assert
    assertSame(requestBuilder, requestBuilder.setBody(new ByteArrayInputStream("AXAXAXAX".getBytes("UTF-8"))));
  }

  /**
   * Test {@link RequestBuilderBase#setBody(List)} with {@code List}.
   * <ul>
   *   <li>When {@link ArrayList#ArrayList()}.</li>
   *   <li>Then {@link RequestBuilder#RequestBuilder()}
   * {@link RequestBuilderBase#compositeByteData} Empty.</li>
   * </ul>
   * <p>
   * Method under test: {@link RequestBuilderBase#setBody(List)}
   */
  @Test
  @DisplayName("Test setBody(List) with 'List'; when ArrayList(); then RequestBuilder() compositeByteData Empty")
  void testSetBodyWithList_whenArrayList_thenRequestBuilderCompositeByteDataEmpty() {
    // Arrange
    RequestBuilder requestBuilder = new RequestBuilder();

    // Act
    RequestBuilder actualSetBodyResult = requestBuilder.setBody(new ArrayList<>());

    // Assert
    assertTrue(requestBuilder.compositeByteData.isEmpty());
    assertSame(requestBuilder, actualSetBodyResult);
  }

  /**
   * Test {@link RequestBuilderBase#addQueryParams(List)}.
   * <ul>
   *   <li>Then return {@link RequestBuilderBase#queryParams} first Name is
   * {@code https://example.org/example}.</li>
   * </ul>
   * <p>
   * Method under test: {@link RequestBuilderBase#addQueryParams(List)}
   */
  @Test
  @DisplayName("Test addQueryParams(List); then return queryParams first Name is 'https://example.org/example'")
  void testAddQueryParams_thenReturnQueryParamsFirstNameIsHttpsExampleOrgExample() {
    // Arrange
    RequestBuilder requestBuilder = new RequestBuilder();
    requestBuilder.addQueryParam("https://example.org/example", "https://example.org/example");

    // Act and Assert
    List<Param> paramList = requestBuilder.addQueryParams(new ArrayList<>()).queryParams;
    assertEquals(1, paramList.size());
    Param getResult = paramList.get(0);
    assertEquals("https://example.org/example", getResult.getName());
    assertEquals("https://example.org/example", getResult.getValue());
    assertEquals(1, requestBuilder.queryParams.size());
  }

  /**
   * Test {@link RequestBuilderBase#addQueryParams(List)}.
   * <ul>
   *   <li>When {@link ArrayList#ArrayList()}.</li>
   *   <li>Then {@link RequestBuilder#RequestBuilder()}
   * {@link RequestBuilderBase#queryParams} Empty.</li>
   * </ul>
   * <p>
   * Method under test: {@link RequestBuilderBase#addQueryParams(List)}
   */
  @Test
  @DisplayName("Test addQueryParams(List); when ArrayList(); then RequestBuilder() queryParams Empty")
  void testAddQueryParams_whenArrayList_thenRequestBuilderQueryParamsEmpty() {
    // Arrange
    RequestBuilder requestBuilder = new RequestBuilder();

    // Act
    RequestBuilder actualAddQueryParamsResult = requestBuilder.addQueryParams(new ArrayList<>());

    // Assert
    assertTrue(requestBuilder.queryParams.isEmpty());
    assertSame(requestBuilder, actualAddQueryParamsResult);
  }

  /**
   * Test {@link RequestBuilderBase#setQueryParams(Map)} with {@code map}.
   * <ul>
   *   <li>Given {@code foo}.</li>
   *   <li>When {@link HashMap#HashMap()} {@code foo} is
   * {@link ArrayList#ArrayList()}.</li>
   * </ul>
   * <p>
   * Method under test: {@link RequestBuilderBase#setQueryParams(Map)}
   */
  @Test
  @DisplayName("Test setQueryParams(Map) with 'map'; given 'foo'; when HashMap() 'foo' is ArrayList()")
  void testSetQueryParamsWithMap_givenFoo_whenHashMapFooIsArrayList() {
    // Arrange
    RequestBuilder requestBuilder = new RequestBuilder();

    HashMap<String, List<String>> map = new HashMap<>();
    map.put("foo", new ArrayList<>());

    // Act
    RequestBuilder actualSetQueryParamsResult = requestBuilder.setQueryParams(map);

    // Assert
    assertTrue(requestBuilder.queryParams.isEmpty());
    assertSame(requestBuilder, actualSetQueryParamsResult);
  }

  /**
   * Test {@link RequestBuilderBase#setQueryParams(Map)} with {@code map}.
   * <ul>
   *   <li>Then {@link RequestBuilder#RequestBuilder()}
   * {@link RequestBuilderBase#queryParams} is {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link RequestBuilderBase#setQueryParams(Map)}
   */
  @Test
  @DisplayName("Test setQueryParams(Map) with 'map'; then RequestBuilder() queryParams is 'null'")
  void testSetQueryParamsWithMap_thenRequestBuilderQueryParamsIsNull() {
    // Arrange
    RequestBuilder requestBuilder = new RequestBuilder();
    requestBuilder.setUri(null);

    // Act and Assert
    assertNull(requestBuilder.queryParams);
    assertSame(requestBuilder, requestBuilder.setQueryParams((Map<String, List<String>>) null));
  }

  /**
   * Test {@link RequestBuilderBase#setQueryParams(Map)} with {@code map}.
   * <ul>
   *   <li>Then {@link RequestBuilder#RequestBuilder()}
   * {@link RequestBuilderBase#uri} toJavaNetURI toString is a string.</li>
   * </ul>
   * <p>
   * Method under test: {@link RequestBuilderBase#setQueryParams(Map)}
   */
  @Test
  @DisplayName("Test setQueryParams(Map) with 'map'; then RequestBuilder() uri toJavaNetURI toString is a string")
  void testSetQueryParamsWithMap_thenRequestBuilderUriToJavaNetURIToStringIsAString() throws URISyntaxException {
    // Arrange
    RequestBuilder requestBuilder = new RequestBuilder();
    requestBuilder
        .setUri(new Uri("https://example.org/example", "https://example.org/example", "https://example.org/example",
            8080, "https://example.org/example", "https://example.org/example", "https://example.org/example"));

    // Act and Assert
    Uri uri = requestBuilder.setQueryParams((Map<String, List<String>>) null).uri;
    assertEquals("https://example.org/example", uri.getScheme());
    assertEquals("https://example.org/example://https://example.org/example:8080", uri.getBaseUrl());
    assertEquals("https://example.org/example://https://example.org/example@https://example.org/example:8080https:/"
        + "/example.org/example", requestBuilder.uri.toJavaNetURI().toString());
    assertEquals("https://example.org/example://https://example.org/example@https://example.org/example:8080https:/"
        + "/example.org/example", uri.toJavaNetURI().toString());
  }

  /**
   * Test {@link RequestBuilderBase#setQueryParams(Map)} with {@code map}.
   * <ul>
   *   <li>Then return {@link RequestBuilderBase#uri} NonEmptyPath is
   * {@code /example}.</li>
   * </ul>
   * <p>
   * Method under test: {@link RequestBuilderBase#setQueryParams(Map)}
   */
  @Test
  @DisplayName("Test setQueryParams(Map) with 'map'; then return uri NonEmptyPath is '/example'")
  void testSetQueryParamsWithMap_thenReturnUriNonEmptyPathIsExample() throws URISyntaxException {
    // Arrange
    RequestBuilder requestBuilder = new RequestBuilder();
    requestBuilder.setUri(Uri.create(
        new Uri("https://example.org/example", "https://example.org/example", "https://example.org/example", 8080,
            "https://example.org/example", "https://example.org/example", "https://example.org/example"),
        "https://example.org/example"));

    // Act and Assert
    Uri uri = requestBuilder.setQueryParams((Map<String, List<String>>) null).uri;
    assertEquals("/example", uri.getNonEmptyPath());
    assertEquals("/example", uri.getPath());
    assertEquals("example.org", uri.getHost());
    assertEquals("example.org:443", uri.getAuthority());
    assertEquals("https://example.org/example", uri.toJavaNetURI().toString());
    assertEquals("https://example.org:443", uri.getBaseUrl());
    assertNull(uri.getFragment());
    assertNull(uri.getUserInfo());
    assertEquals(-1, uri.getPort());
    assertEquals(443, uri.getExplicitPort());
  }

  /**
   * Test {@link RequestBuilderBase#setQueryParams(Map)} with {@code map}.
   * <ul>
   *   <li>Then return {@link RequestBuilderBase#uri} Query is empty string.</li>
   * </ul>
   * <p>
   * Method under test: {@link RequestBuilderBase#setQueryParams(Map)}
   */
  @Test
  @DisplayName("Test setQueryParams(Map) with 'map'; then return uri Query is empty string")
  void testSetQueryParamsWithMap_thenReturnUriQueryIsEmptyString() throws URISyntaxException {
    // Arrange
    RequestBuilder requestBuilder = new RequestBuilder();
    requestBuilder.setUri(new Uri("https://example.org/example", "https://example.org/example",
        "https://example.org/example", 8080, "https://example.org/example", "", "https://example.org/example"));

    // Act and Assert
    Uri uri = requestBuilder.setQueryParams((Map<String, List<String>>) null).uri;
    assertEquals("", uri.getQuery());
    assertEquals("https://example.org/example", uri.getScheme());
    assertEquals("https://example.org/example://https://example.org/example:8080", uri.getBaseUrl());
    assertEquals("https://example.org/example://https://example.org/example@https://example.org/example:8080https:/"
        + "/example.org/example?", uri.toJavaNetURI().toString());
  }

  /**
   * Test {@link RequestBuilderBase#setQueryParams(Map)} with {@code map}.
   * <ul>
   *   <li>Then return {@link RequestBuilderBase#uri} Scheme is {@code https}.</li>
   * </ul>
   * <p>
   * Method under test: {@link RequestBuilderBase#setQueryParams(Map)}
   */
  @Test
  @DisplayName("Test setQueryParams(Map) with 'map'; then return uri Scheme is 'https'")
  void testSetQueryParamsWithMap_thenReturnUriSchemeIsHttps() throws URISyntaxException {
    // Arrange
    RequestBuilder requestBuilder = new RequestBuilder();
    requestBuilder.setUri(new Uri(Uri.HTTPS, "https://example.org/example", "https://example.org/example", 8080,
        "https://example.org/example", "https://example.org/example", "https://example.org/example"));

    // Act and Assert
    Uri uri = requestBuilder.setQueryParams((Map<String, List<String>>) null).uri;
    assertEquals("https", uri.getScheme());
    assertEquals("https://https://example.org/example:8080", uri.getBaseUrl());
    assertEquals("https://https://example.org/example@https://example.org/example:8080https://example.org/example",
        requestBuilder.uri.toJavaNetURI().toString());
    assertEquals("https://https://example.org/example@https://example.org/example:8080https://example.org/example",
        uri.toJavaNetURI().toString());
  }

  /**
   * Test {@link RequestBuilderBase#setQueryParams(Map)} with {@code map}.
   * <ul>
   *   <li>Then return {@link RequestBuilderBase#uri} Scheme is {@code ws}.</li>
   * </ul>
   * <p>
   * Method under test: {@link RequestBuilderBase#setQueryParams(Map)}
   */
  @Test
  @DisplayName("Test setQueryParams(Map) with 'map'; then return uri Scheme is 'ws'")
  void testSetQueryParamsWithMap_thenReturnUriSchemeIsWs() throws URISyntaxException {
    // Arrange
    RequestBuilder requestBuilder = new RequestBuilder();
    requestBuilder.setUri(new Uri(Uri.WS, "https://example.org/example", "https://example.org/example", 8080,
        "https://example.org/example", "https://example.org/example", "https://example.org/example"));

    // Act and Assert
    Uri uri = requestBuilder.setQueryParams((Map<String, List<String>>) null).uri;
    assertEquals("ws", uri.getScheme());
    assertEquals("ws://https://example.org/example:8080", uri.getBaseUrl());
    assertEquals("ws://https://example.org/example@https://example.org/example:8080https://example.org/example",
        requestBuilder.uri.toJavaNetURI().toString());
    assertEquals("ws://https://example.org/example@https://example.org/example:8080https://example.org/example",
        uri.toJavaNetURI().toString());
  }

  /**
   * Test {@link RequestBuilderBase#setQueryParams(Map)} with {@code map}.
   * <ul>
   *   <li>Then return {@link RequestBuilderBase#uri} Scheme is {@code wss}.</li>
   * </ul>
   * <p>
   * Method under test: {@link RequestBuilderBase#setQueryParams(Map)}
   */
  @Test
  @DisplayName("Test setQueryParams(Map) with 'map'; then return uri Scheme is 'wss'")
  void testSetQueryParamsWithMap_thenReturnUriSchemeIsWss() throws URISyntaxException {
    // Arrange
    RequestBuilder requestBuilder = new RequestBuilder();
    requestBuilder.setUri(new Uri(Uri.WSS, "https://example.org/example", "https://example.org/example", 8080,
        "https://example.org/example", "https://example.org/example", "https://example.org/example"));

    // Act and Assert
    Uri uri = requestBuilder.setQueryParams((Map<String, List<String>>) null).uri;
    assertEquals("wss", uri.getScheme());
    assertEquals("wss://https://example.org/example:8080", uri.getBaseUrl());
    assertEquals("wss://https://example.org/example@https://example.org/example:8080https://example.org/example",
        requestBuilder.uri.toJavaNetURI().toString());
    assertEquals("wss://https://example.org/example@https://example.org/example:8080https://example.org/example",
        uri.toJavaNetURI().toString());
  }

  /**
   * Test {@link RequestBuilderBase#setQueryParams(Map)} with {@code map}.
   * <ul>
   *   <li>When {@link HashMap#HashMap()}.</li>
   *   <li>Then {@link RequestBuilder#RequestBuilder()}
   * {@link RequestBuilderBase#queryParams} Empty.</li>
   * </ul>
   * <p>
   * Method under test: {@link RequestBuilderBase#setQueryParams(Map)}
   */
  @Test
  @DisplayName("Test setQueryParams(Map) with 'map'; when HashMap(); then RequestBuilder() queryParams Empty")
  void testSetQueryParamsWithMap_whenHashMap_thenRequestBuilderQueryParamsEmpty() {
    // Arrange
    RequestBuilder requestBuilder = new RequestBuilder();

    // Act
    RequestBuilder actualSetQueryParamsResult = requestBuilder.setQueryParams(new HashMap<>());

    // Assert
    assertTrue(requestBuilder.queryParams.isEmpty());
    assertSame(requestBuilder, actualSetQueryParamsResult);
  }

  /**
   * Test {@link RequestBuilderBase#setQueryParams(List)} with {@code params}.
   * <ul>
   *   <li>Then {@link RequestBuilder#RequestBuilder()}
   * {@link RequestBuilderBase#queryParams} Empty.</li>
   * </ul>
   * <p>
   * Method under test: {@link RequestBuilderBase#setQueryParams(List)}
   */
  @Test
  @DisplayName("Test setQueryParams(List) with 'params'; then RequestBuilder() queryParams Empty")
  void testSetQueryParamsWithParams_thenRequestBuilderQueryParamsEmpty() {
    // Arrange
    RequestBuilder requestBuilder = new RequestBuilder();

    // Act
    RequestBuilder actualSetQueryParamsResult = requestBuilder.setQueryParams(new ArrayList<>());

    // Assert
    assertTrue(requestBuilder.queryParams.isEmpty());
    assertSame(requestBuilder, actualSetQueryParamsResult);
  }

  /**
   * Test {@link RequestBuilderBase#setQueryParams(List)} with {@code params}.
   * <ul>
   *   <li>Then {@link RequestBuilder#RequestBuilder()}
   * {@link RequestBuilderBase#uri} toJavaNetURI toString is a string.</li>
   * </ul>
   * <p>
   * Method under test: {@link RequestBuilderBase#setQueryParams(List)}
   */
  @Test
  @DisplayName("Test setQueryParams(List) with 'params'; then RequestBuilder() uri toJavaNetURI toString is a string")
  void testSetQueryParamsWithParams_thenRequestBuilderUriToJavaNetURIToStringIsAString() throws URISyntaxException {
    // Arrange
    RequestBuilder requestBuilder = new RequestBuilder();
    requestBuilder
        .setUri(new Uri("https://example.org/example", "https://example.org/example", "https://example.org/example",
            8080, "https://example.org/example", "https://example.org/example", "https://example.org/example"));

    // Act and Assert
    Uri uri = requestBuilder.setQueryParams(new ArrayList<>()).uri;
    assertEquals("https://example.org/example", uri.getScheme());
    assertEquals("https://example.org/example://https://example.org/example:8080", uri.getBaseUrl());
    assertEquals("https://example.org/example://https://example.org/example@https://example.org/example:8080https:/"
        + "/example.org/example", requestBuilder.uri.toJavaNetURI().toString());
    assertEquals("https://example.org/example://https://example.org/example@https://example.org/example:8080https:/"
        + "/example.org/example", uri.toJavaNetURI().toString());
  }

  /**
   * Test {@link RequestBuilderBase#setQueryParams(List)} with {@code params}.
   * <ul>
   *   <li>Then return {@link RequestBuilderBase#uri} Query is empty string.</li>
   * </ul>
   * <p>
   * Method under test: {@link RequestBuilderBase#setQueryParams(List)}
   */
  @Test
  @DisplayName("Test setQueryParams(List) with 'params'; then return uri Query is empty string")
  void testSetQueryParamsWithParams_thenReturnUriQueryIsEmptyString() throws URISyntaxException {
    // Arrange
    RequestBuilder requestBuilder = new RequestBuilder();
    requestBuilder.setUri(new Uri("https://example.org/example", "https://example.org/example",
        "https://example.org/example", 8080, "https://example.org/example", "", "https://example.org/example"));

    // Act and Assert
    Uri uri = requestBuilder.setQueryParams(new ArrayList<>()).uri;
    assertEquals("", uri.getQuery());
    assertEquals("https://example.org/example", uri.getScheme());
    assertEquals("https://example.org/example://https://example.org/example:8080", uri.getBaseUrl());
    assertEquals("https://example.org/example://https://example.org/example@https://example.org/example:8080https:/"
        + "/example.org/example?", uri.toJavaNetURI().toString());
  }

  /**
   * Test {@link RequestBuilderBase#setQueryParams(List)} with {@code params}.
   * <ul>
   *   <li>Then return {@link RequestBuilderBase#uri} SchemeDefaultPort is
   * eighty.</li>
   * </ul>
   * <p>
   * Method under test: {@link RequestBuilderBase#setQueryParams(List)}
   */
  @Test
  @DisplayName("Test setQueryParams(List) with 'params'; then return uri SchemeDefaultPort is eighty")
  void testSetQueryParamsWithParams_thenReturnUriSchemeDefaultPortIsEighty() throws URISyntaxException {
    // Arrange
    RequestBuilder requestBuilder = new RequestBuilder();
    requestBuilder.setUri(new Uri("https://example.org/example", "https://example.org/example",
        "https://example.org/example", 8080, "https://example.org/example", null, "https://example.org/example"));

    // Act and Assert
    Uri uri = requestBuilder.setQueryParams(new ArrayList<>()).uri;
    assertEquals("https://example.org/example://https://example.org/example@https://example.org/example:8080https:/"
        + "/example.org/example", uri.toJavaNetURI().toString());
    assertEquals(80, uri.getSchemeDefaultPort());
    assertFalse(uri.isSecured());
    assertFalse(uri.isWebSocket());
  }

  /**
   * Test {@link RequestBuilderBase#setQueryParams(List)} with {@code params}.
   * <ul>
   *   <li>Then return {@link RequestBuilderBase#uri} Scheme is {@code https}.</li>
   * </ul>
   * <p>
   * Method under test: {@link RequestBuilderBase#setQueryParams(List)}
   */
  @Test
  @DisplayName("Test setQueryParams(List) with 'params'; then return uri Scheme is 'https'")
  void testSetQueryParamsWithParams_thenReturnUriSchemeIsHttps() throws URISyntaxException {
    // Arrange
    RequestBuilder requestBuilder = new RequestBuilder();
    requestBuilder.setUri(new Uri(Uri.HTTPS, "https://example.org/example", "https://example.org/example", 8080,
        "https://example.org/example", "https://example.org/example", "https://example.org/example"));

    // Act and Assert
    Uri uri = requestBuilder.setQueryParams(new ArrayList<>()).uri;
    assertEquals("https", uri.getScheme());
    assertEquals("https://https://example.org/example:8080", uri.getBaseUrl());
    assertEquals("https://https://example.org/example@https://example.org/example:8080https://example.org/example",
        requestBuilder.uri.toJavaNetURI().toString());
    assertEquals("https://https://example.org/example@https://example.org/example:8080https://example.org/example",
        uri.toJavaNetURI().toString());
  }

  /**
   * Test {@link RequestBuilderBase#setQueryParams(List)} with {@code params}.
   * <ul>
   *   <li>Then return {@link RequestBuilderBase#uri} Scheme is {@code ws}.</li>
   * </ul>
   * <p>
   * Method under test: {@link RequestBuilderBase#setQueryParams(List)}
   */
  @Test
  @DisplayName("Test setQueryParams(List) with 'params'; then return uri Scheme is 'ws'")
  void testSetQueryParamsWithParams_thenReturnUriSchemeIsWs() throws URISyntaxException {
    // Arrange
    RequestBuilder requestBuilder = new RequestBuilder();
    requestBuilder.setUri(new Uri(Uri.WS, "https://example.org/example", "https://example.org/example", 8080,
        "https://example.org/example", "https://example.org/example", "https://example.org/example"));

    // Act and Assert
    Uri uri = requestBuilder.setQueryParams(new ArrayList<>()).uri;
    assertEquals("ws", uri.getScheme());
    assertEquals("ws://https://example.org/example:8080", uri.getBaseUrl());
    assertEquals("ws://https://example.org/example@https://example.org/example:8080https://example.org/example",
        requestBuilder.uri.toJavaNetURI().toString());
    assertEquals("ws://https://example.org/example@https://example.org/example:8080https://example.org/example",
        uri.toJavaNetURI().toString());
  }

  /**
   * Test {@link RequestBuilderBase#setQueryParams(List)} with {@code params}.
   * <ul>
   *   <li>Then return {@link RequestBuilderBase#uri} Scheme is {@code wss}.</li>
   * </ul>
   * <p>
   * Method under test: {@link RequestBuilderBase#setQueryParams(List)}
   */
  @Test
  @DisplayName("Test setQueryParams(List) with 'params'; then return uri Scheme is 'wss'")
  void testSetQueryParamsWithParams_thenReturnUriSchemeIsWss() throws URISyntaxException {
    // Arrange
    RequestBuilder requestBuilder = new RequestBuilder();
    requestBuilder.setUri(new Uri(Uri.WSS, "https://example.org/example", "https://example.org/example", 8080,
        "https://example.org/example", "https://example.org/example", "https://example.org/example"));

    // Act and Assert
    Uri uri = requestBuilder.setQueryParams(new ArrayList<>()).uri;
    assertEquals("wss", uri.getScheme());
    assertEquals("wss://https://example.org/example:8080", uri.getBaseUrl());
    assertEquals("wss://https://example.org/example@https://example.org/example:8080https://example.org/example",
        requestBuilder.uri.toJavaNetURI().toString());
    assertEquals("wss://https://example.org/example@https://example.org/example:8080https://example.org/example",
        uri.toJavaNetURI().toString());
  }

  /**
   * Test {@link RequestBuilderBase#setFormParams(Map)} with {@code map}.
   * <ul>
   *   <li>Given {@link ArrayList#ArrayList()} add {@code foo}.</li>
   *   <li>Then return {@link RequestBuilderBase#formParams} size is one.</li>
   * </ul>
   * <p>
   * Method under test: {@link RequestBuilderBase#setFormParams(Map)}
   */
  @Test
  @DisplayName("Test setFormParams(Map) with 'map'; given ArrayList() add 'foo'; then return formParams size is one")
  void testSetFormParamsWithMap_givenArrayListAddFoo_thenReturnFormParamsSizeIsOne() {
    // Arrange
    RequestBuilder requestBuilder = new RequestBuilder();

    ArrayList<String> stringList = new ArrayList<>();
    stringList.add("foo");

    HashMap<String, List<String>> map = new HashMap<>();
    map.put("foo", stringList);

    // Act and Assert
    List<Param> paramList = requestBuilder.setFormParams(map).formParams;
    assertEquals(1, paramList.size());
    Param getResult = paramList.get(0);
    assertEquals("foo", getResult.getName());
    assertEquals("foo", getResult.getValue());
    assertEquals(1, requestBuilder.formParams.size());
  }

  /**
   * Test {@link RequestBuilderBase#setFormParams(Map)} with {@code map}.
   * <ul>
   *   <li>Given {@link ArrayList#ArrayList()}.</li>
   *   <li>Then {@link RequestBuilder#RequestBuilder()}
   * {@link RequestBuilderBase#formParams} Empty.</li>
   * </ul>
   * <p>
   * Method under test: {@link RequestBuilderBase#setFormParams(Map)}
   */
  @Test
  @DisplayName("Test setFormParams(Map) with 'map'; given ArrayList(); then RequestBuilder() formParams Empty")
  void testSetFormParamsWithMap_givenArrayList_thenRequestBuilderFormParamsEmpty() {
    // Arrange
    RequestBuilder requestBuilder = new RequestBuilder();

    HashMap<String, List<String>> map = new HashMap<>();
    map.put("foo", new ArrayList<>());

    // Act
    RequestBuilder actualSetFormParamsResult = requestBuilder.setFormParams(map);

    // Assert
    assertTrue(requestBuilder.formParams.isEmpty());
    assertSame(requestBuilder, actualSetFormParamsResult);
  }

  /**
   * Test {@link RequestBuilderBase#setFormParams(Map)} with {@code map}.
   * <ul>
   *   <li>When {@link HashMap#HashMap()}.</li>
   *   <li>Then {@link RequestBuilder#RequestBuilder()}
   * {@link RequestBuilderBase#formParams} Empty.</li>
   * </ul>
   * <p>
   * Method under test: {@link RequestBuilderBase#setFormParams(Map)}
   */
  @Test
  @DisplayName("Test setFormParams(Map) with 'map'; when HashMap(); then RequestBuilder() formParams Empty")
  void testSetFormParamsWithMap_whenHashMap_thenRequestBuilderFormParamsEmpty() {
    // Arrange
    RequestBuilder requestBuilder = new RequestBuilder();

    // Act
    RequestBuilder actualSetFormParamsResult = requestBuilder.setFormParams(new HashMap<>());

    // Assert
    assertTrue(requestBuilder.formParams.isEmpty());
    assertSame(requestBuilder, actualSetFormParamsResult);
  }

  /**
   * Test {@link RequestBuilderBase#setFormParams(Map)} with {@code map}.
   * <ul>
   *   <li>When {@code null}.</li>
   *   <li>Then {@link RequestBuilder#RequestBuilder()}
   * {@link RequestBuilderBase#formParams} is {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link RequestBuilderBase#setFormParams(Map)}
   */
  @Test
  @DisplayName("Test setFormParams(Map) with 'map'; when 'null'; then RequestBuilder() formParams is 'null'")
  void testSetFormParamsWithMap_whenNull_thenRequestBuilderFormParamsIsNull() {
    // Arrange
    RequestBuilder requestBuilder = new RequestBuilder();

    // Act and Assert
    assertNull(requestBuilder.formParams);
    assertSame(requestBuilder, requestBuilder.setFormParams((Map<String, List<String>>) null));
  }

  /**
   * Test {@link RequestBuilderBase#setFormParams(List)} with {@code params}.
   * <ul>
   *   <li>When {@link ArrayList#ArrayList()}.</li>
   *   <li>Then {@link RequestBuilder#RequestBuilder()}
   * {@link RequestBuilderBase#formParams} Empty.</li>
   * </ul>
   * <p>
   * Method under test: {@link RequestBuilderBase#setFormParams(List)}
   */
  @Test
  @DisplayName("Test setFormParams(List) with 'params'; when ArrayList(); then RequestBuilder() formParams Empty")
  void testSetFormParamsWithParams_whenArrayList_thenRequestBuilderFormParamsEmpty() {
    // Arrange
    RequestBuilder requestBuilder = new RequestBuilder();

    // Act
    RequestBuilder actualSetFormParamsResult = requestBuilder.setFormParams(new ArrayList<>());

    // Assert
    assertTrue(requestBuilder.formParams.isEmpty());
    assertSame(requestBuilder, actualSetFormParamsResult);
  }

  /**
   * Test {@link RequestBuilderBase#setBodyParts(List)}.
   * <ul>
   *   <li>When {@link ArrayList#ArrayList()}.</li>
   *   <li>Then {@link ArrayList#ArrayList()} Empty.</li>
   * </ul>
   * <p>
   * Method under test: {@link RequestBuilderBase#setBodyParts(List)}
   */
  @Test
  @DisplayName("Test setBodyParts(List); when ArrayList(); then ArrayList() Empty")
  void testSetBodyParts_whenArrayList_thenArrayListEmpty() {
    // Arrange
    RequestBuilder requestBuilder = new RequestBuilder();
    ArrayList<Part> bodyParts = new ArrayList<>();

    // Act
    RequestBuilder actualSetBodyPartsResult = requestBuilder.setBodyParts(bodyParts);

    // Assert
    assertTrue(bodyParts.isEmpty());
    assertTrue(requestBuilder.bodyParts.isEmpty());
    assertSame(requestBuilder, actualSetBodyPartsResult);
  }

  /**
   * Test {@link RequestBuilderBase#setProxyServer(ProxyServer)} with
   * {@code proxyServer}.
   * <p>
   * Method under test: {@link RequestBuilderBase#setProxyServer(ProxyServer)}
   */
  @Test
  @DisplayName("Test setProxyServer(ProxyServer) with 'proxyServer'")
  void testSetProxyServerWithProxyServer() {
    // Arrange
    RequestBuilder requestBuilder = new RequestBuilder();
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
    ArrayList<String> nonProxyHosts = new ArrayList<>();

    // Act
    RequestBuilder actualSetProxyServerResult = requestBuilder.setProxyServer(
        new ProxyServer("https://example.org/example", 8080, 8080, realm, nonProxyHosts, ProxyType.HTTP));

    // Assert
    ProxyServer proxyServer = requestBuilder.proxyServer;
    assertEquals("https://example.org/example", proxyServer.getHost());
    assertNull(proxyServer.getCustomHeaders());
    assertEquals(8080, proxyServer.getPort());
    assertEquals(8080, proxyServer.getSecuredPort());
    assertEquals(ProxyType.HTTP, proxyServer.getProxyType());
    assertSame(nonProxyHosts, proxyServer.getNonProxyHosts());
    assertSame(requestBuilder, actualSetProxyServerResult);
  }

  /**
   * Test {@link RequestBuilderBase#setProxyServer(Builder)} with
   * {@code proxyServerBuilder}.
   * <p>
   * Method under test:
   * {@link RequestBuilderBase#setProxyServer(ProxyServer.Builder)}
   */
  @Test
  @DisplayName("Test setProxyServer(Builder) with 'proxyServerBuilder'")
  void testSetProxyServerWithProxyServerBuilder() {
    // Arrange
    RequestBuilder requestBuilder = new RequestBuilder();

    // Act
    RequestBuilder actualSetProxyServerResult = requestBuilder
        .setProxyServer(Dsl.proxyServer("https://example.org/example", 8080));

    // Assert
    ProxyServer proxyServer = requestBuilder.proxyServer;
    assertEquals("https://example.org/example", proxyServer.getHost());
    assertNull(proxyServer.getCustomHeaders());
    assertNull(proxyServer.getRealm());
    assertEquals(8080, proxyServer.getPort());
    assertEquals(8080, proxyServer.getSecuredPort());
    assertEquals(ProxyType.HTTP, proxyServer.getProxyType());
    assertSame(requestBuilder, actualSetProxyServerResult);
  }

  /**
   * Test {@link RequestBuilderBase#setProxyServer(Builder)} with
   * {@code proxyServerBuilder}.
   * <p>
   * Method under test:
   * {@link RequestBuilderBase#setProxyServer(ProxyServer.Builder)}
   */
  @Test
  @DisplayName("Test setProxyServer(Builder) with 'proxyServerBuilder'")
  void testSetProxyServerWithProxyServerBuilder2() {
    // Arrange
    RequestBuilder requestBuilder = new RequestBuilder();
    ProxyServer.Builder proxyServerBuilder = Dsl.proxyServer("https://example.org/example", 8080);
    proxyServerBuilder.setNonProxyHost(null);
    proxyServerBuilder.setProxyType(null);

    // Act
    RequestBuilder actualSetProxyServerResult = requestBuilder.setProxyServer(proxyServerBuilder);

    // Assert
    ProxyServer proxyServer = requestBuilder.proxyServer;
    assertEquals("https://example.org/example", proxyServer.getHost());
    assertNull(proxyServer.getCustomHeaders());
    assertNull(proxyServer.getRealm());
    assertEquals(8080, proxyServer.getPort());
    assertEquals(8080, proxyServer.getSecuredPort());
    assertEquals(ProxyType.HTTP, proxyServer.getProxyType());
    assertSame(requestBuilder, actualSetProxyServerResult);
  }

  /**
   * Test {@link RequestBuilderBase#setProxyServer(Builder)} with
   * {@code proxyServerBuilder}.
   * <ul>
   *   <li>Given {@code HTTP}.</li>
   * </ul>
   * <p>
   * Method under test:
   * {@link RequestBuilderBase#setProxyServer(ProxyServer.Builder)}
   */
  @Test
  @DisplayName("Test setProxyServer(Builder) with 'proxyServerBuilder'; given 'HTTP'")
  void testSetProxyServerWithProxyServerBuilder_givenHttp() {
    // Arrange
    RequestBuilder requestBuilder = new RequestBuilder();
    ProxyServer.Builder proxyServerBuilder = Dsl.proxyServer("https://example.org/example", 8080);
    proxyServerBuilder.setNonProxyHost(null);
    proxyServerBuilder.setProxyType(ProxyType.HTTP);

    // Act
    RequestBuilder actualSetProxyServerResult = requestBuilder.setProxyServer(proxyServerBuilder);

    // Assert
    ProxyServer proxyServer = requestBuilder.proxyServer;
    assertEquals("https://example.org/example", proxyServer.getHost());
    assertNull(proxyServer.getCustomHeaders());
    assertNull(proxyServer.getRealm());
    assertEquals(8080, proxyServer.getPort());
    assertEquals(8080, proxyServer.getSecuredPort());
    assertEquals(ProxyType.HTTP, proxyServer.getProxyType());
    assertSame(requestBuilder, actualSetProxyServerResult);
  }

  /**
   * Test {@link RequestBuilderBase#setRealm(Builder)} with {@code Builder}.
   * <ul>
   *   <li>Given {@code BASIC}.</li>
   *   <li>Then {@link RequestBuilder#RequestBuilder()}
   * {@link RequestBuilderBase#realm} Nc is {@code 00000001}.</li>
   * </ul>
   * <p>
   * Method under test: {@link RequestBuilderBase#setRealm(Realm.Builder)}
   */
  @Test
  @DisplayName("Test setRealm(Builder) with 'Builder'; given 'BASIC'; then RequestBuilder() realm Nc is '00000001'")
  void testSetRealmWithBuilder_givenBasic_thenRequestBuilderRealmNcIs00000001() {
    // Arrange
    RequestBuilder requestBuilder = new RequestBuilder();

    Realm.Builder realm = new Realm.Builder();
    realm.setScheme(Realm.AuthScheme.BASIC);

    // Act
    RequestBuilder actualSetRealmResult = requestBuilder.setRealm(realm);

    // Assert
    Realm realm2 = requestBuilder.realm;
    assertEquals("00000001", realm2.getNc());
    assertEquals("localhost", realm2.getNtlmHost());
    assertNull(realm2.getAlgorithm());
    assertNull(realm2.getCnonce());
    assertNull(realm2.getLoginContextName());
    assertNull(realm2.getNonce());
    assertNull(realm2.getNtlmDomain());
    assertNull(realm2.getOpaque());
    assertNull(realm2.getPassword());
    assertNull(realm2.getPrincipal());
    assertNull(realm2.getQop());
    assertNull(realm2.getRealmName());
    assertNull(realm2.getResponse());
    assertNull(realm2.getServicePrincipalName());
    assertNull(realm2.getCustomLoginConfig());
    assertNull(realm2.getUri());
    assertEquals(Realm.AuthScheme.BASIC, realm2.getScheme());
    assertFalse(realm2.isOmitQuery());
    assertFalse(realm2.isUseAbsoluteURI());
    assertFalse(realm2.isUseCanonicalHostname());
    assertFalse(realm2.isUsePreemptiveAuth());
    assertSame(requestBuilder, actualSetRealmResult);
  }

  /**
   * Test {@link RequestBuilderBase#setRealm(Realm)} with {@code Realm}.
   * <p>
   * Method under test: {@link RequestBuilderBase#setRealm(Realm)}
   */
  @Test
  @DisplayName("Test setRealm(Realm) with 'Realm'")
  void testSetRealmWithRealm() {
    // Arrange
    RequestBuilder requestBuilder = new RequestBuilder();

    // Act and Assert
    assertSame(requestBuilder, requestBuilder.setRealm(mock(Realm.class)));
  }

  /**
   * Test {@link RequestBuilderBase#setRequestTimeout(Duration)}.
   * <p>
   * Method under test: {@link RequestBuilderBase#setRequestTimeout(Duration)}
   */
  @Test
  @DisplayName("Test setRequestTimeout(Duration)")
  void testSetRequestTimeout() {
    // Arrange
    RequestBuilder requestBuilder = new RequestBuilder();

    // Act and Assert
    assertSame(requestBuilder, requestBuilder.setRequestTimeout(null));
  }

  /**
   * Test {@link RequestBuilderBase#setReadTimeout(Duration)}.
   * <p>
   * Method under test: {@link RequestBuilderBase#setReadTimeout(Duration)}
   */
  @Test
  @DisplayName("Test setReadTimeout(Duration)")
  void testSetReadTimeout() {
    // Arrange
    RequestBuilder requestBuilder = new RequestBuilder();

    // Act and Assert
    assertSame(requestBuilder, requestBuilder.setReadTimeout(null));
  }

  /**
   * Test {@link RequestBuilderBase#setCharset(Charset)}.
   * <p>
   * Method under test: {@link RequestBuilderBase#setCharset(Charset)}
   */
  @Test
  @DisplayName("Test setCharset(Charset)")
  void testSetCharset() {
    // Arrange
    RequestBuilder requestBuilder = new RequestBuilder();

    // Act
    RequestBuilder actualSetCharsetResult = requestBuilder.setCharset(Charset.forName("UTF-8"));

    // Assert
    assertEquals("UTF-8", requestBuilder.charset.name());
    assertSame(requestBuilder, actualSetCharsetResult);
  }

  /**
   * Test
   * {@link RequestBuilderBase#setChannelPoolPartitioning(ChannelPoolPartitioning)}.
   * <p>
   * Method under test:
   * {@link RequestBuilderBase#setChannelPoolPartitioning(ChannelPoolPartitioning)}
   */
  @Test
  @DisplayName("Test setChannelPoolPartitioning(ChannelPoolPartitioning)")
  void testSetChannelPoolPartitioning() {
    // Arrange
    RequestBuilder requestBuilder = new RequestBuilder();

    // Act and Assert
    assertSame(requestBuilder, requestBuilder.setChannelPoolPartitioning(mock(ChannelPoolPartitioning.class)));
  }

  /**
   * Test {@link RequestBuilderBase#setNameResolver(NameResolver)}.
   * <p>
   * Method under test: {@link RequestBuilderBase#setNameResolver(NameResolver)}
   */
  @Test
  @DisplayName("Test setNameResolver(NameResolver)")
  void testSetNameResolver() {
    // Arrange
    RequestBuilder requestBuilder = new RequestBuilder();

    // Act and Assert
    assertSame(requestBuilder, requestBuilder.setNameResolver(RequestBuilderBase.DEFAULT_NAME_RESOLVER));
  }

  /**
   * Test {@link RequestBuilderBase#setSignatureCalculator(SignatureCalculator)}.
   * <p>
   * Method under test:
   * {@link RequestBuilderBase#setSignatureCalculator(SignatureCalculator)}
   */
  @Test
  @DisplayName("Test setSignatureCalculator(SignatureCalculator)")
  void testSetSignatureCalculator() {
    // Arrange
    RequestBuilder requestBuilder = new RequestBuilder();

    // Act and Assert
    assertSame(requestBuilder, requestBuilder.setSignatureCalculator(mock(SignatureCalculator.class)));
  }
}
