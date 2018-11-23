package src.test;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import org.junit.Test;

import src.main.UnagiStore;

public class EelShopTest {

    @Test
    public void test_6人テーブルに3名と1名と2名がそれぞれ席を指定した場合は最初の2グループだけ全員が座れる() {
        // paizaの例1
        int table = 6;
        int[][] groupA = new int[][] { { 3, 2 }, { 1, 6 }, { 2, 5 } };
        int result = UnagiStore.execute(table, groupA);
        assertThat(result, is(4));
    }

    @Test
    public void test_12人テーブルに4名ずつの6グループが席を指定した場合は全席分埋まって座れないグループがある() {
        // paizaの例2
        int table = 12;
        int[][] group = new int[][] { { 4, 6 }, { 4, 8 }, { 4, 10 }, { 4, 12 }, { 4, 2 }, { 4, 4 } };
        int result = UnagiStore.execute(table, group);
        assertThat(result, is(12));
    }

}
