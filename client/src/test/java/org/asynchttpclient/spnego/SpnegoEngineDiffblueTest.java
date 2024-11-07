package org.asynchttpclient.spnego;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import java.util.HashMap;
import java.util.Map;
import javax.security.auth.login.Configuration;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class SpnegoEngineDiffblueTest {
  /**
   * Test {@link SpnegoEngine#SpnegoEngine()}.
   * <p>
   * Method under test: {@link SpnegoEngine#SpnegoEngine()}
   */
  @Test
  @DisplayName("Test new SpnegoEngine()")
  void testNewSpnegoEngine() {
    // Arrange, Act and Assert
    assertNull((new SpnegoEngine()).getLoginConfiguration());
    assertNull((new SpnegoEngine("janedoe", "https://example.org/example", "https://example.org/example",
        "https://example.org/example", true, new HashMap<>(), "https://example.org/example",
        mock(SpnegoTokenGenerator.class))).getLoginConfiguration());
  }

  /**
   * Test
   * {@link SpnegoEngine#instance(String, String, String, String, boolean, Map, String)}.
   * <ul>
   *   <li>Given {@code foo}.</li>
   *   <li>When {@link HashMap#HashMap()} {@code foo} is {@code foo}.</li>
   *   <li>Then return LoginConfiguration Type is {@code null}.</li>
   * </ul>
   * <p>
   * Method under test:
   * {@link SpnegoEngine#instance(String, String, String, String, boolean, Map, String)}
   */
  @Test
  @DisplayName("Test instance(String, String, String, String, boolean, Map, String); given 'foo'; when HashMap() 'foo' is 'foo'; then return LoginConfiguration Type is 'null'")
  void testInstance_givenFoo_whenHashMapFooIsFoo_thenReturnLoginConfigurationTypeIsNull() {
    // Arrange
    HashMap<String, String> customLoginConfig = new HashMap<>();
    customLoginConfig.put("foo", "foo");

    // Act and Assert
    Configuration loginConfiguration = SpnegoEngine
        .instance("janedoe", "https://example.org/example", "https://example.org/example",
            "https://example.org/example", true, customLoginConfig, "https://example.org/example")
        .getLoginConfiguration();
    assertNull(loginConfiguration.getType());
    assertNull(loginConfiguration.getProvider());
    assertNull(loginConfiguration.getParameters());
  }

  /**
   * Test
   * {@link SpnegoEngine#instance(String, String, String, String, boolean, Map, String)}.
   * <ul>
   *   <li>When {@code janedoe}.</li>
   *   <li>Then return LoginConfiguration is {@code null}.</li>
   * </ul>
   * <p>
   * Method under test:
   * {@link SpnegoEngine#instance(String, String, String, String, boolean, Map, String)}
   */
  @Test
  @DisplayName("Test instance(String, String, String, String, boolean, Map, String); when 'janedoe'; then return LoginConfiguration is 'null'")
  void testInstance_whenJanedoe_thenReturnLoginConfigurationIsNull() {
    // Arrange, Act and Assert
    assertNull(SpnegoEngine
        .instance("janedoe", "https://example.org/example", "https://example.org/example",
            "https://example.org/example", true, new HashMap<>(), "https://example.org/example")
        .getLoginConfiguration());
  }

  /**
   * Test
   * {@link SpnegoEngine#instance(String, String, String, String, boolean, Map, String)}.
   * <ul>
   *   <li>When {@code null}.</li>
   *   <li>Then return LoginConfiguration is {@code null}.</li>
   * </ul>
   * <p>
   * Method under test:
   * {@link SpnegoEngine#instance(String, String, String, String, boolean, Map, String)}
   */
  @Test
  @DisplayName("Test instance(String, String, String, String, boolean, Map, String); when 'null'; then return LoginConfiguration is 'null'")
  void testInstance_whenNull_thenReturnLoginConfigurationIsNull() {
    // Arrange, Act and Assert
    assertNull(SpnegoEngine
        .instance(null, "https://example.org/example", "https://example.org/example", "https://example.org/example",
            true, new HashMap<>(), "https://example.org/example")
        .getLoginConfiguration());
    assertNull(
        SpnegoEngine
            .instance("janedoe", "https://example.org/example", "https://example.org/example",
                "https://example.org/example", true, new HashMap<>(), null)
            .getLoginConfiguration());
  }

  /**
   * Test {@link SpnegoEngine#generateToken(String)}.
   * <ul>
   *   <li>Given {@link SpnegoEngine#SpnegoEngine()}.</li>
   *   <li>When {@code localhost}.</li>
   *   <li>Then throw {@link SpnegoEngineException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link SpnegoEngine#generateToken(String)}
   */
  @Test
  @DisplayName("Test generateToken(String); given SpnegoEngine(); when 'localhost'; then throw SpnegoEngineException")
  void testGenerateToken_givenSpnegoEngine_whenLocalhost_thenThrowSpnegoEngineException() throws SpnegoEngineException {
    // Arrange, Act and Assert
    assertThrows(SpnegoEngineException.class, () -> (new SpnegoEngine()).generateToken("localhost"));
  }

  /**
   * Test {@link SpnegoEngine#generateToken(String)}.
   * <ul>
   *   <li>Given {@link SpnegoEngine#SpnegoEngine()}.</li>
   *   <li>When {@code null}.</li>
   *   <li>Then throw {@link SpnegoEngineException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link SpnegoEngine#generateToken(String)}
   */
  @Test
  @DisplayName("Test generateToken(String); given SpnegoEngine(); when 'null'; then throw SpnegoEngineException")
  void testGenerateToken_givenSpnegoEngine_whenNull_thenThrowSpnegoEngineException() throws SpnegoEngineException {
    // Arrange, Act and Assert
    assertThrows(SpnegoEngineException.class, () -> (new SpnegoEngine()).generateToken(null));
  }

  /**
   * Test {@link SpnegoEngine#getCompleteServicePrincipalName(String)}.
   * <p>
   * Method under test:
   * {@link SpnegoEngine#getCompleteServicePrincipalName(String)}
   */
  @Test
  @DisplayName("Test getCompleteServicePrincipalName(String)")
  void testGetCompleteServicePrincipalName() {
    // Arrange, Act and Assert
    assertEquals("https://example.org/example@https://example.org/example",
        (new SpnegoEngine("janedoe", "https://example.org/example", "https://example.org/example",
            "https://example.org/example", true, new HashMap<>(), "https://example.org/example",
            mock(SpnegoTokenGenerator.class))).getCompleteServicePrincipalName("https://example.org/example"));
  }

  /**
   * Test {@link SpnegoEngine#getCompleteServicePrincipalName(String)}.
   * <ul>
   *   <li>Then return {@code @}.</li>
   * </ul>
   * <p>
   * Method under test:
   * {@link SpnegoEngine#getCompleteServicePrincipalName(String)}
   */
  @Test
  @DisplayName("Test getCompleteServicePrincipalName(String); then return '@'")
  void testGetCompleteServicePrincipalName_thenReturnCommercialAt() {
    // Arrange, Act and Assert
    assertEquals("@",
        (new SpnegoEngine("janedoe", "https://example.org/example", "@", "https://example.org/example", true,
            new HashMap<>(), "https://example.org/example", mock(SpnegoTokenGenerator.class)))
                .getCompleteServicePrincipalName("https://example.org/example"));
  }

  /**
   * Test {@link SpnegoEngine#getCompleteServicePrincipalName(String)}.
   * <ul>
   *   <li>Then return {@code HTTP@https://example.org/example}.</li>
   * </ul>
   * <p>
   * Method under test:
   * {@link SpnegoEngine#getCompleteServicePrincipalName(String)}
   */
  @Test
  @DisplayName("Test getCompleteServicePrincipalName(String); then return 'HTTP@https://example.org/example'")
  void testGetCompleteServicePrincipalName_thenReturnHttpHttpsExampleOrgExample() {
    // Arrange, Act and Assert
    assertEquals("HTTP@https://example.org/example",
        (new SpnegoEngine()).getCompleteServicePrincipalName("https://example.org/example"));
  }

  /**
   * Test {@link SpnegoEngine#getCompleteServicePrincipalName(String)}.
   * <ul>
   *   <li>Then return {@code https://example.org/example}.</li>
   * </ul>
   * <p>
   * Method under test:
   * {@link SpnegoEngine#getCompleteServicePrincipalName(String)}
   */
  @Test
  @DisplayName("Test getCompleteServicePrincipalName(String); then return 'https://example.org/example'")
  void testGetCompleteServicePrincipalName_thenReturnHttpsExampleOrgExample() {
    // Arrange, Act and Assert
    assertEquals("https://example.org/example",
        (new SpnegoEngine("janedoe", "https://example.org/example", "https://example.org/example", null, true,
            new HashMap<>(), "https://example.org/example", mock(SpnegoTokenGenerator.class)))
                .getCompleteServicePrincipalName("https://example.org/example"));
  }

  /**
   * Test {@link SpnegoEngine#getLoginConfiguration()}.
   * <ul>
   *   <li>Given {@link HashMap#HashMap()} {@code foo} is {@code foo}.</li>
   *   <li>Then return Type is {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link SpnegoEngine#getLoginConfiguration()}
   */
  @Test
  @DisplayName("Test getLoginConfiguration(); given HashMap() 'foo' is 'foo'; then return Type is 'null'")
  void testGetLoginConfiguration_givenHashMapFooIsFoo_thenReturnTypeIsNull() {
    // Arrange
    HashMap<String, String> customLoginConfig = new HashMap<>();
    customLoginConfig.put("foo", "foo");

    // Act
    Configuration actualLoginConfiguration = (new SpnegoEngine("janedoe", "https://example.org/example",
        "https://example.org/example", "https://example.org/example", true, customLoginConfig,
        "https://example.org/example", mock(SpnegoTokenGenerator.class))).getLoginConfiguration();

    // Assert
    assertNull(actualLoginConfiguration.getType());
    assertNull(actualLoginConfiguration.getProvider());
    assertNull(actualLoginConfiguration.getParameters());
  }

  /**
   * Test {@link SpnegoEngine#getLoginConfiguration()}.
   * <ul>
   *   <li>Given {@link SpnegoEngine#SpnegoEngine()}.</li>
   *   <li>Then return {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link SpnegoEngine#getLoginConfiguration()}
   */
  @Test
  @DisplayName("Test getLoginConfiguration(); given SpnegoEngine(); then return 'null'")
  void testGetLoginConfiguration_givenSpnegoEngine_thenReturnNull() {
    // Arrange, Act and Assert
    assertNull((new SpnegoEngine()).getLoginConfiguration());
  }

  /**
   * Test {@link SpnegoEngine#getLoginConfiguration()}.
   * <ul>
   *   <li>Then return {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link SpnegoEngine#getLoginConfiguration()}
   */
  @Test
  @DisplayName("Test getLoginConfiguration(); then return 'null'")
  void testGetLoginConfiguration_thenReturnNull() {
    // Arrange, Act and Assert
    assertNull((new SpnegoEngine("janedoe", "https://example.org/example", "https://example.org/example",
        "https://example.org/example", true, new HashMap<>(), "https://example.org/example",
        mock(SpnegoTokenGenerator.class))).getLoginConfiguration());
  }
}
