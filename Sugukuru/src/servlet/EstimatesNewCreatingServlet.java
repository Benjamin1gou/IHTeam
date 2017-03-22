/***************************
 * 学籍番号:40281
 * 作成者　:m.haruka
 * 作成日　:2016/11/08
 * 内容　　:新規見積書作成クラス。
 * *************************/
package servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.Constants;
import beans.DBManager;
import beans.DBManager.PreparedStatementByKoki;
import beans.InspectionValue;
import dtd.Estimate;
import dtd.EstimateDetail;

/**
 * Servlet implementation class EstimatesNewCreatingServlet
 */
@WebServlet("/EstimatesNewCreatingServlet")
public class EstimatesNewCreatingServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public EstimatesNewCreatingServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");

		//入力値保存
		Constants constants = new Constants(this, request);
		Estimate est = new Estimate();
		EstimateDetail estDtl = new EstimateDetail();

		DBManager dbManager=null;
		//SQL読み込み
		PreparedStatementByKoki statementByKoki = dbManager.getStatementByKoki(InspectionValue.readSql(this, "OrderRecodeList.sql"));


		try{
			/*
			 * 入力チェック※あとで
			 */
			// 顧客IDの数値チェック
			boolean errFlg = false;
			//正常時、パラメータをセットする。
			statementByKoki.setString("CUSTOMER_ID", est.customerId);


			/**
			 * 在庫オーバーチェック
			 */


			/**
			 * 入荷日チェック
			 * 出荷日から丸二日以内に入荷するものは見積を通す
			 */


			/*
			 * 受注情報と受注明細のInsert
			 */
			if(!errFlg){
				int s = statementByKoki.update();
			}

		}catch(SQLException e){
			//税抜き合計金額、税、税込み合計金額を算出
			//sqlエラー
			e.printStackTrace();
		}finally{
			try{
			dbManager.closeDB();
			}catch(SQLException e){
				//closeエラー
				e.printStackTrace();
			}
		}

		//エラーメッセージリクエスト設定
		//request.setAttribute("orderRecodeList", );

		//フォワード処理
		constants.forward(request, response);

	}

}
