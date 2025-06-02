package org.asynchttpclient.channel;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;
import org.asynchttpclient.Realm;
import org.asynchttpclient.Realm.AuthScheme;
import org.asynchttpclient.Realm.Builder;
import org.asynchttpclient.channel.ChannelPoolPartitioning.CompositePartitionKey;
import org.asynchttpclient.channel.ChannelPoolPartitioning.PerHostChannelPoolPartitioning;
import org.asynchttpclient.proxy.ProxyServer;
import org.asynchttpclient.proxy.ProxyType;
import org.asynchttpclient.uri.Uri;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

class ChannelPoolPartitioningDiffblueTest {
  /**
   * Test CompositePartitionKey {@link CompositePartitionKey#equals(Object)}, and {@link CompositePartitionKey#hashCode()}.
   * <ul>
   *   <li>When other is equal.</li>
   *   <li>Then return equal.</li>
   * </ul>
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link CompositePartitionKey#equals(Object)}
   *   <li>{@link CompositePartitionKey#hashCode()}
   * </ul>
   */
  @Test
  @DisplayName("Test CompositePartitionKey equals(Object), and hashCode(); when other is equal; then return equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean CompositePartitionKey.equals(Object)", "int CompositePartitionKey.hashCode()"})
  void testCompositePartitionKeyEqualsAndHashCode_whenOtherIsEqual_thenReturnEqual() {
    // Arrange
    CompositePartitionKey compositePartitionKey = new CompositePartitionKey("https://example.org/example",
        "https://example.org/example", "https://example.org/example", 8080, ProxyType.HTTP);
    CompositePartitionKey compositePartitionKey2 = new CompositePartitionKey("https://example.org/example",
        "https://example.org/example", "https://example.org/example", 8080, ProxyType.HTTP);

    // Act and Assert
    assertEquals(compositePartitionKey, compositePartitionKey2);
    int expectedHashCodeResult = compositePartitionKey.hashCode();
    assertEquals(expectedHashCodeResult, compositePartitionKey2.hashCode());
  }

  /**
   * Test CompositePartitionKey {@link CompositePartitionKey#equals(Object)}, and {@link CompositePartitionKey#hashCode()}.
   * <ul>
   *   <li>When other is same.</li>
   *   <li>Then return equal.</li>
   * </ul>
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link CompositePartitionKey#equals(Object)}
   *   <li>{@link CompositePartitionKey#hashCode()}
   * </ul>
   */
  @Test
  @DisplayName("Test CompositePartitionKey equals(Object), and hashCode(); when other is same; then return equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean CompositePartitionKey.equals(Object)", "int CompositePartitionKey.hashCode()"})
  void testCompositePartitionKeyEqualsAndHashCode_whenOtherIsSame_thenReturnEqual() {
    // Arrange
    CompositePartitionKey compositePartitionKey = new CompositePartitionKey("https://example.org/example",
        "https://example.org/example", "https://example.org/example", 8080, ProxyType.HTTP);

    // Act and Assert
    assertEquals(compositePartitionKey, compositePartitionKey);
    int expectedHashCodeResult = compositePartitionKey.hashCode();
    assertEquals(expectedHashCodeResult, compositePartitionKey.hashCode());
  }

  /**
   * Test CompositePartitionKey {@link CompositePartitionKey#equals(Object)}.
   * <ul>
   *   <li>When other is different.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link CompositePartitionKey#equals(Object)}
   */
  @Test
  @DisplayName("Test CompositePartitionKey equals(Object); when other is different; then return not equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean CompositePartitionKey.equals(Object)", "int CompositePartitionKey.hashCode()"})
  void testCompositePartitionKeyEquals_whenOtherIsDifferent_thenReturnNotEqual() {
    // Arrange
    CompositePartitionKey compositePartitionKey = new CompositePartitionKey("Target Host Base Url",
        "https://example.org/example", "https://example.org/example", 8080, ProxyType.HTTP);

    // Act and Assert
    assertNotEquals(compositePartitionKey, new CompositePartitionKey("https://example.org/example",
        "https://example.org/example", "https://example.org/example", 8080, ProxyType.HTTP));
  }

  /**
   * Test CompositePartitionKey {@link CompositePartitionKey#equals(Object)}.
   * <ul>
   *   <li>When other is different.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link CompositePartitionKey#equals(Object)}
   */
  @Test
  @DisplayName("Test CompositePartitionKey equals(Object); when other is different; then return not equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean CompositePartitionKey.equals(Object)", "int CompositePartitionKey.hashCode()"})
  void testCompositePartitionKeyEquals_whenOtherIsDifferent_thenReturnNotEqual2() {
    // Arrange
    CompositePartitionKey compositePartitionKey = new CompositePartitionKey("https://example.org/example", "localhost",
        "https://example.org/example", 8080, ProxyType.HTTP);

    // Act and Assert
    assertNotEquals(compositePartitionKey, new CompositePartitionKey("https://example.org/example",
        "https://example.org/example", "https://example.org/example", 8080, ProxyType.HTTP));
  }

  /**
   * Test CompositePartitionKey {@link CompositePartitionKey#equals(Object)}.
   * <ul>
   *   <li>When other is different.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link CompositePartitionKey#equals(Object)}
   */
  @Test
  @DisplayName("Test CompositePartitionKey equals(Object); when other is different; then return not equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean CompositePartitionKey.equals(Object)", "int CompositePartitionKey.hashCode()"})
  void testCompositePartitionKeyEquals_whenOtherIsDifferent_thenReturnNotEqual3() {
    // Arrange
    CompositePartitionKey compositePartitionKey = new CompositePartitionKey("https://example.org/example",
        "https://example.org/example", "localhost", 8080, ProxyType.HTTP);

    // Act and Assert
    assertNotEquals(compositePartitionKey, new CompositePartitionKey("https://example.org/example",
        "https://example.org/example", "https://example.org/example", 8080, ProxyType.HTTP));
  }

  /**
   * Test CompositePartitionKey {@link CompositePartitionKey#equals(Object)}.
   * <ul>
   *   <li>When other is different.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link CompositePartitionKey#equals(Object)}
   */
  @Test
  @DisplayName("Test CompositePartitionKey equals(Object); when other is different; then return not equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean CompositePartitionKey.equals(Object)", "int CompositePartitionKey.hashCode()"})
  void testCompositePartitionKeyEquals_whenOtherIsDifferent_thenReturnNotEqual4() {
    // Arrange
    CompositePartitionKey compositePartitionKey = new CompositePartitionKey("https://example.org/example",
        "https://example.org/example", "https://example.org/example", 1, ProxyType.HTTP);

    // Act and Assert
    assertNotEquals(compositePartitionKey, new CompositePartitionKey("https://example.org/example",
        "https://example.org/example", "https://example.org/example", 8080, ProxyType.HTTP));
  }

  /**
   * Test CompositePartitionKey {@link CompositePartitionKey#equals(Object)}.
   * <ul>
   *   <li>When other is different.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link CompositePartitionKey#equals(Object)}
   */
  @Test
  @DisplayName("Test CompositePartitionKey equals(Object); when other is different; then return not equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean CompositePartitionKey.equals(Object)", "int CompositePartitionKey.hashCode()"})
  void testCompositePartitionKeyEquals_whenOtherIsDifferent_thenReturnNotEqual5() {
    // Arrange
    CompositePartitionKey compositePartitionKey = new CompositePartitionKey("https://example.org/example",
        "https://example.org/example", "https://example.org/example", 8080, null);

    // Act and Assert
    assertNotEquals(compositePartitionKey, new CompositePartitionKey("https://example.org/example",
        "https://example.org/example", "https://example.org/example", 8080, ProxyType.HTTP));
  }

  /**
   * Test CompositePartitionKey {@link CompositePartitionKey#equals(Object)}.
   * <ul>
   *   <li>When other is {@code null}.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link CompositePartitionKey#equals(Object)}
   */
  @Test
  @DisplayName("Test CompositePartitionKey equals(Object); when other is 'null'; then return not equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean CompositePartitionKey.equals(Object)", "int CompositePartitionKey.hashCode()"})
  void testCompositePartitionKeyEquals_whenOtherIsNull_thenReturnNotEqual() {
    // Arrange, Act and Assert
    assertNotEquals(new CompositePartitionKey("https://example.org/example", "https://example.org/example",
        "https://example.org/example", 8080, ProxyType.HTTP), null);
  }

  /**
   * Test CompositePartitionKey {@link CompositePartitionKey#equals(Object)}.
   * <ul>
   *   <li>When other is wrong type.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link CompositePartitionKey#equals(Object)}
   */
  @Test
  @DisplayName("Test CompositePartitionKey equals(Object); when other is wrong type; then return not equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean CompositePartitionKey.equals(Object)", "int CompositePartitionKey.hashCode()"})
  void testCompositePartitionKeyEquals_whenOtherIsWrongType_thenReturnNotEqual() {
    // Arrange, Act and Assert
    assertNotEquals(new CompositePartitionKey("https://example.org/example", "https://example.org/example",
        "https://example.org/example", 8080, ProxyType.HTTP), "Different type to CompositePartitionKey");
  }

  /**
   * Test CompositePartitionKey getters and setters.
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link CompositePartitionKey#CompositePartitionKey(String, String, String, int, ProxyType)}
   *   <li>{@link CompositePartitionKey#toString()}
   * </ul>
   */
  @Test
  @DisplayName("Test CompositePartitionKey getters and setters")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void CompositePartitionKey.<init>(String, String, String, int, ProxyType)",
      "String CompositePartitionKey.toString()"})
  void testCompositePartitionKeyGettersAndSetters() {
    // Arrange, Act and Assert
    assertEquals(
        "CompositePartitionKey(targetHostBaseUrl=https://example.org/example, virtualHost=https://example.org/example,"
            + " proxyHost=https://example.org/example, proxyPort=8080, proxyType=HTTP",
        (new CompositePartitionKey("https://example.org/example", "https://example.org/example",
            "https://example.org/example", 8080, ProxyType.HTTP)).toString());
  }

  /**
   * Test PerHostChannelPoolPartitioning {@link PerHostChannelPoolPartitioning#getPartitionKey(Uri, String, ProxyServer)}.
   * <p>
   * Method under test: {@link PerHostChannelPoolPartitioning#getPartitionKey(Uri, String, ProxyServer)}
   */
  @Test
  @DisplayName("Test PerHostChannelPoolPartitioning getPartitionKey(Uri, String, ProxyServer)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Object PerHostChannelPoolPartitioning.getPartitionKey(Uri, String, ProxyServer)"})
  void testPerHostChannelPoolPartitioningGetPartitionKey() {
    // Arrange
    Uri uri = new Uri("https://example.org/example", "https://example.org/example", "https://example.org/example", 8080,
        "https://example.org/example", "https://example.org/example", "https://example.org/example");

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
    assertTrue(PerHostChannelPoolPartitioning.INSTANCE.getPartitionKey(uri, "https://example.org/example",
        new ProxyServer("https://example.org/example", 8080, 8080, realm, new ArrayList<>(),
            ProxyType.HTTP)) instanceof CompositePartitionKey);
  }

  /**
   * Test PerHostChannelPoolPartitioning {@link PerHostChannelPoolPartitioning#getPartitionKey(Uri, String, ProxyServer)}.
   * <p>
   * Method under test: {@link PerHostChannelPoolPartitioning#getPartitionKey(Uri, String, ProxyServer)}
   */
  @Test
  @DisplayName("Test PerHostChannelPoolPartitioning getPartitionKey(Uri, String, ProxyServer)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Object PerHostChannelPoolPartitioning.getPartitionKey(Uri, String, ProxyServer)"})
  void testPerHostChannelPoolPartitioningGetPartitionKey2() {
    // Arrange
    Uri uri = new Uri(Uri.HTTPS, "https://example.org/example", "https://example.org/example", 8080,
        "https://example.org/example", "https://example.org/example", "https://example.org/example");

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
    assertTrue(PerHostChannelPoolPartitioning.INSTANCE.getPartitionKey(uri, "https://example.org/example",
        new ProxyServer("https://example.org/example", 8080, 8080, realm, new ArrayList<>(),
            ProxyType.HTTP)) instanceof CompositePartitionKey);
  }

  /**
   * Test PerHostChannelPoolPartitioning {@link PerHostChannelPoolPartitioning#getPartitionKey(Uri, String, ProxyServer)}.
   * <ul>
   *   <li>When {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link PerHostChannelPoolPartitioning#getPartitionKey(Uri, String, ProxyServer)}
   */
  @Test
  @DisplayName("Test PerHostChannelPoolPartitioning getPartitionKey(Uri, String, ProxyServer); when 'null'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Object PerHostChannelPoolPartitioning.getPartitionKey(Uri, String, ProxyServer)"})
  void testPerHostChannelPoolPartitioningGetPartitionKey_whenNull() {
    // Arrange, Act and Assert
    assertTrue(PerHostChannelPoolPartitioning.INSTANCE.getPartitionKey(
        new Uri("https://example.org/example", "https://example.org/example", "https://example.org/example", 8080,
            "https://example.org/example", "https://example.org/example", "https://example.org/example"),
        "https://example.org/example", null) instanceof CompositePartitionKey);
  }
}
