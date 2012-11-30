package fi.jawsy.jawwa.lang;

/**
 * Function that takes two input parameters.
 * 
 * @param <A>
 *            first parameter type
 * @param <B>
 *            second parameter type
 * @param <T>
 *            result type
 */
public interface Function2<A, B, T> {

    T apply(A first, B second);

}
