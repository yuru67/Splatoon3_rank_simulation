package splatoon3_rank_simulation_jfx;

import java.util.Random;

public class Make_random {
	
	public static boolean make_random(double i) {
		
		//変数宣言
		double win = 0;
		
		//1～1000 の 乱数生成
		Random rand = new Random();
		int num = rand.nextInt(1000) + 1;
		
		//iは0.1%まで入力されているので10倍して計算する
		win = i * 10;
		
		//勝率以下の数値なら勝ち(true)
		if(num <= win) {
			return true ;
		} else {
			return false;
		}
	}
}