package tddbc.jjugccc.hanbaikanri;


/**
 * 注文時に、在庫切れであった場合に送出される例外クラス。
 * 
 * 在庫切れは事前にチェックするのが基本ではあるが、
 * トランザクションのタイミングで在庫が切れている可能性もある。
 * このため、チェック例外として確実にハンドリングできるようにする。

 * @author shuji
 */
@SuppressWarnings("serial")
public class OutOfStockException extends Exception {

    Item item;

    public OutOfStockException(Item item) {
        this.item = item;
    }
}
