package com.nhnacademy.jaehyeon.exercise10_2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/*
 * https://math.hws.edu/javanotes/c10/exercises.html
 * 사용자로부터 입력을 받아 음수가 아닌 정수 집합을 나타내는 두 개의 TreeSet을 생성합니다.
 * 입력을 파싱하고 입력 오류를 처리합니다.
 * 입력된 연산자 (+, *, -)에 따라 해당 연산을 수행하고 결과를 출력합니다.
 * 프로그램은 여러 입력 라인을 처리하며 구문 오류가 발생하면 오류를 보고하고 다음 입력 라인으로 이동합니다.
 * 프로그램은 Java를 사용하여 Set 인터페이스의 메서드를 활용하여 합집합, 교집합 및 차이 연산을 수행합니다. 사용자가 요청한 연산에 따라 결과 집합을 생성하고 출력합니다.
 */
public class Exercise10_2 {


    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            String expression = inputString(br);

            ExpressionProcessing expressionProcessing = new ExpressionProcessing(expression);
            SetCalculate setCalculate = new SetCalculate(expressionProcessing);
            Set<Integer> result = setCalculate.operationSet();
            System.out.println(toStringResult(result, expressionProcessing));


        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public static String inputString(BufferedReader br) throws IOException {
        String inputExpression;
        while (true) {
            try {
                inputExpression = br.readLine();
                verify(inputExpression);
                break;
            } catch (NotMatchExpression e) {
                System.out.println(e.getMessage());
            }
        }
        return inputExpression;
    }

    public static void verify(String inputStr) throws NotMatchExpression {
        String regex =
                "\\[\\s*\\d+\\s*(,\\s*\\d+\\s*)*\\]\\s*[+*-]\\s*\\[\\s*\\d+\\s*(,\\s*\\d+\\s*)*\\]";
/*        "\\[" + //    \\[ , \\] : [와 ] 를 나타낸다
                "\\s*" +       //     \\s* : 공백문자의 0 회 이상의 발생을 나타냄
                "\\d+" +       //      \\d : 하나 이상의 숫자 (0-9) 를 나타냄
                "\\s*" +       //       \\s* : 공백문자의 0 회 이상의 발생을 나타냄
                "(,\\s*\\d+\\s*)*]";    // , 쉼표로 구분된 숫자를 나타낸다.
        //  쉼표로 구분된 숫자가 0 회 이상 반복되는 것을 나타낸다 따라서 숫자와 쉼표의 반복되는 패턴을 나타냄
*/
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(inputStr);
        if (!matcher.matches()) {
            throw new NotMatchExpression("올바른 표현식을 입력하세요 ");
        }
    }


    public static String toStringResult(Set<Integer> result, ExpressionProcessing expressionProcessing) {
        StringBuilder sb = new StringBuilder();
        sb.append(operationName(expressionProcessing)).append(" 계산결과 : ").append(result.toString());
        return sb.toString();
    }

    public static String operationName(ExpressionProcessing expressionProcessing) {
        String result = "";
        switch (expressionProcessing.getOperator()) {
            case "*":
                result = "교집합";
                break;
            case "+":
                result = "합집합";
                break;
            case "-":
                result = "차집합";
                break;
        }

        return result;
    }
}
