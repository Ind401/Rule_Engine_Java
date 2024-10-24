package org.example;
import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RuleParser {
    public Node parse(String ruleString) {
        Stack<Node> stack = new Stack<>();
        String[] tokens = tokenize(ruleString);

        for (String token : tokens) {
            if (token.equals("AND") || token.equals("OR")) {
                Node right = stack.pop();
                Node left = stack.pop();
                stack.push(new Node("operator", left, right, token));
            } else {
                stack.push(new Node("operand", token));
            }
        }

        return stack.pop();
    }

    private String[] tokenize(String ruleString) {
        return ruleString.split("\\s+");
    }
}
