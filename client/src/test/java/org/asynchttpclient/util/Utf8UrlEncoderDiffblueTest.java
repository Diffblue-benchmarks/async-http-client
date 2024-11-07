package org.asynchttpclient.util;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class Utf8UrlEncoderDiffblueTest {
  /**
   * Test {@link Utf8UrlEncoder#encodePath(String)}.
   * <ul>
   *   <li>When {@code https://example.org/example}.</li>
   *   <li>Then return {@code https://example.org/example}.</li>
   * </ul>
   * <p>
   * Method under test: {@link Utf8UrlEncoder#encodePath(String)}
   */
  @Test
  @DisplayName("Test encodePath(String); when 'https://example.org/example'; then return 'https://example.org/example'")
  void testEncodePath_whenHttpsExampleOrgExample_thenReturnHttpsExampleOrgExample() {
    // Arrange, Act and Assert
    assertEquals("https://example.org/example", Utf8UrlEncoder.encodePath("https://example.org/example"));
  }

  /**
   * Test {@link Utf8UrlEncoder#encodeAndAppendQuery(StringBuilder, String)}.
   * <p>
   * Method under test:
   * {@link Utf8UrlEncoder#encodeAndAppendQuery(StringBuilder, String)}
   */
  @Test
  @DisplayName("Test encodeAndAppendQuery(StringBuilder, String)")
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
   * Test {@link Utf8UrlEncoder#encodeQueryElement(String)}.
   * <p>
   * Method under test: {@link Utf8UrlEncoder#encodeQueryElement(String)}
   */
  @Test
  @DisplayName("Test encodeQueryElement(String)")
  void testEncodeQueryElement() {
    // Arrange, Act and Assert
    assertEquals("https%3A%2F%2Fexample.org%2Fexample",
        Utf8UrlEncoder.encodeQueryElement("https://example.org/example"));
  }

  /**
   * Test
   * {@link Utf8UrlEncoder#encodeAndAppendQueryElement(StringBuilder, CharSequence)}.
   * <ul>
   *   <li>Then {@link StringBuilder#StringBuilder(String)} with {@code foo}
   * toString is {@code foo*%2F*}.</li>
   * </ul>
   * <p>
   * Method under test:
   * {@link Utf8UrlEncoder#encodeAndAppendQueryElement(StringBuilder, CharSequence)}
   */
  @Test
  @DisplayName("Test encodeAndAppendQueryElement(StringBuilder, CharSequence); then StringBuilder(String) with 'foo' toString is 'foo*%2F*'")
  void testEncodeAndAppendQueryElement_thenStringBuilderWithFooToStringIsFoo2f() {
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
   * Test
   * {@link Utf8UrlEncoder#encodeAndAppendQueryElement(StringBuilder, CharSequence)}.
   * <ul>
   *   <li>Then {@link StringBuilder#StringBuilder(String)} with {@code foo}
   * toString is {@code fooNegotiate}.</li>
   * </ul>
   * <p>
   * Method under test:
   * {@link Utf8UrlEncoder#encodeAndAppendQueryElement(StringBuilder, CharSequence)}
   */
  @Test
  @DisplayName("Test encodeAndAppendQueryElement(StringBuilder, CharSequence); then StringBuilder(String) with 'foo' toString is 'fooNegotiate'")
  void testEncodeAndAppendQueryElement_thenStringBuilderWithFooToStringIsFooNegotiate() {
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
   * Test
   * {@link Utf8UrlEncoder#encodeAndAppendFormElement(StringBuilder, CharSequence)}.
   * <ul>
   *   <li>Then {@link StringBuilder#StringBuilder(String)} with {@code foo}
   * toString is {@code foo*%2F*}.</li>
   * </ul>
   * <p>
   * Method under test:
   * {@link Utf8UrlEncoder#encodeAndAppendFormElement(StringBuilder, CharSequence)}
   */
  @Test
  @DisplayName("Test encodeAndAppendFormElement(StringBuilder, CharSequence); then StringBuilder(String) with 'foo' toString is 'foo*%2F*'")
  void testEncodeAndAppendFormElement_thenStringBuilderWithFooToStringIsFoo2f() {
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
   * Test
   * {@link Utf8UrlEncoder#encodeAndAppendFormElement(StringBuilder, CharSequence)}.
   * <ul>
   *   <li>Then {@link StringBuilder#StringBuilder(String)} with {@code foo}
   * toString is {@code fooNegotiate}.</li>
   * </ul>
   * <p>
   * Method under test:
   * {@link Utf8UrlEncoder#encodeAndAppendFormElement(StringBuilder, CharSequence)}
   */
  @Test
  @DisplayName("Test encodeAndAppendFormElement(StringBuilder, CharSequence); then StringBuilder(String) with 'foo' toString is 'fooNegotiate'")
  void testEncodeAndAppendFormElement_thenStringBuilderWithFooToStringIsFooNegotiate() {
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
   * Test {@link Utf8UrlEncoder#percentEncodeQueryElement(String)}.
   * <ul>
   *   <li>Then return {@code https%3A%2F%2Fexample.org%2Fexample}.</li>
   * </ul>
   * <p>
   * Method under test: {@link Utf8UrlEncoder#percentEncodeQueryElement(String)}
   */
  @Test
  @DisplayName("Test percentEncodeQueryElement(String); then return 'https%3A%2F%2Fexample.org%2Fexample'")
  void testPercentEncodeQueryElement_thenReturnHttps3a2f2FexampleOrg2Fexample() {
    // Arrange, Act and Assert
    assertEquals("https%3A%2F%2Fexample.org%2Fexample",
        Utf8UrlEncoder.percentEncodeQueryElement("https://example.org/example"));
  }

  /**
   * Test {@link Utf8UrlEncoder#percentEncodeQueryElement(String)}.
   * <ul>
   *   <li>When {@code null}.</li>
   *   <li>Then return {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link Utf8UrlEncoder#percentEncodeQueryElement(String)}
   */
  @Test
  @DisplayName("Test percentEncodeQueryElement(String); when 'null'; then return 'null'")
  void testPercentEncodeQueryElement_whenNull_thenReturnNull() {
    // Arrange, Act and Assert
    assertNull(Utf8UrlEncoder.percentEncodeQueryElement(null));
  }

  /**
   * Test
   * {@link Utf8UrlEncoder#encodeAndAppendPercentEncoded(StringBuilder, CharSequence)}.
   * <ul>
   *   <li>Then {@link StringBuilder#StringBuilder(String)} with {@code foo}
   * toString is {@code foo%2A%2F%2A}.</li>
   * </ul>
   * <p>
   * Method under test:
   * {@link Utf8UrlEncoder#encodeAndAppendPercentEncoded(StringBuilder, CharSequence)}
   */
  @Test
  @DisplayName("Test encodeAndAppendPercentEncoded(StringBuilder, CharSequence); then StringBuilder(String) with 'foo' toString is 'foo%2A%2F%2A'")
  void testEncodeAndAppendPercentEncoded_thenStringBuilderWithFooToStringIsFoo2a2f2a() {
    // Arrange
    StringBuilder sb = new StringBuilder("foo");

    // Act
    StringBuilder actualEncodeAndAppendPercentEncodedResult = Utf8UrlEncoder.encodeAndAppendPercentEncoded(sb,
        HttpUtils.ACCEPT_ALL_HEADER_VALUE);

    // Assert
    assertEquals("foo%2A%2F%2A", sb.toString());
    assertEquals("foo%2A%2F%2A", actualEncodeAndAppendPercentEncodedResult.toString());
  }

  /**
   * Test
   * {@link Utf8UrlEncoder#encodeAndAppendPercentEncoded(StringBuilder, CharSequence)}.
   * <ul>
   *   <li>Then {@link StringBuilder#StringBuilder(String)} with {@code foo}
   * toString is {@code fooNegotiate}.</li>
   * </ul>
   * <p>
   * Method under test:
   * {@link Utf8UrlEncoder#encodeAndAppendPercentEncoded(StringBuilder, CharSequence)}
   */
  @Test
  @DisplayName("Test encodeAndAppendPercentEncoded(StringBuilder, CharSequence); then StringBuilder(String) with 'foo' toString is 'fooNegotiate'")
  void testEncodeAndAppendPercentEncoded_thenStringBuilderWithFooToStringIsFooNegotiate() {
    // Arrange
    StringBuilder sb = new StringBuilder("foo");

    // Act
    StringBuilder actualEncodeAndAppendPercentEncodedResult = Utf8UrlEncoder.encodeAndAppendPercentEncoded(sb,
        AuthenticatorUtils.NEGOTIATE);

    // Assert
    assertEquals("fooNegotiate", sb.toString());
    assertEquals("fooNegotiate", actualEncodeAndAppendPercentEncodedResult.toString());
  }
}
