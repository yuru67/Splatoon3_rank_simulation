package splatoon3_rank_simulation_jfx;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Calc_rank {
	public static Object[] calc_rank(String rank, int rank_point, int s_plus_level) {
		
		//変数宣言
		int point_for_calc = s_plus_level;
		int over_line = 0, under_line = 0;//昇格, 降格ライン
		int s_plus_level_old = 0;
		
		//test
		//boolean test;
		
		//正規表現パターン作成
		Pattern pattern_c = Pattern.compile("^C[-\\+]?$"); //C帯
		Pattern pattern_b = Pattern.compile("^B[-\\+]?$"); //B帯
		Pattern pattern_a = Pattern.compile("^A[-\\+]?$"); //A帯
		Pattern pattern_s_plus_over = Pattern.compile("^[1-4]?9$"); //9,19,29,39,49のパターン作成
		Pattern pattern_s_plus_under = Pattern.compile("^[1-4]?0$"); //0,10,20,30,40のパターン作成
		
		//マッチ作成
		Matcher matcher_c = pattern_c.matcher(rank);
		Matcher matcher_b = pattern_b.matcher(rank);
		Matcher matcher_a = pattern_a.matcher(rank);
		
		//返り値用のオブジェクト型配列
		//ウデマエ, ポイント, S+ポイント
		Object[] rank_and_point = {rank, rank_point, s_plus_level};
		
		//ウデマエの判定
		if (matcher_c.find()) { //C帯
			if (rank_point >= 600) { //昇格戦
				rank = "B-";
				rank_point = 100;
			} else if (600 > rank_point && rank_point >= 400) {
				rank = "C+";
			} else if (400 >rank_point && rank_point >= 200) {
				rank = "C";
			} else {
				rank = "C-";
			}
		} else if(matcher_b.find()) { //B帯
			if (rank_point >= 850) { //昇格戦
				rank = "A-";
				rank_point = 200;
			} else if (850 > rank_point && rank_point >= 600) {
				rank = "B+";
			} else if (600 >rank_point && rank_point >= 350) {
				rank = "B";
			} else {
				rank = "B-";
			}
		} else if(matcher_a.find()) { //A帯
			if (rank_point >= 1100) { //昇格戦
				rank = "S";
				rank_point = 300;
			} else if (1100 > rank_point && rank_point >= 800) {
				rank = "A+";
			} else if (800 >rank_point && rank_point >= 500) {
				rank = "A";
			} else {
				rank = "A-";
			}
		} else if(rank.equals("S")) { //S
			if (rank_point >= 1000) {
				rank = "S+";
				rank_point = 300;
			} else {
				rank = "S";
			}
		} else if(rank.equals("S+")) { //S+
			
			//S+のみ飛び級を考慮してループさせる
			while (true) {
				
				//S+のマッチ作成
				//正規表現のために文字列へ変換
				String s_plus_level_str = String.valueOf(s_plus_level);
				
				//S+9,19,29,39,49とマッチ
				Matcher matcher_s_plus_over = pattern_s_plus_over.matcher(s_plus_level_str);
				
				//S+0,10,20,30,40とマッチ
				Matcher matcher_s_plus_under = pattern_s_plus_under.matcher(s_plus_level_str);
				
				//test
				//System.out.printf("rank:%s, rank_point:%d, S+ :%d\n", rank, rank_point, s_plus_level);
				//test = (rank_point >= over_line);
				//System.out.println("over_line:" + test);
				//test = (rank_point < under_line);
				//System.out.println("under_line:" + test);
				//test = matcher_s_plus.find();
				//System.out.println("match:" + test);
				
				//S+10以上の時, 一桁目だけにして計算しやすくする
				point_for_calc = s_plus_level % 10;
				
				//昇格, 降格ラインを計算
				over_line = (point_for_calc + 1) * 350 + 300;
				under_line = point_for_calc * 350 + 300;
				//System.out.printf("Over_Line: %d\n", over_line);
				//System.out.printf("Under_Line: %d\n", under_line);
				
				//s_plus_levelを保存
				s_plus_level_old = s_plus_level;
				
				//昇格 (S+0)
				if((s_plus_level == 0) && (rank_point >= over_line)) {
					s_plus_level++;
					//System.out.println("Rank_Up.(S+0)");
				}
				//昇格戦パターン (S+9,19,29,39,49)
				else if ((matcher_s_plus_over.find()) && (rank_point >= over_line)) {
					s_plus_level++;
					rank_point = 300;
					//System.out.println("Rank_Up.(S+9,19,29,39,49)");
				}
				
				//カンスト時(S+50)
				else if ((s_plus_level == 50) && (rank_point >= 9999)){
					rank_point = 9999;
					//System.out.println("Count_Stop.(S+50)");
				}
				
				//昇格 (S+9,19,29,39,49,50以外)
				else if((matcher_s_plus_over.find() == false) && (s_plus_level != 50) && (rank_point >= over_line)) {
					s_plus_level++;
					//System.out.println("Rank_Up.(S+1-49)");
				}
				
				//降格 (S+0,10,20,30,40,50以外)
				else if((matcher_s_plus_under.find() == false) && (s_plus_level != 50) && (rank_point < under_line)) {
					s_plus_level--;
					//System.out.println("Rank_Down.(S+)");
				}
				
				//昇格も降格も無しは何もしない
				else {
					//System.out.println("No rank_down, No rank_up.(S+)");
					//System.out.printf("Border_Line: %d\n", over_line);
					}
				
				//変更後の値と変更前の値が同じならループを抜ける
				if (s_plus_level == s_plus_level_old) {
					//System.out.println("");
					break;
				}
			}
		}
		if (rank_point < -9999) { //マイナスカンストを超えた時, -9999にする
			rank_point = -9999;
		}
		
		//計算結果をオブジェクト型の配列に格納
		rank_and_point[0] = rank;
		rank_and_point[1] = rank_point;
		rank_and_point[2] = s_plus_level;
		
		return rank_and_point;
	}
	
	//test
	public static void main(String[] args) {
		String[] rank_test = {"C", "B", "A", "S", "S+"};
		
		String rank = "";
		int rank_point = 4000;
		int s_plus_level= 27;
		
		//返り値用のオブジェクト型配列
		//ウデマエ, ポイント, S+ポイント
		Object[] rank_and_point = {rank, rank_point, s_plus_level};
		/*
		//test (C to S+)
		for (int i = 0; i < rank_test.length; i++) {
			rank_point = 0;
			for (int j = 0; j < 6; j++) {
				rank_and_point = calc_rank(rank_test[i], rank_point, s_plus_level);
				rank_point-= 200;
				s_plus_level = (int) rank_and_point[2];
				
				System.out.printf("rank:%s, rank_point:%d, S+ :%d\n", rank_and_point[0], rank_and_point[1], rank_and_point[2]);
			}
			System.out.printf("**next**\n");
		}
		*/
		//test(S+ only)
		for (int j = 0; j < 10; j++) {
			rank_and_point = calc_rank(rank_test[4], rank_point, s_plus_level);
			rank_point-= 500;
			s_plus_level = (int) rank_and_point[2];
			
			System.out.printf("rank:%s, rank_point:%d, S+ :%d\n", rank_and_point[0], rank_and_point[1], rank_and_point[2]);
		}
	}
}
