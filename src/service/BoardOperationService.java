package service;

import dto.BoardDTO;
import exception.SearchWrongException;

import java.util.List;

public interface BoardOperationService {

    int majorAvg(String major) throws SearchWrongException;
    String calGrade(int score);
    String maxAvg();
}
