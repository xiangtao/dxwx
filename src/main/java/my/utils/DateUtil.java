package my.utils;

import java.text.DateFormatSymbols;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

/**
* 说明:      取日期时间工具 <p>
*/
public class DateUtil {
	
	public static final String Log_Date_format = "yyyyMMdd-HHmm";
	
	public static final String Log_Date_format_dd = "yyyyMMdd";

	public DateUtil(){}
	/**
     * @see     取得当前日期（格式为：yyyy-MM-dd）
     * @return	String
     */
    public static String getDate()
    {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String sDate = sdf.format(new Date());
        return sDate;
    }

    /**
     * @see     取得当前时间（格式为：yyy-MM-dd HH:mm:ss）
     * @return	String
     */
    public static String getDateTime()
    {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String sDate = sdf.format(new Date());
        return sDate;
    }
   
    /**
     * @see     按指定格式取得当前时间()
     * @return	String
     */
    public static String getTimeFormat(String strFormat)
    {
        SimpleDateFormat sdf = new SimpleDateFormat(strFormat);
        String sDate = sdf.format(new Date());
        return sDate;
    }
    /**
     * @see     取得指定时间的给定格式()
     * @return	String
     * @throws ParseException
     */
    public static String getGiveDateFormat(Date myDate,String strFormat)
    {
        SimpleDateFormat sdf = new SimpleDateFormat(strFormat);
        String sDate = sdf.format(myDate);
        return sDate;
    }
    

    /**
     * @return	String
     * @throws ParseException
     */
    public static Date getDateByDateStr(String myDate,String strFormat) throws ParseException
    {
        SimpleDateFormat sdf = new SimpleDateFormat(strFormat);
        Date sdate  = sdf.parse(myDate);
        return sdate;
    }
    
    /**
     * 给定语言环境
     * @param myDate
     * @param strFormat
     * @param locale
     * @return
     * @throws ParseException
     */
    public static Date getDateByDateStr(String myDate,String strFormat, Locale locale) throws ParseException
    {
        SimpleDateFormat sdf = new SimpleDateFormat(strFormat,locale);
        Date sdate  = sdf.parse(myDate);
        return sdate;
    }
    
    public static Date getDateByDateStr(String myDate,String strFormat,  DateFormatSymbols formatSymbols) throws ParseException
    {
        SimpleDateFormat sdf = new SimpleDateFormat(strFormat,formatSymbols);
        Date sdate  = sdf.parse(myDate);
        return sdate;
    }
    
    
    
    
    public static Date addDateCount(Date date,int field, int amount ){
    	Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.add(field, amount);
		return c.getTime();
    }

    public static String formatDateTime(String strDateTime, String strFormat)
    {
        String sDateTime = strDateTime;
        try {
            Calendar Cal = parseDateTime(strDateTime);
            SimpleDateFormat sdf = null;
            sdf = new SimpleDateFormat(strFormat);
            sDateTime = sdf.format(Cal.getTime());
        } catch (Exception e) {

        }
        return sDateTime;
    }

    public static Calendar parseDateTime(String baseDate)
    {
        Calendar cal = null;
        cal = new GregorianCalendar();
        int yy = Integer.parseInt(baseDate.substring(0, 4));
        int mm = Integer.parseInt(baseDate.substring(5, 7)) - 1;
        int dd = Integer.parseInt(baseDate.substring(8, 10));
        int hh = 0;
        int mi = 0;
        int ss = 0;
        if(baseDate.length() > 12)
        {
            hh = Integer.parseInt(baseDate.substring(11, 13));
            mi = Integer.parseInt(baseDate.substring(14, 16));
            ss = Integer.parseInt(baseDate.substring(17, 19));
        }
        cal.set(yy, mm, dd, hh, mi, ss);
        return cal;
    }
    public static Calendar parseDateTime1(String baseDate)
    {
        Calendar cal = null;
       
        cal = new GregorianCalendar();
        int yy = Integer.parseInt(baseDate.substring(0, 4));
        int mm = Integer.parseInt(baseDate.substring(5, 7)) - 1;
        int dd = Integer.parseInt(baseDate.substring(8, 10));
        int hh = 0;
        int mi = 0;
        int ss = 0;
        if(baseDate.length() > 12)
        {
            hh = Integer.parseInt(baseDate.substring(11, 13));
            mi = Integer.parseInt(baseDate.substring(14, 16));
            if(baseDate.length()>18){
            	 ss = Integer.parseInt(baseDate.substring(17, 19));
            }
           
        }
        cal.set(yy, mm, dd, hh, mi, ss);
        return cal;
    }
    public int getDay(String strDate)
    {
        Calendar cal = parseDateTime(strDate);
        return  cal.get(Calendar.DATE);
    }

    public int getMonth(String strDate)
    {
        Calendar cal = parseDateTime(strDate);

        return cal.get(Calendar.MONTH) + 1;
    }

    public int getWeekDay(String strDate)
    {
        Calendar cal = parseDateTime(strDate);
        return cal.get(Calendar.DAY_OF_WEEK);
    }

    public String getWeekDayName(String strDate)
    {
        String mName[] = {
            "日", "一", "二", "三", "四", "五", "六"
        };
        int iWeek = getWeekDay(strDate);
        iWeek = iWeek - 1;
        return "星期" + mName[iWeek];
    }

    public int getYear(String strDate)
    {
        Calendar cal = parseDateTime(strDate);
        return cal.get(Calendar.YEAR) + 1900;
    }

    public String DateAdd(String strDate, int iCount, int iType)
    {
        Calendar Cal = parseDateTime(strDate);
        int pType = 0;
        if(iType == 0)
        {
            pType = 1;
        } else
        if(iType == 1)
        {
            pType = 2;
        } else
        if(iType == 2)
        {
            pType = 5;
        } else
        if(iType == 3)
        {
            pType = 10;
        } else
        if(iType == 4)
        {
            pType = 12;
        } else
        if(iType == 5)
        {
            pType = 13;
        }
        Cal.add(pType, iCount);
        SimpleDateFormat sdf = null;
        if(iType <= 2)
            sdf = new SimpleDateFormat("yyyy-MM-dd");
        else
            sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String sDate = sdf.format(Cal.getTime());
        return sDate;
    }

    public String DateAdd(String strOption, int iDays, String strStartDate)
    {
        if(!strOption.equals("d"));
        return strStartDate;
    }

    public int DateDiff(String strDateBegin, String strDateEnd, int iType)
    {
        Calendar calBegin = parseDateTime(strDateBegin);
        Calendar calEnd = parseDateTime(strDateEnd);
        long lBegin = calBegin.getTimeInMillis();
        long lEnd = calEnd.getTimeInMillis();
        int ss = (int)((lBegin - lEnd) / 1000L);
        int min = ss / 60;
        int hour = min / 60;
        int day = hour / 24;
        if(iType == 0)
            return hour;
        if(iType == 1)
            return min;
        if(iType == 2)
            return day;
        else
            return -1;
    }
    
     /*****************************************
     * @功能     计算某年第一周开始日期
     * @return	 返回日期
     * @throws ParseException
     ****************************************/
    public int getWeekNumOfYear()
    {
    	Calendar calendar = Calendar.getInstance();
    	int iWeekNum = calendar.get(Calendar.WEEK_OF_YEAR);
    	return iWeekNum;
    }
   
    public static void main(String[] args) throws ParseException {
    	Date date = DateUtil.getDateByDateStr("09/Apr/2013:10:51:02 +0800", "dd/MMM/yyyy:HH:mm:ss Z",DateFormatSymbols.getInstance(Locale.ENGLISH));
//		date = DateUtil.addDateCount(date, Calendar.MINUTE,10);
//		String name = DateUtil.getGiveDateFormat(date, "yyyyMMdd-HHmm");  //Tue Apr 09 10:51:02 CST 2013
		
		System.out.println("------------>" + DateUtil.getGiveDateFormat(date, "yyyy-MM-dd HH:mm:ss"));
		
		
		date = new Date();
		Date date2 = DateUtil.addDateCount(date, Calendar.MINUTE,-5);
		
		System.out.println("------------>" + DateUtil.getGiveDateFormat(date2, "yyyy-MM-dd HH:mm:ss"));
		
		
		
	}
}
