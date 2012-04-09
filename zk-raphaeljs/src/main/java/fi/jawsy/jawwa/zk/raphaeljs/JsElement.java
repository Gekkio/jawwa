package fi.jawsy.jawwa.zk.raphaeljs;

import java.util.Map;

import lombok.val;

import com.google.common.collect.Maps;

public abstract class JsElement<S extends JsElement<S>> {

    private class AttrExpression implements JsExpression {

        private final Map<String, JsExpression> attrs = Maps.newLinkedHashMap();

        @Override
        public void print(StringBuilder sb) {
            sb.append(name);
            sb.append('.');
            sb.append("attr({");
            val i = attrs.entrySet().iterator();
            while (i.hasNext()) {
                val entry = i.next();
                sb.append('"');
                sb.append(entry.getKey());
                sb.append("\":");
                entry.getValue().print(sb);
                if (i.hasNext())
                    sb.append(',');
            }
            sb.append("})");
        }

    }

    protected AttrExpression attrStatement;

    protected final String name;
    protected final JsPaper paper;

    public JsElement(JsPaper paper) {
        this.name = paper.nextVariableName();
        this.paper = paper;
    }

    protected abstract S me();

    protected S attr(String attrName, JsExpression attrValue) {
        if (attrStatement == null) {
            attrStatement = new AttrExpression();
            paper.addStatement(attrStatement);
        }
        attrStatement.attrs.put(attrName, attrValue);
        return me();
    }

    protected S attr(String attrName, final JsStringExpression attrValue) {
        if (attrStatement == null) {
            attrStatement = new AttrExpression();
            paper.addStatement(attrStatement);
        }
        attrStatement.attrs.put(attrName, new JsExpression() {
            @Override
            public void print(StringBuilder sb) {
                sb.append('\"');
                attrValue.printInString(sb);
                sb.append('\"');
            }
        });
        return me();
    }

    public S cursor(JsString value) {
        return attr("cursor", value);
    }

    public S cursor(String value) {
        return attr("cursor", JsExp.wrap(value));
    }

    public S fill(JsColor color) {
        return attr("fill", color);
    }

    public S fillOpacity(double value) {
        return attr("fill-opacity", JsExp.wrap(value));
    }

    public S fillOpacity(int value) {
        return attr("fill-opacity", JsExp.wrap(value));
    }

    public S fillOpacity(JsNumber value) {
        return attr("fill-opacity", value);
    }

    public S opacity(double value) {
        return attr("opacity", JsExp.wrap(value));
    }

    public S opacity(int value) {
        return attr("opacity", JsExp.wrap(value));
    }

    public S opacity(JsNumber value) {
        return attr("opacity", value);
    }

    public S stroke(JsColor color) {
        return attr("stroke", color);
    }

    public S strokeMiterLimit(double value) {
        return attr("stroke-miterlimit", JsExp.wrap(value));
    }

    public S strokeMiterLimit(int value) {
        return attr("stroke-miterlimit", JsExp.wrap(value));
    }

    public S strokeMiterLimit(JsNumber value) {
        return attr("stroke-miterlimit", value);
    }

    public S strokeOpacity(double value) {
        return attr("stroke-opacity", JsExp.wrap(value));
    }

    public S strokeOpacity(int value) {
        return attr("stroke-opacity", JsExp.wrap(value));
    }

    public S strokeOpacity(JsNumber value) {
        return attr("stroke-opacity", value);
    }

    public S strokeWidth(double value) {
        return attr("stroke-width", JsExp.wrap(value));
    }

    public S strokeWidth(int value) {
        return attr("stroke-width", JsExp.wrap(value));
    }

    public S strokeWidth(JsNumber value) {
        return attr("stroke-width", value);
    }

    public S title(JsString value) {
        return attr("title", value);
    }

    public S title(String value) {
        return attr("title", JsExp.wrap(value));
    }

}
