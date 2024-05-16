package model;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

//프로젝트명 : Ticket World
//클레스 역할 : 공연과 관련된 정보를 관리하는 기능을 처리하는 클래스
//제작자 : 안시우, 제작일 : 24년 5월 14일
public class PerformanceVO implements Comparable<PerformanceVO>, Serializable {
	// memberVariable
	private int performance_id; // 공연ID
	private String performance_name; // 공연이름
	private String performance_genre; // 공연장르
	private String performance_day; // 공연일
	private String performance_venue; // 공연장소
	private int performance_limit_age; // 공연 관람제한연령
	private int performance_total_seats; // 총좌석수
	private int performance_sold_seats; // 판매된 좌석수
	private String performance_seatsInfo; // 좌석정보
	private int performance_ticket_price; // 티켓가격

	// constructor
	public PerformanceVO() {
		super();
	}

	public PerformanceVO(String performance_name, String performance_genre, String performance_day,
			String performance_venue, int performance_limit_age, int performance_total_seats,
			int performance_ticket_price) {
		super();
		this.performance_name = performance_name;
		this.performance_genre = performance_genre;
		this.performance_day = performance_day;
		this.performance_venue = performance_venue;
		this.performance_limit_age = performance_limit_age;
		this.performance_total_seats = performance_total_seats;
		this.performance_ticket_price = performance_ticket_price;
	}

	public PerformanceVO(int performance_id, String performance_name, String performance_genre, String performance_day,
			String performance_venue, int performance_limit_age, int performance_total_seats,
			int performance_sold_seats, String performance_seatsInfo, int performance_ticket_price) {
		super();
		this.performance_id = performance_id;
		this.performance_name = performance_name;
		this.performance_genre = performance_genre;
		this.performance_day = performance_day;
		this.performance_venue = performance_venue;
		this.performance_limit_age = performance_limit_age;
		this.performance_total_seats = performance_total_seats;
		this.performance_sold_seats = performance_sold_seats;
		this.performance_seatsInfo = performance_seatsInfo;
		this.performance_ticket_price = performance_ticket_price;
	}

	public PerformanceVO(int performance_id, String performance_name, String performance_genre, String performance_day,
			String performance_venue, int performance_limit_age, int performance_total_seats,
			int performance_sold_seats, int performance_ticket_price) {
		super();
		this.performance_id = performance_id;
		this.performance_name = performance_name;
		this.performance_genre = performance_genre;
		this.performance_day = performance_day;
		this.performance_venue = performance_venue;
		this.performance_limit_age = performance_limit_age;
		this.performance_total_seats = performance_total_seats;
		this.performance_sold_seats = performance_sold_seats;
		this.performance_ticket_price = performance_ticket_price;
	}

	// memberFunction
	public int getPerformance_id() {
		return performance_id;
	}

	public void setPerformance_id(int performance_id) {
		this.performance_id = performance_id;
	}

	public String getPerformance_name() {
		return performance_name;
	}

	public void setPerformance_name(String performance_name) {
		this.performance_name = performance_name;
	}

	public String getPerformance_genre() {
		return performance_genre;
	}

	public void setPerformance_genre(String performance_genre) {
		this.performance_genre = performance_genre;
	}

	public String getPerformance_day() {
		return performance_day;
	}

	public void setPerformance_day(String performance_day) {
		this.performance_day = performance_day;
	}

	public String getPerformance_venue() {
		return performance_venue;
	}

	public void setPerformance_venue(String performance_venue) {
		this.performance_venue = performance_venue;
	}

	public int getPerformance_limit_age() {
		return performance_limit_age;
	}

	public void setPerformance_limit_age(int performance_limit_age) {
		this.performance_limit_age = performance_limit_age;
	}

	public int getPerformance_total_seats() {
		return performance_total_seats;
	}

	public void setPerformance_total_seats(int performance_total_seats) {
		this.performance_total_seats = performance_total_seats;
	}

	public int getPerformance_sold_seats() {
		return performance_sold_seats;
	}

	public void setPerformance_sold_seats(int performance_sold_seats) {
		this.performance_sold_seats = performance_sold_seats;
	}

	public String getPerformance_seatsInfo() {
		return performance_seatsInfo;
	}

	public void setPerformance_seatsInfo(String performance_seatsInfo) {
		this.performance_seatsInfo = performance_seatsInfo;
	}

	public int getPerformance_ticket_price() {
		return performance_ticket_price;
	}

	public void setPerformance_ticket_price(int performance_ticket_price) {
		this.performance_ticket_price = performance_ticket_price;
	}

	// 남은좌석계산함수
	public int calcRemainingSeat() {
		return performance_total_seats - performance_sold_seats;
	}

	// Overriding
	@Override
	public int compareTo(PerformanceVO o) {
		return performance_ticket_price - o.performance_ticket_price;
	}

	@Override
	public int hashCode() {
		return Objects.hash(performance_id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PerformanceVO other = (PerformanceVO) obj;
		return performance_id == other.performance_id;
	}

	@Override
	public String toString() {
		return " " + performance_id + " | " + performance_name + " | " + performance_genre + " | " + performance_day
				+ " | " + performance_venue + " | " + performance_limit_age + " | "
				+ (performance_total_seats - performance_sold_seats) + "/" + performance_total_seats + " | "
				+ performance_ticket_price + "";
	}

}
