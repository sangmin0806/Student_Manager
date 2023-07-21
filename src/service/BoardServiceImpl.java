package service;

import dao.BoardDAO;
import dao.BoardDAOImpl;
import dto.BoardDTO;
import dto.PrizeDTO;
import exception.DMLException;
import exception.SearchWrongException;
import view.MenuView;

import java.util.List;

public class BoardServiceImpl implements BoardService, BoardOperationService{
    private static BoardService instance = new BoardServiceImpl();
    private static BoardOperationService opInstance = new BoardServiceImpl();
    private  BoardDAO boardDAO = BoardDAOImpl.getInstance();
    private BoardServiceImpl(){}
    public static BoardService getInstance(){
        return instance;
    }
    public static BoardOperationService getOpInstance(){
        return opInstance;
    }

    @Override
    public List<BoardDTO> boardSelectAll() throws SearchWrongException {
        List<BoardDTO> list = boardDAO.boardSelectAll();
        return list;
    }

    @Override
    public List<BoardDTO> boardSelectByStudent(String Keyword) throws SearchWrongException{
        List<BoardDTO> list = boardDAO.boardSelectByStudent(Keyword);
        if(list.isEmpty()) {
            throw new SearchWrongException("검색하려는 학생이 없음");
        }
        return list;
    }

    @Override
    public List<BoardDTO> boardSelectByMajor(String major) throws SearchWrongException {
        List<BoardDTO> list = boardDAO.boardSelectByMajor(major);
        return list;
    }

    @Override
    public void boardInsert(BoardDTO boardDTO) throws DMLException {
        String grade = calGrade(boardDTO.getScore());
        boardDTO.setGrade(grade);
        int result = boardDAO.boardInsert(boardDTO);
        if(result == 0)
            throw new DMLException("등록 실패");
    }
    @Override
    public String calGrade(int score){
        String grade = null;
        if(score>=90)
            grade = "A";
        else if(score>=80)
            grade = "B";
        else if(score>=70)
            grade = "C";
        else if(score>=60)
            grade = "D";
        else
            grade = "F";
        return grade;
    }

    @Override
    public String maxAvg() {

        String com = String.valueOf(MenuView.Major.컴퓨터);
        String information = String.valueOf(MenuView.Major.정보통신);
        String media = String.valueOf(MenuView.Major.미디어소프트);
        int comAvg = majorAvg(com);
        int informationAvg = majorAvg(information);
        int mediaAvg = majorAvg(media);

        String max;
        if (comAvg >= informationAvg && comAvg >= mediaAvg) {
            max = com;
        } else if (informationAvg >= mediaAvg) {
            max = information;
        } else {
            max = media;
        }

        return max;
    }


    @Override
    public void boardUpdate(BoardDTO boardDTO) throws DMLException {
        String grade = calGrade(boardDTO.getScore());
        boardDTO.setGrade(grade);
        int result = boardDAO.boardUpdate(boardDTO);
        if(result == 0)
            throw new DMLException("수정 실패");
    }


    @Override
    public void boardDelete(int StudentID) throws DMLException {
        int result = boardDAO.boardDelete(StudentID);
        if(result == 0)
            throw new DMLException("삭제 실패");
    }

    @Override
    public void prizeInsert(PrizeDTO prizeDTO) throws DMLException {
        boardDAO.prizeInsert(prizeDTO);
    }

    @Override
    public BoardDTO prizeSelectAll(int studentId) throws SearchWrongException {
        BoardDTO boardDTO = boardDAO.prizeSelectAll(studentId);
        return boardDTO;
    }

    @Override
    public int majorAvg(String major) throws SearchWrongException{
        List<BoardDTO> list  = boardDAO.boardSelectByMajor(major);
        if(list.size()==0){
            throw new SearchWrongException("해당학과가 없습니다");
        }
        int sum =0;
        int avg=0;
        for (BoardDTO boardDTO : list) {
            sum += boardDTO.getScore();
        }
        avg = sum / list.size();
        return avg;
    }

}
