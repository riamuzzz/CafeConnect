package bean;

// 商品別売り上げデータ
public class ProductSale {

	// 商品名
	private String productName;

	// 年
	private String year;

	// 月
	private String month;

	// データ（売上金額や個数）
	private int data;


	// ゲッター、セッター
	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}

	public int getData() {
		return data;
	}

	public void setData(int data) {
		this.data = data;
	}


}
