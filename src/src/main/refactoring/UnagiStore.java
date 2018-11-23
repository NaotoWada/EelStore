package src.main.refactoring;

import java.util.Scanner;

/**
 * paizaの長テーブルのうなぎ屋の解法
 * <p>
 * クラス名を[Main]に変更すれば、そのまま提出可能
 * @author Naoto Wada
 *
 */
public class UnagiStore {

    /**
     * paiza用のメイン関数
     * @param args
     */
    public static void main(String[] args) {

        int result = 0;
        try (Scanner sc = new Scanner(System.in)) {
            String tableAndParties = sc.nextLine();
            int capacity = Integer.parseInt(tableAndParties.split(" ")[0]);
            Chairs chair = new Chairs(capacity);
            int parties = Integer.parseInt(tableAndParties.split(" ")[1]);

            int[][] groups = createGroups(sc, parties);

            result = execute(chair, groups);
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

    /**
     * 実行関数.
     * <p>
     * 座席数と入力グループの情報から顧客を着席させる.<br>
     *
     * @param chair 座席数
     * @param groups 入力グループ情報
     * @return 着席数
     */
    public static int execute(Chairs chair, int[][] groups) {

        for (int grpCnt = 0; grpCnt < groups.length; grpCnt++) {

            int customers = groups[grpCnt][0];
            int position = groups[grpCnt][1];

            chair.occupyIfAvailable(customers, position);
        }
        return chair.countOccupied();
    }
}
