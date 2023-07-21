package service;

import dto.BoardDTO;
import dto.PrizeDTO;
import exception.DMLException;
import exception.SearchWrongException;

import java.util.List;

public interface BoardService {
     List<BoardDTO> boardSelectAll()throws SearchWrongException;
     List<BoardDTO> boardSelectByStudent(String keyword)throws SearchWrongException;
     List<BoardDTO> boardSelectByMajor(String keyword)throws SearchWrongException;
     void boardInsert(BoardDTO boardDTO) throws DMLException;
     void boardUpdate(BoardDTO boardDTO) throws DMLException;
     void boardDelete(int studentID) throws DMLException;
     void prizeInsert(PrizeDTO prizeDTO)throws DMLException;
     BoardDTO prizeSelectAll(int studentId) throws SearchWrongException;

}
