package org.asynchttpclient.uri;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class UriParserDiffblueTest {
  /**
   * Test {@link UriParser#parse(Uri, String)} with {@code context},
   * {@code originalUrl}.
   * <p>
   * Method under test: {@link UriParser#parse(Uri, String)}
   */
  @Test
  @DisplayName("Test parse(Uri, String) with 'context', 'originalUrl'")
  void testParseWithContextOriginalUrl() {
    // Arrange and Act
    UriParser actualParseResult = UriParser.parse(
        new Uri(Uri.HTTPS, "https://example.org/example", "https://example.org/example", 8080,
            "https://example.org/example", "https://example.org/example", "https://example.org/example"),
        "https://example.org/example");

    // Assert
    assertEquals("/example", actualParseResult.path);
    assertEquals("example.org", actualParseResult.host);
    assertNull(actualParseResult.userInfo);
    assertEquals(-1, actualParseResult.port);
    assertEquals(Uri.HTTPS, actualParseResult.scheme);
  }

  /**
   * Test {@link UriParser#parse(Uri, String)} with {@code context},
   * {@code originalUrl}.
   * <p>
   * Method under test: {@link UriParser#parse(Uri, String)}
   */
  @Test
  @DisplayName("Test parse(Uri, String) with 'context', 'originalUrl'")
  void testParseWithContextOriginalUrl2() {
    // Arrange and Act
    UriParser actualParseResult = UriParser.parse(Uri.create(
        new Uri("https://example.org/example", "https://example.org/example", "https://example.org/example", 8080,
            "https://example.org/example", "https://example.org/example", "https://example.org/example"),
        "https://example.org/example"), "https://example.org/example");

    // Assert
    assertEquals("/example", actualParseResult.path);
    assertEquals("example.org", actualParseResult.host);
    assertNull(actualParseResult.userInfo);
    assertEquals(-1, actualParseResult.port);
    assertEquals(Uri.HTTPS, actualParseResult.scheme);
  }

  /**
   * Test {@link UriParser#parse(Uri, String)} with {@code context},
   * {@code originalUrl}.
   * <p>
   * Method under test: {@link UriParser#parse(Uri, String)}
   */
  @Test
  @DisplayName("Test parse(Uri, String) with 'context', 'originalUrl'")
  void testParseWithContextOriginalUrl3() {
    // Arrange and Act
    UriParser actualParseResult = UriParser.parse(new Uri(Uri.HTTPS, "https://example.org/example",
        "https://example.org/example", 8080, "", "https://example.org/example", "https://example.org/example"),
        "https://example.org/example");

    // Assert
    assertEquals("/example", actualParseResult.path);
    assertEquals("example.org", actualParseResult.host);
    assertNull(actualParseResult.userInfo);
    assertEquals(-1, actualParseResult.port);
    assertEquals(Uri.HTTPS, actualParseResult.scheme);
  }

  /**
   * Test {@link UriParser#parse(Uri, String)} with {@code context},
   * {@code originalUrl}.
   * <p>
   * Method under test: {@link UriParser#parse(Uri, String)}
   */
  @Test
  @DisplayName("Test parse(Uri, String) with 'context', 'originalUrl'")
  void testParseWithContextOriginalUrl4() {
    // Arrange and Act
    UriParser actualParseResult = UriParser.parse(new Uri(Uri.HTTPS, "https://example.org/example",
        "https://example.org/example", 8080, null, "https://example.org/example", "https://example.org/example"),
        "https://example.org/example");

    // Assert
    assertEquals("/example", actualParseResult.path);
    assertEquals("example.org", actualParseResult.host);
    assertNull(actualParseResult.userInfo);
    assertEquals(-1, actualParseResult.port);
    assertEquals(Uri.HTTPS, actualParseResult.scheme);
  }

  /**
   * Test {@link UriParser#parse(Uri, String)} with {@code context},
   * {@code originalUrl}.
   * <ul>
   *   <li>Then return {@link UriParser#path} is {@code /example}.</li>
   * </ul>
   * <p>
   * Method under test: {@link UriParser#parse(Uri, String)}
   */
  @Test
  @DisplayName("Test parse(Uri, String) with 'context', 'originalUrl'; then return path is '/example'")
  void testParseWithContextOriginalUrl_thenReturnPathIsExample() {
    // Arrange and Act
    UriParser actualParseResult = UriParser.parse(
        new Uri("https://example.org/example", "https://example.org/example", "https://example.org/example", 8080,
            "https://example.org/example", "https://example.org/example", "https://example.org/example"),
        "https://example.org/example");

    // Assert
    assertEquals("/example", actualParseResult.path);
    assertEquals("example.org", actualParseResult.host);
    assertNull(actualParseResult.userInfo);
    assertEquals(-1, actualParseResult.port);
    assertEquals(Uri.HTTPS, actualParseResult.scheme);
  }

  /**
   * Test {@link UriParser#parse(Uri, String)} with {@code context},
   * {@code originalUrl}.
   * <ul>
   *   <li>Then return {@link UriParser#path} is
   * {@code https://example.org/originalUrl}.</li>
   * </ul>
   * <p>
   * Method under test: {@link UriParser#parse(Uri, String)}
   */
  @Test
  @DisplayName("Test parse(Uri, String) with 'context', 'originalUrl'; then return path is 'https://example.org/originalUrl'")
  void testParseWithContextOriginalUrl_thenReturnPathIsHttpsExampleOrgOriginalUrl() {
    // Arrange and Act
    UriParser actualParseResult = UriParser.parse(
        new Uri("https://example.org/example", "https://example.org/example", "https://example.org/example", 8080,
            "https://example.org/example", "https://example.org/example", "https://example.org/example"),
        "originalUrl");

    // Assert
    assertEquals("https://example.org/example", actualParseResult.host);
    assertEquals("https://example.org/example", actualParseResult.userInfo);
    assertEquals("https://example.org/originalUrl", actualParseResult.path);
    assertEquals(8080, actualParseResult.port);
  }

  /**
   * Test {@link UriParser#parse(Uri, String)} with {@code context},
   * {@code originalUrl}.
   * <ul>
   *   <li>Then return {@link UriParser#path} is {@code originalUrl}.</li>
   * </ul>
   * <p>
   * Method under test: {@link UriParser#parse(Uri, String)}
   */
  @Test
  @DisplayName("Test parse(Uri, String) with 'context', 'originalUrl'; then return path is 'originalUrl'")
  void testParseWithContextOriginalUrl_thenReturnPathIsOriginalUrl() {
    // Arrange and Act
    UriParser actualParseResult = UriParser
        .parse(new Uri("https://example.org/example", "https://example.org/example", "https://example.org/example",
            8080, "originalUrl", "https://example.org/example", "https://example.org/example"), "originalUrl");

    // Assert
    assertEquals("https://example.org/example", actualParseResult.host);
    assertEquals("https://example.org/example", actualParseResult.userInfo);
    assertEquals("originalUrl", actualParseResult.path);
    assertEquals(8080, actualParseResult.port);
  }

  /**
   * Test {@link UriParser#parse(Uri, String)} with {@code context},
   * {@code originalUrl}.
   * <ul>
   *   <li>Then return {@link UriParser#path} is {@code /originalUrl}.</li>
   * </ul>
   * <p>
   * Method under test: {@link UriParser#parse(Uri, String)}
   */
  @Test
  @DisplayName("Test parse(Uri, String) with 'context', 'originalUrl'; then return path is '/originalUrl'")
  void testParseWithContextOriginalUrl_thenReturnPathIsOriginalUrl2() {
    // Arrange and Act
    UriParser actualParseResult = UriParser.parse(new Uri("https://example.org/example", "https://example.org/example",
        "https://example.org/example", 8080, "", "https://example.org/example", "https://example.org/example"),
        "originalUrl");

    // Assert
    assertEquals("/originalUrl", actualParseResult.path);
    assertEquals("https://example.org/example", actualParseResult.host);
    assertEquals("https://example.org/example", actualParseResult.userInfo);
    assertEquals(8080, actualParseResult.port);
  }

  /**
   * Test {@link UriParser#parse(Uri, String)} with {@code context},
   * {@code originalUrl}.
   * <ul>
   *   <li>Then return {@link UriParser#path} is {@code /../originalUrl}.</li>
   * </ul>
   * <p>
   * Method under test: {@link UriParser#parse(Uri, String)}
   */
  @Test
  @DisplayName("Test parse(Uri, String) with 'context', 'originalUrl'; then return path is '/../originalUrl'")
  void testParseWithContextOriginalUrl_thenReturnPathIsOriginalUrl3() {
    // Arrange and Act
    UriParser actualParseResult = UriParser.parse(new Uri("https://example.org/example", "https://example.org/example",
        "https://example.org/example", 8080, "/../", "https://example.org/example", "https://example.org/example"),
        "originalUrl");

    // Assert
    assertEquals("/../originalUrl", actualParseResult.path);
    assertEquals("https://example.org/example", actualParseResult.host);
    assertEquals("https://example.org/example", actualParseResult.userInfo);
    assertEquals(8080, actualParseResult.port);
  }

  /**
   * Test {@link UriParser#parse(Uri, String)} with {@code context},
   * {@code originalUrl}.
   * <ul>
   *   <li>Then return {@link UriParser#path} is {@code ////}.</li>
   * </ul>
   * <p>
   * Method under test: {@link UriParser#parse(Uri, String)}
   */
  @Test
  @DisplayName("Test parse(Uri, String) with 'context', 'originalUrl'; then return path is '////'")
  void testParseWithContextOriginalUrl_thenReturnPathIsSlashSlashSlashSlash() {
    // Arrange and Act
    UriParser actualParseResult = UriParser
        .parse(new Uri("https://example.org/example", "https://example.org/example", "https://example.org/example",
            8080, "https://example.org/example", "https://example.org/example", "https://example.org/example"), "////");

    // Assert
    assertEquals("////", actualParseResult.path);
    assertEquals("https://example.org/example", actualParseResult.host);
    assertEquals("https://example.org/example", actualParseResult.userInfo);
    assertEquals(8080, actualParseResult.port);
  }

  /**
   * Test {@link UriParser#parse(Uri, String)} with {@code context},
   * {@code originalUrl}.
   * <ul>
   *   <li>When empty string.</li>
   * </ul>
   * <p>
   * Method under test: {@link UriParser#parse(Uri, String)}
   */
  @Test
  @DisplayName("Test parse(Uri, String) with 'context', 'originalUrl'; when empty string")
  void testParseWithContextOriginalUrl_whenEmptyString() {
    // Arrange and Act
    UriParser actualParseResult = UriParser
        .parse(new Uri("https://example.org/example", "https://example.org/example", "https://example.org/example",
            8080, "https://example.org/example", "https://example.org/example", "https://example.org/example"), "");

    // Assert
    assertEquals("https://example.org/example", actualParseResult.fragment);
    assertEquals("https://example.org/example", actualParseResult.host);
    assertEquals("https://example.org/example", actualParseResult.path);
    assertEquals("https://example.org/example", actualParseResult.query);
    assertEquals("https://example.org/example", actualParseResult.userInfo);
    assertEquals(8080, actualParseResult.port);
  }

  /**
   * Test {@link UriParser#parse(Uri, String)} with {@code context},
   * {@code originalUrl}.
   * <ul>
   *   <li>When {@code null}.</li>
   *   <li>Then return {@link UriParser#path} is {@code /example}.</li>
   * </ul>
   * <p>
   * Method under test: {@link UriParser#parse(Uri, String)}
   */
  @Test
  @DisplayName("Test parse(Uri, String) with 'context', 'originalUrl'; when 'null'; then return path is '/example'")
  void testParseWithContextOriginalUrl_whenNull_thenReturnPathIsExample() {
    // Arrange and Act
    UriParser actualParseResult = UriParser.parse(null, "https://example.org/example");

    // Assert
    assertEquals("/example", actualParseResult.path);
    assertEquals("example.org", actualParseResult.host);
    assertNull(actualParseResult.userInfo);
    assertEquals(-1, actualParseResult.port);
    assertEquals(Uri.HTTPS, actualParseResult.scheme);
  }

  /**
   * Test {@link UriParser#parse(Uri, String)} with {@code context},
   * {@code originalUrl}.
   * <ul>
   *   <li>When {@code /.}.</li>
   *   <li>Then return {@link UriParser#path} is {@code /}.</li>
   * </ul>
   * <p>
   * Method under test: {@link UriParser#parse(Uri, String)}
   */
  @Test
  @DisplayName("Test parse(Uri, String) with 'context', 'originalUrl'; when '/.'; then return path is '/'")
  void testParseWithContextOriginalUrl_whenSlashDot_thenReturnPathIsSlash() {
    // Arrange and Act
    UriParser actualParseResult = UriParser
        .parse(new Uri("https://example.org/example", "https://example.org/example", "https://example.org/example",
            8080, "https://example.org/example", "https://example.org/example", "https://example.org/example"), "/.");

    // Assert
    assertEquals("/", actualParseResult.path);
    assertEquals("https://example.org/example", actualParseResult.host);
    assertEquals("https://example.org/example", actualParseResult.userInfo);
    assertEquals(8080, actualParseResult.port);
  }

  /**
   * Test {@link UriParser#parse(Uri, String)} with {@code context},
   * {@code originalUrl}.
   * <ul>
   *   <li>When {@code //}.</li>
   *   <li>Then return {@link UriParser#host} is empty string.</li>
   * </ul>
   * <p>
   * Method under test: {@link UriParser#parse(Uri, String)}
   */
  @Test
  @DisplayName("Test parse(Uri, String) with 'context', 'originalUrl'; when '//'; then return host is empty string")
  void testParseWithContextOriginalUrl_whenSlashSlash_thenReturnHostIsEmptyString() {
    // Arrange and Act
    UriParser actualParseResult = UriParser
        .parse(new Uri("https://example.org/example", "https://example.org/example", "https://example.org/example",
            8080, "https://example.org/example", "https://example.org/example", "https://example.org/example"), "//");

    // Assert
    assertEquals("", actualParseResult.host);
    assertEquals("https://example.org/example", actualParseResult.path);
    assertNull(actualParseResult.userInfo);
    assertEquals(-1, actualParseResult.port);
  }

  /**
   * Test {@link UriParser#parse(Uri, String)} with {@code context},
   * {@code originalUrl}.
   * <ul>
   *   <li>When {@code url:}.</li>
   * </ul>
   * <p>
   * Method under test: {@link UriParser#parse(Uri, String)}
   */
  @Test
  @DisplayName("Test parse(Uri, String) with 'context', 'originalUrl'; when 'url:'")
  void testParseWithContextOriginalUrl_whenUrl() {
    // Arrange and Act
    UriParser actualParseResult = UriParser
        .parse(new Uri("https://example.org/example", "https://example.org/example", "https://example.org/example",
            8080, "https://example.org/example", "https://example.org/example", "https://example.org/example"), "url:");

    // Assert
    assertEquals("https://example.org/example", actualParseResult.fragment);
    assertEquals("https://example.org/example", actualParseResult.host);
    assertEquals("https://example.org/example", actualParseResult.path);
    assertEquals("https://example.org/example", actualParseResult.query);
    assertEquals("https://example.org/example", actualParseResult.userInfo);
    assertEquals(8080, actualParseResult.port);
  }
}
