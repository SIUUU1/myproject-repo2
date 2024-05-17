package controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import main.TicketWorldMain;
import model.CartVO;

public class CartDAO {
	// 장바구니 저장함수
	public void setCartRegister(CartVO cartvo) {
		String sql = "insert into cart values(cart_id_seq.nextval, ?, ?, ?, ?, ?)";
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = DBUtil.makeConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, cartvo.getCustomer_id());
			pstmt.setInt(2, cartvo.getPerformance_id());
			pstmt.setString(3, cartvo.getReservation_seats());
			pstmt.setInt(4, cartvo.getTotal_reservation_seats());
			pstmt.setInt(5, cartvo.getTotal_payment_amount());
			int value = pstmt.executeUpdate();
			if (value == 1) {
				System.out.println(cartvo.getCustomer_id() + " 장바구니 등록 성공");
			} else {
				System.out.println(cartvo.getCustomer_id() + " 장바구니 등록 실패");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (pstmt != null) {
					pstmt.close();
				}
				if (con != null) {
					con.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	// 장바구니목록리스트함수
	public ArrayList<CartVO> getCartTotalList() {
		ArrayList<CartVO> cartList = new ArrayList<CartVO>();
		String sql = "select c.cart_id, c.customer_id, c.performance_id, p.performance_name, p.performance_day, c.reservation_seats, c.total_reservation_seats,"
				+ "c.total_payment_amount from cart c inner join performances p on c.performance_id= p.performance_id where customer_id = ?";
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = DBUtil.makeConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, TicketWorldMain.customer.getCustomer_id());
			rs = pstmt.executeQuery();
			System.out.println("================================================================");
			System.out.println(" \t\t\t " + "내 장바구니");
			System.out.println("================================================================");
			System.out.println(" 공연ID | 공연명 | 공연일 | 예매좌석 | 총예매수 | 총결제금액 ");
			System.out.println("================================================================");
			while (rs.next()) {
				CartVO cartvo = new CartVO();
				cartvo.setCart_id(rs.getInt("cart_id"));
				cartvo.setCustomer_id(rs.getString("customer_id"));
				cartvo.setPerformance_id(rs.getInt("performance_id"));
				cartvo.setReservation_seats(rs.getString("reservation_seats"));
				cartvo.setTotal_reservation_seats(rs.getInt("total_reservation_seats"));
				cartvo.setTotal_payment_amount(rs.getInt("total_payment_amount"));
				cartList.add(cartvo);

				System.out.println(" " + rs.getInt("performance_id") + " | " + rs.getString("performance_name") + " | "
						+ String.valueOf(rs.getDate("performance_day")) + " | " + rs.getString("reservation_seats") + "| "
						+ rs.getInt("total_reservation_seats") + " | " + rs.getInt("total_payment_amount"));
			}
			System.out.println("================================================================");
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (pstmt != null) {
					pstmt.close();
				}
				if (con != null) {
					con.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return cartList;
	}

	// 장바구니 비우기 함수
	public void setCartDelete(String customer_id) {
		String sql = "delete from cart where customer_id=?";
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = DBUtil.makeConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, customer_id);
			int value = pstmt.executeUpdate();
			if (value != 0) {
				System.out.println(customer_id + " 장바구니 비우기 성공");
			} else {
				System.out.println(customer_id + " 장바구니 비우기 실패");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (pstmt != null) {
					pstmt.close();
				}
				if (con != null) {
					con.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	// 장바구니 항목 지우기 함수
	public void setCartDeletItem(String customer_id, int p_id) {
		String sql = "delete from cart where customer_id=? and performance_id=?";
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = DBUtil.makeConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, customer_id);
			pstmt.setInt(2, p_id);
			int value = pstmt.executeUpdate();
			if (value == 1) {
				System.out.println(p_id + "번 공연 예매 삭제 성공");
			} else {
				System.out.println(p_id + "번 공연 예매 삭제 성공");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (pstmt != null) {
					pstmt.close();
				}
				if (con != null) {
					con.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

}
