/***************************
 * 学籍番号:40313
 * 作成者　:k.koki
 * 作成日　:2016/11/10
 * 内容　　:ログインに関する定数定義などを記述する。
 * *************************/
package beans;

import java.util.ArrayList;
import java.util.HashMap;

public interface LoginInterface {
	/**
	 * ログインチャレンジを行うSQLファイル、DB名を定義する。
	 * @author 浩生
	 *
	 */
	public enum Dao{
		SQL("SelectLogin.sql"),
		/*これより上に定義する。*/
		DB("jv34");
		public String val;
		Dao(String val){
			this.val=val;
		}
	}
	/**
	 * ログイン状態によって初期フォワード先を定義する。
	 * @author 浩生
	 *
	 */
	public enum Forward{
		/*これより上に定数を定義してください。*/
		Welcome("0","welcome.jsp");
		public String flg;
		public String to;
		Forward(String flg,String to){
			this.flg=flg;
			this.to=to;
		}
		/**
		 * 指定フラグを持つ遷移先ページ名を取得します。
		 * 見つからない場合はnullを返します。
		 * @auther 浩生
		 * 2016/11/10
		 * @param flg
		 * @return
		 */
		public static Forward indexOf(String flg){
			for(Forward forward:Forward.values()){
				if(forward.flg.equals(flg)){
					return forward;
				}
			}
			return null;
		}
	}

	/**
	 * ログインチャレンジのレスポンス定義
	 * ログイン成功
	 * @auther 浩生
	 * 2016/11/10
	 * @param OK String
	 */
	public static final String OK="ok";
	/**
	 * ログインチャレンジのレスポンス定義
	 * チャレンジ失敗
	 * @auther 浩生
	 * 2016/11/10
	 * @param NG String
	 */
	public static final String NG="ng";

	/**
	 * ユーザーがアクセスできるコンテンツを定義します。
	 * @author 浩生
	 *
	 */
	public enum Access{
		/* これより上に定義します。 */
		Master("0","all");


		public String accessId;
		public String param;
		private Access(String accessId,String param) {
			// TODO 自動生成されたコンストラクター・スタブ
			this.accessId=accessId;
			this.param=param;
		}
		/**
		 * 指定アクセスIDで初めに見つかったモノを返します。
		 * @auther 浩生
		 * 2016/11/10
		 * @param accessId
		 * @return
		 */
		public static Access indexOf(String accessId){
			for(Access access:Access.values()){
				if(access.accessId.equals(accessId)){
					return access;
				}
			}
			return null;
		}
		/**
		 * 指定アクセスIDで見つかったモノを全て返します。
		 * @auther 浩生
		 * 2016/11/10
		 * @param accessId
		 * @return
		 */
		public static ArrayList<Access> indexOfs(String accessId){
			ArrayList<Access> result=new ArrayList<LoginInterface.Access>();
			for(Access access:Access.values()){
				if(result.add(access));
			}
			return result;
		}
		/**
		 * 指定アクセスIDの配列で検索したモノ全てを
		 * ハッシュマップで取得します。
		 * @auther 浩生
		 * 2016/11/10
		 * @param accessIds
		 * @return
		 */
		public static HashMap<String, ArrayList<Access>> indexOfMap(String...accessIds){
			HashMap<String, ArrayList<Access>> result=new HashMap<String, ArrayList<Access>>();
			for(String accessId:accessIds){
				ArrayList<Access> mapVal=new ArrayList<LoginInterface.Access>();
				if((mapVal=indexOfs(accessId)).size()>0){
					result.put(accessId, mapVal);
				}
			}
			return result;
		}
	}
	/**
	 * ユーザーがアクセスできるサーブレットを列挙します。
	 *-------------列挙仕様------------------
	 *user：ユーザーの種類を意味します。
	 *例（営業部）など
	 *access:ユーザーがアクセスできる先を取得します。
	 *一般的にはサーブレット名を記述します。
	 * @author 浩生
	 *
	 */
	public enum AccessList{
		/*これより上に定義*/
		Master("master","All");
		public String user;
		public String access;
		private AccessList(String user,String access) {
			// TODO 自動生成されたコンストラクター・スタブ
			this.user=user;
			this.access=access;
		}
		/**
		 * userからアクセスできる権限を一つ取得します。
		 * @auther 浩生
		 * 2016/11/13
		 * @param user
		 * @return
		 */
		public static AccessList indexof(String user){
			for(AccessList accessList:AccessList.values()){
				if(accessList.user.equals(user)){
					return accessList;
				}
			}
			return null;
		}
		/**
		 * ユーザ－のアクセス権限を全て取得します。
		 * @auther 浩生
		 * 2016/11/13
		 * @param user
		 * @return
		 */
		public static ArrayList<AccessList> indexOfs(String user){
			ArrayList<AccessList> result=new ArrayList<LoginInterface.AccessList>();
			for(AccessList accessList:AccessList.values()){
				if(accessList.user.equals(user)){
					result.add(accessList);
				}
			}
			return result;
		}
	}

}
