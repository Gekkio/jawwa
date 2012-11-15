package fi.jawsy.jawwa.lang;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.io.ObjectStreamException;
import java.io.Serializable;
import java.util.Iterator;
import java.util.concurrent.Callable;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import com.google.common.base.Function;
import com.google.common.base.Preconditions;
import com.google.common.base.Predicate;
import com.google.common.base.Supplier;
import com.google.common.collect.Iterators;

/**
 * A generic 0-or-1 container.
 * 
 * <h3>Usage examples</h3>
 * 
 * <em>Construction</em>
 * 
 * <pre>
 * Option&lt;String&gt; empty = Option.none();
 * Option&lt;String&gt; full = Option.some(&quot;value&quot;);
 * Option&lt;String&gt; empty2 = Option.option(null);
 * Option&lt;String&gt; full2 = Option.option(&quot;value&quot;);
 * </pre>
 * 
 * <em>Iteration</em>
 * 
 * <pre>
 * for (String value : empty) {
 *     // Never executed
 * }
 * for (String value : full) {
 *     // Executed exactly once
 * }
 * </pre>
 * 
 * <em>Default values</em>
 * 
 * <pre>
 * // Will contain &quot;default&quot;
 * String value1 = empty.getOrElse(&quot;default&quot;);
 * // Will contain &quot;value&quot;
 * String value2 = full.getOrElse(&quot;default&quot;);
 * </pre>
 * 
 * <em>Chaining<em>
 * <pre>
 * // Will contain &quot;default&quot;
 * Option&lt;String&gt; value1 = empty.orElse(Option.some(&quot;default&quot;));
 * // Will contain &quot;default&quot;
 * Option&lt;String&gt; value2 = empty.orElse(Option.some(&quot;default&quot;)).orElse(Option.some(&quot;not used&quot;));
 * </pre>
 * 
 * <em>Value lookup</em>
 * 
 * <pre>
 * // Throws an exception
 * String value = empty.getValue();
 * // Value is taken from the container
 * String value = full.getValue();
 * </pre>
 * 
 * <em>Safe value lookup</em>
 * 
 * <pre>
 * 
 * Option&lt;String&gt; unsure;
 * 
 * if (unsure.isDefined()) {
 *     String value = unsure.getValue();
 *     // Do something with the value
 * } else {
 *     // Do something else
 * }
 * </pre>
 * 
 * @param <T>
 *            underlying type
 */
public abstract class Option<T> implements Iterable<T>, Serializable {

    private static final long serialVersionUID = -2847406469172844207L;

    /**
     * Returns an Option that contains the given value if it conforms to the given type.
     * 
     * @param <A>
     *            wanted type
     * @param value
     *            value to be tested
     * @param clazz
     *            wanted type class
     * @return Some(value) if value is instanceof clazz, None otherwise
     */
    @SuppressWarnings("unchecked")
    public static <A> Option<A> requireType(final Object value, final Class<A> clazz) {
        if (value == null || !clazz.isAssignableFrom(value.getClass())) {
            return none();
        }
        return some((A) value);
    }

    /**
     * Returns an Option that is empty.
     * 
     * @param <A>
     *            underlying type
     * @return an empty Option
     */
    @SuppressWarnings("unchecked")
    public static <A> Option<A> none() {
        return None.INSTANCE;
    }

    /**
     * Returns an Option that is empty.
     * 
     * @param <A>
     *            underlying type
     * @param clazz
     *            class of underlying type (for convenience only)
     * @return an empty Option
     */
    @SuppressWarnings("unchecked")
    public static <A> Option<A> none(final Class<A> clazz) {
        return None.INSTANCE;
    }

    /**
     * Returns an Option that contains the given value.
     * 
     * @param <A>
     *            underlying type
     * @param value
     *            underlying value
     * @return a full Option
     */
    public static <A> Option<A> some(final A value) {
        Preconditions.checkNotNull(value, "value cannot be null");
        return new Some<A>(value);
    }

    /**
     * Tries to get a value from the given supplier. If an exception is thrown, it is silently consumed and None
     * returned.
     * 
     * @param <A>
     *            underlying type
     * @param supplier
     *            value supplier
     * @return Some(value) if no exception was thrown, None otherwise
     */
    public static <A> Option<A> tryo(final Supplier<A> supplier) {
        Preconditions.checkNotNull(supplier, "supplier cannot be null");
        try {
            return Option.option(supplier.get());
        } catch (Exception e) {
            return Option.none();
        }
    }

    /**
     * Tries to get a value from the given callable. If an exception is thrown, it is silently consumed and None
     * returned.
     * 
     * @param <A>
     *            underlying type
     * @param callable
     *            callable value
     * @return Some(value) if no exception was thrown, None otherwise
     */
    public static <A> Option<A> tryo(final Callable<A> callable) {
        Preconditions.checkNotNull(callable, "callable cannot be null");
        try {
            return Option.option(callable.call());
        } catch (Exception e) {
            return Option.none();
        }
    }

    /**
     * Wraps a potentially null object into an {@link Option}.
     * 
     * @param <A>
     *            underlying type
     * @param value
     *            underlying value
     * @return None if value is null, Some(value) otherwise
     */
    public static <A> Option<A> option(final A value) {
        if (value == null) {
            return none();
        } else {
            return some(value);
        }
    }

    /**
     * Private constructor that prevents unintended subclassing
     */
    private Option() {
    }

    /**
     * Filters the container based on a boolean value. This means that if the container contains a value, it will be
     * retained only if the given parameter is true.
     * 
     * @param value
     *            boolean predicate
     * @return filtered container
     */
    public abstract Option<T> filter(boolean predicate);

    /**
     * Filters the container based on a predicate. This means that if the container contains a value, it will be
     * retained only if the predicate returns true for the value.
     * 
     * @param predicate
     *            predicate
     * @return filtered container
     */
    public abstract Option<T> filter(Predicate<? super T> predicate);

    /**
     * Transforms (maps) the container value using a function that returns an option.
     * 
     * @param <O>
     *            target type
     * @param function
     *            transformer function
     * @return f(value) if the container had a value, None otherwise
     */
    public abstract <O> Option<O> flatMap(Function<? super T, ? extends Option<O>> function);

    /**
     * Returns the value in the container or if the container is empty, returns an object from the given supplier.
     * 
     * @param defaultValueSupplier
     *            default value supplier
     * @return value from container or the result of supplier.get()
     */
    public abstract T getOrElse(Supplier<T> defaultValueSupplier);

    /**
     * Returns the value in the container or if the container is empty, returns the given parameter.
     * 
     * @param defaultValue
     *            default value
     * @return value from container or default value
     */
    public abstract T getOrElse(T defaultValue);

    /**
     * Returns the value in the container or if the container is empty, returns null.
     * 
     * @return value from container or null
     */
    public abstract T getOrNull();

    /**
     * Returns the value in the container or if the container is empty, throws the given exception.
     * 
     * @throws RuntimeException
     *             if the container is empty
     * @return value from container
     */
    public abstract T getOrThrow(RuntimeException e);

    /**
     * Returns the value in the container or if the container is empty, throws an UnsupportedOperationException with the
     * given message.
     * 
     * @throws UnsupportedOperationException
     *             if the container is empty
     * @return value from container
     */
    public abstract T getOrThrow(String msg);

    /**
     * Returns the value in the container or throws an exception.
     * 
     * @throws UnsupportedOperationException
     *             if the container is empty
     * @return the value
     */
    public abstract T getValue();

    /**
     * Checks whether the Option has a defined value.
     * 
     * @return true if container is non-empty
     */
    public abstract boolean isDefined();

    /**
     * Transforms (maps) the container value using a function.
     * 
     * @param <O>
     *            target type
     * @param function
     *            transformer function
     * @return Some(f(value)) if the container had a value, None otherwise
     */
    public abstract <O> Option<O> map(Function<? super T, ? extends O> function);

    /**
     * Returns the container or if the container is empty, returns the given parameter.
     * 
     * @param defaultValue
     *            default value
     * @return container or default value
     */
    public abstract Option<T> orElse(Option<T> defaultOption);

    /**
     * Returns the container or if the container is empty, returns a container from the given supplier.
     * 
     * @param defaultOptionSupplier
     *            default value
     * @return container or the result of supplier.get()
     */
    public abstract Option<T> orElse(Supplier<Option<T>> defaultOptionSupplier);

    /**
     * Converts the container to an Either value. If the container contains a value, Either.Right containing the value
     * is returned. Otherwise, Either.Left containing the given left value is returned.
     * 
     * @param leftValue
     *            value to be used if the container is empty
     * @return Either.right(value) if container has a value, Either.left(leftValue) otherwise.
     */
    public abstract <L> Either<L, T> toRight(L leftValue);

    /**
     * Converts the container to an Either value. If the container contains a value, Either.Left containing the value is
     * returned. Otherwise, Either.Right containing the given right value is returned.
     * 
     * @param rightValue
     *            value to be used if the container is empty
     * @return Either.left(value) if container has a value, Either.right(rightValue) otherwise.
     */
    public abstract <R> Either<T, R> toLeft(R rightValue);

    private static final class None<T> extends Option<T> {

        @SuppressWarnings("rawtypes")
        private static final None INSTANCE = new None();

        private static final long serialVersionUID = 6338093416407916502L;

        @Override
        public boolean equals(final Object obj) {
            if (obj == null)
                return false;
            if (obj == this)
                return true;
            return obj.getClass().equals(this.getClass());
        }

        @Override
        public Option<T> filter(final boolean predicate) {
            return this;
        }

        @Override
        public Option<T> filter(final Predicate<? super T> predicate) {
            return this;
        }

        @Override
        public <O> Option<O> flatMap(final Function<? super T, ? extends Option<O>> function) {
            return Option.none();
        }

        @Override
        public T getOrElse(final Supplier<T> defaultValueSupplier) {
            Preconditions.checkNotNull(defaultValueSupplier, "defaultValueSupplier cannot be null");
            return defaultValueSupplier.get();
        }

        @Override
        public T getOrElse(final T defaultValue) {
            Preconditions.checkNotNull(defaultValue, "defaultValue cannot be null");
            return defaultValue;
        }

        @Override
        public T getOrNull() {
            return null;
        }

        @Override
        public T getOrThrow(RuntimeException e) {
            throw e;
        }

        @Override
        public T getOrThrow(String msg) {
            throw new UnsupportedOperationException(msg);
        }

        @Override
        public T getValue() {
            throw new UnsupportedOperationException("Attempted to get a value from None");
        }

        @Override
        public int hashCode() {
            return 31;
        }

        @Override
        public boolean isDefined() {
            return false;
        }

        @Override
        public Iterator<T> iterator() {
            return Iterators.emptyIterator();
        }

        @Override
        public <O> Option<O> map(final Function<? super T, ? extends O> function) {
            return Option.none();
        }

        @Override
        public Option<T> orElse(final Option<T> defaultOption) {
            Preconditions.checkNotNull(defaultOption, "defaultOption cannot be null");
            return defaultOption;
        }

        @Override
        public Option<T> orElse(final Supplier<Option<T>> defaultOptionSupplier) {
            Preconditions.checkNotNull(defaultOptionSupplier, "defaultOptionSupplier cannot be null");
            return defaultOptionSupplier.get();
        }

        private Object readResolve() throws ObjectStreamException {
            return INSTANCE;
        }

        @Override
        public String toString() {
            return "None";
        }

        /**
         * Always returns rightValue wrapped in Right.
         */
        @Override
        public <R> Either<T, R> toLeft(R rightValue) {
            return Either.right(rightValue);
        }

        /**
         * Always returns leftValue wrapped in Left.
         */
        @Override
        public <L> Either<L, T> toRight(L leftValue) {
            return Either.left(leftValue);
        }

    }

    @RequiredArgsConstructor
    @EqualsAndHashCode(callSuper = false)
    private static final class Some<T> extends Option<T> {

        private static final long serialVersionUID = 2862031508691460175L;

        private final T value;

        @Override
        public Option<T> filter(final boolean predicate) {
            if (predicate) {
                return this;
            }
            return Option.none();
        }

        @Override
        public Option<T> filter(final Predicate<? super T> predicate) {
            Preconditions.checkNotNull(predicate, "predicate cannot be null");
            if (predicate.apply(value)) {
                return this;
            }
            return Option.none();
        }

        @Override
        public <O> Option<O> flatMap(final Function<? super T, ? extends Option<O>> function) {
            Preconditions.checkNotNull(function, "function cannot be null");
            return function.apply(value);
        }

        /**
         * Always returns the underlying value.
         */
        @Override
        public T getOrElse(final Supplier<T> defaultValue) {
            return value;
        }

        /**
         * Always returns the underlying value.
         */
        @Override
        public T getOrElse(final T defaultValue) {
            return value;
        }

        /**
         * Always returns the underlying value.
         */
        @Override
        public T getOrNull() {
            return value;
        }

        @Override
        public T getOrThrow(RuntimeException e) {
            return value;
        }

        @Override
        public T getOrThrow(String msg) {
            return value;
        }

        @Override
        public T getValue() {
            return value;
        }

        /**
         * Always returns true.
         */
        @Override
        public boolean isDefined() {
            return true;
        }

        /**
         * Returns an iterator that contains the underlying value.
         */
        @Override
        public Iterator<T> iterator() {
            return Iterators.singletonIterator(value);
        }

        @Override
        public <O> Option<O> map(final Function<? super T, ? extends O> function) {
            Preconditions.checkNotNull(function, "function cannot be null");
            return Option.<O> some(function.apply(value));
        }

        /**
         * Because this is a Some, returns this instead of the defaultOption.
         */
        @Override
        public Option<T> orElse(final Option<T> defaultOption) {
            return this;
        }

        /**
         * Because this is a Some, returns this instead of the defaultOption.
         */
        @Override
        public Option<T> orElse(final Supplier<Option<T>> defaultOptionSupplier) {
            return this;
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append("Some(");
            sb.append(value);
            sb.append(')');
            return sb.toString();
        }

        private Object writeReplace() throws ObjectStreamException {
            return new SerializedForm(value);
        }

        @AllArgsConstructor
        @NoArgsConstructor
        private static final class SerializedForm implements Externalizable {

            private Object value;

            @Override
            public void readExternal(final ObjectInput in) throws IOException, ClassNotFoundException {
                value = in.readObject();
            }

            private Object readResolve() throws ObjectStreamException {
                return Option.some(value);
            }

            @Override
            public void writeExternal(final ObjectOutput out) throws IOException {
                out.writeObject(value);
            }

        }

        /**
         * Always returns the underlying value wrapped in Right.
         */
        @Override
        public <L> Either<L, T> toRight(L leftValue) {
            return Either.right(value);
        }

        /**
         * Always returns the underlying value wrapped in Left.
         */
        @Override
        public <R> Either<T, R> toLeft(R rightValue) {
            return Either.left(value);
        }

    }

}