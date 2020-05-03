import java.util.*;

public class Test {

	static Line[] lines;
	static Sidewalk[] sidewalks;
	final private static int totalLines = 4;
	final private static int totalSidewalks = 6;
//	private Line one;
//	private Line two;
//	private Line three;
//	private Line four;
//	private Line five;
//	private Line six;
//	private Line seven;
//	private Line eight;
//	private Line nine;
//	private Line ten;
//	private Line eleven;
//	private Line twelve;
//	private Sidewalk sone;
//	private Sidewalk stwo;
//	private Sidewalk sthree;
//	private Sidewalk sfour;
//	private Sidewalk sfive;
//	private Sidewalk ssix;

	public static void main(String[] args) {
		setIntersection();
		Line[] output;
		while (true) {
			Scanner keyboard = new Scanner(System.in);
			System.out.print("Please enter the line list: ");
			String s = keyboard.nextLine();
			if (s.equals("exit"))
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
//		lines[0] = new Line(1, null, null);
//		lines[1] = new Line(3, null, null);
//		lines[2] = new Line(4, null, null);
//		lines[3] = new Line(5, null, null);
//		lines[4] = new Line(6, null, null);
//		lines[5] = new Line(8, null, null);
//		lines[6] = new Line(10, null, null);
//		lines[7] = new Line(11, null, null);
//		lines[8] = new Line(2, lines[5 - 1], lines[6 - 1]);
//		lines[9] = new Line(7, lines[3 - 1], lines[4 - 1]);
//		lines[10] = new Line(9, lines[3 - 1], lines[4 - 1]);
//		lines[11] = new Line(12, lines[5 - 1], lines[6 - 1]);

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
		lines[0].addLine(lines[5]);
		lines[0].addLine(lines[8]);
		lines[0].addLine(lines[9]);

	}

}
