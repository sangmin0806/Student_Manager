package view;

import dto.BoardDTO;
import dto.PrizeDTO;

import java.util.List;

public class SuccessView {

	public static void selectPrint(List<BoardDTO> list) {
		System.out.println("----Board LIST ("+list.size()+") 개 ------------------");
		for(BoardDTO board : list) {
			System.out.println(board);//board.toString()호출
		}
		
	}

	public static void messagePrint(String message) {
		System.out.println(message);
		
	}

	public static void selectByNoPrint(BoardDTO boardDTO) {
		System.out.println(boardDTO);
		
	}

	public static void prizeSelectPrint(BoardDTO boardDTO) {
		System.out.println(boardDTO);
		System.out.println("상장 개수 : " + boardDTO.getPrizeList().size() +"개");
		for(PrizeDTO prizeDTO : boardDTO.getPrizeList()) {
			System.out.println("       ▶ " + prizeDTO );
		}
	}
	public static void majorAvgPrint(String major, int avg){
		System.out.println(major+"학과의 평균은 " + avg+"점 입니다.");
	}

}












