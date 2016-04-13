package ua.goit.gojava.expression;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Alex Korneyko, 2016
 * @version 1.0 12.04.2016
 *          Выражение, состоящее из обобщённых элементов (коллекция elementSet),
 *          которые по смыслу могут быть как большими целыми, так и операциями
 *          или другими символами математических формул
 */
public class Expression {

    //Коллекция, которая и является выражением
    public List<ExpressionElement> elementSet = new ArrayList<>();


    @Override
    public String toString() {

        String result = "";

        for (ExpressionElement element : elementSet) {
            result += element.toString();
        }

        return result;
    }

    @Override
    public Expression clone() {

        final Expression expression2 = new Expression();

        for (ExpressionElement element : elementSet) {
            expression2.elementSet.add(element.clone());
        }
        return expression2;
    }

    @Override
    public boolean equals(Object anotherExpression) {

        Expression expression2;

        if (anotherExpression == null) {
            return false;
        }

        if (anotherExpression instanceof Expression) {
            expression2 = (Expression) anotherExpression;
        } else {
            return false;
        }

        if (this.elementSet.size() != expression2.elementSet.size()) {
            return false;
        }

        for (int i = 0; i < this.elementSet.size(); i++) {
            if (!this.elementSet.get(i).equals(expression2.elementSet.get(i))) {
                return false;
            }
        }

        return true;
    }
}
