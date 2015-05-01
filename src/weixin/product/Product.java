package weixin.product;

/**
 * 产品信息模型
 * 
 * @author qiuaijun
 *
 * @date 2015年4月23日
 */
public class Product {
	// 产品标识
	private long id;
	// 产品类别
	private long categoryId;
	// 产品名称
	private String name;
	// 产品的图片
	private String imageUrl;
	// 产品描述信息
	private String desc;
	// 产品价格
	private double price;
	// 产品的产地
	private String home;
	// 产品状态:0-下架, 1-上架
	private int status;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(long categoryId) {
		this.categoryId = categoryId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getHome() {
		return home;
	}

	public void setHome(String home) {
		this.home = home;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

}
