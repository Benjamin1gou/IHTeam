/***************************
 * 学籍番号:40313
 * 作成者　:k.koki
 * 作成日　:2016/11/10
 * 内容　　:ログインに関する制御を定義する抽象クラス。
 * *************************/
package beans;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.DBManager.PreparedStatementByKoki;

public abstract class LoginSuper implements LoginInterface {
	private HttpServlet servlet;
	private HttpServletRequest request;
	/**
	 * ログインチャレンジ結果を格納するクラスです。
	 *
	 * @auther 浩生 2016/11/10
	 * @param result
	 *            String
	 */
	protected String result;
	/**
	 * ログインアクセスしたユーザー情報を格納する箱クラス。
	 * @author 浩生
	 *
	 */
	public class User{
		/**
		 * ログイン条件
		 * @auther 浩生
		 * 2016/11/10
		 * @param params String[]
		 */
		public String[] params;
		/**
		 * ログインチャレンジ結果
		 * @auther 浩生
		 * 2016/11/10
		 * @param result String
		 */
		public String result;
		/**
		 * ログイン時に実行したSQL
		 * @auther 浩生
		 * 2016/11/10
		 * @param sql String
		 */
		public String sql;
		public ArrayList<ArrayList<String>> resultList;
		{
			this.params=null;
			this.result=new String();
			this.sql=new String();
			this.resultList=new ArrayList<ArrayList<String>>();

		}
	}

	private String welcomePage = new String();

	{
		this.servlet=null;
		this.request=null;
		this.result=new String();

	}
	public LoginSuper(HttpServlet servlet, HttpServletRequest request) {
		// TODO 自動生成されたコンストラクター・スタブ
		this.servlet = servlet;
		this.request = request;
	}

	/**
	 * ログインをチャレンジします。
	 *
	 * @auther 浩生 2016/11/10
	 * @param param
	 *            リクエストで受け取るパラメータ名を指定してください。
	 *            このパラメータをString型で取得してSQLファイルのステートメントにString型で 設定します。
	 *            ステートメントはパラメータ名と同じにしてください。
	 *            ステートメントはDBManager.getPrepardStatementByKokiを使用します。
	 * @return {@link User}
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 * @see DBManager
	 * @see DBManager#getPreparedStatement(String)
	 * @see PreparedStatementByKoki
	 * @see PreparedStatementByKoki#setString(String, String)
	 */
	public User challenge(String... params) throws ClassNotFoundException,
			SQLException {
		//ログイン情報格納
		User user=new User();
		user.params=params;
		DBManager dbManager = new DBManager(Dao.DB.val);
		PreparedStatementByKoki statementByKoki = dbManager
				.getStatementByKoki(readSql(this.servlet, Dao.SQL.val));
		user.sql=statementByKoki.out();
		for (String param : params) {
			statementByKoki.setString(param,
					(String) this.request.getParameter(param));
		}
		if ((user.resultList=statementByKoki.select()).size() == 0) {
			this.result = NG;
			user.result=NG;
			return user;
		}
		this.result = OK;
		user.result=OK;
		return user;
	}

	/**
	 * SQLファイルを読み込みます。
	 *
	 * @auther 浩生 2016/11/10
	 * @param servlet
	 * @param path
	 * @return
	 */
	protected static String readSql(HttpServlet servlet, String path) {
		return readSql(servlet, path, "");
	}

	/**
	 * SQLファイルを読み込む際に行末にlineをつけて取得します。
	 *
	 * @auther 浩生 2016/11/10
	 * @param servlet
	 * @param path
	 * @param line
	 * @return
	 */
	protected static String readSql(HttpServlet servlet, String path,
			String line) {
		path = servlet.getServletContext().getRealPath("/WEB-INF/sql/" + path);
		StringBuilder builder = new StringBuilder();

		try {
			BufferedReader reader = new BufferedReader(new InputStreamReader(
					new FileInputStream(path), "UTF-8"));
			String string = reader.readLine();
			while (string != null) {
				builder.append(string);
				builder.append(line);
				string = reader.readLine();
			}
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
		return builder.toString();
	}

	/**
	 * ログインチャレンジ結果により遷移先を切り替える処理を変えてください。 例えば、ログインユーザーごとに遷移先が変わる場合の
	 * コントローラ処理をここに記述します。
	 *
	 * @auther 浩生 2016/11/10
	 * @return 遷移先定数定義したファイル。 ここに記述するコントローラ処理は下記の列挙型とメソッドを参照して記述してください。
	 * @see Forward
	 * @see Forward#indexOf(String)
	 *
	 */
	public abstract Forward getForward();

	/**
	 * ウェルカムページに遷移するかを判断する変数。
	 *
	 * @auther 浩生 2016/11/10
	 * @param welcomeFlg
	 *            int
	 */
	private int welcomeFlg = 1;

	/**
	 * ログインチャレンジがNGの時にウェルカムページに遷移する機能をONにします。 この機能はデフォルトでONになっています。
	 *
	 * @auther 浩生 2016/11/10
	 * @see LoginSuper#go(HttpServletRequest, HttpServletResponse)
	 */
	public void onWelcome() {
		this.welcomeFlg = 1;
	}

	/**
	 * ログインチャレンジがNGの時にウェルカムページへ遷移する機能をOFFにします。
	 *
	 * @auther 浩生 2016/11/10
	 * @see LoginSuper#go(HttpServletRequest, HttpServletResponse)
	 */
	public void offWelcome() {
		this.welcomeFlg = -1;
	}

	/**
	 * ログインチャレンジ結果後に遷移を行うメソッドです。
	 * ログインチャレンジ結果に左右する処置は下記のgetForward()メソッドに処理を記述してください。
	 *
	 * @throws IOException
	 * @throws ServletException
	 * @throws NotYetChallenge
	 * @auther 浩生 2016/11/10
	 * @see LoginSuper#getForward()遷移先振り分けメソッド
	 */
	public void go(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, NotYetChallenge {
		if (this.result.isEmpty()) {
			throw new NotYetChallenge();
		}
		Forward forward = null;
		if (this.result == NG) {
			// ウェルカムページに遷移する
			if (this.welcomeFlg > 0) {
				forward = Forward.Welcome;
			} else {
				if (this.welcomePage.isEmpty() == false) {
					RequestDispatcher rd = request.getRequestDispatcher("/"
							+ this.welcomePage);
					rd.forward(request, response);
					return;
				} else {
					System.out.println("WelComeページが設定されていません。");
					return;
				}
			}
		} else {
			forward=getForward();
		}
		String forwardUrl = "/" + forward.to;
		RequestDispatcher rd = request.getRequestDispatcher(forwardUrl);
		rd.forward(request, response);
	}

	/**
	 * ログインチャレンジが行っていない場合に起きる例外クラス。
	 *
	 * @author 浩生
	 *
	 */
	public class NotYetChallenge extends Exception {
		@Override
		public String getMessage() {
			// TODO 自動生成されたメソッド・スタブ
			return "ログインチャレンジが行われていません。";
		}
	}


	/**
	 * デフォルトウェルカムページ以外ウェルカムページを設定します。 ウェルカム遷移機能がOFFになっている場合はフォワード処理は強制的に終了します。
	 *
	 * @auther 浩生 2016/11/10
	 * @param page
	 * @see LoginSuper#offWelcome()
	 * @see LoginSuper#onWelcome()
	 */
	public void setWelCome(String page) {
		this.welcomePage = page;
	}

	/**
	 * ログインチャレンジ結果ごとに付与するユーザーアクセスIDを
	 * 振り分ける処理を記述してください。
	 * @auther 浩生
	 * 2016/11/10
	 * @return
	 */
	abstract public ArrayList<String> getUserAccessId();

	/**
	 * html中の(param:ok:ng)で構成された文字列をユーザーのアクセスリストによって
	 * 判断され出力するモノです。
	 * paramはAccess.paramで列挙されたものになります。
	 * またユーザーごとのアクセスIDは複数指定できます。
	 * それは下記の（ユーザーアクセス権限振り分け処理）を参照してください。
	 * 未実装
	 * @auther 浩生
	 * 2016/11/11
	 * @param html
	 * @return
	 */
	public String getUserHtml(String html){
		//ユーザーのアクセスIDリストを取得
		ArrayList<String> userAccessList=getUserAccessId();
		//ユーザーのアクセスparamを取得
		HashMap<String, ArrayList<Access>> userAccessMap=Access.indexOfMap((String[])userAccessList.toArray());
		for(String accessId:userAccessList){
			//アクセスIDに対応するアクセスパラムを取得する。
			ArrayList<Access> accesses=userAccessMap.get(accessId);
			for(Access access:accesses){
				//outAccessHtml処理
			}
		}
		return "";
	}

	/**
	 * アクセスリストに記載されておらずHTML置換が終了していないものを全て除去します。
	 * これはHMTL整形した後に呼び出してください。
	 * @auther 浩生
	 * 2016/11/11
	 * @param html
	 * @return
	 */
	private String cleanHtml(String html){
		return html.replaceAll("\\(.+\\:.+:.+\\:\\)", "");
	}

	/**
	 * (param:ok:ng)で構成されているものをngに置き換えます。
	 * @auther 浩生
	 * 2016/11/11
	 * @param html
	 * @param param
	 * @return
	 * @throws AccessHtmlFormat
	 */
	private String repAccHtmlNg(String html,String param) throws AccessHtmlFormat{
		String[] find=findParamOfHtml(html, param);
		String[] params=find[2].split(":");
		return find[0]+params[2]+find[1];
	}

	/**
	 * (param:ok:ng)で構成されているものをokに置換します。
	 * @auther 浩生
	 * 2016/11/11
	 * @param param
	 * @return
	 * @throws AccessHtmlFormat
	 */
	private String repAccHtmlOk(String html,String param) throws AccessHtmlFormat{
		String[] find=findParamOfHtml(html, param);
		String[] params=find[2].split(":");
		return find[0]+params[1]+find[1];
	}

	/**
	 * htmlの
	 * (param:ok:ng)で構成されているモノを発見し
	 * その前後の文字列を変えす。
	 * とその()内の文字列を返す。
	 * @auther 浩生
	 * 2016/11/10
	 * @param html
	 * @param param
	 * @return 前、後構成の文字列、()内文字列
	 * @throws AccessHtmlFormat
	 */
	public String[] findParamOfHtml(String html,String param) throws AccessHtmlFormat{
		int start=html.indexOf("("+param);
		int end=findEndOfHtml(html, param, start);
		if(end==-1)throw new AccessHtmlFormat();
		String[] result=new String[3];
		result[0]=html.substring(0, start);
		result[1]=html.substring(end, html.length());
		result[2]=html.substring(start,end);
		return result;
	}
	/**
	 * (param:ok:ng)で構成されているモノの末尾のindexを返す。
	 * @auther 浩生
	 * 2016/11/10
	 * @param html
	 * @param param
	 * @param start
	 * @return 未発見で-1
	 */
	private int findEndOfHtml(String html,String param,int start){
		String val=html.substring(start,html.length());
		for(int count=0;count<html.length();count++){
			if(String.valueOf(val.charAt(count)).equals(")")==true){
				// ) 発見
				return start+count;
			}
		}
		return -1;
	}
	public class AccessHtml extends Exception{
		@Override
		public String getMessage() {
			// TODO 自動生成されたメソッド・スタブ
			return "Html生成中の異常エラーです。";
		}
	}
	public class AccessHtmlFormat extends AccessHtml{
		@Override
		public String getMessage() {
			// TODO 自動生成されたメソッド・スタブ
			return "アクセス制御するhtmlソースの構成に誤りがあります。";
		}
	}
	public static void main(String[] args){	
		String html="<html><head><body><p>(param:ok:ng)</p></body></head></html>";

	}
	/**
	 * ログイン成功時のユーザーが持つことの
	 * @auther 浩生
	 * 2016/11/13
	 * @return
	 */
	abstract AccessList getAccessList();
}
