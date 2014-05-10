package tddbc.jjugccc.hanbaikanri;

/**
 * 商品注文アプリケーション。
 *
 * 注文された商品を在庫から引き当てる。
 * 注文された商品の合計金額を返す。
 */
public class OrderApplication {

    Stock stock = new Stock();
    Orders orders = new Orders();
    
    public void offer(Order order) throws OutOfStockException {
        if (!stock.hasStock(order)) {
            throw new OutOfStockException(order.item);
        }
        orders.add(order);
    }
    
    public int getTotalSales() {
        Sales sales = new Sales(orders);
        return sales.getTotalSales();
    }
}
