package com.tav.common.util;

import java.text.DateFormat;
import java.text.DateFormatSymbols;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;
import java.util.Vector;

import com.tav.common.util.bean.CustomLocale;
import org.apache.log4j.Logger;

/**
 *
 * @author truongtx5
 *
 */
public class DateUtil {

    private static final Logger logger = Logger.getLogger(DateUtil.class);

    private static final String[] DAYS_NAME_OF_WEEK_VN = new String[]{"",
        "Chủ nhật", "Thứ hai", "Thứ ba", "Thứ tư", "Thứ năm", "Thứ sáu",
        "Thứ bảy"};

    private static final String[] DAYS_NAME_OF_WEEK_VN_SHORT = new String[]{
        "", "CN", "Thứ 2", "Thứ 3", "Thứ 4", "Thứ 5", "Thứ 6", "Thứ 7"};

    private static long HOUR_IN_MS = 60 * 60 * 1000;

    public static String date2ddMMyyyyHHMMss(Date value) {
        if (value != null) {
            SimpleDateFormat dateTimeNoSlash = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
            return dateTimeNoSlash.format(value);
        }
        return "";
    }

    /**
     * Subtract days
     *
     */
    public static int subtractDays(Date date1, Date date2) {
        GregorianCalendar gc1 = new GregorianCalendar();
        gc1.setTime(date1);
        GregorianCalendar gc2 = new GregorianCalendar();
        gc2.setTime(date2);
        int days1 = 0;
        int days2 = 0;
        int maxYear = Math.max(gc1.get(Calendar.YEAR), gc2.get(Calendar.YEAR));
        GregorianCalendar gctmp = (GregorianCalendar) gc1.clone();
        for (int f = gctmp.get(Calendar.YEAR); f < maxYear; f++) {
            days1 += gctmp.getActualMaximum(Calendar.DAY_OF_YEAR);
            gctmp.add(Calendar.YEAR, 1);
        }
        gctmp = (GregorianCalendar) gc2.clone();
        for (int f = gctmp.get(Calendar.YEAR); f < maxYear; f++) {
            days2 += gctmp.getActualMaximum(Calendar.DAY_OF_YEAR);
            gctmp.add(Calendar.YEAR, 1);
        }
        days1 += gc1.get(Calendar.DAY_OF_YEAR) - 1;
        days2 += gc2.get(Calendar.DAY_OF_YEAR) - 1;
        return (days1 - days2);
    }

    /**
     * Get days between 2 dates
     *
     * @param fromDate
     * @param toDate
     * @return
     */
    public static long getDaysBetween2Dates(Date fromDate, Date toDate) {
        Calendar c1 = Calendar.getInstance();
        Calendar c2 = Calendar.getInstance();
        c1.setTime(fromDate);
        c2.setTime(toDate);
        long day = (c2.getTime().getTime() - c1.getTime().getTime())
                / (24 * 3600 * 1000);
        return day;
    }

    /**
     * Get month between 2 dates
     *
     * @param date1
     * @param date2
     * @return
     */
    public static int getMonthSubtractDays(Date date1, Date date2) {
        int year1 = getYear(date1);
        int year2 = getYear(date2);
        int month1 = getMonth(date1);
        int month2 = getMonth(date2);
        return ((year1 - year2) * 12 + (month1 - month2));
    }

    /**
     * Get days in month
     *
     * @param date
     * @return
     */
    public static short getDaysInMonth(Date date) {
        GregorianCalendar c = new GregorianCalendar();
        c.setTime(date);
        return (short) c.getActualMaximum(Calendar.DAY_OF_MONTH);
    }

    /**
     * Get days in month
     *
     * @param month
     * @param year
     * @return
     */
    public static int getDaysInMonth(int month, int year) {
        Date date = createDate(year, month, 1);
        GregorianCalendar c = new GregorianCalendar();
        c.setTime(date);
        return c.getActualMaximum(Calendar.DAY_OF_MONTH);
    }

    /**
     * Get system date
     *
     * @return
     */
    public static Date getSystemDate() {
        Calendar calendar = new GregorianCalendar();
        return calendar.getTime();
    }

    /**
     * Add month
     *
     * @param date
     * @param month
     * @return
     */
    public static Date addMonth(Date date, int month) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.MONTH, month);
        return c.getTime();
    }

    /**
     * Add date
     *
     * @param date
     * @param num
     * @return
     */
    public static Date addDate(Date date, int num) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.DATE, num);
        return c.getTime();
    }

    /**
     * Add date
     *
     * @param date
     * @param num
     * @return
     */
    public static Date addHours(Date date, int num) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.HOUR_OF_DAY, num);
        return c.getTime();
    }

    /**
     * Add second
     *
     * @param second
     * @param num
     * @return
     */
    public static Date addMinute(Date date, int num) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.MINUTE, num);
        return c.getTime();
    }

    /**
     * Add second
     *
     * @param second
     * @param num
     * @return
     */
    public static Date addSecond(Date date, int num) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.SECOND, num);
        return c.getTime();
    }

    public static java.sql.Date convertDateUtil2Sql(Date date) {
        return new java.sql.Date(date.getTime());
    }

    public static java.sql.Timestamp convertDateUtil2TimestampSql(Date date) {
        return new java.sql.Timestamp(date.getTime());
    }

    public static java.sql.Timestamp convertDate2TimestampSql(Date date,
            boolean isFirstTimeOfDate, boolean isLastTimeOfDate) {
        String defaultFormat = "dd/MM/yyyy";
        String dateStr = new SimpleDateFormat(defaultFormat).format(date);
        if (isFirstTimeOfDate) {
            dateStr += " 00:00:00";
        }
        if (isLastTimeOfDate) {
            dateStr += " 23:59:59";
        }
        String convertedPattern = "dd/MM/yyyy HH:mm:ss";
        Date _converted;
        try {
            _converted = new SimpleDateFormat(convertedPattern).parse(dateStr);
        } catch (ParseException e) {
            logger.error(e);
            _converted = date;
        }
        return convertDateUtil2TimestampSql(_converted);
    }

    public static Short getQuarterByMonth(Short month) {
        return (short) (((month - 1) / 3) + 1);
    }

    public static int getStartMonthByQuarter(int quarter) {
        return quarter * 3 - 2;
    }

    public static java.sql.Date stringToDateSQL(String dateDMY) {
        if (CommonUtil.isEmpty(dateDMY)) {
            return null;
        }
        int posDay = dateDMY.indexOf("/");
        if (posDay < 0) {
            return null;
        }
        String strDay = dateDMY.substring(0, posDay);
        if (!CommonUtil.isDigitString(strDay)) {
            return null;
        }
        int day = Integer.parseInt(strDay);
        if (day < 1 || day > 31) {
            return null;
        }
        int posMonth = dateDMY.indexOf("/", posDay + 1);
        if (posMonth < 0) {
            return null;
        }
        String strMonth = dateDMY.substring(posDay + 1, posMonth);
        if (!CommonUtil.isDigitString(strMonth)) {
            return null;
        }
        int month = Integer.parseInt(strMonth);
        if (month < 1 || month > 12) {
            return null;
        }
        String strYear = dateDMY.substring(posMonth + 1);
        if (!CommonUtil.isDigitString(strYear)) {
            return null;
        }
        int year = Integer.parseInt(strYear);
        if (month == 2) {
            if (year % 4 == 0) {
                if (day > 29) {
                    return null;
                }
            } else if (day > 28) {
                return null;
            }
        }
        if (month == 4 || month == 6 || month == 9 || month == 11) {
            if (day > 30) {
                return null;
            }
        }
        return java.sql.Date.valueOf(year + "-"
                + (month < 10 ? ("0" + month) : month) + "-"
                + (day < 10 ? ("0" + day) : day));
    }

    public static Date stringToDate(String dateDMY) {
        if (CommonUtil.isEmpty(dateDMY)) {
            return null;
        }

        String[] array = dateDMY.split("/");
        String result = array[array.length - 1];
        if (result.length() == 2) {
            result = "20" + result;
            int index = dateDMY.lastIndexOf("/");
            String temp = dateDMY.substring(0, index + 1);
            result = temp + result;
        } else {
            result = dateDMY;
        }
        java.sql.Date date = stringToDateSQL(result);
        if (date == null) {
            return null;
        }
        Date temp = new Date(date.getTime());
        return temp;
    }

    public static boolean isDMYDate(String dateDMY) {
        Date date = stringToDateSQL(dateDMY);
        return (date != null);
    }

    public static int getCurrentYear() {
        Calendar calendar = new GregorianCalendar();
        return calendar.get(Calendar.YEAR);
    }

    public static int getYear(Date date) {
        if (date == null) {
            return 0;
        }
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int year = cal.get(Calendar.YEAR);
        return year;
    }

    /**
     *
     * @param date
     * @return
     */
    public static short getDate(Date date) {
        if (date == null) {
            return 0;
        }
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int dateInt = cal.get(Calendar.DATE);
        return ((Integer) dateInt).shortValue();
    }

    public static String getStringYear(Date date) {
        String sYear = "";
        if (date == null) {
            return sYear;
        }
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int year = cal.get(Calendar.YEAR);
        sYear = year + "";
        return sYear;
    }

    public static int getCurrentMonth() {
        Calendar calendar = new GregorianCalendar();
        return calendar.get(Calendar.MONTH) + 1;
    }

    public static int getMonth(Date date) {
        if (date == null) {
            return 0;
        }
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int month = cal.get(Calendar.MONTH) + 1;
        return month;
    }

    public static String getStringMonth(Date date) {
        String sMonth = "";
        if (date == null) {
            return sMonth;
        }
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int month = cal.get(Calendar.MONTH) + 1;
        if (month < 10) {
            sMonth = "0" + month;
        } else {
            sMonth = month + "";
        }
        return sMonth;
    }

    public static int getCurrentDay() {
        Calendar calendar = new GregorianCalendar();
        return calendar.get(Calendar.DAY_OF_MONTH);
    }

    public static int getDay(Date date) {
        if (date == null) {
            return 0;
        }
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int day = cal.get(Calendar.DAY_OF_MONTH);
        return day;
    }

    public static int getDayOfWeek(Date date) {
        if (date == null) {
            return 0;
        }
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int day = cal.get(Calendar.DAY_OF_WEEK);
        return day;
    }

    public static String getStringDay(Date date) {
        String sDay = "";
        if (date == null) {
            return sDay;
        }
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int day = cal.get(Calendar.DAY_OF_MONTH);
        if (day < 10) {
            sDay = "0" + day;
        } else {
            sDay = day + "";
        }
        return sDay;
    }

    /**
     * LAY NGAY VA THANG
     *
     * @param date
     * @return
     * @author VICQ
     */
    public static String getStringDayMouth(Date date) {

        String sDay = "";
        if (date == null) {
            return sDay;
        }
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int day = cal.get(Calendar.DAY_OF_MONTH);
        if (day < 10) {
            sDay = "0" + day;
        } else {
            sDay = day + "";
        }
        cal.setTime(date);
        int month = cal.get(Calendar.MONTH) + 1;
        if (month < 10) {
            return sDay + "0" + month;
        } else {
            return sDay + month + "";
        }

    }

    public static int getCurrentHour() {
        Calendar calendar = new GregorianCalendar();
        return calendar.get(Calendar.HOUR_OF_DAY);
    }

    public static int getHour(Date date) {
        if (date == null) {
            return 0;
        }
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int hour = cal.get(Calendar.HOUR_OF_DAY);
        return hour;
    }

    public static int getMinute(Date date) {
        if (date == null) {
            return 0;
        }
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int minute = cal.get(Calendar.MINUTE);
        return minute;
    }

    public static int getSecond(Date date) {
        if (date == null) {
            return 0;
        }
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int second = cal.get(Calendar.SECOND);
        return second;
    }

    public static Date createDate(int year, int month, int dayOfMonth, int hour) {
        Calendar cal = Calendar.getInstance();
        cal.set(year, month - 1, dayOfMonth, hour, 0, 0);
        return cal.getTime();
    }

    public static Date createDate(int year, int month, int dayOfMonth,
            int hour, int minute, int second) {
        Calendar cal = Calendar.getInstance();
        cal.set(year, month - 1, dayOfMonth, hour, minute, second);
        return cal.getTime();
    }

    public static Date createDate(int year, int month, int dayOfMonth) {
        Calendar cal = Calendar.getInstance();
        cal.set(year, month - 1, dayOfMonth);
        return cal.getTime();
    }

    public static Date createDate(int year, int month) {
        Calendar cal = Calendar.getInstance();
        cal.set(year, month - 1, 1);
        return cal.getTime();
    }

    public static Date createDate(int year) {
        Calendar cal = Calendar.getInstance();
        cal.set(year, 0, 1, 0, 0, 0);
        return cal.getTime();
    }

    /**
     *
     *
     * @return Date
     */
    public static Date createLastDate(String year) {
        String dateDMY = "31/12/" + year;
        return stringToDate(dateDMY);
    }

    public static Date getCurrentTime() {
        Calendar calendar = Calendar.getInstance();
        return calendar.getTime();
    }

    public static String getDayHourMinute(Date date) {
        DateUtil.getStringMonth(date);
        DateUtil.getStringDay(date);
        DateUtil.getStringYear(date);
        String hourminute = ":" + String.valueOf(DateUtil.getHour(date));
        if (DateUtil.getHour(date) == 0) {
            hourminute = "";
        }
        String targetString = DateUtil.getStringDay(date) + "/"
                + DateUtil.getStringMonth(date) + "/"
                + DateUtil.getStringYear(date) + hourminute;
        return targetString;
    }

    /**
     *
     *
     */
    public static final List<Integer> createYearList(int startYear) {
        List<Integer> result = new Vector<Integer>();
        Date currentDate = new Date();
        int currentYear = DateUtil.getYear(currentDate);
        if (currentYear < startYear) {
            result.add(currentYear);
        } else {
            for (int i = startYear; i <= currentYear + 1; i++) {
                result.add(i);
            }
        }
        return result;
    }

    /**
     * @param date
     * @param month
     * @return
     */
    public static Date addDayByMonth(Date date, short month) {
        String rets = "";

        int thang = DateUtil.getMonth(date) + month;
        int phanle = thang % 12;
        int phannguyen = thang / 12;

        rets = rets + String.valueOf(DateUtil.getDay(date));
        rets = rets + "/";
        rets = rets + String.valueOf(phanle == 0 ? 12 : phanle);
        rets = rets + "/";
        rets = rets
                + String.valueOf(DateUtil.getYear(date)
                        + (phanle == 0 ? phannguyen - 1 : phannguyen));

        return DateUtil.stringToDate(rets);
    }

    /**
     *
     *
     * @param date
     * @return
     */
    public static Date getDayBefore(Date date) {
        long MILLIS_IN_A_DAY = 1000 * 60 * 60 * 24;

        Date dayBefore = new Date(date.getTime() - MILLIS_IN_A_DAY);
        return dayBefore;
    }

    /**
     * Lấy ngày nằm sau.
     *
     * @param ngay Ngày tính làm mốc.
     * @param soNgay Số ngày nằm sau.
     *
     * @return Ngày cảnh báo nằm sau.
     *
     */
    public static Date getDayAfter(Date ngay, int soNgay) {
        GregorianCalendar gc = new GregorianCalendar();
        gc.setTime(ngay);
        gc.add(Calendar.DAY_OF_YEAR, soNgay);
        Date dayAfter = gc.getTime();

        return dayAfter;
    }

    /**
     *
     */
    public static Date getDayBefore(Date ngay, int soNgay) {
        GregorianCalendar gcSau = new GregorianCalendar();
        gcSau.setTime(ngay);
        gcSau.add(Calendar.DAY_OF_YEAR, -soNgay);
        Date dayBefore = gcSau.getTime();

        return dayBefore;
    }

    /**
     * Compare date
     *
     * @param date1
     * @param date2
     * @return
     */
    public static int compareDate(Date date1, Date date2) {
        Calendar cal1 = Calendar.getInstance();
        cal1.setTime(date1);
        Calendar cal2 = Calendar.getInstance();
        cal2.setTime(date2);
        int year1 = cal1.get(Calendar.YEAR);
        int year2 = cal2.get(Calendar.YEAR);
        int month1 = cal1.get(Calendar.MONTH);
        int month2 = cal2.get(Calendar.MONTH);
        int day1 = cal1.get(Calendar.DAY_OF_MONTH);
        int day2 = cal2.get(Calendar.DAY_OF_MONTH);
        if (year1 > year2) {
            return 1;
        } else if (year1 < year2) {
            return -1;
        }
        if (month1 > month2) {
            return 1;
        } else if (month1 < month2) {
            return -1;
        }
        if (day1 > day2) {
            return 1;
        } else if (day1 < day2) {
            return -1;
        }
        return 0;
    }

    /**
     * Hàm so sánh hai ngày, chỉ so sánh tháng và năm
     *
     * @param date1
     * @param date2
     * @return 0 nếu bằng nhau, < 0 nếu date1 < date2, > 0 nếu date1 > date2
     */
    public static int compare2Month(Date date1, Date date2) {
        Calendar cal1 = Calendar.getInstance();
        cal1.setTime(date1);
        Calendar cal2 = Calendar.getInstance();
        cal2.setTime(date2);
        int y1 = cal1.get(Calendar.YEAR);
        int y2 = cal2.get(Calendar.YEAR);
        int m1 = cal1.get(Calendar.MONTH);
        int m2 = cal2.get(Calendar.MONTH);
        if (y1 != y2) {
            return y1 - y2;
        }
        return m1 - m2;
    }

    /**
     *
     *
     * @param d1
     * @param d2
     * @return
     *
     */
    public static long laySoNgayGiuaHaiThoiDiem(Date d1, Date d2) {
        Calendar c1 = Calendar.getInstance();
        c1.setTime(d1);
        c1.set(Calendar.HOUR_OF_DAY, 0);

        Calendar c2 = Calendar.getInstance();
        c2.setTime(d2);
        c2.set(Calendar.HOUR_OF_DAY, 0);

        long ONE_HOUR = 60 * 60 * 1000L;
        // long day = ((d2.getTime() - d1.getTime() + ONE_HOUR) / (ONE_HOUR *
        // 24));
        long day = ((c2.getTimeInMillis() - c1.getTimeInMillis() + ONE_HOUR) / (ONE_HOUR * 24));
        return day < 0 ? -day : day;

    }

    /**
     *
     * @param d1
     * @param d2
     * @return
     */
    public static int laySoThangGiuaHaiThoiDiem(Date d1, Date d2) {
        int ret = (getMonth(d2) + getYear(d2) * 12 - (getMonth(d1) + getYear(d1) * 12));
        return ret;
    }

    /**
     * Hàm xử lý ngày khi tăng hoặc giảm tháng.
     *
     * @param Thang là tháng cần tính ngày
     * @param Nam năm cần tính ngày
     * @param ngay là ngày của tháng được tính
     * @return ngày trong Nam và Thang
     */
    public static int layDay(int thang, int nam, int ngay) {

        Date ngayGoc = DateUtil.stringToDate("1/" + String.valueOf(thang) + "/"
                + String.valueOf(nam));

        int day = DateUtil.getDaysInMonth(ngayGoc);

        if (ngay <= day) {
            return ngay;
        } else {
            return day;
        }
    }

    /**
     * Lấy ngày đầu tiên của tháng
     */
    public static Date getFirstDayOfMonth(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.DAY_OF_MONTH, 1);
        cal.set(Calendar.AM_PM, Calendar.AM);
        cal.set(Calendar.HOUR, 11);
        cal.set(Calendar.MINUTE, 59);
        cal.set(Calendar.SECOND, 59);
        return cal.getTime();
    }

    public static Date getSameDayLastYear(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.YEAR, -1);
        return cal.getTime();
    }

    public static void main(String args[]) {
        System.out.println(getSameDayLastYear(createDate(2012, 2, 29)));

    }

    /**
     * Lấy ngày cuối cùng của tháng
     */
    public static Date getLastDayOfMonth(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.DAY_OF_MONTH,
                cal.getActualMaximum(Calendar.DAY_OF_MONTH));
        cal.set(Calendar.AM_PM, Calendar.PM);
        cal.set(Calendar.HOUR, 11);
        cal.set(Calendar.MINUTE, 59);
        cal.set(Calendar.SECOND, 59);
        return cal.getTime();
    }

    /**
     * Lấy ngày đầu tiên của quý
     *
     * @author pmdn_tutm3
     * @Time: Mar 6, 2013
     * @param quarter
     * @param year
     * @return
     */
    public static Date getFirstDayOfQuarter(int quarter, int year) {
        int month = quarter * 3 - 2;
        return createDate((short) year, (short) month);
    }

    /**
     * @Description: Lấy ngày đầu tiên của 6 tháng đầu năm hoặc cuối năm
     * @Author: pmdn_ducln1
     * @Time: Apr 4, 2013
     * @param quarter
     * @param year
     * @return
     */
    public static Date getFirstDayOfSixMonth(int sixMonth, int year) {
        int month = sixMonth * 6 - 5;
        return createDate((short) year, (short) month);
    }

    /**
     * @Description: Lấy ngày đầu tiên của năm
     * @Author: pmdn_ducln1
     * @Time: Apr 4, 2013
     * @param year
     * @return
     */
    public static Date getFirstDayOfYear(int year) {
        return getFirstDayOfMonth(createDate(year, 1));
    }

    /**
     * Lấy ngày cuối cùng của quý
     *
     * @author pmdn_tutm3
     * @Time: Mar 6, 2013
     * @param quarter
     * @param year
     * @return
     */
    public static Date getLastDayOfQuarter(int quarter, int year) {
        int month = quarter * 3;
        return getLastDayOfMonth(createDate((short) year, (short) month));
    }

    /**
     * Lấy ngày cuối cùng của 6 tháng đầu năm hoặc cuối năm
     *
     * @author ducln1
     * @Time: Mar 6, 2013
     * @param quarter
     * @param year
     * @return
     */
    public static Date getLastDayOfSixMonth(int sixMonth, int year) {
        int month = sixMonth * 6;
        return getLastDayOfMonth(createDate((short) year, (short) month));
    }

    /**
     * Lấy ngày cuối cùng của năm
     *
     * @author ducln1
     * @Time: Mar 6, 2013
     * @param quarter
     * @param year
     * @return
     */
    public static Date getLastDayOfYear(int year) {
        return getLastDayOfMonth(createDate((short) year, (short) 12));
    }

    /**
     * Ngày tháng theo định dạng
     *
     * @author pmdn_tutm3
     * @Time: Mar 11, 2013
     * @param date
     * @param format
     * @return
     */
    public static String formatDate(Date date, String format) {
        if (date == null) {
            return "";
        }
        SimpleDateFormat sf = new SimpleDateFormat(format);
        return sf.format(date);
    }

    public static Date getDateSubHour(Date date, int hour) {
        try {
            return new Date(date.getTime() - hour * HOUR_IN_MS);
        } catch (Exception e) {
            logger.error(e);
            return null;
        }

    }

    public static Date getMondayAnyWeek(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.setFirstDayOfWeek(Calendar.MONDAY);
        calendar.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
        calendar.set(Calendar.AM_PM, Calendar.AM);
        calendar.set(Calendar.HOUR, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        return new Date(calendar.getTimeInMillis());
    }

    public static Date getMondayThisWeek() {
        Calendar calendar = Calendar.getInstance();
        calendar.setFirstDayOfWeek(Calendar.MONDAY);
        calendar.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
//		calendar.set(Calendar.AM_PM, Calendar.AM);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        return new Date(calendar.getTimeInMillis());
    }

    public static Date getSundayThisWeek() {
        Calendar calendar = Calendar.getInstance();
        calendar.setFirstDayOfWeek(Calendar.MONDAY);
        calendar.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
//		calendar.set(Calendar.AM_PM, Calendar.PM);
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        return new Date(calendar.getTimeInMillis());
    }

    public static Date getMondayThisWeek(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.setFirstDayOfWeek(Calendar.MONDAY);
        calendar.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
//		calendar.set(Calendar.AM_PM, Calendar.AM);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        return new Date(calendar.getTimeInMillis());
    }

    public static Date getSundayThisWeek(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.setFirstDayOfWeek(Calendar.MONDAY);
        calendar.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
//		calendar.set(Calendar.AM_PM, Calendar.PM);
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        return new Date(calendar.getTimeInMillis());
    }

    public static Date getSundayAnyWeek(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.setFirstDayOfWeek(Calendar.MONDAY);
        calendar.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
//        calendar.set(Calendar.AM_PM, Calendar.PM);
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        return new Date(calendar.getTimeInMillis());
    }

    public static Date getFridayThisWeek(int hour, int minute) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_WEEK, Calendar.FRIDAY);
        calendar.set(Calendar.AM_PM, Calendar.AM);
        calendar.set(Calendar.HOUR_OF_DAY, hour);
        calendar.set(Calendar.MINUTE, minute);
        calendar.set(Calendar.SECOND, 59);
        return new Date(calendar.getTimeInMillis());
    }

    public static Date getDateInWeekLockMeeting(Date date, int hour, int minute, int day) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.setFirstDayOfWeek(Calendar.MONDAY);
        calendar.set(Calendar.DAY_OF_WEEK, day);
        calendar.set(Calendar.AM_PM, Calendar.PM);
        calendar.set(Calendar.HOUR_OF_DAY, hour);
        calendar.set(Calendar.MINUTE, minute);
        calendar.set(Calendar.SECOND, 59);
        return new Date(calendar.getTimeInMillis());
    }

    /**
     * Get start day of this week
     *
     * @return
     */
    public static Date getStartDayOfWeek() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
        calendar.set(Calendar.AM_PM, Calendar.AM);
        calendar.set(Calendar.HOUR, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        return new Date(calendar.getTimeInMillis());
    }

    /**
     * Get last day of this week
     *
     * @return
     */
    public static Date getLastDayOfWeek() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_WEEK, Calendar.SATURDAY);
        calendar.set(Calendar.AM_PM, Calendar.PM);
        calendar.set(Calendar.HOUR, 11);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        return new Date(calendar.getTimeInMillis());
    }

    /**
     * Check date in week
     *
     * @param date
     * @return
     */
    public static boolean inWeek(Date date) {
        if (date == null) {
            return false;
        }
        Date startDay = getMondayThisWeek();
        Date lastDay = getSundayThisWeek();
        if (startDay.after(date)) {
            return false;
        }
        if (lastDay.before(date)) {
            return false;
        }
        return true;
    }

    public static Long getTime(Date date) {
        Calendar cal = Calendar.getInstance(); // locale-specific
        cal.setTime(date);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTimeInMillis();
    }

    public static Date truncDate(Date date) {
        if (date == null) {
            return null;
        }
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.set(Calendar.HOUR_OF_DAY, 0);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);
        c.set(Calendar.MILLISECOND, 0);
        return c.getTime();
    }

    public static Date addingDate(Date date, int addDate) {
        if (date == null) {
            return null;
        }
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.DATE, addDate);
        return c.getTime();
    }

    /**
     * Add date minute
     *
     * @param date
     * @param hour
     * @param min
     * @return
     */
    public static Date addHourMinute(Date date, int hour, int min) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.HOUR_OF_DAY, hour);
        c.add(Calendar.MINUTE, min);
        return c.getTime();
    }

    /**
     * Create Date Hourse Minute
     *
     * @author khanhnn12
     * @param date
     * @param hour
     * @param minute
     * @return
     */
    public static Date createDateOfHoursMinute(Date date, int hour, int minute) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.set(Calendar.HOUR_OF_DAY, hour);
        c.set(Calendar.MINUTE, minute);
        c.set(Calendar.SECOND, 0);
        return c.getTime();
    }

    /**
     * get displayName WeekDays this week
     *
     * @author khanhnn12
     * @return
     */
    public static String getWeekDaysThisWeek(Date date) {
        String dayNames[] = new DateFormatSymbols().getWeekdays();
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        String results = dayNames[c.get(Calendar.DAY_OF_WEEK)];
        return results;
    }

    // tridv
    /**
     * Ham lay time kieu string dau vao la kieu string
     *
     * @param dateFormat
     * @param date
     * @param timezone
     * @return
     */
    /**
     * Ham lay time kieu Date dau vao la kieu string
     *
     * @param dateFormat
     * @param date
     * @param timezone
     * @return
     */
    /**
     * Ham lay time kieu string dau vao la kieu Date
     *
     * @param dateFormat
     * @param date
     * @param timezone
     * @return
     */
    /**
     * Ham lay time kieu Date dau vao la kieu Date
     *
     * @param dateFormat
     * @param date
     * @param timezone
     * @return
     */
    /**
     * Ham chuyen doi timezone cua nguoi dung
     *
     * @param timezone
     * @return
     */
    public static Date getStartTimeOfDate(Date date) throws Exception {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTime();
    }

    public static Date getEndTimeOfDate(Date date) throws Exception {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        calendar.set(Calendar.MILLISECOND, 999);
        return calendar.getTime();
    }

    /**
     *
     * @param utcTime
     * @return
     */
    public static Date convertUtcToLocalTime(String utcTime) {
        Date result = null;

        if ((utcTime != null) && (!"null".equals(utcTime))) {
            final DateFormat utcFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
            utcFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
            try {
                result = utcFormat.parse(utcTime);
            } catch (ParseException ex) {
                logger.error(ex);
            }
        }
        return result;
    }

    /**
     *
     * @param localTime
     * @return
     */
    public static String convertLocalToString(Date localTime) {
        String result = null;
        if (localTime != null) {
            final SimpleDateFormat localFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS");
            result = localFormat.format(localTime.getTime());
        }

        return result;
    }

    public static String convertDateTimeToString(Date localTime, String pattern) {
        String result = null;
        if (localTime != null) {
            final SimpleDateFormat localFormat = new SimpleDateFormat(pattern);
            result = localFormat.format(localTime.getTime());
        }

        return result;
    }

    public static String convertDTMToStandardStr(Date localTime) {
        return convertDateTimeToString(localTime, "dd/MM/yyyy HH:mm:ss");
    }

    /**
     *
     * @param input
     * @param nDay
     * @return
     */
    public static Date getNextNDayOfDate(Date input, Long nDay) {
        Calendar objCal = Calendar.getInstance();
        objCal.setTime(input);
        // objCal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
        objCal.add(Calendar.DAY_OF_MONTH, nDay.intValue());
        return objCal.getTime();
    }

    public static Date getMondayNextWeek() {
        Calendar now = Calendar.getInstance();
        int weekday = now.get(Calendar.DAY_OF_WEEK);
        if (weekday != Calendar.MONDAY) {
            // calculate how much to add
            // the 2 is the difference between Saturday and Monday
            int days = (Calendar.SATURDAY - weekday + 2) % 7;
            now.add(Calendar.DAY_OF_YEAR, days);
            now.set(Calendar.HOUR, 0);
            now.set(Calendar.MINUTE, 0);
            now.set(Calendar.SECOND, 0);
            now.set(Calendar.MILLISECOND, 0);

        }
        // now is the date you want
        return now.getTime();
    }

    public static long compareDateTime(Date date1, Date date2) {
        Long minute1 = (long) date1.getTime();
        Long minute2 = (long) date2.getTime();
        return (minute2 - minute1);
    }

    public static String getDayName(Date date, Locale locale) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        if (locale != null && locale.equals(CustomLocale.VIETNAME)) {
            return DAYS_NAME_OF_WEEK_VN[calendar.get(Calendar.DAY_OF_WEEK)];
        } else {
            DateFormatSymbols dfs = new DateFormatSymbols(locale);
            String[] weekdays = dfs.getWeekdays();
            return weekdays[calendar.get(Calendar.DAY_OF_WEEK)];
        }
    }

    public static String getShortDayName(Date date, Locale locale) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        if (locale != null && locale.equals(CustomLocale.VIETNAME)) {
            return DAYS_NAME_OF_WEEK_VN_SHORT[calendar
                    .get(Calendar.DAY_OF_WEEK)];
        } else {
            DateFormatSymbols dfs = new DateFormatSymbols(locale);
            String[] weekdays = dfs.getShortWeekdays();
            return weekdays[calendar.get(Calendar.DAY_OF_WEEK)];
        }
    }

}
