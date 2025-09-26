package org.asynchttpclient.netty;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.anyInt;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import io.netty.buffer.AdaptiveByteBufAllocator;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.CompositeByteBuf;
import io.netty.buffer.DuplicatedByteBuf;
import io.netty.buffer.EmptyByteBuf;
import io.netty.buffer.ReadOnlyByteBuf;
import io.netty.channel.embedded.EmbeddedChannel;
import io.netty.handler.codec.http.DefaultFullHttpResponse;
import io.netty.handler.codec.http.DefaultHttpHeaders;
import io.netty.handler.codec.http.EmptyHttpHeaders;
import io.netty.handler.codec.http.HttpHeaders;
import io.netty.handler.codec.http.HttpResponseStatus;
import io.netty.handler.codec.http.HttpVersion;
import io.netty.handler.codec.http.cookie.Cookie;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import org.asynchttpclient.HttpResponseBodyPart;
import org.asynchttpclient.config.AsyncHttpClientConfigDefaults;
import org.asynchttpclient.uri.Uri;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

class NettyResponseDiffblueTest {
  /**
   * Test {@link NettyResponse#getStatusCode()}.
   *
   * <ul>
   *   <li>Then return one.
   * </ul>
   *
   * <p>Method under test: {@link NettyResponse#getStatusCode()}
   */
  @Test
  @DisplayName("Test getStatusCode(); then return one")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"int NettyResponse.getStatusCode()"})
  void testGetStatusCode_thenReturnOne() {
    // Arrange
    Uri uri =
        new Uri(
            "https://example.org/example",
            "https://example.org/example",
            "https://example.org/example",
            8080,
            "https://example.org/example",
            "https://example.org/example",
            "https://example.org/example");
    HttpVersion version = new HttpVersion("https://example.org/example", 1, 1, true);
    DefaultFullHttpResponse response =
        new DefaultFullHttpResponse(version, HttpResponseStatus.valueOf(1));

    NettyResponseStatus status = new NettyResponseStatus(uri, response, new EmbeddedChannel());
    DefaultHttpHeaders headers = new DefaultHttpHeaders();

    NettyResponse nettyResponse = new NettyResponse(status, headers, new ArrayList<>());

    // Act and Assert
    assertEquals(1, nettyResponse.getStatusCode());
  }

  /**
   * Test {@link NettyResponse#getStatusText()}.
   *
   * <ul>
   *   <li>Then return {@code Unknown Status (1)}.
   * </ul>
   *
   * <p>Method under test: {@link NettyResponse#getStatusText()}
   */
  @Test
  @DisplayName("Test getStatusText(); then return 'Unknown Status (1)'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"String NettyResponse.getStatusText()"})
  void testGetStatusText_thenReturnUnknownStatus1() {
    // Arrange
    Uri uri =
        new Uri(
            "https://example.org/example",
            "https://example.org/example",
            "https://example.org/example",
            8080,
            "https://example.org/example",
            "https://example.org/example",
            "https://example.org/example");
    HttpVersion version = new HttpVersion("https://example.org/example", 1, 1, true);
    DefaultFullHttpResponse response =
        new DefaultFullHttpResponse(version, HttpResponseStatus.valueOf(1));

    NettyResponseStatus status = new NettyResponseStatus(uri, response, new EmbeddedChannel());
    DefaultHttpHeaders headers = new DefaultHttpHeaders();

    NettyResponse nettyResponse = new NettyResponse(status, headers, new ArrayList<>());

    // Act and Assert
    assertEquals("Unknown Status (1)", nettyResponse.getStatusText());
  }

  /**
   * Test {@link NettyResponse#getUri()}.
   *
   * <p>Method under test: {@link NettyResponse#getUri()}
   */
  @Test
  @DisplayName("Test getUri()")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"Uri NettyResponse.getUri()"})
  void testGetUri() {
    // Arrange
    Uri uri =
        new Uri(
            "https://example.org/example",
            "https://example.org/example",
            "https://example.org/example",
            8080,
            "https://example.org/example",
            "https://example.org/example",
            "https://example.org/example");
    HttpVersion version = new HttpVersion("https://example.org/example", 1, 1, true);
    DefaultFullHttpResponse response =
        new DefaultFullHttpResponse(version, HttpResponseStatus.valueOf(1));

    NettyResponseStatus status = new NettyResponseStatus(uri, response, new EmbeddedChannel());
    DefaultHttpHeaders headers = new DefaultHttpHeaders();

    NettyResponse nettyResponse = new NettyResponse(status, headers, new ArrayList<>());

    // Act and Assert
    assertSame(uri, nettyResponse.getUri());
  }

  /**
   * Test {@link NettyResponse#getContentType()}.
   *
   * <ul>
   *   <li>Then return {@code https://example.org/example}.
   * </ul>
   *
   * <p>Method under test: {@link NettyResponse#getContentType()}
   */
  @Test
  @DisplayName("Test getContentType(); then return 'https://example.org/example'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"String NettyResponse.getContentType()"})
  void testGetContentType_thenReturnHttpsExampleOrgExample() {
    // Arrange
    EmptyHttpHeaders headers = mock(EmptyHttpHeaders.class);
    when(headers.get(Mockito.<CharSequence>any())).thenReturn("https://example.org/example");
    Uri uri =
        new Uri(
            "https://example.org/example",
            "https://example.org/example",
            "https://example.org/example",
            8080,
            "https://example.org/example",
            "https://example.org/example",
            "https://example.org/example");
    HttpVersion version = new HttpVersion("https://example.org/example", 1, 1, true);
    DefaultFullHttpResponse response =
        new DefaultFullHttpResponse(version, HttpResponseStatus.valueOf(1));

    NettyResponseStatus status = new NettyResponseStatus(uri, response, new EmbeddedChannel());

    NettyResponse nettyResponse = new NettyResponse(status, headers, new ArrayList<>());

    // Act
    String actualContentType = nettyResponse.getContentType();

    // Assert
    verify(headers).get(isA(CharSequence.class));
    assertEquals("https://example.org/example", actualContentType);
  }

  /**
   * Test {@link NettyResponse#getContentType()}.
   *
   * <ul>
   *   <li>Then return {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link NettyResponse#getContentType()}
   */
  @Test
  @DisplayName("Test getContentType(); then return 'null'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"String NettyResponse.getContentType()"})
  void testGetContentType_thenReturnNull() {
    // Arrange
    Uri uri =
        new Uri(
            "https://example.org/example",
            "https://example.org/example",
            "https://example.org/example",
            8080,
            "https://example.org/example",
            "https://example.org/example",
            "https://example.org/example");
    HttpVersion version = new HttpVersion("https://example.org/example", 1, 1, true);
    DefaultFullHttpResponse response =
        new DefaultFullHttpResponse(version, HttpResponseStatus.valueOf(1));

    NettyResponseStatus status = new NettyResponseStatus(uri, response, new EmbeddedChannel());
    DefaultHttpHeaders headers = new DefaultHttpHeaders();

    NettyResponse nettyResponse = new NettyResponse(status, headers, new ArrayList<>());

    // Act and Assert
    assertNull(nettyResponse.getContentType());
  }

  /**
   * Test {@link NettyResponse#getHeader(CharSequence)}.
   *
   * <ul>
   *   <li>Then return {@code https://example.org/example}.
   * </ul>
   *
   * <p>Method under test: {@link NettyResponse#getHeader(CharSequence)}
   */
  @Test
  @DisplayName("Test getHeader(CharSequence); then return 'https://example.org/example'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"String NettyResponse.getHeader(CharSequence)"})
  void testGetHeader_thenReturnHttpsExampleOrgExample() {
    // Arrange
    EmptyHttpHeaders headers = mock(EmptyHttpHeaders.class);
    when(headers.get(Mockito.<CharSequence>any())).thenReturn("https://example.org/example");
    Uri uri =
        new Uri(
            "https://example.org/example",
            "https://example.org/example",
            "https://example.org/example",
            8080,
            "https://example.org/example",
            "https://example.org/example",
            "https://example.org/example");
    HttpVersion version = new HttpVersion("https://example.org/example", 1, 1, true);
    DefaultFullHttpResponse response =
        new DefaultFullHttpResponse(version, HttpResponseStatus.valueOf(1));

    NettyResponseStatus status = new NettyResponseStatus(uri, response, new EmbeddedChannel());

    NettyResponse nettyResponse = new NettyResponse(status, headers, new ArrayList<>());

    // Act
    String actualHeader =
        nettyResponse.getHeader(AsyncHttpClientConfigDefaults.ACQUIRE_FREE_CHANNEL_TIMEOUT);

    // Assert
    verify(headers).get(isA(CharSequence.class));
    assertEquals("https://example.org/example", actualHeader);
  }

  /**
   * Test {@link NettyResponse#getHeader(CharSequence)}.
   *
   * <ul>
   *   <li>Then return {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link NettyResponse#getHeader(CharSequence)}
   */
  @Test
  @DisplayName("Test getHeader(CharSequence); then return 'null'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"String NettyResponse.getHeader(CharSequence)"})
  void testGetHeader_thenReturnNull() {
    // Arrange
    Uri uri =
        new Uri(
            "https://example.org/example",
            "https://example.org/example",
            "https://example.org/example",
            8080,
            "https://example.org/example",
            "https://example.org/example",
            "https://example.org/example");
    HttpVersion version = new HttpVersion("https://example.org/example", 1, 1, true);
    DefaultFullHttpResponse response =
        new DefaultFullHttpResponse(version, HttpResponseStatus.valueOf(1));

    NettyResponseStatus status = new NettyResponseStatus(uri, response, new EmbeddedChannel());
    DefaultHttpHeaders headers = new DefaultHttpHeaders();

    NettyResponse nettyResponse = new NettyResponse(status, headers, new ArrayList<>());

    // Act and Assert
    assertNull(nettyResponse.getHeader(AsyncHttpClientConfigDefaults.ACQUIRE_FREE_CHANNEL_TIMEOUT));
  }

  /**
   * Test {@link NettyResponse#getHeaders(CharSequence)} with {@code CharSequence}.
   *
   * <ul>
   *   <li>Then calls {@link EmptyHttpHeaders#getAll(CharSequence)}.
   * </ul>
   *
   * <p>Method under test: {@link NettyResponse#getHeaders(CharSequence)}
   */
  @Test
  @DisplayName("Test getHeaders(CharSequence) with 'CharSequence'; then calls getAll(CharSequence)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"List NettyResponse.getHeaders(CharSequence)"})
  void testGetHeadersWithCharSequence_thenCallsGetAll() {
    // Arrange
    EmptyHttpHeaders headers = mock(EmptyHttpHeaders.class);
    when(headers.getAll(Mockito.<CharSequence>any())).thenReturn(new ArrayList<>());
    Uri uri =
        new Uri(
            "https://example.org/example",
            "https://example.org/example",
            "https://example.org/example",
            8080,
            "https://example.org/example",
            "https://example.org/example",
            "https://example.org/example");
    HttpVersion version = new HttpVersion("https://example.org/example", 1, 1, true);
    DefaultFullHttpResponse response =
        new DefaultFullHttpResponse(version, HttpResponseStatus.valueOf(1));

    NettyResponseStatus status = new NettyResponseStatus(uri, response, new EmbeddedChannel());

    NettyResponse nettyResponse = new NettyResponse(status, headers, new ArrayList<>());

    // Act
    List<String> actualHeaders =
        nettyResponse.getHeaders(AsyncHttpClientConfigDefaults.ACQUIRE_FREE_CHANNEL_TIMEOUT);

    // Assert
    verify(headers).getAll(isA(CharSequence.class));
    assertTrue(actualHeaders.isEmpty());
  }

  /**
   * Test {@link NettyResponse#getHeaders(CharSequence)} with {@code CharSequence}.
   *
   * <ul>
   *   <li>Then return Empty.
   * </ul>
   *
   * <p>Method under test: {@link NettyResponse#getHeaders(CharSequence)}
   */
  @Test
  @DisplayName("Test getHeaders(CharSequence) with 'CharSequence'; then return Empty")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"List NettyResponse.getHeaders(CharSequence)"})
  void testGetHeadersWithCharSequence_thenReturnEmpty() {
    // Arrange
    Uri uri =
        new Uri(
            "https://example.org/example",
            "https://example.org/example",
            "https://example.org/example",
            8080,
            "https://example.org/example",
            "https://example.org/example",
            "https://example.org/example");
    HttpVersion version = new HttpVersion("https://example.org/example", 1, 1, true);
    DefaultFullHttpResponse response =
        new DefaultFullHttpResponse(version, HttpResponseStatus.valueOf(1));

    NettyResponseStatus status = new NettyResponseStatus(uri, response, new EmbeddedChannel());
    DefaultHttpHeaders headers = new DefaultHttpHeaders();

    NettyResponse nettyResponse = new NettyResponse(status, headers, new ArrayList<>());

    // Act and Assert
    assertTrue(
        nettyResponse
            .getHeaders(AsyncHttpClientConfigDefaults.ACQUIRE_FREE_CHANNEL_TIMEOUT)
            .isEmpty());
  }

  /**
   * Test {@link NettyResponse#getHeaders()}.
   *
   * <ul>
   *   <li>Then return {@link DefaultHttpHeaders#DefaultHttpHeaders()}.
   * </ul>
   *
   * <p>Method under test: {@link NettyResponse#getHeaders()}
   */
  @Test
  @DisplayName("Test getHeaders(); then return DefaultHttpHeaders()")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"HttpHeaders NettyResponse.getHeaders()"})
  void testGetHeaders_thenReturnDefaultHttpHeaders() {
    // Arrange
    Uri uri =
        new Uri(
            "https://example.org/example",
            "https://example.org/example",
            "https://example.org/example",
            8080,
            "https://example.org/example",
            "https://example.org/example",
            "https://example.org/example");
    HttpVersion version = new HttpVersion("https://example.org/example", 1, 1, true);
    DefaultFullHttpResponse response =
        new DefaultFullHttpResponse(version, HttpResponseStatus.valueOf(1));

    NettyResponseStatus status = new NettyResponseStatus(uri, response, new EmbeddedChannel());
    DefaultHttpHeaders headers = new DefaultHttpHeaders();

    NettyResponse nettyResponse = new NettyResponse(status, headers, new ArrayList<>());

    // Act and Assert
    assertSame(headers, nettyResponse.getHeaders());
  }

  /**
   * Test {@link NettyResponse#getHeaders()}.
   *
   * <ul>
   *   <li>Then return {@link EmptyHttpHeaders#INSTANCE}.
   * </ul>
   *
   * <p>Method under test: {@link NettyResponse#getHeaders()}
   */
  @Test
  @DisplayName("Test getHeaders(); then return INSTANCE")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"HttpHeaders NettyResponse.getHeaders()"})
  void testGetHeaders_thenReturnInstance() {
    // Arrange
    Uri uri =
        new Uri(
            "https://example.org/example",
            "https://example.org/example",
            "https://example.org/example",
            8080,
            "https://example.org/example",
            "https://example.org/example",
            "https://example.org/example");
    HttpVersion version = new HttpVersion("https://example.org/example", 1, 1, true);
    DefaultFullHttpResponse response =
        new DefaultFullHttpResponse(version, HttpResponseStatus.valueOf(1));

    NettyResponseStatus status = new NettyResponseStatus(uri, response, new EmbeddedChannel());
    NettyResponse nettyResponse = new NettyResponse(status, null, new ArrayList<>());

    // Act
    HttpHeaders actualHeaders = nettyResponse.getHeaders();

    // Assert
    assertSame(((EmptyHttpHeaders) actualHeaders).INSTANCE, actualHeaders);
  }

  /**
   * Test {@link NettyResponse#isRedirected()}.
   *
   * <ul>
   *   <li>Then return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link NettyResponse#isRedirected()}
   */
  @Test
  @DisplayName("Test isRedirected(); then return 'false'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean NettyResponse.isRedirected()"})
  void testIsRedirected_thenReturnFalse() {
    // Arrange
    Uri uri =
        new Uri(
            "https://example.org/example",
            "https://example.org/example",
            "https://example.org/example",
            8080,
            "https://example.org/example",
            "https://example.org/example",
            "https://example.org/example");
    HttpVersion version = new HttpVersion("https://example.org/example", 1, 1, true);
    DefaultFullHttpResponse response =
        new DefaultFullHttpResponse(version, HttpResponseStatus.valueOf(1));

    NettyResponseStatus status = new NettyResponseStatus(uri, response, new EmbeddedChannel());
    DefaultHttpHeaders headers = new DefaultHttpHeaders();

    NettyResponse nettyResponse = new NettyResponse(status, headers, new ArrayList<>());

    // Act and Assert
    assertFalse(nettyResponse.isRedirected());
  }

  /**
   * Test {@link NettyResponse#isRedirected()}.
   *
   * <ul>
   *   <li>Then return {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link NettyResponse#isRedirected()}
   */
  @Test
  @DisplayName("Test isRedirected(); then return 'true'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean NettyResponse.isRedirected()"})
  void testIsRedirected_thenReturnTrue() {
    // Arrange
    Uri uri =
        new Uri(
            "https://example.org/example",
            "https://example.org/example",
            "https://example.org/example",
            8080,
            "https://example.org/example",
            "https://example.org/example",
            "https://example.org/example");
    HttpVersion version = new HttpVersion("https://example.org/example", 1, 1, true);
    DefaultFullHttpResponse response =
        new DefaultFullHttpResponse(
            version, HttpResponseStatus.valueOf(301, "https://example.org/example"));

    NettyResponseStatus status = new NettyResponseStatus(uri, response, new EmbeddedChannel());
    DefaultHttpHeaders headers = new DefaultHttpHeaders();

    NettyResponse nettyResponse = new NettyResponse(status, headers, new ArrayList<>());

    // Act and Assert
    assertTrue(nettyResponse.isRedirected());
  }

  /**
   * Test {@link NettyResponse#getCookies()}.
   *
   * <p>Method under test: {@link NettyResponse#getCookies()}
   */
  @Test
  @DisplayName("Test getCookies()")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"List NettyResponse.getCookies()"})
  void testGetCookies() {
    // Arrange
    Uri uri =
        new Uri(
            "https://example.org/example",
            "https://example.org/example",
            "https://example.org/example",
            8080,
            "https://example.org/example",
            "https://example.org/example",
            "https://example.org/example");
    HttpVersion version = new HttpVersion("https://example.org/example", 1, 1, true);
    DefaultFullHttpResponse response =
        new DefaultFullHttpResponse(version, HttpResponseStatus.valueOf(1));

    NettyResponseStatus status = new NettyResponseStatus(uri, response, new EmbeddedChannel());
    DefaultHttpHeaders headers = new DefaultHttpHeaders();

    NettyResponse nettyResponse = new NettyResponse(status, headers, new ArrayList<>());

    // Act and Assert
    assertTrue(nettyResponse.getCookies().isEmpty());
  }

  /**
   * Test {@link NettyResponse#getCookies()}.
   *
   * <ul>
   *   <li>Given {@link ArrayList#ArrayList()} add {@code 42}.
   *   <li>Then calls {@link EmptyHttpHeaders#getAll(CharSequence)}.
   * </ul>
   *
   * <p>Method under test: {@link NettyResponse#getCookies()}
   */
  @Test
  @DisplayName("Test getCookies(); given ArrayList() add '42'; then calls getAll(CharSequence)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"List NettyResponse.getCookies()"})
  void testGetCookies_givenArrayListAdd42_thenCallsGetAll() {
    // Arrange
    ArrayList<String> stringList = new ArrayList<>();
    stringList.add("42");
    stringList.add("foo");

    EmptyHttpHeaders headers = mock(EmptyHttpHeaders.class);
    when(headers.getAll(Mockito.<CharSequence>any())).thenReturn(stringList);
    Uri uri =
        new Uri(
            "https://example.org/example",
            "https://example.org/example",
            "https://example.org/example",
            8080,
            "https://example.org/example",
            "https://example.org/example",
            "https://example.org/example");
    HttpVersion version = new HttpVersion("https://example.org/example", 1, 1, true);
    DefaultFullHttpResponse response =
        new DefaultFullHttpResponse(version, HttpResponseStatus.valueOf(1));

    NettyResponseStatus status = new NettyResponseStatus(uri, response, new EmbeddedChannel());

    NettyResponse nettyResponse = new NettyResponse(status, headers, new ArrayList<>());

    // Act
    List<Cookie> actualCookies = nettyResponse.getCookies();

    // Assert
    verify(headers).getAll(isA(CharSequence.class));
    assertTrue(actualCookies.isEmpty());
  }

  /**
   * Test {@link NettyResponse#getCookies()}.
   *
   * <ul>
   *   <li>Given {@link ArrayList#ArrayList()} add empty string.
   *   <li>Then calls {@link EmptyHttpHeaders#getAll(CharSequence)}.
   * </ul>
   *
   * <p>Method under test: {@link NettyResponse#getCookies()}
   */
  @Test
  @DisplayName(
      "Test getCookies(); given ArrayList() add empty string; then calls getAll(CharSequence)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"List NettyResponse.getCookies()"})
  void testGetCookies_givenArrayListAddEmptyString_thenCallsGetAll() {
    // Arrange
    ArrayList<String> stringList = new ArrayList<>();
    stringList.add("");
    stringList.add("42");
    stringList.add("foo");

    EmptyHttpHeaders headers = mock(EmptyHttpHeaders.class);
    when(headers.getAll(Mockito.<CharSequence>any())).thenReturn(stringList);
    Uri uri =
        new Uri(
            "https://example.org/example",
            "https://example.org/example",
            "https://example.org/example",
            8080,
            "https://example.org/example",
            "https://example.org/example",
            "https://example.org/example");
    HttpVersion version = new HttpVersion("https://example.org/example", 1, 1, true);
    DefaultFullHttpResponse response =
        new DefaultFullHttpResponse(version, HttpResponseStatus.valueOf(1));

    NettyResponseStatus status = new NettyResponseStatus(uri, response, new EmbeddedChannel());

    NettyResponse nettyResponse = new NettyResponse(status, headers, new ArrayList<>());

    // Act
    List<Cookie> actualCookies = nettyResponse.getCookies();

    // Assert
    verify(headers).getAll(isA(CharSequence.class));
    assertTrue(actualCookies.isEmpty());
  }

  /**
   * Test {@link NettyResponse#getCookies()}.
   *
   * <ul>
   *   <li>Given {@link ArrayList#ArrayList()} add {@code foo}.
   *   <li>Then calls {@link EmptyHttpHeaders#getAll(CharSequence)}.
   * </ul>
   *
   * <p>Method under test: {@link NettyResponse#getCookies()}
   */
  @Test
  @DisplayName("Test getCookies(); given ArrayList() add 'foo'; then calls getAll(CharSequence)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"List NettyResponse.getCookies()"})
  void testGetCookies_givenArrayListAddFoo_thenCallsGetAll() {
    // Arrange
    ArrayList<String> stringList = new ArrayList<>();
    stringList.add("foo");

    EmptyHttpHeaders headers = mock(EmptyHttpHeaders.class);
    when(headers.getAll(Mockito.<CharSequence>any())).thenReturn(stringList);
    Uri uri =
        new Uri(
            "https://example.org/example",
            "https://example.org/example",
            "https://example.org/example",
            8080,
            "https://example.org/example",
            "https://example.org/example",
            "https://example.org/example");
    HttpVersion version = new HttpVersion("https://example.org/example", 1, 1, true);
    DefaultFullHttpResponse response =
        new DefaultFullHttpResponse(version, HttpResponseStatus.valueOf(1));

    NettyResponseStatus status = new NettyResponseStatus(uri, response, new EmbeddedChannel());

    NettyResponse nettyResponse = new NettyResponse(status, headers, new ArrayList<>());

    // Act
    List<Cookie> actualCookies = nettyResponse.getCookies();

    // Assert
    verify(headers).getAll(isA(CharSequence.class));
    assertTrue(actualCookies.isEmpty());
  }

  /**
   * Test {@link NettyResponse#getCookies()}.
   *
   * <ul>
   *   <li>Given {@link EmptyHttpHeaders} {@link EmptyHttpHeaders#getAll(CharSequence)} return
   *       {@link ArrayList#ArrayList()}.
   *   <li>Then calls {@link EmptyHttpHeaders#getAll(CharSequence)}.
   * </ul>
   *
   * <p>Method under test: {@link NettyResponse#getCookies()}
   */
  @Test
  @DisplayName(
      "Test getCookies(); given EmptyHttpHeaders getAll(CharSequence) return ArrayList(); then calls getAll(CharSequence)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"List NettyResponse.getCookies()"})
  void testGetCookies_givenEmptyHttpHeadersGetAllReturnArrayList_thenCallsGetAll() {
    // Arrange
    EmptyHttpHeaders headers = mock(EmptyHttpHeaders.class);
    when(headers.getAll(Mockito.<CharSequence>any())).thenReturn(new ArrayList<>());
    Uri uri =
        new Uri(
            "https://example.org/example",
            "https://example.org/example",
            "https://example.org/example",
            8080,
            "https://example.org/example",
            "https://example.org/example",
            "https://example.org/example");
    HttpVersion version = new HttpVersion("https://example.org/example", 1, 1, true);
    DefaultFullHttpResponse response =
        new DefaultFullHttpResponse(version, HttpResponseStatus.valueOf(1));

    NettyResponseStatus status = new NettyResponseStatus(uri, response, new EmbeddedChannel());

    NettyResponse nettyResponse = new NettyResponse(status, headers, new ArrayList<>());

    // Act
    List<Cookie> actualCookies = nettyResponse.getCookies();

    // Assert
    verify(headers, atLeast(1)).getAll(Mockito.<CharSequence>any());
    assertTrue(actualCookies.isEmpty());
  }

  /**
   * Test {@link NettyResponse#hasResponseStatus()}.
   *
   * <ul>
   *   <li>Then return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link NettyResponse#hasResponseStatus()}
   */
  @Test
  @DisplayName("Test hasResponseStatus(); then return 'false'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean NettyResponse.hasResponseStatus()"})
  void testHasResponseStatus_thenReturnFalse() {
    // Arrange
    DefaultHttpHeaders headers = new DefaultHttpHeaders();
    NettyResponse nettyResponse = new NettyResponse(null, headers, new ArrayList<>());

    // Act and Assert
    assertFalse(nettyResponse.hasResponseStatus());
  }

  /**
   * Test {@link NettyResponse#hasResponseStatus()}.
   *
   * <ul>
   *   <li>Then return {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link NettyResponse#hasResponseStatus()}
   */
  @Test
  @DisplayName("Test hasResponseStatus(); then return 'true'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean NettyResponse.hasResponseStatus()"})
  void testHasResponseStatus_thenReturnTrue() {
    // Arrange
    Uri uri =
        new Uri(
            "https://example.org/example",
            "https://example.org/example",
            "https://example.org/example",
            8080,
            "https://example.org/example",
            "https://example.org/example",
            "https://example.org/example");
    HttpVersion version = new HttpVersion("https://example.org/example", 1, 1, true);
    DefaultFullHttpResponse response =
        new DefaultFullHttpResponse(version, HttpResponseStatus.valueOf(1));

    NettyResponseStatus status = new NettyResponseStatus(uri, response, new EmbeddedChannel());
    DefaultHttpHeaders headers = new DefaultHttpHeaders();

    NettyResponse nettyResponse = new NettyResponse(status, headers, new ArrayList<>());

    // Act and Assert
    assertTrue(nettyResponse.hasResponseStatus());
  }

  /**
   * Test {@link NettyResponse#hasResponseHeaders()}.
   *
   * <ul>
   *   <li>Given {@link EmptyHttpHeaders} {@link EmptyHttpHeaders#isEmpty()} return {@code false}.
   *   <li>Then return {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link NettyResponse#hasResponseHeaders()}
   */
  @Test
  @DisplayName(
      "Test hasResponseHeaders(); given EmptyHttpHeaders isEmpty() return 'false'; then return 'true'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean NettyResponse.hasResponseHeaders()"})
  void testHasResponseHeaders_givenEmptyHttpHeadersIsEmptyReturnFalse_thenReturnTrue() {
    // Arrange
    EmptyHttpHeaders headers = mock(EmptyHttpHeaders.class);
    when(headers.isEmpty()).thenReturn(false);
    Uri uri =
        new Uri(
            "https://example.org/example",
            "https://example.org/example",
            "https://example.org/example",
            8080,
            "https://example.org/example",
            "https://example.org/example",
            "https://example.org/example");
    HttpVersion version = new HttpVersion("https://example.org/example", 1, 1, true);
    DefaultFullHttpResponse response =
        new DefaultFullHttpResponse(version, HttpResponseStatus.valueOf(1));

    NettyResponseStatus status = new NettyResponseStatus(uri, response, new EmbeddedChannel());

    NettyResponse nettyResponse = new NettyResponse(status, headers, new ArrayList<>());

    // Act
    boolean actualHasResponseHeadersResult = nettyResponse.hasResponseHeaders();

    // Assert
    verify(headers).isEmpty();
    assertTrue(actualHasResponseHeadersResult);
  }

  /**
   * Test {@link NettyResponse#hasResponseHeaders()}.
   *
   * <ul>
   *   <li>Given {@link EmptyHttpHeaders} {@link EmptyHttpHeaders#isEmpty()} return {@code true}.
   *   <li>Then return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link NettyResponse#hasResponseHeaders()}
   */
  @Test
  @DisplayName(
      "Test hasResponseHeaders(); given EmptyHttpHeaders isEmpty() return 'true'; then return 'false'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean NettyResponse.hasResponseHeaders()"})
  void testHasResponseHeaders_givenEmptyHttpHeadersIsEmptyReturnTrue_thenReturnFalse() {
    // Arrange
    EmptyHttpHeaders headers = mock(EmptyHttpHeaders.class);
    when(headers.isEmpty()).thenReturn(true);
    Uri uri =
        new Uri(
            "https://example.org/example",
            "https://example.org/example",
            "https://example.org/example",
            8080,
            "https://example.org/example",
            "https://example.org/example",
            "https://example.org/example");
    HttpVersion version = new HttpVersion("https://example.org/example", 1, 1, true);
    DefaultFullHttpResponse response =
        new DefaultFullHttpResponse(version, HttpResponseStatus.valueOf(1));

    NettyResponseStatus status = new NettyResponseStatus(uri, response, new EmbeddedChannel());

    NettyResponse nettyResponse = new NettyResponse(status, headers, new ArrayList<>());

    // Act
    boolean actualHasResponseHeadersResult = nettyResponse.hasResponseHeaders();

    // Assert
    verify(headers).isEmpty();
    assertFalse(actualHasResponseHeadersResult);
  }

  /**
   * Test {@link NettyResponse#hasResponseHeaders()}.
   *
   * <ul>
   *   <li>Then return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link NettyResponse#hasResponseHeaders()}
   */
  @Test
  @DisplayName("Test hasResponseHeaders(); then return 'false'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean NettyResponse.hasResponseHeaders()"})
  void testHasResponseHeaders_thenReturnFalse() {
    // Arrange
    Uri uri =
        new Uri(
            "https://example.org/example",
            "https://example.org/example",
            "https://example.org/example",
            8080,
            "https://example.org/example",
            "https://example.org/example",
            "https://example.org/example");
    HttpVersion version = new HttpVersion("https://example.org/example", 1, 1, true);
    DefaultFullHttpResponse response =
        new DefaultFullHttpResponse(version, HttpResponseStatus.valueOf(1));

    NettyResponseStatus status = new NettyResponseStatus(uri, response, new EmbeddedChannel());
    DefaultHttpHeaders headers = new DefaultHttpHeaders();

    NettyResponse nettyResponse = new NettyResponse(status, headers, new ArrayList<>());

    // Act and Assert
    assertFalse(nettyResponse.hasResponseHeaders());
  }

  /**
   * Test {@link NettyResponse#hasResponseBody()}.
   *
   * <ul>
   *   <li>Then return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link NettyResponse#hasResponseBody()}
   */
  @Test
  @DisplayName("Test hasResponseBody(); then return 'false'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean NettyResponse.hasResponseBody()"})
  void testHasResponseBody_thenReturnFalse() {
    // Arrange
    Uri uri =
        new Uri(
            "https://example.org/example",
            "https://example.org/example",
            "https://example.org/example",
            8080,
            "https://example.org/example",
            "https://example.org/example",
            "https://example.org/example");
    HttpVersion version = new HttpVersion("https://example.org/example", 1, 1, true);
    DefaultFullHttpResponse response =
        new DefaultFullHttpResponse(version, HttpResponseStatus.valueOf(1));

    NettyResponseStatus status = new NettyResponseStatus(uri, response, new EmbeddedChannel());
    DefaultHttpHeaders headers = new DefaultHttpHeaders();

    NettyResponse nettyResponse = new NettyResponse(status, headers, new ArrayList<>());

    // Act and Assert
    assertFalse(nettyResponse.hasResponseBody());
  }

  /**
   * Test {@link NettyResponse#hasResponseBody()}.
   *
   * <ul>
   *   <li>Then return {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link NettyResponse#hasResponseBody()}
   */
  @Test
  @DisplayName("Test hasResponseBody(); then return 'true'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean NettyResponse.hasResponseBody()"})
  void testHasResponseBody_thenReturnTrue() {
    // Arrange
    ArrayList<HttpResponseBodyPart> bodyParts = new ArrayList<>();
    DuplicatedByteBuf buf = new DuplicatedByteBuf(new EmptyByteBuf(new AdaptiveByteBufAllocator()));
    bodyParts.add(new EagerResponseBodyPart(buf, true));
    Uri uri =
        new Uri(
            "https://example.org/example",
            "https://example.org/example",
            "https://example.org/example",
            8080,
            "https://example.org/example",
            "https://example.org/example",
            "https://example.org/example");
    HttpVersion version = new HttpVersion("https://example.org/example", 1, 1, true);
    DefaultFullHttpResponse response =
        new DefaultFullHttpResponse(version, HttpResponseStatus.valueOf(1));

    NettyResponseStatus status = new NettyResponseStatus(uri, response, new EmbeddedChannel());

    NettyResponse nettyResponse = new NettyResponse(status, new DefaultHttpHeaders(), bodyParts);

    // Act and Assert
    assertTrue(nettyResponse.hasResponseBody());
  }

  /**
   * Test {@link NettyResponse#getResponseBodyAsBytes()}.
   *
   * <p>Method under test: {@link NettyResponse#getResponseBodyAsBytes()}
   */
  @Test
  @DisplayName("Test getResponseBodyAsBytes()")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"byte[] NettyResponse.getResponseBodyAsBytes()"})
  void testGetResponseBodyAsBytes() {
    // Arrange
    ArrayList<HttpResponseBodyPart> bodyParts = new ArrayList<>();
    bodyParts.add(new LazyResponseBodyPart(new EmptyByteBuf(new AdaptiveByteBufAllocator()), true));
    Uri uri =
        new Uri(
            "https://example.org/example",
            "https://example.org/example",
            "https://example.org/example",
            8080,
            "https://example.org/example",
            "https://example.org/example",
            "https://example.org/example");
    HttpVersion version = new HttpVersion("https://example.org/example", 1, 1, true);
    DefaultFullHttpResponse response =
        new DefaultFullHttpResponse(version, HttpResponseStatus.valueOf(1));

    NettyResponseStatus status = new NettyResponseStatus(uri, response, new EmbeddedChannel());

    NettyResponse nettyResponse = new NettyResponse(status, new DefaultHttpHeaders(), bodyParts);

    // Act and Assert
    assertArrayEquals(new byte[] {}, nettyResponse.getResponseBodyAsBytes());
  }

  /**
   * Test {@link NettyResponse#getResponseBodyAsBytes()}.
   *
   * <ul>
   *   <li>Given {@link DuplicatedByteBuf#DuplicatedByteBuf(ByteBuf)} with buffer is {@link
   *       EmptyByteBuf#EmptyByteBuf(ByteBufAllocator)}.
   * </ul>
   *
   * <p>Method under test: {@link NettyResponse#getResponseBodyAsBytes()}
   */
  @Test
  @DisplayName(
      "Test getResponseBodyAsBytes(); given DuplicatedByteBuf(ByteBuf) with buffer is EmptyByteBuf(ByteBufAllocator)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"byte[] NettyResponse.getResponseBodyAsBytes()"})
  void testGetResponseBodyAsBytes_givenDuplicatedByteBufWithBufferIsEmptyByteBuf() {
    // Arrange
    ArrayList<HttpResponseBodyPart> bodyParts = new ArrayList<>();
    DuplicatedByteBuf buf = new DuplicatedByteBuf(new EmptyByteBuf(new AdaptiveByteBufAllocator()));
    bodyParts.add(new EagerResponseBodyPart(buf, true));
    Uri uri =
        new Uri(
            "https://example.org/example",
            "https://example.org/example",
            "https://example.org/example",
            8080,
            "https://example.org/example",
            "https://example.org/example",
            "https://example.org/example");
    HttpVersion version = new HttpVersion("https://example.org/example", 1, 1, true);
    DefaultFullHttpResponse response =
        new DefaultFullHttpResponse(version, HttpResponseStatus.valueOf(1));

    NettyResponseStatus status = new NettyResponseStatus(uri, response, new EmbeddedChannel());

    NettyResponse nettyResponse = new NettyResponse(status, new DefaultHttpHeaders(), bodyParts);

    // Act and Assert
    assertArrayEquals(new byte[] {}, nettyResponse.getResponseBodyAsBytes());
  }

  /**
   * Test {@link NettyResponse#getResponseBodyAsBytes()}.
   *
   * <ul>
   *   <li>Given {@link ReadOnlyByteBuf#ReadOnlyByteBuf(ByteBuf)} with buffer is {@link
   *       EmptyByteBuf#EmptyByteBuf(ByteBufAllocator)}.
   * </ul>
   *
   * <p>Method under test: {@link NettyResponse#getResponseBodyAsBytes()}
   */
  @Test
  @DisplayName(
      "Test getResponseBodyAsBytes(); given ReadOnlyByteBuf(ByteBuf) with buffer is EmptyByteBuf(ByteBufAllocator)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"byte[] NettyResponse.getResponseBodyAsBytes()"})
  void testGetResponseBodyAsBytes_givenReadOnlyByteBufWithBufferIsEmptyByteBuf() {
    // Arrange
    ArrayList<HttpResponseBodyPart> bodyParts = new ArrayList<>();
    ReadOnlyByteBuf buf = new ReadOnlyByteBuf(new EmptyByteBuf(new AdaptiveByteBufAllocator()));
    bodyParts.add(new LazyResponseBodyPart(buf, true));
    Uri uri =
        new Uri(
            "https://example.org/example",
            "https://example.org/example",
            "https://example.org/example",
            8080,
            "https://example.org/example",
            "https://example.org/example",
            "https://example.org/example");
    HttpVersion version = new HttpVersion("https://example.org/example", 1, 1, true);
    DefaultFullHttpResponse response =
        new DefaultFullHttpResponse(version, HttpResponseStatus.valueOf(1));

    NettyResponseStatus status = new NettyResponseStatus(uri, response, new EmbeddedChannel());

    NettyResponse nettyResponse = new NettyResponse(status, new DefaultHttpHeaders(), bodyParts);

    // Act and Assert
    assertArrayEquals(new byte[] {}, nettyResponse.getResponseBodyAsBytes());
  }

  /**
   * Test {@link NettyResponse#getResponseBodyAsBytes()}.
   *
   * <ul>
   *   <li>Then calls {@link ByteBuf#capacity()}.
   * </ul>
   *
   * <p>Method under test: {@link NettyResponse#getResponseBodyAsBytes()}
   */
  @Test
  @DisplayName("Test getResponseBodyAsBytes(); then calls capacity()")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"byte[] NettyResponse.getResponseBodyAsBytes()"})
  void testGetResponseBodyAsBytes_thenCallsCapacity() {
    // Arrange
    ByteBuf buffer = mock(ByteBuf.class);
    when(buffer.capacity()).thenReturn(3);
    when(buffer.maxCapacity()).thenReturn(3);
    when(buffer.readerIndex()).thenReturn(1);
    when(buffer.writerIndex()).thenReturn(1);
    when(buffer.getBytes(anyInt(), Mockito.<byte[]>any(), anyInt(), anyInt()))
        .thenReturn(new DuplicatedByteBuf(new EmptyByteBuf(new AdaptiveByteBufAllocator())));
    DuplicatedByteBuf buffer2 = new DuplicatedByteBuf(buffer);
    ReadOnlyByteBuf buf = new ReadOnlyByteBuf(buffer2);
    LazyResponseBodyPart lazyResponseBodyPart = new LazyResponseBodyPart(buf, true);

    ArrayList<HttpResponseBodyPart> bodyParts = new ArrayList<>();
    bodyParts.add(lazyResponseBodyPart);
    Uri uri =
        new Uri(
            "https://example.org/example",
            "https://example.org/example",
            "https://example.org/example",
            8080,
            "https://example.org/example",
            "https://example.org/example",
            "https://example.org/example");
    HttpVersion version = new HttpVersion("https://example.org/example", 1, 1, true);
    DefaultFullHttpResponse response =
        new DefaultFullHttpResponse(version, HttpResponseStatus.valueOf(1));

    NettyResponseStatus status = new NettyResponseStatus(uri, response, new EmbeddedChannel());

    NettyResponse nettyResponse = new NettyResponse(status, new DefaultHttpHeaders(), bodyParts);

    // Act
    byte[] actualResponseBodyAsBytes = nettyResponse.getResponseBodyAsBytes();

    // Assert
    verify(buffer, atLeast(1)).capacity();
    verify(buffer).getBytes(eq(1), isA(byte[].class), eq(0), eq(0));
    verify(buffer).maxCapacity();
    verify(buffer).readerIndex();
    verify(buffer).writerIndex();
    assertArrayEquals(new byte[] {}, actualResponseBodyAsBytes);
  }

  /**
   * Test {@link NettyResponse#getResponseBodyAsBytes()}.
   *
   * <ul>
   *   <li>Then return empty array of {@code byte}.
   * </ul>
   *
   * <p>Method under test: {@link NettyResponse#getResponseBodyAsBytes()}
   */
  @Test
  @DisplayName("Test getResponseBodyAsBytes(); then return empty array of byte")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"byte[] NettyResponse.getResponseBodyAsBytes()"})
  void testGetResponseBodyAsBytes_thenReturnEmptyArrayOfByte() {
    // Arrange
    Uri uri =
        new Uri(
            "https://example.org/example",
            "https://example.org/example",
            "https://example.org/example",
            8080,
            "https://example.org/example",
            "https://example.org/example",
            "https://example.org/example");
    HttpVersion version = new HttpVersion("https://example.org/example", 1, 1, true);
    DefaultFullHttpResponse response =
        new DefaultFullHttpResponse(version, HttpResponseStatus.valueOf(1));

    NettyResponseStatus status = new NettyResponseStatus(uri, response, new EmbeddedChannel());
    DefaultHttpHeaders headers = new DefaultHttpHeaders();

    NettyResponse nettyResponse = new NettyResponse(status, headers, new ArrayList<>());

    // Act and Assert
    assertArrayEquals(new byte[] {}, nettyResponse.getResponseBodyAsBytes());
  }

  /**
   * Test {@link NettyResponse#getResponseBodyAsByteBuffer()}.
   *
   * <p>Method under test: {@link NettyResponse#getResponseBodyAsByteBuffer()}
   */
  @Test
  @DisplayName("Test getResponseBodyAsByteBuffer()")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"ByteBuffer NettyResponse.getResponseBodyAsByteBuffer()"})
  void testGetResponseBodyAsByteBuffer() {
    // Arrange
    ArrayList<HttpResponseBodyPart> bodyParts = new ArrayList<>();
    bodyParts.add(new LazyResponseBodyPart(new EmptyByteBuf(new AdaptiveByteBufAllocator()), true));
    Uri uri =
        new Uri(
            "https://example.org/example",
            "https://example.org/example",
            "https://example.org/example",
            8080,
            "https://example.org/example",
            "https://example.org/example",
            "https://example.org/example");
    HttpVersion version = new HttpVersion("https://example.org/example", 1, 1, true);
    DefaultFullHttpResponse response =
        new DefaultFullHttpResponse(version, HttpResponseStatus.valueOf(1));

    NettyResponseStatus status = new NettyResponseStatus(uri, response, new EmbeddedChannel());

    NettyResponse nettyResponse = new NettyResponse(status, new DefaultHttpHeaders(), bodyParts);

    // Act
    ByteBuffer actualResponseBodyAsByteBuffer = nettyResponse.getResponseBodyAsByteBuffer();

    // Assert
    assertEquals(0, actualResponseBodyAsByteBuffer.capacity());
    assertEquals(0, actualResponseBodyAsByteBuffer.limit());
    assertEquals(0, actualResponseBodyAsByteBuffer.position());
    assertFalse(actualResponseBodyAsByteBuffer.hasRemaining());
    assertTrue(actualResponseBodyAsByteBuffer.hasArray());
    assertArrayEquals(new byte[] {}, actualResponseBodyAsByteBuffer.array());
  }

  /**
   * Test {@link NettyResponse#getResponseBodyAsByteBuffer()}.
   *
   * <ul>
   *   <li>Given {@link DuplicatedByteBuf#DuplicatedByteBuf(ByteBuf)} with buffer is {@link
   *       EmptyByteBuf#EmptyByteBuf(ByteBufAllocator)}.
   * </ul>
   *
   * <p>Method under test: {@link NettyResponse#getResponseBodyAsByteBuffer()}
   */
  @Test
  @DisplayName(
      "Test getResponseBodyAsByteBuffer(); given DuplicatedByteBuf(ByteBuf) with buffer is EmptyByteBuf(ByteBufAllocator)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"ByteBuffer NettyResponse.getResponseBodyAsByteBuffer()"})
  void testGetResponseBodyAsByteBuffer_givenDuplicatedByteBufWithBufferIsEmptyByteBuf() {
    // Arrange
    ArrayList<HttpResponseBodyPart> bodyParts = new ArrayList<>();
    DuplicatedByteBuf buf = new DuplicatedByteBuf(new EmptyByteBuf(new AdaptiveByteBufAllocator()));
    bodyParts.add(new EagerResponseBodyPart(buf, true));
    Uri uri =
        new Uri(
            "https://example.org/example",
            "https://example.org/example",
            "https://example.org/example",
            8080,
            "https://example.org/example",
            "https://example.org/example",
            "https://example.org/example");
    HttpVersion version = new HttpVersion("https://example.org/example", 1, 1, true);
    DefaultFullHttpResponse response =
        new DefaultFullHttpResponse(version, HttpResponseStatus.valueOf(1));

    NettyResponseStatus status = new NettyResponseStatus(uri, response, new EmbeddedChannel());

    NettyResponse nettyResponse = new NettyResponse(status, new DefaultHttpHeaders(), bodyParts);

    // Act
    ByteBuffer actualResponseBodyAsByteBuffer = nettyResponse.getResponseBodyAsByteBuffer();

    // Assert
    assertEquals(0, actualResponseBodyAsByteBuffer.capacity());
    assertEquals(0, actualResponseBodyAsByteBuffer.limit());
    assertEquals(0, actualResponseBodyAsByteBuffer.position());
    assertFalse(actualResponseBodyAsByteBuffer.hasRemaining());
    assertTrue(actualResponseBodyAsByteBuffer.hasArray());
    assertArrayEquals(new byte[] {}, actualResponseBodyAsByteBuffer.array());
  }

  /**
   * Test {@link NettyResponse#getResponseBodyAsByteBuffer()}.
   *
   * <ul>
   *   <li>Given {@link ReadOnlyByteBuf#ReadOnlyByteBuf(ByteBuf)} with buffer is {@link
   *       EmptyByteBuf#EmptyByteBuf(ByteBufAllocator)}.
   * </ul>
   *
   * <p>Method under test: {@link NettyResponse#getResponseBodyAsByteBuffer()}
   */
  @Test
  @DisplayName(
      "Test getResponseBodyAsByteBuffer(); given ReadOnlyByteBuf(ByteBuf) with buffer is EmptyByteBuf(ByteBufAllocator)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"ByteBuffer NettyResponse.getResponseBodyAsByteBuffer()"})
  void testGetResponseBodyAsByteBuffer_givenReadOnlyByteBufWithBufferIsEmptyByteBuf() {
    // Arrange
    ArrayList<HttpResponseBodyPart> bodyParts = new ArrayList<>();
    ReadOnlyByteBuf buf = new ReadOnlyByteBuf(new EmptyByteBuf(new AdaptiveByteBufAllocator()));
    bodyParts.add(new LazyResponseBodyPart(buf, true));
    Uri uri =
        new Uri(
            "https://example.org/example",
            "https://example.org/example",
            "https://example.org/example",
            8080,
            "https://example.org/example",
            "https://example.org/example",
            "https://example.org/example");
    HttpVersion version = new HttpVersion("https://example.org/example", 1, 1, true);
    DefaultFullHttpResponse response =
        new DefaultFullHttpResponse(version, HttpResponseStatus.valueOf(1));

    NettyResponseStatus status = new NettyResponseStatus(uri, response, new EmbeddedChannel());

    NettyResponse nettyResponse = new NettyResponse(status, new DefaultHttpHeaders(), bodyParts);

    // Act
    ByteBuffer actualResponseBodyAsByteBuffer = nettyResponse.getResponseBodyAsByteBuffer();

    // Assert
    assertEquals(0, actualResponseBodyAsByteBuffer.capacity());
    assertEquals(0, actualResponseBodyAsByteBuffer.limit());
    assertEquals(0, actualResponseBodyAsByteBuffer.position());
    assertFalse(actualResponseBodyAsByteBuffer.hasRemaining());
    assertTrue(actualResponseBodyAsByteBuffer.hasArray());
    assertArrayEquals(new byte[] {}, actualResponseBodyAsByteBuffer.array());
  }

  /**
   * Test {@link NettyResponse#getResponseBodyAsByteBuffer()}.
   *
   * <ul>
   *   <li>Then calls {@link ByteBuf#capacity()}.
   * </ul>
   *
   * <p>Method under test: {@link NettyResponse#getResponseBodyAsByteBuffer()}
   */
  @Test
  @DisplayName("Test getResponseBodyAsByteBuffer(); then calls capacity()")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"ByteBuffer NettyResponse.getResponseBodyAsByteBuffer()"})
  void testGetResponseBodyAsByteBuffer_thenCallsCapacity() {
    // Arrange
    ByteBuf buffer = mock(ByteBuf.class);
    when(buffer.capacity()).thenReturn(3);
    when(buffer.maxCapacity()).thenReturn(3);
    when(buffer.readerIndex()).thenReturn(1);
    when(buffer.writerIndex()).thenReturn(1);
    when(buffer.getBytes(anyInt(), Mockito.<byte[]>any(), anyInt(), anyInt()))
        .thenReturn(new DuplicatedByteBuf(new EmptyByteBuf(new AdaptiveByteBufAllocator())));
    DuplicatedByteBuf buffer2 = new DuplicatedByteBuf(buffer);
    ReadOnlyByteBuf buf = new ReadOnlyByteBuf(buffer2);
    LazyResponseBodyPart lazyResponseBodyPart = new LazyResponseBodyPart(buf, true);

    ArrayList<HttpResponseBodyPart> bodyParts = new ArrayList<>();
    bodyParts.add(lazyResponseBodyPart);
    Uri uri =
        new Uri(
            "https://example.org/example",
            "https://example.org/example",
            "https://example.org/example",
            8080,
            "https://example.org/example",
            "https://example.org/example",
            "https://example.org/example");
    HttpVersion version = new HttpVersion("https://example.org/example", 1, 1, true);
    DefaultFullHttpResponse response =
        new DefaultFullHttpResponse(version, HttpResponseStatus.valueOf(1));

    NettyResponseStatus status = new NettyResponseStatus(uri, response, new EmbeddedChannel());

    NettyResponse nettyResponse = new NettyResponse(status, new DefaultHttpHeaders(), bodyParts);

    // Act
    ByteBuffer actualResponseBodyAsByteBuffer = nettyResponse.getResponseBodyAsByteBuffer();

    // Assert
    verify(buffer, atLeast(1)).capacity();
    verify(buffer).getBytes(eq(1), isA(byte[].class), eq(0), eq(0));
    verify(buffer).maxCapacity();
    verify(buffer).readerIndex();
    verify(buffer).writerIndex();
    assertEquals(0, actualResponseBodyAsByteBuffer.capacity());
    assertEquals(0, actualResponseBodyAsByteBuffer.limit());
    assertEquals(0, actualResponseBodyAsByteBuffer.position());
    assertFalse(actualResponseBodyAsByteBuffer.hasRemaining());
    assertTrue(actualResponseBodyAsByteBuffer.hasArray());
    assertArrayEquals(new byte[] {}, actualResponseBodyAsByteBuffer.array());
  }

  /**
   * Test {@link NettyResponse#getResponseBodyAsByteBuffer()}.
   *
   * <ul>
   *   <li>Then return capacity is zero.
   * </ul>
   *
   * <p>Method under test: {@link NettyResponse#getResponseBodyAsByteBuffer()}
   */
  @Test
  @DisplayName("Test getResponseBodyAsByteBuffer(); then return capacity is zero")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"ByteBuffer NettyResponse.getResponseBodyAsByteBuffer()"})
  void testGetResponseBodyAsByteBuffer_thenReturnCapacityIsZero() {
    // Arrange
    Uri uri =
        new Uri(
            "https://example.org/example",
            "https://example.org/example",
            "https://example.org/example",
            8080,
            "https://example.org/example",
            "https://example.org/example",
            "https://example.org/example");
    HttpVersion version = new HttpVersion("https://example.org/example", 1, 1, true);
    DefaultFullHttpResponse response =
        new DefaultFullHttpResponse(version, HttpResponseStatus.valueOf(1));

    NettyResponseStatus status = new NettyResponseStatus(uri, response, new EmbeddedChannel());
    DefaultHttpHeaders headers = new DefaultHttpHeaders();

    NettyResponse nettyResponse = new NettyResponse(status, headers, new ArrayList<>());

    // Act
    ByteBuffer actualResponseBodyAsByteBuffer = nettyResponse.getResponseBodyAsByteBuffer();

    // Assert
    assertEquals(0, actualResponseBodyAsByteBuffer.capacity());
    assertEquals(0, actualResponseBodyAsByteBuffer.limit());
    assertEquals(0, actualResponseBodyAsByteBuffer.position());
    assertFalse(actualResponseBodyAsByteBuffer.hasRemaining());
    assertTrue(actualResponseBodyAsByteBuffer.hasArray());
    assertArrayEquals(new byte[] {}, actualResponseBodyAsByteBuffer.array());
  }

  /**
   * Test {@link NettyResponse#getResponseBodyAsByteBuf()}.
   *
   * <p>Method under test: {@link NettyResponse#getResponseBodyAsByteBuf()}
   */
  @Test
  @DisplayName("Test getResponseBodyAsByteBuf()")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"ByteBuf NettyResponse.getResponseBodyAsByteBuf()"})
  void testGetResponseBodyAsByteBuf() {
    // Arrange
    ArrayList<HttpResponseBodyPart> bodyParts = new ArrayList<>();
    EmptyByteBuf buffer = new EmptyByteBuf(new AdaptiveByteBufAllocator());
    DuplicatedByteBuf buf = new DuplicatedByteBuf(buffer);
    bodyParts.add(new EagerResponseBodyPart(buf, true));
    Uri uri =
        new Uri(
            "https://example.org/example",
            "https://example.org/example",
            "https://example.org/example",
            8080,
            "https://example.org/example",
            "42",
            "https://example.org/example");
    HttpVersion version = new HttpVersion("https://example.org/example", 1, 1, true);
    DefaultFullHttpResponse response =
        new DefaultFullHttpResponse(version, HttpResponseStatus.valueOf(1));

    NettyResponseStatus status = new NettyResponseStatus(uri, response, new EmbeddedChannel());

    NettyResponse nettyResponse = new NettyResponse(status, new DefaultHttpHeaders(), bodyParts);

    // Act
    ByteBuf actualResponseBodyAsByteBuf = nettyResponse.getResponseBodyAsByteBuf();

    // Assert
    assertTrue(actualResponseBodyAsByteBuf instanceof CompositeByteBuf);
    assertEquals(buffer, actualResponseBodyAsByteBuf);
  }

  /**
   * Test {@link NettyResponse#getResponseBodyAsByteBuf()}.
   *
   * <ul>
   *   <li>Then return {@link CompositeByteBuf}.
   * </ul>
   *
   * <p>Method under test: {@link NettyResponse#getResponseBodyAsByteBuf()}
   */
  @Test
  @DisplayName("Test getResponseBodyAsByteBuf(); then return CompositeByteBuf")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"ByteBuf NettyResponse.getResponseBodyAsByteBuf()"})
  void testGetResponseBodyAsByteBuf_thenReturnCompositeByteBuf() {
    // Arrange
    ArrayList<HttpResponseBodyPart> bodyParts = new ArrayList<>();
    EmptyByteBuf buffer = new EmptyByteBuf(new AdaptiveByteBufAllocator());
    DuplicatedByteBuf buf = new DuplicatedByteBuf(buffer);
    bodyParts.add(new EagerResponseBodyPart(buf, true));
    Uri uri =
        new Uri(
            "https://example.org/example",
            "https://example.org/example",
            "https://example.org/example",
            8080,
            "https://example.org/example",
            "https://example.org/example",
            "https://example.org/example");
    HttpVersion version = new HttpVersion("https://example.org/example", 1, 1, true);
    DefaultFullHttpResponse response =
        new DefaultFullHttpResponse(version, HttpResponseStatus.valueOf(1));

    NettyResponseStatus status = new NettyResponseStatus(uri, response, new EmbeddedChannel());

    NettyResponse nettyResponse = new NettyResponse(status, new DefaultHttpHeaders(), bodyParts);

    // Act
    ByteBuf actualResponseBodyAsByteBuf = nettyResponse.getResponseBodyAsByteBuf();

    // Assert
    assertTrue(actualResponseBodyAsByteBuf instanceof CompositeByteBuf);
    assertEquals(buffer, actualResponseBodyAsByteBuf);
  }

  /**
   * Test {@link NettyResponse#getResponseBody()}.
   *
   * <p>Method under test: {@link NettyResponse#getResponseBody()}
   */
  @Test
  @DisplayName("Test getResponseBody()")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"String NettyResponse.getResponseBody()"})
  void testGetResponseBody() {
    // Arrange
    Uri uri =
        new Uri(
            "https://example.org/example",
            "https://example.org/example",
            "https://example.org/example",
            8080,
            "https://example.org/example",
            "https://example.org/example",
            "https://example.org/example");
    HttpVersion version = new HttpVersion("https://example.org/example", 1, 1, true);
    DefaultFullHttpResponse response =
        new DefaultFullHttpResponse(version, HttpResponseStatus.valueOf(1));

    NettyResponseStatus status = new NettyResponseStatus(uri, response, new EmbeddedChannel());
    NettyResponse nettyResponse = new NettyResponse(status, null, new ArrayList<>());

    // Act and Assert
    assertEquals("", nettyResponse.getResponseBody());
  }

  /**
   * Test {@link NettyResponse#getResponseBody(Charset)} with {@code Charset}.
   *
   * <p>Method under test: {@link NettyResponse#getResponseBody(Charset)}
   */
  @Test
  @DisplayName("Test getResponseBody(Charset) with 'Charset'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"String NettyResponse.getResponseBody(Charset)"})
  void testGetResponseBodyWithCharset() {
    // Arrange
    ArrayList<HttpResponseBodyPart> bodyParts = new ArrayList<>();
    bodyParts.add(new LazyResponseBodyPart(new EmptyByteBuf(new AdaptiveByteBufAllocator()), true));
    Uri uri =
        new Uri(
            "https://example.org/example",
            "https://example.org/example",
            "https://example.org/example",
            8080,
            "https://example.org/example",
            "https://example.org/example",
            "https://example.org/example");
    HttpVersion version = new HttpVersion("https://example.org/example", 1, 1, true);
    DefaultFullHttpResponse response =
        new DefaultFullHttpResponse(version, HttpResponseStatus.valueOf(1));

    NettyResponseStatus status = new NettyResponseStatus(uri, response, new EmbeddedChannel());

    NettyResponse nettyResponse = new NettyResponse(status, new DefaultHttpHeaders(), bodyParts);

    // Act and Assert
    assertEquals("", nettyResponse.getResponseBody(Charset.forName("UTF-8")));
  }

  /**
   * Test {@link NettyResponse#getResponseBody(Charset)} with {@code Charset}.
   *
   * <ul>
   *   <li>Given {@link DuplicatedByteBuf#DuplicatedByteBuf(ByteBuf)} with buffer is {@link
   *       EmptyByteBuf#EmptyByteBuf(ByteBufAllocator)}.
   * </ul>
   *
   * <p>Method under test: {@link NettyResponse#getResponseBody(Charset)}
   */
  @Test
  @DisplayName(
      "Test getResponseBody(Charset) with 'Charset'; given DuplicatedByteBuf(ByteBuf) with buffer is EmptyByteBuf(ByteBufAllocator)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"String NettyResponse.getResponseBody(Charset)"})
  void testGetResponseBodyWithCharset_givenDuplicatedByteBufWithBufferIsEmptyByteBuf() {
    // Arrange
    ArrayList<HttpResponseBodyPart> bodyParts = new ArrayList<>();
    DuplicatedByteBuf buf = new DuplicatedByteBuf(new EmptyByteBuf(new AdaptiveByteBufAllocator()));
    bodyParts.add(new EagerResponseBodyPart(buf, true));
    Uri uri =
        new Uri(
            "https://example.org/example",
            "https://example.org/example",
            "https://example.org/example",
            8080,
            "https://example.org/example",
            "https://example.org/example",
            "https://example.org/example");
    HttpVersion version = new HttpVersion("https://example.org/example", 1, 1, true);
    DefaultFullHttpResponse response =
        new DefaultFullHttpResponse(version, HttpResponseStatus.valueOf(1));

    NettyResponseStatus status = new NettyResponseStatus(uri, response, new EmbeddedChannel());

    NettyResponse nettyResponse = new NettyResponse(status, new DefaultHttpHeaders(), bodyParts);

    // Act and Assert
    assertEquals("", nettyResponse.getResponseBody(Charset.forName("UTF-8")));
  }

  /**
   * Test {@link NettyResponse#getResponseBody(Charset)} with {@code Charset}.
   *
   * <ul>
   *   <li>Given {@link ReadOnlyByteBuf#ReadOnlyByteBuf(ByteBuf)} with buffer is {@link
   *       EmptyByteBuf#EmptyByteBuf(ByteBufAllocator)}.
   * </ul>
   *
   * <p>Method under test: {@link NettyResponse#getResponseBody(Charset)}
   */
  @Test
  @DisplayName(
      "Test getResponseBody(Charset) with 'Charset'; given ReadOnlyByteBuf(ByteBuf) with buffer is EmptyByteBuf(ByteBufAllocator)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"String NettyResponse.getResponseBody(Charset)"})
  void testGetResponseBodyWithCharset_givenReadOnlyByteBufWithBufferIsEmptyByteBuf() {
    // Arrange
    ArrayList<HttpResponseBodyPart> bodyParts = new ArrayList<>();
    ReadOnlyByteBuf buf = new ReadOnlyByteBuf(new EmptyByteBuf(new AdaptiveByteBufAllocator()));
    bodyParts.add(new LazyResponseBodyPart(buf, true));
    Uri uri =
        new Uri(
            "https://example.org/example",
            "https://example.org/example",
            "https://example.org/example",
            8080,
            "https://example.org/example",
            "https://example.org/example",
            "https://example.org/example");
    HttpVersion version = new HttpVersion("https://example.org/example", 1, 1, true);
    DefaultFullHttpResponse response =
        new DefaultFullHttpResponse(version, HttpResponseStatus.valueOf(1));

    NettyResponseStatus status = new NettyResponseStatus(uri, response, new EmbeddedChannel());

    NettyResponse nettyResponse = new NettyResponse(status, new DefaultHttpHeaders(), bodyParts);

    // Act and Assert
    assertEquals("", nettyResponse.getResponseBody(Charset.forName("UTF-8")));
  }

  /**
   * Test {@link NettyResponse#getResponseBody(Charset)} with {@code Charset}.
   *
   * <ul>
   *   <li>Then calls {@link ByteBuf#capacity()}.
   * </ul>
   *
   * <p>Method under test: {@link NettyResponse#getResponseBody(Charset)}
   */
  @Test
  @DisplayName("Test getResponseBody(Charset) with 'Charset'; then calls capacity()")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"String NettyResponse.getResponseBody(Charset)"})
  void testGetResponseBodyWithCharset_thenCallsCapacity() {
    // Arrange
    ByteBuf buffer = mock(ByteBuf.class);
    when(buffer.capacity()).thenReturn(3);
    when(buffer.maxCapacity()).thenReturn(3);
    when(buffer.readerIndex()).thenReturn(1);
    when(buffer.writerIndex()).thenReturn(1);
    when(buffer.getBytes(anyInt(), Mockito.<byte[]>any(), anyInt(), anyInt()))
        .thenReturn(new DuplicatedByteBuf(new EmptyByteBuf(new AdaptiveByteBufAllocator())));
    DuplicatedByteBuf buffer2 = new DuplicatedByteBuf(buffer);
    ReadOnlyByteBuf buf = new ReadOnlyByteBuf(buffer2);
    LazyResponseBodyPart lazyResponseBodyPart = new LazyResponseBodyPart(buf, true);

    ArrayList<HttpResponseBodyPart> bodyParts = new ArrayList<>();
    bodyParts.add(lazyResponseBodyPart);
    Uri uri =
        new Uri(
            "https://example.org/example",
            "https://example.org/example",
            "https://example.org/example",
            8080,
            "https://example.org/example",
            "https://example.org/example",
            "https://example.org/example");
    HttpVersion version = new HttpVersion("https://example.org/example", 1, 1, true);
    DefaultFullHttpResponse response =
        new DefaultFullHttpResponse(version, HttpResponseStatus.valueOf(1));

    NettyResponseStatus status = new NettyResponseStatus(uri, response, new EmbeddedChannel());

    NettyResponse nettyResponse = new NettyResponse(status, new DefaultHttpHeaders(), bodyParts);

    // Act
    String actualResponseBody = nettyResponse.getResponseBody(Charset.forName("UTF-8"));

    // Assert
    verify(buffer, atLeast(1)).capacity();
    verify(buffer).getBytes(eq(1), isA(byte[].class), eq(0), eq(0));
    verify(buffer).maxCapacity();
    verify(buffer).readerIndex();
    verify(buffer).writerIndex();
    assertEquals("", actualResponseBody);
  }

  /**
   * Test {@link NettyResponse#getResponseBody(Charset)} with {@code Charset}.
   *
   * <ul>
   *   <li>Then return empty string.
   * </ul>
   *
   * <p>Method under test: {@link NettyResponse#getResponseBody(Charset)}
   */
  @Test
  @DisplayName("Test getResponseBody(Charset) with 'Charset'; then return empty string")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"String NettyResponse.getResponseBody(Charset)"})
  void testGetResponseBodyWithCharset_thenReturnEmptyString() {
    // Arrange
    Uri uri =
        new Uri(
            "https://example.org/example",
            "https://example.org/example",
            "https://example.org/example",
            8080,
            "https://example.org/example",
            "https://example.org/example",
            "https://example.org/example");
    HttpVersion version = new HttpVersion("https://example.org/example", 1, 1, true);
    DefaultFullHttpResponse response =
        new DefaultFullHttpResponse(version, HttpResponseStatus.valueOf(1));

    NettyResponseStatus status = new NettyResponseStatus(uri, response, new EmbeddedChannel());
    DefaultHttpHeaders headers = new DefaultHttpHeaders();

    NettyResponse nettyResponse = new NettyResponse(status, headers, new ArrayList<>());

    // Act and Assert
    assertEquals("", nettyResponse.getResponseBody(Charset.forName("UTF-8")));
  }

  /**
   * Test {@link NettyResponse#getResponseBody()}.
   *
   * <ul>
   *   <li>Given {@link EmptyByteBuf#EmptyByteBuf(ByteBufAllocator)} with alloc is {@link
   *       AdaptiveByteBufAllocator#AdaptiveByteBufAllocator()}.
   * </ul>
   *
   * <p>Method under test: {@link NettyResponse#getResponseBody()}
   */
  @Test
  @DisplayName(
      "Test getResponseBody(); given EmptyByteBuf(ByteBufAllocator) with alloc is AdaptiveByteBufAllocator()")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"String NettyResponse.getResponseBody()"})
  void testGetResponseBody_givenEmptyByteBufWithAllocIsAdaptiveByteBufAllocator() {
    // Arrange
    ArrayList<HttpResponseBodyPart> bodyParts = new ArrayList<>();
    DuplicatedByteBuf buf = new DuplicatedByteBuf(new EmptyByteBuf(new AdaptiveByteBufAllocator()));
    bodyParts.add(new EagerResponseBodyPart(buf, true));
    Uri uri =
        new Uri(
            "https://example.org/example",
            "https://example.org/example",
            "https://example.org/example",
            8080,
            "https://example.org/example",
            "https://example.org/example",
            "https://example.org/example");
    HttpVersion version = new HttpVersion("https://example.org/example", 1, 1, true);
    DefaultFullHttpResponse response =
        new DefaultFullHttpResponse(version, HttpResponseStatus.valueOf(1));

    NettyResponseStatus status = new NettyResponseStatus(uri, response, new EmbeddedChannel());

    NettyResponse nettyResponse = new NettyResponse(status, new DefaultHttpHeaders(), bodyParts);

    // Act and Assert
    assertEquals("", nettyResponse.getResponseBody());
  }

  /**
   * Test {@link NettyResponse#getResponseBody()}.
   *
   * <ul>
   *   <li>Then return empty string.
   * </ul>
   *
   * <p>Method under test: {@link NettyResponse#getResponseBody()}
   */
  @Test
  @DisplayName("Test getResponseBody(); then return empty string")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"String NettyResponse.getResponseBody()"})
  void testGetResponseBody_thenReturnEmptyString() {
    // Arrange
    Uri uri =
        new Uri(
            "https://example.org/example",
            "https://example.org/example",
            "https://example.org/example",
            8080,
            "https://example.org/example",
            "https://example.org/example",
            "https://example.org/example");
    HttpVersion version = new HttpVersion("https://example.org/example", 1, 1, true);
    DefaultFullHttpResponse response =
        new DefaultFullHttpResponse(version, HttpResponseStatus.valueOf(1));

    NettyResponseStatus status = new NettyResponseStatus(uri, response, new EmbeddedChannel());
    DefaultHttpHeaders headers = new DefaultHttpHeaders();

    NettyResponse nettyResponse = new NettyResponse(status, headers, new ArrayList<>());

    // Act and Assert
    assertEquals("", nettyResponse.getResponseBody());
  }

  /**
   * Test {@link NettyResponse#getResponseBodyAsStream()}.
   *
   * <p>Method under test: {@link NettyResponse#getResponseBodyAsStream()}
   */
  @Test
  @DisplayName("Test getResponseBodyAsStream()")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"InputStream NettyResponse.getResponseBodyAsStream()"})
  void testGetResponseBodyAsStream() throws IOException {
    // Arrange
    ArrayList<HttpResponseBodyPart> bodyParts = new ArrayList<>();
    bodyParts.add(new LazyResponseBodyPart(new EmptyByteBuf(new AdaptiveByteBufAllocator()), true));
    Uri uri =
        new Uri(
            "https://example.org/example",
            "https://example.org/example",
            "https://example.org/example",
            8080,
            "https://example.org/example",
            "https://example.org/example",
            "https://example.org/example");
    HttpVersion version = new HttpVersion("https://example.org/example", 1, 1, true);
    DefaultFullHttpResponse response =
        new DefaultFullHttpResponse(version, HttpResponseStatus.valueOf(1));

    NettyResponseStatus status = new NettyResponseStatus(uri, response, new EmbeddedChannel());

    NettyResponse nettyResponse = new NettyResponse(status, new DefaultHttpHeaders(), bodyParts);

    // Act and Assert
    int actualReadResult = nettyResponse.getResponseBodyAsStream().read(new byte[] {});
    assertEquals(-1, actualReadResult);
  }

  /**
   * Test {@link NettyResponse#getResponseBodyAsStream()}.
   *
   * <ul>
   *   <li>Given {@link DuplicatedByteBuf#DuplicatedByteBuf(ByteBuf)} with buffer is {@link
   *       EmptyByteBuf#EmptyByteBuf(ByteBufAllocator)}.
   * </ul>
   *
   * <p>Method under test: {@link NettyResponse#getResponseBodyAsStream()}
   */
  @Test
  @DisplayName(
      "Test getResponseBodyAsStream(); given DuplicatedByteBuf(ByteBuf) with buffer is EmptyByteBuf(ByteBufAllocator)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"InputStream NettyResponse.getResponseBodyAsStream()"})
  void testGetResponseBodyAsStream_givenDuplicatedByteBufWithBufferIsEmptyByteBuf()
      throws IOException {
    // Arrange
    ArrayList<HttpResponseBodyPart> bodyParts = new ArrayList<>();
    DuplicatedByteBuf buf = new DuplicatedByteBuf(new EmptyByteBuf(new AdaptiveByteBufAllocator()));
    bodyParts.add(new EagerResponseBodyPart(buf, true));
    Uri uri =
        new Uri(
            "https://example.org/example",
            "https://example.org/example",
            "https://example.org/example",
            8080,
            "https://example.org/example",
            "https://example.org/example",
            "https://example.org/example");
    HttpVersion version = new HttpVersion("https://example.org/example", 1, 1, true);
    DefaultFullHttpResponse response =
        new DefaultFullHttpResponse(version, HttpResponseStatus.valueOf(1));

    NettyResponseStatus status = new NettyResponseStatus(uri, response, new EmbeddedChannel());

    NettyResponse nettyResponse = new NettyResponse(status, new DefaultHttpHeaders(), bodyParts);

    // Act and Assert
    int actualReadResult = nettyResponse.getResponseBodyAsStream().read(new byte[] {});
    assertEquals(-1, actualReadResult);
  }

  /**
   * Test {@link NettyResponse#getResponseBodyAsStream()}.
   *
   * <ul>
   *   <li>Given {@link ReadOnlyByteBuf#ReadOnlyByteBuf(ByteBuf)} with buffer is {@link
   *       EmptyByteBuf#EmptyByteBuf(ByteBufAllocator)}.
   * </ul>
   *
   * <p>Method under test: {@link NettyResponse#getResponseBodyAsStream()}
   */
  @Test
  @DisplayName(
      "Test getResponseBodyAsStream(); given ReadOnlyByteBuf(ByteBuf) with buffer is EmptyByteBuf(ByteBufAllocator)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"InputStream NettyResponse.getResponseBodyAsStream()"})
  void testGetResponseBodyAsStream_givenReadOnlyByteBufWithBufferIsEmptyByteBuf()
      throws IOException {
    // Arrange
    ArrayList<HttpResponseBodyPart> bodyParts = new ArrayList<>();
    ReadOnlyByteBuf buf = new ReadOnlyByteBuf(new EmptyByteBuf(new AdaptiveByteBufAllocator()));
    bodyParts.add(new LazyResponseBodyPart(buf, true));
    Uri uri =
        new Uri(
            "https://example.org/example",
            "https://example.org/example",
            "https://example.org/example",
            8080,
            "https://example.org/example",
            "https://example.org/example",
            "https://example.org/example");
    HttpVersion version = new HttpVersion("https://example.org/example", 1, 1, true);
    DefaultFullHttpResponse response =
        new DefaultFullHttpResponse(version, HttpResponseStatus.valueOf(1));

    NettyResponseStatus status = new NettyResponseStatus(uri, response, new EmbeddedChannel());

    NettyResponse nettyResponse = new NettyResponse(status, new DefaultHttpHeaders(), bodyParts);

    // Act and Assert
    int actualReadResult = nettyResponse.getResponseBodyAsStream().read(new byte[] {});
    assertEquals(-1, actualReadResult);
  }

  /**
   * Test {@link NettyResponse#getResponseBodyAsStream()}.
   *
   * <ul>
   *   <li>Then calls {@link ByteBuf#capacity()}.
   * </ul>
   *
   * <p>Method under test: {@link NettyResponse#getResponseBodyAsStream()}
   */
  @Test
  @DisplayName("Test getResponseBodyAsStream(); then calls capacity()")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"InputStream NettyResponse.getResponseBodyAsStream()"})
  void testGetResponseBodyAsStream_thenCallsCapacity() throws IOException {
    // Arrange
    ByteBuf buffer = mock(ByteBuf.class);
    when(buffer.capacity()).thenReturn(3);
    when(buffer.maxCapacity()).thenReturn(3);
    when(buffer.readerIndex()).thenReturn(1);
    when(buffer.writerIndex()).thenReturn(1);
    when(buffer.getBytes(anyInt(), Mockito.<byte[]>any(), anyInt(), anyInt()))
        .thenReturn(new DuplicatedByteBuf(new EmptyByteBuf(new AdaptiveByteBufAllocator())));
    DuplicatedByteBuf buffer2 = new DuplicatedByteBuf(buffer);
    ReadOnlyByteBuf buf = new ReadOnlyByteBuf(buffer2);
    LazyResponseBodyPart lazyResponseBodyPart = new LazyResponseBodyPart(buf, true);

    ArrayList<HttpResponseBodyPart> bodyParts = new ArrayList<>();
    bodyParts.add(lazyResponseBodyPart);
    Uri uri =
        new Uri(
            "https://example.org/example",
            "https://example.org/example",
            "https://example.org/example",
            8080,
            "https://example.org/example",
            "https://example.org/example",
            "https://example.org/example");
    HttpVersion version = new HttpVersion("https://example.org/example", 1, 1, true);
    DefaultFullHttpResponse response =
        new DefaultFullHttpResponse(version, HttpResponseStatus.valueOf(1));

    NettyResponseStatus status = new NettyResponseStatus(uri, response, new EmbeddedChannel());

    NettyResponse nettyResponse = new NettyResponse(status, new DefaultHttpHeaders(), bodyParts);

    // Act
    InputStream actualResponseBodyAsStream = nettyResponse.getResponseBodyAsStream();

    // Assert
    verify(buffer, atLeast(1)).capacity();
    verify(buffer).getBytes(eq(1), isA(byte[].class), eq(0), eq(0));
    verify(buffer).maxCapacity();
    verify(buffer).readerIndex();
    verify(buffer).writerIndex();
    int actualReadResult = actualResponseBodyAsStream.read(new byte[] {});
    assertEquals(-1, actualReadResult);
  }

  /**
   * Test {@link NettyResponse#getResponseBodyAsStream()}.
   *
   * <ul>
   *   <li>Then return read is minus one.
   * </ul>
   *
   * <p>Method under test: {@link NettyResponse#getResponseBodyAsStream()}
   */
  @Test
  @DisplayName("Test getResponseBodyAsStream(); then return read is minus one")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"InputStream NettyResponse.getResponseBodyAsStream()"})
  void testGetResponseBodyAsStream_thenReturnReadIsMinusOne() throws IOException {
    // Arrange
    Uri uri =
        new Uri(
            "https://example.org/example",
            "https://example.org/example",
            "https://example.org/example",
            8080,
            "https://example.org/example",
            "https://example.org/example",
            "https://example.org/example");
    HttpVersion version = new HttpVersion("https://example.org/example", 1, 1, true);
    DefaultFullHttpResponse response =
        new DefaultFullHttpResponse(version, HttpResponseStatus.valueOf(1));

    NettyResponseStatus status = new NettyResponseStatus(uri, response, new EmbeddedChannel());
    DefaultHttpHeaders headers = new DefaultHttpHeaders();

    NettyResponse nettyResponse = new NettyResponse(status, headers, new ArrayList<>());

    // Act and Assert
    int actualReadResult = nettyResponse.getResponseBodyAsStream().read(new byte[] {});
    assertEquals(-1, actualReadResult);
  }

  /**
   * Test {@link NettyResponse#toString()}.
   *
   * <p>Method under test: {@link NettyResponse#toString()}
   */
  @Test
  @DisplayName("Test toString()")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"String NettyResponse.toString()"})
  void testToString() {
    // Arrange
    Uri uri =
        new Uri(
            "https://example.org/example",
            "https://example.org/example",
            "https://example.org/example",
            8080,
            "https://example.org/example",
            "https://example.org/example",
            "https://example.org/example");
    HttpVersion version = new HttpVersion("https://example.org/example", 1, 1, true);
    DefaultFullHttpResponse response =
        new DefaultFullHttpResponse(version, HttpResponseStatus.valueOf(1));

    NettyResponseStatus status = new NettyResponseStatus(uri, response, new EmbeddedChannel());
    NettyResponse nettyResponse = new NettyResponse(status, null, new ArrayList<>());

    // Act and Assert
    assertEquals(
        "NettyResponse {\n\tstatusCode=1\n\theaders=\n\tbody=\n\n}", nettyResponse.toString());
  }

  /**
   * Test {@link NettyResponse#toString()}.
   *
   * <p>Method under test: {@link NettyResponse#toString()}
   */
  @Test
  @DisplayName("Test toString()")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"String NettyResponse.toString()"})
  void testToString2() {
    // Arrange
    DefaultHttpHeaders headers = new DefaultHttpHeaders();
    headers.add((CharSequence) AsyncHttpClientConfigDefaults.ACQUIRE_FREE_CHANNEL_TIMEOUT, "Value");
    Uri uri =
        new Uri(
            "https://example.org/example",
            "https://example.org/example",
            "https://example.org/example",
            8080,
            "https://example.org/example",
            "https://example.org/example",
            "https://example.org/example");
    HttpVersion version = new HttpVersion("https://example.org/example", 1, 1, true);
    DefaultFullHttpResponse response =
        new DefaultFullHttpResponse(version, HttpResponseStatus.valueOf(1));

    NettyResponseStatus status = new NettyResponseStatus(uri, response, new EmbeddedChannel());

    NettyResponse nettyResponse = new NettyResponse(status, headers, new ArrayList<>());

    // Act and Assert
    assertEquals(
        "NettyResponse {\n\tstatusCode=1\n\theaders=\n\t\tacquireFreeChannelTimeout: Value\n\tbody=\n\n}",
        nettyResponse.toString());
  }

  /**
   * Test {@link NettyResponse#toString()}.
   *
   * <ul>
   *   <li>Given {@link EmptyByteBuf#EmptyByteBuf(ByteBufAllocator)} with alloc is {@link
   *       AdaptiveByteBufAllocator#AdaptiveByteBufAllocator()}.
   * </ul>
   *
   * <p>Method under test: {@link NettyResponse#toString()}
   */
  @Test
  @DisplayName(
      "Test toString(); given EmptyByteBuf(ByteBufAllocator) with alloc is AdaptiveByteBufAllocator()")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"String NettyResponse.toString()"})
  void testToString_givenEmptyByteBufWithAllocIsAdaptiveByteBufAllocator() {
    // Arrange
    ArrayList<HttpResponseBodyPart> bodyParts = new ArrayList<>();
    DuplicatedByteBuf buf = new DuplicatedByteBuf(new EmptyByteBuf(new AdaptiveByteBufAllocator()));
    bodyParts.add(new EagerResponseBodyPart(buf, true));
    Uri uri =
        new Uri(
            "https://example.org/example",
            "https://example.org/example",
            "https://example.org/example",
            8080,
            "https://example.org/example",
            "https://example.org/example",
            "https://example.org/example");
    HttpVersion version = new HttpVersion("https://example.org/example", 1, 1, true);
    DefaultFullHttpResponse response =
        new DefaultFullHttpResponse(version, HttpResponseStatus.valueOf(1));

    NettyResponseStatus status = new NettyResponseStatus(uri, response, new EmbeddedChannel());

    NettyResponse nettyResponse = new NettyResponse(status, new DefaultHttpHeaders(), bodyParts);

    // Act and Assert
    assertEquals(
        "NettyResponse {\n\tstatusCode=1\n\theaders=\n\tbody=\n\n}", nettyResponse.toString());
  }

  /**
   * Test {@link NettyResponse#toString()}.
   *
   * <ul>
   *   <li>Then return {@code NettyResponse { statusCode=1 headers= body= }}.
   * </ul>
   *
   * <p>Method under test: {@link NettyResponse#toString()}
   */
  @Test
  @DisplayName("Test toString(); then return 'NettyResponse { statusCode=1 headers= body= }'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"String NettyResponse.toString()"})
  void testToString_thenReturnNettyResponseStatusCode1HeadersBody() {
    // Arrange
    Uri uri =
        new Uri(
            "https://example.org/example",
            "https://example.org/example",
            "https://example.org/example",
            8080,
            "https://example.org/example",
            "https://example.org/example",
            "https://example.org/example");
    HttpVersion version = new HttpVersion("https://example.org/example", 1, 1, true);
    DefaultFullHttpResponse response =
        new DefaultFullHttpResponse(version, HttpResponseStatus.valueOf(1));

    NettyResponseStatus status = new NettyResponseStatus(uri, response, new EmbeddedChannel());
    DefaultHttpHeaders headers = new DefaultHttpHeaders();

    NettyResponse nettyResponse = new NettyResponse(status, headers, new ArrayList<>());

    // Act and Assert
    assertEquals(
        "NettyResponse {\n\tstatusCode=1\n\theaders=\n\tbody=\n\n}", nettyResponse.toString());
  }
}
