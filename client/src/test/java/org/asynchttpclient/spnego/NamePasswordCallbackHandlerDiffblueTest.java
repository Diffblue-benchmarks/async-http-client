package org.asynchttpclient.spnego;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import jakarta.security.auth.message.callback.CertStoreCallback;
import java.io.IOException;
import javax.security.auth.callback.Callback;
import javax.security.auth.callback.NameCallback;
import javax.security.auth.callback.PasswordCallback;
import javax.security.auth.callback.UnsupportedCallbackException;
import org.junit.jupiter.api.Test;

class NamePasswordCallbackHandlerDiffblueTest {
  /**
   * Method under test: {@link NamePasswordCallbackHandler#handle(Callback[])}
   */
  @Test
  void testHandle() throws IOException, UnsupportedCallbackException {
    // Arrange
    NamePasswordCallbackHandler namePasswordCallbackHandler = new NamePasswordCallbackHandler("janedoe",
        "https://example.org/example");

    // Act and Assert
    assertThrows(UnsupportedCallbackException.class,
        () -> namePasswordCallbackHandler.handle(new Callback[]{new CertStoreCallback()}));
  }

  /**
   * Method under test: {@link NamePasswordCallbackHandler#handle(Callback[])}
   */
  @Test
  void testHandle2() throws IOException, UnsupportedCallbackException {
    // Arrange
    NamePasswordCallbackHandler namePasswordCallbackHandler = new NamePasswordCallbackHandler("janedoe",
        "https://example.org/example", "https://example.org/example");

    // Act and Assert
    assertThrows(UnsupportedCallbackException.class,
        () -> namePasswordCallbackHandler.handle(new Callback[]{new CertStoreCallback()}));
  }

  /**
   * Method under test: {@link NamePasswordCallbackHandler#handle(Callback[])}
   */
  @Test
  void testHandle3() throws IOException, UnsupportedCallbackException {
    // Arrange
    NamePasswordCallbackHandler namePasswordCallbackHandler = new NamePasswordCallbackHandler("janedoe",
        "https://example.org/example");
    NameCallback nameCallback = new NameCallback("setObject");
    Callback[] callbacks = new Callback[]{nameCallback};

    // Act
    namePasswordCallbackHandler.handle(callbacks);

    // Assert that nothing has changed
    Callback callback = callbacks[0];
    assertTrue(callback instanceof NameCallback);
    assertEquals("janedoe", ((NameCallback) callback).getName());
    assertEquals(1, callbacks.length);
    assertSame(nameCallback, callback);
  }

  /**
   * Method under test: {@link NamePasswordCallbackHandler#handle(Callback[])}
   */
  @Test
  void testHandle4() throws IOException, UnsupportedCallbackException {
    // Arrange
    NamePasswordCallbackHandler namePasswordCallbackHandler = new NamePasswordCallbackHandler("janedoe",
        "https://example.org/example");
    PasswordCallback passwordCallback = new PasswordCallback("setObject", true);

    Callback[] callbacks = new Callback[]{passwordCallback};

    // Act
    namePasswordCallbackHandler.handle(callbacks);

    // Assert that nothing has changed
    assertEquals(1, callbacks.length);
    assertSame(passwordCallback, callbacks[0]);
  }

  /**
   * Method under test: {@link NamePasswordCallbackHandler#handle(Callback[])}
   */
  @Test
  void testHandle5() throws IOException, UnsupportedCallbackException {
    // Arrange
    NamePasswordCallbackHandler namePasswordCallbackHandler = new NamePasswordCallbackHandler("janedoe", null);
    PasswordCallback passwordCallback = new PasswordCallback("setObject", true);

    Callback[] callbacks = new Callback[]{passwordCallback};

    // Act
    namePasswordCallbackHandler.handle(callbacks);

    // Assert that nothing has changed
    Callback callback = callbacks[0];
    assertTrue(callback instanceof PasswordCallback);
    assertEquals(1, callbacks.length);
    assertSame(passwordCallback, callback);
  }

  /**
   * Method under test:
   * {@link NamePasswordCallbackHandler#NamePasswordCallbackHandler(String, String)}
   */
  @Test
  void testNewNamePasswordCallbackHandler() throws IOException, UnsupportedCallbackException {
    // Arrange and Act
    NamePasswordCallbackHandler actualNamePasswordCallbackHandler = new NamePasswordCallbackHandler("janedoe",
        "https://example.org/example");
    NameCallback nameCallback = new NameCallback("setObject");
    Callback[] callbacks = new Callback[]{nameCallback};
    actualNamePasswordCallbackHandler.handle(callbacks);

    // Assert that nothing has changed
    Callback callback = callbacks[0];
    assertTrue(callback instanceof NameCallback);
    assertEquals("janedoe", ((NameCallback) callback).getName());
    assertEquals(1, callbacks.length);
    assertFalse(actualNamePasswordCallbackHandler.handleCallback(null));
    assertSame(nameCallback, callback);
  }

  /**
   * Method under test:
   * {@link NamePasswordCallbackHandler#NamePasswordCallbackHandler(String, String)}
   */
  @Test
  void testNewNamePasswordCallbackHandler2() throws IOException, UnsupportedCallbackException {
    // Arrange and Act
    NamePasswordCallbackHandler actualNamePasswordCallbackHandler = new NamePasswordCallbackHandler("janedoe",
        "https://example.org/example");
    PasswordCallback passwordCallback = new PasswordCallback("setObject", true);

    Callback[] callbacks = new Callback[]{passwordCallback};
    actualNamePasswordCallbackHandler.handle(callbacks);

    // Assert that nothing has changed
    assertEquals(1, callbacks.length);
    assertFalse(actualNamePasswordCallbackHandler.handleCallback(null));
    assertSame(passwordCallback, callbacks[0]);
  }

  /**
   * Method under test:
   * {@link NamePasswordCallbackHandler#NamePasswordCallbackHandler(String, String, String)}
   */
  @Test
  void testNewNamePasswordCallbackHandler3() throws IOException, UnsupportedCallbackException {
    // Arrange and Act
    NamePasswordCallbackHandler actualNamePasswordCallbackHandler = new NamePasswordCallbackHandler("janedoe",
        "https://example.org/example", "https://example.org/example");
    NameCallback nameCallback = new NameCallback("foo");
    Callback[] callbacks = new Callback[]{nameCallback};
    actualNamePasswordCallbackHandler.handle(callbacks);

    // Assert that nothing has changed
    Callback callback = callbacks[0];
    assertTrue(callback instanceof NameCallback);
    assertEquals("janedoe", ((NameCallback) callback).getName());
    assertEquals(1, callbacks.length);
    assertFalse(actualNamePasswordCallbackHandler.handleCallback(null));
    assertSame(nameCallback, callback);
  }

  /**
   * Method under test:
   * {@link NamePasswordCallbackHandler#NamePasswordCallbackHandler(String, String, String)}
   */
  @Test
  void testNewNamePasswordCallbackHandler4() throws IOException, UnsupportedCallbackException {
    // Arrange and Act
    NamePasswordCallbackHandler actualNamePasswordCallbackHandler = new NamePasswordCallbackHandler("janedoe",
        "https://example.org/example", "https://example.org/example");
    PasswordCallback passwordCallback = new PasswordCallback("foo", true);

    Callback[] callbacks = new Callback[]{passwordCallback};
    actualNamePasswordCallbackHandler.handle(callbacks);

    // Assert that nothing has changed
    assertEquals(1, callbacks.length);
    assertFalse(actualNamePasswordCallbackHandler.handleCallback(null));
    assertSame(passwordCallback, callbacks[0]);
  }

  /**
   * Method under test:
   * {@link NamePasswordCallbackHandler#handleCallback(Callback)}
   */
  @Test
  void testHandleCallback() {
    // Arrange
    NamePasswordCallbackHandler namePasswordCallbackHandler = new NamePasswordCallbackHandler("janedoe",
        "https://example.org/example");

    // Act and Assert
    assertFalse(namePasswordCallbackHandler.handleCallback(new CertStoreCallback()));
  }
}
