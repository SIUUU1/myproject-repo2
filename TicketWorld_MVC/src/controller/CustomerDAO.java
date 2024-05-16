package controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.CustomerVO;
import model.PerformanceVO;

public class CustomerDAO {
	// 회원목록리스트함수
	public void getCustomerTotalList() {
		String sql = "select * from customers order by customer_id";
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = DBUtil.makeConnection();
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			System.out.println("----------------------------------------------------------------");
			System.out.println(" ID | 등급 | PW | 이름 | 전화번호 | 이메일 | 주소 | 나이 | 누적결제금액 | 포인트 | 포인트적립률 | 구매할인율 ");
			System.out.println("----------------------------------------------------------------");
			while (rs.next()) {
				CustomerVO cvo = new CustomerVO();
				cvo.setCustomer_id(rs.getString("customer_id"));
				cvo.setCustomer_grade(rs.getString("customer_grade"));
				cvo.setCustomer_pw(rs.getString("customer_pw"));
				cvo.setCustomer_name(rs.getString("customer_name"));
				cvo.setCustomer_phone(rs.getString("customer_phone"));
				cvo.setCustomer_email(rs.getString("customer_email"));
				cvo.setCustomer_address(rs.getString("customer_address"));
				cvo.setCustomer_age(rs.getInt("customer_age"));
				cvo.setCustomer_accumulated_payment(rs.getInt("customer_accumulated_payment"));
				cvo.setCustomer_points(rs.getInt("customer_points"));
				cvo.setCustomer_point_ratio(rs.getDouble("customer_point_ratio"));
				cvo.setCustomer_sale_ratio(rs.getDouble("customer_sale_ratio"));
				System.out.println(cvo.toString());
			}
			System.out.println("----------------------------------------------------------------");
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

	}

	// 회원정보수정함수
	public void setCustomerUpdate(CustomerVO cvo) {
		String sql = "update customers set customer_grade=?, customer_pw = ?, customer_name=?,"
				+ "customer_phone=?, customer_email=?, customer_address=?, customer_age=?, "
				+ "customer_accumulated_payment=?, customer_points=?, customer_point_ratio=?,"
				+ "customer_sale_ratio=? where customer_id=?";
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = DBUtil.makeConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, cvo.getCustomer_grade());
			pstmt.setString(2, cvo.getCustomer_pw());
			pstmt.setString(3, cvo.getCustomer_name());
			pstmt.setString(4, cvo.getCustomer_phone());
			pstmt.setString(5, cvo.getCustomer_email());
			pstmt.setString(6, cvo.getCustomer_address());
			pstmt.setInt(7, cvo.getCustomer_age());
			pstmt.setInt(8, cvo.getCustomer_accumulated_payment());
			pstmt.setInt(9, cvo.getCustomer_points());
			pstmt.setDouble(10, cvo.getCustomer_point_ratio());
			pstmt.setDouble(11, cvo.getCustomer_sale_ratio());
			pstmt.setString(12, cvo.getCustomer_id());
			int value = pstmt.executeUpdate();
			if (value == 1) {
				System.out.println(cvo.getCustomer_name() + " 회원정보 수정 성공");
			} else {
				System.out.println(cvo.getCustomer_name() + " 회원정보 수정 실패");
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

	// 회원정보삭제함수
	public void setCustomerDelete(String c_id) {
		String sql = "delete from customers where customer_id=?";
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = DBUtil.makeConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, c_id);
			int value = pstmt.executeUpdate();
			if (value == 1) {
				System.out.println(c_id + " 회원 삭제 성공");
			} else {
				System.out.println(c_id + " 회원 삭제 실패");
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

	// 회원가입 시 회원정보입력함수
	public void setCustomerRegister(CustomerVO cvo) {
		String sql = "insert into customers values(?, 'Basic', ?, ?, ?, ?, ?, ?,0,0,0.03,0)";
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = DBUtil.makeConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, cvo.getCustomer_id());
			pstmt.setString(2, cvo.getCustomer_pw());
			pstmt.setString(3, cvo.getCustomer_name());
			pstmt.setString(4, cvo.getCustomer_phone());
			pstmt.setString(5, cvo.getCustomer_email());
			pstmt.setString(6, cvo.getCustomer_address());
			pstmt.setInt(7, cvo.getCustomer_age());
			int value = pstmt.executeUpdate();
			if (value == 1) {
				System.out.println(cvo.getCustomer_name() + " 회원가입 성공하셨습니다.");
			} else {
				System.out.println(cvo.getCustomer_name() + "회원가입 실패하셨습니다.");
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

	// 로그인 시 정보확인함수
	public CustomerVO loginCustomerRegister(String login_id, String login_pw) {
		CustomerVO cvo = null;
		String sql = "select * from customers where customer_id = ?";
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = DBUtil.makeConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, login_id);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				if (login_pw.equals(rs.getString("customer_pw"))) {
					//System.out.println("로그인 성공");
					cvo = new CustomerVO();
					cvo.setCustomer_id(rs.getString("customer_id"));
					cvo.setCustomer_grade(rs.getString("customer_grade"));
					cvo.setCustomer_pw(rs.getString("customer_pw"));
					cvo.setCustomer_name(rs.getString("customer_name"));
					cvo.setCustomer_phone(rs.getString("customer_phone"));
					cvo.setCustomer_email(rs.getString("customer_email"));
					cvo.setCustomer_address(rs.getString("customer_address"));
					cvo.setCustomer_age(rs.getInt("customer_age"));
					cvo.setCustomer_accumulated_payment(rs.getInt("customer_accumulated_payment"));
					cvo.setCustomer_points(rs.getInt("customer_points"));
					cvo.setCustomer_point_ratio(rs.getDouble("customer_point_ratio"));
					cvo.setCustomer_sale_ratio(rs.getDouble("customer_sale_ratio"));
				} else {
					System.out.println("잘못된 비밀번호 입니다.");
				}
			} else {
				System.out.println("존재하지 않는 아이디 입니다.");
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
		return cvo;
	}

	// 본인 확인함수
	public String identificationCustomerRegister(String find_name, String find_phone) {
		String sql = "select customer_id from customers where customer_name = ? AND customer_phone = ?";
		String customer_id = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = DBUtil.makeConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, find_name);
			pstmt.setString(2, find_phone);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				customer_id = rs.getString("customer_id");
			} else {
				System.out.println("존재하지 않는 회원입니다.");
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
		return customer_id;
	}

	// 패스워드 재설정함수
	public void resetPWCustomerRegister(String customer_id, String reset_pw) {
		String sql = "update customers set customer_pw = ? where customer_id = ?";
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = DBUtil.makeConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, reset_pw);
			pstmt.setString(2, customer_id);
			int value = pstmt.executeUpdate();
			if (value == 1) {
				System.out.println("비밀번호가 성공적으로 재설정되었습니다.");
			} else {
				System.out.println("비밀번호 재설정에 실패하셨습니다.");
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
