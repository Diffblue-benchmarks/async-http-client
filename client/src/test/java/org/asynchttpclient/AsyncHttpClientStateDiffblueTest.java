package org.asynchttpclient;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.util.concurrent.atomic.AtomicBoolean;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

class AsyncHttpClientStateDiffblueTest {
  /**
   * Test {@link AsyncHttpClientState#AsyncHttpClientState(AtomicBoolean)}.
   *
   * <p>Method under test: {@link AsyncHttpClientState#AsyncHttpClientState(AtomicBoolean)}
   */
  @Test
  @DisplayName("Test new AsyncHttpClientState(AtomicBoolean)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void AsyncHttpClientState.<init>(AtomicBoolean)"})
  void testNewAsyncHttpClientState() {
    // Arrange, Act and Assert
    assertFalse(new AsyncHttpClientState(new AtomicBoolean()).isClosed());
  }

  /**
   * Test {@link AsyncHttpClientState#isClosed()}.
   *
   * <ul>
   *   <li>Given {@link AsyncHttpClientState#AsyncHttpClientState(AtomicBoolean)} with closed is
   *       {@link AtomicBoolean#AtomicBoolean()}.
   *   <li>Then return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link AsyncHttpClientState#isClosed()}
   */
  @Test
  @DisplayName(
      "Test isClosed(); given AsyncHttpClientState(AtomicBoolean) with closed is AtomicBoolean(); then return 'false'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean AsyncHttpClientState.isClosed()"})
  void testIsClosed_givenAsyncHttpClientStateWithClosedIsAtomicBoolean_thenReturnFalse() {
    // Arrange, Act and Assert
    assertFalse(new AsyncHttpClientState(new AtomicBoolean()).isClosed());
  }

  /**
   * Test {@link AsyncHttpClientState#isClosed()}.
   *
   * <ul>
   *   <li>Given {@link AtomicBoolean#AtomicBoolean(boolean)} with {@code true}.
   *   <li>Then return {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link AsyncHttpClientState#isClosed()}
   */
  @Test
  @DisplayName("Test isClosed(); given AtomicBoolean(boolean) with 'true'; then return 'true'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean AsyncHttpClientState.isClosed()"})
  void testIsClosed_givenAtomicBooleanWithTrue_thenReturnTrue() {
    // Arrange, Act and Assert
    assertTrue(new AsyncHttpClientState(new AtomicBoolean(true)).isClosed());
  }
}
