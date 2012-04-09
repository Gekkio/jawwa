package fi.jawsy.jawwa.zk.raphaeljs;

import lombok.val;

import org.zkoss.json.JSONValue;

import com.google.common.base.Function;

public final class JsExp {

    private JsExp() {
    }

    static final Function<JsExpression, String> PRINT = new Function<JsExpression, String>() {
        @Override
        public String apply(JsExpression input) {
            val sb = new StringBuilder();
            input.print(sb);
            return sb.toString();
        }
    };

    public static JsNumber wrap(int value) {
        return new JsNumber.IntLiteral(value);
    }

    public static JsNumber wrap(double value) {
        return new JsNumber.DoubleLiteral(value);
    }

    public static JsString wrap(String value) {
        return new JsString(value);
    }

    static JsExpression raw(final String value) {
        return new JsExpression() {
            @Override
            public void print(StringBuilder sb) {
                sb.append(value);
            }
        };
    }

    static String escape(String value) {
        return JSONValue.toJSONString(value);
    }

}
