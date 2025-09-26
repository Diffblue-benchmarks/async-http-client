package org.asynchttpclient.util;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
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
   *   <li>Then return position is zero.
   * </ul>
   *
   * <p>Method under test: {@link StringUtils#charSequence2ByteBuffer(CharSequence, Charset)}
   */
  @Test
  @DisplayName(
      "Test charSequence2ByteBuffer(CharSequence, Charset); when NEGOTIATE; then return position is zero")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"ByteBuffer StringUtils.charSequence2ByteBuffer(CharSequence, Charset)"})
  void testCharSequence2ByteBuffer_whenNegotiate_thenReturnPositionIsZero()
      throws UnsupportedEncodingException {
    // Arrange and Act
    ByteBuffer actualCharSequence2ByteBufferResult =
        StringUtils.charSequence2ByteBuffer(AuthenticatorUtils.NEGOTIATE, Charset.forName("UTF-8"));

    // Assert
    assertEquals(0, actualCharSequence2ByteBufferResult.position());
    assertEquals(9, actualCharSequence2ByteBufferResult.capacity());
    assertEquals(9, actualCharSequence2ByteBufferResult.limit());
    assertTrue(actualCharSequence2ByteBufferResult.hasRemaining());
    assertTrue(actualCharSequence2ByteBufferResult.hasArray());
    byte[] expectedArrayResult = "Negotiate".getBytes("UTF-8");
    assertArrayEquals(expectedArrayResult, actualCharSequence2ByteBufferResult.array());
  }

  /**
   * Test {@link StringUtils#byteBuffer2ByteArray(ByteBuffer)}.
   *
   * <ul>
   *   <li>Then wrap {@code AXAXAXAX} Bytes is {@code UTF-8} position is eight.
   * </ul>
   *
   * <p>Method under test: {@link StringUtils#byteBuffer2ByteArray(ByteBuffer)}
   */
  @Test
  @DisplayName(
      "Test byteBuffer2ByteArray(ByteBuffer); then wrap 'AXAXAXAX' Bytes is 'UTF-8' position is eight")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"byte[] StringUtils.byteBuffer2ByteArray(ByteBuffer)"})
  void testByteBuffer2ByteArray_thenWrapAxaxaxaxBytesIsUtf8PositionIsEight()
      throws UnsupportedEncodingException {
    // Arrange
    ByteBuffer bb = ByteBuffer.wrap("AXAXAXAX".getBytes("UTF-8"));

    // Act
    byte[] actualByteBuffer2ByteArrayResult = StringUtils.byteBuffer2ByteArray(bb);

    // Assert
    assertEquals(8, bb.position());
    assertFalse(bb.hasRemaining());
    assertArrayEquals("AXAXAXAX".getBytes("UTF-8"), actualByteBuffer2ByteArrayResult);
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
   *   <li>Then {@link StringBuilder#StringBuilder(String)} with {@code foo} toString is {@code
   *       foo4158415841584158}.
   * </ul>
   *
   * <p>Method under test: {@link StringUtils#appendBase16(StringBuilder, byte[])}
   */
  @Test
  @DisplayName(
      "Test appendBase16(StringBuilder, byte[]); then StringBuilder(String) with 'foo' toString is 'foo4158415841584158'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void StringUtils.appendBase16(StringBuilder, byte[])"})
  void testAppendBase16_thenStringBuilderWithFooToStringIsFoo4158415841584158()
      throws UnsupportedEncodingException {
    // Arrange
    StringBuilder buf = new StringBuilder("foo");

    // Act
    StringUtils.appendBase16(buf, "AXAXAXAX".getBytes("UTF-8"));

    // Assert
    assertEquals("foo4158415841584158", buf.toString());
  }

  /**
   * Test {@link StringUtils#appendBase16(StringBuilder, byte[])}.
   *
   * <ul>
   *   <li>When {@code A}.
   *   <li>Then {@link StringBuilder#StringBuilder(String)} with {@code foo} toString is {@code
   *       fooff58415841584158}.
   * </ul>
   *
   * <p>Method under test: {@link StringUtils#appendBase16(StringBuilder, byte[])}
   */
  @Test
  @DisplayName(
      "Test appendBase16(StringBuilder, byte[]); when 'A'; then StringBuilder(String) with 'foo' toString is 'fooff58415841584158'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void StringUtils.appendBase16(StringBuilder, byte[])"})
  void testAppendBase16_whenA_thenStringBuilderWithFooToStringIsFooff58415841584158() {
    // Arrange
    StringBuilder buf = new StringBuilder("foo");

    // Act
    StringUtils.appendBase16(buf, new byte[] {-1, 'X', 'A', 'X', 'A', 'X', 'A', 'X'});

    // Assert
    assertEquals("fooff58415841584158", buf.toString());
  }
}
