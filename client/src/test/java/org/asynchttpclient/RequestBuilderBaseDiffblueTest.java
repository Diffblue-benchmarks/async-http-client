package org.asynchttpclient;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import com.diffblue.cover.annotations.MethodsUnderTest;
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
import org.asynchttpclient.Realm.AuthScheme;
import org.asynchttpclient.Realm.Builder;
import org.asynchttpclient.channel.ChannelPoolPartitioning;
import org.asynchttpclient.config.AsyncHttpClientConfigDefaults;
import org.asynchttpclient.proxy.ProxyServer;
import org.asynchttpclient.proxy.ProxyType;
import org.asynchttpclient.request.body.generator.BodyGenerator;
import org.asynchttpclient.request.body.multipart.ByteArrayPart;
import org.asynchttpclient.request.body.multipart.Part;
import org.asynchttpclient.uri.Uri;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

class RequestBuilderBaseDiffblueTest {
  /**
   * Test {@link RequestBuilderBase#setUrl(String)}.
   * <ul>
   *   <li>When {@code http://localhost}.</li>
   *   <li>Then {@link RequestBuilder#RequestBuilder()} {@link RequestBuilderBase#uri} Path is empty string.</li>
   * </ul>
   * <p>
   * Method under test: {@link RequestBuilderBase#setUrl(String)}
   */
  @Test
  @DisplayName("Test setUrl(String); when 'http://localhost'; then RequestBuilder() uri Path is empty string")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"RequestBuilderBase RequestBuilderBase.setUrl(String)"})
  void testSetUrl_whenHttpLocalhost_thenRequestBuilderUriPathIsEmptyString() {
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
   *   <li>Then {@link RequestBuilder#RequestBuilder()} {@link RequestBuilderBase#uri} NonEmptyPath is {@code /example}.</li>
   * </ul>
   * <p>
   * Method under test: {@link RequestBuilderBase#setUrl(String)}
   */
  @Test
  @DisplayName("Test setUrl(String); when 'https://example.org/example'; then RequestBuilder() uri NonEmptyPath is '/example'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"RequestBuilderBase RequestBuilderBase.setUrl(String)"})
  void testSetUrl_whenHttpsExampleOrgExample_thenRequestBuilderUriNonEmptyPathIsExample() {
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
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"RequestBuilderBase RequestBuilderBase.setUri(Uri)"})
  void testSetUri() {
    // Arrange
    RequestBuilder requestBuilder = new RequestBuilder();

    // Act and Assert
    assertSame(requestBuilder,
        requestBuilder
            .setUri(new Uri("https://example.org/example", "https://example.org/example", "https://example.org/example",
                8080, "https://example.org/example", "https://example.org/example", "https://example.org/example")));
  }

  /**
   * Test {@link RequestBuilderBase#setAddress(InetAddress)}.
   * <p>
   * Method under test: {@link RequestBuilderBase#setAddress(InetAddress)}
   */
  @Test
  @DisplayName("Test setAddress(InetAddress)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"RequestBuilderBase RequestBuilderBase.setAddress(InetAddress)"})
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
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"RequestBuilderBase RequestBuilderBase.setLocalAddress(InetAddress)"})
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
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"RequestBuilderBase RequestBuilderBase.clearHeaders()"})
  void testClearHeaders() {
    // Arrange
    RequestBuilder requestBuilder = new RequestBuilder();

    // Act and Assert
    assertSame(requestBuilder, requestBuilder.clearHeaders());
  }

  /**
   * Test {@link RequestBuilderBase#setHeader(CharSequence, Iterable)} with {@code CharSequence}, {@code Iterable}.
   * <p>
   * Method under test: {@link RequestBuilderBase#setHeader(CharSequence, Iterable)}
   */
  @Test
  @DisplayName("Test setHeader(CharSequence, Iterable) with 'CharSequence', 'Iterable'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"RequestBuilderBase RequestBuilderBase.setHeader(CharSequence, Iterable)"})
  void testSetHeaderWithCharSequenceIterable() {
    // Arrange
    RequestBuilder requestBuilder = new RequestBuilder();
    requestBuilder.addHeader(AsyncHttpClientConfigDefaults.ACQUIRE_FREE_CHANNEL_TIMEOUT, "https://example.org/example");

    // Act and Assert
    assertSame(requestBuilder, requestBuilder.setHeader(AsyncHttpClientConfigDefaults.ACQUIRE_FREE_CHANNEL_TIMEOUT,
        (Iterable<?>) new ArrayList<>()));
  }

  /**
   * Test {@link RequestBuilderBase#setHeader(CharSequence, Iterable)} with {@code CharSequence}, {@code Iterable}.
   * <p>
   * Method under test: {@link RequestBuilderBase#setHeader(CharSequence, Iterable)}
   */
  @Test
  @DisplayName("Test setHeader(CharSequence, Iterable) with 'CharSequence', 'Iterable'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"RequestBuilderBase RequestBuilderBase.setHeader(CharSequence, Iterable)"})
  void testSetHeaderWithCharSequenceIterable2() {
    // Arrange
    RequestBuilder requestBuilder = new RequestBuilder();
    requestBuilder.addHeader(AsyncHttpClientConfigDefaults.ACQUIRE_FREE_CHANNEL_TIMEOUT, "42");
    requestBuilder.addHeader(AsyncHttpClientConfigDefaults.ACQUIRE_FREE_CHANNEL_TIMEOUT, "https://example.org/example");

    // Act and Assert
    assertSame(requestBuilder, requestBuilder.setHeader(AsyncHttpClientConfigDefaults.ACQUIRE_FREE_CHANNEL_TIMEOUT,
        (Iterable<?>) new ArrayList<>()));
  }

  /**
   * Test {@link RequestBuilderBase#setHeader(CharSequence, Iterable)} with {@code CharSequence}, {@code Iterable}.
   * <ul>
   *   <li>Given {@link RequestBuilder#RequestBuilder()}.</li>
   * </ul>
   * <p>
   * Method under test: {@link RequestBuilderBase#setHeader(CharSequence, Iterable)}
   */
  @Test
  @DisplayName("Test setHeader(CharSequence, Iterable) with 'CharSequence', 'Iterable'; given RequestBuilder()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"RequestBuilderBase RequestBuilderBase.setHeader(CharSequence, Iterable)"})
  void testSetHeaderWithCharSequenceIterable_givenRequestBuilder() {
    // Arrange
    RequestBuilder requestBuilder = new RequestBuilder();

    // Act and Assert
    assertSame(requestBuilder, requestBuilder.setHeader(AsyncHttpClientConfigDefaults.ACQUIRE_FREE_CHANNEL_TIMEOUT,
        (Iterable<?>) new ArrayList<>()));
  }

  /**
   * Test {@link RequestBuilderBase#setHeader(CharSequence, Object)} with {@code CharSequence}, {@code Object}.
   * <p>
   * Method under test: {@link RequestBuilderBase#setHeader(CharSequence, Object)}
   */
  @Test
  @DisplayName("Test setHeader(CharSequence, Object) with 'CharSequence', 'Object'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"RequestBuilderBase RequestBuilderBase.setHeader(CharSequence, Object)"})
  void testSetHeaderWithCharSequenceObject() {
    // Arrange
    RequestBuilder requestBuilder = new RequestBuilder();
    requestBuilder.addHeader(AsyncHttpClientConfigDefaults.ACQUIRE_FREE_CHANNEL_TIMEOUT, "https://example.org/example");

    // Act and Assert
    assertSame(requestBuilder,
        requestBuilder.setHeader(AsyncHttpClientConfigDefaults.ACQUIRE_FREE_CHANNEL_TIMEOUT, (Object) "Value"));
  }

  /**
   * Test {@link RequestBuilderBase#setHeader(CharSequence, Object)} with {@code CharSequence}, {@code Object}.
   * <p>
   * Method under test: {@link RequestBuilderBase#setHeader(CharSequence, Object)}
   */
  @Test
  @DisplayName("Test setHeader(CharSequence, Object) with 'CharSequence', 'Object'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"RequestBuilderBase RequestBuilderBase.setHeader(CharSequence, Object)"})
  void testSetHeaderWithCharSequenceObject2() {
    // Arrange
    RequestBuilder requestBuilder = new RequestBuilder();
    requestBuilder.addHeader(AsyncHttpClientConfigDefaults.ACQUIRE_FREE_CHANNEL_TIMEOUT, "42");
    requestBuilder.addHeader(AsyncHttpClientConfigDefaults.ACQUIRE_FREE_CHANNEL_TIMEOUT, "https://example.org/example");

    // Act and Assert
    assertSame(requestBuilder,
        requestBuilder.setHeader(AsyncHttpClientConfigDefaults.ACQUIRE_FREE_CHANNEL_TIMEOUT, (Object) "Value"));
  }

  /**
   * Test {@link RequestBuilderBase#setHeader(CharSequence, Object)} with {@code CharSequence}, {@code Object}.
   * <ul>
   *   <li>Given {@link RequestBuilder#RequestBuilder()}.</li>
   * </ul>
   * <p>
   * Method under test: {@link RequestBuilderBase#setHeader(CharSequence, Object)}
   */
  @Test
  @DisplayName("Test setHeader(CharSequence, Object) with 'CharSequence', 'Object'; given RequestBuilder()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"RequestBuilderBase RequestBuilderBase.setHeader(CharSequence, Object)"})
  void testSetHeaderWithCharSequenceObject_givenRequestBuilder() {
    // Arrange
    RequestBuilder requestBuilder = new RequestBuilder();

    // Act and Assert
    assertSame(requestBuilder,
        requestBuilder.setHeader(AsyncHttpClientConfigDefaults.ACQUIRE_FREE_CHANNEL_TIMEOUT, (Object) "Value"));
  }

  /**
   * Test {@link RequestBuilderBase#setHeader(CharSequence, String)} with {@code CharSequence}, {@code String}.
   * <p>
   * Method under test: {@link RequestBuilderBase#setHeader(CharSequence, String)}
   */
  @Test
  @DisplayName("Test setHeader(CharSequence, String) with 'CharSequence', 'String'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"RequestBuilderBase RequestBuilderBase.setHeader(CharSequence, String)"})
  void testSetHeaderWithCharSequenceString() {
    // Arrange
    RequestBuilder requestBuilder = new RequestBuilder();
    requestBuilder.addHeader(AsyncHttpClientConfigDefaults.ACQUIRE_FREE_CHANNEL_TIMEOUT, "https://example.org/example");

    // Act and Assert
    assertSame(requestBuilder, requestBuilder.setHeader(AsyncHttpClientConfigDefaults.ACQUIRE_FREE_CHANNEL_TIMEOUT,
        "https://example.org/example"));
  }

  /**
   * Test {@link RequestBuilderBase#setHeader(CharSequence, String)} with {@code CharSequence}, {@code String}.
   * <p>
   * Method under test: {@link RequestBuilderBase#setHeader(CharSequence, String)}
   */
  @Test
  @DisplayName("Test setHeader(CharSequence, String) with 'CharSequence', 'String'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"RequestBuilderBase RequestBuilderBase.setHeader(CharSequence, String)"})
  void testSetHeaderWithCharSequenceString2() {
    // Arrange
    RequestBuilder requestBuilder = new RequestBuilder();
    requestBuilder.addHeader(AsyncHttpClientConfigDefaults.ACQUIRE_FREE_CHANNEL_TIMEOUT, "42");
    requestBuilder.addHeader(AsyncHttpClientConfigDefaults.ACQUIRE_FREE_CHANNEL_TIMEOUT, "https://example.org/example");

    // Act and Assert
    assertSame(requestBuilder, requestBuilder.setHeader(AsyncHttpClientConfigDefaults.ACQUIRE_FREE_CHANNEL_TIMEOUT,
        "https://example.org/example"));
  }

  /**
   * Test {@link RequestBuilderBase#setHeader(CharSequence, String)} with {@code CharSequence}, {@code String}.
   * <ul>
   *   <li>Given {@link RequestBuilder#RequestBuilder()}.</li>
   * </ul>
   * <p>
   * Method under test: {@link RequestBuilderBase#setHeader(CharSequence, String)}
   */
  @Test
  @DisplayName("Test setHeader(CharSequence, String) with 'CharSequence', 'String'; given RequestBuilder()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"RequestBuilderBase RequestBuilderBase.setHeader(CharSequence, String)"})
  void testSetHeaderWithCharSequenceString_givenRequestBuilder() {
    // Arrange
    RequestBuilder requestBuilder = new RequestBuilder();

    // Act and Assert
    assertSame(requestBuilder, requestBuilder.setHeader(AsyncHttpClientConfigDefaults.ACQUIRE_FREE_CHANNEL_TIMEOUT,
        "https://example.org/example"));
  }

  /**
   * Test {@link RequestBuilderBase#addHeader(CharSequence, Iterable)} with {@code CharSequence}, {@code Iterable}.
   * <ul>
   *   <li>Then return {@link RequestBuilderBase#headers} unwrap size is zero.</li>
   * </ul>
   * <p>
   * Method under test: {@link RequestBuilderBase#addHeader(CharSequence, Iterable)}
   */
  @Test
  @DisplayName("Test addHeader(CharSequence, Iterable) with 'CharSequence', 'Iterable'; then return headers unwrap size is zero")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"RequestBuilderBase RequestBuilderBase.addHeader(CharSequence, Iterable)"})
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
   * Test {@link RequestBuilderBase#addHeader(CharSequence, Object)} with {@code CharSequence}, {@code Object}.
   * <ul>
   *   <li>When {@link AsyncHttpClientConfigDefaults#ACQUIRE_FREE_CHANNEL_TIMEOUT}.</li>
   * </ul>
   * <p>
   * Method under test: {@link RequestBuilderBase#addHeader(CharSequence, Object)}
   */
  @Test
  @DisplayName("Test addHeader(CharSequence, Object) with 'CharSequence', 'Object'; when ACQUIRE_FREE_CHANNEL_TIMEOUT")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"RequestBuilderBase RequestBuilderBase.addHeader(CharSequence, Object)"})
  void testAddHeaderWithCharSequenceObject_whenAcquire_free_channel_timeout() {
    // Arrange
    RequestBuilder requestBuilder = new RequestBuilder();

    // Act and Assert
    assertSame(requestBuilder,
        requestBuilder.addHeader(AsyncHttpClientConfigDefaults.ACQUIRE_FREE_CHANNEL_TIMEOUT, (Object) "Value"));
  }

  /**
   * Test {@link RequestBuilderBase#addHeader(CharSequence, Object)} with {@code CharSequence}, {@code Object}.
   * <ul>
   *   <li>When cached {@code String}.</li>
   *   <li>Then return {@link RequestBuilder#RequestBuilder()}.</li>
   * </ul>
   * <p>
   * Method under test: {@link RequestBuilderBase#addHeader(CharSequence, Object)}
   */
  @Test
  @DisplayName("Test addHeader(CharSequence, Object) with 'CharSequence', 'Object'; when cached 'String'; then return RequestBuilder()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"RequestBuilderBase RequestBuilderBase.addHeader(CharSequence, Object)"})
  void testAddHeaderWithCharSequenceObject_whenCachedString_thenReturnRequestBuilder() {
    // Arrange
    RequestBuilder requestBuilder = new RequestBuilder();

    // Act and Assert
    assertSame(requestBuilder, requestBuilder.addHeader(AsciiString.cached("String"), (Object) "Value"));
  }

  /**
   * Test {@link RequestBuilderBase#addHeader(CharSequence, Object)} with {@code CharSequence}, {@code Object}.
   * <ul>
   *   <li>When {@code null}.</li>
   *   <li>Then return {@link RequestBuilder#RequestBuilder()}.</li>
   * </ul>
   * <p>
   * Method under test: {@link RequestBuilderBase#addHeader(CharSequence, Object)}
   */
  @Test
  @DisplayName("Test addHeader(CharSequence, Object) with 'CharSequence', 'Object'; when 'null'; then return RequestBuilder()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"RequestBuilderBase RequestBuilderBase.addHeader(CharSequence, Object)"})
  void testAddHeaderWithCharSequenceObject_whenNull_thenReturnRequestBuilder() {
    // Arrange
    RequestBuilder requestBuilder = new RequestBuilder();

    // Act and Assert
    assertSame(requestBuilder,
        requestBuilder.addHeader(AsyncHttpClientConfigDefaults.ACQUIRE_FREE_CHANNEL_TIMEOUT, (Object) null));
  }

  /**
   * Test {@link RequestBuilderBase#addHeader(CharSequence, String)} with {@code CharSequence}, {@code String}.
   * <ul>
   *   <li>When {@link AsyncHttpClientConfigDefaults#ACQUIRE_FREE_CHANNEL_TIMEOUT}.</li>
   * </ul>
   * <p>
   * Method under test: {@link RequestBuilderBase#addHeader(CharSequence, String)}
   */
  @Test
  @DisplayName("Test addHeader(CharSequence, String) with 'CharSequence', 'String'; when ACQUIRE_FREE_CHANNEL_TIMEOUT")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"RequestBuilderBase RequestBuilderBase.addHeader(CharSequence, String)"})
  void testAddHeaderWithCharSequenceString_whenAcquire_free_channel_timeout() {
    // Arrange
    RequestBuilder requestBuilder = new RequestBuilder();

    // Act and Assert
    assertSame(requestBuilder, requestBuilder.addHeader(AsyncHttpClientConfigDefaults.ACQUIRE_FREE_CHANNEL_TIMEOUT,
        "https://example.org/example"));
  }

  /**
   * Test {@link RequestBuilderBase#addHeader(CharSequence, String)} with {@code CharSequence}, {@code String}.
   * <ul>
   *   <li>When cached {@code String}.</li>
   *   <li>Then return {@link RequestBuilder#RequestBuilder()}.</li>
   * </ul>
   * <p>
   * Method under test: {@link RequestBuilderBase#addHeader(CharSequence, String)}
   */
  @Test
  @DisplayName("Test addHeader(CharSequence, String) with 'CharSequence', 'String'; when cached 'String'; then return RequestBuilder()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"RequestBuilderBase RequestBuilderBase.addHeader(CharSequence, String)"})
  void testAddHeaderWithCharSequenceString_whenCachedString_thenReturnRequestBuilder() {
    // Arrange
    RequestBuilder requestBuilder = new RequestBuilder();

    // Act and Assert
    assertSame(requestBuilder, requestBuilder.addHeader(AsciiString.cached("String"), "https://example.org/example"));
  }

  /**
   * Test {@link RequestBuilderBase#addHeader(CharSequence, String)} with {@code CharSequence}, {@code String}.
   * <ul>
   *   <li>When {@code null}.</li>
   *   <li>Then return {@link RequestBuilder#RequestBuilder()}.</li>
   * </ul>
   * <p>
   * Method under test: {@link RequestBuilderBase#addHeader(CharSequence, String)}
   */
  @Test
  @DisplayName("Test addHeader(CharSequence, String) with 'CharSequence', 'String'; when 'null'; then return RequestBuilder()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"RequestBuilderBase RequestBuilderBase.addHeader(CharSequence, String)"})
  void testAddHeaderWithCharSequenceString_whenNull_thenReturnRequestBuilder() {
    // Arrange
    RequestBuilder requestBuilder = new RequestBuilder();

    // Act and Assert
    assertSame(requestBuilder,
        requestBuilder.addHeader(AsyncHttpClientConfigDefaults.ACQUIRE_FREE_CHANNEL_TIMEOUT, (String) null));
  }

  /**
   * Test {@link RequestBuilderBase#setHeaders(HttpHeaders)} with {@code HttpHeaders}.
   * <ul>
   *   <li>When {@link DefaultHttpHeaders#DefaultHttpHeaders()}.</li>
   * </ul>
   * <p>
   * Method under test: {@link RequestBuilderBase#setHeaders(HttpHeaders)}
   */
  @Test
  @DisplayName("Test setHeaders(HttpHeaders) with 'HttpHeaders'; when DefaultHttpHeaders()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"RequestBuilderBase RequestBuilderBase.setHeaders(HttpHeaders)"})
  void testSetHeadersWithHttpHeaders_whenDefaultHttpHeaders() {
    // Arrange
    RequestBuilder requestBuilder = new RequestBuilder();

    // Act and Assert
    assertSame(requestBuilder, requestBuilder.setHeaders(new DefaultHttpHeaders()));
  }

  /**
   * Test {@link RequestBuilderBase#setHeaders(HttpHeaders)} with {@code HttpHeaders}.
   * <ul>
   *   <li>When {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link RequestBuilderBase#setHeaders(HttpHeaders)}
   */
  @Test
  @DisplayName("Test setHeaders(HttpHeaders) with 'HttpHeaders'; when 'null'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"RequestBuilderBase RequestBuilderBase.setHeaders(HttpHeaders)"})
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
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"RequestBuilderBase RequestBuilderBase.setHeaders(Map)"})
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
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"RequestBuilderBase RequestBuilderBase.setHeaders(Map)"})
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
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"RequestBuilderBase RequestBuilderBase.setHeaders(Map)"})
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
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"RequestBuilderBase RequestBuilderBase.setSingleHeaders(Map)"})
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
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"RequestBuilderBase RequestBuilderBase.setSingleHeaders(Map)"})
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
   *   <li>Then {@link RequestBuilder#RequestBuilder()} {@link RequestBuilderBase#cookies} size is two.</li>
   * </ul>
   * <p>
   * Method under test: {@link RequestBuilderBase#setCookies(Collection)}
   */
  @Test
  @DisplayName("Test setCookies(Collection); then RequestBuilder() cookies size is two")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"RequestBuilderBase RequestBuilderBase.setCookies(Collection)"})
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
   *   <li>Then return {@link RequestBuilderBase#cookies} size is one.</li>
   * </ul>
   * <p>
   * Method under test: {@link RequestBuilderBase#setCookies(Collection)}
   */
  @Test
  @DisplayName("Test setCookies(Collection); then return cookies size is one")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"RequestBuilderBase RequestBuilderBase.setCookies(Collection)"})
  void testSetCookies_thenReturnCookiesSizeIsOne() {
    // Arrange
    RequestBuilder requestBuilder = new RequestBuilder();

    ArrayList<Cookie> cookies = new ArrayList<>();
    DefaultCookie defaultCookie = new DefaultCookie("https://example.org/example", "https://example.org/example");

    cookies.add(defaultCookie);

    // Act and Assert
    ArrayList<Cookie> cookieList = requestBuilder.setCookies(cookies).cookies;
    assertEquals(1, cookieList.size());
    Cookie getResult = cookieList.get(0);
    assertTrue(getResult instanceof DefaultCookie);
    ArrayList<Cookie> cookieList2 = requestBuilder.cookies;
    assertEquals(1, cookieList2.size());
    assertSame(defaultCookie, cookieList2.get(0));
    assertSame(defaultCookie, getResult);
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
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"RequestBuilderBase RequestBuilderBase.setCookies(Collection)"})
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
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"RequestBuilderBase RequestBuilderBase.addCookie(Cookie)"})
  void testAddCookie_givenRequestBuilder() {
    // Arrange
    RequestBuilder requestBuilder = new RequestBuilder();

    // Act
    RequestBuilder actualAddCookieResult = requestBuilder
        .addCookie(new DefaultCookie("https://example.org/example", "https://example.org/example"));

    // Assert
    assertEquals(1, requestBuilder.cookies.size());
    assertSame(requestBuilder, actualAddCookieResult);
  }

  /**
   * Test {@link RequestBuilderBase#addCookie(Cookie)}.
   * <ul>
   *   <li>Given {@link RequestBuilder#RequestBuilder()} Cookies is {@link ArrayList#ArrayList()}.</li>
   * </ul>
   * <p>
   * Method under test: {@link RequestBuilderBase#addCookie(Cookie)}
   */
  @Test
  @DisplayName("Test addCookie(Cookie); given RequestBuilder() Cookies is ArrayList()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"RequestBuilderBase RequestBuilderBase.addCookie(Cookie)"})
  void testAddCookie_givenRequestBuilderCookiesIsArrayList() {
    // Arrange
    RequestBuilder requestBuilder = new RequestBuilder();
    requestBuilder.setCookies(new ArrayList<>());

    // Act
    RequestBuilder actualAddCookieResult = requestBuilder
        .addCookie(new DefaultCookie("https://example.org/example", "https://example.org/example"));

    // Assert
    assertEquals(1, requestBuilder.cookies.size());
    assertSame(requestBuilder, actualAddCookieResult);
  }

  /**
   * Test {@link RequestBuilderBase#addOrReplaceCookie(Cookie)}.
   * <ul>
   *   <li>Given {@link RequestBuilder#RequestBuilder()}.</li>
   *   <li>Then {@link RequestBuilder#RequestBuilder()} {@link RequestBuilderBase#cookies} size is one.</li>
   * </ul>
   * <p>
   * Method under test: {@link RequestBuilderBase#addOrReplaceCookie(Cookie)}
   */
  @Test
  @DisplayName("Test addOrReplaceCookie(Cookie); given RequestBuilder(); then RequestBuilder() cookies size is one")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"RequestBuilderBase RequestBuilderBase.addOrReplaceCookie(Cookie)"})
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
   *   <li>Then {@link RequestBuilderBase#cookies} first return {@link DefaultCookie}.</li>
   * </ul>
   * <p>
   * Method under test: {@link RequestBuilderBase#addOrReplaceCookie(Cookie)}
   */
  @Test
  @DisplayName("Test addOrReplaceCookie(Cookie); then cookies first return DefaultCookie")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"RequestBuilderBase RequestBuilderBase.addOrReplaceCookie(Cookie)"})
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
   *   <li>Then return {@link RequestBuilderBase#cookies} size is two.</li>
   * </ul>
   * <p>
   * Method under test: {@link RequestBuilderBase#addOrReplaceCookie(Cookie)}
   */
  @Test
  @DisplayName("Test addOrReplaceCookie(Cookie); then return cookies size is two")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"RequestBuilderBase RequestBuilderBase.addOrReplaceCookie(Cookie)"})
  void testAddOrReplaceCookie_thenReturnCookiesSizeIsTwo() {
    // Arrange
    RequestBuilder requestBuilder = new RequestBuilder();
    DefaultCookie cookie = new DefaultCookie("Name", "https://example.org/example");

    requestBuilder.addCookie(cookie);
    DefaultCookie cookie2 = new DefaultCookie("https://example.org/example", "https://example.org/example");

    // Act and Assert
    ArrayList<Cookie> cookieList = requestBuilder.addOrReplaceCookie(cookie2).cookies;
    assertEquals(2, cookieList.size());
    Cookie getResult = cookieList.get(1);
    assertTrue(getResult instanceof DefaultCookie);
    ArrayList<Cookie> cookieList2 = requestBuilder.cookies;
    assertEquals(2, cookieList2.size());
    assertSame(cookie, cookieList.get(0));
    assertSame(cookie2, cookieList2.get(1));
    assertSame(cookie2, getResult);
  }

  /**
   * Test {@link RequestBuilderBase#resetQuery()}.
   * <p>
   * Method under test: {@link RequestBuilderBase#resetQuery()}
   */
  @Test
  @DisplayName("Test resetQuery()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void RequestBuilderBase.resetQuery()"})
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
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void RequestBuilderBase.resetQuery()"})
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
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void RequestBuilderBase.resetQuery()"})
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
   *   <li>Then {@link RequestBuilder#RequestBuilder()} {@link RequestBuilderBase#uri} toJavaNetURI toString is a string.</li>
   * </ul>
   * <p>
   * Method under test: {@link RequestBuilderBase#resetQuery()}
   */
  @Test
  @DisplayName("Test resetQuery(); then RequestBuilder() uri toJavaNetURI toString is a string")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void RequestBuilderBase.resetQuery()"})
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
   * Test {@link RequestBuilderBase#setBody(BodyGenerator)} with {@code BodyGenerator}.
   * <p>
   * Method under test: {@link RequestBuilderBase#setBody(BodyGenerator)}
   */
  @Test
  @DisplayName("Test setBody(BodyGenerator) with 'BodyGenerator'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"RequestBuilderBase RequestBuilderBase.setBody(BodyGenerator)"})
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
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"RequestBuilderBase RequestBuilderBase.setBody(byte[])"})
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
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"RequestBuilderBase RequestBuilderBase.setBody(ByteBuffer)"})
  void testSetBodyWithByteBuffer() throws UnsupportedEncodingException {
    // Arrange
    RequestBuilder requestBuilder = new RequestBuilder();

    // Act and Assert
    assertSame(requestBuilder, requestBuilder.setBody(ByteBuffer.wrap("AXAXAXAX".getBytes("UTF-8"))));
  }

  /**
   * Test {@link RequestBuilderBase#setBody(File)} with {@code File}.
   * <p>
   * Method under test: {@link RequestBuilderBase#setBody(File)}
   */
  @Test
  @DisplayName("Test setBody(File) with 'File'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"RequestBuilderBase RequestBuilderBase.setBody(File)"})
  void testSetBodyWithFile() {
    // Arrange
    RequestBuilder requestBuilder = new RequestBuilder();

    // Act and Assert
    assertSame(requestBuilder,
        requestBuilder.setBody(Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toFile()));
  }

  /**
   * Test {@link RequestBuilderBase#setBody(InputStream)} with {@code InputStream}.
   * <p>
   * Method under test: {@link RequestBuilderBase#setBody(InputStream)}
   */
  @Test
  @DisplayName("Test setBody(InputStream) with 'InputStream'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"RequestBuilderBase RequestBuilderBase.setBody(InputStream)"})
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
   *   <li>Then {@link RequestBuilder#RequestBuilder()} {@link RequestBuilderBase#compositeByteData} Empty.</li>
   * </ul>
   * <p>
   * Method under test: {@link RequestBuilderBase#setBody(List)}
   */
  @Test
  @DisplayName("Test setBody(List) with 'List'; when ArrayList(); then RequestBuilder() compositeByteData Empty")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"RequestBuilderBase RequestBuilderBase.setBody(List)"})
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
   *   <li>Then return {@link RequestBuilderBase#queryParams} first Name is {@code https://example.org/example}.</li>
   * </ul>
   * <p>
   * Method under test: {@link RequestBuilderBase#addQueryParams(List)}
   */
  @Test
  @DisplayName("Test addQueryParams(List); then return queryParams first Name is 'https://example.org/example'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"RequestBuilderBase RequestBuilderBase.addQueryParams(List)"})
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
   *   <li>Then {@link RequestBuilder#RequestBuilder()} {@link RequestBuilderBase#queryParams} Empty.</li>
   * </ul>
   * <p>
   * Method under test: {@link RequestBuilderBase#addQueryParams(List)}
   */
  @Test
  @DisplayName("Test addQueryParams(List); when ArrayList(); then RequestBuilder() queryParams Empty")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"RequestBuilderBase RequestBuilderBase.addQueryParams(List)"})
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
   *   <li>When {@link HashMap#HashMap()} {@code foo} is {@link ArrayList#ArrayList()}.</li>
   * </ul>
   * <p>
   * Method under test: {@link RequestBuilderBase#setQueryParams(Map)}
   */
  @Test
  @DisplayName("Test setQueryParams(Map) with 'map'; given 'foo'; when HashMap() 'foo' is ArrayList()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"RequestBuilderBase RequestBuilderBase.setQueryParams(Map)"})
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
   *   <li>Then {@link RequestBuilder#RequestBuilder()} {@link RequestBuilderBase#queryParams} is {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link RequestBuilderBase#setQueryParams(Map)}
   */
  @Test
  @DisplayName("Test setQueryParams(Map) with 'map'; then RequestBuilder() queryParams is 'null'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"RequestBuilderBase RequestBuilderBase.setQueryParams(Map)"})
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
   *   <li>Then {@link RequestBuilder#RequestBuilder()} {@link RequestBuilderBase#uri} toJavaNetURI toString is a string.</li>
   * </ul>
   * <p>
   * Method under test: {@link RequestBuilderBase#setQueryParams(Map)}
   */
  @Test
  @DisplayName("Test setQueryParams(Map) with 'map'; then RequestBuilder() uri toJavaNetURI toString is a string")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"RequestBuilderBase RequestBuilderBase.setQueryParams(Map)"})
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
   *   <li>Then return {@link RequestBuilderBase#uri} NonEmptyPath is {@code /example}.</li>
   * </ul>
   * <p>
   * Method under test: {@link RequestBuilderBase#setQueryParams(Map)}
   */
  @Test
  @DisplayName("Test setQueryParams(Map) with 'map'; then return uri NonEmptyPath is '/example'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"RequestBuilderBase RequestBuilderBase.setQueryParams(Map)"})
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
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"RequestBuilderBase RequestBuilderBase.setQueryParams(Map)"})
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
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"RequestBuilderBase RequestBuilderBase.setQueryParams(Map)"})
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
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"RequestBuilderBase RequestBuilderBase.setQueryParams(Map)"})
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
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"RequestBuilderBase RequestBuilderBase.setQueryParams(Map)"})
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
   *   <li>Then {@link RequestBuilder#RequestBuilder()} {@link RequestBuilderBase#queryParams} Empty.</li>
   * </ul>
   * <p>
   * Method under test: {@link RequestBuilderBase#setQueryParams(Map)}
   */
  @Test
  @DisplayName("Test setQueryParams(Map) with 'map'; when HashMap(); then RequestBuilder() queryParams Empty")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"RequestBuilderBase RequestBuilderBase.setQueryParams(Map)"})
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
   *   <li>Then {@link RequestBuilder#RequestBuilder()} {@link RequestBuilderBase#queryParams} Empty.</li>
   * </ul>
   * <p>
   * Method under test: {@link RequestBuilderBase#setQueryParams(List)}
   */
  @Test
  @DisplayName("Test setQueryParams(List) with 'params'; then RequestBuilder() queryParams Empty")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"RequestBuilderBase RequestBuilderBase.setQueryParams(List)"})
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
   *   <li>Then {@link RequestBuilder#RequestBuilder()} {@link RequestBuilderBase#uri} toJavaNetURI toString is a string.</li>
   * </ul>
   * <p>
   * Method under test: {@link RequestBuilderBase#setQueryParams(List)}
   */
  @Test
  @DisplayName("Test setQueryParams(List) with 'params'; then RequestBuilder() uri toJavaNetURI toString is a string")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"RequestBuilderBase RequestBuilderBase.setQueryParams(List)"})
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
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"RequestBuilderBase RequestBuilderBase.setQueryParams(List)"})
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
   *   <li>Then return {@link RequestBuilderBase#uri} SchemeDefaultPort is eighty.</li>
   * </ul>
   * <p>
   * Method under test: {@link RequestBuilderBase#setQueryParams(List)}
   */
  @Test
  @DisplayName("Test setQueryParams(List) with 'params'; then return uri SchemeDefaultPort is eighty")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"RequestBuilderBase RequestBuilderBase.setQueryParams(List)"})
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
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"RequestBuilderBase RequestBuilderBase.setQueryParams(List)"})
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
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"RequestBuilderBase RequestBuilderBase.setQueryParams(List)"})
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
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"RequestBuilderBase RequestBuilderBase.setQueryParams(List)"})
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
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"RequestBuilderBase RequestBuilderBase.setFormParams(Map)"})
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
   *   <li>Then {@link RequestBuilder#RequestBuilder()} {@link RequestBuilderBase#formParams} Empty.</li>
   * </ul>
   * <p>
   * Method under test: {@link RequestBuilderBase#setFormParams(Map)}
   */
  @Test
  @DisplayName("Test setFormParams(Map) with 'map'; given ArrayList(); then RequestBuilder() formParams Empty")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"RequestBuilderBase RequestBuilderBase.setFormParams(Map)"})
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
   *   <li>Then {@link RequestBuilder#RequestBuilder()} {@link RequestBuilderBase#formParams} Empty.</li>
   * </ul>
   * <p>
   * Method under test: {@link RequestBuilderBase#setFormParams(Map)}
   */
  @Test
  @DisplayName("Test setFormParams(Map) with 'map'; when HashMap(); then RequestBuilder() formParams Empty")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"RequestBuilderBase RequestBuilderBase.setFormParams(Map)"})
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
   *   <li>Then {@link RequestBuilder#RequestBuilder()} {@link RequestBuilderBase#formParams} is {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link RequestBuilderBase#setFormParams(Map)}
   */
  @Test
  @DisplayName("Test setFormParams(Map) with 'map'; when 'null'; then RequestBuilder() formParams is 'null'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"RequestBuilderBase RequestBuilderBase.setFormParams(Map)"})
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
   *   <li>Then {@link RequestBuilder#RequestBuilder()} {@link RequestBuilderBase#formParams} Empty.</li>
   * </ul>
   * <p>
   * Method under test: {@link RequestBuilderBase#setFormParams(List)}
   */
  @Test
  @DisplayName("Test setFormParams(List) with 'params'; when ArrayList(); then RequestBuilder() formParams Empty")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"RequestBuilderBase RequestBuilderBase.setFormParams(List)"})
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
   *   <li>Then {@link ArrayList#ArrayList()} size is one.</li>
   * </ul>
   * <p>
   * Method under test: {@link RequestBuilderBase#setBodyParts(List)}
   */
  @Test
  @DisplayName("Test setBodyParts(List); then ArrayList() size is one")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"RequestBuilderBase RequestBuilderBase.setBodyParts(List)"})
  void testSetBodyParts_thenArrayListSizeIsOne() throws UnsupportedEncodingException {
    // Arrange
    RequestBuilder requestBuilder = new RequestBuilder();

    ArrayList<Part> bodyParts = new ArrayList<>();
    bodyParts.add(new ByteArrayPart("https://example.org/example", "AXAXAXAX".getBytes("UTF-8")));

    // Act
    RequestBuilder actualSetBodyPartsResult = requestBuilder.setBodyParts(bodyParts);

    // Assert
    assertEquals(1, bodyParts.size());
    List<Part> partList = actualSetBodyPartsResult.bodyParts;
    assertEquals(1, partList.size());
    Part getResult = partList.get(0);
    assertTrue(getResult instanceof ByteArrayPart);
    assertEquals(1, requestBuilder.bodyParts.size());
    byte[] expectedBytes = "AXAXAXAX".getBytes("UTF-8");
    assertArrayEquals(expectedBytes, ((ByteArrayPart) getResult).getBytes());
  }

  /**
   * Test {@link RequestBuilderBase#setBodyParts(List)}.
   * <ul>
   *   <li>Then return {@link RequestBuilderBase#bodyParts} size is two.</li>
   * </ul>
   * <p>
   * Method under test: {@link RequestBuilderBase#setBodyParts(List)}
   */
  @Test
  @DisplayName("Test setBodyParts(List); then return bodyParts size is two")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"RequestBuilderBase RequestBuilderBase.setBodyParts(List)"})
  void testSetBodyParts_thenReturnBodyPartsSizeIsTwo() throws UnsupportedEncodingException {
    // Arrange
    RequestBuilder requestBuilder = new RequestBuilder();

    ArrayList<Part> bodyParts = new ArrayList<>();
    bodyParts.add(new ByteArrayPart("https://example.org/example", "AXAXAXAX".getBytes("UTF-8")));
    ByteArrayPart byteArrayPart = new ByteArrayPart("https://example.org/example", "AXAXAXAX".getBytes("UTF-8"));

    bodyParts.add(byteArrayPart);

    // Act and Assert
    List<Part> partList = requestBuilder.setBodyParts(bodyParts).bodyParts;
    assertEquals(2, partList.size());
    Part getResult = partList.get(0);
    assertTrue(getResult instanceof ByteArrayPart);
    List<Part> partList2 = requestBuilder.bodyParts;
    assertEquals(2, partList2.size());
    assertSame(byteArrayPart, partList2.get(1));
    assertSame(byteArrayPart, partList.get(1));
    byte[] expectedBytes = "AXAXAXAX".getBytes("UTF-8");
    assertArrayEquals(expectedBytes, ((ByteArrayPart) getResult).getBytes());
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
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"RequestBuilderBase RequestBuilderBase.setBodyParts(List)"})
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
   * Test {@link RequestBuilderBase#setProxyServer(Builder)} with {@code proxyServerBuilder}.
   * <p>
   * Method under test: {@link RequestBuilderBase#setProxyServer(ProxyServer.Builder)}
   */
  @Test
  @DisplayName("Test setProxyServer(Builder) with 'proxyServerBuilder'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"RequestBuilderBase RequestBuilderBase.setProxyServer(ProxyServer.Builder)"})
  void testSetProxyServerWithProxyServerBuilder() {
    // Arrange
    RequestBuilder requestBuilder = new RequestBuilder();

    // Act and Assert
    assertSame(requestBuilder, requestBuilder.setProxyServer(Dsl.proxyServer("https://example.org/example", 8080)));
  }

  /**
   * Test {@link RequestBuilderBase#setProxyServer(Builder)} with {@code proxyServerBuilder}.
   * <p>
   * Method under test: {@link RequestBuilderBase#setProxyServer(ProxyServer.Builder)}
   */
  @Test
  @DisplayName("Test setProxyServer(Builder) with 'proxyServerBuilder'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"RequestBuilderBase RequestBuilderBase.setProxyServer(ProxyServer.Builder)"})
  void testSetProxyServerWithProxyServerBuilder2() {
    // Arrange
    RequestBuilder requestBuilder = new RequestBuilder();
    ProxyServer.Builder proxyServerBuilder = Dsl.proxyServer("https://example.org/example", 8080);
    proxyServerBuilder.setNonProxyHost(null);
    proxyServerBuilder.setProxyType(null);

    // Act and Assert
    assertSame(requestBuilder, requestBuilder.setProxyServer(proxyServerBuilder));
  }

  /**
   * Test {@link RequestBuilderBase#setProxyServer(Builder)} with {@code proxyServerBuilder}.
   * <ul>
   *   <li>Given {@code HTTP}.</li>
   * </ul>
   * <p>
   * Method under test: {@link RequestBuilderBase#setProxyServer(ProxyServer.Builder)}
   */
  @Test
  @DisplayName("Test setProxyServer(Builder) with 'proxyServerBuilder'; given 'HTTP'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"RequestBuilderBase RequestBuilderBase.setProxyServer(ProxyServer.Builder)"})
  void testSetProxyServerWithProxyServerBuilder_givenHttp() {
    // Arrange
    RequestBuilder requestBuilder = new RequestBuilder();
    ProxyServer.Builder proxyServerBuilder = Dsl.proxyServer("https://example.org/example", 8080);
    proxyServerBuilder.setNonProxyHost(null);
    proxyServerBuilder.setProxyType(ProxyType.HTTP);

    // Act and Assert
    assertSame(requestBuilder, requestBuilder.setProxyServer(proxyServerBuilder));
  }

  /**
   * Test {@link RequestBuilderBase#setProxyServer(ProxyServer)} with {@code proxyServer}.
   * <ul>
   *   <li>Then return {@link RequestBuilder#RequestBuilder()}.</li>
   * </ul>
   * <p>
   * Method under test: {@link RequestBuilderBase#setProxyServer(ProxyServer)}
   */
  @Test
  @DisplayName("Test setProxyServer(ProxyServer) with 'proxyServer'; then return RequestBuilder()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"RequestBuilderBase RequestBuilderBase.setProxyServer(ProxyServer)"})
  void testSetProxyServerWithProxyServer_thenReturnRequestBuilder() {
    // Arrange
    RequestBuilder requestBuilder = new RequestBuilder();
    Builder setAlgorithmResult = (new Builder()).setAlgorithm("https://example.org/example");
    Builder setCharsetResult = setAlgorithmResult.setCharset(Charset.forName("UTF-8"));
    Builder setServicePrincipalNameResult = setCharsetResult.setCustomLoginConfig(new HashMap<>())
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
        .setScheme(AuthScheme.BASIC)
        .setServicePrincipalName("https://example.org/example");
    Realm realm = setServicePrincipalNameResult
        .setUri(new Uri("https://example.org/example", "https://example.org/example", "https://example.org/example",
            8080, "https://example.org/example", "https://example.org/example", "https://example.org/example"))
        .setUseAbsoluteURI(true)
        .setUseCanonicalHostname(true)
        .setUsePreemptiveAuth(true)
        .build();

    // Act and Assert
    assertSame(requestBuilder, requestBuilder.setProxyServer(
        new ProxyServer("https://example.org/example", 8080, 8080, realm, new ArrayList<>(), ProxyType.HTTP)));
  }

  /**
   * Test {@link RequestBuilderBase#setRealm(Builder)} with {@code Builder}.
   * <ul>
   *   <li>Given {@code BASIC}.</li>
   *   <li>Then return {@link RequestBuilder#RequestBuilder()}.</li>
   * </ul>
   * <p>
   * Method under test: {@link RequestBuilderBase#setRealm(Builder)}
   */
  @Test
  @DisplayName("Test setRealm(Builder) with 'Builder'; given 'BASIC'; then return RequestBuilder()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"RequestBuilderBase RequestBuilderBase.setRealm(Builder)"})
  void testSetRealmWithBuilder_givenBasic_thenReturnRequestBuilder() {
    // Arrange
    RequestBuilder requestBuilder = new RequestBuilder();

    Builder realm = new Builder();
    realm.setScheme(AuthScheme.BASIC);

    // Act and Assert
    assertSame(requestBuilder, requestBuilder.setRealm(realm));
  }

  /**
   * Test {@link RequestBuilderBase#setRealm(Realm)} with {@code Realm}.
   * <p>
   * Method under test: {@link RequestBuilderBase#setRealm(Realm)}
   */
  @Test
  @DisplayName("Test setRealm(Realm) with 'Realm'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"RequestBuilderBase RequestBuilderBase.setRealm(Realm)"})
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
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"RequestBuilderBase RequestBuilderBase.setRequestTimeout(Duration)"})
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
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"RequestBuilderBase RequestBuilderBase.setReadTimeout(Duration)"})
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
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"RequestBuilderBase RequestBuilderBase.setCharset(Charset)"})
  void testSetCharset() {
    // Arrange
    RequestBuilder requestBuilder = new RequestBuilder();

    // Act and Assert
    assertSame(requestBuilder, requestBuilder.setCharset(Charset.forName("UTF-8")));
  }

  /**
   * Test {@link RequestBuilderBase#setChannelPoolPartitioning(ChannelPoolPartitioning)}.
   * <p>
   * Method under test: {@link RequestBuilderBase#setChannelPoolPartitioning(ChannelPoolPartitioning)}
   */
  @Test
  @DisplayName("Test setChannelPoolPartitioning(ChannelPoolPartitioning)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"RequestBuilderBase RequestBuilderBase.setChannelPoolPartitioning(ChannelPoolPartitioning)"})
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
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"RequestBuilderBase RequestBuilderBase.setNameResolver(NameResolver)"})
  void testSetNameResolver() {
    // Arrange
    RequestBuilder requestBuilder = new RequestBuilder();

    // Act and Assert
    assertSame(requestBuilder, requestBuilder.setNameResolver(RequestBuilderBase.DEFAULT_NAME_RESOLVER));
  }

  /**
   * Test {@link RequestBuilderBase#setSignatureCalculator(SignatureCalculator)}.
   * <p>
   * Method under test: {@link RequestBuilderBase#setSignatureCalculator(SignatureCalculator)}
   */
  @Test
  @DisplayName("Test setSignatureCalculator(SignatureCalculator)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"RequestBuilderBase RequestBuilderBase.setSignatureCalculator(SignatureCalculator)"})
  void testSetSignatureCalculator() {
    // Arrange
    RequestBuilder requestBuilder = new RequestBuilder();

    // Act and Assert
    assertSame(requestBuilder, requestBuilder.setSignatureCalculator(mock(SignatureCalculator.class)));
  }
}
