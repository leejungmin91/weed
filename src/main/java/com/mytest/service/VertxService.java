package com.mytest.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.vertx.java.core.Handler;
import org.vertx.java.core.Vertx;
import org.vertx.java.core.http.HttpServer;
import org.vertx.java.core.json.JsonObject;

import com.mytest.DAO.DefaultEmbeddableVerticle;
import com.nhncorp.mods.socket.io.SocketIOServer;
import com.nhncorp.mods.socket.io.SocketIOSocket;
import com.nhncorp.mods.socket.io.impl.DefaultSocketIOServer;

public class VertxService extends DefaultEmbeddableVerticle {

	private static SocketIOServer io = null;
	private static final Logger logger = LoggerFactory
			.getLogger(VertxService.class);

	@Override
	public void start(Vertx vertx) {

		int port = 9090;

		HttpServer server = vertx.createHttpServer();

		io = new DefaultSocketIOServer(vertx, server);

		io.sockets().onConnection(new Handler<SocketIOSocket>() {

			public void handle(final SocketIOSocket socket) {

				socket.on("msg", new Handler<JsonObject>() {

					public void handle(JsonObject event) {

						logger.info("chat handler ::: "
								+ event.getString("msg"));

						io.sockets().emit("response", event);
 
					}

				});

			}

		});

		server.listen(port);

	}

}