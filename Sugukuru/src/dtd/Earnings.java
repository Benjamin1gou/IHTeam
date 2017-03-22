/***************************
 * 学籍番号:40024
 * 作成者　:t.ueshima
 * 作成日　:2016/11/02
 * 内容　　:売上情報を管理する
 * *************************/
package dtd;

import beans.CalendarByKoki;

public class Earnings {
	/**
	 * @auther Tester
	 * 2016/11/02
	 * @param earningId int 売上ID
	 */
	public int earningId;
	/**
	 * @auther Tester
	 * 2016/11/02
	 * @param orderId int 受注ID
	 */
	public int orderId;

	/**
	 * @auther Tester
	 * 2016/11/02
	 * @param customerId String 顧客ID
	 */
	@Request
	public String customerId;

	/**
	 * @auther Tester
	 * 2016/11/02
	 * @param employmentId int 担当者ID
	 */
	public int employmentId;

	/**
	 * @auther Tester
	 * 2016/11/02
	 * @param shipmentDate CalendarByKoki 出荷日時
	 */
	public CalendarByKoki shipmentDate;

	/**
	 * @auther Tester
	 * 2016/11/02
	 * @param noTaxTotalFee int 税抜合計金額
	 */
	public int noTaxTotalFee;

	/**
	 * @auther Tester
	 * 2016/11/02
	 * @param taxFee int 消費税
	 */
	public int taxFee;

	/**
	 * @auther Tester
	 * 2016/11/02
	 * @param note String 備考
	 */
	public String note;

	/**
	 * 顧客名
	 * @auther 浩生
	 * 2016/11/13
	 * @param customerName String
	 */
	public String customerName;

	/**
	 * 決算ID
	 * DBがデフォルトNullなのでここでは文字列として使う。
	 * このフィールドに入るのは、決済DTDと同等の数字文字列
	 * を入れる。
	 * @auther 浩生
	 * 2016/11/14
	 * @param settlementId String
	 * @see Settlement#settlementId
	 */
	public String settlementId;
}
