package tddbc.jjugccc.hanbaikanri;

import java.util.HashMap;

/**
 * 在庫クラス。
 * 
 * 型情報を持ったMapの拡張クラスとして実装。
 * 在庫の追加や、在庫の確認について責務を持つ。
 * 
 * @author shuji
 */
@SuppressWarnings("serial")
public class Stock extends HashMap<Item, Integer>{

    @Override
    public Integer put(Item item, Integer num) {
        Integer stock = this.get(item);
        if (stock == null) {
            return super.put(item,  num);
        } else {
            return super.put(item, stock + num);
        }
    }

    public boolean hasStock(Order order) {
        return hasStock(order.item, order.num);
    }
    
    public boolean hasStock(Item item, int num) {
        return num <= getStock(item);
    }
    
    public int getStock(Item item) {
        Integer stock = this.get(item);
        return stock != null ? stock.intValue() : 0;
    }
    
    public String toOutputString(Item item) {
        return String.format("在庫数（%s）: %d", item.name, getStock(item));
    }

    
}
