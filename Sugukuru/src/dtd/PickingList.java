/***************************
 * 学籍番号:40024
 * 作成者　:t.ueshima
 * 作成日　:2016/11/02
 * 内容　　:ピッキング情報を管理するマスタ。
 * *************************/
package dtd;

public class PickingList {
	/**
	 * @auther Tester
	 * 2016/11/07
	 * @param pickId String 行番号
	 */
	public String pickId;
	
	/**
	 * @auther Tester
	 * 2016/11/07
	 * @param productId String 商品番号
	 */
	public String productId;
	
	/**
	 * @auther Tester
	 * 2016/11/07
	 * @param productName String 商品名
	 */
	public String productName;
	
	/**
	 * @auther Tester
	 * 2016/11/07
	 * @param rackNumber String 棚番号
	 */
	public String rackNumber;
	
	/**
	 * @auther Tester
	 * 2016/11/20
	 * @param stock String　在庫数
	 */
	public String stock;
	
	/**
	 * @auther Tester
	 * 2016/11/07
	 * @param needs String 必要数
	 */
	public String needs;
	
	/**
	 * @auther Tester
	 * 2016/11/07
	 * @param pickNum String ピッキング数
	 */
	public String pickNum;
	
	/**
	 * @auther Tester
	 * 2016/11/16
	 * @param inspectedAmount String 検品数
	 */
	public String inspectedAmount;
	
	/**
	 * @auther Tester
	 * 2016/11/07
	 * @param pickState String 作業ステップ
	 */
	public String pickState;
	
	/**
	 * @auther Tester
	 * 2016/11/20
	 * @param unit String 商品単位
	 */
	public String unit;
}
