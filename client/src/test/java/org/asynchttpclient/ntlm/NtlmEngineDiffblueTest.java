package org.asynchttpclient.ntlm;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import java.io.UnsupportedEncodingException;
import org.asynchttpclient.ntlm.NtlmEngine.Type3Message;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class NtlmEngineDiffblueTest {
  /**
   * Test Type3Message {@link Type3Message#getResponse()}.
   * <p>
   * Method under test: {@link NtlmEngine.Type3Message#getResponse()}
   */
  @Test
  @DisplayName("Test Type3Message getResponse()")
  void testType3MessageGetResponse() throws UnsupportedEncodingException {
    // Arrange
    byte[] nonce = "AXAXAXAX".getBytes("UTF-8");

    // Act and Assert
    assertEquals(
        "TlRMTVNTUAADAAAAGAAYAEgAAAAYABgAYAAAAB4AHgB4AAAANgA2AJYAAAAeAB4AzAAAAAAAAADqAAAAAQAAAgUBKAoAAAAP4251"
            + "aKwCYRTRZL1wscPO1olAjLz9MrB2vcqJ1jdQ75Be/MAOzfutLb31QSkwSOqNSABUAFQAUABTADoALwAvAEUAWABBAE0AUABMAEUA"
            + "aAB0AHQAcABzADoALwAvAGUAeABhAG0AcABsAGUALgBvAHIAZwAvAGUAeABhAG0AcABsAGUASABUAFQAUABTADoALwAvAEUAWABB"
            + "AE0AUABMAEUA",
        (new NtlmEngine.Type3Message("https://example.org/example", "https://example.org/example",
            "https://example.org/example", "https://example.org/example", nonce, 1, "https://example.org/example",
            "AXAXAXAX".getBytes("UTF-8"))).getResponse());
  }

  /**
   * Test Type3Message {@link Type3Message#getResponse()}.
   * <p>
   * Method under test: {@link NtlmEngine.Type3Message#getResponse()}
   */
  @Test
  @DisplayName("Test Type3Message getResponse()")
  void testType3MessageGetResponse2() throws UnsupportedEncodingException {
    // Arrange
    byte[] nonce = "AXAXAXAX".getBytes("UTF-8");

    // Act and Assert
    assertEquals(
        "TlRMTVNTUAADAAAAGAAYAEgAAAAYABgAYAAAAAAAAAB4AAAANgA2AHgAAAAeAB4ArgAAAAAAAADMAAAAAQAAAgUBKAoAAAAP4251"
            + "aKwCYRTRZL1wscPO1olAjLz9MrB2vcqJ1jdQ75Be/MAOzfutLb31QSkwSOqNaAB0AHQAcABzADoALwAvAGUAeABhAG0AcABsAGUA"
            + "LgBvAHIAZwAvAGUAeABhAG0AcABsAGUASABUAFQAUABTADoALwAvAEUAWABBAE0AUABMAEUA",
        (new NtlmEngine.Type3Message(null, "https://example.org/example", "https://example.org/example",
            "https://example.org/example", nonce, 1, "https://example.org/example", "AXAXAXAX".getBytes("UTF-8")))
                .getResponse());
  }

  /**
   * Test Type3Message {@link Type3Message#getResponse()}.
   * <p>
   * Method under test: {@link NtlmEngine.Type3Message#getResponse()}
   */
  @Test
  @DisplayName("Test Type3Message getResponse()")
  void testType3MessageGetResponse3() throws UnsupportedEncodingException {
    // Arrange
    byte[] nonce = "AXAXAXAX".getBytes("UTF-8");

    // Act and Assert
    assertEquals(
        "TlRMTVNTUAADAAAAGAAYAEgAAAAYABgAYAAAAB4AHgB4AAAANgA2AJYAAAAAAAAAzAAAAAAAAADMAAAAAQAAAgUBKAoAAAAP4251"
            + "aKwCYRTRZL1wscPO1olAjLz9MrB2vcqJ1jdQ75Be/MAOzfutLb31QSkwSOqNSABUAFQAUABTADoALwAvAEUAWABBAE0AUABMAEUA"
            + "aAB0AHQAcABzADoALwAvAGUAeABhAG0AcABsAGUALgBvAHIAZwAvAGUAeABhAG0AcABsAGUA",
        (new NtlmEngine.Type3Message("https://example.org/example", null, "https://example.org/example",
            "https://example.org/example", nonce, 1, "https://example.org/example", "AXAXAXAX".getBytes("UTF-8")))
                .getResponse());
  }

  /**
   * Test Type3Message
   * {@link Type3Message#Type3Message(String, String, String, String, byte[], int, String, byte[])}.
   * <ul>
   *   <li>Then return {@link Type3Message#type2Flags} is one hundred
   * twenty-eight.</li>
   * </ul>
   * <p>
   * Method under test:
   * {@link NtlmEngine.Type3Message#Type3Message(String, String, String, String, byte[], int, String, byte[])}
   */
  @Test
  @DisplayName("Test Type3Message new Type3Message(String, String, String, String, byte[], int, String, byte[]); then return type2Flags is one hundred twenty-eight")
  void testType3MessageNewType3Message_thenReturnType2FlagsIsOneHundredTwentyEight()
      throws UnsupportedEncodingException {
    // Arrange
    byte[] nonce = "AXAXAXAX".getBytes("UTF-8");

    // Act
    NtlmEngine.Type3Message actualType3Message = new NtlmEngine.Type3Message("https://example.org/example",
        "https://example.org/example", "https://example.org/example", "https://example.org/example", nonce, 128,
        "https://example.org/example", "AXAXAXAX".getBytes("UTF-8"));

    // Assert
    assertEquals("TlRMTVNTUAADAAAAGAAYAEgAAAAYABgAYAAAAB4AHgB4AAAANgA2AJYAAAAeAB4AzAAAAAAAAADqAAAAgAAAAgUBKAoAAAAP4251"
        + "aKwCYRTRZL1wscPO1olAjLz9MrB2vcqJ1jdQ75Be/MAOzfutLb31QSkwSOqNSABUAFQAUABTADoALwAvAEUAWABBAE0AUABMAEUA"
        + "aAB0AHQAcABzADoALwAvAGUAeABhAG0AcABsAGUALgBvAHIAZwAvAGUAeABhAG0AcABsAGUASABUAFQAUABTADoALwAvAEUAWABB"
        + "AE0AUABMAEUA", actualType3Message.getResponse());
    assertEquals(128, actualType3Message.type2Flags);
    assertArrayEquals(new byte[]{-29, 'n', 'u', 'h', -84, 2, 'a', 20, -47, 'd', -67, 'p', -79, -61, -50, -42, -119, '@',
        -116, -68, -3, '2', -80, 'v'}, actualType3Message.lmResp);
    assertArrayEquals(new byte[]{-67, -54, -119, -42, '7', 'P', -17, -112, '^', -4, -64, 14, -51, -5, -83, '-', -67,
        -11, 'A', ')', '0', 'H', -22, -115}, actualType3Message.ntResp);
  }

  /**
   * Test Type3Message
   * {@link Type3Message#Type3Message(String, String, String, String, byte[], int, String, byte[])}.
   * <ul>
   *   <li>When {@code 524288}.</li>
   *   <li>Then return eleventh element is zero.</li>
   * </ul>
   * <p>
   * Method under test:
   * {@link NtlmEngine.Type3Message#Type3Message(String, String, String, String, byte[], int, String, byte[])}
   */
  @Test
  @DisplayName("Test Type3Message new Type3Message(String, String, String, String, byte[], int, String, byte[]); when '524288'; then return eleventh element is zero")
  void testType3MessageNewType3Message_when524288_thenReturnEleventhElementIsZero()
      throws UnsupportedEncodingException {
    // Arrange
    byte[] nonce = "AXAXAXAX".getBytes("UTF-8");

    // Act
    NtlmEngine.Type3Message actualType3Message = new NtlmEngine.Type3Message("https://example.org/example",
        "https://example.org/example", "https://example.org/example", "https://example.org/example", nonce, 524288,
        "https://example.org/example", "AXAXAXAX".getBytes("UTF-8"));

    // Assert
    byte[] byteArray = actualType3Message.lmResp;
    assertEquals((byte) 0, byteArray[10]);
    assertEquals((byte) 0, byteArray[11]);
    assertEquals((byte) 0, byteArray[12]);
    assertEquals((byte) 0, byteArray[13]);
    assertEquals((byte) 0, byteArray[14]);
    assertEquals((byte) 0, byteArray[15]);
    assertEquals((byte) 0, byteArray[17]);
    assertEquals((byte) 0, byteArray[18]);
    assertEquals((byte) 0, byteArray[19]);
    assertEquals((byte) 0, byteArray[20]);
    assertEquals((byte) 0, byteArray[21]);
    assertEquals((byte) 0, byteArray[22]);
    assertEquals((byte) 0, byteArray[23]);
    assertEquals((byte) 0, byteArray[8]);
    assertEquals((byte) 0, byteArray[9]);
    assertEquals((byte) 0, byteArray[Short.SIZE]);
    assertEquals(24, byteArray.length);
    assertEquals(524288, actualType3Message.type2Flags);
  }

  /**
   * Test Type3Message
   * {@link Type3Message#Type3Message(String, String, String, String, byte[], int, String, byte[])}.
   * <ul>
   *   <li>When {@code 8388608}.</li>
   *   <li>Then return {@link Type3Message#type2Flags} is {@code 8388608}.</li>
   * </ul>
   * <p>
   * Method under test:
   * {@link NtlmEngine.Type3Message#Type3Message(String, String, String, String, byte[], int, String, byte[])}
   */
  @Test
  @DisplayName("Test Type3Message new Type3Message(String, String, String, String, byte[], int, String, byte[]); when '8388608'; then return type2Flags is '8388608'")
  void testType3MessageNewType3Message_when8388608_thenReturnType2FlagsIs8388608() throws UnsupportedEncodingException {
    // Arrange
    byte[] nonce = "AXAXAXAX".getBytes("UTF-8");

    // Act
    NtlmEngine.Type3Message actualType3Message = new NtlmEngine.Type3Message("https://example.org/example",
        "https://example.org/example", "https://example.org/example", "https://example.org/example", nonce, 8388608,
        "https://example.org/example", "AXAXAXAX".getBytes("UTF-8"));

    // Assert
    byte[] byteArray = actualType3Message.ntResp;
    assertEquals((byte) 0, byteArray[18]);
    assertEquals((byte) 0, byteArray[19]);
    assertEquals((byte) 0, byteArray[20]);
    assertEquals((byte) 0, byteArray[21]);
    assertEquals((byte) 0, byteArray[22]);
    assertEquals((byte) 0, byteArray[23]);
    assertEquals((byte) 0, byteArray[40]);
    assertEquals((byte) 0, byteArray[41]);
    assertEquals((byte) 0, byteArray[42]);
    assertEquals((byte) 0, byteArray[43]);
    assertEquals((byte) 0, byteArray[52]);
    assertEquals((byte) 0, byteArray[53]);
    assertEquals((byte) 0, byteArray[54]);
    assertEquals((byte) 0, byteArray[55]);
    assertEquals((byte) 1, byteArray[17]);
    assertEquals((byte) 1, byteArray[Short.SIZE]);
    assertEquals(56, byteArray.length);
    assertEquals(8388608, actualType3Message.type2Flags);
    assertEquals('A', byteArray[44]);
    assertEquals('A', byteArray[46]);
    assertEquals('A', byteArray[48]);
    assertEquals('A', byteArray[50]);
    assertEquals('X', byteArray[45]);
    assertEquals('X', byteArray[47]);
    assertEquals('X', byteArray[49]);
    assertEquals('X', byteArray[51]);
  }

  /**
   * Test Type3Message
   * {@link Type3Message#Type3Message(String, String, String, String, byte[], int, String, byte[])}.
   * <ul>
   *   <li>When {@code A}.</li>
   *   <li>Then return {@link Type3Message#domainBytes} is {@code null}.</li>
   * </ul>
   * <p>
   * Method under test:
   * {@link NtlmEngine.Type3Message#Type3Message(String, String, String, String, byte[], int, String, byte[])}
   */
  @Test
  @DisplayName("Test Type3Message new Type3Message(String, String, String, String, byte[], int, String, byte[]); when 'A'; then return domainBytes is 'null'")
  void testType3MessageNewType3Message_whenA_thenReturnDomainBytesIsNull() {
    // Arrange and Act
    NtlmEngine.Type3Message actualType3Message = new NtlmEngine.Type3Message(null, null, "https://example.org/example",
        "https://example.org/example", new byte[]{'A', 'X', 'A', 'X', 'A', 'X', 'A', 'X'}, 1, null, null);

    // Assert
    assertEquals("TlRMTVNTUAADAAAAGAAYAEgAAAAYABgAYAAAAAAAAAB4AAAANgA2AHgAAAAAAAAArgAAAAAAAACuAAAAAQAAAgUBKAoAAAAP4251"
        + "aKwCYRTRZL1wscPO1olAjLz9MrB2vcqJ1jdQ75Be/MAOzfutLb31QSkwSOqNaAB0AHQAcABzADoALwAvAGUAeABhAG0AcABsAGUA"
        + "LgBvAHIAZwAvAGUAeABhAG0AcABsAGUA", actualType3Message.getResponse());
    assertNull(actualType3Message.domainBytes);
    assertNull(actualType3Message.hostBytes);
    assertEquals(1, actualType3Message.type2Flags);
  }

  /**
   * Test Type3Message
   * {@link Type3Message#Type3Message(String, String, String, String, byte[], int, String, byte[])}.
   * <ul>
   *   <li>When {@code DES}.</li>
   *   <li>Then return array length is six.</li>
   * </ul>
   * <p>
   * Method under test:
   * {@link NtlmEngine.Type3Message#Type3Message(String, String, String, String, byte[], int, String, byte[])}
   */
  @Test
  @DisplayName("Test Type3Message new Type3Message(String, String, String, String, byte[], int, String, byte[]); when 'DES'; then return array length is six")
  void testType3MessageNewType3Message_whenDes_thenReturnArrayLengthIsSix() throws UnsupportedEncodingException {
    // Arrange
    byte[] nonce = "AXAXAXAX".getBytes("UTF-8");

    // Act
    NtlmEngine.Type3Message actualType3Message = new NtlmEngine.Type3Message("DES", "https://example.org/example",
        "https://example.org/example", "https://example.org/example", nonce, 1, "https://example.org/example",
        "AXAXAXAX".getBytes("UTF-8"));

    // Assert
    assertEquals(
        "TlRMTVNTUAADAAAAGAAYAEgAAAAYABgAYAAAAAYABgB4AAAANgA2AH4AAAAeAB4AtAAAAAAAAADSAAAAAQAAAgUBKAoAAAAP4251"
            + "aKwCYRTRZL1wscPO1olAjLz9MrB2vcqJ1jdQ75Be/MAOzfutLb31QSkwSOqNRABFAFMAaAB0AHQAcABzADoALwAvAGUAeABhAG0A"
            + "cABsAGUALgBvAHIAZwAvAGUAeABhAG0AcABsAGUASABUAFQAUABTADoALwAvAEUAWABBAE0AUABMAEUA",
        actualType3Message.getResponse());
    byte[] byteArray = actualType3Message.domainBytes;
    assertEquals(6, byteArray.length);
    assertEquals('D', byteArray[0]);
    assertEquals('E', byteArray[2]);
    assertEquals('S', byteArray[4]);
  }

  /**
   * Test Type3Message
   * {@link Type3Message#Type3Message(String, String, String, String, byte[], int, String, byte[])}.
   * <ul>
   *   <li>When minus one.</li>
   *   <li>Then return {@link Type3Message#type2Flags} is minus one.</li>
   * </ul>
   * <p>
   * Method under test:
   * {@link NtlmEngine.Type3Message#Type3Message(String, String, String, String, byte[], int, String, byte[])}
   */
  @Test
  @DisplayName("Test Type3Message new Type3Message(String, String, String, String, byte[], int, String, byte[]); when minus one; then return type2Flags is minus one")
  void testType3MessageNewType3Message_whenMinusOne_thenReturnType2FlagsIsMinusOne()
      throws UnsupportedEncodingException {
    // Arrange
    byte[] nonce = "AXAXAXAX".getBytes("UTF-8");

    // Act
    NtlmEngine.Type3Message actualType3Message = new NtlmEngine.Type3Message("https://example.org/example",
        "https://example.org/example", "https://example.org/example", "https://example.org/example", nonce, -1,
        "https://example.org/example", "AXAXAXAX".getBytes("UTF-8"));

    // Assert
    assertEquals(-1, actualType3Message.type2Flags);
    byte[] byteArray = actualType3Message.ntResp;
    assertEquals((byte) 0, byteArray[18]);
    assertEquals((byte) 0, byteArray[19]);
    assertEquals((byte) 0, byteArray[20]);
    assertEquals((byte) 0, byteArray[21]);
    assertEquals((byte) 0, byteArray[22]);
    assertEquals((byte) 0, byteArray[23]);
    assertEquals((byte) 0, byteArray[40]);
    assertEquals((byte) 0, byteArray[41]);
    assertEquals((byte) 0, byteArray[42]);
    assertEquals((byte) 0, byteArray[43]);
    assertEquals((byte) 0, byteArray[52]);
    assertEquals((byte) 0, byteArray[53]);
    assertEquals((byte) 0, byteArray[54]);
    assertEquals((byte) 0, byteArray[55]);
    assertEquals((byte) 1, byteArray[17]);
    assertEquals((byte) 1, byteArray[Short.SIZE]);
    assertEquals(56, byteArray.length);
    assertEquals(Short.SIZE, actualType3Message.sessionKey.length);
    assertEquals('A', byteArray[44]);
    assertEquals('A', byteArray[46]);
    assertEquals('A', byteArray[48]);
    assertEquals('A', byteArray[50]);
    assertEquals('X', byteArray[45]);
    assertEquals('X', byteArray[47]);
    assertEquals('X', byteArray[49]);
    assertEquals('X', byteArray[51]);
  }

  /**
   * Test Type3Message
   * {@link Type3Message#Type3Message(String, String, String, String, byte[], int, String, byte[])}.
   * <ul>
   *   <li>When one.</li>
   *   <li>Then return {@link Type3Message#type2Flags} is one.</li>
   * </ul>
   * <p>
   * Method under test:
   * {@link NtlmEngine.Type3Message#Type3Message(String, String, String, String, byte[], int, String, byte[])}
   */
  @Test
  @DisplayName("Test Type3Message new Type3Message(String, String, String, String, byte[], int, String, byte[]); when one; then return type2Flags is one")
  void testType3MessageNewType3Message_whenOne_thenReturnType2FlagsIsOne() throws UnsupportedEncodingException {
    // Arrange
    byte[] nonce = "AXAXAXAX".getBytes("UTF-8");

    // Act
    NtlmEngine.Type3Message actualType3Message = new NtlmEngine.Type3Message("https://example.org/example",
        "https://example.org/example", "https://example.org/example", "https://example.org/example", nonce, 1,
        "https://example.org/example", "AXAXAXAX".getBytes("UTF-8"));

    // Assert
    assertEquals("TlRMTVNTUAADAAAAGAAYAEgAAAAYABgAYAAAAB4AHgB4AAAANgA2AJYAAAAeAB4AzAAAAAAAAADqAAAAAQAAAgUBKAoAAAAP4251"
        + "aKwCYRTRZL1wscPO1olAjLz9MrB2vcqJ1jdQ75Be/MAOzfutLb31QSkwSOqNSABUAFQAUABTADoALwAvAEUAWABBAE0AUABMAEUA"
        + "aAB0AHQAcABzADoALwAvAGUAeABhAG0AcABsAGUALgBvAHIAZwAvAGUAeABhAG0AcABsAGUASABUAFQAUABTADoALwAvAEUAWABB"
        + "AE0AUABMAEUA", actualType3Message.getResponse());
    assertEquals(1, actualType3Message.type2Flags);
    assertArrayEquals(new byte[]{-29, 'n', 'u', 'h', -84, 2, 'a', 20, -47, 'd', -67, 'p', -79, -61, -50, -42, -119, '@',
        -116, -68, -3, '2', -80, 'v'}, actualType3Message.lmResp);
    assertArrayEquals(new byte[]{-67, -54, -119, -42, '7', 'P', -17, -112, '^', -4, -64, 14, -51, -5, -83, '-', -67,
        -11, 'A', ')', '0', 'H', -22, -115}, actualType3Message.ntResp);
  }

  /**
   * Test Type3Message
   * {@link Type3Message#Type3Message(String, String, String, String, byte[], int, String, byte[])}.
   * <ul>
   *   <li>When {@link Short#SIZE}.</li>
   *   <li>Then return {@link Type3Message#type2Flags} is {@link Short#SIZE}.</li>
   * </ul>
   * <p>
   * Method under test:
   * {@link NtlmEngine.Type3Message#Type3Message(String, String, String, String, byte[], int, String, byte[])}
   */
  @Test
  @DisplayName("Test Type3Message new Type3Message(String, String, String, String, byte[], int, String, byte[]); when SIZE; then return type2Flags is SIZE")
  void testType3MessageNewType3Message_whenSize_thenReturnType2FlagsIsSize() throws UnsupportedEncodingException {
    // Arrange
    byte[] nonce = "AXAXAXAX".getBytes("UTF-8");

    // Act
    NtlmEngine.Type3Message actualType3Message = new NtlmEngine.Type3Message("https://example.org/example",
        "https://example.org/example", "https://example.org/example", "https://example.org/example", nonce, Short.SIZE,
        "https://example.org/example", "AXAXAXAX".getBytes("UTF-8"));

    // Assert
    assertEquals("TlRMTVNTUAADAAAAGAAYAEgAAAAYABgAYAAAAB4AHgB4AAAANgA2AJYAAAAeAB4AzAAAABAAEADqAAAAEAAAAgUBKAoAAAAP4251"
        + "aKwCYRTRZL1wscPO1olAjLz9MrB2vcqJ1jdQ75Be/MAOzfutLb31QSkwSOqNSABUAFQAUABTADoALwAvAEUAWABBAE0AUABMAEUA"
        + "aAB0AHQAcABzADoALwAvAGUAeABhAG0AcABsAGUALgBvAHIAZwAvAGUAeABhAG0AcABsAGUASABUAFQAUABTADoALwAvAEUAWABB"
        + "AE0AUABMAEUAKT9T7Xem0v9F5GnYz7IS8Q==", actualType3Message.getResponse());
    assertEquals(Short.SIZE, actualType3Message.type2Flags);
    assertArrayEquals(new byte[]{')', '?', 'S', -19, 'w', -90, -46, -1, 'E', -28, 'i', -40, -49, -78, 18, -15},
        actualType3Message.sessionKey);
  }

  /**
   * Test {@link NtlmEngine#writeULong(byte[], int, int)}.
   * <ul>
   *   <li>When {@code AXAXAXAX} Bytes is {@code UTF-8}.</li>
   *   <li>Then fourth element is zero.</li>
   * </ul>
   * <p>
   * Method under test: {@link NtlmEngine#writeULong(byte[], int, int)}
   */
  @Test
  @DisplayName("Test writeULong(byte[], int, int); when 'AXAXAXAX' Bytes is 'UTF-8'; then fourth element is zero")
  void testWriteULong_whenAxaxaxaxBytesIsUtf8_thenFourthElementIsZero() throws UnsupportedEncodingException {
    // Arrange
    byte[] buffer = "AXAXAXAX".getBytes("UTF-8");

    // Act
    NtlmEngine.writeULong(buffer, 42, 2);

    // Assert
    assertEquals((byte) 0, buffer[3]);
    assertEquals((byte) 0, buffer[4]);
    assertEquals((byte) 0, buffer[5]);
    assertEquals(8, buffer.length);
    assertEquals('*', buffer[2]);
  }

  /**
   * Test {@link NtlmEngine#F(int, int, int)}.
   * <ul>
   *   <li>When minus one.</li>
   * </ul>
   * <p>
   * Method under test: {@link NtlmEngine#F(int, int, int)}
   */
  @Test
  @DisplayName("Test F(int, int, int); when minus one")
  void testF_whenMinusOne() {
    // Arrange, Act and Assert
    assertEquals(3, NtlmEngine.F(-1, 3, 3));
  }

  /**
   * Test {@link NtlmEngine#F(int, int, int)}.
   * <ul>
   *   <li>When one.</li>
   * </ul>
   * <p>
   * Method under test: {@link NtlmEngine#F(int, int, int)}
   */
  @Test
  @DisplayName("Test F(int, int, int); when one")
  void testF_whenOne() {
    // Arrange, Act and Assert
    assertEquals(3, NtlmEngine.F(1, 3, 3));
  }

  /**
   * Test {@link NtlmEngine#F(int, int, int)}.
   * <ul>
   *   <li>When three.</li>
   * </ul>
   * <p>
   * Method under test: {@link NtlmEngine#F(int, int, int)}
   */
  @Test
  @DisplayName("Test F(int, int, int); when three")
  void testF_whenThree() {
    // Arrange, Act and Assert
    assertEquals(3, NtlmEngine.F(3, 3, 3));
  }

  /**
   * Test {@link NtlmEngine#F(int, int, int)}.
   * <ul>
   *   <li>When two.</li>
   * </ul>
   * <p>
   * Method under test: {@link NtlmEngine#F(int, int, int)}
   */
  @Test
  @DisplayName("Test F(int, int, int); when two")
  void testF_whenTwo() {
    // Arrange, Act and Assert
    assertEquals(3, NtlmEngine.F(2, 3, 3));
  }

  /**
   * Test {@link NtlmEngine#G(int, int, int)}.
   * <ul>
   *   <li>When one.</li>
   * </ul>
   * <p>
   * Method under test: {@link NtlmEngine#G(int, int, int)}
   */
  @Test
  @DisplayName("Test G(int, int, int); when one")
  void testG_whenOne() {
    // Arrange, Act and Assert
    assertEquals(3, NtlmEngine.G(1, 3, 3));
  }

  /**
   * Test {@link NtlmEngine#G(int, int, int)}.
   * <ul>
   *   <li>When three.</li>
   * </ul>
   * <p>
   * Method under test: {@link NtlmEngine#G(int, int, int)}
   */
  @Test
  @DisplayName("Test G(int, int, int); when three")
  void testG_whenThree() {
    // Arrange, Act and Assert
    assertEquals(3, NtlmEngine.G(3, 3, 3));
  }

  /**
   * Test {@link NtlmEngine#G(int, int, int)}.
   * <ul>
   *   <li>When two.</li>
   * </ul>
   * <p>
   * Method under test: {@link NtlmEngine#G(int, int, int)}
   */
  @Test
  @DisplayName("Test G(int, int, int); when two")
  void testG_whenTwo() {
    // Arrange, Act and Assert
    assertEquals(3, NtlmEngine.G(2, 3, 3));
  }

  /**
   * Test {@link NtlmEngine#G(int, int, int)}.
   * <ul>
   *   <li>When zero.</li>
   * </ul>
   * <p>
   * Method under test: {@link NtlmEngine#G(int, int, int)}
   */
  @Test
  @DisplayName("Test G(int, int, int); when zero")
  void testG_whenZero() {
    // Arrange, Act and Assert
    assertEquals(3, NtlmEngine.G(0, 3, 3));
  }

  /**
   * Test {@link NtlmEngine#H(int, int, int)}.
   * <ul>
   *   <li>When one.</li>
   *   <li>Then return one.</li>
   * </ul>
   * <p>
   * Method under test: {@link NtlmEngine#H(int, int, int)}
   */
  @Test
  @DisplayName("Test H(int, int, int); when one; then return one")
  void testH_whenOne_thenReturnOne() {
    // Arrange, Act and Assert
    assertEquals(1, NtlmEngine.H(1, 3, 3));
  }

  /**
   * Test {@link NtlmEngine#H(int, int, int)}.
   * <ul>
   *   <li>When three.</li>
   *   <li>Then return three.</li>
   * </ul>
   * <p>
   * Method under test: {@link NtlmEngine#H(int, int, int)}
   */
  @Test
  @DisplayName("Test H(int, int, int); when three; then return three")
  void testH_whenThree_thenReturnThree() {
    // Arrange, Act and Assert
    assertEquals(3, NtlmEngine.H(3, 3, 3));
  }

  /**
   * Test {@link NtlmEngine#H(int, int, int)}.
   * <ul>
   *   <li>When two.</li>
   *   <li>Then return two.</li>
   * </ul>
   * <p>
   * Method under test: {@link NtlmEngine#H(int, int, int)}
   */
  @Test
  @DisplayName("Test H(int, int, int); when two; then return two")
  void testH_whenTwo_thenReturnTwo() {
    // Arrange, Act and Assert
    assertEquals(2, NtlmEngine.H(2, 3, 3));
  }

  /**
   * Test {@link NtlmEngine#H(int, int, int)}.
   * <ul>
   *   <li>When zero.</li>
   *   <li>Then return zero.</li>
   * </ul>
   * <p>
   * Method under test: {@link NtlmEngine#H(int, int, int)}
   */
  @Test
  @DisplayName("Test H(int, int, int); when zero; then return zero")
  void testH_whenZero_thenReturnZero() {
    // Arrange, Act and Assert
    assertEquals(0, NtlmEngine.H(0, 3, 3));
  }

  /**
   * Test {@link NtlmEngine#rotintlft(int, int)}.
   * <ul>
   *   <li>When forty-two.</li>
   *   <li>Then return {@code 43008}.</li>
   * </ul>
   * <p>
   * Method under test: {@link NtlmEngine#rotintlft(int, int)}
   */
  @Test
  @DisplayName("Test rotintlft(int, int); when forty-two; then return '43008'")
  void testRotintlft_whenFortyTwo_thenReturn43008() {
    // Arrange, Act and Assert
    assertEquals(43008, NtlmEngine.rotintlft(42, 10));
  }

  /**
   * Test {@link NtlmEngine#rotintlft(int, int)}.
   * <ul>
   *   <li>When one.</li>
   *   <li>Then return {@code 1024}.</li>
   * </ul>
   * <p>
   * Method under test: {@link NtlmEngine#rotintlft(int, int)}
   */
  @Test
  @DisplayName("Test rotintlft(int, int); when one; then return '1024'")
  void testRotintlft_whenOne_thenReturn1024() {
    // Arrange, Act and Assert
    assertEquals(1024, NtlmEngine.rotintlft(1, 10));
  }

  /**
   * Test {@link NtlmEngine#rotintlft(int, int)}.
   * <ul>
   *   <li>When {@link Integer#SIZE}.</li>
   *   <li>Then return {@code 32768}.</li>
   * </ul>
   * <p>
   * Method under test: {@link NtlmEngine#rotintlft(int, int)}
   */
  @Test
  @DisplayName("Test rotintlft(int, int); when SIZE; then return '32768'")
  void testRotintlft_whenSize_thenReturn32768() {
    // Arrange, Act and Assert
    assertEquals(32768, NtlmEngine.rotintlft(Integer.SIZE, 10));
  }

  /**
   * Test {@link NtlmEngine#rotintlft(int, int)}.
   * <ul>
   *   <li>When three.</li>
   *   <li>Then return {@code 3072}.</li>
   * </ul>
   * <p>
   * Method under test: {@link NtlmEngine#rotintlft(int, int)}
   */
  @Test
  @DisplayName("Test rotintlft(int, int); when three; then return '3072'")
  void testRotintlft_whenThree_thenReturn3072() {
    // Arrange, Act and Assert
    assertEquals(3072, NtlmEngine.rotintlft(3, 10));
  }

  /**
   * Test {@link NtlmEngine#generateType1Msg()}.
   * <p>
   * Method under test: {@link NtlmEngine#generateType1Msg()}
   */
  @Test
  @DisplayName("Test generateType1Msg()")
  void testGenerateType1Msg() {
    // Arrange, Act and Assert
    assertEquals("TlRMTVNTUAABAAAAAYIIogAAAAAoAAAAAAAAACgAAAAFASgKAAAADw==", NtlmEngine.INSTANCE.generateType1Msg());
  }
}
