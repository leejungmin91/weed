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
		 * int year = calendar.get(Calendar.YEAR); // ������ ���� int month =
		 * calendar.get(Calendar.MONTH);// ���� ���� int date =
		 * calendar.get(Calendar.DATE);// ���� ���� int amPm =
		 * calendar.get(Calendar.AM_PM);// ����/���ı����� ���� int hour =
		 * calendar.get(Calendar.HOUR);// �ø� ���� int min =
		 * calendar.get(Calendar.MINUTE);// ���� ���� int sec =
		 * calendar.get(Calendar.SECOND);// �ʸ� ���� String sAmPm = amPm ==
		 * Calendar.AM ? "����" : "����"; final String Reg_Date =
		 * year+"�� "+month+"�� "+date+"�� "+sAmPm+" "+hour+"�� "+min+"�� "+sec+"�� ";
		 */

		final Chat chat = new Chat();
		HttpServer server = vertx.createHttpServer();

		io = new DefaultSocketIOServer(vertx, server);

		io.sockets().onConnection(new Handler<SocketIOSocket>() {
			// ���� ����

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