package weixin.commodity;

/**
 * 
 * @author qiuaijun
 *
 * @date 2015年4月21日
 */
public class Goods {
	// 商品名称
	private String name;
	// 商品分类id，商品分类列表请通过《获取指定分类的所有子分类》获取
	private String category;
	// 商品主图(图片需调用图片上传接口获得图片Url填写至此，否则无法添加商品。图片分辨率推荐尺寸为640×600)
	private String main_imp_url;
	// 商品图片列表(图片需调用图片上传接口获得图片Url填写至此，否则无法添加商品。图片分辨率推荐尺寸为640×600)
	private String img;
	// 商品详细信息-文字描述
	private String detail_text;
	// 图片(图片需调用图片上传接口获得图片Url填写至此，否则无法添加商品)
	private String detail_image_url;
	// 商品属性列表，属性列表请通过《获取指定分类的所有属性》获取

}
