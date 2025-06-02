package org.asynchttpclient.config;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.asynchttpclient.config.AsyncHttpClientConfigHelper.Config;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

class AsyncHttpClientConfigHelperDiffblueTest {
  /**
   * Test Config {@link Config#getBoolean(String)}.
   * <ul>
   *   <li>When {@code https://example.org/example}.</li>
   *   <li>Then return {@code false}.</li>
   * </ul>
   * <p>
   * Method under test: {@link Config#getBoolean(String)}
   */
  @Test
  @DisplayName("Test Config getBoolean(String); when 'https://example.org/example'; then return 'false'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean Config.getBoolean(String)"})
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
   * Method under test: {@link Config#getString(String)}
   */
  @Test
  @DisplayName("Test Config getString(String); when 'https://example.org/example'; then return 'null'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"String Config.getString(String)"})
  void testConfigGetString_whenHttpsExampleOrgExample_thenReturnNull() {
    // Arrange, Act and Assert
    assertNull(AsyncHttpClientConfigHelper.getAsyncHttpClientConfig().getString("https://example.org/example"));
  }

  /**
   * Test Config new {@link Config} (default constructor).
   * <p>
   * Method under test: default or parameterless constructor of {@link Config}
   */
  @Test
  @DisplayName("Test Config new Config (default constructor)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void Config.<init>()"})
  void testConfigNewConfig() {
    // Arrange and Act
    Config actualConfig = new Config();

    // Assert
    assertNull(actualConfig.getString("https://example.org/example"));
    assertFalse(actualConfig.getBoolean("https://example.org/example"));
  }

  /**
   * Test {@link AsyncHttpClientConfigHelper#getAsyncHttpClientConfig()}.
   * <p>
   * Method under test: {@link AsyncHttpClientConfigHelper#getAsyncHttpClientConfig()}
   */
  @Test
  @DisplayName("Test getAsyncHttpClientConfig()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Config AsyncHttpClientConfigHelper.getAsyncHttpClientConfig()"})
  void testGetAsyncHttpClientConfig() {
    // Arrange and Act
    Config actualAsyncHttpClientConfig = AsyncHttpClientConfigHelper.getAsyncHttpClientConfig();

    // Assert
    assertNull(actualAsyncHttpClientConfig.getString("https://example.org/example"));
    assertFalse(actualAsyncHttpClientConfig.getBoolean("https://example.org/example"));
  }
}
