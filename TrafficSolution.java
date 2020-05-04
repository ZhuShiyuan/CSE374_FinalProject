import java.util.*;

public class TrafficSolution {

	static Line[] lines;
	static Sidewalk[] sidewalks;
	final private static int totalLines = 12;
	final private static int totalSidewalks = 6;

	public static void main(String[] args) {
		setIntersection();
		Line[] output;
		while (true) {
			Scanner keyboard = new Scanner(System.in);
			System.out.print("Please enter the line list: ");
			String s = keyboard.nextLine();
			if (s.equals("exit") || s.equals("Exit"))
				break;
			if (!s.isEmpty()) {
				String[] slist = s.split(" ");
				int[][] list = new int[slist.length / 2][2];
				for (int i = 0; i < slist.length / 2; i++) {
					list[i][0] = Integer.parseInt(slist[i * 2]);
					list[i][1] = Integer.parseInt(slist[i * 2 + 1]);
				}
				output = trafficControl(list);
			} else {
				output = trafficControl(null);
			}
			for (Line i : output) {
				System.out.println(i.toString());
			}
		}
	}

	private static Line[] trafficControl(int[][] list) {
		if (list == null && lines == null)
			return null;
		if (list == null && lines != null) {
			sort(lines);
			return getLines(lines);
		}
		addNewCars(lines, list);
		sort(lines);
		addWaitTime(lines, 20);
		return getLines(lines);
	}

	private static void addNewCars(Line[] lines, int[][] list) {
		for (int[] i : list) {
			for (Line j : lines) {
				if (j.getLineNo() == i[0] - 1) {
					j.addCar(i[1]);
					break;
				}
			}
		}
	}

	// I'm not sure it works
//	private static void sort(Line[] lines) {
//		for (int j = 2; j < lines.length; j++) {
//			Line key = lines[j];
//			int i = j - 1;
//			while (i > 0 && greaterThan(lines[i], key)) {
//
//				lines[i + 1] = lines[i];
//				i--;
//			}
//			lines[i + 1] = key;
//		}
//	}

	private static void sort(Line[] lines) {
		int n = lines.length;
		for (int i = 0; i < n - 1; i++) {
			for (int j = 0; j < n - i - 1; j++) {
				if (!greaterThan(lines[j], lines[j + 1])) {
					Line temp = lines[j];
					lines[j] = lines[j + 1];
					lines[j + 1] = temp;
				}
			}
		}
	}

	private static boolean greaterThan(Line first, Line second) {
		if (first.getFirstWaitTime() != second.getFirstWaitTime()) {
			return first.getFirstWaitTime() > second.getFirstWaitTime();
		} else if (first.getTotalWaitTime() != second.getTotalWaitTime()) {
			return first.getTotalWaitTime() > second.getTotalWaitTime();
		} else if (first.getAverageWaitTime() != second.getAverageWaitTime()) {
			return first.getAverageWaitTime() > second.getAverageWaitTime();
		} else
			// Don't have priority yet, simply return the first line
			return true;
	}

	private static Line[] getLines(Line[] lines) {
		ArrayList<Line> alist = lines[0].getLineList();
		Line[] list = new Line[alist.size() + 1];
		for (int i = 0; i < list.length - 1; i++) {
			list[i] = alist.get(i);
		}
		list[list.length - 1] = lines[0];
		for (Line i : list) {
			if (i.getCarNums() < 10)
				i.removeCar(i.getCarNums());
			else
				i.removeCar(10);
		}
		return list;
	}

	private static void addWaitTime(Line[] lines, int num) {
		for (Line i : lines) {
			i.addWaitTime(num);
		}
	}

	private static void setIntersection() {
		// Make the line list
		lines = new Line[totalLines];
		// Use for loop
		for (int i = 0; i < totalLines; i++) {
			lines[i] = new Line(i);
		}

		// Make the sidewalk list
		sidewalks = new Sidewalk[totalSidewalks];
		for (int i = 0; i < totalSidewalks; i++) {
			sidewalks[i] = new Sidewalk(i);
		}

		// Set all the lines
		lines[2 - 1].setLineOne(lines[5 - 1]);
		lines[2 - 1].setLineTwo(lines[6 - 1]);
		lines[7 - 1].setLineOne(lines[3 - 1]);
		lines[7 - 1].setLineTwo(lines[4 - 1]);
		lines[9 - 1].setLineOne(lines[3 - 1]);
		lines[9 - 1].setLineTwo(lines[4 - 1]);
		lines[12 - 1].setLineOne(lines[5 - 1]);
		lines[12 - 1].setLineTwo(lines[6 - 1]);

		lines[0].addLine(lines[1]);
		lines[0].addLine(lines[2]);
		lines[0].addLine(lines[4]);
		lines[0].addLine(lines[5]);
		lines[0].addLine(lines[6]);

		lines[1].addLine(lines[0]);
		lines[1].addLine(lines[2]);
		lines[1].addLine(lines[4]);
		lines[1].addLine(lines[5]);
		lines[1].addLine(lines[6]);

		lines[2].addLine(lines[3]);
		lines[2].addLine(lines[4]);
		lines[2].addLine(lines[5]);
		lines[2].addLine(lines[6]);
		lines[2].addLine(lines[11]);

		lines[3].addLine(lines[2]);
		lines[3].addLine(lines[4]);
		lines[3].addLine(lines[5]);
		lines[3].addLine(lines[6]);
		lines[3].addLine(lines[11]);

		lines[4].addLine(lines[2]);
		lines[4].addLine(lines[3]);
		lines[4].addLine(lines[5]);
		lines[4].addLine(lines[6]);
		lines[4].addLine(lines[11]);

		lines[5].addLine(lines[2]);
		lines[5].addLine(lines[3]);
		lines[5].addLine(lines[4]);
		lines[5].addLine(lines[6]);
		lines[5].addLine(lines[11]);

		lines[6].addLine(lines[2]);
		lines[6].addLine(lines[3]);
		lines[6].addLine(lines[7]);
		lines[6].addLine(lines[9]);
		lines[6].addLine(lines[11]);

		lines[7].addLine(lines[2]);
		lines[7].addLine(lines[3]);
		lines[7].addLine(lines[6]);
		lines[7].addLine(lines[9]);
		lines[7].addLine(lines[11]);

		lines[8].addLine(lines[2]);
		lines[8].addLine(lines[3]);
		lines[8].addLine(lines[5]);
		lines[8].addLine(lines[9]);
		lines[8].addLine(lines[11]);

		lines[9].addLine(lines[2]);
		lines[9].addLine(lines[3]);
		lines[9].addLine(lines[5]);
		lines[9].addLine(lines[8]);
		lines[9].addLine(lines[11]);

		lines[10].addLine(lines[0]);
		lines[10].addLine(lines[4]);
		lines[10].addLine(lines[5]);
		lines[10].addLine(lines[6]);
		lines[10].addLine(lines[11]);

		lines[11].addLine(lines[0]);
		lines[11].addLine(lines[4]);
		lines[11].addLine(lines[5]);
		lines[11].addLine(lines[6]);
		lines[11].addLine(lines[10]);

	}

}
