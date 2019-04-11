package com.riverplant.expertus.defi.controller;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.riverplant.expertus.defi.dto.FileInfo;

/**
 * 
 * @author riverplant
 *
 */
@RestController
@RequestMapping("/file")
public class FileController {

	@PostMapping("/upload")
	public FileInfo upload(MultipartFile file) throws IllegalStateException, IOException {
		System.out.println(file.getOriginalFilename());
		File output = new File("./testFile2.txt");
		if (output.exists())
			output.delete();
		file.transferTo(output);
		return new FileInfo(output.getAbsolutePath());

	}

	@GetMapping("/download")
	public void download(HttpServletRequest request, HttpServletResponse response)
			throws FileNotFoundException, IOException {
           String filePath = "J:\\repository\\expertusDefi\\testFile2.txt";
           File f = new File(filePath);
		try (InputStream inputStream = new FileInputStream(filePath);
				OutputStream outputStream = response.getOutputStream();) {
			response.setContentType("application/x-download");
			response.addHeader("Content-Disposition", "attachment;filename="+f.getName());
			IOUtils.copy(inputStream, outputStream);
			outputStream.flush();
			
		}

	}

}
