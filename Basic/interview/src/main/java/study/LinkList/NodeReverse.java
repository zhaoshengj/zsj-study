package study.LinkList;

import org.junit.Test;

public class NodeReverse {

    //就地逆序
    public void  Reverse1(LNode head){
        if(head == null || head.next == null){
            return;
        }
        LNode cur = head.next;
        LNode next = cur.next;
        cur.next = null;
        LNode pre = cur;
        cur = next;
        while (cur.next != null){
            next = cur.next;
            cur.next = pre;
            pre=cur;
            cur = cur.next;
            cur = next;
        }
        cur.next = pre;
        head.next = cur;
    }

    //插入法
    public void Reverse2(LNode head) {
        if(head == null || head.next == null){
            return;
        }
        LNode cur = head.next.next;
        head.next.next = null;
        while (cur != null){
            LNode next = cur.next;
            cur.next =head.next;
            head.next = cur;
            cur = next;
        }

    }

    //递归 // 不带头节点
    public LNode reverse(LNode head) {
        if(head == null || head.next == null){
            return head;
        }else {
            //反转后面的节点
            LNode newHead = reverse(head.next);

            head.next.next = head;
            head.next = null;
            return newHead;
        }

    }
    //递归 // 不带头节点
    public void Reverse3(LNode head) {
        if(head == null || head.next == null){
            return ;
        }
        LNode fir = head.next;
        LNode newNode = reverse(fir);
        head.next = newNode;
    }


    @Test
    public void test(){

        LNode head = new LNode();
        head.next = null;
        LNode tmp = null;
        LNode cur = head;
        //构造单链表
        for(int i=0;i<8;i++){
            tmp = new LNode(i);
            cur.next = tmp;
            cur = tmp;
        }

        System.out.println("逆序前：");
        for (cur = head.next;cur != null;cur = cur.next) System.out.print(cur.val+" ");
        Reverse3(head);
        System.out.println("\n 逆序后：");
        for (cur = head.next;cur != null;cur = cur.next) System.out.print(cur.val+" ");

      /*  LNode lNode = new LNode(1);
        lNode.next = new LNode(2);
        System.out.println("逆序前：");
        for (cur = lNode;cur != null;cur = cur.next) {

            System.out.print(cur.val + " ");
        }
        reverse1(lNode);
        System.out.println("\n 逆序后：");
        for (cur = lNode;cur != null;cur = cur.next) System.out.print(cur.val+" ");*/

    }

}
