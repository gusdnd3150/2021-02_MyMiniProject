package com.example.demo.file;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.example.demo.vo.MediaVo;
import com.example.demo.vo.PortfolioFileVo;
import com.example.demo.vo.UserVo;


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
							 imagePath = "/companyLogo/"; 
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
	
	
	// 마이페이지 파일 업로드
	public PortfolioFileVo uploadUserFile(MultipartHttpServletRequest upfile,HttpServletRequest request) {
		PortfolioFileVo fileVo = new PortfolioFileVo();
		UserVo user = (UserVo) request.getSession().getAttribute("USER");
		
		fileVo.setId(user.getId());
		MultipartFile imageFile = upfile.getFile("pofolFile"); 
		
		try {
			
			String imagePath = "/userPofol/"; 
			String path = request.getSession().getServletContext().getRealPath("/");// locallhost8080/
			String savePath = path + imagePath;   
	        
			File file = new File(savePath);    

	        if(file.exists() == false){
	            file.mkdirs(); 
	        }
		        originalFileName = imageFile.getOriginalFilename();
		        originalFileExtension = imageFile.getOriginalFilename().substring(originalFileName.lastIndexOf("."));
		        storedFileName = getRandomString() + originalFileExtension;   //위에 랜덤값을 뽑아주는 매소드 + 

				file = new File(savePath + storedFileName);
		        System.out.println(file.getAbsolutePath()); 
		        imageFile.transferTo(file); 
		        
		        fileVo.setFile_original_name(originalFileName);
		        fileVo.setFile_saved_name(storedFileName);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		return fileVo;
	}
	
	
	public void deleteFile(PortfolioFileVo fileVo,HttpServletRequest request) {
		
		String filePath = "/userPofol/"; 
		String path = request.getSession().getServletContext().getRealPath("/");// locallhost8080/
		String savePath = path + filePath;
		
		File deleteFolder = new File(savePath);
		File[] folder =deleteFolder.listFiles();
		
		for(int i=0;i<folder.length ;i++) {
			if(folder[i].getName().equals(fileVo.getFile_saved_name())) {
				folder[i].delete();
			}
		}
	}
	
	//파일다운로드
	public void downloadFile(PortfolioFileVo fileVo,HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		OutputStream out =response.getOutputStream();
		
		String filePath = "/userPofol/"; 
		String path = request.getSession().getServletContext().getRealPath("/");// locallhost8080/
		String downLoadPath = path + filePath+ fileVo.getFile_saved_name();
		
		File file = new File(downLoadPath);
		
		String name = new String(fileVo.getFile_original_name().getBytes("UTF-8"),"ISO-8859-1");
		
		response.setContentType("application/ISO-8859-1; charset=UTF-8");
		response.setHeader("Cache-Control", "no-cache");
		response.setHeader("Content-Disposition", "attachment;filename=\""+name+"\";");
		
		FileInputStream in = new FileInputStream(file);
		
		byte[] buffer = new byte[1024 * 8];    
		while (true) {
			int count = in.read(buffer); // 버퍼에 읽어들인 문자개수
			if (count == -1) // 버퍼의 마지막에 도달했는지 체크
				break;
			out.write(buffer, 0, count);
		}
		in.close();
		out.close();
		
		
	}

	
	public MediaVo insertMediaFileIntro(MultipartHttpServletRequest upfile,HttpServletRequest request) {
		MultipartFile mediaFile = upfile.getFile("mediaIntro"); 
		MediaVo media = new MediaVo();
		
		try {
			String mediaPath = "/userMedia/"; 
			String path = request.getSession().getServletContext().getRealPath("/");
			String savePath = path + mediaPath;   
	        
			File file = new File(savePath);    

	        if(file.exists() == false){
	            file.mkdirs(); 
	        }
		        originalFileName = mediaFile.getOriginalFilename();
		        originalFileExtension = mediaFile.getOriginalFilename().substring(originalFileName.lastIndexOf("."));
		        storedFileName = getRandomString() + originalFileExtension;   

				file = new File(savePath + storedFileName);
		        System.out.println(file.getAbsolutePath()); 
		        mediaFile.transferTo(file); 
		        
		        media.setMedia_original(originalFileExtension);
		        media.setMedia_saved(storedFileName);
		        
		        
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		return media;
	}
}
