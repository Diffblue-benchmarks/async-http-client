package org.asynchttpclient.spnego;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.mock;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.util.HashMap;
import java.util.Map;
import javax.security.auth.login.Configuration;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

class SpnegoEngineDiffblueTest {
  /**
   * Test {@link SpnegoEngine#SpnegoEngine()}.
   *
   * <p>Method under test: {@link SpnegoEngine#SpnegoEngine()}
   */
  @Test
  @DisplayName("Test new SpnegoEngine()")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void SpnegoEngine.<init>()"})
  void testNewSpnegoEngine() {
    // Arrange, Act and Assert
    assertNull(new SpnegoEngine().getLoginConfiguration());
  }

  /**
   * Test {@link SpnegoEngine#SpnegoEngine(String, String, String, String, boolean, Map, String,
   * SpnegoTokenGenerator)}.
   *
   * <p>Method under test: {@link SpnegoEngine#SpnegoEngine(String, String, String, String, boolean,
   * Map, String, SpnegoTokenGenerator)}
   */
  @Test
  @DisplayName(
      "Test new SpnegoEngine(String, String, String, String, boolean, Map, String, SpnegoTokenGenerator)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void SpnegoEngine.<init>(String, String, String, String, boolean, Map, String, SpnegoTokenGenerator)"
  })
  void testNewSpnegoEngine2() {
    // Arrange and Act
    SpnegoEngine actualSpnegoEngine =
        new SpnegoEngine(
            "janedoe",
            "https://example.org/example",
            "https://example.org/example",
            "https://example.org/example",
            true,
            new HashMap<>(),
            "https://example.org/example",
            mock(SpnegoTokenGenerator.class));

    // Assert
    assertNull(actualSpnegoEngine.getLoginConfiguration());
  }

  /**
   * Test {@link SpnegoEngine#instance(String, String, String, String, boolean, Map, String)}.
   *
   * <ul>
   *   <li>Given {@code foo}.
   *   <li>When {@link HashMap#HashMap()} {@code foo} is {@code foo}.
   *   <li>Then return LoginConfiguration Type is {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link SpnegoEngine#instance(String, String, String, String, boolean,
   * Map, String)}
   */
  @Test
  @DisplayName(
      "Test instance(String, String, String, String, boolean, Map, String); given 'foo'; when HashMap() 'foo' is 'foo'; then return LoginConfiguration Type is 'null'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "SpnegoEngine SpnegoEngine.instance(String, String, String, String, boolean, Map, String)"
  })
  void testInstance_givenFoo_whenHashMapFooIsFoo_thenReturnLoginConfigurationTypeIsNull() {
    // Arrange
    HashMap<String, String> customLoginConfig = new HashMap<>();
    customLoginConfig.put("foo", "foo");

    // Act
    SpnegoEngine actualInstanceResult =
        SpnegoEngine.instance(
            "janedoe",
            "https://example.org/example",
            "https://example.org/example",
            "https://example.org/example",
            true,
            customLoginConfig,
            "https://example.org/example");

    // Assert
    Configuration loginConfiguration = actualInstanceResult.getLoginConfiguration();
    assertNull(loginConfiguration.getType());
    assertNull(loginConfiguration.getProvider());
    assertNull(loginConfiguration.getParameters());
  }

  /**
   * Test {@link SpnegoEngine#instance(String, String, String, String, boolean, Map, String)}.
   *
   * <ul>
   *   <li>When {@code janedoe}.
   *   <li>Then return LoginConfiguration is {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link SpnegoEngine#instance(String, String, String, String, boolean,
   * Map, String)}
   */
  @Test
  @DisplayName(
      "Test instance(String, String, String, String, boolean, Map, String); when 'janedoe'; then return LoginConfiguration is 'null'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "SpnegoEngine SpnegoEngine.instance(String, String, String, String, boolean, Map, String)"
  })
  void testInstance_whenJanedoe_thenReturnLoginConfigurationIsNull() {
    // Arrange and Act
    SpnegoEngine actualInstanceResult =
        SpnegoEngine.instance(
            "janedoe",
            "https://example.org/example",
            "https://example.org/example",
            "https://example.org/example",
            true,
            new HashMap<>(),
            "https://example.org/example");

    // Assert
    assertNull(actualInstanceResult.getLoginConfiguration());
  }

  /**
   * Test {@link SpnegoEngine#instance(String, String, String, String, boolean, Map, String)}.
   *
   * <ul>
   *   <li>When {@code null}.
   *   <li>Then return LoginConfiguration is {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link SpnegoEngine#instance(String, String, String, String, boolean,
   * Map, String)}
   */
  @Test
  @DisplayName(
      "Test instance(String, String, String, String, boolean, Map, String); when 'null'; then return LoginConfiguration is 'null'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "SpnegoEngine SpnegoEngine.instance(String, String, String, String, boolean, Map, String)"
  })
  void testInstance_whenNull_thenReturnLoginConfigurationIsNull() {
    // Arrange and Act
    SpnegoEngine actualInstanceResult =
        SpnegoEngine.instance(
            null,
            "https://example.org/example",
            "https://example.org/example",
            "https://example.org/example",
            true,
            null,
            null);

    // Assert
    assertNull(actualInstanceResult.getLoginConfiguration());
  }

  /**
   * Test {@link SpnegoEngine#instance(String, String, String, String, boolean, Map, String)}.
   *
   * <ul>
   *   <li>When {@code null}.
   *   <li>Then return LoginConfiguration is {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link SpnegoEngine#instance(String, String, String, String, boolean,
   * Map, String)}
   */
  @Test
  @DisplayName(
      "Test instance(String, String, String, String, boolean, Map, String); when 'null'; then return LoginConfiguration is 'null'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "SpnegoEngine SpnegoEngine.instance(String, String, String, String, boolean, Map, String)"
  })
  void testInstance_whenNull_thenReturnLoginConfigurationIsNull2() {
    // Arrange and Act
    SpnegoEngine actualInstanceResult =
        SpnegoEngine.instance(
            null,
            "https://example.org/example",
            "https://example.org/example",
            "https://example.org/example",
            true,
            new HashMap<>(),
            null);

    // Assert
    assertNull(actualInstanceResult.getLoginConfiguration());
  }

  /**
   * Test {@link SpnegoEngine#getCompleteServicePrincipalName(String)}.
   *
   * <p>Method under test: {@link SpnegoEngine#getCompleteServicePrincipalName(String)}
   */
  @Test
  @DisplayName("Test getCompleteServicePrincipalName(String)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"String SpnegoEngine.getCompleteServicePrincipalName(String)"})
  void testGetCompleteServicePrincipalName() {
    // Arrange
    SpnegoEngine spnegoEngine =
        new SpnegoEngine(
            "janedoe",
            "https://example.org/example",
            "@",
            null,
            false,
            new HashMap<>(),
            "https://example.org/example",
            mock(SpnegoTokenGenerator.class));

    // Act and Assert
    assertEquals("@", spnegoEngine.getCompleteServicePrincipalName("https://example.org/example"));
  }

  /**
   * Test {@link SpnegoEngine#getCompleteServicePrincipalName(String)}.
   *
   * <p>Method under test: {@link SpnegoEngine#getCompleteServicePrincipalName(String)}
   */
  @Test
  @DisplayName("Test getCompleteServicePrincipalName(String)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"String SpnegoEngine.getCompleteServicePrincipalName(String)"})
  void testGetCompleteServicePrincipalName2() {
    // Arrange
    SpnegoEngine spnegoEngine =
        new SpnegoEngine(
            "janedoe",
            "https://example.org/example",
            "@",
            "https://example.org/example",
            false,
            new HashMap<>(),
            "https://example.org/example",
            mock(SpnegoTokenGenerator.class));

    // Act and Assert
    assertEquals("@", spnegoEngine.getCompleteServicePrincipalName("https://example.org/example"));
  }

  /**
   * Test {@link SpnegoEngine#getCompleteServicePrincipalName(String)}.
   *
   * <p>Method under test: {@link SpnegoEngine#getCompleteServicePrincipalName(String)}
   */
  @Test
  @DisplayName("Test getCompleteServicePrincipalName(String)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"String SpnegoEngine.getCompleteServicePrincipalName(String)"})
  void testGetCompleteServicePrincipalName3() {
    // Arrange
    SpnegoEngine spnegoEngine =
        new SpnegoEngine(
            "janedoe",
            "https://example.org/example",
            "https://example.org/example",
            "https://example.org/example",
            false,
            new HashMap<>(),
            "https://example.org/example",
            mock(SpnegoTokenGenerator.class));

    // Act and Assert
    assertEquals(
        "https://example.org/example@https://example.org/example",
        spnegoEngine.getCompleteServicePrincipalName("https://example.org/example"));
  }

  /**
   * Test {@link SpnegoEngine#getCompleteServicePrincipalName(String)}.
   *
   * <ul>
   *   <li>Then return {@code HTTP@https://example.org/example}.
   * </ul>
   *
   * <p>Method under test: {@link SpnegoEngine#getCompleteServicePrincipalName(String)}
   */
  @Test
  @DisplayName(
      "Test getCompleteServicePrincipalName(String); then return 'HTTP@https://example.org/example'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"String SpnegoEngine.getCompleteServicePrincipalName(String)"})
  void testGetCompleteServicePrincipalName_thenReturnHttpHttpsExampleOrgExample() {
    // Arrange
    SpnegoEngine spnegoEngine =
        new SpnegoEngine(
            "janedoe",
            "https://example.org/example",
            null,
            null,
            false,
            new HashMap<>(),
            "https://example.org/example",
            mock(SpnegoTokenGenerator.class));

    // Act and Assert
    assertEquals(
        "HTTP@https://example.org/example",
        spnegoEngine.getCompleteServicePrincipalName("https://example.org/example"));
  }

  /**
   * Test {@link SpnegoEngine#getLoginConfiguration()}.
   *
   * <ul>
   *   <li>Given {@link HashMap#HashMap()} {@code foo} is {@code foo}.
   *   <li>Then return Type is {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link SpnegoEngine#getLoginConfiguration()}
   */
  @Test
  @DisplayName(
      "Test getLoginConfiguration(); given HashMap() 'foo' is 'foo'; then return Type is 'null'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"Configuration SpnegoEngine.getLoginConfiguration()"})
  void testGetLoginConfiguration_givenHashMapFooIsFoo_thenReturnTypeIsNull() {
    // Arrange
    HashMap<String, String> customLoginConfig = new HashMap<>();
    customLoginConfig.put("foo", "foo");
    SpnegoEngine spnegoEngine =
        new SpnegoEngine(
            "janedoe",
            "https://example.org/example",
            "https://example.org/example",
            "https://example.org/example",
            true,
            customLoginConfig,
            "https://example.org/example",
            mock(SpnegoTokenGenerator.class));

    // Act
    Configuration actualLoginConfiguration = spnegoEngine.getLoginConfiguration();

    // Assert
    assertNull(actualLoginConfiguration.getType());
    assertNull(actualLoginConfiguration.getProvider());
    assertNull(actualLoginConfiguration.getParameters());
  }

  /**
   * Test {@link SpnegoEngine#getLoginConfiguration()}.
   *
   * <ul>
   *   <li>Given {@link SpnegoEngine#SpnegoEngine()}.
   *   <li>Then return {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link SpnegoEngine#getLoginConfiguration()}
   */
  @Test
  @DisplayName("Test getLoginConfiguration(); given SpnegoEngine(); then return 'null'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"Configuration SpnegoEngine.getLoginConfiguration()"})
  void testGetLoginConfiguration_givenSpnegoEngine_thenReturnNull() {
    // Arrange, Act and Assert
    assertNull(new SpnegoEngine().getLoginConfiguration());
  }

  /**
   * Test {@link SpnegoEngine#getLoginConfiguration()}.
   *
   * <ul>
   *   <li>Then return {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link SpnegoEngine#getLoginConfiguration()}
   */
  @Test
  @DisplayName("Test getLoginConfiguration(); then return 'null'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"Configuration SpnegoEngine.getLoginConfiguration()"})
  void testGetLoginConfiguration_thenReturnNull() {
    // Arrange
    SpnegoEngine spnegoEngine =
        new SpnegoEngine(
            "janedoe",
            "https://example.org/example",
            "https://example.org/example",
            "https://example.org/example",
            true,
            new HashMap<>(),
            "https://example.org/example",
            mock(SpnegoTokenGenerator.class));

    // Act and Assert
    assertNull(spnegoEngine.getLoginConfiguration());
  }
}
