package my.utils;

/**
 * @organization: Microsoft
 * @author: tanyouzhong
 * @fileName: LocationUtil.java
 * @package: my.utils
 * @description: 位置工具类
 * @date: 2014年7月17日上午10:41:18
 * @modifyauthor: TODO
 * @modifydate: TODO
 * @modifycontent: TODO
 * @version: 1.0
 */
public class LocationUtil
{
	/** 地球半径（单位：米） **/
	private static final double EARTH_RADIUS = 6378137;

	/**
	 * @description 根据两点间经纬度坐标，计算两点间距离，单位为米
	 * @param p1 地点1
	 * @param p2 地点2
	 * @return 两点间的距离
	 */
	public static float distance(Point p1, Point p2)
	{
		double radLat1 = rad(p1.getLatitude());
		double radLat2 = rad(p2.getLatitude());
		double a = radLat1 - radLat2;
		double b = rad(p1.getLongitude()) - rad(p2.getLongitude());
		double distance = 2 * Math.asin(Math.sqrt(Math.pow(Math.sin(a / 2), 2) 
				+ Math.cos(radLat1) * Math.cos(radLat2) * Math.pow(Math.sin(b / 2), 2)));
		distance = distance * EARTH_RADIUS;
		distance = Math.round(distance * 10000) / 10000;
		return (float) distance;
	}

	private static double rad(double d)
	{
		return d * Math.PI / 180.0;
	}

	/**
	 * @author tanyouzhong
	 * @description 经纬度所代表的的地球上的某一点
	 * @date 2014年7月16日 下午9:28:31
	 */
	public static class Point
	{
		/** 经度 **/
		private float longitude;

		/** 纬度 **/
		private float latitude;

		public Point(float longitude, float latitude)
		{
			this.longitude = longitude;
			this.latitude = latitude;
		}

		public float getLongitude()
		{
			return longitude;
		}

		public void setLongitude(float longitude)
		{
			this.longitude = longitude;
		}

		public float getLatitude()
		{
			return latitude;
		}

		public void setLatitude(float latitude)
		{
			this.latitude = latitude;
		}
	}

	/**
	 * @description test
	 */
	public static void main(String[] args)
	{
		System.out.print(distance(
				new Point(29.5690130000f, 106.5455790000f),
				new Point(29.5675170000f, 106.5496440000f)));
	}
}