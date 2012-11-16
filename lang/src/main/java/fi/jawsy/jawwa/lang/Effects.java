package fi.jawsy.jawwa.lang;

import java.io.Serializable;

import lombok.RequiredArgsConstructor;

import com.google.common.base.Function;

/**
 * Utilities for Effect objects.
 */
public final class Effects {

    private Effects() {
    }

    /**
     * Converts an effect to a function that performs the given effect.
     * 
     * @param e
     *            effect
     * @return function
     */
    public static <T> Function<T, Void> toFunction(final Effect<T> e) {
        class EffectFunction implements Function<T, Void>, Serializable {
            private static final long serialVersionUID = -4601876026894597505L;

            @Override
            public Void apply(T input) {
                e.apply(input);
                return null;
            }
        }
        return new EffectFunction();
    }

    /**
     * Converts a function to an effect that applies the given function and discards the result.
     * 
     * @param f
     *            function
     * @return effect
     */
    public static <T> Effect<T> fromFunction(final Function<? super T, ?> f) {
        class FromFunctionEffect implements Effect<T>, Serializable {
            private static final long serialVersionUID = -8381630600993099590L;

            @Override
            public void apply(T input) {
                f.apply(input);
            }
        }
        return new FromFunctionEffect();
    }

    static class NoopEffect implements Effect<Object>, Serializable {

        private static final long serialVersionUID = -6608178536760540031L;

        public static final NoopEffect INSTANCE = new NoopEffect();

        @Override
        public void apply(Object input) {
        }

    }

    /**
     * Returns an effect that does nothing.
     * 
     * @return effect
     */
    public static Effect<? super Object> noop() {
        return NoopEffect.INSTANCE;
    }

    /**
     * Returns an effect that does nothing.
     * 
     * @param clazz
     *            class of input type (for convenience only)
     * @return effect
     */
    @SuppressWarnings("unchecked")
    public static <T> Effect<T> noop(Class<T> clazz) {
        return (Effect<T>) NoopEffect.INSTANCE;
    }

    @RequiredArgsConstructor
    static class SystemOutEffect implements Effect<Object>, Serializable {

        private static final long serialVersionUID = 5986895017772281905L;

        public final String prefix;

        public static final SystemOutEffect INSTANCE = new SystemOutEffect("");

        @Override
        public void apply(Object input) {
            System.out.print(prefix);
            System.out.println(input);
        }

    }

    /**
     * Returns an effect that logs the input to System.out.
     * 
     * @return effect
     */
    public static Effect<? super Object> systemOut() {
        return SystemOutEffect.INSTANCE;
    }

    /**
     * Returns an effect that logs the input to System.out with the given prefix string.
     * 
     * @return effect
     */
    public static Effect<? super Object> systemOut(String prefix) {
        return new SystemOutEffect(prefix);
    }

    @RequiredArgsConstructor
    static class SystemErrEffect implements Effect<Object>, Serializable {

        private static final long serialVersionUID = -635378664192791635L;

        public final String prefix;

        public static final SystemErrEffect INSTANCE = new SystemErrEffect("");

        @Override
        public void apply(Object input) {
            System.err.print(prefix);
            System.err.println(input);
        }

    }

    /**
     * Returns an effect that logs the input to System.err.
     * 
     * @return effect
     */
    public static Effect<? super Object> systemErr() {
        return SystemErrEffect.INSTANCE;
    }

    /**
     * Returns an effect that logs the input to System.err with the given prefix string.
     * 
     * @return effect
     */
    public static Effect<? super Object> systemErr(String prefix) {
        return new SystemErrEffect(prefix);
    }

}
