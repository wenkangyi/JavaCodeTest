package tcptest;

import java.io.*;
import java.net.*;


public class TcpServer {
	private BufferedReader reader;
	private BufferedWriter writer;
	private ServerSocket server;
	private Socket client;
	void getServer() {
		try {
			server = new ServerSocket(6060);
			System.out.println("服务器套接字已经创建成功！");    
			while(true) {
				System.out.println("等待客户机的连接");
				client = server.accept();
				reader = new BufferedReader(new InputStreamReader(client.getInputStream()));
				//writer = new PrintWriter(client.getOutputStream(),true);
				writer = new BufferedWriter(new OutputStreamWriter(client.getOutputStream()));
				getClientMessage();//netty, mina       html（网页）-->请求   nginx(代理) －－>转发  tomcat <－－> java  <--  机器连接到java程序里面   servelt, init, server destroy
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	private void getClientMessage() {
		try {
			while(true) {
				String msg = reader.readLine();
				if(msg == null) {
					System.out.println("接收为空，连接结束！");
					break;
				}
				
				System.out.println("客户机：" + msg);
				//writer.print(msg);
				writer.write(msg);
				writer.flush();
				Thread.sleep(500);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		try {
			if(reader != null) reader.close();
			if(client != null) client.close();
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		TcpServer tcp = new TcpServer();
		tcp.getServer();
	}
}
