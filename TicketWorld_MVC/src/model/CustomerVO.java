package model;

import java.io.Serializable;
import java.util.Objects;

//프로젝트명 : Ticket World
//클레스 역할 : 일반고객과 관련된 정보를 관리하는 기능을 처리하는 클래스
//제작자 : 안시우, 제작일 : 24년 5월 14일
public class CustomerVO implements Comparable<CustomerVO>, Serializable {

	// memberVariable
	public static final int VIP_ACCUMULPAYMENT = 100_000; // VIP등급최소누적금액
	private String customer_id; // 고객 ID
	private String customer_grade; // 고객 등급
	private String customer_pw; // 고객 PW
	private String customer_name; // 고객 이름
	private String customer_phone; // 고객 전화번호
	private String customer_email; // 고객 이메일
	private String customer_address; // 고객 주소
	private int customer_age; // 고객 나이
	private int customer_accumulated_payment; // 고객 누적결제금액
	private int customer_points; // 고객 포인트
	private double customer_point_ratio; // 고객 포인트 적립률
	private double customer_sale_ratio; // 고객 구매 할인율

	// constructor
	public CustomerVO() {
		super();
	}

	public CustomerVO(String customer_id, String customer_pw, String customer_name, String customer_phone,
			String customer_email, String customer_address, int customer_age) {
		super();
		this.customer_id = customer_id;
		this.customer_pw = customer_pw;
		this.customer_name = customer_name;
		this.customer_phone = customer_phone;
		this.customer_email = customer_email;
		this.customer_address = customer_address;
		this.customer_age = customer_age;
	}

	public CustomerVO(String customer_id, String customer_grade, String customer_pw, String customer_name,
			String customer_phone, String customer_email, String customer_address, int customer_age,
			int customer_accumulated_payment, int customer_points, double customer_point_ratio,
			double customer_sale_ratio) {
		super();
		this.customer_id = customer_id;
		this.customer_grade = customer_grade;
		this.customer_pw = customer_pw;
		this.customer_name = customer_name;
		this.customer_phone = customer_phone;
		this.customer_email = customer_email;
		this.customer_address = customer_address;
		this.customer_age = customer_age;
		this.customer_accumulated_payment = customer_accumulated_payment;
		this.customer_points = customer_points;
		this.customer_point_ratio = customer_point_ratio;
		this.customer_sale_ratio = customer_sale_ratio;
	}

	// memberFunction
	public String getCustomer_id() {
		return customer_id;
	}

	public void setCustomer_id(String customer_id) {
		this.customer_id = customer_id;
	}

	public String getCustomer_pw() {
		return customer_pw;
	}

	public void setCustomer_pw(String customer_pw) {
		this.customer_pw = customer_pw;
	}

	public String getCustomer_name() {
		return customer_name;
	}

	public void setCustomer_name(String customer_name) {
		this.customer_name = customer_name;
	}

	public String getCustomer_phone() {
		return customer_phone;
	}

	public void setCustomer_phone(String customer_phone) {
		this.customer_phone = customer_phone;
	}

	public String getCustomer_email() {
		return customer_email;
	}

	public void setCustomer_email(String customer_email) {
		this.customer_email = customer_email;
	}

	public String getCustomer_address() {
		return customer_address;
	}

	public void setCustomer_address(String customer_address) {
		this.customer_address = customer_address;
	}

	public int getCustomer_age() {
		return customer_age;
	}

	public void setCustomer_age(int customer_age) {
		this.customer_age = customer_age;
	}

	public String getCustomer_grade() {
		return customer_grade;
	}

	public void setCustomer_grade(String customer_grade) {
		this.customer_grade = customer_grade;
	}

	public int getCustomer_accumulated_payment() {
		return customer_accumulated_payment;
	}

	public void setCustomer_accumulated_payment(int customer_accumulated_payment) {
		this.customer_accumulated_payment = customer_accumulated_payment;
	}

	public int getCustomer_points() {
		return customer_points;
	}

	public void setCustomer_points(int customer_points) {
		this.customer_points = customer_points;
	}

	public double getCustomer_point_ratio() {
		return customer_point_ratio;
	}

	public void setCustomer_point_ratio(double customer_point_ratio) {
		this.customer_point_ratio = customer_point_ratio;
	}

	public double getCustomer_sale_ratio() {
		return customer_sale_ratio;
	}

	public void setCustomer_sale_ratio(double customer_sale_ratio) {
		this.customer_sale_ratio = customer_sale_ratio;
	}

	// 등급업데이트
	public void updateCustomer_grade() {
		if (customer_accumulated_payment >= VIP_ACCUMULPAYMENT) {
			customer_grade = "VIP";
			customer_point_ratio = 0.05;
			customer_sale_ratio = 0.1;
		}
	}

	// Overriding
	@Override
	public int hashCode() {
		return Objects.hash(customer_id);
	}

	@Override
	public boolean equals(Object obj) {
		CustomerVO customer = null;
		if (obj instanceof CustomerVO) {
			customer = (CustomerVO) obj;
			return customer_id.equals(customer.customer_id);
		}
		return false;
	}

	@Override
	public int compareTo(CustomerVO o) {
		return customer_id.compareTo(o.customer_id);
	}

	@Override
	public String toString() {
		return " " + customer_id + " | " + customer_grade + " | " + customer_pw + " | " + customer_name + " | "
				+ customer_phone + " | " + customer_email + " | " + customer_address + " | " + customer_age + " | "
				+ customer_accumulated_payment + " | " + customer_points + " | " + customer_point_ratio + " | "
				+ customer_sale_ratio + "";
	}

}
