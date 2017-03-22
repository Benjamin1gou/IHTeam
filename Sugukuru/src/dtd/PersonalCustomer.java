/***************************
 * 学籍番号:40024
 * 作成者　:t.ueshima
 * 作成日　:2016/11/02
 * 内容　　:個人顧客情報を管理するマスタ。
 * *************************/
package dtd;

import beans.CalendarByKoki;

public class PersonalCustomer extends Customer{
	/**
	 * @auther Tester
	 * 2016/11/02
	 * @param mailAddress String メールアドレス
	 */
	public String mailAddress;
	
	/**
	 * @auther Tester
	 * 2016/11/02
	 * @param password String パスワード
	 */
	public String password;
	
	/**
	 * @auther Tester
	 * 2016/11/02
	 * @param admiddionDate Date　入会日付
	 */
	public CalendarByKoki admiddionDate;
	
	/**
	 * @auther Tester
	 * 2016/11/02
	 * @param admissionFlg int 入会状態
	 */
	public int admissionFlg = 0;
}
