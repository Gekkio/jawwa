package fi.jawsy.jawwa.zk.raphaeljs;

import com.google.common.base.Preconditions;

public final class JsColors {

    private JsColors() {
    }

    public static JsColor color(String value) {
        return new JsColor.Color(value);
    }

    public static JsColor rgb(int r, int g, int b) {
        Preconditions.checkArgument((0 <= r) && (r <= 255), "r is not in range 0 <= r <= 255");
        Preconditions.checkArgument((0 <= g) && (g <= 255), "g is not in range 0 <= g <= 255");
        Preconditions.checkArgument((0 <= b) && (b <= 255), "b is not in range 0 <= b <= 255");
        return new JsColor.Rgb(JsExp.wrap(r), JsExp.wrap(g), JsExp.wrap(b));
    }

    public static JsColor rgb(JsNumber r, JsNumber g, JsNumber b) {
        return new JsColor.Rgb(r, g, b);
    }

    public static JsColor hsb(double h, double s, double b) {
        Preconditions.checkArgument((0.0 <= h) && (h <= 1.0), "h is not in range 0.0-1.0");
        Preconditions.checkArgument((0.0 <= s) && (s <= 1.0), "s is not in range 0.0-1.0");
        Preconditions.checkArgument((0.0 <= b) && (b <= 1.0), "b is not in range 0.0-1.0");
        return new JsColor.Hsb(JsExp.wrap(h), JsExp.wrap(s), JsExp.wrap(b));
    }

    public static JsColor hsb(JsNumber h, JsNumber s, JsNumber b) {
        return new JsColor.Hsb(h, s, b);
    }

    public static JsColor hsl(double h, double s, double l) {
        Preconditions.checkArgument((0.0 <= h) && (h <= 1.0), "h is not in range 0.0-1.0");
        Preconditions.checkArgument((0.0 <= s) && (s <= 1.0), "s is not in range 0.0-1.0");
        Preconditions.checkArgument((0.0 <= l) && (l <= 1.0), "l is not in range 0.0-1.0");
        return new JsColor.Hsl(JsExp.wrap(h), JsExp.wrap(s), JsExp.wrap(l));
    }

    public static JsColor hsl(JsNumber h, JsNumber s, JsNumber l) {
        return new JsColor.Hsl(h, s, l);
    }

    public static JsColor rgba(int r, int g, int b, double a) {
        Preconditions.checkArgument((0 <= r) && (r <= 255), "r is not in range 0 <= r <= 255");
        Preconditions.checkArgument((0 <= g) && (g <= 255), "g is not in range 0 <= g <= 255");
        Preconditions.checkArgument((0 <= b) && (b <= 255), "b is not in range 0 <= b <= 255");
        Preconditions.checkArgument((0.0 <= a) && (a <= 1.0), "a is not in range 0.0-1.0");
        return new JsColor.Rgba(JsExp.wrap(r), JsExp.wrap(g), JsExp.wrap(b), JsExp.wrap(a));
    }

    public static JsColor rgba(JsNumber r, JsNumber g, JsNumber b, JsNumber a) {
        return new JsColor.Rgba(r, g, b, a);
    }

    public static JsColor hsba(int h, int s, int b, double a) {
        Preconditions.checkArgument((0 <= h) && (h <= 255), "h is not in range 0 <= h <= 255");
        Preconditions.checkArgument((0 <= s) && (s <= 255), "s is not in range 0 <= g <= 255");
        Preconditions.checkArgument((0 <= b) && (b <= 255), "b is not in range 0 <= b <= 255");
        Preconditions.checkArgument((0.0 <= a) && (a <= 1.0), "a is not in range 0.0-1.0");
        return new JsColor.Hsba(JsExp.wrap(h), JsExp.wrap(s), JsExp.wrap(b), JsExp.wrap(a));
    }

    public static JsColor hsba(JsNumber h, JsNumber s, JsNumber b, JsNumber a) {
        return new JsColor.Hsba(h, s, b, a);
    }

    public static JsColor hsla(int h, int s, int l, double a) {
        Preconditions.checkArgument((0 <= h) && (h <= 255), "h is not in range 0 <= h <= 255");
        Preconditions.checkArgument((0 <= s) && (s <= 255), "s is not in range 0 <= g <= 255");
        Preconditions.checkArgument((0 <= l) && (l <= 255), "l is not in range 0 <= b <= 255");
        Preconditions.checkArgument((0.0 <= a) && (a <= 1.0), "a is not in range 0.0-1.0");
        return new JsColor.Hsla(JsExp.wrap(h), JsExp.wrap(s), JsExp.wrap(l), JsExp.wrap(a));
    }

    public static JsColor hsla(JsNumber h, JsNumber s, JsNumber l, JsNumber a) {
        return new JsColor.Hsla(h, s, l, a);
    }

}
