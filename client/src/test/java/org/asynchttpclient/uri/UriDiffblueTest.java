package org.asynchttpclient.uri;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import java.net.URISyntaxException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class UriDiffblueTest {
  /**
   * Test {@link Uri#Uri(String, String, String, int, String, String, String)}.
   * <ul>
   *   <li>When {@code https://example.org/example}.</li>
   *   <li>Then return Scheme is {@code https://example.org/example}.</li>
   * </ul>
   * <p>
   * Method under test:
   * {@link Uri#Uri(String, String, String, int, String, String, String)}
   */
  @Test
  @DisplayName("Test new Uri(String, String, String, int, String, String, String); when 'https://example.org/example'; then return Scheme is 'https://example.org/example'")
  void testNewUri_whenHttpsExampleOrgExample_thenReturnSchemeIsHttpsExampleOrgExample() throws URISyntaxException {
    // Arrange and Act
    Uri actualUri = new Uri("https://example.org/example", "https://example.org/example", "https://example.org/example",
        8080, "https://example.org/example", "https://example.org/example", "https://example.org/example");

    // Assert
    assertEquals("https://example.org/example", actualUri.getScheme());
    assertEquals("https://example.org/example://https://example.org/example:8080", actualUri.getBaseUrl());
    assertEquals("https://example.org/example://https://example.org/example@https://example.org/example:8080https:/"
        + "/example.org/example?https://example.org/example", actualUri.toJavaNetURI().toString());
    assertEquals(80, actualUri.getSchemeDefaultPort());
    assertFalse(actualUri.isSecured());
    assertFalse(actualUri.isWebSocket());
  }

  /**
   * Test {@link Uri#Uri(String, String, String, int, String, String, String)}.
   * <ul>
   *   <li>When {@link Uri#HTTPS}.</li>
   *   <li>Then return BaseUrl is
   * {@code https://https://example.org/example:8080}.</li>
   * </ul>
   * <p>
   * Method under test:
   * {@link Uri#Uri(String, String, String, int, String, String, String)}
   */
  @Test
  @DisplayName("Test new Uri(String, String, String, int, String, String, String); when HTTPS; then return BaseUrl is 'https://https://example.org/example:8080'")
  void testNewUri_whenHttps_thenReturnBaseUrlIsHttpsHttpsExampleOrgExample8080() throws URISyntaxException {
    // Arrange and Act
    Uri actualUri = new Uri(Uri.HTTPS, "https://example.org/example", "https://example.org/example", 8080,
        "https://example.org/example", "https://example.org/example", "https://example.org/example");

    // Assert
    assertEquals("https://https://example.org/example:8080", actualUri.getBaseUrl());
    assertEquals("https://https://example.org/example@https://example.org/example:8080https://example.org/example?https"
        + "://example.org/example", actualUri.toJavaNetURI().toString());
    assertEquals(443, actualUri.getSchemeDefaultPort());
    assertFalse(actualUri.isWebSocket());
    assertTrue(actualUri.isSecured());
    assertEquals(Uri.HTTPS, actualUri.getScheme());
  }

  /**
   * Test {@link Uri#Uri(String, String, String, int, String, String, String)}.
   * <ul>
   *   <li>When {@link Uri#WS}.</li>
   *   <li>Then return BaseUrl is
   * {@code ws://https://example.org/example:8080}.</li>
   * </ul>
   * <p>
   * Method under test:
   * {@link Uri#Uri(String, String, String, int, String, String, String)}
   */
  @Test
  @DisplayName("Test new Uri(String, String, String, int, String, String, String); when WS; then return BaseUrl is 'ws://https://example.org/example:8080'")
  void testNewUri_whenWs_thenReturnBaseUrlIsWsHttpsExampleOrgExample8080() throws URISyntaxException {
    // Arrange and Act
    Uri actualUri = new Uri(Uri.WS, "https://example.org/example", "https://example.org/example", 8080,
        "https://example.org/example", "https://example.org/example", "https://example.org/example");

    // Assert
    assertEquals("ws://https://example.org/example:8080", actualUri.getBaseUrl());
    assertEquals("ws://https://example.org/example@https://example.org/example:8080https://example.org/example?https:/"
        + "/example.org/example", actualUri.toJavaNetURI().toString());
    assertEquals(80, actualUri.getSchemeDefaultPort());
    assertFalse(actualUri.isSecured());
    assertTrue(actualUri.isWebSocket());
    assertEquals(Uri.WS, actualUri.getScheme());
  }

  /**
   * Test {@link Uri#Uri(String, String, String, int, String, String, String)}.
   * <ul>
   *   <li>When {@link Uri#WSS}.</li>
   *   <li>Then return BaseUrl is
   * {@code wss://https://example.org/example:8080}.</li>
   * </ul>
   * <p>
   * Method under test:
   * {@link Uri#Uri(String, String, String, int, String, String, String)}
   */
  @Test
  @DisplayName("Test new Uri(String, String, String, int, String, String, String); when WSS; then return BaseUrl is 'wss://https://example.org/example:8080'")
  void testNewUri_whenWss_thenReturnBaseUrlIsWssHttpsExampleOrgExample8080() throws URISyntaxException {
    // Arrange and Act
    Uri actualUri = new Uri(Uri.WSS, "https://example.org/example", "https://example.org/example", 8080,
        "https://example.org/example", "https://example.org/example", "https://example.org/example");

    // Assert
    assertEquals("wss://https://example.org/example:8080", actualUri.getBaseUrl());
    assertEquals("wss://https://example.org/example@https://example.org/example:8080https://example.org/example?https:"
        + "//example.org/example", actualUri.toJavaNetURI().toString());
    assertEquals(443, actualUri.getSchemeDefaultPort());
    assertTrue(actualUri.isSecured());
    assertTrue(actualUri.isWebSocket());
    assertEquals(Uri.WSS, actualUri.getScheme());
  }

  /**
   * Test {@link Uri#create(Uri, String)} with {@code context},
   * {@code originalUrl}.
   * <p>
   * Method under test: {@link Uri#create(Uri, String)}
   */
  @Test
  @DisplayName("Test create(Uri, String) with 'context', 'originalUrl'")
  void testCreateWithContextOriginalUrl() throws URISyntaxException {
    // Arrange and Act
    Uri actualCreateResult = Uri.create(
        new Uri(Uri.HTTPS, "https://example.org/example", "https://example.org/example", 8080,
            "https://example.org/example", "https://example.org/example", "https://example.org/example"),
        "https://example.org/example");

    // Assert
    assertEquals("/example", actualCreateResult.getNonEmptyPath());
    assertEquals("/example", actualCreateResult.getPath());
    assertEquals("example.org", actualCreateResult.getHost());
    assertEquals("example.org:443", actualCreateResult.getAuthority());
    assertEquals("https://example.org/example", actualCreateResult.toJavaNetURI().toString());
    assertEquals("https://example.org:443", actualCreateResult.getBaseUrl());
    assertNull(actualCreateResult.getUserInfo());
    assertEquals(-1, actualCreateResult.getPort());
    assertEquals(443, actualCreateResult.getExplicitPort());
    assertEquals(Uri.HTTPS, actualCreateResult.getScheme());
  }

  /**
   * Test {@link Uri#create(Uri, String)} with {@code context},
   * {@code originalUrl}.
   * <p>
   * Method under test: {@link Uri#create(Uri, String)}
   */
  @Test
  @DisplayName("Test create(Uri, String) with 'context', 'originalUrl'")
  void testCreateWithContextOriginalUrl2() {
    // Arrange
    Uri context = Uri.create(
        new Uri("https://example.org/example", "https://example.org/example", "https://example.org/example", 8080,
            "https://example.org/example", "https://example.org/example", "https://example.org/example"),
        "https://example.org/example");

    // Act and Assert
    assertEquals(context, Uri.create(context, "https://example.org/example"));
  }

  /**
   * Test {@link Uri#create(Uri, String)} with {@code context},
   * {@code originalUrl}.
   * <p>
   * Method under test: {@link Uri#create(Uri, String)}
   */
  @Test
  @DisplayName("Test create(Uri, String) with 'context', 'originalUrl'")
  void testCreateWithContextOriginalUrl3() throws URISyntaxException {
    // Arrange and Act
    Uri actualCreateResult = Uri.create(
        new Uri("https://example.org/example", "https://example.org/example", "https://example.org/example", 8080,
            "https://example.org/example", "https://example.org/example", "https://example.org/example"),
        "originalUrl");

    // Assert
    assertEquals("https://example.org/example", actualCreateResult.getScheme());
    assertEquals("https://example.org/example://https://example.org/example:8080", actualCreateResult.getBaseUrl());
    assertEquals("https://example.org/example://https://example.org/example@https://example.org/example:8080https:/"
        + "/example.org/originalUrl", actualCreateResult.toJavaNetURI().toString());
    assertEquals("https://example.org/originalUrl", actualCreateResult.getNonEmptyPath());
    assertEquals("https://example.org/originalUrl", actualCreateResult.getPath());
  }

  /**
   * Test {@link Uri#create(Uri, String)} with {@code context},
   * {@code originalUrl}.
   * <p>
   * Method under test: {@link Uri#create(Uri, String)}
   */
  @Test
  @DisplayName("Test create(Uri, String) with 'context', 'originalUrl'")
  void testCreateWithContextOriginalUrl4() throws URISyntaxException {
    // Arrange and Act
    Uri actualCreateResult = Uri.create(new Uri(Uri.HTTPS, "https://example.org/example", "https://example.org/example",
        8080, "", "https://example.org/example", "https://example.org/example"), "https://example.org/example");

    // Assert
    assertEquals("/example", actualCreateResult.getNonEmptyPath());
    assertEquals("/example", actualCreateResult.getPath());
    assertEquals("example.org", actualCreateResult.getHost());
    assertEquals("example.org:443", actualCreateResult.getAuthority());
    assertEquals("https://example.org/example", actualCreateResult.toJavaNetURI().toString());
    assertEquals("https://example.org:443", actualCreateResult.getBaseUrl());
    assertNull(actualCreateResult.getUserInfo());
    assertEquals(-1, actualCreateResult.getPort());
    assertEquals(443, actualCreateResult.getExplicitPort());
    assertEquals(Uri.HTTPS, actualCreateResult.getScheme());
  }

  /**
   * Test {@link Uri#create(Uri, String)} with {@code context},
   * {@code originalUrl}.
   * <p>
   * Method under test: {@link Uri#create(Uri, String)}
   */
  @Test
  @DisplayName("Test create(Uri, String) with 'context', 'originalUrl'")
  void testCreateWithContextOriginalUrl5() throws URISyntaxException {
    // Arrange and Act
    Uri actualCreateResult = Uri.create(
        new Uri(Uri.WSS, "https://example.org/example", "https://example.org/example", 8080,
            "https://example.org/example", "https://example.org/example", "https://example.org/example"),
        "originalUrl");

    // Assert
    assertEquals("wss://https://example.org/example:8080", actualCreateResult.getBaseUrl());
    assertEquals("wss://https://example.org/example@https://example.org/example:8080https://example.org/originalUrl",
        actualCreateResult.toJavaNetURI().toString());
    assertTrue(actualCreateResult.isWebSocket());
    assertEquals(Uri.WSS, actualCreateResult.getScheme());
  }

  /**
   * Test {@link Uri#create(Uri, String)} with {@code context},
   * {@code originalUrl}.
   * <ul>
   *   <li>Then return BaseUrl is
   * {@code ws://https://example.org/example:8080}.</li>
   * </ul>
   * <p>
   * Method under test: {@link Uri#create(Uri, String)}
   */
  @Test
  @DisplayName("Test create(Uri, String) with 'context', 'originalUrl'; then return BaseUrl is 'ws://https://example.org/example:8080'")
  void testCreateWithContextOriginalUrl_thenReturnBaseUrlIsWsHttpsExampleOrgExample8080() throws URISyntaxException {
    // Arrange and Act
    Uri actualCreateResult = Uri.create(
        new Uri(Uri.WS, "https://example.org/example", "https://example.org/example", 8080,
            "https://example.org/example", "https://example.org/example", "https://example.org/example"),
        "originalUrl");

    // Assert
    assertEquals("ws://https://example.org/example:8080", actualCreateResult.getBaseUrl());
    assertEquals("ws://https://example.org/example@https://example.org/example:8080https://example.org/originalUrl",
        actualCreateResult.toJavaNetURI().toString());
    assertTrue(actualCreateResult.isWebSocket());
    assertEquals(Uri.WS, actualCreateResult.getScheme());
  }

  /**
   * Test {@link Uri#create(Uri, String)} with {@code context},
   * {@code originalUrl}.
   * <ul>
   *   <li>Then return NonEmptyPath is {@code /example}.</li>
   * </ul>
   * <p>
   * Method under test: {@link Uri#create(Uri, String)}
   */
  @Test
  @DisplayName("Test create(Uri, String) with 'context', 'originalUrl'; then return NonEmptyPath is '/example'")
  void testCreateWithContextOriginalUrl_thenReturnNonEmptyPathIsExample() throws URISyntaxException {
    // Arrange and Act
    Uri actualCreateResult = Uri.create(
        new Uri("https://example.org/example", "https://example.org/example", "https://example.org/example", 8080,
            "https://example.org/example", "https://example.org/example", "https://example.org/example"),
        "https://example.org/example");

    // Assert
    assertEquals("/example", actualCreateResult.getNonEmptyPath());
    assertEquals("/example", actualCreateResult.getPath());
    assertEquals("example.org", actualCreateResult.getHost());
    assertEquals("example.org:443", actualCreateResult.getAuthority());
    assertEquals("https://example.org/example", actualCreateResult.toJavaNetURI().toString());
    assertEquals("https://example.org:443", actualCreateResult.getBaseUrl());
    assertNull(actualCreateResult.getUserInfo());
    assertEquals(-1, actualCreateResult.getPort());
    assertEquals(443, actualCreateResult.getExplicitPort());
    assertEquals(Uri.HTTPS, actualCreateResult.getScheme());
  }

  /**
   * Test {@link Uri#create(Uri, String)} with {@code context},
   * {@code originalUrl}.
   * <ul>
   *   <li>Then return NonEmptyPath is {@code originalUrl}.</li>
   * </ul>
   * <p>
   * Method under test: {@link Uri#create(Uri, String)}
   */
  @Test
  @DisplayName("Test create(Uri, String) with 'context', 'originalUrl'; then return NonEmptyPath is 'originalUrl'")
  void testCreateWithContextOriginalUrl_thenReturnNonEmptyPathIsOriginalUrl() throws URISyntaxException {
    // Arrange and Act
    Uri actualCreateResult = Uri
        .create(new Uri("https://example.org/example", "https://example.org/example", "https://example.org/example",
            8080, "originalUrl", "https://example.org/example", "https://example.org/example"), "originalUrl");

    // Assert
    assertEquals("https://example.org/example", actualCreateResult.getScheme());
    assertEquals("https://example.org/example://https://example.org/example:8080", actualCreateResult.getBaseUrl());
    assertEquals(
        "https://example.org/example://https://example.org/example@https://example.org/example" + ":8080originalUrl",
        actualCreateResult.toJavaNetURI().toString());
    assertEquals("originalUrl", actualCreateResult.getNonEmptyPath());
    assertEquals("originalUrl", actualCreateResult.getPath());
  }

  /**
   * Test {@link Uri#create(Uri, String)} with {@code context},
   * {@code originalUrl}.
   * <ul>
   *   <li>Then return NonEmptyPath is {@code /../originalUrl}.</li>
   * </ul>
   * <p>
   * Method under test: {@link Uri#create(Uri, String)}
   */
  @Test
  @DisplayName("Test create(Uri, String) with 'context', 'originalUrl'; then return NonEmptyPath is '/../originalUrl'")
  void testCreateWithContextOriginalUrl_thenReturnNonEmptyPathIsOriginalUrl2() throws URISyntaxException {
    // Arrange and Act
    Uri actualCreateResult = Uri.create(new Uri("https://example.org/example", "https://example.org/example",
        "https://example.org/example", 8080, "/../", "https://example.org/example", "https://example.org/example"),
        "originalUrl");

    // Assert
    assertEquals("/../originalUrl", actualCreateResult.getNonEmptyPath());
    assertEquals("/../originalUrl", actualCreateResult.getPath());
    assertEquals("https://example.org/example", actualCreateResult.getScheme());
    assertEquals("https://example.org/example://https://example.org/example:8080", actualCreateResult.getBaseUrl());
    assertEquals("https://example.org/example://https://example.org/example@https://example.org/example:8080/.."
        + "/originalUrl", actualCreateResult.toJavaNetURI().toString());
  }

  /**
   * Test {@link Uri#create(Uri, String)} with {@code context},
   * {@code originalUrl}.
   * <ul>
   *   <li>Then return NonEmptyPath is {@code ////}.</li>
   * </ul>
   * <p>
   * Method under test: {@link Uri#create(Uri, String)}
   */
  @Test
  @DisplayName("Test create(Uri, String) with 'context', 'originalUrl'; then return NonEmptyPath is '////'")
  void testCreateWithContextOriginalUrl_thenReturnNonEmptyPathIsSlashSlashSlashSlash() throws URISyntaxException {
    // Arrange and Act
    Uri actualCreateResult = Uri
        .create(new Uri("https://example.org/example", "https://example.org/example", "https://example.org/example",
            8080, "https://example.org/example", "https://example.org/example", "https://example.org/example"), "////");

    // Assert
    assertEquals("////", actualCreateResult.getNonEmptyPath());
    assertEquals("////", actualCreateResult.getPath());
    assertEquals("https://example.org/example", actualCreateResult.getScheme());
    assertEquals("https://example.org/example://https://example.org/example:8080", actualCreateResult.getBaseUrl());
    assertEquals("https://example.org/example://https://example.org/example@https://example.org/example:8080////",
        actualCreateResult.toJavaNetURI().toString());
  }

  /**
   * Test {@link Uri#create(Uri, String)} with {@code context},
   * {@code originalUrl}.
   * <ul>
   *   <li>When empty string.</li>
   * </ul>
   * <p>
   * Method under test: {@link Uri#create(Uri, String)}
   */
  @Test
  @DisplayName("Test create(Uri, String) with 'context', 'originalUrl'; when empty string")
  void testCreateWithContextOriginalUrl_whenEmptyString() {
    // Arrange
    Uri context = new Uri("https://example.org/example", "https://example.org/example", "https://example.org/example",
        8080, "https://example.org/example", "https://example.org/example", "https://example.org/example");

    // Act and Assert
    assertEquals(context, Uri.create(context, ""));
  }

  /**
   * Test {@link Uri#create(Uri, String)} with {@code context},
   * {@code originalUrl}.
   * <ul>
   *   <li>When {@code null}.</li>
   *   <li>Then return NonEmptyPath is {@code /example}.</li>
   * </ul>
   * <p>
   * Method under test: {@link Uri#create(Uri, String)}
   */
  @Test
  @DisplayName("Test create(Uri, String) with 'context', 'originalUrl'; when 'null'; then return NonEmptyPath is '/example'")
  void testCreateWithContextOriginalUrl_whenNull_thenReturnNonEmptyPathIsExample() throws URISyntaxException {
    // Arrange and Act
    Uri actualCreateResult = Uri.create(null, "https://example.org/example");

    // Assert
    assertEquals("/example", actualCreateResult.getNonEmptyPath());
    assertEquals("/example", actualCreateResult.getPath());
    assertEquals("example.org", actualCreateResult.getHost());
    assertEquals("example.org:443", actualCreateResult.getAuthority());
    assertEquals("https://example.org/example", actualCreateResult.toJavaNetURI().toString());
    assertEquals("https://example.org:443", actualCreateResult.getBaseUrl());
    assertNull(actualCreateResult.getUserInfo());
    assertEquals(-1, actualCreateResult.getPort());
    assertEquals(443, actualCreateResult.getExplicitPort());
    assertEquals(Uri.HTTPS, actualCreateResult.getScheme());
  }

  /**
   * Test {@link Uri#create(Uri, String)} with {@code context},
   * {@code originalUrl}.
   * <ul>
   *   <li>When {@code null}.</li>
   *   <li>Then throw {@link IllegalArgumentException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link Uri#create(Uri, String)}
   */
  @Test
  @DisplayName("Test create(Uri, String) with 'context', 'originalUrl'; when 'null'; then throw IllegalArgumentException")
  void testCreateWithContextOriginalUrl_whenNull_thenThrowIllegalArgumentException() {
    // Arrange, Act and Assert
    assertThrows(IllegalArgumentException.class, () -> Uri.create(null, "originalUrl"));
  }

  /**
   * Test {@link Uri#create(Uri, String)} with {@code context},
   * {@code originalUrl}.
   * <ul>
   *   <li>When {@code //}.</li>
   * </ul>
   * <p>
   * Method under test: {@link Uri#create(Uri, String)}
   */
  @Test
  @DisplayName("Test create(Uri, String) with 'context', 'originalUrl'; when '//'")
  void testCreateWithContextOriginalUrl_whenSlashSlash() {
    // Arrange, Act and Assert
    assertThrows(IllegalArgumentException.class,
        () -> Uri
            .create(
                new Uri("https://example.org/example", "https://example.org/example", "https://example.org/example",
                    8080, "https://example.org/example", "https://example.org/example", "https://example.org/example"),
                "//"));
  }

  /**
   * Test {@link Uri#create(Uri, String)} with {@code context},
   * {@code originalUrl}.
   * <ul>
   *   <li>When {@code url:}.</li>
   * </ul>
   * <p>
   * Method under test: {@link Uri#create(Uri, String)}
   */
  @Test
  @DisplayName("Test create(Uri, String) with 'context', 'originalUrl'; when 'url:'")
  void testCreateWithContextOriginalUrl_whenUrl() {
    // Arrange
    Uri context = new Uri("https://example.org/example", "https://example.org/example", "https://example.org/example",
        8080, "https://example.org/example", "https://example.org/example", "https://example.org/example");

    // Act and Assert
    assertEquals(context, Uri.create(context, "url:"));
  }

  /**
   * Test {@link Uri#create(String)} with {@code originalUrl}.
   * <ul>
   *   <li>Then return Authority is {@code example.org:80}.</li>
   * </ul>
   * <p>
   * Method under test: {@link Uri#create(String)}
   */
  @Test
  @DisplayName("Test create(String) with 'originalUrl'; then return Authority is 'example.org:80'")
  void testCreateWithOriginalUrl_thenReturnAuthorityIsExampleOrg80() throws URISyntaxException {
    // Arrange and Act
    Uri actualCreateResult = Uri.create("originalUrlhttps://example.org/example");

    // Assert
    assertEquals("example.org:80", actualCreateResult.getAuthority());
    assertEquals("originalurlhttps", actualCreateResult.getScheme());
    assertEquals("originalurlhttps://example.org/example", actualCreateResult.toJavaNetURI().toString());
    assertEquals("originalurlhttps://example.org:80", actualCreateResult.getBaseUrl());
    assertEquals(80, actualCreateResult.getExplicitPort());
    assertEquals(80, actualCreateResult.getSchemeDefaultPort());
    assertFalse(actualCreateResult.isSecured());
  }

  /**
   * Test {@link Uri#create(String)} with {@code originalUrl}.
   * <ul>
   *   <li>Then return NonEmptyPath is {@code /example}.</li>
   * </ul>
   * <p>
   * Method under test: {@link Uri#create(String)}
   */
  @Test
  @DisplayName("Test create(String) with 'originalUrl'; then return NonEmptyPath is '/example'")
  void testCreateWithOriginalUrl_thenReturnNonEmptyPathIsExample() throws URISyntaxException {
    // Arrange and Act
    Uri actualCreateResult = Uri.create("https://example.org/example");

    // Assert
    assertEquals("/example", actualCreateResult.getNonEmptyPath());
    assertEquals("/example", actualCreateResult.getPath());
    assertEquals("example.org:443", actualCreateResult.getAuthority());
    assertEquals("https://example.org/example", actualCreateResult.toJavaNetURI().toString());
    assertEquals("https://example.org:443", actualCreateResult.getBaseUrl());
    assertEquals(443, actualCreateResult.getExplicitPort());
    assertEquals(443, actualCreateResult.getSchemeDefaultPort());
    assertTrue(actualCreateResult.isSecured());
    assertEquals(Uri.HTTPS, actualCreateResult.getScheme());
  }

  /**
   * Test {@link Uri#create(String)} with {@code originalUrl}.
   * <ul>
   *   <li>Then return NonEmptyPath is
   * {@code /examplehttps://example.org/example}.</li>
   * </ul>
   * <p>
   * Method under test: {@link Uri#create(String)}
   */
  @Test
  @DisplayName("Test create(String) with 'originalUrl'; then return NonEmptyPath is '/examplehttps://example.org/example'")
  void testCreateWithOriginalUrl_thenReturnNonEmptyPathIsExamplehttpsExampleOrgExample() throws URISyntaxException {
    // Arrange and Act
    Uri actualCreateResult = Uri.create("https://example.org/examplehttps://example.org/example");

    // Assert
    assertEquals("/examplehttps://example.org/example", actualCreateResult.getNonEmptyPath());
    assertEquals("/examplehttps://example.org/example", actualCreateResult.getPath());
    assertEquals("example.org:443", actualCreateResult.getAuthority());
    assertEquals("https://example.org/examplehttps://example.org/example",
        actualCreateResult.toJavaNetURI().toString());
    assertEquals("https://example.org:443", actualCreateResult.getBaseUrl());
    assertEquals(443, actualCreateResult.getExplicitPort());
    assertEquals(443, actualCreateResult.getSchemeDefaultPort());
    assertTrue(actualCreateResult.isSecured());
    assertEquals(Uri.HTTPS, actualCreateResult.getScheme());
  }

  /**
   * Test {@link Uri#create(String)} with {@code originalUrl}.
   * <ul>
   *   <li>When {@code 42https://example.org/example}.</li>
   * </ul>
   * <p>
   * Method under test: {@link Uri#create(String)}
   */
  @Test
  @DisplayName("Test create(String) with 'originalUrl'; when '42https://example.org/example'")
  void testCreateWithOriginalUrl_when42httpsExampleOrgExample() {
    // Arrange, Act and Assert
    assertThrows(IllegalArgumentException.class, () -> Uri.create("42https://example.org/example"));
  }

  /**
   * Test {@link Uri#create(String)} with {@code originalUrl}.
   * <ul>
   *   <li>When empty string.</li>
   *   <li>Then throw {@link IllegalArgumentException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link Uri#create(String)}
   */
  @Test
  @DisplayName("Test create(String) with 'originalUrl'; when empty string; then throw IllegalArgumentException")
  void testCreateWithOriginalUrl_whenEmptyString_thenThrowIllegalArgumentException() {
    // Arrange, Act and Assert
    assertThrows(IllegalArgumentException.class, () -> Uri.create(""));
  }

  /**
   * Test {@link Uri#create(String)} with {@code originalUrl}.
   * <ul>
   *   <li>When {@code //https://example.org/example}.</li>
   * </ul>
   * <p>
   * Method under test: {@link Uri#create(String)}
   */
  @Test
  @DisplayName("Test create(String) with 'originalUrl'; when '//https://example.org/example'")
  void testCreateWithOriginalUrl_whenHttpsExampleOrgExample() {
    // Arrange, Act and Assert
    assertThrows(IllegalArgumentException.class, () -> Uri.create("//https://example.org/example"));
  }

  /**
   * Test {@link Uri#create(String)} with {@code originalUrl}.
   * <ul>
   *   <li>When {@code originalUrl}.</li>
   *   <li>Then throw {@link IllegalArgumentException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link Uri#create(String)}
   */
  @Test
  @DisplayName("Test create(String) with 'originalUrl'; when 'originalUrl'; then throw IllegalArgumentException")
  void testCreateWithOriginalUrl_whenOriginalUrl_thenThrowIllegalArgumentException() {
    // Arrange, Act and Assert
    assertThrows(IllegalArgumentException.class, () -> Uri.create("originalUrl"));
  }

  /**
   * Test {@link Uri#create(String)} with {@code originalUrl}.
   * <ul>
   *   <li>When {@code Original Urlhttps://example.org/example}.</li>
   * </ul>
   * <p>
   * Method under test: {@link Uri#create(String)}
   */
  @Test
  @DisplayName("Test create(String) with 'originalUrl'; when 'Original Urlhttps://example.org/example'")
  void testCreateWithOriginalUrl_whenOriginalUrlhttpsExampleOrgExample() {
    // Arrange, Act and Assert
    assertThrows(IllegalArgumentException.class, () -> Uri.create("Original Urlhttps://example.org/example"));
  }

  /**
   * Test {@link Uri#create(String)} with {@code originalUrl}.
   * <ul>
   *   <li>When {@code originalUrlurl:}.</li>
   *   <li>Then throw {@link IllegalArgumentException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link Uri#create(String)}
   */
  @Test
  @DisplayName("Test create(String) with 'originalUrl'; when 'originalUrlurl:'; then throw IllegalArgumentException")
  void testCreateWithOriginalUrl_whenOriginalUrlurl_thenThrowIllegalArgumentException() {
    // Arrange, Act and Assert
    assertThrows(IllegalArgumentException.class, () -> Uri.create("originalUrlurl:"));
  }

  /**
   * Test {@link Uri#create(String)} with {@code originalUrl}.
   * <ul>
   *   <li>When {@code ////}.</li>
   * </ul>
   * <p>
   * Method under test: {@link Uri#create(String)}
   */
  @Test
  @DisplayName("Test create(String) with 'originalUrl'; when '////'")
  void testCreateWithOriginalUrl_whenSlashSlashSlashSlash() {
    // Arrange, Act and Assert
    assertThrows(IllegalArgumentException.class, () -> Uri.create("////"));
  }

  /**
   * Test {@link Uri#create(String)} with {@code originalUrl}.
   * <ul>
   *   <li>When {@code //}.</li>
   *   <li>Then throw {@link IllegalArgumentException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link Uri#create(String)}
   */
  @Test
  @DisplayName("Test create(String) with 'originalUrl'; when '//'; then throw IllegalArgumentException")
  void testCreateWithOriginalUrl_whenSlashSlash_thenThrowIllegalArgumentException() {
    // Arrange, Act and Assert
    assertThrows(IllegalArgumentException.class, () -> Uri.create("//"));
  }

  /**
   * Test {@link Uri#create(String)} with {@code originalUrl}.
   * <ul>
   *   <li>When {@code url:}.</li>
   *   <li>Then throw {@link IllegalArgumentException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link Uri#create(String)}
   */
  @Test
  @DisplayName("Test create(String) with 'originalUrl'; when 'url:'; then throw IllegalArgumentException")
  void testCreateWithOriginalUrl_whenUrl_thenThrowIllegalArgumentException() {
    // Arrange, Act and Assert
    assertThrows(IllegalArgumentException.class, () -> Uri.create("url:"));
  }

  /**
   * Test getters and setters.
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link Uri#toString()}
   *   <li>{@link Uri#getFragment()}
   *   <li>{@link Uri#getHost()}
   *   <li>{@link Uri#getPath()}
   *   <li>{@link Uri#getPort()}
   *   <li>{@link Uri#getQuery()}
   *   <li>{@link Uri#getScheme()}
   *   <li>{@link Uri#getUserInfo()}
   *   <li>{@link Uri#isSecured()}
   *   <li>{@link Uri#isWebSocket()}
   * </ul>
   */
  @Test
  @DisplayName("Test getters and setters")
  void testGettersAndSetters() {
    // Arrange
    Uri uri = new Uri("https://example.org/example", "https://example.org/example", "https://example.org/example", 8080,
        "https://example.org/example", "https://example.org/example", "https://example.org/example");

    // Act
    String actualToStringResult = uri.toString();
    String actualFragment = uri.getFragment();
    String actualHost = uri.getHost();
    String actualPath = uri.getPath();
    int actualPort = uri.getPort();
    String actualQuery = uri.getQuery();
    String actualScheme = uri.getScheme();
    String actualUserInfo = uri.getUserInfo();
    boolean actualIsSecuredResult = uri.isSecured();

    // Assert
    assertEquals("https://example.org/example", actualFragment);
    assertEquals("https://example.org/example", actualHost);
    assertEquals("https://example.org/example", actualPath);
    assertEquals("https://example.org/example", actualQuery);
    assertEquals("https://example.org/example", actualScheme);
    assertEquals("https://example.org/example", actualUserInfo);
    assertEquals("https://example.org/example://https://example.org/example@https://example.org/example:8080https:/"
        + "/example.org/example?https://example.org/example", actualToStringResult);
    assertEquals(8080, actualPort);
    assertFalse(actualIsSecuredResult);
    assertFalse(uri.isWebSocket());
  }

  /**
   * Test {@link Uri#toJavaNetURI()}.
   * <p>
   * Method under test: {@link Uri#toJavaNetURI()}
   */
  @Test
  @DisplayName("Test toJavaNetURI()")
  void testToJavaNetURI() throws URISyntaxException {
    // Arrange, Act and Assert
    assertEquals(
        "https://example.org/example://https://example.org/example@https://example.org/example:8080https:/"
            + "/example.org/example?https://example.org/example",
        (new Uri("https://example.org/example", "https://example.org/example", "https://example.org/example", 8080,
            "https://example.org/example", "https://example.org/example", "https://example.org/example")).toJavaNetURI()
                .toString());
    assertEquals(
        "https://example.org/example://https://example.org/example:8080https://example.org/example?https:/"
            + "/example.org/example",
        (new Uri("https://example.org/example", null, "https://example.org/example", 8080,
            "https://example.org/example", "https://example.org/example", "https://example.org/example")).toJavaNetURI()
                .toString());
    assertEquals(
        "https://example.org/example://https://example.org/example@https://example.org/examplehttps://example"
            + ".org/example?https://example.org/example",
        (new Uri("https://example.org/example", "https://example.org/example", "https://example.org/example", -1,
            "https://example.org/example", "https://example.org/example", "https://example.org/example")).toJavaNetURI()
                .toString());
    assertEquals(
        "https://example.org/example://https://example.org/example@https://example.org/example:8080?https:/"
            + "/example.org/example",
        (new Uri("https://example.org/example", "https://example.org/example", "https://example.org/example", 8080,
            null, "https://example.org/example", "https://example.org/example")).toJavaNetURI().toString());
    assertEquals(
        "https://example.org/example://https://example.org/example@https://example.org/example:8080https:/"
            + "/example.org/example",
        (new Uri("https://example.org/example", "https://example.org/example", "https://example.org/example", 8080,
            "https://example.org/example", null, "https://example.org/example")).toJavaNetURI().toString());
  }

  /**
   * Test {@link Uri#getExplicitPort()}.
   * <ul>
   *   <li>Then return {@code 8080}.</li>
   * </ul>
   * <p>
   * Method under test: {@link Uri#getExplicitPort()}
   */
  @Test
  @DisplayName("Test getExplicitPort(); then return '8080'")
  void testGetExplicitPort_thenReturn8080() {
    // Arrange, Act and Assert
    assertEquals(8080,
        (new Uri("https://example.org/example", "https://example.org/example", "https://example.org/example", 8080,
            "https://example.org/example", "https://example.org/example", "https://example.org/example"))
                .getExplicitPort());
  }

  /**
   * Test {@link Uri#getExplicitPort()}.
   * <ul>
   *   <li>Then return eighty.</li>
   * </ul>
   * <p>
   * Method under test: {@link Uri#getExplicitPort()}
   */
  @Test
  @DisplayName("Test getExplicitPort(); then return eighty")
  void testGetExplicitPort_thenReturnEighty() {
    // Arrange, Act and Assert
    assertEquals(80,
        (new Uri("https://example.org/example", "https://example.org/example", "https://example.org/example", -1,
            "https://example.org/example", "https://example.org/example", "https://example.org/example"))
                .getExplicitPort());
  }

  /**
   * Test {@link Uri#getExplicitPort()}.
   * <ul>
   *   <li>Then return four hundred forty-three.</li>
   * </ul>
   * <p>
   * Method under test: {@link Uri#getExplicitPort()}
   */
  @Test
  @DisplayName("Test getExplicitPort(); then return four hundred forty-three")
  void testGetExplicitPort_thenReturnFourHundredFortyThree() {
    // Arrange, Act and Assert
    assertEquals(
        443, Uri
            .create(
                new Uri("https://example.org/example", "https://example.org/example", "https://example.org/example",
                    8080, "https://example.org/example", "https://example.org/example", "https://example.org/example"),
                "https://example.org/example")
            .getExplicitPort());
  }

  /**
   * Test {@link Uri#getSchemeDefaultPort()}.
   * <ul>
   *   <li>Then return eighty.</li>
   * </ul>
   * <p>
   * Method under test: {@link Uri#getSchemeDefaultPort()}
   */
  @Test
  @DisplayName("Test getSchemeDefaultPort(); then return eighty")
  void testGetSchemeDefaultPort_thenReturnEighty() {
    // Arrange, Act and Assert
    assertEquals(80,
        (new Uri("https://example.org/example", "https://example.org/example", "https://example.org/example", 8080,
            "https://example.org/example", "https://example.org/example", "https://example.org/example"))
                .getSchemeDefaultPort());
  }

  /**
   * Test {@link Uri#getSchemeDefaultPort()}.
   * <ul>
   *   <li>Then return four hundred forty-three.</li>
   * </ul>
   * <p>
   * Method under test: {@link Uri#getSchemeDefaultPort()}
   */
  @Test
  @DisplayName("Test getSchemeDefaultPort(); then return four hundred forty-three")
  void testGetSchemeDefaultPort_thenReturnFourHundredFortyThree() {
    // Arrange, Act and Assert
    assertEquals(443,
        (new Uri(Uri.HTTPS, "https://example.org/example", "https://example.org/example", 8080,
            "https://example.org/example", "https://example.org/example", "https://example.org/example"))
                .getSchemeDefaultPort());
  }

  /**
   * Test {@link Uri#toUrl()}.
   * <p>
   * Method under test: {@link Uri#toUrl()}
   */
  @Test
  @DisplayName("Test toUrl()")
  void testToUrl() {
    // Arrange, Act and Assert
    assertEquals(
        "https://example.org/example://https://example.org/example@https://example.org/example:8080https:/"
            + "/example.org/example?https://example.org/example",
        (new Uri("https://example.org/example", "https://example.org/example", "https://example.org/example", 8080,
            "https://example.org/example", "https://example.org/example", "https://example.org/example")).toUrl());
    assertEquals(
        "https://example.org/example://https://example.org/example:8080https://example.org/example?https:/"
            + "/example.org/example",
        (new Uri("https://example.org/example", null, "https://example.org/example", 8080,
            "https://example.org/example", "https://example.org/example", "https://example.org/example")).toUrl());
    assertEquals(
        "https://example.org/example://https://example.org/example@https://example.org/examplehttps://example"
            + ".org/example?https://example.org/example",
        (new Uri("https://example.org/example", "https://example.org/example", "https://example.org/example", -1,
            "https://example.org/example", "https://example.org/example", "https://example.org/example")).toUrl());
    assertEquals(
        "https://example.org/example://https://example.org/example@https://example.org/example:8080?https:/"
            + "/example.org/example",
        (new Uri("https://example.org/example", "https://example.org/example", "https://example.org/example", 8080,
            null, "https://example.org/example", "https://example.org/example")).toUrl());
    assertEquals(
        "https://example.org/example://https://example.org/example@https://example.org/example:8080https:/"
            + "/example.org/example",
        (new Uri("https://example.org/example", "https://example.org/example", "https://example.org/example", 8080,
            "https://example.org/example", null, "https://example.org/example")).toUrl());
  }

  /**
   * Test {@link Uri#toBaseUrl()}.
   * <p>
   * Method under test: {@link Uri#toBaseUrl()}
   */
  @Test
  @DisplayName("Test toBaseUrl()")
  void testToBaseUrl() {
    // Arrange, Act and Assert
    assertEquals("https://example.org/example://https://example.org/example:8080https://example.org/example",
        (new Uri("https://example.org/example", "https://example.org/example", "https://example.org/example", 8080,
            "https://example.org/example", "https://example.org/example", "https://example.org/example")).toBaseUrl());
    assertEquals("https://example.org/example://https://example.org/examplehttps://example.org/example",
        (new Uri("https://example.org/example", "https://example.org/example", "https://example.org/example", -1,
            "https://example.org/example", "https://example.org/example", "https://example.org/example")).toBaseUrl());
    assertEquals("https://example.org/example://https://example.org/examplehttps://example.org/example",
        (new Uri("https://example.org/example", "https://example.org/example", "https://example.org/example", 80,
            "https://example.org/example", "https://example.org/example", "https://example.org/example")).toBaseUrl());
  }

  /**
   * Test {@link Uri#toBaseUrl()}.
   * <ul>
   *   <li>Then return
   * {@code https://example.org/example://https://example.org/example:8080}.</li>
   * </ul>
   * <p>
   * Method under test: {@link Uri#toBaseUrl()}
   */
  @Test
  @DisplayName("Test toBaseUrl(); then return 'https://example.org/example://https://example.org/example:8080'")
  void testToBaseUrl_thenReturnHttpsExampleOrgExampleHttpsExampleOrgExample8080() {
    // Arrange, Act and Assert
    assertEquals("https://example.org/example://https://example.org/example:8080",
        (new Uri("https://example.org/example", "https://example.org/example", "https://example.org/example", 8080, "",
            "https://example.org/example", "https://example.org/example")).toBaseUrl());
  }

  /**
   * Test {@link Uri#toBaseUrl()}.
   * <ul>
   *   <li>Then return
   * {@code https://https://example.org/example:8080https://example.org/example}.</li>
   * </ul>
   * <p>
   * Method under test: {@link Uri#toBaseUrl()}
   */
  @Test
  @DisplayName("Test toBaseUrl(); then return 'https://https://example.org/example:8080https://example.org/example'")
  void testToBaseUrl_thenReturnHttpsHttpsExampleOrgExample8080httpsExampleOrgExample() {
    // Arrange, Act and Assert
    assertEquals("https://https://example.org/example:8080https://example.org/example",
        (new Uri(Uri.HTTPS, "https://example.org/example", "https://example.org/example", 8080,
            "https://example.org/example", "https://example.org/example", "https://example.org/example")).toBaseUrl());
  }

  /**
   * Test {@link Uri#toRelativeUrl()}.
   * <p>
   * Method under test: {@link Uri#toRelativeUrl()}
   */
  @Test
  @DisplayName("Test toRelativeUrl()")
  void testToRelativeUrl() {
    // Arrange, Act and Assert
    assertEquals("/?https://example.org/example",
        (new Uri("https://example.org/example", "https://example.org/example", "https://example.org/example", 8080,
            null, "https://example.org/example", "https://example.org/example")).toRelativeUrl());
    assertEquals("/?https://example.org/example",
        (new Uri("https://example.org/example", "https://example.org/example", "https://example.org/example", 8080, "",
            "https://example.org/example", "https://example.org/example")).toRelativeUrl());
  }

  /**
   * Test {@link Uri#toRelativeUrl()}.
   * <ul>
   *   <li>Then return {@code https://example.org/example}.</li>
   * </ul>
   * <p>
   * Method under test: {@link Uri#toRelativeUrl()}
   */
  @Test
  @DisplayName("Test toRelativeUrl(); then return 'https://example.org/example'")
  void testToRelativeUrl_thenReturnHttpsExampleOrgExample() {
    // Arrange, Act and Assert
    assertEquals("https://example.org/example",
        (new Uri("https://example.org/example", "https://example.org/example", "https://example.org/example", 8080,
            "https://example.org/example", null, "https://example.org/example")).toRelativeUrl());
  }

  /**
   * Test {@link Uri#toRelativeUrl()}.
   * <ul>
   *   <li>Then return
   * {@code https://example.org/example?https://example.org/example}.</li>
   * </ul>
   * <p>
   * Method under test: {@link Uri#toRelativeUrl()}
   */
  @Test
  @DisplayName("Test toRelativeUrl(); then return 'https://example.org/example?https://example.org/example'")
  void testToRelativeUrl_thenReturnHttpsExampleOrgExampleHttpsExampleOrgExample() {
    // Arrange, Act and Assert
    assertEquals("https://example.org/example?https://example.org/example",
        (new Uri("https://example.org/example", "https://example.org/example", "https://example.org/example", 8080,
            "https://example.org/example", "https://example.org/example", "https://example.org/example"))
                .toRelativeUrl());
  }

  /**
   * Test {@link Uri#toFullUrl()}.
   * <p>
   * Method under test: {@link Uri#toFullUrl()}
   */
  @Test
  @DisplayName("Test toFullUrl()")
  void testToFullUrl() {
    // Arrange, Act and Assert
    assertEquals(
        "https://example.org/example://https://example.org/example@https://example.org/example:8080https:/"
            + "/example.org/example?https://example.org/example#https://example.org/example",
        (new Uri("https://example.org/example", "https://example.org/example", "https://example.org/example", 8080,
            "https://example.org/example", "https://example.org/example", "https://example.org/example")).toFullUrl());
    assertEquals(
        "https://example.org/example://https://example.org/example:8080https://example.org/example?https:/"
            + "/example.org/example#https://example.org/example",
        (new Uri("https://example.org/example", null, "https://example.org/example", 8080,
            "https://example.org/example", "https://example.org/example", "https://example.org/example")).toFullUrl());
    assertEquals(
        "https://example.org/example://https://example.org/example@https://example.org/examplehttps://example"
            + ".org/example?https://example.org/example#https://example.org/example",
        (new Uri("https://example.org/example", "https://example.org/example", "https://example.org/example", -1,
            "https://example.org/example", "https://example.org/example", "https://example.org/example")).toFullUrl());
    assertEquals(
        "https://example.org/example://https://example.org/example@https://example.org/example:8080?https:/"
            + "/example.org/example#https://example.org/example",
        (new Uri("https://example.org/example", "https://example.org/example", "https://example.org/example", 8080,
            null, "https://example.org/example", "https://example.org/example")).toFullUrl());
    assertEquals(
        "https://example.org/example://https://example.org/example@https://example.org/example:8080https:/"
            + "/example.org/example#https://example.org/example",
        (new Uri("https://example.org/example", "https://example.org/example", "https://example.org/example", 8080,
            "https://example.org/example", null, "https://example.org/example")).toFullUrl());
    assertEquals(
        "https://example.org/example://https://example.org/example@https://example.org/example:8080https:/"
            + "/example.org/example?https://example.org/example",
        (new Uri("https://example.org/example", "https://example.org/example", "https://example.org/example", 8080,
            "https://example.org/example", "https://example.org/example", null)).toFullUrl());
  }

  /**
   * Test {@link Uri#getBaseUrl()}.
   * <ul>
   *   <li>Then return {@code https://example.org:443}.</li>
   * </ul>
   * <p>
   * Method under test: {@link Uri#getBaseUrl()}
   */
  @Test
  @DisplayName("Test getBaseUrl(); then return 'https://example.org:443'")
  void testGetBaseUrl_thenReturnHttpsExampleOrg443() {
    // Arrange, Act and Assert
    assertEquals(
        "https://example.org:443", Uri
            .create(
                new Uri("https://example.org/example", "https://example.org/example", "https://example.org/example",
                    8080, "https://example.org/example", "https://example.org/example", "https://example.org/example"),
                "https://example.org/example")
            .getBaseUrl());
  }

  /**
   * Test {@link Uri#getBaseUrl()}.
   * <ul>
   *   <li>Then return
   * {@code https://example.org/example://https://example.org/example:80}.</li>
   * </ul>
   * <p>
   * Method under test: {@link Uri#getBaseUrl()}
   */
  @Test
  @DisplayName("Test getBaseUrl(); then return 'https://example.org/example://https://example.org/example:80'")
  void testGetBaseUrl_thenReturnHttpsExampleOrgExampleHttpsExampleOrgExample80() {
    // Arrange, Act and Assert
    assertEquals("https://example.org/example://https://example.org/example:80",
        (new Uri("https://example.org/example", "https://example.org/example", "https://example.org/example", -1,
            "https://example.org/example", "https://example.org/example", "https://example.org/example")).getBaseUrl());
  }

  /**
   * Test {@link Uri#getBaseUrl()}.
   * <ul>
   *   <li>Then return
   * {@code https://example.org/example://https://example.org/example:8080}.</li>
   * </ul>
   * <p>
   * Method under test: {@link Uri#getBaseUrl()}
   */
  @Test
  @DisplayName("Test getBaseUrl(); then return 'https://example.org/example://https://example.org/example:8080'")
  void testGetBaseUrl_thenReturnHttpsExampleOrgExampleHttpsExampleOrgExample8080() {
    // Arrange, Act and Assert
    assertEquals("https://example.org/example://https://example.org/example:8080",
        (new Uri("https://example.org/example", "https://example.org/example", "https://example.org/example", 8080,
            "https://example.org/example", "https://example.org/example", "https://example.org/example")).getBaseUrl());
  }

  /**
   * Test {@link Uri#getAuthority()}.
   * <ul>
   *   <li>Then return {@code example.org:443}.</li>
   * </ul>
   * <p>
   * Method under test: {@link Uri#getAuthority()}
   */
  @Test
  @DisplayName("Test getAuthority(); then return 'example.org:443'")
  void testGetAuthority_thenReturnExampleOrg443() {
    // Arrange, Act and Assert
    assertEquals(
        "example.org:443", Uri
            .create(
                new Uri("https://example.org/example", "https://example.org/example", "https://example.org/example",
                    8080, "https://example.org/example", "https://example.org/example", "https://example.org/example"),
                "https://example.org/example")
            .getAuthority());
  }

  /**
   * Test {@link Uri#getAuthority()}.
   * <ul>
   *   <li>Then return {@code https://example.org/example:80}.</li>
   * </ul>
   * <p>
   * Method under test: {@link Uri#getAuthority()}
   */
  @Test
  @DisplayName("Test getAuthority(); then return 'https://example.org/example:80'")
  void testGetAuthority_thenReturnHttpsExampleOrgExample80() {
    // Arrange, Act and Assert
    assertEquals("https://example.org/example:80",
        (new Uri("https://example.org/example", "https://example.org/example", "https://example.org/example", -1,
            "https://example.org/example", "https://example.org/example", "https://example.org/example"))
                .getAuthority());
  }

  /**
   * Test {@link Uri#getAuthority()}.
   * <ul>
   *   <li>Then return {@code https://example.org/example:8080}.</li>
   * </ul>
   * <p>
   * Method under test: {@link Uri#getAuthority()}
   */
  @Test
  @DisplayName("Test getAuthority(); then return 'https://example.org/example:8080'")
  void testGetAuthority_thenReturnHttpsExampleOrgExample8080() {
    // Arrange, Act and Assert
    assertEquals("https://example.org/example:8080",
        (new Uri("https://example.org/example", "https://example.org/example", "https://example.org/example", 8080,
            "https://example.org/example", "https://example.org/example", "https://example.org/example"))
                .getAuthority());
  }

  /**
   * Test {@link Uri#isSameBase(Uri)}.
   * <p>
   * Method under test: {@link Uri#isSameBase(Uri)}
   */
  @Test
  @DisplayName("Test isSameBase(Uri)")
  void testIsSameBase() {
    // Arrange
    Uri uri = new Uri("Scheme", "https://example.org/example", "https://example.org/example", 8080,
        "https://example.org/example", "https://example.org/example", "https://example.org/example");

    // Act and Assert
    assertFalse(uri
        .isSameBase(new Uri("https://example.org/example", "https://example.org/example", "https://example.org/example",
            8080, "https://example.org/example", "https://example.org/example", "https://example.org/example")));
  }

  /**
   * Test {@link Uri#isSameBase(Uri)}.
   * <p>
   * Method under test: {@link Uri#isSameBase(Uri)}
   */
  @Test
  @DisplayName("Test isSameBase(Uri)")
  void testIsSameBase2() {
    // Arrange
    Uri uri = new Uri("https://example.org/example", "https://example.org/example", "localhost", 8080,
        "https://example.org/example", "https://example.org/example", "https://example.org/example");

    // Act and Assert
    assertFalse(uri
        .isSameBase(new Uri("https://example.org/example", "https://example.org/example", "https://example.org/example",
            8080, "https://example.org/example", "https://example.org/example", "https://example.org/example")));
  }

  /**
   * Test {@link Uri#isSameBase(Uri)}.
   * <p>
   * Method under test: {@link Uri#isSameBase(Uri)}
   */
  @Test
  @DisplayName("Test isSameBase(Uri)")
  void testIsSameBase3() {
    // Arrange
    Uri uri = new Uri("https://example.org/example", "https://example.org/example", "https://example.org/example", 1,
        "https://example.org/example", "https://example.org/example", "https://example.org/example");

    // Act and Assert
    assertFalse(uri
        .isSameBase(new Uri("https://example.org/example", "https://example.org/example", "https://example.org/example",
            8080, "https://example.org/example", "https://example.org/example", "https://example.org/example")));
  }

  /**
   * Test {@link Uri#isSameBase(Uri)}.
   * <p>
   * Method under test: {@link Uri#isSameBase(Uri)}
   */
  @Test
  @DisplayName("Test isSameBase(Uri)")
  void testIsSameBase4() {
    // Arrange
    Uri uri = new Uri("https://example.org/example", "https://example.org/example", "https://example.org/example", -1,
        "https://example.org/example", "https://example.org/example", "https://example.org/example");

    // Act and Assert
    assertFalse(uri
        .isSameBase(new Uri("https://example.org/example", "https://example.org/example", "https://example.org/example",
            8080, "https://example.org/example", "https://example.org/example", "https://example.org/example")));
  }

  /**
   * Test {@link Uri#isSameBase(Uri)}.
   * <ul>
   *   <li>Then return {@code true}.</li>
   * </ul>
   * <p>
   * Method under test: {@link Uri#isSameBase(Uri)}
   */
  @Test
  @DisplayName("Test isSameBase(Uri); then return 'true'")
  void testIsSameBase_thenReturnTrue() {
    // Arrange
    Uri uri = new Uri("https://example.org/example", "https://example.org/example", "https://example.org/example", 8080,
        "https://example.org/example", "https://example.org/example", "https://example.org/example");

    // Act and Assert
    assertTrue(uri
        .isSameBase(new Uri("https://example.org/example", "https://example.org/example", "https://example.org/example",
            8080, "https://example.org/example", "https://example.org/example", "https://example.org/example")));
  }

  /**
   * Test {@link Uri#getNonEmptyPath()}.
   * <ul>
   *   <li>Then return {@code https://example.org/example}.</li>
   * </ul>
   * <p>
   * Method under test: {@link Uri#getNonEmptyPath()}
   */
  @Test
  @DisplayName("Test getNonEmptyPath(); then return 'https://example.org/example'")
  void testGetNonEmptyPath_thenReturnHttpsExampleOrgExample() {
    // Arrange, Act and Assert
    assertEquals("https://example.org/example",
        (new Uri("https://example.org/example", "https://example.org/example", "https://example.org/example", 8080,
            "https://example.org/example", "https://example.org/example", "https://example.org/example"))
                .getNonEmptyPath());
  }

  /**
   * Test {@link Uri#getNonEmptyPath()}.
   * <ul>
   *   <li>Then return {@code /}.</li>
   * </ul>
   * <p>
   * Method under test: {@link Uri#getNonEmptyPath()}
   */
  @Test
  @DisplayName("Test getNonEmptyPath(); then return '/'")
  void testGetNonEmptyPath_thenReturnSlash() {
    // Arrange, Act and Assert
    assertEquals("/",
        (new Uri("https://example.org/example", "https://example.org/example", "https://example.org/example", 8080, "",
            "https://example.org/example", "https://example.org/example")).getNonEmptyPath());
  }

  /**
   * Test {@link Uri#withNewScheme(String)}.
   * <p>
   * Method under test: {@link Uri#withNewScheme(String)}
   */
  @Test
  @DisplayName("Test withNewScheme(String)")
  void testWithNewScheme() {
    // Arrange
    Uri uri = new Uri("https://example.org/example", "https://example.org/example", "https://example.org/example", 8080,
        "https://example.org/example", "https://example.org/example", "https://example.org/example");

    // Act and Assert
    assertEquals(uri, uri.withNewScheme("https://example.org/example"));
  }

  /**
   * Test {@link Uri#withNewScheme(String)}.
   * <ul>
   *   <li>When {@link Uri#HTTPS}.</li>
   *   <li>Then return BaseUrl is
   * {@code https://https://example.org/example:8080}.</li>
   * </ul>
   * <p>
   * Method under test: {@link Uri#withNewScheme(String)}
   */
  @Test
  @DisplayName("Test withNewScheme(String); when HTTPS; then return BaseUrl is 'https://https://example.org/example:8080'")
  void testWithNewScheme_whenHttps_thenReturnBaseUrlIsHttpsHttpsExampleOrgExample8080() throws URISyntaxException {
    // Arrange and Act
    Uri actualWithNewSchemeResult = (new Uri("https://example.org/example", "https://example.org/example",
        "https://example.org/example", 8080, "https://example.org/example", "https://example.org/example",
        "https://example.org/example")).withNewScheme(Uri.HTTPS);

    // Assert
    assertEquals("https://https://example.org/example:8080", actualWithNewSchemeResult.getBaseUrl());
    assertEquals("https://https://example.org/example@https://example.org/example:8080https://example.org/example?https"
        + "://example.org/example", actualWithNewSchemeResult.toJavaNetURI().toString());
    assertFalse(actualWithNewSchemeResult.isWebSocket());
    assertEquals(Uri.HTTPS, actualWithNewSchemeResult.getScheme());
  }

  /**
   * Test {@link Uri#withNewScheme(String)}.
   * <ul>
   *   <li>When {@link Uri#WS}.</li>
   *   <li>Then return BaseUrl is
   * {@code ws://https://example.org/example:8080}.</li>
   * </ul>
   * <p>
   * Method under test: {@link Uri#withNewScheme(String)}
   */
  @Test
  @DisplayName("Test withNewScheme(String); when WS; then return BaseUrl is 'ws://https://example.org/example:8080'")
  void testWithNewScheme_whenWs_thenReturnBaseUrlIsWsHttpsExampleOrgExample8080() throws URISyntaxException {
    // Arrange and Act
    Uri actualWithNewSchemeResult = (new Uri("https://example.org/example", "https://example.org/example",
        "https://example.org/example", 8080, "https://example.org/example", "https://example.org/example",
        "https://example.org/example")).withNewScheme(Uri.WS);

    // Assert
    assertEquals("ws://https://example.org/example:8080", actualWithNewSchemeResult.getBaseUrl());
    assertEquals("ws://https://example.org/example@https://example.org/example:8080https://example.org/example?https:/"
        + "/example.org/example", actualWithNewSchemeResult.toJavaNetURI().toString());
    assertEquals(80, actualWithNewSchemeResult.getSchemeDefaultPort());
    assertFalse(actualWithNewSchemeResult.isSecured());
    assertEquals(Uri.WS, actualWithNewSchemeResult.getScheme());
  }

  /**
   * Test {@link Uri#withNewScheme(String)}.
   * <ul>
   *   <li>When {@link Uri#WSS}.</li>
   *   <li>Then return BaseUrl is
   * {@code wss://https://example.org/example:8080}.</li>
   * </ul>
   * <p>
   * Method under test: {@link Uri#withNewScheme(String)}
   */
  @Test
  @DisplayName("Test withNewScheme(String); when WSS; then return BaseUrl is 'wss://https://example.org/example:8080'")
  void testWithNewScheme_whenWss_thenReturnBaseUrlIsWssHttpsExampleOrgExample8080() throws URISyntaxException {
    // Arrange and Act
    Uri actualWithNewSchemeResult = (new Uri("https://example.org/example", "https://example.org/example",
        "https://example.org/example", 8080, "https://example.org/example", "https://example.org/example",
        "https://example.org/example")).withNewScheme(Uri.WSS);

    // Assert
    assertEquals("wss://https://example.org/example:8080", actualWithNewSchemeResult.getBaseUrl());
    assertEquals("wss://https://example.org/example@https://example.org/example:8080https://example.org/example?https:"
        + "//example.org/example", actualWithNewSchemeResult.toJavaNetURI().toString());
    assertEquals(443, actualWithNewSchemeResult.getSchemeDefaultPort());
    assertTrue(actualWithNewSchemeResult.isSecured());
    assertTrue(actualWithNewSchemeResult.isWebSocket());
    assertEquals(Uri.WSS, actualWithNewSchemeResult.getScheme());
  }

  /**
   * Test {@link Uri#withNewQuery(String)}.
   * <p>
   * Method under test: {@link Uri#withNewQuery(String)}
   */
  @Test
  @DisplayName("Test withNewQuery(String)")
  void testWithNewQuery() {
    // Arrange
    Uri uri = new Uri("https://example.org/example", "https://example.org/example", "https://example.org/example", 8080,
        "https://example.org/example", "https://example.org/example", "https://example.org/example");

    // Act and Assert
    assertEquals(uri, uri.withNewQuery("https://example.org/example"));
  }

  /**
   * Test {@link Uri#withNewQuery(String)}.
   * <p>
   * Method under test: {@link Uri#withNewQuery(String)}
   */
  @Test
  @DisplayName("Test withNewQuery(String)")
  void testWithNewQuery2() {
    // Arrange
    Uri uri = new Uri(Uri.HTTPS, "https://example.org/example", "https://example.org/example", 8080,
        "https://example.org/example", "https://example.org/example", "https://example.org/example");

    // Act and Assert
    assertEquals(uri, uri.withNewQuery("https://example.org/example"));
  }

  /**
   * Test {@link Uri#withNewQuery(String)}.
   * <p>
   * Method under test: {@link Uri#withNewQuery(String)}
   */
  @Test
  @DisplayName("Test withNewQuery(String)")
  void testWithNewQuery3() {
    // Arrange
    Uri uri = new Uri(Uri.WSS, "https://example.org/example", "https://example.org/example", 8080,
        "https://example.org/example", "https://example.org/example", "https://example.org/example");

    // Act and Assert
    assertEquals(uri, uri.withNewQuery("https://example.org/example"));
  }

  /**
   * Test {@link Uri#withNewQuery(String)}.
   * <p>
   * Method under test: {@link Uri#withNewQuery(String)}
   */
  @Test
  @DisplayName("Test withNewQuery(String)")
  void testWithNewQuery4() {
    // Arrange
    Uri uri = new Uri(Uri.WS, "https://example.org/example", "https://example.org/example", 8080,
        "https://example.org/example", "https://example.org/example", "https://example.org/example");

    // Act and Assert
    assertEquals(uri, uri.withNewQuery("https://example.org/example"));
  }

  /**
   * Test {@link Uri#equals(Object)}, and {@link Uri#hashCode()}.
   * <ul>
   *   <li>When other is equal.</li>
   *   <li>Then return equal.</li>
   * </ul>
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link Uri#equals(Object)}
   *   <li>{@link Uri#hashCode()}
   * </ul>
   */
  @Test
  @DisplayName("Test equals(Object), and hashCode(); when other is equal; then return equal")
  void testEqualsAndHashCode_whenOtherIsEqual_thenReturnEqual() {
    // Arrange
    Uri uri = new Uri("https://example.org/example", "https://example.org/example", "https://example.org/example", 8080,
        "https://example.org/example", "https://example.org/example", "https://example.org/example");
    Uri uri2 = new Uri("https://example.org/example", "https://example.org/example", "https://example.org/example",
        8080, "https://example.org/example", "https://example.org/example", "https://example.org/example");

    // Act and Assert
    assertEquals(uri, uri2);
    int expectedHashCodeResult = uri.hashCode();
    assertEquals(expectedHashCodeResult, uri2.hashCode());
  }

  /**
   * Test {@link Uri#equals(Object)}, and {@link Uri#hashCode()}.
   * <ul>
   *   <li>When other is same.</li>
   *   <li>Then return equal.</li>
   * </ul>
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link Uri#equals(Object)}
   *   <li>{@link Uri#hashCode()}
   * </ul>
   */
  @Test
  @DisplayName("Test equals(Object), and hashCode(); when other is same; then return equal")
  void testEqualsAndHashCode_whenOtherIsSame_thenReturnEqual() {
    // Arrange
    Uri uri = new Uri("https://example.org/example", "https://example.org/example", "https://example.org/example", 8080,
        "https://example.org/example", "https://example.org/example", "https://example.org/example");

    // Act and Assert
    assertEquals(uri, uri);
    int expectedHashCodeResult = uri.hashCode();
    assertEquals(expectedHashCodeResult, uri.hashCode());
  }

  /**
   * Test {@link Uri#equals(Object)}.
   * <ul>
   *   <li>When other is different.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link Uri#equals(Object)}
   */
  @Test
  @DisplayName("Test equals(Object); when other is different; then return not equal")
  void testEquals_whenOtherIsDifferent_thenReturnNotEqual() {
    // Arrange
    Uri uri = new Uri("Scheme", "https://example.org/example", "https://example.org/example", 8080,
        "https://example.org/example", "https://example.org/example", "https://example.org/example");

    // Act and Assert
    assertNotEquals(uri,
        new Uri("https://example.org/example", "https://example.org/example", "https://example.org/example", 8080,
            "https://example.org/example", "https://example.org/example", "https://example.org/example"));
  }

  /**
   * Test {@link Uri#equals(Object)}.
   * <ul>
   *   <li>When other is different.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link Uri#equals(Object)}
   */
  @Test
  @DisplayName("Test equals(Object); when other is different; then return not equal")
  void testEquals_whenOtherIsDifferent_thenReturnNotEqual2() {
    // Arrange
    Uri uri = new Uri("https://example.org/example", "User Info", "https://example.org/example", 8080,
        "https://example.org/example", "https://example.org/example", "https://example.org/example");

    // Act and Assert
    assertNotEquals(uri,
        new Uri("https://example.org/example", "https://example.org/example", "https://example.org/example", 8080,
            "https://example.org/example", "https://example.org/example", "https://example.org/example"));
  }

  /**
   * Test {@link Uri#equals(Object)}.
   * <ul>
   *   <li>When other is different.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link Uri#equals(Object)}
   */
  @Test
  @DisplayName("Test equals(Object); when other is different; then return not equal")
  void testEquals_whenOtherIsDifferent_thenReturnNotEqual3() {
    // Arrange
    Uri uri = new Uri("https://example.org/example", null, "https://example.org/example", 8080,
        "https://example.org/example", "https://example.org/example", "https://example.org/example");

    // Act and Assert
    assertNotEquals(uri,
        new Uri("https://example.org/example", "https://example.org/example", "https://example.org/example", 8080,
            "https://example.org/example", "https://example.org/example", "https://example.org/example"));
  }

  /**
   * Test {@link Uri#equals(Object)}.
   * <ul>
   *   <li>When other is different.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link Uri#equals(Object)}
   */
  @Test
  @DisplayName("Test equals(Object); when other is different; then return not equal")
  void testEquals_whenOtherIsDifferent_thenReturnNotEqual4() {
    // Arrange
    Uri uri = new Uri("https://example.org/example", "https://example.org/example", "localhost", 8080,
        "https://example.org/example", "https://example.org/example", "https://example.org/example");

    // Act and Assert
    assertNotEquals(uri,
        new Uri("https://example.org/example", "https://example.org/example", "https://example.org/example", 8080,
            "https://example.org/example", "https://example.org/example", "https://example.org/example"));
  }

  /**
   * Test {@link Uri#equals(Object)}.
   * <ul>
   *   <li>When other is different.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link Uri#equals(Object)}
   */
  @Test
  @DisplayName("Test equals(Object); when other is different; then return not equal")
  void testEquals_whenOtherIsDifferent_thenReturnNotEqual5() {
    // Arrange
    Uri uri = new Uri("https://example.org/example", "https://example.org/example", "https://example.org/example", 1,
        "https://example.org/example", "https://example.org/example", "https://example.org/example");

    // Act and Assert
    assertNotEquals(uri,
        new Uri("https://example.org/example", "https://example.org/example", "https://example.org/example", 8080,
            "https://example.org/example", "https://example.org/example", "https://example.org/example"));
  }

  /**
   * Test {@link Uri#equals(Object)}.
   * <ul>
   *   <li>When other is different.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link Uri#equals(Object)}
   */
  @Test
  @DisplayName("Test equals(Object); when other is different; then return not equal")
  void testEquals_whenOtherIsDifferent_thenReturnNotEqual6() {
    // Arrange
    Uri uri = new Uri("https://example.org/example", "https://example.org/example", "https://example.org/example", 8080,
        "Path", "https://example.org/example", "https://example.org/example");

    // Act and Assert
    assertNotEquals(uri,
        new Uri("https://example.org/example", "https://example.org/example", "https://example.org/example", 8080,
            "https://example.org/example", "https://example.org/example", "https://example.org/example"));
  }

  /**
   * Test {@link Uri#equals(Object)}.
   * <ul>
   *   <li>When other is different.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link Uri#equals(Object)}
   */
  @Test
  @DisplayName("Test equals(Object); when other is different; then return not equal")
  void testEquals_whenOtherIsDifferent_thenReturnNotEqual7() {
    // Arrange
    Uri uri = new Uri("https://example.org/example", "https://example.org/example", "https://example.org/example", 8080,
        null, "https://example.org/example", "https://example.org/example");

    // Act and Assert
    assertNotEquals(uri,
        new Uri("https://example.org/example", "https://example.org/example", "https://example.org/example", 8080,
            "https://example.org/example", "https://example.org/example", "https://example.org/example"));
  }

  /**
   * Test {@link Uri#equals(Object)}.
   * <ul>
   *   <li>When other is different.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link Uri#equals(Object)}
   */
  @Test
  @DisplayName("Test equals(Object); when other is different; then return not equal")
  void testEquals_whenOtherIsDifferent_thenReturnNotEqual8() {
    // Arrange
    Uri uri = new Uri("https://example.org/example", "https://example.org/example", "https://example.org/example", 8080,
        "https://example.org/example", "Query", "https://example.org/example");

    // Act and Assert
    assertNotEquals(uri,
        new Uri("https://example.org/example", "https://example.org/example", "https://example.org/example", 8080,
            "https://example.org/example", "https://example.org/example", "https://example.org/example"));
  }

  /**
   * Test {@link Uri#equals(Object)}.
   * <ul>
   *   <li>When other is different.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link Uri#equals(Object)}
   */
  @Test
  @DisplayName("Test equals(Object); when other is different; then return not equal")
  void testEquals_whenOtherIsDifferent_thenReturnNotEqual9() {
    // Arrange
    Uri uri = new Uri("https://example.org/example", "https://example.org/example", "https://example.org/example", 8080,
        "https://example.org/example", null, "https://example.org/example");

    // Act and Assert
    assertNotEquals(uri,
        new Uri("https://example.org/example", "https://example.org/example", "https://example.org/example", 8080,
            "https://example.org/example", "https://example.org/example", "https://example.org/example"));
  }

  /**
   * Test {@link Uri#equals(Object)}.
   * <ul>
   *   <li>When other is different.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link Uri#equals(Object)}
   */
  @Test
  @DisplayName("Test equals(Object); when other is different; then return not equal")
  void testEquals_whenOtherIsDifferent_thenReturnNotEqual10() {
    // Arrange
    Uri uri = new Uri("https://example.org/example", "https://example.org/example", "https://example.org/example", 8080,
        "https://example.org/example", "https://example.org/example", null);

    // Act and Assert
    assertNotEquals(uri,
        new Uri("https://example.org/example", "https://example.org/example", "https://example.org/example", 8080,
            "https://example.org/example", "https://example.org/example", "https://example.org/example"));
  }

  /**
   * Test {@link Uri#equals(Object)}.
   * <ul>
   *   <li>When other is {@code null}.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link Uri#equals(Object)}
   */
  @Test
  @DisplayName("Test equals(Object); when other is 'null'; then return not equal")
  void testEquals_whenOtherIsNull_thenReturnNotEqual() {
    // Arrange, Act and Assert
    assertNotEquals(new Uri("https://example.org/example", "https://example.org/example", "https://example.org/example",
        8080, "https://example.org/example", "https://example.org/example", "https://example.org/example"), null);
  }

  /**
   * Test {@link Uri#equals(Object)}.
   * <ul>
   *   <li>When other is wrong type.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link Uri#equals(Object)}
   */
  @Test
  @DisplayName("Test equals(Object); when other is wrong type; then return not equal")
  void testEquals_whenOtherIsWrongType_thenReturnNotEqual() {
    // Arrange, Act and Assert
    assertNotEquals(
        new Uri("https://example.org/example", "https://example.org/example", "https://example.org/example", 8080,
            "https://example.org/example", "https://example.org/example", "https://example.org/example"),
        "Different type to Uri");
  }

  /**
   * Test {@link Uri#validateSupportedScheme(Uri)}.
   * <p>
   * Method under test: {@link Uri#validateSupportedScheme(Uri)}
   */
  @Test
  @DisplayName("Test validateSupportedScheme(Uri)")
  void testValidateSupportedScheme() {
    // Arrange, Act and Assert
    assertThrows(IllegalArgumentException.class,
        () -> Uri.validateSupportedScheme(
            new Uri("https://example.org/example", "https://example.org/example", "https://example.org/example", 8080,
                "https://example.org/example", "https://example.org/example", "https://example.org/example")));
    assertThrows(IllegalArgumentException.class,
        () -> Uri.validateSupportedScheme(new Uri("https://example.org/example", null, "https://example.org/example",
            8080, "https://example.org/example", "https://example.org/example", "https://example.org/example")));
    assertThrows(IllegalArgumentException.class,
        () -> Uri.validateSupportedScheme(
            new Uri("https://example.org/example", "https://example.org/example", "https://example.org/example", -1,
                "https://example.org/example", "https://example.org/example", "https://example.org/example")));
    assertThrows(IllegalArgumentException.class,
        () -> Uri.validateSupportedScheme(new Uri("https://example.org/example", "https://example.org/example",
            "https://example.org/example", 8080, null, "https://example.org/example", "https://example.org/example")));
    assertThrows(IllegalArgumentException.class,
        () -> Uri.validateSupportedScheme(new Uri("https://example.org/example", "https://example.org/example",
            "https://example.org/example", 8080, "https://example.org/example", null, "https://example.org/example")));
  }
}
