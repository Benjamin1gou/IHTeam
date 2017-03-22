/***************************
 * 学籍番号:40313
 * 作成者　:k.koki
 * 作成日　:2016/11/16
 * 内容　　:エラーチェックの際に総合的なメッセージやタイプを識別するために
 * 			定義するインターフェース。
 * *************************/
package beans;

public interface ErrerMessage {

	/**
	 * エラータイプを列挙します。
	 * 入力項目などに違いが出ても共通するメッセージの一部を定義します。
	 * @author 浩生
	 *
	 */
	public enum ErrerType{
		Kana(4,"全角カナ"),
		Str(3,"文字列"),
		Int(3,"半角数字"),
		Emp(2,"空白"),
		Null(1,"null");
		public int code;
		public String comment;
		private ErrerType(int code,String comment) {
			// TODO 自動生成されたコンストラクター・スタブ
			this.code=code;
			this.comment=comment;
		}
	}
	/**
	 * 様々なカレンダーの入力の際に共通するヘッダー部分だけを列挙します。
	 * @author 浩生
	 *
	 */
	public enum Calendar{
		Day(3,"日"),
		Month(2,"月"),
		Year(1,"年");
		public int code;
		public String header;
		private Calendar(int code,String header) {
			// TODO 自動生成されたコンストラクター・スタブ
			this.code=code;
			this.header=header;
		}
	}
}
