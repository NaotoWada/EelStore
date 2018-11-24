package src.main.refactoring;

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
     * 例えば、席数：4, グループ1[客2, 指定席1], グループ2[客2, 指定席2]<br>
     *
     * @param group 指定席情報と顧客数が入ったグループ情報
     */
    public void occupyIfAvailable(Group group) {
        if (hasAlreadyOccupied(group)) {
            return;
        }
        occupySeat(group);
    }

    private boolean hasAlreadyOccupied(Group group) {
        int posit = group.getPosition() - 1;
        for (int i = 0; i < group.getNumber(); i++) {
            if (chairs[posit % chairs.length]) {
                return true;
            }
            posit++;
        }
        return false;
    }

    private void occupySeat(Group group) {
        int posit = group.getPosition() - 1;
        for (int i = 0; i < group.getNumber(); i++) {
            chairs[posit % chairs.length] = true;
            posit++;
        }
    }

}