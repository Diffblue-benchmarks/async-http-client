package org.asynchttpclient;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import io.netty.channel.embedded.EmbeddedChannel;
import io.netty.handler.codec.http.DefaultFullHttpResponse;
import io.netty.handler.codec.http.HttpResponseStatus;
import io.netty.handler.codec.http.HttpVersion;
import org.asynchttpclient.netty.NettyResponseStatus;
import org.asynchttpclient.uri.Uri;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class HttpResponseStatusDiffblueTest {
  /**
   * Test {@link HttpResponseStatus#getUri()}.
   * <p>
   * Method under test: {@link HttpResponseStatus#getUri()}
   */
  @Test
  @DisplayName("Test getUri()")
  void testGetUri() {
    // Arrange
    Uri uri = new Uri("https://example.org/example", "https://example.org/example", "https://example.org/example", 8080,
        "https://example.org/example", "https://example.org/example", "https://example.org/example");

    HttpVersion version = new HttpVersion("https://example.org/example", 1, 1, true);

    DefaultFullHttpResponse response = new DefaultFullHttpResponse(version, HttpResponseStatus.valueOf(1));

    // Act and Assert
    assertSame(uri, (new NettyResponseStatus(uri, response, new EmbeddedChannel())).getUri());
  }

  /**
   * Test {@link HttpResponseStatus#toString()}.
   * <ul>
   *   <li>Then return {@code 1 Unknown Status (1)}.</li>
   * </ul>
   * <p>
   * Method under test: {@link HttpResponseStatus#toString()}
   */
  @Test
  @DisplayName("Test toString(); then return '1 Unknown Status (1)'")
  void testToString_thenReturn1UnknownStatus1() {
    // Arrange
    Uri uri = new Uri("https://example.org/example", "https://example.org/example", "https://example.org/example", 8080,
        "https://example.org/example", "https://example.org/example", "https://example.org/example");

    HttpVersion version = new HttpVersion("https://example.org/example", 1, 1, true);

    DefaultFullHttpResponse response = new DefaultFullHttpResponse(version, HttpResponseStatus.valueOf(1));

    // Act and Assert
    assertEquals("1 Unknown Status (1)", (new NettyResponseStatus(uri, response, new EmbeddedChannel())).toString());
  }
}
