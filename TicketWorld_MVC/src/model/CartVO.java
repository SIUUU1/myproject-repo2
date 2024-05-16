package model;

import java.io.Serializable;
import java.util.Objects;

//프로젝트명 : Ticket World
//클레스 역할 : 장바구니와 관련된 정보를 관리하는 기능을 처리하는 클래스
//제작자 : 안시우, 제작일 : 24년 5월 14일
public class CartVO implements Serializable {
	// memberVariable
	private int cart_id; // 장바구니 ID
	private String customer_id; // 고객 ID
	private int performance_id; // 공연 ID
	private String reservation_seats; // 예매좌석
	private int total_reservation_seats; // 총예매수
	private int total_payment_amount; // 총결제금액

	// constructor
	public CartVO() {
		super();
	}

	public CartVO(String customer_id, int performance_id, String reservation_seats, int total_reservation_seats,
			int total_payment_amount) {
		super();
		this.customer_id = customer_id;
		this.performance_id = performance_id;
		this.reservation_seats = reservation_seats;
		this.total_reservation_seats = total_reservation_seats;
		this.total_payment_amount = total_payment_amount;
	}

	// memberFunction
	public CartVO(int cart_id, String customer_id, int performance_id, String reservation_seats,
			int total_reservation_seats, int total_payment_amount) {
		super();
		this.cart_id = cart_id;
		this.customer_id = customer_id;
		this.performance_id = performance_id;
		this.reservation_seats = reservation_seats;
		this.total_reservation_seats = total_reservation_seats;
		this.total_payment_amount = total_payment_amount;
	}

	public int getCart_id() {
		return cart_id;
	}

	public void setCart_id(int cart_id) {
		this.cart_id = cart_id;
	}

	public String getCustomer_id() {
		return customer_id;
	}

	public void setCustomer_id(String customer_id) {
		this.customer_id = customer_id;
	}

	public int getPerformance_id() {
		return performance_id;
	}

	public void setPerformance_id(int performance_id) {
		this.performance_id = performance_id;
	}

	public String getReservation_seats() {
		return reservation_seats;
	}

	public void setReservation_seats(String reservation_seats) {
		this.reservation_seats = reservation_seats;
	}

	public int getTotal_reservation_seats() {
		return total_reservation_seats;
	}

	public void setTotal_reservation_seats(int total_reservation_seats) {
		this.total_reservation_seats = total_reservation_seats;
	}

	public int getTotal_payment_amount() {
		return total_payment_amount;
	}

	public void setTotal_payment_amount(int total_payment_amount) {
		this.total_payment_amount = total_payment_amount;
	}
//	// 총가격계산 함수
//	public void updateTotalPrice() {
//		this.totalPrice = item.getTicketPrice() * quantity;
//	}
//
//	// 공연좌석갯수 감소 함수
//	public void diminishQuantity() {
//		item.setSoldSeats(item.getSoldSeats() - quantity);
//	}

	// Overriding
	@Override
	public int hashCode() {
		return Objects.hash(cart_id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CartVO other = (CartVO) obj;
		return cart_id == other.cart_id;
	}

	@Override
	public String toString() {
		return " " + cart_id + " | " + customer_id + " | " + performance_id + " | " + reservation_seats + " | "
				+ total_reservation_seats + "";
	}

}
