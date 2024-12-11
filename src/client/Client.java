package client;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Scanner;

import javax.swing.JOptionPane;

public class Client {
	
	public static String inputMenuNum() {
		
		Scanner sc = new Scanner(System.in);
		
		System.out.println("<< 서비스 시스템 >>");
		System.out.println("1. 날짜 ");// yyyy/MM/dd
		System.out.println("2. 현재 시간 "); // hh시mm분ss초 
		System.out.println("3. 오늘의 로또 추천 ");
		System.out.println("0. 연결 종료 ");
		System.out.print(">>");
		
		String menuNum = sc.nextLine();
		return menuNum;
		
	}
	
	
	
	public static void main(String[] args) {
		
		try {
//			String ipAddress = "10.5.5.12";
			InetAddress localAddress = InetAddress.getLocalHost();
			
//			Socket client = new Socket(ipAddress, 20001);
			Socket client = new Socket(localAddress, 20000);
			
			DataInputStream dis = new DataInputStream(client.getInputStream());
			DataOutputStream dos = new DataOutputStream(client.getOutputStream());
			
			while(true) {
				
				String menu = inputMenuNum();
				
				if(menu.equals("0")) {
					System.out.println("서버를 종료하겠습니다.");
					System.exit(0);
				}
				dos.writeUTF(menu);
				dos.flush();
				
				String days = dis.readUTF();
				System.out.println(days);
				dos.flush();
				
			}
			
		}catch(Exception e) {
			System.out.println("서버에 연결할 수 없습니다. ");
			e.printStackTrace();
		}
		
		
		
	}

}
