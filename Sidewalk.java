
public class Sidewalk {
	
	private int sidewalkNo;
	private int pedestrians;
	
	public Sidewalk(int sidewalkNo) {
		this.sidewalkNo = sidewalkNo;
		pedestrians = 0;
	}
	
	public void addPedestrians(int num) {
		this.pedestrians += num;
	}
	
	public int getPedestrianNums() {
		return pedestrians;
	}
	
	public int getsidewalkNo() {
		return sidewalkNo;
	}
}
