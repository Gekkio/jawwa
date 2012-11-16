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

    public static Effect<? super Object> systemOut() {
        return SystemOutEffect.INSTANCE;
    }

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

    public static Effect<? super Object> systemErr() {
        return SystemErrEffect.INSTANCE;
    }

    public static Effect<? super Object> systemErr(String prefix) {
        return new SystemErrEffect(prefix);
    }

}
