package org.asynchttpclient.proxy;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.function.Function;
import org.asynchttpclient.Realm;
import org.asynchttpclient.Realm.AuthScheme;
import org.asynchttpclient.proxy.ProxyServer.Builder;
import org.asynchttpclient.uri.Uri;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

class ProxyServerDiffblueTest {
  /**
   * Test Builder {@link Builder#setNonProxyHost(String)}.
   *
   * <p>Method under test: {@link Builder#setNonProxyHost(String)}
   */
  @Test
  @DisplayName("Test Builder setNonProxyHost(String)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"Builder Builder.setNonProxyHost(String)"})
  void testBuilderSetNonProxyHost() {
    // Arrange
    Builder builder = new Builder("https://example.org/example", 8080);

    // Act
    Builder actualSetNonProxyHostResult = builder.setNonProxyHost("https://example.org/example");

    // Assert
    List<String> nonProxyHosts = builder.build().getNonProxyHosts();
    assertEquals(1, nonProxyHosts.size());
    assertEquals("https://example.org/example", nonProxyHosts.get(0));
    assertSame(builder, actualSetNonProxyHostResult);
  }

  /**
   * Test Builder {@link Builder#setNonProxyHost(String)}.
   *
   * <p>Method under test: {@link Builder#setNonProxyHost(String)}
   */
  @Test
  @DisplayName("Test Builder setNonProxyHost(String)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"Builder Builder.setNonProxyHost(String)"})
  void testBuilderSetNonProxyHost2() {
    // Arrange
    Builder builder = new Builder("https://example.org/example", 8080);
    builder.setNonProxyHost(null);

    // Act
    Builder actualSetNonProxyHostResult = builder.setNonProxyHost("https://example.org/example");

    // Assert
    List<String> nonProxyHosts = builder.build().getNonProxyHosts();
    assertEquals(2, nonProxyHosts.size());
    assertEquals("https://example.org/example", nonProxyHosts.get(1));
    assertNull(nonProxyHosts.get(0));
    assertSame(builder, actualSetNonProxyHostResult);
  }

  /**
   * Test Builder {@link Builder#setRealm(Builder)} with {@code Builder}.
   *
   * <p>Method under test: {@link Builder#setRealm(Realm.Builder)}
   */
  @Test
  @DisplayName("Test Builder setRealm(Builder) with 'Builder'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"Builder Builder.setRealm(Realm.Builder)"})
  void testBuilderSetRealmWithBuilder() {
    // Arrange
    Builder builder = new Builder("https://example.org/example", 8080);

    Realm.Builder realm =
        new Realm.Builder("https://example.org/example", "https://example.org/example");
    realm.setNonce("https://example.org/example");
    Uri uri =
        new Uri(
            "https://example.org/example",
            "https://example.org/example",
            "https://example.org/example",
            8080,
            "https://example.org/example",
            "https://example.org/example",
            "https://example.org/example");
    realm.setUri(uri);
    realm.setScheme(AuthScheme.BASIC);

    // Act and Assert
    Realm realm2 = builder.setRealm(realm).build().getRealm();
    assertEquals("4d08e2ca0bbe3f97b2c090b95d3e62d9", realm2.getResponse());
    assertSame(uri, realm2.getUri());
  }

  /**
   * Test Builder {@link Builder#setRealm(Builder)} with {@code Builder}.
   *
   * <p>Method under test: {@link Builder#setRealm(Realm.Builder)}
   */
  @Test
  @DisplayName("Test Builder setRealm(Builder) with 'Builder'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"Builder Builder.setRealm(Realm.Builder)"})
  void testBuilderSetRealmWithBuilder2() {
    // Arrange
    Builder builder = new Builder("https://example.org/example", 8080);

    Realm.Builder realm =
        new Realm.Builder("https://example.org/example", "https://example.org/example");
    realm.setNonce("https://example.org/example");
    Uri uri =
        new Uri(
            "https://example.org/example",
            "https://example.org/example",
            "https://example.org/example",
            8080,
            "https://example.org/example",
            "",
            "https://example.org/example");
    realm.setUri(uri);
    realm.setScheme(AuthScheme.BASIC);

    // Act and Assert
    Realm realm2 = builder.setRealm(realm).build().getRealm();
    assertEquals("ac8d21b2a82bc835e1a6e606bbfca2de", realm2.getResponse());
    assertSame(uri, realm2.getUri());
  }

  /**
   * Test Builder {@link Builder#setRealm(Builder)} with {@code Builder}.
   *
   * <p>Method under test: {@link Builder#setRealm(Realm.Builder)}
   */
  @Test
  @DisplayName("Test Builder setRealm(Builder) with 'Builder'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"Builder Builder.setRealm(Realm.Builder)"})
  void testBuilderSetRealmWithBuilder3() {
    // Arrange
    Builder builder = new Builder("https://example.org/example", 8080);

    Realm.Builder realm =
        new Realm.Builder("https://example.org/example", "https://example.org/example");
    realm.setUseAbsoluteURI(true);
    realm.setNonce("https://example.org/example");
    Uri uri =
        new Uri(
            "https://example.org/example",
            "https://example.org/example",
            "https://example.org/example",
            8080,
            "https://example.org/example",
            "https://example.org/example",
            "https://example.org/example");
    realm.setUri(uri);
    realm.setScheme(AuthScheme.BASIC);

    // Act and Assert
    Realm realm2 = builder.setRealm(realm).build().getRealm();
    assertEquals("b56797fdd55efbde380e18fc83f14e59", realm2.getResponse());
    assertTrue(realm2.isUseAbsoluteURI());
    assertSame(uri, realm2.getUri());
  }

  /**
   * Test Builder {@link Builder#setRealm(Builder)} with {@code Builder}.
   *
   * <ul>
   *   <li>Given {@code auth-int}.
   *   <li>Then return build Realm Qop is {@code auth-int}.
   * </ul>
   *
   * <p>Method under test: {@link Builder#setRealm(Realm.Builder)}
   */
  @Test
  @DisplayName(
      "Test Builder setRealm(Builder) with 'Builder'; given 'auth-int'; then return build Realm Qop is 'auth-int'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"Builder Builder.setRealm(Realm.Builder)"})
  void testBuilderSetRealmWithBuilder_givenAuthInt_thenReturnBuildRealmQopIsAuthInt() {
    // Arrange
    Builder builder = new Builder("https://example.org/example", 8080);

    Realm.Builder realm =
        new Realm.Builder("https://example.org/example", "https://example.org/example");
    realm.setQop("auth-int");
    realm.setNonce("https://example.org/example");
    Uri uri =
        new Uri(
            "https://example.org/example",
            "https://example.org/example",
            "https://example.org/example",
            8080,
            "https://example.org/example",
            "https://example.org/example",
            "https://example.org/example");
    realm.setUri(uri);
    realm.setScheme(AuthScheme.BASIC);

    // Act and Assert
    Realm realm2 = builder.setRealm(realm).build().getRealm();
    assertEquals("auth-int", realm2.getQop());
    assertSame(uri, realm2.getUri());
  }

  /**
   * Test Builder {@link Builder#setRealm(Builder)} with {@code Builder}.
   *
   * <ul>
   *   <li>Given {@code auth}.
   *   <li>Then return build Realm Qop is {@code auth}.
   * </ul>
   *
   * <p>Method under test: {@link Builder#setRealm(Realm.Builder)}
   */
  @Test
  @DisplayName(
      "Test Builder setRealm(Builder) with 'Builder'; given 'auth'; then return build Realm Qop is 'auth'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"Builder Builder.setRealm(Realm.Builder)"})
  void testBuilderSetRealmWithBuilder_givenAuth_thenReturnBuildRealmQopIsAuth() {
    // Arrange
    Builder builder = new Builder("https://example.org/example", 8080);

    Realm.Builder realm =
        new Realm.Builder("https://example.org/example", "https://example.org/example");
    realm.setQop("auth");
    realm.setNonce("https://example.org/example");
    Uri uri =
        new Uri(
            "https://example.org/example",
            "https://example.org/example",
            "https://example.org/example",
            8080,
            "https://example.org/example",
            "https://example.org/example",
            "https://example.org/example");
    realm.setUri(uri);
    realm.setScheme(AuthScheme.BASIC);

    // Act and Assert
    Realm realm2 = builder.setRealm(realm).build().getRealm();
    assertEquals("auth", realm2.getQop());
    assertSame(uri, realm2.getUri());
  }

  /**
   * Test Builder {@link Builder#setRealm(Builder)} with {@code Builder}.
   *
   * <ul>
   *   <li>Given {@code MD5}.
   *   <li>Then return build Realm Algorithm is {@code MD5}.
   * </ul>
   *
   * <p>Method under test: {@link Builder#setRealm(Realm.Builder)}
   */
  @Test
  @DisplayName(
      "Test Builder setRealm(Builder) with 'Builder'; given 'MD5'; then return build Realm Algorithm is 'MD5'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"Builder Builder.setRealm(Realm.Builder)"})
  void testBuilderSetRealmWithBuilder_givenMd5_thenReturnBuildRealmAlgorithmIsMd5() {
    // Arrange
    Builder builder = new Builder("https://example.org/example", 8080);

    Realm.Builder realm =
        new Realm.Builder("https://example.org/example", "https://example.org/example");
    realm.setAlgorithm("MD5");
    realm.setNonce("https://example.org/example");
    Uri uri =
        new Uri(
            "https://example.org/example",
            "https://example.org/example",
            "https://example.org/example",
            8080,
            "https://example.org/example",
            "https://example.org/example",
            "https://example.org/example");
    realm.setUri(uri);
    realm.setScheme(AuthScheme.BASIC);

    // Act and Assert
    Realm realm2 = builder.setRealm(realm).build().getRealm();
    assertEquals("4d08e2ca0bbe3f97b2c090b95d3e62d9", realm2.getResponse());
    assertEquals("MD5", realm2.getAlgorithm());
    assertSame(uri, realm2.getUri());
  }

  /**
   * Test Builder {@link Builder#setRealm(Builder)} with {@code Builder}.
   *
   * <ul>
   *   <li>Then return build Realm Algorithm is {@code MD5-sess}.
   * </ul>
   *
   * <p>Method under test: {@link Builder#setRealm(Realm.Builder)}
   */
  @Test
  @DisplayName(
      "Test Builder setRealm(Builder) with 'Builder'; then return build Realm Algorithm is 'MD5-sess'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"Builder Builder.setRealm(Realm.Builder)"})
  void testBuilderSetRealmWithBuilder_thenReturnBuildRealmAlgorithmIsMd5Sess() {
    // Arrange
    Builder builder = new Builder("https://example.org/example", 8080);

    Realm.Builder realm =
        new Realm.Builder("https://example.org/example", "https://example.org/example");
    realm.setAlgorithm("MD5-sess");
    realm.setNonce("https://example.org/example");
    Uri uri =
        new Uri(
            "https://example.org/example",
            "https://example.org/example",
            "https://example.org/example",
            8080,
            "https://example.org/example",
            "https://example.org/example",
            "https://example.org/example");
    realm.setUri(uri);
    realm.setScheme(AuthScheme.BASIC);

    // Act and Assert
    Realm realm2 = builder.setRealm(realm).build().getRealm();
    assertEquals("MD5-sess", realm2.getAlgorithm());
    assertSame(uri, realm2.getUri());
  }

  /**
   * Test Builder {@link Builder#setRealm(Builder)} with {@code Builder}.
   *
   * <ul>
   *   <li>Then return build Realm Nonce is empty string.
   * </ul>
   *
   * <p>Method under test: {@link Builder#setRealm(Realm.Builder)}
   */
  @Test
  @DisplayName(
      "Test Builder setRealm(Builder) with 'Builder'; then return build Realm Nonce is empty string")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"Builder Builder.setRealm(Realm.Builder)"})
  void testBuilderSetRealmWithBuilder_thenReturnBuildRealmNonceIsEmptyString() {
    // Arrange
    Builder builder = new Builder("https://example.org/example", 8080);

    Realm.Builder realm =
        new Realm.Builder("https://example.org/example", "https://example.org/example");
    realm.setNonce("");
    realm.setUri(null);
    realm.setScheme(AuthScheme.BASIC);

    // Act and Assert
    Realm realm2 = builder.setRealm(realm).build().getRealm();
    assertEquals("", realm2.getNonce());
    assertNull(realm2.getCnonce());
    assertNull(realm2.getResponse());
    assertNull(realm2.getUri());
  }

  /**
   * Test Builder {@link Builder#setRealm(Builder)} with {@code Builder}.
   *
   * <ul>
   *   <li>Then return build Realm Nonce is {@code https://example.org/example}.
   * </ul>
   *
   * <p>Method under test: {@link Builder#setRealm(Realm.Builder)}
   */
  @Test
  @DisplayName(
      "Test Builder setRealm(Builder) with 'Builder'; then return build Realm Nonce is 'https://example.org/example'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"Builder Builder.setRealm(Realm.Builder)"})
  void testBuilderSetRealmWithBuilder_thenReturnBuildRealmNonceIsHttpsExampleOrgExample() {
    // Arrange
    Builder builder = new Builder("https://example.org/example", 8080);

    Realm.Builder realm =
        new Realm.Builder("https://example.org/example", "https://example.org/example");
    realm.setNonce("https://example.org/example");
    realm.setUri(null);
    realm.setScheme(AuthScheme.BASIC);

    // Act and Assert
    Realm realm2 = builder.setRealm(realm).build().getRealm();
    assertEquals("https://example.org/example", realm2.getNonce());
    assertNull(realm2.getAlgorithm());
    assertNull(realm2.getQop());
    assertNull(realm2.getResponse());
    assertNull(realm2.getUri());
  }

  /**
   * Test Builder {@link Builder#setRealm(Builder)} with {@code Builder}.
   *
   * <ul>
   *   <li>Then return build Realm Nonce is {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link Builder#setRealm(Realm.Builder)}
   */
  @Test
  @DisplayName(
      "Test Builder setRealm(Builder) with 'Builder'; then return build Realm Nonce is 'null'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"Builder Builder.setRealm(Realm.Builder)"})
  void testBuilderSetRealmWithBuilder_thenReturnBuildRealmNonceIsNull() {
    // Arrange
    Builder builder = new Builder("https://example.org/example", 8080);

    Realm.Builder realm = new Realm.Builder();
    realm.setScheme(AuthScheme.BASIC);

    // Act and Assert
    Realm realm2 = builder.setRealm(realm).build().getRealm();
    assertNull(realm2.getCnonce());
    assertNull(realm2.getNonce());
    assertNull(realm2.getPassword());
    assertNull(realm2.getPrincipal());
  }

  /**
   * Test Builder {@link Builder#setRealm(Builder)} with {@code Builder}.
   *
   * <ul>
   *   <li>Then return build Realm OmitQuery.
   * </ul>
   *
   * <p>Method under test: {@link Builder#setRealm(Realm.Builder)}
   */
  @Test
  @DisplayName("Test Builder setRealm(Builder) with 'Builder'; then return build Realm OmitQuery")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"Builder Builder.setRealm(Realm.Builder)"})
  void testBuilderSetRealmWithBuilder_thenReturnBuildRealmOmitQuery() {
    // Arrange
    Builder builder = new Builder("https://example.org/example", 8080);

    Realm.Builder realm =
        new Realm.Builder("https://example.org/example", "https://example.org/example");
    realm.setOmitQuery(true);
    realm.setNonce("https://example.org/example");
    Uri uri =
        new Uri(
            "https://example.org/example",
            "https://example.org/example",
            "https://example.org/example",
            8080,
            "https://example.org/example",
            "https://example.org/example",
            "https://example.org/example");
    realm.setUri(uri);
    realm.setScheme(AuthScheme.BASIC);

    // Act and Assert
    Realm realm2 = builder.setRealm(realm).build().getRealm();
    assertEquals("ac8d21b2a82bc835e1a6e606bbfca2de", realm2.getResponse());
    assertTrue(realm2.isOmitQuery());
    assertSame(uri, realm2.getUri());
  }

  /**
   * Test {@link ProxyServer#isIgnoredForHost(String)}.
   *
   * <p>Method under test: {@link ProxyServer#isIgnoredForHost(String)}
   */
  @Test
  @DisplayName("Test isIgnoredForHost(String)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean ProxyServer.isIgnoredForHost(String)"})
  void testIsIgnoredForHost() {
    // Arrange
    Realm.Builder setAlgorithmResult =
        new Realm.Builder().setAlgorithm("https://example.org/example");

    Realm.Builder setCharsetResult = setAlgorithmResult.setCharset(Charset.forName("UTF-8"));

    Realm.Builder setServicePrincipalNameResult =
        setCharsetResult
            .setCustomLoginConfig(new HashMap<>())
            .setLoginContextName("https://example.org/example")
            .setMethodName("https://example.org/example")
            .setNc("https://example.org/example")
            .setNonce("")
            .setNtlmDomain("https://example.org/example")
            .setNtlmHost("https://example.org/example")
            .setOmitQuery(true)
            .setOpaque("https://example.org/example")
            .setQop("https://example.org/example")
            .setRealmName("https://example.org/example")
            .setResponse("https://example.org/example")
            .setScheme(AuthScheme.BASIC)
            .setServicePrincipalName("https://example.org/example");
    Uri uri =
        new Uri(
            "https://example.org/example",
            "https://example.org/example",
            "https://example.org/example",
            8080,
            "https://example.org/example",
            "https://example.org/example",
            "https://example.org/example");
    Realm realm =
        setServicePrincipalNameResult
            .setUri(uri)
            .setUseAbsoluteURI(true)
            .setUseCanonicalHostname(true)
            .setUsePreemptiveAuth(true)
            .build();

    // Act and Assert
    assertFalse(
        new ProxyServer(
                "https://example.org/example", 8080, 8080, realm, new ArrayList<>(), ProxyType.HTTP)
            .isIgnoredForHost("https://example.org/example"));
  }

  /**
   * Test {@link ProxyServer#isIgnoredForHost(String)}.
   *
   * <p>Method under test: {@link ProxyServer#isIgnoredForHost(String)}
   */
  @Test
  @DisplayName("Test isIgnoredForHost(String)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean ProxyServer.isIgnoredForHost(String)"})
  void testIsIgnoredForHost2() {
    // Arrange
    Realm.Builder setAlgorithmResult =
        new Realm.Builder().setAlgorithm("https://example.org/example");

    Realm.Builder setCharsetResult = setAlgorithmResult.setCharset(Charset.forName("UTF-8"));

    Realm.Builder setServicePrincipalNameResult =
        setCharsetResult
            .setCustomLoginConfig(new HashMap<>())
            .setLoginContextName("https://example.org/example")
            .setMethodName("https://example.org/example")
            .setNc("https://example.org/example")
            .setNonce("")
            .setNtlmDomain("https://example.org/example")
            .setNtlmHost("https://example.org/example")
            .setOmitQuery(true)
            .setOpaque("https://example.org/example")
            .setQop("https://example.org/example")
            .setRealmName("https://example.org/example")
            .setResponse("https://example.org/example")
            .setScheme(AuthScheme.BASIC)
            .setServicePrincipalName("https://example.org/example");
    Uri uri =
        new Uri(
            "https://example.org/example",
            "https://example.org/example",
            "https://example.org/example",
            8080,
            "https://example.org/example",
            "https://example.org/example",
            "https://example.org/example");
    Realm realm =
        setServicePrincipalNameResult
            .setUri(uri)
            .setUseAbsoluteURI(true)
            .setUseCanonicalHostname(true)
            .setUsePreemptiveAuth(true)
            .build();

    // Act and Assert
    assertFalse(
        new ProxyServer("https://example.org/example", 8080, 8080, realm, null, ProxyType.HTTP)
            .isIgnoredForHost("https://example.org/example"));
  }

  /**
   * Test {@link ProxyServer#isIgnoredForHost(String)}.
   *
   * <ul>
   *   <li>Given {@link ArrayList#ArrayList()} add empty string.
   *   <li>Then return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link ProxyServer#isIgnoredForHost(String)}
   */
  @Test
  @DisplayName(
      "Test isIgnoredForHost(String); given ArrayList() add empty string; then return 'false'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean ProxyServer.isIgnoredForHost(String)"})
  void testIsIgnoredForHost_givenArrayListAddEmptyString_thenReturnFalse() {
    // Arrange
    ArrayList<String> nonProxyHosts = new ArrayList<>();
    nonProxyHosts.add("");

    Realm.Builder setAlgorithmResult =
        new Realm.Builder().setAlgorithm("https://example.org/example");

    Realm.Builder setCharsetResult = setAlgorithmResult.setCharset(Charset.forName("UTF-8"));

    Realm.Builder setServicePrincipalNameResult =
        setCharsetResult
            .setCustomLoginConfig(new HashMap<>())
            .setLoginContextName("https://example.org/example")
            .setMethodName("https://example.org/example")
            .setNc("https://example.org/example")
            .setNonce("")
            .setNtlmDomain("https://example.org/example")
            .setNtlmHost("https://example.org/example")
            .setOmitQuery(true)
            .setOpaque("https://example.org/example")
            .setQop("https://example.org/example")
            .setRealmName("https://example.org/example")
            .setResponse("https://example.org/example")
            .setScheme(AuthScheme.BASIC)
            .setServicePrincipalName("https://example.org/example");
    Uri uri =
        new Uri(
            "https://example.org/example",
            "https://example.org/example",
            "https://example.org/example",
            8080,
            "https://example.org/example",
            "https://example.org/example",
            "https://example.org/example");
    Realm realm =
        setServicePrincipalNameResult
            .setUri(uri)
            .setUseAbsoluteURI(true)
            .setUseCanonicalHostname(true)
            .setUsePreemptiveAuth(true)
            .build();

    // Act and Assert
    assertFalse(
        new ProxyServer(
                "https://example.org/example", 8080, 8080, realm, nonProxyHosts, ProxyType.HTTP)
            .isIgnoredForHost("https://example.org/example"));
  }

  /**
   * Test {@link ProxyServer#isIgnoredForHost(String)}.
   *
   * <ul>
   *   <li>Given {@link ArrayList#ArrayList()} add {@code hostname}.
   *   <li>Then return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link ProxyServer#isIgnoredForHost(String)}
   */
  @Test
  @DisplayName(
      "Test isIgnoredForHost(String); given ArrayList() add 'hostname'; then return 'false'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean ProxyServer.isIgnoredForHost(String)"})
  void testIsIgnoredForHost_givenArrayListAddHostname_thenReturnFalse() {
    // Arrange
    ArrayList<String> nonProxyHosts = new ArrayList<>();
    nonProxyHosts.add("hostname");

    Realm.Builder setAlgorithmResult =
        new Realm.Builder().setAlgorithm("https://example.org/example");

    Realm.Builder setCharsetResult = setAlgorithmResult.setCharset(Charset.forName("UTF-8"));

    Realm.Builder setServicePrincipalNameResult =
        setCharsetResult
            .setCustomLoginConfig(new HashMap<>())
            .setLoginContextName("https://example.org/example")
            .setMethodName("https://example.org/example")
            .setNc("https://example.org/example")
            .setNonce("")
            .setNtlmDomain("https://example.org/example")
            .setNtlmHost("https://example.org/example")
            .setOmitQuery(true)
            .setOpaque("https://example.org/example")
            .setQop("https://example.org/example")
            .setRealmName("https://example.org/example")
            .setResponse("https://example.org/example")
            .setScheme(AuthScheme.BASIC)
            .setServicePrincipalName("https://example.org/example");
    Uri uri =
        new Uri(
            "https://example.org/example",
            "https://example.org/example",
            "https://example.org/example",
            8080,
            "https://example.org/example",
            "https://example.org/example",
            "https://example.org/example");
    Realm realm =
        setServicePrincipalNameResult
            .setUri(uri)
            .setUseAbsoluteURI(true)
            .setUseCanonicalHostname(true)
            .setUsePreemptiveAuth(true)
            .build();

    // Act and Assert
    assertFalse(
        new ProxyServer(
                "https://example.org/example", 8080, 8080, realm, nonProxyHosts, ProxyType.HTTP)
            .isIgnoredForHost("https://example.org/example"));
  }

  /**
   * Test {@link ProxyServer#isIgnoredForHost(String)}.
   *
   * <ul>
   *   <li>Given {@link Realm.Builder#Builder()} Scheme is {@code BASIC}.
   *   <li>Then return {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link ProxyServer#isIgnoredForHost(String)}
   */
  @Test
  @DisplayName(
      "Test isIgnoredForHost(String); given Builder() Scheme is 'BASIC'; then return 'true'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean ProxyServer.isIgnoredForHost(String)"})
  void testIsIgnoredForHost_givenBuilderSchemeIsBasic_thenReturnTrue() {
    // Arrange
    Realm.Builder realm = new Realm.Builder();
    realm.setScheme(AuthScheme.BASIC);

    // Act and Assert
    assertTrue(
        new Builder("https://example.org/example", 8080)
            .setCustomHeaders(mock(Function.class))
            .setNonProxyHost("https://example.org/example")
            .setProxyType(ProxyType.HTTP)
            .setRealm(realm)
            .setSecuredPort(8080)
            .build()
            .isIgnoredForHost("https://example.org/example"));
  }
}
