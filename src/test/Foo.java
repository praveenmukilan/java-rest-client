package test;

public class Foo implements Runnable {
	Foo() {
	}

	public void run() {
		String name = Thread.currentThread().getName();
		System.out.println("Thread started : "+name);
		switch (name) {
		case "threadA":
			first();
			break;
		case "threadB":
			second();
			break;
		case "threadC":
			third();
			break;
		}
		System.out.println("Thread ended : "+name);
	}

	synchronized void first() {
		System.out.println("first() ");
	}

	synchronized void second() {
		System.out.println("second() ");
	}

	synchronized void third() {
		System.out.println("third() ");
	}
	 
	 public static void main(String[] a) {
			try {
				
				System.out.println("Thread Started : "+Thread.currentThread().getName());
				Foo foo = new Foo();
				Thread threadA = new Thread(foo, "threadA");
				Thread threadB = new Thread(foo, "threadB");
				Thread threadC = new Thread(foo, "threadC");

				threadA.start();
				threadA.join();
				threadB.start();
				threadB.join();
				threadC.start();
				threadC.join();
				
				System.out.println("Thread Ended : "+Thread.currentThread().getName());
				
			} catch (Exception e) {

			}

		}

}
