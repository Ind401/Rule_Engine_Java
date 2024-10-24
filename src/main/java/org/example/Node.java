package org.example;

public class Node {
        private String type; // "operator" (AND/OR) or "operand" (condition)
        private Node left;
        private Node right;
        private String value; // for "operand" (e.g., age > 30)

        // Constructor for operand (leaf) nodes
        public Node(String type, String value) {
            this.type = type;
            this.value = value;
        }

        // Constructor for operator nodes
        public Node(String type, Node left, Node right, String value) {
            this.type = type;
            this.left = left;
            this.right = right;
            this.value = value;
        }

        // Getters and Setters
        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public Node getLeft() {
            return left;
        }

        public void setLeft(Node left) {
            this.left = left;
        }

        public Node getRight() {
            return right;
        }

        public void setRight(Node right) {
            this.right = right;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }

        // Method to reconstruct the rule string from the AST
        public String getRule() {
            if (this.type.equals("operand")) {
                // If the node is an operand, return its value (e.g., "age > 30")
                return this.value;
            } else if (this.type.equals("operator")) {
                // If the node is an operator, recursively get the left and right rules
                String leftRule = this.left != null ? this.left.getRule() : "";
                String rightRule = this.right != null ? this.right.getRule() : "";

                // Return the rule with parentheses around the operator
                return "(" + leftRule + " " + this.value + " " + rightRule + ")";
            }
            return "";
        }
}
