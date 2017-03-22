/***************************
 * 学籍番号:40313
 * 作成者　:k.koki
 * 作成日　:2016/11/02
 * 内容　　:ユーザーログインに関するスーパークラス。
 * *************************/
package dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import beans.DBManager;
import beans.InspectionValue;

import common.Database;

public abstract class LoginDao implements Database{
	private static final String DEBUG_TAG="LoginDao.class";
	private String loginId;
	private String password;
	private String sqlPath;
	private boolean loginState;
	private ArrayList<ArrayList<String>> dbList;
	private HttpServlet servlet;
	{
		this.loginState=false;
	}
	/**
	 * ログイン情報に関する基本的な情報を持つLoginDaoを生成します。
	 * @param loginId
	 * @param password
	 * @param sqlPath
	 * @throws LoginException
	 */
	public LoginDao(HttpServlet servlet,String loginId,String password,String sqlPath) throws LoginException {
		// TODO 自動生成されたコンストラクター・スタブ
		if(this.check(loginId,password)==false){
			throw new LoginException();
		}
		this.loginId=loginId;
		this.password=password;
		this.sqlPath=sqlPath;
		this.servlet=servlet;
	}

	/**
	 * HttpSessionからログイン情報を持つLoginDaoを生成します。
	 * @param request
	 * @param sqlPath
	 * @throws LoginException
	 */
	public LoginDao(HttpServlet servlet,HttpServletRequest request,String sqlPath) throws LoginException{
		/*if(this.check(loginId,password)==false){
			throw new LoginException();
		}*/
		HttpSession session=request.getSession();
		this.loginId=(String) session.getAttribute("LoginDao.loginId");
		this.password=(String)session.getAttribute("LoginDao.password");
		this.sqlPath=sqlPath;
		this.servlet=servlet;
	}
	/**
	 * ログインID、パスワードに関する桁数チェックなどを行ってください。
	 * @return チェックエラー:false、チェックOK:trueとしてください。
	 */
	public abstract boolean check(String loginId,String password);


	/**
	 * ログインID、パスワードが不正である例外。
	 * @author 浩生
	 *
	 */
	public class LoginException extends Exception{
		@Override
		public String getMessage() {
			// TODO 自動生成されたメソッド・スタブ
			return DEBUG_TAG+":ログイン情報不正";
		}
	}



	/**
	 * ログインをチャレンジします。
	 * 成功：true、失敗：false
	 * ステイトメントは1番目がloginId、2番目がpasswordとなるようにしてください。
	 * @return SqlPathで取得したリストデータ。
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public ArrayList<ArrayList<String>> login() throws ClassNotFoundException, SQLException{
		//DB接続
		DBManager dbManager=new DBManager(DBName);
		//SQLパラメータの設定
		PreparedStatement sql=dbManager.getPreparedStatement(InspectionValue.readSql(this.servlet,this.sqlPath));
		sql.setString(1, this.loginId);
		sql.setString(2, this.password);
		//ログイン状態の確認
		ArrayList<ArrayList<String>> list=dbManager.runSelect(sql);
		if(list.size()==0){
			this.loginState=false;
		}
		this.loginState=true;
		this.dbList=list;
		return list;
	}
	/**
	 * ログインのチャレンジ状態を取得します。
	 * login()メソッド使用後に使用。
	 * @return
	 * @throws LoginException
	 */
	public boolean loginState() throws LoginException{
		return this.loginState;
	}
	/**
	 * ユーザー情報をセッションに保存します。
	 * @param request
	 */
	public void setSession(HttpServletRequest request){
		HttpSession session=request.getSession();
		session.setAttribute("LoginDao.session", this);
	}
	/**
	 * 現在のセッションログイン状態を破棄します。ｓ
	 * @param request
	 */
	public void logout(HttpServletRequest request){
		HttpSession session=request.getSession();
		session.removeAttribute("LoginDao.session");
	}

	/**
	 * ログイン状態のユーザー名を取得します。
	 * @return
	 */
	public abstract String userName();

	public String loginAction() throws LoginException{
		if(this.loginState())return "ログアウト";
		return "ログイン";
	}



}
