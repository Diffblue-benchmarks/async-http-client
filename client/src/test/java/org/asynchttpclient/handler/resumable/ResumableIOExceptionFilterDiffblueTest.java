package org.asynchttpclient.handler.resumable;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.asynchttpclient.AsyncHandler;
import org.asynchttpclient.exception.ChannelClosedException;
import org.asynchttpclient.filter.FilterContext;
import org.junit.jupiter.api.Test;

class ResumableIOExceptionFilterDiffblueTest {
  /**
   * Method under test: {@link ResumableIOExceptionFilter#filter(FilterContext)}
   */
  @Test
  void testFilter() {
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
   * Method under test: {@link ResumableIOExceptionFilter#filter(FilterContext)}
   */
  @Test
  void testFilter2() {
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
