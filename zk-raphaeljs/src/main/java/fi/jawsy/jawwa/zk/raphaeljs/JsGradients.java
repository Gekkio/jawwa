package fi.jawsy.jawwa.zk.raphaeljs;

import com.google.common.collect.ImmutableList;

import fi.jawsy.jawwa.zk.raphaeljs.JsGradient.OffsetPoint;
import fi.jawsy.jawwa.zk.raphaeljs.JsGradient.Point;

public final class JsGradients {

    private JsGradients() {
    }

    public JsGradient.Point point(JsColor color) {
        return new Point(color);
    }

    public JsGradient.Point point(JsColor color, JsNumber offset) {
        return new OffsetPoint(color, offset);
    }

    public JsGradient.Point point(JsColor color, int offset) {
        return new OffsetPoint(color, JsExp.wrap(offset));
    }

    public JsGradient.Point point(JsColor color, double offset) {
        return new OffsetPoint(color, JsExp.wrap(offset));
    }

    public JsGradient linear(int angle, JsColor startColor, JsColor endColor) {
        return new JsGradient.Linear(JsExp.wrap(angle), startColor, ImmutableList.<JsGradient.Point> of(), endColor);
    }

    public JsGradient linear(JsNumber angle, JsColor startColor, JsColor endColor) {
        return new JsGradient.Linear(angle, startColor, ImmutableList.<JsGradient.Point> of(), endColor);
    }

    public JsGradient linear(int angle, JsColor startColor, JsColor endColor, JsGradient.Point... points) {
        return new JsGradient.Linear(JsExp.wrap(angle), startColor, ImmutableList.copyOf(points), endColor);
    }

    public JsGradient linear(JsNumber angle, JsColor startColor, JsColor endColor, JsGradient.Point... points) {
        return new JsGradient.Linear(angle, startColor, ImmutableList.copyOf(points), endColor);
    }

}
