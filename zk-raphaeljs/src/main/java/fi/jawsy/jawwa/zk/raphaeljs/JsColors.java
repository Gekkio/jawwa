package fi.jawsy.jawwa.zk.raphaeljs;

import com.google.common.base.Preconditions;

public final class JsColors {

    public static JsColor rgb(int r, int g, int b) {
        Preconditions.checkArgument((0 <= r) && (r <= 255), "r is not in range 0 <= r <= 255");
        Preconditions.checkArgument((0 <= g) && (g <= 255), "g is not in range 0 <= g <= 255");
        Preconditions.checkArgument((0 <= b) && (b <= 255), "b is not in range 0 <= b <= 255");
        return new JsColor.Rgb(JsExp.wrap(r), JsExp.wrap(g), JsExp.wrap(b));
    }

}
