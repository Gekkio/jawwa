package fi.jawsy.jawwa.lang;

import java.io.Serializable;

import lombok.val;
import lombok.experimental.Value;

@Value
public class Tuple3<A, B, C> implements Serializable {

    private static final long serialVersionUID = -355430479757610793L;

    public static <A, B, C> Tuple3<A, B, C> of(A a, B b, C c) {
        return new Tuple3<A, B, C>(a, b, c);
    }

    public final A a;
    public final B b;
    public final C c;

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
