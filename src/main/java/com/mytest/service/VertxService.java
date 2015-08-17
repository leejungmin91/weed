package com.mytest.service;

import java.util.Calendar;
import java.util.GregorianCalendar;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.vertx.java.core.Handler;
import org.vertx.java.core.Vertx;
import org.vertx.java.core.http.HttpServer;
import org.vertx.java.core.json.JsonObject;

import com.mytest.DAO.ChatDAOImpl;
import com.mytest.DAO.DefaultEmbeddableVerticle;
import com.mytest.DTO.Chat;
import com.nhncorp.mods.socket.io.SocketIOServer;
import com.nhncorp.mods.socket.io.SocketIOSocket;
import com.nhncorp.mods.socket.io.impl.DefaultSocketIOServer;

public class VertxService extends DefaultEmbeddableVerticle {

	private static SocketIOServer io = null;
	private static final Logger logger = LoggerFactory
			.getLogger(VertxService.class);
	//private GregorianCalendar calendar = new GregorianCalendar();
	@Autowired
	private ChatDAOImpl chatDAOImpl;

	@Override
	public void start(Vertx vertx) {

		int port = 8888;

		/*
		 * int year = calendar.get(Calendar.YEAR); // 연도를 리턴 int month =
		 * calendar.get(Calendar.MONTH);// 월을 리턴 int date =
		 * calendar.get(Calendar.DATE);// 일을 리턴 int amPm =
		 * calendar.get(Calendar.AM_PM);// 오전/오후구분을 리턴 int hour =
		 * calendar.get(Calendar.HOUR);// 시를 리턴 int min =
		 * calendar.get(Calendar.MINUTE);// 분을 리턴 int sec =
		 * calendar.get(Calendar.SECOND);// 초를 리턴 String sAmPm = amPm ==
		 * Calendar.AM ? "오전" : "오후"; final String Reg_Date =
		 * year+"년 "+month+"월 "+date+"일 "+sAmPm+" "+hour+"시 "+min+"분 "+sec+"초 ";
		 */

		final Chat chat = new Chat();
		HttpServer server = vertx.createHttpServer();

		io = new DefaultSocketIOServer(vertx, server);

		io.sockets().onConnection(new Handler<SocketIOSocket>() {
			// 소켓 연결

			public void handle(final SocketIOSocket socket) {
				socket.on("join", new Handler<JsonObject>() {

					public void handle(JsonObject event) {
						logger.info("chat handler roomName::: "
								+ event.getString("roomname"));
						logger.info("chat handler chatDAOImpl::: "
								+ chatDAOImpl);

						// chatDAOImpl.insert(chat);
						// io.sockets().emit("join", event);

					}

				});

				socket.on("msg", new Handler<JsonObject>() {
					public void handle(JsonObject event) {

						logger.info("chat handler ::: "
								+ event.getString("msg"));
						chat.setChatRoomName(event.getString("roomname"));
						chat.setChatUserName(event.getString("username"));
						chat.setChatReg_Data(event.getString("reg_date_total"));
						chat.setChatContent(event.getString("msg"));
						chat.setChatRoomPK(event.getString("roomPK"));
						chatDAOImpl.insert(chat);
						io.sockets().emit("connection", event);

					}

				});

			}

		});
		server.listen(port);

	}

}