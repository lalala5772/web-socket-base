package server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;

//서버 잘 종료하기 
public class Server {
	public static void main(String[] args) throws Exception {


		try {
			ServerSocket server = new ServerSocket(20000);

			System.out.println("#접속자를 기다리는 중입니다..");
			Socket client = server.accept();

			System.out.println(client.getInetAddress() + " 로 부터 접속 확인..");

			DataInputStream dis = new DataInputStream(client.getInputStream());
			DataOutputStream dos = new DataOutputStream(client.getOutputStream());

			while(true) {
				String menu = dis.readUTF();

				if(menu.equals("1")) {
					//날짜 
					Date day = new Date();
					SimpleDateFormat dayform = new SimpleDateFormat("yyyy/MM/dd");
					dos.writeUTF(dayform.format(day));
					
				}
				else if(menu.equals("2")) {
					//현재시간 
					Date day = new Date();
					SimpleDateFormat timeform = new SimpleDateFormat("hh시 mm분 ss초");
					dos.writeUTF(timeform.format(day));
				}
				else if(menu.equals("3")) {
					//로또 추천
					String lotto = "";
					for(int i=0;i<6;i++) {
						int num = ((int)(Math.random()*45+1));
						lotto += ""+ num +" ";
					}
					dos.writeUTF(lotto);
					
				}
				else if(menu.equals("0")){
					//서버 종료
					dos.writeUTF("서비스를 종료하겠습니다. ");
					server.close();
					break;
				}
				else {
					dos.writeUTF("잘못 입력하셨습니다. ");
				}
				
				dos.flush();

			}

		}catch(Exception e) {
			System.out.println("서버에 연결할 수 없습니다. ");
			e.printStackTrace();
		}


	}
}
