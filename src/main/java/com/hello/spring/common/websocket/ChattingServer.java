package com.hello.spring.common.websocket;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import lombok.extern.slf4j.Slf4j;


//웹소켓요청을 처리하는 클래스로 등록하려면 springwebsocket에서 제공하는 handler를 상속받아야한다.
// --> TextWebSocketHandler 클래스
// TextWebSocketHandler가 가지고 있는 메소드를 구현!
@Slf4j
public class ChattingServer extends TextWebSocketHandler{

	
	private List<WebSocketSession> clients=new ArrayList();
	//접속될 때마다 세션을 리스트에 넣어줌
	
	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		//session에 각종 정보를 받아올 수 있음
		//프론트에서 websocket객체를 생성했을 때 바로 실행이 됨
		log.debug("{}", session.getId());
		//session.sendMessage(null);
		clients.add(session);
	
	}

	@Override
	protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
		
		log.debug(message.getPayload());//session보낸 데이터 출력
		//jackson을 이용해서 메세지 클래스로 관리하기
		session.getAttributes().put("data", message.getPayload());
		
		session.sendMessage(message);
		for(WebSocketSession client:clients) {
			if(client.isOpen()&&!client.equals(session)) {
				client.sendMessage(message);
			}
		}
		
	}

	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
		// TODO Auto-generated method stub
		super.afterConnectionClosed(session, status);
	}
	
	

}
