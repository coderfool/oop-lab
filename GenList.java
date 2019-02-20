class List<T> {
	
	class Node {
		T data;
		Node next;

		Node() {
			data = null;
			next = null;
		}

		Node(T data) {
			this.data = data;
			next = null;
		}
	}

	Node head;

	List() {
		head = new Node();
	}

	boolean empty() {
		return head.next == null;
	}

	void push(T data) {
		Node node = new Node(data);
		Node temp = head;
		while (temp.next != null) {
			temp = temp.next;
		}
		temp.next = node;
	}

	Node delete() {
		if (empty()) return null;
		Node deleted = head.next;
		head.next = deleted.next;
		return deleted;
	}

	void display() {
		if (empty()) return;
		Node temp = head.next;
		while (temp != null) {
			System.out.print(temp.data + " ");
			temp = temp.next;
		}
		System.out.println();
	}
}

class GenList {
	public static void main(String[] args) {
		List<String> list = new List<>();
		list.push("dfsdf");
		list.push("sd;kf");
		list.push("sdhf");
		list.display();
		list.delete();
		list.display();
		list.push("lidfhg");
		list.display();
	}
}