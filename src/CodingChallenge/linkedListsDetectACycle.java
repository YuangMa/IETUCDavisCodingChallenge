package CodingChallenge;

public class linkedListsDetectACycle {
    static public void main(String[] args){
        ListNode one = new ListNode(1);
        ListNode two = new ListNode(2);
        ListNode three = new ListNode(3);
        one.next = two;
        two.next = three;
        three.next = two;
        ListNode head = one;
        linkedListsDetectACycle solution = new linkedListsDetectACycle();
        System.out.println(solution.has_cycle(head));
    }


    public boolean has_cycle(ListNode head){
        if(head == null){
            return false;
        }

        ListNode fast = head;
        ListNode slow = head;
        while(fast != null && fast.next != null){
            fast = fast.next.next;
            slow = slow.next;
            if(fast == slow){
                return true;
            }
        }
        return false;
    }


}
