package tddbc.jjugccc.legacy.hanbaikanri;

import java.util.HashMap;

/**
 * 販売管理システム。
 * 
 * @author shuji
 * @since 1.0
 */
@SuppressWarnings("all")
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
    private HashMap tyumon = new HashMap();
    /** 在庫数を保持するマップ */
    private HashMap zaiko = new HashMap();

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
        // intにすると何故かNullPointerExceptionが発生する
        Integer zaikoNum = (Integer) zaiko.get(itemName);
        if (zaikoNum != null && num <= zaikoNum) { // 注文が可能な場合
            // 対応する在庫を減らす
            int newZaikoNum = zaikoNum - num;
            zaiko.put(itemName, newZaikoNum);
            // 注文を保存する
            int tyumon = (int) this.tyumon.get(itemName);
            int newTyumon = tyumon + num;
            this.tyumon.put(itemName, newTyumon);
/*
            平成9年 3月 31日 消費税の修正 テスト一郎
            // 売上は価格×数量に消費税（3%）を加味する
            int i = price * num;
            int uriage = (int) (i * 1.03);
*/
            // 合計金額の計算：価格×数量に消費税（5%）
            int uriage = (int) (price * num * 1.05);
            // 合計金額を売上金額に追加
            this.uriage += uriage;
            // 商品の在庫数を表示する。
            System.out.println("在庫数（" + itemName + "）: " + newZaikoNum);
            // 商品の売上額を表示する。
            System.out.println("売上（" + itemName + "）: " + (int) (price * newTyumon * 1.05));
            // 総売上額を返す
            return this.uriage;
        } else { // 注文が失敗する場合
            // nullを返す
            return null;
        }
    }

    /**
     * 商品在庫を登録する。
     *
     * 販売管理システムに商品在庫を登録する。
     * @param ietmName 商品名
     * @param num 数量
     * @since 1.1
     */
    public void addZaiko(String itemName, int num) {
        // 在庫を登録
        zaiko.put(itemName, num);
    }
}
