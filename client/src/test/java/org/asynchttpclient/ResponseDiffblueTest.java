package org.asynchttpclient;

import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.asynchttpclient.Response.ResponseBuilder;
import org.asynchttpclient.netty.EagerResponseBodyPart;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

class ResponseDiffblueTest {
  /**
   * Test ResponseBuilder {@link ResponseBuilder#accumulate(HttpResponseBodyPart)} with {@code
   * bodyPart}.
   *
   * <ul>
   *   <li>Given three.
   *   <li>Then calls {@link EagerResponseBodyPart#length()}.
   * </ul>
   *
   * <p>Method under test: {@link ResponseBuilder#accumulate(HttpResponseBodyPart)}
   */
  @Test
  @DisplayName(
      "Test ResponseBuilder accumulate(HttpResponseBodyPart) with 'bodyPart'; given three; then calls length()")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void ResponseBuilder.accumulate(HttpResponseBodyPart)"})
  void testResponseBuilderAccumulateWithBodyPart_givenThree_thenCallsLength() {
    // Arrange
    ResponseBuilder responseBuilder = new ResponseBuilder();

    EagerResponseBodyPart bodyPart = mock(EagerResponseBodyPart.class);
    when(bodyPart.length()).thenReturn(3);

    // Act
    responseBuilder.accumulate(bodyPart);

    // Assert
    verify(bodyPart).length();
  }

  /**
   * Test ResponseBuilder {@link ResponseBuilder#build()}.
   *
   * <p>Method under test: {@link ResponseBuilder#build()}
   */
  @Test
  @DisplayName("Test ResponseBuilder build()")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void ResponseBuilder.accumulate(org.asynchttpclient.HttpResponseStatus)",
    "Response ResponseBuilder.build()"
  })
  void testResponseBuilderBuild() {
    // Arrange and Act
    Response actualResponse = new ResponseBuilder().build();

    // Assert
    assertNull(actualResponse);
  }

  /**
   * Test ResponseBuilder new {@link ResponseBuilder} (default constructor).
   *
   * <p>Method under test: default or parameterless constructor of {@link ResponseBuilder}
   */
  @Test
  @DisplayName("Test ResponseBuilder new ResponseBuilder (default constructor)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void ResponseBuilder.<init>()"})
  void testResponseBuilderNewResponseBuilder() {
    // Arrange, Act and Assert
    Response actualResponse = new ResponseBuilder().build();
    assertNull(actualResponse);
  }
}
