package org.asynchttpclient.uri;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import org.junit.jupiter.api.Test;

class UriParserDiffblueTest {
  /**
   * Method under test: {@link UriParser#parse(Uri, String)}
   */
  @Test
  void testParse() {
    // Arrange and Act
    UriParser actualParseResult = UriParser.parse(
        new Uri("https://example.org/example", "https://example.org/example", "https://example.org/example", 8080,
            "https://example.org/example", "https://example.org/example", "https://example.org/example"),
        "https://example.org/example");

    // Assert
    assertEquals("/example", actualParseResult.path);
    assertEquals("example.org", actualParseResult.host);
    assertNull(actualParseResult.fragment);
    assertNull(actualParseResult.query);
    assertNull(actualParseResult.userInfo);
    assertEquals(-1, actualParseResult.port);
    assertEquals(Uri.HTTPS, actualParseResult.scheme);
  }

  /**
   * Method under test: {@link UriParser#parse(Uri, String)}
   */
  @Test
  void testParse2() {
    // Arrange and Act
    UriParser actualParseResult = UriParser.parse(null, "https://example.org/example");

    // Assert
    assertEquals("/example", actualParseResult.path);
    assertEquals("example.org", actualParseResult.host);
    assertNull(actualParseResult.fragment);
    assertNull(actualParseResult.query);
    assertNull(actualParseResult.userInfo);
    assertEquals(-1, actualParseResult.port);
    assertEquals(Uri.HTTPS, actualParseResult.scheme);
  }

  /**
   * Method under test: {@link UriParser#parse(Uri, String)}
   */
  @Test
  void testParse3() {
    // Arrange and Act
    UriParser actualParseResult = UriParser.parse(
        new Uri(Uri.HTTPS, "https://example.org/example", "https://example.org/example", 8080,
            "https://example.org/example", "https://example.org/example", "https://example.org/example"),
        "https://example.org/example");

    // Assert
    assertEquals("/example", actualParseResult.path);
    assertEquals("example.org", actualParseResult.host);
    assertNull(actualParseResult.fragment);
    assertNull(actualParseResult.query);
    assertNull(actualParseResult.userInfo);
    assertEquals(-1, actualParseResult.port);
    assertEquals(Uri.HTTPS, actualParseResult.scheme);
  }

  /**
   * Method under test: {@link UriParser#parse(Uri, String)}
   */
  @Test
  void testParse4() {
    // Arrange and Act
    UriParser actualParseResult = UriParser.parse(Uri.create(
        new Uri("https://example.org/example", "https://example.org/example", "https://example.org/example", 8080,
            "https://example.org/example", "https://example.org/example", "https://example.org/example"),
        "https://example.org/example"), "https://example.org/example");

    // Assert
    assertEquals("/example", actualParseResult.path);
    assertEquals("example.org", actualParseResult.host);
    assertNull(actualParseResult.fragment);
    assertNull(actualParseResult.query);
    assertNull(actualParseResult.userInfo);
    assertEquals(-1, actualParseResult.port);
    assertEquals(Uri.HTTPS, actualParseResult.scheme);
  }

  /**
   * Method under test: {@link UriParser#parse(Uri, String)}
   */
  @Test
  void testParse5() {
    // Arrange and Act
    UriParser actualParseResult = UriParser.parse(
        new Uri("https://example.org/example", "https://example.org/example", "https://example.org/example", 8080,
            "https://example.org/example", "https://example.org/example", "https://example.org/example"),
        "originalUrl");

    // Assert
    assertEquals("https://example.org/example", actualParseResult.host);
    assertEquals("https://example.org/example", actualParseResult.scheme);
    assertEquals("https://example.org/example", actualParseResult.userInfo);
    assertEquals("https://example.org/originalUrl", actualParseResult.path);
    assertNull(actualParseResult.fragment);
    assertNull(actualParseResult.query);
    assertEquals(8080, actualParseResult.port);
  }

  /**
   * Method under test: {@link UriParser#parse(Uri, String)}
   */
  @Test
  void testParse6() {
    // Arrange and Act
    UriParser actualParseResult = UriParser
        .parse(new Uri("https://example.org/example", "https://example.org/example", "https://example.org/example",
            8080, "https://example.org/example", "https://example.org/example", "https://example.org/example"), "url:");

    // Assert
    assertEquals("https://example.org/example", actualParseResult.fragment);
    assertEquals("https://example.org/example", actualParseResult.host);
    assertEquals("https://example.org/example", actualParseResult.path);
    assertEquals("https://example.org/example", actualParseResult.query);
    assertEquals("https://example.org/example", actualParseResult.scheme);
    assertEquals("https://example.org/example", actualParseResult.userInfo);
    assertEquals(8080, actualParseResult.port);
  }

  /**
   * Method under test: {@link UriParser#parse(Uri, String)}
   */
  @Test
  void testParse7() {
    // Arrange and Act
    UriParser actualParseResult = UriParser
        .parse(new Uri("https://example.org/example", "https://example.org/example", "https://example.org/example",
            8080, "https://example.org/example", "https://example.org/example", "https://example.org/example"), "////");

    // Assert
    assertEquals("////", actualParseResult.path);
    assertEquals("https://example.org/example", actualParseResult.host);
    assertEquals("https://example.org/example", actualParseResult.scheme);
    assertEquals("https://example.org/example", actualParseResult.userInfo);
    assertNull(actualParseResult.fragment);
    assertNull(actualParseResult.query);
    assertEquals(8080, actualParseResult.port);
  }

  /**
   * Method under test: {@link UriParser#parse(Uri, String)}
   */
  @Test
  void testParse8() {
    // Arrange and Act
    UriParser actualParseResult = UriParser
        .parse(new Uri("https://example.org/example", "https://example.org/example", "https://example.org/example",
            8080, "https://example.org/example", "https://example.org/example", "https://example.org/example"), "//");

    // Assert
    assertEquals("", actualParseResult.host);
    assertEquals("https://example.org/example", actualParseResult.path);
    assertEquals("https://example.org/example", actualParseResult.scheme);
    assertNull(actualParseResult.fragment);
    assertNull(actualParseResult.query);
    assertNull(actualParseResult.userInfo);
    assertEquals(-1, actualParseResult.port);
  }

  /**
   * Method under test: {@link UriParser#parse(Uri, String)}
   */
  @Test
  void testParse9() {
    // Arrange and Act
    UriParser actualParseResult = UriParser
        .parse(new Uri("https://example.org/example", "https://example.org/example", "https://example.org/example",
            8080, "https://example.org/example", "https://example.org/example", "https://example.org/example"), "");

    // Assert
    assertEquals("https://example.org/example", actualParseResult.fragment);
    assertEquals("https://example.org/example", actualParseResult.host);
    assertEquals("https://example.org/example", actualParseResult.path);
    assertEquals("https://example.org/example", actualParseResult.query);
    assertEquals("https://example.org/example", actualParseResult.scheme);
    assertEquals("https://example.org/example", actualParseResult.userInfo);
    assertEquals(8080, actualParseResult.port);
  }

  /**
   * Method under test: {@link UriParser#parse(Uri, String)}
   */
  @Test
  void testParse10() {
    // Arrange and Act
    UriParser actualParseResult = UriParser.parse(new Uri(Uri.HTTPS, "https://example.org/example",
        "https://example.org/example", 8080, "", "https://example.org/example", "https://example.org/example"),
        "https://example.org/example");

    // Assert
    assertEquals("/example", actualParseResult.path);
    assertEquals("example.org", actualParseResult.host);
    assertNull(actualParseResult.fragment);
    assertNull(actualParseResult.query);
    assertNull(actualParseResult.userInfo);
    assertEquals(-1, actualParseResult.port);
    assertEquals(Uri.HTTPS, actualParseResult.scheme);
  }

  /**
   * Method under test: {@link UriParser#parse(Uri, String)}
   */
  @Test
  void testParse11() {
    // Arrange and Act
    UriParser actualParseResult = UriParser.parse(new Uri(Uri.HTTPS, "https://example.org/example",
        "https://example.org/example", 8080, null, "https://example.org/example", "https://example.org/example"),
        "https://example.org/example");

    // Assert
    assertEquals("/example", actualParseResult.path);
    assertEquals("example.org", actualParseResult.host);
    assertNull(actualParseResult.fragment);
    assertNull(actualParseResult.query);
    assertNull(actualParseResult.userInfo);
    assertEquals(-1, actualParseResult.port);
    assertEquals(Uri.HTTPS, actualParseResult.scheme);
  }

  /**
   * Method under test: {@link UriParser#parse(Uri, String)}
   */
  @Test
  void testParse12() {
    // Arrange and Act
    UriParser actualParseResult = UriParser
        .parse(new Uri("https://example.org/example", "https://example.org/example", "https://example.org/example",
            8080, "https://example.org/example", "https://example.org/example", "https://example.org/example"), "/.");

    // Assert
    assertEquals("/", actualParseResult.path);
    assertEquals("https://example.org/example", actualParseResult.host);
    assertEquals("https://example.org/example", actualParseResult.scheme);
    assertEquals("https://example.org/example", actualParseResult.userInfo);
    assertNull(actualParseResult.fragment);
    assertNull(actualParseResult.query);
    assertEquals(8080, actualParseResult.port);
  }

  /**
   * Method under test: {@link UriParser#parse(Uri, String)}
   */
  @Test
  void testParse13() {
    // Arrange and Act
    UriParser actualParseResult = UriParser
        .parse(new Uri("https://example.org/example", "https://example.org/example", "https://example.org/example",
            8080, "originalUrl", "https://example.org/example", "https://example.org/example"), "originalUrl");

    // Assert
    assertEquals("https://example.org/example", actualParseResult.host);
    assertEquals("https://example.org/example", actualParseResult.scheme);
    assertEquals("https://example.org/example", actualParseResult.userInfo);
    assertEquals("originalUrl", actualParseResult.path);
    assertNull(actualParseResult.fragment);
    assertNull(actualParseResult.query);
    assertEquals(8080, actualParseResult.port);
  }

  /**
   * Method under test: {@link UriParser#parse(Uri, String)}
   */
  @Test
  void testParse14() {
    // Arrange and Act
    UriParser actualParseResult = UriParser.parse(new Uri("https://example.org/example", "https://example.org/example",
        "https://example.org/example", 8080, "", "https://example.org/example", "https://example.org/example"),
        "originalUrl");

    // Assert
    assertEquals("/originalUrl", actualParseResult.path);
    assertEquals("https://example.org/example", actualParseResult.host);
    assertEquals("https://example.org/example", actualParseResult.scheme);
    assertEquals("https://example.org/example", actualParseResult.userInfo);
    assertNull(actualParseResult.fragment);
    assertNull(actualParseResult.query);
    assertEquals(8080, actualParseResult.port);
  }

  /**
   * Method under test: {@link UriParser#parse(Uri, String)}
   */
  @Test
  void testParse15() {
    // Arrange and Act
    UriParser actualParseResult = UriParser.parse(new Uri("https://example.org/example", "https://example.org/example",
        "https://example.org/example", 8080, "/../", "https://example.org/example", "https://example.org/example"),
        "originalUrl");

    // Assert
    assertEquals("/../originalUrl", actualParseResult.path);
    assertEquals("https://example.org/example", actualParseResult.host);
    assertEquals("https://example.org/example", actualParseResult.scheme);
    assertEquals("https://example.org/example", actualParseResult.userInfo);
    assertNull(actualParseResult.fragment);
    assertNull(actualParseResult.query);
    assertEquals(8080, actualParseResult.port);
  }
}
