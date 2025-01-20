package cafeconnect.cafe;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import tool.Action;

public class CafePassResetAction extends Action{
	@Override
	public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {

			req.getRequestDispatcher("cafePassReset.jsp").forward(req, res);
		}
}
