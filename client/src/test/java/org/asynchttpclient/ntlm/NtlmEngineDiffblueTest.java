package org.asynchttpclient.ntlm;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.io.UnsupportedEncodingException;
import org.asynchttpclient.ntlm.NtlmEngine.Type3Message;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

class NtlmEngineDiffblueTest {
  /**
   * Test Type3Message {@link Type3Message#getResponse()}.
   * <p>
   * Method under test: {@link Type3Message#getResponse()}
   */
  @Test
  @DisplayName("Test Type3Message getResponse()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"String Type3Message.getResponse()"})
  void testType3MessageGetResponse() throws UnsupportedEncodingException {
    // Arrange
    byte[] nonce = "AXAXAXAX".getBytes("UTF-8");

    // Act and Assert
    assertEquals(
        "TlRMTVNTUAADAAAAGAAYAEgAAAAYABgAYAAAAB4AHgB4AAAANgA2AJYAAAAeAB4AzAAAAAAAAADqAAAAAQAAAgUBKAoAAAAP4251"
            + "aKwCYRTRZL1wscPO1olAjLz9MrB2vcqJ1jdQ75Be/MAOzfutLb31QSkwSOqNSABUAFQAUABTADoALwAvAEUAWABBAE0AUABMAEUA"
            + "aAB0AHQAcABzADoALwAvAGUAeABhAG0AcABsAGUALgBvAHIAZwAvAGUAeABhAG0AcABsAGUASABUAFQAUABTADoALwAvAEUAWABB"
            + "AE0AUABMAEUA",
        (new Type3Message("https://example.org/example", "https://example.org/example", "https://example.org/example",
            "https://example.org/example", nonce, 1, "https://example.org/example", "AXAXAXAX".getBytes("UTF-8")))
                .getResponse());
  }

  /**
   * Test Type3Message {@link Type3Message#getResponse()}.
   * <p>
   * Method under test: {@link Type3Message#getResponse()}
   */
  @Test
  @DisplayName("Test Type3Message getResponse()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"String Type3Message.getResponse()"})
  void testType3MessageGetResponse2() throws UnsupportedEncodingException {
    // Arrange
    byte[] nonce = "AXAXAXAX".getBytes("UTF-8");

    // Act and Assert
    assertEquals(
        "TlRMTVNTUAADAAAAGAAYAEgAAAAYABgAYAAAAAAAAAB4AAAANgA2AHgAAAAeAB4ArgAAAAAAAADMAAAAAQAAAgUBKAoAAAAP4251"
            + "aKwCYRTRZL1wscPO1olAjLz9MrB2vcqJ1jdQ75Be/MAOzfutLb31QSkwSOqNaAB0AHQAcABzADoALwAvAGUAeABhAG0AcABsAGUA"
            + "LgBvAHIAZwAvAGUAeABhAG0AcABsAGUASABUAFQAUABTADoALwAvAEUAWABBAE0AUABMAEUA",
        (new Type3Message(null, "https://example.org/example", "https://example.org/example",
            "https://example.org/example", nonce, 1, "https://example.org/example", "AXAXAXAX".getBytes("UTF-8")))
                .getResponse());
  }

  /**
   * Test Type3Message {@link Type3Message#getResponse()}.
   * <p>
   * Method under test: {@link Type3Message#getResponse()}
   */
  @Test
  @DisplayName("Test Type3Message getResponse()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"String Type3Message.getResponse()"})
  void testType3MessageGetResponse3() throws UnsupportedEncodingException {
    // Arrange
    byte[] nonce = "AXAXAXAX".getBytes("UTF-8");

    // Act and Assert
    assertEquals(
        "TlRMTVNTUAADAAAAGAAYAEgAAAAYABgAYAAAAB4AHgB4AAAANgA2AJYAAAAAAAAAzAAAAAAAAADMAAAAAQAAAgUBKAoAAAAP4251"
            + "aKwCYRTRZL1wscPO1olAjLz9MrB2vcqJ1jdQ75Be/MAOzfutLb31QSkwSOqNSABUAFQAUABTADoALwAvAEUAWABBAE0AUABMAEUA"
            + "aAB0AHQAcABzADoALwAvAGUAeABhAG0AcABsAGUALgBvAHIAZwAvAGUAeABhAG0AcABsAGUA",
        (new Type3Message("https://example.org/example", null, "https://example.org/example",
            "https://example.org/example", nonce, 1, "https://example.org/example", "AXAXAXAX".getBytes("UTF-8")))
                .getResponse());
  }

  /**
   * Test Type3Message {@link Type3Message#Type3Message(String, String, String, String, byte[], int, String, byte[])}.
   * <ul>
   *   <li>Then return {@link Type3Message#domainBytes} is array of {@code byte} with {@code D} and zero.</li>
   * </ul>
   * <p>
   * Method under test: {@link Type3Message#Type3Message(String, String, String, String, byte[], int, String, byte[])}
   */
  @Test
  @DisplayName("Test Type3Message new Type3Message(String, String, String, String, byte[], int, String, byte[]); then return domainBytes is array of byte with 'D' and zero")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void Type3Message.<init>(String, String, String, String, byte[], int, String, byte[])"})
  void testType3MessageNewType3Message_thenReturnDomainBytesIsArrayOfByteWithDAndZero()
      throws UnsupportedEncodingException {
    // Arrange
    byte[] nonce = "AXAXAXAX".getBytes("UTF-8");

    // Act
    Type3Message actualType3Message = new Type3Message("DES", "https://example.org/example",
        "https://example.org/example", "https://example.org/example", nonce, 1, "https://example.org/example",
        "AXAXAXAX".getBytes("UTF-8"));

    // Assert
    assertEquals(
        "TlRMTVNTUAADAAAAGAAYAEgAAAAYABgAYAAAAAYABgB4AAAANgA2AH4AAAAeAB4AtAAAAAAAAADSAAAAAQAAAgUBKAoAAAAP4251"
            + "aKwCYRTRZL1wscPO1olAjLz9MrB2vcqJ1jdQ75Be/MAOzfutLb31QSkwSOqNRABFAFMAaAB0AHQAcABzADoALwAvAGUAeABhAG0A"
            + "cABsAGUALgBvAHIAZwAvAGUAeABhAG0AcABsAGUASABUAFQAUABTADoALwAvAEUAWABBAE0AUABMAEUA",
        actualType3Message.getResponse());
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
   * Test Type3Message {@link Type3Message#Type3Message(String, String, String, String, byte[], int, String, byte[])}.
   * <ul>
   *   <li>Then return {@link Type3Message#type2Flags} is one hundred twenty-eight.</li>
   * </ul>
   * <p>
   * Method under test: {@link Type3Message#Type3Message(String, String, String, String, byte[], int, String, byte[])}
   */
  @Test
  @DisplayName("Test Type3Message new Type3Message(String, String, String, String, byte[], int, String, byte[]); then return type2Flags is one hundred twenty-eight")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void Type3Message.<init>(String, String, String, String, byte[], int, String, byte[])"})
  void testType3MessageNewType3Message_thenReturnType2FlagsIsOneHundredTwentyEight()
      throws UnsupportedEncodingException {
    // Arrange
    byte[] nonce = "AXAXAXAX".getBytes("UTF-8");

    // Act
    Type3Message actualType3Message = new Type3Message("https://example.org/example", "https://example.org/example",
        "https://example.org/example", "https://example.org/example", nonce, 128, "https://example.org/example",
        "AXAXAXAX".getBytes("UTF-8"));

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
    assertArrayEquals(new byte[]{'H', 0, 'T', 0, 'T', 0, 'P', 0, 'S', 0, ':', 0, '/', 0, '/', 0, 'E', 0, 'X', 0, 'A', 0,
        'M', 0, 'P', 0, 'L', 0, 'E', 0}, actualType3Message.domainBytes);
    assertArrayEquals(new byte[]{'H', 0, 'T', 0, 'T', 0, 'P', 0, 'S', 0, ':', 0, '/', 0, '/', 0, 'E', 0, 'X', 0, 'A', 0,
        'M', 0, 'P', 0, 'L', 0, 'E', 0}, actualType3Message.hostBytes);
    assertArrayEquals(new byte[]{'h', 0, 't', 0, 't', 0, 'p', 0, 's', 0, ':', 0, '/', 0, '/', 0, 'e', 0, 'x', 0, 'a', 0,
        'm', 0, 'p', 0, 'l', 0, 'e', 0, '.', 0, 'o', 0, 'r', 0, 'g', 0, '/', 0, 'e', 0, 'x', 0, 'a', 0, 'm', 0, 'p', 0,
        'l', 0, 'e', 0}, actualType3Message.userBytes);
  }

  /**
   * Test Type3Message {@link Type3Message#Type3Message(String, String, String, String, byte[], int, String, byte[])}.
   * <ul>
   *   <li>When {@code 524288}.</li>
   *   <li>Then return {@link Type3Message#type2Flags} is {@code 524288}.</li>
   * </ul>
   * <p>
   * Method under test: {@link Type3Message#Type3Message(String, String, String, String, byte[], int, String, byte[])}
   */
  @Test
  @DisplayName("Test Type3Message new Type3Message(String, String, String, String, byte[], int, String, byte[]); when '524288'; then return type2Flags is '524288'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void Type3Message.<init>(String, String, String, String, byte[], int, String, byte[])"})
  void testType3MessageNewType3Message_when524288_thenReturnType2FlagsIs524288() throws UnsupportedEncodingException {
    // Arrange
    byte[] nonce = "AXAXAXAX".getBytes("UTF-8");

    // Act
    Type3Message actualType3Message = new Type3Message("https://example.org/example", "https://example.org/example",
        "https://example.org/example", "https://example.org/example", nonce, 524288, "https://example.org/example",
        "AXAXAXAX".getBytes("UTF-8"));

    // Assert
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
   * Test Type3Message {@link Type3Message#Type3Message(String, String, String, String, byte[], int, String, byte[])}.
   * <ul>
   *   <li>When {@code 8388608}.</li>
   *   <li>Then return {@link Type3Message#type2Flags} is {@code 8388608}.</li>
   * </ul>
   * <p>
   * Method under test: {@link Type3Message#Type3Message(String, String, String, String, byte[], int, String, byte[])}
   */
  @Test
  @DisplayName("Test Type3Message new Type3Message(String, String, String, String, byte[], int, String, byte[]); when '8388608'; then return type2Flags is '8388608'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void Type3Message.<init>(String, String, String, String, byte[], int, String, byte[])"})
  void testType3MessageNewType3Message_when8388608_thenReturnType2FlagsIs8388608() throws UnsupportedEncodingException {
    // Arrange
    byte[] nonce = "AXAXAXAX".getBytes("UTF-8");

    // Act
    Type3Message actualType3Message = new Type3Message("https://example.org/example", "https://example.org/example",
        "https://example.org/example", "https://example.org/example", nonce, 8388608, "https://example.org/example",
        "AXAXAXAX".getBytes("UTF-8"));

    // Assert
    assertEquals(8388608, actualType3Message.type2Flags);
    assertArrayEquals(new byte[]{'H', 0, 'T', 0, 'T', 0, 'P', 0, 'S', 0, ':', 0, '/', 0, '/', 0, 'E', 0, 'X', 0, 'A', 0,
        'M', 0, 'P', 0, 'L', 0, 'E', 0}, actualType3Message.domainBytes);
    assertArrayEquals(new byte[]{'H', 0, 'T', 0, 'T', 0, 'P', 0, 'S', 0, ':', 0, '/', 0, '/', 0, 'E', 0, 'X', 0, 'A', 0,
        'M', 0, 'P', 0, 'L', 0, 'E', 0}, actualType3Message.hostBytes);
    assertArrayEquals(new byte[]{'h', 0, 't', 0, 't', 0, 'p', 0, 's', 0, ':', 0, '/', 0, '/', 0, 'e', 0, 'x', 0, 'a', 0,
        'm', 0, 'p', 0, 'l', 0, 'e', 0, '.', 0, 'o', 0, 'r', 0, 'g', 0, '/', 0, 'e', 0, 'x', 0, 'a', 0, 'm', 0, 'p', 0,
        'l', 0, 'e', 0}, actualType3Message.userBytes);
  }

  /**
   * Test Type3Message {@link Type3Message#Type3Message(String, String, String, String, byte[], int, String, byte[])}.
   * <ul>
   *   <li>When {@code A}.</li>
   *   <li>Then return {@link Type3Message#domainBytes} is {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link Type3Message#Type3Message(String, String, String, String, byte[], int, String, byte[])}
   */
  @Test
  @DisplayName("Test Type3Message new Type3Message(String, String, String, String, byte[], int, String, byte[]); when 'A'; then return domainBytes is 'null'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void Type3Message.<init>(String, String, String, String, byte[], int, String, byte[])"})
  void testType3MessageNewType3Message_whenA_thenReturnDomainBytesIsNull() {
    // Arrange and Act
    Type3Message actualType3Message = new Type3Message(null, null, "https://example.org/example",
        "https://example.org/example", new byte[]{'A', 'X', 'A', 'X', 'A', 'X', 'A', 'X'}, 1, null, null);

    // Assert
    assertEquals("TlRMTVNTUAADAAAAGAAYAEgAAAAYABgAYAAAAAAAAAB4AAAANgA2AHgAAAAAAAAArgAAAAAAAACuAAAAAQAAAgUBKAoAAAAP4251"
        + "aKwCYRTRZL1wscPO1olAjLz9MrB2vcqJ1jdQ75Be/MAOzfutLb31QSkwSOqNaAB0AHQAcABzADoALwAvAGUAeABhAG0AcABsAGUA"
        + "LgBvAHIAZwAvAGUAeABhAG0AcABsAGUA", actualType3Message.getResponse());
    assertNull(actualType3Message.domainBytes);
    assertNull(actualType3Message.hostBytes);
    assertArrayEquals(new byte[]{-29, 'n', 'u', 'h', -84, 2, 'a', 20, -47, 'd', -67, 'p', -79, -61, -50, -42, -119, '@',
        -116, -68, -3, '2', -80, 'v'}, actualType3Message.lmResp);
    assertArrayEquals(new byte[]{-67, -54, -119, -42, '7', 'P', -17, -112, '^', -4, -64, 14, -51, -5, -83, '-', -67,
        -11, 'A', ')', '0', 'H', -22, -115}, actualType3Message.ntResp);
    assertArrayEquals(new byte[]{'h', 0, 't', 0, 't', 0, 'p', 0, 's', 0, ':', 0, '/', 0, '/', 0, 'e', 0, 'x', 0, 'a', 0,
        'm', 0, 'p', 0, 'l', 0, 'e', 0, '.', 0, 'o', 0, 'r', 0, 'g', 0, '/', 0, 'e', 0, 'x', 0, 'a', 0, 'm', 0, 'p', 0,
        'l', 0, 'e', 0}, actualType3Message.userBytes);
  }

  /**
   * Test Type3Message {@link Type3Message#Type3Message(String, String, String, String, byte[], int, String, byte[])}.
   * <ul>
   *   <li>When minus one.</li>
   *   <li>Then return {@link Type3Message#type2Flags} is minus one.</li>
   * </ul>
   * <p>
   * Method under test: {@link Type3Message#Type3Message(String, String, String, String, byte[], int, String, byte[])}
   */
  @Test
  @DisplayName("Test Type3Message new Type3Message(String, String, String, String, byte[], int, String, byte[]); when minus one; then return type2Flags is minus one")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void Type3Message.<init>(String, String, String, String, byte[], int, String, byte[])"})
  void testType3MessageNewType3Message_whenMinusOne_thenReturnType2FlagsIsMinusOne()
      throws UnsupportedEncodingException {
    // Arrange
    byte[] nonce = "AXAXAXAX".getBytes("UTF-8");

    // Act
    Type3Message actualType3Message = new Type3Message("https://example.org/example", "https://example.org/example",
        "https://example.org/example", "https://example.org/example", nonce, -1, "https://example.org/example",
        "AXAXAXAX".getBytes("UTF-8"));

    // Assert
    assertEquals(-1, actualType3Message.type2Flags);
    assertArrayEquals(new byte[]{'H', 0, 'T', 0, 'T', 0, 'P', 0, 'S', 0, ':', 0, '/', 0, '/', 0, 'E', 0, 'X', 0, 'A', 0,
        'M', 0, 'P', 0, 'L', 0, 'E', 0}, actualType3Message.domainBytes);
    assertArrayEquals(new byte[]{'H', 0, 'T', 0, 'T', 0, 'P', 0, 'S', 0, ':', 0, '/', 0, '/', 0, 'E', 0, 'X', 0, 'A', 0,
        'M', 0, 'P', 0, 'L', 0, 'E', 0}, actualType3Message.hostBytes);
    assertArrayEquals(new byte[]{'h', 0, 't', 0, 't', 0, 'p', 0, 's', 0, ':', 0, '/', 0, '/', 0, 'e', 0, 'x', 0, 'a', 0,
        'm', 0, 'p', 0, 'l', 0, 'e', 0, '.', 0, 'o', 0, 'r', 0, 'g', 0, '/', 0, 'e', 0, 'x', 0, 'a', 0, 'm', 0, 'p', 0,
        'l', 0, 'e', 0}, actualType3Message.userBytes);
  }

  /**
   * Test Type3Message {@link Type3Message#Type3Message(String, String, String, String, byte[], int, String, byte[])}.
   * <ul>
   *   <li>When one.</li>
   *   <li>Then return Response is a string.</li>
   * </ul>
   * <p>
   * Method under test: {@link Type3Message#Type3Message(String, String, String, String, byte[], int, String, byte[])}
   */
  @Test
  @DisplayName("Test Type3Message new Type3Message(String, String, String, String, byte[], int, String, byte[]); when one; then return Response is a string")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void Type3Message.<init>(String, String, String, String, byte[], int, String, byte[])"})
  void testType3MessageNewType3Message_whenOne_thenReturnResponseIsAString() throws UnsupportedEncodingException {
    // Arrange
    byte[] nonce = "AXAXAXAX".getBytes("UTF-8");

    // Act
    Type3Message actualType3Message = new Type3Message("https://example.org/example", "https://example.org/example",
        "https://example.org/example", "https://example.org/example", nonce, 1, "https://example.org/example",
        "AXAXAXAX".getBytes("UTF-8"));

    // Assert
    assertEquals("TlRMTVNTUAADAAAAGAAYAEgAAAAYABgAYAAAAB4AHgB4AAAANgA2AJYAAAAeAB4AzAAAAAAAAADqAAAAAQAAAgUBKAoAAAAP4251"
        + "aKwCYRTRZL1wscPO1olAjLz9MrB2vcqJ1jdQ75Be/MAOzfutLb31QSkwSOqNSABUAFQAUABTADoALwAvAEUAWABBAE0AUABMAEUA"
        + "aAB0AHQAcABzADoALwAvAGUAeABhAG0AcABsAGUALgBvAHIAZwAvAGUAeABhAG0AcABsAGUASABUAFQAUABTADoALwAvAEUAWABB"
        + "AE0AUABMAEUA", actualType3Message.getResponse());
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
   * Test Type3Message {@link Type3Message#Type3Message(String, String, String, String, byte[], int, String, byte[])}.
   * <ul>
   *   <li>When {@link Short#SIZE}.</li>
   *   <li>Then return {@link Type3Message#type2Flags} is {@link Short#SIZE}.</li>
   * </ul>
   * <p>
   * Method under test: {@link Type3Message#Type3Message(String, String, String, String, byte[], int, String, byte[])}
   */
  @Test
  @DisplayName("Test Type3Message new Type3Message(String, String, String, String, byte[], int, String, byte[]); when SIZE; then return type2Flags is SIZE")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void Type3Message.<init>(String, String, String, String, byte[], int, String, byte[])"})
  void testType3MessageNewType3Message_whenSize_thenReturnType2FlagsIsSize() throws UnsupportedEncodingException {
    // Arrange
    byte[] nonce = "AXAXAXAX".getBytes("UTF-8");

    // Act
    Type3Message actualType3Message = new Type3Message("https://example.org/example", "https://example.org/example",
        "https://example.org/example", "https://example.org/example", nonce, Short.SIZE, "https://example.org/example",
        "AXAXAXAX".getBytes("UTF-8"));

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
   * Test {@link NtlmEngine#writeULong(byte[], int, int)}.
   * <ul>
   *   <li>Then {@code AXAXAXAX} Bytes is {@code UTF-8} is array of {@code byte} with {@code A} and {@code X}.</li>
   * </ul>
   * <p>
   * Method under test: {@link NtlmEngine#writeULong(byte[], int, int)}
   */
  @Test
  @DisplayName("Test writeULong(byte[], int, int); then 'AXAXAXAX' Bytes is 'UTF-8' is array of byte with 'A' and 'X'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void NtlmEngine.writeULong(byte[], int, int)"})
  void testWriteULong_thenAxaxaxaxBytesIsUtf8IsArrayOfByteWithAAndX() throws UnsupportedEncodingException {
    // Arrange
    byte[] buffer = "AXAXAXAX".getBytes("UTF-8");

    // Act
    NtlmEngine.writeULong(buffer, 42, 2);

    // Assert
    assertArrayEquals(new byte[]{'A', 'X', '*', 0, 0, 0, 'A', 'X'}, buffer);
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
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"int NtlmEngine.F(int, int, int)"})
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
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"int NtlmEngine.F(int, int, int)"})
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
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"int NtlmEngine.F(int, int, int)"})
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
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"int NtlmEngine.F(int, int, int)"})
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
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"int NtlmEngine.G(int, int, int)"})
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
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"int NtlmEngine.G(int, int, int)"})
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
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"int NtlmEngine.G(int, int, int)"})
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
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"int NtlmEngine.G(int, int, int)"})
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
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"int NtlmEngine.H(int, int, int)"})
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
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"int NtlmEngine.H(int, int, int)"})
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
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"int NtlmEngine.H(int, int, int)"})
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
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"int NtlmEngine.H(int, int, int)"})
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
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"int NtlmEngine.rotintlft(int, int)"})
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
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"int NtlmEngine.rotintlft(int, int)"})
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
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"int NtlmEngine.rotintlft(int, int)"})
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
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"int NtlmEngine.rotintlft(int, int)"})
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
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"String NtlmEngine.generateType1Msg()"})
  void testGenerateType1Msg() {
    // Arrange, Act and Assert
    assertEquals("TlRMTVNTUAABAAAAAYIIogAAAAAoAAAAAAAAACgAAAAFASgKAAAADw==", NtlmEngine.INSTANCE.generateType1Msg());
  }
}
