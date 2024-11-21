package scoremanager.cafe;

import java.awt.image.BufferedImage;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;

import tool.Action;
public class SaleExecuteAction extends Action{

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {

		// データセットの作成
        DefaultPieDataset dataset = new DefaultPieDataset();
        dataset.setValue("登録済み", 90);
        dataset.setValue("未登録", 10);

        // JFreeChart の作成
        JFreeChart chart = ChartFactory.createPieChart(
                "サブスク会員割合",    // グラフタイトル
                dataset,               // データセット
                true,                  // 凡例の表示
                true,                  // ヘルプの表示
                false                  // URLリンクの表示
        );

        // JFreeChart を BufferedImage に変換
        BufferedImage chartImage = chart.createBufferedImage(500, 400);  // 画像サイズを指定

//        // クライアントに画像として返す
//        res.setContentType("image/png");  // PNG 画像として返すことを指定
//        ImageIO.write(chartImage, "PNG", res.getOutputStream());  // 画像をレスポンスとして出力

        req.setAttribute("graph", chartImage);

        req.getRequestDispatcher("sale.jsp").forward(req, res);

	}
}
