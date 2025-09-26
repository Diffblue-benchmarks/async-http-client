package org.asynchttpclient.spnego;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import jakarta.security.auth.message.callback.CertStoreCallback;
import java.io.IOException;
import javax.security.auth.callback.Callback;
import javax.security.auth.callback.NameCallback;
import javax.security.auth.callback.PasswordCallback;
import javax.security.auth.callback.UnsupportedCallbackException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

class NamePasswordCallbackHandlerDiffblueTest {
  /**
   * Test {@link NamePasswordCallbackHandler#NamePasswordCallbackHandler(String, String)}.
   *
   * <p>Method under test: {@link NamePasswordCallbackHandler#NamePasswordCallbackHandler(String,
   * String)}
   */
  @Test
  @DisplayName("Test new NamePasswordCallbackHandler(String, String)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void NamePasswordCallbackHandler.<init>(String, String)"})
  void testNewNamePasswordCallbackHandler() throws IOException, UnsupportedCallbackException {
    // Arrange and Act
    NamePasswordCallbackHandler actualNamePasswordCallbackHandler =
        new NamePasswordCallbackHandler("janedoe", "https://example.org/example");
    Callback[] callbacks = new Callback[] {new PasswordCallback("setObject", true)};
    actualNamePasswordCallbackHandler.handle(callbacks);

    // Assert
    Callback callback = callbacks[0];
    assertTrue(callback instanceof PasswordCallback);
    assertEquals(1, callbacks.length);
    assertFalse(actualNamePasswordCallbackHandler.handleCallback(null));
    assertArrayEquals(
        "https://example.org/example".toCharArray(), ((PasswordCallback) callback).getPassword());
  }

  /**
   * Test {@link NamePasswordCallbackHandler#NamePasswordCallbackHandler(String, String, String)}.
   *
   * <p>Method under test: {@link NamePasswordCallbackHandler#NamePasswordCallbackHandler(String,
   * String, String)}
   */
  @Test
  @DisplayName("Test new NamePasswordCallbackHandler(String, String, String)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void NamePasswordCallbackHandler.<init>(String, String, String)"})
  void testNewNamePasswordCallbackHandler2() throws IOException, UnsupportedCallbackException {
    // Arrange and Act
    NamePasswordCallbackHandler actualNamePasswordCallbackHandler =
        new NamePasswordCallbackHandler(
            "janedoe", "https://example.org/example", "https://example.org/example");
    Callback[] callbacks = new Callback[] {new PasswordCallback("foo", true)};
    actualNamePasswordCallbackHandler.handle(callbacks);

    // Assert
    Callback callback = callbacks[0];
    assertTrue(callback instanceof PasswordCallback);
    assertEquals(1, callbacks.length);
    assertFalse(actualNamePasswordCallbackHandler.handleCallback(null));
    assertArrayEquals(
        "https://example.org/example".toCharArray(), ((PasswordCallback) callback).getPassword());
  }

  /**
   * Test {@link NamePasswordCallbackHandler#NamePasswordCallbackHandler(String, String)}.
   *
   * <ul>
   *   <li>Then first element {@link NameCallback}.
   * </ul>
   *
   * <p>Method under test: {@link NamePasswordCallbackHandler#NamePasswordCallbackHandler(String,
   * String)}
   */
  @Test
  @DisplayName(
      "Test new NamePasswordCallbackHandler(String, String); then first element NameCallback")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void NamePasswordCallbackHandler.<init>(String, String)"})
  void testNewNamePasswordCallbackHandler_thenFirstElementNameCallback()
      throws IOException, UnsupportedCallbackException {
    // Arrange and Act
    NamePasswordCallbackHandler actualNamePasswordCallbackHandler =
        new NamePasswordCallbackHandler("janedoe", "https://example.org/example");
    Callback[] callbacks = new Callback[] {new NameCallback("setObject")};
    actualNamePasswordCallbackHandler.handle(callbacks);

    // Assert
    Callback callback = callbacks[0];
    assertTrue(callback instanceof NameCallback);
    assertEquals("janedoe", ((NameCallback) callback).getName());
    assertEquals(1, callbacks.length);
    assertFalse(actualNamePasswordCallbackHandler.handleCallback(null));
  }

  /**
   * Test {@link NamePasswordCallbackHandler#NamePasswordCallbackHandler(String, String, String)}.
   *
   * <ul>
   *   <li>Then first element {@link NameCallback}.
   * </ul>
   *
   * <p>Method under test: {@link NamePasswordCallbackHandler#NamePasswordCallbackHandler(String,
   * String, String)}
   */
  @Test
  @DisplayName(
      "Test new NamePasswordCallbackHandler(String, String, String); then first element NameCallback")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void NamePasswordCallbackHandler.<init>(String, String, String)"})
  void testNewNamePasswordCallbackHandler_thenFirstElementNameCallback2()
      throws IOException, UnsupportedCallbackException {
    // Arrange and Act
    NamePasswordCallbackHandler actualNamePasswordCallbackHandler =
        new NamePasswordCallbackHandler(
            "janedoe", "https://example.org/example", "https://example.org/example");
    Callback[] callbacks = new Callback[] {new NameCallback("foo")};
    actualNamePasswordCallbackHandler.handle(callbacks);

    // Assert
    Callback callback = callbacks[0];
    assertTrue(callback instanceof NameCallback);
    assertEquals("janedoe", ((NameCallback) callback).getName());
    assertEquals(1, callbacks.length);
    assertFalse(actualNamePasswordCallbackHandler.handleCallback(null));
  }

  /**
   * Test {@link NamePasswordCallbackHandler#NamePasswordCallbackHandler(String, String)}.
   *
   * <ul>
   *   <li>Then return not handleCallback {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link NamePasswordCallbackHandler#NamePasswordCallbackHandler(String,
   * String)}
   */
  @Test
  @DisplayName(
      "Test new NamePasswordCallbackHandler(String, String); then return not handleCallback 'null'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void NamePasswordCallbackHandler.<init>(String, String)"})
  void testNewNamePasswordCallbackHandler_thenReturnNotHandleCallbackNull()
      throws IOException, UnsupportedCallbackException {
    // Arrange and Act
    NamePasswordCallbackHandler actualNamePasswordCallbackHandler =
        new NamePasswordCallbackHandler("janedoe", "https://example.org/example");
    actualNamePasswordCallbackHandler.handle(new Callback[] {});

    // Assert
    assertFalse(actualNamePasswordCallbackHandler.handleCallback(null));
  }

  /**
   * Test {@link NamePasswordCallbackHandler#NamePasswordCallbackHandler(String, String, String)}.
   *
   * <ul>
   *   <li>Then return not handleCallback {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link NamePasswordCallbackHandler#NamePasswordCallbackHandler(String,
   * String, String)}
   */
  @Test
  @DisplayName(
      "Test new NamePasswordCallbackHandler(String, String, String); then return not handleCallback 'null'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void NamePasswordCallbackHandler.<init>(String, String, String)"})
  void testNewNamePasswordCallbackHandler_thenReturnNotHandleCallbackNull2()
      throws IOException, UnsupportedCallbackException {
    // Arrange and Act
    NamePasswordCallbackHandler actualNamePasswordCallbackHandler =
        new NamePasswordCallbackHandler(
            "janedoe", "https://example.org/example", "https://example.org/example");
    actualNamePasswordCallbackHandler.handle(new Callback[] {});

    // Assert
    assertFalse(actualNamePasswordCallbackHandler.handleCallback(null));
  }

  /**
   * Test {@link NamePasswordCallbackHandler#NamePasswordCallbackHandler(String, String)}.
   *
   * <ul>
   *   <li>When {@code null}.
   *   <li>Then first element Password is {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link NamePasswordCallbackHandler#NamePasswordCallbackHandler(String,
   * String)}
   */
  @Test
  @DisplayName(
      "Test new NamePasswordCallbackHandler(String, String); when 'null'; then first element Password is 'null'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void NamePasswordCallbackHandler.<init>(String, String)"})
  void testNewNamePasswordCallbackHandler_whenNull_thenFirstElementPasswordIsNull()
      throws IOException, UnsupportedCallbackException {
    // Arrange and Act
    NamePasswordCallbackHandler actualNamePasswordCallbackHandler =
        new NamePasswordCallbackHandler("janedoe", null);
    Callback[] callbacks = new Callback[] {new PasswordCallback("setObject", true)};
    actualNamePasswordCallbackHandler.handle(callbacks);

    // Assert
    Callback callback = callbacks[0];
    assertTrue(callback instanceof PasswordCallback);
    assertNull(((PasswordCallback) callback).getPassword());
    assertEquals(1, callbacks.length);
    assertFalse(actualNamePasswordCallbackHandler.handleCallback(null));
  }

  /**
   * Test {@link NamePasswordCallbackHandler#NamePasswordCallbackHandler(String, String, String)}.
   *
   * <ul>
   *   <li>When {@code null}.
   *   <li>Then first element Password is {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link NamePasswordCallbackHandler#NamePasswordCallbackHandler(String,
   * String, String)}
   */
  @Test
  @DisplayName(
      "Test new NamePasswordCallbackHandler(String, String, String); when 'null'; then first element Password is 'null'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void NamePasswordCallbackHandler.<init>(String, String, String)"})
  void testNewNamePasswordCallbackHandler_whenNull_thenFirstElementPasswordIsNull2()
      throws IOException, UnsupportedCallbackException {
    // Arrange and Act
    NamePasswordCallbackHandler actualNamePasswordCallbackHandler =
        new NamePasswordCallbackHandler("janedoe", null, "https://example.org/example");
    Callback[] callbacks = new Callback[] {new PasswordCallback("foo", true)};
    actualNamePasswordCallbackHandler.handle(callbacks);

    // Assert
    Callback callback = callbacks[0];
    assertTrue(callback instanceof PasswordCallback);
    assertNull(((PasswordCallback) callback).getPassword());
    assertEquals(1, callbacks.length);
    assertFalse(actualNamePasswordCallbackHandler.handleCallback(null));
  }

  /**
   * Test {@link NamePasswordCallbackHandler#handle(Callback[])}.
   *
   * <p>Method under test: {@link NamePasswordCallbackHandler#handle(Callback[])}
   */
  @Test
  @DisplayName("Test handle(Callback[])")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void NamePasswordCallbackHandler.handle(Callback[])"})
  void testHandle() throws IOException, UnsupportedCallbackException {
    // Arrange
    NamePasswordCallbackHandler namePasswordCallbackHandler =
        new NamePasswordCallbackHandler(
            "janedoe", "https://example.org/example", "https://example.org/example");

    // Act and Assert
    assertThrows(
        UnsupportedCallbackException.class,
        () -> namePasswordCallbackHandler.handle(new Callback[] {new CertStoreCallback()}));
  }

  /**
   * Test {@link NamePasswordCallbackHandler#handle(Callback[])}.
   *
   * <p>Method under test: {@link NamePasswordCallbackHandler#handle(Callback[])}
   */
  @Test
  @DisplayName("Test handle(Callback[])")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void NamePasswordCallbackHandler.handle(Callback[])"})
  void testHandle2() throws IOException, UnsupportedCallbackException {
    // Arrange
    NamePasswordCallbackHandler namePasswordCallbackHandler =
        new NamePasswordCallbackHandler("janedoe", null);
    Callback[] callbacks = new Callback[] {new PasswordCallback("setObject", true)};

    // Act
    namePasswordCallbackHandler.handle(callbacks);

    // Assert that nothing has changed
    assertTrue(callbacks[0] instanceof PasswordCallback);
    assertEquals(1, callbacks.length);
  }

  /**
   * Test {@link NamePasswordCallbackHandler#handle(Callback[])}.
   *
   * <ul>
   *   <li>Then first element {@link NameCallback}.
   * </ul>
   *
   * <p>Method under test: {@link NamePasswordCallbackHandler#handle(Callback[])}
   */
  @Test
  @DisplayName("Test handle(Callback[]); then first element NameCallback")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void NamePasswordCallbackHandler.handle(Callback[])"})
  void testHandle_thenFirstElementNameCallback() throws IOException, UnsupportedCallbackException {
    // Arrange
    NamePasswordCallbackHandler namePasswordCallbackHandler =
        new NamePasswordCallbackHandler("janedoe", "https://example.org/example");
    Callback[] callbacks = new Callback[] {new NameCallback("setObject")};

    // Act
    namePasswordCallbackHandler.handle(callbacks);

    // Assert
    Callback callback = callbacks[0];
    assertTrue(callback instanceof NameCallback);
    assertEquals("janedoe", ((NameCallback) callback).getName());
    assertEquals(1, callbacks.length);
  }

  /**
   * Test {@link NamePasswordCallbackHandler#handle(Callback[])}.
   *
   * <ul>
   *   <li>Then first element Password is {@code https://example.org/example} toCharArray.
   * </ul>
   *
   * <p>Method under test: {@link NamePasswordCallbackHandler#handle(Callback[])}
   */
  @Test
  @DisplayName(
      "Test handle(Callback[]); then first element Password is 'https://example.org/example' toCharArray")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void NamePasswordCallbackHandler.handle(Callback[])"})
  void testHandle_thenFirstElementPasswordIsHttpsExampleOrgExampleToCharArray()
      throws IOException, UnsupportedCallbackException {
    // Arrange
    NamePasswordCallbackHandler namePasswordCallbackHandler =
        new NamePasswordCallbackHandler("janedoe", "https://example.org/example");
    Callback[] callbacks = new Callback[] {new PasswordCallback("setObject", true)};

    // Act
    namePasswordCallbackHandler.handle(callbacks);

    // Assert
    Callback callback = callbacks[0];
    assertTrue(callback instanceof PasswordCallback);
    assertEquals(1, callbacks.length);
    assertArrayEquals(
        "https://example.org/example".toCharArray(), ((PasswordCallback) callback).getPassword());
  }

  /**
   * Test {@link NamePasswordCallbackHandler#handle(Callback[])}.
   *
   * <ul>
   *   <li>Then throw {@link UnsupportedCallbackException}.
   * </ul>
   *
   * <p>Method under test: {@link NamePasswordCallbackHandler#handle(Callback[])}
   */
  @Test
  @DisplayName("Test handle(Callback[]); then throw UnsupportedCallbackException")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void NamePasswordCallbackHandler.handle(Callback[])"})
  void testHandle_thenThrowUnsupportedCallbackException()
      throws IOException, UnsupportedCallbackException {
    // Arrange
    NamePasswordCallbackHandler namePasswordCallbackHandler =
        new NamePasswordCallbackHandler("janedoe", "https://example.org/example");

    // Act and Assert
    assertThrows(
        UnsupportedCallbackException.class,
        () -> namePasswordCallbackHandler.handle(new Callback[] {new CertStoreCallback()}));
  }

  /**
   * Test {@link NamePasswordCallbackHandler#handleCallback(Callback)}.
   *
   * <p>Method under test: {@link NamePasswordCallbackHandler#handleCallback(Callback)}
   */
  @Test
  @DisplayName("Test handleCallback(Callback)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean NamePasswordCallbackHandler.handleCallback(Callback)"})
  void testHandleCallback() {
    // Arrange
    NamePasswordCallbackHandler namePasswordCallbackHandler =
        new NamePasswordCallbackHandler("janedoe", "https://example.org/example");

    // Act and Assert
    assertFalse(namePasswordCallbackHandler.handleCallback(new CertStoreCallback()));
  }
}
