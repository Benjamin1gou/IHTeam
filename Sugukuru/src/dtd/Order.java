/***************************
 * 学籍番号:40024
 * 作成者　:t.ueshima
 * 作成日　:2016/11/02
 * 内容　　:受注情報を管理する
 * *************************/
package dtd;

import beans.CalendarByKoki;

public class Order {
	/**
	 * @auther Tester
	 * 2016/11/02
	 * @param orderId int 受注ID
	 */
	public int orderId = 0;

	/**
	 * @auther Tester
	 * 2016/11/02
	 * @param estimateId int 見積もりID
	 */
	public int estimateId = 0;

	/**
	 * @auther Tester
	 * 2016/11/02
	 * @param employementId int 担当社員ID
	 */
	public int employementId = 0;
	/**
	 * @auther Tester
	 * 2016/11/02
	 * @param orderDate CalendarByKoki 受注日時
	 */
	public CalendarByKoki orderDate;
	/**
	 * @auther Tester
	 * 2016/11/02
	 * @param customerId String 顧客ID
	 */
	@Request
	public String customerId;
	@Request
	/**
	 * @auther Tester
	 * 2016/11/02
	 * @param customerName String 顧客名
	 */
	public String customerName;

	/**
	 * @auther Tester
	 * 2016/11/02
	 * @param deliveryAddress String 納品先住所
	 */
	public String deliveryAddress;

	/**
	 * @auther Tester
	 * 2016/11/02
	 * @param deliveryDate CalendarByKoki　納品日
	 */
	public CalendarByKoki deliveryDate;
	/**
	 * 出荷日時
	 * @auther 浩生
	 * 2016/11/08
	 * @param shipmentDate CalendarByKoki
	 */
	public CalendarByKoki shipmentDate;
	{
		customerId=new String();
	}
}
