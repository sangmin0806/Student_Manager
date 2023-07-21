package controller;

import dto.BoardDTO;
import dto.PrizeDTO;
import exception.DMLException;
import exception.SearchWrongException;
import service.BoardOperationService;
import service.BoardService;
import service.BoardServiceImpl;
import view.FailView;
import view.SuccessView;

import java.util.List;

public class BoardController {
    private static BoardService boardService = BoardServiceImpl.getInstance();
    private static BoardOperationService boardOperation = BoardServiceImpl.getOpInstance();
    public static void boardSelectByAll() {
        try{
            List<BoardDTO> list = boardService.boardSelectAll();
            SuccessView.selectPrint(list);
        }catch (SearchWrongException e){
            FailView.errorMessage(e.getMessage());
        }
    }

    public static void boardSelectByStudent(String Keyword) {
        try {
            List<BoardDTO> list = boardService.boardSelectByStudent(Keyword);
            SuccessView.selectPrint(list);
        }catch(SearchWrongException e){
            FailView.errorMessage(e.getMessage());
        }
    }

    public static void boardSelectByMajor(String major) {
        try{
            List<BoardDTO> list =  boardService.boardSelectByMajor(major);
            SuccessView.selectPrint(list);
        }catch (SearchWrongException e){
            FailView.errorMessage(e.getMessage());
        }

    }

    public static void boardInsert(BoardDTO boardDTO) {
        try {
            boardService.boardInsert(boardDTO);
            SuccessView.messagePrint("등록 성공");
        }catch(DMLException e) {
            FailView.errorMessage(e.getMessage());
        }
    }

    public static void boardUpdate(BoardDTO boardDTO) {
        try {
            boardService.boardUpdate(boardDTO);
            SuccessView.messagePrint("수정 성공");
        }catch(DMLException e) {
            FailView.errorMessage(e.getMessage());
        }
    }

    public static void boardDelete(int studentID) {
        try {
            boardService.boardDelete(studentID);
            SuccessView.messagePrint("삭제 성공");
        }catch(DMLException e) {
            FailView.errorMessage(e.getMessage());
        }
    }
    public static void prizeInsert(PrizeDTO prizeDTO){
        try{
            boardService.prizeInsert(prizeDTO);
            SuccessView.messagePrint("상장 등록 성공");
        }catch (DMLException e){
            FailView.errorMessage(e.getMessage());
        }
    }
    public static void prizeSelectAll(int studentId){
        try {
            BoardDTO boardDTO = boardService.prizeSelectAll(studentId);
            SuccessView.prizeSelectPrint(boardDTO);
        }catch (SearchWrongException e){
            FailView.errorMessage(e.getMessage());
        }
    }
    public static void majorAvg(String major){
        try {
            int avg = boardOperation.majorAvg(major);
            SuccessView.majorAvgPrint(major,avg);
        }catch (SearchWrongException e){
            FailView.errorMessage(e.getMessage());
        }
    }
    public static void maxAvg(){
        try {
            String maxMajor = boardOperation.maxAvg();
            int avg = boardOperation.majorAvg(maxMajor);
            SuccessView.majorAvgPrint(maxMajor,avg);
        }catch (SearchWrongException e){
            FailView.errorMessage(e.getMessage());
        }

    }

}
