/***************************
 * 学籍番号:40024
 * 作成者　:t.ueshima
 * 作成日　:2016/11/02
 * 内容　　:棚卸情報を管理する
 * *************************/
package dtd;

public class Inventories {
	/**
	 * @auther Tester
	 * 2016/11/05
	 * @param inventoriesId in 棚卸ID
	 */
	public int inventoriesId;
	
	/**
	 * @auther Tester
	 * 2016/11/05
	 * @param puoductId String 商品ID
	 */
	public String puoductId;
	
	/**
	 * @auther Tester
	 * 2016/11/05
	 * @param puroductName String 商品名
	 */
	public String puroductName;
	
	/**
	 * @auther Tester
	 * 2016/11/05
	 * @param inventoriesAmount int 棚卸数
	 */
	public int inventoriesAmount;
	
	/**
	 * @auther Tester
	 * 2016/11/05
	 * @param finishedAmount int 完了数
	 */
	public int finishedAmount;
	
	/**
	 * @auther Tester
	 * 2016/11/05
	 * @param note String　備考
	 */
	public String note;
}
