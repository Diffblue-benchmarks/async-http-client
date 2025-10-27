package org.asynchttpclient.util;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import org.junit.jupiter.api.Test;

class StringUtilsDiffblueTest {
  /**
   * Method under test: {@link StringUtils#byteBuffer2ByteArray(ByteBuffer)}
   */
  @Test
  void testByteBuffer2ByteArray() throws UnsupportedEncodingException {
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
   * Method under test: {@link StringUtils#toHexString(byte[])}
   */
  @Test
  void testToHexString() throws UnsupportedEncodingException {
    // Arrange, Act and Assert
    assertEquals("4158415841584158", StringUtils.toHexString("AXAXAXAX".getBytes("UTF-8")));
  }

  /**
   * Method under test: {@link StringUtils#appendBase16(StringBuilder, byte[])}
   */
  @Test
  void testAppendBase16() throws UnsupportedEncodingException {
    // Arrange
    StringBuilder buf = new StringBuilder("foo");

    // Act
    StringUtils.appendBase16(buf, "AXAXAXAX".getBytes("UTF-8"));

    // Assert
    assertEquals("foo4158415841584158", buf.toString());
  }

  /**
   * Method under test: {@link StringUtils#appendBase16(StringBuilder, byte[])}
   */
  @Test
  void testAppendBase162() {
    // Arrange
    StringBuilder buf = new StringBuilder("foo");

    // Act
    StringUtils.appendBase16(buf, new byte[]{-1, 'X', 'A', 'X', 'A', 'X', 'A', 'X'});

    // Assert
    assertEquals("fooff58415841584158", buf.toString());
  }
}
