package com.mytest.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.vertx.java.core.Handler;
import org.vertx.java.core.Vertx;
import org.vertx.java.core.http.HttpServer;
import org.vertx.java.core.impl.DefaultVertx;
import org.vertx.java.core.json.JsonObject;

import com.mytest.DAO.ChatDAOImpl;
import com.mytest.DAO.DefaultEmbeddableVerticle;
import com.mytest.DAO.FileDAOImpl;
import com.mytest.DAO.RoomDAOImpl;
import com.mytest.DAO.UserDAOImpl;
import com.mytest.DTO.ChatDTO;
import com.mytest.DTO.FileDTO;
import com.nhncorp.mods.socket.io.SocketIOServer;
import com.nhncorp.mods.socket.io.SocketIOSocket;
import com.nhncorp.mods.socket.io.impl.DefaultSocketIOServer;
import com.nhncorp.mods.socket.io.impl.Room;
import com.nhncorp.mods.socket.io.impl.RoomClient;

/**
 * @author Keesun Baik
 */
public class VertxService extends DefaultEmbeddableVerticle {
	private Vertx vertx;

	public VertxService() {
	}

	public VertxService(Vertx vertx) {
		this.vertx = vertx;
	}

	private static SocketIOServer io = null;
	private static final Logger logger = LoggerFactory
			.getLogger(VertxService.class);
	private GregorianCalendar calendar = new GregorianCalendar();
	private String socketId;
	@Autowired
	private ChatDAOImpl chatDAOImpl;

	@Autowired
	private RoomDAOImpl roomDAOImpl;

	@Autowired
	private UserDAOImpl userDAOImpl;

	@Autowired
	private FileDAOImpl fileDAOImpl;

	private List<Room> getrooms;
	private List<Map> rooms;
	private long index;

	@Override
	public void start(Vertx vertx) {
		int port = 8888;
		int userNum = 5;
		final ChatDTO chat = new ChatDTO();

		HttpServer server = vertx.createHttpServer();

		io = new DefaultSocketIOServer(vertx, server);
		io.sockets().onConnection(new Handler<SocketIOSocket>() {
			// 소켓 연결

			public void handle(final SocketIOSocket socket) {

				socket.on("unsubscribe", new Handler<JsonObject>() {
					public void handle(JsonObject data) {
						String room = data.getString("room");
						socket.leave(room);
						socket.emit("leave:" + room);

						print(io.sockets().manager().rooms());
						print(io.sockets().clients(room));
						print(io.sockets().manager()
								.roomClients(socket.getId()));
					}
				});
				socket.on("join", new Handler<JsonObject>() {

					public void handle(JsonObject data) {
						String roomPK = data.getString("roomPK");
						if (chatDAOImpl.getChatRoomDAOPK(roomPK) != null) {
							if (fileDAOImpl.getFiles("roomPK") != null) {
								logger.info("join file ===>");
								List<FileDTO> filelist = new ArrayList<FileDTO>();
								filelist = fileDAOImpl.getFiles(roomPK);
								String filename;
								String filesize;
								String reg_date;
								String ran_filename;
								logger.info("filelistsize===>"
										+ filelist.size());
								for (int i = 0; i < filelist.size(); i++) {
									
									
									filename = filelist.get(i).getFileName();
									ran_filename = filelist.get(i).getRanFileName();
									Long fileindex = fileDAOImpl.getFileDAORan_Name(ran_filename).getFilePK();
									/*String fileoriginalname = filename;
									int Idx = filename.lastIndexOf(".");
									String filenameroomPKcut = filename.substring(0, Idx);
									String filenamecutext = filename.substring(Idx + 1);
									int Idx2 = filenameroomPKcut.lastIndexOf("_");
									String filenamecutoriginal = filenameroomPKcut.substring(0, Idx2);
									filename = filenamecutoriginal + "." + filenamecutext;*/
									filesize = ""
											+ filelist.get(i).getFileSize();
									reg_date = filelist.get(i).getFileReg_date();
									JsonObject filedata = new JsonObject();
									filedata.putString("filename", filename);
									filedata.putString("ran_filename", ran_filename);
									filedata.putNumber("fileindex", fileindex);
									//filedata.putString("fileoriginalname", fileoriginalname);
									filedata.putString("filesize", filesize);
									filedata.putString("reg_date", reg_date);
									logger.info("join file ===>" + filename
											+ "\t" + filesize);

									socket.in(roomPK)
											.emit("filedata", filedata);
								}
							}
							String contents;
							String reg_date;
							String fb_id;
							String roomname;
							String username;
							List<ChatDTO> chatlist = new ArrayList<ChatDTO>();
							chatlist = chatDAOImpl.getChats(roomPK);
							for (int i = 0; i < chatlist.size(); i++) {
								contents = chatlist.get(i).getChatContent();
								reg_date = chatlist.get(i).getChatReg_Date();
								fb_id = chatlist.get(i).getChatFb_id();
								roomname = data.getString("roomname");
								username = userDAOImpl.getUserDAOFb_id(fb_id)
										.getUserName();
								JsonObject chatdata = new JsonObject();
								chatdata.putString("userpic",
										"https://graph.facebook.com/" + fb_id
												+ "/picture");
								chatdata.putString("username", username);
								chatdata.putString("msg", contents);
								chatdata.putString("reg_date_time", reg_date);
								chatdata.putString("roomname", roomname);
								chatdata.putString("roomPK", roomPK);

								socket.in(roomPK).emit("chatdata", chatdata);
							}
						}

						socket.join(roomPK);
						logger.info("join : ===============>" + roomPK);
					}

				});
				socket.on("filestatus", new Handler<JsonObject>() {

					public void handle(JsonObject event) {
						
					
						
						String roomPK = event.getString("roomPK");
						/*
						String filename = event.getString("filename");
						String ran_filename = null;
						
						//int Idx = filename.lastIndexOf(".");

						String filenamecut0 = filename.substring(0, Idx);
						filenamecut0 = filenamecut0 + "_" + roomPK;
						String filenamecut1 = filename.substring(Idx + 1);
						filename = filenamecut0 + "." + filenamecut1;

						long filesize = event.getLong("filesize");

						FileDTO fileDTO = new FileDTO();
						index = fileDAOImpl.getFilesNum();
						if (fileDAOImpl.getFiles() != null) {
							List<FileDTO> filelist = new ArrayList<FileDTO>();
							filelist = fileDAOImpl.getFiles(roomPK);
							String DBfilename;
							int fileindex = 1;
							for (int i = 0; i < filelist.size(); i++) {
								DBfilename = filelist.get(i).getFileName();

									ran_filename = getRandomString();
									fileindex++;
							}
							fileDTO.setFileName(filename);
							fileDTO.setFileSize(filesize);
							fileDTO.setRanFileRanName(ran_filename);
							fileDTO.setFileRoomPK(roomPK);
							fileDTO.setFilePK(index + 1);
							fileDTO.setFileReg_date(event.getString("reg_date"));
							fileDAOImpl.insert(fileDTO);
							logger.info("file insert not index zero success");
						} else {
							// filename = filenamecut0+"."+filenamecut1;
							fileDTO.setFileName(filename);
							fileDTO.setFileSize(filesize);
							fileDTO.setRanFileRanName(ran_filename);
							fileDTO.setFileRoomPK(roomPK);
							fileDTO.setFilePK(1);
							fileDTO.setFileReg_date(event.getString("reg_date"));
							fileDAOImpl.insert(fileDTO);
							logger.info("file insert null success");
						}
						event.putString("ran_filename", ran_filename);*/
						FileDTO fileDTO = new FileDTO();
						Long fileindex = fileDAOImpl.getFileDAORan_Name(event.getString("ran_filename")).getFilePK();
						logger.info("ran_filename ===========>"+event.getString("ran_filename"));
						event.putNumber("fileindex", fileindex);
						io.sockets().in(roomPK).emit("filestatus", event);
					}
				});

				socket.on("msg", new Handler<JsonObject>() {

					public void handle(JsonObject event) {
						String roomPK = event.getString("roomPK");
						chat.setChatRoomName(event.getString("roomname"));
						chat.setChatFb_id(event.getString("fb_id"));

						chat.setChatReg_Date(event.getString("reg_date_time"));
						chat.setChatContent(event.getString("msg"));
						chat.setChatRoomPK(event.getString("roomPK"));
						chatDAOImpl.insert(chat);
						io.sockets().in(roomPK).emit("connection", event);
					}
				});
			}
		});
		server.listen(port);
	}

	private void print(RoomClient roomClient) {
		System.out
				.println("===================== RoomClient ===================");
		System.out.println(roomClient.toString());
	}

	private void print(String[] clients) {
		System.out.println("===================== Clients ===================");
		if (clients == null) {
			return;
		}
		for (String client : clients) {
			System.out.println(client);
		}
	}

	private void print(Map<String, Room> rooms) {
		System.out.println("===================== Rooms ===================");
		for (Map.Entry<String, Room> entry : rooms.entrySet()) {
			System.out.println(entry.getKey());
			Room room = entry.getValue();
			for (String client : room.values()) {
				System.out.println(client);
			}
		}
	}

	public static void main(String[] args) throws Exception {
		Vertx vertx = new DefaultVertx();
		VertxService app = new VertxService(vertx);
		app.start(vertx);
		Thread.sleep(Long.MAX_VALUE);
	}
	
	public static String getRandomString(){
        return UUID.randomUUID().toString().replaceAll("-", "");
    }
}