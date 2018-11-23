import java.util.ArrayDeque;
import java.util.Deque;

class Solution {
    public static String ecodeString(String s) {
        Deque<Item> stack = new ArrayDeque<>();
        String rst = "";
        if ( s == null || s.length() == 0) return rst;
        int i = 0;
        String currentStr = "";
        int currentNumber = 0;
        while (i < s.length()) {
            char currentChar = s.charAt(i);
            if (currentChar == '[') {
                //put currentStr into Stack if currentStr is not empty and put current number into stack
                if(!currentStr.equals("")) {
                    stack.push(new Item(0, currentStr));
                    currentStr = "";
                }
                stack.push(new Item(1, String.valueOf(currentNumber)));
                currentNumber = 0;
            } else if (currentChar == ']') {
                //pop the number and update the currentNumber.
                //If after pop stack Stack is empty, update rst.
                //If peek is string, update the currentStr
                int num = Integer.parseInt(stack.pop().val);
                currentStr = repeatString(currentStr, num);
                if (stack.peek() != null && stack.peek().operation == 0) {
                    currentStr = stack.pop().val + currentStr;
                }
                if (stack.isEmpty()) {
                    rst += currentStr;
                    currentStr = "";
                    currentNumber = 0;
                }

            } else if (Character.isDigit(currentChar)) {
                //update current number
                int num = Character.getNumericValue(currentChar);
                currentNumber = currentNumber*10 + num;
            } else {
                // It is a letter, update current String
                currentStr += String.valueOf(currentChar);
            }
            i++;
        }
        return rst + currentStr;
    }

    public static String repeatString(String s, int n) {
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < n; i++) {
            sb.append(s);
        }
        return sb.toString();
    }

    static class Item {
        int operation; // 0-> append current string to this string ; 1->repeat current string this numer times.
        String val;
        public Item(int o, String val) {
            this.operation = o;
            this.val = val;
        }
    }

    public static void main(String args[]) {
        String testCase1 = "3[a2[c]]";
        System.out.println(ecodeString(testCase1));
    }
}
