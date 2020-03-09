package com.boot.dubbo.utils;

import com.alibaba.fastjson.JSONObject;
import org.apache.dubbo.common.utils.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@PropertySource(value = {"classpath:application.properties"})
public class BaseTool {
    @Autowired
    private static Environment env;

    /**
     * 开发日志开关
     */
//    private static final String PRINT_DEV_LOG = env.getProperty("print.dev.log");

    final static String sessionKey = "session_";

    /**
     * 返回模板
     * @param result
     * @param object
     * @param errorCode
     * @param message
     * @return
     */
    public static JSONObject __getBaseJson(boolean result, Object object, String errorCode, String message) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("result", result);
        jsonObject.put("data", object == null ? "" : object);
        jsonObject.put("errorCode", errorCode == null ? "" : errorCode);
        jsonObject.put("message", message == null ? "" : message);
        return jsonObject;
    }

    private static JSONObject getBaseJson(boolean result, Object object, String errorCode, String message) {
        JSONObject jsonObject = __getBaseJson(result, object, errorCode, message);
        return jsonObject;
    }

    private static JSONObject getBaseJsonList(boolean result, List<Object> objectList, String errorCode, String message) {
        JSONObject jsonObject = __getBaseJson(result, objectList, errorCode, message);
        return jsonObject;
    }

    public static JSONObject buildSuccessResult() {
        return getBaseJson(true, null, null, null);
    }

    public static JSONObject buildSuccessResult(Object object) {
        return getBaseJson(true, object, null, null);
    }

    public static JSONObject buildSuccessResult(List<Object> objectList) {
        return getBaseJsonList(true, objectList, null, null);
    }

    public static JSONObject buildFailedResult(String message) {
        return getBaseJson(false, null, null, message);
    }

    public static JSONObject buildFailedResult(String errorCode, String message) {
        return getBaseJson(false, null, errorCode, message);
    }

    /**
     *
     * @param values
     * @param index
     * @return
     */
    public static String valueToString(List<Object> values, int index) {
        if (CollectionUtils.isEmpty(values)) {
            return null;
        }
        if (values.size() - 1 < index) {
            return null;
        }
        Object obj = values.get(index);
        if (obj != null) {
            return String.valueOf(obj);
        }
        return null;
    }

    public static String valueToString(Object obj) {
        if (obj != null) {
            return String.valueOf(obj);
        }
        return "";
    }

    public static Integer valueToInteger(List<Object> values, int index) {
        if (CollectionUtils.isEmpty(values)) {
            return null;
        }
        if (values.size() - 1 < index) {
            return null;
        }
        Object obj = values.get(index);
        if (obj != null) {
            return Integer.parseInt(obj.toString());
        }
        return null;
    }

    public static String valueToDate(Object obj, String type) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(type);
        if (obj != null) {
            return dateFormat.format(obj);
        }
        return null;
    }

    public static String getDate(String startDate, String type) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
        Calendar calendar = Calendar.getInstance();
        Date date = null;
        try {
            date = dateFormat.parse(startDate);
            calendar.setTime(date);
            if ("0".equals(type)) {
                calendar.add(Calendar.WEEK_OF_YEAR, -1);
            } else if ("1".equals(type)) {
                calendar.add(Calendar.MONTH, -1);
            } else if ("2".equals(type)) {
                calendar.add(Calendar.MONTH, -3);
            } else if ("3".equals(type)) {
                calendar.add(Calendar.YEAR, -1);
            }
            String newDate = dateFormat.format(calendar.getTime());
            return newDate;
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static long getBetweenDays(Date news, Date old) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            old = sdf.parse(sdf.format(old));
            news = sdf.parse(sdf.format(news));
            Calendar cal = Calendar.getInstance();
            cal.setTime(old);
            long time1 = cal.getTimeInMillis();
            cal.setTime(news);
            long time2 = cal.getTimeInMillis();
            long between_days = (time2 - time1) / (1000 * 3600 * 24);
            return between_days;
        } catch (ParseException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public static String getStringDate(Date startDate, String type, Integer num) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(type);
        Calendar calendar = Calendar.getInstance();
        try {
            calendar.setTime(new Date());
            calendar.add(Calendar.DATE, num);
            String newDate = dateFormat.format(calendar.getTime());
            return newDate;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
    /**
     * 获取UUID
     */
    public static String getUUID() {
        return UUID.randomUUID().toString().replace("-", "");
    }

    /**
     * 获取时间戳
     */
    public static Long createTimestamp() {
        return System.currentTimeMillis() / 1000;
    }

    public static String getUtf8(String arg) {
        String value = null;
        try {
            value = new String(arg.getBytes("ISO8859-1"), "utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return value;
    }
}
