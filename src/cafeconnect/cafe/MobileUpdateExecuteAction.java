package cafeconnect.cafe;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Order;
import dao.OrderDao;
import dao.ProductDao;
import tool.Action;

public class MobileUpdateExecuteAction extends Action{
	@Override
	public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {

		//ローカル変数の宣言 1
		OrderDao oDao = new OrderDao();
		ProductDao pDao = new ProductDao();
		List<String> orderIds = new ArrayList<>();
		List<Order> orders = new ArrayList<>();
		List<Boolean> receives = new ArrayList<>();
		int i =0;
		int j = 0;
		//リクエストパラメータの取得
		while (true) {
		    String orderId = req.getParameter("orderId" + i);
		    String receiveStr = req.getParameter("receive" + i);
		    if (orderId == null) {
		        break; // 注文IDがnullの場合、ループ終了
		    }

		    boolean receive = receiveStr != null && receiveStr.equals("true");
		    receives.add(receive);
		    orderIds.add(orderId);
		    i++;
		}

		// 注文番号ごとの配送状況を取得
		for (String oId : orderIds) {
		    if (oId != null) {
		        Order order = oDao.get(oId);

		        // Nullチェックを追加
		        if (order == null) {
		            System.err.println("注文が見つかりません: " + oId);
		            continue;
		        }

		        order.setReceive(receives.get(j));
		        oDao.save(order);
		        orders.add(order);

		        // `order.getProduct()` の Nullチェック
		        if (order.getProduct() == null) {
		            System.err.println("注文に関連付けられた商品がありません: " + oId);
		            continue;
		        }

		        // `order.getCount()` の Nullチェック
//		        if (order.getCount() == null) {
//		            System.err.println("注文に数量が設定されていません: " + oId);
//		            continue;
//		        }

		        // 商品購入処理
		        pDao.purchaseProduct(order.getProduct(), order.getCount());
		        j++;
		    }
		}
		//JSPへフォワード 7
		req.getRequestDispatcher("MobileOrderView.action").forward(req, res);
	}

}