package src.main;

import java.util.Scanner;

/**
 * paizaの長テーブルのうなぎ屋の解法
 * <p>
 * クラス名を[Main]に変更すれば、そのまま提出可能
 * @author Naoto Wada
 *
 */
public class UnagiStore {

    public static void main(String[] args) {

        int result = 0;
        try (Scanner sc = new Scanner(System.in)) {
            String tableAndParties = sc.nextLine();
            int capacity = Integer.parseInt(tableAndParties.split(" ")[0]);
            int parties = Integer.parseInt(tableAndParties.split(" ")[1]);

            int[][] groups = createGroups(sc, parties);

            result = execute(capacity, groups);
        }
        System.out.println(result);
    }

    private static int[][] createGroups(Scanner sc, int parties) {
        int[][] groups = new int[parties][2];
        for (int i = 0; i < parties; i++) {
            String[] groupAndPosition = sc.nextLine().split(" ");
            groups[i][0] = Integer.parseInt(groupAndPosition[0]);
            groups[i][1] = Integer.parseInt(groupAndPosition[1]);
        }
        return groups;
    }

    public static int execute(int capacity, int[][] groups) {

        Chairs chair = new UnagiStore().new Chairs(capacity);

        for (int grpCnt = 0; grpCnt < groups.length; grpCnt++) {

            int customers = groups[grpCnt][0];
            int position = groups[grpCnt][1];

            chair.occupyIfAvailable(customers, position);
        }
        return chair.countOccupied();
    }

    /**
     * 座席管理クラス
     * <p>
     * 指定した座席に客が座れるかどうかを管理し、着席可能な場合のみ席を確保する.<br>
     * 指定値を超えた場合でも、エラーとはならずに単純に着席を行わない<br>
     * @author Naoto Wada
     *
     */
    public class Chairs {
        private boolean[] chairs;

        public Chairs(int capacity) {
            this.chairs = new boolean[capacity];
        }

        /**
         * 座席の着席数をカウントする.
         * <p>
         * 全てが空席だった場合は[0]を返却する.
         * @return 着席数
         */
        public int countOccupied() {
            int occupied = 0;
            for (boolean sut : chairs) {
                if (sut) {
                    occupied++;
                }
            }
            return occupied;
        }

        /**
         * 指定された座席から顧客が着席できるかを判定した後着席させる.
         * <p>
         * 指定した座席が全席数を超えている場合は着席させない.<br>
         * 例えば、席数：4, 客数x, 指定席5<br>
         * <p>
         * 顧客が連番で座れない場合は着席させない.<br>
         * 例えば、席数：4, グループ1[客2, 指定席1], グループ2[客2, 指定席2]
         * @param customers 客数
         * @param position 指定席
         */
        public void occupyIfAvailable(int customers, int position) {

            if (hasAlreadyOccupied(customers, position)) {
                return;
            }
            occupySeat(customers, position);
        }

        private boolean hasAlreadyOccupied(int customers, int position) {
            int posit = position - 1;
            for (int i = 0; i < customers; i++) {
                if (chairs[posit % chairs.length]) {
                    return true;
                }
                posit++;
            }
            return false;
        }

        /**
         * 指定された座席数から顧客を着席させる.
         * <p>
         * @param customers
         * @param position
         */
        private void occupySeat(int customers, int position) {
            int posit = position - 1;
            for (int i = 0; i < customers; i++) {
                chairs[posit % chairs.length] = true;
                posit++;
            }
        }

    }
}
