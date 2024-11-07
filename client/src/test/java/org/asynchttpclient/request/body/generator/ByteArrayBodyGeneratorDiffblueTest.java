package org.asynchttpclient.request.body.generator;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import io.netty.buffer.AdaptiveByteBufAllocator;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.DuplicatedByteBuf;
import io.netty.buffer.EmptyByteBuf;
import io.netty.buffer.Unpooled;
import java.io.UnsupportedEncodingException;
import org.asynchttpclient.request.body.Body;
import org.asynchttpclient.request.body.generator.ByteArrayBodyGenerator.ByteBody;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ByteArrayBodyGeneratorDiffblueTest {
  /**
   * Test ByteBody {@link ByteBody#getContentLength()}.
   * <ul>
   *   <li>Then return eight.</li>
   * </ul>
   * <p>
   * Method under test: {@link ByteArrayBodyGenerator.ByteBody#getContentLength()}
   */
  @Test
  @DisplayName("Test ByteBody getContentLength(); then return eight")
  void testByteBodyGetContentLength_thenReturnEight() throws UnsupportedEncodingException {
    // Arrange, Act and Assert
    assertEquals(8L, ((new ByteArrayBodyGenerator("AXAXAXAX".getBytes("UTF-8"))).new ByteBody()).getContentLength());
  }

  /**
   * Test ByteBody {@link ByteBody#ByteBody(ByteArrayBodyGenerator)}.
   * <p>
   * Method under test:
   * {@link ByteArrayBodyGenerator.ByteBody#ByteBody(ByteArrayBodyGenerator)}
   */
  @Test
  @DisplayName("Test ByteBody new ByteBody(ByteArrayBodyGenerator)")
  void testByteBodyNewByteBody() throws UnsupportedEncodingException {
    // Arrange, Act and Assert
    assertEquals(8L, ((new ByteArrayBodyGenerator("AXAXAXAX".getBytes("UTF-8"))).new ByteBody()).getContentLength());
  }

  /**
   * Test ByteBody {@link ByteBody#transferTo(ByteBuf)}.
   * <ul>
   *   <li>Given {@link ByteArrayBodyGenerator#ByteArrayBodyGenerator(byte[])} with
   * bytes is empty array of {@code byte}.</li>
   * </ul>
   * <p>
   * Method under test:
   * {@link ByteArrayBodyGenerator.ByteBody#transferTo(ByteBuf)}
   */
  @Test
  @DisplayName("Test ByteBody transferTo(ByteBuf); given ByteArrayBodyGenerator(byte[]) with bytes is empty array of byte")
  void testByteBodyTransferTo_givenByteArrayBodyGeneratorWithBytesIsEmptyArrayOfByte() {
    // Arrange
    ByteArrayBodyGenerator.ByteBody byteBody = (new ByteArrayBodyGenerator(new byte[]{})).new ByteBody();

    // Act and Assert
    assertEquals(Body.BodyState.CONTINUE,
        byteBody.transferTo(new DuplicatedByteBuf(new EmptyByteBuf(new AdaptiveByteBufAllocator()))));
  }

  /**
   * Test ByteBody {@link ByteBody#transferTo(ByteBuf)}.
   * <ul>
   *   <li>Then return {@code CONTINUE}.</li>
   * </ul>
   * <p>
   * Method under test:
   * {@link ByteArrayBodyGenerator.ByteBody#transferTo(ByteBuf)}
   */
  @Test
  @DisplayName("Test ByteBody transferTo(ByteBuf); then return 'CONTINUE'")
  void testByteBodyTransferTo_thenReturnContinue() throws UnsupportedEncodingException {
    // Arrange
    ByteArrayBodyGenerator.ByteBody byteBody = (new ByteArrayBodyGenerator(
        "AXAXAXAX".getBytes("UTF-8"))).new ByteBody();

    // Act and Assert
    assertEquals(Body.BodyState.CONTINUE,
        byteBody.transferTo(new DuplicatedByteBuf(new EmptyByteBuf(new AdaptiveByteBufAllocator()))));
  }

  /**
   * Test ByteBody {@link ByteBody#transferTo(ByteBuf)}.
   * <ul>
   *   <li>When {@link DuplicatedByteBuf#DuplicatedByteBuf(ByteBuf)} with buffer is
   * compositeBuffer three.</li>
   * </ul>
   * <p>
   * Method under test:
   * {@link ByteArrayBodyGenerator.ByteBody#transferTo(ByteBuf)}
   */
  @Test
  @DisplayName("Test ByteBody transferTo(ByteBuf); when DuplicatedByteBuf(ByteBuf) with buffer is compositeBuffer three")
  void testByteBodyTransferTo_whenDuplicatedByteBufWithBufferIsCompositeBufferThree()
      throws UnsupportedEncodingException {
    // Arrange
    ByteArrayBodyGenerator.ByteBody byteBody = (new ByteArrayBodyGenerator(
        "AXAXAXAX".getBytes("UTF-8"))).new ByteBody();

    // Act and Assert
    assertEquals(Body.BodyState.CONTINUE, byteBody.transferTo(new DuplicatedByteBuf(Unpooled.compositeBuffer(3))));
  }

  /**
   * Test ByteBody {@link ByteBody#transferTo(ByteBuf)}.
   * <ul>
   *   <li>When {@link DuplicatedByteBuf#DuplicatedByteBuf(ByteBuf)} with buffer is
   * compositeBuffer three.</li>
   * </ul>
   * <p>
   * Method under test:
   * {@link ByteArrayBodyGenerator.ByteBody#transferTo(ByteBuf)}
   */
  @Test
  @DisplayName("Test ByteBody transferTo(ByteBuf); when DuplicatedByteBuf(ByteBuf) with buffer is compositeBuffer three")
  void testByteBodyTransferTo_whenDuplicatedByteBufWithBufferIsCompositeBufferThree2() {
    // Arrange
    ByteArrayBodyGenerator.ByteBody byteBody = (new ByteArrayBodyGenerator(new byte[]{})).new ByteBody();

    // Act and Assert
    assertEquals(Body.BodyState.CONTINUE, byteBody.transferTo(new DuplicatedByteBuf(Unpooled.compositeBuffer(3))));
  }

  /**
   * Test {@link ByteArrayBodyGenerator#createBody()}.
   * <p>
   * Method under test: {@link ByteArrayBodyGenerator#createBody()}
   */
  @Test
  @DisplayName("Test createBody()")
  void testCreateBody() throws UnsupportedEncodingException {
    // Arrange and Act
    Body actualCreateBodyResult = (new ByteArrayBodyGenerator("AXAXAXAX".getBytes("UTF-8"))).createBody();

    // Assert
    assertTrue(actualCreateBodyResult instanceof ByteArrayBodyGenerator.ByteBody);
    assertEquals(8L, actualCreateBodyResult.getContentLength());
  }
}
