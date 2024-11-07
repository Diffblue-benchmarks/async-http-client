package org.asynchttpclient.spnego;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import jakarta.security.auth.message.callback.CertStoreCallback;
import java.io.IOException;
import javax.security.auth.callback.Callback;
import javax.security.auth.callback.NameCallback;
import javax.security.auth.callback.PasswordCallback;
import javax.security.auth.callback.UnsupportedCallbackException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class NamePasswordCallbackHandlerDiffblueTest {
  /**
   * Test
   * {@link NamePasswordCallbackHandler#NamePasswordCallbackHandler(String, String)}.
   * <ul>
   *   <li>Then first element {@link NameCallback}.</li>
   * </ul>
   * <p>
   * Method under test:
   * {@link NamePasswordCallbackHandler#NamePasswordCallbackHandler(String, String)}
   */
  @Test
  @DisplayName("Test new NamePasswordCallbackHandler(String, String); then first element NameCallback")
  void testNewNamePasswordCallbackHandler_thenFirstElementNameCallback()
      throws IOException, UnsupportedCallbackException {
    // Arrange and Act
    NamePasswordCallbackHandler actualNamePasswordCallbackHandler = new NamePasswordCallbackHandler("janedoe",
        "https://example.org/example");
    Callback[] callbacks = new Callback[]{new NameCallback("setObject")};
    actualNamePasswordCallbackHandler.handle(callbacks);

    // Assert that nothing has changed
    Callback callback = callbacks[0];
    assertTrue(callback instanceof NameCallback);
    assertEquals("janedoe", ((NameCallback) callback).getName());
    assertEquals(1, callbacks.length);
    assertFalse(actualNamePasswordCallbackHandler.handleCallback(null));
  }

  /**
   * Test
   * {@link NamePasswordCallbackHandler#NamePasswordCallbackHandler(String, String, String)}.
   * <ul>
   *   <li>Then first element {@link NameCallback}.</li>
   * </ul>
   * <p>
   * Method under test:
   * {@link NamePasswordCallbackHandler#NamePasswordCallbackHandler(String, String, String)}
   */
  @Test
  @DisplayName("Test new NamePasswordCallbackHandler(String, String, String); then first element NameCallback")
  void testNewNamePasswordCallbackHandler_thenFirstElementNameCallback2()
      throws IOException, UnsupportedCallbackException {
    // Arrange and Act
    NamePasswordCallbackHandler actualNamePasswordCallbackHandler = new NamePasswordCallbackHandler("janedoe",
        "https://example.org/example", "https://example.org/example");
    Callback[] callbacks = new Callback[]{new NameCallback("foo")};
    actualNamePasswordCallbackHandler.handle(callbacks);

    // Assert that nothing has changed
    Callback callback = callbacks[0];
    assertTrue(callback instanceof NameCallback);
    assertEquals("janedoe", ((NameCallback) callback).getName());
    assertEquals(1, callbacks.length);
    assertFalse(actualNamePasswordCallbackHandler.handleCallback(null));
  }

  /**
   * Test
   * {@link NamePasswordCallbackHandler#NamePasswordCallbackHandler(String, String)}.
   * <ul>
   *   <li>Then first element {@link PasswordCallback}.</li>
   * </ul>
   * <p>
   * Method under test:
   * {@link NamePasswordCallbackHandler#NamePasswordCallbackHandler(String, String)}
   */
  @Test
  @DisplayName("Test new NamePasswordCallbackHandler(String, String); then first element PasswordCallback")
  void testNewNamePasswordCallbackHandler_thenFirstElementPasswordCallback()
      throws IOException, UnsupportedCallbackException {
    // Arrange and Act
    NamePasswordCallbackHandler actualNamePasswordCallbackHandler = new NamePasswordCallbackHandler("janedoe",
        "https://example.org/example");
    Callback[] callbacks = new Callback[]{new PasswordCallback("setObject", true)};
    actualNamePasswordCallbackHandler.handle(callbacks);

    // Assert that nothing has changed
    Callback callback = callbacks[0];
    assertTrue(callback instanceof PasswordCallback);
    assertEquals(1, callbacks.length);
    char[] expectedPassword = "https://example.org/example".toCharArray();
    assertArrayEquals(expectedPassword, ((PasswordCallback) callback).getPassword());
  }

  /**
   * Test
   * {@link NamePasswordCallbackHandler#NamePasswordCallbackHandler(String, String, String)}.
   * <ul>
   *   <li>Then first element {@link PasswordCallback}.</li>
   * </ul>
   * <p>
   * Method under test:
   * {@link NamePasswordCallbackHandler#NamePasswordCallbackHandler(String, String, String)}
   */
  @Test
  @DisplayName("Test new NamePasswordCallbackHandler(String, String, String); then first element PasswordCallback")
  void testNewNamePasswordCallbackHandler_thenFirstElementPasswordCallback2()
      throws IOException, UnsupportedCallbackException {
    // Arrange and Act
    NamePasswordCallbackHandler actualNamePasswordCallbackHandler = new NamePasswordCallbackHandler("janedoe",
        "https://example.org/example", "https://example.org/example");
    Callback[] callbacks = new Callback[]{new PasswordCallback("foo", true)};
    actualNamePasswordCallbackHandler.handle(callbacks);

    // Assert that nothing has changed
    Callback callback = callbacks[0];
    assertTrue(callback instanceof PasswordCallback);
    assertEquals(1, callbacks.length);
    char[] expectedPassword = "https://example.org/example".toCharArray();
    assertArrayEquals(expectedPassword, ((PasswordCallback) callback).getPassword());
  }

  /**
   * Test {@link NamePasswordCallbackHandler#handle(Callback[])}.
   * <p>
   * Method under test: {@link NamePasswordCallbackHandler#handle(Callback[])}
   */
  @Test
  @DisplayName("Test handle(Callback[])")
  void testHandle() throws IOException, UnsupportedCallbackException {
    // Arrange
    NamePasswordCallbackHandler namePasswordCallbackHandler = new NamePasswordCallbackHandler("janedoe",
        "https://example.org/example", "https://example.org/example");

    // Act and Assert
    assertThrows(UnsupportedCallbackException.class,
        () -> namePasswordCallbackHandler.handle(new Callback[]{new CertStoreCallback()}));
  }

  /**
   * Test {@link NamePasswordCallbackHandler#handle(Callback[])}.
   * <p>
   * Method under test: {@link NamePasswordCallbackHandler#handle(Callback[])}
   */
  @Test
  @DisplayName("Test handle(Callback[])")
  void testHandle2() throws IOException, UnsupportedCallbackException {
    // Arrange
    NamePasswordCallbackHandler namePasswordCallbackHandler = new NamePasswordCallbackHandler("janedoe", null);
    Callback[] callbacks = new Callback[]{new PasswordCallback("setObject", true)};

    // Act
    namePasswordCallbackHandler.handle(callbacks);

    // Assert that nothing has changed
    assertTrue(callbacks[0] instanceof PasswordCallback);
    assertEquals(1, callbacks.length);
  }

  /**
   * Test {@link NamePasswordCallbackHandler#handle(Callback[])}.
   * <ul>
   *   <li>Then first element {@link NameCallback}.</li>
   * </ul>
   * <p>
   * Method under test: {@link NamePasswordCallbackHandler#handle(Callback[])}
   */
  @Test
  @DisplayName("Test handle(Callback[]); then first element NameCallback")
  void testHandle_thenFirstElementNameCallback() throws IOException, UnsupportedCallbackException {
    // Arrange
    NamePasswordCallbackHandler namePasswordCallbackHandler = new NamePasswordCallbackHandler("janedoe",
        "https://example.org/example");
    Callback[] callbacks = new Callback[]{new NameCallback("setObject")};

    // Act
    namePasswordCallbackHandler.handle(callbacks);

    // Assert that nothing has changed
    Callback callback = callbacks[0];
    assertTrue(callback instanceof NameCallback);
    assertEquals("janedoe", ((NameCallback) callback).getName());
    assertEquals(1, callbacks.length);
  }

  /**
   * Test {@link NamePasswordCallbackHandler#handle(Callback[])}.
   * <ul>
   *   <li>Then first element Password is {@code https://example.org/example}
   * toCharArray.</li>
   * </ul>
   * <p>
   * Method under test: {@link NamePasswordCallbackHandler#handle(Callback[])}
   */
  @Test
  @DisplayName("Test handle(Callback[]); then first element Password is 'https://example.org/example' toCharArray")
  void testHandle_thenFirstElementPasswordIsHttpsExampleOrgExampleToCharArray()
      throws IOException, UnsupportedCallbackException {
    // Arrange
    NamePasswordCallbackHandler namePasswordCallbackHandler = new NamePasswordCallbackHandler("janedoe",
        "https://example.org/example");
    Callback[] callbacks = new Callback[]{new PasswordCallback("setObject", true)};

    // Act
    namePasswordCallbackHandler.handle(callbacks);

    // Assert that nothing has changed
    Callback callback = callbacks[0];
    assertTrue(callback instanceof PasswordCallback);
    assertEquals(1, callbacks.length);
    char[] expectedPassword = "https://example.org/example".toCharArray();
    assertArrayEquals(expectedPassword, ((PasswordCallback) callback).getPassword());
  }

  /**
   * Test {@link NamePasswordCallbackHandler#handle(Callback[])}.
   * <ul>
   *   <li>Then throw {@link UnsupportedCallbackException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link NamePasswordCallbackHandler#handle(Callback[])}
   */
  @Test
  @DisplayName("Test handle(Callback[]); then throw UnsupportedCallbackException")
  void testHandle_thenThrowUnsupportedCallbackException() throws IOException, UnsupportedCallbackException {
    // Arrange
    NamePasswordCallbackHandler namePasswordCallbackHandler = new NamePasswordCallbackHandler("janedoe",
        "https://example.org/example");

    // Act and Assert
    assertThrows(UnsupportedCallbackException.class,
        () -> namePasswordCallbackHandler.handle(new Callback[]{new CertStoreCallback()}));
  }

  /**
   * Test {@link NamePasswordCallbackHandler#handleCallback(Callback)}.
   * <p>
   * Method under test:
   * {@link NamePasswordCallbackHandler#handleCallback(Callback)}
   */
  @Test
  @DisplayName("Test handleCallback(Callback)")
  void testHandleCallback() {
    // Arrange
    NamePasswordCallbackHandler namePasswordCallbackHandler = new NamePasswordCallbackHandler("janedoe",
        "https://example.org/example");

    // Act and Assert
    assertFalse(namePasswordCallbackHandler.handleCallback(new CertStoreCallback()));
  }
}
