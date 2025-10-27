package org.asynchttpclient.netty.request.body;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import io.netty.handler.codec.http.DefaultHttpHeaders;
import java.util.ArrayList;
import org.asynchttpclient.AsyncHttpClientConfig;
import org.asynchttpclient.request.body.multipart.MultipartBody;
import org.asynchttpclient.request.body.multipart.Part;
import org.junit.jupiter.api.Test;

class NettyMultipartBodyDiffblueTest {
  /**
   * Method under test: {@link NettyMultipartBody#getContentTypeOverride()}
   */
  @Test
  void testGetContentTypeOverride() {
    // Arrange
    ArrayList<Part> parts = new ArrayList<>();
    NettyMultipartBody nettyMultipartBody = new NettyMultipartBody(parts, new DefaultHttpHeaders(),
        mock(AsyncHttpClientConfig.class));

    // Act
    nettyMultipartBody.getContentTypeOverride();

    // Assert
    assertTrue(nettyMultipartBody.getBody() instanceof MultipartBody);
  }
}
