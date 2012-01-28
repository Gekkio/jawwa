package fi.jawsy.jawwa.zk.raphaeljs;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.val;

public interface JsColor extends JsExpression {

    @RequiredArgsConstructor
    static class Color implements JsColor {
        @NonNull
        private final String value;

        @Override
        public String print() {
            return JsExp.escape(value);
        }
    }

    @RequiredArgsConstructor
    static class Rgb implements JsColor {

        private final JsNumber r;
        private final JsNumber g;
        private final JsNumber b;

        @Override
        public String print() {
            val sb = new StringBuilder();
            sb.append("\"rgb(");
            sb.append(r.print());
            sb.append(',');
            sb.append(g.print());
            sb.append(',');
            sb.append(b.print());
            sb.append(")\"");
            return sb.toString();
        }

    }

    @RequiredArgsConstructor
    static class Rgba implements JsColor {

        private final JsNumber r;
        private final JsNumber g;
        private final JsNumber b;
        private final JsNumber a;

        @Override
        public String print() {
            val sb = new StringBuilder();
            sb.append("\"rgb(");
            sb.append(r.print());
            sb.append(',');
            sb.append(g.print());
            sb.append(',');
            sb.append(b.print());
            sb.append(',');
            sb.append(a.print());
            sb.append(")\"");
            return sb.toString();
        }

    }

    @RequiredArgsConstructor
    static class Hsb implements JsColor {

        private final JsNumber r;
        private final JsNumber g;
        private final JsNumber b;

        @Override
        public String print() {
            val sb = new StringBuilder();
            sb.append("\"rgb(");
            sb.append(r.print());
            sb.append(',');
            sb.append(g.print());
            sb.append(',');
            sb.append(b.print());
            sb.append(")\"");
            return sb.toString();
        }

    }

    @RequiredArgsConstructor
    static class Hsba implements JsColor {

        private final JsNumber r;
        private final JsNumber g;
        private final JsNumber b;
        private final JsNumber a;

        @Override
        public String print() {
            val sb = new StringBuilder();
            sb.append("\"rgb(");
            sb.append(r.print());
            sb.append(',');
            sb.append(g.print());
            sb.append(',');
            sb.append(b.print());
            sb.append(',');
            sb.append(a.print());
            sb.append(")\"");
            return sb.toString();
        }

    }

    @RequiredArgsConstructor
    static class Hsl implements JsColor {

        private final JsNumber r;
        private final JsNumber g;
        private final JsNumber b;

        @Override
        public String print() {
            val sb = new StringBuilder();
            sb.append("\"rgb(");
            sb.append(r.print());
            sb.append(',');
            sb.append(g.print());
            sb.append(',');
            sb.append(b.print());
            sb.append(")\"");
            return sb.toString();
        }

    }

    @RequiredArgsConstructor
    static class Hsla implements JsColor {

        private final JsNumber r;
        private final JsNumber g;
        private final JsNumber b;
        private final JsNumber a;

        @Override
        public String print() {
            val sb = new StringBuilder();
            sb.append("\"rgb(");
            sb.append(r.print());
            sb.append(',');
            sb.append(g.print());
            sb.append(',');
            sb.append(b.print());
            sb.append(',');
            sb.append(a.print());
            sb.append(")\"");
            return sb.toString();
        }

    }

}
