package bean;

import java.util.Date;
public class Product {

	//商品ID
	private String productId;

	//カテゴリ
	private Category category;

	//商品名
	private String productName;

	//金額
	private int price;

	//画像URL
	private String image;

	//商品詳細
	private String productDetail;

	//在庫数
	private int count;

	//販売
	private boolean sell;

	//入荷日
	private Date inStockDay;

	//ゲッターセッター
	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getProductDetail() {
		return productDetail;
	}

	public void setProductDetail(String productDetail) {
		this.productDetail = productDetail;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public boolean isSell() {
		return sell;
	}

	public void setSell(boolean sell) {
		this.sell = sell;
	}

	public Date getInStockDay() {
		return inStockDay;
	}

	public void setInStockDay(Date inStockDay) {
		this.inStockDay = inStockDay;
	}


}
