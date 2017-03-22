/***************************
 * 学籍番号:40313
 * 作成者　:k.koki
 * 作成日　:2016/11/02
 * 内容　　:
 * *************************/
package dtd;

import java.util.Date;

import beans.CalendarByKoki;

public class CorporationCustomer extends Customer{
	/**
	 * @auther Tester
	 * 2016/11/02
	 * @param corporatinName String 法人名
	 */
	public String corporatinName;

	/**
	 * @auther Tester
	 * 2016/11/02
	 * @param generation int 世代
	 */
	public int generation;

	/**
	 * @auther Tester
	 * 2016/11/02
	 * @param abbreviationName String 法人名略称
	 */
	public String abbreviationName;

	/**
	 * @auther Tester
	 * 2016/11/02
	 * @param officeName String 営業所名
	 */
	public String officeName;

	/**
	 * @auther Tester
	 * 2016/11/02
	 * @param cutOffDate Date 締め日
	 */
	public CalendarByKoki cutOffDate;

	/**
	 * @auther Tester
	 * 2016/11/02
	 * @param recallManner int 回収方法
	 */
	public int  recallManner = 0;

	/**
	 * @auther Tester
	 * 2016/11/02
	 * @param delivery String 届け先住所
	 */
	public String deliveryAddress;

	/**
	 * @auther Tester
	 * 2016/11/02
	 * @param businessChagePreace String 営業担当書
	 */
	public String businessChagePreace;

	/**
	 * @auther Tester
	 * 2016/11/02
	 * @param creditLimit int 与信限度額
	 */
	public int creditLimit = 0;

	/**
	 * @auther Tester
	 * 2016/11/02
	 * @param recodeDate Date 登録日
	 */
	public CalendarByKoki recodeDate;

	/**
	 * @auther Tester
	 * 2016/11/02
	 * @param startDate Date 開始日
	 */
	public CalendarByKoki startDate;

	/**
	 * @auther Tester
	 * 2016/11/02
	 * @param expiryDate Date 失効日
	 */
	public Date expiryDate;
}
