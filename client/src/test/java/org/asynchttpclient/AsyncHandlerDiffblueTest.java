package org.asynchttpclient;

import static org.junit.jupiter.api.Assertions.assertEquals;
import io.netty.handler.codec.http.DefaultHttpHeaders;
import io.netty.handler.codec.http.HttpHeaders;
import java.util.ArrayList;
import org.asynchttpclient.ws.WebSocketUpgradeHandler;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class AsyncHandlerDiffblueTest {
  /**
   * Test {@link AsyncHandler#onTrailingHeadersReceived(HttpHeaders)}.
   * <p>
   * Method under test:
   * {@link AsyncHandler#onTrailingHeadersReceived(HttpHeaders)}
   */
  @Test
  @DisplayName("Test onTrailingHeadersReceived(HttpHeaders)")
  void testOnTrailingHeadersReceived() throws Exception {
    // Arrange
    WebSocketUpgradeHandler webSocketUpgradeHandler = new WebSocketUpgradeHandler(new ArrayList<>());

    // Act and Assert
    assertEquals(AsyncHandler.State.CONTINUE,
        webSocketUpgradeHandler.onTrailingHeadersReceived(new DefaultHttpHeaders()));
  }
}
