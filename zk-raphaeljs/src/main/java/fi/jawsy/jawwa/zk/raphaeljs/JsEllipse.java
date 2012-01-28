package fi.jawsy.jawwa.zk.raphaeljs;

import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.val;

@EqualsAndHashCode(callSuper = true)
@ToString
public final class JsEllipse extends JsElement<JsEllipse> {

    private final JsNumber x;
    private final JsNumber y;
    private final JsNumber rx;
    private final JsNumber ry;

    public JsEllipse(JsPaper paper, JsNumber x, JsNumber y, JsNumber rx, JsNumber ry) {
        super(paper);
        this.x = x;
        this.y = y;
        this.rx = rx;
        this.ry = ry;

        val sb = new StringBuilder();
        sb.append("var ");
        sb.append(this.name);
        sb.append("=");
        sb.append("paper.ellipse(");
        sb.append(x.print());
        sb.append(',');
        sb.append(y.print());
        sb.append(',');
        sb.append(rx.print());
        sb.append(',');
        sb.append(ry.print());
        sb.append(')');

        paper.addStatement(JsExp.raw(sb.toString()));
    }

    @Override
    protected JsEllipse me() {
        return this;
    }

}
