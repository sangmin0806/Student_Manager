package view;


import controller.BoardController;
import dto.BoardDTO;
import dto.PrizeDTO;

import java.util.Scanner;

public class MenuView {
   static Scanner sc =new Scanner(System.in);
 
   /**
    * 메뉴
    * */
   public static void menuChoice() {
	   while(true) {
		   System.out.println("\n----------------------------------------");
		   System.out.println("1. 전체 학생 조회");
		   System.out.println("2. 이름 검색");
		   System.out.println("3. 전공별 학생 검색");
		   System.out.println("4. 학과별 평균 조회");
		   System.out.println("5. 학생 정보 및 성적 등록");
		   System.out.println("6. 학생 성적 수정");
		   System.out.println("7. 학생 삭제");
		   System.out.println("8. 상장 조회");
		   System.out.println("9. 상장 등록");
		   System.out.print("10. 프로그램 종료");
		   System.out.println("\n----------------------------------------");
		   System.out.print("현재 학과별 평균 1등 : ");
		   maxAvg();
		   System.out.println("선택메뉴는?");

              try {
	              int menu = Integer.parseInt(sc.nextLine());//
	              switch (menu) {
					  case 1:
						  selectBoardAll();
						  break;
					  case 2:
						  boardSelectByStudent();
						  break;
					  case 3:
						  inputBoardByMajor();
						  break;
					  case 4:
						  inputMajorAvg();
						  break;
					  case 5:
						  boardInsert();
						  break;
					  case 6:
						  boardUpdate();
						  break;
					  case 7:
						  boardDelete();
						  break;
					  case 8:
						  selectPrize();
						  break;
					  case 9:
						  inputPrize();
						  break;
					  case 10:
						  System.exit(0);
						  break;
					  default:
						  System.out.println("잘못되었습니다..다시 입력해주세요.");
				}
	        	 
              }catch (NumberFormatException e) {
				System.out.println("메뉴는 숫자만 가능합니다.");
			}
         }//while문
    }
	public enum Major {
	   정보통신,컴퓨터,미디어소프트
	}
	public static void maxAvg(){
	   BoardController.maxAvg();
	}
	public static String majorChange(int num){
		Major word = null;
		switch (num){
			case 1:
				word = Major.정보통신;
				break;
			case 2:
				word = Major.컴퓨터;
				break;
			case 3:
				word = Major.미디어소프트;
				break;
		}
		String  major = String.valueOf(word);
		return major;
	}

	private static void inputMajorAvg() {
		System.out.println("조회하려는 학과는?");
		System.out.println("1.정보통신   2.컴퓨터   3.미디어소프트");
		int num = Integer.parseInt(sc.nextLine());

		BoardController.majorAvg(majorChange(num));
	}

	private static void selectPrize() {
		System.out.println("조회하려는 학생의 학번은?");
		int studentId = Integer.parseInt(sc.nextLine());
		BoardController.prizeSelectAll(studentId);

	}

	private static void inputPrize() {
		System.out.println("수상받은 학생의 학번은?");
		int studentId = Integer.parseInt(sc.nextLine());
		System.out.println("상장 이름은?");
		String prizeName = sc.nextLine();
		System.out.println("장학금은?");
		int scholarship = Integer.parseInt(sc.nextLine());;

		BoardController.prizeInsert(new PrizeDTO(prizeName,scholarship,studentId));

	}


	private static void selectBoardAll() {
	   BoardController.boardSelectByAll();
	}

	public static void inputBoardByMajor(){
		System.out.println("전공과목 이름은?");
		System.out.println("1.정보통신   2.컴퓨터   3.미디어소프트");
		int num = Integer.parseInt(sc.nextLine());
		BoardController.boardSelectByMajor(majorChange(num));
	}
	public static void boardSelectByStudent() { //2
		System.out.println("찾을 학생 이름은?");
		String word = sc.nextLine();
		BoardController.boardSelectByStudent(word);
	}



	public static void boardInsert() { //4
		try {
			System.out.println("등록할 학생의 학번은?");
			int student_ID = Integer.parseInt(sc.nextLine());
			System.out.println("등록할 학생의 이름은?");
			String student = sc.nextLine();
			System.out.println("등록할 학생의 학과는?");
			System.out.println("1.정보통신   2.컴퓨터   3.미디어소프트");
			int num = Integer.parseInt(sc.nextLine());
			String major = majorChange(num);
			System.out.println("등록할 학생의 성적은?");
			int score = Integer.parseInt(sc.nextLine());
			String grade = null;

			BoardDTO board = new BoardDTO(student_ID, student, major, grade, score);
			BoardController.boardInsert(board);
		}catch(NumberFormatException e) {
			System.out.println("학번과 성적은 숫자만 입력 가능");
			System.out.println("1. 등록 취소	2. 다시 등록");
			int choice = Integer.parseInt(sc.nextLine());
			if(choice==2)
				boardInsert();
		}
	}

	public static void boardUpdate() { //5
		try {
			System.out.println("수정할 학생의 학번은?");
			int studentID = Integer.parseInt(sc.nextLine());
			System.out.println("수정할 학생의 점수는?");
			int score = Integer.parseInt(sc.nextLine());

			BoardDTO boardDTO = new BoardDTO(studentID, null, null, null, score);
			BoardController.boardUpdate(boardDTO);

		}catch(NumberFormatException e) {
			System.out.println("숫자만 입력하시오.");
			boardUpdate();
		}
	}

	public static void boardDelete() {//6
		try {
			System.out.println("삭제할 학생의 학번은?");
			String studentID = sc.nextLine();
			BoardController.boardDelete(Integer.parseInt(studentID));
		}catch(NumberFormatException e){
			System.out.println("숫자만 입력하시오.");
			boardDelete();
		}
	}

}//클래스끝

















