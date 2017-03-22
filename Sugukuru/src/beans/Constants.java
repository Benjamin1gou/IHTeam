/***************************
 * 学籍番号:40313
 * 作成者　:k.koki
 * 作成日　:2016/11/05
 * 内容　　:Viewで表示する項目名をDBから取得するクラス。
 * *************************/
package beans;

import java.io.IOException;
import java.lang.reflect.Field;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.DBManager.PreparedStatementByKoki;

import common.Database;

import dtd.Request;
import dtd.RequestArray;


public class Constants implements Database{
	/**
	 * 遷移元、遷移先、DB№を管理します。
	 * @author 浩生
	 *
	 */
	public enum Page{
		DoClaimListDetail_jsp("do_claim_detail.jsp","","09"),
		PaymentDetail_jsp("claim_list_detail.jsp","PaymentListServlet","08"),
		PaymentDetail_ser("PaymentListServlet","accouting/claim_list_detail.jsp","08"),
		PaymentList_ser("PaymentListServlet","accouting/payment_list.jsp","07"),
		PaymentList_jsp("payment_list.jsp","PaymentListServlet","07"),
		ClaimList_ser("ClaimListServlet","accouting/claim_list.jsp","06"),
		ClaimList_jsp("claim_list.jsp","ClaimListServlet","06"),
		DoClaim_ser("DoClaimServlet","accouting/do_claim.jsp","05"),
		DoClaim_jsp("do_claim.jsp","DoClaimServlet","05"),
		EarningsList_ser("EarningsListServlet","accouting/earnings_list.jsp","04"),
		EarningsList_jsp("earnings_list.jsp","EarningsListServlet","04"),
		EstimatesNewCreating_jsp("estimates_new_creating.jsp","EstimatesNewCreatingServlet","03"),
		EstimatesNewCreating_ser("EstimatesNewCreatingServlet","estimates_new_creating.jsp","03"),
		Common("common","common","00"),
		OrderList_jsp("order_list.jsp","StockOrderListServlet","02"),
		OrderList_ser("StockOrderListServlet","stock/order_list.jsp","02"),
		OrderRecodeList_jsp("order_recode_list.jsp","OrderRecodeListServlet","01"),
		OrderRecodeList_ser("OrderRecodeListServlet","sales/order_recode_list.jsp","01");
		/**
		 * 呼び出し元ページを示します。
		 * @auther 浩生
		 * 2016/11/06
		 * @param from String
		 */
		public String from;
		/**
		 * 遷移先ページを示します。
		 * @auther 浩生
		 * 2016/11/06
		 * @param to String
		 */
		public String to;
		/**
		 * DBの主キー２桁を示します。
		 * @auther 浩生
		 * 2016/11/06
		 * @param index String
		 */
		public String index;
		private Page(String view,String servlet,String index){
			this.from=view;
			this.to=servlet;
			this.index=index;
		}
		/**
		 * ページからそれに対応する定数を返します。
		 * null指定でcommon変数を変えします。
		 * @auther 浩生
		 * 2016/11/07
		 * @param from
		 * @return
		 */
		public static Page indexOf(String from){
			for(Page page:values()){
				if(page.from.equals(from)){
					return page;
				}
			}
			return null;
		}
	}
	private HttpServlet servlet;
	private Page page;
	private HttpServletRequest request;
	/**
	 * このクラスを呼び出したファイルを示します。
	 * @auther 浩生
	 * 2016/11/06
	 * @param from String
	 */
	private String from;
	private ArrayList<Constant> constantList;
	private Page now;
	/**
	 * 個々のコンスタント情報を持つ箱クラス。
	 * @author 浩生
	 *
	 */
	public class Constant{
		public String constantId;
		public String value;
		public String pgName;
		{
			this.constantId="";
			this.value="";
			this.pgName="";
		}
	}
	{
		//初期化
		this.constantList=new ArrayList<Constants.Constant>();
	}
	/**
	 * requestからファイル名を読み込み、各ページで定義される定数を持つConstantsを生成します。
	 * @param servlet
	 * @param request
	 */
	public Constants(HttpServlet servlet,HttpServletRequest request){
		this.servlet=servlet;
		this.request=request;
		//requestからPage.fromを取得しindex、toを取得します。
		this.page=Page.indexOf(getPage(request.getRequestURI()));
		//DBから値を取得し、コンスタントリストに格納する。
		getConstants(InspectionValue.readSql(this.servlet, "SelectConstants.sql"));
	}

	/**
	 * xxxx/xxx/xxで示されたURIの末尾を返します。
	 * @param uri
	 * @return
	 */
	private String getPage(String uri){
		String uris[]=uri.split("/");
		return uris[uris.length-1];
	}
	/**
	 * DBからコンスタントリストを取得しフィールドに格納します。
	 */
	private void getConstants(String sql){
		try{
			DBManager dbManager=new DBManager(DBName);
			PreparedStatementByKoki statementByKoki=dbManager.getStatementByKoki(sql);
			statementByKoki.setString("CONSTANT_ID", this.page.index+"%");
			for(ArrayList<String> row:statementByKoki.select()){
				Constant constant=new Constant();
				constant.constantId=row.get(0);
				constant.value=row.get(1);
				constant.pgName=row.get(2);
				this.constantList.add(constant);
			}
		}catch(Exception e){
			e.printStackTrace();
			this.constantList=null;
		}
	}
	/**
	 * コンスタントIDの下２桁からコンスタント情報を取得します。
	 * @param key　下２桁
	 * @return　エラー時は空のコンスタント情報を返します。
	 */
	public Constant getConstant(String key){
		try{
			if(this.constantList==null)new ConstantException();
			for(Constant constant:this.constantList){
				if(constant.constantId.equals(this.page.index+key)){
					return constant;
				}
			}
			new NotFoundConstant();
		}catch(Exception e){
			e.printStackTrace();
		}
		return new Constant();
	}
	public class ConstantException extends Exception{
		@Override
		public String getMessage() {
			// TODO 自動生成されたメソッド・スタブ
			return "コンスタントリストが設定されていません。";
		}
	}
	public class NotFoundConstant extends ConstantException{
		@Override
		public String getMessage() {
			// TODO 自動生成されたメソッド・スタブ
			return "指定keyではコンスタントは見つかりませんでした。";
		}
	}
	/**
	 * サーブレット内のみで使用できるメソッド
	 * 設定された遷移先にフォワードする処理。
	 * @param request
	 * @param response
	 * @throws IOException
	 * @throws ServletException
	 */
	public void forward(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
		String forwardUrl=/*InetAddress.getLocalHost().getHostAddress()+":"+request.getServerPort()+request.getContextPath()+*/"/"+this.page.to;
		System.out.println(forwardUrl);
		RequestDispatcher rd=request.getRequestDispatcher(forwardUrl);
		rd.forward(request, response);
	}
	/**
	 * requestからdtdインスタンスへ値をセットするメソッド
	 * このメソッドはRequestクラスの該当フィールド(String)にdtdアノテーションを付与し
	 * pgNameとフィールド名が一致しないとセットされません。
	 * @param object
	 * @return
	 */
	public Object decodeRequest(Object object){
		//オブジェクトクラスに変数設定
		object=settingField(object, object.getClass().getDeclaredFields());
		return object;
	}
	/**
	 * objectにfieledsを設定する。
	 * @param object
	 * @param fields
	 * @return
	 */
	private Object settingField(Object object,Field[] fields){
		for(Field field:fields){
			//単一取得フィールドの時
			if(field.getAnnotation(Request.class)!=null){
				for(Constant constant:this.constantList){
					if(constant.pgName.equals(field.getName())){
						field.setAccessible(true);
						try {
							field.set(object, (String)this.request.getParameter(constant.pgName));
						} catch (IllegalArgumentException
								| IllegalAccessException e) {
							// TODO 自動生成された catch ブロック
							e.printStackTrace();
						}
					}
				}
			}
			//複数取得フィールドの時
			if(field.getAnnotation(RequestArray.class)!=null){
				for(Constant constant:this.constantList){
					if(constant.pgName.equals(field.getName())){
						field.setAccessible(true);
						try{
							field.set(object, (String[])this.request.getParameterValues(constant.pgName));
						}catch(IllegalAccessException exception){
							exception.printStackTrace();
						}
					}
				}
			}
		}
		return object;
	}

	/**
	 * superクラスがなくなるまで探索する。
	 * @param object
	 * @return
	 */
	public Object superDecodeRequest(Object object){
		object=settingField(object, object.getClass().getDeclaredFields());
		Class superClass=object.getClass().getSuperclass();
		while(superClass!=null && !superClass.equals(Object.class)){
			object=settingField(object, superClass.getDeclaredFields());
			superClass=superClass.getSuperclass();
		}
		//object=settingField(object, object.getClass().getSuperclass().getDeclaredFields());
		return object;
	}
	/**
	 * 送信先サーブレット先のURLを取得します。
	 * @auther 浩生
	 * 2016/11/07
	 * @return
	 * @throws UnknownHostException
	 */
	public String getServletUrl() throws UnknownHostException{
		return "http://"+InetAddress.getLocalHost().getHostAddress()+":"+request.getServerPort()+request.getContextPath()+"/"+this.page.to;
	}
	public static final String LOGIN_ID="login_id";
	public static final String PASSWORD="password";
	/**
	 * このクラスで定義されているHttpSerletを取得する。
	 * @auther 浩生
	 * 2016/11/09
	 * @return
	 */
	public HttpServlet getServlet(){
		return this.servlet;
	}
	/**
	 * このクラスで定義されているコンスタントリストのサイズを取得します。
	 * @auther 浩生
	 * 2016/11/09
	 * @return
	 */
	public int size(){
		return this.constantList.size();
	}
	private Constants(){
		this.constantList=new ArrayList<Constants.Constant>();
		this.page=Page.indexOf("common");
		getConstants("SELECT * FROM constants_master WHERE constant_id LIKE '00%'");
	}
	/**
	 * 各ページで共通するConstantsクラス。
	 * @auther 浩生
	 * 2016/11/09
	 * @param CONSTANTS Constants
	 */
	public static final Constants CONSTANTS=new Constants();
	/**
	 * 各ページで共通するコンスタントを取得します。
	 * @auther 浩生
	 * 2016/11/09
	 * @param constantID 下２桁
	 * @return
	 */
	public static final Constant getCommon(String constantID){
		return CONSTANTS.getConstant(constantID);
	}
	/**
	 * フォーム内のモードを列挙する。
	 * @author 浩生
	 *
	 */
	public enum Action{
		Detail,
		List,
		Search,
		Insert,
		Update;
		public static final Action indexOf(String name){
			for(Action action:Action.values()){
				if(name.equals(action.name())){
					return action;
				}
			}
			return null;
		}
	}
	/**
	 * このフォームのアクション
	 * <input type="hidden value=Action/>で決定できます。
	 * これはフォームごとに実行したい処理が異なる場合は
	 * Pageを登録しないといけませんが、同一ページで
	 * 複数の処理分岐がある場合、
	 * 例えば、検索と登録処理を同じページで行いたい場合に所謂、
	 * モードを分けたい時と一致します。
	 * サーブレット内でモードは下記のgetMode()でActionを取得できます。
	 * なお、このメソッドは一つのフォームの中で一度のみ使う事をが出来ます。
	 * @auther 浩生
	 * 2016/11/13
	 * @param action
	 * @return
	 * @see Constants#getMode()
	 */
	public static final String getAction(Action action){
		return "<input type=\"hidden\" value=\""+action.name()+"\" name=\"constantMode\"/>";
	}
	/**
	 * リクエストで送られてきたモードを取得します。
	 * @auther 浩生
	 * 2016/11/13
	 * @return
	 */
	public Action getMode(){
		String modeName=(String)this.request.getParameter("constantMode");
		return Action.indexOf(modeName);
	}
	/**
	 * コンスタントに設定されている
	 * HttpServletRequestを取得します。
	 * @auther 浩生
	 * 2016/11/14
	 * @return
	 */
	public HttpServletRequest getRequest(){
		return this.request;
	}
	/**
	 * jspからpageContextを使用してサーブレットに返す場合
	 * @auther 浩生
	 * 2016/11/14
	 * @return
	 */
	public String getPageContextServlet(){
		return "/"+this.page.to;
	}
	/**
	 * 指定のPage.toのURLを取得する。
	 * @auther 浩生
	 * 2016/11/22
	 * @param page
	 * @return
	 * @throws UnknownHostException
	 */
	public String getPageToUrl(Page page) throws UnknownHostException{
		return "http://"+InetAddress.getLocalHost().getHostAddress()+":"+request.getServerPort()+request.getContextPath()+"/"+page.to;
	}
	/**
	 * 末尾にパラメータを設定する。
	 * paramの構成は
	 * name,value,name,valueの形式にする。
	 * @auther 浩生
	 * 2016/11/22
	 * @param page
	 * @param params
	 * @return
	 * @throws UnknownHostException
	 */
	public String getPageToUrl(Page page,String...params) throws UnknownHostException{
		StringBuilder sb=new StringBuilder();
		sb.append(getPageToUrl(page));
		if(params.length<2){
			return sb.toString();
		}else{
			if(params.length%2>0)return sb.toString();
		}
		sb.append("?");
		for(int count=0;count<params.length;count=count+2){
			if(count>2){
				sb.append("&");
			}
			sb.append(params[count]+"="+params[count+1]);
		}
		return sb.toString();

	}


}
