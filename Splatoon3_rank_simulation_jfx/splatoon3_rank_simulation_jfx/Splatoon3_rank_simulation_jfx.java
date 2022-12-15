package splatoon3_rank_simulation_jfx;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Splatoon3_rank_simulation_jfx {

	public static List<String> splatoon3_rank_simulation_jfx(int w, int l, String r, int s_p_l, int r_p) {
		
		//変数宣言
		//入力時に使用
		int win = 0, lose = 0; //勝ち負け数
//		String win_str = "", lose_str = ""; //勝ち負け数 (String)
		String rank = ""; //ウデマエ
		int s_plus_level = 0; //S+のいくつなのかを保存しておく
//		String s_plus_level_str = ""; //S+のいくつなのかを保存しておく (String)
		int rank_point = 0; // ウデマエポイント
//		String rank_point_str = ""; //ウデマエポイント (String)
		
		//計算時に使用
		double winning_parcentage = 0;//勝率
		int pay_point = 0, get_point = 0, rank_point_old = 0; //計算用のウデマエポイント
		int s_plus_level_old = 0; //計算前のS+の数値を保存しておく
		String s_plus_level_old_str = ""; //S+の数値を保存しておく (String)
		String rank_old = "";
		
		//勝ち数, 負け数, 金表彰, 銀表彰を配列に格納
		int [] simulation_result = {0, 0, 0, 0};
		
		//返り値用
		//ウデマエ, ポイント, S+ポイント
		Object[] rank_and_point = {rank, rank_point, s_plus_level};
		List<String> result_text_list = new ArrayList<>(); //リザルトテキスト

		//カウンター
		int win_count = 0, lose_count = 0, game_count = 0;
		double win_par = 0;
		double result_save = 0;
		
		//正規表現パターン作成
		Pattern pattern_rank = Pattern.compile("^[CBAS][-\\+]?$"); //C-からS+までパターン作成
		Pattern pattern_s_plus_level_old= Pattern.compile("^[1-4]?9$"); //9,19,29,39,49のパターン作成
		
		//List型にテキストを保存
		result_text_list.add("***** *****\n");
		result_text_list.add("Splatoon3 rank simulation start\n");
		result_text_list.add("***** *****\n");

		try {
			//引数を代入
			win = w;
			lose = l;
			rank = r;
			s_plus_level = s_p_l;
			rank_point = r_p;
			//マッチ作成
			Matcher matcher_rank = pattern_rank.matcher(rank);
			if (matcher_rank.find() == false) { //ウデマエが正しい文字じゃないときエラー
				System.out.println("Error! : 正しいウデマエを入力してください !");
			}
			
			if (s_plus_level < 0 || 50 < s_plus_level) { //S+の数値が正しくないときエラー
				System.out.println("Error! : 正しい数値を入力してください !");
			}
			if (rank_point < -9999 || 9999 < rank_point) { //ウデマエの数値が正しくないとエラー
				System.out.println("Error! : -9999から9999の間で入力してください !");
			}
			
		} catch (Exception e) {
			System.out.println("Catch Error! [rank_simulation_jfx]");
			return result_text_list;
		}
		
		//実際の勝ち数負け数から勝率を計算
		winning_parcentage = Calc_win_par.calc_win_par(win, lose);
		
		//S+50まで到達or3000試合経過するまでシミュレーション
		while (true) {
			
			//ここから指定ウデマエまでループ
			
			//計算前のウデマエを保存
			rank_old = rank;
			rank_point_old = rank_point;
			s_plus_level_old = s_plus_level;
			//正規表現のために文字列へ変換
			s_plus_level_old_str = String.valueOf(s_plus_level_old);
			//S+のマッチ作成
			//S+9,19,29,39,49とマッチ
			Matcher matcher_s_plus_level_old = pattern_s_plus_level_old.matcher(s_plus_level_old_str);

			
			//今のウデマエから支払うポイントを計算
			//変なウデマエの時は-1を返す
			pay_point = Calc_pay_point.calc_pay_point(rank);
			
			//勝率からシミュレーションを開始
			simulation_result = Game_simulation.game_simulation(winning_parcentage);
			win_count += simulation_result[0];
			lose_count += simulation_result[1];
			game_count = win_count + lose_count;
			
			//ウデマエ,勝ち,金,銀の数から得たポイントを計算
			get_point = Calc_get_point.calc_get_point(rank, simulation_result[0], simulation_result[2], simulation_result[3]);
			
			//ウデマエポイントを計算
			rank_point = rank_point - pay_point + get_point;
			
			//ウデマエ, ウデマエポイント, S+ポイントから今のウデマエやポイントを計算
			rank_and_point = Calc_rank.calc_rank(rank, rank_point, s_plus_level);
			
			//計算結果を保存
			rank = (String) rank_and_point[0];
			rank_point = (int) rank_and_point[1];
			s_plus_level = (int) rank_and_point[2];
			
			
			//ウデマエ変動時か, S+10ごと(昇格時のみ)に表示
			if (rank_old.equals(rank) == false || (s_plus_level % 10 == 0 && matcher_s_plus_level_old.find()) ) {
				//正規表現パターン作成
				Pattern pattern_old = Pattern.compile("^([CBA]\\+$)|^S$"); //C+,B+,A+,Sのパターン
				Matcher matcher_old = pattern_old.matcher(rank_old);
				Pattern pattern_next = Pattern.compile("^([BA]\\-$)|^S\\+$"); //B-,A-,S,S+のパターン
				Matcher matcher_next = pattern_next.matcher(rank);
				Pattern pattern_s_plus = Pattern.compile("^S\\+$"); //S+のパターン
				Matcher matcher_s_plus_old = pattern_s_plus.matcher(rank_old);
				Matcher matcher_s_plus_next = pattern_s_plus.matcher(rank);
				
				win_par = Calc_win_par.calc_win_par(win_count, lose_count);
				//勝ち負け数, 勝率, 試合数, ウデマエ, ウデマエポイント, S+ポイント
//				System.out.printf("勝ち数: %d, 負け数: %d\n", win_count, lose_count);
//				System.out.printf("勝率: %.2f\n",win_par);
//				System.out.printf("試合数: %d\n",game_count);
//				System.out.printf("ウデマエ: %s\n",rank);
//				System.out.printf("ウデマエポイント: %d\n",rank_point);
//				System.out.printf("S+の数値: %d\n",s_plus_level);
				
				//List型にテキストを保存
				result_text_list.add("勝ち数: " + win_count + ", 負け数: " + lose_count + "\n");
				result_save = ((double)Math.round(win_par * 10)) / 10; //少数第2位で四捨五入
				result_text_list.add("勝率: " + result_save + " %\n");
				result_text_list.add("試合数: " + game_count + "\n");
				result_text_list.add("ウデマエ: " + rank + "\n");
				result_text_list.add("ウデマエポイント: " + rank_point + "\n");
				result_text_list.add("S+の数値: " + s_plus_level + "\n");
				
				
				if (rank_point_old < rank_point) { //ウデマエポイントが上がったとき
//					System.out.printf("+++++  RANK UP %s to %s  +++++\n", rank_old, rank);
					result_text_list.add("+++++  RANK UP " + rank_old + " to " + rank + "  +++++\n");
					
				//昇格戦(C+,B+,A+,S)
				} else if ((rank_point_old > rank_point) && matcher_old.find() && matcher_next.find()) {
//					System.out.printf("+++++  RANK UP %s to %s  +++++\n", rank_old, rank);
					result_text_list.add("+++++  RANK UP " + rank_old + " to " + rank + "  +++++\n");
					
				//昇格戦(S+10ごと)
				} else if ((rank_point_old > rank_point) && matcher_s_plus_old.find() && matcher_s_plus_next.find()) {
//					System.out.printf("+++++  RANK UP S+%d to S+%d  +++++\n", s_plus_level-1, s_plus_level);
					result_save = s_plus_level -1;
					result_text_list.add("+++++  RANK UP S+" + s_plus_level_old + " to S+" + s_plus_level + "  +++++\n");
				//ウデマエポイントが下がったとき
				} else if (rank_point_old > rank_point) {
//					System.out.printf("-----  RANK DOWN %s to %s  -----\n", rank_old, rank);
					result_text_list.add("-----  RANK DOWN " + rank_old + " to " + rank + "  -----\n");
				}
//				System.out.printf("\n");
			}
			//S+50に到達, もしくは5000試合で終了
			if ((rank.equals("S+") && s_plus_level == 50) || game_count > 5000) {
//				System.out.println("***** *****");
//				System.out.println("Splatoon3 rank simulation end");
//				System.out.println("***** *****");
//				
//				System.out.println("");
//				System.out.println("*** 結果 ***");
//				System.out.printf("勝ち数: %d, 負け数: %d\n", win_count, lose_count);
//				System.out.printf("勝率: %.2f\n",win_par);
//				System.out.printf("試合数: %d\n",game_count);
//				System.out.printf("ウデマエ: %s\n",rank);
//				System.out.printf("ウデマエポイント: %d\n",rank_point);
//				System.out.printf("S+の数値: %d\n",s_plus_level);
				
				
				//List型にテキストを保存
				result_text_list.add("***** *****\n");
				result_text_list.add("Splatoon3 rank simulation end\n");
				result_text_list.add("***** *****\n");
				result_text_list.add("\n");
				result_text_list.add("*** 結果 ***\n");
				
				result_text_list.add("勝ち数: " + win_count +", 負け数: " + lose_count + "\n");
				result_save = ((double)Math.round(win_par * 10)) / 10; //少数第2位で四捨五入
				result_text_list.add("最終勝率: " + result_save + " %\n");
				result_text_list.add("試合数: " + game_count + "\n");
				result_text_list.add("ウデマエ: " + rank + "\n");
				result_text_list.add("ウデマエポイント: " + rank_point + "\n");
				result_text_list.add("S+の数値: " + s_plus_level + "\n");
//				for(int i = 0; i < result_text_list.size(); i++) {
//					System.out.println(result_text_list.get(i));
//				}
				
				return result_text_list;
			}
		}
		/*
		// test
		System.out.printf("rank is %s\n", rank);
		System.out.printf("pay_point is %d\n", pay_point);

		System.out.printf("winning_parcentage is %.2f\n", winning_parcentage);
		
		System.out.printf("win    is %d\n", simulation_result[0]);
		System.out.printf("lose   is %d\n", simulation_result[1]);
		System.out.printf("gold   is %d\n", simulation_result[2]);
		System.out.printf("silver is %d\n", simulation_result[3]);
		
		System.out.printf("get_point is %d\n", get_point);
		System.out.printf("rank_point is %d\n", rank_point);	
		*/
	}

}
