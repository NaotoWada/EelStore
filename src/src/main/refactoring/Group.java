package src.main.refactoring;

public class Group {

    private int memberNum;
    private int position;

    public Group(int memberNum, int position) {
        this.memberNum = memberNum;
        this.position  =position;
    }

    public int getNumber() {
        return this.memberNum;
    }

    public int getPosition() {
        return this.position;
    }

}
