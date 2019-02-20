package com.womow.toc.whocare.helper;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**日历，日期，时间相关的方法封装
 * @author cxy	2015-07-30
 */
public class Helper4Date {
	
	public static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	public static final SimpleDateFormat yyyyMMddHHmmss = new SimpleDateFormat("yyyyMMddHHmmss");
	public static final SimpleDateFormat sdf1 = new SimpleDateFormat("yyyyMMdd");
	public static final SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");
	public static final SimpleDateFormat sdf3 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss SSSS");
	public static final long dayms=24L * 60L * 60L * 1000L;	//一天的毫秒数
	
	/** 获取当前时间
	 * @return
	 */
	public static String getCurrentTime()
	{
        String systemtime=sdf.format(new Date());
        return systemtime;
	}
	
	/** 获取当前日期(2016-02-14)
	 * @return
	 */
	public static String getCurrentDay()
	{
        String systemtime=sdf2.format(new Date());
        return systemtime;
	}
	
	/** 获取当前日期(20160214)
	 * @return
	 */
	public static String getCurrentDayJustNum()
	{
        String systemtime=sdf1.format(new Date());
        return systemtime;
	}
	
	/** 获取当前月份(201804)
	 * @return
	 */
	public static String getCurrentMonthJustNum()
	{
		SimpleDateFormat sdfYyyyMm = new SimpleDateFormat("yyyyMM");
        String systemtime=sdfYyyyMm.format(new Date());
        return systemtime;
	}
	
	/** 获取当前时间 精确到毫秒
	 * @return
	 */
	public static String getCurrentTimeSSSS()
	{
        String systemtime=sdf3.format(new Date());
        return systemtime;
	}
	
	/**获得 多少分钟后的毫秒数
	 * @param minutes
	 * @return
	 */
	public static long getAfterMinutesTimeMillis(int minutes)
	{
		return System.currentTimeMillis()+(minutes*60*1000);
	}
	
	/**获得从当前日期 开始 未来的几天
	 * @return
	 */
	public static String getAfterDate(int days)
	{
		long nowTime=new Date().getTime();
		Calendar c = Calendar.getInstance();
		String result="";
		for(int i=0;i<days;i++)
		{
			result+=sdf2.format(new Date(nowTime+i*24L*60L*60L*1000L))+",";
		}
		if(!"".equals(result))
		{
			result=result.substring(0, result.length()-1);
		}
		return result;
	}
	
	/** 获得从开始时间 到结束时间的所有天
	 *  最多获取一年的所有天
	 * @author cxy 2015-12-23
	 * @param st
	 * @param et
	 * @return
	 */
	public static List<String> getDaysBetween(String st,String et)
	{
		List<String> days = new ArrayList<String>();
		try {
			Date st1=Helper4Date.sdf2.parse(st);
			Date et1=Helper4Date.sdf2.parse(et);
			
			long stms=st1.getTime();
			long etms=et1.getTime();
			
			long dayms=24L * 60L * 60L * 1000L;
			for(int i=0;i<365;i++)
			{
				long tmpms=stms + i*dayms;
				if(tmpms <= etms)
				{
					days.add(Helper4Date.sdf2.format(new Date(tmpms)));
				}else
				{
					break;
				}
			}
			
		} catch (ParseException e) {
		}
		
		return days;
	}
	
	/**获得某个时间之后几天的具体日期
	 * @param st
	 * @param afterDay
	 * @return
	 */
	public static List<String> getDaysBetweenAfterDays(String st,int afterDay)
	{
		List<String> days = new ArrayList<String>();
		try {
			Date st1=Helper4Date.sdf2.parse(st);
			Calendar day2 = Calendar.getInstance();
			day2.setTime(st1);
			day2.add(Calendar.DATE, afterDay);
			Date et1=day2.getTime();
			
			long stms=st1.getTime();
			long etms=et1.getTime();
			
			long dayms=24L * 60L * 60L * 1000L;
			for(int i=0;i<365;i++)
			{
				long tmpms=stms + i*dayms;
				if(tmpms <= etms)
				{
					days.add(Helper4Date.sdf2.format(new Date(tmpms)));
				}else
				{
					break;
				}
			}
			
		} catch (ParseException e) {
		}
		
		return days;
	}
	
	/** 获得从开始时间 到结束时间的所有天
	 *  最多获取31天所有天
	 * @author cxy 2015-12-24
	 * @param st
	 * @param et
	 * @return
	 */
	public static List<String> getDays31Between(String st,String et)
	{
		List<String> days = new ArrayList<String>();
		try {
			Date st1=Helper4Date.sdf2.parse(st);
			Date et1=Helper4Date.sdf2.parse(et);
			
			long stms=st1.getTime();
			long etms=et1.getTime();
			
			
			for(int i=0;i<31;i++)
			{
				long tmpms=stms + i*dayms;
				if(tmpms <= etms)
				{
					days.add(Helper4Date.sdf2.format(new Date(tmpms)));
				}else
				{
					break;
				}
			}
			
		} catch (ParseException e) {
		}
		
		return days;
	}
	
	/** 获得某月的最后一天
	 * @author cxy 2015-12-23
	 * @param year_month	2015-12
	 * @return
	 */
	public static String getLastDayOfMonth(String year_month)
    {
		int year=Integer.valueOf(year_month.split("-")[0]);
		int month=Integer.valueOf(year_month.split("-")[1]);
		
        Calendar cal = Calendar.getInstance();
        //设置年份
        cal.set(Calendar.YEAR,year);
        //设置月份
        cal.set(Calendar.MONTH, month-1);
        //获取某月最大天数
        int lastDay = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
        //设置日历中月份的最大天数
        cal.set(Calendar.DAY_OF_MONTH, lastDay);
        //格式化日期
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String lastDayOfMonth = sdf.format(cal.getTime());
         
        return lastDayOfMonth;
    }
	
	/**获得 系统月份上月的某天
	 * @param day	DAY_OF_MONTH
	 * @return
	 * 使用时谨慎,这里并没有经过测试用例测试!
	 */
	public static String getLastMonthOneDay(int day) {
		Calendar c = Calendar.getInstance();    
        c.add(Calendar.MONTH, -1);
        c.set(Calendar.DAY_OF_MONTH,day);
        return sdf2.format(c.getTime());
	}
	
	/** 获得 本月的某天
	 * @param day
	 * @return
	 */
	public static String getNowMonthOneDay(int day) {
		Calendar c = Calendar.getInstance();    
        c.set(Calendar.DAY_OF_MONTH,day);
        return sdf2.format(c.getTime());
	}
	
	/** 获得 两个日期相差多少天(et小于st，返回0)
	 * @param day
	 * @return
	 */
	public static int getDateDiff(String st,String et)
	{
		int r=0;
		try {
			Date st1=Helper4Date.sdf2.parse(st);
			Date et1=Helper4Date.sdf2.parse(et);
			
			if(st1.getTime() < et1.getTime())
			{
				r = Integer.valueOf(String.valueOf((et1.getTime()-st1.getTime())/dayms));
			}
			
		} catch (Exception e) {
		}
		
		return r;
	}
	
	/** 获得 两个日期相差多少天(et小于st，返回 负数)
	 * @param day
	 * @return
	 */
	public static int getDateDiffHaveLt0(String st,String et)
	{
		int r=0;
		try {
			Date st1=Helper4Date.sdf2.parse(st);
			Date et1=Helper4Date.sdf2.parse(et);
			r = Integer.valueOf(String.valueOf((et1.getTime()-st1.getTime())/dayms));
		} catch (Exception e) {
		}
		
		return r;
	}
	
	/** 获得 两个日期之间一共做少天
	 * @param day
	 * @return
	 */
	public static int getDaysNum(String st,String et)
	{
		int r=0;
		try {
			Date st1=Helper4Date.sdf2.parse(st);
			Date et1=Helper4Date.sdf2.parse(et);
			
			if(st1.getTime() <= et1.getTime())
			{
				r = Integer.valueOf(String.valueOf((et1.getTime()-st1.getTime())/dayms))+1;
			}
			
		} catch (Exception e) {
		}
		
		return r;
	}
	
	//计算某天后的日期
	public static String getDayAfter(String day,int afterDay)
	{
		try {
			Date data=Helper4Date.sdf2.parse(day);
			Calendar day2 = Calendar.getInstance();
			day2.setTime(data);
			day2.add(Calendar.DATE, afterDay);
			return sdf2.format(day2.getTime());
			
		} catch (ParseException e) {
		}
		
		return "";
	}
	
	//验证时间格式
	public static boolean isValidDate4yyyyMM(Set<String> months) {
		boolean convertSuccess=true;
		SimpleDateFormat format = new SimpleDateFormat("yyyyMM");
		try {
			for(String one : months)
			{
				if(one == null || one.length() != 6)
				{
					convertSuccess=false;
				}else
				{
					format.setLenient(false);	//严格验证 201816 过不去
			        format.parse(one);
				}
			}
	    } catch (ParseException e) {
	        convertSuccess=false;
	    } 
	    return convertSuccess;
	}
	
	public static void main(String[] args) {
		
		System.out.println(getCurrentMonthJustNum());
		
		Set<String> months = new HashSet<>();
		months.add("201803");
		months.add("201903");
		//months.add("2018-03");
		//months.add("20183");
		months.add("201813");
		System.out.println(isValidDate4yyyyMM(months));
		
		
		//SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		//System.out.println(sdf.format(new Date()));
		//System.out.println(sdf1.format(new Date()));
//		System.out.println(getDayAfter("2015-01-01",30));
//		System.out.println(getDaysNum("啊",""));
//		System.out.println(getDaysNum("2015-12-01","2015-12-31"));
//		System.out.println(getDaysNum("2015-12-01","2015-12-01"));
//		System.out.println(getDaysNum("2015-12-11","2015-12-01"));
//		System.out.println(getDaysNum("2015-11-11","2015-12-11"));
	}
}
