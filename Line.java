import java.util.*;

public class Line {

	private Queue<Car> carList;
	private ArrayList<Line> lineList;
	private ArrayList<Sidewalk> sidewalkList;
	private int lineNo;
	private Line nextLineOne;
	private Line nextLineTwo;

	public Line(int lineNo) {
		this.carList = new PriorityQueue<Car>();
		this.lineList = new ArrayList<Line>();
		this.sidewalkList = new ArrayList<Sidewalk>();
		this.lineNo = lineNo;
		this.nextLineOne = null;
		this.nextLineTwo = null;
	}

	// Get line list
	public ArrayList<Line> getLineList() {
		return lineList;
	}

	// Get sidewalk list
	public ArrayList<Sidewalk> getSidewalkList() {
		return sidewalkList;
	}

	// Get first car's wait time
	public int getFirstWaitTime() {
		if (carList.peek() == null)
			return 0;
		return carList.peek().getWaitTime();
	}

	// Warning!!!!!!!!!!!!!!
	// Get total wait time
	public int getTotalWaitTime() {
		int time = 0;
		// For each may not work!!!!!!!!!!!! Check Later.
		for (Car c : carList) {
			time += c.getWaitTime();
		}
		return time;
	}

	// Warning!!!!!!!!!!!!!!
	// Get average wait time
	public int getAverageWaitTime() {
		if (carList.size() == 0)
			return 0;
		return (int) (getTotalWaitTime() / carList.size());
	}

	// Warning!!!!!!!!!!!!!!
	// Add wait time to each car
	public void addWaitTime(int num) {
		for (Car c : carList) {
			c.addWaitTime(num);
		}
	}

	// Add cars to the line
	public void addCar(int num) {
		while (num > 0) {
			carList.add(new Car());
			num--;
		}
	}

	// Remove cars to the next place
	public boolean removeCar(int num) {
		if (carList.size() < num)
			return false;
		if (this.nextLineOne == null) {
			while (num > 0) {
				carList.poll();
				num--;
			}
			return true;
		}
		while (num > 0) {
			nextLineOne.carList.add(carList.poll());
			num--;
			if (num > 0) {
				nextLineTwo.carList.add(carList.poll());
				num--;
			}
		}
		return true;
	}

	// All the other lines that can go together
	public void addLine(Line newLine) {
		lineList.add(newLine);
	}

	// All the other sidewalks that can go together
	public void addSideWalk(Sidewalk newSidewalk) {
		sidewalkList.add(newSidewalk);
	}

	// Get the total car numbers in line
	public int getCarNums() {
		return carList.size();
	}

	// Get the line number
	public int getLineNo() {
		return lineNo;
	}

	// Set the next line one
	public void setLineOne(Line newLine) {
		this.nextLineOne = newLine;
	}

	// Set the next line two
	public void setLineTwo(Line newLine) {
		this.nextLineTwo = newLine;
	}

	@Override
	public String toString() {
		return "[Line = " + (lineNo + 1) + ", Cars = " + getCarNums() + "]";
	}

}
