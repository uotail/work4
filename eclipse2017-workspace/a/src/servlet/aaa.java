package servlet;

import java.util.UUID;

public class aaa {
	public static final int temp=1000000000;//9个0
	public static void main(String[] args) {
		 String uu = UUID.randomUUID().toString().replaceAll("-", "");
		 double score=99;
		double enCode = score*temp+uu.hashCode();
		System.out.println(enCode);
	}
	}


