package tddbc.jjugccc.legacy.hanbaikanri;

import java.util.HashMap;

/**
 * 販売管理システム。
 * 
 * @author shuji
 * @since 平成5年 2月 21日
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
     * 価格×数量に5%を追加した合計金額を売上金額に追加する。
     * 
     * 注文が追加されたならば、商品の在庫数と売上額を表示する。
     * 
     * @param itemName 商品名
     * @param price 価格
     * @param num 数量
     * @return 総売上額
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
/*
            平成9年 4月 21日 バグ修正 — 表示される売上が5%対応されていなかった
            System.out.println("売上（" + itemName + "）: " + (int) (price * newTyumon * 1.03));
*/
            System.out.println("売上（" + itemName + "）: " + (int) (price * newTyumon * 1.05));
            // 総売上額を返す
            return this.uriage;
        } else { // 注文が失敗する場合
            // nullを返す
            return null;
        }
    }

/*
   平成5年 3月 23日 追加
 */
    public void addZaiko(String itemName, int num) {
        // 何故かnumに負数が設定される時があるが、その場合は何もしない
        if (1 <= num) { // 数量が追加可能な場合
            // intにすると何故かNullPointerExceptionが発生する
            Integer zaikoNum = (Integer) zaiko.get(itemName);
            if (zaikoNum != null) { // 既に在庫が存在する場合
                // 在庫数＋数量を在庫数にする
                zaiko.put(itemName, zaikoNum + num);
            } else { // 在庫が存在しない場合
                // 新たに商品在庫を追加する
                zaiko.put(itemName, num);
            }
        }
    }
/*
    平成5年 3月 23日 追加  ここまで
 */
}
