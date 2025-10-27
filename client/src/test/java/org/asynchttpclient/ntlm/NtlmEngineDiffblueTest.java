package org.asynchttpclient.ntlm;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import java.io.UnsupportedEncodingException;
import org.junit.jupiter.api.Test;

class NtlmEngineDiffblueTest {
  /**
   * Method under test: {@link NtlmEngine.Type3Message#getResponse()}
   */
  @Test
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
   * Method under test: {@link NtlmEngine.Type3Message#getResponse()}
   */
  @Test
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
   * Method under test: {@link NtlmEngine.Type3Message#getResponse()}
   */
  @Test
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
   * Method under test: {@link NtlmEngine.Type3Message#getResponse()}
   */
  @Test
  void testType3MessageGetResponse4() throws UnsupportedEncodingException {
    // Arrange
    byte[] nonce = "AXAXAXAX".getBytes("UTF-8");
    NtlmEngine.Type3Message type3Message = new NtlmEngine.Type3Message("https://example.org/example",
        "https://example.org/example", "https://example.org/example", "https://example.org/example", nonce, -1,
        "https://example.org/example", "AXAXAXAX".getBytes("UTF-8"));

    // Act
    type3Message.getResponse();

    // Assert
    assertEquals(24, type3Message.lmResp.length);
  }

  /**
   * Method under test:
   * {@link NtlmEngine.Type3Message#Type3Message(String, String, String, String, byte[], int, String, byte[])}
   */
  @Test
  void testType3MessageNewType3Message() throws UnsupportedEncodingException {
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
    assertNull(actualType3Message.sessionKey);
    assertEquals(1, actualType3Message.type2Flags);
    assertArrayEquals(new byte[]{-29, 'n', 'u', 'h', -84, 2, 'a', 20, -47, 'd', -67, 'p', -79, -61, -50, -42, -119, '@',
        -116, -68, -3, '2', -80, 'v'}, actualType3Message.lmResp);
    assertArrayEquals(new byte[]{-67, -54, -119, -42, '7', 'P', -17, -112, '^', -4, -64, 14, -51, -5, -83, '-', -67,
        -11, 'A', ')', '0', 'H', -22, -115}, actualType3Message.ntResp);
    assertArrayEquals(new byte[]{'H', 0, 'T', 0, 'T', 0, 'P', 0, 'S', 0, ':', 0, '/', 0, '/', 0, 'E', 0, 'X', 0, 'A', 0,
        'M', 0, 'P', 0, 'L', 0, 'E', 0}, actualType3Message.domainBytes);
    assertArrayEquals(new byte[]{'H', 0, 'T', 0, 'T', 0, 'P', 0, 'S', 0, ':', 0, '/', 0, '/', 0, 'E', 0, 'X', 0, 'A', 0,
        'M', 0, 'P', 0, 'L', 0, 'E', 0}, actualType3Message.hostBytes);
    assertArrayEquals(new byte[]{'h', 0, 't', 0, 't', 0, 'p', 0, 's', 0, ':', 0, '/', 0, '/', 0, 'e', 0, 'x', 0, 'a', 0,
        'm', 0, 'p', 0, 'l', 0, 'e', 0, '.', 0, 'o', 0, 'r', 0, 'g', 0, '/', 0, 'e', 0, 'x', 0, 'a', 0, 'm', 0, 'p', 0,
        'l', 0, 'e', 0}, actualType3Message.userBytes);
  }

  /**
   * Method under test:
   * {@link NtlmEngine.Type3Message#Type3Message(String, String, String, String, byte[], int, String, byte[])}
   */
  @Test
  void testType3MessageNewType3Message2() {
    // Arrange and Act
    NtlmEngine.Type3Message actualType3Message = new NtlmEngine.Type3Message(null, null, "https://example.org/example",
        "https://example.org/example", new byte[]{'A', 'X', 'A', 'X', 'A', 'X', 'A', 'X'}, 1, null, null);

    // Assert
    assertEquals("TlRMTVNTUAADAAAAGAAYAEgAAAAYABgAYAAAAAAAAAB4AAAANgA2AHgAAAAAAAAArgAAAAAAAACuAAAAAQAAAgUBKAoAAAAP4251"
        + "aKwCYRTRZL1wscPO1olAjLz9MrB2vcqJ1jdQ75Be/MAOzfutLb31QSkwSOqNaAB0AHQAcABzADoALwAvAGUAeABhAG0AcABsAGUA"
        + "LgBvAHIAZwAvAGUAeABhAG0AcABsAGUA", actualType3Message.getResponse());
    assertNull(actualType3Message.domainBytes);
    assertNull(actualType3Message.hostBytes);
    assertNull(actualType3Message.sessionKey);
    assertEquals(1, actualType3Message.type2Flags);
    assertArrayEquals(new byte[]{-29, 'n', 'u', 'h', -84, 2, 'a', 20, -47, 'd', -67, 'p', -79, -61, -50, -42, -119, '@',
        -116, -68, -3, '2', -80, 'v'}, actualType3Message.lmResp);
    assertArrayEquals(new byte[]{-67, -54, -119, -42, '7', 'P', -17, -112, '^', -4, -64, 14, -51, -5, -83, '-', -67,
        -11, 'A', ')', '0', 'H', -22, -115}, actualType3Message.ntResp);
    assertArrayEquals(new byte[]{'h', 0, 't', 0, 't', 0, 'p', 0, 's', 0, ':', 0, '/', 0, '/', 0, 'e', 0, 'x', 0, 'a', 0,
        'm', 0, 'p', 0, 'l', 0, 'e', 0, '.', 0, 'o', 0, 'r', 0, 'g', 0, '/', 0, 'e', 0, 'x', 0, 'a', 0, 'm', 0, 'p', 0,
        'l', 0, 'e', 0}, actualType3Message.userBytes);
  }

  /**
   * Method under test:
   * {@link NtlmEngine.Type3Message#Type3Message(String, String, String, String, byte[], int, String, byte[])}
   */
  @Test
  void testType3MessageNewType3Message3() throws UnsupportedEncodingException {
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
    assertNull(actualType3Message.sessionKey);
    assertEquals(1, actualType3Message.type2Flags);
    assertArrayEquals(new byte[]{'D', 0, 'E', 0, 'S', 0}, actualType3Message.domainBytes);
    assertArrayEquals(new byte[]{-29, 'n', 'u', 'h', -84, 2, 'a', 20, -47, 'd', -67, 'p', -79, -61, -50, -42, -119, '@',
        -116, -68, -3, '2', -80, 'v'}, actualType3Message.lmResp);
    assertArrayEquals(new byte[]{-67, -54, -119, -42, '7', 'P', -17, -112, '^', -4, -64, 14, -51, -5, -83, '-', -67,
        -11, 'A', ')', '0', 'H', -22, -115}, actualType3Message.ntResp);
    assertArrayEquals(new byte[]{'H', 0, 'T', 0, 'T', 0, 'P', 0, 'S', 0, ':', 0, '/', 0, '/', 0, 'E', 0, 'X', 0, 'A', 0,
        'M', 0, 'P', 0, 'L', 0, 'E', 0}, actualType3Message.hostBytes);
    assertArrayEquals(new byte[]{'h', 0, 't', 0, 't', 0, 'p', 0, 's', 0, ':', 0, '/', 0, '/', 0, 'e', 0, 'x', 0, 'a', 0,
        'm', 0, 'p', 0, 'l', 0, 'e', 0, '.', 0, 'o', 0, 'r', 0, 'g', 0, '/', 0, 'e', 0, 'x', 0, 'a', 0, 'm', 0, 'p', 0,
        'l', 0, 'e', 0}, actualType3Message.userBytes);
  }

  /**
   * Method under test:
   * {@link NtlmEngine.Type3Message#Type3Message(String, String, String, String, byte[], int, String, byte[])}
   */
  @Test
  void testType3MessageNewType3Message4() throws UnsupportedEncodingException {
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
    assertEquals(24, actualType3Message.lmResp.length);
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
    assertArrayEquals(new byte[]{'H', 0, 'T', 0, 'T', 0, 'P', 0, 'S', 0, ':', 0, '/', 0, '/', 0, 'E', 0, 'X', 0, 'A', 0,
        'M', 0, 'P', 0, 'L', 0, 'E', 0}, actualType3Message.domainBytes);
    assertArrayEquals(new byte[]{'H', 0, 'T', 0, 'T', 0, 'P', 0, 'S', 0, ':', 0, '/', 0, '/', 0, 'E', 0, 'X', 0, 'A', 0,
        'M', 0, 'P', 0, 'L', 0, 'E', 0}, actualType3Message.hostBytes);
    assertArrayEquals(new byte[]{'h', 0, 't', 0, 't', 0, 'p', 0, 's', 0, ':', 0, '/', 0, '/', 0, 'e', 0, 'x', 0, 'a', 0,
        'm', 0, 'p', 0, 'l', 0, 'e', 0, '.', 0, 'o', 0, 'r', 0, 'g', 0, '/', 0, 'e', 0, 'x', 0, 'a', 0, 'm', 0, 'p', 0,
        'l', 0, 'e', 0}, actualType3Message.userBytes);
  }

  /**
   * Method under test:
   * {@link NtlmEngine.Type3Message#Type3Message(String, String, String, String, byte[], int, String, byte[])}
   */
  @Test
  void testType3MessageNewType3Message5() throws UnsupportedEncodingException {
    // Arrange
    byte[] nonce = "AXAXAXAX".getBytes("UTF-8");

    // Act
    NtlmEngine.Type3Message actualType3Message = new NtlmEngine.Type3Message("https://example.org/example",
        "https://example.org/example", "https://example.org/example", "https://example.org/example", nonce, 8388608,
        "https://example.org/example", "AXAXAXAX".getBytes("UTF-8"));

    // Assert
    assertNull(actualType3Message.sessionKey);
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
    assertEquals(24, actualType3Message.lmResp.length);
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
    assertArrayEquals(new byte[]{'H', 0, 'T', 0, 'T', 0, 'P', 0, 'S', 0, ':', 0, '/', 0, '/', 0, 'E', 0, 'X', 0, 'A', 0,
        'M', 0, 'P', 0, 'L', 0, 'E', 0}, actualType3Message.domainBytes);
    assertArrayEquals(new byte[]{'H', 0, 'T', 0, 'T', 0, 'P', 0, 'S', 0, ':', 0, '/', 0, '/', 0, 'E', 0, 'X', 0, 'A', 0,
        'M', 0, 'P', 0, 'L', 0, 'E', 0}, actualType3Message.hostBytes);
    assertArrayEquals(new byte[]{'h', 0, 't', 0, 't', 0, 'p', 0, 's', 0, ':', 0, '/', 0, '/', 0, 'e', 0, 'x', 0, 'a', 0,
        'm', 0, 'p', 0, 'l', 0, 'e', 0, '.', 0, 'o', 0, 'r', 0, 'g', 0, '/', 0, 'e', 0, 'x', 0, 'a', 0, 'm', 0, 'p', 0,
        'l', 0, 'e', 0}, actualType3Message.userBytes);
  }

  /**
   * Method under test:
   * {@link NtlmEngine.Type3Message#Type3Message(String, String, String, String, byte[], int, String, byte[])}
   */
  @Test
  void testType3MessageNewType3Message6() throws UnsupportedEncodingException {
    // Arrange
    byte[] nonce = "AXAXAXAX".getBytes("UTF-8");

    // Act
    NtlmEngine.Type3Message actualType3Message = new NtlmEngine.Type3Message("https://example.org/example",
        "https://example.org/example", "https://example.org/example", "https://example.org/example", nonce, 524288,
        "https://example.org/example", "AXAXAXAX".getBytes("UTF-8"));

    // Assert
    assertNull(actualType3Message.sessionKey);
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
    assertEquals(24, actualType3Message.ntResp.length);
    assertEquals(524288, actualType3Message.type2Flags);
    assertArrayEquals(new byte[]{'H', 0, 'T', 0, 'T', 0, 'P', 0, 'S', 0, ':', 0, '/', 0, '/', 0, 'E', 0, 'X', 0, 'A', 0,
        'M', 0, 'P', 0, 'L', 0, 'E', 0}, actualType3Message.domainBytes);
    assertArrayEquals(new byte[]{'H', 0, 'T', 0, 'T', 0, 'P', 0, 'S', 0, ':', 0, '/', 0, '/', 0, 'E', 0, 'X', 0, 'A', 0,
        'M', 0, 'P', 0, 'L', 0, 'E', 0}, actualType3Message.hostBytes);
    assertArrayEquals(new byte[]{'h', 0, 't', 0, 't', 0, 'p', 0, 's', 0, ':', 0, '/', 0, '/', 0, 'e', 0, 'x', 0, 'a', 0,
        'm', 0, 'p', 0, 'l', 0, 'e', 0, '.', 0, 'o', 0, 'r', 0, 'g', 0, '/', 0, 'e', 0, 'x', 0, 'a', 0, 'm', 0, 'p', 0,
        'l', 0, 'e', 0}, actualType3Message.userBytes);
  }

  /**
   * Method under test:
   * {@link NtlmEngine.Type3Message#Type3Message(String, String, String, String, byte[], int, String, byte[])}
   */
  @Test
  void testType3MessageNewType3Message7() throws UnsupportedEncodingException {
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
    assertArrayEquals(new byte[]{-29, 'n', 'u', 'h', -84, 2, 'a', 20, -47, 'd', -67, 'p', -79, -61, -50, -42, -119, '@',
        -116, -68, -3, '2', -80, 'v'}, actualType3Message.lmResp);
    assertArrayEquals(new byte[]{-67, -54, -119, -42, '7', 'P', -17, -112, '^', -4, -64, 14, -51, -5, -83, '-', -67,
        -11, 'A', ')', '0', 'H', -22, -115}, actualType3Message.ntResp);
    assertArrayEquals(new byte[]{'H', 0, 'T', 0, 'T', 0, 'P', 0, 'S', 0, ':', 0, '/', 0, '/', 0, 'E', 0, 'X', 0, 'A', 0,
        'M', 0, 'P', 0, 'L', 0, 'E', 0}, actualType3Message.domainBytes);
    assertArrayEquals(new byte[]{'H', 0, 'T', 0, 'T', 0, 'P', 0, 'S', 0, ':', 0, '/', 0, '/', 0, 'E', 0, 'X', 0, 'A', 0,
        'M', 0, 'P', 0, 'L', 0, 'E', 0}, actualType3Message.hostBytes);
    assertArrayEquals(new byte[]{'h', 0, 't', 0, 't', 0, 'p', 0, 's', 0, ':', 0, '/', 0, '/', 0, 'e', 0, 'x', 0, 'a', 0,
        'm', 0, 'p', 0, 'l', 0, 'e', 0, '.', 0, 'o', 0, 'r', 0, 'g', 0, '/', 0, 'e', 0, 'x', 0, 'a', 0, 'm', 0, 'p', 0,
        'l', 0, 'e', 0}, actualType3Message.userBytes);
  }

  /**
   * Method under test:
   * {@link NtlmEngine.Type3Message#Type3Message(String, String, String, String, byte[], int, String, byte[])}
   */
  @Test
  void testType3MessageNewType3Message8() throws UnsupportedEncodingException {
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
    assertNull(actualType3Message.sessionKey);
    assertEquals(128, actualType3Message.type2Flags);
    assertArrayEquals(new byte[]{-29, 'n', 'u', 'h', -84, 2, 'a', 20, -47, 'd', -67, 'p', -79, -61, -50, -42, -119, '@',
        -116, -68, -3, '2', -80, 'v'}, actualType3Message.lmResp);
    assertArrayEquals(new byte[]{-67, -54, -119, -42, '7', 'P', -17, -112, '^', -4, -64, 14, -51, -5, -83, '-', -67,
        -11, 'A', ')', '0', 'H', -22, -115}, actualType3Message.ntResp);
    assertArrayEquals(new byte[]{'H', 0, 'T', 0, 'T', 0, 'P', 0, 'S', 0, ':', 0, '/', 0, '/', 0, 'E', 0, 'X', 0, 'A', 0,
        'M', 0, 'P', 0, 'L', 0, 'E', 0}, actualType3Message.domainBytes);
    assertArrayEquals(new byte[]{'H', 0, 'T', 0, 'T', 0, 'P', 0, 'S', 0, ':', 0, '/', 0, '/', 0, 'E', 0, 'X', 0, 'A', 0,
        'M', 0, 'P', 0, 'L', 0, 'E', 0}, actualType3Message.hostBytes);
    assertArrayEquals(new byte[]{'h', 0, 't', 0, 't', 0, 'p', 0, 's', 0, ':', 0, '/', 0, '/', 0, 'e', 0, 'x', 0, 'a', 0,
        'm', 0, 'p', 0, 'l', 0, 'e', 0, '.', 0, 'o', 0, 'r', 0, 'g', 0, '/', 0, 'e', 0, 'x', 0, 'a', 0, 'm', 0, 'p', 0,
        'l', 0, 'e', 0}, actualType3Message.userBytes);
  }

  /**
   * Method under test: {@link NtlmEngine#writeULong(byte[], int, int)}
   */
  @Test
  void testWriteULong() throws UnsupportedEncodingException {
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
   * Method under test: {@link NtlmEngine#F(int, int, int)}
   */
  @Test
  void testF() {
    // Arrange, Act and Assert
    assertEquals(3, NtlmEngine.F(2, 3, 3));
    assertEquals(3, NtlmEngine.F(-1, 3, 3));
    assertEquals(3, NtlmEngine.F(3, 3, 3));
    assertEquals(3, NtlmEngine.F(1, 3, 3));
  }

  /**
   * Method under test: {@link NtlmEngine#G(int, int, int)}
   */
  @Test
  void testG() {
    // Arrange, Act and Assert
    assertEquals(3, NtlmEngine.G(2, 3, 3));
    assertEquals(3, NtlmEngine.G(3, 3, 3));
    assertEquals(3, NtlmEngine.G(1, 3, 3));
    assertEquals(3, NtlmEngine.G(0, 3, 3));
  }

  /**
   * Method under test: {@link NtlmEngine#H(int, int, int)}
   */
  @Test
  void testH() {
    // Arrange, Act and Assert
    assertEquals(2, NtlmEngine.H(2, 3, 3));
    assertEquals(3, NtlmEngine.H(3, 3, 3));
    assertEquals(1, NtlmEngine.H(1, 3, 3));
    assertEquals(0, NtlmEngine.H(0, 3, 3));
  }

  /**
   * Method under test: {@link NtlmEngine#rotintlft(int, int)}
   */
  @Test
  void testRotintlft() {
    // Arrange, Act and Assert
    assertEquals(43008, NtlmEngine.rotintlft(42, 10));
    assertEquals(32768, NtlmEngine.rotintlft(Integer.SIZE, 10));
    assertEquals(3072, NtlmEngine.rotintlft(3, 10));
    assertEquals(1024, NtlmEngine.rotintlft(1, 10));
  }

  /**
   * Method under test: {@link NtlmEngine#generateType1Msg()}
   */
  @Test
  void testGenerateType1Msg() {
    // Arrange, Act and Assert
    assertEquals("TlRMTVNTUAABAAAAAYIIogAAAAAoAAAAAAAAACgAAAAFASgKAAAADw==", NtlmEngine.INSTANCE.generateType1Msg());
  }
}
