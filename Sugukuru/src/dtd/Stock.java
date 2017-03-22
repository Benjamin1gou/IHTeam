/***************************
 * 学籍番号:40024
 * 作成者　:t.ueshima
 * 作成日　:2016/11/02
 * 内容　　:在庫情報を管理する
 * *************************/
package dtd;

import beans.CalendarByKoki;

public class Stock {
	/**
	 * @auther Tester
	 * 2016/11/05
	 * @param stockId int 在庫ID
	 */
	public int stockId;
	/**
	 * @auther Tester
	 * 2016/11/05
	 * @param productId String 商品ID
	 */
	public String productId;
	/**
	 * @auther Tester
	 * 2016/11/05
	 * @param productName String 商品名
	 */
	public String productName;
	/**
	 * @auther Tester
	 * 2016/11/05
	 * @param stockAmount int 実在庫数
	 */
	public int stockAmount;
	/**
	 * @auther Tester
	 * 2016/11/05
	 * @param provisionStock int 引き当て在庫数
	 */
	public int provisionStock;
	/**
	 * @auther Tester
	 * 2016/11/05
	 * @param entryDate CalendarByKoki 登録日
	 */
	public CalendarByKoki entryDate;
}
