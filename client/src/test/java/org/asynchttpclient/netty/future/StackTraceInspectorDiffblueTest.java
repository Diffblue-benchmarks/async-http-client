package org.asynchttpclient.netty.future;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.io.IOException;
import org.asynchttpclient.exception.ChannelClosedException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

class StackTraceInspectorDiffblueTest {
  /**
   * Test {@link StackTraceInspector#recoverOnNettyDisconnectException(Throwable)}.
   *
   * <p>Method under test: {@link StackTraceInspector#recoverOnNettyDisconnectException(Throwable)}
   */
  @Test
  @DisplayName("Test recoverOnNettyDisconnectException(Throwable)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean StackTraceInspector.recoverOnNettyDisconnectException(Throwable)"})
  void testRecoverOnNettyDisconnectException() {
    // Arrange, Act and Assert
    assertFalse(
        StackTraceInspector.recoverOnNettyDisconnectException(
            new Throwable("Not all who wander are lost", ChannelClosedException.INSTANCE)));
  }

  /**
   * Test {@link StackTraceInspector#recoverOnNettyDisconnectException(Throwable)}.
   *
   * <p>Method under test: {@link StackTraceInspector#recoverOnNettyDisconnectException(Throwable)}
   */
  @Test
  @DisplayName("Test recoverOnNettyDisconnectException(Throwable)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean StackTraceInspector.recoverOnNettyDisconnectException(Throwable)"})
  void testRecoverOnNettyDisconnectException2() {
    // Arrange
    Throwable t =
        new Throwable(
            "Not all who wander are lost",
            new Throwable("Not all who wander are lost", ChannelClosedException.INSTANCE));

    // Act and Assert
    assertFalse(StackTraceInspector.recoverOnNettyDisconnectException(t));
  }

  /**
   * Test {@link StackTraceInspector#recoverOnNettyDisconnectException(Throwable)}.
   *
   * <p>Method under test: {@link StackTraceInspector#recoverOnNettyDisconnectException(Throwable)}
   */
  @Test
  @DisplayName("Test recoverOnNettyDisconnectException(Throwable)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean StackTraceInspector.recoverOnNettyDisconnectException(Throwable)"})
  void testRecoverOnNettyDisconnectException3() {
    // Arrange
    Throwable t = new Throwable("Not all who wander are lost", ChannelClosedException.INSTANCE);
    StackTraceElement stackTraceElement =
        new StackTraceElement(
            "io.netty.handler.ssl.SslHandler", "io.netty.handler.ssl.SslHandler", "foo.txt", 2);
    t.setStackTrace(new StackTraceElement[] {stackTraceElement});

    // Act and Assert
    assertFalse(StackTraceInspector.recoverOnNettyDisconnectException(t));
  }

  /**
   * Test {@link StackTraceInspector#recoverOnNettyDisconnectException(Throwable)}.
   *
   * <p>Method under test: {@link StackTraceInspector#recoverOnNettyDisconnectException(Throwable)}
   */
  @Test
  @DisplayName("Test recoverOnNettyDisconnectException(Throwable)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean StackTraceInspector.recoverOnNettyDisconnectException(Throwable)"})
  void testRecoverOnNettyDisconnectException4() {
    // Arrange
    Throwable t = new Throwable("Not all who wander are lost", ChannelClosedException.INSTANCE);
    StackTraceElement stackTraceElement =
        new StackTraceElement("io.netty.handler.ssl.SslHandler", "disconnect", "foo.txt", 2);
    t.setStackTrace(new StackTraceElement[] {stackTraceElement});

    // Act and Assert
    assertTrue(StackTraceInspector.recoverOnNettyDisconnectException(t));
  }

  /**
   * Test {@link StackTraceInspector#recoverOnReadOrWriteException(Throwable)}.
   *
   * <p>Method under test: {@link StackTraceInspector#recoverOnReadOrWriteException(Throwable)}
   */
  @Test
  @DisplayName("Test recoverOnReadOrWriteException(Throwable)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean StackTraceInspector.recoverOnReadOrWriteException(Throwable)"})
  void testRecoverOnReadOrWriteException() {
    // Arrange, Act and Assert
    assertTrue(
        StackTraceInspector.recoverOnReadOrWriteException(
            new IOException("Connection reset by peer", ChannelClosedException.INSTANCE)));
  }

  /**
   * Test {@link StackTraceInspector#recoverOnReadOrWriteException(Throwable)}.
   *
   * <p>Method under test: {@link StackTraceInspector#recoverOnReadOrWriteException(Throwable)}
   */
  @Test
  @DisplayName("Test recoverOnReadOrWriteException(Throwable)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean StackTraceInspector.recoverOnReadOrWriteException(Throwable)"})
  void testRecoverOnReadOrWriteException2() {
    // Arrange
    Throwable t = new Throwable();
    StackTraceElement stackTraceElement =
        new StackTraceElement(
            "sun.nio.ch.SocketDispatcher", "sun.nio.ch.SocketDispatcher", "foo.txt", 2);
    t.setStackTrace(new StackTraceElement[] {stackTraceElement});

    // Act and Assert
    assertFalse(StackTraceInspector.recoverOnReadOrWriteException(t));
  }

  /**
   * Test {@link StackTraceInspector#recoverOnReadOrWriteException(Throwable)}.
   *
   * <p>Method under test: {@link StackTraceInspector#recoverOnReadOrWriteException(Throwable)}
   */
  @Test
  @DisplayName("Test recoverOnReadOrWriteException(Throwable)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean StackTraceInspector.recoverOnReadOrWriteException(Throwable)"})
  void testRecoverOnReadOrWriteException3() {
    // Arrange
    Throwable t = new Throwable();
    StackTraceElement stackTraceElement =
        new StackTraceElement("sun.nio.ch.SocketDispatcher", "read", "foo.txt", 2);
    t.setStackTrace(new StackTraceElement[] {stackTraceElement});

    // Act and Assert
    assertTrue(StackTraceInspector.recoverOnReadOrWriteException(t));
  }

  /**
   * Test {@link StackTraceInspector#recoverOnReadOrWriteException(Throwable)}.
   *
   * <p>Method under test: {@link StackTraceInspector#recoverOnReadOrWriteException(Throwable)}
   */
  @Test
  @DisplayName("Test recoverOnReadOrWriteException(Throwable)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean StackTraceInspector.recoverOnReadOrWriteException(Throwable)"})
  void testRecoverOnReadOrWriteException4() {
    // Arrange
    Throwable t = new Throwable();
    StackTraceElement stackTraceElement =
        new StackTraceElement("sun.nio.ch.SocketDispatcher", "write", "foo.txt", 2);
    t.setStackTrace(new StackTraceElement[] {stackTraceElement});

    // Act and Assert
    assertTrue(StackTraceInspector.recoverOnReadOrWriteException(t));
  }

  /**
   * Test {@link StackTraceInspector#recoverOnReadOrWriteException(Throwable)}.
   *
   * <ul>
   *   <li>When {@link Throwable#Throwable()}.
   *   <li>Then return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link StackTraceInspector#recoverOnReadOrWriteException(Throwable)}
   */
  @Test
  @DisplayName(
      "Test recoverOnReadOrWriteException(Throwable); when Throwable(); then return 'false'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean StackTraceInspector.recoverOnReadOrWriteException(Throwable)"})
  void testRecoverOnReadOrWriteException_whenThrowable_thenReturnFalse() {
    // Arrange, Act and Assert
    assertFalse(StackTraceInspector.recoverOnReadOrWriteException(new Throwable()));
  }
}
