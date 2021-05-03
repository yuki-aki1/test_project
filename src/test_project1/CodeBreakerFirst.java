package test_project1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * CodeBreakerFirst<br>
 * シンプルにmainメソッドだけでコードブレイカーを作成します。
 *
 * @author ono
 */
public class CodeBreakerFirst {

    /**
     * @param args
     */
    public static void main(String[] args) {
        //変数の初期化。
        /*必要な変数
         * タイトル、ルール文、答えの格納される配列、
         * 入力された数字の格納される配列。
         * ヒット数、ブロー数、チャレンジの回数
         */
        String title = "*** CodeBreaker ***";// タイトル
        String rule = "隠された3つの数字をあてます。\n"
                    + "1つの数字は1から6の間です。\n"
                    + "3つの答えの中に同じ数字はありません。\n"
                    + "入力した数字の、"
                    + "位置と数字が当たってたらヒット、\n"
                    + "数字だけあってたらブローとカウントします。\n"
                    + "全部当てたら(3つともヒットになったら)"
                    + "終了です\n\n";
        int[] answer = new int[3];// 答えが入る
        int[] input = new int[3];// 入力した答えが入る
        /*
         * hitはhitのカウント用、blowもblowのカウント用。 
         * countは何回目のチャレンジかを数えている。
         */
        int hit = 0, blow = 0, count = 0;

        //タイトルとルールの表示
        BufferedReader br 
            = new BufferedReader(new InputStreamReader(System.in));
        System.out.println(title);
        System.out.println(rule);
        
        
        //ランダムな答えを作成。
        //ただし、仕様通り、同じ数字がないようにする。
        for (int i = 0; i < answer.length; i++) {
            //自分より前の要素にかぶるやつがないか確かめる。
            //あったらもう1回random
            boolean flag = false;
            answer[i] = (int) (Math.random() * 6 + 1);
            do {
                flag = false;
                for (int j = i - 1; j >= 0; j--) {
                    if (answer[i] == answer[j]) {
                        flag = true;
                        answer[i] = (int) (Math.random() * 6 + 1);
                    }
                }

            } while (flag == true);
        }
        //ゲーム部
        while (true) {
            count++;
            System.out.println("*** "+count + "回目 ***");
            //インプット
            for (int i = 0; i < answer.length; i++) {
                System.out.print( (i + 1) + "個目 : ");
                try {
                    input[i] = Integer.parseInt(br.readLine());
                } catch (NumberFormatException e) {
                    System.err.println("数値を入力してください");
                    i--;
                } catch (IOException e) {
                    System.err.println("もう一度入力してください");
                    i--;
                }
            }
            //答え判断
            hit = 0;
            blow = 0;
            for (int i = 0; i < answer.length; i++) {
                for (int j = 0; j < answer.length; j++) {
                    if (i == j && input[i] == answer[j]) {
                        hit++;
                    } else if (input[i] == answer[j]) {
                        blow++;
                    }
                }
            }
            //終了判断　ヒットが3つになったら終了
            System.out.println("ヒット" + hit + " ブロー" + blow);
            if (hit == 3) {
                System.out.println("おめでとー");
                break;
            }else{
                System.out.println();
            }
        }
    }
}