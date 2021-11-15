package Controller;

import java.text.ParseException;
import javax.ws.rs.POST;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.core.io.Resource;

import org.springframework.http.HttpHeaders;

import org.springframework.http.MediaType;

import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

import org.springframework.web.multipart.MultipartFile;

import org.springframework.web.servlet.support.ServletUriComponentsBuilder;


import javax.servlet.http.HttpServletRequest;

import java.io.IOException;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;


@RestController
public class UserController {

    //add
//    @RequestMapping(value = {"/upload1"}, method = RequestMethod.POST, produces = "text/html;charset=utf-8")
//    @ResponseBody
//    public String addOBJ(MultipartHttpServletRequest multipartRequest,
//            HttpServletRequest request) throws ParseException {
//        String rs = null;
//        System.out.println("aaaaaaaaaa day r");
//        return rs;
//    }

    @PostMapping("/uploadFile")
    public String addOBJ(@RequestParam("file") MultipartFile file) throws ParseException {
        String rs = null;
        System.out.println("aaaaaaaaaa day r");
        return rs;
    }
    
}
