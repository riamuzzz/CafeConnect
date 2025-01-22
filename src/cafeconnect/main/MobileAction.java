package cafeconnect.main;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Mobile;
import bean.User;
import dao.MobileDao;
import tool.Action;

public class MobileAction extends Action{
	@Override
	public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {

		//ユーザー情報取得
		HttpSession session = req.getSession();//セッション
		User user = (User)session.getAttribute("user");//ログインユーザー
		MobileDao mDao = new MobileDao();
		List<Mobile> mobilies = new ArrayList<>();
		//ユーザ情報をもとにモバイルテーブルから情報を取得する
		mobilies = mDao.filter(user.getUserId());
		req.setAttribute("mobilies", mobilies);
		req.getRequestDispatcher("mobile.jsp").forward(req, res);
	}
}
