package com.yomursin.scrum.web.endpoint;

import org.apache.tomcat.util.http.fileupload.FileItem;
import org.apache.tomcat.util.http.fileupload.FileUploadException;
import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.apache.tomcat.util.http.fileupload.disk.DiskFileItemFactory;
import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;
import org.apache.tomcat.util.http.fileupload.servlet.ServletRequestContext;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

/**
 * @author Bing D. Yee
 * @since 2021/09/10
 */
@RestController
@RequestMapping("/v1/uploader")
public class FileServerController {

    @PostMapping
    public void upload(HttpServletRequest request, HttpServletResponse response) {
        response.setCharacterEncoding("utf-8");
        Integer chunk = null, chunks = null;
        String name = null, localPath = "/Users/bingdyee/Downloads/";
        BufferedOutputStream bos = null;
        try {
            DiskFileItemFactory factory = new DiskFileItemFactory();
            factory.setSizeThreshold(1024);
            factory.setRepository(new File(localPath));
            ServletFileUpload fileUpload = new ServletFileUpload(factory);
            fileUpload.setFileSizeMax(10L * 1024);
            fileUpload.setSizeMax(10L*1024*1024*1024);
            List<FileItem> fileItems = fileUpload.parseRequest(new ServletRequestContext(request));
            for(FileItem fileItem : fileItems) {
                if (fileItem.isFormField()) {
                    if ("chunk".equals(fileItem.getFieldName())) {
                        chunk = Integer.parseInt(fileItem.getString("utf-8"));
                    }
                    if ("chunks".equals(fileItem.getFieldName())) {
                        chunks = Integer.parseInt(fileItem.getString("utf-8"));
                    }
                    if ("name".equals(fileItem.getFieldName())) {
                        name = fileItem.getString("utf-8");
                    }
                }
            }
            for (FileItem fileItem : fileItems) {
                if (!fileItem.isFormField()) {
                    String tmpFileName = name;
                    if (name != null) {
                        if (chunk != null) {
                            tmpFileName = chunk + "_" + name;
                        }
                        File tmpFile = new File(localPath, tmpFileName);
                        if (!tmpFile.exists()) {
                            fileItem.write(tmpFile);
                        }
                    }
                }
            }
            if (chunk != null && chunk == chunks.intValue() - 1) {
                File tmpFile = new File(localPath, name);
                bos = new BufferedOutputStream(new FileOutputStream(tmpFile));
                for (int i = 0; i < chunks; ++i) {
                    File file = new File(localPath, i + "_" + name);
                    while (!file.exists()) {
                        Thread.sleep(100);
                    }
                    byte[] bytes = Files.readAllBytes(Paths.get(i + "_" + name));
                    bos.write(bytes);
                    bos.flush();
                    file.delete();
                }
                bos.flush();
            }
            response.getWriter().write("上传成功" + name);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (bos != null) {
                try {
                    bos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
