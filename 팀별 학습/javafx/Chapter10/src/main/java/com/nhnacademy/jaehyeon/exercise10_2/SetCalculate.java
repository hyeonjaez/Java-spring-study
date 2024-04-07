package com.nhnacademy.jaehyeon.exercise10_2;

import java.util.Set;

public class SetCalculate {
    private final ExpressionProcessing expressionProcessing;

    public SetCalculate(ExpressionProcessing expressionProcessing) {
        this.expressionProcessing = expressionProcessing;
    }

    public Set<Integer> operationSet() {

        switch (this.expressionProcessing.getOperator()) {
            case "+":
                return calculateUnion(expressionProcessing.getFirstNumberSet(),
                        expressionProcessing.getSecondNumberSet());

            case "*":
                return calculateIntersection(expressionProcessing.getFirstNumberSet(),
                        expressionProcessing.getSecondNumberSet());

            case "-":
                return calculateDifference(expressionProcessing.getFirstNumberSet(),
                        expressionProcessing.getSecondNumberSet());

        }

        return null;
    }

    private Set<Integer> calculateUnion(Set<Integer> first, Set<Integer> second) {
        first.addAll(second);
        return first;
    }

    private Set<Integer> calculateIntersection(Set<Integer> first, Set<Integer> second) {
        first.retainAll(second);
        return first;
    }

    private Set<Integer> calculateDifference(Set<Integer> first, Set<Integer> second) {
        first.removeAll(second);
        return first;
    }
}
