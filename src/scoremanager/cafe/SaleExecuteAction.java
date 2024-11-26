package scoremanager.cafe;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.sql.Date;
import java.util.Base64;
import java.util.List;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.StandardChartTheme;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;

import bean.Sale;
import bean.User;
import dao.SaleDao;
import dao.UserDao;
import tool.Action;

public class SaleExecuteAction extends Action{

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {

		//必要なDaoをインスタンス化
		SaleDao saleDao = new SaleDao();
		UserDao userDao = new UserDao();

		//サブスクに会員登録をしているuser情報を取得
		List<User> subUser = saleDao.filter();
		//user全員の情報を取得
		List<User> allUser = userDao.filter(null, null, null);

		// 会員の割合(double) = 会員人数 ÷ ユーザ全員
		double percent = (double)subUser.size() / (double)allUser.size();

		// 会員の割合を%に変更
		int sub = (int) (percent*100);
		// 会員ではないユーザの割合を%にする
		int notSub = (int) (100 - percent * 100);

		// データセットの作成
		ChartFactory.setChartTheme(StandardChartTheme.createLegacyTheme()); //日本語対応させる
        DefaultPieDataset dataset = new DefaultPieDataset();
        dataset.setValue("登録済み\n" + sub + "%", sub);   // 会員のデータをセット
        dataset.setValue("未登録\n" + notSub + "%", notSub); // 会員ではないデータをセット

        // JFreeChart の作成
        JFreeChart chart = ChartFactory.createPieChart(
                "サブスク会員割合",    // グラフタイトル
                dataset,               // データセット
                true,                  // 凡例の表示
                true,                  // ヘルプの表示
                false                  // URLリンクの表示
        );


        Date day = Date.valueOf("2024-11-08");
        List<Sale> date = saleDao.dateFilter(day);

        DefaultCategoryDataset data = new DefaultCategoryDataset();
        for (Sale sale : date){
        	data.addValue(sale.getPrice(), sale.getYear(), sale.getMonth());
        }

        JFreeChart lineChart = ChartFactory.createLineChart(
        		"商品売上",
        		"日",
        		"金額",
        		data,
        		PlotOrientation.VERTICAL,
        		true,
                false,
                false
        );

        // JFreeChart を BufferedImage に変換
        BufferedImage chartImage = chart.createBufferedImage(500, 400);  // 画像サイズを指定
        BufferedImage lineChartImage = lineChart.createBufferedImage(500, 400);  // 画像サイズを指定

        // 画像をBase64エンコードするためのByteArrayOutputStream(jspのhtmlに埋め込めるようにする)
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ImageIO.write(chartImage, "PNG", baos);
        byte[] imageBytes = baos.toByteArray();
        String encodedImage = Base64.getEncoder().encodeToString(imageBytes);

        ByteArrayOutputStream lineBaos = new ByteArrayOutputStream();
        ImageIO.write(lineChartImage, "PNG", lineBaos);
        byte[] lineImageBytes = lineBaos.toByteArray();
        String lineEncodedImage = Base64.getEncoder().encodeToString(lineImageBytes);

      //グラフの画像をセット
        req.setAttribute("graph", encodedImage);
        req.setAttribute("lineGraph", lineEncodedImage);



        req.getRequestDispatcher("sale.jsp").forward(req, res);

	}
}
