package org.asynchttpclient.config;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.util.Properties;
import org.asynchttpclient.config.AsyncHttpClientConfigHelper.Config;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class AsyncHttpClientConfigHelperDiffblueTest {
  @InjectMocks private Config config;

  @Mock private Properties properties;

  /**
   * Test Config {@link Config#getBoolean(String)}.
   *
   * <ul>
   *   <li>Given AsyncHttpClientConfig.
   *   <li>Then return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link Config#getBoolean(String)}
   */
  @Test
  @DisplayName("Test Config getBoolean(String); given AsyncHttpClientConfig; then return 'false'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean Config.getBoolean(String)"})
  void testConfigGetBoolean_givenAsyncHttpClientConfig_thenReturnFalse() {
    // Arrange, Act and Assert
    assertFalse(
        AsyncHttpClientConfigHelper.getAsyncHttpClientConfig()
            .getBoolean("https://example.org/example"));
  }

  /**
   * Test Config {@link Config#getBoolean(String)}.
   *
   * <ul>
   *   <li>Given {@link Properties} {@link Properties#getProperty(String)} return {@code Property}.
   *   <li>Then return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link Config#getBoolean(String)}
   */
  @Test
  @DisplayName(
      "Test Config getBoolean(String); given Properties getProperty(String) return 'Property'; then return 'false'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean Config.getBoolean(String)"})
  void testConfigGetBoolean_givenPropertiesGetPropertyReturnProperty_thenReturnFalse() {
    // Arrange
    when(properties.getProperty(Mockito.<String>any())).thenReturn("Property");

    // Act
    boolean actualBoolean = config.getBoolean("https://example.org/example");

    // Assert
    verify(properties).getProperty("https://example.org/example");
    assertFalse(actualBoolean);
  }

  /**
   * Test Config {@link Config#getBoolean(String)}.
   *
   * <ul>
   *   <li>Then throw {@link IllegalArgumentException}.
   * </ul>
   *
   * <p>Method under test: {@link Config#getBoolean(String)}
   */
  @Test
  @DisplayName("Test Config getBoolean(String); then throw IllegalArgumentException")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean Config.getBoolean(String)"})
  void testConfigGetBoolean_thenThrowIllegalArgumentException() {
    // Arrange
    when(properties.getProperty(Mockito.<String>any())).thenThrow(new IllegalArgumentException());

    // Act and Assert
    assertThrows(
        IllegalArgumentException.class, () -> config.getBoolean("https://example.org/example"));
    verify(properties).getProperty("https://example.org/example");
  }

  /**
   * Test Config {@link Config#getDuration(String)}.
   *
   * <ul>
   *   <li>Then throw {@link IllegalArgumentException}.
   * </ul>
   *
   * <p>Method under test: {@link Config#getDuration(String)}
   */
  @Test
  @DisplayName("Test Config getDuration(String); then throw IllegalArgumentException")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"java.time.Duration Config.getDuration(String)"})
  void testConfigGetDuration_thenThrowIllegalArgumentException() {
    // Arrange
    when(properties.getProperty(Mockito.<String>any())).thenThrow(new IllegalArgumentException());

    // Act and Assert
    assertThrows(
        IllegalArgumentException.class, () -> config.getDuration("https://example.org/example"));
    verify(properties).getProperty("https://example.org/example");
  }

  /**
   * Test Config {@link Config#getInt(String)}.
   *
   * <ul>
   *   <li>Given {@link Properties} {@link Properties#getProperty(String)} return {@code 42}.
   *   <li>Then return forty-two.
   * </ul>
   *
   * <p>Method under test: {@link Config#getInt(String)}
   */
  @Test
  @DisplayName(
      "Test Config getInt(String); given Properties getProperty(String) return '42'; then return forty-two")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"int Config.getInt(String)"})
  void testConfigGetInt_givenPropertiesGetPropertyReturn42_thenReturnFortyTwo() {
    // Arrange
    when(properties.getProperty(Mockito.<String>any())).thenReturn("42");

    // Act
    int actualInt = config.getInt("https://example.org/example");

    // Assert
    verify(properties).getProperty("https://example.org/example");
    assertEquals(42, actualInt);
  }

  /**
   * Test Config {@link Config#getInt(String)}.
   *
   * <ul>
   *   <li>Then throw {@link IllegalArgumentException}.
   * </ul>
   *
   * <p>Method under test: {@link Config#getInt(String)}
   */
  @Test
  @DisplayName("Test Config getInt(String); then throw IllegalArgumentException")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"int Config.getInt(String)"})
  void testConfigGetInt_thenThrowIllegalArgumentException() {
    // Arrange
    when(properties.getProperty(Mockito.<String>any())).thenThrow(new IllegalArgumentException());

    // Act and Assert
    assertThrows(
        IllegalArgumentException.class, () -> config.getInt("https://example.org/example"));
    verify(properties).getProperty("https://example.org/example");
  }

  /**
   * Test Config {@link Config#getStringArray(String)}.
   *
   * <ul>
   *   <li>Then return array of {@link String} with {@code Property}.
   * </ul>
   *
   * <p>Method under test: {@link Config#getStringArray(String)}
   */
  @Test
  @DisplayName("Test Config getStringArray(String); then return array of String with 'Property'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"String[] Config.getStringArray(String)"})
  void testConfigGetStringArray_thenReturnArrayOfStringWithProperty() {
    // Arrange
    when(properties.getProperty(Mockito.<String>any())).thenReturn("Property");

    // Act
    String[] actualStringArray = config.getStringArray("https://example.org/example");

    // Assert
    verify(properties).getProperty("https://example.org/example");
    assertArrayEquals(new String[] {"Property"}, actualStringArray);
  }

  /**
   * Test Config {@link Config#getStringArray(String)}.
   *
   * <ul>
   *   <li>Then return {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link Config#getStringArray(String)}
   */
  @Test
  @DisplayName("Test Config getStringArray(String); then return 'null'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"String[] Config.getStringArray(String)"})
  void testConfigGetStringArray_thenReturnNull() {
    // Arrange
    when(properties.getProperty(Mockito.<String>any())).thenReturn("");

    // Act
    String[] actualStringArray = config.getStringArray("https://example.org/example");

    // Assert
    verify(properties).getProperty("https://example.org/example");
    assertNull(actualStringArray);
  }

  /**
   * Test Config {@link Config#getStringArray(String)}.
   *
   * <ul>
   *   <li>Then throw {@link IllegalArgumentException}.
   * </ul>
   *
   * <p>Method under test: {@link Config#getStringArray(String)}
   */
  @Test
  @DisplayName("Test Config getStringArray(String); then throw IllegalArgumentException")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"String[] Config.getStringArray(String)"})
  void testConfigGetStringArray_thenThrowIllegalArgumentException() {
    // Arrange
    when(properties.getProperty(Mockito.<String>any())).thenThrow(new IllegalArgumentException());

    // Act and Assert
    assertThrows(
        IllegalArgumentException.class, () -> config.getStringArray("https://example.org/example"));
    verify(properties).getProperty("https://example.org/example");
  }

  /**
   * Test Config {@link Config#getString(String)}.
   *
   * <ul>
   *   <li>Given AsyncHttpClientConfig.
   *   <li>Then return {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link Config#getString(String)}
   */
  @Test
  @DisplayName("Test Config getString(String); given AsyncHttpClientConfig; then return 'null'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"String Config.getString(String)"})
  void testConfigGetString_givenAsyncHttpClientConfig_thenReturnNull() {
    // Arrange, Act and Assert
    assertNull(
        AsyncHttpClientConfigHelper.getAsyncHttpClientConfig()
            .getString("https://example.org/example"));
  }

  /**
   * Test Config {@link Config#getString(String)}.
   *
   * <ul>
   *   <li>Given {@link Properties} {@link Properties#getProperty(String)} return {@code Property}.
   *   <li>Then return {@code Property}.
   * </ul>
   *
   * <p>Method under test: {@link Config#getString(String)}
   */
  @Test
  @DisplayName(
      "Test Config getString(String); given Properties getProperty(String) return 'Property'; then return 'Property'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"String Config.getString(String)"})
  void testConfigGetString_givenPropertiesGetPropertyReturnProperty_thenReturnProperty() {
    // Arrange
    when(properties.getProperty(Mockito.<String>any())).thenReturn("Property");

    // Act
    String actualString = config.getString("https://example.org/example");

    // Assert
    verify(properties).getProperty("https://example.org/example");
    assertEquals("Property", actualString);
  }

  /**
   * Test Config {@link Config#getString(String)}.
   *
   * <ul>
   *   <li>Then throw {@link IllegalArgumentException}.
   * </ul>
   *
   * <p>Method under test: {@link Config#getString(String)}
   */
  @Test
  @DisplayName("Test Config getString(String); then throw IllegalArgumentException")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"String Config.getString(String)"})
  void testConfigGetString_thenThrowIllegalArgumentException() {
    // Arrange
    when(properties.getProperty(Mockito.<String>any())).thenThrow(new IllegalArgumentException());

    // Act and Assert
    assertThrows(
        IllegalArgumentException.class, () -> config.getString("https://example.org/example"));
    verify(properties).getProperty("https://example.org/example");
  }

  /**
   * Test Config new {@link Config} (default constructor).
   *
   * <p>Method under test: default or parameterless constructor of {@link Config}
   */
  @Test
  @DisplayName("Test Config new Config (default constructor)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
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
   *
   * <p>Method under test: {@link AsyncHttpClientConfigHelper#getAsyncHttpClientConfig()}
   */
  @Test
  @DisplayName("Test getAsyncHttpClientConfig()")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"Config AsyncHttpClientConfigHelper.getAsyncHttpClientConfig()"})
  void testGetAsyncHttpClientConfig() {
    // Arrange and Act
    Config actualAsyncHttpClientConfig = AsyncHttpClientConfigHelper.getAsyncHttpClientConfig();

    // Assert
    assertNull(actualAsyncHttpClientConfig.getString("https://example.org/example"));
    assertFalse(actualAsyncHttpClientConfig.getBoolean("https://example.org/example"));
  }
}
