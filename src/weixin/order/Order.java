package weixin.order;

/**
 * @author qiuaijun
 *
 * @date 2015年4月29日
 */
public class Order {
	private int id;
	// 用户的OPENID
	private String userId;
	// 订单流程中所处状态: 1-选择商品 2-确定数量 3-完成预订
	private int status;

	private long productId;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public long getProductId() {
		return productId;
	}

	public void setProductId(long productId) {
		this.productId = productId;
	}

}
