
public class Car {

	private int waitTime;

	public Car() {
		this.waitTime = 0;
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

}
