/**
 *  The parentheses in a string are balanced if and only if these 2 conditions are met:
 * There are the same number of "(" and ")" in the string.
 * Scanning through the string from left to right and counting how many "(" and ")" there are so far, 
 * there should never be a time where there are more ")" than "(". We call count("(") - count(")") the balance of the string..
 *
 * So, here we use a set to store the indexes of the characters which we plan to remove (we cannot directly remove the character because if the length of the
 * string changed, the indexes changed too)
 * if we encounter a '(', we store it's index into the stack.
 * whenever we encounter a ')', we pop out a '(' from the stack.
 * if, the stack is Empty when we want to pop out a '(', it means this ')' is invalid, then we can put it into the plannedToRemove set.
 * after going through the whole string, if there's still some '(' in the stack, means removed these '(' would make the string valid.
 * So, we put all the '(' indexes in the stack into the plannedToRemove set.
 *
 * We use another for-loop to go through the string again, if the character is not in plannedToRemove set, we append it toe the result StringBuilder.
 */

class Solution {
    public String minRemoveToMakeValid(String s) {
        Set<Integer> indexToRemove = new HashSet();
        Stack<Integer> stack = new Stack();
        
        for(int i=0; i<s.length(); ++i) {
            char c = s.charAt(i);
            if(c == '(') {
                stack.push(i);
            }
            else if(c == ')') {
                if(!stack.isEmpty()) {
                    stack.pop();
                }
                else {
                    indexToRemove.add(i);
                }
            }
        }
        
        while(!stack.isEmpty()) {
            indexToRemove.add(stack.pop());
        }
        
        StringBuilder sb = new StringBuilder();
        for(int i=0; i<s.length(); ++i) {
            if(!indexToRemove.contains(i)) sb.append(s.charAt(i));
        }
        
        return sb.toString();
    }
}
