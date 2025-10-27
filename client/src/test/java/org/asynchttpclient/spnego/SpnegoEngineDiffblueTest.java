package org.asynchttpclient.spnego;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import java.util.HashMap;
import java.util.Map;
import java.util.function.BiFunction;
import javax.security.auth.login.Configuration;
import org.junit.jupiter.api.Test;

class SpnegoEngineDiffblueTest {
  /**
   * Method under test:
   * {@link SpnegoEngine#instance(String, String, String, String, boolean, Map, String)}
   */
  @Test
  void testInstance() {
    // Arrange, Act and Assert
    assertNull(SpnegoEngine
        .instance("janedoe", "https://example.org/example", "https://example.org/example",
            "https://example.org/example", true, new HashMap<>(), "https://example.org/example")
        .getLoginConfiguration());
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
   * Method under test:
   * {@link SpnegoEngine#instance(String, String, String, String, boolean, Map, String)}
   */
  @Test
  void testInstance2() {
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
   * Method under test:
   * {@link SpnegoEngine#instance(String, String, String, String, boolean, Map, String)}
   */
  @Test
  void testInstance3() {
    // Arrange
    HashMap<String, String> customLoginConfig = new HashMap<>();
    customLoginConfig.computeIfPresent("foo", mock(BiFunction.class));
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
   * Method under test: {@link SpnegoEngine#generateToken(String)}
   */
  @Test
  void testGenerateToken() throws SpnegoEngineException {
    // Arrange, Act and Assert
    assertThrows(SpnegoEngineException.class, () -> (new SpnegoEngine()).generateToken("localhost"));
    assertThrows(SpnegoEngineException.class, () -> (new SpnegoEngine()).generateToken(null));
  }

  /**
   * Method under test:
   * {@link SpnegoEngine#getCompleteServicePrincipalName(String)}
   */
  @Test
  void testGetCompleteServicePrincipalName() {
    // Arrange, Act and Assert
    assertEquals("HTTP@https://example.org/example",
        (new SpnegoEngine()).getCompleteServicePrincipalName("https://example.org/example"));
    assertEquals("https://example.org/example@https://example.org/example",
        (new SpnegoEngine("janedoe", "https://example.org/example", "https://example.org/example",
            "https://example.org/example", true, new HashMap<>(), "https://example.org/example",
            mock(SpnegoTokenGenerator.class))).getCompleteServicePrincipalName("https://example.org/example"));
    assertEquals("@",
        (new SpnegoEngine("janedoe", "https://example.org/example", "@", "https://example.org/example", true,
            new HashMap<>(), "https://example.org/example", mock(SpnegoTokenGenerator.class)))
                .getCompleteServicePrincipalName("https://example.org/example"));
    assertEquals("https://example.org/example",
        (new SpnegoEngine("janedoe", "https://example.org/example", "https://example.org/example", null, true,
            new HashMap<>(), "https://example.org/example", mock(SpnegoTokenGenerator.class)))
                .getCompleteServicePrincipalName("https://example.org/example"));
  }

  /**
   * Method under test: {@link SpnegoEngine#getLoginConfiguration()}
   */
  @Test
  void testGetLoginConfiguration() {
    // Arrange, Act and Assert
    assertNull((new SpnegoEngine()).getLoginConfiguration());
    assertNull((new SpnegoEngine("janedoe", "https://example.org/example", "https://example.org/example",
        "https://example.org/example", true, new HashMap<>(), "https://example.org/example",
        mock(SpnegoTokenGenerator.class))).getLoginConfiguration());
  }

  /**
   * Method under test: {@link SpnegoEngine#getLoginConfiguration()}
   */
  @Test
  void testGetLoginConfiguration2() {
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
   * Method under test: {@link SpnegoEngine#SpnegoEngine()}
   */
  @Test
  void testNewSpnegoEngine() {
    // Arrange, Act and Assert
    assertNull((new SpnegoEngine()).getLoginConfiguration());
    assertNull((new SpnegoEngine("janedoe", "https://example.org/example", "https://example.org/example",
        "https://example.org/example", true, new HashMap<>(), "https://example.org/example",
        mock(SpnegoTokenGenerator.class))).getLoginConfiguration());
  }
}
