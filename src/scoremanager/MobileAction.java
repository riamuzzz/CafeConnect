package scoremanager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import tool.Action;

public class MobileAction extends Action{
	@Override
	public void execute(
			HttpServletRequest req, HttpServletResponse res
		) throws Exception {
		req.getRequestDispatcher("mobile.jsp").forward(req, res);;
		}
}
