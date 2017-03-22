/***************************
 * 学籍番号:40313
 * 作成者　:k.koki
 * 作成日　:2016/11/07
 * 内容　　:全てのソースで使用する共通変数。
 * *************************/
package common;

import beans.Bank.Banks;
import beans.Bank.Store;


public interface Common {
	/**
	 * 消費税
	 * @auther 浩生
	 * 2016/11/07
	 * @param TAX double
	 */
	double TAX=1.08;
	/**
	 * カード払い
	 * @auther 浩生
	 * 2016/11/08
	 * @param CARD int
	 */
	int CARD=0;
	/**
	 * 現金払い
	 * @auther 浩生
	 * 2016/11/08
	 * @param CASH int
	 */
	int CASH=1;
	/**
	 * 手形
	 * @auther 浩生
	 * 2016/11/08
	 * @param BILL int
	 */
	int BILL=2;
	/**
	 * 進捗ステップID
	 * @auther 浩生
	 * 2016/11/08
	 * @param STEP0 int
	 */
	int STEP0=0;
	/**
	 * 進捗ステップID
	 * @auther 浩生
	 * 2016/11/08
	 * @param STEP1 int
	 */
	int STEP1=1;
	/**
	 * 進捗ステップID
	 * @auther 浩生
	 * 2016/11/08
	 * @param STEP2 int
	 */
	int STEP2=2;
	/**
	 * 進捗ステップID
	 * @auther 浩生
	 * 2016/11/08
	 * @param STEP3 int
	 */
	int STEP3=3;
	/**
	 * システムエラーコード
	 * どうしようもないエラー
	 * @auther 浩生
	 * 2016/11/09
	 * @param EXECPTION int
	 */
	int EXECPTION=1;
	/**
	 * システムエラーコード
	 * SQLエラー
	 * @auther 浩生
	 * 2016/11/09
	 * @param SQLEXCEPTION int
	 */
	int SQLEXCEPTION=2;
	/**
	 * システムエラーコード
	 * DBが読み込めない
	 * @auther 浩生
	 * 2016/11/09
	 * @param CLASSNOTFOUNTEXCEPTION int
	 */
	int CLASSNOTFOUNTEXCEPTION=3;

	/**
	 * 支払種別
	 * @author 浩生
	 *
	 */
	public enum RecallManner{
		CARD("0","カード"),
		CASH("1","現金"),
		BILL("2","手形");
		public String code;
		public String value;
		private RecallManner(String code,String value) {
			// TODO 自動生成されたコンストラクター・スタブ
			this.code=code;
			this.value=value;
		}
		public static RecallManner indexOf(String code){
			for(RecallManner manner:RecallManner.values()){
				if(manner.code.equals(code)){
					return manner;
				}
			}
			return null;
		}
	}
	/**
	 * スグクルの振込口座番号
	 * @auther 浩生
	 * 2016/11/20
	 * @param SUGUKURU_KOZA String
	 */
	public static String SUGUKURU_KOZA="1234567";
	/**
	 * スグクルの銀行
	 * @auther 浩生
	 * 2016/11/20
	 * @param SUGUKUTU_BANK Banks
	 */
	public static Banks SUGUKUTU_BANK=Banks.MIZUHO;
	/**
	 * スグクルの銀行支店
	 * @auther 浩生
	 * 2016/11/20
	 * @param SUGUKUTU_STORE Store
	 */
	public static Store SUGUKUTU_STORE=Store.MIZU_TOKYO;


}
