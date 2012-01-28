package fi.jawsy.jawwa.zk.raphaeljs;

import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.val;

@EqualsAndHashCode(callSuper = true)
@ToString
public final class JsCircle extends JsElement<JsCircle> {

    private final JsNumber x;
    private final JsNumber y;
    private final JsNumber r;

    JsCircle(JsPaper paper, JsNumber x, JsNumber y, JsNumber r) {
        super(paper);
        this.x = x;
        this.y = y;
        this.r = r;

        val sb = new StringBuilder();
        sb.append("var ");
        sb.append(this.name);
        sb.append("=");
        sb.append("paper.circle(");
        sb.append(x.print());
        sb.append(',');
        sb.append(y.print());
        sb.append(',');
        sb.append(r.print());
        sb.append(')');

        paper.addStatement(JsExp.raw(sb.toString()));
    }

    public JsCircle fill(JsColor color) {
        return attr("fill", color);
    }

    public JsCircle fillOpacity(double value) {
        return attr("fill-opacity", JsExp.wrap(value));
    }

    public JsCircle fillOpacity(int value) {
        return attr("fill-opacity", JsExp.wrap(value));
    }

    public JsCircle fillOpacity(JsNumber value) {
        return attr("fill-opacity", value);
    }

    public JsCircle radius(double value) {
        return attr("radius", JsExp.wrap(value));
    }

    public JsCircle radius(int value) {
        return attr("radius", JsExp.wrap(value));
    }

    public JsCircle radius(JsNumber value) {
        return attr("radius", value);
    }

    @Override
    protected JsCircle me() {
        return this;
    }

}
