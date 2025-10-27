package org.asynchttpclient.util;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import org.junit.jupiter.api.Test;

class Utf8UrlEncoderDiffblueTest {
  /**
   * Method under test: {@link Utf8UrlEncoder#encodePath(String)}
   */
  @Test
  void testEncodePath() {
    // Arrange, Act and Assert
    assertEquals("https://example.org/example", Utf8UrlEncoder.encodePath("https://example.org/example"));
  }

  /**
   * Method under test:
   * {@link Utf8UrlEncoder#encodeAndAppendQuery(StringBuilder, String)}
   */
  @Test
  void testEncodeAndAppendQuery() {
    // Arrange
    StringBuilder sb = new StringBuilder("foo");

    // Act
    StringBuilder actualEncodeAndAppendQueryResult = Utf8UrlEncoder.encodeAndAppendQuery(sb,
        "https://example.org/example");

    // Assert
    assertEquals("foohttps://example.org/example", sb.toString());
    assertEquals("foohttps://example.org/example", actualEncodeAndAppendQueryResult.toString());
  }

  /**
   * Method under test: {@link Utf8UrlEncoder#encodeQueryElement(String)}
   */
  @Test
  void testEncodeQueryElement() {
    // Arrange, Act and Assert
    assertEquals("https%3A%2F%2Fexample.org%2Fexample",
        Utf8UrlEncoder.encodeQueryElement("https://example.org/example"));
  }

  /**
   * Method under test:
   * {@link Utf8UrlEncoder#encodeAndAppendQueryElement(StringBuilder, CharSequence)}
   */
  @Test
  void testEncodeAndAppendQueryElement() {
    // Arrange
    StringBuilder sb = new StringBuilder("foo");

    // Act
    StringBuilder actualEncodeAndAppendQueryElementResult = Utf8UrlEncoder.encodeAndAppendQueryElement(sb,
        AuthenticatorUtils.NEGOTIATE);

    // Assert
    assertEquals("fooNegotiate", sb.toString());
    assertEquals("fooNegotiate", actualEncodeAndAppendQueryElementResult.toString());
  }

  /**
   * Method under test:
   * {@link Utf8UrlEncoder#encodeAndAppendQueryElement(StringBuilder, CharSequence)}
   */
  @Test
  void testEncodeAndAppendQueryElement2() {
    // Arrange
    StringBuilder sb = new StringBuilder("foo");

    // Act
    StringBuilder actualEncodeAndAppendQueryElementResult = Utf8UrlEncoder.encodeAndAppendQueryElement(sb,
        HttpUtils.ACCEPT_ALL_HEADER_VALUE);

    // Assert
    assertEquals("foo*%2F*", sb.toString());
    assertEquals("foo*%2F*", actualEncodeAndAppendQueryElementResult.toString());
  }

  /**
   * Method under test:
   * {@link Utf8UrlEncoder#encodeAndAppendFormElement(StringBuilder, CharSequence)}
   */
  @Test
  void testEncodeAndAppendFormElement() {
    // Arrange
    StringBuilder sb = new StringBuilder("foo");

    // Act
    StringBuilder actualEncodeAndAppendFormElementResult = Utf8UrlEncoder.encodeAndAppendFormElement(sb,
        AuthenticatorUtils.NEGOTIATE);

    // Assert
    assertEquals("fooNegotiate", sb.toString());
    assertEquals("fooNegotiate", actualEncodeAndAppendFormElementResult.toString());
  }

  /**
   * Method under test:
   * {@link Utf8UrlEncoder#encodeAndAppendFormElement(StringBuilder, CharSequence)}
   */
  @Test
  void testEncodeAndAppendFormElement2() {
    // Arrange
    StringBuilder sb = new StringBuilder("foo");

    // Act
    StringBuilder actualEncodeAndAppendFormElementResult = Utf8UrlEncoder.encodeAndAppendFormElement(sb,
        HttpUtils.ACCEPT_ALL_HEADER_VALUE);

    // Assert
    assertEquals("foo*%2F*", sb.toString());
    assertEquals("foo*%2F*", actualEncodeAndAppendFormElementResult.toString());
  }

  /**
   * Method under test: {@link Utf8UrlEncoder#percentEncodeQueryElement(String)}
   */
  @Test
  void testPercentEncodeQueryElement() {
    // Arrange, Act and Assert
    assertEquals("https%3A%2F%2Fexample.org%2Fexample",
        Utf8UrlEncoder.percentEncodeQueryElement("https://example.org/example"));
    assertNull(Utf8UrlEncoder.percentEncodeQueryElement(null));
  }

  /**
   * Method under test:
   * {@link Utf8UrlEncoder#encodeAndAppendPercentEncoded(StringBuilder, CharSequence)}
   */
  @Test
  void testEncodeAndAppendPercentEncoded() {
    // Arrange
    StringBuilder sb = new StringBuilder("foo");

    // Act
    StringBuilder actualEncodeAndAppendPercentEncodedResult = Utf8UrlEncoder.encodeAndAppendPercentEncoded(sb,
        AuthenticatorUtils.NEGOTIATE);

    // Assert
    assertEquals("fooNegotiate", sb.toString());
    assertEquals("fooNegotiate", actualEncodeAndAppendPercentEncodedResult.toString());
  }

  /**
   * Method under test:
   * {@link Utf8UrlEncoder#encodeAndAppendPercentEncoded(StringBuilder, CharSequence)}
   */
  @Test
  void testEncodeAndAppendPercentEncoded2() {
    // Arrange
    StringBuilder sb = new StringBuilder("foo");

    // Act
    StringBuilder actualEncodeAndAppendPercentEncodedResult = Utf8UrlEncoder.encodeAndAppendPercentEncoded(sb,
        HttpUtils.ACCEPT_ALL_HEADER_VALUE);

    // Assert
    assertEquals("foo%2A%2F%2A", sb.toString());
    assertEquals("foo%2A%2F%2A", actualEncodeAndAppendPercentEncodedResult.toString());
  }
}
