package org.asynchttpclient;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;
import org.asynchttpclient.ListenableFuture.CompletedFailure;
import org.asynchttpclient.exception.ChannelClosedException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

class ListenableFutureDiffblueTest {
  /**
   * Test CompletedFailure {@link CompletedFailure#addListener(Runnable, Executor)}.
   * <ul>
   *   <li>When {@code null}.</li>
   *   <li>Then calls {@link Runnable#run()}.</li>
   * </ul>
   * <p>
   * Method under test: {@link CompletedFailure#addListener(Runnable, Executor)}
   */
  @Test
  @DisplayName("Test CompletedFailure addListener(Runnable, Executor); when 'null'; then calls run()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"ListenableFuture CompletedFailure.addListener(Runnable, Executor)"})
  void testCompletedFailureAddListener_whenNull_thenCallsRun() {
    // Arrange
    CompletedFailure<Object> completedFailure = new CompletedFailure<>(ChannelClosedException.INSTANCE);
    Runnable listener = mock(Runnable.class);
    doNothing().when(listener).run();

    // Act
    ListenableFuture<Object> actualAddListenerResult = completedFailure.addListener(listener, null);

    // Assert
    verify(listener).run();
    assertTrue(actualAddListenerResult instanceof CompletedFailure);
    assertTrue(actualAddListenerResult.isDone());
    assertSame(completedFailure, actualAddListenerResult);
  }

  /**
   * Test CompletedFailure {@link CompletedFailure#addListener(Runnable, Executor)}.
   * <ul>
   *   <li>When {@link Runnable}.</li>
   *   <li>Then calls {@link Executor#execute(Runnable)}.</li>
   * </ul>
   * <p>
   * Method under test: {@link CompletedFailure#addListener(Runnable, Executor)}
   */
  @Test
  @DisplayName("Test CompletedFailure addListener(Runnable, Executor); when Runnable; then calls execute(Runnable)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"ListenableFuture CompletedFailure.addListener(Runnable, Executor)"})
  void testCompletedFailureAddListener_whenRunnable_thenCallsExecute() {
    // Arrange
    CompletedFailure<Object> completedFailure = new CompletedFailure<>(ChannelClosedException.INSTANCE);
    Runnable listener = mock(Runnable.class);
    Executor exec = mock(Executor.class);
    doNothing().when(exec).execute(Mockito.<Runnable>any());

    // Act
    ListenableFuture<Object> actualAddListenerResult = completedFailure.addListener(listener, exec);

    // Assert
    verify(exec).execute(isA(Runnable.class));
    assertTrue(actualAddListenerResult instanceof CompletedFailure);
    assertTrue(actualAddListenerResult.isDone());
    assertSame(completedFailure, actualAddListenerResult);
  }

  /**
   * Test CompletedFailure {@link CompletedFailure#cancel(boolean)}.
   * <p>
   * Method under test: {@link CompletedFailure#cancel(boolean)}
   */
  @Test
  @DisplayName("Test CompletedFailure cancel(boolean)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean CompletedFailure.cancel(boolean)"})
  void testCompletedFailureCancel() {
    // Arrange
    CompletedFailure<Object> completedFailure = new CompletedFailure<>(ChannelClosedException.INSTANCE);

    // Act and Assert
    assertTrue(completedFailure.cancel(true));
  }

  /**
   * Test CompletedFailure {@link CompletedFailure#get()}.
   * <p>
   * Method under test: {@link CompletedFailure#get()}
   */
  @Test
  @DisplayName("Test CompletedFailure get()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Object CompletedFailure.get()"})
  void testCompletedFailureGet() throws ExecutionException {
    // Arrange
    CompletedFailure<Object> completedFailure = new CompletedFailure<>(ChannelClosedException.INSTANCE);

    // Act and Assert
    assertThrows(ExecutionException.class, () -> completedFailure.get());
  }

  /**
   * Test CompletedFailure {@link CompletedFailure#get(long, TimeUnit)} with {@code long}, {@code TimeUnit}.
   * <p>
   * Method under test: {@link CompletedFailure#get(long, TimeUnit)}
   */
  @Test
  @DisplayName("Test CompletedFailure get(long, TimeUnit) with 'long', 'TimeUnit'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Object CompletedFailure.get(long, TimeUnit)"})
  void testCompletedFailureGetWithLongTimeUnit() throws ExecutionException {
    // Arrange
    CompletedFailure<Object> completedFailure = new CompletedFailure<>(ChannelClosedException.INSTANCE);

    // Act and Assert
    assertThrows(ExecutionException.class, () -> completedFailure.get(10L, TimeUnit.NANOSECONDS));
  }

  /**
   * Test CompletedFailure getters and setters.
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link CompletedFailure#abort(Throwable)}
   *   <li>{@link CompletedFailure#done()}
   *   <li>{@link CompletedFailure#touch()}
   *   <li>{@link CompletedFailure#isCancelled()}
   *   <li>{@link CompletedFailure#isDone()}
   * </ul>
   */
  @Test
  @DisplayName("Test CompletedFailure getters and setters")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void CompletedFailure.abort(Throwable)", "void CompletedFailure.done()",
      "boolean CompletedFailure.isCancelled()", "boolean CompletedFailure.isDone()", "void CompletedFailure.touch()"})
  void testCompletedFailureGettersAndSetters() {
    // Arrange
    CompletedFailure<Object> completedFailure = new CompletedFailure<>(ChannelClosedException.INSTANCE);

    // Act
    completedFailure.abort(ChannelClosedException.INSTANCE);
    completedFailure.done();
    completedFailure.touch();
    boolean actualIsCancelledResult = completedFailure.isCancelled();

    // Assert
    assertFalse(actualIsCancelledResult);
    assertTrue(completedFailure.isDone());
  }

  /**
   * Test CompletedFailure {@link CompletedFailure#CompletedFailure(String, Throwable)}.
   * <p>
   * Method under test: {@link CompletedFailure#CompletedFailure(String, Throwable)}
   */
  @Test
  @DisplayName("Test CompletedFailure new CompletedFailure(String, Throwable)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void CompletedFailure.<init>(String, Throwable)"})
  void testCompletedFailureNewCompletedFailure() {
    // Arrange and Act
    CompletedFailure<Object> actualCompletedFailure = new CompletedFailure<>("https://example.org/example",
        ChannelClosedException.INSTANCE);

    // Assert
    assertTrue(actualCompletedFailure.isDone());
  }

  /**
   * Test CompletedFailure {@link CompletedFailure#CompletedFailure(Throwable)}.
   * <p>
   * Method under test: {@link CompletedFailure#CompletedFailure(Throwable)}
   */
  @Test
  @DisplayName("Test CompletedFailure new CompletedFailure(Throwable)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void CompletedFailure.<init>(Throwable)"})
  void testCompletedFailureNewCompletedFailure2() {
    // Arrange and Act
    CompletedFailure<Object> actualCompletedFailure = new CompletedFailure<>(ChannelClosedException.INSTANCE);

    // Assert
    assertTrue(actualCompletedFailure.isDone());
  }

  /**
   * Test CompletedFailure {@link CompletedFailure#toCompletableFuture()}.
   * <p>
   * Method under test: {@link CompletedFailure#toCompletableFuture()}
   */
  @Test
  @DisplayName("Test CompletedFailure toCompletableFuture()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"java.util.concurrent.CompletableFuture CompletedFailure.toCompletableFuture()"})
  void testCompletedFailureToCompletableFuture() {
    // Arrange
    CompletedFailure<Object> completedFailure = new CompletedFailure<>(ChannelClosedException.INSTANCE);

    // Act and Assert
    assertTrue(completedFailure.toCompletableFuture().isDone());
  }
}
