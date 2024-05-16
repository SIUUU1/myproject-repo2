package model;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Objects;

//프로젝트명 : Ticket World
//클레스 역할 : 고객의 구매와 관련된 정보를 관리하는 기능을 처리하는 클래스
//제작자 : 안시우, 제작일 : 24년 5월 14일
public class PaymentVO implements Serializable {
	// memberVariable
	private int payment_id; // 결제내역 ID
	private String customer_id; // 고객 ID
	private int performance_id; // 공연 ID
	private String reservation_date; // 예매날짜
	private String recipient_name; // 수령인 이름
	private String recipient_phone; // 수령인 전화번호
	private String recipient_address; // 수령인 주소
	private String reservation_seats; // 예매좌석
	private int total_reservation_seats; // 총예매수
	private int total_payment_amount; // 총결제금액

	// constructor
	public PaymentVO() {
		super();
	}

	public PaymentVO(String customer_id, int performance_id, String recipient_name, String recipient_phone,
			String recipient_address, String reservation_seats, int total_reservation_seats, int total_payment_amount) {
		super();
		this.customer_id = customer_id;
		this.performance_id = performance_id;
		this.recipient_name = recipient_name;
		this.recipient_phone = recipient_phone;
		this.recipient_address = recipient_address;
		this.reservation_seats = reservation_seats;
		this.total_reservation_seats = total_reservation_seats;
		this.total_payment_amount = total_payment_amount;
	}

	public PaymentVO(String customer_id, int performance_id, String reservation_date, String recipient_name,
			String recipient_phone, String recipient_address, String reservation_seats, int total_reservation_seats,
			int total_payment_amount) {
		super();
		this.customer_id = customer_id;
		this.performance_id = performance_id;
		this.reservation_date = reservation_date;
		this.recipient_name = recipient_name;
		this.recipient_phone = recipient_phone;
		this.recipient_address = recipient_address;
		this.reservation_seats = reservation_seats;
		this.total_reservation_seats = total_reservation_seats;
		this.total_payment_amount = total_payment_amount;
	}

	// memberFunction
	public int getPayment_id() {
		return payment_id;
	}

	public void setPayment_id(int payment_id) {
		this.payment_id = payment_id;
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

	public String getReservation_date() {
		return reservation_date;
	}

	public void setReservation_date(String reservation_date) {
		this.reservation_date = reservation_date;
	}

	public String getRecipient_name() {
		return recipient_name;
	}

	public void setRecipient_name(String recipient_name) {
		this.recipient_name = recipient_name;
	}

	public String getRecipient_phone() {
		return recipient_phone;
	}

	public void setRecipient_phone(String recipient_phone) {
		this.recipient_phone = recipient_phone;
	}

	public String getRecipient_address() {
		return recipient_address;
	}

	public void setRecipient_address(String recipient_address) {
		this.recipient_address = recipient_address;
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

	// Overriding
	@Override
	public int hashCode() {
		return Objects.hash(payment_id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PaymentVO other = (PaymentVO) obj;
		return payment_id == other.payment_id;
	}

}
