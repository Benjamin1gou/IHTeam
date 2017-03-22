/***************************
 * 学籍番号:40024
 * 作成者　:t.ueshima
 * 作成日　:2016/11/02
 * 内容　　:貸出情報を管理する
 * *************************/
package dtd;

import beans.CalendarByKoki;

public class Lone {
	/**
	 * @auther Tester
	 * 2016/11/05
	 * @param loneId int 貸し出しID
	 */
	public int loneId;
	
	/**
	 * @auther Tester
	 * 2016/11/05
	 * @param customerId String 顧客ID
	 */
	public String customerId;
	
	/**
	 * @auther Tester
	 * 2016/11/05
	 * @param address String 顧客住所
	 */
	public String address;
	
	/**
	 * @auther Tester
	 * 2016/11/05
	 * @param phoneNomber String 電話番号
	 */
	public String phoneNomber;
	
	/**
	 * @auther Tester
	 * 2016/11/05
	 * @param loneStartDate CalendarByKoki 貸出日
	 */
	public CalendarByKoki loneStartDate;
	
	/**
	 * @auther Tester
	 * 2016/11/05
	 * @param loneEndDate CalendarByKoki 返却日
	 */
	public CalendarByKoki loneEndDate;
}
