/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tav.service.common;

import com.google.common.base.Strings;
import java.text.Normalizer;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Pattern;
import org.apache.log4j.Logger;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;

/**
 *
 * @author LienPTK
 */
public class ConvertData {

    protected static final Logger LOGGER = Logger.getLogger(ConvertData.class);

    /**
     * Removes all the potentially malicious characters from a string
     *
     * @param value the raw string
     * @return the sanitized string
     */
    public static String stripXSS(String value) {
        String cleanValue = null;
        if (value != null) {
            cleanValue = Normalizer.normalize(value, Normalizer.Form.NFD);

            // Avoid null characters
            cleanValue = cleanValue.replaceAll("\0", "");

            // Avoid anything between script tags
            Pattern scriptPattern = Pattern.compile("<script>(.*?)</script>", Pattern.CASE_INSENSITIVE);
            cleanValue = scriptPattern.matcher(cleanValue).replaceAll("");

            // Avoid anything in a src='...' type of expression
            scriptPattern = Pattern.compile("src[\r\n]*=[\r\n]*\\\'(.*?)\\\'", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);
            cleanValue = scriptPattern.matcher(cleanValue).replaceAll("");

            scriptPattern = Pattern.compile("src[\r\n]*=[\r\n]*\\\"(.*?)\\\"", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);
            cleanValue = scriptPattern.matcher(cleanValue).replaceAll("");

            // Remove any lonesome </script> tag
            scriptPattern = Pattern.compile("</script>", Pattern.CASE_INSENSITIVE);
            cleanValue = scriptPattern.matcher(cleanValue).replaceAll("");

            // Remove any lonesome <script ...> tag
            scriptPattern = Pattern.compile("<script(.*?)>", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);
            cleanValue = scriptPattern.matcher(cleanValue).replaceAll("");

            // Avoid eval(...) expressions
            scriptPattern = Pattern.compile("eval\\((.*?)\\)", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);
            cleanValue = scriptPattern.matcher(cleanValue).replaceAll("");

            // Avoid expression(...) expressions
            scriptPattern = Pattern.compile("expression\\((.*?)\\)", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);
            cleanValue = scriptPattern.matcher(cleanValue).replaceAll("");

            // Avoid javascript:... expressions
            scriptPattern = Pattern.compile("javascript:", Pattern.CASE_INSENSITIVE);
            cleanValue = scriptPattern.matcher(cleanValue).replaceAll("");

            // Avoid vbscript:... expressions
            scriptPattern = Pattern.compile("vbscript:", Pattern.CASE_INSENSITIVE);
            cleanValue = scriptPattern.matcher(cleanValue).replaceAll("");

            // Avoid onload= expressions
            scriptPattern = Pattern.compile("onload(.*?)=", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);
            cleanValue = scriptPattern.matcher(cleanValue).replaceAll("");
        }
        return cleanValue;
    }
    
//    private String stripXSS2(String value) {
//        if (value != null) {
//            // NOTE: It's highly recommended to use the ESAPI library and uncomment the following line to
//            // avoid encoded attacks.
//            // value = ESAPI.encoder().canonicalize(value);
// 
//            // Avoid null characters
//            value = value.replaceAll("", "");
// 
//            // Avoid anything between script tags
//            Pattern scriptPattern = Pattern.compile("<script>(.*?)</script>", Pattern.CASE_INSENSITIVE);
//            value = scriptPattern.matcher(value).replaceAll("");
// 
//            // Avoid anything in a src='...' type of expression
//            scriptPattern = Pattern.compile("src[\r\n]*=[\r\n]*\\\'(.*?)\\\'", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);
//            value = scriptPattern.matcher(value).replaceAll("");
// 
//            scriptPattern = Pattern.compile("src[\r\n]*=[\r\n]*\\\"(.*?)\\\"", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);
//            value = scriptPattern.matcher(value).replaceAll("");
// 
//            // Remove any lonesome </script> tag
//            scriptPattern = Pattern.compile("</script>", Pattern.CASE_INSENSITIVE);
//            value = scriptPattern.matcher(value).replaceAll("");
// 
//            // Remove any lonesome <script ...> tag
//            scriptPattern = Pattern.compile("<script(.*?)>", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);
//            value = scriptPattern.matcher(value).replaceAll("");
// 
//            // Avoid eval(...) expressions
//            scriptPattern = Pattern.compile("eval\\((.*?)\\)", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);
//            value = scriptPattern.matcher(value).replaceAll("");
// 
//            // Avoid expression(...) expressions
//            scriptPattern = Pattern.compile("expression\\((.*?)\\)", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);
//            value = scriptPattern.matcher(value).replaceAll("");
// 
//            // Avoid javascript:... expressions
//            scriptPattern = Pattern.compile("javascript:", Pattern.CASE_INSENSITIVE);
//            value = scriptPattern.matcher(value).replaceAll("");
// 
//            // Avoid vbscript:... expressions
//            scriptPattern = Pattern.compile("vbscript:", Pattern.CASE_INSENSITIVE);
//            value = scriptPattern.matcher(value).replaceAll("");
// 
//            // Avoid onload= expressions
//            scriptPattern = Pattern.compile("onload(.*?)=", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);
//            value = scriptPattern.matcher(value).replaceAll("");
//        }
//        return value;
//    }

    public static List<Long> convertStringToListLong(String input) {
        List<Long> lstResult = new ArrayList<>();
        if (!Strings.isNullOrEmpty(input)) {
            String[] arrInput = input.replaceAll("\\s+", "").split(",");
            try {
                for (String item : arrInput) {
                    lstResult.add(Long.parseLong(item));
                }
            } catch (Exception ex) {
                LOGGER.error(ex.getMessage());
                return null;
            }
        }
        return lstResult;
    }

    public static Long convertToLongOrNull(String input) {
        try {
            return Long.parseLong(input);
        } catch (Exception nu) {
            LOGGER.error(nu.getMessage());
            return null;
        }
    }

    public static String getDataFromCell(DataFormatter formatter, Cell cell) {
        String data = formatter.formatCellValue(cell).trim();
        return data.length() == 0 ? null : data;
    }

    public static Double convertToDoubleOrNull(String input) {
        try {
            return Double.parseDouble(input);
        } catch (Exception ex) {
            LOGGER.error(ex.getMessage());
            return null;
        }
    }

    public static Date convertToDateUtil(String input) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        try {
            sdf.setLenient(false);
            return sdf.parse(input);
        } catch (ParseException ex) {
            LOGGER.error(ex.getMessage());
            return null;
        }
    }

    public static String convertMoneyToTextDouble(String input) {
        try {
            long rel = 0;
            if (input != null) {
                if (input.indexOf(",") >= 0 || input.indexOf(".") >= 0) {
                    double num = Double.parseDouble(input) * 1000;
                    rel = (long) (num * 1000);
                } else {
                    rel = Long.parseLong(input) * 1000000;
                }
                String text = convertMoneyToTextInteger(rel + "");
                if (text != null) {
                    if (text.length() > 0) {
                        text = text.substring(0, 1).toUpperCase() + text.substring(1);
                    }
                }
                return text + " đồng";
            } else {
                return "";
            }
        } catch (Exception ex) {
            return "";
        }

//        if (input != null) {
//            if (input.indexOf(",") >= 0 || input.indexOf(".") >= 0) {
//                System.out.println("11111111111 str " + input);
//                String intNumber = input.substring(0, input.indexOf("."));
//                String realNumber = input.substring(input.indexOf(".") + 1, input.length());
//                System.out.println("11111111111 intNumber " + intNumber);
//                System.out.println("111111111111 realNumber " + realNumber);
//
//                String text = "";
//                try {
//                    double rel = Double.parseDouble("0." + realNumber);
//                    int relInt = (int) (Math.round(rel * 1000));
//                    if ("0".equalsIgnoreCase(intNumber)) {
//                        text = convertMoneyToTextInteger(intNumber) + " triệu " + convertMoneyToTextInteger(relInt + "") + " nghìn đồng";
//                    } else {
//                        text = convertMoneyToTextInteger(relInt + "") + " nghìn đồng";
//                    }
//
//                    System.out.println(text);
//                    return text;
//                } catch (Exception ex) {
//                    return "";
//                }
//
//            } else {
//                return convertMoneyToText(input);
//            }
//        } else {
//            return "";
//        }
    }

    public static String convertMoneyToTextInteger(String input) {
        try {
            String[] so = new String[]{"không", "Một", "Hai", "Ba", "Bốn", "Năm", "Sáu", "Bảy", "Tám", "Chín"};
            String[] soDonVi = new String[]{"không", "một", "hai", "ba", "bốn", "năm", "sáu", "bảy", "tám", "chín"};
            String[] hang = new String[]{"", "nghìn", "triệu", "tỷ"};
            int i, j, donvi, chuc, tram;
            String str = " ";
            boolean booAm = false;
            double decS = convertToDoubleOrNull(input);
            if (decS < 0) {
                decS = -decS;
                input = String.valueOf(decS);
                booAm = true;
            }
            i = input.length();
            if (i == 0) {
                str = so[0] + str;
            } else {
                j = 0;
                while (i > 0) {
                    donvi = Integer.parseInt(String.valueOf(input.charAt(i - 1)));
                    i--;
                    if (i > 0) {
                        chuc = Integer.parseInt(String.valueOf(input.charAt(i - 1)));
                    } else {
                        chuc = -1;
                    }
                    i--;
                    if (i > 0) {
                        tram = Integer.parseInt(String.valueOf(input.charAt(i - 1)));
                    } else {
                        tram = -1;
                    }
                    i--;
                    if ((donvi > 0) || (chuc > 0) || (tram > 0) || (j == 3)) {
                        str = hang[j] + str;
                    }
                    j++;
                    if (j > 3) {
                        j = 1;
                    }
                    if ((donvi == 1) && (chuc > 1)) {
                        str = "một " + str;
                    } else if ((donvi == 5) && (chuc > 0)) {
                        str = "lăm " + str;
                    } else if (donvi > 0) {
                        str = soDonVi[donvi] + " " + str;
                    }
                    if (chuc < 0) {
                        break;
                    } else {
                        if ((chuc == 0) && (donvi > 0)) {
                            str = "lẻ " + str;
                        }
                        if (chuc == 1) {
                            str = "mười " + str;
                        }
                        if (chuc > 1) {
                            str = soDonVi[chuc] + " mươi " + str;
                        }
                    }
                    if (tram < 0) {
                        break;
                    } else if ((tram > 0) || (chuc > 0) || (donvi > 0)) {
                        str = soDonVi[tram] + " trăm " + str;
                    }
                    str = " " + str;
                }
            }
            if (booAm) {
                str = "Âm " + str;
            }
            str = str.substring(0, 1).toUpperCase() + str.substring(1).toLowerCase();
            return str.trim().replaceAll(" +", " ");
        } catch (Exception ex) {
            return "";
        }
    }

    public static String convertMoneyToText(String input) {
        try {
            String[] so = new String[]{"không", "Một", "Hai", "Ba", "Bốn", "Năm", "Sáu", "Bảy", "Tám", "Chín"};
            String[] soDonVi = new String[]{"không", "một", "hai", "ba", "bốn", "năm", "sáu", "bảy", "tám", "chín"};
            String[] hang = new String[]{"", "nghìn", "triệu", "tỷ"};
            int i, j, donvi, chuc, tram;
            String str = " ";
            boolean booAm = false;
            double decS = convertToDoubleOrNull(input);
            if (decS < 0) {
                decS = -decS;
                input = String.valueOf(decS);
                booAm = true;
            }
            i = input.length();
            if (i == 0) {
                str = so[0] + str;
            } else {
                j = 0;
                while (i > 0) {
                    donvi = Integer.parseInt(String.valueOf(input.charAt(i - 1)));
                    i--;
                    if (i > 0) {
                        chuc = Integer.parseInt(String.valueOf(input.charAt(i - 1)));
                    } else {
                        chuc = -1;
                    }
                    i--;
                    if (i > 0) {
                        tram = Integer.parseInt(String.valueOf(input.charAt(i - 1)));
                    } else {
                        tram = -1;
                    }
                    i--;
                    if ((donvi > 0) || (chuc > 0) || (tram > 0) || (j == 3)) {
                        str = hang[j] + str;
                    }
                    j++;
                    if (j > 3) {
                        j = 1;
                    }
                    if ((donvi == 1) && (chuc > 1)) {
                        str = "một " + str;
                    } else if ((donvi == 5) && (chuc > 0)) {
                        str = "lăm " + str;
                    } else if (donvi > 0) {
                        str = soDonVi[donvi] + " " + str;
                    }
                    if (chuc < 0) {
                        break;
                    } else {
                        if ((chuc == 0) && (donvi > 0)) {
                            str = "lẻ " + str;
                        }
                        if (chuc == 1) {
                            str = "mười " + str;
                        }
                        if (chuc > 1) {
                            str = soDonVi[chuc] + " mươi " + str;
                        }
                    }
                    if (tram < 0) {
                        break;
                    } else if ((tram > 0) || (chuc > 0) || (donvi > 0)) {
                        str = soDonVi[tram] + " trăm " + str;
                    }
                    str = " " + str;
                }
            }
            if (booAm) {
                str = "Âm " + str;
            }
            str = str.substring(0, 1).toUpperCase() + str.substring(1).toLowerCase();
            return str.trim().replaceAll(" +", " ") + " triệu đồng chẵn";
        } catch (Exception ex) {
            return "";
        }
    }

    public static String formatDouble(double d) {
        if (d == (long) d) {
            return String.format("%d", (long) d);
        } else {
            return String.format("%s", d);
        }
    }

}
