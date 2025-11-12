package org.asynchttpclient.request.body.generator;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import io.netty.buffer.AdaptiveByteBufAllocator;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.DuplicatedByteBuf;
import io.netty.buffer.EmptyByteBuf;
import io.netty.buffer.Unpooled;
import java.io.UnsupportedEncodingException;
import org.asynchttpclient.request.body.Body;
import org.asynchttpclient.request.body.Body.BodyState;
import org.asynchttpclient.request.body.generator.ByteArrayBodyGenerator.ByteBody;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

class ByteArrayBodyGeneratorDiffblueTest {
  /**
   * Test ByteBody {@link ByteBody#getContentLength()}.
   *
   * <ul>
   *   <li>Then return eight.
   * </ul>
   *
   * <p>Method under test: {@link ByteBody#getContentLength()}
   */
  @Test
  @DisplayName("Test ByteBody getContentLength(); then return eight")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"long ByteBody.getContentLength()"})
  void testByteBodyGetContentLength_thenReturnEight() throws UnsupportedEncodingException {
    // Arrange, Act and Assert
    assertEquals(
        8L,
        new ByteArrayBodyGenerator("AXAXAXAX".getBytes("UTF-8")).new ByteBody().getContentLength());
  }

  /**
   * Test ByteBody {@link ByteBody#ByteBody(ByteArrayBodyGenerator)}.
   *
   * <p>Method under test: {@link ByteBody#ByteBody(ByteArrayBodyGenerator)}
   */
  @Test
  @DisplayName("Test ByteBody new ByteBody(ByteArrayBodyGenerator)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void ByteBody.<init>(ByteArrayBodyGenerator)"})
  void testByteBodyNewByteBody() throws UnsupportedEncodingException {
    // Arrange, Act and Assert
    assertEquals(
        8L,
        new ByteArrayBodyGenerator("AXAXAXAX".getBytes("UTF-8")).new ByteBody().getContentLength());
  }

  /**
   * Test ByteBody {@link ByteBody#transferTo(ByteBuf)}.
   *
   * <ul>
   *   <li>Given {@link ByteArrayBodyGenerator#ByteArrayBodyGenerator(byte[])} with bytes is empty
   *       array of {@code byte}.
   * </ul>
   *
   * <p>Method under test: {@link ByteBody#transferTo(ByteBuf)}
   */
  @Test
  @DisplayName(
      "Test ByteBody transferTo(ByteBuf); given ByteArrayBodyGenerator(byte[]) with bytes is empty array of byte")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"BodyState ByteBody.transferTo(ByteBuf)"})
  void testByteBodyTransferTo_givenByteArrayBodyGeneratorWithBytesIsEmptyArrayOfByte() {
    // Arrange
    ByteArrayBodyGenerator byteArrayBodyGenerator = new ByteArrayBodyGenerator(new byte[] {});
    ByteBody byteBody = byteArrayBodyGenerator.new ByteBody();

    // Act
    BodyState actualTransferToResult =
        byteBody.transferTo(
            new DuplicatedByteBuf(new EmptyByteBuf(new AdaptiveByteBufAllocator())));

    // Assert
    assertEquals(BodyState.CONTINUE, actualTransferToResult);
  }

  /**
   * Test ByteBody {@link ByteBody#transferTo(ByteBuf)}.
   *
   * <ul>
   *   <li>Then return {@code CONTINUE}.
   * </ul>
   *
   * <p>Method under test: {@link ByteBody#transferTo(ByteBuf)}
   */
  @Test
  @DisplayName("Test ByteBody transferTo(ByteBuf); then return 'CONTINUE'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"BodyState ByteBody.transferTo(ByteBuf)"})
  void testByteBodyTransferTo_thenReturnContinue() throws UnsupportedEncodingException {
    // Arrange
    ByteBody byteBody = new ByteArrayBodyGenerator("AXAXAXAX".getBytes("UTF-8")).new ByteBody();

    // Act
    BodyState actualTransferToResult =
        byteBody.transferTo(
            new DuplicatedByteBuf(new EmptyByteBuf(new AdaptiveByteBufAllocator())));

    // Assert
    assertEquals(BodyState.CONTINUE, actualTransferToResult);
  }

  /**
   * Test ByteBody {@link ByteBody#transferTo(ByteBuf)}.
   *
   * <ul>
   *   <li>When compositeBuffer three.
   * </ul>
   *
   * <p>Method under test: {@link ByteBody#transferTo(ByteBuf)}
   */
  @Test
  @DisplayName("Test ByteBody transferTo(ByteBuf); when compositeBuffer three")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"BodyState ByteBody.transferTo(ByteBuf)"})
  void testByteBodyTransferTo_whenCompositeBufferThree() {
    // Arrange
    ByteArrayBodyGenerator byteArrayBodyGenerator = new ByteArrayBodyGenerator(new byte[] {});
    ByteBody byteBody = byteArrayBodyGenerator.new ByteBody();

    // Act and Assert
    assertEquals(BodyState.CONTINUE, byteBody.transferTo(Unpooled.compositeBuffer(3)));
  }

  /**
   * Test ByteBody {@link ByteBody#transferTo(ByteBuf)}.
   *
   * <ul>
   *   <li>When compositeBuffer three.
   *   <li>Then return {@code CONTINUE}.
   * </ul>
   *
   * <p>Method under test: {@link ByteBody#transferTo(ByteBuf)}
   */
  @Test
  @DisplayName(
      "Test ByteBody transferTo(ByteBuf); when compositeBuffer three; then return 'CONTINUE'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"BodyState ByteBody.transferTo(ByteBuf)"})
  void testByteBodyTransferTo_whenCompositeBufferThree_thenReturnContinue()
      throws UnsupportedEncodingException {
    // Arrange
    ByteBody byteBody = new ByteArrayBodyGenerator("AXAXAXAX".getBytes("UTF-8")).new ByteBody();

    // Act and Assert
    assertEquals(BodyState.CONTINUE, byteBody.transferTo(Unpooled.compositeBuffer(3)));
  }

  /**
   * Test {@link ByteArrayBodyGenerator#createBody()}.
   *
   * <p>Method under test: {@link ByteArrayBodyGenerator#createBody()}
   */
  @Test
  @DisplayName("Test createBody()")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"Body ByteArrayBodyGenerator.createBody()"})
  void testCreateBody() throws UnsupportedEncodingException {
    // Arrange and Act
    Body actualCreateBodyResult =
        new ByteArrayBodyGenerator("AXAXAXAX".getBytes("UTF-8")).createBody();

    // Assert
    assertTrue(actualCreateBodyResult instanceof ByteBody);
    assertEquals(8L, actualCreateBodyResult.getContentLength());
  }
}
