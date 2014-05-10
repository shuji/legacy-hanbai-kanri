package tddbc.jjugccc.hanbaikanri;

/**
 * 注文クラス。
 * 
 * 注文をオブジェクトとして扱い、注文の小計計算を責務として割り当てる。
 * また、消費税変動に対応するため、外部から消費税はパラメータとして与える。
 * 
 * @author shuji
 */
public class Order {

    Item item;
    int num;
    TaxRate taxRate;

    public Order(Item item, int num, TaxRate taxRate) {
        this.item = item;
        this.num = num;
        this.taxRate = taxRate;
    }

    public int getAmount() {
        return taxRate.calculate(item.price * num);
    }
    
    public String toOutputString() {
        return String.format("売上（%s）: %d", item.name, getAmount());
    }

}
