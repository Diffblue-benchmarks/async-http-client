package org.asynchttpclient;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import io.netty.channel.embedded.EmbeddedChannel;
import io.netty.handler.codec.http.DefaultFullHttpResponse;
import io.netty.handler.codec.http.HttpResponseStatus;
import io.netty.handler.codec.http.HttpVersion;
import org.asynchttpclient.netty.NettyResponseStatus;
import org.asynchttpclient.uri.Uri;
import org.junit.jupiter.api.Test;

class HttpResponseStatusDiffblueTest {
  /**
   * Method under test: {@link HttpResponseStatus#toString()}
   */
  @Test
  void testToString() {
    // Arrange
    Uri uri = mock(Uri.class);
    HttpVersion version = new HttpVersion("https://example.org/example", 1, 1, true);

    DefaultFullHttpResponse response = new DefaultFullHttpResponse(version, HttpResponseStatus.valueOf(1));

    // Act and Assert
    assertEquals("1 Unknown Status (1)", (new NettyResponseStatus(uri, response, new EmbeddedChannel())).toString());
  }
}
