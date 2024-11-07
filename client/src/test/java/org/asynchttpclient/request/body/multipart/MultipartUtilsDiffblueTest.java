package org.asynchttpclient.request.body.multipart;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import java.io.ByteArrayInputStream;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import org.asynchttpclient.request.body.multipart.part.ByteArrayMultipartPart;
import org.asynchttpclient.request.body.multipart.part.InputStreamMultipartPart;
import org.asynchttpclient.request.body.multipart.part.MessageEndMultipartPart;
import org.asynchttpclient.request.body.multipart.part.MultipartPart;
import org.asynchttpclient.request.body.multipart.part.MultipartState;
import org.asynchttpclient.request.body.multipart.part.StringMultipartPart;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class MultipartUtilsDiffblueTest {
  /**
   * Test {@link MultipartUtils#generateMultipartParts(List, byte[])}.
   * <p>
   * Method under test:
   * {@link MultipartUtils#generateMultipartParts(List, byte[])}
   */
  @Test
  @DisplayName("Test generateMultipartParts(List, byte[])")
  void testGenerateMultipartParts() throws UnsupportedEncodingException {
    // Arrange
    ByteArrayPart byteArrayPart = new ByteArrayPart("https://example.org/example", "AXAXAXAX".getBytes("UTF-8"));
    byteArrayPart.setCustomHeaders(new ArrayList<>());

    ArrayList<Part> parts = new ArrayList<>();
    parts.add(byteArrayPart);

    // Act
    List<MultipartPart<? extends Part>> actualGenerateMultipartPartsResult = MultipartUtils
        .generateMultipartParts(parts, "AXAXAXAX".getBytes("UTF-8"));

    // Assert
    assertEquals(2, actualGenerateMultipartPartsResult.size());
    MultipartPart<? extends Part> getResult = actualGenerateMultipartPartsResult.get(0);
    assertTrue(getResult instanceof ByteArrayMultipartPart);
    MultipartPart<? extends Part> getResult2 = actualGenerateMultipartPartsResult.get(1);
    assertTrue(getResult2 instanceof MessageEndMultipartPart);
    assertEquals(132L, getResult.length());
    assertEquals(14L, getResult2.length());
  }

  /**
   * Test {@link MultipartUtils#generateMultipartParts(List, byte[])}.
   * <ul>
   *   <li>Given {@code null}.</li>
   *   <li>Then throw {@link IllegalArgumentException}.</li>
   * </ul>
   * <p>
   * Method under test:
   * {@link MultipartUtils#generateMultipartParts(List, byte[])}
   */
  @Test
  @DisplayName("Test generateMultipartParts(List, byte[]); given 'null'; then throw IllegalArgumentException")
  void testGenerateMultipartParts_givenNull_thenThrowIllegalArgumentException() throws UnsupportedEncodingException {
    // Arrange
    ArrayList<Part> parts = new ArrayList<>();
    parts.add(null);

    // Act and Assert
    assertThrows(IllegalArgumentException.class,
        () -> MultipartUtils.generateMultipartParts(parts, "AXAXAXAX".getBytes("UTF-8")));
  }

  /**
   * Test {@link MultipartUtils#generateMultipartParts(List, byte[])}.
   * <ul>
   *   <li>Then first return {@link InputStreamMultipartPart}.</li>
   * </ul>
   * <p>
   * Method under test:
   * {@link MultipartUtils#generateMultipartParts(List, byte[])}
   */
  @Test
  @DisplayName("Test generateMultipartParts(List, byte[]); then first return InputStreamMultipartPart")
  void testGenerateMultipartParts_thenFirstReturnInputStreamMultipartPart() throws UnsupportedEncodingException {
    // Arrange
    ArrayList<Part> parts = new ArrayList<>();
    parts.add(new InputStreamPart("https://example.org/example",
        new ByteArrayInputStream(new byte[]{'A', 1, 'A', 1, 'A', 1, 'A', 1}), "https://example.org/example"));

    // Act
    List<MultipartPart<? extends Part>> actualGenerateMultipartPartsResult = MultipartUtils
        .generateMultipartParts(parts, "AXAXAXAX".getBytes("UTF-8"));

    // Assert
    assertEquals(2, actualGenerateMultipartPartsResult.size());
    MultipartPart<? extends Part> getResult = actualGenerateMultipartPartsResult.get(0);
    assertTrue(getResult instanceof InputStreamMultipartPart);
    MultipartPart<? extends Part> getResult2 = actualGenerateMultipartPartsResult.get(1);
    assertTrue(getResult2 instanceof MessageEndMultipartPart);
    assertEquals(-1L, getResult.length());
    assertEquals(14L, getResult2.length());
  }

  /**
   * Test {@link MultipartUtils#generateMultipartParts(List, byte[])}.
   * <ul>
   *   <li>Then first return {@link StringMultipartPart}.</li>
   * </ul>
   * <p>
   * Method under test:
   * {@link MultipartUtils#generateMultipartParts(List, byte[])}
   */
  @Test
  @DisplayName("Test generateMultipartParts(List, byte[]); then first return StringMultipartPart")
  void testGenerateMultipartParts_thenFirstReturnStringMultipartPart() throws UnsupportedEncodingException {
    // Arrange
    ArrayList<Part> parts = new ArrayList<>();
    parts.add(new StringPart("https://example.org/example", "https://example.org/example"));

    // Act
    List<MultipartPart<? extends Part>> actualGenerateMultipartPartsResult = MultipartUtils
        .generateMultipartParts(parts, "AXAXAXAX".getBytes("UTF-8"));

    // Assert
    assertEquals(2, actualGenerateMultipartPartsResult.size());
    MultipartPart<? extends Part> getResult = actualGenerateMultipartPartsResult.get(1);
    assertTrue(getResult instanceof MessageEndMultipartPart);
    MultipartPart<? extends Part> getResult2 = actualGenerateMultipartPartsResult.get(0);
    assertTrue(getResult2 instanceof StringMultipartPart);
    assertEquals(111L, getResult2.length());
    assertEquals(14L, getResult.length());
  }

  /**
   * Test {@link MultipartUtils#generateMultipartParts(List, byte[])}.
   * <ul>
   *   <li>Then return first length is one hundred fifty.</li>
   * </ul>
   * <p>
   * Method under test:
   * {@link MultipartUtils#generateMultipartParts(List, byte[])}
   */
  @Test
  @DisplayName("Test generateMultipartParts(List, byte[]); then return first length is one hundred fifty")
  void testGenerateMultipartParts_thenReturnFirstLengthIsOneHundredFifty() throws UnsupportedEncodingException {
    // Arrange
    ArrayList<Part> parts = new ArrayList<>();
    parts.add(new ByteArrayPart("https://example.org/example", new byte[]{'A', 1, 'A', 1, 'A', 1, 'A', 1},
        "https://example.org/example", Charset.forName("UTF-8")));

    // Act
    List<MultipartPart<? extends Part>> actualGenerateMultipartPartsResult = MultipartUtils
        .generateMultipartParts(parts, "AXAXAXAX".getBytes("UTF-8"));

    // Assert
    assertEquals(2, actualGenerateMultipartPartsResult.size());
    MultipartPart<? extends Part> getResult = actualGenerateMultipartPartsResult.get(0);
    assertTrue(getResult instanceof ByteArrayMultipartPart);
    MultipartPart<? extends Part> getResult2 = actualGenerateMultipartPartsResult.get(1);
    assertTrue(getResult2 instanceof MessageEndMultipartPart);
    assertEquals(14L, getResult2.length());
    assertEquals(150L, getResult.length());
  }

  /**
   * Test {@link MultipartUtils#generateMultipartParts(List, byte[])}.
   * <ul>
   *   <li>Then return first length is one hundred ninety.</li>
   * </ul>
   * <p>
   * Method under test:
   * {@link MultipartUtils#generateMultipartParts(List, byte[])}
   */
  @Test
  @DisplayName("Test generateMultipartParts(List, byte[]); then return first length is one hundred ninety")
  void testGenerateMultipartParts_thenReturnFirstLengthIsOneHundredNinety() throws UnsupportedEncodingException {
    // Arrange
    ByteArrayPart byteArrayPart = new ByteArrayPart("https://example.org/example", "AXAXAXAX".getBytes("UTF-8"));
    byteArrayPart.addCustomHeader("https://example.org/example", "https://example.org/example");

    ArrayList<Part> parts = new ArrayList<>();
    parts.add(byteArrayPart);

    // Act
    List<MultipartPart<? extends Part>> actualGenerateMultipartPartsResult = MultipartUtils
        .generateMultipartParts(parts, "AXAXAXAX".getBytes("UTF-8"));

    // Assert
    assertEquals(2, actualGenerateMultipartPartsResult.size());
    MultipartPart<? extends Part> getResult = actualGenerateMultipartPartsResult.get(0);
    assertTrue(getResult instanceof ByteArrayMultipartPart);
    MultipartPart<? extends Part> getResult2 = actualGenerateMultipartPartsResult.get(1);
    assertTrue(getResult2 instanceof MessageEndMultipartPart);
    assertEquals(14L, getResult2.length());
    assertEquals(190L, getResult.length());
  }

  /**
   * Test {@link MultipartUtils#generateMultipartParts(List, byte[])}.
   * <ul>
   *   <li>Then return first length is one hundred thirty-two.</li>
   * </ul>
   * <p>
   * Method under test:
   * {@link MultipartUtils#generateMultipartParts(List, byte[])}
   */
  @Test
  @DisplayName("Test generateMultipartParts(List, byte[]); then return first length is one hundred thirty-two")
  void testGenerateMultipartParts_thenReturnFirstLengthIsOneHundredThirtyTwo() throws UnsupportedEncodingException {
    // Arrange
    ArrayList<Part> parts = new ArrayList<>();
    parts.add(new ByteArrayPart("https://example.org/example", "AXAXAXAX".getBytes("UTF-8")));

    // Act
    List<MultipartPart<? extends Part>> actualGenerateMultipartPartsResult = MultipartUtils
        .generateMultipartParts(parts, "AXAXAXAX".getBytes("UTF-8"));

    // Assert
    assertEquals(2, actualGenerateMultipartPartsResult.size());
    MultipartPart<? extends Part> getResult = actualGenerateMultipartPartsResult.get(0);
    assertTrue(getResult instanceof ByteArrayMultipartPart);
    MultipartPart<? extends Part> getResult2 = actualGenerateMultipartPartsResult.get(1);
    assertTrue(getResult2 instanceof MessageEndMultipartPart);
    assertEquals(132L, getResult.length());
    assertEquals(14L, getResult2.length());
  }

  /**
   * Test {@link MultipartUtils#generateMultipartParts(List, byte[])}.
   * <ul>
   *   <li>Then return first length is one hundred twenty-four.</li>
   * </ul>
   * <p>
   * Method under test:
   * {@link MultipartUtils#generateMultipartParts(List, byte[])}
   */
  @Test
  @DisplayName("Test generateMultipartParts(List, byte[]); then return first length is one hundred twenty-four")
  void testGenerateMultipartParts_thenReturnFirstLengthIsOneHundredTwentyFour() throws UnsupportedEncodingException {
    // Arrange
    ArrayList<Part> parts = new ArrayList<>();
    parts.add(new ByteArrayPart("https://example.org/example", new byte[]{}));

    // Act
    List<MultipartPart<? extends Part>> actualGenerateMultipartPartsResult = MultipartUtils
        .generateMultipartParts(parts, "AXAXAXAX".getBytes("UTF-8"));

    // Assert
    assertEquals(2, actualGenerateMultipartPartsResult.size());
    MultipartPart<? extends Part> getResult = actualGenerateMultipartPartsResult.get(0);
    assertTrue(getResult instanceof ByteArrayMultipartPart);
    MultipartPart<? extends Part> getResult2 = actualGenerateMultipartPartsResult.get(1);
    assertTrue(getResult2 instanceof MessageEndMultipartPart);
    assertEquals(124L, getResult.length());
    assertEquals(14L, getResult2.length());
  }

  /**
   * Test {@link MultipartUtils#generateMultipartParts(List, byte[])}.
   * <ul>
   *   <li>Then return first length is two hundred eight.</li>
   * </ul>
   * <p>
   * Method under test:
   * {@link MultipartUtils#generateMultipartParts(List, byte[])}
   */
  @Test
  @DisplayName("Test generateMultipartParts(List, byte[]); then return first length is two hundred eight")
  void testGenerateMultipartParts_thenReturnFirstLengthIsTwoHundredEight() throws UnsupportedEncodingException {
    // Arrange
    ByteArrayPart byteArrayPart = new ByteArrayPart("https://example.org/example", "AXAXAXAX".getBytes("UTF-8"));
    byteArrayPart.setDispositionType("https://example.org/example");
    byteArrayPart.addCustomHeader("https://example.org/example", "https://example.org/example");

    ArrayList<Part> parts = new ArrayList<>();
    parts.add(byteArrayPart);

    // Act
    List<MultipartPart<? extends Part>> actualGenerateMultipartPartsResult = MultipartUtils
        .generateMultipartParts(parts, "AXAXAXAX".getBytes("UTF-8"));

    // Assert
    assertEquals(2, actualGenerateMultipartPartsResult.size());
    MultipartPart<? extends Part> getResult = actualGenerateMultipartPartsResult.get(0);
    assertTrue(getResult instanceof ByteArrayMultipartPart);
    MultipartPart<? extends Part> getResult2 = actualGenerateMultipartPartsResult.get(1);
    assertTrue(getResult2 instanceof MessageEndMultipartPart);
    assertEquals(14L, getResult2.length());
    assertEquals(208L, getResult.length());
  }

  /**
   * Test {@link MultipartUtils#generateMultipartParts(List, byte[])}.
   * <ul>
   *   <li>Then return size is three.</li>
   * </ul>
   * <p>
   * Method under test:
   * {@link MultipartUtils#generateMultipartParts(List, byte[])}
   */
  @Test
  @DisplayName("Test generateMultipartParts(List, byte[]); then return size is three")
  void testGenerateMultipartParts_thenReturnSizeIsThree() throws UnsupportedEncodingException {
    // Arrange
    ArrayList<Part> parts = new ArrayList<>();
    parts.add(new ByteArrayPart("https://example.org/example", "AXAXAXAX".getBytes("UTF-8")));
    parts.add(new ByteArrayPart("https://example.org/example", "AXAXAXAX".getBytes("UTF-8")));

    // Act
    List<MultipartPart<? extends Part>> actualGenerateMultipartPartsResult = MultipartUtils
        .generateMultipartParts(parts, "AXAXAXAX".getBytes("UTF-8"));

    // Assert
    assertEquals(3, actualGenerateMultipartPartsResult.size());
    MultipartPart<? extends Part> getResult = actualGenerateMultipartPartsResult.get(1);
    assertTrue(getResult instanceof ByteArrayMultipartPart);
    MultipartPart<? extends Part> getResult2 = actualGenerateMultipartPartsResult.get(2);
    assertTrue(getResult2 instanceof MessageEndMultipartPart);
    assertEquals(132L, getResult.length());
    assertEquals(14L, getResult2.length());
    assertEquals(MultipartState.PRE_CONTENT, getResult2.getState());
    assertFalse(getResult2.isTargetSlow());
  }

  /**
   * Test {@link MultipartUtils#generateMultipartParts(List, byte[])}.
   * <ul>
   *   <li>When {@link ArrayList#ArrayList()}.</li>
   *   <li>Then return size is one.</li>
   * </ul>
   * <p>
   * Method under test:
   * {@link MultipartUtils#generateMultipartParts(List, byte[])}
   */
  @Test
  @DisplayName("Test generateMultipartParts(List, byte[]); when ArrayList(); then return size is one")
  void testGenerateMultipartParts_whenArrayList_thenReturnSizeIsOne() throws UnsupportedEncodingException {
    // Arrange
    ArrayList<Part> parts = new ArrayList<>();

    // Act
    List<MultipartPart<? extends Part>> actualGenerateMultipartPartsResult = MultipartUtils
        .generateMultipartParts(parts, "AXAXAXAX".getBytes("UTF-8"));

    // Assert
    assertEquals(1, actualGenerateMultipartPartsResult.size());
    MultipartPart<? extends Part> getResult = actualGenerateMultipartPartsResult.get(0);
    assertTrue(getResult instanceof MessageEndMultipartPart);
    assertEquals(14L, getResult.length());
    assertEquals(MultipartState.PRE_CONTENT, getResult.getState());
    assertFalse(getResult.isTargetSlow());
  }
}
