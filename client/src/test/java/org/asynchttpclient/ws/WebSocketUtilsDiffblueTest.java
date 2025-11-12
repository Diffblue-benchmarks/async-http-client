package org.asynchttpclient.ws;

import static org.junit.jupiter.api.Assertions.assertEquals;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

class WebSocketUtilsDiffblueTest {
  /**
   * Test {@link WebSocketUtils#getAcceptKey(String)}.
   *
   * <ul>
   *   <li>When {@code https://example.org/example}.
   *   <li>Then return {@code jjBb/h4G0xabWNaKzkyRU/jf9o4=}.
   * </ul>
   *
   * <p>Method under test: {@link WebSocketUtils#getAcceptKey(String)}
   */
  @Test
  @DisplayName(
      "Test getAcceptKey(String); when 'https://example.org/example'; then return 'jjBb/h4G0xabWNaKzkyRU/jf9o4='")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"String WebSocketUtils.getAcceptKey(String)"})
  void testGetAcceptKey_whenHttpsExampleOrgExample_thenReturnJjBbH4G0xabWNaKzkyRUJf9o4() {
    // Arrange, Act and Assert
    assertEquals(
        "jjBb/h4G0xabWNaKzkyRU/jf9o4=", WebSocketUtils.getAcceptKey("https://example.org/example"));
  }

  /**
   * Test {@link WebSocketUtils#getAcceptKey(String)}.
   *
   * <ul>
   *   <li>When {@code SHA1}.
   *   <li>Then return {@code yUZ1/BjeMiHjE7ZkxrvU1DL2oQ4=}.
   * </ul>
   *
   * <p>Method under test: {@link WebSocketUtils#getAcceptKey(String)}
   */
  @Test
  @DisplayName("Test getAcceptKey(String); when 'SHA1'; then return 'yUZ1/BjeMiHjE7ZkxrvU1DL2oQ4='")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"String WebSocketUtils.getAcceptKey(String)"})
  void testGetAcceptKey_whenSha1_thenReturnYUZ1BjeMiHjE7ZkxrvU1DL2oQ4() {
    // Arrange, Act and Assert
    assertEquals("yUZ1/BjeMiHjE7ZkxrvU1DL2oQ4=", WebSocketUtils.getAcceptKey("SHA1"));
  }
}
