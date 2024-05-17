package controller;

import java.util.ArrayList;
import java.util.Scanner;

import main.TicketWorldMain;
import model.CartVO;
import model.CustomerVO;
import model.PaymentVO;

public class PaymentRegisterManager {
	static Scanner sc = new Scanner(System.in);
	static CartRegisterManager cartManager = new CartRegisterManager();
	static CustomerRegisterManager customerManager = new CustomerRegisterManager();
	static CartDAO cartdao = new CartDAO();
	static PaymentDAO paydao = new PaymentDAO();

	// 결제기능구현
	public void cartPayment(ArrayList<CartVO> cartList) {
		CustomerVO cvo = TicketWorldMain.customer;
		PaymentVO payvo = new PaymentVO();
		System.out.print("장바구니에 있는 모든 항목을 결제하시겠습니까? Y | N ");
		String str = sc.nextLine();
		if (str.equalsIgnoreCase("Y")) {
			System.out.println("결제창으로 이동합니다.");
			System.out.println();
			System.out.print(cvo.getCustomer_name() + "님의 기본정보를 가져오시겠습니까? Y | N ");
			str = sc.nextLine();
			if (str.equalsIgnoreCase("Y")) {
				// 결제금액 계산 및 적립
				int totalAmount = cartManager.calcPrice();
				int totalPayment = calcTotalPrice(totalAmount);
				// 결제 정보 입력하기
				payvo = paymentRegister(cvo, cartManager.cartList(), totalPayment);

			} else {
				System.out.print("배송받을 고객명을 입력하세요. ");
				String name = sc.nextLine();
				System.out.print("배송받을 고객의 연락처를 입력하세요. ");
				String phone = sc.nextLine();
				System.out.print("배송받을 고객의 배송지를 입력하세요. ");
				String address = sc.nextLine();
				System.out.println();
				// 결제금액 계산 및 적립
				int totalAmount = cartManager.calcPrice();
				int totalPayment = calcTotalPrice(totalAmount);
				// 결제 정보 입력하기
				payvo = paymentRegister(cvo, name, phone, address, cartList, totalPayment);
			}
			// 결제 내역 출력하기
			paydao.getPayment(payvo);

			// 장바구니 비우기
			cartdao.setCartDelete(cvo.getCustomer_id());
		}
	}

	// 결제 정보 입력하기
	public PaymentVO paymentRegister(CustomerVO cvo, String name, String phone, String address, ArrayList<CartVO> cartList,
			int totalPayment) {
		PaymentVO payvo = null;
		String customer_id = cvo.getCustomer_id();
		String recipient_name = name;
		String recipient_phone = phone;
		String recipient_address = address;
		int total_payment_amount = totalPayment;
		for (int i = 0; i < cartList.size(); i++) {
			int performance_id = cartList.get(i).getPerformance_id();
			String reservation_seats = cartList.get(i).getReservation_seats();
			int total_reservation_seats = cartList.get(i).getTotal_reservation_seats();
			payvo = new PaymentVO(customer_id, performance_id, recipient_name, recipient_phone, recipient_address,
					reservation_seats, total_reservation_seats, total_payment_amount);
			paydao.setPerformanceRegister(payvo);
		}
		return payvo;
	}

	// 결제 정보 입력하기
	public PaymentVO paymentRegister(CustomerVO cvo, ArrayList<CartVO> cartList, int totalPayment) {
		PaymentVO payvo = null;
		String customer_id = cvo.getCustomer_id();
		String recipient_name = cvo.getCustomer_name();
		String recipient_phone = cvo.getCustomer_phone();
		String recipient_address = cvo.getCustomer_address();
		int total_payment_amount = totalPayment;
		for (int i = 0; i < cartList.size(); i++) {
			int performance_id = cartList.get(i).getPerformance_id();
			String reservation_seats = cartList.get(i).getReservation_seats();
			int total_reservation_seats = cartList.get(i).getTotal_reservation_seats();
			payvo = new PaymentVO(customer_id, performance_id, recipient_name, recipient_phone, recipient_address,
					reservation_seats, total_reservation_seats, total_payment_amount);
			paydao.setPerformanceRegister(payvo);
		}
		return payvo;
	}

	// 결제 계산 함수
	public int calcTotalPrice(int price) {
		boolean flag = false;
		int usingPoints = 0; // 사용할 포인트
		CustomerVO cvo = TicketWorldMain.customer;
		while (!flag) {
			System.out.println(cvo.getCustomer_name() + "고객님의 현재 적립금은 " + cvo.getCustomer_points() + "원 입니다.");
			System.out.print("사용할 적립금을 입력해주세요(예: 0) ");
			usingPoints = sc.nextInt();
			sc.nextLine();
			if (cvo.getCustomer_points() >= usingPoints) {
				flag = true;
			} else {
				System.out.println("사용할 적립금이 부족합니다.");
			}
		}
		System.out.println("적립금 " + usingPoints + "원 사용되었습니다.");
		if (cvo.getCustomer_grade().equals("VIP")) {
			price -= ((int) (price * cvo.getCustomer_sale_ratio()) + usingPoints);
			cvo.setCustomer_points(
					cvo.getCustomer_points() - usingPoints + (int) (price * cvo.getCustomer_point_ratio()));
			cvo.setCustomer_accumulated_payment(cvo.getCustomer_accumulated_payment() + price);
			System.out.println((int) (price * cvo.getCustomer_sale_ratio()) + "원 할인받고, "
					+ (int) (price * cvo.getCustomer_point_ratio()) + "원 적립되어, 총 적립금은 " + cvo.getCustomer_points()
					+ "원 입니다.");
		} else {
			price -= usingPoints;
			cvo.setCustomer_points(
					cvo.getCustomer_points() - usingPoints + (int) (price * cvo.getCustomer_point_ratio()));
			cvo.setCustomer_accumulated_payment(cvo.getCustomer_accumulated_payment() + price);
			System.out.println("적립금 " + (int) (price * cvo.getCustomer_point_ratio()) + "원 적립되어, 총 적립금은 "
					+ cvo.getCustomer_points() + "원 입니다.");
		}
		// 고객등급 업데이트
		cvo.updateCustomer_grade();
		// 고객결제정보업데이트
		customerManager.customerPaymentUpdate(cvo);
		return price;
	}

	// 결제내역 출력
	public void printPaymentList(String cus_id) {
		paydao.getPaymentList(cus_id);
	}
}
