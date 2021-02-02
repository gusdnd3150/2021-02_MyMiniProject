package com.example.demo.file;

import java.io.File;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;


@Service
public class FileService {

	private String originalFileName = null;
    private String originalFileExtension = null;
    private String storedFileName = null;

	public String addImageFile(MultipartHttpServletRequest upfile,HttpServletRequest request,String type) throws Exception {
					String result = null;
					
					
					MultipartFile imageFile = upfile.getFile("file");  //photo라는 이름으로 넘어오는 파일을 전부 받는다
					String imagePath =null;
					
					try {
						if(type.equals("user")) {
							imagePath = "/user/";
						}else {
							 imagePath = "/company/"; 
						}
					String path = request.getSession().getServletContext().getRealPath("/");// locallhost8080/
					String savePath = path + imagePath;   //
			        
					File file = new File(savePath);    //지정한 url에 대한 파일 객체를 생성한다

			        if(file.exists() == false){
			            file.mkdirs(); // 지정한 경로에 폴더가 없으면 생성한다
			        }
			        
			        	if(imageFile!=null){
			        	
				        originalFileName = imageFile.getOriginalFilename();
				        originalFileExtension = imageFile.getOriginalFilename().substring(originalFileName.lastIndexOf("."));
				        storedFileName = getRandomString() + originalFileExtension;   //위에 랜덤값을 뽑아주는 매소드 + 

						System.out.println("originFileName : " + originalFileName);
						System.out.println("originalFileExtension : " + originalFileExtension);
						System.out.println("storedFileName : " + storedFileName);

						file = new File(savePath + storedFileName);
				        System.out.println(file.getAbsolutePath()); 
				        imageFile.transferTo(file); 
				        result=storedFileName;
				        
				        
				        }else {
				        	result="emptyImage";
				        }

					} catch (Exception e) {
						e.printStackTrace();
						result="fail";
					}
					return result;
				}

	
	public String insertProfile(MultipartHttpServletRequest upfile,HttpServletRequest request) throws Exception {
		String result = null;
		HttpSession session= request.getSession();
		
		MultipartFile imageFile = upfile.getFile("profileImage");  //photo라는 이름으로 넘어오는 파일을 전부 받는다

		try {

		String imagePath = "/user/"; 
		String path = request.getSession().getServletContext().getRealPath("/");// locallhost8080/
		String savePath = path + imagePath;   //
        
		File file = new File(savePath);    //지정한 url에 대한 파일 객체를 생성한다

        if(file.exists() == false){
            file.mkdirs(); // 지정한 경로에 폴더가 없으면 생성한다
        }
        
        	if(imageFile!=null){
        	
	        originalFileName = imageFile.getOriginalFilename();
	        originalFileExtension = imageFile.getOriginalFilename().substring(originalFileName.lastIndexOf("."));
	        storedFileName = getRandomString() + originalFileExtension;   //위에 랜덤값을 뽑아주는 매소드 + 

			System.out.println("originFileName : " + originalFileName);
			System.out.println("originalFileExtension : " + originalFileExtension);
			System.out.println("storedFileName : " + storedFileName);

			file = new File(savePath + storedFileName);
	        System.out.println(file.getAbsolutePath()); 
	        imageFile.transferTo(file); 
	        result=storedFileName;
	        }else {
	        	result="emptyImage";
	        }

		} catch (Exception e) {
			e.printStackTrace();
			result="fail";
		}
		return result;
	}
	
	public static String getRandomString() { // 파일업로드 시 랜덤값을 만들어줌
		return UUID.randomUUID().toString().replaceAll("-", "");
	}

}
