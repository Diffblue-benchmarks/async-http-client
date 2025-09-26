package org.asynchttpclient.request.body.multipart;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import io.netty.handler.codec.http.DefaultHttpHeaders;
import io.netty.handler.codec.http.HttpHeaders;
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
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

class MultipartUtilsDiffblueTest {
  /**
   * Test {@link MultipartUtils#newMultipartBody(List, HttpHeaders)}.
   *
   * <ul>
   *   <li>Given {@code null}.
   *   <li>Then throw {@link IllegalArgumentException}.
   * </ul>
   *
   * <p>Method under test: {@link MultipartUtils#newMultipartBody(List, HttpHeaders)}
   */
  @Test
  @DisplayName(
      "Test newMultipartBody(List, HttpHeaders); given 'null'; then throw IllegalArgumentException")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "org.asynchttpclient.request.body.multipart.MultipartBody MultipartUtils.newMultipartBody(List, HttpHeaders)"
  })
  void testNewMultipartBody_givenNull_thenThrowIllegalArgumentException() {
    // Arrange
    ArrayList<Part> parts = new ArrayList<>();
    parts.add(null);

    // Act and Assert
    assertThrows(
        IllegalArgumentException.class,
        () -> MultipartUtils.newMultipartBody(parts, new DefaultHttpHeaders()));
  }

  /**
   * Test {@link MultipartUtils#newMultipartBody(List, HttpHeaders)}.
   *
   * <ul>
   *   <li>Then return ContentLength is minus one.
   * </ul>
   *
   * <p>Method under test: {@link MultipartUtils#newMultipartBody(List, HttpHeaders)}
   */
  @Test
  @DisplayName("Test newMultipartBody(List, HttpHeaders); then return ContentLength is minus one")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "org.asynchttpclient.request.body.multipart.MultipartBody MultipartUtils.newMultipartBody(List, HttpHeaders)"
  })
  void testNewMultipartBody_thenReturnContentLengthIsMinusOne()
      throws UnsupportedEncodingException {
    // Arrange
    ArrayList<Part> parts = new ArrayList<>();
    InputStreamPart inputStreamPart =
        new InputStreamPart(
            "https://example.org/example",
            new ByteArrayInputStream("AXAXAXAX".getBytes("UTF-8")),
            "https://example.org/example");
    parts.add(inputStreamPart);

    // Act and Assert
    assertEquals(
        -1L, MultipartUtils.newMultipartBody(parts, new DefaultHttpHeaders()).getContentLength());
  }

  /**
   * Test {@link MultipartUtils#generateMultipartParts(List, byte[])}.
   *
   * <p>Method under test: {@link MultipartUtils#generateMultipartParts(List, byte[])}
   */
  @Test
  @DisplayName("Test generateMultipartParts(List, byte[])")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"List MultipartUtils.generateMultipartParts(List, byte[])"})
  void testGenerateMultipartParts() throws UnsupportedEncodingException {
    // Arrange
    ArrayList<Part> parts = new ArrayList<>();
    ByteArrayPart byteArrayPart =
        new ByteArrayPart("https://example.org/example", "AXAXAXAX".getBytes("UTF-8"));
    parts.add(byteArrayPart);

    // Act
    List<MultipartPart<? extends Part>> actualGenerateMultipartPartsResult =
        MultipartUtils.generateMultipartParts(
            parts, new byte[] {'A', 'X', 'A', 'X', 'A', 'X', 'A', 'X'});

    // Assert
    assertEquals(2, actualGenerateMultipartPartsResult.size());
    MultipartPart<? extends Part> getResult = actualGenerateMultipartPartsResult.get(0);
    assertTrue(getResult instanceof ByteArrayMultipartPart);
    MultipartPart<? extends Part> getResult2 = actualGenerateMultipartPartsResult.get(1);
    assertTrue(getResult2 instanceof MessageEndMultipartPart);
    assertEquals(132L, getResult.length());
    assertEquals(14L, getResult2.length());
    assertEquals(MultipartState.PRE_CONTENT, getResult2.getState());
    assertFalse(getResult2.isTargetSlow());
  }

  /**
   * Test {@link MultipartUtils#generateMultipartParts(List, byte[])}.
   *
   * <p>Method under test: {@link MultipartUtils#generateMultipartParts(List, byte[])}
   */
  @Test
  @DisplayName("Test generateMultipartParts(List, byte[])")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"List MultipartUtils.generateMultipartParts(List, byte[])"})
  void testGenerateMultipartParts2() throws UnsupportedEncodingException {
    // Arrange
    ByteArrayPart byteArrayPart =
        new ByteArrayPart("https://example.org/example", "AXAXAXAX".getBytes("UTF-8"));
    byteArrayPart.setCustomHeaders(new ArrayList<>());

    ArrayList<Part> parts = new ArrayList<>();
    parts.add(byteArrayPart);

    // Act
    List<MultipartPart<? extends Part>> actualGenerateMultipartPartsResult =
        MultipartUtils.generateMultipartParts(
            parts, new byte[] {'A', 'X', 'A', 'X', 'A', 'X', 'A', 'X'});

    // Assert
    assertEquals(2, actualGenerateMultipartPartsResult.size());
    MultipartPart<? extends Part> getResult = actualGenerateMultipartPartsResult.get(0);
    assertTrue(getResult instanceof ByteArrayMultipartPart);
    MultipartPart<? extends Part> getResult2 = actualGenerateMultipartPartsResult.get(1);
    assertTrue(getResult2 instanceof MessageEndMultipartPart);
    assertEquals(132L, getResult.length());
    assertEquals(14L, getResult2.length());
    assertEquals(MultipartState.PRE_CONTENT, getResult2.getState());
    assertFalse(getResult2.isTargetSlow());
  }

  /**
   * Test {@link MultipartUtils#generateMultipartParts(List, byte[])}.
   *
   * <ul>
   *   <li>Given {@code A}.
   *   <li>Then return first length is one hundred fifty.
   * </ul>
   *
   * <p>Method under test: {@link MultipartUtils#generateMultipartParts(List, byte[])}
   */
  @Test
  @DisplayName(
      "Test generateMultipartParts(List, byte[]); given 'A'; then return first length is one hundred fifty")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"List MultipartUtils.generateMultipartParts(List, byte[])"})
  void testGenerateMultipartParts_givenA_thenReturnFirstLengthIsOneHundredFifty() {
    // Arrange
    ArrayList<Part> parts = new ArrayList<>();
    ByteArrayPart byteArrayPart =
        new ByteArrayPart(
            "https://example.org/example",
            new byte[] {'A', 1, 'A', 1, 'A', 1, 'A', 1},
            "https://example.org/example",
            Charset.forName("UTF-8"));
    parts.add(byteArrayPart);

    // Act
    List<MultipartPart<? extends Part>> actualGenerateMultipartPartsResult =
        MultipartUtils.generateMultipartParts(
            parts, new byte[] {'A', 'X', 'A', 'X', 'A', 'X', 'A', 'X'});

    // Assert
    assertEquals(2, actualGenerateMultipartPartsResult.size());
    MultipartPart<? extends Part> getResult = actualGenerateMultipartPartsResult.get(0);
    assertTrue(getResult instanceof ByteArrayMultipartPart);
    MultipartPart<? extends Part> getResult2 = actualGenerateMultipartPartsResult.get(1);
    assertTrue(getResult2 instanceof MessageEndMultipartPart);
    assertEquals(14L, getResult2.length());
    assertEquals(150L, getResult.length());
    assertEquals(MultipartState.PRE_CONTENT, getResult2.getState());
    assertFalse(getResult2.isTargetSlow());
  }

  /**
   * Test {@link MultipartUtils#generateMultipartParts(List, byte[])}.
   *
   * <ul>
   *   <li>Given {@code null}.
   *   <li>Then throw {@link IllegalArgumentException}.
   * </ul>
   *
   * <p>Method under test: {@link MultipartUtils#generateMultipartParts(List, byte[])}
   */
  @Test
  @DisplayName(
      "Test generateMultipartParts(List, byte[]); given 'null'; then throw IllegalArgumentException")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"List MultipartUtils.generateMultipartParts(List, byte[])"})
  void testGenerateMultipartParts_givenNull_thenThrowIllegalArgumentException() {
    // Arrange
    ArrayList<Part> parts = new ArrayList<>();
    parts.add(null);

    // Act and Assert
    assertThrows(
        IllegalArgumentException.class,
        () ->
            MultipartUtils.generateMultipartParts(
                parts, new byte[] {'A', 'X', 'A', 'X', 'A', 'X', 'A', 'X'}));
  }

  /**
   * Test {@link MultipartUtils#generateMultipartParts(List, byte[])}.
   *
   * <ul>
   *   <li>Then first return {@link InputStreamMultipartPart}.
   * </ul>
   *
   * <p>Method under test: {@link MultipartUtils#generateMultipartParts(List, byte[])}
   */
  @Test
  @DisplayName(
      "Test generateMultipartParts(List, byte[]); then first return InputStreamMultipartPart")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"List MultipartUtils.generateMultipartParts(List, byte[])"})
  void testGenerateMultipartParts_thenFirstReturnInputStreamMultipartPart()
      throws UnsupportedEncodingException {
    // Arrange
    ArrayList<Part> parts = new ArrayList<>();
    InputStreamPart inputStreamPart =
        new InputStreamPart(
            "https://example.org/example",
            new ByteArrayInputStream("AXAXAXAX".getBytes("UTF-8")),
            "https://example.org/example");
    parts.add(inputStreamPart);

    // Act
    List<MultipartPart<? extends Part>> actualGenerateMultipartPartsResult =
        MultipartUtils.generateMultipartParts(
            parts, new byte[] {'A', 'X', 'A', 'X', 'A', 'X', 'A', 'X'});

    // Assert
    assertEquals(2, actualGenerateMultipartPartsResult.size());
    MultipartPart<? extends Part> getResult = actualGenerateMultipartPartsResult.get(0);
    assertTrue(getResult instanceof InputStreamMultipartPart);
    MultipartPart<? extends Part> getResult2 = actualGenerateMultipartPartsResult.get(1);
    assertTrue(getResult2 instanceof MessageEndMultipartPart);
    assertEquals(-1L, getResult.length());
    assertEquals(14L, getResult2.length());
    assertEquals(MultipartState.PRE_CONTENT, getResult2.getState());
    assertFalse(getResult2.isTargetSlow());
  }

  /**
   * Test {@link MultipartUtils#generateMultipartParts(List, byte[])}.
   *
   * <ul>
   *   <li>Then return first length is eighty-four.
   * </ul>
   *
   * <p>Method under test: {@link MultipartUtils#generateMultipartParts(List, byte[])}
   */
  @Test
  @DisplayName("Test generateMultipartParts(List, byte[]); then return first length is eighty-four")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"List MultipartUtils.generateMultipartParts(List, byte[])"})
  void testGenerateMultipartParts_thenReturnFirstLengthIsEightyFour() {
    // Arrange
    ArrayList<Part> parts = new ArrayList<>();
    parts.add(new StringPart("https://example.org/example", ""));

    // Act
    List<MultipartPart<? extends Part>> actualGenerateMultipartPartsResult =
        MultipartUtils.generateMultipartParts(
            parts, new byte[] {'A', 'X', 'A', 'X', 'A', 'X', 'A', 'X'});

    // Assert
    assertEquals(2, actualGenerateMultipartPartsResult.size());
    MultipartPart<? extends Part> getResult = actualGenerateMultipartPartsResult.get(1);
    assertTrue(getResult instanceof MessageEndMultipartPart);
    MultipartPart<? extends Part> getResult2 = actualGenerateMultipartPartsResult.get(0);
    assertTrue(getResult2 instanceof StringMultipartPart);
    assertEquals(14L, getResult.length());
    assertEquals(84L, getResult2.length());
    assertEquals(MultipartState.PRE_CONTENT, getResult.getState());
    assertFalse(getResult.isTargetSlow());
  }

  /**
   * Test {@link MultipartUtils#generateMultipartParts(List, byte[])}.
   *
   * <ul>
   *   <li>Then return first length is one hundred eleven.
   * </ul>
   *
   * <p>Method under test: {@link MultipartUtils#generateMultipartParts(List, byte[])}
   */
  @Test
  @DisplayName(
      "Test generateMultipartParts(List, byte[]); then return first length is one hundred eleven")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"List MultipartUtils.generateMultipartParts(List, byte[])"})
  void testGenerateMultipartParts_thenReturnFirstLengthIsOneHundredEleven() {
    // Arrange
    ArrayList<Part> parts = new ArrayList<>();
    parts.add(new StringPart("https://example.org/example", "https://example.org/example"));

    // Act
    List<MultipartPart<? extends Part>> actualGenerateMultipartPartsResult =
        MultipartUtils.generateMultipartParts(
            parts, new byte[] {'A', 'X', 'A', 'X', 'A', 'X', 'A', 'X'});

    // Assert
    assertEquals(2, actualGenerateMultipartPartsResult.size());
    MultipartPart<? extends Part> getResult = actualGenerateMultipartPartsResult.get(1);
    assertTrue(getResult instanceof MessageEndMultipartPart);
    MultipartPart<? extends Part> getResult2 = actualGenerateMultipartPartsResult.get(0);
    assertTrue(getResult2 instanceof StringMultipartPart);
    assertEquals(111L, getResult2.length());
    assertEquals(14L, getResult.length());
    assertEquals(MultipartState.PRE_CONTENT, getResult.getState());
    assertFalse(getResult.isTargetSlow());
  }

  /**
   * Test {@link MultipartUtils#generateMultipartParts(List, byte[])}.
   *
   * <ul>
   *   <li>Then return first length is one hundred ninety.
   * </ul>
   *
   * <p>Method under test: {@link MultipartUtils#generateMultipartParts(List, byte[])}
   */
  @Test
  @DisplayName(
      "Test generateMultipartParts(List, byte[]); then return first length is one hundred ninety")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"List MultipartUtils.generateMultipartParts(List, byte[])"})
  void testGenerateMultipartParts_thenReturnFirstLengthIsOneHundredNinety()
      throws UnsupportedEncodingException {
    // Arrange
    ByteArrayPart byteArrayPart =
        new ByteArrayPart("https://example.org/example", "AXAXAXAX".getBytes("UTF-8"));
    byteArrayPart.addCustomHeader("https://example.org/example", "https://example.org/example");

    ArrayList<Part> parts = new ArrayList<>();
    parts.add(byteArrayPart);

    // Act
    List<MultipartPart<? extends Part>> actualGenerateMultipartPartsResult =
        MultipartUtils.generateMultipartParts(
            parts, new byte[] {'A', 'X', 'A', 'X', 'A', 'X', 'A', 'X'});

    // Assert
    assertEquals(2, actualGenerateMultipartPartsResult.size());
    MultipartPart<? extends Part> getResult = actualGenerateMultipartPartsResult.get(0);
    assertTrue(getResult instanceof ByteArrayMultipartPart);
    MultipartPart<? extends Part> getResult2 = actualGenerateMultipartPartsResult.get(1);
    assertTrue(getResult2 instanceof MessageEndMultipartPart);
    assertEquals(14L, getResult2.length());
    assertEquals(190L, getResult.length());
    assertEquals(MultipartState.PRE_CONTENT, getResult2.getState());
    assertFalse(getResult2.isTargetSlow());
  }

  /**
   * Test {@link MultipartUtils#generateMultipartParts(List, byte[])}.
   *
   * <ul>
   *   <li>Then return first length is two hundred eight.
   * </ul>
   *
   * <p>Method under test: {@link MultipartUtils#generateMultipartParts(List, byte[])}
   */
  @Test
  @DisplayName(
      "Test generateMultipartParts(List, byte[]); then return first length is two hundred eight")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"List MultipartUtils.generateMultipartParts(List, byte[])"})
  void testGenerateMultipartParts_thenReturnFirstLengthIsTwoHundredEight()
      throws UnsupportedEncodingException {
    // Arrange
    ByteArrayPart byteArrayPart =
        new ByteArrayPart("https://example.org/example", "AXAXAXAX".getBytes("UTF-8"));
    byteArrayPart.setDispositionType("https://example.org/example");
    byteArrayPart.addCustomHeader("https://example.org/example", "https://example.org/example");

    ArrayList<Part> parts = new ArrayList<>();
    parts.add(byteArrayPart);

    // Act
    List<MultipartPart<? extends Part>> actualGenerateMultipartPartsResult =
        MultipartUtils.generateMultipartParts(
            parts, new byte[] {'A', 'X', 'A', 'X', 'A', 'X', 'A', 'X'});

    // Assert
    assertEquals(2, actualGenerateMultipartPartsResult.size());
    MultipartPart<? extends Part> getResult = actualGenerateMultipartPartsResult.get(0);
    assertTrue(getResult instanceof ByteArrayMultipartPart);
    MultipartPart<? extends Part> getResult2 = actualGenerateMultipartPartsResult.get(1);
    assertTrue(getResult2 instanceof MessageEndMultipartPart);
    assertEquals(14L, getResult2.length());
    assertEquals(208L, getResult.length());
    assertEquals(MultipartState.PRE_CONTENT, getResult2.getState());
    assertFalse(getResult2.isTargetSlow());
  }

  /**
   * Test {@link MultipartUtils#generateMultipartParts(List, byte[])}.
   *
   * <ul>
   *   <li>When {@link ArrayList#ArrayList()}.
   *   <li>Then return size is one.
   * </ul>
   *
   * <p>Method under test: {@link MultipartUtils#generateMultipartParts(List, byte[])}
   */
  @Test
  @DisplayName(
      "Test generateMultipartParts(List, byte[]); when ArrayList(); then return size is one")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"List MultipartUtils.generateMultipartParts(List, byte[])"})
  void testGenerateMultipartParts_whenArrayList_thenReturnSizeIsOne()
      throws UnsupportedEncodingException {
    // Arrange and Act
    List<MultipartPart<? extends Part>> actualGenerateMultipartPartsResult =
        MultipartUtils.generateMultipartParts(new ArrayList<>(), "AXAXAXAX".getBytes("UTF-8"));

    // Assert
    assertEquals(1, actualGenerateMultipartPartsResult.size());
    MultipartPart<? extends Part> getResult = actualGenerateMultipartPartsResult.get(0);
    assertTrue(getResult instanceof MessageEndMultipartPart);
    assertEquals(14L, getResult.length());
    assertEquals(MultipartState.PRE_CONTENT, getResult.getState());
    assertFalse(getResult.isTargetSlow());
  }
}
