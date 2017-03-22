/***************************
 * 学籍番号:40024
 * 作成者　:t.ueshima
 * 作成日　:2016/11/14
 * 内容　　:小分け詳細を管理する
 * *************************/
package dtd;

public class SubdivisionDetail {
	/**
	 * @auther Tester
	 * 2016/11/14
	 * @param num String　行番号
	 */
	public String num;
	/**
	 * @auther Tester
	 * 2016/11/14
	 * @param productNumber String　商品ID
	 */
	public String productNumber;
	/**
	 * @auther Tester
	 * 2016/11/14
	 * @param productName String　商品名
	 */
	public String productName;
	/**
	 * @auther Tester
	 * 2016/11/14
	 * @param Amount String　商品数量
	 */
	public String amount;
	/**
	 * @auther Tester
	 * 2016/11/14
	 * @param step String　作業ステップ
	 */
	public String step;
	
	public void setStep(String value){
		switch(value){
			case "0":
				step = "未作業";
				break;
			case "1":
				step = "ピッキング済";
				break;
			case "2":
				step = "検品済";
				break;
			case "3":
				step = "小分済";
				break;
		}
	}
}
