package cafeconnect.cafe;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Properties;
import java.util.Set;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.internet.MimeUtility;
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
				mailAddress.add(order.getUser().getEmail());
				order.setReceive(receives.get(j));
				oDao.save(order);
				orders.add(order);
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
				String subject = "ご注文の商品が完成しました"; // 件名
				String from = "cafeconnect1115@gmail.com";  // 送信者メール
				String host = "smtp.gmail.com"; // host
				String fromName = "かふぇコネクト";// 送信者名
				System.out.println(email + ":aaaaaaa");
				String to = email;  // 受信メール
				String toName = "cafe"; // 受信者名
				String user = "cafeconnect1115@gmail.com"; // user
				String message = "ご注文の商品が完成しました。"; // 本文
				String password = "ypix rdlw drri tiym";  // アプリパスワード

				Properties properties;
		        Session session;
		        Transport transport = null;
		        MimeMessage mimeMessage;
		        MimeBodyPart messageBody;
		        MimeMultipart multipart;
		        InternetAddress[] address;
				try {
		            // GmailのSMTP設定
		            properties = System.getProperties();
		            properties.setProperty("mail.transport.protocol", "smtp");
		            properties.setProperty("mail.smtp.host", host);
		            properties.setProperty("mail.smtp.port", "587");
		            properties.setProperty("mail.smtp.auth", "true");
		            properties.setProperty("mail.smtp.starttls.enable", "true");

		            // セッションの作成
		            session = Session.getInstance(properties);

		            // メールメッセージの作成
		            mimeMessage = new MimeMessage(session);
		            mimeMessage.setSubject(MimeUtility.encodeText(subject, "iso-2022-jp", "B"), "iso-2022-jp");
		            mimeMessage.setSentDate(new Date());

		            address = new InternetAddress[1];
		            address[0] = new InternetAddress(from);
		            if (fromName != null) {
		                address[0].setPersonal(MimeUtility.encodeText(fromName, "iso-2022-jp", "B"));
		            }
		            mimeMessage.setFrom(address[0]);

		            address[0] = new InternetAddress(to);
		            if (toName != null) {
		                address[0].setPersonal(MimeUtility.encodeText(toName, "iso-2022-jp", "B"));
		            }
		            mimeMessage.setRecipients(Message.RecipientType.TO, address);

		            // Multipartメッセージの作成
		            multipart = new MimeMultipart();
		            mimeMessage.setContent(multipart);

		            // 本文の作成
		            messageBody = new MimeBodyPart();
		            messageBody.setText(message + "\n\n\n\nメールアドレス:" + from);
		            messageBody.setHeader("Content-Transfer-Encoding", "7bit");
		            multipart.addBodyPart(messageBody);  // 本文部分を追加

		            // メールサーバーへの接続と送信
		            transport = session.getTransport();
		            transport.connect(host, user, password);  // アプリパスワードを使用
		            transport.sendMessage(mimeMessage, mimeMessage.getAllRecipients());
		        } finally {
		            if (transport != null) {
		                try {
		                    transport.close();
		                } catch (MessagingException e) {
		                    // エラーハンドリング
		                }
		            }
		        }




		//JSPへのフォワード先をモバイルかオンラインショップかで分ける
		if (orders.get(0).getProduct().getCategory().getCategoryId().equals("CATE02")){
			req.getRequestDispatcher("OnlineOrderView.action").forward(req, res);
		} else {
			req.getRequestDispatcher("MobileOrderView.action").forward(req, res);
		}
			}
	}

}}