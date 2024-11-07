package org.asynchttpclient.util;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class StringUtilsDiffblueTest {
  /**
   * Test {@link StringUtils#charSequence2ByteBuffer(CharSequence, Charset)}.
   * <ul>
   *   <li>When {@link AuthenticatorUtils#NEGOTIATE}.</li>
   *   <li>Then return position is zero.</li>
   * </ul>
   * <p>
   * Method under test:
   * {@link StringUtils#charSequence2ByteBuffer(CharSequence, Charset)}
   */
  @Test
  @DisplayName("Test charSequence2ByteBuffer(CharSequence, Charset); when NEGOTIATE; then return position is zero")
  void testCharSequence2ByteBuffer_whenNegotiate_thenReturnPositionIsZero() throws UnsupportedEncodingException {
    // Arrange and Act
    ByteBuffer actualCharSequence2ByteBufferResult = StringUtils.charSequence2ByteBuffer(AuthenticatorUtils.NEGOTIATE,
        Charset.forName("UTF-8"));

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
   * <ul>
   *   <li>Then wrap {@code AXAXAXAX} Bytes is {@code UTF-8} position is eight.</li>
   * </ul>
   * <p>
   * Method under test: {@link StringUtils#byteBuffer2ByteArray(ByteBuffer)}
   */
  @Test
  @DisplayName("Test byteBuffer2ByteArray(ByteBuffer); then wrap 'AXAXAXAX' Bytes is 'UTF-8' position is eight")
  void testByteBuffer2ByteArray_thenWrapAxaxaxaxBytesIsUtf8PositionIsEight() throws UnsupportedEncodingException {
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
   * <ul>
   *   <li>When {@link AuthenticatorUtils#NEGOTIATE}.</li>
   *   <li>Then return {@code Negotiate} Bytes is {@code UTF-8}.</li>
   * </ul>
   * <p>
   * Method under test:
   * {@link StringUtils#charSequence2Bytes(CharSequence, Charset)}
   */
  @Test
  @DisplayName("Test charSequence2Bytes(CharSequence, Charset); when NEGOTIATE; then return 'Negotiate' Bytes is 'UTF-8'")
  void testCharSequence2Bytes_whenNegotiate_thenReturnNegotiateBytesIsUtf8() throws UnsupportedEncodingException {
    // Arrange and Act
    byte[] actualCharSequence2BytesResult = StringUtils.charSequence2Bytes(AuthenticatorUtils.NEGOTIATE,
        Charset.forName("UTF-8"));

    // Assert
    assertArrayEquals("Negotiate".getBytes("UTF-8"), actualCharSequence2BytesResult);
  }

  /**
   * Test {@link StringUtils#toHexString(byte[])}.
   * <p>
   * Method under test: {@link StringUtils#toHexString(byte[])}
   */
  @Test
  @DisplayName("Test toHexString(byte[])")
  void testToHexString() throws UnsupportedEncodingException {
    // Arrange, Act and Assert
    assertEquals("4158415841584158", StringUtils.toHexString("AXAXAXAX".getBytes("UTF-8")));
  }

  /**
   * Test {@link StringUtils#appendBase16(StringBuilder, byte[])}.
   * <ul>
   *   <li>Then {@link StringBuilder#StringBuilder(String)} with {@code foo}
   * toString is {@code foo4158415841584158}.</li>
   * </ul>
   * <p>
   * Method under test: {@link StringUtils#appendBase16(StringBuilder, byte[])}
   */
  @Test
  @DisplayName("Test appendBase16(StringBuilder, byte[]); then StringBuilder(String) with 'foo' toString is 'foo4158415841584158'")
  void testAppendBase16_thenStringBuilderWithFooToStringIsFoo4158415841584158() throws UnsupportedEncodingException {
    // Arrange
    StringBuilder buf = new StringBuilder("foo");

    // Act
    StringUtils.appendBase16(buf, "AXAXAXAX".getBytes("UTF-8"));

    // Assert
    assertEquals("foo4158415841584158", buf.toString());
  }

  /**
   * Test {@link StringUtils#appendBase16(StringBuilder, byte[])}.
   * <ul>
   *   <li>When {@code A}.</li>
   *   <li>Then {@link StringBuilder#StringBuilder(String)} with {@code foo}
   * toString is {@code fooff58415841584158}.</li>
   * </ul>
   * <p>
   * Method under test: {@link StringUtils#appendBase16(StringBuilder, byte[])}
   */
  @Test
  @DisplayName("Test appendBase16(StringBuilder, byte[]); when 'A'; then StringBuilder(String) with 'foo' toString is 'fooff58415841584158'")
  void testAppendBase16_whenA_thenStringBuilderWithFooToStringIsFooff58415841584158() {
    // Arrange
    StringBuilder buf = new StringBuilder("foo");

    // Act
    StringUtils.appendBase16(buf, new byte[]{-1, 'X', 'A', 'X', 'A', 'X', 'A', 'X'});

    // Assert
    assertEquals("fooff58415841584158", buf.toString());
  }
}
