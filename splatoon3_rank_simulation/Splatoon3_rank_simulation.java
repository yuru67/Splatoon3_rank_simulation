package splatoon3_rank_simulation;

import java.text.Normalizer;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Splatoon3_rank_simulation {

	public static void main(String[] args) {
		
		//変数宣言
		//入力時に使用
		int win = 0, lose = 0; //勝ち負け数
		String win_str = "", lose_str = ""; //勝ち負け数 (String)
		String rank = ""; //ウデマエ
		int s_plus_level = 0; //S+のいくつなのかを保存しておく
		String s_plus_level_str = ""; //S+のいくつなのかを保存しておく (String)
		int rank_point = 0; // ウデマエポイント
		String rank_point_str = ""; //ウデマエポイント (String)
		int num = 0; //switch用変数
		int check = 0; // 入力した情報が合っているかどうか判断するときに使用
		String check_str = ""; // 入力した情報が合っているかどうか判断するときに使用 (String)
		
		//計算時に使用
		double winning_parcentage = 0;//勝率
		int pay_point = 0, get_point = 0, rank_point_old = 0; //計算用のウデマエポイント
		int s_plus_level_old = 0; //計算前のS+の数値を保存しておく
		String s_plus_level_old_str = ""; //S+の数値を保存しておく (String)
		String rank_old = "";
		
		//勝ち数, 負け数, 金表彰, 銀表彰を配列に格納
		int [] simulation_result = {0, 0, 0, 0};
		
		//返り値用のオブジェクト型配列
		//ウデマエ, ポイント, S+ポイント
		Object[] rank_and_point = {rank, rank_point, s_plus_level};
		
		//カウンター
		int win_count = 0, lose_count = 0, game_count = 0;
		double win_par = 0;
		
		
		//Scannerクラスのインスタンスを生成
		Scanner sc = new Scanner(System.in);
		//正規表現パターン作成
		Pattern pattern_rank = Pattern.compile("^[CBAS][-\\+]?$"); //C-からS+までパターン作成
		Pattern pattern_s_plus_level_old= Pattern.compile("^[1-4]?9$"); //9,19,29,39,49のパターン作成
		
		System.out.println("***** *****");
		System.out.println("Splatoon3 rank simulation start");
		System.out.println("***** *****");
		
		//必要な情報を入力
		while (true) {
			switch (num) {
			case 0: //勝ち数を入力
				System.out.println("");
				System.out.println("勝ち数を入力");
				try {
					win_str = sc.next(); //文字列で入力
					win_str = Normalizer.normalize(win_str, Normalizer.Form.NFKC); //全角を半角に変える
					win = Integer.parseInt(win_str); //文字列をintに変換
					System.out.printf("win : %d\n", win);
				} catch (Exception e) {
					System.out.println("Error!");
					num = 0;
					continue;
				} 
			case 1: //負け数を入力
				System.out.println("");
				System.out.println("負け数を入力");
				System.out.println("戻って入力しなおす場合は-1を入力");
				try {
					lose_str = sc.next();
					lose_str = Normalizer.normalize(lose_str, Normalizer.Form.NFKC); //全角を半角に変える
					lose = Integer.parseInt(lose_str); //文字列をintに変換
					System.out.printf("lose : %d\n", lose);
					if (lose == -1) { //-1が入力されたので勝ち数入力へ戻る
						num = 0;
						continue;
					}
				} catch (Exception e) {
					System.out.println("Error!");
					num = 1;
					continue;
				}
			case 2: //ウデマエを入力
				System.out.println("");
				System.out.println("ウデマエを入力");
				System.out.println("戻って入力しなおす場合は-1を入力");
				try {
					rank = sc.next();
					rank = Normalizer.normalize(rank, Normalizer.Form.NFKC); //全角を半角に変える
					rank = rank.toUpperCase(); //小文字を大文字へ変える
					System.out.printf("rank : %s\n", rank);
					
					//マッチ作成
					Matcher matcher_rank = pattern_rank.matcher(rank);
					if (rank.equals("-1")) { //-1が入力されたので負け数入力へ戻る
						num = 1;
						continue;
					} else if (matcher_rank.find() == false) {
						System.out.println("Error! : 正しいウデマエを入力してください !");
						num = 2;
						continue;
					}
				} catch (Exception e) {
					System.out.println("Error!");
					num = 2;
					continue;
				}
			case 3:
				//S+の時のみ数値を入力
				if (rank.equals("S+")) {
					System.out.println("");
					System.out.println("S+の数値を入力");
					System.out.println("戻って入力しなおす場合は-1を入力");
					try {
						s_plus_level_str = sc.next();
						s_plus_level_str = Normalizer.normalize(s_plus_level_str, Normalizer.Form.NFKC); //全角を半角に変える
						s_plus_level = Integer.parseInt(s_plus_level_str); //文字列をintに変換
						System.out.printf("s_plus_level : %d\n", s_plus_level);
						if (s_plus_level == -1) { //-1が入力されたのでウデマエ入力へ戻る
							num = 2;
							continue;
						} else if (s_plus_level < 0 && 50 < s_plus_level) {
							System.out.println("Error! : 正しい数値を入力してください !");
							num = 3;
							continue;
						}
					} catch (Exception e) {
						System.out.println("Error!");
						num = 3;
						continue;
					}
				}
			case 4: //ウデマエの数値を入力
				System.out.println("");
				System.out.println("ウデマエの数値を入力");
				System.out.println("戻って入力しなおす場合は10000を入力");
				try {
					rank_point_str = sc.next();
					rank_point_str = Normalizer.normalize(rank_point_str, Normalizer.Form.NFKC); //全角を半角に変える
					rank_point = Integer.parseInt(rank_point_str); //文字列をintに変換
					System.out.printf("rank_point:%d\n", rank_point);
					if (rank.equals("S+") && rank_point == 10000) { //ウデマエS+かつ, 10000が入力されたのでS+の数値入力へ戻る
						num = 3;
						continue;
					} else if (rank_point == 10000) { //ウデマエS+以外で, 10000が入力されたのでウデマエ入力へ戻る
						num = 2;
						continue;
					} else if (rank_point < -9999 && 9999 < rank_point) {
						System.out.println("Error! : -9999から9999の間で入力してください !");
						num = 4;
						continue;
					}
				} catch (Exception e) {
					System.out.println("Error!");
					num = 4;
					continue;
				}
			case 5: //入力した情報を表示
				System.out.println("");
				System.out.printf("勝ち数 : %d, 負け数 : %d\n", win, lose);
				if (rank.equals("S+")) { //ウデマエS+
					System.out.printf("ウデマエ : %s %d\n", rank, s_plus_level);
				} else { //ウデマエS+以外
					System.out.printf("ウデマエ : %s\n", rank);
				}
				System.out.printf("ウデマエポイント : %d\n", rank_point);
				System.out.println("");
				
				//確認
				System.out.println("これで良ければ 0");
				System.out.println("勝ち数から入力しなおす場合は 1");
				System.out.println("負け数から入力しなおす場合は 2");
				System.out.println("ウデマエから入力しなおす場合は 3");
				if (rank.equals("S+")) {
					System.out.println("S+から数値を入力しなおす場合は 4");
					System.out.println("ウデマエポイントから入力しなおす場合は 5");
				} else {
				System.out.println("ウデマエポイントから入力しなおす場合は4");
				}
				
				try {
					check_str = sc.next();
					check_str = Normalizer.normalize(check_str, Normalizer.Form.NFKC); //全角を半角に変える
					check = Integer.parseInt(check_str); //文字列をintに変換
					if (check == 0) { //シミュレーションを開始
						break;
					} else if (check == 1) { //勝ち数
						num = 0;
						continue;
					} else if (check == 2) { //負け数
						num = 1;
						continue;
					} else if (check == 3) { //ウデマエ
						num = 2;
						continue;
					} else if (rank.equals("S+") && check == 4) { //S+の数値
						num = 3;
						continue;
					} else if (rank.equals("S+") && check == 5) { //ウデマエポイント
						num = 4;
						continue;
					} else if (check == 4) { //ウデマエポイント
						num = 4;
						continue;
					} else {
						System.out.println("正しい数字を入力してください");
						num = 5;
						continue;
					}
				} catch (Exception e) {
					System.out.println("Error!");
					num = 5;
					continue;
				}
			}
			//入力終了
			sc.close();
			break;
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
				Pattern pattern_next = Pattern.compile("^([BA]\\-$)|^S\\+?$"); //B-,A-,S,S+のパターン
				Matcher matcher_next = pattern_next.matcher(rank);
				Pattern pattern_s_plus = Pattern.compile("^S\\+?$"); //S+のパターン
				Matcher matcher_s_plus_old = pattern_s_plus.matcher(rank_old);
				Matcher matcher_s_plus_next = pattern_s_plus.matcher(rank);
				
				win_par = Calc_win_par.calc_win_par(win_count, lose_count);
				//勝ち負け数, 勝率, 試合数, ウデマエ, ウデマエポイント, S+ポイント
				System.out.printf("勝ち数: %d, 負け数: %d\n", win_count, lose_count);
				System.out.printf("勝率: %.2f\n",win_par);
				System.out.printf("試合数: %d\n",game_count);
				System.out.printf("ウデマエ: %s\n",rank);
				System.out.printf("ウデマエポイント: %d\n",rank_point);
				System.out.printf("S+の数値: %d\n",s_plus_level);
				
				if (rank_point_old < rank_point) { //ウデマエポイントが上がったとき
					System.out.printf("+++++  RANK UP %s to %s  +++++\n", rank_old, rank);
					
				//昇格戦(C+,B+,A+,S)
				} else if ((rank_point_old > rank_point) && matcher_old.find() && matcher_next.find()) {
					System.out.printf("+++++  RANK UP %s to %s  +++++\n", rank_old, rank);
					
				//昇格戦(S+10ごと)
				} else if ((rank_point_old > rank_point) && matcher_s_plus_old.find() && matcher_s_plus_next.find()) {
					System.out.printf("+++++  RANK UP S+%d to S+%d  +++++\n", s_plus_level-1, s_plus_level);
					
				//ウデマエポイントが下がったとき
				} else if (rank_point_old > rank_point) {
					System.out.printf("-----  RANK DOWN %s to %s  -----\n", rank_old, rank);
				}
				System.out.printf("\n");
			}
			//S+50に到達, もしくは5000試合で終了
			if ((rank.equals("S+") && s_plus_level == 50) || game_count > 5000) {
				System.out.println("***** *****");
				System.out.println("Splatoon3 rank simulation end");
				System.out.println("***** *****");
				
				System.out.println("");
				System.out.println("*** 結果 ***");
				System.out.printf("勝ち数: %d, 負け数: %d\n", win_count, lose_count);
				System.out.printf("勝率: %.2f\n",win_par);
				System.out.printf("試合数: %d\n",game_count);
				System.out.printf("ウデマエ: %s\n",rank);
				System.out.printf("ウデマエポイント: %d\n",rank_point);
				System.out.printf("S+の数値: %d\n",s_plus_level);

				
				break;
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
