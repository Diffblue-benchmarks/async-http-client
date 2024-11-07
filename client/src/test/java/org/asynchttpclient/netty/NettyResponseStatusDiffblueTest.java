package org.asynchttpclient.netty;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import io.netty.channel.Channel;
import io.netty.channel.embedded.EmbeddedChannel;
import io.netty.handler.codec.http.DefaultFullHttpResponse;
import io.netty.handler.codec.http.HttpResponse;
import io.netty.handler.codec.http.HttpResponseStatus;
import io.netty.handler.codec.http.HttpVersion;
import org.asynchttpclient.uri.Uri;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class NettyResponseStatusDiffblueTest {
  /**
   * Test
   * {@link NettyResponseStatus#NettyResponseStatus(Uri, HttpResponse, Channel)}.
   * <ul>
   *   <li>Then return LocalAddress is {@code null}.</li>
   * </ul>
   * <p>
   * Method under test:
   * {@link NettyResponseStatus#NettyResponseStatus(Uri, HttpResponse, Channel)}
   */
  @Test
  @DisplayName("Test new NettyResponseStatus(Uri, HttpResponse, Channel); then return LocalAddress is 'null'")
  void testNewNettyResponseStatus_thenReturnLocalAddressIsNull() {
    // Arrange
    Uri uri = new Uri("https://example.org/example", "https://example.org/example", "https://example.org/example", 8080,
        "https://example.org/example", "https://example.org/example", "https://example.org/example");

    HttpVersion version = new HttpVersion("https://example.org/example", 1, 1, true);

    // Act
    NettyResponseStatus actualNettyResponseStatus = new NettyResponseStatus(uri,
        new DefaultFullHttpResponse(version, HttpResponseStatus.valueOf(1)), null);

    // Assert
    assertEquals("HTTPS://EXAMPLE.ORG/EXAMPLE", actualNettyResponseStatus.getProtocolName());
    assertEquals("HTTPS://EXAMPLE.ORG/EXAMPLE/1.1", actualNettyResponseStatus.getProtocolText());
    assertEquals("Unknown Status (1)", actualNettyResponseStatus.getStatusText());
    assertNull(actualNettyResponseStatus.getLocalAddress());
    assertNull(actualNettyResponseStatus.getRemoteAddress());
    assertEquals(1, actualNettyResponseStatus.getProtocolMajorVersion());
    assertEquals(1, actualNettyResponseStatus.getProtocolMinorVersion());
    assertEquals(1, actualNettyResponseStatus.getStatusCode());
    assertSame(uri, actualNettyResponseStatus.getUri());
  }

  /**
   * Test
   * {@link NettyResponseStatus#NettyResponseStatus(Uri, HttpResponse, Channel)}.
   * <ul>
   *   <li>Then return ProtocolName is {@code HTTPS://EXAMPLE.ORG/EXAMPLE}.</li>
   * </ul>
   * <p>
   * Method under test:
   * {@link NettyResponseStatus#NettyResponseStatus(Uri, HttpResponse, Channel)}
   */
  @Test
  @DisplayName("Test new NettyResponseStatus(Uri, HttpResponse, Channel); then return ProtocolName is 'HTTPS://EXAMPLE.ORG/EXAMPLE'")
  void testNewNettyResponseStatus_thenReturnProtocolNameIsHttpsExampleOrgExample() {
    // Arrange
    Uri uri = new Uri("https://example.org/example", "https://example.org/example", "https://example.org/example", 8080,
        "https://example.org/example", "https://example.org/example", "https://example.org/example");

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

  /**
   * Test {@link NettyResponseStatus#getStatusCode()}.
   * <ul>
   *   <li>Then return one.</li>
   * </ul>
   * <p>
   * Method under test: {@link NettyResponseStatus#getStatusCode()}
   */
  @Test
  @DisplayName("Test getStatusCode(); then return one")
  void testGetStatusCode_thenReturnOne() {
    // Arrange
    Uri uri = new Uri("https://example.org/example", "https://example.org/example", "https://example.org/example", 8080,
        "https://example.org/example", "https://example.org/example", "https://example.org/example");

    HttpVersion version = new HttpVersion("https://example.org/example", 1, 1, true);

    DefaultFullHttpResponse response = new DefaultFullHttpResponse(version, HttpResponseStatus.valueOf(1));

    // Act and Assert
    assertEquals(1, (new NettyResponseStatus(uri, response, new EmbeddedChannel())).getStatusCode());
  }

  /**
   * Test {@link NettyResponseStatus#getStatusText()}.
   * <ul>
   *   <li>Then return {@code Unknown Status (1)}.</li>
   * </ul>
   * <p>
   * Method under test: {@link NettyResponseStatus#getStatusText()}
   */
  @Test
  @DisplayName("Test getStatusText(); then return 'Unknown Status (1)'")
  void testGetStatusText_thenReturnUnknownStatus1() {
    // Arrange
    Uri uri = new Uri("https://example.org/example", "https://example.org/example", "https://example.org/example", 8080,
        "https://example.org/example", "https://example.org/example", "https://example.org/example");

    HttpVersion version = new HttpVersion("https://example.org/example", 1, 1, true);

    DefaultFullHttpResponse response = new DefaultFullHttpResponse(version, HttpResponseStatus.valueOf(1));

    // Act and Assert
    assertEquals("Unknown Status (1)", (new NettyResponseStatus(uri, response, new EmbeddedChannel())).getStatusText());
  }

  /**
   * Test {@link NettyResponseStatus#getProtocolName()}.
   * <ul>
   *   <li>Then return {@code HTTPS://EXAMPLE.ORG/EXAMPLE}.</li>
   * </ul>
   * <p>
   * Method under test: {@link NettyResponseStatus#getProtocolName()}
   */
  @Test
  @DisplayName("Test getProtocolName(); then return 'HTTPS://EXAMPLE.ORG/EXAMPLE'")
  void testGetProtocolName_thenReturnHttpsExampleOrgExample() {
    // Arrange
    Uri uri = new Uri("https://example.org/example", "https://example.org/example", "https://example.org/example", 8080,
        "https://example.org/example", "https://example.org/example", "https://example.org/example");

    HttpVersion version = new HttpVersion("https://example.org/example", 1, 1, true);

    DefaultFullHttpResponse response = new DefaultFullHttpResponse(version, HttpResponseStatus.valueOf(1));

    // Act and Assert
    assertEquals("HTTPS://EXAMPLE.ORG/EXAMPLE",
        (new NettyResponseStatus(uri, response, new EmbeddedChannel())).getProtocolName());
  }

  /**
   * Test {@link NettyResponseStatus#getProtocolMajorVersion()}.
   * <ul>
   *   <li>Then return one.</li>
   * </ul>
   * <p>
   * Method under test: {@link NettyResponseStatus#getProtocolMajorVersion()}
   */
  @Test
  @DisplayName("Test getProtocolMajorVersion(); then return one")
  void testGetProtocolMajorVersion_thenReturnOne() {
    // Arrange
    Uri uri = new Uri("https://example.org/example", "https://example.org/example", "https://example.org/example", 8080,
        "https://example.org/example", "https://example.org/example", "https://example.org/example");

    HttpVersion version = new HttpVersion("https://example.org/example", 1, 1, true);

    DefaultFullHttpResponse response = new DefaultFullHttpResponse(version, HttpResponseStatus.valueOf(1));

    // Act and Assert
    assertEquals(1, (new NettyResponseStatus(uri, response, new EmbeddedChannel())).getProtocolMajorVersion());
  }

  /**
   * Test {@link NettyResponseStatus#getProtocolMinorVersion()}.
   * <ul>
   *   <li>Then return one.</li>
   * </ul>
   * <p>
   * Method under test: {@link NettyResponseStatus#getProtocolMinorVersion()}
   */
  @Test
  @DisplayName("Test getProtocolMinorVersion(); then return one")
  void testGetProtocolMinorVersion_thenReturnOne() {
    // Arrange
    Uri uri = new Uri("https://example.org/example", "https://example.org/example", "https://example.org/example", 8080,
        "https://example.org/example", "https://example.org/example", "https://example.org/example");

    HttpVersion version = new HttpVersion("https://example.org/example", 1, 1, true);

    DefaultFullHttpResponse response = new DefaultFullHttpResponse(version, HttpResponseStatus.valueOf(1));

    // Act and Assert
    assertEquals(1, (new NettyResponseStatus(uri, response, new EmbeddedChannel())).getProtocolMinorVersion());
  }

  /**
   * Test {@link NettyResponseStatus#getProtocolText()}.
   * <ul>
   *   <li>Then return {@code HTTPS://EXAMPLE.ORG/EXAMPLE/1.1}.</li>
   * </ul>
   * <p>
   * Method under test: {@link NettyResponseStatus#getProtocolText()}
   */
  @Test
  @DisplayName("Test getProtocolText(); then return 'HTTPS://EXAMPLE.ORG/EXAMPLE/1.1'")
  void testGetProtocolText_thenReturnHttpsExampleOrgExample11() {
    // Arrange
    Uri uri = new Uri("https://example.org/example", "https://example.org/example", "https://example.org/example", 8080,
        "https://example.org/example", "https://example.org/example", "https://example.org/example");

    HttpVersion version = new HttpVersion("https://example.org/example", 1, 1, true);

    DefaultFullHttpResponse response = new DefaultFullHttpResponse(version, HttpResponseStatus.valueOf(1));

    // Act and Assert
    assertEquals("HTTPS://EXAMPLE.ORG/EXAMPLE/1.1",
        (new NettyResponseStatus(uri, response, new EmbeddedChannel())).getProtocolText());
  }
}
