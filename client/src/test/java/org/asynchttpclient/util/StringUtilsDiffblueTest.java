package org.asynchttpclient.util;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

class StringUtilsDiffblueTest {
  /**
   * Test {@link StringUtils#charSequence2ByteBuffer(CharSequence, Charset)}.
   *
   * <ul>
   *   <li>When {@link AuthenticatorUtils#NEGOTIATE}.
   *   <li>Then return array is {@code Negotiate} Bytes is {@code UTF-8}.
   * </ul>
   *
   * <p>Method under test: {@link StringUtils#charSequence2ByteBuffer(CharSequence, Charset)}
   */
  @Test
  @DisplayName(
      "Test charSequence2ByteBuffer(CharSequence, Charset); when NEGOTIATE; then return array is 'Negotiate' Bytes is 'UTF-8'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"ByteBuffer StringUtils.charSequence2ByteBuffer(CharSequence, Charset)"})
  void testCharSequence2ByteBuffer_whenNegotiate_thenReturnArrayIsNegotiateBytesIsUtf8()
      throws UnsupportedEncodingException {
    // Arrange and Act
    ByteBuffer actualCharSequence2ByteBufferResult =
        StringUtils.charSequence2ByteBuffer(AuthenticatorUtils.NEGOTIATE, Charset.forName("UTF-8"));

    // Assert
    byte[] expectedArrayResult = "Negotiate".getBytes("UTF-8");
    assertArrayEquals(expectedArrayResult, actualCharSequence2ByteBufferResult.array());
    assertEquals(9, actualCharSequence2ByteBufferResult.capacity());
    ByteBuffer actualFlipResult = actualCharSequence2ByteBufferResult.flip();
    assertSame(actualCharSequence2ByteBufferResult, actualFlipResult);
    assertTrue(actualCharSequence2ByteBufferResult.hasArray());
    assertFalse(actualCharSequence2ByteBufferResult.hasRemaining());
    assertEquals(0, actualCharSequence2ByteBufferResult.limit());
    assertEquals(0, actualCharSequence2ByteBufferResult.position());
  }

  /**
   * Test {@link StringUtils#byteBuffer2ByteArray(ByteBuffer)}.
   *
   * <ul>
   *   <li>Then return {@code AXAXAXAX} Bytes is {@code UTF-8}.
   * </ul>
   *
   * <p>Method under test: {@link StringUtils#byteBuffer2ByteArray(ByteBuffer)}
   */
  @Test
  @DisplayName("Test byteBuffer2ByteArray(ByteBuffer); then return 'AXAXAXAX' Bytes is 'UTF-8'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"byte[] StringUtils.byteBuffer2ByteArray(ByteBuffer)"})
  void testByteBuffer2ByteArray_thenReturnAxaxaxaxBytesIsUtf8()
      throws UnsupportedEncodingException {
    // Arrange, Act and Assert
    assertArrayEquals(
        "AXAXAXAX".getBytes("UTF-8"),
        StringUtils.byteBuffer2ByteArray(ByteBuffer.wrap("AXAXAXAX".getBytes("UTF-8"))));
  }

  /**
   * Test {@link StringUtils#charSequence2Bytes(CharSequence, Charset)}.
   *
   * <ul>
   *   <li>When {@link AuthenticatorUtils#NEGOTIATE}.
   *   <li>Then return {@code Negotiate} Bytes is {@code UTF-8}.
   * </ul>
   *
   * <p>Method under test: {@link StringUtils#charSequence2Bytes(CharSequence, Charset)}
   */
  @Test
  @DisplayName(
      "Test charSequence2Bytes(CharSequence, Charset); when NEGOTIATE; then return 'Negotiate' Bytes is 'UTF-8'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"byte[] StringUtils.charSequence2Bytes(CharSequence, Charset)"})
  void testCharSequence2Bytes_whenNegotiate_thenReturnNegotiateBytesIsUtf8()
      throws UnsupportedEncodingException {
    // Arrange, Act and Assert
    assertArrayEquals(
        "Negotiate".getBytes("UTF-8"),
        StringUtils.charSequence2Bytes(AuthenticatorUtils.NEGOTIATE, Charset.forName("UTF-8")));
  }

  /**
   * Test {@link StringUtils#toHexString(byte[])}.
   *
   * <ul>
   *   <li>When {@code AXAXAXAX} Bytes is {@code UTF-8}.
   *   <li>Then return {@code 4158415841584158}.
   * </ul>
   *
   * <p>Method under test: {@link StringUtils#toHexString(byte[])}
   */
  @Test
  @DisplayName(
      "Test toHexString(byte[]); when 'AXAXAXAX' Bytes is 'UTF-8'; then return '4158415841584158'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"java.lang.String StringUtils.toHexString(byte[])"})
  void testToHexString_whenAxaxaxaxBytesIsUtf8_thenReturn4158415841584158()
      throws UnsupportedEncodingException {
    // Arrange, Act and Assert
    assertEquals("4158415841584158", StringUtils.toHexString("AXAXAXAX".getBytes("UTF-8")));
  }

  /**
   * Test {@link StringUtils#toHexString(byte[])}.
   *
   * <ul>
   *   <li>When empty array of {@code byte}.
   *   <li>Then return empty string.
   * </ul>
   *
   * <p>Method under test: {@link StringUtils#toHexString(byte[])}
   */
  @Test
  @DisplayName("Test toHexString(byte[]); when empty array of byte; then return empty string")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"java.lang.String StringUtils.toHexString(byte[])"})
  void testToHexString_whenEmptyArrayOfByte_thenReturnEmptyString() {
    // Arrange, Act and Assert
    assertEquals("", StringUtils.toHexString(new byte[] {}));
  }

  /**
   * Test {@link StringUtils#appendBase16(StringBuilder, byte[])}.
   *
   * <ul>
   *   <li>Then {@link StringBuilder#StringBuilder(String)} with {@code Str} toString is {@code
   *       Str4158415841584158}.
   * </ul>
   *
   * <p>Method under test: {@link StringUtils#appendBase16(StringBuilder, byte[])}
   */
  @Test
  @DisplayName(
      "Test appendBase16(StringBuilder, byte[]); then StringBuilder(String) with 'Str' toString is 'Str4158415841584158'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void StringUtils.appendBase16(StringBuilder, byte[])"})
  void testAppendBase16_thenStringBuilderWithStrToStringIsStr4158415841584158()
      throws UnsupportedEncodingException {
    // Arrange
    StringBuilder buf = new StringBuilder("Str");

    // Act
    StringUtils.appendBase16(buf, "AXAXAXAX".getBytes("UTF-8"));

    // Assert
    assertEquals("Str4158415841584158", buf.toString());
  }

  /**
   * Test {@link StringUtils#appendBase16(StringBuilder, byte[])}.
   *
   * <ul>
   *   <li>When {@code A}.
   *   <li>Then {@link StringBuilder#StringBuilder(String)} with {@code Str} toString is {@code
   *       Strff58415841584158}.
   * </ul>
   *
   * <p>Method under test: {@link StringUtils#appendBase16(StringBuilder, byte[])}
   */
  @Test
  @DisplayName(
      "Test appendBase16(StringBuilder, byte[]); when 'A'; then StringBuilder(String) with 'Str' toString is 'Strff58415841584158'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void StringUtils.appendBase16(StringBuilder, byte[])"})
  void testAppendBase16_whenA_thenStringBuilderWithStrToStringIsStrff58415841584158() {
    // Arrange
    StringBuilder buf = new StringBuilder("Str");

    // Act
    StringUtils.appendBase16(buf, new byte[] {-1, 'X', 'A', 'X', 'A', 'X', 'A', 'X'});

    // Assert
    assertEquals("Strff58415841584158", buf.toString());
  }
}
