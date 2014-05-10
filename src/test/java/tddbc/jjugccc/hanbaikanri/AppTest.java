package tddbc.jjugccc.hanbaikanri;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.runners.Enclosed;
import org.junit.runner.RunWith;

@RunWith(Enclosed.class)
public class AppTest {

    static final Item MAGAZINE = new Item("雑誌", 500);
    static final Item DVD = new Item("DVD", 2300);

    public static class 在庫がゼロの時 {

        @Test(expected = OutOfStockException.class)
        public void 雑誌を1つ注文すると例外() throws Exception {
            // Setup
            App sut = new App();
            Order order = new Order(MAGAZINE, 1, sut.getTaxRate());
            // Exercise
            sut.offer(order);
        }

    }

    public static class 雑誌の在庫が1の時 {

        App sut;

        @Before
        public void setUp() throws Exception {
            sut = new App();
            sut.stock.append(MAGAZINE, 1);
        }

        @Test
        public void 雑誌を1冊注文する() throws Exception {
            // Setup
            Order order = new Order(MAGAZINE, 1, sut.getTaxRate());
            // Exercise
            sut.offer(order);
            // Verify
            assertThat(sut.orders, is(hasItem(order)));
            assertThat(sut.getTotalSales(), is(525));
        }

        @Test(expected = OutOfStockException.class)
        public void 雑誌を2つ注文すると例外() throws Exception {
            // Setup
            Order order = new Order(MAGAZINE, 2, sut.getTaxRate());
            // Exercise
            sut.offer(order);
        }

        @Test(expected = OutOfStockException.class)
        public void DVDを1つ注文すると例外() throws Exception {
            // Setup
            Order order = new Order(DVD, 1, sut.getTaxRate());
            // Exercise
            sut.offer(order);
        }

    }

}
