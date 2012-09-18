package fi.jawsy.jawwa.lang;

import java.io.Serializable;

import lombok.val;
import lombok.experimental.Value;

@Value
public class Tuple2<A, B> implements Serializable {

    private static final long serialVersionUID = -8208726697294737183L;

    public static <A, B> Tuple2<A, B> of(A a, B b) {
        return new Tuple2<A, B>(a, b);
    }

    public final A a;
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
