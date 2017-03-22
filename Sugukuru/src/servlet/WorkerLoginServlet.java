package servlet;


import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import beans.Constants;
import beans.LoginEmployment;
import dao.LoginDao.LoginException;

/**
 * Servlet implementation class WorkerLoginServlet
 */
@WebServlet("/WorkerLoginServlet")
public class WorkerLoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public WorkerLoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

	/**
	 * @throws IOException
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");

		//ログインのON or OF処理
		HttpSession session=request.getSession();
		if(session.getAttribute("LoginDao.session")==null){
			//ログイン処理
			try {
				LoginEmployment loginEmployment=new LoginEmployment(this,(String)request.getParameter(Constants.LOGIN_ID),(String)request.getParameter(Constants.PASSWORD));
			} catch (LoginException e) {
				// TODO 自動生成された catch ブロック
			}
		}else{
			//ログオフ処理
			session.removeAttribute("LoginDao.session");
		}
		System.out.println("ser");
		RequestDispatcher rd=request.getRequestDispatcher("sales/index.jsp");
		rd.forward(request, response);

	}

}
