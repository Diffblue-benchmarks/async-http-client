package org.asynchttpclient.request.body.multipart.part;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import io.netty.buffer.AdaptiveByteBufAllocator;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.CompositeByteBuf;
import io.netty.buffer.DuplicatedByteBuf;
import io.netty.buffer.EmptyByteBuf;
import io.netty.buffer.ReadOnlyByteBuf;
import io.netty.buffer.Unpooled;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.channels.WritableByteChannel;
import java.nio.charset.Charset;
import java.util.ArrayList;
import org.apache.tomcat.util.net.NioChannel;
import org.apache.tomcat.util.net.SocketBufferHandler;
import org.asynchttpclient.request.body.multipart.ByteArrayPart;
import org.asynchttpclient.request.body.multipart.part.PartVisitor.CounterPartVisitor;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

class MultipartPartDiffblueTest {
  /**
   * Test {@link MultipartPart#length()}.
   *
   * <p>Method under test: {@link MultipartPart#length()}
   */
  @Test
  @DisplayName("Test length()")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"long MultipartPart.length()"})
  void testLength() throws UnsupportedEncodingException {
    // Arrange, Act and Assert
    assertEquals(14L, new MessageEndMultipartPart("AXAXAXAX".getBytes("UTF-8")).length());
  }

  /**
   * Test {@link MultipartPart#getState()}.
   *
   * <p>Method under test: {@link MultipartPart#getState()}
   */
  @Test
  @DisplayName("Test getState()")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"MultipartState MultipartPart.getState()"})
  void testGetState() throws UnsupportedEncodingException {
    // Arrange, Act and Assert
    assertEquals(
        MultipartState.PRE_CONTENT,
        new MessageEndMultipartPart("AXAXAXAX".getBytes("UTF-8")).getState());
  }

  /**
   * Test {@link MultipartPart#isTargetSlow()}.
   *
   * <p>Method under test: {@link MultipartPart#isTargetSlow()}
   */
  @Test
  @DisplayName("Test isTargetSlow()")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean MultipartPart.isTargetSlow()"})
  void testIsTargetSlow() throws UnsupportedEncodingException {
    // Arrange, Act and Assert
    assertFalse(new MessageEndMultipartPart("AXAXAXAX".getBytes("UTF-8")).isTargetSlow());
  }

  /**
   * Test {@link MultipartPart#transferTo(ByteBuf)} with {@code ByteBuf}.
   *
   * <p>Method under test: {@link MultipartPart#transferTo(ByteBuf)}
   */
  @Test
  @DisplayName("Test transferTo(ByteBuf) with 'ByteBuf'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"long MultipartPart.transferTo(ByteBuf)"})
  void testTransferToWithByteBuf() throws IOException {
    // Arrange
    ByteArrayPart part =
        new ByteArrayPart("https://example.org/example", "AXAXAXAX".getBytes("UTF-8"));
    ByteArrayMultipartPart byteArrayMultipartPart =
        new ByteArrayMultipartPart(part, "AXAXAXAX".getBytes("UTF-8"));

    // Act
    long actualTransferToResult =
        byteArrayMultipartPart.transferTo(
            new DuplicatedByteBuf(new EmptyByteBuf(new AdaptiveByteBufAllocator())));

    // Assert
    assertEquals(0L, actualTransferToResult);
  }

  /**
   * Test {@link MultipartPart#transferTo(ByteBuf)} with {@code ByteBuf}.
   *
   * <p>Method under test: {@link MultipartPart#transferTo(ByteBuf)}
   */
  @Test
  @DisplayName("Test transferTo(ByteBuf) with 'ByteBuf'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"long MultipartPart.transferTo(ByteBuf)"})
  void testTransferToWithByteBuf2() throws IOException {
    // Arrange
    ByteArrayPart part = new ByteArrayPart(null, "AXAXAXAX".getBytes("UTF-8"));
    ByteArrayMultipartPart byteArrayMultipartPart =
        new ByteArrayMultipartPart(part, "AXAXAXAX".getBytes("UTF-8"));

    // Act
    long actualTransferToResult =
        byteArrayMultipartPart.transferTo(
            new DuplicatedByteBuf(new EmptyByteBuf(new AdaptiveByteBufAllocator())));

    // Assert
    assertEquals(0L, actualTransferToResult);
  }

  /**
   * Test {@link MultipartPart#transferTo(ByteBuf)} with {@code ByteBuf}.
   *
   * <p>Method under test: {@link MultipartPart#transferTo(ByteBuf)}
   */
  @Test
  @DisplayName("Test transferTo(ByteBuf) with 'ByteBuf'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"long MultipartPart.transferTo(ByteBuf)"})
  void testTransferToWithByteBuf3() throws IOException {
    // Arrange
    ByteArrayPart part =
        new ByteArrayPart("https://example.org/example", "AXAXAXAX".getBytes("UTF-8"));
    part.addCustomHeader("https://example.org/example", "https://example.org/example");
    ByteArrayMultipartPart byteArrayMultipartPart =
        new ByteArrayMultipartPart(part, "AXAXAXAX".getBytes("UTF-8"));

    // Act
    long actualTransferToResult =
        byteArrayMultipartPart.transferTo(
            new DuplicatedByteBuf(new EmptyByteBuf(new AdaptiveByteBufAllocator())));

    // Assert
    assertEquals(0L, actualTransferToResult);
  }

  /**
   * Test {@link MultipartPart#transferTo(ByteBuf)} with {@code ByteBuf}.
   *
   * <p>Method under test: {@link MultipartPart#transferTo(ByteBuf)}
   */
  @Test
  @DisplayName("Test transferTo(ByteBuf) with 'ByteBuf'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"long MultipartPart.transferTo(ByteBuf)"})
  void testTransferToWithByteBuf4() throws IOException {
    // Arrange
    ByteArrayPart part =
        new ByteArrayPart("https://example.org/example", "AXAXAXAX".getBytes("UTF-8"));
    part.setDispositionType("https://example.org/example");
    part.addCustomHeader("https://example.org/example", "https://example.org/example");
    ByteArrayMultipartPart byteArrayMultipartPart =
        new ByteArrayMultipartPart(part, "AXAXAXAX".getBytes("UTF-8"));

    // Act
    long actualTransferToResult =
        byteArrayMultipartPart.transferTo(
            new DuplicatedByteBuf(new EmptyByteBuf(new AdaptiveByteBufAllocator())));

    // Assert
    assertEquals(0L, actualTransferToResult);
  }

  /**
   * Test {@link MultipartPart#transferTo(ByteBuf)} with {@code ByteBuf}.
   *
   * <p>Method under test: {@link MultipartPart#transferTo(ByteBuf)}
   */
  @Test
  @DisplayName("Test transferTo(ByteBuf) with 'ByteBuf'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"long MultipartPart.transferTo(ByteBuf)"})
  void testTransferToWithByteBuf5() throws IOException {
    // Arrange
    ByteArrayPart part =
        new ByteArrayPart("https://example.org/example", "AXAXAXAX".getBytes("UTF-8"));
    ByteArrayMultipartPart byteArrayMultipartPart =
        new ByteArrayMultipartPart(part, "AXAXAXAX".getBytes("UTF-8"));

    // Act
    long actualTransferToResult =
        byteArrayMultipartPart.transferTo(new EmptyByteBuf(new AdaptiveByteBufAllocator()));

    // Assert
    assertEquals(0L, actualTransferToResult);
  }

  /**
   * Test {@link MultipartPart#transferTo(ByteBuf)} with {@code ByteBuf}.
   *
   * <ul>
   *   <li>Given {@code A}.
   *   <li>When {@link DuplicatedByteBuf#DuplicatedByteBuf(ByteBuf)} with buffer is {@link
   *       EmptyByteBuf#EmptyByteBuf(ByteBufAllocator)}.
   * </ul>
   *
   * <p>Method under test: {@link MultipartPart#transferTo(ByteBuf)}
   */
  @Test
  @DisplayName(
      "Test transferTo(ByteBuf) with 'ByteBuf'; given 'A'; when DuplicatedByteBuf(ByteBuf) with buffer is EmptyByteBuf(ByteBufAllocator)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"long MultipartPart.transferTo(ByteBuf)"})
  void testTransferToWithByteBuf_givenA_whenDuplicatedByteBufWithBufferIsEmptyByteBuf()
      throws IOException {
    // Arrange
    ByteArrayPart part =
        new ByteArrayPart(
            "https://example.org/example",
            new byte[] {'A', 1, 'A', 1, 'A', 1, 'A', 1},
            "https://example.org/example",
            Charset.forName("UTF-8"));
    ByteArrayMultipartPart byteArrayMultipartPart =
        new ByteArrayMultipartPart(part, "AXAXAXAX".getBytes("UTF-8"));

    // Act
    long actualTransferToResult =
        byteArrayMultipartPart.transferTo(
            new DuplicatedByteBuf(new EmptyByteBuf(new AdaptiveByteBufAllocator())));

    // Assert
    assertEquals(0L, actualTransferToResult);
  }

  /**
   * Test {@link MultipartPart#transfer(ByteBuf, ByteBuf, MultipartState)} with {@code ByteBuf},
   * {@code ByteBuf}, {@code MultipartState}.
   *
   * <ul>
   *   <li>Then return zero.
   * </ul>
   *
   * <p>Method under test: {@link MultipartPart#transfer(ByteBuf, ByteBuf, MultipartState)}
   */
  @Test
  @DisplayName(
      "Test transfer(ByteBuf, ByteBuf, MultipartState) with 'ByteBuf', 'ByteBuf', 'MultipartState'; then return zero")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"long MultipartPart.transfer(ByteBuf, ByteBuf, MultipartState)"})
  void testTransferWithByteBufByteBufMultipartState_thenReturnZero()
      throws UnsupportedEncodingException {
    // Arrange
    MessageEndMultipartPart messageEndMultipartPart =
        new MessageEndMultipartPart("AXAXAXAX".getBytes("UTF-8"));
    DuplicatedByteBuf source =
        new DuplicatedByteBuf(new EmptyByteBuf(new AdaptiveByteBufAllocator()));

    // Act and Assert
    assertEquals(
        0L,
        messageEndMultipartPart.transfer(
            source,
            new DuplicatedByteBuf(new EmptyByteBuf(new AdaptiveByteBufAllocator())),
            MultipartState.PRE_CONTENT));
  }

  /**
   * Test {@link MultipartPart#transfer(ByteBuf, ByteBuf, MultipartState)} with {@code ByteBuf},
   * {@code ByteBuf}, {@code MultipartState}.
   *
   * <ul>
   *   <li>When compositeBuffer three.
   * </ul>
   *
   * <p>Method under test: {@link MultipartPart#transfer(ByteBuf, ByteBuf, MultipartState)}
   */
  @Test
  @DisplayName(
      "Test transfer(ByteBuf, ByteBuf, MultipartState) with 'ByteBuf', 'ByteBuf', 'MultipartState'; when compositeBuffer three")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"long MultipartPart.transfer(ByteBuf, ByteBuf, MultipartState)"})
  void testTransferWithByteBufByteBufMultipartState_whenCompositeBufferThree()
      throws UnsupportedEncodingException {
    // Arrange
    MessageEndMultipartPart messageEndMultipartPart =
        new MessageEndMultipartPart("AXAXAXAX".getBytes("UTF-8"));
    DuplicatedByteBuf source =
        new DuplicatedByteBuf(new EmptyByteBuf(new AdaptiveByteBufAllocator()));

    // Act and Assert
    assertEquals(
        0L,
        messageEndMultipartPart.transfer(
            source, Unpooled.compositeBuffer(3), MultipartState.PRE_CONTENT));
  }

  /**
   * Test {@link MultipartPart#transfer(ByteBuf, WritableByteChannel, MultipartState)} with {@code
   * ByteBuf}, {@code WritableByteChannel}, {@code MultipartState}.
   *
   * <p>Method under test: {@link MultipartPart#transfer(ByteBuf, WritableByteChannel,
   * MultipartState)}
   */
  @Test
  @DisplayName(
      "Test transfer(ByteBuf, WritableByteChannel, MultipartState) with 'ByteBuf', 'WritableByteChannel', 'MultipartState'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"long MultipartPart.transfer(ByteBuf, WritableByteChannel, MultipartState)"})
  void testTransferWithByteBufWritableByteChannelMultipartState() throws IOException {
    // Arrange
    MessageEndMultipartPart messageEndMultipartPart =
        new MessageEndMultipartPart("AXAXAXAX".getBytes("UTF-8"));
    CompositeByteBuf buffer = Unpooled.compositeBuffer(3);
    ReadOnlyByteBuf buffer2 = new ReadOnlyByteBuf(buffer);
    DuplicatedByteBuf source = new DuplicatedByteBuf(buffer2);

    // Act and Assert
    assertEquals(
        0L,
        messageEndMultipartPart.transfer(
            source,
            new NioChannel(new SocketBufferHandler(3, 3, true)),
            MultipartState.PRE_CONTENT));
  }

  /**
   * Test {@link MultipartPart#transfer(ByteBuf, WritableByteChannel, MultipartState)} with {@code
   * ByteBuf}, {@code WritableByteChannel}, {@code MultipartState}.
   *
   * <ul>
   *   <li>Then return zero.
   * </ul>
   *
   * <p>Method under test: {@link MultipartPart#transfer(ByteBuf, WritableByteChannel,
   * MultipartState)}
   */
  @Test
  @DisplayName(
      "Test transfer(ByteBuf, WritableByteChannel, MultipartState) with 'ByteBuf', 'WritableByteChannel', 'MultipartState'; then return zero")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"long MultipartPart.transfer(ByteBuf, WritableByteChannel, MultipartState)"})
  void testTransferWithByteBufWritableByteChannelMultipartState_thenReturnZero()
      throws IOException {
    // Arrange
    MessageEndMultipartPart messageEndMultipartPart =
        new MessageEndMultipartPart("AXAXAXAX".getBytes("UTF-8"));
    DuplicatedByteBuf source =
        new DuplicatedByteBuf(new EmptyByteBuf(new AdaptiveByteBufAllocator()));

    // Act and Assert
    assertEquals(
        0L,
        messageEndMultipartPart.transfer(
            source,
            new NioChannel(new SocketBufferHandler(3, 3, true)),
            MultipartState.PRE_CONTENT));
  }

  /**
   * Test {@link MultipartPart#computePreContentLength()}.
   *
   * <p>Method under test: {@link MultipartPart#computePreContentLength()}
   */
  @Test
  @DisplayName("Test computePreContentLength()")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"int MultipartPart.computePreContentLength()"})
  void testComputePreContentLength() throws UnsupportedEncodingException {
    // Arrange
    ByteArrayPart part =
        new ByteArrayPart("https://example.org/example", "AXAXAXAX".getBytes("UTF-8"));
    ByteArrayMultipartPart byteArrayMultipartPart =
        new ByteArrayMultipartPart(part, "AXAXAXAX".getBytes("UTF-8"));

    // Act and Assert
    assertEquals(122, byteArrayMultipartPart.computePreContentLength());
  }

  /**
   * Test {@link MultipartPart#computePreContentLength()}.
   *
   * <p>Method under test: {@link MultipartPart#computePreContentLength()}
   */
  @Test
  @DisplayName("Test computePreContentLength()")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"int MultipartPart.computePreContentLength()"})
  void testComputePreContentLength2() throws UnsupportedEncodingException {
    // Arrange
    ByteArrayPart part =
        new ByteArrayPart("https://example.org/example", "AXAXAXAX".getBytes("UTF-8"));
    part.setCustomHeaders(new ArrayList<>());
    ByteArrayMultipartPart byteArrayMultipartPart =
        new ByteArrayMultipartPart(part, "AXAXAXAX".getBytes("UTF-8"));

    // Act and Assert
    assertEquals(122, byteArrayMultipartPart.computePreContentLength());
  }

  /**
   * Test {@link MultipartPart#computePreContentLength()}.
   *
   * <ul>
   *   <li>Then return eighty-six.
   * </ul>
   *
   * <p>Method under test: {@link MultipartPart#computePreContentLength()}
   */
  @Test
  @DisplayName("Test computePreContentLength(); then return eighty-six")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"int MultipartPart.computePreContentLength()"})
  void testComputePreContentLength_thenReturnEightySix() throws UnsupportedEncodingException {
    // Arrange
    ByteArrayPart part = new ByteArrayPart(null, "AXAXAXAX".getBytes("UTF-8"));
    ByteArrayMultipartPart byteArrayMultipartPart =
        new ByteArrayMultipartPart(part, "AXAXAXAX".getBytes("UTF-8"));

    // Act and Assert
    assertEquals(86, byteArrayMultipartPart.computePreContentLength());
  }

  /**
   * Test {@link MultipartPart#computePreContentLength()}.
   *
   * <ul>
   *   <li>Then return one hundred eighty.
   * </ul>
   *
   * <p>Method under test: {@link MultipartPart#computePreContentLength()}
   */
  @Test
  @DisplayName("Test computePreContentLength(); then return one hundred eighty")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"int MultipartPart.computePreContentLength()"})
  void testComputePreContentLength_thenReturnOneHundredEighty()
      throws UnsupportedEncodingException {
    // Arrange
    ByteArrayPart part =
        new ByteArrayPart("https://example.org/example", "AXAXAXAX".getBytes("UTF-8"));
    part.addCustomHeader("https://example.org/example", "https://example.org/example");
    ByteArrayMultipartPart byteArrayMultipartPart =
        new ByteArrayMultipartPart(part, "AXAXAXAX".getBytes("UTF-8"));

    // Act and Assert
    assertEquals(180, byteArrayMultipartPart.computePreContentLength());
  }

  /**
   * Test {@link MultipartPart#computePreContentLength()}.
   *
   * <ul>
   *   <li>Then return one hundred forty.
   * </ul>
   *
   * <p>Method under test: {@link MultipartPart#computePreContentLength()}
   */
  @Test
  @DisplayName("Test computePreContentLength(); then return one hundred forty")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"int MultipartPart.computePreContentLength()"})
  void testComputePreContentLength_thenReturnOneHundredForty() throws UnsupportedEncodingException {
    // Arrange
    byte[] bytes = "A\"A\"A\"A\"".getBytes("UTF-8");
    ByteArrayPart part =
        new ByteArrayPart(
            "https://example.org/example",
            bytes,
            "https://example.org/example",
            Charset.forName("UTF-8"));
    ByteArrayMultipartPart byteArrayMultipartPart =
        new ByteArrayMultipartPart(part, "AXAXAXAX".getBytes("UTF-8"));

    // Act and Assert
    assertEquals(140, byteArrayMultipartPart.computePreContentLength());
  }

  /**
   * Test {@link MultipartPart#computePreContentLength()}.
   *
   * <ul>
   *   <li>Then return one hundred ninety-eight.
   * </ul>
   *
   * <p>Method under test: {@link MultipartPart#computePreContentLength()}
   */
  @Test
  @DisplayName("Test computePreContentLength(); then return one hundred ninety-eight")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"int MultipartPart.computePreContentLength()"})
  void testComputePreContentLength_thenReturnOneHundredNinetyEight()
      throws UnsupportedEncodingException {
    // Arrange
    ByteArrayPart part =
        new ByteArrayPart("https://example.org/example", "AXAXAXAX".getBytes("UTF-8"));
    part.setDispositionType("https://example.org/example");
    part.addCustomHeader("https://example.org/example", "https://example.org/example");
    ByteArrayMultipartPart byteArrayMultipartPart =
        new ByteArrayMultipartPart(part, "AXAXAXAX".getBytes("UTF-8"));

    // Act and Assert
    assertEquals(198, byteArrayMultipartPart.computePreContentLength());
  }

  /**
   * Test {@link MultipartPart#computePreContentBytes(int)}.
   *
   * <p>Method under test: {@link MultipartPart#computePreContentBytes(int)}
   */
  @Test
  @DisplayName("Test computePreContentBytes(int)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"ByteBuf MultipartPart.computePreContentBytes(int)"})
  void testComputePreContentBytes() throws UnsupportedEncodingException {
    // Arrange
    ByteArrayPart part =
        new ByteArrayPart("https://example.org/example", "AXAXAXAX".getBytes("UTF-8"));
    ByteArrayMultipartPart byteArrayMultipartPart =
        new ByteArrayMultipartPart(part, "AXAXAXAX".getBytes("UTF-8"));

    // Act and Assert
    assertTrue(byteArrayMultipartPart.computePreContentBytes(3).isContiguous());
  }

  /**
   * Test {@link MultipartPart#computePreContentBytes(int)}.
   *
   * <p>Method under test: {@link MultipartPart#computePreContentBytes(int)}
   */
  @Test
  @DisplayName("Test computePreContentBytes(int)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"ByteBuf MultipartPart.computePreContentBytes(int)"})
  void testComputePreContentBytes2() throws UnsupportedEncodingException {
    // Arrange
    ByteArrayPart part = new ByteArrayPart(null, "AXAXAXAX".getBytes("UTF-8"));
    ByteArrayMultipartPart byteArrayMultipartPart =
        new ByteArrayMultipartPart(part, "AXAXAXAX".getBytes("UTF-8"));

    // Act and Assert
    assertTrue(byteArrayMultipartPart.computePreContentBytes(3).isContiguous());
  }

  /**
   * Test {@link MultipartPart#computePreContentBytes(int)}.
   *
   * <p>Method under test: {@link MultipartPart#computePreContentBytes(int)}
   */
  @Test
  @DisplayName("Test computePreContentBytes(int)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"ByteBuf MultipartPart.computePreContentBytes(int)"})
  void testComputePreContentBytes3() throws UnsupportedEncodingException {
    // Arrange
    byte[] bytes = "A\"A\"A\"A\"".getBytes("UTF-8");
    ByteArrayPart part =
        new ByteArrayPart(
            "https://example.org/example",
            bytes,
            "https://example.org/example",
            Charset.forName("UTF-8"));
    ByteArrayMultipartPart byteArrayMultipartPart =
        new ByteArrayMultipartPart(part, "AXAXAXAX".getBytes("UTF-8"));

    // Act and Assert
    assertTrue(byteArrayMultipartPart.computePreContentBytes(3).isContiguous());
  }

  /**
   * Test {@link MultipartPart#computePreContentBytes(int)}.
   *
   * <p>Method under test: {@link MultipartPart#computePreContentBytes(int)}
   */
  @Test
  @DisplayName("Test computePreContentBytes(int)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"ByteBuf MultipartPart.computePreContentBytes(int)"})
  void testComputePreContentBytes4() throws UnsupportedEncodingException {
    // Arrange
    ByteArrayPart part =
        new ByteArrayPart("https://example.org/example", "AXAXAXAX".getBytes("UTF-8"));
    part.addCustomHeader("https://example.org/example", "https://example.org/example");
    ByteArrayMultipartPart byteArrayMultipartPart =
        new ByteArrayMultipartPart(part, "AXAXAXAX".getBytes("UTF-8"));

    // Act and Assert
    assertTrue(byteArrayMultipartPart.computePreContentBytes(3).isContiguous());
  }

  /**
   * Test {@link MultipartPart#computePreContentBytes(int)}.
   *
   * <p>Method under test: {@link MultipartPart#computePreContentBytes(int)}
   */
  @Test
  @DisplayName("Test computePreContentBytes(int)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"ByteBuf MultipartPart.computePreContentBytes(int)"})
  void testComputePreContentBytes5() throws UnsupportedEncodingException {
    // Arrange
    ByteArrayPart part =
        new ByteArrayPart("https://example.org/example", "AXAXAXAX".getBytes("UTF-8"));
    part.setDispositionType("https://example.org/example");
    part.addCustomHeader("https://example.org/example", "https://example.org/example");
    ByteArrayMultipartPart byteArrayMultipartPart =
        new ByteArrayMultipartPart(part, "AXAXAXAX".getBytes("UTF-8"));

    // Act and Assert
    assertTrue(byteArrayMultipartPart.computePreContentBytes(3).isContiguous());
  }

  /**
   * Test {@link MultipartPart#computePreContentBytes(int)}.
   *
   * <p>Method under test: {@link MultipartPart#computePreContentBytes(int)}
   */
  @Test
  @DisplayName("Test computePreContentBytes(int)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"ByteBuf MultipartPart.computePreContentBytes(int)"})
  void testComputePreContentBytes6() throws UnsupportedEncodingException {
    // Arrange
    ByteArrayPart part =
        new ByteArrayPart("https://example.org/example", "AXAXAXAX".getBytes("UTF-8"));
    part.setCustomHeaders(new ArrayList<>());
    ByteArrayMultipartPart byteArrayMultipartPart =
        new ByteArrayMultipartPart(part, "AXAXAXAX".getBytes("UTF-8"));

    // Act and Assert
    assertTrue(byteArrayMultipartPart.computePreContentBytes(3).isContiguous());
  }

  /**
   * Test {@link MultipartPart#computePreContentBytes(int)}.
   *
   * <ul>
   *   <li>Given {@code A}.
   *   <li>When three.
   *   <li>Then return Contiguous.
   * </ul>
   *
   * <p>Method under test: {@link MultipartPart#computePreContentBytes(int)}
   */
  @Test
  @DisplayName("Test computePreContentBytes(int); given 'A'; when three; then return Contiguous")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"ByteBuf MultipartPart.computePreContentBytes(int)"})
  void testComputePreContentBytes_givenA_whenThree_thenReturnContiguous()
      throws UnsupportedEncodingException {
    // Arrange
    ByteArrayPart part =
        new ByteArrayPart(
            "https://example.org/example",
            new byte[] {'A', Byte.MAX_VALUE, 'A', 'X', 'A', 'X', 'A', 'X'});
    ByteArrayMultipartPart byteArrayMultipartPart =
        new ByteArrayMultipartPart(part, "AXAXAXAX".getBytes("UTF-8"));

    // Act and Assert
    assertTrue(byteArrayMultipartPart.computePreContentBytes(3).isContiguous());
  }

  /**
   * Test {@link MultipartPart#computePostContentLength()}.
   *
   * <p>Method under test: {@link MultipartPart#computePostContentLength()}
   */
  @Test
  @DisplayName("Test computePostContentLength()")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"int MultipartPart.computePostContentLength()"})
  void testComputePostContentLength() throws UnsupportedEncodingException {
    // Arrange
    ByteArrayPart part =
        new ByteArrayPart("https://example.org/example", "AXAXAXAX".getBytes("UTF-8"));
    ByteArrayMultipartPart byteArrayMultipartPart =
        new ByteArrayMultipartPart(part, "AXAXAXAX".getBytes("UTF-8"));

    // Act and Assert
    assertEquals(2, byteArrayMultipartPart.computePostContentLength());
  }

  /**
   * Test {@link MultipartPart#computePostContentBytes(int)}.
   *
   * <ul>
   *   <li>When three.
   *   <li>Then return Contiguous.
   * </ul>
   *
   * <p>Method under test: {@link MultipartPart#computePostContentBytes(int)}
   */
  @Test
  @DisplayName("Test computePostContentBytes(int); when three; then return Contiguous")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"ByteBuf MultipartPart.computePostContentBytes(int)"})
  void testComputePostContentBytes_whenThree_thenReturnContiguous()
      throws UnsupportedEncodingException {
    // Arrange
    ByteArrayPart part =
        new ByteArrayPart("https://example.org/example", "AXAXAXAX".getBytes("UTF-8"));
    ByteArrayMultipartPart byteArrayMultipartPart =
        new ByteArrayMultipartPart(part, "AXAXAXAX".getBytes("UTF-8"));

    // Act and Assert
    assertTrue(byteArrayMultipartPart.computePostContentBytes(3).isContiguous());
  }

  /**
   * Test {@link MultipartPart#visitStart(PartVisitor)}.
   *
   * <ul>
   *   <li>When {@link CounterPartVisitor} (default constructor).
   *   <li>Then {@link CounterPartVisitor} (default constructor) Count is ten.
   * </ul>
   *
   * <p>Method under test: {@link MultipartPart#visitStart(PartVisitor)}
   */
  @Test
  @DisplayName(
      "Test visitStart(PartVisitor); when CounterPartVisitor (default constructor); then CounterPartVisitor (default constructor) Count is ten")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void MultipartPart.visitStart(PartVisitor)"})
  void testVisitStart_whenCounterPartVisitor_thenCounterPartVisitorCountIsTen()
      throws UnsupportedEncodingException {
    // Arrange
    MessageEndMultipartPart messageEndMultipartPart =
        new MessageEndMultipartPart("AXAXAXAX".getBytes("UTF-8"));
    CounterPartVisitor visitor = new CounterPartVisitor();

    // Act
    messageEndMultipartPart.visitStart(visitor);

    // Assert
    assertEquals(10, visitor.getCount());
  }

  /**
   * Test {@link MultipartPart#visitEndOfHeaders(PartVisitor)}.
   *
   * <ul>
   *   <li>When {@link CounterPartVisitor} (default constructor).
   *   <li>Then {@link CounterPartVisitor} (default constructor) Count is four.
   * </ul>
   *
   * <p>Method under test: {@link MultipartPart#visitEndOfHeaders(PartVisitor)}
   */
  @Test
  @DisplayName(
      "Test visitEndOfHeaders(PartVisitor); when CounterPartVisitor (default constructor); then CounterPartVisitor (default constructor) Count is four")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void MultipartPart.visitEndOfHeaders(PartVisitor)"})
  void testVisitEndOfHeaders_whenCounterPartVisitor_thenCounterPartVisitorCountIsFour()
      throws UnsupportedEncodingException {
    // Arrange
    MessageEndMultipartPart messageEndMultipartPart =
        new MessageEndMultipartPart("AXAXAXAX".getBytes("UTF-8"));
    CounterPartVisitor visitor = new CounterPartVisitor();

    // Act
    messageEndMultipartPart.visitEndOfHeaders(visitor);

    // Assert
    assertEquals(4, visitor.getCount());
  }

  /**
   * Test {@link MultipartPart#visitPostContent(PartVisitor)}.
   *
   * <ul>
   *   <li>When {@link CounterPartVisitor} (default constructor).
   *   <li>Then {@link CounterPartVisitor} (default constructor) Count is two.
   * </ul>
   *
   * <p>Method under test: {@link MultipartPart#visitPostContent(PartVisitor)}
   */
  @Test
  @DisplayName(
      "Test visitPostContent(PartVisitor); when CounterPartVisitor (default constructor); then CounterPartVisitor (default constructor) Count is two")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void MultipartPart.visitPostContent(PartVisitor)"})
  void testVisitPostContent_whenCounterPartVisitor_thenCounterPartVisitorCountIsTwo()
      throws UnsupportedEncodingException {
    // Arrange
    MessageEndMultipartPart messageEndMultipartPart =
        new MessageEndMultipartPart("AXAXAXAX".getBytes("UTF-8"));
    CounterPartVisitor visitor = new CounterPartVisitor();

    // Act
    messageEndMultipartPart.visitPostContent(visitor);

    // Assert
    assertEquals(2, visitor.getCount());
  }
}
