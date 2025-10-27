package org.asynchttpclient.channel;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import java.util.ArrayList;
import org.asynchttpclient.Realm;
import org.asynchttpclient.proxy.ProxyServer;
import org.asynchttpclient.proxy.ProxyType;
import org.asynchttpclient.uri.Uri;
import org.junit.jupiter.api.Test;

class ChannelPoolPartitioningDiffblueTest {
  /**
   * Methods under test:
   * <ul>
   *   <li>{@link ChannelPoolPartitioning.CompositePartitionKey#equals(Object)}
   *   <li>{@link ChannelPoolPartitioning.CompositePartitionKey#hashCode()}
   * </ul>
   */
  @Test
  void testCompositePartitionKeyEqualsAndHashCode_whenOtherIsEqual_thenReturnEqual() {
    // Arrange
    ChannelPoolPartitioning.CompositePartitionKey compositePartitionKey = new ChannelPoolPartitioning.CompositePartitionKey(
        "https://example.org/example", "https://example.org/example", "https://example.org/example", 8080,
        ProxyType.HTTP);
    ChannelPoolPartitioning.CompositePartitionKey compositePartitionKey2 = new ChannelPoolPartitioning.CompositePartitionKey(
        "https://example.org/example", "https://example.org/example", "https://example.org/example", 8080,
        ProxyType.HTTP);

    // Act and Assert
    assertEquals(compositePartitionKey, compositePartitionKey2);
    int expectedHashCodeResult = compositePartitionKey.hashCode();
    assertEquals(expectedHashCodeResult, compositePartitionKey2.hashCode());
  }

  /**
   * Methods under test:
   * <ul>
   *   <li>{@link ChannelPoolPartitioning.CompositePartitionKey#equals(Object)}
   *   <li>{@link ChannelPoolPartitioning.CompositePartitionKey#hashCode()}
   * </ul>
   */
  @Test
  void testCompositePartitionKeyEqualsAndHashCode_whenOtherIsSame_thenReturnEqual() {
    // Arrange
    ChannelPoolPartitioning.CompositePartitionKey compositePartitionKey = new ChannelPoolPartitioning.CompositePartitionKey(
        "https://example.org/example", "https://example.org/example", "https://example.org/example", 8080,
        ProxyType.HTTP);

    // Act and Assert
    assertEquals(compositePartitionKey, compositePartitionKey);
    int expectedHashCodeResult = compositePartitionKey.hashCode();
    assertEquals(expectedHashCodeResult, compositePartitionKey.hashCode());
  }

  /**
   * Method under test:
   * {@link ChannelPoolPartitioning.CompositePartitionKey#equals(Object)}
   */
  @Test
  void testCompositePartitionKeyEquals_whenOtherIsDifferent_thenReturnNotEqual() {
    // Arrange
    ChannelPoolPartitioning.CompositePartitionKey compositePartitionKey = new ChannelPoolPartitioning.CompositePartitionKey(
        "Target Host Base Url", "https://example.org/example", "https://example.org/example", 8080, ProxyType.HTTP);

    // Act and Assert
    assertNotEquals(compositePartitionKey,
        new ChannelPoolPartitioning.CompositePartitionKey("https://example.org/example", "https://example.org/example",
            "https://example.org/example", 8080, ProxyType.HTTP));
  }

  /**
   * Method under test:
   * {@link ChannelPoolPartitioning.CompositePartitionKey#equals(Object)}
   */
  @Test
  void testCompositePartitionKeyEquals_whenOtherIsDifferent_thenReturnNotEqual2() {
    // Arrange
    ChannelPoolPartitioning.CompositePartitionKey compositePartitionKey = new ChannelPoolPartitioning.CompositePartitionKey(
        "https://example.org/example", "localhost", "https://example.org/example", 8080, ProxyType.HTTP);

    // Act and Assert
    assertNotEquals(compositePartitionKey,
        new ChannelPoolPartitioning.CompositePartitionKey("https://example.org/example", "https://example.org/example",
            "https://example.org/example", 8080, ProxyType.HTTP));
  }

  /**
   * Method under test:
   * {@link ChannelPoolPartitioning.CompositePartitionKey#equals(Object)}
   */
  @Test
  void testCompositePartitionKeyEquals_whenOtherIsDifferent_thenReturnNotEqual3() {
    // Arrange
    ChannelPoolPartitioning.CompositePartitionKey compositePartitionKey = new ChannelPoolPartitioning.CompositePartitionKey(
        "https://example.org/example", "https://example.org/example", "localhost", 8080, ProxyType.HTTP);

    // Act and Assert
    assertNotEquals(compositePartitionKey,
        new ChannelPoolPartitioning.CompositePartitionKey("https://example.org/example", "https://example.org/example",
            "https://example.org/example", 8080, ProxyType.HTTP));
  }

  /**
   * Method under test:
   * {@link ChannelPoolPartitioning.CompositePartitionKey#equals(Object)}
   */
  @Test
  void testCompositePartitionKeyEquals_whenOtherIsDifferent_thenReturnNotEqual4() {
    // Arrange
    ChannelPoolPartitioning.CompositePartitionKey compositePartitionKey = new ChannelPoolPartitioning.CompositePartitionKey(
        "https://example.org/example", "https://example.org/example", "https://example.org/example", 1, ProxyType.HTTP);

    // Act and Assert
    assertNotEquals(compositePartitionKey,
        new ChannelPoolPartitioning.CompositePartitionKey("https://example.org/example", "https://example.org/example",
            "https://example.org/example", 8080, ProxyType.HTTP));
  }

  /**
   * Method under test:
   * {@link ChannelPoolPartitioning.CompositePartitionKey#equals(Object)}
   */
  @Test
  void testCompositePartitionKeyEquals_whenOtherIsDifferent_thenReturnNotEqual5() {
    // Arrange
    ChannelPoolPartitioning.CompositePartitionKey compositePartitionKey = new ChannelPoolPartitioning.CompositePartitionKey(
        "https://example.org/example", "https://example.org/example", "https://example.org/example", 8080, null);

    // Act and Assert
    assertNotEquals(compositePartitionKey,
        new ChannelPoolPartitioning.CompositePartitionKey("https://example.org/example", "https://example.org/example",
            "https://example.org/example", 8080, ProxyType.HTTP));
  }

  /**
   * Method under test:
   * {@link ChannelPoolPartitioning.CompositePartitionKey#equals(Object)}
   */
  @Test
  void testCompositePartitionKeyEquals_whenOtherIsNull_thenReturnNotEqual() {
    // Arrange, Act and Assert
    assertNotEquals(new ChannelPoolPartitioning.CompositePartitionKey("https://example.org/example",
        "https://example.org/example", "https://example.org/example", 8080, ProxyType.HTTP), null);
  }

  /**
   * Method under test:
   * {@link ChannelPoolPartitioning.CompositePartitionKey#equals(Object)}
   */
  @Test
  void testCompositePartitionKeyEquals_whenOtherIsWrongType_thenReturnNotEqual() {
    // Arrange, Act and Assert
    assertNotEquals(new ChannelPoolPartitioning.CompositePartitionKey("https://example.org/example",
        "https://example.org/example", "https://example.org/example", 8080, ProxyType.HTTP),
        "Different type to CompositePartitionKey");
  }

  /**
   * Methods under test:
   * <ul>
   *   <li>
   * {@link ChannelPoolPartitioning.CompositePartitionKey#CompositePartitionKey(String, String, String, int, ProxyType)}
   *   <li>{@link ChannelPoolPartitioning.CompositePartitionKey#toString()}
   * </ul>
   */
  @Test
  void testCompositePartitionKeyGettersAndSetters() {
    // Arrange, Act and Assert
    assertEquals(
        "CompositePartitionKey(targetHostBaseUrl=https://example.org/example, virtualHost=https://example.org/example,"
            + " proxyHost=https://example.org/example, proxyPort=8080, proxyType=HTTP",
        (new ChannelPoolPartitioning.CompositePartitionKey("https://example.org/example", "https://example.org/example",
            "https://example.org/example", 8080, ProxyType.HTTP)).toString());
  }

  /**
   * Method under test:
   * {@link ChannelPoolPartitioning.PerHostChannelPoolPartitioning#getPartitionKey(Uri, String, ProxyServer)}
   */
  @Test
  void testPerHostChannelPoolPartitioningGetPartitionKey() {
    // Arrange
    Uri uri = new Uri("https://example.org/example", "https://example.org/example", "https://example.org/example", 8080,
        "https://example.org/example", "https://example.org/example", "https://example.org/example");

    Realm realm = mock(Realm.class);

    // Act and Assert
    assertTrue(ChannelPoolPartitioning.PerHostChannelPoolPartitioning.INSTANCE.getPartitionKey(uri,
        "https://example.org/example", new ProxyServer("https://example.org/example", 8080, 8080, realm,
            new ArrayList<>(), ProxyType.HTTP)) instanceof ChannelPoolPartitioning.CompositePartitionKey);
  }

  /**
   * Method under test:
   * {@link ChannelPoolPartitioning.PerHostChannelPoolPartitioning#getPartitionKey(Uri, String, ProxyServer)}
   */
  @Test
  void testPerHostChannelPoolPartitioningGetPartitionKey2() {
    // Arrange
    Uri uri = new Uri(Uri.HTTPS, "https://example.org/example", "https://example.org/example", 8080,
        "https://example.org/example", "https://example.org/example", "https://example.org/example");

    Realm realm = mock(Realm.class);

    // Act and Assert
    assertTrue(ChannelPoolPartitioning.PerHostChannelPoolPartitioning.INSTANCE.getPartitionKey(uri,
        "https://example.org/example", new ProxyServer("https://example.org/example", 8080, 8080, realm,
            new ArrayList<>(), ProxyType.HTTP)) instanceof ChannelPoolPartitioning.CompositePartitionKey);
  }

  /**
   * Method under test:
   * {@link ChannelPoolPartitioning.PerHostChannelPoolPartitioning#getPartitionKey(Uri, String, ProxyServer)}
   */
  @Test
  void testPerHostChannelPoolPartitioningGetPartitionKey3() {
    // Arrange, Act and Assert
    assertTrue(ChannelPoolPartitioning.PerHostChannelPoolPartitioning.INSTANCE.getPartitionKey(
        new Uri("https://example.org/example", "https://example.org/example", "https://example.org/example", 8080,
            "https://example.org/example", "https://example.org/example", "https://example.org/example"),
        "https://example.org/example", null) instanceof ChannelPoolPartitioning.CompositePartitionKey);
  }

  /**
   * Method under test:
   * {@link ChannelPoolPartitioning.PerHostChannelPoolPartitioning#getPartitionKey(Uri, String, ProxyServer)}
   */
  @Test
  void testPerHostChannelPoolPartitioningGetPartitionKey4() {
    // Arrange
    Uri uri = new Uri(Uri.HTTPS, "https://example.org/example", "https://example.org/example", 8080,
        "https://example.org/example", "https://example.org/example", "https://example.org/example");

    Realm realm = mock(Realm.class);

    // Act and Assert
    assertTrue(ChannelPoolPartitioning.PerHostChannelPoolPartitioning.INSTANCE.getPartitionKey(uri,
        "https://example.org/example", new ProxyServer("https://example.org/example", 8080, 8080, realm,
            new ArrayList<>(), null)) instanceof ChannelPoolPartitioning.CompositePartitionKey);
  }

  /**
   * Method under test:
   * {@link ChannelPoolPartitioning.PerHostChannelPoolPartitioning#getPartitionKey(Uri, String, ProxyServer)}
   */
  @Test
  void testPerHostChannelPoolPartitioningGetPartitionKey5() {
    // Arrange, Act and Assert
    assertEquals("https://example.org/example://https://example.org/example:8080",
        ChannelPoolPartitioning.PerHostChannelPoolPartitioning.INSTANCE.getPartitionKey(
            new Uri("https://example.org/example", "https://example.org/example", "https://example.org/example", 8080,
                "https://example.org/example", "https://example.org/example", "https://example.org/example"),
            null, null));
  }
}
