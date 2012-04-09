package fi.jawsy.jawwa.zk.raphaeljs;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;

public interface JsColor extends JsStringExpression {

    @RequiredArgsConstructor
    static class Color implements JsColor {
        @NonNull
        private final String value;

        @Override
        public void printInString(StringBuilder sb) {
            sb.append(JsExp.escape(value));
        }
    }

    @RequiredArgsConstructor
    static class Rgb implements JsColor {

        private final JsNumber r;
        private final JsNumber g;
        private final JsNumber b;

        @Override
        public void printInString(StringBuilder sb) {
            sb.append("rgb(");
            r.printInString(sb);
            sb.append(',');
            g.printInString(sb);
            sb.append(',');
            b.printInString(sb);
            sb.append(")\"");
        }

    }

    @RequiredArgsConstructor
    static class Rgba implements JsColor {

        private final JsNumber r;
        private final JsNumber g;
        private final JsNumber b;
        private final JsNumber a;

        @Override
        public void printInString(StringBuilder sb) {
            sb.append("rgba(");
            r.printInString(sb);
            sb.append(',');
            g.printInString(sb);
            sb.append(',');
            b.printInString(sb);
            sb.append(',');
            a.printInString(sb);
            sb.append(")");
        }

    }

    @RequiredArgsConstructor
    static class Hsb implements JsColor {

        private final JsNumber h;
        private final JsNumber s;
        private final JsNumber b;

        @Override
        public void printInString(StringBuilder sb) {
            sb.append("hsb(");
            h.printInString(sb);
            sb.append(',');
            s.printInString(sb);
            sb.append(',');
            b.printInString(sb);
            sb.append(")");
        }

    }

    @RequiredArgsConstructor
    static class Hsba implements JsColor {

        private final JsNumber h;
        private final JsNumber s;
        private final JsNumber b;
        private final JsNumber a;

        @Override
        public void printInString(StringBuilder sb) {
            sb.append("hsba(");
            h.printInString(sb);
            sb.append(',');
            s.printInString(sb);
            sb.append(',');
            b.printInString(sb);
            sb.append(',');
            a.printInString(sb);
            sb.append(")");
        }

    }

    @RequiredArgsConstructor
    static class Hsl implements JsColor {

        private final JsNumber h;
        private final JsNumber s;
        private final JsNumber l;

        @Override
        public void printInString(StringBuilder sb) {
            sb.append("hsl(");
            h.printInString(sb);
            sb.append(',');
            s.printInString(sb);
            sb.append(',');
            l.printInString(sb);
            sb.append(")");
        }

    }

    @RequiredArgsConstructor
    static class Hsla implements JsColor {

        private final JsNumber h;
        private final JsNumber s;
        private final JsNumber l;
        private final JsNumber a;

        @Override
        public void printInString(StringBuilder sb) {
            sb.append("\"hsla(");
            h.printInString(sb);
            sb.append(',');
            s.printInString(sb);
            sb.append(',');
            l.printInString(sb);
            sb.append(',');
            a.printInString(sb);
            sb.append(")\"");
        }

    }

}
