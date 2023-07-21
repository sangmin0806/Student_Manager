package dao;

import dto.BoardDTO;
import dto.PrizeDTO;
import exception.DMLException;
import exception.SearchWrongException;

import java.util.List;

public interface BoardDAO {
    List<BoardDTO> boardSelectAll()throws SearchWrongException;
    List<BoardDTO> boardSelectByStudent(String Keyword)throws SearchWrongException;
    List<BoardDTO> boardSelectByMajor(String Keyword)throws SearchWrongException;
    int boardInsert(BoardDTO boardDTO) throws DMLException;
    int boardUpdate(BoardDTO boardDTO) throws DMLException;
    int boardDelete(int studentId) throws DMLException;
    int prizeInsert(PrizeDTO prizeDTO) throws DMLException;
    BoardDTO prizeSelectAll(int studentId)throws SearchWrongException;

}
