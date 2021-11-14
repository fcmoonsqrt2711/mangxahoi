package Controller;

import java.text.ParseException;
import javax.ws.rs.POST;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

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

    @RequestMapping(value = {"/upload1"}, method = RequestMethod.POST, produces = "text/html;charset=utf-8")
    public String addOBJ(@RequestParam("file") MultipartFile file) throws ParseException {
        String rs = null;
        System.out.println("aaaaaaaaaa day r");
        return rs;
    }
    
}
