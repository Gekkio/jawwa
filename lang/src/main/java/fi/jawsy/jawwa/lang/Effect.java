package fi.jawsy.jawwa.lang;

/**
 * Side-effecting function that has no return value.
 * 
 * @param <T>
 *            input type
 */
public interface Effect<T> {

    void apply(T input);

}
