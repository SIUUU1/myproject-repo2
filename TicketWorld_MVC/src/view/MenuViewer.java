package view;

public class MenuViewer {
	// 로그인 메뉴
	public static void loginMenuView() {
		System.out.println("****************************************************************");
		System.out.println("\t\t" + "   Welcome to Ticket World");
		System.out.println("****************************************************************");
		System.out.println(" 1. 회원 로그인        \t2. 회원가입");
		System.out.println(" 3. 관리자 로그인       \t4. 아이디 비밀번호 찾기");
		System.out.println(" 5. 종료");
		System.out.println("****************************************************************");
		System.out.print("menu 선택 >> ");
	}

	// 아이디 비밀번호 찾기 메뉴
	public static void forgotIdPwMenuView() {
		System.out.println("****************************************************************");
		System.out.println(" 1. 아이디 찾기           \t2. 비밀번호 재설정");
		System.out.println(" 3. 뒤로가기");
		System.out.println("****************************************************************");
		System.out.print("menu 선택 >> ");
	}

	// 고객 메뉴
	public static void mainMenuView() {
		System.out.println("****************************************************************");
		System.out.println("\t\t\t" + "Ticket World Menu");
		System.out.println("****************************************************************");
		System.out.println(" 1. 본인 정보 확인하기     \t2. 예매하기");
		System.out.println(" 3. 장바구니             \t4. 결제내역보기");
		System.out.println(" 5. 로그아웃");
		System.out.println("****************************************************************");
		System.out.print("menu 선택 >> ");
	}

	// 장바구니 메뉴
	public static void cartMenuView() {
		System.out.println("****************************************************************");
		System.out.println(" 1. 결제하기             \t2. 장바구니 항목 삭제하기");
		System.out.println(" 3. 장바구니 비우기        \t4. 뒤로가기");
		System.out.println("****************************************************************");
		System.out.print("menu 선택 >> ");
	}

	// 공연 검색 메뉴
	public static void selectPerformanceMenuView() {
		System.out.println("****************************************************************");
		System.out.println(" 1. 즉시 예매          \t2. 공연명 검색 ");
		System.out.println(" 3. 장르 검색          \t4. 뒤로가기");
		System.out.println("****************************************************************");
		System.out.print("menu 선택 >> ");
	}

	// 공연 장르 메뉴
	public static void searchGenreMenuView() {
		System.out.println("****************************************************************");
		System.out.println(" 1. 뮤지컬            \t2. 콘서트");
		System.out.println(" 3. 연극              \t4. 클래식");
		System.out.println(" 5. 무용              \t6. 기타");
		System.out.println(" 7. 뒤로가기");
		System.out.println("****************************************************************");
		System.out.print("검색할 장르를 고르세요.>> ");
	}

	// 관리자 메뉴
	public static void adminMenuView() {
		System.out.println("****************************************************************");
		System.out.println(" 1. 회원 관리             \t2. 공연항목관리");
		System.out.println(" 3. 종료");
		System.out.println("****************************************************************");
		System.out.print("menu 선택 >> ");
	}
	//관리자 회원 관리 메뉴
	public static void manageMemberMenuView() {
		System.out.println("****************************************************************");
		System.out.println(" 1. 회원 전체 보기         \t2. 회원 정보 수정");
		System.out.println(" 3. 회원 정보 삭제         \t4. 뒤로가기");
		System.out.println("****************************************************************");
		System.out.print("menu 선택 >> ");
	}

	// 관리자 공연 항목 관리 메뉴
	public static void managePerformanceMenuView() {
		System.out.println("****************************************************************");
		System.out.println(" 1. 공연 항목 전체 보기    \t2. 공연 항목 추가");
		System.out.println(" 3. 공연 항목 수정        \t4. 공연 항목 삭제");
		System.out.println(" 5. 뒤로가기");
		System.out.println("****************************************************************");
		System.out.print("menu 선택 >> ");
	}
}
