package fi.jawsy.jawwa.zk.raphaeljs;

import lombok.RequiredArgsConstructor;
import lombok.val;

import com.google.common.collect.ImmutableList;

public interface JsGradient {

    void printInString(StringBuilder sb);

    @RequiredArgsConstructor
    public static class Point {
        public final JsColor color;

        public void printInString(StringBuilder sb) {
            color.printInString(sb);
        }
    }

    public static class OffsetPoint extends Point {
        public final JsNumber offset;

        public OffsetPoint(JsColor color, JsNumber offset) {
            super(color);
            this.offset = offset;
        }

        public void printInString(StringBuilder sb) {
            super.printInString(sb);
            sb.append(':');
            offset.print(sb);
        }
    }

    @RequiredArgsConstructor
    static class Linear implements JsGradient {

        public final JsNumber angle;

        public final JsColor startColor;

        public final ImmutableList<Point> points;

        public final JsColor endColor;

        @Override
        public void printInString(StringBuilder sb) {
            angle.printInString(sb);
            sb.append('-');
            startColor.printInString(sb);
            for (val point : points) {
                sb.append('-');
                point.printInString(sb);
            }
            sb.append('-');
            endColor.printInString(sb);
        }

    }

}
