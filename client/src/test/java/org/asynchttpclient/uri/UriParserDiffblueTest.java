package org.asynchttpclient.uri;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

class UriParserDiffblueTest {
  /**
   * Test {@link UriParser#parse(Uri, String)} with {@code context}, {@code originalUrl}.
   *
   * <p>Method under test: {@link UriParser#parse(Uri, String)}
   */
  @Test
  @DisplayName("Test parse(Uri, String) with 'context', 'originalUrl'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"UriParser UriParser.parse(Uri, String)"})
  void testParseWithContextOriginalUrl() {
    // Arrange
    Uri context =
        new Uri(
            "https://example.org/example",
            "https://example.org/example",
            "https://example.org/example",
            8080,
            "https://example.org/example",
            "https://example.org/example",
            "https://example.org/example");

    // Act
    UriParser actualParseResult =
        UriParser.parse(
            Uri.create(context, "https://example.org/example"), "https://example.org/example");

    // Assert
    assertEquals("/example", actualParseResult.path);
    assertEquals("example.org", actualParseResult.host);
    assertNull(actualParseResult.userInfo);
    assertEquals(-1, actualParseResult.port);
    assertEquals(Uri.HTTPS, actualParseResult.scheme);
  }

  /**
   * Test {@link UriParser#parse(Uri, String)} with {@code context}, {@code originalUrl}.
   *
   * <p>Method under test: {@link UriParser#parse(Uri, String)}
   */
  @Test
  @DisplayName("Test parse(Uri, String) with 'context', 'originalUrl'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"UriParser UriParser.parse(Uri, String)"})
  void testParseWithContextOriginalUrl2() {
    // Arrange
    Uri context =
        new Uri(
            "https://example.org/example",
            "https://example.org/example",
            "https://example.org/example",
            8080,
            "",
            "https://example.org/example",
            "https://example.org/example");

    // Act
    UriParser actualParseResult = UriParser.parse(context, "originalUrl");

    // Assert
    assertEquals("/originalUrl", actualParseResult.path);
    assertEquals("https://example.org/example", actualParseResult.host);
    assertEquals("https://example.org/example", actualParseResult.userInfo);
    assertEquals(8080, actualParseResult.port);
  }

  /**
   * Test {@link UriParser#parse(Uri, String)} with {@code context}, {@code originalUrl}.
   *
   * <p>Method under test: {@link UriParser#parse(Uri, String)}
   */
  @Test
  @DisplayName("Test parse(Uri, String) with 'context', 'originalUrl'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"UriParser UriParser.parse(Uri, String)"})
  void testParseWithContextOriginalUrl3() {
    // Arrange
    Uri context =
        new Uri(
            "https://example.org/example",
            "https://example.org/example",
            "https://example.org/example",
            8080,
            null,
            "https://example.org/example",
            "https://example.org/example");

    // Act
    UriParser actualParseResult = UriParser.parse(context, "originalUrl");

    // Assert
    assertEquals("/originalUrl", actualParseResult.path);
    assertEquals("https://example.org/example", actualParseResult.host);
    assertEquals("https://example.org/example", actualParseResult.userInfo);
    assertEquals(8080, actualParseResult.port);
  }

  /**
   * Test {@link UriParser#parse(Uri, String)} with {@code context}, {@code originalUrl}.
   *
   * <p>Method under test: {@link UriParser#parse(Uri, String)}
   */
  @Test
  @DisplayName("Test parse(Uri, String) with 'context', 'originalUrl'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"UriParser UriParser.parse(Uri, String)"})
  void testParseWithContextOriginalUrl4() {
    // Arrange
    Uri context =
        new Uri(
            "https://example.org/example",
            "https://example.org/example",
            "https://example.org/example",
            8080,
            "./",
            "https://example.org/example",
            "https://example.org/example");

    // Act
    UriParser actualParseResult = UriParser.parse(context, "originalUrl");

    // Assert
    assertEquals("https://example.org/example", actualParseResult.host);
    assertEquals("https://example.org/example", actualParseResult.userInfo);
    assertEquals("originalUrl", actualParseResult.path);
    assertEquals(8080, actualParseResult.port);
  }

  /**
   * Test {@link UriParser#parse(Uri, String)} with {@code context}, {@code originalUrl}.
   *
   * <p>Method under test: {@link UriParser#parse(Uri, String)}
   */
  @Test
  @DisplayName("Test parse(Uri, String) with 'context', 'originalUrl'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"UriParser UriParser.parse(Uri, String)"})
  void testParseWithContextOriginalUrl5() {
    // Arrange
    Uri context =
        new Uri(
            Uri.HTTPS,
            "https://example.org/example",
            "https://example.org/example",
            8080,
            "https://example.org/example",
            "https://example.org/example",
            "https://example.org/example");

    // Act
    UriParser actualParseResult = UriParser.parse(context, "https://example.org/example");

    // Assert
    assertEquals("/example", actualParseResult.path);
    assertEquals("example.org", actualParseResult.host);
    assertNull(actualParseResult.userInfo);
    assertEquals(-1, actualParseResult.port);
    assertEquals(Uri.HTTPS, actualParseResult.scheme);
  }

  /**
   * Test {@link UriParser#parse(Uri, String)} with {@code context}, {@code originalUrl}.
   *
   * <ul>
   *   <li>Then return {@link UriParser#path} is {@code /example}.
   * </ul>
   *
   * <p>Method under test: {@link UriParser#parse(Uri, String)}
   */
  @Test
  @DisplayName(
      "Test parse(Uri, String) with 'context', 'originalUrl'; then return path is '/example'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"UriParser UriParser.parse(Uri, String)"})
  void testParseWithContextOriginalUrl_thenReturnPathIsExample() {
    // Arrange
    Uri context =
        new Uri(
            "https://example.org/example",
            "https://example.org/example",
            "https://example.org/example",
            8080,
            "https://example.org/example",
            "https://example.org/example",
            "https://example.org/example");

    // Act
    UriParser actualParseResult = UriParser.parse(context, "https://example.org/example");

    // Assert
    assertEquals("/example", actualParseResult.path);
    assertEquals("example.org", actualParseResult.host);
    assertNull(actualParseResult.userInfo);
    assertEquals(-1, actualParseResult.port);
    assertEquals(Uri.HTTPS, actualParseResult.scheme);
  }

  /**
   * Test {@link UriParser#parse(Uri, String)} with {@code context}, {@code originalUrl}.
   *
   * <ul>
   *   <li>Then return {@link UriParser#path} is {@code https://example.org/originalUrl}.
   * </ul>
   *
   * <p>Method under test: {@link UriParser#parse(Uri, String)}
   */
  @Test
  @DisplayName(
      "Test parse(Uri, String) with 'context', 'originalUrl'; then return path is 'https://example.org/originalUrl'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"UriParser UriParser.parse(Uri, String)"})
  void testParseWithContextOriginalUrl_thenReturnPathIsHttpsExampleOrgOriginalUrl() {
    // Arrange
    Uri context =
        new Uri(
            "https://example.org/example",
            "https://example.org/example",
            "https://example.org/example",
            8080,
            "https://example.org/example",
            "https://example.org/example",
            "https://example.org/example");

    // Act
    UriParser actualParseResult = UriParser.parse(context, "originalUrl");

    // Assert
    assertEquals("https://example.org/example", actualParseResult.host);
    assertEquals("https://example.org/example", actualParseResult.userInfo);
    assertEquals("https://example.org/originalUrl", actualParseResult.path);
    assertEquals(8080, actualParseResult.port);
  }

  /**
   * Test {@link UriParser#parse(Uri, String)} with {@code context}, {@code originalUrl}.
   *
   * <ul>
   *   <li>Then return {@link UriParser#path} is {@code originalUrl}.
   * </ul>
   *
   * <p>Method under test: {@link UriParser#parse(Uri, String)}
   */
  @Test
  @DisplayName(
      "Test parse(Uri, String) with 'context', 'originalUrl'; then return path is 'originalUrl'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"UriParser UriParser.parse(Uri, String)"})
  void testParseWithContextOriginalUrl_thenReturnPathIsOriginalUrl() {
    // Arrange
    Uri context =
        new Uri(
            "https://example.org/example",
            "https://example.org/example",
            "https://example.org/example",
            8080,
            "originalUrl",
            "https://example.org/example",
            "https://example.org/example");

    // Act
    UriParser actualParseResult = UriParser.parse(context, "originalUrl");

    // Assert
    assertEquals("https://example.org/example", actualParseResult.host);
    assertEquals("https://example.org/example", actualParseResult.userInfo);
    assertEquals("originalUrl", actualParseResult.path);
    assertEquals(8080, actualParseResult.port);
  }

  /**
   * Test {@link UriParser#parse(Uri, String)} with {@code context}, {@code originalUrl}.
   *
   * <ul>
   *   <li>Then return {@link UriParser#path} is {@code /../originalUrl}.
   * </ul>
   *
   * <p>Method under test: {@link UriParser#parse(Uri, String)}
   */
  @Test
  @DisplayName(
      "Test parse(Uri, String) with 'context', 'originalUrl'; then return path is '/../originalUrl'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"UriParser UriParser.parse(Uri, String)"})
  void testParseWithContextOriginalUrl_thenReturnPathIsOriginalUrl2() {
    // Arrange
    Uri context =
        new Uri(
            "https://example.org/example",
            "https://example.org/example",
            "https://example.org/example",
            8080,
            "/../",
            "https://example.org/example",
            "https://example.org/example");

    // Act
    UriParser actualParseResult = UriParser.parse(context, "originalUrl");

    // Assert
    assertEquals("/../originalUrl", actualParseResult.path);
    assertEquals("https://example.org/example", actualParseResult.host);
    assertEquals("https://example.org/example", actualParseResult.userInfo);
    assertEquals(8080, actualParseResult.port);
  }

  /**
   * Test {@link UriParser#parse(Uri, String)} with {@code context}, {@code originalUrl}.
   *
   * <ul>
   *   <li>Then return {@link UriParser#path} is {@code ////}.
   * </ul>
   *
   * <p>Method under test: {@link UriParser#parse(Uri, String)}
   */
  @Test
  @DisplayName("Test parse(Uri, String) with 'context', 'originalUrl'; then return path is '////'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"UriParser UriParser.parse(Uri, String)"})
  void testParseWithContextOriginalUrl_thenReturnPathIsSlashSlashSlashSlash() {
    // Arrange
    Uri context =
        new Uri(
            "https://example.org/example",
            "https://example.org/example",
            "https://example.org/example",
            8080,
            "https://example.org/example",
            "https://example.org/example",
            "https://example.org/example");

    // Act
    UriParser actualParseResult = UriParser.parse(context, "////");

    // Assert
    assertEquals("////", actualParseResult.path);
    assertEquals("https://example.org/example", actualParseResult.host);
    assertEquals("https://example.org/example", actualParseResult.userInfo);
    assertEquals(8080, actualParseResult.port);
  }

  /**
   * Test {@link UriParser#parse(Uri, String)} with {@code context}, {@code originalUrl}.
   *
   * <ul>
   *   <li>When {@code ./}.
   *   <li>Then return {@link UriParser#path} is {@code ./}.
   * </ul>
   *
   * <p>Method under test: {@link UriParser#parse(Uri, String)}
   */
  @Test
  @DisplayName(
      "Test parse(Uri, String) with 'context', 'originalUrl'; when './'; then return path is './'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"UriParser UriParser.parse(Uri, String)"})
  void testParseWithContextOriginalUrl_whenDotSlash_thenReturnPathIsDotSlash() {
    // Arrange
    Uri context =
        new Uri(
            "https://example.org/example",
            "https://example.org/example",
            "https://example.org/example",
            8080,
            "originalUrl",
            "https://example.org/example",
            "https://example.org/example");

    // Act
    UriParser actualParseResult = UriParser.parse(context, "./");

    // Assert
    assertEquals("./", actualParseResult.path);
    assertEquals("https://example.org/example", actualParseResult.host);
    assertEquals("https://example.org/example", actualParseResult.userInfo);
    assertEquals(8080, actualParseResult.port);
  }

  /**
   * Test {@link UriParser#parse(Uri, String)} with {@code context}, {@code originalUrl}.
   *
   * <ul>
   *   <li>When empty string.
   * </ul>
   *
   * <p>Method under test: {@link UriParser#parse(Uri, String)}
   */
  @Test
  @DisplayName("Test parse(Uri, String) with 'context', 'originalUrl'; when empty string")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"UriParser UriParser.parse(Uri, String)"})
  void testParseWithContextOriginalUrl_whenEmptyString() {
    // Arrange
    Uri context =
        new Uri(
            "https://example.org/example",
            "https://example.org/example",
            "https://example.org/example",
            8080,
            "https://example.org/example",
            "https://example.org/example",
            "https://example.org/example");

    // Act
    UriParser actualParseResult = UriParser.parse(context, "");

    // Assert
    assertEquals("https://example.org/example", actualParseResult.fragment);
    assertEquals("https://example.org/example", actualParseResult.host);
    assertEquals("https://example.org/example", actualParseResult.path);
    assertEquals("https://example.org/example", actualParseResult.query);
    assertEquals("https://example.org/example", actualParseResult.userInfo);
    assertEquals(8080, actualParseResult.port);
  }

  /**
   * Test {@link UriParser#parse(Uri, String)} with {@code context}, {@code originalUrl}.
   *
   * <ul>
   *   <li>When {@code null}.
   *   <li>Then return {@link UriParser#path} is {@code /example}.
   * </ul>
   *
   * <p>Method under test: {@link UriParser#parse(Uri, String)}
   */
  @Test
  @DisplayName(
      "Test parse(Uri, String) with 'context', 'originalUrl'; when 'null'; then return path is '/example'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"UriParser UriParser.parse(Uri, String)"})
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
   * Test {@link UriParser#parse(Uri, String)} with {@code context}, {@code originalUrl}.
   *
   * <ul>
   *   <li>When {@code //}.
   *   <li>Then return {@link UriParser#host} is empty string.
   * </ul>
   *
   * <p>Method under test: {@link UriParser#parse(Uri, String)}
   */
  @Test
  @DisplayName(
      "Test parse(Uri, String) with 'context', 'originalUrl'; when '//'; then return host is empty string")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"UriParser UriParser.parse(Uri, String)"})
  void testParseWithContextOriginalUrl_whenSlashSlash_thenReturnHostIsEmptyString() {
    // Arrange
    Uri context =
        new Uri(
            "https://example.org/example",
            "https://example.org/example",
            "https://example.org/example",
            8080,
            "https://example.org/example",
            "https://example.org/example",
            "https://example.org/example");

    // Act
    UriParser actualParseResult = UriParser.parse(context, "//");

    // Assert
    assertEquals("", actualParseResult.host);
    assertEquals("https://example.org/example", actualParseResult.path);
    assertNull(actualParseResult.userInfo);
    assertEquals(-1, actualParseResult.port);
  }

  /**
   * Test {@link UriParser#parse(Uri, String)} with {@code context}, {@code originalUrl}.
   *
   * <ul>
   *   <li>When {@code url:}.
   * </ul>
   *
   * <p>Method under test: {@link UriParser#parse(Uri, String)}
   */
  @Test
  @DisplayName("Test parse(Uri, String) with 'context', 'originalUrl'; when 'url:'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"UriParser UriParser.parse(Uri, String)"})
  void testParseWithContextOriginalUrl_whenUrl() {
    // Arrange
    Uri context =
        new Uri(
            "https://example.org/example",
            "https://example.org/example",
            "https://example.org/example",
            8080,
            "https://example.org/example",
            "https://example.org/example",
            "https://example.org/example");

    // Act
    UriParser actualParseResult = UriParser.parse(context, "url:");

    // Assert
    assertEquals("https://example.org/example", actualParseResult.fragment);
    assertEquals("https://example.org/example", actualParseResult.host);
    assertEquals("https://example.org/example", actualParseResult.path);
    assertEquals("https://example.org/example", actualParseResult.query);
    assertEquals("https://example.org/example", actualParseResult.userInfo);
    assertEquals(8080, actualParseResult.port);
  }
}
