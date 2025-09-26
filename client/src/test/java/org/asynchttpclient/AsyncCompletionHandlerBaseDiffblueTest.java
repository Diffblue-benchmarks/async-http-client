package org.asynchttpclient;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.asynchttpclient.AsyncHandler.State;
import org.asynchttpclient.Response.ResponseBuilder;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

class AsyncCompletionHandlerBaseDiffblueTest {
  /**
   * Test {@link AsyncCompletionHandlerBase#onCompleted(Response)} with {@code Response}.
   *
   * <ul>
   *   <li>When {@link ResponseBuilder} (default constructor) build.
   *   <li>Then return {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link AsyncCompletionHandlerBase#onCompleted(Response)}
   */
  @Test
  @DisplayName(
      "Test onCompleted(Response) with 'Response'; when ResponseBuilder (default constructor) build; then return 'null'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"Response AsyncCompletionHandlerBase.onCompleted(Response)"})
  void testOnCompletedWithResponse_whenResponseBuilderBuild_thenReturnNull() throws Exception {
    // Arrange
    AsyncCompletionHandlerBase asyncCompletionHandlerBase = new AsyncCompletionHandlerBase();

    // Act
    Response actualOnCompletedResult =
        asyncCompletionHandlerBase.onCompleted(new ResponseBuilder().build());

    // Assert
    assertNull(actualOnCompletedResult);
  }

  /**
   * Test new {@link AsyncCompletionHandlerBase} (default constructor).
   *
   * <p>Method under test: default or parameterless constructor of {@link
   * AsyncCompletionHandlerBase}
   */
  @Test
  @DisplayName("Test new AsyncCompletionHandlerBase (default constructor)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void AsyncCompletionHandlerBase.<init>()"})
  void testNewAsyncCompletionHandlerBase() throws Exception {
    // Arrange and Act
    AsyncCompletionHandlerBase actualAsyncCompletionHandlerBase = new AsyncCompletionHandlerBase();

    // Assert
    assertNull(actualAsyncCompletionHandlerBase.onCompleted());
    assertEquals(State.CONTINUE, actualAsyncCompletionHandlerBase.onContentWritten());
    assertEquals(State.CONTINUE, actualAsyncCompletionHandlerBase.onHeadersWritten());
  }
}
