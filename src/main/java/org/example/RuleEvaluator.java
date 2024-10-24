package org.example;
import java.time.LocalDate;
import java.time.Period;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RuleEvaluator {
    public boolean evaluate(Node node, User user) {
        if (node.getType().equals("operand")) {
            return evaluateCondition(node.getValue(), user);
        } else if (node.getType().equals("operator")) {
            boolean leftEval = evaluate(node.getLeft(), user);
            boolean rightEval = evaluate(node.getRight(), user);
            if (node.getValue().equals("AND")) {
                return leftEval && rightEval;
            } else if (node.getValue().equals("OR")) {
                return leftEval || rightEval;
            }
        }
        return false;
    }

    private boolean evaluateCondition(String condition, User user) {
        Pattern pattern = Pattern.compile("(\\w+)\\s*(>|<|=)\\s*([\\w'-]+)");
        Matcher matcher = pattern.matcher(condition);

        if (matcher.matches()) {
            String attribute = matcher.group(1);
            String operator = matcher.group(2);
            String value = matcher.group(3);

            switch (attribute) {
                case "age":
                    int userAge = getAge(user.getDateOfBirth());
                    return compare(userAge, Integer.parseInt(value), operator);
                case "department":
                    return compare(user.getDepartment(), value, operator);
                case "income":
                    return compare(user.getIncome(), Integer.parseInt(value), operator);
                default:
                    return false;
            }
        }
        return false;
    }

    private boolean compare(int userValue, int conditionValue, String operator) {
        switch (operator) {
            case ">": return userValue > conditionValue;
            case "<": return userValue < conditionValue;
            case "=": return userValue == conditionValue;
        }
        return false;
    }

    private boolean compare(String userValue, String conditionValue, String operator) {
        if (operator.equals("=")) {
            return userValue.equals(conditionValue);
        }
        return false;
    }

    private int getAge(LocalDate dateOfBirth) {
        return Period.between(dateOfBirth, LocalDate.now()).getYears();
    }
}
