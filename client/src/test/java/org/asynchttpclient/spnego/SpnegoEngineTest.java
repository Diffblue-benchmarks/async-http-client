/*
 *    Copyright (c) 2023 AsyncHttpClient Project. All rights reserved.
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */
package org.asynchttpclient.spnego;

import io.github.artsok.RepeatedIfExceptionsTest;
import org.apache.commons.io.FileUtils;
import org.apache.kerby.kerberos.kerb.server.SimpleKdcServer;
import org.asynchttpclient.AbstractBasicTest;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import java.io.File;
import java.util.HashMap;
import java.util.Map;
import javax.security.auth.login.Configuration;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;

public class SpnegoEngineTest extends AbstractBasicTest {
  private SimpleKdcServer kerbyServer;

  private String basedir;

  private String alice;

  private String bob;

  private File aliceKeytab;

  private File bobKeytab;

  private File loginConfig;

  Map<String, String> customLoginConfig;

  SpnegoTokenGenerator tokenGenerator = mock(SpnegoTokenGenerator.class);

  byte[] mockGeneratedToken = "token As Bytes".getBytes();

  SpnegoEngine specialEngineSetup;

  @BeforeEach
  public void startServers() throws Exception {
    basedir = System.getProperty("basedir");
    if (basedir == null) {
      basedir = new File(".").getCanonicalPath();
    }

    // System.setProperty("sun.security.krb5.debug", "true");
    System.setProperty(
        "java.security.krb5.conf",
        new File(basedir + File.separator + "target" + File.separator + "krb5.conf")
            .getCanonicalPath());
    loginConfig = new File(basedir + File.separator + "target" + File.separator + "kerberos.jaas");
    System.setProperty("java.security.auth.login.config", loginConfig.getCanonicalPath());
    kerbyServer = new SimpleKdcServer();
    kerbyServer.setKdcRealm("service.ws.apache.org");
    kerbyServer.setAllowUdp(false);
    kerbyServer.setWorkDir(new File(basedir, "target"));

    // kerbyServer.setInnerKdcImpl(new NettyKdcServerImpl(kerbyServer.getKdcSetting()));
    kerbyServer.init();

    // Create principals
    alice = "alice@service.ws.apache.org";
    bob = "bob/service.ws.apache.org@service.ws.apache.org";
    kerbyServer.createPrincipal(alice, "alice");
    kerbyServer.createPrincipal(bob, "bob");
    aliceKeytab = new File(basedir + File.separator + "target" + File.separator + "alice.keytab");
    bobKeytab = new File(basedir + File.separator + "target" + File.separator + "bob.keytab");
    kerbyServer.exportPrincipal(alice, aliceKeytab);
    kerbyServer.exportPrincipal(bob, bobKeytab);
    kerbyServer.start();
    FileUtils.copyInputStreamToFile(
        SpnegoEngine.class.getResourceAsStream("/kerberos.jaas"), loginConfig);
    customLoginConfig = new HashMap<>();
    customLoginConfig.put("alice", "levine");
    when(tokenGenerator.generateSpnegoDERObject(any())).thenReturn(mockGeneratedToken);
    specialEngineSetup =
        new SpnegoEngine(
            this.alice,
            "password",
            null,
            this.basedir,
            true,
            customLoginConfig,
            this.basedir,
            tokenGenerator);
  }

  @RepeatedIfExceptionsTest(repeats = 5)
  public void testSpnegoGenerateTokenWithUsernamePassword() throws Exception {
    SpnegoEngine spnegoEngine =
        new SpnegoEngine(
            "alice", "alice", "bob", "service.ws.apache.org", false, null, "alice", null);
    String token = spnegoEngine.generateToken("localhost");
    assertNotNull(token);
    assertTrue(token.startsWith("YII"));
  }

  @AfterEach
  public void cleanup() throws Exception {
    if (kerbyServer != null) {
      kerbyServer.stop();
    }
    FileUtils.deleteQuietly(aliceKeytab);
    FileUtils.deleteQuietly(bobKeytab);
    FileUtils.deleteQuietly(loginConfig);
  }

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
    // Arrange
    String username = this.basedir;
    String password = this.basedir;
    String servicePrincipalName = this.basedir;
    String realmName = this.basedir;
    HashMap<String, String> customLoginConfig = new HashMap<>();

    // Act
    SpnegoEngine actualSpnegoEngine =
        new SpnegoEngine(
            username,
            password,
            servicePrincipalName,
            realmName,
            true,
            customLoginConfig,
            this.basedir,
            mock(SpnegoTokenGenerator.class));

    // Assert
    assertNull(actualSpnegoEngine.getLoginConfiguration());
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
  void testNewSpnegoEngine3() {
    // Arrange
    String username = this.alice;
    String password = this.alice;
    String servicePrincipalName = this.alice;
    String realmName = this.alice;
    Map<String, String> customLoginConfig = this.customLoginConfig;
    String loginContextName = this.alice;

    // Act
    SpnegoEngine actualSpnegoEngine =
        new SpnegoEngine(
            username,
            password,
            servicePrincipalName,
            realmName,
            true,
            customLoginConfig,
            loginContextName,
            this.tokenGenerator);

    // Assert
    Configuration loginConfiguration = actualSpnegoEngine.getLoginConfiguration();
    assertNull(loginConfiguration.getType());
    assertNull(loginConfiguration.getProvider());
    assertNull(loginConfiguration.getParameters());
  }

  /**
   * Test {@link SpnegoEngine#instance(String, String, String, String, boolean, Map, String)}.
   *
   * <ul>
   *   <li>When {@link HashMap#HashMap()}.
   *   <li>Then return LoginConfiguration is {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link SpnegoEngine#instance(String, String, String, String, boolean,
   * Map, String)}
   */
  @Test
  @DisplayName(
      "Test instance(String, String, String, String, boolean, Map, String); when HashMap(); then return LoginConfiguration is 'null'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "SpnegoEngine SpnegoEngine.instance(String, String, String, String, boolean, Map, String)"
  })
  void testInstance_whenHashMap_thenReturnLoginConfigurationIsNull() {
    // Arrange
    String username = this.basedir;
    String password = this.basedir;
    String servicePrincipalName = this.basedir;
    String realmName = this.basedir;
    HashMap<String, String> customLoginConfig = new HashMap<>();

    // Act
    SpnegoEngine actualInstanceResult =
        SpnegoEngine.instance(
            username,
            password,
            servicePrincipalName,
            realmName,
            true,
            customLoginConfig,
            this.basedir);

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
    // Arrange
    String password = this.basedir;
    String servicePrincipalName = this.basedir;

    // Act
    SpnegoEngine actualInstanceResult =
        SpnegoEngine.instance(null, password, servicePrincipalName, this.basedir, true, null, null);

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
    // Arrange
    String password = this.basedir;
    String servicePrincipalName = this.basedir;
    String realmName = this.basedir;

    // Act
    SpnegoEngine actualInstanceResult =
        SpnegoEngine.instance(
            null, password, servicePrincipalName, realmName, true, new HashMap<>(), null);

    // Assert
    assertNull(actualInstanceResult.getLoginConfiguration());
  }

  /**
   * Test {@link SpnegoEngine#instance(String, String, String, String, boolean, Map, String)}.
   *
   * <ul>
   *   <li>When this {@link SpnegoEngineTest#customLoginConfig}.
   *   <li>Then return LoginConfiguration Type is {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link SpnegoEngine#instance(String, String, String, String, boolean,
   * Map, String)}
   */
  @Test
  @DisplayName(
      "Test instance(String, String, String, String, boolean, Map, String); when this customLoginConfig; then return LoginConfiguration Type is 'null'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "SpnegoEngine SpnegoEngine.instance(String, String, String, String, boolean, Map, String)"
  })
  void testInstance_whenThisCustomLoginConfig_thenReturnLoginConfigurationTypeIsNull() {
    // Arrange
    String username = this.alice;
    String password = this.alice;
    String servicePrincipalName = this.alice;
    String realmName = this.alice;
    Map<String, String> customLoginConfig = this.customLoginConfig;

    // Act
    SpnegoEngine actualInstanceResult =
        SpnegoEngine.instance(
            username,
            password,
            servicePrincipalName,
            realmName,
            true,
            customLoginConfig,
            this.alice);

    // Assert
    Configuration loginConfiguration = actualInstanceResult.getLoginConfiguration();
    assertNull(loginConfiguration.getType());
    assertNull(loginConfiguration.getProvider());
    assertNull(loginConfiguration.getParameters());
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
    String username = this.basedir;
    String password = this.basedir;
    HashMap<String, String> customLoginConfig = new HashMap<>();

    SpnegoEngine spnegoEngine =
        new SpnegoEngine(
            username,
            password,
            "@",
            null,
            false,
            customLoginConfig,
            this.basedir,
            mock(SpnegoTokenGenerator.class));

    // Act
    String actualCompleteServicePrincipalName =
        spnegoEngine.getCompleteServicePrincipalName(this.basedir);

    // Assert
    assertEquals("@", actualCompleteServicePrincipalName);
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
    String username = this.basedir;
    String password = this.basedir;
    String realmName = this.basedir;
    HashMap<String, String> customLoginConfig = new HashMap<>();

    SpnegoEngine spnegoEngine =
        new SpnegoEngine(
            username,
            password,
            "@",
            realmName,
            false,
            customLoginConfig,
            this.basedir,
            mock(SpnegoTokenGenerator.class));

    // Act
    String actualCompleteServicePrincipalName =
        spnegoEngine.getCompleteServicePrincipalName(this.basedir);

    // Assert
    assertEquals("@", actualCompleteServicePrincipalName);
  }

  /**
   * Test {@link SpnegoEngine#getLoginConfiguration()}.
   *
   * <p>Method under test: {@link SpnegoEngine#getLoginConfiguration()}
   */
  @Test
  @DisplayName("Test getLoginConfiguration()")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"Configuration SpnegoEngine.getLoginConfiguration()"})
  void testGetLoginConfiguration() {
    // Arrange and Act
    Configuration actualLoginConfiguration = this.specialEngineSetup.getLoginConfiguration();

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
    String username = this.basedir;
    String password = this.basedir;
    String servicePrincipalName = this.basedir;
    String realmName = this.basedir;
    HashMap<String, String> customLoginConfig = new HashMap<>();

    SpnegoEngine spnegoEngine =
        new SpnegoEngine(
            username,
            password,
            servicePrincipalName,
            realmName,
            true,
            customLoginConfig,
            this.basedir,
            mock(SpnegoTokenGenerator.class));

    // Act and Assert
    assertNull(spnegoEngine.getLoginConfiguration());
  }
}
