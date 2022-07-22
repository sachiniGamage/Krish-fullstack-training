public class reverseLinkedList {
	
	public static void main(String[] args) {
		LinkedList list = new LinkedList();
		Node n1 = new Node(0);
		Node n2 = new Node(1);
		Node n3 = new Node(2);
		Node n4 = new Node(3);
		
		list.head = n1;
		n1.next = n2;
		n2.next = n3;
		n3.next = n4;
		
//		Node n = list.head;
//		while(n!=null) {
//			System.out.println(n.val);
//			n = n.next;
//		}
		
		reverse(list);
		
		 Node nr = list.head;
	        while (nr != null) {
	            System.out.println(nr.val);
	            nr = nr.next;
	        }
	}
	
	public static void reverse(LinkedList list) {
		Node previous = null;
        Node current = list.head;
        Node temp = null;
        
        //null 0 -> 1 ->2 ...
        while (current != null) {
            temp = current.next; //temp = 1
            current.next = previous; //null <- 0  linked 0 to null
            previous = current; // previous = 0
            current = temp; //current = 1
        }
        list.head = previous;
	}
}


class LinkedList{
	Node head;
}


class Node{
	int val;
	Node next;
	
	public Node(int data) {
		this.val = data;
		next = null;
		
	}
}
