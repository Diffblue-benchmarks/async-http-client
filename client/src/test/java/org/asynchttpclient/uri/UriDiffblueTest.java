package org.asynchttpclient.uri;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import java.net.URISyntaxException;
import org.junit.jupiter.api.Test;

class UriDiffblueTest {
  /**
   * Method under test: {@link Uri#create(String)}
   */
  @Test
  void testCreate() throws URISyntaxException {
    // Arrange and Act
    Uri actualCreateResult = Uri.create("https://example.org/example");

    // Assert
    assertEquals("/example", actualCreateResult.getNonEmptyPath());
    assertEquals("/example", actualCreateResult.getPath());
    assertEquals("example.org", actualCreateResult.getHost());
    assertEquals("example.org:443", actualCreateResult.getAuthority());
    assertEquals("https://example.org/example", actualCreateResult.toJavaNetURI().toString());
    assertEquals("https://example.org:443", actualCreateResult.getBaseUrl());
    assertNull(actualCreateResult.getFragment());
    assertNull(actualCreateResult.getQuery());
    assertNull(actualCreateResult.getUserInfo());
    assertEquals(-1, actualCreateResult.getPort());
    assertEquals(443, actualCreateResult.getExplicitPort());
    assertEquals(443, actualCreateResult.getSchemeDefaultPort());
    assertFalse(actualCreateResult.isWebSocket());
    assertTrue(actualCreateResult.isSecured());
    assertEquals(Uri.HTTPS, actualCreateResult.getScheme());
  }

  /**
   * Method under test: {@link Uri#create(String)}
   */
  @Test
  void testCreate2() {
    // Arrange, Act and Assert
    assertThrows(IllegalArgumentException.class, () -> Uri.create("originalUrl"));
  }

  /**
   * Method under test: {@link Uri#create(String)}
   */
  @Test
  void testCreate3() {
    // Arrange, Act and Assert
    assertThrows(IllegalArgumentException.class, () -> Uri.create("url:"));
  }

  /**
   * Method under test: {@link Uri#create(String)}
   */
  @Test
  void testCreate4() {
    // Arrange, Act and Assert
    assertThrows(IllegalArgumentException.class, () -> Uri.create("////"));
  }

  /**
   * Method under test: {@link Uri#create(String)}
   */
  @Test
  void testCreate5() {
    // Arrange, Act and Assert
    assertThrows(IllegalArgumentException.class, () -> Uri.create("//"));
  }

  /**
   * Method under test: {@link Uri#create(String)}
   */
  @Test
  void testCreate6() {
    // Arrange, Act and Assert
    assertThrows(IllegalArgumentException.class, () -> Uri.create(""));
  }

  /**
   * Method under test: {@link Uri#create(String)}
   */
  @Test
  void testCreate7() throws URISyntaxException {
    // Arrange and Act
    Uri actualCreateResult = Uri.create("https://example.org/examplehttps://example.org/example");

    // Assert
    assertEquals("/examplehttps://example.org/example", actualCreateResult.getNonEmptyPath());
    assertEquals("/examplehttps://example.org/example", actualCreateResult.getPath());
    assertEquals("example.org", actualCreateResult.getHost());
    assertEquals("example.org:443", actualCreateResult.getAuthority());
    assertEquals("https://example.org/examplehttps://example.org/example",
        actualCreateResult.toJavaNetURI().toString());
    assertEquals("https://example.org:443", actualCreateResult.getBaseUrl());
    assertNull(actualCreateResult.getFragment());
    assertNull(actualCreateResult.getQuery());
    assertNull(actualCreateResult.getUserInfo());
    assertEquals(-1, actualCreateResult.getPort());
    assertEquals(443, actualCreateResult.getExplicitPort());
    assertEquals(443, actualCreateResult.getSchemeDefaultPort());
    assertFalse(actualCreateResult.isWebSocket());
    assertTrue(actualCreateResult.isSecured());
    assertEquals(Uri.HTTPS, actualCreateResult.getScheme());
  }

  /**
   * Method under test: {@link Uri#create(String)}
   */
  @Test
  void testCreate8() throws URISyntaxException {
    // Arrange and Act
    Uri actualCreateResult = Uri.create("originalUrlhttps://example.org/example");

    // Assert
    assertEquals("/example", actualCreateResult.getNonEmptyPath());
    assertEquals("/example", actualCreateResult.getPath());
    assertEquals("example.org", actualCreateResult.getHost());
    assertEquals("example.org:80", actualCreateResult.getAuthority());
    assertEquals("originalurlhttps", actualCreateResult.getScheme());
    assertEquals("originalurlhttps://example.org/example", actualCreateResult.toJavaNetURI().toString());
    assertEquals("originalurlhttps://example.org:80", actualCreateResult.getBaseUrl());
    assertNull(actualCreateResult.getFragment());
    assertNull(actualCreateResult.getQuery());
    assertNull(actualCreateResult.getUserInfo());
    assertEquals(-1, actualCreateResult.getPort());
    assertEquals(80, actualCreateResult.getExplicitPort());
    assertEquals(80, actualCreateResult.getSchemeDefaultPort());
    assertFalse(actualCreateResult.isSecured());
    assertFalse(actualCreateResult.isWebSocket());
  }

  /**
   * Method under test: {@link Uri#create(String)}
   */
  @Test
  void testCreate9() {
    // Arrange, Act and Assert
    assertThrows(IllegalArgumentException.class, () -> Uri.create("originalUrlurl:"));
  }

  /**
   * Method under test: {@link Uri#create(String)}
   */
  @Test
  void testCreate10() {
    // Arrange, Act and Assert
    assertThrows(IllegalArgumentException.class, () -> Uri.create("//https://example.org/example"));
  }

  /**
   * Method under test: {@link Uri#create(String)}
   */
  @Test
  void testCreate11() {
    // Arrange, Act and Assert
    assertThrows(IllegalArgumentException.class, () -> Uri.create("Original Urlhttps://example.org/example"));
  }

  /**
   * Method under test: {@link Uri#create(String)}
   */
  @Test
  void testCreate12() {
    // Arrange, Act and Assert
    assertThrows(IllegalArgumentException.class, () -> Uri.create("42https://example.org/example"));
  }

  /**
   * Method under test: {@link Uri#create(Uri, String)}
   */
  @Test
  void testCreate13() throws URISyntaxException {
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
    assertNull(actualCreateResult.getFragment());
    assertNull(actualCreateResult.getQuery());
    assertNull(actualCreateResult.getUserInfo());
    assertEquals(-1, actualCreateResult.getPort());
    assertEquals(443, actualCreateResult.getExplicitPort());
    assertEquals(443, actualCreateResult.getSchemeDefaultPort());
    assertFalse(actualCreateResult.isWebSocket());
    assertTrue(actualCreateResult.isSecured());
    assertEquals(Uri.HTTPS, actualCreateResult.getScheme());
  }

  /**
   * Method under test: {@link Uri#create(Uri, String)}
   */
  @Test
  void testCreate14() throws URISyntaxException {
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
    assertNull(actualCreateResult.getFragment());
    assertNull(actualCreateResult.getQuery());
    assertNull(actualCreateResult.getUserInfo());
    assertEquals(-1, actualCreateResult.getPort());
    assertEquals(443, actualCreateResult.getExplicitPort());
    assertEquals(443, actualCreateResult.getSchemeDefaultPort());
    assertFalse(actualCreateResult.isWebSocket());
    assertTrue(actualCreateResult.isSecured());
    assertEquals(Uri.HTTPS, actualCreateResult.getScheme());
  }

  /**
   * Method under test: {@link Uri#create(Uri, String)}
   */
  @Test
  void testCreate15() throws URISyntaxException {
    // Arrange and Act
    Uri actualCreateResult = Uri.create(null, "https://example.org/example");

    // Assert
    assertEquals("/example", actualCreateResult.getNonEmptyPath());
    assertEquals("/example", actualCreateResult.getPath());
    assertEquals("example.org", actualCreateResult.getHost());
    assertEquals("example.org:443", actualCreateResult.getAuthority());
    assertEquals("https://example.org/example", actualCreateResult.toJavaNetURI().toString());
    assertEquals("https://example.org:443", actualCreateResult.getBaseUrl());
    assertNull(actualCreateResult.getFragment());
    assertNull(actualCreateResult.getQuery());
    assertNull(actualCreateResult.getUserInfo());
    assertEquals(-1, actualCreateResult.getPort());
    assertEquals(443, actualCreateResult.getExplicitPort());
    assertEquals(443, actualCreateResult.getSchemeDefaultPort());
    assertFalse(actualCreateResult.isWebSocket());
    assertTrue(actualCreateResult.isSecured());
    assertEquals(Uri.HTTPS, actualCreateResult.getScheme());
  }

  /**
   * Method under test: {@link Uri#create(Uri, String)}
   */
  @Test
  void testCreate16() throws URISyntaxException {
    // Arrange
    Uri context = Uri.create(
        new Uri("https://example.org/example", "https://example.org/example", "https://example.org/example", 8080,
            "https://example.org/example", "https://example.org/example", "https://example.org/example"),
        "https://example.org/example");

    // Act
    Uri actualCreateResult = Uri.create(context, "https://example.org/example");

    // Assert
    assertEquals("/example", context.getNonEmptyPath());
    assertEquals("/example", context.getPath());
    assertEquals("example.org", context.getHost());
    assertEquals("example.org:443", context.getAuthority());
    assertEquals("https://example.org/example", context.toJavaNetURI().toString());
    assertEquals("https://example.org:443", context.getBaseUrl());
    assertNull(context.getFragment());
    assertNull(context.getQuery());
    assertNull(context.getUserInfo());
    assertEquals(-1, context.getPort());
    assertEquals(443, context.getExplicitPort());
    assertEquals(443, context.getSchemeDefaultPort());
    assertFalse(context.isWebSocket());
    assertTrue(context.isSecured());
    assertEquals(context, actualCreateResult);
    assertEquals(Uri.HTTPS, context.getScheme());
  }

  /**
   * Method under test: {@link Uri#create(Uri, String)}
   */
  @Test
  void testCreate17() throws URISyntaxException {
    // Arrange and Act
    Uri actualCreateResult = Uri.create(
        new Uri("https://example.org/example", "https://example.org/example", "https://example.org/example", 8080,
            "https://example.org/example", "https://example.org/example", "https://example.org/example"),
        "originalUrl");

    // Assert
    assertEquals("https://example.org/example", actualCreateResult.getHost());
    assertEquals("https://example.org/example", actualCreateResult.getScheme());
    assertEquals("https://example.org/example", actualCreateResult.getUserInfo());
    assertEquals("https://example.org/example://https://example.org/example:8080", actualCreateResult.getBaseUrl());
    assertEquals("https://example.org/example://https://example.org/example@https://example.org/example:8080https:/"
        + "/example.org/originalUrl", actualCreateResult.toJavaNetURI().toString());
    assertEquals("https://example.org/example:8080", actualCreateResult.getAuthority());
    assertEquals("https://example.org/originalUrl", actualCreateResult.getNonEmptyPath());
    assertEquals("https://example.org/originalUrl", actualCreateResult.getPath());
    assertNull(actualCreateResult.getFragment());
    assertNull(actualCreateResult.getQuery());
    assertEquals(80, actualCreateResult.getSchemeDefaultPort());
    assertEquals(8080, actualCreateResult.getExplicitPort());
    assertEquals(8080, actualCreateResult.getPort());
    assertFalse(actualCreateResult.isSecured());
    assertFalse(actualCreateResult.isWebSocket());
  }

  /**
   * Method under test: {@link Uri#create(Uri, String)}
   */
  @Test
  void testCreate18() {
    // Arrange
    Uri context = new Uri("https://example.org/example", "https://example.org/example", "https://example.org/example",
        8080, "https://example.org/example", "https://example.org/example", "https://example.org/example");

    // Act and Assert
    assertEquals(context, Uri.create(context, "url:"));
  }

  /**
   * Method under test: {@link Uri#create(Uri, String)}
   */
  @Test
  void testCreate19() throws URISyntaxException {
    // Arrange and Act
    Uri actualCreateResult = Uri
        .create(new Uri("https://example.org/example", "https://example.org/example", "https://example.org/example",
            8080, "https://example.org/example", "https://example.org/example", "https://example.org/example"), "////");

    // Assert
    assertEquals("////", actualCreateResult.getNonEmptyPath());
    assertEquals("////", actualCreateResult.getPath());
    assertEquals("https://example.org/example", actualCreateResult.getHost());
    assertEquals("https://example.org/example", actualCreateResult.getScheme());
    assertEquals("https://example.org/example", actualCreateResult.getUserInfo());
    assertEquals("https://example.org/example://https://example.org/example:8080", actualCreateResult.getBaseUrl());
    assertEquals("https://example.org/example://https://example.org/example@https://example.org/example:8080////",
        actualCreateResult.toJavaNetURI().toString());
    assertEquals("https://example.org/example:8080", actualCreateResult.getAuthority());
    assertNull(actualCreateResult.getFragment());
    assertNull(actualCreateResult.getQuery());
    assertEquals(80, actualCreateResult.getSchemeDefaultPort());
    assertEquals(8080, actualCreateResult.getExplicitPort());
    assertEquals(8080, actualCreateResult.getPort());
    assertFalse(actualCreateResult.isSecured());
    assertFalse(actualCreateResult.isWebSocket());
  }

  /**
   * Method under test: {@link Uri#create(Uri, String)}
   */
  @Test
  void testCreate20() {
    // Arrange, Act and Assert
    assertThrows(IllegalArgumentException.class,
        () -> Uri
            .create(
                new Uri("https://example.org/example", "https://example.org/example", "https://example.org/example",
                    8080, "https://example.org/example", "https://example.org/example", "https://example.org/example"),
                "//"));
  }

  /**
   * Method under test: {@link Uri#create(Uri, String)}
   */
  @Test
  void testCreate21() {
    // Arrange
    Uri context = new Uri("https://example.org/example", "https://example.org/example", "https://example.org/example",
        8080, "https://example.org/example", "https://example.org/example", "https://example.org/example");

    // Act and Assert
    assertEquals(context, Uri.create(context, ""));
  }

  /**
   * Method under test: {@link Uri#create(Uri, String)}
   */
  @Test
  void testCreate22() throws URISyntaxException {
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
    assertNull(actualCreateResult.getFragment());
    assertNull(actualCreateResult.getQuery());
    assertNull(actualCreateResult.getUserInfo());
    assertEquals(-1, actualCreateResult.getPort());
    assertEquals(443, actualCreateResult.getExplicitPort());
    assertEquals(443, actualCreateResult.getSchemeDefaultPort());
    assertFalse(actualCreateResult.isWebSocket());
    assertTrue(actualCreateResult.isSecured());
    assertEquals(Uri.HTTPS, actualCreateResult.getScheme());
  }

  /**
   * Method under test: {@link Uri#create(Uri, String)}
   */
  @Test
  void testCreate23() {
    // Arrange, Act and Assert
    assertThrows(IllegalArgumentException.class, () -> Uri.create(null, "originalUrl"));
  }

  /**
   * Method under test: {@link Uri#create(Uri, String)}
   */
  @Test
  void testCreate24() throws URISyntaxException {
    // Arrange and Act
    Uri actualCreateResult = Uri.create(
        new Uri(Uri.WSS, "https://example.org/example", "https://example.org/example", 8080,
            "https://example.org/example", "https://example.org/example", "https://example.org/example"),
        "originalUrl");

    // Assert
    assertEquals("https://example.org/example", actualCreateResult.getHost());
    assertEquals("https://example.org/example", actualCreateResult.getUserInfo());
    assertEquals("https://example.org/example:8080", actualCreateResult.getAuthority());
    assertEquals("https://example.org/originalUrl", actualCreateResult.getNonEmptyPath());
    assertEquals("https://example.org/originalUrl", actualCreateResult.getPath());
    assertEquals("wss://https://example.org/example:8080", actualCreateResult.getBaseUrl());
    assertEquals("wss://https://example.org/example@https://example.org/example:8080https://example.org/originalUrl",
        actualCreateResult.toJavaNetURI().toString());
    assertNull(actualCreateResult.getFragment());
    assertNull(actualCreateResult.getQuery());
    assertEquals(443, actualCreateResult.getSchemeDefaultPort());
    assertEquals(8080, actualCreateResult.getExplicitPort());
    assertEquals(8080, actualCreateResult.getPort());
    assertTrue(actualCreateResult.isSecured());
    assertTrue(actualCreateResult.isWebSocket());
    assertEquals(Uri.WSS, actualCreateResult.getScheme());
  }

  /**
   * Method under test: {@link Uri#create(Uri, String)}
   */
  @Test
  void testCreate25() throws URISyntaxException {
    // Arrange and Act
    Uri actualCreateResult = Uri.create(
        new Uri(Uri.WS, "https://example.org/example", "https://example.org/example", 8080,
            "https://example.org/example", "https://example.org/example", "https://example.org/example"),
        "originalUrl");

    // Assert
    assertEquals("https://example.org/example", actualCreateResult.getHost());
    assertEquals("https://example.org/example", actualCreateResult.getUserInfo());
    assertEquals("https://example.org/example:8080", actualCreateResult.getAuthority());
    assertEquals("https://example.org/originalUrl", actualCreateResult.getNonEmptyPath());
    assertEquals("https://example.org/originalUrl", actualCreateResult.getPath());
    assertEquals("ws://https://example.org/example:8080", actualCreateResult.getBaseUrl());
    assertEquals("ws://https://example.org/example@https://example.org/example:8080https://example.org/originalUrl",
        actualCreateResult.toJavaNetURI().toString());
    assertNull(actualCreateResult.getFragment());
    assertNull(actualCreateResult.getQuery());
    assertEquals(80, actualCreateResult.getSchemeDefaultPort());
    assertEquals(8080, actualCreateResult.getExplicitPort());
    assertEquals(8080, actualCreateResult.getPort());
    assertFalse(actualCreateResult.isSecured());
    assertTrue(actualCreateResult.isWebSocket());
    assertEquals(Uri.WS, actualCreateResult.getScheme());
  }

  /**
   * Method under test: {@link Uri#create(Uri, String)}
   */
  @Test
  void testCreate26() throws URISyntaxException {
    // Arrange and Act
    Uri actualCreateResult = Uri
        .create(new Uri("https://example.org/example", "https://example.org/example", "https://example.org/example",
            8080, "originalUrl", "https://example.org/example", "https://example.org/example"), "originalUrl");

    // Assert
    assertEquals("https://example.org/example", actualCreateResult.getHost());
    assertEquals("https://example.org/example", actualCreateResult.getScheme());
    assertEquals("https://example.org/example", actualCreateResult.getUserInfo());
    assertEquals("https://example.org/example://https://example.org/example:8080", actualCreateResult.getBaseUrl());
    assertEquals(
        "https://example.org/example://https://example.org/example@https://example.org/example" + ":8080originalUrl",
        actualCreateResult.toJavaNetURI().toString());
    assertEquals("https://example.org/example:8080", actualCreateResult.getAuthority());
    assertEquals("originalUrl", actualCreateResult.getNonEmptyPath());
    assertEquals("originalUrl", actualCreateResult.getPath());
    assertNull(actualCreateResult.getFragment());
    assertNull(actualCreateResult.getQuery());
    assertEquals(80, actualCreateResult.getSchemeDefaultPort());
    assertEquals(8080, actualCreateResult.getExplicitPort());
    assertEquals(8080, actualCreateResult.getPort());
    assertFalse(actualCreateResult.isSecured());
    assertFalse(actualCreateResult.isWebSocket());
  }

  /**
   * Method under test: {@link Uri#create(Uri, String)}
   */
  @Test
  void testCreate27() throws URISyntaxException {
    // Arrange and Act
    Uri actualCreateResult = Uri.create(new Uri("https://example.org/example", "https://example.org/example",
        "https://example.org/example", 8080, "/../", "https://example.org/example", "https://example.org/example"),
        "originalUrl");

    // Assert
    assertEquals("/../originalUrl", actualCreateResult.getNonEmptyPath());
    assertEquals("/../originalUrl", actualCreateResult.getPath());
    assertEquals("https://example.org/example", actualCreateResult.getHost());
    assertEquals("https://example.org/example", actualCreateResult.getScheme());
    assertEquals("https://example.org/example", actualCreateResult.getUserInfo());
    assertEquals("https://example.org/example://https://example.org/example:8080", actualCreateResult.getBaseUrl());
    assertEquals("https://example.org/example://https://example.org/example@https://example.org/example:8080/.."
        + "/originalUrl", actualCreateResult.toJavaNetURI().toString());
    assertEquals("https://example.org/example:8080", actualCreateResult.getAuthority());
    assertNull(actualCreateResult.getFragment());
    assertNull(actualCreateResult.getQuery());
    assertEquals(80, actualCreateResult.getSchemeDefaultPort());
    assertEquals(8080, actualCreateResult.getExplicitPort());
    assertEquals(8080, actualCreateResult.getPort());
    assertFalse(actualCreateResult.isSecured());
    assertFalse(actualCreateResult.isWebSocket());
  }

  /**
   * Method under test: {@link Uri#toJavaNetURI()}
   */
  @Test
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
   * Method under test: {@link Uri#getExplicitPort()}
   */
  @Test
  void testGetExplicitPort() {
    // Arrange, Act and Assert
    assertEquals(8080,
        (new Uri("https://example.org/example", "https://example.org/example", "https://example.org/example", 8080,
            "https://example.org/example", "https://example.org/example", "https://example.org/example"))
                .getExplicitPort());
    assertEquals(80,
        (new Uri("https://example.org/example", "https://example.org/example", "https://example.org/example", -1,
            "https://example.org/example", "https://example.org/example", "https://example.org/example"))
                .getExplicitPort());
    assertEquals(
        443, Uri
            .create(
                new Uri("https://example.org/example", "https://example.org/example", "https://example.org/example",
                    8080, "https://example.org/example", "https://example.org/example", "https://example.org/example"),
                "https://example.org/example")
            .getExplicitPort());
  }

  /**
   * Method under test: {@link Uri#getSchemeDefaultPort()}
   */
  @Test
  void testGetSchemeDefaultPort() {
    // Arrange, Act and Assert
    assertEquals(80,
        (new Uri("https://example.org/example", "https://example.org/example", "https://example.org/example", 8080,
            "https://example.org/example", "https://example.org/example", "https://example.org/example"))
                .getSchemeDefaultPort());
    assertEquals(443,
        (new Uri(Uri.HTTPS, "https://example.org/example", "https://example.org/example", 8080,
            "https://example.org/example", "https://example.org/example", "https://example.org/example"))
                .getSchemeDefaultPort());
  }

  /**
   * Method under test: {@link Uri#toUrl()}
   */
  @Test
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
   * Method under test: {@link Uri#toBaseUrl()}
   */
  @Test
  void testToBaseUrl() {
    // Arrange, Act and Assert
    assertEquals("https://example.org/example://https://example.org/example:8080https://example.org/example",
        (new Uri("https://example.org/example", "https://example.org/example", "https://example.org/example", 8080,
            "https://example.org/example", "https://example.org/example", "https://example.org/example")).toBaseUrl());
    assertEquals("https://https://example.org/example:8080https://example.org/example",
        (new Uri(Uri.HTTPS, "https://example.org/example", "https://example.org/example", 8080,
            "https://example.org/example", "https://example.org/example", "https://example.org/example")).toBaseUrl());
    assertEquals("https://example.org/example://https://example.org/examplehttps://example.org/example",
        (new Uri("https://example.org/example", "https://example.org/example", "https://example.org/example", -1,
            "https://example.org/example", "https://example.org/example", "https://example.org/example")).toBaseUrl());
    assertEquals("https://example.org/example://https://example.org/examplehttps://example.org/example",
        (new Uri("https://example.org/example", "https://example.org/example", "https://example.org/example", 80,
            "https://example.org/example", "https://example.org/example", "https://example.org/example")).toBaseUrl());
    assertEquals("https://example.org/example://https://example.org/example:8080",
        (new Uri("https://example.org/example", "https://example.org/example", "https://example.org/example", 8080, "",
            "https://example.org/example", "https://example.org/example")).toBaseUrl());
  }

  /**
   * Method under test: {@link Uri#toRelativeUrl()}
   */
  @Test
  void testToRelativeUrl() {
    // Arrange, Act and Assert
    assertEquals("https://example.org/example?https://example.org/example",
        (new Uri("https://example.org/example", "https://example.org/example", "https://example.org/example", 8080,
            "https://example.org/example", "https://example.org/example", "https://example.org/example"))
                .toRelativeUrl());
    assertEquals("/?https://example.org/example",
        (new Uri("https://example.org/example", "https://example.org/example", "https://example.org/example", 8080,
            null, "https://example.org/example", "https://example.org/example")).toRelativeUrl());
    assertEquals("/?https://example.org/example",
        (new Uri("https://example.org/example", "https://example.org/example", "https://example.org/example", 8080, "",
            "https://example.org/example", "https://example.org/example")).toRelativeUrl());
    assertEquals("https://example.org/example",
        (new Uri("https://example.org/example", "https://example.org/example", "https://example.org/example", 8080,
            "https://example.org/example", null, "https://example.org/example")).toRelativeUrl());
  }

  /**
   * Method under test: {@link Uri#toFullUrl()}
   */
  @Test
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
   * Method under test: {@link Uri#getBaseUrl()}
   */
  @Test
  void testGetBaseUrl() {
    // Arrange, Act and Assert
    assertEquals("https://example.org/example://https://example.org/example:8080",
        (new Uri("https://example.org/example", "https://example.org/example", "https://example.org/example", 8080,
            "https://example.org/example", "https://example.org/example", "https://example.org/example")).getBaseUrl());
    assertEquals("https://example.org/example://https://example.org/example:80",
        (new Uri("https://example.org/example", "https://example.org/example", "https://example.org/example", -1,
            "https://example.org/example", "https://example.org/example", "https://example.org/example")).getBaseUrl());
    assertEquals(
        "https://example.org:443", Uri
            .create(
                new Uri("https://example.org/example", "https://example.org/example", "https://example.org/example",
                    8080, "https://example.org/example", "https://example.org/example", "https://example.org/example"),
                "https://example.org/example")
            .getBaseUrl());
  }

  /**
   * Method under test: {@link Uri#getAuthority()}
   */
  @Test
  void testGetAuthority() {
    // Arrange, Act and Assert
    assertEquals("https://example.org/example:8080",
        (new Uri("https://example.org/example", "https://example.org/example", "https://example.org/example", 8080,
            "https://example.org/example", "https://example.org/example", "https://example.org/example"))
                .getAuthority());
    assertEquals("https://example.org/example:80",
        (new Uri("https://example.org/example", "https://example.org/example", "https://example.org/example", -1,
            "https://example.org/example", "https://example.org/example", "https://example.org/example"))
                .getAuthority());
    assertEquals(
        "example.org:443", Uri
            .create(
                new Uri("https://example.org/example", "https://example.org/example", "https://example.org/example",
                    8080, "https://example.org/example", "https://example.org/example", "https://example.org/example"),
                "https://example.org/example")
            .getAuthority());
  }

  /**
   * Method under test: {@link Uri#isSameBase(Uri)}
   */
  @Test
  void testIsSameBase() {
    // Arrange
    Uri uri = new Uri("https://example.org/example", "https://example.org/example", "https://example.org/example", 8080,
        "https://example.org/example", "https://example.org/example", "https://example.org/example");

    // Act and Assert
    assertTrue(uri
        .isSameBase(new Uri("https://example.org/example", "https://example.org/example", "https://example.org/example",
            8080, "https://example.org/example", "https://example.org/example", "https://example.org/example")));
  }

  /**
   * Method under test: {@link Uri#isSameBase(Uri)}
   */
  @Test
  void testIsSameBase2() {
    // Arrange
    Uri uri = new Uri("Scheme", "https://example.org/example", "https://example.org/example", 8080,
        "https://example.org/example", "https://example.org/example", "https://example.org/example");

    // Act and Assert
    assertFalse(uri
        .isSameBase(new Uri("https://example.org/example", "https://example.org/example", "https://example.org/example",
            8080, "https://example.org/example", "https://example.org/example", "https://example.org/example")));
  }

  /**
   * Method under test: {@link Uri#isSameBase(Uri)}
   */
  @Test
  void testIsSameBase3() {
    // Arrange
    Uri uri = new Uri("https://example.org/example", "https://example.org/example", "localhost", 8080,
        "https://example.org/example", "https://example.org/example", "https://example.org/example");

    // Act and Assert
    assertFalse(uri
        .isSameBase(new Uri("https://example.org/example", "https://example.org/example", "https://example.org/example",
            8080, "https://example.org/example", "https://example.org/example", "https://example.org/example")));
  }

  /**
   * Method under test: {@link Uri#isSameBase(Uri)}
   */
  @Test
  void testIsSameBase4() {
    // Arrange
    Uri uri = new Uri("https://example.org/example", "https://example.org/example", "https://example.org/example", 1,
        "https://example.org/example", "https://example.org/example", "https://example.org/example");

    // Act and Assert
    assertFalse(uri
        .isSameBase(new Uri("https://example.org/example", "https://example.org/example", "https://example.org/example",
            8080, "https://example.org/example", "https://example.org/example", "https://example.org/example")));
  }

  /**
   * Method under test: {@link Uri#isSameBase(Uri)}
   */
  @Test
  void testIsSameBase5() {
    // Arrange
    Uri uri = new Uri("https://example.org/example", "https://example.org/example", "https://example.org/example", -1,
        "https://example.org/example", "https://example.org/example", "https://example.org/example");

    // Act and Assert
    assertFalse(uri
        .isSameBase(new Uri("https://example.org/example", "https://example.org/example", "https://example.org/example",
            8080, "https://example.org/example", "https://example.org/example", "https://example.org/example")));
  }

  /**
   * Method under test: {@link Uri#getNonEmptyPath()}
   */
  @Test
  void testGetNonEmptyPath() {
    // Arrange, Act and Assert
    assertEquals("https://example.org/example",
        (new Uri("https://example.org/example", "https://example.org/example", "https://example.org/example", 8080,
            "https://example.org/example", "https://example.org/example", "https://example.org/example"))
                .getNonEmptyPath());
    assertEquals("/",
        (new Uri("https://example.org/example", "https://example.org/example", "https://example.org/example", 8080, "",
            "https://example.org/example", "https://example.org/example")).getNonEmptyPath());
  }

  /**
   * Method under test: {@link Uri#withNewScheme(String)}
   */
  @Test
  void testWithNewScheme() {
    // Arrange
    Uri uri = new Uri("https://example.org/example", "https://example.org/example", "https://example.org/example", 8080,
        "https://example.org/example", "https://example.org/example", "https://example.org/example");

    // Act and Assert
    assertEquals(uri, uri.withNewScheme("https://example.org/example"));
  }

  /**
   * Method under test: {@link Uri#withNewScheme(String)}
   */
  @Test
  void testWithNewScheme2() throws URISyntaxException {
    // Arrange and Act
    Uri actualWithNewSchemeResult = (new Uri("https://example.org/example", "https://example.org/example",
        "https://example.org/example", 8080, "https://example.org/example", "https://example.org/example",
        "https://example.org/example")).withNewScheme(Uri.HTTPS);

    // Assert
    assertEquals("https://example.org/example", actualWithNewSchemeResult.getFragment());
    assertEquals("https://example.org/example", actualWithNewSchemeResult.getHost());
    assertEquals("https://example.org/example", actualWithNewSchemeResult.getNonEmptyPath());
    assertEquals("https://example.org/example", actualWithNewSchemeResult.getPath());
    assertEquals("https://example.org/example", actualWithNewSchemeResult.getQuery());
    assertEquals("https://example.org/example", actualWithNewSchemeResult.getUserInfo());
    assertEquals("https://example.org/example:8080", actualWithNewSchemeResult.getAuthority());
    assertEquals("https://https://example.org/example:8080", actualWithNewSchemeResult.getBaseUrl());
    assertEquals("https://https://example.org/example@https://example.org/example:8080https://example.org/example?https"
        + "://example.org/example", actualWithNewSchemeResult.toJavaNetURI().toString());
    assertEquals(443, actualWithNewSchemeResult.getSchemeDefaultPort());
    assertEquals(8080, actualWithNewSchemeResult.getExplicitPort());
    assertEquals(8080, actualWithNewSchemeResult.getPort());
    assertFalse(actualWithNewSchemeResult.isWebSocket());
    assertTrue(actualWithNewSchemeResult.isSecured());
    assertEquals(Uri.HTTPS, actualWithNewSchemeResult.getScheme());
  }

  /**
   * Method under test: {@link Uri#withNewScheme(String)}
   */
  @Test
  void testWithNewScheme3() throws URISyntaxException {
    // Arrange and Act
    Uri actualWithNewSchemeResult = (new Uri("https://example.org/example", "https://example.org/example",
        "https://example.org/example", 8080, "https://example.org/example", "https://example.org/example",
        "https://example.org/example")).withNewScheme(Uri.WSS);

    // Assert
    assertEquals("https://example.org/example", actualWithNewSchemeResult.getFragment());
    assertEquals("https://example.org/example", actualWithNewSchemeResult.getHost());
    assertEquals("https://example.org/example", actualWithNewSchemeResult.getNonEmptyPath());
    assertEquals("https://example.org/example", actualWithNewSchemeResult.getPath());
    assertEquals("https://example.org/example", actualWithNewSchemeResult.getQuery());
    assertEquals("https://example.org/example", actualWithNewSchemeResult.getUserInfo());
    assertEquals("https://example.org/example:8080", actualWithNewSchemeResult.getAuthority());
    assertEquals("wss://https://example.org/example:8080", actualWithNewSchemeResult.getBaseUrl());
    assertEquals("wss://https://example.org/example@https://example.org/example:8080https://example.org/example?https:"
        + "//example.org/example", actualWithNewSchemeResult.toJavaNetURI().toString());
    assertEquals(443, actualWithNewSchemeResult.getSchemeDefaultPort());
    assertEquals(8080, actualWithNewSchemeResult.getExplicitPort());
    assertEquals(8080, actualWithNewSchemeResult.getPort());
    assertTrue(actualWithNewSchemeResult.isSecured());
    assertTrue(actualWithNewSchemeResult.isWebSocket());
    assertEquals(Uri.WSS, actualWithNewSchemeResult.getScheme());
  }

  /**
   * Method under test: {@link Uri#withNewScheme(String)}
   */
  @Test
  void testWithNewScheme4() throws URISyntaxException {
    // Arrange and Act
    Uri actualWithNewSchemeResult = (new Uri("https://example.org/example", "https://example.org/example",
        "https://example.org/example", 8080, "https://example.org/example", "https://example.org/example",
        "https://example.org/example")).withNewScheme(Uri.WS);

    // Assert
    assertEquals("https://example.org/example", actualWithNewSchemeResult.getFragment());
    assertEquals("https://example.org/example", actualWithNewSchemeResult.getHost());
    assertEquals("https://example.org/example", actualWithNewSchemeResult.getNonEmptyPath());
    assertEquals("https://example.org/example", actualWithNewSchemeResult.getPath());
    assertEquals("https://example.org/example", actualWithNewSchemeResult.getQuery());
    assertEquals("https://example.org/example", actualWithNewSchemeResult.getUserInfo());
    assertEquals("https://example.org/example:8080", actualWithNewSchemeResult.getAuthority());
    assertEquals("ws://https://example.org/example:8080", actualWithNewSchemeResult.getBaseUrl());
    assertEquals("ws://https://example.org/example@https://example.org/example:8080https://example.org/example?https:/"
        + "/example.org/example", actualWithNewSchemeResult.toJavaNetURI().toString());
    assertEquals(80, actualWithNewSchemeResult.getSchemeDefaultPort());
    assertEquals(8080, actualWithNewSchemeResult.getExplicitPort());
    assertEquals(8080, actualWithNewSchemeResult.getPort());
    assertFalse(actualWithNewSchemeResult.isSecured());
    assertTrue(actualWithNewSchemeResult.isWebSocket());
    assertEquals(Uri.WS, actualWithNewSchemeResult.getScheme());
  }

  /**
   * Method under test: {@link Uri#withNewQuery(String)}
   */
  @Test
  void testWithNewQuery() {
    // Arrange
    Uri uri = new Uri("https://example.org/example", "https://example.org/example", "https://example.org/example", 8080,
        "https://example.org/example", "https://example.org/example", "https://example.org/example");

    // Act and Assert
    assertEquals(uri, uri.withNewQuery("https://example.org/example"));
  }

  /**
   * Method under test: {@link Uri#withNewQuery(String)}
   */
  @Test
  void testWithNewQuery2() {
    // Arrange
    Uri uri = new Uri(Uri.HTTPS, "https://example.org/example", "https://example.org/example", 8080,
        "https://example.org/example", "https://example.org/example", "https://example.org/example");

    // Act and Assert
    assertEquals(uri, uri.withNewQuery("https://example.org/example"));
  }

  /**
   * Method under test: {@link Uri#withNewQuery(String)}
   */
  @Test
  void testWithNewQuery3() {
    // Arrange
    Uri uri = new Uri(Uri.WSS, "https://example.org/example", "https://example.org/example", 8080,
        "https://example.org/example", "https://example.org/example", "https://example.org/example");

    // Act and Assert
    assertEquals(uri, uri.withNewQuery("https://example.org/example"));
  }

  /**
   * Method under test: {@link Uri#withNewQuery(String)}
   */
  @Test
  void testWithNewQuery4() {
    // Arrange
    Uri uri = new Uri(Uri.WS, "https://example.org/example", "https://example.org/example", 8080,
        "https://example.org/example", "https://example.org/example", "https://example.org/example");

    // Act and Assert
    assertEquals(uri, uri.withNewQuery("https://example.org/example"));
  }

  /**
   * Methods under test:
   * <ul>
   *   <li>{@link Uri#equals(Object)}
   *   <li>{@link Uri#hashCode()}
   * </ul>
   */
  @Test
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
   * Methods under test:
   * <ul>
   *   <li>{@link Uri#equals(Object)}
   *   <li>{@link Uri#hashCode()}
   * </ul>
   */
  @Test
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
   * Method under test: {@link Uri#validateSupportedScheme(Uri)}
   */
  @Test
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

  /**
   * Method under test: {@link Uri#equals(Object)}
   */
  @Test
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
   * Method under test: {@link Uri#equals(Object)}
   */
  @Test
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
   * Method under test: {@link Uri#equals(Object)}
   */
  @Test
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
   * Method under test: {@link Uri#equals(Object)}
   */
  @Test
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
   * Method under test: {@link Uri#equals(Object)}
   */
  @Test
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
   * Method under test: {@link Uri#equals(Object)}
   */
  @Test
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
   * Method under test: {@link Uri#equals(Object)}
   */
  @Test
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
   * Method under test: {@link Uri#equals(Object)}
   */
  @Test
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
   * Method under test: {@link Uri#equals(Object)}
   */
  @Test
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
   * Method under test: {@link Uri#equals(Object)}
   */
  @Test
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
   * Method under test: {@link Uri#equals(Object)}
   */
  @Test
  void testEquals_whenOtherIsNull_thenReturnNotEqual() {
    // Arrange, Act and Assert
    assertNotEquals(new Uri("https://example.org/example", "https://example.org/example", "https://example.org/example",
        8080, "https://example.org/example", "https://example.org/example", "https://example.org/example"), null);
  }

  /**
   * Method under test: {@link Uri#equals(Object)}
   */
  @Test
  void testEquals_whenOtherIsWrongType_thenReturnNotEqual() {
    // Arrange, Act and Assert
    assertNotEquals(
        new Uri("https://example.org/example", "https://example.org/example", "https://example.org/example", 8080,
            "https://example.org/example", "https://example.org/example", "https://example.org/example"),
        "Different type to Uri");
  }

  /**
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
   * Method under test:
   * {@link Uri#Uri(String, String, String, int, String, String, String)}
   */
  @Test
  void testNewUri() throws URISyntaxException {
    // Arrange and Act
    Uri actualUri = new Uri("https://example.org/example", "https://example.org/example", "https://example.org/example",
        8080, "https://example.org/example", "https://example.org/example", "https://example.org/example");

    // Assert
    assertEquals("https://example.org/example", actualUri.getFragment());
    assertEquals("https://example.org/example", actualUri.getHost());
    assertEquals("https://example.org/example", actualUri.getNonEmptyPath());
    assertEquals("https://example.org/example", actualUri.getPath());
    assertEquals("https://example.org/example", actualUri.getQuery());
    assertEquals("https://example.org/example", actualUri.getScheme());
    assertEquals("https://example.org/example", actualUri.getUserInfo());
    assertEquals("https://example.org/example://https://example.org/example:8080", actualUri.getBaseUrl());
    assertEquals("https://example.org/example://https://example.org/example@https://example.org/example:8080https:/"
        + "/example.org/example?https://example.org/example", actualUri.toJavaNetURI().toString());
    assertEquals("https://example.org/example:8080", actualUri.getAuthority());
    assertEquals(80, actualUri.getSchemeDefaultPort());
    assertEquals(8080, actualUri.getExplicitPort());
    assertEquals(8080, actualUri.getPort());
    assertFalse(actualUri.isSecured());
    assertFalse(actualUri.isWebSocket());
  }

  /**
   * Method under test:
   * {@link Uri#Uri(String, String, String, int, String, String, String)}
   */
  @Test
  void testNewUri2() throws URISyntaxException {
    // Arrange and Act
    Uri actualUri = new Uri(Uri.HTTPS, "https://example.org/example", "https://example.org/example", 8080,
        "https://example.org/example", "https://example.org/example", "https://example.org/example");

    // Assert
    assertEquals("https://example.org/example", actualUri.getFragment());
    assertEquals("https://example.org/example", actualUri.getHost());
    assertEquals("https://example.org/example", actualUri.getNonEmptyPath());
    assertEquals("https://example.org/example", actualUri.getPath());
    assertEquals("https://example.org/example", actualUri.getQuery());
    assertEquals("https://example.org/example", actualUri.getUserInfo());
    assertEquals("https://example.org/example:8080", actualUri.getAuthority());
    assertEquals("https://https://example.org/example:8080", actualUri.getBaseUrl());
    assertEquals("https://https://example.org/example@https://example.org/example:8080https://example.org/example?https"
        + "://example.org/example", actualUri.toJavaNetURI().toString());
    assertEquals(443, actualUri.getSchemeDefaultPort());
    assertEquals(8080, actualUri.getExplicitPort());
    assertEquals(8080, actualUri.getPort());
    assertFalse(actualUri.isWebSocket());
    assertTrue(actualUri.isSecured());
    assertEquals(Uri.HTTPS, actualUri.getScheme());
  }

  /**
   * Method under test:
   * {@link Uri#Uri(String, String, String, int, String, String, String)}
   */
  @Test
  void testNewUri3() throws URISyntaxException {
    // Arrange and Act
    Uri actualUri = new Uri(Uri.WSS, "https://example.org/example", "https://example.org/example", 8080,
        "https://example.org/example", "https://example.org/example", "https://example.org/example");

    // Assert
    assertEquals("https://example.org/example", actualUri.getFragment());
    assertEquals("https://example.org/example", actualUri.getHost());
    assertEquals("https://example.org/example", actualUri.getNonEmptyPath());
    assertEquals("https://example.org/example", actualUri.getPath());
    assertEquals("https://example.org/example", actualUri.getQuery());
    assertEquals("https://example.org/example", actualUri.getUserInfo());
    assertEquals("https://example.org/example:8080", actualUri.getAuthority());
    assertEquals("wss://https://example.org/example:8080", actualUri.getBaseUrl());
    assertEquals("wss://https://example.org/example@https://example.org/example:8080https://example.org/example?https:"
        + "//example.org/example", actualUri.toJavaNetURI().toString());
    assertEquals(443, actualUri.getSchemeDefaultPort());
    assertEquals(8080, actualUri.getExplicitPort());
    assertEquals(8080, actualUri.getPort());
    assertTrue(actualUri.isSecured());
    assertTrue(actualUri.isWebSocket());
    assertEquals(Uri.WSS, actualUri.getScheme());
  }

  /**
   * Method under test:
   * {@link Uri#Uri(String, String, String, int, String, String, String)}
   */
  @Test
  void testNewUri4() throws URISyntaxException {
    // Arrange and Act
    Uri actualUri = new Uri(Uri.WS, "https://example.org/example", "https://example.org/example", 8080,
        "https://example.org/example", "https://example.org/example", "https://example.org/example");

    // Assert
    assertEquals("https://example.org/example", actualUri.getFragment());
    assertEquals("https://example.org/example", actualUri.getHost());
    assertEquals("https://example.org/example", actualUri.getNonEmptyPath());
    assertEquals("https://example.org/example", actualUri.getPath());
    assertEquals("https://example.org/example", actualUri.getQuery());
    assertEquals("https://example.org/example", actualUri.getUserInfo());
    assertEquals("https://example.org/example:8080", actualUri.getAuthority());
    assertEquals("ws://https://example.org/example:8080", actualUri.getBaseUrl());
    assertEquals("ws://https://example.org/example@https://example.org/example:8080https://example.org/example?https:/"
        + "/example.org/example", actualUri.toJavaNetURI().toString());
    assertEquals(80, actualUri.getSchemeDefaultPort());
    assertEquals(8080, actualUri.getExplicitPort());
    assertEquals(8080, actualUri.getPort());
    assertFalse(actualUri.isSecured());
    assertTrue(actualUri.isWebSocket());
    assertEquals(Uri.WS, actualUri.getScheme());
  }
}
