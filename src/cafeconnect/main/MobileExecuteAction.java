package cafeconnect.main;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Mobile;
import dao.MobileDao;
import dao.OrderDao;
import tool.Action;

public class MobileExecuteAction extends Action{
	@Override
	public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
		//jspファイルから受付番号取得
		String receptionId = req.getParameter("receptionId");
		MobileDao mDao = new MobileDao();
		OrderDao oDao = new OrderDao();
		List<Mobile> mobilies = new ArrayList<>();
		mobilies = mDao.idFilter(Integer.parseInt(receptionId));
		//注文テーブルにモバイルオーダー情報を追加
		for (Mobile mobile : mobilies) {
			oDao.mobileCreate(mobile.getProduct(), mobile.getUser(), mobile.getCount());
		}
		//モバイルテーブルからモバイルオーダー情報削除
		mDao.delete(Integer.parseInt(receptionId));
	}
}
