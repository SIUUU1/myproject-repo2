package controller;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;
import java.util.Scanner;

public class AdminRegisterManager {
	//관리자 로그인
	public static boolean adminMode() {
		Scanner sc = new Scanner(System.in);
		boolean adminFlag = false;
		// db.properties 파일경로
		String filePath = "/Users/ansiu/myproject-repo1/TicketWorld/src/db.properties";
		try {
			// db.properties 관리자 아이디, 관리자 비밀번호 가져오기
			Properties properties = new Properties();
			properties.load(new FileReader(filePath));
			String adminId = properties.getProperty("adminId");
			String adminPw = properties.getProperty("adminPw");
			System.out.print("관리자 아이디를 입력하세요 ");
			String id = sc.nextLine();
			System.out.print("관리자 비밀번호를 입력하세요 ");
			String pw = sc.nextLine();
			if (id.equals(adminId) && pw.equals(adminPw)) {
				adminFlag = true;
				System.out.println("관리자 모드에 접속하셨습니다.");
			} else {
				System.out.println("관리자 로그인 실패하셨습니다.");
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} 
		return adminFlag;
	}
}
