package main;

import java.util.ArrayList;
import java.util.Scanner;

import controller.AdminRegisterManager;
import controller.CartRegisterManager;
import controller.CustomerRegisterManager;
import controller.PaymentRegisterManager;
import controller.PerformanceRegisterManager;
import model.CartVO;
import model.CustomerVO;
import model.PerformanceVO;
import view.ADMIN_CHOICE;
import view.CART_CHOICE;
import view.CUSTOMER_CHOICE;
import view.FORGOTIDPW_CHOICE;
import view.LOGIN_CHOICE;
import view.MANAGE_MEMBER_CHOICE;
import view.MANAGE_PERFORMANCE_CHOICE;
import view.MenuViewer;
import view.SELECT_GENRE_CHOICE;
import view.SELECT_PERFORMANCE_CHOICE;

//프로젝트명 : Ticket World
//클레스 역할 : 고객, 공연과 관련된 정보를 관리하는 기능을 처리하는 클래스 
//제작자 : 안시우, 제작일 : 24년 5월 14일
public class TicketWorldMain {
	public static Scanner sc = new Scanner(System.in);
	public static ArrayList<PerformanceVO> performanceInfoList = new ArrayList<PerformanceVO>();
	public static ArrayList<CustomerVO> customerInfoList = new ArrayList<CustomerVO>();
	public static CustomerRegisterManager cusManager = new CustomerRegisterManager();
	public static PerformanceRegisterManager perfManager = new PerformanceRegisterManager();
	public static CartRegisterManager cartManager = new CartRegisterManager();
	public static PaymentRegisterManager paymentManager = new PaymentRegisterManager();
	public static CustomerVO customer = null; // 현재 고객
	static int cusNumId = -1; // 현재 고객의 고객리스트 index

	public static void main(String[] args) {
		boolean flag = false; // 로그인 페이지 무한 반복
		boolean loginFlag = false;
		boolean mainExitFlag = false; // Customer Main 페이지 무한 반복
		boolean adminFlag = false;
		boolean exitFlag = false; // Admin Main 페이지 무한 반복

		// Login Page
		while (!flag) {
			MenuViewer.loginMenuView();
			String menuSelect = sc.nextLine().replaceAll("[^1-5]", "0");
			if (menuSelect.length() == 0) { // menuSelect가 null일때 대비
				System.out.println("다시 입력하세요.");
			} else {
				int menuSelectNum = Integer.parseInt(menuSelect);
				if (menuSelectNum < 1 || menuSelectNum > 5) {
					System.out.println("1부터 5까지의 숫자를 입력해주세요");
				} else {
					switch (menuSelectNum) {
					case LOGIN_CHOICE.LOGIN:
						// 고객 로그인
						customer = cusManager.logIn();
						if (customer != null) {
							loginFlag = true;
							flag = true;
						}
						break;
					case LOGIN_CHOICE.REGISTER:
						// 회원가입
						cusManager.customerRegister();
						break;
					case LOGIN_CHOICE.ADMIN_LOGIN:
						// 관리자 로그인
						if (AdminRegisterManager.adminMode()) {
							adminFlag = true;
							flag = true;
						}
						break;
					case LOGIN_CHOICE.FORGOTIDPW:
						// 아이디 찾기 패스워드 재설정
						forgotIdPwMenu();
						break;
					case LOGIN_CHOICE.QUIT:
						System.out.println("종료합니다.");
						flag = true;
						break;
					}
				}
			}
		} // end of while

		// Customer Main
		if (loginFlag) {
			System.out.println();
			System.out.println(customer.getCustomer_name() + "님 환영합니다.");
			while (!mainExitFlag) {
				MenuViewer.mainMenuView();

				// 공연정보로딩
				perfManager.performanceList();

				String menuSelect = sc.nextLine().replaceAll("[^1-5]", "0");
				if (menuSelect.length() == 0) {
					System.out.println("다시 입력해주세요.");
				} else {
					int menuSelectNum = Integer.parseInt(menuSelect);
					if (menuSelectNum < 1 || menuSelectNum > 5) {
						System.out.println("1부터 5까지의 숫자를 입력해주세요");
					} else {
						switch (menuSelectNum) {
						case CUSTOMER_CHOICE.CUSTOMERINFO:
							// 본인 정보 확인하기
							cusManager.customerInfo(customer);
							break;
						case CUSTOMER_CHOICE.TICKETING:
							// 공연 검색 메뉴
							selectPerformanceMenu();
							break;
						case CUSTOMER_CHOICE.CART:
							// 장바구니
							cartMenu();
							break;
						case CUSTOMER_CHOICE.PAYMENT:
							// 결제 내역 출력
							paymentManager.printPaymentList(customer.getCustomer_id());
							// menuPaymentBill();
							break;
						case CUSTOMER_CHOICE.LOGOUT:
							System.out.println("로그아웃");
							mainExitFlag = true;
							break;
						}
					}
				}
			} // end of while
		}

		// Admin Main
		if (adminFlag) {
			while (!exitFlag) {
				MenuViewer.adminMenuView();
				String menuSelect = sc.nextLine().replaceAll("[^1-3]", "0");
				if (menuSelect.length() == 0) {
					System.out.println("다시 입력해주세요.");
				} else {
					int menuSelectNum = Integer.parseInt(menuSelect);
					if (menuSelectNum < 1 || menuSelectNum > 3) {
						System.out.println("1부터 3까지의 숫자를 입력해주세요");
					} else {
						switch (menuSelectNum) {
						case ADMIN_CHOICE.MANAGE_MEMBER:
							// 회원 관리
							manageMemberMenu();
							break;
						case ADMIN_CHOICE.MANAGE_PERFORMANCE:
							// 공연항목관리
							managePerformanceMenu();
							break;
						case ADMIN_CHOICE.QUIT:
							System.out.println("관리자 모드 종료합니다.");
							exitFlag = true;
							break;
						}
					}
				}
			} // end of while
		}
	}// end of main

	// 장바구니 메뉴
	public static void cartMenu() {
		// 장바구니가 비어있으면 실행안됨
		// 장바구니 출력
		ArrayList<CartVO> cartList = cartManager.cartList();
		boolean exitFlag = false;
		while (!exitFlag) {
			MenuViewer.cartMenuView();
			String menuSelect = sc.nextLine().replaceAll("[^1-4]", "0");
			if (menuSelect.length() == 0) {
				System.out.println("다시 입력해주세요.");
			} else {
				int menuSelectNum = Integer.parseInt(menuSelect);
				if (menuSelectNum < 1 || menuSelectNum > 4) {
					System.out.println("1부터 4까지의 숫자를 입력해주세요");
				} else {
					switch (menuSelectNum) {
					case CART_CHOICE.PAYMENT:
						paymentManager.cartPayment(cartList);
						exitFlag = true;
						break;
					case CART_CHOICE.CARTREMOVEITEM:
						cartManager.cartDeleteItem();
						exitFlag = true;
						break;
					case CART_CHOICE.CARTCLEAR:
						cartManager.cartDelete();
						exitFlag = true;
						break;
					case CART_CHOICE.BACK:
						// 뒤로가기
						exitFlag = true;
						break;
					}
				}
			}
		} // end of while
	}

	// 아이디 찾기 패스워드 재설정 메뉴
	public static void forgotIdPwMenu() {
		boolean exitFlag = false;
		while (!exitFlag) {
			MenuViewer.forgotIdPwMenuView();
			String menuSelect = sc.nextLine().replaceAll("[^1-3]", "0");
			if (menuSelect.length() == 0) {
				System.out.println("다시 입력하세요.");
			} else {
				int menuSelectNum = Integer.parseInt(menuSelect);
				if (menuSelectNum < 1 || menuSelectNum > 3) {
					System.out.println("1부터 3까지의 숫자를 입력해주세요.");
				} else {
					switch (menuSelectNum) {
					case FORGOTIDPW_CHOICE.FIND_ID:
						// 본인확인
						String customer_id = cusManager.identification();
						if (customer_id != null) {
							// 찾은 아이디 출력
							cusManager.find_ID(customer_id);
						}
						break;
					case FORGOTIDPW_CHOICE.RESET_PW:
						// 본인확인
						customer_id = cusManager.identification();
						if (customer_id != null) {
							// 패스워드 재설정
							cusManager.reset_PW(customer_id);
						}
						break;
					case FORGOTIDPW_CHOICE.BACK:
						// 뒤로가기
						exitFlag = true;
						break;
					}
				}
			}
		} // end of while
	}

	// 공연 검색 메뉴
	public static void selectPerformanceMenu() {
		boolean exitFlag = false;
		boolean findFlag = false;

		// 공연정보출력
		perfManager.printPerformanceList(performanceInfoList);

		while (!exitFlag) {
			MenuViewer.selectPerformanceMenuView();

			// 공연정보로딩
			perfManager.performanceList();

			String menuSelect = sc.nextLine().replaceAll("[^1-4]", "0");
			if (menuSelect.length() == 0) {
				System.out.println("다시 입력해주세요.");
			} else {
				int menuSelectNum = Integer.parseInt(menuSelect);
				if (menuSelectNum < 1 || menuSelectNum > 4) {
					System.out.println("1부터 4까지의 숫자를 입력해주세요");
				} else {

					switch (menuSelectNum) {
					case SELECT_PERFORMANCE_CHOICE.INSTANT:
						// 공연예매
						cartManager.ticketing(performanceInfoList);
						exitFlag = true;
						break;
					case SELECT_PERFORMANCE_CHOICE.NAME:
						// 공연이름검색
						findFlag = perfManager.selectPerformanceName(performanceInfoList);
						if (findFlag) {
							// 공연예매
							cartManager.ticketing(performanceInfoList);
						}
						exitFlag = true;
						break;
					case SELECT_PERFORMANCE_CHOICE.GENRE:
						selectGenreMenu();
						exitFlag = true;
						break;
					case SELECT_PERFORMANCE_CHOICE.BACK:
						// 뒤로가기
						exitFlag = true;
						break;
					}
				}
			}
		} // end of while
	}

	// 공연 장르 검색
	public static void selectGenreMenu() {
		boolean exitFlag = false;
		boolean findFlag = false;
		while (!exitFlag) {
			MenuViewer.searchGenreMenuView();
			String menuSelect = sc.nextLine().replaceAll("[^1-7]", "0");
			if (menuSelect.length() == 0) {
				System.out.println("다시 입력해주세요.");
			} else {
				int menuSelectNum = Integer.parseInt(menuSelect);
				if (menuSelectNum < 1 || menuSelectNum > 7) {
					System.out.println("1부터 7까지의 숫자를 입력해주세요");
				} else {
					switch (menuSelectNum) {
					case SELECT_GENRE_CHOICE.MUSICAL:
						// 공연장르검색
						findFlag = perfManager.selectPerformanceGenre(performanceInfoList, "뮤지컬");
						if (findFlag) {
							// 공연예매
							cartManager.ticketing(performanceInfoList);
						}
						break;
					case SELECT_GENRE_CHOICE.CONCERT:
						// 공연장르검색
						findFlag = perfManager.selectPerformanceGenre(performanceInfoList, "콘서트");
						if (findFlag) {
							// 공연예매
							cartManager.ticketing(performanceInfoList);
						}
						break;
					case SELECT_GENRE_CHOICE.PLAY:
						// 공연장르검색
						findFlag = perfManager.selectPerformanceGenre(performanceInfoList, "연극");
						if (findFlag) {
							// 공연예매
							cartManager.ticketing(performanceInfoList);
						}
						break;
					case SELECT_GENRE_CHOICE.CLASSIC:
						// 공연장르검색
						findFlag = perfManager.selectPerformanceGenre(performanceInfoList, "클래식");
						if (findFlag) {
							// 공연예매
							cartManager.ticketing(performanceInfoList);
						}
						break;
					case SELECT_GENRE_CHOICE.DANCE:
						// 공연장르검색
						findFlag = perfManager.selectPerformanceGenre(performanceInfoList, "무용");
						if (findFlag) {
							// 공연예매
							cartManager.ticketing(performanceInfoList);
						}
						break;
					case SELECT_GENRE_CHOICE.EXTRA:
						// 공연장르검색
						findFlag = perfManager.selectPerformanceGenre(performanceInfoList, null);
						if (findFlag) {
							// 공연예매
							cartManager.ticketing(performanceInfoList);
						}
						break;
					case SELECT_GENRE_CHOICE.BACK:
						// 뒤로가기
						exitFlag = true;
						break;
					}
				}
			}
		} // end of while
	}

	// 관리자 공연관리 메뉴
	public static void managePerformanceMenu() {
		boolean exitFlag = false;
		while (!exitFlag) {
			MenuViewer.managePerformanceMenuView();

			// 공연정보로딩
			perfManager.performanceList();

			String menuSelect = sc.nextLine().replaceAll("[^1-5]", "0");
			if (menuSelect.length() == 0) {
				System.out.println("다시 입력해주세요.");
			} else {
				int menuSelectNum = Integer.parseInt(menuSelect);
				if (menuSelectNum < 1 || menuSelectNum > 5) {
					System.out.println("1부터 5까지의 숫자를 입력해주세요");
				} else {
					switch (menuSelectNum) {
					case MANAGE_PERFORMANCE_CHOICE.LIST:
						perfManager.printPerformanceList(performanceInfoList);
						break;
					case MANAGE_PERFORMANCE_CHOICE.INSERT:
						perfManager.printPerformanceList(performanceInfoList);
						perfManager.performanceRegister();
						break;
					case MANAGE_PERFORMANCE_CHOICE.UPDATE:
						perfManager.printPerformanceList(performanceInfoList);
						perfManager.performanceUpdate();
						break;
					case MANAGE_PERFORMANCE_CHOICE.DELETE:
						perfManager.printPerformanceList(performanceInfoList);
						perfManager.performanceDelete();
						break;
					case MANAGE_PERFORMANCE_CHOICE.BACK:
						// 뒤로가기
						exitFlag = true;
						break;
					}
				}
			}
		} // end of while
	}

	// 관리자 회원관리 메뉴
	public static void manageMemberMenu() {
		boolean exitFlag = false;
		while (!exitFlag) {
			MenuViewer.manageMemberMenuView();
			String menuSelect = sc.nextLine().replaceAll("[^1-4]", "0");
			if (menuSelect.length() == 0) {
				System.out.println("다시 입력해주세요.");
			} else {
				int menuSelectNum = Integer.parseInt(menuSelect);
				if (menuSelectNum < 1 || menuSelectNum > 4) {
					System.out.println("1부터 4까지의 숫자를 입력해주세요");
				} else {
					switch (menuSelectNum) {
					case MANAGE_MEMBER_CHOICE.LIST:
						cusManager.customerList();
						break;
					case MANAGE_MEMBER_CHOICE.UPDATE:
						cusManager.customerUpdate();
						break;
					case MANAGE_MEMBER_CHOICE.DELETE:
						cusManager.customerDelete();
						break;
					case MANAGE_MEMBER_CHOICE.BACK:
						// 뒤로가기
						exitFlag = true;
						break;
					}
				}
			}
		} // end of while
	}
}
