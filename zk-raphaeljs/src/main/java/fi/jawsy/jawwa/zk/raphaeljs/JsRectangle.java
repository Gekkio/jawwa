package fi.jawsy.jawwa.zk.raphaeljs;

import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.val;

@EqualsAndHashCode(callSuper = true)
@ToString
public final class JsRectangle extends JsElement<JsRectangle> {

    private final JsNumber x;
    private final JsNumber y;
    private final JsNumber width;
    private final JsNumber height;
    private final JsNumber r;

    public JsRectangle(JsPaper paper, JsNumber x, JsNumber y, JsNumber width, JsNumber height, JsNumber r) {
        super(paper);
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.r = r;

        val sb = new StringBuilder();
        sb.append("var ");
        sb.append(this.name);
        sb.append("=");
        sb.append("paper.rect(");
        x.print(sb);
        sb.append(',');
        y.print(sb);
        sb.append(',');
        width.print(sb);
        sb.append(',');
        height.print(sb);
        sb.append(',');
        r.print(sb);
        sb.append(')');

        paper.addStatement(JsExp.raw(sb.toString()));
    }

    @Override
    protected JsRectangle me() {
        return this;
    }

}
