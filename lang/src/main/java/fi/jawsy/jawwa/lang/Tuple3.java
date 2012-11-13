package fi.jawsy.jawwa.lang;

import java.io.Serializable;

import lombok.val;
import lombok.experimental.Value;

/**
 * An immutable tuple of 3 elements.
 * 
 * @param <A>
 *            first element type
 * @param <B>
 *            second element type
 * @param <C>
 *            third element type
 */
@Value
public class Tuple3<A, B, C> implements Serializable {

    private static final long serialVersionUID = -355430479757610793L;

    /**
     * Creates a new Tuple3 from the arguments.
     * 
     * @param a
     *            first value
     * @param b
     *            second value
     * @param c
     *            third value
     * @return constructed Tuple3 instance
     */
    public static <A, B, C> Tuple3<A, B, C> of(A a, B b, C c) {
        return new Tuple3<A, B, C>(a, b, c);
    }

    /**
     * First value
     */
    public final A a;
    /**
     * Second value
     */
    public final B b;
    /**
     * Third value
     */
    public final C c;

    /**
     * Drops the first value from the tuple.
     * 
     * @return Tuple2 with remaining values
     */
    public Tuple2<B, C> dropA() {
        return Tuple2.of(b, c);
    }

    /**
     * Drops the second value from the tuple.
     * 
     * @return Tuple2 with remaining values
     */
    public Tuple2<A, B> dropC() {
        return Tuple2.of(a, b);
    }

    /**
     * Drops the third value from the tuple.
     * 
     * @return Tuple2 with remaining values
     */
    public Tuple2<A, C> dropB() {
        return Tuple2.of(a, c);
    }

    public String toString() {
        val sb = new StringBuilder();
        sb.append('(');
        sb.append(a);
        sb.append(',');
        sb.append(b);
        sb.append(',');
        sb.append(c);
        sb.append(')');
        return sb.toString();
    }

}
