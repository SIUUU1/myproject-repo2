package controller;

import java.util.Scanner;

import model.CustomerVO;

public class CustomerRegisterManager {
	public static Scanner sc = new Scanner(System.in);
	public static CustomerDAO cdao = new CustomerDAO();

	// 회원목록 출력기능구현
	public void customerList() {
		cdao.getCustomerTotalList();
	}

	// 회원정보 수정기능구현
	public void customerUpdate() {
		// 회원정보리스트
		cdao.getCustomerTotalList();

		System.out.print("수정할 회원ID>>");
		String c_id = sc.nextLine();
		System.out.print("고객 등급 : ");
		String c_grade = sc.nextLine();
		System.out.print("고객 PW : ");
		String c_pw = sc.nextLine();
		System.out.print("고객 이름 : ");
		String c_name = sc.nextLine();
		System.out.print("고객 전화번호 : ");
		String c_phone = sc.nextLine();
		System.out.print("고객 이메일 : ");
		String c_email = sc.nextLine();
		System.out.print("고객 주소 : ");
		String c_address = sc.nextLine();
		System.out.print("고객 나이 : ");
		int c_age = sc.nextInt();
		sc.nextLine();
		System.out.print("고객 누적결제금액 : ");
		int c_accumulated_payment = sc.nextInt();
		sc.nextLine();
		System.out.print("고객 포인트 : ");
		int c_points = sc.nextInt();
		sc.nextLine();
		System.out.print("고객 포인트 적립률 : ");
		double c_point_ratio = sc.nextDouble();
		sc.nextLine();
		System.out.print("고객 구매 할인율 : ");
		double c_sale_ratio = sc.nextDouble();
		sc.nextLine();

		CustomerVO cvo = new CustomerVO(c_id, c_grade, c_pw, c_name, c_phone, c_email, c_address, c_age,
				c_accumulated_payment, c_points, c_point_ratio, c_sale_ratio);
		cdao.setCustomerUpdate(cvo);
	}

	// 회원정보 삭제기능구현
	public void customerDelete() {
		// 회원정보리스트
		cdao.getCustomerTotalList();

		System.out.print("삭제할 회원ID>>");
		String c_id = sc.nextLine();

		cdao.setCustomerDelete(c_id);
	}

	// 고객 로그인 기능 구현
	public CustomerVO logIn() {
		CustomerVO cvo = null;
		System.out.print("아이디를 입력하세요 ");
		String login_id = sc.nextLine();
		System.out.print("비밀번호를 입력하세요 ");
		String login_pw = sc.nextLine();
		cvo = cdao.loginCustomerRegister(login_id, login_pw);
		return cvo;
	}

	// 로그인한 회원정보 출력 기능구현
	public void customerInfo(CustomerVO customer) {
		System.out.println("================================================================");
		System.out.println("\t\t\t  " + customer.getCustomer_name() + " 님의 정보");
		System.out.println("================================================================");
		System.out.println(" 등급 | 누적결제금액 | 포인트 ");
		System.out.println("----------------------------------------------------------------");
		System.out.println(" " + customer.getCustomer_grade() + " | " + customer.getCustomer_accumulated_payment()
				+ " | " + customer.getCustomer_points());
		System.out.println("================================================================");
	}

	// 회원가입시 회원 정보 저장하는 기능구현
	public void customerRegister() {
		// 중복 피하기
		System.out.print("아이디 : ");
		String c_id = sc.nextLine();
		System.out.print("비밀번호 : ");
		String c_pw = sc.nextLine();
		System.out.print("이름 : ");
		String c_name = sc.nextLine();
		System.out.print("연락처(예:010-0000-0000) : ");
		String c_phone = sc.nextLine();
		System.out.print("이메일 : ");
		String c_email = sc.nextLine();
		System.out.print("주소 : ");
		String c_address = sc.nextLine();
		System.out.print("나이 : ");
		int c_age = sc.nextInt();
		sc.nextLine();
		CustomerVO cvo = new CustomerVO(c_id, c_pw, c_name, c_phone, c_email, c_address, c_age);

		cdao.setCustomerRegister(cvo);
	}

	// 본인 확인 기능구현
	public String identification() {
		String customer_id = null;
		System.out.print("이름을 입력하세요. ");
		String find_name = sc.nextLine();
		System.out.print("전화번호를 입력하세요. ");
		String find_phone = sc.nextLine();
		customer_id = cdao.identificationCustomerRegister(find_name, find_phone);
		return customer_id;
	}

	// 찾은 아이디 출력 기능구현
	public void find_ID(String customer_id) {
		System.out.println("고객님의 아이디는 " + customer_id + " 입니다.");
	}

	// 회원 패스워드 재설정 기능구현
	public void reset_PW(String customer_id) {
		System.out.print("재설정할 비밀번호를 입력하세요. ");
		String reset_pw = sc.nextLine();
		cdao.resetPWCustomerRegister(customer_id, reset_pw);
	}

	// 고객결제정보업데이트
	public void customerPaymentUpdate(CustomerVO cvo) {
		String customer_id = cvo.getCustomer_id();
		String customer_grade = cvo.getCustomer_grade();
		String customer_pw = cvo.getCustomer_pw();
		String customer_name = cvo.getCustomer_name();
		String customer_phone = cvo.getCustomer_phone();
		String customer_email = cvo.getCustomer_email();
		String customer_address = cvo.getCustomer_address();
		int customer_age = cvo.getCustomer_age();
		int customer_accumulated_payment = cvo.getCustomer_accumulated_payment();
		int customer_points = cvo.getCustomer_points();
		double customer_point_ratio = cvo.getCustomer_point_ratio();
		double customer_sale_ratio = cvo.getCustomer_sale_ratio();
		CustomerVO cvo1 = new CustomerVO(customer_id, customer_pw, customer_name, customer_phone, customer_email,
				customer_address, customer_age);

		cdao.setCustomerUpdate(cvo1);
	}
}
