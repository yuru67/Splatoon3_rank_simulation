package splatoon3_rank_simulation_jfx;

//勝ち負け計算
public class Calc_win_par {

	//勝率計算
	public static double calc_win_par(int win_count, int lose_count) {

		//変数定義
		double winning_parcentage = 0;
		
		//勝率計算
		winning_parcentage = (double)win_count / ((double)win_count + (double)lose_count) * 100;
		
		//小数第二位で四捨五入
		winning_parcentage = ((double)Math.round(winning_parcentage * 10)) / 10;
		
		/* test
		System.out.printf("win_count is %.2f\n", win_count);
		System.out.printf("lose_count is %.2f\n", lose_count);
		System.out.printf("winning_parcentage is %.2f\n", winning_parcentage);
		*/
		
		return winning_parcentage;
	}

	//test
	public static void main(String[] args) {
		int test_win = 33;
		int test_lose = 16;
		
		double test_par = 0.0;
		
		test_par = calc_win_par(test_win, test_lose);
		
		System.out.printf("test_par is %.3f\n", test_par);
		
	}

}
