package org.asynchttpclient;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import io.netty.buffer.AdaptiveByteBufAllocator;
import io.netty.buffer.DuplicatedByteBuf;
import io.netty.buffer.EmptyByteBuf;
import org.asynchttpclient.netty.EagerResponseBodyPart;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class HttpResponseBodyPartDiffblueTest {
  /**
   * Test {@link HttpResponseBodyPart#isLast()}.
   * <ul>
   *   <li>Then return {@code false}.</li>
   * </ul>
   * <p>
   * Method under test: {@link HttpResponseBodyPart#isLast()}
   */
  @Test
  @DisplayName("Test isLast(); then return 'false'")
  void testIsLast_thenReturnFalse() {
    // Arrange, Act and Assert
    assertFalse(
        (new EagerResponseBodyPart(new DuplicatedByteBuf(new EmptyByteBuf(new AdaptiveByteBufAllocator())), false))
            .isLast());
  }

  /**
   * Test {@link HttpResponseBodyPart#isLast()}.
   * <ul>
   *   <li>Then return {@code true}.</li>
   * </ul>
   * <p>
   * Method under test: {@link HttpResponseBodyPart#isLast()}
   */
  @Test
  @DisplayName("Test isLast(); then return 'true'")
  void testIsLast_thenReturnTrue() {
    // Arrange, Act and Assert
    assertTrue(
        (new EagerResponseBodyPart(new DuplicatedByteBuf(new EmptyByteBuf(new AdaptiveByteBufAllocator())), true))
            .isLast());
  }
}
