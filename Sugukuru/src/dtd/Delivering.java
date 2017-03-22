/***************************
 * 学籍番号:40024
 * 作成者　:t.ueshima
 * 作成日　:2016/11/02
 * 内容　　:納品情報を管理する
 * *************************/
package dtd;

import beans.CalendarByKoki;

public class Delivering {
	/**
	 * @auther Tester
	 * 2016/11/02
	 * @param orderId int 受注ID
	 */
	public int orderId;
	
	/**
	 * @auther Tester
	 * 2016/11/02
	 * @param makedDate CalendarByKoki 作成日
	 */
	public CalendarByKoki makedDate;
	
	/**
	 * @auther Tester
	 * 2016/11/02
	 * @param deliveredDate CalendarByKoki 納品受領日時
	 * 
	 */
	public CalendarByKoki deliveredDate;
}
