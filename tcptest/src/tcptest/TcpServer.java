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
			System.out.println("�������׽����Ѿ������ɹ���");    
			while(true) {
				System.out.println("�ȴ��ͻ���������");
				client = server.accept();
				reader = new BufferedReader(new InputStreamReader(client.getInputStream()));
				//writer = new PrintWriter(client.getOutputStream(),true);
				writer = new BufferedWriter(new OutputStreamWriter(client.getOutputStream()));
				getClientMessage();//netty, mina       html����ҳ��-->����   nginx(����) ����>ת��  tomcat <����> java  <--  �������ӵ�java��������   servelt, init, server destroy
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
					System.out.println("����Ϊ�գ����ӽ�����");
					break;
				}
				
				System.out.println("�ͻ�����" + msg);
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
