package org.asynchttpclient;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;
import org.asynchttpclient.ListenableFuture.CompletedFailure;
import org.asynchttpclient.exception.ChannelClosedException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

class ListenableFutureDiffblueTest {
  /**
   * Test CompletedFailure
   * {@link CompletedFailure#addListener(Runnable, Executor)}.
   * <ul>
   *   <li>When {@code null}.</li>
   *   <li>Then calls {@link Runnable#run()}.</li>
   * </ul>
   * <p>
   * Method under test:
   * {@link ListenableFuture.CompletedFailure#addListener(Runnable, Executor)}
   */
  @Test
  @DisplayName("Test CompletedFailure addListener(Runnable, Executor); when 'null'; then calls run()")
  void testCompletedFailureAddListener_whenNull_thenCallsRun() {
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
   * Test CompletedFailure
   * {@link CompletedFailure#addListener(Runnable, Executor)}.
   * <ul>
   *   <li>When {@link Runnable}.</li>
   *   <li>Then calls {@link Executor#execute(Runnable)}.</li>
   * </ul>
   * <p>
   * Method under test:
   * {@link ListenableFuture.CompletedFailure#addListener(Runnable, Executor)}
   */
  @Test
  @DisplayName("Test CompletedFailure addListener(Runnable, Executor); when Runnable; then calls execute(Runnable)")
  void testCompletedFailureAddListener_whenRunnable_thenCallsExecute() {
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
   * Test CompletedFailure {@link CompletedFailure#cancel(boolean)}.
   * <p>
   * Method under test: {@link ListenableFuture.CompletedFailure#cancel(boolean)}
   */
  @Test
  @DisplayName("Test CompletedFailure cancel(boolean)")
  void testCompletedFailureCancel() {
    // Arrange
    ListenableFuture.CompletedFailure<Object> completedFailure = new ListenableFuture.CompletedFailure<>(
        ChannelClosedException.INSTANCE);

    // Act and Assert
    assertTrue(completedFailure.cancel(true));
  }

  /**
   * Test CompletedFailure {@link CompletedFailure#get()}.
   * <p>
   * Method under test: {@link ListenableFuture.CompletedFailure#get()}
   */
  @Test
  @DisplayName("Test CompletedFailure get()")
  void testCompletedFailureGet() throws ExecutionException {
    // Arrange
    ListenableFuture.CompletedFailure<Object> completedFailure = new ListenableFuture.CompletedFailure<>(
        ChannelClosedException.INSTANCE);

    // Act and Assert
    assertThrows(ExecutionException.class, () -> completedFailure.get());
  }

  /**
   * Test CompletedFailure {@link CompletedFailure#get(long, TimeUnit)} with
   * {@code long}, {@code TimeUnit}.
   * <p>
   * Method under test:
   * {@link ListenableFuture.CompletedFailure#get(long, TimeUnit)}
   */
  @Test
  @DisplayName("Test CompletedFailure get(long, TimeUnit) with 'long', 'TimeUnit'")
  void testCompletedFailureGetWithLongTimeUnit() throws ExecutionException {
    // Arrange
    ListenableFuture.CompletedFailure<Object> completedFailure = new ListenableFuture.CompletedFailure<>(
        ChannelClosedException.INSTANCE);

    // Act and Assert
    assertThrows(ExecutionException.class, () -> completedFailure.get(10L, TimeUnit.NANOSECONDS));
  }

  /**
   * Test CompletedFailure getters and setters.
   * <p>
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
  @DisplayName("Test CompletedFailure getters and setters")
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
   * Test CompletedFailure
   * {@link CompletedFailure#CompletedFailure(String, Throwable)}.
   * <p>
   * Method under test:
   * {@link ListenableFuture.CompletedFailure#CompletedFailure(String, Throwable)}
   */
  @Test
  @DisplayName("Test CompletedFailure new CompletedFailure(String, Throwable)")
  void testCompletedFailureNewCompletedFailure() {
    // Arrange and Act
    ListenableFuture.CompletedFailure<Object> actualCompletedFailure = new ListenableFuture.CompletedFailure<>(
        "https://example.org/example", ChannelClosedException.INSTANCE);

    // Assert
    assertTrue(actualCompletedFailure.isDone());
  }

  /**
   * Test CompletedFailure {@link CompletedFailure#CompletedFailure(Throwable)}.
   * <p>
   * Method under test:
   * {@link ListenableFuture.CompletedFailure#CompletedFailure(Throwable)}
   */
  @Test
  @DisplayName("Test CompletedFailure new CompletedFailure(Throwable)")
  void testCompletedFailureNewCompletedFailure2() {
    // Arrange and Act
    ListenableFuture.CompletedFailure<Object> actualCompletedFailure = new ListenableFuture.CompletedFailure<>(
        ChannelClosedException.INSTANCE);

    // Assert
    assertTrue(actualCompletedFailure.isDone());
  }

  /**
   * Test CompletedFailure {@link CompletedFailure#toCompletableFuture()}.
   * <p>
   * Method under test:
   * {@link ListenableFuture.CompletedFailure#toCompletableFuture()}
   */
  @Test
  @DisplayName("Test CompletedFailure toCompletableFuture()")
  void testCompletedFailureToCompletableFuture() {
    // Arrange
    ListenableFuture.CompletedFailure<Object> completedFailure = new ListenableFuture.CompletedFailure<>(
        ChannelClosedException.INSTANCE);

    // Act and Assert
    assertTrue(completedFailure.toCompletableFuture().isDone());
  }
}
