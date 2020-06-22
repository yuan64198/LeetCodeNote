/**
 * Create a new node to store the head of the result.
 * Use a while-loop to calculate the number of each digit level.
 * Note that we have to initialize value of next digit level with the c_out of the current digit level.
 * Use an if to judge if the c_out of the last digit level is 0.
 * if the c_out of the last digit level is 0, then we break out of the while-loop.
 * Other wise we will return a result with an extra 0 in the end.
 * The time complexity is O(m+n), where m and n are the length of two linkedlist.
 */
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
 
class Solution {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode head = new ListNode(0);
        ListNode curr = head;
        
        while(l1 != null || l2 != null) {
            if(l1 != null) curr.val += l1.val;
            if(l2 != null) curr.val += l2.val;
            
            int c_out = curr.val/10;
            curr.val = curr.val%10;
            
            if((l1 == null || l1.next == null) && (l2 == null || l2.next == null) && c_out == 0) break;
            curr.next = new ListNode(c_out);
            curr = curr.next;
            
            if(l1 != null) l1 = l1.next;
            if(l2 != null) l2 = l2.next;
        }
        
        return head;
    }
}
