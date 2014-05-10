package tddbc.jjugccc.hanbaikanri;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.runners.Enclosed;
import org.junit.runner.RunWith;

@RunWith(Enclosed.class)
public class OrderApplicationTest {

    static final TaxRate TAXRATE = new TaxRate(5);
    
    static final Item MAGAZINE = new Item("雑誌", 500);
    static final Item DVD = new Item("DVD", 2300);

    public static class 在庫がゼロの時 {

        @Test(expected = OutOfStockException.class)
        public void 雑誌を1つ注文すると例外() throws Exception {
            // Setup
            OrderApplication sut = new OrderApplication();
            Order order = new Order(MAGAZINE, 1, TAXRATE);
            // Exercise
            sut.offer(order);
        }

    }

    public static class 雑誌の在庫が1の時 {

        OrderApplication sut;

        @Before
        public void setUp() throws Exception {
            sut = new OrderApplication();
            sut.stock.put(MAGAZINE, 1);
        }

        @Test
        public void 雑誌を1冊注文する() throws Exception {
            // Setup
            Order order = new Order(MAGAZINE, 1, TAXRATE);
            // Exercise
            sut.offer(order);
            // Verify
            assertThat(sut.orders, is(hasItem(order)));
            assertThat(sut.getTotalSales(), is(525));
        }

        @Test(expected = OutOfStockException.class)
        public void 雑誌を2つ注文すると例外() throws Exception {
            // Setup
            Order order = new Order(MAGAZINE, 2, TAXRATE);
            // Exercise
            sut.offer(order);
        }

        @Test(expected = OutOfStockException.class)
        public void DVDを1つ注文すると例外() throws Exception {
            // Setup
            Order order = new Order(DVD, 1, TAXRATE);
            // Exercise
            sut.offer(order);
        }

    }

}
