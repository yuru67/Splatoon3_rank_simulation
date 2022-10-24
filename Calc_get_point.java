package splatoon3_rank_simulation;

public class Calc_get_point {
	
	//ウデマエ, 勝ち数, 金表彰, 銀表彰
	public static int calc_get_point(String rank, int win, int gold, int silver) {
		
		//変数宣言

		//合計ポイント
		int get_point = 0;
		
		//金表彰, 銀表彰を計算し加算
		get_point += gold * 5;
		get_point += silver * 1;

		//勝ち数に応じたポイントを計算し加算
		if (rank.equals("C-") || rank.equals("C") || rank.equals("C+")) {
			get_point =+ win * 20;
		} else if (rank.equals("B-") || rank.equals("B") || rank.equals("B+")) {
			get_point =+ win * 30;
		} else if (rank.equals("A-") || rank.equals("A") || rank.equals("A+")) {
			get_point =+ win * 40;
		} else if (rank.equals("S") || rank.equals("S+")) {
			get_point =+ win * 50;
		} else {
			//存在しないウデマエを入力されたとき
			return -1;
		}
		
		//勝利数が多い時ポイント追加
		if (win == 2) { //2勝
			get_point += 5;
		} else if (win == 3) { //3勝
			get_point += 15;
		} else if (win == 4) { //4勝
			get_point += 30;
		} else if (win == 5) { //5勝
			get_point += 50;
		}
		
		//合計点を返す
		return get_point;		
	}
	
	//test
	public static void main(String[] args) {
		int test;
		test = calc_get_point("S+", 5, 10, 5);
		
		System.out.println(test);
	}
}
