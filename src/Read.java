import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.text.SimpleDateFormat;
import java.util.Date;


public class Read {
	public static void main(String[] args) throws FileNotFoundException {
		Date startdate;
		Date enddate;
		try {
			System.err.println("2016-07-26 15:12:42".length());
			FileReader fileReader = new FileReader("/Users/guzh/Downloads/sys.log");
			BufferedReader br = new BufferedReader(fileReader);
			String str;
			
			while((str=br.readLine())!=null){//判断是否是最后一行
				if(str.indexOf("入金查询开始") > 0){
					System.err.println(str);//输出每一行内容。
				}
				if(str.indexOf("入金查询正常结束") > 0){
					System.out.println(str);//输出每一行内容。
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}