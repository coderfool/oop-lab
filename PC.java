class Q {
	boolean produced = false;
	int value = 0;

	synchronized void produce(int val) {
		try {
			while (produced) {
				wait();
			}
			value = val;
			Thread.sleep(500);
			System.out.println("Produced: " + value);
			produced = true;
			notify();
		}
		catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	synchronized void consume() {
		try {
			while (!produced) {
				wait();
			}
			Thread.sleep(500);
			System.out.println("Consumed: " + value);
			produced = false;
			notify();
		}
		catch (InterruptedException e) {
			e.printStackTrace();
		} 
	}
}

class Producer implements Runnable {
	Q q;
	Thread t;

	Producer(Q q) {
		this.q = q;
		t = new Thread(this);
		t.start();
	}

	public void run() {
		int i = 0;
		while (true) {
			q.produce(i++);
		}
	}
}

class Consumer implements Runnable {
	Q q;
	Thread t;

	Consumer(Q q) {
		this.q = q;
		t = new Thread(this);
		t.start();
	}

	public void run() {
		while (true) {
			q.consume();
		}
	}
}

class PC {
	public static void main(String[] args) {
		Q q = new Q();
		new Producer(q);
		new Consumer(q);
	}
}