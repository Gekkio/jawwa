package fi.jawsy.jawwa.lang;

import java.io.Serializable;

import com.google.common.base.Function;

/**
 * Functions for Tuple3 objects.
 */
public final class Tuple3Functions {

    private Tuple3Functions() {
    }

    /**
     * Returns a function that updates the first elements of input tuples with the given value.
     * 
     * @param a
     *            new first element value
     * @return function
     */
    public static <A, B, C> Function<Tuple3<A, B, C>, Tuple3<A, B, C>> withA(final A a) {
        class WithAFunction implements Function<Tuple3<A, B, C>, Tuple3<A, B, C>>, Serializable {
            private static final long serialVersionUID = 3715349024733990454L;

            @Override
            public Tuple3<A, B, C> apply(Tuple3<A, B, C> input) {
                return input.withA(a);
            }
        }
        return new WithAFunction();
    }

    /**
     * Returns a function that updates the first elements of input tuples with the given value.
     * 
     * @param a
     *            new first element value
     * @param clazzB
     *            second element type (for convenience only)
     * @param clazzC
     *            third element type (for convenience only)
     * @return function
     */
    public static <A, B, C> Function<Tuple3<A, B, C>, Tuple3<A, B, C>> withA(final A a, Class<B> clazzB, Class<C> clazzC) {
        return withA(a);
    }

    /**
     * Returns a function that updates the second elements of input tuples with the given value.
     * 
     * @param b
     *            new second element value
     * @return function
     */
    public static <A, B, C> Function<Tuple3<A, B, C>, Tuple3<A, B, C>> withB(final B b) {
        class WithBFunction implements Function<Tuple3<A, B, C>, Tuple3<A, B, C>>, Serializable {
            private static final long serialVersionUID = -1119766957347856671L;

            @Override
            public Tuple3<A, B, C> apply(Tuple3<A, B, C> input) {
                return input.withB(b);
            }
        }
        return new WithBFunction();
    }

    /**
     * Returns a function that updates the second elements of input tuples with the given value.
     * 
     * @param clazzA
     *            first element type (for convenience only)
     * @param b
     *            new second element value
     * @param clazzC
     *            third element type (for convenience only)
     * @return function
     */
    public static <A, B, C> Function<Tuple3<A, B, C>, Tuple3<A, B, C>> withB(Class<A> clazzA, final B b, Class<C> clazzC) {
        return withB(b);
    }

    /**
     * Returns a function that updates the third elements of input tuples with the given value.
     * 
     * @param c
     *            new third element value
     * @return function
     */
    public static <A, B, C> Function<Tuple3<A, B, C>, Tuple3<A, B, C>> withC(final C c) {
        class WithCFunction implements Function<Tuple3<A, B, C>, Tuple3<A, B, C>>, Serializable {
            private static final long serialVersionUID = 3218222630256860618L;

            @Override
            public Tuple3<A, B, C> apply(Tuple3<A, B, C> input) {
                return input.withC(c);
            }
        }
        return new WithCFunction();
    }

    /**
     * Returns a function that updates the third elements of input tuples with the given value.
     * 
     * @param clazzA
     *            first element type (for convenience only)
     * @param clazzB
     *            second element type (for convenience only)
     * @param c
     *            new third element value
     * @return function
     */
    public static <A, B, C> Function<Tuple3<A, B, C>, Tuple3<A, B, C>> withC(Class<A> clazzA, Class<B> clazzB, final C c) {
        return withC(c);
    }

}
