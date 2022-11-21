package splatoon3_rank_simulation;

public class Game_simulation {
	
	//試合シミュレーション

	//引数は勝率
	public static int[] game_simulation(double winning_parcentage) {
		
		//変数定義
		int win_count = 0;
		int lose_count = 0;
		boolean w_l;
		
		int commendation_gold = 0;
		int commendation_silver = 0;
		boolean commendation;
		
		int[] result = {0, 0, 0, 0};
		
		while(win_count < 5 && lose_count < 3) {
			//勝率を引数に勝ち負けを決める関数
			w_l = Make_random.make_random(winning_parcentage);
			if (w_l == true) {
				win_count++;
			} else if (w_l == false) {
				lose_count++;
			} else {
				System.out.printf("w_l is error !!");
			}
			//金銀３つ表彰を決める
			for (int i = 0; i < 3; i++) {
				commendation = Make_random.make_random(winning_parcentage);
				if (commendation == true) {
					commendation_gold++;
				} else if (commendation == false){
					commendation_silver++;
				} else {
					System.out.print("commendation is error !!");
				}
			}
		}
		//勝ち数, 負け数, 金表彰, 銀表彰を配列に格納
		result[0] = win_count;
		result[1] = lose_count;
		result[2] = commendation_gold;
		result[3] = commendation_silver;
		
		//配列を返す
		return result;
	}
	
	//test
	public static void main(String[] args) {
		int[] test = {0, 0, 0, 0};
		test =  game_simulation(50);
		System.out.printf("gold is         %d\n", test[0]);
		System.out.printf("silver is       %d\n", test[1]);
		System.out.printf("gold medal is   %d\n", test[2]);
		System.out.printf("silver medal is %d\n", test[3]);
	}
	
	
}
