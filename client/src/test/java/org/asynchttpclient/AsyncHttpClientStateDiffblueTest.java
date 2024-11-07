package org.asynchttpclient;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import java.util.concurrent.atomic.AtomicBoolean;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class AsyncHttpClientStateDiffblueTest {
  /**
   * Test {@link AsyncHttpClientState#AsyncHttpClientState(AtomicBoolean)}.
   * <p>
   * Method under test:
   * {@link AsyncHttpClientState#AsyncHttpClientState(AtomicBoolean)}
   */
  @Test
  @DisplayName("Test new AsyncHttpClientState(AtomicBoolean)")
  void testNewAsyncHttpClientState() {
    // Arrange, Act and Assert
    assertTrue((new AsyncHttpClientState(new AtomicBoolean(true))).isClosed());
  }

  /**
   * Test {@link AsyncHttpClientState#isClosed()}.
   * <ul>
   *   <li>Given {@link AtomicBoolean#AtomicBoolean(boolean)} with
   * {@code false}.</li>
   *   <li>Then return {@code false}.</li>
   * </ul>
   * <p>
   * Method under test: {@link AsyncHttpClientState#isClosed()}
   */
  @Test
  @DisplayName("Test isClosed(); given AtomicBoolean(boolean) with 'false'; then return 'false'")
  void testIsClosed_givenAtomicBooleanWithFalse_thenReturnFalse() {
    // Arrange, Act and Assert
    assertFalse((new AsyncHttpClientState(new AtomicBoolean(false))).isClosed());
  }

  /**
   * Test {@link AsyncHttpClientState#isClosed()}.
   * <ul>
   *   <li>Given {@link AtomicBoolean#AtomicBoolean(boolean)} with
   * {@code true}.</li>
   *   <li>Then return {@code true}.</li>
   * </ul>
   * <p>
   * Method under test: {@link AsyncHttpClientState#isClosed()}
   */
  @Test
  @DisplayName("Test isClosed(); given AtomicBoolean(boolean) with 'true'; then return 'true'")
  void testIsClosed_givenAtomicBooleanWithTrue_thenReturnTrue() {
    // Arrange, Act and Assert
    assertTrue((new AsyncHttpClientState(new AtomicBoolean(true))).isClosed());
  }
}
