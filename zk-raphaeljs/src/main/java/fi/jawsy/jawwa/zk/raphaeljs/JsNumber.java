package fi.jawsy.jawwa.zk.raphaeljs;

import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import lombok.val;

public interface JsNumber extends JsExpression {

    @RequiredArgsConstructor(access = AccessLevel.PACKAGE)
    @EqualsAndHashCode
    @ToString(includeFieldNames = false)
    static class IntLiteral implements JsNumber {

        private final int value;

        public JsNumber plus(int value) {
            return new IntLiteral(this.value + value);
        }

        @Override
        public JsNumber minus(int value) {
            return new IntLiteral(this.value - value);
        }

        @Override
        public JsNumber divide(int value) {
            return new IntLiteral(this.value / value);
        }

        @Override
        public JsNumber multiply(int value) {
            return new IntLiteral(this.value * value);
        }

        @Override
        public JsNumber plus(double value) {
            return new DoubleLiteral(this.value + value);
        }

        @Override
        public JsNumber minus(double value) {
            return new DoubleLiteral(this.value - value);
        }

        @Override
        public JsNumber divide(double value) {
            return new DoubleLiteral(this.value / value);
        }

        @Override
        public JsNumber multiply(double value) {
            return new DoubleLiteral(this.value * value);
        }

        @Override
        public String print() {
            return String.valueOf(value);
        }
    }

    @RequiredArgsConstructor(access = AccessLevel.PACKAGE)
    @EqualsAndHashCode
    @ToString(includeFieldNames = false)
    static class DoubleLiteral implements JsNumber {

        private final double value;

        @Override
        public JsNumber plus(int value) {
            return new DoubleLiteral(this.value + value);
        }

        @Override
        public JsNumber minus(int value) {
            return new DoubleLiteral(this.value - value);
        }

        @Override
        public JsNumber divide(int value) {
            return new DoubleLiteral(this.value / value);
        }

        @Override
        public JsNumber multiply(int value) {
            return new DoubleLiteral(this.value * value);
        }

        @Override
        public JsNumber plus(double value) {
            return new DoubleLiteral(this.value + value);
        }

        @Override
        public JsNumber minus(double value) {
            return new DoubleLiteral(this.value - value);
        }

        @Override
        public JsNumber divide(double value) {
            return new DoubleLiteral(this.value / value);
        }

        @Override
        public JsNumber multiply(double value) {
            return new DoubleLiteral(this.value * value);
        }

        @Override
        public String print() {
            return String.valueOf(value);
        }
    }

    @RequiredArgsConstructor(access = AccessLevel.PACKAGE)
    @EqualsAndHashCode
    @ToString(includeFieldNames = false)
    static class NumberExpression implements JsNumber {

        private final String value;

        private JsNumber operator(char operator, int value) {
            val sb = new StringBuilder();
            sb.append('(');
            sb.append(this.value);
            sb.append(operator);
            sb.append(value);
            sb.append(')');
            return new NumberExpression(sb.toString());
        }

        private JsNumber operator(char operator, double value) {
            val sb = new StringBuilder();
            sb.append('(');
            sb.append(this.value);
            sb.append(operator);
            sb.append(value);
            sb.append(')');
            return new NumberExpression(sb.toString());
        }

        @Override
        public JsNumber plus(int value) {
            return operator('+', value);
        }

        @Override
        public JsNumber minus(int value) {
            return operator('-', value);
        }

        @Override
        public JsNumber divide(int value) {
            return operator('/', value);
        }

        @Override
        public JsNumber multiply(int value) {
            return operator('*', value);
        }

        @Override
        public JsNumber plus(double value) {
            return operator('+', value);
        }

        @Override
        public JsNumber minus(double value) {
            return operator('-', value);
        }

        @Override
        public JsNumber divide(double value) {
            return operator('/', value);
        }

        @Override
        public JsNumber multiply(double value) {
            return operator('*', value);
        }

        @Override
        public String print() {
            return value;
        }

    }

    JsNumber plus(int value);

    JsNumber minus(int value);

    JsNumber divide(int value);

    JsNumber multiply(int value);

    JsNumber plus(double value);

    JsNumber minus(double value);

    JsNumber divide(double value);

    JsNumber multiply(double value);

}
