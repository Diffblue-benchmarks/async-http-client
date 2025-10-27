package org.asynchttpclient.netty;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.Mockito.mock;
import io.netty.channel.Channel;
import io.netty.channel.embedded.EmbeddedChannel;
import io.netty.handler.codec.http.DefaultFullHttpResponse;
import io.netty.handler.codec.http.HttpResponse;
import io.netty.handler.codec.http.HttpResponseStatus;
import io.netty.handler.codec.http.HttpVersion;
import org.asynchttpclient.uri.Uri;
import org.junit.jupiter.api.Test;

class NettyResponseStatusDiffblueTest {
  /**
   * Method under test: {@link NettyResponseStatus#getProtocolName()}
   */
  @Test
  void testGetProtocolName() {
    // Arrange
    Uri uri = mock(Uri.class);
    HttpVersion version = new HttpVersion("https://example.org/example", 1, 1, true);

    DefaultFullHttpResponse response = new DefaultFullHttpResponse(version, HttpResponseStatus.valueOf(1));

    // Act and Assert
    assertEquals("HTTPS://EXAMPLE.ORG/EXAMPLE",
        (new NettyResponseStatus(uri, response, new EmbeddedChannel())).getProtocolName());
  }

  /**
   * Method under test: {@link NettyResponseStatus#getProtocolMajorVersion()}
   */
  @Test
  void testGetProtocolMajorVersion() {
    // Arrange
    Uri uri = mock(Uri.class);
    HttpVersion version = new HttpVersion("https://example.org/example", 1, 1, true);

    DefaultFullHttpResponse response = new DefaultFullHttpResponse(version, HttpResponseStatus.valueOf(1));

    // Act and Assert
    assertEquals(1, (new NettyResponseStatus(uri, response, new EmbeddedChannel())).getProtocolMajorVersion());
  }

  /**
   * Method under test: {@link NettyResponseStatus#getProtocolMinorVersion()}
   */
  @Test
  void testGetProtocolMinorVersion() {
    // Arrange
    Uri uri = mock(Uri.class);
    HttpVersion version = new HttpVersion("https://example.org/example", 1, 1, true);

    DefaultFullHttpResponse response = new DefaultFullHttpResponse(version, HttpResponseStatus.valueOf(1));

    // Act and Assert
    assertEquals(1, (new NettyResponseStatus(uri, response, new EmbeddedChannel())).getProtocolMinorVersion());
  }

  /**
   * Method under test: {@link NettyResponseStatus#getProtocolText()}
   */
  @Test
  void testGetProtocolText() {
    // Arrange
    Uri uri = mock(Uri.class);
    HttpVersion version = new HttpVersion("https://example.org/example", 1, 1, true);

    DefaultFullHttpResponse response = new DefaultFullHttpResponse(version, HttpResponseStatus.valueOf(1));

    // Act and Assert
    assertEquals("HTTPS://EXAMPLE.ORG/EXAMPLE/1.1",
        (new NettyResponseStatus(uri, response, new EmbeddedChannel())).getProtocolText());
  }

  /**
   * Method under test:
   * {@link NettyResponseStatus#NettyResponseStatus(Uri, HttpResponse, Channel)}
   */
  @Test
  void testNewNettyResponseStatus() {
    // Arrange
    Uri uri = mock(Uri.class);
    HttpVersion version = new HttpVersion("https://example.org/example", 1, 1, true);

    DefaultFullHttpResponse response = new DefaultFullHttpResponse(version, HttpResponseStatus.valueOf(1));

    // Act
    NettyResponseStatus actualNettyResponseStatus = new NettyResponseStatus(uri, response, new EmbeddedChannel());

    // Assert
    assertEquals("HTTPS://EXAMPLE.ORG/EXAMPLE", actualNettyResponseStatus.getProtocolName());
    assertEquals("HTTPS://EXAMPLE.ORG/EXAMPLE/1.1", actualNettyResponseStatus.getProtocolText());
    assertEquals("Unknown Status (1)", actualNettyResponseStatus.getStatusText());
    assertEquals(1, actualNettyResponseStatus.getProtocolMajorVersion());
    assertEquals(1, actualNettyResponseStatus.getProtocolMinorVersion());
    assertEquals(1, actualNettyResponseStatus.getStatusCode());
    assertSame(uri, actualNettyResponseStatus.getUri());
  }
}
