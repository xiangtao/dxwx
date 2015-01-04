package my.utils;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.Random;

import com.deve.pig.controller.VerifyCodeController;

public class CreateVerificationCode
{
	// 验证码图片尺寸
	private static int WIDTH;

	private static int HEIGHT;

	/****************************** 生成返回数字验证码图片 *****************************************/

	/*
	 * 生成数字验证码图片
	 */
	public static BufferedImage getNumCheckCode(int width, int height,
			Color bgColor, Color borderColor, int charCount, int randomLineCount)
	{
		WIDTH = width;
		HEIGHT = height;

		// 创建缓冲区图形对象
		BufferedImage bufferimage = new BufferedImage(WIDTH, HEIGHT,
				BufferedImage.TYPE_INT_RGB);
		// 获得绘图环境
		Graphics g = bufferimage.getGraphics();

		// 设置图片相关属性
		method(g, bgColor, borderColor);

		// 画干扰线
		drawRandomLine(g, randomLineCount);

		// 画随机数字
		String drawStr = "0123456789";
		drawRandomChar((Graphics2D) g, drawStr, 18, charCount); // 将g其强制转换为2D绘图对象，以便实现随机字符旋转

		return bufferimage;
	}

	/****************************** 私有方法 *****************************************/

	// 调用下面的私有方法的方法(设置图片相关属性的方法)
	public static void method(Graphics g, Color bgColor, Color borderColor)
	{
		// 设置验证码图片背景颜色
		setBackground(g, bgColor);

		// 设置验证码图片边框
		setBorder(g, borderColor);
	}

	// 设置背景色方法
	private static void setBackground(Graphics g, Color bgColor)
	{
		g.setColor(bgColor);
		g.fillRect(0, 0, WIDTH, HEIGHT);
	}

	// 设置边框方法
	private static void setBorder(Graphics g, Color borderColor)
	{
		g.setColor(borderColor);
		g.drawRect(0, 0, WIDTH - 1, HEIGHT - 1);
	}

	// 画干扰线方法(参数：1、绘图对象 2、干扰线条数)
	private static void drawRandomLine(Graphics g, int lineCount)
	{
		Random r = new Random(); // 随机数生成器

		for (int i = 0; i < lineCount; i++) // 随机生成参数指定条颜色不同干扰线
		{
			int rr = r.nextInt(255); // 红色分量
			int gg = r.nextInt(255); // 绿色分量
			int bb = r.nextInt(255); // 蓝色分量
			g.setColor(new Color(rr, gg, bb)); // 设置干扰线颜色

			int x1 = r.nextInt(WIDTH); // 起始X
			int x2 = r.nextInt(WIDTH); // 终点X
			int y1 = r.nextInt(HEIGHT); // 起始Y
			int y2 = r.nextInt(HEIGHT); // 终点Y

			g.drawLine(x1, y1, x2, y2); // 绘制干扰线
		}
	}

	// 画随机字符方法(参数：1、绘图对象 2、抽取字符库 3、绘制字符大小 4、绘制字符个数)
	private static void drawRandomChar(Graphics2D g, String drawStr,
			int fontSize, int drawCharCount)
	{
		g.setFont(new Font("宋体", Font.BOLD, fontSize)); // 设置绘制字符字体
		StringBuffer code = new StringBuffer();
		Random r = new Random(); // 随机数生成器

		for (int i = 0; i < drawCharCount; i++)
		{
			int rr = r.nextInt(255); // 红色分量
			int gg = r.nextInt(255); // 绿色分量
			int bb = r.nextInt(255); // 蓝色分量
			g.setColor(new Color(rr, gg, bb)); // 设置字符颜色

			String str = "" + drawStr.charAt(r.nextInt(drawStr.length())); // 随机抽取字符

			code.append(str); // 将每生成的一个字符追加到code中

			int degree = r.nextInt() % 20; // 旋转度数
			g.rotate(degree * Math.PI / 180, 5 + i * 25, 25); // 随机旋转字符

			g.drawString(str, 5 + i * (WIDTH / drawCharCount), fontSize); // 绘制随机字符

			g.rotate(-degree * Math.PI / 180, 5 + i * 23, 25); // 反向旋转前面的角度，复位，以便下次旋转
		}

		VerifyCodeController.verifyCode_s = code.toString(); // 将生成的验证码值传给GetVerifyCode中得verifyCode_s
	}
}
