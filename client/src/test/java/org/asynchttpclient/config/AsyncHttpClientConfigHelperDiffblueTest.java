package org.asynchttpclient.config;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import org.junit.jupiter.api.Test;

class AsyncHttpClientConfigHelperDiffblueTest {
  /**
   * Method under test:
   * {@link AsyncHttpClientConfigHelper.Config#getBoolean(String)}
   */
  @Test
  void testConfigGetBoolean() {
    // Arrange, Act and Assert
    assertFalse(AsyncHttpClientConfigHelper.getAsyncHttpClientConfig().getBoolean("https://example.org/example"));
  }

  /**
   * Method under test:
   * {@link AsyncHttpClientConfigHelper.Config#getString(String)}
   */
  @Test
  void testConfigGetString() {
    // Arrange, Act and Assert
    assertNull(AsyncHttpClientConfigHelper.getAsyncHttpClientConfig().getString("https://example.org/example"));
  }

  /**
   * Method under test: default or parameterless constructor of
   * {@link AsyncHttpClientConfigHelper.Config}
   */
  @Test
  void testConfigNewConfig() {
    // Arrange and Act
    AsyncHttpClientConfigHelper.Config actualConfig = new AsyncHttpClientConfigHelper.Config();

    // Assert
    assertNull(actualConfig.getString("https://example.org/example"));
    assertFalse(actualConfig.getBoolean("https://example.org/example"));
  }

  /**
   * Method under test:
   * {@link AsyncHttpClientConfigHelper#getAsyncHttpClientConfig()}
   */
  @Test
  void testGetAsyncHttpClientConfig() {
    // Arrange and Act
    AsyncHttpClientConfigHelper.Config actualAsyncHttpClientConfig = AsyncHttpClientConfigHelper
        .getAsyncHttpClientConfig();

    // Assert
    assertNull(actualAsyncHttpClientConfig.getString("https://example.org/example"));
    assertFalse(actualAsyncHttpClientConfig.getBoolean("https://example.org/example"));
  }
}
