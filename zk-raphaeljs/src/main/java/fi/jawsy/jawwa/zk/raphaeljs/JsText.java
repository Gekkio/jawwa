package fi.jawsy.jawwa.zk.raphaeljs;

import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import lombok.val;

@EqualsAndHashCode(callSuper = true)
@ToString
public final class JsText extends JsElement<JsText> {

    @RequiredArgsConstructor
    public static enum Anchor implements JsExpression {
        END("end"), MIDDLE("middle"), START("start");

        private final String value;

        @Override
        public void print(StringBuilder sb) {
            sb.append(value);
        }
    }

    private final JsNumber x;
    private final JsNumber y;
    private final JsString text;

    public JsText(JsPaper paper, JsNumber x, JsNumber y, JsString text) {
        super(paper);
        this.x = x;
        this.y = y;
        this.text = text;

        val sb = new StringBuilder();
        sb.append("var ");
        sb.append(this.name);
        sb.append("=");
        sb.append("paper.text(");
        x.print(sb);
        sb.append(',');
        y.print(sb);
        sb.append(',');
        text.print(sb);
        sb.append(')');

        paper.addStatement(JsExp.raw(sb.toString()));
    }

    public JsText font(JsString value) {
        return attr("font", value);
    }

    public JsText font(String value) {
        return attr("font", JsExp.wrap(value));
    }

    public JsText fontFamily(JsString value) {
        return attr("font-family", value);
    }

    public JsText fontFamily(String value) {
        return attr("font-family", JsExp.wrap(value));
    }

    public JsText fontSize(double value) {
        return attr("font-size", JsExp.wrap(value));
    }

    public JsText fontSize(int value) {
        return attr("font-size", JsExp.wrap(value));
    }

    public JsText fontSize(JsNumber value) {
        return attr("font-size", value);
    }

    public JsText fontWeight(JsString value) {
        return attr("font-weight", value);
    }

    public JsText fontWeight(String value) {
        return attr("font-weight", JsExp.wrap(value));
    }

    @Override
    protected JsText me() {
        return this;
    }

    public JsText textAnchor(Anchor anchor) {
        return attr("text-anchor", anchor);
    }

}
