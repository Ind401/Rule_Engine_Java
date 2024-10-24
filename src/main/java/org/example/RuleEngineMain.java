package org.example;
import java.time.LocalDate;

public class RuleEngineMain {
    public static void main(String[] args) {
        // Example Rule: "age > 30 AND department = 'Sales' AND income > 50000"
        String ruleString = "age > 30 AND department = Sales AND income > 50000";

        // Parse the rule
        RuleParser parser = new RuleParser();
        Node ruleAst = parser.parse(ruleString);

        // Define a user
        User user = new User(LocalDate.of(1985, 5, 20), "Sales", 60000);

        // Evaluate rule
        RuleEvaluator evaluator = new RuleEvaluator();
        boolean isEligible = evaluator.evaluate(ruleAst, user);

        System.out.println("Is user eligible? " + isEligible);
    }
}
