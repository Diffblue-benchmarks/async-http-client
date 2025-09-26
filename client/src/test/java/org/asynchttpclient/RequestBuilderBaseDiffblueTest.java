package org.asynchttpclient;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import io.netty.buffer.AdaptiveByteBufAllocator;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.DuplicatedByteBuf;
import io.netty.buffer.EmptyByteBuf;
import io.netty.handler.codec.DefaultHeadersImpl;
import io.netty.handler.codec.Headers;
import io.netty.handler.codec.http.DefaultCookie;
import io.netty.handler.codec.http.DefaultHttpHeaders;
import io.netty.handler.codec.http.HttpHeaders;
import io.netty.handler.codec.http.cookie.Cookie;
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
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import org.asynchttpclient.Realm.AuthScheme;
import org.asynchttpclient.channel.ChannelPoolPartitioning;
import org.asynchttpclient.config.AsyncHttpClientConfigDefaults;
import org.asynchttpclient.proxy.ProxyServer;
import org.asynchttpclient.proxy.ProxyServer.Builder;
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
   *
   * <ul>
   *   <li>When {@code http://localhost}.
   *   <li>Then {@link RequestBuilder#RequestBuilder()} {@link RequestBuilderBase#uri} Path is empty
   *       string.
   * </ul>
   *
   * <p>Method under test: {@link RequestBuilderBase#setUrl(String)}
   */
  @Test
  @DisplayName(
      "Test setUrl(String); when 'http://localhost'; then RequestBuilder() uri Path is empty string")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
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
   *
   * <ul>
   *   <li>When {@code https://example.org/example}.
   *   <li>Then {@link RequestBuilder#RequestBuilder()} {@link RequestBuilderBase#uri} NonEmptyPath
   *       is {@code /example}.
   * </ul>
   *
   * <p>Method under test: {@link RequestBuilderBase#setUrl(String)}
   */
  @Test
  @DisplayName(
      "Test setUrl(String); when 'https://example.org/example'; then RequestBuilder() uri NonEmptyPath is '/example'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
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
   *
   * <p>Method under test: {@link RequestBuilderBase#setUri(Uri)}
   */
  @Test
  @DisplayName("Test setUri(Uri)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"RequestBuilderBase RequestBuilderBase.setUri(Uri)"})
  void testSetUri() {
    // Arrange
    RequestBuilder requestBuilder = new RequestBuilder();
    Uri uri =
        new Uri(
            "https://example.org/example",
            "https://example.org/example",
            "https://example.org/example",
            8080,
            "https://example.org/example",
            "https://example.org/example",
            "https://example.org/example");

    // Act
    RequestBuilder actualSetUriResult = requestBuilder.setUri(uri);

    // Assert
    assertSame(requestBuilder, actualSetUriResult);
  }

  /**
   * Test {@link RequestBuilderBase#setAddress(InetAddress)}.
   *
   * <p>Method under test: {@link RequestBuilderBase#setAddress(InetAddress)}
   */
  @Test
  @DisplayName("Test setAddress(InetAddress)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"RequestBuilderBase RequestBuilderBase.setAddress(InetAddress)"})
  void testSetAddress() {
    // Arrange
    RequestBuilder requestBuilder = new RequestBuilder();

    // Act
    RequestBuilder actualSetAddressResult = requestBuilder.setAddress(mock(InetAddress.class));

    // Assert
    assertSame(requestBuilder, actualSetAddressResult);
  }

  /**
   * Test {@link RequestBuilderBase#setLocalAddress(InetAddress)}.
   *
   * <p>Method under test: {@link RequestBuilderBase#setLocalAddress(InetAddress)}
   */
  @Test
  @DisplayName("Test setLocalAddress(InetAddress)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"RequestBuilderBase RequestBuilderBase.setLocalAddress(InetAddress)"})
  void testSetLocalAddress() {
    // Arrange
    RequestBuilder requestBuilder = new RequestBuilder();

    // Act
    RequestBuilder actualSetLocalAddressResult =
        requestBuilder.setLocalAddress(mock(InetAddress.class));

    // Assert
    assertSame(requestBuilder, actualSetLocalAddressResult);
  }

  /**
   * Test {@link RequestBuilderBase#setVirtualHost(String)}.
   *
   * <p>Method under test: {@link RequestBuilderBase#setVirtualHost(String)}
   */
  @Test
  @DisplayName("Test setVirtualHost(String)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"RequestBuilderBase RequestBuilderBase.setVirtualHost(String)"})
  void testSetVirtualHost() {
    // Arrange
    RequestBuilder requestBuilder = new RequestBuilder();

    // Act
    RequestBuilder actualSetVirtualHostResult =
        requestBuilder.setVirtualHost("https://example.org/example");

    // Assert
    assertEquals("https://example.org/example", requestBuilder.virtualHost);
    assertSame(requestBuilder, actualSetVirtualHostResult);
  }

  /**
   * Test {@link RequestBuilderBase#clearHeaders()}.
   *
   * <p>Method under test: {@link RequestBuilderBase#clearHeaders()}
   */
  @Test
  @DisplayName("Test clearHeaders()")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"RequestBuilderBase RequestBuilderBase.clearHeaders()"})
  void testClearHeaders() {
    // Arrange
    RequestBuilder requestBuilder = new RequestBuilder();

    // Act
    RequestBuilder actualClearHeadersResult = requestBuilder.clearHeaders();

    // Assert
    assertSame(requestBuilder, actualClearHeadersResult);
  }

  /**
   * Test {@link RequestBuilderBase#setHeader(CharSequence, Iterable)} with {@code CharSequence},
   * {@code Iterable}.
   *
   * <p>Method under test: {@link RequestBuilderBase#setHeader(CharSequence, Iterable)}
   */
  @Test
  @DisplayName("Test setHeader(CharSequence, Iterable) with 'CharSequence', 'Iterable'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"RequestBuilderBase RequestBuilderBase.setHeader(CharSequence, Iterable)"})
  void testSetHeaderWithCharSequenceIterable() {
    // Arrange
    RequestBuilder requestBuilder = new RequestBuilder();
    requestBuilder.addHeader(
        AsyncHttpClientConfigDefaults.ACQUIRE_FREE_CHANNEL_TIMEOUT, "https://example.org/example");

    // Act and Assert
    HttpHeaders httpHeaders =
        requestBuilder.setHeader(
                AsyncHttpClientConfigDefaults.ACQUIRE_FREE_CHANNEL_TIMEOUT,
                (Iterable<?>) new ArrayList<>())
            .headers;
    Headers<CharSequence, CharSequence, ?> unwrapResult =
        ((DefaultHttpHeaders) httpHeaders).unwrap();
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
   * Test {@link RequestBuilderBase#setHeader(CharSequence, Iterable)} with {@code CharSequence},
   * {@code Iterable}.
   *
   * <p>Method under test: {@link RequestBuilderBase#setHeader(CharSequence, Iterable)}
   */
  @Test
  @DisplayName("Test setHeader(CharSequence, Iterable) with 'CharSequence', 'Iterable'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"RequestBuilderBase RequestBuilderBase.setHeader(CharSequence, Iterable)"})
  void testSetHeaderWithCharSequenceIterable2() {
    // Arrange
    RequestBuilder requestBuilder = new RequestBuilder();
    requestBuilder.addHeader(AsyncHttpClientConfigDefaults.ACQUIRE_FREE_CHANNEL_TIMEOUT, "42");
    requestBuilder.addHeader(
        AsyncHttpClientConfigDefaults.ACQUIRE_FREE_CHANNEL_TIMEOUT, "https://example.org/example");

    // Act and Assert
    HttpHeaders httpHeaders =
        requestBuilder.setHeader(
                AsyncHttpClientConfigDefaults.ACQUIRE_FREE_CHANNEL_TIMEOUT,
                (Iterable<?>) new ArrayList<>())
            .headers;
    Headers<CharSequence, CharSequence, ?> unwrapResult =
        ((DefaultHttpHeaders) httpHeaders).unwrap();
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
   * Test {@link RequestBuilderBase#setHeader(CharSequence, Iterable)} with {@code CharSequence},
   * {@code Iterable}.
   *
   * <ul>
   *   <li>Given {@link RequestBuilder#RequestBuilder()}.
   * </ul>
   *
   * <p>Method under test: {@link RequestBuilderBase#setHeader(CharSequence, Iterable)}
   */
  @Test
  @DisplayName(
      "Test setHeader(CharSequence, Iterable) with 'CharSequence', 'Iterable'; given RequestBuilder()")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"RequestBuilderBase RequestBuilderBase.setHeader(CharSequence, Iterable)"})
  void testSetHeaderWithCharSequenceIterable_givenRequestBuilder() {
    // Arrange
    RequestBuilder requestBuilder = new RequestBuilder();

    // Act and Assert
    HttpHeaders httpHeaders =
        requestBuilder.setHeader(
                AsyncHttpClientConfigDefaults.ACQUIRE_FREE_CHANNEL_TIMEOUT,
                (Iterable<?>) new ArrayList<>())
            .headers;
    Headers<CharSequence, CharSequence, ?> unwrapResult =
        ((DefaultHttpHeaders) httpHeaders).unwrap();
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
   * Test {@link RequestBuilderBase#setHeader(CharSequence, Object)} with {@code CharSequence},
   * {@code Object}.
   *
   * <ul>
   *   <li>Then return {@link RequestBuilderBase#headers} unwrap size is three.
   * </ul>
   *
   * <p>Method under test: {@link RequestBuilderBase#setHeader(CharSequence, Object)}
   */
  @Test
  @DisplayName(
      "Test setHeader(CharSequence, Object) with 'CharSequence', 'Object'; then return headers unwrap size is three")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"RequestBuilderBase RequestBuilderBase.setHeader(CharSequence, Object)"})
  void testSetHeaderWithCharSequenceObject_thenReturnHeadersUnwrapSizeIsThree() {
    // Arrange
    RequestBuilder requestBuilder = new RequestBuilder();
    requestBuilder.addHeader(AsyncHttpClientConfigDefaults.ACQUIRE_FREE_CHANNEL_TIMEOUT, "Value");
    requestBuilder.addHeader(AsyncHttpClientConfigDefaults.ACQUIRE_FREE_CHANNEL_TIMEOUT, "42");

    // Act and Assert
    HttpHeaders httpHeaders =
        requestBuilder.setHeader(AsciiString.cached("String"), (Object) "Value").headers;
    Headers<CharSequence, CharSequence, ?> unwrapResult =
        ((DefaultHttpHeaders) httpHeaders).unwrap();
    assertTrue(unwrapResult instanceof DefaultHeadersImpl);
    HttpHeaders httpHeaders2 = requestBuilder.headers;
    assertTrue(httpHeaders2 instanceof DefaultHttpHeaders);
    assertTrue(httpHeaders instanceof DefaultHttpHeaders);
    assertEquals(3, unwrapResult.size());
    assertEquals(3, httpHeaders2.size());
    assertEquals(3, httpHeaders.size());
    assertTrue(unwrapResult.iterator().hasNext());
  }

  /**
   * Test {@link RequestBuilderBase#setHeader(CharSequence, String)} with {@code CharSequence},
   * {@code String}.
   *
   * <ul>
   *   <li>Then return {@link RequestBuilderBase#headers} unwrap size is three.
   * </ul>
   *
   * <p>Method under test: {@link RequestBuilderBase#setHeader(CharSequence, String)}
   */
  @Test
  @DisplayName(
      "Test setHeader(CharSequence, String) with 'CharSequence', 'String'; then return headers unwrap size is three")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"RequestBuilderBase RequestBuilderBase.setHeader(CharSequence, String)"})
  void testSetHeaderWithCharSequenceString_thenReturnHeadersUnwrapSizeIsThree() {
    // Arrange
    RequestBuilder requestBuilder = new RequestBuilder();
    requestBuilder.addHeader(AsyncHttpClientConfigDefaults.ACQUIRE_FREE_CHANNEL_TIMEOUT, "Value");
    requestBuilder.addHeader(AsyncHttpClientConfigDefaults.ACQUIRE_FREE_CHANNEL_TIMEOUT, "42");

    // Act and Assert
    HttpHeaders httpHeaders =
        requestBuilder.setHeader(AsciiString.cached("String"), "https://example.org/example")
            .headers;
    Headers<CharSequence, CharSequence, ?> unwrapResult =
        ((DefaultHttpHeaders) httpHeaders).unwrap();
    assertTrue(unwrapResult instanceof DefaultHeadersImpl);
    HttpHeaders httpHeaders2 = requestBuilder.headers;
    assertTrue(httpHeaders2 instanceof DefaultHttpHeaders);
    assertTrue(httpHeaders instanceof DefaultHttpHeaders);
    assertEquals(3, unwrapResult.size());
    assertEquals(3, httpHeaders2.size());
    assertEquals(3, httpHeaders.size());
    assertTrue(unwrapResult.iterator().hasNext());
  }

  /**
   * Test {@link RequestBuilderBase#addHeader(CharSequence, Iterable)} with {@code CharSequence},
   * {@code Iterable}.
   *
   * <ul>
   *   <li>Then return {@link RequestBuilderBase#headers} size is two.
   * </ul>
   *
   * <p>Method under test: {@link RequestBuilderBase#addHeader(CharSequence, Iterable)}
   */
  @Test
  @DisplayName(
      "Test addHeader(CharSequence, Iterable) with 'CharSequence', 'Iterable'; then return headers size is two")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"RequestBuilderBase RequestBuilderBase.addHeader(CharSequence, Iterable)"})
  void testAddHeaderWithCharSequenceIterable_thenReturnHeadersSizeIsTwo() {
    // Arrange
    RequestBuilder requestBuilder = new RequestBuilder();
    AsciiString name = AsciiString.cached("String");

    ArrayList<Object> values = new ArrayList<>();
    values.add("42");
    values.add("42");

    // Act and Assert
    HttpHeaders httpHeaders = requestBuilder.addHeader(name, (Iterable<?>) values).headers;
    assertTrue(httpHeaders instanceof DefaultHttpHeaders);
    assertEquals(2, httpHeaders.size());
    Headers<CharSequence, CharSequence, ?> unwrapResult =
        ((DefaultHttpHeaders) httpHeaders).unwrap();
    assertTrue(unwrapResult instanceof DefaultHeadersImpl);
    Iterator<Entry<CharSequence, CharSequence>> iteratorResult = unwrapResult.iterator();
    Entry<CharSequence, CharSequence> expectedNextResult = iteratorResult.next();
    Entry<CharSequence, CharSequence> actualNextResult = iteratorResult.next();
    assertFalse(iteratorResult.hasNext());
    assertEquals(expectedNextResult, actualNextResult);
    assertEquals(2, unwrapResult.size());
    HttpHeaders httpHeaders2 = requestBuilder.headers;
    assertTrue(httpHeaders2 instanceof DefaultHttpHeaders);
    assertEquals(2, httpHeaders2.size());
  }

  /**
   * Test {@link RequestBuilderBase#addHeader(CharSequence, Iterable)} with {@code CharSequence},
   * {@code Iterable}.
   *
   * <ul>
   *   <li>Then return {@link RequestBuilderBase#headers} unwrap size is zero.
   * </ul>
   *
   * <p>Method under test: {@link RequestBuilderBase#addHeader(CharSequence, Iterable)}
   */
  @Test
  @DisplayName(
      "Test addHeader(CharSequence, Iterable) with 'CharSequence', 'Iterable'; then return headers unwrap size is zero")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"RequestBuilderBase RequestBuilderBase.addHeader(CharSequence, Iterable)"})
  void testAddHeaderWithCharSequenceIterable_thenReturnHeadersUnwrapSizeIsZero() {
    // Arrange
    RequestBuilder requestBuilder = new RequestBuilder();

    // Act and Assert
    HttpHeaders httpHeaders =
        requestBuilder.addHeader(
                AsyncHttpClientConfigDefaults.ACQUIRE_FREE_CHANNEL_TIMEOUT,
                (Iterable<?>) new ArrayList<>())
            .headers;
    Headers<CharSequence, CharSequence, ?> unwrapResult =
        ((DefaultHttpHeaders) httpHeaders).unwrap();
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
   * Test {@link RequestBuilderBase#addHeader(CharSequence, Object)} with {@code CharSequence},
   * {@code Object}.
   *
   * <ul>
   *   <li>When {@link AsyncHttpClientConfigDefaults#ACQUIRE_FREE_CHANNEL_TIMEOUT}.
   * </ul>
   *
   * <p>Method under test: {@link RequestBuilderBase#addHeader(CharSequence, Object)}
   */
  @Test
  @DisplayName(
      "Test addHeader(CharSequence, Object) with 'CharSequence', 'Object'; when ACQUIRE_FREE_CHANNEL_TIMEOUT")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"RequestBuilderBase RequestBuilderBase.addHeader(CharSequence, Object)"})
  void testAddHeaderWithCharSequenceObject_whenAcquire_free_channel_timeout() {
    // Arrange
    RequestBuilder requestBuilder = new RequestBuilder();

    // Act
    RequestBuilder actualAddHeaderResult =
        requestBuilder.addHeader(
            AsyncHttpClientConfigDefaults.ACQUIRE_FREE_CHANNEL_TIMEOUT, (Object) "Value");

    // Assert
    assertSame(requestBuilder, actualAddHeaderResult);
  }

  /**
   * Test {@link RequestBuilderBase#addHeader(CharSequence, Object)} with {@code CharSequence},
   * {@code Object}.
   *
   * <ul>
   *   <li>When {@link AsyncHttpClientConfigDefaults#ACQUIRE_FREE_CHANNEL_TIMEOUT}.
   * </ul>
   *
   * <p>Method under test: {@link RequestBuilderBase#addHeader(CharSequence, Object)}
   */
  @Test
  @DisplayName(
      "Test addHeader(CharSequence, Object) with 'CharSequence', 'Object'; when ACQUIRE_FREE_CHANNEL_TIMEOUT")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"RequestBuilderBase RequestBuilderBase.addHeader(CharSequence, Object)"})
  void testAddHeaderWithCharSequenceObject_whenAcquire_free_channel_timeout2() {
    // Arrange
    RequestBuilder requestBuilder = new RequestBuilder();

    // Act
    RequestBuilder actualAddHeaderResult =
        requestBuilder.addHeader(
            AsyncHttpClientConfigDefaults.ACQUIRE_FREE_CHANNEL_TIMEOUT, (Object) null);

    // Assert
    assertSame(requestBuilder, actualAddHeaderResult);
  }

  /**
   * Test {@link RequestBuilderBase#addHeader(CharSequence, Object)} with {@code CharSequence},
   * {@code Object}.
   *
   * <ul>
   *   <li>When cached {@code String}.
   *   <li>Then return {@link RequestBuilder#RequestBuilder()}.
   * </ul>
   *
   * <p>Method under test: {@link RequestBuilderBase#addHeader(CharSequence, Object)}
   */
  @Test
  @DisplayName(
      "Test addHeader(CharSequence, Object) with 'CharSequence', 'Object'; when cached 'String'; then return RequestBuilder()")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"RequestBuilderBase RequestBuilderBase.addHeader(CharSequence, Object)"})
  void testAddHeaderWithCharSequenceObject_whenCachedString_thenReturnRequestBuilder() {
    // Arrange
    RequestBuilder requestBuilder = new RequestBuilder();

    // Act
    RequestBuilder actualAddHeaderResult =
        requestBuilder.addHeader(AsciiString.cached("String"), (Object) "Value");

    // Assert
    assertSame(requestBuilder, actualAddHeaderResult);
  }

  /**
   * Test {@link RequestBuilderBase#addHeader(CharSequence, String)} with {@code CharSequence},
   * {@code String}.
   *
   * <ul>
   *   <li>When {@link AsyncHttpClientConfigDefaults#ACQUIRE_FREE_CHANNEL_TIMEOUT}.
   * </ul>
   *
   * <p>Method under test: {@link RequestBuilderBase#addHeader(CharSequence, String)}
   */
  @Test
  @DisplayName(
      "Test addHeader(CharSequence, String) with 'CharSequence', 'String'; when ACQUIRE_FREE_CHANNEL_TIMEOUT")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"RequestBuilderBase RequestBuilderBase.addHeader(CharSequence, String)"})
  void testAddHeaderWithCharSequenceString_whenAcquire_free_channel_timeout() {
    // Arrange
    RequestBuilder requestBuilder = new RequestBuilder();

    // Act
    RequestBuilder actualAddHeaderResult =
        requestBuilder.addHeader(
            AsyncHttpClientConfigDefaults.ACQUIRE_FREE_CHANNEL_TIMEOUT,
            "https://example.org/example");

    // Assert
    assertSame(requestBuilder, actualAddHeaderResult);
  }

  /**
   * Test {@link RequestBuilderBase#addHeader(CharSequence, String)} with {@code CharSequence},
   * {@code String}.
   *
   * <ul>
   *   <li>When {@link AsyncHttpClientConfigDefaults#ACQUIRE_FREE_CHANNEL_TIMEOUT}.
   * </ul>
   *
   * <p>Method under test: {@link RequestBuilderBase#addHeader(CharSequence, String)}
   */
  @Test
  @DisplayName(
      "Test addHeader(CharSequence, String) with 'CharSequence', 'String'; when ACQUIRE_FREE_CHANNEL_TIMEOUT")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"RequestBuilderBase RequestBuilderBase.addHeader(CharSequence, String)"})
  void testAddHeaderWithCharSequenceString_whenAcquire_free_channel_timeout2() {
    // Arrange
    RequestBuilder requestBuilder = new RequestBuilder();

    // Act
    RequestBuilder actualAddHeaderResult =
        requestBuilder.addHeader(
            AsyncHttpClientConfigDefaults.ACQUIRE_FREE_CHANNEL_TIMEOUT, (String) null);

    // Assert
    assertSame(requestBuilder, actualAddHeaderResult);
  }

  /**
   * Test {@link RequestBuilderBase#addHeader(CharSequence, String)} with {@code CharSequence},
   * {@code String}.
   *
   * <ul>
   *   <li>When cached {@code String}.
   *   <li>Then return {@link RequestBuilder#RequestBuilder()}.
   * </ul>
   *
   * <p>Method under test: {@link RequestBuilderBase#addHeader(CharSequence, String)}
   */
  @Test
  @DisplayName(
      "Test addHeader(CharSequence, String) with 'CharSequence', 'String'; when cached 'String'; then return RequestBuilder()")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"RequestBuilderBase RequestBuilderBase.addHeader(CharSequence, String)"})
  void testAddHeaderWithCharSequenceString_whenCachedString_thenReturnRequestBuilder() {
    // Arrange
    RequestBuilder requestBuilder = new RequestBuilder();

    // Act
    RequestBuilder actualAddHeaderResult =
        requestBuilder.addHeader(AsciiString.cached("String"), "https://example.org/example");

    // Assert
    assertSame(requestBuilder, actualAddHeaderResult);
  }

  /**
   * Test {@link RequestBuilderBase#setHeaders(HttpHeaders)} with {@code HttpHeaders}.
   *
   * <ul>
   *   <li>When {@link DefaultHttpHeaders#DefaultHttpHeaders()}.
   * </ul>
   *
   * <p>Method under test: {@link RequestBuilderBase#setHeaders(HttpHeaders)}
   */
  @Test
  @DisplayName("Test setHeaders(HttpHeaders) with 'HttpHeaders'; when DefaultHttpHeaders()")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"RequestBuilderBase RequestBuilderBase.setHeaders(HttpHeaders)"})
  void testSetHeadersWithHttpHeaders_whenDefaultHttpHeaders() {
    // Arrange
    RequestBuilder requestBuilder = new RequestBuilder();

    // Act
    RequestBuilder actualSetHeadersResult = requestBuilder.setHeaders(new DefaultHttpHeaders());

    // Assert
    assertSame(requestBuilder, actualSetHeadersResult);
  }

  /**
   * Test {@link RequestBuilderBase#setHeaders(HttpHeaders)} with {@code HttpHeaders}.
   *
   * <ul>
   *   <li>When {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link RequestBuilderBase#setHeaders(HttpHeaders)}
   */
  @Test
  @DisplayName("Test setHeaders(HttpHeaders) with 'HttpHeaders'; when 'null'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"RequestBuilderBase RequestBuilderBase.setHeaders(HttpHeaders)"})
  void testSetHeadersWithHttpHeaders_whenNull() {
    // Arrange
    RequestBuilder requestBuilder = new RequestBuilder();

    // Act
    RequestBuilder actualSetHeadersResult = requestBuilder.setHeaders((HttpHeaders) null);

    // Assert
    assertSame(requestBuilder, actualSetHeadersResult);
  }

  /**
   * Test {@link RequestBuilderBase#setHeaders(Map)} with {@code Map}.
   *
   * <ul>
   *   <li>Given {@link ArrayList#ArrayList()}.
   *   <li>Then return {@link RequestBuilderBase#headers} unwrap size is zero.
   * </ul>
   *
   * <p>Method under test: {@link RequestBuilderBase#setHeaders(Map)}
   */
  @Test
  @DisplayName(
      "Test setHeaders(Map) with 'Map'; given ArrayList(); then return headers unwrap size is zero")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"RequestBuilderBase RequestBuilderBase.setHeaders(Map)"})
  void testSetHeadersWithMap_givenArrayList_thenReturnHeadersUnwrapSizeIsZero() {
    // Arrange
    RequestBuilder requestBuilder = new RequestBuilder();

    HashMap<CharSequence, Iterable<?>> headers = new HashMap<>();
    headers.put(AsyncHttpClientConfigDefaults.ACQUIRE_FREE_CHANNEL_TIMEOUT, new ArrayList<>());

    // Act and Assert
    HttpHeaders httpHeaders = requestBuilder.setHeaders(headers).headers;
    Headers<CharSequence, CharSequence, ?> unwrapResult =
        ((DefaultHttpHeaders) httpHeaders).unwrap();
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
   *
   * <ul>
   *   <li>When {@link HashMap#HashMap()}.
   *   <li>Then return {@link RequestBuilderBase#headers} unwrap size is zero.
   * </ul>
   *
   * <p>Method under test: {@link RequestBuilderBase#setHeaders(Map)}
   */
  @Test
  @DisplayName(
      "Test setHeaders(Map) with 'Map'; when HashMap(); then return headers unwrap size is zero")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"RequestBuilderBase RequestBuilderBase.setHeaders(Map)"})
  void testSetHeadersWithMap_whenHashMap_thenReturnHeadersUnwrapSizeIsZero() {
    // Arrange
    RequestBuilder requestBuilder = new RequestBuilder();

    // Act and Assert
    HttpHeaders httpHeaders = requestBuilder.setHeaders(new HashMap<>()).headers;
    Headers<CharSequence, CharSequence, ?> unwrapResult =
        ((DefaultHttpHeaders) httpHeaders).unwrap();
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
   *
   * <ul>
   *   <li>When {@code null}.
   *   <li>Then return {@link RequestBuilderBase#headers} unwrap size is zero.
   * </ul>
   *
   * <p>Method under test: {@link RequestBuilderBase#setHeaders(Map)}
   */
  @Test
  @DisplayName(
      "Test setHeaders(Map) with 'Map'; when 'null'; then return headers unwrap size is zero")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"RequestBuilderBase RequestBuilderBase.setHeaders(Map)"})
  void testSetHeadersWithMap_whenNull_thenReturnHeadersUnwrapSizeIsZero() {
    // Arrange
    RequestBuilder requestBuilder = new RequestBuilder();

    // Act and Assert
    HttpHeaders httpHeaders =
        requestBuilder.setHeaders((Map<? extends CharSequence, ? extends Iterable<?>>) null)
            .headers;
    Headers<CharSequence, CharSequence, ?> unwrapResult =
        ((DefaultHttpHeaders) httpHeaders).unwrap();
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
   *
   * <ul>
   *   <li>When {@link HashMap#HashMap()}.
   *   <li>Then return {@link RequestBuilderBase#headers} unwrap size is zero.
   * </ul>
   *
   * <p>Method under test: {@link RequestBuilderBase#setSingleHeaders(Map)}
   */
  @Test
  @DisplayName(
      "Test setSingleHeaders(Map); when HashMap(); then return headers unwrap size is zero")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"RequestBuilderBase RequestBuilderBase.setSingleHeaders(Map)"})
  void testSetSingleHeaders_whenHashMap_thenReturnHeadersUnwrapSizeIsZero() {
    // Arrange
    RequestBuilder requestBuilder = new RequestBuilder();

    // Act and Assert
    HttpHeaders httpHeaders = requestBuilder.setSingleHeaders(new HashMap<>()).headers;
    Headers<CharSequence, CharSequence, ?> unwrapResult =
        ((DefaultHttpHeaders) httpHeaders).unwrap();
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
   * Test {@link RequestBuilderBase#setSingleHeaders(Map)}.
   *
   * <ul>
   *   <li>When {@code null}.
   *   <li>Then return {@link RequestBuilderBase#headers} unwrap size is zero.
   * </ul>
   *
   * <p>Method under test: {@link RequestBuilderBase#setSingleHeaders(Map)}
   */
  @Test
  @DisplayName("Test setSingleHeaders(Map); when 'null'; then return headers unwrap size is zero")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"RequestBuilderBase RequestBuilderBase.setSingleHeaders(Map)"})
  void testSetSingleHeaders_whenNull_thenReturnHeadersUnwrapSizeIsZero() {
    // Arrange
    RequestBuilder requestBuilder = new RequestBuilder();

    // Act and Assert
    HttpHeaders httpHeaders = requestBuilder.setSingleHeaders(null).headers;
    Headers<CharSequence, CharSequence, ?> unwrapResult =
        ((DefaultHttpHeaders) httpHeaders).unwrap();
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
   * Test {@link RequestBuilderBase#setCookies(Collection)}.
   *
   * <ul>
   *   <li>Then {@link RequestBuilder#RequestBuilder()} {@link RequestBuilderBase#cookies} size is
   *       two.
   * </ul>
   *
   * <p>Method under test: {@link RequestBuilderBase#setCookies(Collection)}
   */
  @Test
  @DisplayName("Test setCookies(Collection); then RequestBuilder() cookies size is two")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"RequestBuilderBase RequestBuilderBase.setCookies(Collection)"})
  void testSetCookies_thenRequestBuilderCookiesSizeIsTwo() {
    // Arrange
    RequestBuilder requestBuilder = new RequestBuilder();

    ArrayList<Cookie> cookies = new ArrayList<>();
    cookies.add(new DefaultCookie("https://example.org/example", "https://example.org/example"));
    DefaultCookie defaultCookie =
        new DefaultCookie("https://example.org/example", "https://example.org/example");
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
   *
   * <ul>
   *   <li>Then return {@link RequestBuilderBase#cookies} size is one.
   * </ul>
   *
   * <p>Method under test: {@link RequestBuilderBase#setCookies(Collection)}
   */
  @Test
  @DisplayName("Test setCookies(Collection); then return cookies size is one")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"RequestBuilderBase RequestBuilderBase.setCookies(Collection)"})
  void testSetCookies_thenReturnCookiesSizeIsOne() {
    // Arrange
    RequestBuilder requestBuilder = new RequestBuilder();

    ArrayList<Cookie> cookies = new ArrayList<>();
    DefaultCookie defaultCookie =
        new DefaultCookie("https://example.org/example", "https://example.org/example");
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
   *
   * <ul>
   *   <li>When {@link ArrayList#ArrayList()}.
   *   <li>Then {@link ArrayList#ArrayList()} Empty.
   * </ul>
   *
   * <p>Method under test: {@link RequestBuilderBase#setCookies(Collection)}
   */
  @Test
  @DisplayName("Test setCookies(Collection); when ArrayList(); then ArrayList() Empty")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
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
   *
   * <ul>
   *   <li>Given {@link RequestBuilder#RequestBuilder()}.
   * </ul>
   *
   * <p>Method under test: {@link RequestBuilderBase#addCookie(Cookie)}
   */
  @Test
  @DisplayName("Test addCookie(Cookie); given RequestBuilder()")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"RequestBuilderBase RequestBuilderBase.addCookie(Cookie)"})
  void testAddCookie_givenRequestBuilder() {
    // Arrange
    RequestBuilder requestBuilder = new RequestBuilder();

    // Act
    RequestBuilder actualAddCookieResult =
        requestBuilder.addCookie(
            new DefaultCookie("https://example.org/example", "https://example.org/example"));

    // Assert
    assertEquals(1, requestBuilder.cookies.size());
    assertSame(requestBuilder, actualAddCookieResult);
  }

  /**
   * Test {@link RequestBuilderBase#addCookie(Cookie)}.
   *
   * <ul>
   *   <li>Given {@link RequestBuilder#RequestBuilder()} Cookies is {@link ArrayList#ArrayList()}.
   * </ul>
   *
   * <p>Method under test: {@link RequestBuilderBase#addCookie(Cookie)}
   */
  @Test
  @DisplayName("Test addCookie(Cookie); given RequestBuilder() Cookies is ArrayList()")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"RequestBuilderBase RequestBuilderBase.addCookie(Cookie)"})
  void testAddCookie_givenRequestBuilderCookiesIsArrayList() {
    // Arrange
    RequestBuilder requestBuilder = new RequestBuilder();
    requestBuilder.setCookies(new ArrayList<>());

    // Act
    RequestBuilder actualAddCookieResult =
        requestBuilder.addCookie(
            new DefaultCookie("https://example.org/example", "https://example.org/example"));

    // Assert
    assertEquals(1, requestBuilder.cookies.size());
    assertSame(requestBuilder, actualAddCookieResult);
  }

  /**
   * Test {@link RequestBuilderBase#addOrReplaceCookie(Cookie)}.
   *
   * <ul>
   *   <li>Given {@link RequestBuilder#RequestBuilder()}.
   *   <li>Then {@link RequestBuilder#RequestBuilder()} {@link RequestBuilderBase#cookies} size is
   *       one.
   * </ul>
   *
   * <p>Method under test: {@link RequestBuilderBase#addOrReplaceCookie(Cookie)}
   */
  @Test
  @DisplayName(
      "Test addOrReplaceCookie(Cookie); given RequestBuilder(); then RequestBuilder() cookies size is one")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"RequestBuilderBase RequestBuilderBase.addOrReplaceCookie(Cookie)"})
  void testAddOrReplaceCookie_givenRequestBuilder_thenRequestBuilderCookiesSizeIsOne() {
    // Arrange
    RequestBuilder requestBuilder = new RequestBuilder();
    DefaultCookie cookie =
        new DefaultCookie("https://example.org/example", "https://example.org/example");

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
   *
   * <ul>
   *   <li>Then {@link RequestBuilderBase#cookies} first return {@link DefaultCookie}.
   * </ul>
   *
   * <p>Method under test: {@link RequestBuilderBase#addOrReplaceCookie(Cookie)}
   */
  @Test
  @DisplayName("Test addOrReplaceCookie(Cookie); then cookies first return DefaultCookie")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"RequestBuilderBase RequestBuilderBase.addOrReplaceCookie(Cookie)"})
  void testAddOrReplaceCookie_thenCookiesFirstReturnDefaultCookie() {
    // Arrange
    LinkedHashSet<Cookie> cookies = new LinkedHashSet<>();
    DefaultCookie defaultCookie =
        new DefaultCookie("https://example.org/example", "https://example.org/example");
    cookies.add(defaultCookie);

    RequestBuilder requestBuilder = new RequestBuilder();
    requestBuilder.setCookies(cookies);
    DefaultCookie cookie =
        new DefaultCookie("https://example.org/example", "https://example.org/example");

    // Act and Assert
    ArrayList<Cookie> cookieList = requestBuilder.addOrReplaceCookie(cookie).cookies;
    assertEquals(1, cookieList.size());
    Cookie getResult = cookieList.get(0);
    assertTrue(getResult instanceof DefaultCookie);
    ArrayList<Cookie> cookieList2 = requestBuilder.cookies;
    assertEquals(1, cookieList2.size());
    assertEquals(defaultCookie, getResult);
    assertSame(cookie, cookieList2.get(0));
  }

  /**
   * Test {@link RequestBuilderBase#addOrReplaceCookie(Cookie)}.
   *
   * <ul>
   *   <li>Then return {@link RequestBuilderBase#cookies} size is two.
   * </ul>
   *
   * <p>Method under test: {@link RequestBuilderBase#addOrReplaceCookie(Cookie)}
   */
  @Test
  @DisplayName("Test addOrReplaceCookie(Cookie); then return cookies size is two")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"RequestBuilderBase RequestBuilderBase.addOrReplaceCookie(Cookie)"})
  void testAddOrReplaceCookie_thenReturnCookiesSizeIsTwo() {
    // Arrange
    RequestBuilder requestBuilder = new RequestBuilder();
    DefaultCookie cookie = new DefaultCookie("Name", "https://example.org/example");
    requestBuilder.addCookie(cookie);
    DefaultCookie cookie2 =
        new DefaultCookie("https://example.org/example", "https://example.org/example");

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
   *
   * <p>Method under test: {@link RequestBuilderBase#resetQuery()}
   */
  @Test
  @DisplayName("Test resetQuery()")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void RequestBuilderBase.resetQuery()"})
  void testResetQuery() throws URISyntaxException {
    // Arrange
    RequestBuilder requestBuilder = new RequestBuilder();
    Uri uri =
        new Uri(
            Uri.WSS,
            "https://example.org/example",
            "https://example.org/example",
            8080,
            "https://example.org/example",
            "https://example.org/example",
            "https://example.org/example");
    requestBuilder.setUri(uri);

    // Act
    requestBuilder.resetQuery();

    // Assert
    Uri uri2 = requestBuilder.uri;
    assertEquals(
        "wss://https://example.org/example@https://example.org/example:8080https://example.org/example",
        uri2.toJavaNetURI().toString());
    assertNull(uri2.getQuery());
  }

  /**
   * Test {@link RequestBuilderBase#resetQuery()}.
   *
   * <p>Method under test: {@link RequestBuilderBase#resetQuery()}
   */
  @Test
  @DisplayName("Test resetQuery()")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void RequestBuilderBase.resetQuery()"})
  void testResetQuery2() throws URISyntaxException {
    // Arrange
    RequestBuilder requestBuilder = new RequestBuilder();
    Uri uri =
        new Uri(
            Uri.WS,
            "https://example.org/example",
            "https://example.org/example",
            8080,
            "https://example.org/example",
            "https://example.org/example",
            "https://example.org/example");
    requestBuilder.setUri(uri);

    // Act
    requestBuilder.resetQuery();

    // Assert
    Uri uri2 = requestBuilder.uri;
    assertEquals(
        "ws://https://example.org/example@https://example.org/example:8080https://example.org/example",
        uri2.toJavaNetURI().toString());
    assertNull(uri2.getQuery());
  }

  /**
   * Test {@link RequestBuilderBase#resetQuery()}.
   *
   * <ul>
   *   <li>Then {@link RequestBuilder#RequestBuilder()} {@link RequestBuilderBase#uri} toJavaNetURI
   *       toString is a string.
   * </ul>
   *
   * <p>Method under test: {@link RequestBuilderBase#resetQuery()}
   */
  @Test
  @DisplayName("Test resetQuery(); then RequestBuilder() uri toJavaNetURI toString is a string")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void RequestBuilderBase.resetQuery()"})
  void testResetQuery_thenRequestBuilderUriToJavaNetURIToStringIsAString()
      throws URISyntaxException {
    // Arrange
    RequestBuilder requestBuilder = new RequestBuilder();
    Uri uri =
        new Uri(
            "https://example.org/example",
            "https://example.org/example",
            "https://example.org/example",
            8080,
            "https://example.org/example",
            "https://example.org/example",
            "https://example.org/example");
    requestBuilder.setUri(uri);

    // Act
    requestBuilder.resetQuery();

    // Assert
    Uri uri2 = requestBuilder.uri;
    assertEquals(
        "https://example.org/example://https://example.org/example@https://example.org/example:8080https:/"
            + "/example.org/example",
        uri2.toJavaNetURI().toString());
    assertNull(uri2.getQuery());
  }

  /**
   * Test {@link RequestBuilderBase#resetQuery()}.
   *
   * <ul>
   *   <li>Then {@link RequestBuilder#RequestBuilder()} {@link RequestBuilderBase#uri} toJavaNetURI
   *       toString is {@code https://example.org/example}.
   * </ul>
   *
   * <p>Method under test: {@link RequestBuilderBase#resetQuery()}
   */
  @Test
  @DisplayName(
      "Test resetQuery(); then RequestBuilder() uri toJavaNetURI toString is 'https://example.org/example'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void RequestBuilderBase.resetQuery()"})
  void testResetQuery_thenRequestBuilderUriToJavaNetURIToStringIsHttpsExampleOrgExample()
      throws URISyntaxException {
    // Arrange
    RequestBuilder requestBuilder = new RequestBuilder();
    Uri context =
        new Uri(
            "https://example.org/example",
            "https://example.org/example",
            "https://example.org/example",
            8080,
            "https://example.org/example",
            "https://example.org/example",
            "https://example.org/example");
    requestBuilder.setUri(Uri.create(context, "https://example.org/example"));

    // Act
    requestBuilder.resetQuery();

    // Assert that nothing has changed
    assertEquals("https://example.org/example", requestBuilder.uri.toJavaNetURI().toString());
  }

  /**
   * Test {@link RequestBuilderBase#setBody(BodyGenerator)} with {@code BodyGenerator}.
   *
   * <p>Method under test: {@link RequestBuilderBase#setBody(BodyGenerator)}
   */
  @Test
  @DisplayName("Test setBody(BodyGenerator) with 'BodyGenerator'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"RequestBuilderBase RequestBuilderBase.setBody(BodyGenerator)"})
  void testSetBodyWithBodyGenerator() {
    // Arrange
    RequestBuilder requestBuilder = new RequestBuilder();

    // Act
    RequestBuilder actualSetBodyResult = requestBuilder.setBody(mock(BodyGenerator.class));

    // Assert
    assertSame(requestBuilder, actualSetBodyResult);
  }

  /**
   * Test {@link RequestBuilderBase#setBody(byte[])} with {@code byte[]}.
   *
   * <p>Method under test: {@link RequestBuilderBase#setBody(byte[])}
   */
  @Test
  @DisplayName("Test setBody(byte[]) with 'byte[]'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"RequestBuilderBase RequestBuilderBase.setBody(byte[])"})
  void testSetBodyWithByte() throws UnsupportedEncodingException {
    // Arrange
    RequestBuilder requestBuilder = new RequestBuilder();

    // Act
    RequestBuilder actualSetBodyResult = requestBuilder.setBody("AXAXAXAX".getBytes("UTF-8"));

    // Assert
    assertSame(requestBuilder, actualSetBodyResult);
    assertArrayEquals("AXAXAXAX".getBytes("UTF-8"), requestBuilder.byteData);
  }

  /**
   * Test {@link RequestBuilderBase#setBody(ByteBuf)} with {@code ByteBuf}.
   *
   * <p>Method under test: {@link RequestBuilderBase#setBody(ByteBuf)}
   */
  @Test
  @DisplayName("Test setBody(ByteBuf) with 'ByteBuf'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"RequestBuilderBase RequestBuilderBase.setBody(ByteBuf)"})
  void testSetBodyWithByteBuf() {
    // Arrange
    RequestBuilder requestBuilder = new RequestBuilder();

    // Act
    RequestBuilder actualSetBodyResult =
        requestBuilder.setBody(
            new DuplicatedByteBuf(new EmptyByteBuf(new AdaptiveByteBufAllocator())));

    // Assert
    assertTrue(requestBuilder.byteBufData instanceof DuplicatedByteBuf);
    assertSame(requestBuilder, actualSetBodyResult);
  }

  /**
   * Test {@link RequestBuilderBase#setBody(ByteBuffer)} with {@code ByteBuffer}.
   *
   * <p>Method under test: {@link RequestBuilderBase#setBody(ByteBuffer)}
   */
  @Test
  @DisplayName("Test setBody(ByteBuffer) with 'ByteBuffer'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"RequestBuilderBase RequestBuilderBase.setBody(ByteBuffer)"})
  void testSetBodyWithByteBuffer() throws UnsupportedEncodingException {
    // Arrange
    RequestBuilder requestBuilder = new RequestBuilder();

    // Act
    RequestBuilder actualSetBodyResult =
        requestBuilder.setBody(ByteBuffer.wrap("AXAXAXAX".getBytes("UTF-8")));

    // Assert
    assertSame(requestBuilder, actualSetBodyResult);
  }

  /**
   * Test {@link RequestBuilderBase#setBody(File)} with {@code File}.
   *
   * <p>Method under test: {@link RequestBuilderBase#setBody(File)}
   */
  @Test
  @DisplayName("Test setBody(File) with 'File'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"RequestBuilderBase RequestBuilderBase.setBody(File)"})
  void testSetBodyWithFile() {
    // Arrange
    RequestBuilder requestBuilder = new RequestBuilder();

    // Act
    RequestBuilder actualSetBodyResult =
        requestBuilder.setBody(
            Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toFile());

    // Assert
    assertSame(requestBuilder, actualSetBodyResult);
  }

  /**
   * Test {@link RequestBuilderBase#setBody(InputStream)} with {@code InputStream}.
   *
   * <p>Method under test: {@link RequestBuilderBase#setBody(InputStream)}
   */
  @Test
  @DisplayName("Test setBody(InputStream) with 'InputStream'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"RequestBuilderBase RequestBuilderBase.setBody(InputStream)"})
  void testSetBodyWithInputStream() throws UnsupportedEncodingException {
    // Arrange
    RequestBuilder requestBuilder = new RequestBuilder();

    // Act
    RequestBuilder actualSetBodyResult =
        requestBuilder.setBody(new ByteArrayInputStream("AXAXAXAX".getBytes("UTF-8")));

    // Assert
    assertSame(requestBuilder, actualSetBodyResult);
  }

  /**
   * Test {@link RequestBuilderBase#setBody(List)} with {@code List}.
   *
   * <ul>
   *   <li>Then {@link RequestBuilder#RequestBuilder()} {@link RequestBuilderBase#compositeByteData}
   *       size is one.
   * </ul>
   *
   * <p>Method under test: {@link RequestBuilderBase#setBody(List)}
   */
  @Test
  @DisplayName(
      "Test setBody(List) with 'List'; then RequestBuilder() compositeByteData size is one")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"RequestBuilderBase RequestBuilderBase.setBody(List)"})
  void testSetBodyWithList_thenRequestBuilderCompositeByteDataSizeIsOne()
      throws UnsupportedEncodingException {
    // Arrange
    RequestBuilder requestBuilder = new RequestBuilder();

    ArrayList<byte[]> data = new ArrayList<>();
    data.add("AXAXAXAX".getBytes("UTF-8"));

    // Act
    RequestBuilder actualSetBodyResult = requestBuilder.setBody(data);

    // Assert
    assertEquals(1, requestBuilder.compositeByteData.size());
    assertSame(requestBuilder, actualSetBodyResult);
  }

  /**
   * Test {@link RequestBuilderBase#setBody(List)} with {@code List}.
   *
   * <ul>
   *   <li>Then {@link RequestBuilder#RequestBuilder()} {@link RequestBuilderBase#compositeByteData}
   *       size is two.
   * </ul>
   *
   * <p>Method under test: {@link RequestBuilderBase#setBody(List)}
   */
  @Test
  @DisplayName(
      "Test setBody(List) with 'List'; then RequestBuilder() compositeByteData size is two")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"RequestBuilderBase RequestBuilderBase.setBody(List)"})
  void testSetBodyWithList_thenRequestBuilderCompositeByteDataSizeIsTwo()
      throws UnsupportedEncodingException {
    // Arrange
    RequestBuilder requestBuilder = new RequestBuilder();

    ArrayList<byte[]> data = new ArrayList<>();
    data.add("AXAXAXAX".getBytes("UTF-8"));
    data.add("AXAXAXAX".getBytes("UTF-8"));

    // Act
    RequestBuilder actualSetBodyResult = requestBuilder.setBody(data);

    // Assert
    assertEquals(2, requestBuilder.compositeByteData.size());
    List<byte[]> byteArrayList = actualSetBodyResult.compositeByteData;
    assertEquals(2, byteArrayList.size());
    assertArrayEquals("AXAXAXAX".getBytes("UTF-8"), byteArrayList.get(0));
    assertArrayEquals("AXAXAXAX".getBytes("UTF-8"), byteArrayList.get(1));
  }

  /**
   * Test {@link RequestBuilderBase#setBody(List)} with {@code List}.
   *
   * <ul>
   *   <li>When {@link ArrayList#ArrayList()}.
   *   <li>Then {@link RequestBuilder#RequestBuilder()} {@link RequestBuilderBase#compositeByteData}
   *       Empty.
   * </ul>
   *
   * <p>Method under test: {@link RequestBuilderBase#setBody(List)}
   */
  @Test
  @DisplayName(
      "Test setBody(List) with 'List'; when ArrayList(); then RequestBuilder() compositeByteData Empty")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
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
   * Test {@link RequestBuilderBase#setBody(String)} with {@code String}.
   *
   * <p>Method under test: {@link RequestBuilderBase#setBody(String)}
   */
  @Test
  @DisplayName("Test setBody(String) with 'String'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"RequestBuilderBase RequestBuilderBase.setBody(String)"})
  void testSetBodyWithString() {
    // Arrange
    RequestBuilder requestBuilder = new RequestBuilder();

    // Act
    RequestBuilder actualSetBodyResult = requestBuilder.setBody("https://example.org/example");

    // Assert
    assertEquals("https://example.org/example", requestBuilder.stringData);
    assertSame(requestBuilder, actualSetBodyResult);
  }

  /**
   * Test {@link RequestBuilderBase#addQueryParam(String, String)}.
   *
   * <ul>
   *   <li>Given {@link RequestBuilder#RequestBuilder()}.
   *   <li>Then {@link RequestBuilder#RequestBuilder()} {@link RequestBuilderBase#queryParams} size
   *       is one.
   * </ul>
   *
   * <p>Method under test: {@link RequestBuilderBase#addQueryParam(String, String)}
   */
  @Test
  @DisplayName(
      "Test addQueryParam(String, String); given RequestBuilder(); then RequestBuilder() queryParams size is one")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"RequestBuilderBase RequestBuilderBase.addQueryParam(String, String)"})
  void testAddQueryParam_givenRequestBuilder_thenRequestBuilderQueryParamsSizeIsOne() {
    // Arrange
    RequestBuilder requestBuilder = new RequestBuilder();

    // Act
    RequestBuilder actualAddQueryParamResult =
        requestBuilder.addQueryParam("https://example.org/example", "https://example.org/example");

    // Assert
    assertEquals(1, requestBuilder.queryParams.size());
    assertSame(requestBuilder, actualAddQueryParamResult);
  }

  /**
   * Test {@link RequestBuilderBase#addQueryParam(String, String)}.
   *
   * <ul>
   *   <li>Then {@link RequestBuilder#RequestBuilder()} {@link RequestBuilderBase#queryParams} size
   *       is two.
   * </ul>
   *
   * <p>Method under test: {@link RequestBuilderBase#addQueryParam(String, String)}
   */
  @Test
  @DisplayName("Test addQueryParam(String, String); then RequestBuilder() queryParams size is two")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"RequestBuilderBase RequestBuilderBase.addQueryParam(String, String)"})
  void testAddQueryParam_thenRequestBuilderQueryParamsSizeIsTwo() {
    // Arrange
    RequestBuilder requestBuilder = new RequestBuilder();
    requestBuilder.addQueryParam("https://example.org/example", "https://example.org/example");

    // Act
    RequestBuilder actualAddQueryParamResult =
        requestBuilder.addQueryParam("https://example.org/example", "https://example.org/example");

    // Assert
    assertEquals(2, requestBuilder.queryParams.size());
    assertSame(requestBuilder, actualAddQueryParamResult);
  }

  /**
   * Test {@link RequestBuilderBase#addQueryParams(List)}.
   *
   * <ul>
   *   <li>Given {@link RequestBuilder#RequestBuilder()}.
   *   <li>Then {@link RequestBuilder#RequestBuilder()} {@link RequestBuilderBase#queryParams}
   *       Empty.
   * </ul>
   *
   * <p>Method under test: {@link RequestBuilderBase#addQueryParams(List)}
   */
  @Test
  @DisplayName(
      "Test addQueryParams(List); given RequestBuilder(); then RequestBuilder() queryParams Empty")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"RequestBuilderBase RequestBuilderBase.addQueryParams(List)"})
  void testAddQueryParams_givenRequestBuilder_thenRequestBuilderQueryParamsEmpty() {
    // Arrange
    RequestBuilder requestBuilder = new RequestBuilder();

    // Act
    RequestBuilder actualAddQueryParamsResult = requestBuilder.addQueryParams(new ArrayList<>());

    // Assert
    assertTrue(requestBuilder.queryParams.isEmpty());
    assertSame(requestBuilder, actualAddQueryParamsResult);
  }

  /**
   * Test {@link RequestBuilderBase#addQueryParams(List)}.
   *
   * <ul>
   *   <li>Then {@link RequestBuilder#RequestBuilder()} {@link RequestBuilderBase#queryParams} size
   *       is three.
   * </ul>
   *
   * <p>Method under test: {@link RequestBuilderBase#addQueryParams(List)}
   */
  @Test
  @DisplayName("Test addQueryParams(List); then RequestBuilder() queryParams size is three")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"RequestBuilderBase RequestBuilderBase.addQueryParams(List)"})
  void testAddQueryParams_thenRequestBuilderQueryParamsSizeIsThree() {
    // Arrange
    RequestBuilder requestBuilder = new RequestBuilder();
    requestBuilder.addQueryParam("https://example.org/example", "https://example.org/example");

    ArrayList<Param> params = new ArrayList<>();
    params.add(new Param("https://example.org/example", "https://example.org/example"));
    Param param = new Param("https://example.org/example", "https://example.org/example");
    params.add(param);

    // Act
    RequestBuilder actualAddQueryParamsResult = requestBuilder.addQueryParams(params);

    // Assert
    List<Param> paramList = requestBuilder.queryParams;
    assertEquals(3, paramList.size());
    List<Param> paramList2 = actualAddQueryParamsResult.queryParams;
    assertEquals(3, paramList2.size());
    assertSame(param, paramList.get(2));
    assertSame(param, paramList2.get(2));
  }

  /**
   * Test {@link RequestBuilderBase#addQueryParams(List)}.
   *
   * <ul>
   *   <li>Then {@link RequestBuilder#RequestBuilder()} {@link RequestBuilderBase#queryParams} size
   *       is two.
   * </ul>
   *
   * <p>Method under test: {@link RequestBuilderBase#addQueryParams(List)}
   */
  @Test
  @DisplayName("Test addQueryParams(List); then RequestBuilder() queryParams size is two")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"RequestBuilderBase RequestBuilderBase.addQueryParams(List)"})
  void testAddQueryParams_thenRequestBuilderQueryParamsSizeIsTwo() {
    // Arrange
    RequestBuilder requestBuilder = new RequestBuilder();
    requestBuilder.addQueryParam("https://example.org/example", "https://example.org/example");

    ArrayList<Param> params = new ArrayList<>();
    Param param = new Param("https://example.org/example", "https://example.org/example");
    params.add(param);

    // Act
    RequestBuilder actualAddQueryParamsResult = requestBuilder.addQueryParams(params);

    // Assert
    List<Param> paramList = requestBuilder.queryParams;
    assertEquals(2, paramList.size());
    List<Param> paramList2 = actualAddQueryParamsResult.queryParams;
    assertEquals(2, paramList2.size());
    assertEquals(param, paramList2.get(0));
    assertSame(param, paramList.get(1));
    assertSame(param, paramList2.get(1));
  }

  /**
   * Test {@link RequestBuilderBase#addQueryParams(List)}.
   *
   * <ul>
   *   <li>When {@link ArrayList#ArrayList()}.
   *   <li>Then return {@link RequestBuilderBase#queryParams} size is one.
   * </ul>
   *
   * <p>Method under test: {@link RequestBuilderBase#addQueryParams(List)}
   */
  @Test
  @DisplayName("Test addQueryParams(List); when ArrayList(); then return queryParams size is one")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"RequestBuilderBase RequestBuilderBase.addQueryParams(List)"})
  void testAddQueryParams_whenArrayList_thenReturnQueryParamsSizeIsOne() {
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
   * Test {@link RequestBuilderBase#setQueryParams(Map)} with {@code map}.
   *
   * <ul>
   *   <li>Given {@link ArrayList#ArrayList()} add {@code foo}.
   *   <li>Then return {@link RequestBuilderBase#queryParams} size is one.
   * </ul>
   *
   * <p>Method under test: {@link RequestBuilderBase#setQueryParams(Map)}
   */
  @Test
  @DisplayName(
      "Test setQueryParams(Map) with 'map'; given ArrayList() add 'foo'; then return queryParams size is one")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"RequestBuilderBase RequestBuilderBase.setQueryParams(Map)"})
  void testSetQueryParamsWithMap_givenArrayListAddFoo_thenReturnQueryParamsSizeIsOne() {
    // Arrange
    RequestBuilder requestBuilder = new RequestBuilder();

    ArrayList<String> stringList = new ArrayList<>();
    stringList.add("foo");

    HashMap<String, List<String>> map = new HashMap<>();
    map.put("foo", stringList);

    // Act and Assert
    List<Param> paramList = requestBuilder.setQueryParams(map).queryParams;
    assertEquals(1, paramList.size());
    Param getResult = paramList.get(0);
    assertEquals("foo", getResult.getName());
    assertEquals("foo", getResult.getValue());
    assertEquals(1, requestBuilder.queryParams.size());
  }

  /**
   * Test {@link RequestBuilderBase#setQueryParams(Map)} with {@code map}.
   *
   * <ul>
   *   <li>Given {@link ArrayList#ArrayList()}.
   *   <li>Then {@link RequestBuilder#RequestBuilder()} {@link RequestBuilderBase#queryParams}
   *       Empty.
   * </ul>
   *
   * <p>Method under test: {@link RequestBuilderBase#setQueryParams(Map)}
   */
  @Test
  @DisplayName(
      "Test setQueryParams(Map) with 'map'; given ArrayList(); then RequestBuilder() queryParams Empty")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"RequestBuilderBase RequestBuilderBase.setQueryParams(Map)"})
  void testSetQueryParamsWithMap_givenArrayList_thenRequestBuilderQueryParamsEmpty() {
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
   *
   * <ul>
   *   <li>Then {@link RequestBuilder#RequestBuilder()} {@link RequestBuilderBase#queryParams} is
   *       {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link RequestBuilderBase#setQueryParams(Map)}
   */
  @Test
  @DisplayName("Test setQueryParams(Map) with 'map'; then RequestBuilder() queryParams is 'null'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"RequestBuilderBase RequestBuilderBase.setQueryParams(Map)"})
  void testSetQueryParamsWithMap_thenRequestBuilderQueryParamsIsNull() {
    // Arrange
    RequestBuilder requestBuilder = new RequestBuilder();
    requestBuilder.setUri(null);

    // Act
    RequestBuilder actualSetQueryParamsResult =
        requestBuilder.setQueryParams((Map<String, List<String>>) null);

    // Assert
    assertNull(requestBuilder.queryParams);
    assertSame(requestBuilder, actualSetQueryParamsResult);
  }

  /**
   * Test {@link RequestBuilderBase#setQueryParams(Map)} with {@code map}.
   *
   * <ul>
   *   <li>Then {@link RequestBuilder#RequestBuilder()} {@link RequestBuilderBase#uri} Query is
   *       {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link RequestBuilderBase#setQueryParams(Map)}
   */
  @Test
  @DisplayName("Test setQueryParams(Map) with 'map'; then RequestBuilder() uri Query is 'null'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"RequestBuilderBase RequestBuilderBase.setQueryParams(Map)"})
  void testSetQueryParamsWithMap_thenRequestBuilderUriQueryIsNull() {
    // Arrange
    RequestBuilder requestBuilder = new RequestBuilder();
    Uri uri =
        new Uri(
            "https://example.org/example",
            "https://example.org/example",
            "https://example.org/example",
            8080,
            "https://example.org/example",
            "https://example.org/example",
            "https://example.org/example");
    requestBuilder.setUri(uri);

    // Act and Assert
    Uri uri2 = requestBuilder.setQueryParams((Map<String, List<String>>) null).uri;
    assertEquals("https://example.org/example", uri2.getFragment());
    assertEquals("https://example.org/example", uri2.getHost());
    assertEquals("https://example.org/example", uri2.getNonEmptyPath());
    assertEquals("https://example.org/example", uri2.getPath());
    assertEquals("https://example.org/example", uri2.getScheme());
    assertEquals("https://example.org/example", uri2.getUserInfo());
    assertEquals(
        "https://example.org/example://https://example.org/example:8080", uri2.getBaseUrl());
    assertEquals("https://example.org/example:8080", uri2.getAuthority());
    assertNull(requestBuilder.uri.getQuery());
    assertNull(uri2.getQuery());
    assertEquals(80, uri2.getSchemeDefaultPort());
    assertEquals(8080, uri2.getExplicitPort());
    assertEquals(8080, uri2.getPort());
    assertFalse(uri2.isSecured());
  }

  /**
   * Test {@link RequestBuilderBase#setQueryParams(Map)} with {@code map}.
   *
   * <ul>
   *   <li>Then return {@link RequestBuilderBase#uri} NonEmptyPath is {@code /example}.
   * </ul>
   *
   * <p>Method under test: {@link RequestBuilderBase#setQueryParams(Map)}
   */
  @Test
  @DisplayName("Test setQueryParams(Map) with 'map'; then return uri NonEmptyPath is '/example'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"RequestBuilderBase RequestBuilderBase.setQueryParams(Map)"})
  void testSetQueryParamsWithMap_thenReturnUriNonEmptyPathIsExample() {
    // Arrange
    RequestBuilder requestBuilder = new RequestBuilder();
    Uri context =
        new Uri(
            "https://example.org/example",
            "https://example.org/example",
            "https://example.org/example",
            8080,
            "https://example.org/example",
            "https://example.org/example",
            "https://example.org/example");
    requestBuilder.setUri(Uri.create(context, "https://example.org/example"));

    // Act and Assert
    Uri uri = requestBuilder.setQueryParams((Map<String, List<String>>) null).uri;
    assertEquals("/example", uri.getNonEmptyPath());
    assertEquals("/example", uri.getPath());
    assertEquals("example.org", uri.getHost());
    assertEquals("example.org:443", uri.getAuthority());
    assertEquals("https", uri.getScheme());
    assertEquals("https://example.org:443", uri.getBaseUrl());
    assertNull(uri.getFragment());
    assertNull(uri.getUserInfo());
    assertEquals(-1, uri.getPort());
    assertEquals(443, uri.getExplicitPort());
    assertEquals(443, uri.getSchemeDefaultPort());
    assertTrue(uri.isSecured());
  }

  /**
   * Test {@link RequestBuilderBase#setQueryParams(Map)} with {@code map}.
   *
   * <ul>
   *   <li>Then return {@link RequestBuilderBase#uri} Query is empty string.
   * </ul>
   *
   * <p>Method under test: {@link RequestBuilderBase#setQueryParams(Map)}
   */
  @Test
  @DisplayName("Test setQueryParams(Map) with 'map'; then return uri Query is empty string")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"RequestBuilderBase RequestBuilderBase.setQueryParams(Map)"})
  void testSetQueryParamsWithMap_thenReturnUriQueryIsEmptyString() {
    // Arrange
    RequestBuilder requestBuilder = new RequestBuilder();
    Uri uri =
        new Uri(
            "https://example.org/example",
            "https://example.org/example",
            "https://example.org/example",
            8080,
            "https://example.org/example",
            "",
            "https://example.org/example");
    requestBuilder.setUri(uri);

    // Act and Assert
    Uri uri2 = requestBuilder.setQueryParams((Map<String, List<String>>) null).uri;
    assertEquals("", uri2.getQuery());
    assertEquals("https://example.org/example", uri2.getFragment());
    assertEquals("https://example.org/example", uri2.getHost());
    assertEquals("https://example.org/example", uri2.getNonEmptyPath());
    assertEquals("https://example.org/example", uri2.getPath());
    assertEquals("https://example.org/example", uri2.getScheme());
    assertEquals("https://example.org/example", uri2.getUserInfo());
    assertEquals(
        "https://example.org/example://https://example.org/example:8080", uri2.getBaseUrl());
    assertEquals("https://example.org/example:8080", uri2.getAuthority());
    assertEquals(80, uri2.getSchemeDefaultPort());
    assertEquals(8080, uri2.getExplicitPort());
    assertEquals(8080, uri2.getPort());
    assertFalse(uri2.isSecured());
  }

  /**
   * Test {@link RequestBuilderBase#setQueryParams(Map)} with {@code map}.
   *
   * <ul>
   *   <li>When {@link HashMap#HashMap()}.
   *   <li>Then {@link RequestBuilder#RequestBuilder()} {@link RequestBuilderBase#queryParams}
   *       Empty.
   * </ul>
   *
   * <p>Method under test: {@link RequestBuilderBase#setQueryParams(Map)}
   */
  @Test
  @DisplayName(
      "Test setQueryParams(Map) with 'map'; when HashMap(); then RequestBuilder() queryParams Empty")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
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
   *
   * <p>Method under test: {@link RequestBuilderBase#setQueryParams(List)}
   */
  @Test
  @DisplayName("Test setQueryParams(List) with 'params'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"RequestBuilderBase RequestBuilderBase.setQueryParams(List)"})
  void testSetQueryParamsWithParams() {
    // Arrange
    RequestBuilder requestBuilder = new RequestBuilder();
    Uri uri =
        new Uri(
            "https://example.org/example",
            "https://example.org/example",
            "https://example.org/example",
            8080,
            "https://example.org/example",
            null,
            "https://example.org/example");
    requestBuilder.setUri(uri);

    // Act and Assert
    Uri uri2 = requestBuilder.setQueryParams(new ArrayList<>()).uri;
    assertEquals("https://example.org/example", uri2.getFragment());
    assertEquals("https://example.org/example", uri2.getHost());
    assertEquals("https://example.org/example", uri2.getNonEmptyPath());
    assertEquals("https://example.org/example", uri2.getPath());
    assertEquals("https://example.org/example", uri2.getScheme());
    assertEquals("https://example.org/example", uri2.getUserInfo());
    assertEquals(
        "https://example.org/example://https://example.org/example:8080", uri2.getBaseUrl());
    assertEquals("https://example.org/example:8080", uri2.getAuthority());
    assertNull(uri2.getQuery());
    assertEquals(80, uri2.getSchemeDefaultPort());
    assertEquals(8080, uri2.getExplicitPort());
    assertEquals(8080, uri2.getPort());
    assertFalse(uri2.isSecured());
    assertFalse(uri2.isWebSocket());
  }

  /**
   * Test {@link RequestBuilderBase#setQueryParams(List)} with {@code params}.
   *
   * <ul>
   *   <li>Then {@link RequestBuilder#RequestBuilder()} {@link RequestBuilderBase#queryParams}
   *       Empty.
   * </ul>
   *
   * <p>Method under test: {@link RequestBuilderBase#setQueryParams(List)}
   */
  @Test
  @DisplayName("Test setQueryParams(List) with 'params'; then RequestBuilder() queryParams Empty")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
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
   *
   * <ul>
   *   <li>Then {@link RequestBuilder#RequestBuilder()} {@link RequestBuilderBase#queryParams} size
   *       is one.
   * </ul>
   *
   * <p>Method under test: {@link RequestBuilderBase#setQueryParams(List)}
   */
  @Test
  @DisplayName(
      "Test setQueryParams(List) with 'params'; then RequestBuilder() queryParams size is one")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"RequestBuilderBase RequestBuilderBase.setQueryParams(List)"})
  void testSetQueryParamsWithParams_thenRequestBuilderQueryParamsSizeIsOne() {
    // Arrange
    RequestBuilder requestBuilder = new RequestBuilder();

    ArrayList<Param> params = new ArrayList<>();
    Param param = new Param("https://example.org/example", "https://example.org/example");
    params.add(param);

    // Act
    RequestBuilder actualSetQueryParamsResult = requestBuilder.setQueryParams(params);

    // Assert
    List<Param> paramList = requestBuilder.queryParams;
    assertEquals(1, paramList.size());
    List<Param> paramList2 = actualSetQueryParamsResult.queryParams;
    assertEquals(1, paramList2.size());
    assertSame(param, paramList.get(0));
    assertSame(param, paramList2.get(0));
  }

  /**
   * Test {@link RequestBuilderBase#setQueryParams(List)} with {@code params}.
   *
   * <ul>
   *   <li>Then {@link RequestBuilder#RequestBuilder()} {@link RequestBuilderBase#queryParams} size
   *       is two.
   * </ul>
   *
   * <p>Method under test: {@link RequestBuilderBase#setQueryParams(List)}
   */
  @Test
  @DisplayName(
      "Test setQueryParams(List) with 'params'; then RequestBuilder() queryParams size is two")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"RequestBuilderBase RequestBuilderBase.setQueryParams(List)"})
  void testSetQueryParamsWithParams_thenRequestBuilderQueryParamsSizeIsTwo() {
    // Arrange
    RequestBuilder requestBuilder = new RequestBuilder();

    ArrayList<Param> params = new ArrayList<>();
    params.add(new Param("https://example.org/example", "https://example.org/example"));
    Param param = new Param("https://example.org/example", "https://example.org/example");
    params.add(param);

    // Act
    RequestBuilder actualSetQueryParamsResult = requestBuilder.setQueryParams(params);

    // Assert
    List<Param> paramList = requestBuilder.queryParams;
    assertEquals(2, paramList.size());
    List<Param> paramList2 = actualSetQueryParamsResult.queryParams;
    assertEquals(2, paramList2.size());
    assertSame(param, paramList.get(1));
    assertSame(param, paramList2.get(1));
  }

  /**
   * Test {@link RequestBuilderBase#setQueryParams(List)} with {@code params}.
   *
   * <ul>
   *   <li>Then {@link RequestBuilder#RequestBuilder()} {@link RequestBuilderBase#uri} toJavaNetURI
   *       toString is a string.
   * </ul>
   *
   * <p>Method under test: {@link RequestBuilderBase#setQueryParams(List)}
   */
  @Test
  @DisplayName(
      "Test setQueryParams(List) with 'params'; then RequestBuilder() uri toJavaNetURI toString is a string")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"RequestBuilderBase RequestBuilderBase.setQueryParams(List)"})
  void testSetQueryParamsWithParams_thenRequestBuilderUriToJavaNetURIToStringIsAString()
      throws URISyntaxException {
    // Arrange
    RequestBuilder requestBuilder = new RequestBuilder();
    Uri uri =
        new Uri(
            "https://example.org/example",
            "https://example.org/example",
            "https://example.org/example",
            8080,
            "https://example.org/example",
            "https://example.org/example",
            "https://example.org/example");
    requestBuilder.setUri(uri);

    // Act
    RequestBuilder actualSetQueryParamsResult = requestBuilder.setQueryParams(new ArrayList<>());

    // Assert
    Uri uri2 = requestBuilder.uri;
    assertEquals(
        "https://example.org/example://https://example.org/example@https://example.org/example:8080https:/"
            + "/example.org/example",
        uri2.toJavaNetURI().toString());
    Uri uri3 = actualSetQueryParamsResult.uri;
    assertEquals(
        "https://example.org/example://https://example.org/example@https://example.org/example:8080https:/"
            + "/example.org/example",
        uri3.toJavaNetURI().toString());
    assertNull(uri2.getQuery());
    assertNull(uri3.getQuery());
  }

  /**
   * Test {@link RequestBuilderBase#setQueryParams(List)} with {@code params}.
   *
   * <ul>
   *   <li>Then return {@link RequestBuilderBase#uri} Query is empty string.
   * </ul>
   *
   * <p>Method under test: {@link RequestBuilderBase#setQueryParams(List)}
   */
  @Test
  @DisplayName("Test setQueryParams(List) with 'params'; then return uri Query is empty string")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"RequestBuilderBase RequestBuilderBase.setQueryParams(List)"})
  void testSetQueryParamsWithParams_thenReturnUriQueryIsEmptyString() {
    // Arrange
    RequestBuilder requestBuilder = new RequestBuilder();
    Uri uri =
        new Uri(
            "https://example.org/example",
            "https://example.org/example",
            "https://example.org/example",
            8080,
            "https://example.org/example",
            "",
            "https://example.org/example");
    requestBuilder.setUri(uri);

    // Act and Assert
    Uri uri2 = requestBuilder.setQueryParams(new ArrayList<>()).uri;
    assertEquals("", uri2.getQuery());
    assertEquals("https://example.org/example", uri2.getFragment());
    assertEquals("https://example.org/example", uri2.getHost());
    assertEquals("https://example.org/example", uri2.getNonEmptyPath());
    assertEquals("https://example.org/example", uri2.getPath());
    assertEquals("https://example.org/example", uri2.getScheme());
    assertEquals("https://example.org/example", uri2.getUserInfo());
    assertEquals(
        "https://example.org/example://https://example.org/example:8080", uri2.getBaseUrl());
    assertEquals("https://example.org/example:8080", uri2.getAuthority());
    assertEquals(80, uri2.getSchemeDefaultPort());
    assertEquals(8080, uri2.getExplicitPort());
    assertEquals(8080, uri2.getPort());
    assertFalse(uri2.isSecured());
    assertFalse(uri2.isWebSocket());
  }

  /**
   * Test {@link RequestBuilderBase#addFormParam(String, String)}.
   *
   * <ul>
   *   <li>Given {@link RequestBuilder#RequestBuilder()}.
   * </ul>
   *
   * <p>Method under test: {@link RequestBuilderBase#addFormParam(String, String)}
   */
  @Test
  @DisplayName("Test addFormParam(String, String); given RequestBuilder()")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"RequestBuilderBase RequestBuilderBase.addFormParam(String, String)"})
  void testAddFormParam_givenRequestBuilder() {
    // Arrange
    RequestBuilder requestBuilder = new RequestBuilder();

    // Act
    RequestBuilder actualAddFormParamResult =
        requestBuilder.addFormParam("https://example.org/example", "https://example.org/example");

    // Assert
    assertEquals(1, requestBuilder.formParams.size());
    assertSame(requestBuilder, actualAddFormParamResult);
  }

  /**
   * Test {@link RequestBuilderBase#addFormParam(String, String)}.
   *
   * <ul>
   *   <li>Given {@link RequestBuilder#RequestBuilder()} FormParams is {@link
   *       ArrayList#ArrayList()}.
   * </ul>
   *
   * <p>Method under test: {@link RequestBuilderBase#addFormParam(String, String)}
   */
  @Test
  @DisplayName(
      "Test addFormParam(String, String); given RequestBuilder() FormParams is ArrayList()")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"RequestBuilderBase RequestBuilderBase.addFormParam(String, String)"})
  void testAddFormParam_givenRequestBuilderFormParamsIsArrayList() {
    // Arrange
    RequestBuilder requestBuilder = new RequestBuilder();
    requestBuilder.setFormParams(new ArrayList<>());

    // Act
    RequestBuilder actualAddFormParamResult =
        requestBuilder.addFormParam("https://example.org/example", "https://example.org/example");

    // Assert
    assertEquals(1, requestBuilder.formParams.size());
    assertSame(requestBuilder, actualAddFormParamResult);
  }

  /**
   * Test {@link RequestBuilderBase#setFormParams(Map)} with {@code map}.
   *
   * <ul>
   *   <li>Given {@link ArrayList#ArrayList()} add {@code foo}.
   *   <li>Then return {@link RequestBuilderBase#formParams} size is one.
   * </ul>
   *
   * <p>Method under test: {@link RequestBuilderBase#setFormParams(Map)}
   */
  @Test
  @DisplayName(
      "Test setFormParams(Map) with 'map'; given ArrayList() add 'foo'; then return formParams size is one")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
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
   *
   * <ul>
   *   <li>Given {@link ArrayList#ArrayList()}.
   *   <li>Then {@link RequestBuilder#RequestBuilder()} {@link RequestBuilderBase#formParams} Empty.
   * </ul>
   *
   * <p>Method under test: {@link RequestBuilderBase#setFormParams(Map)}
   */
  @Test
  @DisplayName(
      "Test setFormParams(Map) with 'map'; given ArrayList(); then RequestBuilder() formParams Empty")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
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
   *
   * <ul>
   *   <li>When {@link HashMap#HashMap()}.
   *   <li>Then {@link RequestBuilder#RequestBuilder()} {@link RequestBuilderBase#formParams} Empty.
   * </ul>
   *
   * <p>Method under test: {@link RequestBuilderBase#setFormParams(Map)}
   */
  @Test
  @DisplayName(
      "Test setFormParams(Map) with 'map'; when HashMap(); then RequestBuilder() formParams Empty")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
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
   *
   * <ul>
   *   <li>When {@code null}.
   *   <li>Then {@link RequestBuilder#RequestBuilder()} {@link RequestBuilderBase#formParams} is
   *       {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link RequestBuilderBase#setFormParams(Map)}
   */
  @Test
  @DisplayName(
      "Test setFormParams(Map) with 'map'; when 'null'; then RequestBuilder() formParams is 'null'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"RequestBuilderBase RequestBuilderBase.setFormParams(Map)"})
  void testSetFormParamsWithMap_whenNull_thenRequestBuilderFormParamsIsNull() {
    // Arrange
    RequestBuilder requestBuilder = new RequestBuilder();

    // Act
    RequestBuilder actualSetFormParamsResult =
        requestBuilder.setFormParams((Map<String, List<String>>) null);

    // Assert
    assertNull(requestBuilder.formParams);
    assertSame(requestBuilder, actualSetFormParamsResult);
  }

  /**
   * Test {@link RequestBuilderBase#setFormParams(List)} with {@code params}.
   *
   * <ul>
   *   <li>Then {@link RequestBuilder#RequestBuilder()} {@link RequestBuilderBase#formParams} size
   *       is one.
   * </ul>
   *
   * <p>Method under test: {@link RequestBuilderBase#setFormParams(List)}
   */
  @Test
  @DisplayName(
      "Test setFormParams(List) with 'params'; then RequestBuilder() formParams size is one")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"RequestBuilderBase RequestBuilderBase.setFormParams(List)"})
  void testSetFormParamsWithParams_thenRequestBuilderFormParamsSizeIsOne() {
    // Arrange
    RequestBuilder requestBuilder = new RequestBuilder();

    ArrayList<Param> params = new ArrayList<>();
    Param param = new Param("https://example.org/example", "https://example.org/example");
    params.add(param);

    // Act
    RequestBuilder actualSetFormParamsResult = requestBuilder.setFormParams(params);

    // Assert
    List<Param> paramList = requestBuilder.formParams;
    assertEquals(1, paramList.size());
    List<Param> paramList2 = actualSetFormParamsResult.formParams;
    assertEquals(1, paramList2.size());
    assertSame(param, paramList.get(0));
    assertSame(param, paramList2.get(0));
  }

  /**
   * Test {@link RequestBuilderBase#setFormParams(List)} with {@code params}.
   *
   * <ul>
   *   <li>Then {@link RequestBuilder#RequestBuilder()} {@link RequestBuilderBase#formParams} size
   *       is two.
   * </ul>
   *
   * <p>Method under test: {@link RequestBuilderBase#setFormParams(List)}
   */
  @Test
  @DisplayName(
      "Test setFormParams(List) with 'params'; then RequestBuilder() formParams size is two")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"RequestBuilderBase RequestBuilderBase.setFormParams(List)"})
  void testSetFormParamsWithParams_thenRequestBuilderFormParamsSizeIsTwo() {
    // Arrange
    RequestBuilder requestBuilder = new RequestBuilder();

    ArrayList<Param> params = new ArrayList<>();
    params.add(new Param("https://example.org/example", "https://example.org/example"));
    Param param = new Param("https://example.org/example", "https://example.org/example");
    params.add(param);

    // Act
    RequestBuilder actualSetFormParamsResult = requestBuilder.setFormParams(params);

    // Assert
    List<Param> paramList = requestBuilder.formParams;
    assertEquals(2, paramList.size());
    List<Param> paramList2 = actualSetFormParamsResult.formParams;
    assertEquals(2, paramList2.size());
    assertSame(param, paramList.get(1));
    assertSame(param, paramList2.get(1));
  }

  /**
   * Test {@link RequestBuilderBase#setFormParams(List)} with {@code params}.
   *
   * <ul>
   *   <li>When {@link ArrayList#ArrayList()}.
   *   <li>Then {@link RequestBuilder#RequestBuilder()} {@link RequestBuilderBase#formParams} Empty.
   * </ul>
   *
   * <p>Method under test: {@link RequestBuilderBase#setFormParams(List)}
   */
  @Test
  @DisplayName(
      "Test setFormParams(List) with 'params'; when ArrayList(); then RequestBuilder() formParams Empty")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
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
   * Test {@link RequestBuilderBase#addBodyPart(Part)}.
   *
   * <ul>
   *   <li>Given {@link RequestBuilder#RequestBuilder()}.
   * </ul>
   *
   * <p>Method under test: {@link RequestBuilderBase#addBodyPart(Part)}
   */
  @Test
  @DisplayName("Test addBodyPart(Part); given RequestBuilder()")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"RequestBuilderBase RequestBuilderBase.addBodyPart(Part)"})
  void testAddBodyPart_givenRequestBuilder() throws UnsupportedEncodingException {
    // Arrange
    RequestBuilder requestBuilder = new RequestBuilder();
    ByteArrayPart bodyPart =
        new ByteArrayPart("https://example.org/example", "AXAXAXAX".getBytes("UTF-8"));

    // Act
    RequestBuilder actualAddBodyPartResult = requestBuilder.addBodyPart(bodyPart);

    // Assert
    assertEquals(1, requestBuilder.bodyParts.size());
    assertSame(requestBuilder, actualAddBodyPartResult);
  }

  /**
   * Test {@link RequestBuilderBase#addBodyPart(Part)}.
   *
   * <ul>
   *   <li>Given {@link RequestBuilder#RequestBuilder()} BodyParts is {@link ArrayList#ArrayList()}.
   * </ul>
   *
   * <p>Method under test: {@link RequestBuilderBase#addBodyPart(Part)}
   */
  @Test
  @DisplayName("Test addBodyPart(Part); given RequestBuilder() BodyParts is ArrayList()")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"RequestBuilderBase RequestBuilderBase.addBodyPart(Part)"})
  void testAddBodyPart_givenRequestBuilderBodyPartsIsArrayList()
      throws UnsupportedEncodingException {
    // Arrange
    RequestBuilder requestBuilder = new RequestBuilder();
    requestBuilder.setBodyParts(new ArrayList<>());
    ByteArrayPart bodyPart =
        new ByteArrayPart("https://example.org/example", "AXAXAXAX".getBytes("UTF-8"));

    // Act
    RequestBuilder actualAddBodyPartResult = requestBuilder.addBodyPart(bodyPart);

    // Assert
    assertEquals(1, requestBuilder.bodyParts.size());
    assertSame(requestBuilder, actualAddBodyPartResult);
  }

  /**
   * Test {@link RequestBuilderBase#setBodyParts(List)}.
   *
   * <ul>
   *   <li>Then {@link ArrayList#ArrayList()} size is one.
   * </ul>
   *
   * <p>Method under test: {@link RequestBuilderBase#setBodyParts(List)}
   */
  @Test
  @DisplayName("Test setBodyParts(List); then ArrayList() size is one")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"RequestBuilderBase RequestBuilderBase.setBodyParts(List)"})
  void testSetBodyParts_thenArrayListSizeIsOne() throws UnsupportedEncodingException {
    // Arrange
    RequestBuilder requestBuilder = new RequestBuilder();

    ArrayList<Part> bodyParts = new ArrayList<>();
    ByteArrayPart byteArrayPart =
        new ByteArrayPart("https://example.org/example", "AXAXAXAX".getBytes("UTF-8"));
    bodyParts.add(byteArrayPart);

    // Act
    RequestBuilder actualSetBodyPartsResult = requestBuilder.setBodyParts(bodyParts);

    // Assert
    assertEquals(1, bodyParts.size());
    List<Part> partList = actualSetBodyPartsResult.bodyParts;
    assertEquals(1, partList.size());
    Part getResult = partList.get(0);
    assertTrue(getResult instanceof ByteArrayPart);
    assertEquals(1, requestBuilder.bodyParts.size());
    assertArrayEquals("AXAXAXAX".getBytes("UTF-8"), ((ByteArrayPart) getResult).getBytes());
  }

  /**
   * Test {@link RequestBuilderBase#setBodyParts(List)}.
   *
   * <ul>
   *   <li>Then return {@link RequestBuilderBase#bodyParts} size is two.
   * </ul>
   *
   * <p>Method under test: {@link RequestBuilderBase#setBodyParts(List)}
   */
  @Test
  @DisplayName("Test setBodyParts(List); then return bodyParts size is two")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"RequestBuilderBase RequestBuilderBase.setBodyParts(List)"})
  void testSetBodyParts_thenReturnBodyPartsSizeIsTwo() throws UnsupportedEncodingException {
    // Arrange
    RequestBuilder requestBuilder = new RequestBuilder();

    ArrayList<Part> bodyParts = new ArrayList<>();
    ByteArrayPart byteArrayPart =
        new ByteArrayPart("https://example.org/example", "AXAXAXAX".getBytes("UTF-8"));
    bodyParts.add(byteArrayPart);
    ByteArrayPart byteArrayPart2 =
        new ByteArrayPart("https://example.org/example", "AXAXAXAX".getBytes("UTF-8"));
    bodyParts.add(byteArrayPart2);

    // Act and Assert
    List<Part> partList = requestBuilder.setBodyParts(bodyParts).bodyParts;
    assertEquals(2, partList.size());
    Part getResult = partList.get(0);
    assertTrue(getResult instanceof ByteArrayPart);
    List<Part> partList2 = requestBuilder.bodyParts;
    assertEquals(2, partList2.size());
    assertSame(byteArrayPart2, partList2.get(1));
    assertSame(byteArrayPart2, partList.get(1));
    assertArrayEquals("AXAXAXAX".getBytes("UTF-8"), ((ByteArrayPart) getResult).getBytes());
  }

  /**
   * Test {@link RequestBuilderBase#setBodyParts(List)}.
   *
   * <ul>
   *   <li>When {@link ArrayList#ArrayList()}.
   *   <li>Then {@link ArrayList#ArrayList()} Empty.
   * </ul>
   *
   * <p>Method under test: {@link RequestBuilderBase#setBodyParts(List)}
   */
  @Test
  @DisplayName("Test setBodyParts(List); when ArrayList(); then ArrayList() Empty")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
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
   *
   * <p>Method under test: {@link RequestBuilderBase#setProxyServer(Builder)}
   */
  @Test
  @DisplayName("Test setProxyServer(Builder) with 'proxyServerBuilder'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"RequestBuilderBase RequestBuilderBase.setProxyServer(Builder)"})
  void testSetProxyServerWithProxyServerBuilder() {
    // Arrange
    RequestBuilder requestBuilder = new RequestBuilder();

    // Act
    RequestBuilder actualSetProxyServerResult =
        requestBuilder.setProxyServer(Dsl.proxyServer("https://example.org/example", 8080));

    // Assert
    assertSame(requestBuilder, actualSetProxyServerResult);
  }

  /**
   * Test {@link RequestBuilderBase#setProxyServer(Builder)} with {@code proxyServerBuilder}.
   *
   * <p>Method under test: {@link RequestBuilderBase#setProxyServer(Builder)}
   */
  @Test
  @DisplayName("Test setProxyServer(Builder) with 'proxyServerBuilder'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"RequestBuilderBase RequestBuilderBase.setProxyServer(Builder)"})
  void testSetProxyServerWithProxyServerBuilder2() {
    // Arrange
    RequestBuilder requestBuilder = new RequestBuilder();

    Builder proxyServerBuilder = new Builder("https://example.org/example", 8080);
    proxyServerBuilder.setNonProxyHost(null);
    proxyServerBuilder.setProxyType(null);

    // Act
    RequestBuilder actualSetProxyServerResult = requestBuilder.setProxyServer(proxyServerBuilder);

    // Assert
    assertSame(requestBuilder, actualSetProxyServerResult);
  }

  /**
   * Test {@link RequestBuilderBase#setProxyServer(Builder)} with {@code proxyServerBuilder}.
   *
   * <ul>
   *   <li>Given {@code HTTP}.
   * </ul>
   *
   * <p>Method under test: {@link RequestBuilderBase#setProxyServer(Builder)}
   */
  @Test
  @DisplayName("Test setProxyServer(Builder) with 'proxyServerBuilder'; given 'HTTP'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"RequestBuilderBase RequestBuilderBase.setProxyServer(Builder)"})
  void testSetProxyServerWithProxyServerBuilder_givenHttp() {
    // Arrange
    RequestBuilder requestBuilder = new RequestBuilder();

    Builder proxyServerBuilder = new Builder("https://example.org/example", 8080);
    proxyServerBuilder.setNonProxyHost(null);
    proxyServerBuilder.setProxyType(ProxyType.HTTP);

    // Act
    RequestBuilder actualSetProxyServerResult = requestBuilder.setProxyServer(proxyServerBuilder);

    // Assert
    assertSame(requestBuilder, actualSetProxyServerResult);
  }

  /**
   * Test {@link RequestBuilderBase#setProxyServer(ProxyServer)} with {@code proxyServer}.
   *
   * <ul>
   *   <li>Then return {@link RequestBuilder#RequestBuilder()}.
   * </ul>
   *
   * <p>Method under test: {@link RequestBuilderBase#setProxyServer(ProxyServer)}
   */
  @Test
  @DisplayName("Test setProxyServer(ProxyServer) with 'proxyServer'; then return RequestBuilder()")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"RequestBuilderBase RequestBuilderBase.setProxyServer(ProxyServer)"})
  void testSetProxyServerWithProxyServer_thenReturnRequestBuilder() {
    // Arrange
    RequestBuilder requestBuilder = new RequestBuilder();

    Realm.Builder setAlgorithmResult =
        new Realm.Builder().setAlgorithm("https://example.org/example");

    Realm.Builder setCharsetResult = setAlgorithmResult.setCharset(Charset.forName("UTF-8"));

    Realm.Builder setServicePrincipalNameResult =
        setCharsetResult
            .setCustomLoginConfig(new HashMap<>())
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
    Uri uri =
        new Uri(
            "https://example.org/example",
            "https://example.org/example",
            "https://example.org/example",
            8080,
            "https://example.org/example",
            "https://example.org/example",
            "https://example.org/example");
    Realm realm =
        setServicePrincipalNameResult
            .setUri(uri)
            .setUseAbsoluteURI(true)
            .setUseCanonicalHostname(true)
            .setUsePreemptiveAuth(true)
            .build();

    // Act
    RequestBuilder actualSetProxyServerResult =
        requestBuilder.setProxyServer(
            new ProxyServer(
                "https://example.org/example",
                8080,
                8080,
                realm,
                new ArrayList<>(),
                ProxyType.HTTP));

    // Assert
    assertSame(requestBuilder, actualSetProxyServerResult);
  }

  /**
   * Test {@link RequestBuilderBase#setRealm(Builder)} with {@code Builder}.
   *
   * <ul>
   *   <li>Given {@code BASIC}.
   *   <li>Then return {@link RequestBuilder#RequestBuilder()}.
   * </ul>
   *
   * <p>Method under test: {@link RequestBuilderBase#setRealm(Realm.Builder)}
   */
  @Test
  @DisplayName("Test setRealm(Builder) with 'Builder'; given 'BASIC'; then return RequestBuilder()")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"RequestBuilderBase RequestBuilderBase.setRealm(Realm.Builder)"})
  void testSetRealmWithBuilder_givenBasic_thenReturnRequestBuilder() {
    // Arrange
    RequestBuilder requestBuilder = new RequestBuilder();

    Realm.Builder realm = new Realm.Builder();
    realm.setScheme(AuthScheme.BASIC);

    // Act
    RequestBuilder actualSetRealmResult = requestBuilder.setRealm(realm);

    // Assert
    assertSame(requestBuilder, actualSetRealmResult);
  }

  /**
   * Test {@link RequestBuilderBase#setRealm(Realm)} with {@code Realm}.
   *
   * <p>Method under test: {@link RequestBuilderBase#setRealm(Realm)}
   */
  @Test
  @DisplayName("Test setRealm(Realm) with 'Realm'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"RequestBuilderBase RequestBuilderBase.setRealm(Realm)"})
  void testSetRealmWithRealm() {
    // Arrange
    RequestBuilder requestBuilder = new RequestBuilder();

    // Act
    RequestBuilder actualSetRealmResult = requestBuilder.setRealm(mock(Realm.class));

    // Assert
    assertSame(requestBuilder, actualSetRealmResult);
  }

  /**
   * Test {@link RequestBuilderBase#setFollowRedirect(boolean)}.
   *
   * <p>Method under test: {@link RequestBuilderBase#setFollowRedirect(boolean)}
   */
  @Test
  @DisplayName("Test setFollowRedirect(boolean)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"RequestBuilderBase RequestBuilderBase.setFollowRedirect(boolean)"})
  void testSetFollowRedirect() {
    // Arrange
    RequestBuilder requestBuilder = new RequestBuilder();

    // Act
    RequestBuilder actualSetFollowRedirectResult = requestBuilder.setFollowRedirect(true);

    // Assert
    assertTrue(requestBuilder.followRedirect);
    assertSame(requestBuilder, actualSetFollowRedirectResult);
  }

  /**
   * Test {@link RequestBuilderBase#setRequestTimeout(Duration)}.
   *
   * <p>Method under test: {@link RequestBuilderBase#setRequestTimeout(Duration)}
   */
  @Test
  @DisplayName("Test setRequestTimeout(Duration)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"RequestBuilderBase RequestBuilderBase.setRequestTimeout(Duration)"})
  void testSetRequestTimeout() {
    // Arrange
    RequestBuilder requestBuilder = new RequestBuilder();

    // Act
    RequestBuilder actualSetRequestTimeoutResult =
        requestBuilder.setRequestTimeout(Duration.ofSeconds(1L));

    // Assert
    assertSame(requestBuilder, actualSetRequestTimeoutResult);
  }

  /**
   * Test {@link RequestBuilderBase#setReadTimeout(Duration)}.
   *
   * <p>Method under test: {@link RequestBuilderBase#setReadTimeout(Duration)}
   */
  @Test
  @DisplayName("Test setReadTimeout(Duration)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"RequestBuilderBase RequestBuilderBase.setReadTimeout(Duration)"})
  void testSetReadTimeout() {
    // Arrange
    RequestBuilder requestBuilder = new RequestBuilder();

    // Act
    RequestBuilder actualSetReadTimeoutResult =
        requestBuilder.setReadTimeout(Duration.ofSeconds(1L));

    // Assert
    assertSame(requestBuilder, actualSetReadTimeoutResult);
  }

  /**
   * Test {@link RequestBuilderBase#setRangeOffset(long)}.
   *
   * <p>Method under test: {@link RequestBuilderBase#setRangeOffset(long)}
   */
  @Test
  @DisplayName("Test setRangeOffset(long)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"RequestBuilderBase RequestBuilderBase.setRangeOffset(long)"})
  void testSetRangeOffset() {
    // Arrange
    RequestBuilder requestBuilder = new RequestBuilder();

    // Act
    RequestBuilder actualSetRangeOffsetResult = requestBuilder.setRangeOffset(1L);

    // Assert
    assertEquals(1L, requestBuilder.rangeOffset);
    assertSame(requestBuilder, actualSetRangeOffsetResult);
  }

  /**
   * Test {@link RequestBuilderBase#setMethod(String)}.
   *
   * <p>Method under test: {@link RequestBuilderBase#setMethod(String)}
   */
  @Test
  @DisplayName("Test setMethod(String)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"RequestBuilderBase RequestBuilderBase.setMethod(String)"})
  void testSetMethod() {
    // Arrange
    RequestBuilder requestBuilder = new RequestBuilder();

    // Act
    RequestBuilder actualSetMethodResult = requestBuilder.setMethod("https://example.org/example");

    // Assert
    assertEquals("https://example.org/example", requestBuilder.method);
    assertSame(requestBuilder, actualSetMethodResult);
  }

  /**
   * Test {@link RequestBuilderBase#setCharset(Charset)}.
   *
   * <p>Method under test: {@link RequestBuilderBase#setCharset(Charset)}
   */
  @Test
  @DisplayName("Test setCharset(Charset)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"RequestBuilderBase RequestBuilderBase.setCharset(Charset)"})
  void testSetCharset() {
    // Arrange
    RequestBuilder requestBuilder = new RequestBuilder();

    // Act
    RequestBuilder actualSetCharsetResult = requestBuilder.setCharset(Charset.forName("UTF-8"));

    // Assert
    assertSame(requestBuilder, actualSetCharsetResult);
  }

  /**
   * Test {@link RequestBuilderBase#setChannelPoolPartitioning(ChannelPoolPartitioning)}.
   *
   * <p>Method under test: {@link
   * RequestBuilderBase#setChannelPoolPartitioning(ChannelPoolPartitioning)}
   */
  @Test
  @DisplayName("Test setChannelPoolPartitioning(ChannelPoolPartitioning)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RequestBuilderBase RequestBuilderBase.setChannelPoolPartitioning(ChannelPoolPartitioning)"
  })
  void testSetChannelPoolPartitioning() {
    // Arrange
    RequestBuilder requestBuilder = new RequestBuilder();

    // Act
    RequestBuilder actualSetChannelPoolPartitioningResult =
        requestBuilder.setChannelPoolPartitioning(mock(ChannelPoolPartitioning.class));

    // Assert
    assertSame(requestBuilder, actualSetChannelPoolPartitioningResult);
  }

  /**
   * Test {@link RequestBuilderBase#setSignatureCalculator(SignatureCalculator)}.
   *
   * <p>Method under test: {@link RequestBuilderBase#setSignatureCalculator(SignatureCalculator)}
   */
  @Test
  @DisplayName("Test setSignatureCalculator(SignatureCalculator)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RequestBuilderBase RequestBuilderBase.setSignatureCalculator(SignatureCalculator)"
  })
  void testSetSignatureCalculator() {
    // Arrange
    RequestBuilder requestBuilder = new RequestBuilder();

    // Act
    RequestBuilder actualSetSignatureCalculatorResult =
        requestBuilder.setSignatureCalculator(mock(SignatureCalculator.class));

    // Assert
    assertSame(requestBuilder, actualSetSignatureCalculatorResult);
  }
}
