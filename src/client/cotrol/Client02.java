package client.cotrol;  
  
import java.io.BufferedReader;  
import java.io.BufferedWriter;  
import java.io.IOException;  
import java.io.InputStream;  
import java.io.InputStreamReader;  
import java.io.OutputStream;  
import java.io.OutputStreamWriter;  
import java.net.Socket;  
import java.net.UnknownHostException;  
  
public class Client02 {
    public static void main(String[] args) { 
        Runnable run = new Runnable() {
			
			@Override
			public void run() {
				try {
					Socket s = new Socket("127.0.0.1",3001);  
					
					//构建IO  
					InputStream is = s.getInputStream();  
					OutputStream os = s.getOutputStream();  
					
					BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(os));  
					//向服务器端发送一条消息  
					bw.write("A001030101001020799000012310000000000216000000     012014031311273020150830850314000000999999                                                                                                    000001                     001301010               20120903150354999999                                          0000000945312020120903175015      85291&11014165191000&888800000016258&上海新跃物流会员一&V00000027&52&20130802-7&1&0.00&0.00&0.00&&");  
					bw.flush();  
					
					//读取服务器返回的消息  
					BufferedReader br = new BufferedReader(new InputStreamReader(is));  
					String mess = br.readLine();  
					System.out.println("服务器："+mess);  
				} catch (UnknownHostException e) {  
					e.printStackTrace();  
				} catch (IOException e) {  
					e.printStackTrace();  
				}
			}
		};
		for (int i = 0; i < 200; i++) {
			new Thread(run).start();
		}
    } 
}  