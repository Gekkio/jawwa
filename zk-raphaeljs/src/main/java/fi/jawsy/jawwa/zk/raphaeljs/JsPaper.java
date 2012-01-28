package fi.jawsy.jawwa.zk.raphaeljs;

import java.util.List;

import lombok.val;

import com.google.common.base.Joiner;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;

public final class JsPaper {

    private static final JsNumber WIDTH = new JsNumber.NumberExpression("paper.width");
    private static final JsNumber HEIGHT = new JsNumber.NumberExpression("paper.height");

    private int varCounter = 1;

    private final List<JsExpression> statements = Lists.newArrayList();

    JsPaper() {
    }

    void addStatement(JsExpression statement) {
        statements.add(statement);
    }

    public JsNumber width() {
        return WIDTH;
    }

    public JsNumber height() {
        return HEIGHT;
    }

    public void clear() {
        addStatement(JsExp.raw("paper.clear()"));
    }

    public JsCircle circle(int x, int y, int r) {
        return new JsCircle(this, JsExp.wrap(x), JsExp.wrap(y), JsExp.wrap(r));
    }

    public JsCircle circle(JsNumber x, JsNumber y, JsNumber r) {
        return new JsCircle(this, x, y, r);
    }

    public JsEllipse ellipse(int x, int y, int rx, int ry) {
        return new JsEllipse(this, JsExp.wrap(x), JsExp.wrap(y), JsExp.wrap(rx), JsExp.wrap(ry));
    }

    public JsEllipse ellipse(JsNumber x, JsNumber y, JsNumber rx, JsNumber ry) {
        return new JsEllipse(this, x, y, rx, ry);
    }

    public JsRectangle rect(int x, int y, int width, int height, int r) {
        return new JsRectangle(this, JsExp.wrap(x), JsExp.wrap(y), JsExp.wrap(width), JsExp.wrap(height), JsExp.wrap(r));
    }

    public JsRectangle rect(JsNumber x, JsNumber y, JsNumber width, JsNumber height, JsNumber r) {
        return new JsRectangle(this, x, y, width, height, r);
    }

    public JsText text(int x, int y, String text) {
        return new JsText(this, JsExp.wrap(x), JsExp.wrap(y), JsExp.wrap(text));
    }

    public JsText text(JsNumber x, JsNumber y, JsString text) {
        return new JsText(this, x, y, text);
    }

    String nextVariableName() {
        val name = "va" + varCounter;
        varCounter += 1;
        return name;
    }

    String toJavascript() {
        return Joiner.on(';').join(Iterables.transform(statements, JsExp.PRINT));
    }

}
