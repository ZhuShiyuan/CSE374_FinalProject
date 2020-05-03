
public class Car implements Comparable<Car>{

	private int waitTime;

	public Car() {
		this.waitTime = 1;
	}

	public Car(int waitTime) {
		this.waitTime = waitTime;
	}

	public int getWaitTime() {
		return waitTime;
	}

	public void addWaitTime(int num) {
		this.waitTime += num;
	}

	@Override
	public int compareTo(Car o) {
		return this.waitTime - o.waitTime;
	}

}
