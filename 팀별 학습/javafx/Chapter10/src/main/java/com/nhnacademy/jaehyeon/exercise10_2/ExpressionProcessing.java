package com.nhnacademy.jaehyeon.exercise10_2;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class ExpressionProcessing {

    private final String expression;
    private String operator;
    private Set<Integer> firstNumberSet;
    private Set<Integer> secondNumberSet;

    public ExpressionProcessing(String expression) {
        this.expression = expression;
        this.firstNumberSet = new TreeSet<>();
        this.secondNumberSet = new TreeSet<>();
        extractOperator();
        extractNumberSet();
    }

    public void extractOperator() {
        Pattern pattern = Pattern.compile("[*+-]");
        Matcher matcher = pattern.matcher(this.expression);
        if (matcher.find()) {
            this.operator = matcher.group();
        }

    }

    public void extractNumberSet() {
        String regex = "\\s*[*+-]\\s*";
        /* \\s* : 공백문자가 0회이상 나타나는것을 의미함
         *  [*+-] : *,+,- 중 하나를 의미함
         */
        String[] parts = this.expression.split(regex);
        setNumber(parts);
    }

    private void setNumber(String[] parts) {
        List<Set<Integer>> list = Arrays.stream(parts)
                .map(s -> s.replaceAll("\\[", "")
                        .replaceAll("\\]", ""))
                .map(s -> s.split(","))
                .map(strings -> Arrays.stream(strings)
                        .map(String::trim)
                        .map(Integer::parseInt).collect(Collectors.toSet()))
                .collect(Collectors.toList());

        this.firstNumberSet = list.get(0);
        this.secondNumberSet = list.get(1);

    }

    public String getOperator() {
        return operator;
    }

    public Set<Integer> getFirstNumberSet() {
        return firstNumberSet;
    }

    public Set<Integer> getSecondNumberSet() {
        return secondNumberSet;
    }
}
