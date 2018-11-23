package src.main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class OldUnagiStore {

    private enum TERMS {

        /** 座席数"0" .*/
        SEAT(0),
        /** グループ数"1" .*/
        GROUP(1),
        /** グループ数"0" .*/
        MEMBER(0),
        /** グループ数"1" .*/
        START(1),
        ;
        /** 入力位置 .*/
        private int numberOfType;

        /**
         * TERMSコンストラクタ
         * @param numberOfType
         *      入力位置
         */
        TERMS(int numberOfType) {
            this.numberOfType = numberOfType;
        }
    }

    public static void main(String[] args) {

        try (BufferedReader input = new BufferedReader(new InputStreamReader(System.in))) {

            String inputTerm = input.readLine();

            //座席数とグループ数の取得
            int seat = Integer.parseInt(inputTerm.split(" ")[TERMS.SEAT.numberOfType]);
            int group = Integer.parseInt(inputTerm.split(" ")[TERMS.GROUP.numberOfType]);

            //グループ人数と座席開始位置の取得
            int[] member = new int[group];
            int[] start = new int[group];

            //グループ人数と座席開始位置の入力
            for (int i = 0; i < group; i++) {

                String record = input.readLine();
                member[i] = Integer.parseInt(record.split(" ")[TERMS.MEMBER.numberOfType]);
                start[i] = Integer.parseInt(record.split(" ")[TERMS.START.numberOfType]);
            }

            //座席の設定
            int[] result = new int[seat];

            //グループの件数分ループ
            for (int grpCnt = 0; grpCnt < group; grpCnt++) {

                //スタート位置をフラグ化
                int startF = result[start[grpCnt]] - 1;

                //グループ内人数分ループ
                for (int mbrCnt = 0; mbrCnt < member[grpCnt]; mbrCnt++) {

                    //挿入位置をフラグ化
                    int insertF = (start[grpCnt] - 1 + mbrCnt);

                    //①スタート位置から終点までが一度に収まる場合
                    if (result.length > startF + member[grpCnt]) {

                        if (!(insertF == result.length)) {
                            //スタート位置に値がない場合
                            if (result[insertF] == 0) {

                                result[insertF] = 1;
                                continue;
                            }
                        }
                    }
                    //②スタート位置から最大値を超えていく場合
                    int overLength = insertF - member[grpCnt];
                    if (insertF == result.length) {

                        //②－１最大数を超えない値域
                        if (result[overLength] == 0) {

                            result[overLength] = 1;
                            continue;
                        }
                        //②－２最大数を超えた値域
                        else if (result[overLength - insertF] == 0) {

                            result[overLength - insertF] = 1;
                            continue;
                        }
                    }

                }
            }

            //値の計算
            int amount = 0;
            for (int res : result) {

                amount += res;
            }

            System.out.println(amount);

        } catch (IOException exp) {
            throw new IllegalArgumentException("入力値不正");
        }
    }
}
