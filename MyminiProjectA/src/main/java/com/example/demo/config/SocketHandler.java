package com.example.demo.config;



import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import com.example.demo.vo.SocketVO;
import com.example.demo.vo.UserVo;

@Component
public class SocketHandler extends TextWebSocketHandler{

	private Map<String, WebSocketSession> users =new ConcurrentHashMap<String, WebSocketSession>();
	 
	@Override        //서버와 연결 시
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		users.put(session.getId(), session);
		System.out.println("연결시:"+session.getId());
		
	}
	@Override        //서버와 연결종료 시
	public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
		users.remove(session.getId());
		System.out.println("종료시:"+session.getId());
	}
	
	@Override
	protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
         Map<String,Object> test = session.getAttributes();
         
          Map<String,Object> map = session.getAttributes();
	       String name = (String) map.get("name");
	       
	       for (WebSocketSession s:users.values()) {
	    	   s.sendMessage(message);
	       }
         
	}


	
	
	
	

}
