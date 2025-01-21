package cafeconnect;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import tool.Action;

public class UserCreateAction extends Action{
	@Override
	public void execute(
			HttpServletRequest req, HttpServletResponse res) throws Exception {

				// 新規会員登録ページに遷移
				req.getRequestDispatcher("createUser.jsp").forward(req, res);

		}
}
