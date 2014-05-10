package tddbc.jjugccc.hanbaikanri;

/**
 * 売上クラス。
 * 
 * 売上の計算に関する責務を持つ。
 * 売上は注文リストから計算できる。
 * 
 * @author shuji
 */
public class Sales {

    private Orders orders;

    public Sales(Orders orders) {
        this.orders = orders;
    }

    public int getTotalSales() {
        int total = 0;
        for (Order order : this.orders) {
            total += order.getAmount();
        }
        return total;
    }

}
