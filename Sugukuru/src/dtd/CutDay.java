/***************************
 * 学籍番号:40313
 * 作成者　:k.koki
 * 作成日　:2016/11/14
 * 内容　　:締日を選択するセレクトボックス用の箱クラス。
 * *************************/
package dtd;

public class CutDay {
	/**
	 * セレクトボックスのvalueに格納する値
	 * @auther 浩生
	 * 2016/11/14
	 * @param value String
	 */
	public String value;
	/**
	 * セレクトボックスのオプションに表示する値を取得する。
	 * @auther 浩生
	 * 2016/11/14
	 * @return
	 */
	public String getCutDay(){
		if(this.value.equals("99")){
			return "月末";
		}
		return this.value+"日";
	}
}
