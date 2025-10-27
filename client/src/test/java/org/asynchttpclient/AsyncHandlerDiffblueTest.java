package org.asynchttpclient;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import io.netty.handler.codec.http.DefaultHttpHeaders;
import io.netty.handler.codec.http.EmptyHttpHeaders;
import io.netty.handler.codec.http.HttpHeaders;
import java.util.ArrayList;
import org.asynchttpclient.ws.WebSocketUpgradeHandler;
import org.junit.jupiter.api.Test;

class AsyncHandlerDiffblueTest {
  /**
   * Method under test:
   * {@link AsyncHandler#onTrailingHeadersReceived(HttpHeaders)}
   */
  @Test
  void testOnTrailingHeadersReceived() throws Exception {
    // Arrange
    WebSocketUpgradeHandler webSocketUpgradeHandler = new WebSocketUpgradeHandler(new ArrayList<>());

    // Act and Assert
    assertEquals(AsyncHandler.State.CONTINUE,
        webSocketUpgradeHandler.onTrailingHeadersReceived(new DefaultHttpHeaders()));
  }

  /**
   * Method under test:
   * {@link AsyncHandler#onTrailingHeadersReceived(HttpHeaders)}
   */
  @Test
  void testOnTrailingHeadersReceived2() throws Exception {
    // Arrange, Act and Assert
    assertEquals(AsyncHandler.State.CONTINUE,
        (new WebSocketUpgradeHandler(new ArrayList<>())).onTrailingHeadersReceived(mock(EmptyHttpHeaders.class)));
  }
}
