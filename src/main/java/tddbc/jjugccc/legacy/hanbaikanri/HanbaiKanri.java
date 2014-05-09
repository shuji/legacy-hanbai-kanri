package tddbc.jjugccc.legacy.hanbaikanri;

import java.util.HashMap;
import java.util.Map;

/**
 * 販売管理システム。
 * 
 * @author shuji
 * @since 1.0
 */
public class HanbaiKanri {

    private static HanbaiKanri INSTANCE = new HanbaiKanri();

    /**
     * シングルトンインスタンスを取得する。
     * @return 販売管理オブジェクト
     */
    public static HanbaiKanri getInstance() {
        return INSTANCE;
    }
    
    /** 総売上額 */
    private int uriage = 0;
    /** 注文数を保持するマップ */
    private Map<String, Integer> tyumon = new HashMap<>();
    /** 在庫数を保持するマップ */
    private Map<String, Integer> zaiko = new HashMap<>();

    private HanbaiKanri() {
        zaiko.put("雑誌", 5);
        tyumon.put("雑誌", 0);
    }

    /**
     * 注文を追加する。
     * 
     * 注文を追加したならば、対応する在庫が減る。
     * 減った在庫は注文として保存する。
     * 価格×数量に消費税（5%）を追加した合計金額を売上金額に追加する。
     * このモジュールは総売上額を結果として返すこと。
     * 
     * 注文が追加されたならば、商品の在庫数と売上額を表示する。
     * 在庫が足りない場合は注文は失敗する。
     * 
     * @param itemName 商品名
     * @param price 価格
     * @param num 数量
     * @return 総売上額、注文が失敗した場合はnull
     */
    public Integer tyumon(String itemName, int price, int num) {
        Integer zaikoNum = zaiko.get(itemName);
        if (zaikoNum != null && num <= zaikoNum) {
            int newZaikoNum = zaikoNum - num;
            zaiko.put(itemName, newZaikoNum);
            int tyumon = this.tyumon.get(itemName);
            int newTyumon = tyumon + num;
            this.tyumon.put(itemName, newTyumon);
            int uriage = (int) (price * num * 1.05);
            this.uriage += uriage;
            System.out.println("在庫数（" + itemName + "）: " + newZaikoNum);
            System.out.println("売上（" + itemName + "）: " + (int) (price * newTyumon * 1.05));
            return this.uriage;
        } else {
            System.out.println("注文が失敗しました");
            return null;
        }
    }
}
