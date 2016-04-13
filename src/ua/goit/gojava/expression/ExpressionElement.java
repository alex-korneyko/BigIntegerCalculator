package ua.goit.gojava.expression;

import ua.goit.gojava.big.BigInteger;

import static ua.goit.gojava.expression.ExpressionElementType.*;

/**
 * @author Alexandr Korneyko, 2016
 * @version 1.0 12.04.2016
 *          Обобщённый элемен выражения.
 *          Может предсавлять собой как число, так и любой другой
 *          символ математических формул.
 *          Так-же имеет свойство приоритетности содержащейся операции,
 *          для чисел - 0.
 *          В выражении может быть любое количество элементов
 */
public class ExpressionElement implements Comparable<ExpressionElement> {

    //Если этот элемент - число, то здесь его значение, если нет, то 0
    final public BigInteger value;

    //Тип элемента. Если число, то здесь BIG_INT, если операция, то PLUS, MINUS ...
    final public ExpressionElementType elementType;

    //Приоритетность операций. 0 - для чисел, 1 - плюс/минус, 2 - умножить/делить, 3 - степень/корень
    final public int operationPriority;

    //Конструктор, если элемент - операция
    public ExpressionElement(ExpressionElementType elementType) {
        this.value = new BigInteger("0");
        this.elementType = elementType;

        if (elementType == PLUS || elementType == MINUS) {
            operationPriority = 1;
        } else if (elementType == MULTIPLY || elementType == DIVIDE) {
            operationPriority = 2;
        } else {
            operationPriority = 3;
        }
    }

    //Конструктор, если элемент число
    public ExpressionElement(BigInteger value) {
        this.value = value;
        this.elementType = BIG_INT;

        operationPriority = 0;
    }

    @Override
    public String toString() {

        return this.elementType == BIG_INT ? this.value.toString() : elementType.toString();
    }

    @Override
    public ExpressionElement clone() {

        return new ExpressionElement(new BigInteger(this.value.toString()));
    }

    @Override
    public boolean equals(Object anotherElement) {

        final ExpressionElement element2;

        if (anotherElement != null && anotherElement instanceof ExpressionElement) {
            element2 = (ExpressionElement) anotherElement;
        } else {
            return false;
        }

        return this.elementType == element2.elementType && this.value.equals(element2.value);
    }

    @Override
    public int compareTo(ExpressionElement element) {
        if (element == null) {
            return 1;
        }

        if (this.operationPriority == 0 && element.operationPriority == 0) {
            return this.value.compareTo(element.value);
        }

        return this.operationPriority - element.operationPriority;
    }
}
