package scoremanager;

import java.util.Date;
import java.util.Properties;

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

import tool.Action;

public class ContactExecuteAction extends Action {

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {


        String subject = "お問い合わせ"; // 件名
        String to = "cafeconnect1115@gmail.com";  // 受信メール
        String toName = req.getParameter("name"); // 受信者名
		String from = req.getParameter("mail");  // 送信者メール
		String fromName = req.getParameter("name");// 送信者名
		String tel = req.getParameter("tel"); // 送信者の電話番号
		String message = req.getParameter("message"); // 本文
        String host = "smtp.gmail.com"; // host
        String user = "cafeconnect1115@gmail.com"; // user
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
            messageBody.setText(message + "\n\n\n\nメールアドレス:" + from + "\n電話番号:" + tel, "iso-2022-jp");
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

		req.getRequestDispatcher("otoi kanryo.jsp").forward(req, res);

	}

}
