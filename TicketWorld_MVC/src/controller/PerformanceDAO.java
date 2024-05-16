package controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.PerformanceVO;

public class PerformanceDAO {
	// 공연목록리스트함수
	public ArrayList<PerformanceVO> getPerformanceTotalList() {
		ArrayList<PerformanceVO> performanceList = new ArrayList<PerformanceVO>();
		String sql = "select * from performances order by performance_id";
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = DBUtil.makeConnection();
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				PerformanceVO pvo = new PerformanceVO();
				pvo.setPerformance_id(rs.getInt("performance_id"));
				pvo.setPerformance_name(rs.getString("performance_name"));
				pvo.setPerformance_genre(rs.getString("performance_genre"));
				pvo.setPerformance_day(String.valueOf(rs.getDate("performance_day")));
				pvo.setPerformance_venue(rs.getString("performance_venue"));
				pvo.setPerformance_limit_age(rs.getInt("performance_limit_age"));
				pvo.setPerformance_total_seats(rs.getInt("performance_total_seats"));
				pvo.setPerformance_sold_seats(rs.getInt("performance_sold_seats"));
				pvo.setPerformance_seatsInfo(rs.getString("performance_seatsInfo"));
				pvo.setPerformance_ticket_price(rs.getInt("performance_ticket_price"));
				performanceList.add(pvo);
			}
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
		return performanceList;
	}

	// 공연정보입력함수
	public void setPerformanceRegister(PerformanceVO pvo) {
		// 총좌석수를 문자열로 변환
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < pvo.getPerformance_total_seats(); i++) {
			sb.append("0");
		}

		String sql = "insert into performances values(performance_id_seq.nextval, ?, ?, ?, ?, ?, ?, 0, ?,?)";
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = DBUtil.makeConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, pvo.getPerformance_name());
			pstmt.setString(2, pvo.getPerformance_genre());
			pstmt.setString(3, pvo.getPerformance_day());
			pstmt.setString(4, pvo.getPerformance_venue());
			pstmt.setInt(5, pvo.getPerformance_limit_age());
			pstmt.setInt(6, pvo.getPerformance_total_seats());
			pstmt.setString(7, sb.toString());
			pstmt.setInt(8, pvo.getPerformance_ticket_price());
			int value = pstmt.executeUpdate();
			if (value == 1) {
				System.out.println(pvo.getPerformance_name() + " 공연 등록 성공");
			} else {
				System.out.println(pvo.getPerformance_name() + " 공연 등록 실패");
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

	// 공연정보수정함수
	public void setPerformanceUpdate(PerformanceVO pvo) {
		// 총좌석수를 문자열로 변환
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < pvo.getPerformance_total_seats(); i++) {
			sb.append("0");
		}

		String sql = "update performances set performance_name=?, performance_genre=?, performance_day=?,"
				+ "performance_venue=?, performance_limit_age=?, performance_total_seats=?, performance_sold_seats=?,"
				+ "performance_seatsinfo=?,performance_ticket_price=? where performance_id = ?";
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = DBUtil.makeConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, pvo.getPerformance_name());
			pstmt.setString(2, pvo.getPerformance_genre());
			pstmt.setString(3, pvo.getPerformance_day());
			pstmt.setString(4, pvo.getPerformance_venue());
			pstmt.setInt(5, pvo.getPerformance_limit_age());
			pstmt.setInt(6, pvo.getPerformance_total_seats());
			pstmt.setInt(7, pvo.getPerformance_sold_seats());
			pstmt.setString(8, sb.toString());
			pstmt.setInt(9, pvo.getPerformance_ticket_price());
			pstmt.setInt(10, pvo.getPerformance_id());
			int value = pstmt.executeUpdate();
			if (value == 1) {
				System.out.println(pvo.getPerformance_name() + " 공연정보 수정 성공");
			} else {
				System.out.println(pvo.getPerformance_name() + " 공연정보 수정 실패");
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

	// 공연정보삭제함수
	public void setPerformanceDelete(int p_id) {
		String sql = "delete from performances where performance_id=?";
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = DBUtil.makeConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, p_id);
			int value = pstmt.executeUpdate();
			if (value == 1) {
				System.out.println(p_id + " 공연 삭제 성공");
			} else {
				System.out.println(p_id + " 공연 삭제 실패");
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

	// 예매 후 공연 정보 수정함수
	public void setPerformanceUpdateAfterTicketing(PerformanceVO pvo) {
		String sql = "update performances set performance_sold_seats=?, performance_seatsinfo=? where performance_id = ?";
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = DBUtil.makeConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, pvo.getPerformance_sold_seats());
			pstmt.setString(2, pvo.getPerformance_seatsInfo());
			pstmt.setInt(3, pvo.getPerformance_id());
			int value = pstmt.executeUpdate();
			if (value == 1) {
				//System.out.println(pvo.getPerformance_name() + "예매 후 공연정보 수정 성공");
			} else {
				//System.out.println(pvo.getPerformance_name() + "예매 후 공연정보 수정 실패");
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
