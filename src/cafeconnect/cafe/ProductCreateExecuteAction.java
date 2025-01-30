package cafeconnect.cafe;

import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Category;
import bean.Product;
import dao.CategoryDao;
import dao.ProductDao;
import tool.Action;

public class ProductCreateExecuteAction extends Action{

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {

		// productDaoをインスタンス化
		ProductDao productDao = new ProductDao();
		// categoryDaoをインスタンス化
		CategoryDao categoryDao = new CategoryDao();

		int productId;

//		System.out.println(productDao.getId().getProductId());

		// 商品登録が一つ目の場合product_idを1にする
		if (productDao.getId() == null){
			productId = 1;
		// 商品登録が一つ目以外の時product_idを+1する
		} else {
			productId = productDao.getId().getProductId()+1;  // 商品ID
		}

		System.out.println("productId:" + productId);

		String categoryId = null;                   			// カテゴリID
		Category category = null;                   			// カテゴリ
		String productName = null;                  			// 商品名
		int price = 0;                              			 // 価格
		int count = 0;                             				  // 在庫数
		String productDetail = null;                			// 商品詳細
		String img = null;                           			// 写真
		String sellStr = null;                       			// 販売状況str型
		Boolean sell = null;                         			// 販売状況
		LocalDate nowDate = LocalDate.now();         // LcalDateインスタンスを取得
		String nowDateStr = String.valueOf(nowDate); // string型に変換
//		LocalDateTime nowDateTime = LocalDateTime.now();         // LcalDateTimeインスタンスを取得
//		String nowDateTimeStr = String.valueOf(nowDateTime.getSecond()); // string型に変換
		Date inStockDay = Date.valueOf(nowDateStr);  // string型からDate型に変換
		String fileName = null;// 画像のファイル名

		// 現在の日時を取得
        LocalDateTime now = LocalDateTime.now();

        // カスタムフォーマットを定義
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-M-d-H-m-s");

        // フォーマットを適用して表示
        String formattedNow = now.format(formatter);
        System.out.println(formattedNow);  // 例: 2025-1-16-10-15-40

		// リクエストパラメータの取得
		categoryId = req.getParameter("category");           // カテゴリId
		productName = req.getParameter("productName");       // 商品名
		price = Integer.parseInt(req.getParameter("price")); // 価格
		count = Integer.parseInt(req.getParameter("count")); // 在庫数
		productDetail = req.getParameter("productDetail");   // 商品詳細
		img = req.getParameter("image");                     // 写真
		sellStr = req.getParameter("sell");                  // 販売状況

		// sellStrが"on"ではない時販売状況をfalseにする
		if (sellStr == null){
			sell = false;
		} else {
			sell = true;
		}
		System.out.println(sellStr);
		System.out.println(sell);



		// ファイルを保存したい！！
//		System.out.println("カレントディレクトリ: " + System.getProperty("user.dir"));
//
//
//
//		// 画像ファイルのパスを指定（ローカルファイル）
//		// 保存するファイル名を作成（重複を避けるためタイムスタンプを追加）
////		String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date(count));
//        String sourceImagePath = "C:/products/images/" + img;  // ここにローカルの画像パスを指定
//        System.out.println("img:" + img);
//
//        // 保存先フォルダのパスを指定
//        ServletContext context = req.getServletContext();
//        String savePath = req.getServletContext().getRealPath("/cafeconnect/img/product/");
//        String saveFolder = context.getRealPath(savePath);
//
//        String saveFolderPath = context.getContextPath() + saveFolder;
//
//        // フォルダが存在しない場合は作成
//        File folder = new File(saveFolderPath);
//        if (!folder.exists()) {
//            folder.mkdirs();
//        }
//
//        // 画像ファイルを読み込み、保存する
//        File sourceImageFile = new File(sourceImagePath);
//        if (sourceImageFile.exists()) {
//
//            File outputFile = new File(saveFolderPath + productName + formattedNow + ".jpg");
//
//            // ファイルをコピーして保存
//            Files.copy(Paths.get(sourceImagePath), Paths.get(outputFile.getAbsolutePath()));
//
//            System.out.println("画像が保存されました: " + outputFile.getAbsolutePath());
//            System.out.println("ファイル名：" + productName + formattedNow + ".jpg");
//            fileName = productName + formattedNow + ".jpg";
//        } else {
//            System.out.println("指定された画像ファイルが存在しません。");
//        }
//





		// 入力されたcategoryIdからcategoryを取得
		category = categoryDao.get(categoryId);

		// productを初期化
		Product product = new Product();

		// productに値をset
		product.setProductId(productId);
		product.setCategory(category);
		product.setProductName(productName);
		product.setPrice(price);
		product.setCount(count);
		product.setProductDetail(productDetail);
		product.setImage(fileName);
		product.setSell(sell);
		product.setInStockDay(inStockDay);

		// productをsave
		productDao.save(product);
		List<Category> categorys = categoryDao.get(); // カテゴリの情報すべて取得

		req.setAttribute("categorys", categorys); // リクエストパラメータにcategoryをセット


		// 商品登録ページに遷移
		req.getRequestDispatcher("productCreate.jsp").forward(req, res);

	}
}
