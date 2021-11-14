/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import com.tav.common.web.base.BaseController;
import com.tav.service.dto.ServiceResult;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;

/**
 *
 * @author KhietDT
 */
public class SubBaseController extends BaseController {

    // TODO: fix tạm url vào constant, khi có time thì sửa cho vào file properties
    public final String REPORT_IMPORT_PATH = "/upload/files/import/";
    public final String REPORT_TEMPLATE_PATH = "/upload/files/template/";
    public final String REPORT_EXPORT_PATH = "/upload/files/export/";

    protected void processServiceResult(ServiceResult serviceResult) {
        String errorType = serviceResult.getErrorType();

      
    }

    protected void addEventLog(String action, String description) {
       
    }

   
    /**
     * check authority with object
     *
     * @author duynn
     * @since 28/06/2017
     * @param objectName
     * @return
     */

    /**
     * checkAuthority user settlement
     *
     * @author : LienPTK
     * @param idSettlements id of settlement
     * @return boolean
     */

    /**
     * @description : rewrite check can access to settlemnt by user
     * @author duynn
     * @date : 17/7/2017
     * @param settlementId
     * @return
     */
    /**
     * get absolute url from context path
     *
     * @param contextPath String of contextPath
     * @return String of absolute url
     */
    public String getAbsoluteUrl(String contextPath) {
        HttpServletRequest request = getRequest();
        String folderDir = ((HttpServletRequest) request).getSession()
                .getServletContext().getRealPath(contextPath) + "/";
        return folderDir;
    }

    protected String getRemoteIpAddress(HttpServletRequest request) {
        String ipAddress = request.getHeader("X-FORWARDED-FOR");

        if (ipAddress == null) {
            ipAddress = request.getRemoteAddr();
        }

        return ipAddress;
    }

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
    }

}
