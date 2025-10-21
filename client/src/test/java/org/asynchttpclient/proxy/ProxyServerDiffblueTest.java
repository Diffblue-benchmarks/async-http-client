package org.asynchttpclient.proxy;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.function.Function;
import org.asynchttpclient.Realm;
import org.asynchttpclient.Realm.AuthScheme;
import org.asynchttpclient.Realm.Builder;
import org.asynchttpclient.uri.Uri;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

class ProxyServerDiffblueTest {
  /**
   * Test Builder {@link ProxyServer.Builder#setNonProxyHost(String)}.
   * <p>
   * Method under test: {@link ProxyServer.Builder#setNonProxyHost(String)}
   */
  @Test
  @DisplayName("Test Builder setNonProxyHost(String)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"ProxyServer.Builder ProxyServer.Builder.setNonProxyHost(String)"})
  void testBuilderSetNonProxyHost() {
    // Arrange
    ProxyServer.Builder builder = new ProxyServer.Builder("https://example.org/example", 8080);

    // Act
    ProxyServer.Builder actualSetNonProxyHostResult = builder.setNonProxyHost("https://example.org/example");

    // Assert
    List<String> nonProxyHosts = builder.build().getNonProxyHosts();
    assertEquals(1, nonProxyHosts.size());
    assertEquals("https://example.org/example", nonProxyHosts.get(0));
    assertSame(builder, actualSetNonProxyHostResult);
  }

  /**
   * Test Builder {@link ProxyServer.Builder#setNonProxyHost(String)}.
   * <p>
   * Method under test: {@link ProxyServer.Builder#setNonProxyHost(String)}
   */
  @Test
  @DisplayName("Test Builder setNonProxyHost(String)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"ProxyServer.Builder ProxyServer.Builder.setNonProxyHost(String)"})
  void testBuilderSetNonProxyHost2() {
    // Arrange
    ProxyServer.Builder builder = new ProxyServer.Builder("https://example.org/example", 8080);
    builder.setNonProxyHost(null);

    // Act
    ProxyServer.Builder actualSetNonProxyHostResult = builder.setNonProxyHost("https://example.org/example");

    // Assert
    List<String> nonProxyHosts = builder.build().getNonProxyHosts();
    assertEquals(2, nonProxyHosts.size());
    assertEquals("https://example.org/example", nonProxyHosts.get(1));
    assertNull(nonProxyHosts.get(0));
    assertSame(builder, actualSetNonProxyHostResult);
  }

  /**
   * Test Builder {@link ProxyServer.Builder#setRealm(Builder)} with {@code Builder}.
   * <p>
   * Method under test: {@link ProxyServer.Builder#setRealm(Builder)}
   */
  @Test
  @DisplayName("Test Builder setRealm(Builder) with 'Builder'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"ProxyServer.Builder ProxyServer.Builder.setRealm(Builder)"})
  void testBuilderSetRealmWithBuilder() {
    // Arrange
    ProxyServer.Builder builder = new ProxyServer.Builder("https://example.org/example", 8080);

    Builder realm = new Builder();
    realm.setScheme(AuthScheme.BASIC);

    // Act and Assert
    assertSame(builder, builder.setRealm(realm));
  }

  /**
   * Test {@link ProxyServer#isIgnoredForHost(String)}.
   * <ul>
   *   <li>Given {@link ArrayList#ArrayList()} add empty string.</li>
   *   <li>Then return {@code false}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ProxyServer#isIgnoredForHost(String)}
   */
  @Test
  @DisplayName("Test isIgnoredForHost(String); given ArrayList() add empty string; then return 'false'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean ProxyServer.isIgnoredForHost(String)"})
  void testIsIgnoredForHost_givenArrayListAddEmptyString_thenReturnFalse() {
    // Arrange
    ArrayList<String> nonProxyHosts = new ArrayList<>();
    nonProxyHosts.add("");
    Builder setAlgorithmResult = (new Builder()).setAlgorithm("https://example.org/example");
    Builder setCharsetResult = setAlgorithmResult.setCharset(Charset.forName("UTF-8"));
    Builder setServicePrincipalNameResult = setCharsetResult.setCustomLoginConfig(new HashMap<>())
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
    Realm realm = setServicePrincipalNameResult
        .setUri(new Uri("https://example.org/example", "https://example.org/example", "https://example.org/example",
            8080, "https://example.org/example", "https://example.org/example", "https://example.org/example"))
        .setUseAbsoluteURI(true)
        .setUseCanonicalHostname(true)
        .setUsePreemptiveAuth(true)
        .build();

    // Act and Assert
    assertFalse((new ProxyServer("https://example.org/example", 8080, 8080, realm, nonProxyHosts, ProxyType.HTTP))
        .isIgnoredForHost("https://example.org/example"));
  }

  /**
   * Test {@link ProxyServer#isIgnoredForHost(String)}.
   * <ul>
   *   <li>Given {@link ArrayList#ArrayList()} add {@code foo}.</li>
   *   <li>Then return {@code false}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ProxyServer#isIgnoredForHost(String)}
   */
  @Test
  @DisplayName("Test isIgnoredForHost(String); given ArrayList() add 'foo'; then return 'false'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean ProxyServer.isIgnoredForHost(String)"})
  void testIsIgnoredForHost_givenArrayListAddFoo_thenReturnFalse() {
    // Arrange
    ArrayList<String> nonProxyHosts = new ArrayList<>();
    nonProxyHosts.add("foo");
    Builder setAlgorithmResult = (new Builder()).setAlgorithm("https://example.org/example");
    Builder setCharsetResult = setAlgorithmResult.setCharset(Charset.forName("UTF-8"));
    Builder setServicePrincipalNameResult = setCharsetResult.setCustomLoginConfig(new HashMap<>())
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
    Realm realm = setServicePrincipalNameResult
        .setUri(new Uri("https://example.org/example", "https://example.org/example", "https://example.org/example",
            8080, "https://example.org/example", "https://example.org/example", "https://example.org/example"))
        .setUseAbsoluteURI(true)
        .setUseCanonicalHostname(true)
        .setUsePreemptiveAuth(true)
        .build();

    // Act and Assert
    assertFalse((new ProxyServer("https://example.org/example", 8080, 8080, realm, nonProxyHosts, ProxyType.HTTP))
        .isIgnoredForHost("https://example.org/example"));
  }

  /**
   * Test {@link ProxyServer#isIgnoredForHost(String)}.
   * <ul>
   *   <li>Given {@link Builder#Builder()} Scheme is {@code BASIC}.</li>
   *   <li>Then return {@code true}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ProxyServer#isIgnoredForHost(String)}
   */
  @Test
  @DisplayName("Test isIgnoredForHost(String); given Builder() Scheme is 'BASIC'; then return 'true'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean ProxyServer.isIgnoredForHost(String)"})
  void testIsIgnoredForHost_givenBuilderSchemeIsBasic_thenReturnTrue() {
    // Arrange
    Builder realm = new Builder();
    realm.setScheme(AuthScheme.BASIC);
    ProxyServer buildResult = (new ProxyServer.Builder("https://example.org/example", 8080))
        .setCustomHeaders(mock(Function.class))
        .setNonProxyHost("https://example.org/example")
        .setProxyType(ProxyType.HTTP)
        .setRealm(realm)
        .setSecuredPort(8080)
        .build();

    // Act and Assert
    assertTrue(buildResult.isIgnoredForHost("https://example.org/example"));
  }

  /**
   * Test {@link ProxyServer#isIgnoredForHost(String)}.
   * <ul>
   *   <li>Then return {@code false}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ProxyServer#isIgnoredForHost(String)}
   */
  @Test
  @DisplayName("Test isIgnoredForHost(String); then return 'false'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean ProxyServer.isIgnoredForHost(String)"})
  void testIsIgnoredForHost_thenReturnFalse() {
    // Arrange
    Builder setAlgorithmResult = (new Builder()).setAlgorithm("https://example.org/example");
    Builder setCharsetResult = setAlgorithmResult.setCharset(Charset.forName("UTF-8"));
    Builder setServicePrincipalNameResult = setCharsetResult.setCustomLoginConfig(new HashMap<>())
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
    Realm realm = setServicePrincipalNameResult
        .setUri(new Uri("https://example.org/example", "https://example.org/example", "https://example.org/example",
            8080, "https://example.org/example", "https://example.org/example", "https://example.org/example"))
        .setUseAbsoluteURI(true)
        .setUseCanonicalHostname(true)
        .setUsePreemptiveAuth(true)
        .build();

    // Act and Assert
    assertFalse((new ProxyServer("https://example.org/example", 8080, 8080, realm, new ArrayList<>(), ProxyType.HTTP))
        .isIgnoredForHost("https://example.org/example"));
  }
}
