package tddbc.jjugccc.legacy.hanbaikanri;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import org.junit.Test;

public class HanbaiKanriTest {

    @Test
    public void test() {
        HanbaiKanri hanbaiKanri = HanbaiKanri.getInstance();
        Integer uriage = hanbaiKanri.tyumon("雑誌", 500, 2);
        assertThat(uriage, is(1050));
        uriage = hanbaiKanri.tyumon("雑誌", 500, 1);
        assertThat(uriage, is(1575));
        uriage = hanbaiKanri.tyumon("雑誌", 500, 2);
        assertThat(uriage, is(2625));
    }

    @Test
    public void test2() {
        HanbaiKanri hanbaiKanri = HanbaiKanri.getInstance();
        Integer uriage = hanbaiKanri.tyumon("お弁当", 300, 2);
        assertThat(uriage, is(nullValue()));
    }


    @Test
    public void test3() {
        HanbaiKanri hanbaiKanri = HanbaiKanri.getInstance();
        hanbaiKanri.addZaiko("雑誌", 2);
    }
}
