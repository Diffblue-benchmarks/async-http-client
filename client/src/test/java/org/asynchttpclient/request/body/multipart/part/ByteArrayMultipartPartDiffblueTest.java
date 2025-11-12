package org.asynchttpclient.request.body.multipart.part;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import io.netty.buffer.AdaptiveByteBufAllocator;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.CompositeByteBuf;
import io.netty.buffer.DuplicatedByteBuf;
import io.netty.buffer.EmptyByteBuf;
import io.netty.buffer.Unpooled;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.channels.WritableByteChannel;
import java.nio.charset.Charset;
import java.util.List;
import org.apache.tomcat.util.net.NioChannel;
import org.apache.tomcat.util.net.SocketBufferHandler;
import org.asynchttpclient.Param;
import org.asynchttpclient.request.body.multipart.ByteArrayPart;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

class ByteArrayMultipartPartDiffblueTest {
  /**
   * Test {@link ByteArrayMultipartPart#ByteArrayMultipartPart(ByteArrayPart, byte[])}.
   *
   * <p>Method under test: {@link ByteArrayMultipartPart#ByteArrayMultipartPart(ByteArrayPart,
   * byte[])}
   */
  @Test
  @DisplayName("Test new ByteArrayMultipartPart(ByteArrayPart, byte[])")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void ByteArrayMultipartPart.<init>(ByteArrayPart, byte[])"})
  void testNewByteArrayMultipartPart() throws UnsupportedEncodingException {
    // Arrange
    ByteArrayPart part =
        new ByteArrayPart("https://example.org/example", "AXAXAXAX".getBytes("UTF-8"));
    part.setDispositionType("https://example.org/example");
    part.addCustomHeader("https://example.org/example", "https://example.org/example");

    // Act
    ByteArrayMultipartPart actualByteArrayMultipartPart =
        new ByteArrayMultipartPart(part, "AXAXAXAX".getBytes("UTF-8"));

    // Assert
    ByteArrayPart byteArrayPart = actualByteArrayMultipartPart.part;
    assertEquals("https://example.org/example", byteArrayPart.getDispositionType());
    assertEquals(208L, actualByteArrayMultipartPart.length());
    assertArrayEquals("AXAXAXAX".getBytes("UTF-8"), byteArrayPart.getBytes());
    assertArrayEquals("AXAXAXAX".getBytes("UTF-8"), actualByteArrayMultipartPart.boundary);
  }

  /**
   * Test {@link ByteArrayMultipartPart#ByteArrayMultipartPart(ByteArrayPart, byte[])}.
   *
   * <ul>
   *   <li>Then return ContentLength is zero.
   * </ul>
   *
   * <p>Method under test: {@link ByteArrayMultipartPart#ByteArrayMultipartPart(ByteArrayPart,
   * byte[])}
   */
  @Test
  @DisplayName(
      "Test new ByteArrayMultipartPart(ByteArrayPart, byte[]); then return ContentLength is zero")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void ByteArrayMultipartPart.<init>(ByteArrayPart, byte[])"})
  void testNewByteArrayMultipartPart_thenReturnContentLengthIsZero()
      throws UnsupportedEncodingException {
    // Arrange
    ByteArrayPart part = new ByteArrayPart("https://example.org/example", new byte[] {});

    // Act
    ByteArrayMultipartPart actualByteArrayMultipartPart =
        new ByteArrayMultipartPart(part, "AXAXAXAX".getBytes("UTF-8"));

    // Assert
    assertEquals(0L, actualByteArrayMultipartPart.getContentLength());
    assertEquals(124L, actualByteArrayMultipartPart.length());
    assertArrayEquals("AXAXAXAX".getBytes("UTF-8"), actualByteArrayMultipartPart.boundary);
    assertArrayEquals(new byte[] {}, actualByteArrayMultipartPart.part.getBytes());
  }

  /**
   * Test {@link ByteArrayMultipartPart#ByteArrayMultipartPart(ByteArrayPart, byte[])}.
   *
   * <ul>
   *   <li>Then return {@link MultipartPart#part} Charset name is {@code UTF-8}.
   * </ul>
   *
   * <p>Method under test: {@link ByteArrayMultipartPart#ByteArrayMultipartPart(ByteArrayPart,
   * byte[])}
   */
  @Test
  @DisplayName(
      "Test new ByteArrayMultipartPart(ByteArrayPart, byte[]); then return part Charset name is 'UTF-8'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void ByteArrayMultipartPart.<init>(ByteArrayPart, byte[])"})
  void testNewByteArrayMultipartPart_thenReturnPartCharsetNameIsUtf8()
      throws UnsupportedEncodingException {
    // Arrange
    byte[] bytes = "A\"A\"A\"A\"".getBytes("UTF-8");
    Charset charset = Charset.forName("UTF-8");

    ByteArrayPart part =
        new ByteArrayPart(
            "https://example.org/example", bytes, "https://example.org/example", charset);

    // Act
    ByteArrayMultipartPart actualByteArrayMultipartPart =
        new ByteArrayMultipartPart(part, "AXAXAXAX".getBytes("UTF-8"));

    // Assert
    ByteArrayPart byteArrayPart = actualByteArrayMultipartPart.part;
    Charset charset2 = byteArrayPart.getCharset();
    assertEquals("UTF-8", charset2.name());
    assertEquals("https://example.org/example", byteArrayPart.getContentType());
    assertEquals(150L, actualByteArrayMultipartPart.length());
    assertSame(charset, charset2);
    assertArrayEquals("AXAXAXAX".getBytes("UTF-8"), actualByteArrayMultipartPart.boundary);
    assertArrayEquals("A\"A\"A\"A\"".getBytes("UTF-8"), byteArrayPart.getBytes());
  }

  /**
   * Test {@link ByteArrayMultipartPart#ByteArrayMultipartPart(ByteArrayPart, byte[])}.
   *
   * <ul>
   *   <li>Then return {@link MultipartPart#part} CustomHeaders is {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link ByteArrayMultipartPart#ByteArrayMultipartPart(ByteArrayPart,
   * byte[])}
   */
  @Test
  @DisplayName(
      "Test new ByteArrayMultipartPart(ByteArrayPart, byte[]); then return part CustomHeaders is 'null'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void ByteArrayMultipartPart.<init>(ByteArrayPart, byte[])"})
  void testNewByteArrayMultipartPart_thenReturnPartCustomHeadersIsNull()
      throws UnsupportedEncodingException {
    // Arrange
    ByteArrayPart part =
        new ByteArrayPart("https://example.org/example", "AXAXAXAX".getBytes("UTF-8"));

    // Act
    ByteArrayMultipartPart actualByteArrayMultipartPart =
        new ByteArrayMultipartPart(part, "AXAXAXAX".getBytes("UTF-8"));

    // Assert
    ByteArrayPart byteArrayPart = actualByteArrayMultipartPart.part;
    assertNull(byteArrayPart.getCustomHeaders());
    assertEquals(132L, actualByteArrayMultipartPart.length());
    assertArrayEquals("AXAXAXAX".getBytes("UTF-8"), byteArrayPart.getBytes());
    assertArrayEquals("AXAXAXAX".getBytes("UTF-8"), actualByteArrayMultipartPart.boundary);
  }

  /**
   * Test {@link ByteArrayMultipartPart#ByteArrayMultipartPart(ByteArrayPart, byte[])}.
   *
   * <ul>
   *   <li>Then return {@link MultipartPart#part} CustomHeaders size is one.
   * </ul>
   *
   * <p>Method under test: {@link ByteArrayMultipartPart#ByteArrayMultipartPart(ByteArrayPart,
   * byte[])}
   */
  @Test
  @DisplayName(
      "Test new ByteArrayMultipartPart(ByteArrayPart, byte[]); then return part CustomHeaders size is one")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void ByteArrayMultipartPart.<init>(ByteArrayPart, byte[])"})
  void testNewByteArrayMultipartPart_thenReturnPartCustomHeadersSizeIsOne()
      throws UnsupportedEncodingException {
    // Arrange
    ByteArrayPart part =
        new ByteArrayPart("https://example.org/example", "AXAXAXAX".getBytes("UTF-8"));
    part.addCustomHeader("https://example.org/example", "https://example.org/example");

    // Act
    ByteArrayMultipartPart actualByteArrayMultipartPart =
        new ByteArrayMultipartPart(part, "AXAXAXAX".getBytes("UTF-8"));

    // Assert
    ByteArrayPart byteArrayPart = actualByteArrayMultipartPart.part;
    List<Param> customHeaders = byteArrayPart.getCustomHeaders();
    assertEquals(1, customHeaders.size());
    Param getResult = customHeaders.get(0);
    assertEquals("https://example.org/example", getResult.getName());
    assertEquals("https://example.org/example", getResult.getValue());
    assertEquals(190L, actualByteArrayMultipartPart.length());
    assertArrayEquals("AXAXAXAX".getBytes("UTF-8"), byteArrayPart.getBytes());
    assertArrayEquals("AXAXAXAX".getBytes("UTF-8"), actualByteArrayMultipartPart.boundary);
  }

  /**
   * Test {@link ByteArrayMultipartPart#getContentLength()}.
   *
   * <p>Method under test: {@link ByteArrayMultipartPart#getContentLength()}
   */
  @Test
  @DisplayName("Test getContentLength()")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"long ByteArrayMultipartPart.getContentLength()"})
  void testGetContentLength() throws UnsupportedEncodingException {
    // Arrange
    ByteArrayPart part =
        new ByteArrayPart("https://example.org/example", "AXAXAXAX".getBytes("UTF-8"));
    ByteArrayMultipartPart byteArrayMultipartPart =
        new ByteArrayMultipartPart(part, "AXAXAXAX".getBytes("UTF-8"));

    // Act and Assert
    assertEquals(8L, byteArrayMultipartPart.getContentLength());
  }

  /**
   * Test {@link ByteArrayMultipartPart#transferContentTo(ByteBuf)} with {@code ByteBuf}.
   *
   * <p>Method under test: {@link ByteArrayMultipartPart#transferContentTo(ByteBuf)}
   */
  @Test
  @DisplayName("Test transferContentTo(ByteBuf) with 'ByteBuf'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"long ByteArrayMultipartPart.transferContentTo(ByteBuf)"})
  void testTransferContentToWithByteBuf() throws UnsupportedEncodingException {
    // Arrange
    ByteArrayPart part =
        new ByteArrayPart("https://example.org/example", "AXAXAXAX".getBytes("UTF-8"));
    ByteArrayMultipartPart byteArrayMultipartPart =
        new ByteArrayMultipartPart(part, "AXAXAXAX".getBytes("UTF-8"));
    DuplicatedByteBuf target =
        new DuplicatedByteBuf(new EmptyByteBuf(new AdaptiveByteBufAllocator()));

    // Act
    long actualTransferContentToResult = byteArrayMultipartPart.transferContentTo(target);

    // Assert
    assertEquals(0, target.readerIndex());
    assertEquals(0, target.writerIndex());
    assertEquals(0L, actualTransferContentToResult);
    assertEquals(MultipartState.PRE_CONTENT, byteArrayMultipartPart.getState());
  }

  /**
   * Test {@link ByteArrayMultipartPart#transferContentTo(ByteBuf)} with {@code ByteBuf}.
   *
   * <p>Method under test: {@link ByteArrayMultipartPart#transferContentTo(ByteBuf)}
   */
  @Test
  @DisplayName("Test transferContentTo(ByteBuf) with 'ByteBuf'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"long ByteArrayMultipartPart.transferContentTo(ByteBuf)"})
  void testTransferContentToWithByteBuf2() throws UnsupportedEncodingException {
    // Arrange
    ByteArrayPart part = new ByteArrayPart("https://example.org/example", new byte[] {});
    ByteArrayMultipartPart byteArrayMultipartPart =
        new ByteArrayMultipartPart(part, "AXAXAXAX".getBytes("UTF-8"));
    DuplicatedByteBuf target =
        new DuplicatedByteBuf(new EmptyByteBuf(new AdaptiveByteBufAllocator()));

    // Act
    long actualTransferContentToResult = byteArrayMultipartPart.transferContentTo(target);

    // Assert
    assertEquals(0, target.readerIndex());
    assertEquals(0, target.writerIndex());
    assertEquals(0L, actualTransferContentToResult);
    assertEquals(MultipartState.POST_CONTENT, byteArrayMultipartPart.getState());
  }

  /**
   * Test {@link ByteArrayMultipartPart#transferContentTo(ByteBuf)} with {@code ByteBuf}.
   *
   * <ul>
   *   <li>Then compositeBuffer three readerIndex is zero.
   * </ul>
   *
   * <p>Method under test: {@link ByteArrayMultipartPart#transferContentTo(ByteBuf)}
   */
  @Test
  @DisplayName(
      "Test transferContentTo(ByteBuf) with 'ByteBuf'; then compositeBuffer three readerIndex is zero")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"long ByteArrayMultipartPart.transferContentTo(ByteBuf)"})
  void testTransferContentToWithByteBuf_thenCompositeBufferThreeReaderIndexIsZero()
      throws UnsupportedEncodingException {
    // Arrange
    ByteArrayPart part =
        new ByteArrayPart("https://example.org/example", "AXAXAXAX".getBytes("UTF-8"));
    ByteArrayMultipartPart byteArrayMultipartPart =
        new ByteArrayMultipartPart(part, "AXAXAXAX".getBytes("UTF-8"));

    CompositeByteBuf target = Unpooled.compositeBuffer(3);
    target.addComponent(new DuplicatedByteBuf(new EmptyByteBuf(new AdaptiveByteBufAllocator())));

    // Act
    long actualTransferContentToResult = byteArrayMultipartPart.transferContentTo(target);

    // Assert
    assertEquals(0, target.readerIndex());
    assertEquals(0, target.writerIndex());
    assertEquals(0L, actualTransferContentToResult);
    assertEquals(MultipartState.PRE_CONTENT, byteArrayMultipartPart.getState());
  }

  /**
   * Test {@link ByteArrayMultipartPart#transferContentTo(ByteBuf)} with {@code ByteBuf}.
   *
   * <ul>
   *   <li>When compositeBuffer three.
   * </ul>
   *
   * <p>Method under test: {@link ByteArrayMultipartPart#transferContentTo(ByteBuf)}
   */
  @Test
  @DisplayName("Test transferContentTo(ByteBuf) with 'ByteBuf'; when compositeBuffer three")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"long ByteArrayMultipartPart.transferContentTo(ByteBuf)"})
  void testTransferContentToWithByteBuf_whenCompositeBufferThree()
      throws UnsupportedEncodingException {
    // Arrange
    ByteArrayPart part =
        new ByteArrayPart("https://example.org/example", "AXAXAXAX".getBytes("UTF-8"));
    ByteArrayMultipartPart byteArrayMultipartPart =
        new ByteArrayMultipartPart(part, "AXAXAXAX".getBytes("UTF-8"));
    CompositeByteBuf target = Unpooled.compositeBuffer(3);

    // Act
    long actualTransferContentToResult = byteArrayMultipartPart.transferContentTo(target);

    // Assert
    assertEquals(0, target.readerIndex());
    assertEquals(0, target.writerIndex());
    assertEquals(0L, actualTransferContentToResult);
    assertEquals(MultipartState.PRE_CONTENT, byteArrayMultipartPart.getState());
  }

  /**
   * Test {@link ByteArrayMultipartPart#transferContentTo(WritableByteChannel)} with {@code
   * WritableByteChannel}.
   *
   * <ul>
   *   <li>Then return zero.
   * </ul>
   *
   * <p>Method under test: {@link ByteArrayMultipartPart#transferContentTo(WritableByteChannel)}
   */
  @Test
  @DisplayName(
      "Test transferContentTo(WritableByteChannel) with 'WritableByteChannel'; then return zero")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"long ByteArrayMultipartPart.transferContentTo(WritableByteChannel)"})
  void testTransferContentToWithWritableByteChannel_thenReturnZero() throws IOException {
    // Arrange
    ByteArrayPart part = new ByteArrayPart("https://example.org/example", new byte[] {});
    ByteArrayMultipartPart byteArrayMultipartPart =
        new ByteArrayMultipartPart(part, "AXAXAXAX".getBytes("UTF-8"));

    // Act
    long actualTransferContentToResult =
        byteArrayMultipartPart.transferContentTo(
            new NioChannel(new SocketBufferHandler(3, 3, true)));

    // Assert
    assertEquals(0L, actualTransferContentToResult);
    assertEquals(MultipartState.POST_CONTENT, byteArrayMultipartPart.getState());
  }
}
