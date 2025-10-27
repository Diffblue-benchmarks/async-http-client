package org.asynchttpclient.netty.future;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import java.io.IOException;
import org.asynchttpclient.exception.ChannelClosedException;
import org.junit.jupiter.api.Test;

class StackTraceInspectorDiffblueTest {
  /**
   * Method under test:
   * {@link StackTraceInspector#recoverOnNettyDisconnectException(Throwable)}
   */
  @Test
  void testRecoverOnNettyDisconnectException() {
    // Arrange, Act and Assert
    assertFalse(StackTraceInspector.recoverOnNettyDisconnectException(
        new Throwable("io.netty.handler.ssl.SslHandler", ChannelClosedException.INSTANCE)));
    assertFalse(StackTraceInspector.recoverOnNettyDisconnectException(new Throwable("io.netty.handler.ssl.SslHandler",
        new Throwable("io.netty.handler.ssl.SslHandler", ChannelClosedException.INSTANCE))));
  }

  /**
   * Method under test:
   * {@link StackTraceInspector#recoverOnNettyDisconnectException(Throwable)}
   */
  @Test
  void testRecoverOnNettyDisconnectException2() {
    // Arrange
    Throwable t = new Throwable("io.netty.handler.ssl.SslHandler", ChannelClosedException.INSTANCE);
    t.setStackTrace(new StackTraceElement[]{new StackTraceElement("io.netty.handler.ssl.SslHandler",
        "io.netty.handler.ssl.SslHandler", "io.netty.handler.ssl.SslHandler", Short.SIZE)});

    // Act and Assert
    assertFalse(StackTraceInspector.recoverOnNettyDisconnectException(t));
  }

  /**
   * Method under test:
   * {@link StackTraceInspector#recoverOnNettyDisconnectException(Throwable)}
   */
  @Test
  void testRecoverOnNettyDisconnectException3() {
    // Arrange
    Throwable t = new Throwable("io.netty.handler.ssl.SslHandler", ChannelClosedException.INSTANCE);
    t.setStackTrace(new StackTraceElement[]{new StackTraceElement("io.netty.handler.ssl.SslHandler", "disconnect",
        "io.netty.handler.ssl.SslHandler", Short.SIZE)});

    // Act and Assert
    assertTrue(StackTraceInspector.recoverOnNettyDisconnectException(t));
  }

  /**
   * Method under test:
   * {@link StackTraceInspector#recoverOnReadOrWriteException(Throwable)}
   */
  @Test
  void testRecoverOnReadOrWriteException() {
    // Arrange, Act and Assert
    assertTrue(StackTraceInspector.recoverOnReadOrWriteException(new IOException("Connection reset by peer")));
  }

  /**
   * Method under test:
   * {@link StackTraceInspector#recoverOnReadOrWriteException(Throwable)}
   */
  @Test
  void testRecoverOnReadOrWriteException2() {
    // Arrange
    Throwable t = new Throwable("Connection reset by peer", ChannelClosedException.INSTANCE);
    t.setStackTrace(new StackTraceElement[]{
        new StackTraceElement("sun.nio.ch.SocketDispatcher", "read", "sun.nio.ch.SocketDispatcher", 15)});

    // Act and Assert
    assertTrue(StackTraceInspector.recoverOnReadOrWriteException(t));
  }

  /**
   * Method under test:
   * {@link StackTraceInspector#recoverOnReadOrWriteException(Throwable)}
   */
  @Test
  void testRecoverOnReadOrWriteException3() {
    // Arrange
    Throwable t = new Throwable("Connection reset by peer", ChannelClosedException.INSTANCE);
    t.setStackTrace(new StackTraceElement[]{
        new StackTraceElement("sun.nio.ch.SocketDispatcher", "write", "sun.nio.ch.SocketDispatcher", 15)});

    // Act and Assert
    assertTrue(StackTraceInspector.recoverOnReadOrWriteException(t));
  }
}
