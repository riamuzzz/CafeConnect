package bean;

import java.awt.image.BufferedImage;

public class Sale {

	//商品
	private Product product;

	//注文
	private Order order;

	//グラフ
	private BufferedImage graph;


	//ゲッターセッター
	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	public BufferedImage getGraph() {
		return graph;
	}

	public void setGraph(BufferedImage graph) {
		this.graph = graph;
	}


}
