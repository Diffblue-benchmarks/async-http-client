package org.asynchttpclient.netty.future;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import java.io.IOException;
import org.asynchttpclient.exception.ChannelClosedException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class StackTraceInspectorDiffblueTest {
  /**
   * Test
   * {@link StackTraceInspector#recoverOnNettyDisconnectException(Throwable)}.
   * <p>
   * Method under test:
   * {@link StackTraceInspector#recoverOnNettyDisconnectException(Throwable)}
   */
  @Test
  @DisplayName("Test recoverOnNettyDisconnectException(Throwable)")
  void testRecoverOnNettyDisconnectException() {
    // Arrange, Act and Assert
    assertFalse(StackTraceInspector.recoverOnNettyDisconnectException(
        new Throwable("io.netty.handler.ssl.SslHandler", ChannelClosedException.INSTANCE)));
    assertFalse(StackTraceInspector.recoverOnNettyDisconnectException(new Throwable("io.netty.handler.ssl.SslHandler",
        new Throwable("io.netty.handler.ssl.SslHandler", ChannelClosedException.INSTANCE))));
  }

  /**
   * Test
   * {@link StackTraceInspector#recoverOnNettyDisconnectException(Throwable)}.
   * <p>
   * Method under test:
   * {@link StackTraceInspector#recoverOnNettyDisconnectException(Throwable)}
   */
  @Test
  @DisplayName("Test recoverOnNettyDisconnectException(Throwable)")
  void testRecoverOnNettyDisconnectException2() {
    // Arrange
    Throwable t = new Throwable("io.netty.handler.ssl.SslHandler", ChannelClosedException.INSTANCE);
    t.setStackTrace(new StackTraceElement[]{new StackTraceElement("io.netty.handler.ssl.SslHandler",
        "io.netty.handler.ssl.SslHandler", "io.netty.handler.ssl.SslHandler", Short.SIZE)});

    // Act and Assert
    assertFalse(StackTraceInspector.recoverOnNettyDisconnectException(t));
  }

  /**
   * Test
   * {@link StackTraceInspector#recoverOnNettyDisconnectException(Throwable)}.
   * <p>
   * Method under test:
   * {@link StackTraceInspector#recoverOnNettyDisconnectException(Throwable)}
   */
  @Test
  @DisplayName("Test recoverOnNettyDisconnectException(Throwable)")
  void testRecoverOnNettyDisconnectException3() {
    // Arrange
    Throwable t = new Throwable("io.netty.handler.ssl.SslHandler", ChannelClosedException.INSTANCE);
    t.setStackTrace(new StackTraceElement[]{new StackTraceElement("io.netty.handler.ssl.SslHandler", "disconnect",
        "io.netty.handler.ssl.SslHandler", Short.SIZE)});

    // Act and Assert
    assertTrue(StackTraceInspector.recoverOnNettyDisconnectException(t));
  }

  /**
   * Test {@link StackTraceInspector#recoverOnReadOrWriteException(Throwable)}.
   * <p>
   * Method under test:
   * {@link StackTraceInspector#recoverOnReadOrWriteException(Throwable)}
   */
  @Test
  @DisplayName("Test recoverOnReadOrWriteException(Throwable)")
  void testRecoverOnReadOrWriteException() {
    // Arrange
    Throwable t = new Throwable("Connection reset by peer", ChannelClosedException.INSTANCE);
    t.setStackTrace(new StackTraceElement[]{
        new StackTraceElement("sun.nio.ch.SocketDispatcher", "read", "sun.nio.ch.SocketDispatcher", 15)});

    // Act and Assert
    assertTrue(StackTraceInspector.recoverOnReadOrWriteException(t));
  }

  /**
   * Test {@link StackTraceInspector#recoverOnReadOrWriteException(Throwable)}.
   * <p>
   * Method under test:
   * {@link StackTraceInspector#recoverOnReadOrWriteException(Throwable)}
   */
  @Test
  @DisplayName("Test recoverOnReadOrWriteException(Throwable)")
  void testRecoverOnReadOrWriteException2() {
    // Arrange
    Throwable t = new Throwable("Connection reset by peer", ChannelClosedException.INSTANCE);
    t.setStackTrace(new StackTraceElement[]{
        new StackTraceElement("sun.nio.ch.SocketDispatcher", "write", "sun.nio.ch.SocketDispatcher", 15)});

    // Act and Assert
    assertTrue(StackTraceInspector.recoverOnReadOrWriteException(t));
  }

  /**
   * Test {@link StackTraceInspector#recoverOnReadOrWriteException(Throwable)}.
   * <ul>
   *   <li>When {@link IOException#IOException(String)} with
   * {@code Connection reset by peer}.</li>
   * </ul>
   * <p>
   * Method under test:
   * {@link StackTraceInspector#recoverOnReadOrWriteException(Throwable)}
   */
  @Test
  @DisplayName("Test recoverOnReadOrWriteException(Throwable); when IOException(String) with 'Connection reset by peer'")
  void testRecoverOnReadOrWriteException_whenIOExceptionWithConnectionResetByPeer() {
    // Arrange, Act and Assert
    assertTrue(StackTraceInspector.recoverOnReadOrWriteException(new IOException("Connection reset by peer")));
  }
}
