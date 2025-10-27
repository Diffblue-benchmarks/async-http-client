package org.asynchttpclient;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.Mockito.mock;
import io.netty.channel.embedded.EmbeddedChannel;
import io.netty.handler.codec.http.DefaultFullHttpResponse;
import io.netty.handler.codec.http.DefaultHttpHeaders;
import io.netty.handler.codec.http.HttpResponseStatus;
import io.netty.handler.codec.http.HttpVersion;
import java.util.ArrayList;
import org.asynchttpclient.netty.NettyResponse;
import org.asynchttpclient.netty.NettyResponseStatus;
import org.asynchttpclient.uri.Uri;
import org.junit.jupiter.api.Test;

class AsyncCompletionHandlerBaseDiffblueTest {
  /**
   * Method under test: default or parameterless constructor of
   * {@link AsyncCompletionHandlerBase}
   */
  @Test
  void testNewAsyncCompletionHandlerBase() throws Exception {
    // Arrange and Act
    AsyncCompletionHandlerBase actualAsyncCompletionHandlerBase = new AsyncCompletionHandlerBase();

    // Assert
    assertNull(actualAsyncCompletionHandlerBase.onCompleted());
    assertEquals(AsyncHandler.State.CONTINUE, actualAsyncCompletionHandlerBase.onContentWritten());
    assertEquals(AsyncHandler.State.CONTINUE, actualAsyncCompletionHandlerBase.onHeadersWritten());
  }

  /**
   * Method under test: {@link AsyncCompletionHandlerBase#onCompleted(Response)}
   */
  @Test
  void testOnCompleted() throws Exception {
    // Arrange
    AsyncCompletionHandlerBase asyncCompletionHandlerBase = new AsyncCompletionHandlerBase();
    Uri uri = mock(Uri.class);
    HttpVersion version = new HttpVersion("https://example.org/example", 1, 1, true);

    DefaultFullHttpResponse response = new DefaultFullHttpResponse(version, HttpResponseStatus.valueOf(1));

    NettyResponseStatus status = new NettyResponseStatus(uri, response, new EmbeddedChannel());

    DefaultHttpHeaders headers = new DefaultHttpHeaders();
    NettyResponse response2 = new NettyResponse(status, headers, new ArrayList<>());

    // Act and Assert
    assertSame(response2, asyncCompletionHandlerBase.onCompleted(response2));
  }
}
