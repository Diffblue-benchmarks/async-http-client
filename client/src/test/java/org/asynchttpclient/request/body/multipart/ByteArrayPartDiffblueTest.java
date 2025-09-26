package org.asynchttpclient.request.body.multipart;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

class ByteArrayPartDiffblueTest {
  /**
   * Test {@link ByteArrayPart#ByteArrayPart(String, byte[])}.
   *
   * <p>Method under test: {@link ByteArrayPart#ByteArrayPart(String, byte[])}
   */
  @Test
  @DisplayName("Test new ByteArrayPart(String, byte[])")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void ByteArrayPart.<init>(String, byte[])"})
  void testNewByteArrayPart() throws UnsupportedEncodingException {
    // Arrange and Act
    ByteArrayPart actualByteArrayPart =
        new ByteArrayPart("https://example.org/example", "AXAXAXAX".getBytes("UTF-8"));

    // Assert
    assertEquals("application/octet-stream", actualByteArrayPart.getContentType());
    assertEquals("https://example.org/example", actualByteArrayPart.getName());
    assertNull(actualByteArrayPart.getFileName());
    assertNull(actualByteArrayPart.getContentId());
    assertNull(actualByteArrayPart.getDispositionType());
    assertNull(actualByteArrayPart.getTransferEncoding());
    assertNull(actualByteArrayPart.getCharset());
    assertNull(actualByteArrayPart.getCustomHeaders());
    assertArrayEquals("AXAXAXAX".getBytes("UTF-8"), actualByteArrayPart.getBytes());
  }

  /**
   * Test {@link ByteArrayPart#ByteArrayPart(String, byte[], String)}.
   *
   * <ul>
   *   <li>Then return ContentType is {@code https://example.org/example}.
   * </ul>
   *
   * <p>Method under test: {@link ByteArrayPart#ByteArrayPart(String, byte[], String)}
   */
  @Test
  @DisplayName(
      "Test new ByteArrayPart(String, byte[], String); then return ContentType is 'https://example.org/example'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void ByteArrayPart.<init>(String, byte[], String)"})
  void testNewByteArrayPart_thenReturnContentTypeIsHttpsExampleOrgExample()
      throws UnsupportedEncodingException {
    // Arrange and Act
    ByteArrayPart actualByteArrayPart =
        new ByteArrayPart(
            "https://example.org/example",
            "AXAXAXAX".getBytes("UTF-8"),
            "https://example.org/example");

    // Assert
    assertEquals("https://example.org/example", actualByteArrayPart.getContentType());
    assertEquals("https://example.org/example", actualByteArrayPart.getName());
    assertNull(actualByteArrayPart.getFileName());
    assertNull(actualByteArrayPart.getContentId());
    assertNull(actualByteArrayPart.getDispositionType());
    assertNull(actualByteArrayPart.getTransferEncoding());
    assertNull(actualByteArrayPart.getCharset());
    assertNull(actualByteArrayPart.getCustomHeaders());
    assertArrayEquals("AXAXAXAX".getBytes("UTF-8"), actualByteArrayPart.getBytes());
  }

  /**
   * Test {@link ByteArrayPart#ByteArrayPart(String, byte[], String, Charset)}.
   *
   * <ul>
   *   <li>Then return ContentType is {@code https://example.org/example}.
   * </ul>
   *
   * <p>Method under test: {@link ByteArrayPart#ByteArrayPart(String, byte[], String, Charset)}
   */
  @Test
  @DisplayName(
      "Test new ByteArrayPart(String, byte[], String, Charset); then return ContentType is 'https://example.org/example'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void ByteArrayPart.<init>(String, byte[], String, Charset)"})
  void testNewByteArrayPart_thenReturnContentTypeIsHttpsExampleOrgExample2()
      throws UnsupportedEncodingException {
    // Arrange
    byte[] bytes = "AXAXAXAX".getBytes("UTF-8");
    Charset charset = Charset.forName("UTF-8");

    // Act
    ByteArrayPart actualByteArrayPart =
        new ByteArrayPart(
            "https://example.org/example", bytes, "https://example.org/example", charset);

    // Assert
    assertEquals("https://example.org/example", actualByteArrayPart.getContentType());
    assertEquals("https://example.org/example", actualByteArrayPart.getName());
    assertNull(actualByteArrayPart.getFileName());
    assertNull(actualByteArrayPart.getContentId());
    assertNull(actualByteArrayPart.getDispositionType());
    assertNull(actualByteArrayPart.getTransferEncoding());
    assertNull(actualByteArrayPart.getCustomHeaders());
    assertSame(charset, actualByteArrayPart.getCharset());
    assertArrayEquals("AXAXAXAX".getBytes("UTF-8"), actualByteArrayPart.getBytes());
  }

  /**
   * Test {@link ByteArrayPart#ByteArrayPart(String, byte[], String, Charset, String)}.
   *
   * <ul>
   *   <li>Then return ContentType is {@code https://example.org/example}.
   * </ul>
   *
   * <p>Method under test: {@link ByteArrayPart#ByteArrayPart(String, byte[], String, Charset,
   * String)}
   */
  @Test
  @DisplayName(
      "Test new ByteArrayPart(String, byte[], String, Charset, String); then return ContentType is 'https://example.org/example'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void ByteArrayPart.<init>(String, byte[], String, Charset, String)"})
  void testNewByteArrayPart_thenReturnContentTypeIsHttpsExampleOrgExample3()
      throws UnsupportedEncodingException {
    // Arrange
    byte[] bytes = "AXAXAXAX".getBytes("UTF-8");
    Charset charset = Charset.forName("UTF-8");

    // Act
    ByteArrayPart actualByteArrayPart =
        new ByteArrayPart(
            "https://example.org/example",
            bytes,
            "https://example.org/example",
            charset,
            "https://example.org/example");

    // Assert
    assertEquals("https://example.org/example", actualByteArrayPart.getFileName());
    assertEquals("https://example.org/example", actualByteArrayPart.getContentType());
    assertEquals("https://example.org/example", actualByteArrayPart.getName());
    assertNull(actualByteArrayPart.getContentId());
    assertNull(actualByteArrayPart.getDispositionType());
    assertNull(actualByteArrayPart.getTransferEncoding());
    assertNull(actualByteArrayPart.getCustomHeaders());
    assertSame(charset, actualByteArrayPart.getCharset());
    assertArrayEquals("AXAXAXAX".getBytes("UTF-8"), actualByteArrayPart.getBytes());
  }

  /**
   * Test {@link ByteArrayPart#ByteArrayPart(String, byte[], String, Charset, String, String)}.
   *
   * <ul>
   *   <li>Then return ContentType is {@code https://example.org/example}.
   * </ul>
   *
   * <p>Method under test: {@link ByteArrayPart#ByteArrayPart(String, byte[], String, Charset,
   * String, String)}
   */
  @Test
  @DisplayName(
      "Test new ByteArrayPart(String, byte[], String, Charset, String, String); then return ContentType is 'https://example.org/example'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void ByteArrayPart.<init>(String, byte[], String, Charset, String, String)"})
  void testNewByteArrayPart_thenReturnContentTypeIsHttpsExampleOrgExample4()
      throws UnsupportedEncodingException {
    // Arrange
    byte[] bytes = "AXAXAXAX".getBytes("UTF-8");
    Charset charset = Charset.forName("UTF-8");

    // Act
    ByteArrayPart actualByteArrayPart =
        new ByteArrayPart(
            "https://example.org/example",
            bytes,
            "https://example.org/example",
            charset,
            "https://example.org/example",
            "https://example.org/example");

    // Assert
    assertEquals("https://example.org/example", actualByteArrayPart.getFileName());
    assertEquals("https://example.org/example", actualByteArrayPart.getContentId());
    assertEquals("https://example.org/example", actualByteArrayPart.getContentType());
    assertEquals("https://example.org/example", actualByteArrayPart.getName());
    assertNull(actualByteArrayPart.getDispositionType());
    assertNull(actualByteArrayPart.getTransferEncoding());
    assertNull(actualByteArrayPart.getCustomHeaders());
    assertSame(charset, actualByteArrayPart.getCharset());
    assertArrayEquals("AXAXAXAX".getBytes("UTF-8"), actualByteArrayPart.getBytes());
  }

  /**
   * Test {@link ByteArrayPart#ByteArrayPart(String, byte[], String, Charset, String, String,
   * String)}.
   *
   * <ul>
   *   <li>Then return ContentType is {@code https://example.org/example}.
   * </ul>
   *
   * <p>Method under test: {@link ByteArrayPart#ByteArrayPart(String, byte[], String, Charset,
   * String, String, String)}
   */
  @Test
  @DisplayName(
      "Test new ByteArrayPart(String, byte[], String, Charset, String, String, String); then return ContentType is 'https://example.org/example'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void ByteArrayPart.<init>(String, byte[], String, Charset, String, String, String)"
  })
  void testNewByteArrayPart_thenReturnContentTypeIsHttpsExampleOrgExample5()
      throws UnsupportedEncodingException {
    // Arrange
    byte[] bytes = "AXAXAXAX".getBytes("UTF-8");
    Charset charset = Charset.forName("UTF-8");

    // Act
    ByteArrayPart actualByteArrayPart =
        new ByteArrayPart(
            "https://example.org/example",
            bytes,
            "https://example.org/example",
            charset,
            "https://example.org/example",
            "https://example.org/example",
            "https://example.org/example");

    // Assert
    assertEquals("https://example.org/example", actualByteArrayPart.getFileName());
    assertEquals("https://example.org/example", actualByteArrayPart.getContentId());
    assertEquals("https://example.org/example", actualByteArrayPart.getContentType());
    assertEquals("https://example.org/example", actualByteArrayPart.getName());
    assertEquals("https://example.org/example", actualByteArrayPart.getTransferEncoding());
    assertNull(actualByteArrayPart.getDispositionType());
    assertNull(actualByteArrayPart.getCustomHeaders());
    assertSame(charset, actualByteArrayPart.getCharset());
    assertArrayEquals("AXAXAXAX".getBytes("UTF-8"), actualByteArrayPart.getBytes());
  }

  /**
   * Test {@link ByteArrayPart#ByteArrayPart(String, byte[], String)}.
   *
   * <ul>
   *   <li>When {@code A}.
   *   <li>Then return ContentType is {@code application/octet-stream}.
   * </ul>
   *
   * <p>Method under test: {@link ByteArrayPart#ByteArrayPart(String, byte[], String)}
   */
  @Test
  @DisplayName(
      "Test new ByteArrayPart(String, byte[], String); when 'A'; then return ContentType is 'application/octet-stream'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void ByteArrayPart.<init>(String, byte[], String)"})
  void testNewByteArrayPart_whenA_thenReturnContentTypeIsApplicationOctetStream()
      throws UnsupportedEncodingException {
    // Arrange and Act
    ByteArrayPart actualByteArrayPart =
        new ByteArrayPart(
            "https://example.org/example",
            new byte[] {'A', 'X', 'A', 'X', 'A', 'X', 'A', 'X'},
            null);

    // Assert
    assertEquals("application/octet-stream", actualByteArrayPart.getContentType());
    assertEquals("https://example.org/example", actualByteArrayPart.getName());
    assertNull(actualByteArrayPart.getFileName());
    assertNull(actualByteArrayPart.getContentId());
    assertNull(actualByteArrayPart.getDispositionType());
    assertNull(actualByteArrayPart.getTransferEncoding());
    assertNull(actualByteArrayPart.getCharset());
    assertNull(actualByteArrayPart.getCustomHeaders());
    assertArrayEquals("AXAXAXAX".getBytes("UTF-8"), actualByteArrayPart.getBytes());
  }

  /**
   * Test {@link ByteArrayPart#ByteArrayPart(String, byte[], String, Charset)}.
   *
   * <ul>
   *   <li>When {@code A}.
   *   <li>Then return ContentType is {@code application/octet-stream}.
   * </ul>
   *
   * <p>Method under test: {@link ByteArrayPart#ByteArrayPart(String, byte[], String, Charset)}
   */
  @Test
  @DisplayName(
      "Test new ByteArrayPart(String, byte[], String, Charset); when 'A'; then return ContentType is 'application/octet-stream'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void ByteArrayPart.<init>(String, byte[], String, Charset)"})
  void testNewByteArrayPart_whenA_thenReturnContentTypeIsApplicationOctetStream2()
      throws UnsupportedEncodingException {
    // Arrange
    Charset charset = Charset.forName("UTF-8");

    // Act
    ByteArrayPart actualByteArrayPart =
        new ByteArrayPart(
            "https://example.org/example",
            new byte[] {'A', 'X', 'A', 'X', 'A', 'X', 'A', 'X'},
            null,
            charset);

    // Assert
    assertEquals("application/octet-stream", actualByteArrayPart.getContentType());
    assertEquals("https://example.org/example", actualByteArrayPart.getName());
    assertNull(actualByteArrayPart.getFileName());
    assertNull(actualByteArrayPart.getContentId());
    assertNull(actualByteArrayPart.getDispositionType());
    assertNull(actualByteArrayPart.getTransferEncoding());
    assertNull(actualByteArrayPart.getCustomHeaders());
    assertSame(charset, actualByteArrayPart.getCharset());
    assertArrayEquals("AXAXAXAX".getBytes("UTF-8"), actualByteArrayPart.getBytes());
  }

  /**
   * Test {@link ByteArrayPart#ByteArrayPart(String, byte[], String, Charset, String)}.
   *
   * <ul>
   *   <li>When {@code A}.
   *   <li>Then return FileName is {@code https://example.org/example}.
   * </ul>
   *
   * <p>Method under test: {@link ByteArrayPart#ByteArrayPart(String, byte[], String, Charset,
   * String)}
   */
  @Test
  @DisplayName(
      "Test new ByteArrayPart(String, byte[], String, Charset, String); when 'A'; then return FileName is 'https://example.org/example'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void ByteArrayPart.<init>(String, byte[], String, Charset, String)"})
  void testNewByteArrayPart_whenA_thenReturnFileNameIsHttpsExampleOrgExample()
      throws UnsupportedEncodingException {
    // Arrange
    Charset charset = Charset.forName("UTF-8");

    // Act
    ByteArrayPart actualByteArrayPart =
        new ByteArrayPart(
            "https://example.org/example",
            new byte[] {'A', 'X', 'A', 'X', 'A', 'X', 'A', 'X'},
            null,
            charset,
            "https://example.org/example");

    // Assert
    assertEquals("application/octet-stream", actualByteArrayPart.getContentType());
    assertEquals("https://example.org/example", actualByteArrayPart.getFileName());
    assertEquals("https://example.org/example", actualByteArrayPart.getName());
    assertNull(actualByteArrayPart.getContentId());
    assertNull(actualByteArrayPart.getDispositionType());
    assertNull(actualByteArrayPart.getTransferEncoding());
    assertNull(actualByteArrayPart.getCustomHeaders());
    assertSame(charset, actualByteArrayPart.getCharset());
    assertArrayEquals("AXAXAXAX".getBytes("UTF-8"), actualByteArrayPart.getBytes());
  }

  /**
   * Test {@link ByteArrayPart#ByteArrayPart(String, byte[], String, Charset, String, String)}.
   *
   * <ul>
   *   <li>When {@code A}.
   *   <li>Then return FileName is {@code https://example.org/example}.
   * </ul>
   *
   * <p>Method under test: {@link ByteArrayPart#ByteArrayPart(String, byte[], String, Charset,
   * String, String)}
   */
  @Test
  @DisplayName(
      "Test new ByteArrayPart(String, byte[], String, Charset, String, String); when 'A'; then return FileName is 'https://example.org/example'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void ByteArrayPart.<init>(String, byte[], String, Charset, String, String)"})
  void testNewByteArrayPart_whenA_thenReturnFileNameIsHttpsExampleOrgExample2()
      throws UnsupportedEncodingException {
    // Arrange
    Charset charset = Charset.forName("UTF-8");

    // Act
    ByteArrayPart actualByteArrayPart =
        new ByteArrayPart(
            "https://example.org/example",
            new byte[] {'A', 'X', 'A', 'X', 'A', 'X', 'A', 'X'},
            null,
            charset,
            "https://example.org/example",
            "https://example.org/example");

    // Assert
    assertEquals("application/octet-stream", actualByteArrayPart.getContentType());
    assertEquals("https://example.org/example", actualByteArrayPart.getFileName());
    assertEquals("https://example.org/example", actualByteArrayPart.getContentId());
    assertEquals("https://example.org/example", actualByteArrayPart.getName());
    assertNull(actualByteArrayPart.getDispositionType());
    assertNull(actualByteArrayPart.getTransferEncoding());
    assertNull(actualByteArrayPart.getCustomHeaders());
    assertSame(charset, actualByteArrayPart.getCharset());
    assertArrayEquals("AXAXAXAX".getBytes("UTF-8"), actualByteArrayPart.getBytes());
  }

  /**
   * Test {@link ByteArrayPart#ByteArrayPart(String, byte[], String, Charset, String, String,
   * String)}.
   *
   * <ul>
   *   <li>When {@code A}.
   *   <li>Then return FileName is {@code https://example.org/example}.
   * </ul>
   *
   * <p>Method under test: {@link ByteArrayPart#ByteArrayPart(String, byte[], String, Charset,
   * String, String, String)}
   */
  @Test
  @DisplayName(
      "Test new ByteArrayPart(String, byte[], String, Charset, String, String, String); when 'A'; then return FileName is 'https://example.org/example'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void ByteArrayPart.<init>(String, byte[], String, Charset, String, String, String)"
  })
  void testNewByteArrayPart_whenA_thenReturnFileNameIsHttpsExampleOrgExample3()
      throws UnsupportedEncodingException {
    // Arrange
    Charset charset = Charset.forName("UTF-8");

    // Act
    ByteArrayPart actualByteArrayPart =
        new ByteArrayPart(
            "https://example.org/example",
            new byte[] {'A', 'X', 'A', 'X', 'A', 'X', 'A', 'X'},
            null,
            charset,
            "https://example.org/example",
            "https://example.org/example",
            "https://example.org/example");

    // Assert
    assertEquals("application/octet-stream", actualByteArrayPart.getContentType());
    assertEquals("https://example.org/example", actualByteArrayPart.getFileName());
    assertEquals("https://example.org/example", actualByteArrayPart.getContentId());
    assertEquals("https://example.org/example", actualByteArrayPart.getName());
    assertEquals("https://example.org/example", actualByteArrayPart.getTransferEncoding());
    assertNull(actualByteArrayPart.getDispositionType());
    assertNull(actualByteArrayPart.getCustomHeaders());
    assertSame(charset, actualByteArrayPart.getCharset());
    assertArrayEquals("AXAXAXAX".getBytes("UTF-8"), actualByteArrayPart.getBytes());
  }

  /**
   * Test {@link ByteArrayPart#ByteArrayPart(String, byte[], String, Charset, String)}.
   *
   * <ul>
   *   <li>When {@code A}.
   *   <li>Then return FileName is {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link ByteArrayPart#ByteArrayPart(String, byte[], String, Charset,
   * String)}
   */
  @Test
  @DisplayName(
      "Test new ByteArrayPart(String, byte[], String, Charset, String); when 'A'; then return FileName is 'null'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void ByteArrayPart.<init>(String, byte[], String, Charset, String)"})
  void testNewByteArrayPart_whenA_thenReturnFileNameIsNull() throws UnsupportedEncodingException {
    // Arrange
    Charset charset = Charset.forName("UTF-8");

    // Act
    ByteArrayPart actualByteArrayPart =
        new ByteArrayPart(
            "https://example.org/example",
            new byte[] {'A', 'X', 'A', 'X', 'A', 'X', 'A', 'X'},
            null,
            charset,
            null);

    // Assert
    assertEquals("application/octet-stream", actualByteArrayPart.getContentType());
    assertEquals("https://example.org/example", actualByteArrayPart.getName());
    assertNull(actualByteArrayPart.getFileName());
    assertNull(actualByteArrayPart.getContentId());
    assertNull(actualByteArrayPart.getDispositionType());
    assertNull(actualByteArrayPart.getTransferEncoding());
    assertNull(actualByteArrayPart.getCustomHeaders());
    assertSame(charset, actualByteArrayPart.getCharset());
    assertArrayEquals("AXAXAXAX".getBytes("UTF-8"), actualByteArrayPart.getBytes());
  }

  /**
   * Test {@link ByteArrayPart#ByteArrayPart(String, byte[], String, Charset, String, String)}.
   *
   * <ul>
   *   <li>When {@code A}.
   *   <li>Then return FileName is {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link ByteArrayPart#ByteArrayPart(String, byte[], String, Charset,
   * String, String)}
   */
  @Test
  @DisplayName(
      "Test new ByteArrayPart(String, byte[], String, Charset, String, String); when 'A'; then return FileName is 'null'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void ByteArrayPart.<init>(String, byte[], String, Charset, String, String)"})
  void testNewByteArrayPart_whenA_thenReturnFileNameIsNull2() throws UnsupportedEncodingException {
    // Arrange
    Charset charset = Charset.forName("UTF-8");

    // Act
    ByteArrayPart actualByteArrayPart =
        new ByteArrayPart(
            "https://example.org/example",
            new byte[] {'A', 'X', 'A', 'X', 'A', 'X', 'A', 'X'},
            null,
            charset,
            null,
            "https://example.org/example");

    // Assert
    assertEquals("application/octet-stream", actualByteArrayPart.getContentType());
    assertEquals("https://example.org/example", actualByteArrayPart.getContentId());
    assertEquals("https://example.org/example", actualByteArrayPart.getName());
    assertNull(actualByteArrayPart.getFileName());
    assertNull(actualByteArrayPart.getDispositionType());
    assertNull(actualByteArrayPart.getTransferEncoding());
    assertNull(actualByteArrayPart.getCustomHeaders());
    assertSame(charset, actualByteArrayPart.getCharset());
    assertArrayEquals("AXAXAXAX".getBytes("UTF-8"), actualByteArrayPart.getBytes());
  }

  /**
   * Test {@link ByteArrayPart#ByteArrayPart(String, byte[], String, Charset, String, String,
   * String)}.
   *
   * <ul>
   *   <li>When {@code A}.
   *   <li>Then return FileName is {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link ByteArrayPart#ByteArrayPart(String, byte[], String, Charset,
   * String, String, String)}
   */
  @Test
  @DisplayName(
      "Test new ByteArrayPart(String, byte[], String, Charset, String, String, String); when 'A'; then return FileName is 'null'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void ByteArrayPart.<init>(String, byte[], String, Charset, String, String, String)"
  })
  void testNewByteArrayPart_whenA_thenReturnFileNameIsNull3() throws UnsupportedEncodingException {
    // Arrange
    Charset charset = Charset.forName("UTF-8");

    // Act
    ByteArrayPart actualByteArrayPart =
        new ByteArrayPart(
            "https://example.org/example",
            new byte[] {'A', 'X', 'A', 'X', 'A', 'X', 'A', 'X'},
            null,
            charset,
            null,
            "https://example.org/example",
            "https://example.org/example");

    // Assert
    assertEquals("application/octet-stream", actualByteArrayPart.getContentType());
    assertEquals("https://example.org/example", actualByteArrayPart.getContentId());
    assertEquals("https://example.org/example", actualByteArrayPart.getName());
    assertEquals("https://example.org/example", actualByteArrayPart.getTransferEncoding());
    assertNull(actualByteArrayPart.getFileName());
    assertNull(actualByteArrayPart.getDispositionType());
    assertNull(actualByteArrayPart.getCustomHeaders());
    assertSame(charset, actualByteArrayPart.getCharset());
    assertArrayEquals("AXAXAXAX".getBytes("UTF-8"), actualByteArrayPart.getBytes());
  }

  /**
   * Test {@link ByteArrayPart#ByteArrayPart(String, byte[], String, Charset, String)}.
   *
   * <ul>
   *   <li>When {@code .}.
   *   <li>Then return FileName is {@code .}.
   * </ul>
   *
   * <p>Method under test: {@link ByteArrayPart#ByteArrayPart(String, byte[], String, Charset,
   * String)}
   */
  @Test
  @DisplayName(
      "Test new ByteArrayPart(String, byte[], String, Charset, String); when '.'; then return FileName is '.'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void ByteArrayPart.<init>(String, byte[], String, Charset, String)"})
  void testNewByteArrayPart_whenDot_thenReturnFileNameIsDot() throws UnsupportedEncodingException {
    // Arrange
    Charset charset = Charset.forName("UTF-8");

    // Act
    ByteArrayPart actualByteArrayPart =
        new ByteArrayPart(
            "https://example.org/example",
            new byte[] {'A', 'X', 'A', 'X', 'A', 'X', 'A', 'X'},
            null,
            charset,
            ".");

    // Assert
    assertEquals(".", actualByteArrayPart.getFileName());
    assertEquals("application/octet-stream", actualByteArrayPart.getContentType());
    assertEquals("https://example.org/example", actualByteArrayPart.getName());
    assertNull(actualByteArrayPart.getContentId());
    assertNull(actualByteArrayPart.getDispositionType());
    assertNull(actualByteArrayPart.getTransferEncoding());
    assertNull(actualByteArrayPart.getCustomHeaders());
    assertSame(charset, actualByteArrayPart.getCharset());
    assertArrayEquals("AXAXAXAX".getBytes("UTF-8"), actualByteArrayPart.getBytes());
  }

  /**
   * Test {@link ByteArrayPart#ByteArrayPart(String, byte[], String, Charset, String, String)}.
   *
   * <ul>
   *   <li>When {@code .}.
   *   <li>Then return FileName is {@code .}.
   * </ul>
   *
   * <p>Method under test: {@link ByteArrayPart#ByteArrayPart(String, byte[], String, Charset,
   * String, String)}
   */
  @Test
  @DisplayName(
      "Test new ByteArrayPart(String, byte[], String, Charset, String, String); when '.'; then return FileName is '.'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void ByteArrayPart.<init>(String, byte[], String, Charset, String, String)"})
  void testNewByteArrayPart_whenDot_thenReturnFileNameIsDot2() throws UnsupportedEncodingException {
    // Arrange
    Charset charset = Charset.forName("UTF-8");

    // Act
    ByteArrayPart actualByteArrayPart =
        new ByteArrayPart(
            "https://example.org/example",
            new byte[] {'A', 'X', 'A', 'X', 'A', 'X', 'A', 'X'},
            null,
            charset,
            ".",
            "https://example.org/example");

    // Assert
    assertEquals(".", actualByteArrayPart.getFileName());
    assertEquals("application/octet-stream", actualByteArrayPart.getContentType());
    assertEquals("https://example.org/example", actualByteArrayPart.getContentId());
    assertEquals("https://example.org/example", actualByteArrayPart.getName());
    assertNull(actualByteArrayPart.getDispositionType());
    assertNull(actualByteArrayPart.getTransferEncoding());
    assertNull(actualByteArrayPart.getCustomHeaders());
    assertSame(charset, actualByteArrayPart.getCharset());
    assertArrayEquals("AXAXAXAX".getBytes("UTF-8"), actualByteArrayPart.getBytes());
  }

  /**
   * Test {@link ByteArrayPart#ByteArrayPart(String, byte[], String, Charset, String, String,
   * String)}.
   *
   * <ul>
   *   <li>When {@code .}.
   *   <li>Then return FileName is {@code .}.
   * </ul>
   *
   * <p>Method under test: {@link ByteArrayPart#ByteArrayPart(String, byte[], String, Charset,
   * String, String, String)}
   */
  @Test
  @DisplayName(
      "Test new ByteArrayPart(String, byte[], String, Charset, String, String, String); when '.'; then return FileName is '.'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void ByteArrayPart.<init>(String, byte[], String, Charset, String, String, String)"
  })
  void testNewByteArrayPart_whenDot_thenReturnFileNameIsDot3() throws UnsupportedEncodingException {
    // Arrange
    Charset charset = Charset.forName("UTF-8");

    // Act
    ByteArrayPart actualByteArrayPart =
        new ByteArrayPart(
            "https://example.org/example",
            new byte[] {'A', 'X', 'A', 'X', 'A', 'X', 'A', 'X'},
            null,
            charset,
            ".",
            "https://example.org/example",
            "https://example.org/example");

    // Assert
    assertEquals(".", actualByteArrayPart.getFileName());
    assertEquals("application/octet-stream", actualByteArrayPart.getContentType());
    assertEquals("https://example.org/example", actualByteArrayPart.getContentId());
    assertEquals("https://example.org/example", actualByteArrayPart.getName());
    assertEquals("https://example.org/example", actualByteArrayPart.getTransferEncoding());
    assertNull(actualByteArrayPart.getDispositionType());
    assertNull(actualByteArrayPart.getCustomHeaders());
    assertSame(charset, actualByteArrayPart.getCharset());
    assertArrayEquals("AXAXAXAX".getBytes("UTF-8"), actualByteArrayPart.getBytes());
  }

  /**
   * Test {@link ByteArrayPart#ByteArrayPart(String, byte[], String, Charset, String)}.
   *
   * <ul>
   *   <li>When {@code foo.txt}.
   *   <li>Then return FileName is {@code foo.txt}.
   * </ul>
   *
   * <p>Method under test: {@link ByteArrayPart#ByteArrayPart(String, byte[], String, Charset,
   * String)}
   */
  @Test
  @DisplayName(
      "Test new ByteArrayPart(String, byte[], String, Charset, String); when 'foo.txt'; then return FileName is 'foo.txt'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void ByteArrayPart.<init>(String, byte[], String, Charset, String)"})
  void testNewByteArrayPart_whenFooTxt_thenReturnFileNameIsFooTxt()
      throws UnsupportedEncodingException {
    // Arrange
    Charset charset = Charset.forName("UTF-8");

    // Act
    ByteArrayPart actualByteArrayPart =
        new ByteArrayPart(
            "https://example.org/example",
            new byte[] {'A', 'X', 'A', 'X', 'A', 'X', 'A', 'X'},
            null,
            charset,
            "foo.txt");

    // Assert
    assertEquals("foo.txt", actualByteArrayPart.getFileName());
    assertEquals("https://example.org/example", actualByteArrayPart.getName());
    assertEquals("text/plain", actualByteArrayPart.getContentType());
    assertNull(actualByteArrayPart.getContentId());
    assertNull(actualByteArrayPart.getDispositionType());
    assertNull(actualByteArrayPart.getTransferEncoding());
    assertNull(actualByteArrayPart.getCustomHeaders());
    assertSame(charset, actualByteArrayPart.getCharset());
    assertArrayEquals("AXAXAXAX".getBytes("UTF-8"), actualByteArrayPart.getBytes());
  }

  /**
   * Test {@link ByteArrayPart#ByteArrayPart(String, byte[], String, Charset, String, String)}.
   *
   * <ul>
   *   <li>When {@code foo.txt}.
   *   <li>Then return FileName is {@code foo.txt}.
   * </ul>
   *
   * <p>Method under test: {@link ByteArrayPart#ByteArrayPart(String, byte[], String, Charset,
   * String, String)}
   */
  @Test
  @DisplayName(
      "Test new ByteArrayPart(String, byte[], String, Charset, String, String); when 'foo.txt'; then return FileName is 'foo.txt'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void ByteArrayPart.<init>(String, byte[], String, Charset, String, String)"})
  void testNewByteArrayPart_whenFooTxt_thenReturnFileNameIsFooTxt2()
      throws UnsupportedEncodingException {
    // Arrange
    Charset charset = Charset.forName("UTF-8");

    // Act
    ByteArrayPart actualByteArrayPart =
        new ByteArrayPart(
            "https://example.org/example",
            new byte[] {'A', 'X', 'A', 'X', 'A', 'X', 'A', 'X'},
            null,
            charset,
            "foo.txt",
            "https://example.org/example");

    // Assert
    assertEquals("foo.txt", actualByteArrayPart.getFileName());
    assertEquals("https://example.org/example", actualByteArrayPart.getContentId());
    assertEquals("https://example.org/example", actualByteArrayPart.getName());
    assertEquals("text/plain", actualByteArrayPart.getContentType());
    assertNull(actualByteArrayPart.getDispositionType());
    assertNull(actualByteArrayPart.getTransferEncoding());
    assertNull(actualByteArrayPart.getCustomHeaders());
    assertSame(charset, actualByteArrayPart.getCharset());
    assertArrayEquals("AXAXAXAX".getBytes("UTF-8"), actualByteArrayPart.getBytes());
  }

  /**
   * Test {@link ByteArrayPart#ByteArrayPart(String, byte[], String, Charset, String, String,
   * String)}.
   *
   * <ul>
   *   <li>When {@code foo.txt}.
   *   <li>Then return FileName is {@code foo.txt}.
   * </ul>
   *
   * <p>Method under test: {@link ByteArrayPart#ByteArrayPart(String, byte[], String, Charset,
   * String, String, String)}
   */
  @Test
  @DisplayName(
      "Test new ByteArrayPart(String, byte[], String, Charset, String, String, String); when 'foo.txt'; then return FileName is 'foo.txt'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void ByteArrayPart.<init>(String, byte[], String, Charset, String, String, String)"
  })
  void testNewByteArrayPart_whenFooTxt_thenReturnFileNameIsFooTxt3()
      throws UnsupportedEncodingException {
    // Arrange
    Charset charset = Charset.forName("UTF-8");

    // Act
    ByteArrayPart actualByteArrayPart =
        new ByteArrayPart(
            "https://example.org/example",
            new byte[] {'A', 'X', 'A', 'X', 'A', 'X', 'A', 'X'},
            null,
            charset,
            "foo.txt",
            "https://example.org/example",
            "https://example.org/example");

    // Assert
    assertEquals("foo.txt", actualByteArrayPart.getFileName());
    assertEquals("https://example.org/example", actualByteArrayPart.getContentId());
    assertEquals("https://example.org/example", actualByteArrayPart.getName());
    assertEquals("https://example.org/example", actualByteArrayPart.getTransferEncoding());
    assertEquals("text/plain", actualByteArrayPart.getContentType());
    assertNull(actualByteArrayPart.getDispositionType());
    assertNull(actualByteArrayPart.getCustomHeaders());
    assertSame(charset, actualByteArrayPart.getCharset());
    assertArrayEquals("AXAXAXAX".getBytes("UTF-8"), actualByteArrayPart.getBytes());
  }

  /**
   * Test {@link ByteArrayPart#getBytes()}.
   *
   * <p>Method under test: {@link ByteArrayPart#getBytes()}
   */
  @Test
  @DisplayName("Test getBytes()")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"byte[] ByteArrayPart.getBytes()"})
  void testGetBytes() throws UnsupportedEncodingException {
    // Arrange
    ByteArrayPart byteArrayPart =
        new ByteArrayPart("https://example.org/example", "AXAXAXAX".getBytes("UTF-8"));

    // Act and Assert
    assertArrayEquals("AXAXAXAX".getBytes("UTF-8"), byteArrayPart.getBytes());
  }
}
