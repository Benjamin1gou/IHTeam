/***************************
 * 学籍番号:40313
 * 作成者　:k.koki
 * 作成日　:2016/11/02
 * 内容　　:従業員のログインを管理するクラス。
 * *************************/
package beans;

import javax.servlet.http.HttpServlet;

import dao.LoginDao;

public class LoginEmployment extends LoginDao{

	private static final String SqlPath="login_employment.sql";


	public LoginEmployment(HttpServlet servlet,String loginId,String password) throws LoginException{
		super(servlet,loginId, password, SqlPath);
	}


	/* (非 Javadoc)
	 * @see dao.LoginDao#check(java.lang.String, java.lang.String)
	 */
	@Override
	public boolean check(String loginId, String password) {
		// TODO 自動生成されたメソッド・スタブ
		//ログインIDのチェック
		boolean login=(loginId.length()>0 && loginId.length()<=20);
		boolean pass=(password.length()>0 && password.length()<=20);
		if(login && pass)return true;
		return false;
	}

	@Override
	public String userName() {
		// TODO 自動生成されたメソッド・スタブ
		return "TestUser";
	}



}
