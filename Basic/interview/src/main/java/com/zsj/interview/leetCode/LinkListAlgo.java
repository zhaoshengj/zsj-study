package com.zsj.interview.leetCode;

import org.junit.Test;

public class LinkListAlgo {


    public class ListNode {
        int val;
        ListNode next;
        ListNode(int x) { val = x; }
    }

    /**
     *   删除链表的倒数第N个节点
     * @param head
     */
    public ListNode removeNthFromEnd1(ListNode head, int n) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        int size = 0;
        ListNode s = head;
        while (s != null){
            size++;
            s = s.next;
        }
        if(size < n){
            return head;
        }
        size = size -n;

        s=dummy;
        while (size >0){
           size--;
           s = s.next;
        }
        s.next = s.next.next;
        return dummy.next;
    }
    public ListNode removeNthFromEnd2(ListNode head, int n) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode first = dummy;
        ListNode second = dummy;
        for(int i=1;i<= n+1;i++){
            first = first.next;
        }

        while (first != null){
            first = first.next;
            second = second.next;
        }

        System.out.println(dummy);
        System.out.println(first);
        System.out.println(second);

        System.out.println(second.next);
        System.out.println(second.next.next);
        second.next = second.next.next;
        return dummy.next;
    }

    public ListNode reverseList(ListNode head) {
        ListNode fir = null;
        ListNode curr = head;
        while (curr != null){
            ListNode nex = curr.next;

            curr.next = fir;
            fir = curr;
            curr = nex;
        }
        return fir;

    }



    @Test
    public void test() {
        ListNode node = new ListNode(5);
        node.next = new ListNode(2);
        node.next.next = new ListNode(4);
        removeNthFromEnd2(node,1);

    }

}
