package org.asynchttpclient;

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
import io.netty.buffer.ByteBuf;
import io.netty.buffer.DuplicatedByteBuf;
import io.netty.buffer.ReadOnlyByteBuf;
import io.netty.handler.codec.http.DefaultCookie;
import io.netty.handler.codec.http.DefaultHttpHeaders;
import io.netty.handler.codec.http.EmptyHttpHeaders;
import io.netty.handler.codec.http.HttpHeaders;
import io.netty.handler.codec.http.cookie.Cookie;
import io.netty.resolver.NameResolver;
import io.netty.util.AsciiString;
import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
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
import java.util.function.BiFunction;
import java.util.function.Function;
import org.asynchttpclient.channel.ChannelPoolPartitioning;
import org.asynchttpclient.config.AsyncHttpClientConfigDefaults;
import org.asynchttpclient.proxy.ProxyServer;
import org.asynchttpclient.proxy.ProxyType;
import org.asynchttpclient.request.body.generator.BodyGenerator;
import org.asynchttpclient.request.body.multipart.ByteArrayPart;
import org.asynchttpclient.request.body.multipart.Part;
import org.asynchttpclient.uri.Uri;
import org.junit.jupiter.api.Test;

class RequestBuilderBaseDiffblueTest {
  /**
   * Method under test: {@link RequestBuilderBase#setUrl(String)}
   */
  @Test
  void testSetUrl() throws URISyntaxException {
    // Arrange
    RequestBuilder requestBuilder = new RequestBuilder();

    // Act
    RequestBuilder actualSetUrlResult = requestBuilder.setUrl("https://example.org/example");

    // Assert
    Uri uri = requestBuilder.uri;
    assertEquals("/example", uri.getNonEmptyPath());
    assertEquals("/example", uri.getPath());
    assertEquals("example.org", uri.getHost());
    assertEquals("example.org:443", uri.getAuthority());
    assertEquals("https", uri.getScheme());
    assertEquals("https://example.org/example", uri.toJavaNetURI().toString());
    assertEquals("https://example.org:443", uri.getBaseUrl());
    assertNull(uri.getFragment());
    assertNull(uri.getQuery());
    assertNull(uri.getUserInfo());
    assertEquals(-1, uri.getPort());
    assertEquals(443, uri.getExplicitPort());
    assertEquals(443, uri.getSchemeDefaultPort());
    assertFalse(uri.isWebSocket());
    assertTrue(uri.isSecured());
    assertSame(requestBuilder, actualSetUrlResult);
  }

  /**
   * Method under test: {@link RequestBuilderBase#setUrl(String)}
   */
  @Test
  void testSetUrl2() throws URISyntaxException {
    // Arrange
    RequestBuilder requestBuilder = new RequestBuilder();

    // Act
    RequestBuilder actualSetUrlResult = requestBuilder.setUrl("http://localhost");

    // Assert
    Uri uri = requestBuilder.uri;
    assertEquals("", uri.getPath());
    assertEquals("/", uri.getNonEmptyPath());
    assertEquals("http", uri.getScheme());
    assertEquals("http://localhost", uri.toJavaNetURI().toString());
    assertEquals("http://localhost:80", uri.getBaseUrl());
    assertEquals("localhost", uri.getHost());
    assertEquals("localhost:80", uri.getAuthority());
    assertNull(uri.getFragment());
    assertNull(uri.getQuery());
    assertNull(uri.getUserInfo());
    assertEquals(-1, uri.getPort());
    assertEquals(80, uri.getExplicitPort());
    assertEquals(80, uri.getSchemeDefaultPort());
    assertFalse(uri.isSecured());
    assertFalse(uri.isWebSocket());
    assertSame(requestBuilder, actualSetUrlResult);
  }

  /**
   * Method under test: {@link RequestBuilderBase#setUrl(String)}
   */
  @Test
  void testSetUrl3() throws URISyntaxException {
    // Arrange
    RequestBuilder requestBuilder = new RequestBuilder();
    requestBuilder.setAddress(mock(InetAddress.class));

    // Act
    RequestBuilder actualSetUrlResult = requestBuilder.setUrl("https://example.org/example");

    // Assert
    Uri uri = requestBuilder.uri;
    assertEquals("/example", uri.getNonEmptyPath());
    assertEquals("/example", uri.getPath());
    assertEquals("example.org", uri.getHost());
    assertEquals("example.org:443", uri.getAuthority());
    assertEquals("https", uri.getScheme());
    assertEquals("https://example.org/example", uri.toJavaNetURI().toString());
    assertEquals("https://example.org:443", uri.getBaseUrl());
    assertNull(uri.getFragment());
    assertNull(uri.getQuery());
    assertNull(uri.getUserInfo());
    assertEquals(-1, uri.getPort());
    assertEquals(443, uri.getExplicitPort());
    assertEquals(443, uri.getSchemeDefaultPort());
    assertFalse(uri.isWebSocket());
    assertTrue(uri.isSecured());
    assertSame(requestBuilder, actualSetUrlResult);
  }

  /**
   * Method under test: {@link RequestBuilderBase#setUri(Uri)}
   */
  @Test
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
   * Method under test: {@link RequestBuilderBase#setUri(Uri)}
   */
  @Test
  void testSetUri2() throws URISyntaxException {
    // Arrange
    RequestBuilder requestBuilder = new RequestBuilder();
    requestBuilder.setAddress(mock(InetAddress.class));

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
   * Method under test: {@link RequestBuilderBase#setAddress(InetAddress)}
   */
  @Test
  void testSetAddress() {
    // Arrange
    RequestBuilder requestBuilder = new RequestBuilder();

    // Act and Assert
    assertSame(requestBuilder, requestBuilder.setAddress(mock(InetAddress.class)));
  }

  /**
   * Method under test: {@link RequestBuilderBase#setLocalAddress(InetAddress)}
   */
  @Test
  void testSetLocalAddress() {
    // Arrange
    RequestBuilder requestBuilder = new RequestBuilder();

    // Act and Assert
    assertSame(requestBuilder, requestBuilder.setLocalAddress(mock(InetAddress.class)));
  }

  /**
   * Method under test: {@link RequestBuilderBase#clearHeaders()}
   */
  @Test
  void testClearHeaders() {
    // Arrange
    RequestBuilder requestBuilder = new RequestBuilder();

    // Act and Assert
    assertSame(requestBuilder, requestBuilder.clearHeaders());
  }

  /**
   * Method under test: {@link RequestBuilderBase#clearHeaders()}
   */
  @Test
  void testClearHeaders2() {
    // Arrange
    RequestBuilder requestBuilder = new RequestBuilder();
    requestBuilder.setAddress(mock(InetAddress.class));

    // Act and Assert
    assertSame(requestBuilder, requestBuilder.clearHeaders());
  }

  /**
   * Method under test:
   * {@link RequestBuilderBase#setHeader(CharSequence, Iterable)}
   */
  @Test
  void testSetHeader() {
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
   * Method under test:
   * {@link RequestBuilderBase#setHeader(CharSequence, Iterable)}
   */
  @Test
  void testSetHeader2() {
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
   * Method under test:
   * {@link RequestBuilderBase#setHeader(CharSequence, Iterable)}
   */
  @Test
  void testSetHeader3() {
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
   * Method under test:
   * {@link RequestBuilderBase#setHeader(CharSequence, Iterable)}
   */
  @Test
  void testSetHeader4() {
    // Arrange
    RequestBuilder requestBuilder = new RequestBuilder();
    requestBuilder.setAddress(mock(InetAddress.class));
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
   * Method under test: {@link RequestBuilderBase#setHeader(CharSequence, Object)}
   */
  @Test
  void testSetHeader5() {
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
   * Method under test: {@link RequestBuilderBase#setHeader(CharSequence, Object)}
   */
  @Test
  void testSetHeader6() {
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
   * Method under test: {@link RequestBuilderBase#setHeader(CharSequence, Object)}
   */
  @Test
  void testSetHeader7() {
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
   * Method under test: {@link RequestBuilderBase#setHeader(CharSequence, Object)}
   */
  @Test
  void testSetHeader8() {
    // Arrange
    RequestBuilder requestBuilder = new RequestBuilder();
    requestBuilder.setAddress(mock(InetAddress.class));
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
   * Method under test: {@link RequestBuilderBase#setHeader(CharSequence, String)}
   */
  @Test
  void testSetHeader9() {
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
   * Method under test: {@link RequestBuilderBase#setHeader(CharSequence, String)}
   */
  @Test
  void testSetHeader10() {
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
   * Method under test: {@link RequestBuilderBase#setHeader(CharSequence, String)}
   */
  @Test
  void testSetHeader11() {
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
   * Method under test: {@link RequestBuilderBase#setHeader(CharSequence, String)}
   */
  @Test
  void testSetHeader12() {
    // Arrange
    RequestBuilder requestBuilder = new RequestBuilder();
    requestBuilder.setAddress(mock(InetAddress.class));
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
   * Method under test:
   * {@link RequestBuilderBase#addHeader(CharSequence, Iterable)}
   */
  @Test
  void testAddHeader() {
    // Arrange
    RequestBuilder requestBuilder = new RequestBuilder();

    // Act
    RequestBuilder actualAddHeaderResult = requestBuilder
        .addHeader(AsyncHttpClientConfigDefaults.ACQUIRE_FREE_CHANNEL_TIMEOUT, (Iterable<?>) new ArrayList<>());

    // Assert
    HttpHeaders httpHeaders = requestBuilder.headers;
    assertTrue(httpHeaders instanceof DefaultHttpHeaders);
    assertEquals(0, httpHeaders.size());
    assertTrue(httpHeaders.isEmpty());
    assertSame(requestBuilder, actualAddHeaderResult);
  }

  /**
   * Method under test:
   * {@link RequestBuilderBase#addHeader(CharSequence, Iterable)}
   */
  @Test
  void testAddHeader2() {
    // Arrange
    RequestBuilder requestBuilder = new RequestBuilder();

    ArrayList<Object> values = new ArrayList<>();
    values.add("42");

    // Act
    RequestBuilder actualAddHeaderResult = requestBuilder
        .addHeader(AsyncHttpClientConfigDefaults.ACQUIRE_FREE_CHANNEL_TIMEOUT, (Iterable<?>) values);

    // Assert
    HttpHeaders httpHeaders = requestBuilder.headers;
    assertTrue(httpHeaders instanceof DefaultHttpHeaders);
    assertEquals(1, httpHeaders.size());
    assertFalse(httpHeaders.isEmpty());
    assertSame(requestBuilder, actualAddHeaderResult);
  }

  /**
   * Method under test:
   * {@link RequestBuilderBase#addHeader(CharSequence, Iterable)}
   */
  @Test
  void testAddHeader3() {
    // Arrange
    RequestBuilder requestBuilder = new RequestBuilder();
    requestBuilder.setAddress(mock(InetAddress.class));

    // Act
    RequestBuilder actualAddHeaderResult = requestBuilder
        .addHeader(AsyncHttpClientConfigDefaults.ACQUIRE_FREE_CHANNEL_TIMEOUT, (Iterable<?>) new ArrayList<>());

    // Assert
    HttpHeaders httpHeaders = requestBuilder.headers;
    assertTrue(httpHeaders instanceof DefaultHttpHeaders);
    assertEquals(0, httpHeaders.size());
    assertTrue(httpHeaders.isEmpty());
    assertSame(requestBuilder, actualAddHeaderResult);
  }

  /**
   * Method under test:
   * {@link RequestBuilderBase#addHeader(CharSequence, Iterable)}
   */
  @Test
  void testAddHeader4() {
    // Arrange
    RequestBuilder requestBuilder = new RequestBuilder();
    AsciiString name = AsciiString.cached("String");

    ArrayList<Object> values = new ArrayList<>();
    values.add("42");

    // Act
    RequestBuilder actualAddHeaderResult = requestBuilder.addHeader(name, (Iterable<?>) values);

    // Assert
    HttpHeaders httpHeaders = requestBuilder.headers;
    assertTrue(httpHeaders instanceof DefaultHttpHeaders);
    assertEquals(1, httpHeaders.size());
    assertFalse(httpHeaders.isEmpty());
    assertSame(requestBuilder, actualAddHeaderResult);
  }

  /**
   * Method under test:
   * {@link RequestBuilderBase#addHeader(CharSequence, Iterable)}
   */
  @Test
  void testAddHeader5() {
    // Arrange
    RequestBuilder requestBuilder = new RequestBuilder();
    AsciiString name = AsciiString.cached("String");

    ArrayList<Object> values = new ArrayList<>();
    values.add("42");
    values.add("42");

    // Act
    RequestBuilder actualAddHeaderResult = requestBuilder.addHeader(name, (Iterable<?>) values);

    // Assert
    HttpHeaders httpHeaders = requestBuilder.headers;
    assertTrue(httpHeaders instanceof DefaultHttpHeaders);
    assertEquals(2, httpHeaders.size());
    assertFalse(httpHeaders.isEmpty());
    assertSame(requestBuilder, actualAddHeaderResult);
  }

  /**
   * Method under test: {@link RequestBuilderBase#addHeader(CharSequence, Object)}
   */
  @Test
  void testAddHeader6() {
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
   * Method under test: {@link RequestBuilderBase#addHeader(CharSequence, Object)}
   */
  @Test
  void testAddHeader7() {
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
   * Method under test: {@link RequestBuilderBase#addHeader(CharSequence, Object)}
   */
  @Test
  void testAddHeader8() {
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
   * Method under test: {@link RequestBuilderBase#addHeader(CharSequence, Object)}
   */
  @Test
  void testAddHeader9() {
    // Arrange
    RequestBuilder requestBuilder = new RequestBuilder();
    requestBuilder.setAddress(mock(InetAddress.class));

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
   * Method under test: {@link RequestBuilderBase#addHeader(CharSequence, String)}
   */
  @Test
  void testAddHeader10() {
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
   * Method under test: {@link RequestBuilderBase#addHeader(CharSequence, String)}
   */
  @Test
  void testAddHeader11() {
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
   * Method under test: {@link RequestBuilderBase#addHeader(CharSequence, String)}
   */
  @Test
  void testAddHeader12() {
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
   * Method under test: {@link RequestBuilderBase#addHeader(CharSequence, String)}
   */
  @Test
  void testAddHeader13() {
    // Arrange
    RequestBuilder requestBuilder = new RequestBuilder();
    requestBuilder.setAddress(mock(InetAddress.class));

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
   * Method under test: {@link RequestBuilderBase#setHeaders(HttpHeaders)}
   */
  @Test
  void testSetHeaders() {
    // Arrange
    RequestBuilder requestBuilder = new RequestBuilder();

    // Act and Assert
    assertSame(requestBuilder, requestBuilder.setHeaders(new DefaultHttpHeaders()));
  }

  /**
   * Method under test: {@link RequestBuilderBase#setHeaders(HttpHeaders)}
   */
  @Test
  void testSetHeaders2() {
    // Arrange
    RequestBuilder requestBuilder = new RequestBuilder();

    // Act and Assert
    assertSame(requestBuilder, requestBuilder.setHeaders((HttpHeaders) null));
  }

  /**
   * Method under test: {@link RequestBuilderBase#setHeaders(HttpHeaders)}
   */
  @Test
  void testSetHeaders3() {
    // Arrange
    RequestBuilder requestBuilder = new RequestBuilder();

    // Act and Assert
    assertSame(requestBuilder, requestBuilder.setHeaders(mock(EmptyHttpHeaders.class)));
  }

  /**
   * Method under test: {@link RequestBuilderBase#setHeaders(Map)}
   */
  @Test
  void testSetHeaders4() {
    // Arrange
    RequestBuilder requestBuilder = new RequestBuilder();

    // Act
    RequestBuilder actualSetHeadersResult = requestBuilder.setHeaders(new HashMap<>());

    // Assert
    HttpHeaders httpHeaders = requestBuilder.headers;
    assertTrue(httpHeaders instanceof DefaultHttpHeaders);
    assertEquals(0, httpHeaders.size());
    assertTrue(httpHeaders.isEmpty());
    assertSame(requestBuilder, actualSetHeadersResult);
  }

  /**
   * Method under test: {@link RequestBuilderBase#setHeaders(Map)}
   */
  @Test
  void testSetHeaders5() {
    // Arrange
    RequestBuilder requestBuilder = new RequestBuilder();

    // Act
    RequestBuilder actualSetHeadersResult = requestBuilder
        .setHeaders((Map<? extends CharSequence, ? extends Iterable<?>>) null);

    // Assert
    HttpHeaders httpHeaders = requestBuilder.headers;
    assertTrue(httpHeaders instanceof DefaultHttpHeaders);
    assertEquals(0, httpHeaders.size());
    assertTrue(httpHeaders.isEmpty());
    assertSame(requestBuilder, actualSetHeadersResult);
  }

  /**
   * Method under test: {@link RequestBuilderBase#setHeaders(Map)}
   */
  @Test
  void testSetHeaders6() {
    // Arrange
    RequestBuilder requestBuilder = new RequestBuilder();

    HashMap<CharSequence, Iterable<?>> headers = new HashMap<>();
    headers.put(AsyncHttpClientConfigDefaults.ACQUIRE_FREE_CHANNEL_TIMEOUT, new ArrayList<>());

    // Act
    RequestBuilder actualSetHeadersResult = requestBuilder.setHeaders(headers);

    // Assert
    HttpHeaders httpHeaders = requestBuilder.headers;
    assertTrue(httpHeaders instanceof DefaultHttpHeaders);
    assertEquals(0, httpHeaders.size());
    assertTrue(httpHeaders.isEmpty());
    assertSame(requestBuilder, actualSetHeadersResult);
  }

  /**
   * Method under test: {@link RequestBuilderBase#setHeaders(Map)}
   */
  @Test
  void testSetHeaders7() {
    // Arrange
    RequestBuilder requestBuilder = new RequestBuilder();

    HashMap<CharSequence, Iterable<?>> headers = new HashMap<>();
    headers.computeIfPresent(AsyncHttpClientConfigDefaults.ACQUIRE_FREE_CHANNEL_TIMEOUT, mock(BiFunction.class));
    headers.put(AsyncHttpClientConfigDefaults.ACQUIRE_FREE_CHANNEL_TIMEOUT, new ArrayList<>());

    // Act
    RequestBuilder actualSetHeadersResult = requestBuilder.setHeaders(headers);

    // Assert
    HttpHeaders httpHeaders = requestBuilder.headers;
    assertTrue(httpHeaders instanceof DefaultHttpHeaders);
    assertEquals(0, httpHeaders.size());
    assertTrue(httpHeaders.isEmpty());
    assertSame(requestBuilder, actualSetHeadersResult);
  }

  /**
   * Method under test: {@link RequestBuilderBase#setHeaders(Map)}
   */
  @Test
  void testSetHeaders8() {
    // Arrange
    RequestBuilder requestBuilder = new RequestBuilder();

    ArrayList<Object> objectList = new ArrayList<>();
    objectList.add("42");

    HashMap<CharSequence, Iterable<?>> headers = new HashMap<>();
    headers.put(AsyncHttpClientConfigDefaults.ACQUIRE_FREE_CHANNEL_TIMEOUT, objectList);

    // Act
    RequestBuilder actualSetHeadersResult = requestBuilder.setHeaders(headers);

    // Assert
    HttpHeaders httpHeaders = requestBuilder.headers;
    assertTrue(httpHeaders instanceof DefaultHttpHeaders);
    assertEquals(1, httpHeaders.size());
    assertFalse(httpHeaders.isEmpty());
    assertSame(requestBuilder, actualSetHeadersResult);
  }

  /**
   * Method under test: {@link RequestBuilderBase#setSingleHeaders(Map)}
   */
  @Test
  void testSetSingleHeaders() {
    // Arrange
    RequestBuilder requestBuilder = new RequestBuilder();

    // Act
    RequestBuilder actualSetSingleHeadersResult = requestBuilder.setSingleHeaders(new HashMap<>());

    // Assert
    HttpHeaders httpHeaders = requestBuilder.headers;
    assertTrue(httpHeaders instanceof DefaultHttpHeaders);
    assertEquals(0, httpHeaders.size());
    assertTrue(httpHeaders.isEmpty());
    assertSame(requestBuilder, actualSetSingleHeadersResult);
  }

  /**
   * Method under test: {@link RequestBuilderBase#setSingleHeaders(Map)}
   */
  @Test
  void testSetSingleHeaders2() {
    // Arrange
    RequestBuilder requestBuilder = new RequestBuilder();

    // Act
    RequestBuilder actualSetSingleHeadersResult = requestBuilder.setSingleHeaders(null);

    // Assert
    HttpHeaders httpHeaders = requestBuilder.headers;
    assertTrue(httpHeaders instanceof DefaultHttpHeaders);
    assertEquals(0, httpHeaders.size());
    assertTrue(httpHeaders.isEmpty());
    assertSame(requestBuilder, actualSetSingleHeadersResult);
  }

  /**
   * Method under test: {@link RequestBuilderBase#setSingleHeaders(Map)}
   */
  @Test
  void testSetSingleHeaders3() {
    // Arrange
    RequestBuilder requestBuilder = new RequestBuilder();

    HashMap<CharSequence, Object> headers = new HashMap<>();
    headers.put(AsyncHttpClientConfigDefaults.ACQUIRE_FREE_CHANNEL_TIMEOUT, "42");

    // Act
    RequestBuilder actualSetSingleHeadersResult = requestBuilder.setSingleHeaders(headers);

    // Assert
    HttpHeaders httpHeaders = requestBuilder.headers;
    assertTrue(httpHeaders instanceof DefaultHttpHeaders);
    assertEquals(1, httpHeaders.size());
    assertFalse(httpHeaders.isEmpty());
    assertSame(requestBuilder, actualSetSingleHeadersResult);
  }

  /**
   * Method under test: {@link RequestBuilderBase#setSingleHeaders(Map)}
   */
  @Test
  void testSetSingleHeaders4() {
    // Arrange
    RequestBuilder requestBuilder = new RequestBuilder();

    HashMap<CharSequence, Object> headers = new HashMap<>();
    headers.computeIfPresent(AsyncHttpClientConfigDefaults.ACQUIRE_FREE_CHANNEL_TIMEOUT, mock(BiFunction.class));
    headers.put(AsyncHttpClientConfigDefaults.ACQUIRE_FREE_CHANNEL_TIMEOUT, "42");

    // Act
    RequestBuilder actualSetSingleHeadersResult = requestBuilder.setSingleHeaders(headers);

    // Assert
    HttpHeaders httpHeaders = requestBuilder.headers;
    assertTrue(httpHeaders instanceof DefaultHttpHeaders);
    assertEquals(1, httpHeaders.size());
    assertFalse(httpHeaders.isEmpty());
    assertSame(requestBuilder, actualSetSingleHeadersResult);
  }

  /**
   * Method under test: {@link RequestBuilderBase#setSingleHeaders(Map)}
   */
  @Test
  void testSetSingleHeaders5() {
    // Arrange
    RequestBuilder requestBuilder = new RequestBuilder();

    HashMap<CharSequence, Object> headers = new HashMap<>();
    headers.put(AsciiString.cached("String"), "42");

    // Act
    RequestBuilder actualSetSingleHeadersResult = requestBuilder.setSingleHeaders(headers);

    // Assert
    HttpHeaders httpHeaders = requestBuilder.headers;
    assertTrue(httpHeaders instanceof DefaultHttpHeaders);
    assertEquals(1, httpHeaders.size());
    assertFalse(httpHeaders.isEmpty());
    assertSame(requestBuilder, actualSetSingleHeadersResult);
  }

  /**
   * Method under test: {@link RequestBuilderBase#setCookies(Collection)}
   */
  @Test
  void testSetCookies() {
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
   * Method under test: {@link RequestBuilderBase#setCookies(Collection)}
   */
  @Test
  void testSetCookies2() {
    // Arrange
    RequestBuilder requestBuilder = new RequestBuilder();

    ArrayList<Cookie> cookies = new ArrayList<>();
    DefaultCookie defaultCookie = new DefaultCookie("https://example.org/example", "https://example.org/example");

    cookies.add(defaultCookie);

    // Act
    RequestBuilder actualSetCookiesResult = requestBuilder.setCookies(cookies);

    // Assert
    assertEquals(1, cookies.size());
    ArrayList<Cookie> cookieList = requestBuilder.cookies;
    assertEquals(1, cookieList.size());
    assertSame(defaultCookie, cookieList.get(0));
    assertSame(requestBuilder, actualSetCookiesResult);
  }

  /**
   * Method under test: {@link RequestBuilderBase#setCookies(Collection)}
   */
  @Test
  void testSetCookies3() {
    // Arrange
    RequestBuilder requestBuilder = new RequestBuilder();

    ArrayList<Cookie> cookies = new ArrayList<>();
    DefaultCookie defaultCookie = new DefaultCookie("https://example.org/example", "https://example.org/example");

    cookies.add(defaultCookie);
    DefaultCookie defaultCookie2 = new DefaultCookie("https://example.org/example", "https://example.org/example");

    cookies.add(defaultCookie2);

    // Act
    RequestBuilder actualSetCookiesResult = requestBuilder.setCookies(cookies);

    // Assert
    ArrayList<Cookie> cookieList = requestBuilder.cookies;
    assertEquals(2, cookieList.size());
    assertEquals(actualSetCookiesResult.cookies, cookies);
    assertSame(defaultCookie, cookieList.get(0));
    assertSame(defaultCookie2, cookieList.get(1));
    assertSame(requestBuilder, actualSetCookiesResult);
  }

  /**
   * Method under test: {@link RequestBuilderBase#setCookies(Collection)}
   */
  @Test
  void testSetCookies4() {
    // Arrange
    RequestBuilder requestBuilder = new RequestBuilder();
    requestBuilder.setAddress(mock(InetAddress.class));
    ArrayList<Cookie> cookies = new ArrayList<>();

    // Act
    RequestBuilder actualSetCookiesResult = requestBuilder.setCookies(cookies);

    // Assert
    assertTrue(cookies.isEmpty());
    assertTrue(requestBuilder.cookies.isEmpty());
    assertSame(requestBuilder, actualSetCookiesResult);
  }

  /**
   * Method under test: {@link RequestBuilderBase#addCookie(Cookie)}
   */
  @Test
  void testAddCookie() {
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
   * Method under test: {@link RequestBuilderBase#addCookie(Cookie)}
   */
  @Test
  void testAddCookie2() {
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
   * Method under test: {@link RequestBuilderBase#addCookie(Cookie)}
   */
  @Test
  void testAddCookie3() {
    // Arrange
    RequestBuilder requestBuilder = new RequestBuilder();
    requestBuilder.setAddress(mock(InetAddress.class));
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
   * Method under test: {@link RequestBuilderBase#addOrReplaceCookie(Cookie)}
   */
  @Test
  void testAddOrReplaceCookie() {
    // Arrange
    RequestBuilder requestBuilder = new RequestBuilder();
    DefaultCookie cookie = new DefaultCookie("https://example.org/example", "https://example.org/example");

    // Act
    RequestBuilder actualAddOrReplaceCookieResult = requestBuilder.addOrReplaceCookie(cookie);

    // Assert
    ArrayList<Cookie> cookieList = requestBuilder.cookies;
    assertEquals(1, cookieList.size());
    assertSame(cookie, cookieList.get(0));
    assertSame(requestBuilder, actualAddOrReplaceCookieResult);
  }

  /**
   * Method under test: {@link RequestBuilderBase#addOrReplaceCookie(Cookie)}
   */
  @Test
  void testAddOrReplaceCookie2() {
    // Arrange
    RequestBuilder requestBuilder = new RequestBuilder();
    requestBuilder.addCookie(new DefaultCookie("https://example.org/example", "https://example.org/example"));
    DefaultCookie cookie = new DefaultCookie("https://example.org/example", "https://example.org/example");

    // Act
    RequestBuilder actualAddOrReplaceCookieResult = requestBuilder.addOrReplaceCookie(cookie);

    // Assert
    ArrayList<Cookie> cookieList = requestBuilder.cookies;
    assertEquals(1, cookieList.size());
    assertSame(cookie, cookieList.get(0));
    assertSame(requestBuilder, actualAddOrReplaceCookieResult);
  }

  /**
   * Method under test: {@link RequestBuilderBase#addOrReplaceCookie(Cookie)}
   */
  @Test
  void testAddOrReplaceCookie3() {
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
    assertSame(cookie, cookieList.get(0));
    assertSame(cookie2, cookieList.get(1));
    assertSame(requestBuilder, actualAddOrReplaceCookieResult);
  }

  /**
   * Method under test: {@link RequestBuilderBase#addOrReplaceCookie(Cookie)}
   */
  @Test
  void testAddOrReplaceCookie4() {
    // Arrange
    RequestBuilder requestBuilder = new RequestBuilder();
    requestBuilder.setAddress(mock(InetAddress.class));
    requestBuilder.addCookie(new DefaultCookie("https://example.org/example", "https://example.org/example"));
    DefaultCookie cookie = new DefaultCookie("https://example.org/example", "https://example.org/example");

    // Act
    RequestBuilder actualAddOrReplaceCookieResult = requestBuilder.addOrReplaceCookie(cookie);

    // Assert
    ArrayList<Cookie> cookieList = requestBuilder.cookies;
    assertEquals(1, cookieList.size());
    assertSame(cookie, cookieList.get(0));
    assertSame(requestBuilder, actualAddOrReplaceCookieResult);
  }

  /**
   * Method under test: {@link RequestBuilderBase#resetQuery()}
   */
  @Test
  void testResetQuery() {
    // Arrange
    RequestBuilder requestBuilder = new RequestBuilder();

    // Act
    requestBuilder.resetQuery();

    // Assert
    assertNull(requestBuilder.uri);
  }

  /**
   * Method under test: {@link RequestBuilderBase#resetQuery()}
   */
  @Test
  void testResetQuery2() throws URISyntaxException {
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
   * Method under test: {@link RequestBuilderBase#resetQuery()}
   */
  @Test
  void testResetQuery3() {
    // Arrange
    RequestBuilder requestBuilder = new RequestBuilder();
    requestBuilder.setAddress(mock(InetAddress.class));

    // Act
    requestBuilder.resetQuery();

    // Assert
    assertNull(requestBuilder.uri);
  }

  /**
   * Method under test: {@link RequestBuilderBase#resetQuery()}
   */
  @Test
  void testResetQuery4() throws URISyntaxException {
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
   * Method under test: {@link RequestBuilderBase#resetQuery()}
   */
  @Test
  void testResetQuery5() throws URISyntaxException {
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
   * Method under test: {@link RequestBuilderBase#resetQuery()}
   */
  @Test
  void testResetQuery6() throws URISyntaxException {
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
   * Method under test: {@link RequestBuilderBase#setBody(ByteBuf)}
   */
  @Test
  void testSetBody() {
    // Arrange
    RequestBuilder requestBuilder = new RequestBuilder();
    ByteBuf buffer = mock(ByteBuf.class);
    when(buffer.capacity()).thenReturn(3);
    when(buffer.maxCapacity()).thenReturn(3);
    when(buffer.readerIndex()).thenReturn(1);
    when(buffer.writerIndex()).thenReturn(1);
    ReadOnlyByteBuf buffer2 = new ReadOnlyByteBuf(new DuplicatedByteBuf(buffer));

    // Act
    RequestBuilder actualSetBodyResult = requestBuilder.setBody(new DuplicatedByteBuf(buffer2));

    // Assert
    verify(buffer, atLeast(1)).capacity();
    verify(buffer).maxCapacity();
    verify(buffer).readerIndex();
    verify(buffer).writerIndex();
    ByteBuf byteBuf = requestBuilder.byteBufData;
    assertTrue(byteBuf instanceof DuplicatedByteBuf);
    assertNull(byteBuf.alloc());
    assertEquals(1, byteBuf.readerIndex());
    assertEquals(1, byteBuf.writerIndex());
    assertEquals(3, byteBuf.capacity());
    assertEquals(3, byteBuf.maxCapacity());
    assertFalse(byteBuf.hasArray());
    assertFalse(byteBuf.hasMemoryAddress());
    assertFalse(byteBuf.isContiguous());
    assertFalse(byteBuf.isDirect());
    assertFalse(byteBuf.isReadable());
    assertTrue(byteBuf.isReadOnly());
    assertTrue(byteBuf.isWritable());
    assertSame(buffer2, byteBuf.unwrap());
    assertSame(requestBuilder, actualSetBodyResult);
  }

  /**
   * Method under test: {@link RequestBuilderBase#setBody(File)}
   */
  @Test
  void testSetBody2() {
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
   * Method under test: {@link RequestBuilderBase#setBody(File)}
   */
  @Test
  void testSetBody3() {
    // Arrange
    RequestBuilder requestBuilder = new RequestBuilder();
    requestBuilder.setAddress(mock(InetAddress.class));

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
   * Method under test: {@link RequestBuilderBase#setBody(InputStream)}
   */
  @Test
  void testSetBody4() throws UnsupportedEncodingException {
    // Arrange
    RequestBuilder requestBuilder = new RequestBuilder();

    // Act and Assert
    assertSame(requestBuilder, requestBuilder.setBody(new ByteArrayInputStream("AXAXAXAX".getBytes("UTF-8"))));
  }

  /**
   * Method under test: {@link RequestBuilderBase#setBody(InputStream)}
   */
  @Test
  void testSetBody5() {
    // Arrange
    RequestBuilder requestBuilder = new RequestBuilder();

    // Act and Assert
    assertSame(requestBuilder, requestBuilder.setBody(mock(DataInputStream.class)));
  }

  /**
   * Method under test: {@link RequestBuilderBase#setBody(ByteBuffer)}
   */
  @Test
  void testSetBody6() throws UnsupportedEncodingException {
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
   * Method under test: {@link RequestBuilderBase#setBody(ByteBuffer)}
   */
  @Test
  void testSetBody7() throws UnsupportedEncodingException {
    // Arrange
    RequestBuilder requestBuilder = new RequestBuilder();
    requestBuilder.setAddress(mock(InetAddress.class));

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
   * Method under test: {@link RequestBuilderBase#setBody(List)}
   */
  @Test
  void testSetBody8() {
    // Arrange
    RequestBuilder requestBuilder = new RequestBuilder();

    // Act
    RequestBuilder actualSetBodyResult = requestBuilder.setBody(new ArrayList<>());

    // Assert
    assertTrue(requestBuilder.compositeByteData.isEmpty());
    assertSame(requestBuilder, actualSetBodyResult);
  }

  /**
   * Method under test: {@link RequestBuilderBase#setBody(List)}
   */
  @Test
  void testSetBody9() {
    // Arrange
    RequestBuilder requestBuilder = new RequestBuilder();
    requestBuilder.setAddress(mock(InetAddress.class));

    // Act
    RequestBuilder actualSetBodyResult = requestBuilder.setBody(new ArrayList<>());

    // Assert
    assertTrue(requestBuilder.compositeByteData.isEmpty());
    assertSame(requestBuilder, actualSetBodyResult);
  }

  /**
   * Method under test: {@link RequestBuilderBase#setBody(BodyGenerator)}
   */
  @Test
  void testSetBody10() {
    // Arrange
    RequestBuilder requestBuilder = new RequestBuilder();

    // Act and Assert
    assertSame(requestBuilder, requestBuilder.setBody(mock(BodyGenerator.class)));
  }

  /**
   * Method under test: {@link RequestBuilderBase#setBody(byte[])}
   */
  @Test
  void testSetBody11() throws UnsupportedEncodingException {
    // Arrange
    RequestBuilder requestBuilder = new RequestBuilder();

    // Act and Assert
    assertSame(requestBuilder, requestBuilder.setBody("AXAXAXAX".getBytes("UTF-8")));
    assertArrayEquals("AXAXAXAX".getBytes("UTF-8"), requestBuilder.byteData);
  }

  /**
   * Method under test: {@link RequestBuilderBase#setBody(byte[])}
   */
  @Test
  void testSetBody12() throws UnsupportedEncodingException {
    // Arrange
    RequestBuilder requestBuilder = new RequestBuilder();
    requestBuilder.setAddress(mock(InetAddress.class));

    // Act and Assert
    assertSame(requestBuilder, requestBuilder.setBody("AXAXAXAX".getBytes("UTF-8")));
    assertArrayEquals("AXAXAXAX".getBytes("UTF-8"), requestBuilder.byteData);
  }

  /**
   * Method under test: {@link RequestBuilderBase#addQueryParams(List)}
   */
  @Test
  void testAddQueryParams() {
    // Arrange
    RequestBuilder requestBuilder = new RequestBuilder();

    // Act
    RequestBuilder actualAddQueryParamsResult = requestBuilder.addQueryParams(new ArrayList<>());

    // Assert
    assertTrue(requestBuilder.queryParams.isEmpty());
    assertSame(requestBuilder, actualAddQueryParamsResult);
  }

  /**
   * Method under test: {@link RequestBuilderBase#addQueryParams(List)}
   */
  @Test
  void testAddQueryParams2() {
    // Arrange
    RequestBuilder requestBuilder = new RequestBuilder();
    requestBuilder.addQueryParam("https://example.org/example", "https://example.org/example");

    // Act and Assert
    assertEquals(1, requestBuilder.queryParams.size());
    assertSame(requestBuilder, requestBuilder.addQueryParams(new ArrayList<>()));
  }

  /**
   * Method under test: {@link RequestBuilderBase#addQueryParams(List)}
   */
  @Test
  void testAddQueryParams3() {
    // Arrange
    RequestBuilder requestBuilder = new RequestBuilder();
    requestBuilder.setAddress(mock(InetAddress.class));
    requestBuilder.addQueryParam("https://example.org/example", "https://example.org/example");

    // Act and Assert
    assertEquals(1, requestBuilder.queryParams.size());
    assertSame(requestBuilder, requestBuilder.addQueryParams(new ArrayList<>()));
  }

  /**
   * Method under test: {@link RequestBuilderBase#setQueryParams(List)}
   */
  @Test
  void testSetQueryParams() {
    // Arrange
    RequestBuilder requestBuilder = new RequestBuilder();

    // Act
    RequestBuilder actualSetQueryParamsResult = requestBuilder.setQueryParams(new ArrayList<>());

    // Assert
    assertNull(requestBuilder.uri);
    assertTrue(requestBuilder.queryParams.isEmpty());
    assertSame(requestBuilder, actualSetQueryParamsResult);
  }

  /**
   * Method under test: {@link RequestBuilderBase#setQueryParams(List)}
   */
  @Test
  void testSetQueryParams2() throws URISyntaxException {
    // Arrange
    RequestBuilder requestBuilder = new RequestBuilder();
    requestBuilder
        .setUri(new Uri("https://example.org/example", "https://example.org/example", "https://example.org/example",
            8080, "https://example.org/example", "https://example.org/example", "https://example.org/example"));

    // Act
    RequestBuilder actualSetQueryParamsResult = requestBuilder.setQueryParams(new ArrayList<>());

    // Assert
    Uri uri = requestBuilder.uri;
    assertEquals("https://example.org/example://https://example.org/example@https://example.org/example:8080https:/"
        + "/example.org/example", uri.toJavaNetURI().toString());
    assertNull(uri.getQuery());
    assertTrue(requestBuilder.queryParams.isEmpty());
    assertSame(requestBuilder, actualSetQueryParamsResult);
  }

  /**
   * Method under test: {@link RequestBuilderBase#setQueryParams(List)}
   */
  @Test
  void testSetQueryParams3() {
    // Arrange
    RequestBuilder requestBuilder = new RequestBuilder();
    requestBuilder.setAddress(mock(InetAddress.class));

    // Act
    RequestBuilder actualSetQueryParamsResult = requestBuilder.setQueryParams(new ArrayList<>());

    // Assert
    assertNull(requestBuilder.uri);
    assertTrue(requestBuilder.queryParams.isEmpty());
    assertSame(requestBuilder, actualSetQueryParamsResult);
  }

  /**
   * Method under test: {@link RequestBuilderBase#setQueryParams(List)}
   */
  @Test
  void testSetQueryParams4() throws URISyntaxException {
    // Arrange
    RequestBuilder requestBuilder = new RequestBuilder();
    requestBuilder.setUri(new Uri(Uri.HTTPS, "https://example.org/example", "https://example.org/example", 8080,
        "https://example.org/example", "https://example.org/example", "https://example.org/example"));

    // Act
    RequestBuilder actualSetQueryParamsResult = requestBuilder.setQueryParams(new ArrayList<>());

    // Assert
    Uri uri = requestBuilder.uri;
    assertEquals("https://https://example.org/example@https://example.org/example:8080https://example.org/example",
        uri.toJavaNetURI().toString());
    assertNull(uri.getQuery());
    assertTrue(requestBuilder.queryParams.isEmpty());
    assertSame(requestBuilder, actualSetQueryParamsResult);
  }

  /**
   * Method under test: {@link RequestBuilderBase#setQueryParams(List)}
   */
  @Test
  void testSetQueryParams5() throws URISyntaxException {
    // Arrange
    RequestBuilder requestBuilder = new RequestBuilder();
    requestBuilder.setUri(new Uri(Uri.WSS, "https://example.org/example", "https://example.org/example", 8080,
        "https://example.org/example", "https://example.org/example", "https://example.org/example"));

    // Act
    RequestBuilder actualSetQueryParamsResult = requestBuilder.setQueryParams(new ArrayList<>());

    // Assert
    Uri uri = requestBuilder.uri;
    assertEquals("wss://https://example.org/example@https://example.org/example:8080https://example.org/example",
        uri.toJavaNetURI().toString());
    assertNull(uri.getQuery());
    assertTrue(requestBuilder.queryParams.isEmpty());
    assertSame(requestBuilder, actualSetQueryParamsResult);
  }

  /**
   * Method under test: {@link RequestBuilderBase#setQueryParams(List)}
   */
  @Test
  void testSetQueryParams6() throws URISyntaxException {
    // Arrange
    RequestBuilder requestBuilder = new RequestBuilder();
    requestBuilder.setUri(new Uri(Uri.WS, "https://example.org/example", "https://example.org/example", 8080,
        "https://example.org/example", "https://example.org/example", "https://example.org/example"));

    // Act
    RequestBuilder actualSetQueryParamsResult = requestBuilder.setQueryParams(new ArrayList<>());

    // Assert
    Uri uri = requestBuilder.uri;
    assertEquals("ws://https://example.org/example@https://example.org/example:8080https://example.org/example",
        uri.toJavaNetURI().toString());
    assertNull(uri.getQuery());
    assertTrue(requestBuilder.queryParams.isEmpty());
    assertSame(requestBuilder, actualSetQueryParamsResult);
  }

  /**
   * Method under test: {@link RequestBuilderBase#setQueryParams(List)}
   */
  @Test
  void testSetQueryParams7() throws URISyntaxException {
    // Arrange
    RequestBuilder requestBuilder = new RequestBuilder();
    requestBuilder.setUri(new Uri("https://example.org/example", "https://example.org/example",
        "https://example.org/example", 8080, "https://example.org/example", null, "https://example.org/example"));

    // Act
    RequestBuilder actualSetQueryParamsResult = requestBuilder.setQueryParams(new ArrayList<>());

    // Assert
    Uri uri = requestBuilder.uri;
    assertEquals("https://example.org/example://https://example.org/example@https://example.org/example:8080https:/"
        + "/example.org/example", uri.toJavaNetURI().toString());
    assertNull(uri.getQuery());
    assertTrue(requestBuilder.queryParams.isEmpty());
    assertSame(requestBuilder, actualSetQueryParamsResult);
  }

  /**
   * Method under test: {@link RequestBuilderBase#setQueryParams(List)}
   */
  @Test
  void testSetQueryParams8() throws URISyntaxException {
    // Arrange
    RequestBuilder requestBuilder = new RequestBuilder();
    requestBuilder.setUri(new Uri("https://example.org/example", "https://example.org/example",
        "https://example.org/example", 8080, "https://example.org/example", "", "https://example.org/example"));

    // Act
    RequestBuilder actualSetQueryParamsResult = requestBuilder.setQueryParams(new ArrayList<>());

    // Assert
    Uri uri = requestBuilder.uri;
    assertEquals("", uri.getQuery());
    assertEquals("https://example.org/example://https://example.org/example@https://example.org/example:8080https:/"
        + "/example.org/example?", uri.toJavaNetURI().toString());
    assertTrue(requestBuilder.queryParams.isEmpty());
    assertSame(requestBuilder, actualSetQueryParamsResult);
  }

  /**
   * Method under test: {@link RequestBuilderBase#setQueryParams(Map)}
   */
  @Test
  void testSetQueryParams9() {
    // Arrange
    RequestBuilder requestBuilder = new RequestBuilder();
    HashMap<String, List<String>> map = new HashMap<>();

    // Act
    RequestBuilder actualSetQueryParamsResult = requestBuilder.setQueryParams(map);

    // Assert
    assertNull(requestBuilder.uri);
    assertTrue(map.isEmpty());
    assertTrue(requestBuilder.queryParams.isEmpty());
    assertSame(requestBuilder, actualSetQueryParamsResult);
  }

  /**
   * Method under test: {@link RequestBuilderBase#setQueryParams(Map)}
   */
  @Test
  void testSetQueryParams10() {
    // Arrange
    RequestBuilder requestBuilder = new RequestBuilder();
    requestBuilder.setUri(null);

    // Act and Assert
    assertNull(requestBuilder.queryParams);
    assertNull(requestBuilder.uri);
    assertSame(requestBuilder, requestBuilder.setQueryParams((Map<String, List<String>>) null));
  }

  /**
   * Method under test: {@link RequestBuilderBase#setQueryParams(Map)}
   */
  @Test
  void testSetQueryParams11() throws URISyntaxException {
    // Arrange
    RequestBuilder requestBuilder = new RequestBuilder();
    requestBuilder
        .setUri(new Uri("https://example.org/example", "https://example.org/example", "https://example.org/example",
            8080, "https://example.org/example", "https://example.org/example", "https://example.org/example"));

    // Act
    RequestBuilder actualSetQueryParamsResult = requestBuilder.setQueryParams((Map<String, List<String>>) null);

    // Assert
    Uri uri = requestBuilder.uri;
    assertEquals("https://example.org/example://https://example.org/example@https://example.org/example:8080https:/"
        + "/example.org/example", uri.toJavaNetURI().toString());
    assertNull(uri.getQuery());
    assertNull(requestBuilder.queryParams);
    assertSame(requestBuilder, actualSetQueryParamsResult);
  }

  /**
   * Method under test: {@link RequestBuilderBase#setQueryParams(Map)}
   */
  @Test
  void testSetQueryParams12() {
    // Arrange
    RequestBuilder requestBuilder = new RequestBuilder();

    HashMap<String, List<String>> map = new HashMap<>();
    ArrayList<String> stringList = new ArrayList<>();
    map.put("foo", stringList);

    // Act
    RequestBuilder actualSetQueryParamsResult = requestBuilder.setQueryParams(map);

    // Assert
    assertNull(requestBuilder.uri);
    assertEquals(1, map.size());
    List<String> getResult = map.get("foo");
    assertTrue(getResult.isEmpty());
    assertTrue(requestBuilder.queryParams.isEmpty());
    assertSame(stringList, getResult);
    assertSame(requestBuilder, actualSetQueryParamsResult);
  }

  /**
   * Method under test: {@link RequestBuilderBase#setQueryParams(Map)}
   */
  @Test
  void testSetQueryParams13() {
    // Arrange
    RequestBuilder requestBuilder = new RequestBuilder();

    HashMap<String, List<String>> map = new HashMap<>();
    map.computeIfPresent("foo", mock(BiFunction.class));
    ArrayList<String> stringList = new ArrayList<>();
    map.put("foo", stringList);

    // Act
    RequestBuilder actualSetQueryParamsResult = requestBuilder.setQueryParams(map);

    // Assert
    assertNull(requestBuilder.uri);
    assertEquals(1, map.size());
    List<String> getResult = map.get("foo");
    assertTrue(getResult.isEmpty());
    assertTrue(requestBuilder.queryParams.isEmpty());
    assertSame(stringList, getResult);
    assertSame(requestBuilder, actualSetQueryParamsResult);
  }

  /**
   * Method under test: {@link RequestBuilderBase#setQueryParams(Map)}
   */
  @Test
  void testSetQueryParams14() throws URISyntaxException {
    // Arrange
    RequestBuilder requestBuilder = new RequestBuilder();
    requestBuilder.setUri(Uri.create(
        new Uri("https://example.org/example", "https://example.org/example", "https://example.org/example", 8080,
            "https://example.org/example", "https://example.org/example", "https://example.org/example"),
        "https://example.org/example"));

    // Act
    RequestBuilder actualSetQueryParamsResult = requestBuilder.setQueryParams((Map<String, List<String>>) null);

    // Assert
    Uri uri = requestBuilder.uri;
    assertEquals("https://example.org/example", uri.toJavaNetURI().toString());
    assertNull(uri.getQuery());
    assertNull(requestBuilder.queryParams);
    assertSame(requestBuilder, actualSetQueryParamsResult);
  }

  /**
   * Method under test: {@link RequestBuilderBase#setQueryParams(Map)}
   */
  @Test
  void testSetQueryParams15() throws URISyntaxException {
    // Arrange
    RequestBuilder requestBuilder = new RequestBuilder();
    requestBuilder.setUri(new Uri(Uri.HTTPS, "https://example.org/example", "https://example.org/example", 8080,
        "https://example.org/example", "https://example.org/example", "https://example.org/example"));

    // Act
    RequestBuilder actualSetQueryParamsResult = requestBuilder.setQueryParams((Map<String, List<String>>) null);

    // Assert
    Uri uri = requestBuilder.uri;
    assertEquals("https://https://example.org/example@https://example.org/example:8080https://example.org/example",
        uri.toJavaNetURI().toString());
    assertNull(uri.getQuery());
    assertNull(requestBuilder.queryParams);
    assertSame(requestBuilder, actualSetQueryParamsResult);
  }

  /**
   * Method under test: {@link RequestBuilderBase#setQueryParams(Map)}
   */
  @Test
  void testSetQueryParams16() throws URISyntaxException {
    // Arrange
    RequestBuilder requestBuilder = new RequestBuilder();
    requestBuilder.setUri(new Uri(Uri.WSS, "https://example.org/example", "https://example.org/example", 8080,
        "https://example.org/example", "https://example.org/example", "https://example.org/example"));

    // Act
    RequestBuilder actualSetQueryParamsResult = requestBuilder.setQueryParams((Map<String, List<String>>) null);

    // Assert
    Uri uri = requestBuilder.uri;
    assertEquals("wss://https://example.org/example@https://example.org/example:8080https://example.org/example",
        uri.toJavaNetURI().toString());
    assertNull(uri.getQuery());
    assertNull(requestBuilder.queryParams);
    assertSame(requestBuilder, actualSetQueryParamsResult);
  }

  /**
   * Method under test: {@link RequestBuilderBase#setQueryParams(Map)}
   */
  @Test
  void testSetQueryParams17() throws URISyntaxException {
    // Arrange
    RequestBuilder requestBuilder = new RequestBuilder();
    requestBuilder.setUri(new Uri(Uri.WS, "https://example.org/example", "https://example.org/example", 8080,
        "https://example.org/example", "https://example.org/example", "https://example.org/example"));

    // Act
    RequestBuilder actualSetQueryParamsResult = requestBuilder.setQueryParams((Map<String, List<String>>) null);

    // Assert
    Uri uri = requestBuilder.uri;
    assertEquals("ws://https://example.org/example@https://example.org/example:8080https://example.org/example",
        uri.toJavaNetURI().toString());
    assertNull(uri.getQuery());
    assertNull(requestBuilder.queryParams);
    assertSame(requestBuilder, actualSetQueryParamsResult);
  }

  /**
   * Method under test: {@link RequestBuilderBase#setQueryParams(Map)}
   */
  @Test
  void testSetQueryParams18() throws URISyntaxException {
    // Arrange
    RequestBuilder requestBuilder = new RequestBuilder();
    requestBuilder.setUri(new Uri("https://example.org/example", "https://example.org/example",
        "https://example.org/example", 8080, "https://example.org/example", "", "https://example.org/example"));

    // Act
    RequestBuilder actualSetQueryParamsResult = requestBuilder.setQueryParams((Map<String, List<String>>) null);

    // Assert
    Uri uri = requestBuilder.uri;
    assertEquals("", uri.getQuery());
    assertEquals("https://example.org/example://https://example.org/example@https://example.org/example:8080https:/"
        + "/example.org/example?", uri.toJavaNetURI().toString());
    assertNull(requestBuilder.queryParams);
    assertSame(requestBuilder, actualSetQueryParamsResult);
  }

  /**
   * Method under test: {@link RequestBuilderBase#setFormParams(List)}
   */
  @Test
  void testSetFormParams() {
    // Arrange
    RequestBuilder requestBuilder = new RequestBuilder();

    // Act
    RequestBuilder actualSetFormParamsResult = requestBuilder.setFormParams(new ArrayList<>());

    // Assert
    assertTrue(requestBuilder.formParams.isEmpty());
    assertSame(requestBuilder, actualSetFormParamsResult);
  }

  /**
   * Method under test: {@link RequestBuilderBase#setFormParams(List)}
   */
  @Test
  void testSetFormParams2() {
    // Arrange
    RequestBuilder requestBuilder = new RequestBuilder();
    requestBuilder.setAddress(mock(InetAddress.class));

    // Act
    RequestBuilder actualSetFormParamsResult = requestBuilder.setFormParams(new ArrayList<>());

    // Assert
    assertTrue(requestBuilder.formParams.isEmpty());
    assertSame(requestBuilder, actualSetFormParamsResult);
  }

  /**
   * Method under test: {@link RequestBuilderBase#setFormParams(Map)}
   */
  @Test
  void testSetFormParams3() {
    // Arrange
    RequestBuilder requestBuilder = new RequestBuilder();
    HashMap<String, List<String>> map = new HashMap<>();

    // Act
    RequestBuilder actualSetFormParamsResult = requestBuilder.setFormParams(map);

    // Assert
    assertTrue(map.isEmpty());
    assertTrue(requestBuilder.formParams.isEmpty());
    assertSame(requestBuilder, actualSetFormParamsResult);
  }

  /**
   * Method under test: {@link RequestBuilderBase#setFormParams(Map)}
   */
  @Test
  void testSetFormParams4() {
    // Arrange
    RequestBuilder requestBuilder = new RequestBuilder();

    // Act and Assert
    assertNull(requestBuilder.formParams);
    assertSame(requestBuilder, requestBuilder.setFormParams((Map<String, List<String>>) null));
  }

  /**
   * Method under test: {@link RequestBuilderBase#setFormParams(Map)}
   */
  @Test
  void testSetFormParams5() {
    // Arrange
    RequestBuilder requestBuilder = new RequestBuilder();

    HashMap<String, List<String>> map = new HashMap<>();
    ArrayList<String> stringList = new ArrayList<>();
    map.put("foo", stringList);

    // Act
    RequestBuilder actualSetFormParamsResult = requestBuilder.setFormParams(map);

    // Assert
    assertEquals(1, map.size());
    List<String> getResult = map.get("foo");
    assertTrue(getResult.isEmpty());
    assertTrue(requestBuilder.formParams.isEmpty());
    assertSame(stringList, getResult);
    assertSame(requestBuilder, actualSetFormParamsResult);
  }

  /**
   * Method under test: {@link RequestBuilderBase#setFormParams(Map)}
   */
  @Test
  void testSetFormParams6() {
    // Arrange
    RequestBuilder requestBuilder = new RequestBuilder();

    HashMap<String, List<String>> map = new HashMap<>();
    map.computeIfPresent("foo", mock(BiFunction.class));
    ArrayList<String> stringList = new ArrayList<>();
    map.put("foo", stringList);

    // Act
    RequestBuilder actualSetFormParamsResult = requestBuilder.setFormParams(map);

    // Assert
    assertEquals(1, map.size());
    List<String> getResult = map.get("foo");
    assertTrue(getResult.isEmpty());
    assertTrue(requestBuilder.formParams.isEmpty());
    assertSame(stringList, getResult);
    assertSame(requestBuilder, actualSetFormParamsResult);
  }

  /**
   * Method under test: {@link RequestBuilderBase#setFormParams(Map)}
   */
  @Test
  void testSetFormParams7() {
    // Arrange
    RequestBuilder requestBuilder = new RequestBuilder();

    ArrayList<String> stringList = new ArrayList<>();
    stringList.add("foo");

    HashMap<String, List<String>> map = new HashMap<>();
    map.put("foo", stringList);

    // Act
    RequestBuilder actualSetFormParamsResult = requestBuilder.setFormParams(map);

    // Assert
    assertEquals(1, map.size());
    List<String> getResult = map.get("foo");
    assertEquals(1, getResult.size());
    assertEquals(1, requestBuilder.formParams.size());
    assertSame(stringList, getResult);
    assertSame(requestBuilder, actualSetFormParamsResult);
  }

  /**
   * Method under test: {@link RequestBuilderBase#setBodyParts(List)}
   */
  @Test
  void testSetBodyParts() {
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
   * Method under test: {@link RequestBuilderBase#setBodyParts(List)}
   */
  @Test
  void testSetBodyParts2() throws UnsupportedEncodingException {
    // Arrange
    RequestBuilder requestBuilder = new RequestBuilder();

    ArrayList<Part> bodyParts = new ArrayList<>();
    ByteArrayPart byteArrayPart = new ByteArrayPart("https://example.org/example", "AXAXAXAX".getBytes("UTF-8"));

    bodyParts.add(byteArrayPart);

    // Act
    RequestBuilder actualSetBodyPartsResult = requestBuilder.setBodyParts(bodyParts);

    // Assert
    assertEquals(1, bodyParts.size());
    List<Part> partList = requestBuilder.bodyParts;
    assertEquals(1, partList.size());
    assertSame(requestBuilder, actualSetBodyPartsResult);
    assertSame(byteArrayPart, partList.get(0));
  }

  /**
   * Method under test: {@link RequestBuilderBase#setBodyParts(List)}
   */
  @Test
  void testSetBodyParts3() throws UnsupportedEncodingException {
    // Arrange
    RequestBuilder requestBuilder = new RequestBuilder();

    ArrayList<Part> bodyParts = new ArrayList<>();
    ByteArrayPart byteArrayPart = new ByteArrayPart("https://example.org/example", "AXAXAXAX".getBytes("UTF-8"));

    bodyParts.add(byteArrayPart);
    ByteArrayPart byteArrayPart2 = new ByteArrayPart("https://example.org/example", "AXAXAXAX".getBytes("UTF-8"));

    bodyParts.add(byteArrayPart2);

    // Act
    RequestBuilder actualSetBodyPartsResult = requestBuilder.setBodyParts(bodyParts);

    // Assert
    List<Part> partList = requestBuilder.bodyParts;
    assertEquals(2, partList.size());
    assertEquals(actualSetBodyPartsResult.bodyParts, bodyParts);
    assertSame(requestBuilder, actualSetBodyPartsResult);
    assertSame(byteArrayPart, partList.get(0));
    assertSame(byteArrayPart2, partList.get(1));
  }

  /**
   * Method under test: {@link RequestBuilderBase#setBodyParts(List)}
   */
  @Test
  void testSetBodyParts4() {
    // Arrange
    RequestBuilder requestBuilder = new RequestBuilder();
    requestBuilder.setAddress(mock(InetAddress.class));
    ArrayList<Part> bodyParts = new ArrayList<>();

    // Act
    RequestBuilder actualSetBodyPartsResult = requestBuilder.setBodyParts(bodyParts);

    // Assert
    assertTrue(bodyParts.isEmpty());
    assertTrue(requestBuilder.bodyParts.isEmpty());
    assertSame(requestBuilder, actualSetBodyPartsResult);
  }

  /**
   * Method under test:
   * {@link RequestBuilderBase#setProxyServer(ProxyServer.Builder)}
   */
  @Test
  void testSetProxyServer() {
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
   * Method under test:
   * {@link RequestBuilderBase#setProxyServer(ProxyServer.Builder)}
   */
  @Test
  void testSetProxyServer2() {
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
   * Method under test:
   * {@link RequestBuilderBase#setProxyServer(ProxyServer.Builder)}
   */
  @Test
  void testSetProxyServer3() {
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
   * Method under test:
   * {@link RequestBuilderBase#setProxyServer(ProxyServer.Builder)}
   */
  @Test
  void testSetProxyServer4() {
    // Arrange
    RequestBuilder requestBuilder = new RequestBuilder();
    ProxyServer.Builder proxyServerBuilder = Dsl.proxyServer("https://example.org/example", 8080);
    proxyServerBuilder.setCustomHeaders(mock(Function.class));

    // Act
    RequestBuilder actualSetProxyServerResult = requestBuilder.setProxyServer(proxyServerBuilder);

    // Assert
    ProxyServer proxyServer = requestBuilder.proxyServer;
    assertEquals("https://example.org/example", proxyServer.getHost());
    assertNull(proxyServer.getRealm());
    assertEquals(8080, proxyServer.getPort());
    assertEquals(8080, proxyServer.getSecuredPort());
    assertEquals(ProxyType.HTTP, proxyServer.getProxyType());
    assertSame(requestBuilder, actualSetProxyServerResult);
  }

  /**
   * Method under test: {@link RequestBuilderBase#setProxyServer(ProxyServer)}
   */
  @Test
  void testSetProxyServer5() {
    // Arrange
    RequestBuilder requestBuilder = new RequestBuilder();
    Realm realm = mock(Realm.class);
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
   * Method under test: {@link RequestBuilderBase#setRealm(Realm.Builder)}
   */
  @Test
  void testSetRealm() {
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
   * Method under test: {@link RequestBuilderBase#setRealm(Realm)}
   */
  @Test
  void testSetRealm2() {
    // Arrange
    RequestBuilder requestBuilder = new RequestBuilder();

    // Act and Assert
    assertSame(requestBuilder, requestBuilder.setRealm(mock(Realm.class)));
  }

  /**
   * Method under test: {@link RequestBuilderBase#setRequestTimeout(Duration)}
   */
  @Test
  void testSetRequestTimeout() {
    // Arrange
    RequestBuilder requestBuilder = new RequestBuilder();

    // Act and Assert
    assertSame(requestBuilder, requestBuilder.setRequestTimeout(null));
  }

  /**
   * Method under test: {@link RequestBuilderBase#setRequestTimeout(Duration)}
   */
  @Test
  void testSetRequestTimeout2() {
    // Arrange
    RequestBuilder requestBuilder = new RequestBuilder();
    requestBuilder.setAddress(mock(InetAddress.class));

    // Act and Assert
    assertSame(requestBuilder, requestBuilder.setRequestTimeout(null));
  }

  /**
   * Method under test: {@link RequestBuilderBase#setReadTimeout(Duration)}
   */
  @Test
  void testSetReadTimeout() {
    // Arrange
    RequestBuilder requestBuilder = new RequestBuilder();

    // Act and Assert
    assertSame(requestBuilder, requestBuilder.setReadTimeout(null));
  }

  /**
   * Method under test: {@link RequestBuilderBase#setReadTimeout(Duration)}
   */
  @Test
  void testSetReadTimeout2() {
    // Arrange
    RequestBuilder requestBuilder = new RequestBuilder();
    requestBuilder.setAddress(mock(InetAddress.class));

    // Act and Assert
    assertSame(requestBuilder, requestBuilder.setReadTimeout(null));
  }

  /**
   * Method under test: {@link RequestBuilderBase#setCharset(Charset)}
   */
  @Test
  void testSetCharset() {
    // Arrange
    RequestBuilder requestBuilder = new RequestBuilder();

    // Act and Assert
    assertSame(requestBuilder, requestBuilder.setCharset(null));
  }

  /**
   * Method under test: {@link RequestBuilderBase#setCharset(Charset)}
   */
  @Test
  void testSetCharset2() {
    // Arrange
    RequestBuilder requestBuilder = new RequestBuilder();
    requestBuilder.setAddress(mock(InetAddress.class));

    // Act and Assert
    assertSame(requestBuilder, requestBuilder.setCharset(null));
  }

  /**
   * Method under test:
   * {@link RequestBuilderBase#setChannelPoolPartitioning(ChannelPoolPartitioning)}
   */
  @Test
  void testSetChannelPoolPartitioning() {
    // Arrange
    RequestBuilder requestBuilder = new RequestBuilder();

    // Act and Assert
    assertSame(requestBuilder, requestBuilder.setChannelPoolPartitioning(mock(ChannelPoolPartitioning.class)));
  }

  /**
   * Method under test: {@link RequestBuilderBase#setNameResolver(NameResolver)}
   */
  @Test
  void testSetNameResolver() {
    // Arrange
    RequestBuilder requestBuilder = new RequestBuilder();

    // Act and Assert
    assertSame(requestBuilder, requestBuilder.setNameResolver(RequestBuilderBase.DEFAULT_NAME_RESOLVER));
  }

  /**
   * Method under test:
   * {@link RequestBuilderBase#setSignatureCalculator(SignatureCalculator)}
   */
  @Test
  void testSetSignatureCalculator() {
    // Arrange
    RequestBuilder requestBuilder = new RequestBuilder();

    // Act and Assert
    assertSame(requestBuilder, requestBuilder.setSignatureCalculator(mock(SignatureCalculator.class)));
  }
}
