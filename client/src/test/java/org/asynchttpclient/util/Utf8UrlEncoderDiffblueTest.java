package org.asynchttpclient.util;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

class Utf8UrlEncoderDiffblueTest {
  /**
   * Test {@link Utf8UrlEncoder#encodePath(String)}.
   *
   * <ul>
   *   <li>When {@code https://example.org/example}.
   *   <li>Then return {@code https://example.org/example}.
   * </ul>
   *
   * <p>Method under test: {@link Utf8UrlEncoder#encodePath(String)}
   */
  @Test
  @DisplayName(
      "Test encodePath(String); when 'https://example.org/example'; then return 'https://example.org/example'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"String Utf8UrlEncoder.encodePath(String)"})
  void testEncodePath_whenHttpsExampleOrgExample_thenReturnHttpsExampleOrgExample() {
    // Arrange, Act and Assert
    assertEquals(
        "https://example.org/example", Utf8UrlEncoder.encodePath("https://example.org/example"));
  }

  /**
   * Test {@link Utf8UrlEncoder#encodeAndAppendQuery(StringBuilder, String)}.
   *
   * <p>Method under test: {@link Utf8UrlEncoder#encodeAndAppendQuery(StringBuilder, String)}
   */
  @Test
  @DisplayName("Test encodeAndAppendQuery(StringBuilder, String)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"StringBuilder Utf8UrlEncoder.encodeAndAppendQuery(StringBuilder, String)"})
  void testEncodeAndAppendQuery() {
    // Arrange
    StringBuilder sb = new StringBuilder("Str");

    // Act
    StringBuilder actualEncodeAndAppendQueryResult =
        Utf8UrlEncoder.encodeAndAppendQuery(sb, "https://example.org/example");

    // Assert
    assertEquals("Strhttps://example.org/example", sb.toString());
    assertEquals("Strhttps://example.org/example", actualEncodeAndAppendQueryResult.toString());
  }

  /**
   * Test {@link Utf8UrlEncoder#encodeQueryElement(String)}.
   *
   * <p>Method under test: {@link Utf8UrlEncoder#encodeQueryElement(String)}
   */
  @Test
  @DisplayName("Test encodeQueryElement(String)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"String Utf8UrlEncoder.encodeQueryElement(String)"})
  void testEncodeQueryElement() {
    // Arrange, Act and Assert
    assertEquals(
        "https%3A%2F%2Fexample.org%2Fexample",
        Utf8UrlEncoder.encodeQueryElement("https://example.org/example"));
  }

  /**
   * Test {@link Utf8UrlEncoder#encodeAndAppendQueryElement(StringBuilder, CharSequence)}.
   *
   * <ul>
   *   <li>Then {@link StringBuilder#StringBuilder(String)} with {@code Str} toString is {@code
   *       Str*%2F*}.
   * </ul>
   *
   * <p>Method under test: {@link Utf8UrlEncoder#encodeAndAppendQueryElement(StringBuilder,
   * CharSequence)}
   */
  @Test
  @DisplayName(
      "Test encodeAndAppendQueryElement(StringBuilder, CharSequence); then StringBuilder(String) with 'Str' toString is 'Str*%2F*'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "StringBuilder Utf8UrlEncoder.encodeAndAppendQueryElement(StringBuilder, CharSequence)"
  })
  void testEncodeAndAppendQueryElement_thenStringBuilderWithStrToStringIsStr2f() {
    // Arrange
    StringBuilder sb = new StringBuilder("Str");

    // Act
    StringBuilder actualEncodeAndAppendQueryElementResult =
        Utf8UrlEncoder.encodeAndAppendQueryElement(sb, HttpUtils.ACCEPT_ALL_HEADER_VALUE);

    // Assert
    assertEquals("Str*%2F*", sb.toString());
    assertEquals("Str*%2F*", actualEncodeAndAppendQueryElementResult.toString());
  }

  /**
   * Test {@link Utf8UrlEncoder#encodeAndAppendQueryElement(StringBuilder, CharSequence)}.
   *
   * <ul>
   *   <li>Then {@link StringBuilder#StringBuilder(String)} with {@code Str} toString is {@code
   *       StrNegotiate}.
   * </ul>
   *
   * <p>Method under test: {@link Utf8UrlEncoder#encodeAndAppendQueryElement(StringBuilder,
   * CharSequence)}
   */
  @Test
  @DisplayName(
      "Test encodeAndAppendQueryElement(StringBuilder, CharSequence); then StringBuilder(String) with 'Str' toString is 'StrNegotiate'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "StringBuilder Utf8UrlEncoder.encodeAndAppendQueryElement(StringBuilder, CharSequence)"
  })
  void testEncodeAndAppendQueryElement_thenStringBuilderWithStrToStringIsStrNegotiate() {
    // Arrange
    StringBuilder sb = new StringBuilder("Str");

    // Act
    StringBuilder actualEncodeAndAppendQueryElementResult =
        Utf8UrlEncoder.encodeAndAppendQueryElement(sb, AuthenticatorUtils.NEGOTIATE);

    // Assert
    assertEquals("StrNegotiate", sb.toString());
    assertEquals("StrNegotiate", actualEncodeAndAppendQueryElementResult.toString());
  }

  /**
   * Test {@link Utf8UrlEncoder#encodeAndAppendFormElement(StringBuilder, CharSequence)}.
   *
   * <ul>
   *   <li>Then {@link StringBuilder#StringBuilder(String)} with {@code Str} toString is {@code
   *       Str*%2F*}.
   * </ul>
   *
   * <p>Method under test: {@link Utf8UrlEncoder#encodeAndAppendFormElement(StringBuilder,
   * CharSequence)}
   */
  @Test
  @DisplayName(
      "Test encodeAndAppendFormElement(StringBuilder, CharSequence); then StringBuilder(String) with 'Str' toString is 'Str*%2F*'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "StringBuilder Utf8UrlEncoder.encodeAndAppendFormElement(StringBuilder, CharSequence)"
  })
  void testEncodeAndAppendFormElement_thenStringBuilderWithStrToStringIsStr2f() {
    // Arrange
    StringBuilder sb = new StringBuilder("Str");

    // Act
    StringBuilder actualEncodeAndAppendFormElementResult =
        Utf8UrlEncoder.encodeAndAppendFormElement(sb, HttpUtils.ACCEPT_ALL_HEADER_VALUE);

    // Assert
    assertEquals("Str*%2F*", sb.toString());
    assertEquals("Str*%2F*", actualEncodeAndAppendFormElementResult.toString());
  }

  /**
   * Test {@link Utf8UrlEncoder#encodeAndAppendFormElement(StringBuilder, CharSequence)}.
   *
   * <ul>
   *   <li>Then {@link StringBuilder#StringBuilder(String)} with {@code Str} toString is {@code
   *       StrNegotiate}.
   * </ul>
   *
   * <p>Method under test: {@link Utf8UrlEncoder#encodeAndAppendFormElement(StringBuilder,
   * CharSequence)}
   */
  @Test
  @DisplayName(
      "Test encodeAndAppendFormElement(StringBuilder, CharSequence); then StringBuilder(String) with 'Str' toString is 'StrNegotiate'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "StringBuilder Utf8UrlEncoder.encodeAndAppendFormElement(StringBuilder, CharSequence)"
  })
  void testEncodeAndAppendFormElement_thenStringBuilderWithStrToStringIsStrNegotiate() {
    // Arrange
    StringBuilder sb = new StringBuilder("Str");

    // Act
    StringBuilder actualEncodeAndAppendFormElementResult =
        Utf8UrlEncoder.encodeAndAppendFormElement(sb, AuthenticatorUtils.NEGOTIATE);

    // Assert
    assertEquals("StrNegotiate", sb.toString());
    assertEquals("StrNegotiate", actualEncodeAndAppendFormElementResult.toString());
  }

  /**
   * Test {@link Utf8UrlEncoder#percentEncodeQueryElement(String)}.
   *
   * <ul>
   *   <li>Then return {@code https%3A%2F%2Fexample.org%2Fexample}.
   * </ul>
   *
   * <p>Method under test: {@link Utf8UrlEncoder#percentEncodeQueryElement(String)}
   */
  @Test
  @DisplayName(
      "Test percentEncodeQueryElement(String); then return 'https%3A%2F%2Fexample.org%2Fexample'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"String Utf8UrlEncoder.percentEncodeQueryElement(String)"})
  void testPercentEncodeQueryElement_thenReturnHttps3a2f2FexampleOrg2Fexample() {
    // Arrange, Act and Assert
    assertEquals(
        "https%3A%2F%2Fexample.org%2Fexample",
        Utf8UrlEncoder.percentEncodeQueryElement("https://example.org/example"));
  }

  /**
   * Test {@link Utf8UrlEncoder#percentEncodeQueryElement(String)}.
   *
   * <ul>
   *   <li>When {@code null}.
   *   <li>Then return {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link Utf8UrlEncoder#percentEncodeQueryElement(String)}
   */
  @Test
  @DisplayName("Test percentEncodeQueryElement(String); when 'null'; then return 'null'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"String Utf8UrlEncoder.percentEncodeQueryElement(String)"})
  void testPercentEncodeQueryElement_whenNull_thenReturnNull() {
    // Arrange, Act and Assert
    assertNull(Utf8UrlEncoder.percentEncodeQueryElement(null));
  }

  /**
   * Test {@link Utf8UrlEncoder#encodeAndAppendPercentEncoded(StringBuilder, CharSequence)}.
   *
   * <ul>
   *   <li>Then {@link StringBuilder#StringBuilder(String)} with {@code Str} toString is {@code
   *       Str%2A%2F%2A}.
   * </ul>
   *
   * <p>Method under test: {@link Utf8UrlEncoder#encodeAndAppendPercentEncoded(StringBuilder,
   * CharSequence)}
   */
  @Test
  @DisplayName(
      "Test encodeAndAppendPercentEncoded(StringBuilder, CharSequence); then StringBuilder(String) with 'Str' toString is 'Str%2A%2F%2A'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "StringBuilder Utf8UrlEncoder.encodeAndAppendPercentEncoded(StringBuilder, CharSequence)"
  })
  void testEncodeAndAppendPercentEncoded_thenStringBuilderWithStrToStringIsStr2a2f2a() {
    // Arrange
    StringBuilder sb = new StringBuilder("Str");

    // Act
    StringBuilder actualEncodeAndAppendPercentEncodedResult =
        Utf8UrlEncoder.encodeAndAppendPercentEncoded(sb, HttpUtils.ACCEPT_ALL_HEADER_VALUE);

    // Assert
    assertEquals("Str%2A%2F%2A", sb.toString());
    assertEquals("Str%2A%2F%2A", actualEncodeAndAppendPercentEncodedResult.toString());
  }

  /**
   * Test {@link Utf8UrlEncoder#encodeAndAppendPercentEncoded(StringBuilder, CharSequence)}.
   *
   * <ul>
   *   <li>Then {@link StringBuilder#StringBuilder(String)} with {@code Str} toString is {@code
   *       StrNegotiate}.
   * </ul>
   *
   * <p>Method under test: {@link Utf8UrlEncoder#encodeAndAppendPercentEncoded(StringBuilder,
   * CharSequence)}
   */
  @Test
  @DisplayName(
      "Test encodeAndAppendPercentEncoded(StringBuilder, CharSequence); then StringBuilder(String) with 'Str' toString is 'StrNegotiate'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "StringBuilder Utf8UrlEncoder.encodeAndAppendPercentEncoded(StringBuilder, CharSequence)"
  })
  void testEncodeAndAppendPercentEncoded_thenStringBuilderWithStrToStringIsStrNegotiate() {
    // Arrange
    StringBuilder sb = new StringBuilder("Str");

    // Act
    StringBuilder actualEncodeAndAppendPercentEncodedResult =
        Utf8UrlEncoder.encodeAndAppendPercentEncoded(sb, AuthenticatorUtils.NEGOTIATE);

    // Assert
    assertEquals("StrNegotiate", sb.toString());
    assertEquals("StrNegotiate", actualEncodeAndAppendPercentEncodedResult.toString());
  }
}
