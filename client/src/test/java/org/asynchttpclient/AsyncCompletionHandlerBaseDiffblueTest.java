package org.asynchttpclient;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import org.asynchttpclient.Response.ResponseBuilder;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class AsyncCompletionHandlerBaseDiffblueTest {
  /**
   * Test {@link AsyncCompletionHandlerBase#onCompleted(Response)} with
   * {@code Response}.
   * <ul>
   *   <li>When {@link ResponseBuilder} (default constructor) build.</li>
   *   <li>Then return {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link AsyncCompletionHandlerBase#onCompleted(Response)}
   */
  @Test
  @DisplayName("Test onCompleted(Response) with 'Response'; when ResponseBuilder (default constructor) build; then return 'null'")
  void testOnCompletedWithResponse_whenResponseBuilderBuild_thenReturnNull() throws Exception {
    // Arrange
    AsyncCompletionHandlerBase asyncCompletionHandlerBase = new AsyncCompletionHandlerBase();
    Response response = (new Response.ResponseBuilder()).build();

    // Act and Assert
    assertNull(asyncCompletionHandlerBase.onCompleted(response));
  }

  /**
   * Test new {@link AsyncCompletionHandlerBase} (default constructor).
   * <p>
   * Method under test: default or parameterless constructor of
   * {@link AsyncCompletionHandlerBase}
   */
  @Test
  @DisplayName("Test new AsyncCompletionHandlerBase (default constructor)")
  void testNewAsyncCompletionHandlerBase() throws Exception {
    // Arrange and Act
    AsyncCompletionHandlerBase actualAsyncCompletionHandlerBase = new AsyncCompletionHandlerBase();

    // Assert
    assertNull(actualAsyncCompletionHandlerBase.onCompleted());
    assertEquals(AsyncHandler.State.CONTINUE, actualAsyncCompletionHandlerBase.onContentWritten());
    assertEquals(AsyncHandler.State.CONTINUE, actualAsyncCompletionHandlerBase.onHeadersWritten());
  }
}
