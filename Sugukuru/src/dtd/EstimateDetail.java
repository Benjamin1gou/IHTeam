/***************************
 * 学籍番号:40024
 * 作成者　:t.ueshima
 * 作成日　:2016/11/02
 * 内容　　:見積もり情報を管理する
 * *************************/
package dtd;

public class EstimateDetail {
	/**
	 * @auther Tester
	 * 2016/11/02
	 * @param estimateId int 見積もりID
	 */
	public int estimateId = 0;
	
	/**
	 * @auther Tester
	 * 2016/11/02
	 * @param numberId int 行番号
	 */
	public int numberId = 0;
	
	/**
	 * @auther Tester
	 * 2016/11/02
	 * @param productId String 商品Id
	 */
	public String productId;
	
	/**
	 * @auther Tester
	 * 2016/11/02
	 * @param productname String 商品名
	 */
	public String productname;
	
	/**
	 * @auther Tester
	 * 2016/11/02
	 * @param price int 単価
	 */
	public int price = 0;
	
	/**
	 * @auther Tester
	 * 2016/11/02
	 * @param number int 数量
	 */
	public int number = 0;
	
	/**
	 * @auther Tester
	 * 2016/11/02
	 * @param noTaxSubtotalFee int 税抜小計金額
	 */
	public int noTaxSubtotalFee = 0;
	
	/**
	 * @auther Tester
	 * 2016/11/02
	 * @param tax int 消費税
	 */
	public int tax = 0;
	
	/**
	 * @auther Tester
	 * 2016/11/02
	 * @param subtotalFee int 税込小計金額
	 */
	public int subtotalFee = 0;
	
	/**
	 * @auther Tester
	 * 2016/11/02
	 * @param stockNumber int 在庫ID
	 */
	public int stockNumber = 0;
	
}
