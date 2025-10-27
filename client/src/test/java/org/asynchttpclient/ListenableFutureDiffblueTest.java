package org.asynchttpclient;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;
import org.asynchttpclient.exception.ChannelClosedException;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

class ListenableFutureDiffblueTest {
  /**
   * Method under test:
   * {@link ListenableFuture.CompletedFailure#addListener(Runnable, Executor)}
   */
  @Test
  void testCompletedFailureAddListener() {
    // Arrange
    ListenableFuture.CompletedFailure<Object> completedFailure = new ListenableFuture.CompletedFailure<>(
        ChannelClosedException.INSTANCE);
    Runnable listener = mock(Runnable.class);
    Executor exec = mock(Executor.class);
    doNothing().when(exec).execute(Mockito.<Runnable>any());

    // Act
    ListenableFuture<Object> actualAddListenerResult = completedFailure.addListener(listener, exec);

    // Assert
    verify(exec).execute(isA(Runnable.class));
    assertTrue(actualAddListenerResult instanceof ListenableFuture.CompletedFailure);
    assertTrue(actualAddListenerResult.isDone());
    assertSame(completedFailure, actualAddListenerResult);
  }

  /**
   * Method under test:
   * {@link ListenableFuture.CompletedFailure#addListener(Runnable, Executor)}
   */
  @Test
  void testCompletedFailureAddListener2() {
    // Arrange
    ListenableFuture.CompletedFailure<Object> completedFailure = new ListenableFuture.CompletedFailure<>(
        ChannelClosedException.INSTANCE);
    Runnable listener = mock(Runnable.class);
    doNothing().when(listener).run();

    // Act
    ListenableFuture<Object> actualAddListenerResult = completedFailure.addListener(listener, null);

    // Assert
    verify(listener).run();
    assertTrue(actualAddListenerResult instanceof ListenableFuture.CompletedFailure);
    assertTrue(actualAddListenerResult.isDone());
    assertSame(completedFailure, actualAddListenerResult);
  }

  /**
   * Method under test: {@link ListenableFuture.CompletedFailure#cancel(boolean)}
   */
  @Test
  void testCompletedFailureCancel() {
    // Arrange
    ListenableFuture.CompletedFailure<Object> completedFailure = new ListenableFuture.CompletedFailure<>(
        ChannelClosedException.INSTANCE);

    // Act and Assert
    assertTrue(completedFailure.cancel(true));
  }

  /**
   * Method under test: {@link ListenableFuture.CompletedFailure#cancel(boolean)}
   */
  @Test
  void testCompletedFailureCancel2() {
    // Arrange
    Executor exec = mock(Executor.class);
    doNothing().when(exec).execute(Mockito.<Runnable>any());

    ListenableFuture.CompletedFailure<Object> completedFailure = new ListenableFuture.CompletedFailure<>(
        ChannelClosedException.INSTANCE);
    completedFailure.addListener(mock(Runnable.class), exec);

    // Act
    boolean actualCancelResult = completedFailure.cancel(true);

    // Assert
    verify(exec).execute(isA(Runnable.class));
    assertTrue(actualCancelResult);
  }

  /**
   * Method under test: {@link ListenableFuture.CompletedFailure#get()}
   */
  @Test
  void testCompletedFailureGet() throws ExecutionException {
    // Arrange
    ListenableFuture.CompletedFailure<Object> completedFailure = new ListenableFuture.CompletedFailure<>(
        ChannelClosedException.INSTANCE);

    // Act and Assert
    assertThrows(ExecutionException.class, () -> completedFailure.get());
  }

  /**
   * Method under test:
   * {@link ListenableFuture.CompletedFailure#get(long, TimeUnit)}
   */
  @Test
  void testCompletedFailureGet2() throws ExecutionException {
    // Arrange
    ListenableFuture.CompletedFailure<Object> completedFailure = new ListenableFuture.CompletedFailure<>(
        ChannelClosedException.INSTANCE);

    // Act and Assert
    assertThrows(ExecutionException.class, () -> completedFailure.get(10L, TimeUnit.NANOSECONDS));
  }

  /**
   * Methods under test:
   * <ul>
   *   <li>{@link ListenableFuture.CompletedFailure#abort(Throwable)}
   *   <li>{@link ListenableFuture.CompletedFailure#done()}
   *   <li>{@link ListenableFuture.CompletedFailure#touch()}
   *   <li>{@link ListenableFuture.CompletedFailure#isCancelled()}
   *   <li>{@link ListenableFuture.CompletedFailure#isDone()}
   * </ul>
   */
  @Test
  void testCompletedFailureGettersAndSetters() {
    // Arrange
    ListenableFuture.CompletedFailure<Object> completedFailure = new ListenableFuture.CompletedFailure<>(
        ChannelClosedException.INSTANCE);

    // Act
    completedFailure.abort(ChannelClosedException.INSTANCE);
    completedFailure.done();
    completedFailure.touch();
    boolean actualIsCancelledResult = completedFailure.isCancelled();

    // Assert that nothing has changed
    assertFalse(actualIsCancelledResult);
    assertTrue(completedFailure.isDone());
  }

  /**
   * Method under test:
   * {@link ListenableFuture.CompletedFailure#CompletedFailure(String, Throwable)}
   */
  @Test
  void testCompletedFailureNewCompletedFailure() {
    // Arrange and Act
    ListenableFuture.CompletedFailure<Object> actualCompletedFailure = new ListenableFuture.CompletedFailure<>(
        "https://example.org/example", ChannelClosedException.INSTANCE);

    // Assert
    assertTrue(actualCompletedFailure.isDone());
  }

  /**
   * Method under test:
   * {@link ListenableFuture.CompletedFailure#CompletedFailure(Throwable)}
   */
  @Test
  void testCompletedFailureNewCompletedFailure2() {
    // Arrange and Act
    ListenableFuture.CompletedFailure<Object> actualCompletedFailure = new ListenableFuture.CompletedFailure<>(
        ChannelClosedException.INSTANCE);

    // Assert
    assertTrue(actualCompletedFailure.isDone());
  }

  /**
   * Method under test:
   * {@link ListenableFuture.CompletedFailure#toCompletableFuture()}
   */
  @Test
  void testCompletedFailureToCompletableFuture() {
    // Arrange
    ListenableFuture.CompletedFailure<Object> completedFailure = new ListenableFuture.CompletedFailure<>(
        ChannelClosedException.INSTANCE);

    // Act and Assert
    assertTrue(completedFailure.toCompletableFuture().isDone());
  }

  /**
   * Method under test:
   * {@link ListenableFuture.CompletedFailure#toCompletableFuture()}
   */
  @Test
  void testCompletedFailureToCompletableFuture2() {
    // Arrange
    Executor exec = mock(Executor.class);
    doNothing().when(exec).execute(Mockito.<Runnable>any());

    ListenableFuture.CompletedFailure<Object> completedFailure = new ListenableFuture.CompletedFailure<>(
        ChannelClosedException.INSTANCE);
    completedFailure.addListener(mock(Runnable.class), exec);

    // Act
    CompletableFuture<Object> actualToCompletableFutureResult = completedFailure.toCompletableFuture();

    // Assert
    verify(exec).execute(isA(Runnable.class));
    assertTrue(actualToCompletableFutureResult.isDone());
  }
}
