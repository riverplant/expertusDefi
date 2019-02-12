package com.example.demo.controller;
import java.io.File;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
/**
 * 文件上传下载
 * 
 * @author riverplant
 *spring.http.multipart.enable=true
 *spring.http.multipart.file-size-threshold=0
 *spring.http.multipart.location=/tmp //该临时目录在文件上传成功后会删除
 *spring.http.multipart.max-file-size=1Mb
 *spring.http.multipart.max-request-size=10Mb
 */
@Controller
@RequestMapping(value = "/file")
public class FileController {
	private static final Logger logger = LoggerFactory.getLogger(FileController.class);

	@RequestMapping(value = "/file")
	@ResponseBody
	public String upload(@RequestParam("riverFile") MultipartFile file) {
		if (file.isEmpty())
			return "文件为空";
		// 获取文件名
		String fileName = file.getOriginalFilename();
		logger.info("上传文件名为:" + fileName);
		// 获取后缀名,uuid+suffixFileName 在linux里使用
		String suffixFileName = fileName.substring(fileName.lastIndexOf("."));
		logger.info("上传文件后缀名为:" + suffixFileName);

		// 文件上传路径
		String root = "/river/file/";

		File des = new File(root + fileName);
		if (!des.getParentFile().exists())
			des.getParentFile().mkdirs();

		try {
			file.transferTo(des);
			return"上传成功";
		} catch (Exception e) {
			e.printStackTrace();
		}
		return"上传失败";
	}

}
