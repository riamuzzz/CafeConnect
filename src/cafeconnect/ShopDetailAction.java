package cafeconnect;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import tool.Action;

public class ShopDetailAction extends Action{
	@Override
	public void execute(
			HttpServletRequest req, HttpServletResponse res
		) throws Exception {
		req.getRequestDispatcher("shopDetail.jsp").forward(req, res);;
		}
}
