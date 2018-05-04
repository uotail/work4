package servlet;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

public class a {

	public static void main(String[] args) throws IOException {
		Thread t1 = new Thread(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				ThreadLocal<String> tl = new ThreadLocal<>();
				ThreadLocal<String> t2 = new ThreadLocal<>();
				tl.set("a");
				t2.set("b");
				System.out.println(tl.get());
				System.out.println(t2.get());
			}
		});
		t1.start();
	}

}
