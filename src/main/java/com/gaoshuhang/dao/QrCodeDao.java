package com.gaoshuhang.dao;

import java.awt.image.BufferedImage;
import java.net.URL;

/**
 * 和二维码相关数据访问操作,这里只有生成二维码的接口,
 * service层调用这些接口并将获得的二维码存入用户session
 */
public interface QrCodeDao
{
	/**
	 * 设置二维码生存时间,即使不设置也应该有一个默认值
	 *
	 * @param sec 秒数
	 */
	public void setTimeOut(long sec);

	/**
	 * 生成二维码,该方法可能有性能问题
	 *
	 * @return 二维码内存位图对象
	 */
	public BufferedImage createQrCode();

	/**
	 * 生成二维码,第二种二维码获得方式
	 *
	 * @return 二维码URL, 也就是生成二维码后缓存在服务器上, 并返回其URL
	 */
	public URL createQrCodeWithUrl();

}
