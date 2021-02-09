package com.example.demo.service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.vo.SeoulJobInfoVo;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

@Service
public class ApiService {
	
	
	
	//서울시 일자리 정보 api
	public List<SeoulJobInfoVo> getSeoulList()   {
		List<SeoulJobInfoVo> list= new ArrayList<SeoulJobInfoVo>();
		int list_total_count =0;
		
		String KEY ="72566f5866677573383248534c5261";
		String TYPE="json";
		String START_INDEX="1";
		String END_INDEX="18";
		
		String requestURL ="http://openapi.seoul.go.kr:8088/"+KEY+"/"+TYPE+"/GetJobInfo/"+START_INDEX+"/"+END_INDEX+"/";	
		//   http://openapi.seoul.go.kr:8088/72566f5866677573383248534c5261/json/GetJobInfo/1/5/
		
		String requestString = "";
		
		
		
		try{
			
			URL url = new URL(requestURL);    //요청을 처리할 주소
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setDoOutput(true); 				
			connection.setInstanceFollowRedirects(false);  
			connection.setRequestMethod("GET");  //요청
			connection.setRequestProperty("Content-Type", "application/json"); //해더설정
			connection.connect();

			StringBuilder sb = new StringBuilder();
			if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
				BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream(), "utf-8"));
				String line = null;  
				while ((line = br.readLine()) != null) {  
					sb.append(line + "\n");  
				}
				br.close();
				requestString = sb.toString();     //전달된 값을 String이다
			}
			connection.disconnect(); //끝
			
			JsonParser jsonParser = new JsonParser();
			JsonObject jsonObj = (JsonObject) jsonParser.parse(requestString);
			JsonObject GetJobInfo = jsonObj.get("GetJobInfo").getAsJsonObject();
			list_total_count =GetJobInfo.get("list_total_count").getAsInt();
			JsonArray rows = GetJobInfo.get("row").getAsJsonArray();

			Gson gsonObj = new Gson();
			for(int i=0;i<rows.size();i++) {
				SeoulJobInfoVo seoulInfo = gsonObj.fromJson(rows.get(i).getAsJsonObject(),SeoulJobInfoVo.class);
				list.add(seoulInfo);
			}
			
		}catch(Exception e){
			e.printStackTrace();
			e.getStackTrace();
			System.out.println("서울시 api에러");
		}
		
		
		return list;

	}

}
