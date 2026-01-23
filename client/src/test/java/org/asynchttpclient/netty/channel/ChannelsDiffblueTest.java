package org.asynchttpclient.netty.channel;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.embedded.EmbeddedChannel;
import io.netty.channel.local.LocalChannel;
import io.netty.util.Attribute;
import io.netty.util.AttributeKey;
import java.util.concurrent.ExecutionException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

class ChannelsDiffblueTest {
  /**
   * Test {@link Channels#getAttribute(Channel)}.
   *
   * <ul>
   *   <li>When {@link EmbeddedChannel#EmbeddedChannel()}.
   *   <li>Then return {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link Channels#getAttribute(Channel)}
   */
  @Test
  @DisplayName("Test getAttribute(Channel); when EmbeddedChannel(); then return 'null'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"Object Channels.getAttribute(Channel)"})
  void testGetAttribute_whenEmbeddedChannel_thenReturnNull() {
    // Arrange, Act and Assert
    assertNull(Channels.getAttribute(new EmbeddedChannel()));
  }

  /**
   * Test {@link Channels#setAttribute(Channel, Object)}.
   *
   * <ul>
   *   <li>Given {@link Attribute} {@link Attribute#set(Object)} does nothing.
   *   <li>Then calls {@link Attribute#set(Object)}.
   * </ul>
   *
   * <p>Method under test: {@link Channels#setAttribute(Channel, Object)}
   */
  @Test
  @DisplayName(
      "Test setAttribute(Channel, Object); given Attribute set(Object) does nothing; then calls set(Object)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void Channels.setAttribute(Channel, Object)"})
  void testSetAttribute_givenAttributeSetDoesNothing_thenCallsSet() {
    // Arrange
    Attribute<Object> attribute = mock(Attribute.class);
    doNothing().when(attribute).set(Mockito.<Object>any());

    EmbeddedChannel channel = mock(EmbeddedChannel.class);
    when(channel.attr(Mockito.<AttributeKey<Object>>any())).thenReturn(attribute);

    // Act
    Channels.setAttribute(channel, "42");

    // Assert
    verify(attribute).set(isA(Object.class));
    verify(channel).attr(isA(AttributeKey.class));
  }

  /**
   * Test {@link Channels#setDiscard(Channel)}.
   *
   * <ul>
   *   <li>Given {@link Attribute} {@link Attribute#set(Object)} does nothing.
   *   <li>Then calls {@link Attribute#set(Object)}.
   * </ul>
   *
   * <p>Method under test: {@link Channels#setDiscard(Channel)}
   */
  @Test
  @DisplayName(
      "Test setDiscard(Channel); given Attribute set(Object) does nothing; then calls set(Object)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void Channels.setDiscard(Channel)"})
  void testSetDiscard_givenAttributeSetDoesNothing_thenCallsSet() {
    // Arrange
    Attribute<Object> attribute = mock(Attribute.class);
    doNothing().when(attribute).set(Mockito.<Object>any());

    EmbeddedChannel channel = mock(EmbeddedChannel.class);
    when(channel.attr(Mockito.<AttributeKey<Object>>any())).thenReturn(attribute);

    // Act
    Channels.setDiscard(channel);

    // Assert
    verify(attribute).set(isA(Object.class));
    verify(channel).attr(isA(AttributeKey.class));
  }

  /**
   * Test {@link Channels#isChannelActive(Channel)}.
   *
   * <ul>
   *   <li>When {@link EmbeddedChannel#EmbeddedChannel()}.
   *   <li>Then return {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link Channels#isChannelActive(Channel)}
   */
  @Test
  @DisplayName("Test isChannelActive(Channel); when EmbeddedChannel(); then return 'true'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean Channels.isChannelActive(Channel)"})
  void testIsChannelActive_whenEmbeddedChannel_thenReturnTrue() {
    // Arrange, Act and Assert
    assertTrue(Channels.isChannelActive(new EmbeddedChannel()));
  }

  /**
   * Test {@link Channels#isChannelActive(Channel)}.
   *
   * <ul>
   *   <li>When {@link LocalChannel#LocalChannel()}.
   *   <li>Then return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link Channels#isChannelActive(Channel)}
   */
  @Test
  @DisplayName("Test isChannelActive(Channel); when LocalChannel(); then return 'false'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean Channels.isChannelActive(Channel)"})
  void testIsChannelActive_whenLocalChannel_thenReturnFalse() {
    // Arrange, Act and Assert
    assertFalse(Channels.isChannelActive(new LocalChannel()));
  }

  /**
   * Test {@link Channels#isChannelActive(Channel)}.
   *
   * <ul>
   *   <li>When {@code null}.
   *   <li>Then return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link Channels#isChannelActive(Channel)}
   */
  @Test
  @DisplayName("Test isChannelActive(Channel); when 'null'; then return 'false'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean Channels.isChannelActive(Channel)"})
  void testIsChannelActive_whenNull_thenReturnFalse() {
    // Arrange, Act and Assert
    assertFalse(Channels.isChannelActive(null));
  }

  /**
   * Test {@link Channels#setActiveToken(Channel)}.
   *
   * <ul>
   *   <li>Given {@link Attribute} {@link Attribute#set(Object)} does nothing.
   *   <li>Then calls {@link Attribute#set(Object)}.
   * </ul>
   *
   * <p>Method under test: {@link Channels#setActiveToken(Channel)}
   */
  @Test
  @DisplayName(
      "Test setActiveToken(Channel); given Attribute set(Object) does nothing; then calls set(Object)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void Channels.setActiveToken(Channel)"})
  void testSetActiveToken_givenAttributeSetDoesNothing_thenCallsSet() {
    // Arrange
    Attribute<Object> attribute = mock(Attribute.class);
    doNothing().when(attribute).set(Mockito.<Object>any());

    EmbeddedChannel channel = mock(EmbeddedChannel.class);
    when(channel.attr(Mockito.<AttributeKey<Object>>any())).thenReturn(attribute);

    // Act
    Channels.setActiveToken(channel);

    // Assert
    verify(attribute).set(isA(Object.class));
    verify(channel).attr(isA(AttributeKey.class));
  }

  /**
   * Test {@link Channels#isActiveTokenSet(Channel)}.
   *
   * <ul>
   *   <li>When {@link EmbeddedChannel#EmbeddedChannel()}.
   * </ul>
   *
   * <p>Method under test: {@link Channels#isActiveTokenSet(Channel)}
   */
  @Test
  @DisplayName("Test isActiveTokenSet(Channel); when EmbeddedChannel()")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean Channels.isActiveTokenSet(Channel)"})
  void testIsActiveTokenSet_whenEmbeddedChannel() {
    // Arrange, Act and Assert
    assertFalse(Channels.isActiveTokenSet(new EmbeddedChannel()));
  }

  /**
   * Test {@link Channels#isActiveTokenSet(Channel)}.
   *
   * <ul>
   *   <li>When {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link Channels#isActiveTokenSet(Channel)}
   */
  @Test
  @DisplayName("Test isActiveTokenSet(Channel); when 'null'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean Channels.isActiveTokenSet(Channel)"})
  void testIsActiveTokenSet_whenNull() {
    // Arrange, Act and Assert
    assertFalse(Channels.isActiveTokenSet(null));
  }

  /**
   * Test {@link Channels#silentlyCloseChannel(Channel)}.
   *
   * <ul>
   *   <li>Then {@link EmbeddedChannel#EmbeddedChannel()} closeFuture is {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link Channels#silentlyCloseChannel(Channel)}
   */
  @Test
  @DisplayName("Test silentlyCloseChannel(Channel); then EmbeddedChannel() closeFuture is 'null'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void Channels.silentlyCloseChannel(Channel)"})
  void testSilentlyCloseChannel_thenEmbeddedChannelCloseFutureIsNull()
      throws InterruptedException, ExecutionException {
    // Arrange
    EmbeddedChannel channel = new EmbeddedChannel();

    // Act
    Channels.silentlyCloseChannel(channel);

    // Assert
    ChannelFuture closeFutureResult = channel.closeFuture();
    assertNull(closeFutureResult.get());
    assertFalse(channel.isRegistered());
    assertFalse(channel.isActive());
    assertFalse(channel.isOpen());
    assertTrue(closeFutureResult.isDone());
  }

  /**
   * Test {@link Channels#silentlyCloseChannel(Channel)}.
   *
   * <ul>
   *   <li>When {@link LocalChannel#LocalChannel()}.
   *   <li>Then not {@link LocalChannel#LocalChannel()} Registered.
   * </ul>
   *
   * <p>Method under test: {@link Channels#silentlyCloseChannel(Channel)}
   */
  @Test
  @DisplayName(
      "Test silentlyCloseChannel(Channel); when LocalChannel(); then not LocalChannel() Registered")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void Channels.silentlyCloseChannel(Channel)"})
  void testSilentlyCloseChannel_whenLocalChannel_thenNotLocalChannelRegistered() {
    // Arrange
    LocalChannel channel = new LocalChannel();

    // Act
    Channels.silentlyCloseChannel(channel);

    // Assert that nothing has changed
    assertFalse(channel.isRegistered());
  }
}
