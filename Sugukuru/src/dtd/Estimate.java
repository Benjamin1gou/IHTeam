/***************************
 * 学籍番号:40024
 * 作成者　:t.ueshima
 * 作成日　:2016/11/02
 * 内容　　:見積もり情報を管理する
 * *************************/
package dtd;

import beans.CalendarByKoki;

public class Estimate {
	/**
	 * @auther Tester
	 * 2016/11/02
	 * @param estimatesId int 見積もりID
	 */
	public int estimatesId = 0;
	
	/**
	 * @auther Tester
	 * 2016/11/02
	 * @param customerId String 顧客ID
	 */
	public String customerId;
	
	/**
	 * @auther Tester
	 * 2016/11/02
	 * @param deliveryDate Date 希望納品日
	 */
	public CalendarByKoki deliveryDate;
	
	/**
	 * @auther Tester
	 * 2016/11/02
	 * @param recoveryPricess int 回収方法
	 */
	public int recoveryPricess = 0;
	
	/**
	 * @auther Tester
	 * 2016/11/02
	 * @param expirationDate Date 有効期限
	 */
	public CalendarByKoki expirationDate;
	
	/**
	 * @auther Tester
	 * 2016/11/02
	 * @param note String 備考
	 */
	public String note;
	
	/**
	 * @auther Tester
	 * 2016/11/02
	 * @param employmentId int 担当社員ID
	 */
	public int employmentId = 0;
	
	/**
	 * @auther Tester
	 * 2016/11/02
	 * @param createDate Date 作成日
	 */
	public CalendarByKoki createDate;
	
	/**
	 * @auther Tester
	 * 2016/11/02
	 * @param postage int 送料
	 */
	public int postage = 0;
	
	/**
	 * @auther Tester
	 * 2016/11/02
	 * @param noTaxTotalFee int 税込み合計金額
	 */
	public int noTaxTotalFee = 0;
	
	/**
	 * @auther Tester
	 * 2016/11/02
	 * @param taxFee int 消費税
	 */
	public int taxFee = 0;
	
	/**
	 * @auther Tester
	 * 2016/11/02
	 * @param totalFee int 合計金額
	 */
	public int totalFee = 0;

}
