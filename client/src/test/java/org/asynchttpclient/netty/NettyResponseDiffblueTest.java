package org.asynchttpclient.netty;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.MethodsUnderTest;
import io.netty.buffer.AdaptiveByteBufAllocator;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.CompositeByteBuf;
import io.netty.buffer.DuplicatedByteBuf;
import io.netty.buffer.EmptyByteBuf;
import io.netty.channel.embedded.EmbeddedChannel;
import io.netty.handler.codec.http.DefaultFullHttpResponse;
import io.netty.handler.codec.http.DefaultHttpHeaders;
import io.netty.handler.codec.http.EmptyHttpHeaders;
import io.netty.handler.codec.http.HttpHeaders;
import io.netty.handler.codec.http.HttpResponseStatus;
import io.netty.handler.codec.http.HttpVersion;
import io.netty.handler.codec.http.cookie.Cookie;
import java.io.IOException;
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
   * <ul>
   *   <li>Then return one.</li>
   * </ul>
   * <p>
   * Method under test: {@link NettyResponse#getStatusCode()}
   */
  @Test
  @DisplayName("Test getStatusCode(); then return one")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"int NettyResponse.getStatusCode()"})
  void testGetStatusCode_thenReturnOne() {
    // Arrange
    Uri uri = new Uri("https://example.org/example", "https://example.org/example", "https://example.org/example", 8080,
        "https://example.org/example", "https://example.org/example", "https://example.org/example");

    HttpVersion version = new HttpVersion("https://example.org/example", 1, 1, true);

    DefaultFullHttpResponse response = new DefaultFullHttpResponse(version, HttpResponseStatus.valueOf(1));

    NettyResponseStatus status = new NettyResponseStatus(uri, response, new EmbeddedChannel());

    DefaultHttpHeaders headers = new DefaultHttpHeaders();

    // Act and Assert
    assertEquals(1, (new NettyResponse(status, headers, new ArrayList<>())).getStatusCode());
  }

  /**
   * Test {@link NettyResponse#getStatusText()}.
   * <ul>
   *   <li>Then return {@code Unknown Status (1)}.</li>
   * </ul>
   * <p>
   * Method under test: {@link NettyResponse#getStatusText()}
   */
  @Test
  @DisplayName("Test getStatusText(); then return 'Unknown Status (1)'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"String NettyResponse.getStatusText()"})
  void testGetStatusText_thenReturnUnknownStatus1() {
    // Arrange
    Uri uri = new Uri("https://example.org/example", "https://example.org/example", "https://example.org/example", 8080,
        "https://example.org/example", "https://example.org/example", "https://example.org/example");

    HttpVersion version = new HttpVersion("https://example.org/example", 1, 1, true);

    DefaultFullHttpResponse response = new DefaultFullHttpResponse(version, HttpResponseStatus.valueOf(1));

    NettyResponseStatus status = new NettyResponseStatus(uri, response, new EmbeddedChannel());

    DefaultHttpHeaders headers = new DefaultHttpHeaders();

    // Act and Assert
    assertEquals("Unknown Status (1)", (new NettyResponse(status, headers, new ArrayList<>())).getStatusText());
  }

  /**
   * Test {@link NettyResponse#getUri()}.
   * <p>
   * Method under test: {@link NettyResponse#getUri()}
   */
  @Test
  @DisplayName("Test getUri()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Uri NettyResponse.getUri()"})
  void testGetUri() {
    // Arrange
    Uri uri = new Uri("https://example.org/example", "https://example.org/example", "https://example.org/example", 8080,
        "https://example.org/example", "https://example.org/example", "https://example.org/example");

    HttpVersion version = new HttpVersion("https://example.org/example", 1, 1, true);

    DefaultFullHttpResponse response = new DefaultFullHttpResponse(version, HttpResponseStatus.valueOf(1));

    NettyResponseStatus status = new NettyResponseStatus(uri, response, new EmbeddedChannel());

    DefaultHttpHeaders headers = new DefaultHttpHeaders();

    // Act and Assert
    assertSame(uri, (new NettyResponse(status, headers, new ArrayList<>())).getUri());
  }

  /**
   * Test {@link NettyResponse#getContentType()}.
   * <p>
   * Method under test: {@link NettyResponse#getContentType()}
   */
  @Test
  @DisplayName("Test getContentType()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"String NettyResponse.getContentType()"})
  void testGetContentType() {
    // Arrange
    Uri uri = new Uri("https://example.org/example", "https://example.org/example", "https://example.org/example", 8080,
        "https://example.org/example", "https://example.org/example", "https://example.org/example");

    NettyResponseStatus status = new NettyResponseStatus(uri, null, new EmbeddedChannel());

    DefaultHttpHeaders headers = new DefaultHttpHeaders();

    // Act and Assert
    assertNull((new NettyResponse(status, headers, new ArrayList<>())).getContentType());
  }

  /**
   * Test {@link NettyResponse#getContentType()}.
   * <ul>
   *   <li>Then return {@code https://example.org/example}.</li>
   * </ul>
   * <p>
   * Method under test: {@link NettyResponse#getContentType()}
   */
  @Test
  @DisplayName("Test getContentType(); then return 'https://example.org/example'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"String NettyResponse.getContentType()"})
  void testGetContentType_thenReturnHttpsExampleOrgExample() {
    // Arrange
    EmptyHttpHeaders headers = mock(EmptyHttpHeaders.class);
    when(headers.get(Mockito.<CharSequence>any())).thenReturn("https://example.org/example");
    Uri uri = new Uri("https://example.org/example", "https://example.org/example", "https://example.org/example", 8080,
        "https://example.org/example", "https://example.org/example", "https://example.org/example");

    HttpVersion version = new HttpVersion("https://example.org/example", 1, 1, true);

    DefaultFullHttpResponse response = new DefaultFullHttpResponse(version, HttpResponseStatus.valueOf(1));

    NettyResponseStatus status = new NettyResponseStatus(uri, response, new EmbeddedChannel());

    // Act
    String actualContentType = (new NettyResponse(status, headers, new ArrayList<>())).getContentType();

    // Assert
    verify(headers).get(isA(CharSequence.class));
    assertEquals("https://example.org/example", actualContentType);
  }

  /**
   * Test {@link NettyResponse#getContentType()}.
   * <ul>
   *   <li>Then return {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link NettyResponse#getContentType()}
   */
  @Test
  @DisplayName("Test getContentType(); then return 'null'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"String NettyResponse.getContentType()"})
  void testGetContentType_thenReturnNull() {
    // Arrange
    Uri uri = new Uri("https://example.org/example", "https://example.org/example", "https://example.org/example", 8080,
        "https://example.org/example", "https://example.org/example", "https://example.org/example");

    HttpVersion version = new HttpVersion("https://example.org/example", 1, 1, true);

    DefaultFullHttpResponse response = new DefaultFullHttpResponse(version, HttpResponseStatus.valueOf(1));

    NettyResponseStatus status = new NettyResponseStatus(uri, response, new EmbeddedChannel());

    DefaultHttpHeaders headers = new DefaultHttpHeaders();

    // Act and Assert
    assertNull((new NettyResponse(status, headers, new ArrayList<>())).getContentType());
  }

  /**
   * Test {@link NettyResponse#getHeader(CharSequence)}.
   * <ul>
   *   <li>Then return {@code https://example.org/example}.</li>
   * </ul>
   * <p>
   * Method under test: {@link NettyResponse#getHeader(CharSequence)}
   */
  @Test
  @DisplayName("Test getHeader(CharSequence); then return 'https://example.org/example'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"String NettyResponse.getHeader(CharSequence)"})
  void testGetHeader_thenReturnHttpsExampleOrgExample() {
    // Arrange
    EmptyHttpHeaders headers = mock(EmptyHttpHeaders.class);
    when(headers.get(Mockito.<CharSequence>any())).thenReturn("https://example.org/example");
    Uri uri = new Uri("https://example.org/example", "https://example.org/example", "https://example.org/example", 8080,
        "https://example.org/example", "https://example.org/example", "https://example.org/example");

    HttpVersion version = new HttpVersion("https://example.org/example", 1, 1, true);

    DefaultFullHttpResponse response = new DefaultFullHttpResponse(version, HttpResponseStatus.valueOf(1));

    NettyResponseStatus status = new NettyResponseStatus(uri, response, new EmbeddedChannel());

    // Act
    String actualHeader = (new NettyResponse(status, headers, new ArrayList<>()))
        .getHeader(AsyncHttpClientConfigDefaults.ACQUIRE_FREE_CHANNEL_TIMEOUT);

    // Assert
    verify(headers).get(isA(CharSequence.class));
    assertEquals("https://example.org/example", actualHeader);
  }

  /**
   * Test {@link NettyResponse#getHeader(CharSequence)}.
   * <ul>
   *   <li>Then return {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link NettyResponse#getHeader(CharSequence)}
   */
  @Test
  @DisplayName("Test getHeader(CharSequence); then return 'null'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"String NettyResponse.getHeader(CharSequence)"})
  void testGetHeader_thenReturnNull() {
    // Arrange
    Uri uri = new Uri("https://example.org/example", "https://example.org/example", "https://example.org/example", 8080,
        "https://example.org/example", "https://example.org/example", "https://example.org/example");

    HttpVersion version = new HttpVersion("https://example.org/example", 1, 1, true);

    DefaultFullHttpResponse response = new DefaultFullHttpResponse(version, HttpResponseStatus.valueOf(1));

    NettyResponseStatus status = new NettyResponseStatus(uri, response, new EmbeddedChannel());

    DefaultHttpHeaders headers = new DefaultHttpHeaders();

    // Act and Assert
    assertNull((new NettyResponse(status, headers, new ArrayList<>()))
        .getHeader(AsyncHttpClientConfigDefaults.ACQUIRE_FREE_CHANNEL_TIMEOUT));
  }

  /**
   * Test {@link NettyResponse#getHeaders(CharSequence)} with {@code CharSequence}.
   * <ul>
   *   <li>Then calls {@link HttpHeaders#getAll(CharSequence)}.</li>
   * </ul>
   * <p>
   * Method under test: {@link NettyResponse#getHeaders(CharSequence)}
   */
  @Test
  @DisplayName("Test getHeaders(CharSequence) with 'CharSequence'; then calls getAll(CharSequence)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"List NettyResponse.getHeaders(CharSequence)"})
  void testGetHeadersWithCharSequence_thenCallsGetAll() {
    // Arrange
    EmptyHttpHeaders headers = mock(EmptyHttpHeaders.class);
    when(headers.getAll(Mockito.<CharSequence>any())).thenReturn(new ArrayList<>());
    Uri uri = new Uri("https://example.org/example", "https://example.org/example", "https://example.org/example", 8080,
        "https://example.org/example", "https://example.org/example", "https://example.org/example");

    HttpVersion version = new HttpVersion("https://example.org/example", 1, 1, true);

    DefaultFullHttpResponse response = new DefaultFullHttpResponse(version, HttpResponseStatus.valueOf(1));

    NettyResponseStatus status = new NettyResponseStatus(uri, response, new EmbeddedChannel());

    // Act
    List<String> actualHeaders = (new NettyResponse(status, headers, new ArrayList<>()))
        .getHeaders(AsyncHttpClientConfigDefaults.ACQUIRE_FREE_CHANNEL_TIMEOUT);

    // Assert
    verify(headers).getAll(isA(CharSequence.class));
    assertTrue(actualHeaders.isEmpty());
  }

  /**
   * Test {@link NettyResponse#getHeaders(CharSequence)} with {@code CharSequence}.
   * <ul>
   *   <li>Then return Empty.</li>
   * </ul>
   * <p>
   * Method under test: {@link NettyResponse#getHeaders(CharSequence)}
   */
  @Test
  @DisplayName("Test getHeaders(CharSequence) with 'CharSequence'; then return Empty")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"List NettyResponse.getHeaders(CharSequence)"})
  void testGetHeadersWithCharSequence_thenReturnEmpty() {
    // Arrange
    Uri uri = new Uri("https://example.org/example", "https://example.org/example", "https://example.org/example", 8080,
        "https://example.org/example", "https://example.org/example", "https://example.org/example");

    HttpVersion version = new HttpVersion("https://example.org/example", 1, 1, true);

    DefaultFullHttpResponse response = new DefaultFullHttpResponse(version, HttpResponseStatus.valueOf(1));

    NettyResponseStatus status = new NettyResponseStatus(uri, response, new EmbeddedChannel());

    DefaultHttpHeaders headers = new DefaultHttpHeaders();

    // Act and Assert
    assertTrue((new NettyResponse(status, headers, new ArrayList<>()))
        .getHeaders(AsyncHttpClientConfigDefaults.ACQUIRE_FREE_CHANNEL_TIMEOUT)
        .isEmpty());
  }

  /**
   * Test {@link NettyResponse#getHeaders()}.
   * <ul>
   *   <li>Then return {@link DefaultHttpHeaders#DefaultHttpHeaders()}.</li>
   * </ul>
   * <p>
   * Method under test: {@link NettyResponse#getHeaders()}
   */
  @Test
  @DisplayName("Test getHeaders(); then return DefaultHttpHeaders()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"HttpHeaders NettyResponse.getHeaders()"})
  void testGetHeaders_thenReturnDefaultHttpHeaders() {
    // Arrange
    Uri uri = new Uri("https://example.org/example", "https://example.org/example", "https://example.org/example", 8080,
        "https://example.org/example", "https://example.org/example", "https://example.org/example");

    HttpVersion version = new HttpVersion("https://example.org/example", 1, 1, true);

    DefaultFullHttpResponse response = new DefaultFullHttpResponse(version, HttpResponseStatus.valueOf(1));

    NettyResponseStatus status = new NettyResponseStatus(uri, response, new EmbeddedChannel());

    DefaultHttpHeaders headers = new DefaultHttpHeaders();

    // Act and Assert
    assertSame(headers, (new NettyResponse(status, headers, new ArrayList<>())).getHeaders());
  }

  /**
   * Test {@link NettyResponse#getHeaders()}.
   * <ul>
   *   <li>Then return {@link EmptyHttpHeaders#INSTANCE}.</li>
   * </ul>
   * <p>
   * Method under test: {@link NettyResponse#getHeaders()}
   */
  @Test
  @DisplayName("Test getHeaders(); then return INSTANCE")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"HttpHeaders NettyResponse.getHeaders()"})
  void testGetHeaders_thenReturnInstance() {
    // Arrange
    Uri uri = new Uri("https://example.org/example", "https://example.org/example", "https://example.org/example", 8080,
        "https://example.org/example", "https://example.org/example", "https://example.org/example");

    HttpVersion version = new HttpVersion("https://example.org/example", 1, 1, true);

    DefaultFullHttpResponse response = new DefaultFullHttpResponse(version, HttpResponseStatus.valueOf(1));

    NettyResponseStatus status = new NettyResponseStatus(uri, response, new EmbeddedChannel());

    // Act
    HttpHeaders actualHeaders = (new NettyResponse(status, null, new ArrayList<>())).getHeaders();

    // Assert
    assertSame(((EmptyHttpHeaders) actualHeaders).INSTANCE, actualHeaders);
  }

  /**
   * Test {@link NettyResponse#isRedirected()}.
   * <ul>
   *   <li>Then return {@code false}.</li>
   * </ul>
   * <p>
   * Method under test: {@link NettyResponse#isRedirected()}
   */
  @Test
  @DisplayName("Test isRedirected(); then return 'false'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean NettyResponse.isRedirected()"})
  void testIsRedirected_thenReturnFalse() {
    // Arrange
    Uri uri = new Uri("https://example.org/example", "https://example.org/example", "https://example.org/example", 8080,
        "https://example.org/example", "https://example.org/example", "https://example.org/example");

    HttpVersion version = new HttpVersion("https://example.org/example", 1, 1, true);

    DefaultFullHttpResponse response = new DefaultFullHttpResponse(version, HttpResponseStatus.valueOf(1));

    NettyResponseStatus status = new NettyResponseStatus(uri, response, new EmbeddedChannel());

    DefaultHttpHeaders headers = new DefaultHttpHeaders();

    // Act and Assert
    assertFalse((new NettyResponse(status, headers, new ArrayList<>())).isRedirected());
  }

  /**
   * Test {@link NettyResponse#isRedirected()}.
   * <ul>
   *   <li>Then return {@code true}.</li>
   * </ul>
   * <p>
   * Method under test: {@link NettyResponse#isRedirected()}
   */
  @Test
  @DisplayName("Test isRedirected(); then return 'true'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean NettyResponse.isRedirected()"})
  void testIsRedirected_thenReturnTrue() {
    // Arrange
    Uri uri = new Uri("https://example.org/example", "https://example.org/example", "https://example.org/example", 8080,
        "https://example.org/example", "https://example.org/example", "https://example.org/example");

    HttpVersion version = new HttpVersion("https://example.org/example", 1, 1, true);

    DefaultFullHttpResponse response = new DefaultFullHttpResponse(version, HttpResponseStatus.valueOf(301));

    NettyResponseStatus status = new NettyResponseStatus(uri, response, new EmbeddedChannel());

    DefaultHttpHeaders headers = new DefaultHttpHeaders();

    // Act and Assert
    assertTrue((new NettyResponse(status, headers, new ArrayList<>())).isRedirected());
  }

  /**
   * Test {@link NettyResponse#getCookies()}.
   * <p>
   * Method under test: {@link NettyResponse#getCookies()}
   */
  @Test
  @DisplayName("Test getCookies()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"List NettyResponse.getCookies()"})
  void testGetCookies() {
    // Arrange
    Uri uri = new Uri("https://example.org/example", "https://example.org/example", "https://example.org/example", 8080,
        "https://example.org/example", "https://example.org/example", "https://example.org/example");

    HttpVersion version = new HttpVersion("https://example.org/example", 1, 1, true);

    DefaultFullHttpResponse response = new DefaultFullHttpResponse(version, HttpResponseStatus.valueOf(1));

    NettyResponseStatus status = new NettyResponseStatus(uri, response, new EmbeddedChannel());

    DefaultHttpHeaders headers = new DefaultHttpHeaders();

    // Act and Assert
    assertTrue((new NettyResponse(status, headers, new ArrayList<>())).getCookies().isEmpty());
  }

  /**
   * Test {@link NettyResponse#getCookies()}.
   * <ul>
   *   <li>Given {@link ArrayList#ArrayList()} add {@code 42}.</li>
   *   <li>Then calls {@link HttpHeaders#getAll(CharSequence)}.</li>
   * </ul>
   * <p>
   * Method under test: {@link NettyResponse#getCookies()}
   */
  @Test
  @DisplayName("Test getCookies(); given ArrayList() add '42'; then calls getAll(CharSequence)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"List NettyResponse.getCookies()"})
  void testGetCookies_givenArrayListAdd42_thenCallsGetAll() {
    // Arrange
    ArrayList<String> stringList = new ArrayList<>();
    stringList.add("");
    stringList.add("42");
    stringList.add("foo");
    EmptyHttpHeaders headers = mock(EmptyHttpHeaders.class);
    when(headers.getAll(Mockito.<CharSequence>any())).thenReturn(stringList);
    Uri uri = new Uri("https://example.org/example", "https://example.org/example", "https://example.org/example", 8080,
        "https://example.org/example", "https://example.org/example", "https://example.org/example");

    HttpVersion version = new HttpVersion("https://example.org/example", 1, 1, true);

    DefaultFullHttpResponse response = new DefaultFullHttpResponse(version, HttpResponseStatus.valueOf(1));

    NettyResponseStatus status = new NettyResponseStatus(uri, response, new EmbeddedChannel());

    // Act
    List<Cookie> actualCookies = (new NettyResponse(status, headers, new ArrayList<>())).getCookies();

    // Assert
    verify(headers).getAll(isA(CharSequence.class));
    assertTrue(actualCookies.isEmpty());
  }

  /**
   * Test {@link NettyResponse#getCookies()}.
   * <ul>
   *   <li>Given {@link ArrayList#ArrayList()} add {@code foo}.</li>
   *   <li>Then calls {@link HttpHeaders#getAll(CharSequence)}.</li>
   * </ul>
   * <p>
   * Method under test: {@link NettyResponse#getCookies()}
   */
  @Test
  @DisplayName("Test getCookies(); given ArrayList() add 'foo'; then calls getAll(CharSequence)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"List NettyResponse.getCookies()"})
  void testGetCookies_givenArrayListAddFoo_thenCallsGetAll() {
    // Arrange
    ArrayList<String> stringList = new ArrayList<>();
    stringList.add("foo");
    EmptyHttpHeaders headers = mock(EmptyHttpHeaders.class);
    when(headers.getAll(Mockito.<CharSequence>any())).thenReturn(stringList);
    Uri uri = new Uri("https://example.org/example", "https://example.org/example", "https://example.org/example", 8080,
        "https://example.org/example", "https://example.org/example", "https://example.org/example");

    HttpVersion version = new HttpVersion("https://example.org/example", 1, 1, true);

    DefaultFullHttpResponse response = new DefaultFullHttpResponse(version, HttpResponseStatus.valueOf(1));

    NettyResponseStatus status = new NettyResponseStatus(uri, response, new EmbeddedChannel());

    // Act
    List<Cookie> actualCookies = (new NettyResponse(status, headers, new ArrayList<>())).getCookies();

    // Assert
    verify(headers).getAll(isA(CharSequence.class));
    assertTrue(actualCookies.isEmpty());
  }

  /**
   * Test {@link NettyResponse#getCookies()}.
   * <ul>
   *   <li>Given {@link EmptyHttpHeaders} {@link HttpHeaders#getAll(CharSequence)} return {@link ArrayList#ArrayList()}.</li>
   *   <li>Then calls {@link HttpHeaders#getAll(CharSequence)}.</li>
   * </ul>
   * <p>
   * Method under test: {@link NettyResponse#getCookies()}
   */
  @Test
  @DisplayName("Test getCookies(); given EmptyHttpHeaders getAll(CharSequence) return ArrayList(); then calls getAll(CharSequence)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"List NettyResponse.getCookies()"})
  void testGetCookies_givenEmptyHttpHeadersGetAllReturnArrayList_thenCallsGetAll() {
    // Arrange
    EmptyHttpHeaders headers = mock(EmptyHttpHeaders.class);
    when(headers.getAll(Mockito.<CharSequence>any())).thenReturn(new ArrayList<>());
    Uri uri = new Uri("https://example.org/example", "https://example.org/example", "https://example.org/example", 8080,
        "https://example.org/example", "https://example.org/example", "https://example.org/example");

    HttpVersion version = new HttpVersion("https://example.org/example", 1, 1, true);

    DefaultFullHttpResponse response = new DefaultFullHttpResponse(version, HttpResponseStatus.valueOf(1));

    NettyResponseStatus status = new NettyResponseStatus(uri, response, new EmbeddedChannel());

    // Act
    List<Cookie> actualCookies = (new NettyResponse(status, headers, new ArrayList<>())).getCookies();

    // Assert
    verify(headers, atLeast(1)).getAll(Mockito.<CharSequence>any());
    assertTrue(actualCookies.isEmpty());
  }

  /**
   * Test {@link NettyResponse#hasResponseStatus()}.
   * <ul>
   *   <li>Then return {@code false}.</li>
   * </ul>
   * <p>
   * Method under test: {@link NettyResponse#hasResponseStatus()}
   */
  @Test
  @DisplayName("Test hasResponseStatus(); then return 'false'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean NettyResponse.hasResponseStatus()"})
  void testHasResponseStatus_thenReturnFalse() {
    // Arrange
    DefaultHttpHeaders headers = new DefaultHttpHeaders();

    // Act and Assert
    assertFalse((new NettyResponse(null, headers, new ArrayList<>())).hasResponseStatus());
  }

  /**
   * Test {@link NettyResponse#hasResponseStatus()}.
   * <ul>
   *   <li>Then return {@code true}.</li>
   * </ul>
   * <p>
   * Method under test: {@link NettyResponse#hasResponseStatus()}
   */
  @Test
  @DisplayName("Test hasResponseStatus(); then return 'true'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean NettyResponse.hasResponseStatus()"})
  void testHasResponseStatus_thenReturnTrue() {
    // Arrange
    Uri uri = new Uri("https://example.org/example", "https://example.org/example", "https://example.org/example", 8080,
        "https://example.org/example", "https://example.org/example", "https://example.org/example");

    HttpVersion version = new HttpVersion("https://example.org/example", 1, 1, true);

    DefaultFullHttpResponse response = new DefaultFullHttpResponse(version, HttpResponseStatus.valueOf(1));

    NettyResponseStatus status = new NettyResponseStatus(uri, response, new EmbeddedChannel());

    DefaultHttpHeaders headers = new DefaultHttpHeaders();

    // Act and Assert
    assertTrue((new NettyResponse(status, headers, new ArrayList<>())).hasResponseStatus());
  }

  /**
   * Test {@link NettyResponse#hasResponseHeaders()}.
   * <ul>
   *   <li>Given {@link EmptyHttpHeaders} {@link EmptyHttpHeaders#isEmpty()} return {@code false}.</li>
   *   <li>Then return {@code true}.</li>
   * </ul>
   * <p>
   * Method under test: {@link NettyResponse#hasResponseHeaders()}
   */
  @Test
  @DisplayName("Test hasResponseHeaders(); given EmptyHttpHeaders isEmpty() return 'false'; then return 'true'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean NettyResponse.hasResponseHeaders()"})
  void testHasResponseHeaders_givenEmptyHttpHeadersIsEmptyReturnFalse_thenReturnTrue() {
    // Arrange
    EmptyHttpHeaders headers = mock(EmptyHttpHeaders.class);
    when(headers.isEmpty()).thenReturn(false);
    Uri uri = new Uri("https://example.org/example", "https://example.org/example", "https://example.org/example", 8080,
        "https://example.org/example", "https://example.org/example", "https://example.org/example");

    HttpVersion version = new HttpVersion("https://example.org/example", 1, 1, true);

    DefaultFullHttpResponse response = new DefaultFullHttpResponse(version, HttpResponseStatus.valueOf(1));

    NettyResponseStatus status = new NettyResponseStatus(uri, response, new EmbeddedChannel());

    // Act
    boolean actualHasResponseHeadersResult = (new NettyResponse(status, headers, new ArrayList<>()))
        .hasResponseHeaders();

    // Assert
    verify(headers).isEmpty();
    assertTrue(actualHasResponseHeadersResult);
  }

  /**
   * Test {@link NettyResponse#hasResponseHeaders()}.
   * <ul>
   *   <li>Given {@link EmptyHttpHeaders} {@link EmptyHttpHeaders#isEmpty()} return {@code true}.</li>
   *   <li>Then return {@code false}.</li>
   * </ul>
   * <p>
   * Method under test: {@link NettyResponse#hasResponseHeaders()}
   */
  @Test
  @DisplayName("Test hasResponseHeaders(); given EmptyHttpHeaders isEmpty() return 'true'; then return 'false'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean NettyResponse.hasResponseHeaders()"})
  void testHasResponseHeaders_givenEmptyHttpHeadersIsEmptyReturnTrue_thenReturnFalse() {
    // Arrange
    EmptyHttpHeaders headers = mock(EmptyHttpHeaders.class);
    when(headers.isEmpty()).thenReturn(true);
    Uri uri = new Uri("https://example.org/example", "https://example.org/example", "https://example.org/example", 8080,
        "https://example.org/example", "https://example.org/example", "https://example.org/example");

    HttpVersion version = new HttpVersion("https://example.org/example", 1, 1, true);

    DefaultFullHttpResponse response = new DefaultFullHttpResponse(version, HttpResponseStatus.valueOf(1));

    NettyResponseStatus status = new NettyResponseStatus(uri, response, new EmbeddedChannel());

    // Act
    boolean actualHasResponseHeadersResult = (new NettyResponse(status, headers, new ArrayList<>()))
        .hasResponseHeaders();

    // Assert
    verify(headers).isEmpty();
    assertFalse(actualHasResponseHeadersResult);
  }

  /**
   * Test {@link NettyResponse#hasResponseHeaders()}.
   * <ul>
   *   <li>Then return {@code false}.</li>
   * </ul>
   * <p>
   * Method under test: {@link NettyResponse#hasResponseHeaders()}
   */
  @Test
  @DisplayName("Test hasResponseHeaders(); then return 'false'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean NettyResponse.hasResponseHeaders()"})
  void testHasResponseHeaders_thenReturnFalse() {
    // Arrange
    Uri uri = new Uri("https://example.org/example", "https://example.org/example", "https://example.org/example", 8080,
        "https://example.org/example", "https://example.org/example", "https://example.org/example");

    HttpVersion version = new HttpVersion("https://example.org/example", 1, 1, true);

    DefaultFullHttpResponse response = new DefaultFullHttpResponse(version, HttpResponseStatus.valueOf(1));

    NettyResponseStatus status = new NettyResponseStatus(uri, response, new EmbeddedChannel());

    DefaultHttpHeaders headers = new DefaultHttpHeaders();

    // Act and Assert
    assertFalse((new NettyResponse(status, headers, new ArrayList<>())).hasResponseHeaders());
  }

  /**
   * Test {@link NettyResponse#hasResponseBody()}.
   * <ul>
   *   <li>Then return {@code false}.</li>
   * </ul>
   * <p>
   * Method under test: {@link NettyResponse#hasResponseBody()}
   */
  @Test
  @DisplayName("Test hasResponseBody(); then return 'false'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean NettyResponse.hasResponseBody()"})
  void testHasResponseBody_thenReturnFalse() {
    // Arrange
    Uri uri = new Uri("https://example.org/example", "https://example.org/example", "https://example.org/example", 8080,
        "https://example.org/example", "https://example.org/example", "https://example.org/example");

    HttpVersion version = new HttpVersion("https://example.org/example", 1, 1, true);

    DefaultFullHttpResponse response = new DefaultFullHttpResponse(version, HttpResponseStatus.valueOf(1));

    NettyResponseStatus status = new NettyResponseStatus(uri, response, new EmbeddedChannel());

    DefaultHttpHeaders headers = new DefaultHttpHeaders();

    // Act and Assert
    assertFalse((new NettyResponse(status, headers, new ArrayList<>())).hasResponseBody());
  }

  /**
   * Test {@link NettyResponse#hasResponseBody()}.
   * <ul>
   *   <li>Then return {@code true}.</li>
   * </ul>
   * <p>
   * Method under test: {@link NettyResponse#hasResponseBody()}
   */
  @Test
  @DisplayName("Test hasResponseBody(); then return 'true'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean NettyResponse.hasResponseBody()"})
  void testHasResponseBody_thenReturnTrue() {
    // Arrange
    ArrayList<HttpResponseBodyPart> bodyParts = new ArrayList<>();
    bodyParts
        .add(new EagerResponseBodyPart(new DuplicatedByteBuf(new EmptyByteBuf(new AdaptiveByteBufAllocator())), true));
    Uri uri = new Uri("https://example.org/example", "https://example.org/example", "https://example.org/example", 8080,
        "https://example.org/example", "https://example.org/example", "https://example.org/example");

    HttpVersion version = new HttpVersion("https://example.org/example", 1, 1, true);

    DefaultFullHttpResponse response = new DefaultFullHttpResponse(version, HttpResponseStatus.valueOf(1));

    NettyResponseStatus status = new NettyResponseStatus(uri, response, new EmbeddedChannel());

    // Act and Assert
    assertTrue((new NettyResponse(status, new DefaultHttpHeaders(), bodyParts)).hasResponseBody());
  }

  /**
   * Test {@link NettyResponse#getResponseBodyAsBytes()}.
   * <ul>
   *   <li>Given {@link EmptyByteBuf#EmptyByteBuf(ByteBufAllocator)} with alloc is {@link AdaptiveByteBufAllocator#AdaptiveByteBufAllocator()}.</li>
   * </ul>
   * <p>
   * Method under test: {@link NettyResponse#getResponseBodyAsBytes()}
   */
  @Test
  @DisplayName("Test getResponseBodyAsBytes(); given EmptyByteBuf(ByteBufAllocator) with alloc is AdaptiveByteBufAllocator()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"byte[] NettyResponse.getResponseBodyAsBytes()"})
  void testGetResponseBodyAsBytes_givenEmptyByteBufWithAllocIsAdaptiveByteBufAllocator() {
    // Arrange
    ArrayList<HttpResponseBodyPart> bodyParts = new ArrayList<>();
    bodyParts
        .add(new EagerResponseBodyPart(new DuplicatedByteBuf(new EmptyByteBuf(new AdaptiveByteBufAllocator())), true));
    Uri uri = new Uri("https://example.org/example", "https://example.org/example", "https://example.org/example", 8080,
        "https://example.org/example", "https://example.org/example", "https://example.org/example");

    HttpVersion version = new HttpVersion("https://example.org/example", 1, 1, true);

    DefaultFullHttpResponse response = new DefaultFullHttpResponse(version, HttpResponseStatus.valueOf(1));

    NettyResponseStatus status = new NettyResponseStatus(uri, response, new EmbeddedChannel());

    // Act and Assert
    assertArrayEquals(new byte[]{},
        (new NettyResponse(status, new DefaultHttpHeaders(), bodyParts)).getResponseBodyAsBytes());
  }

  /**
   * Test {@link NettyResponse#getResponseBodyAsBytes()}.
   * <ul>
   *   <li>Then return empty array of {@code byte}.</li>
   * </ul>
   * <p>
   * Method under test: {@link NettyResponse#getResponseBodyAsBytes()}
   */
  @Test
  @DisplayName("Test getResponseBodyAsBytes(); then return empty array of byte")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"byte[] NettyResponse.getResponseBodyAsBytes()"})
  void testGetResponseBodyAsBytes_thenReturnEmptyArrayOfByte() {
    // Arrange
    Uri uri = new Uri("https://example.org/example", "https://example.org/example", "https://example.org/example", 8080,
        "https://example.org/example", "https://example.org/example", "https://example.org/example");

    HttpVersion version = new HttpVersion("https://example.org/example", 1, 1, true);

    DefaultFullHttpResponse response = new DefaultFullHttpResponse(version, HttpResponseStatus.valueOf(1));

    NettyResponseStatus status = new NettyResponseStatus(uri, response, new EmbeddedChannel());

    DefaultHttpHeaders headers = new DefaultHttpHeaders();

    // Act and Assert
    assertArrayEquals(new byte[]{}, (new NettyResponse(status, headers, new ArrayList<>())).getResponseBodyAsBytes());
  }

  /**
   * Test {@link NettyResponse#getResponseBodyAsByteBuffer()}.
   * <p>
   * Method under test: {@link NettyResponse#getResponseBodyAsByteBuffer()}
   */
  @Test
  @DisplayName("Test getResponseBodyAsByteBuffer()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"ByteBuffer NettyResponse.getResponseBodyAsByteBuffer()"})
  void testGetResponseBodyAsByteBuffer() {
    // Arrange
    ArrayList<HttpResponseBodyPart> bodyParts = new ArrayList<>();
    bodyParts
        .add(new EagerResponseBodyPart(new DuplicatedByteBuf(new EmptyByteBuf(new AdaptiveByteBufAllocator())), true));
    Uri uri = new Uri("https://example.org/example", "https://example.org/example", "https://example.org/example", 8080,
        "https://example.org/example", "https://example.org/example", "https://example.org/example");

    HttpVersion version = new HttpVersion("https://example.org/example", 1, 1, true);

    DefaultFullHttpResponse response = new DefaultFullHttpResponse(version, HttpResponseStatus.valueOf(1));

    NettyResponseStatus status = new NettyResponseStatus(uri, response, new EmbeddedChannel());

    // Act
    ByteBuffer actualResponseBodyAsByteBuffer = (new NettyResponse(status, new DefaultHttpHeaders(), bodyParts))
        .getResponseBodyAsByteBuffer();

    // Assert
    assertEquals(0, actualResponseBodyAsByteBuffer.capacity());
    assertEquals(0, actualResponseBodyAsByteBuffer.limit());
    assertEquals(0, actualResponseBodyAsByteBuffer.position());
    assertFalse(actualResponseBodyAsByteBuffer.hasRemaining());
    assertTrue(actualResponseBodyAsByteBuffer.hasArray());
    assertArrayEquals(new byte[]{}, actualResponseBodyAsByteBuffer.array());
  }

  /**
   * Test {@link NettyResponse#getResponseBodyAsByteBuffer()}.
   * <ul>
   *   <li>Then return capacity is zero.</li>
   * </ul>
   * <p>
   * Method under test: {@link NettyResponse#getResponseBodyAsByteBuffer()}
   */
  @Test
  @DisplayName("Test getResponseBodyAsByteBuffer(); then return capacity is zero")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"ByteBuffer NettyResponse.getResponseBodyAsByteBuffer()"})
  void testGetResponseBodyAsByteBuffer_thenReturnCapacityIsZero() {
    // Arrange
    Uri uri = new Uri("https://example.org/example", "https://example.org/example", "https://example.org/example", 8080,
        "https://example.org/example", "https://example.org/example", "https://example.org/example");

    HttpVersion version = new HttpVersion("https://example.org/example", 1, 1, true);

    DefaultFullHttpResponse response = new DefaultFullHttpResponse(version, HttpResponseStatus.valueOf(1));

    NettyResponseStatus status = new NettyResponseStatus(uri, response, new EmbeddedChannel());

    DefaultHttpHeaders headers = new DefaultHttpHeaders();

    // Act
    ByteBuffer actualResponseBodyAsByteBuffer = (new NettyResponse(status, headers, new ArrayList<>()))
        .getResponseBodyAsByteBuffer();

    // Assert
    assertEquals(0, actualResponseBodyAsByteBuffer.capacity());
    assertEquals(0, actualResponseBodyAsByteBuffer.limit());
    assertEquals(0, actualResponseBodyAsByteBuffer.position());
    assertFalse(actualResponseBodyAsByteBuffer.hasRemaining());
    assertTrue(actualResponseBodyAsByteBuffer.hasArray());
    assertArrayEquals(new byte[]{}, actualResponseBodyAsByteBuffer.array());
  }

  /**
   * Test {@link NettyResponse#getResponseBodyAsByteBuf()}.
   * <p>
   * Method under test: {@link NettyResponse#getResponseBodyAsByteBuf()}
   */
  @Test
  @DisplayName("Test getResponseBodyAsByteBuf()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"ByteBuf NettyResponse.getResponseBodyAsByteBuf()"})
  void testGetResponseBodyAsByteBuf() {
    // Arrange
    ArrayList<HttpResponseBodyPart> bodyParts = new ArrayList<>();
    EmptyByteBuf buffer = new EmptyByteBuf(new AdaptiveByteBufAllocator());
    bodyParts.add(new EagerResponseBodyPart(new DuplicatedByteBuf(buffer), true));
    Uri uri = new Uri("https://example.org/example", "https://example.org/example", "https://example.org/example", 8080,
        "https://example.org/example", "https://example.org/example", Uri.HTTP);

    HttpVersion version = new HttpVersion("https://example.org/example", 1, 1, true);

    DefaultFullHttpResponse response = new DefaultFullHttpResponse(version, HttpResponseStatus.valueOf(1));

    NettyResponseStatus status = new NettyResponseStatus(uri, response, new EmbeddedChannel());

    // Act
    ByteBuf actualResponseBodyAsByteBuf = (new NettyResponse(status, new DefaultHttpHeaders(), bodyParts))
        .getResponseBodyAsByteBuf();

    // Assert
    assertTrue(actualResponseBodyAsByteBuf instanceof CompositeByteBuf);
    assertEquals(buffer, actualResponseBodyAsByteBuf);
  }

  /**
   * Test {@link NettyResponse#getResponseBodyAsByteBuf()}.
   * <ul>
   *   <li>Then return {@link CompositeByteBuf}.</li>
   * </ul>
   * <p>
   * Method under test: {@link NettyResponse#getResponseBodyAsByteBuf()}
   */
  @Test
  @DisplayName("Test getResponseBodyAsByteBuf(); then return CompositeByteBuf")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"ByteBuf NettyResponse.getResponseBodyAsByteBuf()"})
  void testGetResponseBodyAsByteBuf_thenReturnCompositeByteBuf() {
    // Arrange
    ArrayList<HttpResponseBodyPart> bodyParts = new ArrayList<>();
    EmptyByteBuf buffer = new EmptyByteBuf(new AdaptiveByteBufAllocator());
    bodyParts.add(new EagerResponseBodyPart(new DuplicatedByteBuf(buffer), true));
    Uri uri = new Uri("https://example.org/example", "https://example.org/example", "https://example.org/example", 8080,
        "https://example.org/example", "https://example.org/example", "https://example.org/example");

    HttpVersion version = new HttpVersion("https://example.org/example", 1, 1, true);

    DefaultFullHttpResponse response = new DefaultFullHttpResponse(version, HttpResponseStatus.valueOf(1));

    NettyResponseStatus status = new NettyResponseStatus(uri, response, new EmbeddedChannel());

    // Act
    ByteBuf actualResponseBodyAsByteBuf = (new NettyResponse(status, new DefaultHttpHeaders(), bodyParts))
        .getResponseBodyAsByteBuf();

    // Assert
    assertTrue(actualResponseBodyAsByteBuf instanceof CompositeByteBuf);
    assertEquals(buffer, actualResponseBodyAsByteBuf);
  }

  /**
   * Test {@link NettyResponse#getResponseBody()}.
   * <p>
   * Method under test: {@link NettyResponse#getResponseBody()}
   */
  @Test
  @DisplayName("Test getResponseBody()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"String NettyResponse.getResponseBody()"})
  void testGetResponseBody() {
    // Arrange
    Uri uri = new Uri("https://example.org/example", "https://example.org/example", "https://example.org/example", 8080,
        "https://example.org/example", "https://example.org/example", "https://example.org/example");

    HttpVersion version = new HttpVersion("https://example.org/example", 1, 1, true);

    DefaultFullHttpResponse response = new DefaultFullHttpResponse(version, HttpResponseStatus.valueOf(1));

    NettyResponseStatus status = new NettyResponseStatus(uri, response, new EmbeddedChannel());

    // Act and Assert
    assertEquals("", (new NettyResponse(status, null, new ArrayList<>())).getResponseBody());
  }

  /**
   * Test {@link NettyResponse#getResponseBody(Charset)} with {@code Charset}.
   * <p>
   * Method under test: {@link NettyResponse#getResponseBody(Charset)}
   */
  @Test
  @DisplayName("Test getResponseBody(Charset) with 'Charset'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"String NettyResponse.getResponseBody(Charset)"})
  void testGetResponseBodyWithCharset() {
    // Arrange
    ArrayList<HttpResponseBodyPart> bodyParts = new ArrayList<>();
    bodyParts
        .add(new EagerResponseBodyPart(new DuplicatedByteBuf(new EmptyByteBuf(new AdaptiveByteBufAllocator())), true));
    Uri uri = new Uri("https://example.org/example", "https://example.org/example", "https://example.org/example", 8080,
        "https://example.org/example", "https://example.org/example", "https://example.org/example");

    HttpVersion version = new HttpVersion("https://example.org/example", 1, 1, true);

    DefaultFullHttpResponse response = new DefaultFullHttpResponse(version, HttpResponseStatus.valueOf(1));

    NettyResponseStatus status = new NettyResponseStatus(uri, response, new EmbeddedChannel());

    NettyResponse nettyResponse = new NettyResponse(status, new DefaultHttpHeaders(), bodyParts);

    // Act and Assert
    assertEquals("", nettyResponse.getResponseBody(Charset.forName("UTF-8")));
  }

  /**
   * Test {@link NettyResponse#getResponseBody(Charset)} with {@code Charset}.
   * <ul>
   *   <li>Then return empty string.</li>
   * </ul>
   * <p>
   * Method under test: {@link NettyResponse#getResponseBody(Charset)}
   */
  @Test
  @DisplayName("Test getResponseBody(Charset) with 'Charset'; then return empty string")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"String NettyResponse.getResponseBody(Charset)"})
  void testGetResponseBodyWithCharset_thenReturnEmptyString() {
    // Arrange
    Uri uri = new Uri("https://example.org/example", "https://example.org/example", "https://example.org/example", 8080,
        "https://example.org/example", "https://example.org/example", "https://example.org/example");

    HttpVersion version = new HttpVersion("https://example.org/example", 1, 1, true);

    DefaultFullHttpResponse response = new DefaultFullHttpResponse(version, HttpResponseStatus.valueOf(1));

    NettyResponseStatus status = new NettyResponseStatus(uri, response, new EmbeddedChannel());

    DefaultHttpHeaders headers = new DefaultHttpHeaders();
    NettyResponse nettyResponse = new NettyResponse(status, headers, new ArrayList<>());

    // Act and Assert
    assertEquals("", nettyResponse.getResponseBody(Charset.forName("UTF-8")));
  }

  /**
   * Test {@link NettyResponse#getResponseBody()}.
   * <ul>
   *   <li>Given {@link EmptyByteBuf#EmptyByteBuf(ByteBufAllocator)} with alloc is {@link AdaptiveByteBufAllocator#AdaptiveByteBufAllocator()}.</li>
   * </ul>
   * <p>
   * Method under test: {@link NettyResponse#getResponseBody()}
   */
  @Test
  @DisplayName("Test getResponseBody(); given EmptyByteBuf(ByteBufAllocator) with alloc is AdaptiveByteBufAllocator()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"String NettyResponse.getResponseBody()"})
  void testGetResponseBody_givenEmptyByteBufWithAllocIsAdaptiveByteBufAllocator() {
    // Arrange
    ArrayList<HttpResponseBodyPart> bodyParts = new ArrayList<>();
    bodyParts
        .add(new EagerResponseBodyPart(new DuplicatedByteBuf(new EmptyByteBuf(new AdaptiveByteBufAllocator())), true));
    Uri uri = new Uri("https://example.org/example", "https://example.org/example", "https://example.org/example", 8080,
        "https://example.org/example", "https://example.org/example", "https://example.org/example");

    HttpVersion version = new HttpVersion("https://example.org/example", 1, 1, true);

    DefaultFullHttpResponse response = new DefaultFullHttpResponse(version, HttpResponseStatus.valueOf(1));

    NettyResponseStatus status = new NettyResponseStatus(uri, response, new EmbeddedChannel());

    // Act and Assert
    assertEquals("", (new NettyResponse(status, new DefaultHttpHeaders(), bodyParts)).getResponseBody());
  }

  /**
   * Test {@link NettyResponse#getResponseBody()}.
   * <ul>
   *   <li>Then return empty string.</li>
   * </ul>
   * <p>
   * Method under test: {@link NettyResponse#getResponseBody()}
   */
  @Test
  @DisplayName("Test getResponseBody(); then return empty string")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"String NettyResponse.getResponseBody()"})
  void testGetResponseBody_thenReturnEmptyString() {
    // Arrange
    Uri uri = new Uri("https://example.org/example", "https://example.org/example", "https://example.org/example", 8080,
        "https://example.org/example", "https://example.org/example", "https://example.org/example");

    HttpVersion version = new HttpVersion("https://example.org/example", 1, 1, true);

    DefaultFullHttpResponse response = new DefaultFullHttpResponse(version, HttpResponseStatus.valueOf(1));

    NettyResponseStatus status = new NettyResponseStatus(uri, response, new EmbeddedChannel());

    DefaultHttpHeaders headers = new DefaultHttpHeaders();

    // Act and Assert
    assertEquals("", (new NettyResponse(status, headers, new ArrayList<>())).getResponseBody());
  }

  /**
   * Test {@link NettyResponse#getResponseBodyAsStream()}.
   * <ul>
   *   <li>Given {@link EmptyByteBuf#EmptyByteBuf(ByteBufAllocator)} with alloc is {@link AdaptiveByteBufAllocator#AdaptiveByteBufAllocator()}.</li>
   * </ul>
   * <p>
   * Method under test: {@link NettyResponse#getResponseBodyAsStream()}
   */
  @Test
  @DisplayName("Test getResponseBodyAsStream(); given EmptyByteBuf(ByteBufAllocator) with alloc is AdaptiveByteBufAllocator()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"java.io.InputStream NettyResponse.getResponseBodyAsStream()"})
  void testGetResponseBodyAsStream_givenEmptyByteBufWithAllocIsAdaptiveByteBufAllocator() throws IOException {
    // Arrange
    ArrayList<HttpResponseBodyPart> bodyParts = new ArrayList<>();
    bodyParts
        .add(new EagerResponseBodyPart(new DuplicatedByteBuf(new EmptyByteBuf(new AdaptiveByteBufAllocator())), true));
    Uri uri = new Uri("https://example.org/example", "https://example.org/example", "https://example.org/example", 8080,
        "https://example.org/example", "https://example.org/example", "https://example.org/example");

    HttpVersion version = new HttpVersion("https://example.org/example", 1, 1, true);

    DefaultFullHttpResponse response = new DefaultFullHttpResponse(version, HttpResponseStatus.valueOf(1));

    NettyResponseStatus status = new NettyResponseStatus(uri, response, new EmbeddedChannel());

    // Act and Assert
    assertEquals(-1,
        (new NettyResponse(status, new DefaultHttpHeaders(), bodyParts)).getResponseBodyAsStream().read(new byte[]{}));
  }

  /**
   * Test {@link NettyResponse#getResponseBodyAsStream()}.
   * <ul>
   *   <li>Then return read is minus one.</li>
   * </ul>
   * <p>
   * Method under test: {@link NettyResponse#getResponseBodyAsStream()}
   */
  @Test
  @DisplayName("Test getResponseBodyAsStream(); then return read is minus one")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"java.io.InputStream NettyResponse.getResponseBodyAsStream()"})
  void testGetResponseBodyAsStream_thenReturnReadIsMinusOne() throws IOException {
    // Arrange
    Uri uri = new Uri("https://example.org/example", "https://example.org/example", "https://example.org/example", 8080,
        "https://example.org/example", "https://example.org/example", "https://example.org/example");

    HttpVersion version = new HttpVersion("https://example.org/example", 1, 1, true);

    DefaultFullHttpResponse response = new DefaultFullHttpResponse(version, HttpResponseStatus.valueOf(1));

    NettyResponseStatus status = new NettyResponseStatus(uri, response, new EmbeddedChannel());

    DefaultHttpHeaders headers = new DefaultHttpHeaders();

    // Act and Assert
    assertEquals(-1,
        (new NettyResponse(status, headers, new ArrayList<>())).getResponseBodyAsStream().read(new byte[]{}));
  }

  /**
   * Test {@link NettyResponse#toString()}.
   * <p>
   * Method under test: {@link NettyResponse#toString()}
   */
  @Test
  @DisplayName("Test toString()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"String NettyResponse.toString()"})
  void testToString() {
    // Arrange
    Uri uri = new Uri("https://example.org/example", "https://example.org/example", "https://example.org/example", 8080,
        "https://example.org/example", "https://example.org/example", "https://example.org/example");

    HttpVersion version = new HttpVersion("https://example.org/example", 1, 1, true);

    DefaultFullHttpResponse response = new DefaultFullHttpResponse(version, HttpResponseStatus.valueOf(1));

    NettyResponseStatus status = new NettyResponseStatus(uri, response, new EmbeddedChannel());

    // Act and Assert
    assertEquals("NettyResponse {\n\tstatusCode=1\n\theaders=\n\tbody=\n\n}",
        (new NettyResponse(status, null, new ArrayList<>())).toString());
  }

  /**
   * Test {@link NettyResponse#toString()}.
   * <p>
   * Method under test: {@link NettyResponse#toString()}
   */
  @Test
  @DisplayName("Test toString()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"String NettyResponse.toString()"})
  void testToString2() {
    // Arrange
    DefaultHttpHeaders headers = new DefaultHttpHeaders();
    headers.add((CharSequence) AsyncHttpClientConfigDefaults.ACQUIRE_FREE_CHANNEL_TIMEOUT, "Value");
    Uri uri = new Uri("https://example.org/example", "https://example.org/example", "https://example.org/example", 8080,
        "https://example.org/example", "https://example.org/example", "https://example.org/example");

    HttpVersion version = new HttpVersion("https://example.org/example", 1, 1, true);

    DefaultFullHttpResponse response = new DefaultFullHttpResponse(version, HttpResponseStatus.valueOf(1));

    NettyResponseStatus status = new NettyResponseStatus(uri, response, new EmbeddedChannel());

    // Act and Assert
    assertEquals("NettyResponse {\n\tstatusCode=1\n\theaders=\n\t\tacquireFreeChannelTimeout: Value\n\tbody=\n\n}",
        (new NettyResponse(status, headers, new ArrayList<>())).toString());
  }

  /**
   * Test {@link NettyResponse#toString()}.
   * <ul>
   *   <li>Given {@link EmptyByteBuf#EmptyByteBuf(ByteBufAllocator)} with alloc is {@link AdaptiveByteBufAllocator#AdaptiveByteBufAllocator()}.</li>
   * </ul>
   * <p>
   * Method under test: {@link NettyResponse#toString()}
   */
  @Test
  @DisplayName("Test toString(); given EmptyByteBuf(ByteBufAllocator) with alloc is AdaptiveByteBufAllocator()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"String NettyResponse.toString()"})
  void testToString_givenEmptyByteBufWithAllocIsAdaptiveByteBufAllocator() {
    // Arrange
    ArrayList<HttpResponseBodyPart> bodyParts = new ArrayList<>();
    bodyParts
        .add(new EagerResponseBodyPart(new DuplicatedByteBuf(new EmptyByteBuf(new AdaptiveByteBufAllocator())), true));
    Uri uri = new Uri("https://example.org/example", "https://example.org/example", "https://example.org/example", 8080,
        "https://example.org/example", "https://example.org/example", "https://example.org/example");

    HttpVersion version = new HttpVersion("https://example.org/example", 1, 1, true);

    DefaultFullHttpResponse response = new DefaultFullHttpResponse(version, HttpResponseStatus.valueOf(1));

    NettyResponseStatus status = new NettyResponseStatus(uri, response, new EmbeddedChannel());

    // Act and Assert
    assertEquals("NettyResponse {\n\tstatusCode=1\n\theaders=\n\tbody=\n\n}",
        (new NettyResponse(status, new DefaultHttpHeaders(), bodyParts)).toString());
  }

  /**
   * Test {@link NettyResponse#toString()}.
   * <ul>
   *   <li>Then return {@code NettyResponse { statusCode=1 headers= body= }}.</li>
   * </ul>
   * <p>
   * Method under test: {@link NettyResponse#toString()}
   */
  @Test
  @DisplayName("Test toString(); then return 'NettyResponse { statusCode=1 headers= body= }'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"String NettyResponse.toString()"})
  void testToString_thenReturnNettyResponseStatusCode1HeadersBody() {
    // Arrange
    Uri uri = new Uri("https://example.org/example", "https://example.org/example", "https://example.org/example", 8080,
        "https://example.org/example", "https://example.org/example", "https://example.org/example");

    HttpVersion version = new HttpVersion("https://example.org/example", 1, 1, true);

    DefaultFullHttpResponse response = new DefaultFullHttpResponse(version, HttpResponseStatus.valueOf(1));

    NettyResponseStatus status = new NettyResponseStatus(uri, response, new EmbeddedChannel());

    DefaultHttpHeaders headers = new DefaultHttpHeaders();

    // Act and Assert
    assertEquals("NettyResponse {\n\tstatusCode=1\n\theaders=\n\tbody=\n\n}",
        (new NettyResponse(status, headers, new ArrayList<>())).toString());
  }
}
