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
            int capacity = convertInt(tableAndParties.split(" ")[0]);
            int parties = convertInt(tableAndParties.split(" ")[1]);

            Chairs chair = new Chairs(capacity);
            Group[] groups = createGroups(sc, parties);

            result = execute(chair, groups);
        }
        System.out.println(result);
    }

    private static Group[] createGroups(Scanner sc, int parties) {
        Group[] groups = new Group[parties];
        for (int i = 0; i < parties; i++) {
            String[] groupAndPosition = sc.nextLine().split(" ");
            int member = convertInt(groupAndPosition[0]);
            int position = convertInt(groupAndPosition[1]);

            groups[i] = new Group(member, position);
        }
        return groups;
    }

    private static int convertInt(String strNum) {
        return Integer.parseInt(strNum);
    }

    /**
     * 実行関数.
     * <p>
     * 座席数と入力グループの情報から顧客を着席させる.<br>
     * グループ毎に毎回判定を行った後、席に座れるようなら着席させる<br>
     *
     * @param chair 座席情報
     * @param groups グループ情報配列
     * @return
     */
    public static int execute(Chairs chair, Group[] groups) {

        for (Group group : groups) {
            chair.occupyIfAvailable(group);
        }
        return chair.countOccupied();
    }
}
