package src.main.refactoring;

/**
 * グループ情報クラス.
 * <p>
 * 以下の状態を保持し、公開する.<br>
 * ・int:グループ人数<br>
 * ・int:座りたい席の位置情報<br>
 *
 * @author Naoto Wada
 *
 */
public class Group {

    private int memberNum;
    private int position;

    /**
     * コンストラクタ
     * @param memberNum グループ人数
     * @param position 座りたい席の位置情報
     */
    public Group(int memberNum, int position) {
        this.memberNum = memberNum;
        this.position = position;
    }

    /**
     * グループ人数を取得する.
     * <p>
     * @return グループ人数
     */
    public int getNumber() {
        //でききれば外部に公開したくない
        return this.memberNum;
    }

    /**
     * 座りたい席の位置情報を取得する.
     * <p>
     * @return 座りたい席の位置情報
     */
    public int getPosition() {
        //でききれば外部に公開したくない
        return this.position;
    }

}
