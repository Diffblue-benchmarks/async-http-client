package org.asynchttpclient.netty.channel;

import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import io.netty.channel.Channel;
import io.netty.channel.embedded.EmbeddedChannel;
import io.netty.util.Attribute;
import io.netty.util.AttributeKey;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

class ChannelsDiffblueTest {
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
}
