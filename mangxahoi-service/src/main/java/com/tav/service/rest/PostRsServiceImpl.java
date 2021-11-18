package com.tav.service.rest;

import com.tav.service.business.PostBusinessImpl;
import com.tav.service.dto.PostDTO;
import com.tav.service.dto.SearchCommonFinalDTO;
import com.tav.service.dto.ObjectCommonSearchDTO;
import com.tav.service.dto.PostCommon;
import com.tav.service.dto.ServiceResult;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.core.Response;
import org.apache.cxf.jaxrs.ext.multipart.Attachment;
import org.apache.cxf.jaxrs.ext.multipart.Multipart;
import org.springframework.beans.factory.annotation.Autowired;

public class PostRsServiceImpl implements PostRsService {

    @Autowired
    private PostBusinessImpl postBusinessImpl;

    @Override
    public Response getAll(SearchCommonFinalDTO searchDTO, Integer offset, Integer limit) {
        List<PostCommon> lst = postBusinessImpl.getAll(searchDTO, offset, limit);
        if (lst == null) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        } else {
            return Response.ok(lst).build();
        }
    }

    @Override
    public Response getCount(SearchCommonFinalDTO searchDTO) {
        int result = postBusinessImpl.getCount(searchDTO);
        return Response.ok(result).build();
    }

    @Override
    public Response getOneById(Long id) {
        PostDTO result = postBusinessImpl.getOneObjById(id);
        return Response.ok(result).build();
    }

    @Override
    public Response deleteList(ObjectCommonSearchDTO searchDTO) {
        ServiceResult result = postBusinessImpl.deleteList(searchDTO);
        if ("FAIL".equals(result.getError())) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        } else {
            return Response.ok(result).build();
        }
    }

    @Override
    public Response updateObj(PostDTO postDTO) {
        ServiceResult result = postBusinessImpl.updateObj(postDTO);
        return Response.ok(result).build();
    }

    @Override
    public Response addDTO(PostDTO postDTO) {
        ServiceResult result = postBusinessImpl.addDTO(postDTO);
        return Response.ok(result).build();
    }

    @Override
    public Response upload(@Multipart("postId") String postId,
            @Multipart("upfile") Attachment attachment) {

        ServiceResult result = null;
        OutputStream outStream = null;
        File targetFile = new File("avatar.txt");

        if (postId != null) {

            Long post_Id = Long.parseLong(postId);
            System.out.println("post_Id  : " + post_Id);

            PostDTO postDTO = postBusinessImpl.getOneObjById(post_Id);
            postDTO.setIsAvatar(1L);
            result = postBusinessImpl.updateObj(postDTO);

            String filename = attachment.getContentDisposition().getParameter("filename");
            String[] parts = filename.split("\\.");

            String file_name_save = "post_" + post_Id + "." + parts[parts.length - 1];

            System.out.println("fileeeeeeeeeeeeeeeee   : " + file_name_save);

            java.nio.file.Path path = Paths.get(file_name_save);
            try {
                Files.deleteIfExists(path);
                InputStream in = attachment.getObject(InputStream.class);

                Files.copy(in, path);
            } catch (IOException ex) {
                Logger.getLogger(UserRsServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return Response.ok(result).build();
    }
}
