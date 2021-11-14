/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tav.service.common;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.apache.commons.io.FilenameUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import java.text.Normalizer;

/**
 *
 * @author DELL
 */
public class CommonFunction {

    public static String convertFileNameVietNam(String str) {
        str = Normalizer.normalize(str, Normalizer.Form.NFD);
        str = str.replaceAll("[^\\p{ASCII}]", "");
        str = str.replaceAll("\\p{M}", "");
        str = str.replaceAll(" ", "_");
        str = str.replaceAll(",", "_");
        str = str.replaceAll("-", "_");
        return str;
//        try {
//            String temp = Normalizer.normalize(str, Normalizer.Form.NFD);
//            Pattern pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");
//            return pattern.matcher(temp).replaceAll("").toLowerCase().replaceAll(" ", "-").replaceAll("đ", "d");
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        }
//        return "";
    }

    public static String uploadFileOnAdd(MultipartHttpServletRequest multipartRequest, String fileForm) {
        String rs = null;
        String dataDirectory = CommonConstant.PATH_FOLDER_TOPIC_FILE;
        MultipartFile fileExtend = multipartRequest.getFile(fileForm);
        File fileTemp = new File(dataDirectory + fileExtend.getOriginalFilename());
        String extension = FilenameUtils.getExtension(fileExtend.getOriginalFilename());
        if (fileTemp.exists()) {
            SimpleDateFormat smp = new SimpleDateFormat("dd-MM-yyyy HHmmss");
            String name = FilenameUtils.removeExtension(fileExtend.getOriginalFilename()) + smp.format(new Date()) + "_" + System.currentTimeMillis();
            fileTemp = new File(dataDirectory + name + "." + extension);
        }
        if (!StringUtil.isBlank(fileExtend.getOriginalFilename())) {
            try (BufferedOutputStream streamOut = new BufferedOutputStream(
                    new FileOutputStream(fileTemp))) {
                rs = fileTemp.getName();
                streamOut.write(fileExtend.getBytes());
                streamOut.close();
            } catch (IOException ex) {
                rs = null;
            }
        }
        return rs;
    }

    public static String uploadFileOnAddNonSpace(MultipartHttpServletRequest multipartRequest, String fileForm) {
        String rs = null;
        String dataDirectory = CommonConstant.PATH_FOLDER_TOPIC_FILE;
        MultipartFile fileExtend = multipartRequest.getFile(fileForm);
        File fileTemp = new File(dataDirectory + fileExtend.getOriginalFilename());
        String extension = FilenameUtils.getExtension(fileExtend.getOriginalFilename());
        if (fileTemp.exists()) {
            SimpleDateFormat smp = new SimpleDateFormat("dd-MM-yyyyHHmmss");
            String name = FilenameUtils.removeExtension(fileExtend.getOriginalFilename()) + smp.format(new Date()) + "_" + System.currentTimeMillis();
            fileTemp = new File(dataDirectory + name + "." + extension);
        }
        if (!StringUtil.isBlank(fileExtend.getOriginalFilename())) {
            try (BufferedOutputStream streamOut = new BufferedOutputStream(
                    new FileOutputStream(fileTemp))) {
                rs = fileTemp.getName();
                streamOut.write(fileExtend.getBytes());
                streamOut.close();
            } catch (IOException ex) {
                rs = null;
            }
        }
        return rs;
    }

    public static String uploadFileOnUpdate(MultipartHttpServletRequest multipartRequest, String fileForm) {
        String rs = null;
        String dataDirectory = CommonConstant.PATH_FOLDER_TOPIC_FILE;
        MultipartFile fileExtend = multipartRequest.getFile(fileForm);
        File fileTemp = new File(dataDirectory + fileExtend.getOriginalFilename());
        String extension = FilenameUtils.getExtension(fileExtend.getOriginalFilename());
        if (fileTemp.exists()) {
            SimpleDateFormat smp = new SimpleDateFormat("dd-MM-yyyy HHmmss");
            String name = FilenameUtils.removeExtension(fileExtend.getOriginalFilename()) + smp.format(new Date()) + "_" + System.currentTimeMillis();
            fileTemp = new File(dataDirectory + name + "." + extension);
        }
        if (!StringUtil.isBlank(fileExtend.getOriginalFilename())) {
            try (BufferedOutputStream streamOut = new BufferedOutputStream(
                    new FileOutputStream(fileTemp))) {
                rs = fileTemp.getName();
                streamOut.write(fileExtend.getBytes());
                streamOut.close();
            } catch (IOException ex) {
                rs = "";
            }
        } else {
            rs = "";
        }
        return rs;
    }

}
