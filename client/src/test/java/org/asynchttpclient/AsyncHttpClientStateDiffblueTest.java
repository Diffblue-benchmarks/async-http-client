package org.asynchttpclient;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import java.util.concurrent.atomic.AtomicBoolean;
import org.junit.jupiter.api.Test;

class AsyncHttpClientStateDiffblueTest {
  /**
   * Method under test: {@link AsyncHttpClientState#isClosed()}
   */
  @Test
  void testIsClosed() {
    // Arrange, Act and Assert
    assertTrue((new AsyncHttpClientState(new AtomicBoolean(true))).isClosed());
    assertFalse((new AsyncHttpClientState(new AtomicBoolean(false))).isClosed());
  }

  /**
   * Method under test:
   * {@link AsyncHttpClientState#AsyncHttpClientState(AtomicBoolean)}
   */
  @Test
  void testNewAsyncHttpClientState() {
    // Arrange, Act and Assert
    assertTrue((new AsyncHttpClientState(new AtomicBoolean(true))).isClosed());
  }
}
