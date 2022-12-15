package splatoon3_rank_simulation_jfx;

public class Calc_pay_point {
	public static int calc_pay_point(String rank) {
					
		//入力rankで返り値を変える
		if (rank.equals("C-")) {
			return 0;
		} else if (rank.equals("C")) {
			return 20;
		} else if (rank.equals("C+")) {
			return 40;
		} else if (rank.equals("B-")) {
			return 55;
		} else if (rank.equals("B")) {
			return 70;			
		} else if (rank.equals("B+")) {
			return 85;
		} else if (rank.equals("A-")) {
			return 110;
		} else if (rank.equals("A")) {
			return 120;
		} else if (rank.equals("A+")) {
			return 130;
		} else if (rank.equals("S")) {
			return 170;
		} else if (rank.equals("S+")) {
			return 180;
		} else {
			//存在しないウデマエを入力されたとき
			return -1;
		}
	}
	
	//test
	public static void main(String[] args) {
		String test_s;
		int test;
		test_s = "S";
		test = calc_pay_point(test_s);
		System.out.println(test);
	}
	
}
