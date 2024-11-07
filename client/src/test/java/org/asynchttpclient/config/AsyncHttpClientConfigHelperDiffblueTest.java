package org.asynchttpclient.config;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import org.asynchttpclient.config.AsyncHttpClientConfigHelper.Config;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class AsyncHttpClientConfigHelperDiffblueTest {
  /**
   * Test Config {@link Config#getBoolean(String)}.
   * <ul>
   *   <li>When {@code https://example.org/example}.</li>
   *   <li>Then return {@code false}.</li>
   * </ul>
   * <p>
   * Method under test:
   * {@link AsyncHttpClientConfigHelper.Config#getBoolean(String)}
   */
  @Test
  @DisplayName("Test Config getBoolean(String); when 'https://example.org/example'; then return 'false'")
  void testConfigGetBoolean_whenHttpsExampleOrgExample_thenReturnFalse() {
    // Arrange, Act and Assert
    assertFalse(AsyncHttpClientConfigHelper.getAsyncHttpClientConfig().getBoolean("https://example.org/example"));
  }

  /**
   * Test Config {@link Config#getString(String)}.
   * <ul>
   *   <li>When {@code https://example.org/example}.</li>
   *   <li>Then return {@code null}.</li>
   * </ul>
   * <p>
   * Method under test:
   * {@link AsyncHttpClientConfigHelper.Config#getString(String)}
   */
  @Test
  @DisplayName("Test Config getString(String); when 'https://example.org/example'; then return 'null'")
  void testConfigGetString_whenHttpsExampleOrgExample_thenReturnNull() {
    // Arrange, Act and Assert
    assertNull(AsyncHttpClientConfigHelper.getAsyncHttpClientConfig().getString("https://example.org/example"));
  }

  /**
   * Test Config new {@link Config} (default constructor).
   * <p>
   * Method under test: default or parameterless constructor of
   * {@link AsyncHttpClientConfigHelper.Config}
   */
  @Test
  @DisplayName("Test Config new Config (default constructor)")
  void testConfigNewConfig() {
    // Arrange and Act
    AsyncHttpClientConfigHelper.Config actualConfig = new AsyncHttpClientConfigHelper.Config();

    // Assert
    assertNull(actualConfig.getString("https://example.org/example"));
    assertFalse(actualConfig.getBoolean("https://example.org/example"));
  }

  /**
   * Test {@link AsyncHttpClientConfigHelper#getAsyncHttpClientConfig()}.
   * <p>
   * Method under test:
   * {@link AsyncHttpClientConfigHelper#getAsyncHttpClientConfig()}
   */
  @Test
  @DisplayName("Test getAsyncHttpClientConfig()")
  void testGetAsyncHttpClientConfig() {
    // Arrange and Act
    AsyncHttpClientConfigHelper.Config actualAsyncHttpClientConfig = AsyncHttpClientConfigHelper
        .getAsyncHttpClientConfig();

    // Assert
    assertNull(actualAsyncHttpClientConfig.getString("https://example.org/example"));
    assertFalse(actualAsyncHttpClientConfig.getBoolean("https://example.org/example"));
  }
}
