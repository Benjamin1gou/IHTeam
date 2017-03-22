/***************************
 * 学籍番号:40024
 * 作成者　:t.ueshima
 * 作成日　:2016/11/02
 * 内容　　:督促情報を管理する
 * *************************/
package dtd;

import beans.CalendarByKoki;

public class Reminder {
	/**
	 * @auther Tester
	 * 2016/11/05
	 * @param settlementID int 決済ID
	 */
	public int settlementID;
	
	/**
	 * @auther Tester
	 * 2016/11/05
	 * @param num int 行番号
	 */
	public int num;
	
	/**
	 * @auther Tester
	 * 2016/11/05
	 * @param dunningDate CalendarByKoki 督促状送付日
	 */
	public CalendarByKoki dunningDate;
	
	/**
	 * @auther Tester
	 * 2016/11/05
	 * @param dunningFlg int 督促状送付フラグ
	 */
	public int dunningFlg;
	
	/**
	 * @auther Tester
	 * 2016/11/05
	 * @param note String 備考
	 */
	public String note;
}
