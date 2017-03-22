/***************************
 * 学籍番号:40313
 * 作成者　:k.koki
 * 作成日　:2016/11/14
 * 内容　　:請求処理を行う際の箱クラス。
 * *************************/
package dtd;

import java.util.ArrayList;

import beans.CalendarByKoki;

import common.Common.RecallManner;

public class DoClaim extends Earnings {
	/**
	 * 締日 リクエスト用
	 *
	 * @auther 浩生 2016/11/14
	 * @param cutDay
	 *            String
	 */
	@Request
	public String cutOfDay;

	public CutDay cutDay;

	/**
	 * 繰り越し金
	 * 税抜
	 * @auther 浩生 2016/11/14
	 * @param over_price
	 *            int
	 */
	public int overPrice;

	/**
	 * 繰り越し金
	 * 税
	 * @auther 浩生
	 * 2016/11/18
	 * @param overTax int
	 */
	public int overTax;

	/**
	 * 回収方法
	 *
	 * @auther 浩生 2016/11/14
	 * @param recallManner
	 *            int
	 */
	public String recallManner;

	/**
	 * 回収方法を表示用で取得する。
	 *
	 * @auther 浩生 2016/11/14
	 * @return
	 */
	public String getRecallManner() {
		RecallManner manner = RecallManner.indexOf(this.recallManner);
		if (manner == null)
			return "登録されてない回収方法";
		return manner.value;
	}

	/**
	 * この売上、繰り越し情報の合算金額を取得します。 新規請求額（税抜）＋新規請求額（税）＋繰り越し金
	 *
	 * @auther 浩生 2016/11/14
	 * @return
	 */
	public int getAllTotal() {
		return this.noTaxTotalFee + this.taxFee + this.overPrice;
	}

	@RequestArray
	public String[] rCorporationIds;

	/**
	 * 請求明細に当たる、売上明細
	 * @auther 浩生
	 * 2016/11/15
	 * @param details ArrayList<EstimateDetail>
	 */
	public ArrayList<EarningsDetail> details;

	/**
	 * 印刷日
	 * @auther 浩生
	 * 2016/11/15
	 * @param printDate CalendarByKoki
	 */
	public CalendarByKoki printDate;

	/**
	 * 請求ID
	 * @auther 浩生
	 * 2016/11/15
	 * @param settlementId int
	 */
	public int settlementId;

	/**
	 * 前回請求額
	 * @auther 浩生
	 * 2016/11/15
	 * @param settlementBefore int
	 */
	public int settlementBefore;

	/**
	 * 入金額
	 * @auther 浩生
	 * 2016/11/15
	 * @param payment int
	 */
	public int payment;
	{
		this.cutOfDay = new String();
		this.cutDay = new CutDay();
		this.overPrice = 0;
		this.details=new ArrayList<EarningsDetail>();
		this.printDate=new CalendarByKoki();
	}

}
