package fi.jawsy.jawwa.zk.raphaeljs;

import org.zkoss.json.JSONValue;

import com.google.common.base.Function;

final class JsExp {

    private JsExp() {
    }

    static final Function<JsExpression, String> PRINT = new Function<JsExpression, String>() {
        @Override
        public String apply(JsExpression input) {
            return input.print();
        }
    };

    static JsNumber wrap(int value) {
        return new JsNumber.IntLiteral(value);
    }

    static JsNumber wrap(double value) {
        return new JsNumber.DoubleLiteral(value);
    }

    static JsExpression raw(final String value) {
        return new JsExpression() {
            @Override
            public String print() {
                return value;
            }
        };
    }

    static String escape(String value) {
        return JSONValue.toJSONString(value);
    }

}
