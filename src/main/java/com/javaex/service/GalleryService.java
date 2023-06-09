package com.javaex.service;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.javaex.dao.GalleryDao;
import com.javaex.vo.GalleryVo;

@Service
public class GalleryService {
String saveDir = "C:\\javastudy\\upload";//실제 파일 저장 경로, 사용자가 파일을 업로드 했을 때 저장된다.
	
	//파일 처리
	public String restore(MultipartFile file) {//
		System.out.println("FileUploadService.restore()");
		System.out.println(file.getOriginalFilename());
		
		//원파일 이름
		String orgName = file.getOriginalFilename();//실제 저장되는 사용자가 지정한 파일의 이름 
		System.out.println("orgName: "+ orgName);
		//확장자
		String exName = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
		System.out.println("exName"+ exName);
		System.out.println("exe: "+ file.getOriginalFilename().lastIndexOf("."));
		//저장파일 이름
		String saveName = System.currentTimeMillis()+ UUID.randomUUID().toString();//UUID.randomUUID()가 실제 이미지 이름을 저장 이름으로 랜덤으로 숫자포함된 문자열로 저장.
		System.out.println("saveName"+ saveName);//리턴 형이 UUID
		
		//파일패스:실제 파일의 저장 경로
		String filePath = saveDir +"\\"+saveName;
		System.out.println("filePath:"+filePath);
		
		//파일 사이즈
		long fileSize = file.getSize();
		System.out.println("fileSize:"+ fileSize);
		
		//파일 업로드 (하드디스크에 저장)
		try {
			byte[] fileData = file.getBytes();
			OutputStream out = new FileOutputStream(filePath); 
			BufferedOutputStream bout = new BufferedOutputStream(out);
			bout.write(fileData);
			bout.close();
	
		}catch (IOException e) {
			e.printStackTrace();
		
		}
		return saveName;
	}


}
