package fi.jawsy.jawwa.lang;

import java.io.Serializable;

import lombok.val;
import lombok.experimental.Value;

/**
 * An immutable tuple of 2 elements.
 * 
 * @param <A>
 *            first element type
 * @param <B>
 *            second element type
 */
@Value
public class Tuple2<A, B> implements Serializable {

    private static final long serialVersionUID = -8208726697294737183L;

    /**
     * Creates a new Tuple2 from the arguments.
     * 
     * @param a
     *            first value
     * @param b
     *            second value
     * @return constructed Tuple2 instance
     */
    public static <A, B> Tuple2<A, B> of(A a, B b) {
        return new Tuple2<A, B>(a, b);
    }

    /**
     * First value
     */
    public final A a;
    /**
     * Second value
     */
    public final B b;

    public String toString() {
        val sb = new StringBuilder();
        sb.append('(');
        sb.append(a);
        sb.append(',');
        sb.append(b);
        sb.append(')');
        return sb.toString();
    }

}
