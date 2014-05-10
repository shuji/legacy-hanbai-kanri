package tddbc.jjugccc.hanbaikanri;

public class App {

    private int taxRate = 5;
    
    Stock stock = new Stock();
    Orders orders = new Orders();
    Sales sales = new Sales(orders);
    
    public void offer(Order order) throws OutOfStockException {
        if (!stock.hasStock(order)) {
            throw new OutOfStockException(order.item);
        }
        orders.add(order);
    }
    
    public int getTotalSales() {
        return sales.getTotalSales();
    }

    public int getTaxRate() {
        return taxRate;
    }

}
