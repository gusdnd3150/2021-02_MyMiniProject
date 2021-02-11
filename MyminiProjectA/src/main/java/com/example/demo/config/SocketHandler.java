package com.example.demo.config;



import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import com.example.demo.dao.MyPageDao;
import com.example.demo.vo.SocketVO;
import com.example.demo.vo.UserVo;

@Component
public class SocketHandler extends TextWebSocketHandler{

	private Map<String, WebSocketSession> users =new ConcurrentHashMap<String, WebSocketSession>();
	 
	@Autowired
	MyPageDao dao;
	
	@Override        //서버와 연결 시
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		users.put(session.getId(), session);
		
	}
	@Override        //서버와 연결종료 시
	public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
		users.remove(session.getId());
	}
	
	@Override
	protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
		String id = message.getPayload();
		int count  =dao.selectAlramCount(Integer.parseInt(id));
		if(count > 0) {
			session.sendMessage(new TextMessage(Integer.toString(count)));
		}
	}


	
	
	
	

}
