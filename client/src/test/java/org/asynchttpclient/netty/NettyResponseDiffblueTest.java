package org.asynchttpclient.netty;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import io.netty.channel.embedded.EmbeddedChannel;
import io.netty.handler.codec.http.DefaultFullHttpResponse;
import io.netty.handler.codec.http.DefaultHttpHeaders;
import io.netty.handler.codec.http.HttpResponseStatus;
import io.netty.handler.codec.http.HttpVersion;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import org.asynchttpclient.config.AsyncHttpClientConfigDefaults;
import org.asynchttpclient.uri.Uri;
import org.junit.jupiter.api.Test;

class NettyResponseDiffblueTest {
  /**
   * Method under test: {@link NettyResponse#getStatusCode()}
   */
  @Test
  void testGetStatusCode() {
    // Arrange
    Uri uri = mock(Uri.class);
    HttpVersion version = new HttpVersion("https://example.org/example", 1, 1, true);

    DefaultFullHttpResponse response = new DefaultFullHttpResponse(version, HttpResponseStatus.valueOf(1));

    NettyResponseStatus status = new NettyResponseStatus(uri, response, new EmbeddedChannel());

    DefaultHttpHeaders headers = new DefaultHttpHeaders();

    // Act and Assert
    assertEquals(1, (new NettyResponse(status, headers, new ArrayList<>())).getStatusCode());
  }

  /**
   * Method under test: {@link NettyResponse#getStatusText()}
   */
  @Test
  void testGetStatusText() {
    // Arrange
    Uri uri = mock(Uri.class);
    HttpVersion version = new HttpVersion("https://example.org/example", 1, 1, true);

    DefaultFullHttpResponse response = new DefaultFullHttpResponse(version, HttpResponseStatus.valueOf(1));

    NettyResponseStatus status = new NettyResponseStatus(uri, response, new EmbeddedChannel());

    DefaultHttpHeaders headers = new DefaultHttpHeaders();

    // Act and Assert
    assertEquals("Unknown Status (1)", (new NettyResponse(status, headers, new ArrayList<>())).getStatusText());
  }

  /**
   * Method under test: {@link NettyResponse#getUri()}
   */
  @Test
  void testGetUri() {
    // Arrange
    NettyResponseStatus status = mock(NettyResponseStatus.class);
    Uri uri = new Uri("https://example.org/example", "https://example.org/example", "https://example.org/example", 8080,
        "https://example.org/example", "https://example.org/example", "https://example.org/example");

    when(status.getUri()).thenReturn(uri);
    DefaultHttpHeaders headers = new DefaultHttpHeaders();

    // Act
    Uri actualUri = (new NettyResponse(status, headers, new ArrayList<>())).getUri();

    // Assert
    verify(status).getUri();
    assertSame(uri, actualUri);
  }

  /**
   * Method under test: {@link NettyResponse#getContentType()}
   */
  @Test
  void testGetContentType() {
    // Arrange
    Uri uri = mock(Uri.class);
    HttpVersion version = new HttpVersion("https://example.org/example", 1, 1, true);

    DefaultFullHttpResponse response = new DefaultFullHttpResponse(version, HttpResponseStatus.valueOf(1));

    NettyResponseStatus status = new NettyResponseStatus(uri, response, new EmbeddedChannel());

    DefaultHttpHeaders headers = new DefaultHttpHeaders();

    // Act and Assert
    assertNull((new NettyResponse(status, headers, new ArrayList<>())).getContentType());
  }

  /**
   * Method under test: {@link NettyResponse#getHeader(CharSequence)}
   */
  @Test
  void testGetHeader() {
    // Arrange
    Uri uri = mock(Uri.class);
    HttpVersion version = new HttpVersion("https://example.org/example", 1, 1, true);

    DefaultFullHttpResponse response = new DefaultFullHttpResponse(version, HttpResponseStatus.valueOf(1));

    NettyResponseStatus status = new NettyResponseStatus(uri, response, new EmbeddedChannel());

    DefaultHttpHeaders headers = new DefaultHttpHeaders();

    // Act and Assert
    assertNull((new NettyResponse(status, headers, new ArrayList<>()))
        .getHeader(AsyncHttpClientConfigDefaults.ACQUIRE_FREE_CHANNEL_TIMEOUT));
  }

  /**
   * Method under test: {@link NettyResponse#getHeaders()}
   */
  @Test
  void testGetHeaders() {
    // Arrange
    Uri uri = mock(Uri.class);
    HttpVersion version = new HttpVersion("https://example.org/example", 1, 1, true);

    DefaultFullHttpResponse response = new DefaultFullHttpResponse(version, HttpResponseStatus.valueOf(1));

    NettyResponseStatus status = new NettyResponseStatus(uri, response, new EmbeddedChannel());

    DefaultHttpHeaders headers = new DefaultHttpHeaders();

    // Act and Assert
    assertSame(headers, (new NettyResponse(status, headers, new ArrayList<>())).getHeaders());
  }

  /**
   * Method under test: {@link NettyResponse#getHeaders(CharSequence)}
   */
  @Test
  void testGetHeaders2() {
    // Arrange
    Uri uri = mock(Uri.class);
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
   * Method under test: {@link NettyResponse#isRedirected()}
   */
  @Test
  void testIsRedirected() {
    // Arrange
    Uri uri = mock(Uri.class);
    HttpVersion version = new HttpVersion("https://example.org/example", 1, 1, true);

    DefaultFullHttpResponse response = new DefaultFullHttpResponse(version, HttpResponseStatus.valueOf(1));

    NettyResponseStatus status = new NettyResponseStatus(uri, response, new EmbeddedChannel());

    DefaultHttpHeaders headers = new DefaultHttpHeaders();

    // Act and Assert
    assertFalse((new NettyResponse(status, headers, new ArrayList<>())).isRedirected());
  }

  /**
   * Method under test: {@link NettyResponse#getCookies()}
   */
  @Test
  void testGetCookies() {
    // Arrange
    Uri uri = mock(Uri.class);
    HttpVersion version = new HttpVersion("https://example.org/example", 1, 1, true);

    DefaultFullHttpResponse response = new DefaultFullHttpResponse(version, HttpResponseStatus.valueOf(1));

    NettyResponseStatus status = new NettyResponseStatus(uri, response, new EmbeddedChannel());

    DefaultHttpHeaders headers = new DefaultHttpHeaders();

    // Act and Assert
    assertTrue((new NettyResponse(status, headers, new ArrayList<>())).getCookies().isEmpty());
  }

  /**
   * Method under test: {@link NettyResponse#hasResponseStatus()}
   */
  @Test
  void testHasResponseStatus() {
    // Arrange
    Uri uri = mock(Uri.class);
    HttpVersion version = new HttpVersion("https://example.org/example", 1, 1, true);

    DefaultFullHttpResponse response = new DefaultFullHttpResponse(version, HttpResponseStatus.valueOf(1));

    NettyResponseStatus status = new NettyResponseStatus(uri, response, new EmbeddedChannel());

    DefaultHttpHeaders headers = new DefaultHttpHeaders();

    // Act and Assert
    assertTrue((new NettyResponse(status, headers, new ArrayList<>())).hasResponseStatus());
  }

  /**
   * Method under test: {@link NettyResponse#hasResponseStatus()}
   */
  @Test
  void testHasResponseStatus2() {
    // Arrange
    DefaultHttpHeaders headers = new DefaultHttpHeaders();

    // Act and Assert
    assertFalse((new NettyResponse(null, headers, new ArrayList<>())).hasResponseStatus());
  }

  /**
   * Method under test: {@link NettyResponse#hasResponseHeaders()}
   */
  @Test
  void testHasResponseHeaders() {
    // Arrange
    Uri uri = mock(Uri.class);
    HttpVersion version = new HttpVersion("https://example.org/example", 1, 1, true);

    DefaultFullHttpResponse response = new DefaultFullHttpResponse(version, HttpResponseStatus.valueOf(1));

    NettyResponseStatus status = new NettyResponseStatus(uri, response, new EmbeddedChannel());

    DefaultHttpHeaders headers = new DefaultHttpHeaders();

    // Act and Assert
    assertFalse((new NettyResponse(status, headers, new ArrayList<>())).hasResponseHeaders());
  }

  /**
   * Method under test: {@link NettyResponse#hasResponseBody()}
   */
  @Test
  void testHasResponseBody() {
    // Arrange
    Uri uri = mock(Uri.class);
    HttpVersion version = new HttpVersion("https://example.org/example", 1, 1, true);

    DefaultFullHttpResponse response = new DefaultFullHttpResponse(version, HttpResponseStatus.valueOf(1));

    NettyResponseStatus status = new NettyResponseStatus(uri, response, new EmbeddedChannel());

    DefaultHttpHeaders headers = new DefaultHttpHeaders();

    // Act and Assert
    assertFalse((new NettyResponse(status, headers, new ArrayList<>())).hasResponseBody());
  }

  /**
   * Method under test: {@link NettyResponse#getResponseBodyAsBytes()}
   */
  @Test
  void testGetResponseBodyAsBytes() {
    // Arrange
    Uri uri = mock(Uri.class);
    HttpVersion version = new HttpVersion("https://example.org/example", 1, 1, true);

    DefaultFullHttpResponse response = new DefaultFullHttpResponse(version, HttpResponseStatus.valueOf(1));

    NettyResponseStatus status = new NettyResponseStatus(uri, response, new EmbeddedChannel());

    DefaultHttpHeaders headers = new DefaultHttpHeaders();

    // Act and Assert
    assertEquals(0, (new NettyResponse(status, headers, new ArrayList<>())).getResponseBodyAsBytes().length);
  }

  /**
   * Method under test: {@link NettyResponse#getResponseBodyAsByteBuffer()}
   */
  @Test
  void testGetResponseBodyAsByteBuffer() {
    // Arrange
    Uri uri = mock(Uri.class);
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
    assertEquals(0, actualResponseBodyAsByteBuffer.array().length);
    assertFalse(actualResponseBodyAsByteBuffer.hasRemaining());
    assertTrue(actualResponseBodyAsByteBuffer.hasArray());
  }

  /**
   * Method under test: {@link NettyResponse#getResponseBody()}
   */
  @Test
  void testGetResponseBody() {
    // Arrange
    Uri uri = mock(Uri.class);
    HttpVersion version = new HttpVersion("https://example.org/example", 1, 1, true);

    DefaultFullHttpResponse response = new DefaultFullHttpResponse(version, HttpResponseStatus.valueOf(1));

    NettyResponseStatus status = new NettyResponseStatus(uri, response, new EmbeddedChannel());

    DefaultHttpHeaders headers = new DefaultHttpHeaders();

    // Act and Assert
    assertEquals("", (new NettyResponse(status, headers, new ArrayList<>())).getResponseBody());
  }

  /**
   * Method under test: {@link NettyResponse#getResponseBodyAsStream()}
   */
  @Test
  void testGetResponseBodyAsStream() throws IOException {
    // Arrange
    Uri uri = mock(Uri.class);
    HttpVersion version = new HttpVersion("https://example.org/example", 1, 1, true);

    DefaultFullHttpResponse response = new DefaultFullHttpResponse(version, HttpResponseStatus.valueOf(1));

    NettyResponseStatus status = new NettyResponseStatus(uri, response, new EmbeddedChannel());

    DefaultHttpHeaders headers = new DefaultHttpHeaders();

    // Act and Assert
    assertEquals(-1,
        (new NettyResponse(status, headers, new ArrayList<>())).getResponseBodyAsStream().read(new byte[]{}));
  }

  /**
   * Method under test: {@link NettyResponse#toString()}
   */
  @Test
  void testToString() {
    // Arrange
    Uri uri = mock(Uri.class);
    HttpVersion version = new HttpVersion("https://example.org/example", 1, 1, true);

    DefaultFullHttpResponse response = new DefaultFullHttpResponse(version, HttpResponseStatus.valueOf(1));

    NettyResponseStatus status = new NettyResponseStatus(uri, response, new EmbeddedChannel());

    DefaultHttpHeaders headers = new DefaultHttpHeaders();

    // Act and Assert
    assertEquals("NettyResponse {\n\tstatusCode=1\n\theaders=\n\tbody=\n\n}",
        (new NettyResponse(status, headers, new ArrayList<>())).toString());
  }
}
