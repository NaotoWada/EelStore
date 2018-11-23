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

            Group[] groups = createGroups(sc, parties);

            result = execute(chair, groups);
        }
        System.out.println(result);
    }

    private static Group[] createGroups(Scanner sc, int parties) {
        Group[] groups = new Group[parties];
        for (int i = 0; i < parties; i++) {
            String[] groupAndPosition = sc.nextLine().split(" ");
            int member = Integer.parseInt(groupAndPosition[0]);
            int position = Integer.parseInt(groupAndPosition[1]);

            groups[i] = new Group(member, position);
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
    public static int execute(Chairs chair, Group[] groups) {

        for (Group group : groups) {
            chair.occupyIfAvailable(group);
        }
        return chair.countOccupied();
    }
}
