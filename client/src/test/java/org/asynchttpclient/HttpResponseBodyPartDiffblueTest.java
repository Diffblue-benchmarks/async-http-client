package org.asynchttpclient;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import io.netty.buffer.AdaptiveByteBufAllocator;
import io.netty.buffer.DuplicatedByteBuf;
import io.netty.buffer.EmptyByteBuf;
import org.asynchttpclient.netty.EagerResponseBodyPart;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

class HttpResponseBodyPartDiffblueTest {
  /**
   * Test {@link HttpResponseBodyPart#isLast()}.
   *
   * <ul>
   *   <li>Then return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link HttpResponseBodyPart#isLast()}
   */
  @Test
  @DisplayName("Test isLast(); then return 'false'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean HttpResponseBodyPart.isLast()"})
  void testIsLast_thenReturnFalse() {
    // Arrange
    DuplicatedByteBuf buf = new DuplicatedByteBuf(new EmptyByteBuf(new AdaptiveByteBufAllocator()));

    // Act and Assert
    assertFalse(new EagerResponseBodyPart(buf, false).isLast());
  }

  /**
   * Test {@link HttpResponseBodyPart#isLast()}.
   *
   * <ul>
   *   <li>Then return {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link HttpResponseBodyPart#isLast()}
   */
  @Test
  @DisplayName("Test isLast(); then return 'true'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean HttpResponseBodyPart.isLast()"})
  void testIsLast_thenReturnTrue() {
    // Arrange
    DuplicatedByteBuf buf = new DuplicatedByteBuf(new EmptyByteBuf(new AdaptiveByteBufAllocator()));

    // Act and Assert
    assertTrue(new EagerResponseBodyPart(buf, true).isLast());
  }
}
