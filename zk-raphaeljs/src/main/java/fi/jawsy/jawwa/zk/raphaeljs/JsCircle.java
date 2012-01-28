package fi.jawsy.jawwa.zk.raphaeljs;

import java.util.Map;

import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.val;

import com.google.common.collect.Maps;

@EqualsAndHashCode
@ToString
public final class JsCircle {

    private final JsPaper paper;
    private final JsNumber w;
    private final JsNumber h;
    private final JsNumber r;
    private final String name;

    private AttrExpression attrStatement;

    private class AttrExpression implements JsExpression {

        private final Map<String, JsExpression> attrs = Maps.newLinkedHashMap();

        @Override
        public String print() {
            val sb = new StringBuilder();
            sb.append(name);
            sb.append('.');
            sb.append("attr({");
            val i = attrs.entrySet().iterator();
            while (i.hasNext()) {
                val entry = i.next();
                sb.append('"');
                sb.append(entry.getKey());
                sb.append("\":");
                sb.append(entry.getValue().print());
                if (i.hasNext())
                    sb.append(',');
            }
            sb.append("})");
            return sb.toString();
        }

    }

    JsCircle(JsPaper paper, JsNumber w, JsNumber h, JsNumber r) {
        this.paper = paper;
        this.w = w;
        this.h = h;
        this.r = r;

        this.name = paper.nextVariableName();

        val sb = new StringBuilder();
        sb.append("var ");
        sb.append(this.name);
        sb.append("=");
        sb.append("paper.circle(");
        sb.append(w.print());
        sb.append(',');
        sb.append(h.print());
        sb.append(',');
        sb.append(r.print());
        sb.append(')');

        paper.addStatement(JsExp.raw(sb.toString()));
    }

    public JsCircle opacity(int value) {
        return attr("opacity", JsExp.wrap(value));
    }

    public JsCircle opacity(double value) {
        return attr("opacity", JsExp.wrap(value));
    }

    public JsCircle opacity(JsNumber value) {
        return attr("opacity", value);
    }

    public JsCircle radius(int value) {
        return attr("radius", JsExp.wrap(value));
    }

    public JsCircle radius(double value) {
        return attr("radius", JsExp.wrap(value));
    }

    public JsCircle radius(JsNumber value) {
        return attr("radius", value);
    }

    public JsCircle stroke(JsColor color) {
        return attr("stroke", color);
    }

    private JsCircle attr(String attrName, JsExpression attrValue) {
        if (attrStatement == null) {
            attrStatement = new AttrExpression();
            paper.addStatement(attrStatement);
        }
        attrStatement.attrs.put(attrName, attrValue);
        return this;
    }

}
