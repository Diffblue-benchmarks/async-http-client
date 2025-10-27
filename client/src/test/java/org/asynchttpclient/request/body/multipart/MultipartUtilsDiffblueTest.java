package org.asynchttpclient.request.body.multipart;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import java.io.ByteArrayInputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import org.asynchttpclient.request.body.multipart.part.ByteArrayMultipartPart;
import org.asynchttpclient.request.body.multipart.part.InputStreamMultipartPart;
import org.asynchttpclient.request.body.multipart.part.MessageEndMultipartPart;
import org.asynchttpclient.request.body.multipart.part.MultipartPart;
import org.asynchttpclient.request.body.multipart.part.MultipartState;
import org.asynchttpclient.request.body.multipart.part.StringMultipartPart;
import org.junit.jupiter.api.Test;

class MultipartUtilsDiffblueTest {
  /**
   * Method under test:
   * {@link MultipartUtils#generateMultipartParts(List, byte[])}
   */
  @Test
  void testGenerateMultipartParts() throws UnsupportedEncodingException {
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

  /**
   * Method under test:
   * {@link MultipartUtils#generateMultipartParts(List, byte[])}
   */
  @Test
  void testGenerateMultipartParts2() throws UnsupportedEncodingException {
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
    assertEquals(MultipartState.PRE_CONTENT, getResult.getState());
    assertEquals(MultipartState.PRE_CONTENT, getResult2.getState());
    assertFalse(getResult.isTargetSlow());
    assertFalse(getResult2.isTargetSlow());
  }

  /**
   * Method under test:
   * {@link MultipartUtils#generateMultipartParts(List, byte[])}
   */
  @Test
  void testGenerateMultipartParts3() throws UnsupportedEncodingException {
    // Arrange
    ArrayList<Part> parts = new ArrayList<>();
    parts.add(new ByteArrayPart("https://example.org/example", "AXAXAXAX".getBytes("UTF-8")));
    parts.add(new ByteArrayPart("https://example.org/example", "AXAXAXAX".getBytes("UTF-8")));

    // Act
    List<MultipartPart<? extends Part>> actualGenerateMultipartPartsResult = MultipartUtils
        .generateMultipartParts(parts, "AXAXAXAX".getBytes("UTF-8"));

    // Assert
    assertEquals(3, actualGenerateMultipartPartsResult.size());
    MultipartPart<? extends Part> getResult = actualGenerateMultipartPartsResult.get(0);
    assertTrue(getResult instanceof ByteArrayMultipartPart);
    MultipartPart<? extends Part> getResult2 = actualGenerateMultipartPartsResult.get(1);
    assertTrue(getResult2 instanceof ByteArrayMultipartPart);
    MultipartPart<? extends Part> getResult3 = actualGenerateMultipartPartsResult.get(2);
    assertTrue(getResult3 instanceof MessageEndMultipartPart);
    assertEquals(132L, getResult.length());
    assertEquals(132L, getResult2.length());
    assertEquals(14L, getResult3.length());
    assertEquals(MultipartState.PRE_CONTENT, getResult.getState());
    assertEquals(MultipartState.PRE_CONTENT, getResult2.getState());
    assertEquals(MultipartState.PRE_CONTENT, getResult3.getState());
    assertFalse(getResult.isTargetSlow());
    assertFalse(getResult2.isTargetSlow());
    assertFalse(getResult3.isTargetSlow());
  }

  /**
   * Method under test:
   * {@link MultipartUtils#generateMultipartParts(List, byte[])}
   */
  @Test
  void testGenerateMultipartParts4() throws UnsupportedEncodingException {
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
    assertEquals(MultipartState.PRE_CONTENT, getResult.getState());
    assertEquals(MultipartState.PRE_CONTENT, getResult2.getState());
    assertFalse(getResult.isTargetSlow());
    assertFalse(getResult2.isTargetSlow());
  }

  /**
   * Method under test:
   * {@link MultipartUtils#generateMultipartParts(List, byte[])}
   */
  @Test
  void testGenerateMultipartParts5() throws UnsupportedEncodingException {
    // Arrange
    ArrayList<Part> parts = new ArrayList<>();
    parts.add(null);

    // Act and Assert
    assertThrows(IllegalArgumentException.class,
        () -> MultipartUtils.generateMultipartParts(parts, "AXAXAXAX".getBytes("UTF-8")));
  }

  /**
   * Method under test:
   * {@link MultipartUtils#generateMultipartParts(List, byte[])}
   */
  @Test
  void testGenerateMultipartParts6() throws UnsupportedEncodingException {
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
    assertEquals(MultipartState.PRE_CONTENT, getResult.getState());
    assertEquals(MultipartState.PRE_CONTENT, getResult2.getState());
    assertFalse(getResult.isTargetSlow());
    assertFalse(getResult2.isTargetSlow());
  }

  /**
   * Method under test:
   * {@link MultipartUtils#generateMultipartParts(List, byte[])}
   */
  @Test
  void testGenerateMultipartParts7() throws UnsupportedEncodingException {
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
    assertEquals(MultipartState.PRE_CONTENT, getResult2.getState());
    assertEquals(MultipartState.PRE_CONTENT, getResult.getState());
    assertFalse(getResult2.isTargetSlow());
    assertFalse(getResult.isTargetSlow());
  }

  /**
   * Method under test:
   * {@link MultipartUtils#generateMultipartParts(List, byte[])}
   */
  @Test
  void testGenerateMultipartParts8() throws UnsupportedEncodingException {
    // Arrange
    ArrayList<Part> parts = new ArrayList<>();
    parts.add(
        new StringPart("https://example.org/example", "https://example.org/example", "https://example.org/example"));

    // Act
    List<MultipartPart<? extends Part>> actualGenerateMultipartPartsResult = MultipartUtils
        .generateMultipartParts(parts, "AXAXAXAX".getBytes("UTF-8"));

    // Assert
    assertEquals(2, actualGenerateMultipartPartsResult.size());
    MultipartPart<? extends Part> getResult = actualGenerateMultipartPartsResult.get(1);
    assertTrue(getResult instanceof MessageEndMultipartPart);
    MultipartPart<? extends Part> getResult2 = actualGenerateMultipartPartsResult.get(0);
    assertTrue(getResult2 instanceof StringMultipartPart);
    assertEquals(14L, getResult.length());
    assertEquals(169L, getResult2.length());
    assertEquals(MultipartState.PRE_CONTENT, getResult2.getState());
    assertEquals(MultipartState.PRE_CONTENT, getResult.getState());
    assertFalse(getResult2.isTargetSlow());
    assertFalse(getResult.isTargetSlow());
  }

  /**
   * Method under test:
   * {@link MultipartUtils#generateMultipartParts(List, byte[])}
   */
  @Test
  void testGenerateMultipartParts9() throws UnsupportedEncodingException {
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
    assertEquals(MultipartState.PRE_CONTENT, getResult.getState());
    assertEquals(MultipartState.PRE_CONTENT, getResult2.getState());
    assertFalse(getResult.isTargetSlow());
    assertFalse(getResult2.isTargetSlow());
  }

  /**
   * Method under test:
   * {@link MultipartUtils#generateMultipartParts(List, byte[])}
   */
  @Test
  void testGenerateMultipartParts10() throws UnsupportedEncodingException {
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
    assertEquals(MultipartState.PRE_CONTENT, getResult.getState());
    assertEquals(MultipartState.PRE_CONTENT, getResult2.getState());
    assertFalse(getResult.isTargetSlow());
    assertFalse(getResult2.isTargetSlow());
  }

  /**
   * Method under test:
   * {@link MultipartUtils#generateMultipartParts(List, byte[])}
   */
  @Test
  void testGenerateMultipartParts11() throws UnsupportedEncodingException {
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
    assertEquals(MultipartState.PRE_CONTENT, getResult.getState());
    assertEquals(MultipartState.PRE_CONTENT, getResult2.getState());
    assertFalse(getResult.isTargetSlow());
    assertFalse(getResult2.isTargetSlow());
  }
}
