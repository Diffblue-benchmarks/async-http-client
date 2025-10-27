package org.asynchttpclient.ws;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.anyBoolean;
import static org.mockito.Mockito.anyInt;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

class WebSocketListenerDiffblueTest {
  /**
   * Method under test:
   * {@link WebSocketListener#onBinaryFrame(byte[], boolean, int)}
   */
  @Test
  void testOnBinaryFrame() {
    // Arrange
    WebSocketListener webSocketListener = mock(WebSocketListener.class);
    doNothing().when(webSocketListener).onBinaryFrame(Mockito.<byte[]>any(), anyBoolean(), anyInt());

    // Act
    webSocketListener.onBinaryFrame(new byte[]{'A', 'X', 'A', 'X', 'A', 'X', 'A', 'X'}, true, 1);

    // Assert
    verify(webSocketListener).onBinaryFrame(isA(byte[].class), eq(true), eq(1));
  }

  /**
   * Method under test:
   * {@link WebSocketListener#onTextFrame(String, boolean, int)}
   */
  @Test
  void testOnTextFrame() {
    // Arrange
    WebSocketListener webSocketListener = mock(WebSocketListener.class);
    doNothing().when(webSocketListener).onTextFrame(Mockito.<String>any(), anyBoolean(), anyInt());

    // Act
    webSocketListener.onTextFrame("https://example.org/example", true, 1);

    // Assert
    verify(webSocketListener).onTextFrame(eq("https://example.org/example"), eq(true), eq(1));
  }

  /**
   * Method under test: {@link WebSocketListener#onPingFrame(byte[])}
   */
  @Test
  void testOnPingFrame() {
    // Arrange
    WebSocketListener webSocketListener = mock(WebSocketListener.class);
    doNothing().when(webSocketListener).onPingFrame(Mockito.<byte[]>any());

    // Act
    webSocketListener.onPingFrame(new byte[]{'A', 'X', 'A', 'X', 'A', 'X', 'A', 'X'});

    // Assert
    verify(webSocketListener).onPingFrame(isA(byte[].class));
  }

  /**
   * Method under test: {@link WebSocketListener#onPongFrame(byte[])}
   */
  @Test
  void testOnPongFrame() {
    // Arrange
    WebSocketListener webSocketListener = mock(WebSocketListener.class);
    doNothing().when(webSocketListener).onPongFrame(Mockito.<byte[]>any());

    // Act
    webSocketListener.onPongFrame(new byte[]{'A', 'X', 'A', 'X', 'A', 'X', 'A', 'X'});

    // Assert
    verify(webSocketListener).onPongFrame(isA(byte[].class));
  }
}
