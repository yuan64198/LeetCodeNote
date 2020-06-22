/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
 
/**
 * Using one stack for each linked list, when calculate the current digit number, check if each stack is empty, if it's not then
 * add it to the digit number.
 * Create a new ListNode for the next digit level and initial it with the c_out from the current digit level.
 * return the curr digit if the value is bigger than 0, otherwise return the next digit.
 */
 
class Solution {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        Stack<Integer> stack1 = new Stack();
        Stack<Integer> stack2 = new Stack();
        
        while(l1 != null) {
            stack1.add(l1.val);
            l1 = l1.next;
        }
        
        while(l2 != null) {
            stack2.add(l2.val);
            l2 = l2.next;
        }
        ListNode curr = new ListNode(0);
        
        while(!stack1.isEmpty() || !stack2.isEmpty()) {
            if(!stack1.isEmpty()) curr.val += stack1.pop();
            if(!stack2.isEmpty()) curr.val += stack2.pop();
            
            ListNode prev = new ListNode(curr.val/10);
            prev.next = curr;
            curr.val = curr.val%10;
            curr = prev;
        }
        
        return curr.val == 0 ? curr.next : curr;
    }
}

/**
 * Sol2: Add preceding zeros to the shorter linked list, and use recursion to calculate the number of each digit level.
 * Each level return it's c_out to the upper digit level.
 * But, in this way, we may need to create a lot of preceding zeros for calculation.
 */
 
 class Solution {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        int len1 = 0, len2 = 0;
        ListNode curr = l1;
        while(curr != null) {
            curr = curr.next;
            ++len1;
        }
        curr = l2;
        while(curr != null) {
            curr = curr.next;
            ++len2;
        }
        
        if(len1 < len2) {
            curr = l1;
            l1 = l2;
            l2 = curr;
            int tmp = len1;
            len1 = len2;
            len2 = tmp;
        }
        
        ListNode l2_new = new ListNode(0);
        curr = l2_new;
        
        for(int i=0; i<len1-len2; i++) {
            curr.next = new ListNode(0);
            curr = curr.next;
            //System.out.println(l2_new);
        }
        
        curr.next = l2;
        l2_new = l2_new.next;
        
        
        int c_out = helper(l1, l2_new);
        ListNode head = new ListNode(c_out);
        head.next = l1;
        return head.val == 0 ? head.next : head;
    }
    private int helper(ListNode l1, ListNode l2) {
        if(l1 == null || l2 == null) return 0;
        System.out.println(l2.val);
        int sum = l1.val + l2.val + helper(l1.next, l2.next);
        l1.val = sum%10;
        return sum/10;
    }
}
