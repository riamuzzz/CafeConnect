package cafeconnect.cafe;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Order;
import dao.OrderDao;
import dao.ProductDao;
import tool.Action;

public class OrderUpdateExecuteAction extends Action{
	@Override
	public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
		//ローカル変数の宣言 1
		OrderDao oDao = new OrderDao();
		List<String> oList = new ArrayList<>();
		List<String> mailAddress = new ArrayList<>();
		List<Order> orders = new ArrayList<>();
		List<Boolean> receives = new ArrayList<>();
		ProductDao pDao = new ProductDao();
		boolean mobile = false;
		int i =0;
		int j = 0;
		//リクエストパラメータの取得
		while (true) {
			//ぐるぐる回してjspから注文番号と発送状況を取得
		    String orderId = req.getParameter("orderId" + i);
		    String receiveStr = req.getParameter("receive" + i);
		    //receiveStrはnullのときfalseに変換されるからnullの時もループを中断しない
		    if (orderId == null) {
		        break; // 注文IDがnullの場合、ループ終了
		    }
		    //取得した発送状況がnullならfalse何か入っていたらtrueにしboolean型のリストに格納
		    if (receiveStr == null){
		    	boolean receive = false;
		    	receives.add(receive);
		    } else if (receiveStr.equals("true")){
		    	boolean receive = true;
		    	receives.add(receive);
		    }
		    Order order = oDao.get(orderId);
		    if (order.isMobile() == true){
		    	mobile = true;
		    }
		    //取得した注文番号をリストに格納
		    oList.add(orderId);
		    i++;
		}
		//注文番号ごとに配送状況を更新する
		for (String oId : oList) {
			if(oId != null){
				Order order =oDao.get(oId);
				mailAddress.add(order.getUser().getAddress());
				order.setReceive(receives.get(j));
				oDao.save(order);
				orders.add(order);
				//売れた商品の数だけ在庫数を減らす
				pDao.purchaseProduct(order.getProduct(), order.getCount());
				j++;
			}
		}
		Set<String> set = new HashSet<>();
        for (String mail : mailAddress) {
            set.add(mail);
        }
        String[] newMailAddress = set.toArray(new String[0]);
		//モバイルオーダーなら商品が完成したことを伝えるメールを購入者あてに送る
		if (mobile == true) {
			for (String email : newMailAddress){
				//商品完成メール
			}


		//JSPへのフォワード先をモバイルかオンラインショップかで分ける
		if (orders.get(0).getProduct().getCategory().getCategoryId().equals("CATE02")){
			req.getRequestDispatcher("OnlineOrderView.action").forward(req, res);
		} else {
			req.getRequestDispatcher("MobileOrderView.action").forward(req, res);
		}

	}

}}