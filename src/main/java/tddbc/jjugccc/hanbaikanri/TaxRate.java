package tddbc.jjugccc.hanbaikanri;

/**
 * 消費税率クラス。
 *
 * 消費税を考慮した金額計算を行う。
 * 
 */
public class TaxRate {
    
    private int taxRate;
    
    public TaxRate(int taxRate) {
        this.taxRate = taxRate;
    }

    public int calculate(int itemPrice) {
        return itemPrice * (100 + taxRate) / 100;
    }
}
