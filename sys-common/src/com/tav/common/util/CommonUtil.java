package com.tav.common.util;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Vector;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringEscapeUtils;
import org.apache.log4j.Logger;

/**
 *
 * @author truongtx5
 *
 */
public class CommonUtil {

    private static Logger logger = Logger.getLogger(CommonUtil.class);
    private static String earName = null;

    /**
     * Get local facade name
     *
     * @param localInterface
     * @return
     */
    /**
     * Check email
     *
     * @param s
     * @return
     */
    public static boolean isEmail(String s) {
        if (CommonUtil.isEmpty(s)) {
            return false;
        }
        try {
            Pattern pattern = Pattern
                    .compile("(^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$)");
            Matcher matcher = pattern.matcher(s.trim());
            if (matcher.find()) {
                return true;
            }
        } catch (Exception e) {
            logger.error(e);
        }
        return false;
    }

    public static boolean isInteger(String str) {
        if (str == null || !str.matches("[0-9]+$")) {
            return false;
        }
        return true;
    }

    public static boolean isPhoneNumber(String str) {
        return !((str != null && str.length() > 1 && str.charAt(0) != '8' && str
                .charAt(1) != '4')
                || str == null);
    }

    /**
     * QuynhNV
     *
     * @param phoneNumber
     * @return
     */
    public static boolean validatePhoneNumber(String phoneNumber) {
        if (phoneNumber != null && !phoneNumber.isEmpty()) {
            boolean isValid = false;
            String expression = "([+]?[-]?[0-9\\-]?){9,11}[0-9]$";
            CharSequence inputStr = phoneNumber;
            Pattern pattern = Pattern.compile(expression);
            Matcher matcher = pattern.matcher(inputStr);
            if (phoneNumber.length() >= 9 || phoneNumber.length() <= 11) {
                if (matcher.matches()) {
                    isValid = true;
                }
            } else {
                isValid = false;
            }
            return isValid;
        }
        return false;
    }

    public static boolean isWhiteSpace(String str) {
        Pattern pattern = Pattern.compile("\\s");
        Matcher matcher = pattern.matcher(str);
        return matcher.find();
    }

    /**
     * Check empty
     *
     * @param h
     * @return
     */
    public static boolean isEmpty(Hashtable<?, ?> h) {
        if (h == null || h.size() == 0) {
            return true;
        }
        return false;
    }

    public static boolean isEmpty(Object[] o) {
        if (o == null || o.length == 0) {
            return true;
        }
        return false;
    }

    public static boolean isEmpty(Vector<?> v) {
        if (v == null || v.size() == 0) {
            return true;
        }
        return false;
    }

    public static boolean isEmpty(String s) {
        if (s == null || "".equalsIgnoreCase(s.trim())) {
            return true;
        }
        return false;
    }

    public static boolean isEmpty(List<?> l) {
        if (l == null || l.size() == 0) {
            return true;
        }
        return false;
    }

    public static boolean isEmpty(Set<?> s) {
        if (s == null || s.size() == 0) {
            return true;
        }
        return false;
    }

    public static boolean isEmpty(Map<?, ?> m) {
        if (m == null || m.size() == 0) {
            return true;
        }
        return false;
    }

    /**
     * Check equal
     *
     * @param str1
     * @param str2
     * @return
     */
    public static boolean isEqual(String str1, String str2) {
        boolean b1 = str1 == null;
        boolean b2 = str2 == null;
        if (b1 && b2) {
            return true;
        }
        if (b1 || b2) {
            return false;
        }
        return str1.equals(str2);
    }

    /**
     * Check digit string
     *
     * @param str
     * @return
     */
    public static boolean isDigitString(String str) {
        if (CommonUtil.isEmpty(str)) {
            return false;
        }
        char[] arrChar = str.toCharArray();
        for (int i = 0; i < arrChar.length; i++) {
            if (!Character.isDigit(arrChar[i])) {
                return false;
            }
        }
        return true;
    }

    /**
     * Create random long
     *
     * @return
     */
    public static Long randomLongNumber() {

        Double d = Math.random();
        return Math.round(d * 1000000000000000L);

    }

    /**
     * Convert So sang string
     *
     * @param bigDecimal
     * @param format
     * @return
     */
    public static String formatNumber(BigDecimal bigDecimal, String format) {
        NumberFormat numberFormat = new DecimalFormat(format);
        return numberFormat.format(bigDecimal);
    }

    /**
     * Replace blank spaces
     *
     * @param s
     * @return
     */
    public static String trimBlankSpaces(String s) {
        if (s == null) {
            return null;
        }
        s = s.trim();
        s = s.replaceAll("\\s+", " ");
        return s;
    }

    /**
     * Return default value if object is null
     *
     * @param value
     * @param nullValue
     * @param notNullValue
     * @return
     */
    public static Long NVL(Long value, Long nullValue, Long notNullValue) {
        return value == null ? nullValue : notNullValue;
    }

    public static Long NVL(Long value, Long defaultValue) {
        return NVL(value, defaultValue, value);
    }

    public static Long NVL(Long value) {
        return NVL(value, 0L);
    }

    public static Number NVL(Number value, Number nullValue, Number notNullValue) {
        return value == null ? nullValue : notNullValue;
    }

    public static Number NVL(Number value, Number defaultValue) {
        return NVL(value, defaultValue, value);
    }

    public static Number NVL(Number value) {
        if (value instanceof Long) {
            return NVL(value, 0L);
        } else if (value instanceof Double) {
            return NVL(value, 0D);
        }
        return NVL(value, 0);
    }

    public static Double NVL(Double value, Double nullValue, Double notNullValue) {
        return value == null ? nullValue : notNullValue;
    }

    public static Double NVL(Double value, Double defaultValue) {
        return NVL(value, defaultValue, value);
    }

    public static Double NVL(Double value) {
        return NVL(value, new Double(0));
    }

    public static String NVL(String value, String nullValue, String notNullValue) {
        return value == null ? nullValue : notNullValue;
    }

    public static String NVL(String value, String defaultValue) {
        return NVL(value, defaultValue, value);
    }

    public static String NVL(String value) {
        return NVL(value, "");
    }

    public static Object NVL(Object value, Object nullValue, Object notNullValue) {
        return value == null ? nullValue : notNullValue;
    }

    public static Object NVL(Object value, Object defaultValue) {
        return NVL(value, defaultValue, value);
    }

    public static Integer getKeyFromMap(Map<Integer, Object[]> map,
            Object[] values) throws Exception {
        Set<Integer> keySet = map.keySet();
        for (Integer key : keySet) {
            Object[] vs = map.get(key);
            if (vs.length != values.length) {
                continue;
            }
            int length = vs.length;
            boolean valid = true;
            for (int i = 0; i < length; i++) {
                if ((vs[i] != null && values[i] == null)
                        || (vs[i] == null && values[i] != null)
                        || (vs[i] != null && !vs[i].equals(values[i]))) {
                    valid = false;
                }
            }
            if (valid) {
                return key;
            }
        }
        throw new Exception("Key not found!");
    }

    public static String getSMSMobile(String strMobile) {
        String mobile = "";
        if (strMobile != null) {
            String result = strMobile.replaceAll("[^0-9/]", "");
            String[] resultArray = result.split("/");
            if (resultArray != null && resultArray.length > 0) {
                for (int i = 0; i < resultArray.length; i++) {
                    String unit = resultArray[i];
                    if (unit.length() >= 9) {
                        String header = unit.substring(0, 2);
                        if ("00".equals(header)) {
                            mobile = unit;
                            break;
                        } else if ("09".equals(header) || "01".equals(header)) {
                            unit = unit.substring(1, unit.length());
                            mobile = "84" + unit;
                            break;
                        } else if ("84".equals(header)) {
                            mobile = unit;
                            break;
                        } else {
                            mobile = "84" + unit;
                            break;
                        }
                    }
                }
            }
        }
        return mobile;
    }

    /**
     * Escape Client Message
     *
     * @param message
     * @return
     */
    public static String escapeClientMessage(String message) {
        if (message == null) {
            return null;
        }
        return StringEscapeUtils.escapeXml(message).replace("\r\n", "<br>")
                .replace("\n", "<br>");
    }

    /**
     * Escape Return character
     *
     * @param message
     * @return
     */
    public static String escapeReturn(String message) {
        if (message == null) {
            return null;
        }
        return StringEscapeUtils.escapeXml(message).replace("\r\n", "")
                .replace("\n", "");
    }

    /**
     * Escape Javascript arguments
     *
     * @param message
     * @return
     */
    public static String escapeJsArg(String message) {
        if (message == null) {
            return null;
        }
        return message.replace("'", "\'");
    }

}
