package src.test.refactoring;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import org.junit.Test;

import src.main.refactoring.Chairs;
import src.main.refactoring.UnagiStore;

public class EelShopTest {

    @Test
    public void test_6人テーブルに3名と1名と2名がそれぞれ席を指定した場合は最初の2グループだけ全員が座れる() {
        // paizaの例1
        Chairs chair = createChairs(6);
        int[][] groupA = new int[][] { { 3, 2 }, { 1, 6 }, { 2, 5 } };
        int result = UnagiStore.execute(chair, groupA);
        assertThat(result, is(4));
    }

    private Chairs createChairs(int chairNum) {
        return new Chairs(chairNum);
    }

    @Test
    public void test_12人テーブルに4名ずつの6グループが席を指定した場合は全席分埋まって座れないグループがある() {
        // paizaの例2
        Chairs chair = createChairs(12);
        int[][] group = new int[][] { { 4, 6 }, { 4, 8 }, { 4, 10 }, { 4, 12 }, { 4, 2 }, { 4, 4 } };
        int result = UnagiStore.execute(chair, group);
        assertThat(result, is(12));
    }

    @Test
    public void test_4人テーブルに1名と1名の2つのグループだった場合で指定先が被った場合は1名のみ座れる() {
        Chairs chair = createChairs(4);
        int[][] group = new int[][] { { 1, 4 }, { 1, 4 } };
        int result = UnagiStore.execute(chair, group);
        assertThat(result, is(1));
    }

    @Test
    public void test_4人テーブルに1名と4名の2つのグループだった場合に1名のみ座れる() {
        Chairs chair = createChairs(4);
        int[][] group = new int[][] { { 1, 4 }, { 4, 1 } };
        int result = UnagiStore.execute(chair, group);
        assertThat(result, is(1));
    }

    @Test
    public void test_4人テーブルに4名1グループだった場合に4名が全員座れる() {
        Chairs chair = createChairs(4);
        int[][] group = new int[][] { { 4, 1 } };
        int result = UnagiStore.execute(chair, group);
        assertThat(result, is(4));
    }
}
