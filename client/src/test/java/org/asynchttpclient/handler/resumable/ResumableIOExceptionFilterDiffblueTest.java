package org.asynchttpclient.handler.resumable;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.asynchttpclient.AsyncHandler;
import org.asynchttpclient.exception.ChannelClosedException;
import org.asynchttpclient.filter.FilterContext;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

class ResumableIOExceptionFilterDiffblueTest {
  /**
   * Test {@link ResumableIOExceptionFilter#filter(FilterContext)}.
   *
   * <ul>
   *   <li>Given {@link AsyncHandler}.
   *   <li>Then calls {@link FilterContext#getAsyncHandler()}.
   * </ul>
   *
   * <p>Method under test: {@link ResumableIOExceptionFilter#filter(FilterContext)}
   */
  @Test
  @DisplayName("Test filter(FilterContext); given AsyncHandler; then calls getAsyncHandler()")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"FilterContext ResumableIOExceptionFilter.filter(FilterContext)"})
  void testFilter_givenAsyncHandler_thenCallsGetAsyncHandler() {
    // Arrange
    ResumableIOExceptionFilter resumableIOExceptionFilter = new ResumableIOExceptionFilter();

    FilterContext<Object> ctx = mock(FilterContext.class);
    when(ctx.getAsyncHandler()).thenReturn(mock(AsyncHandler.class));
    when(ctx.getIOException()).thenReturn(ChannelClosedException.INSTANCE);

    // Act
    FilterContext<Object> actualFilterResult = resumableIOExceptionFilter.filter(ctx);

    // Assert
    verify(ctx).getAsyncHandler();
    verify(ctx).getIOException();
    assertSame(ctx, actualFilterResult);
  }

  /**
   * Test {@link ResumableIOExceptionFilter#filter(FilterContext)}.
   *
   * <ul>
   *   <li>Given {@code null}.
   *   <li>When {@link FilterContext} {@link FilterContext#getIOException()} return {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link ResumableIOExceptionFilter#filter(FilterContext)}
   */
  @Test
  @DisplayName(
      "Test filter(FilterContext); given 'null'; when FilterContext getIOException() return 'null'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"FilterContext ResumableIOExceptionFilter.filter(FilterContext)"})
  void testFilter_givenNull_whenFilterContextGetIOExceptionReturnNull() {
    // Arrange
    ResumableIOExceptionFilter resumableIOExceptionFilter = new ResumableIOExceptionFilter();

    FilterContext<Object> ctx = mock(FilterContext.class);
    when(ctx.getIOException()).thenReturn(null);

    // Act
    FilterContext<Object> actualFilterResult = resumableIOExceptionFilter.filter(ctx);

    // Assert
    verify(ctx).getIOException();
    assertSame(ctx, actualFilterResult);
  }
}
