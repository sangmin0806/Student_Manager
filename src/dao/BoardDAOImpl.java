package dao;

import dto.BoardDTO;
import dto.PrizeDTO;
import exception.DMLException;
import exception.SearchWrongException;
import util.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BoardDAOImpl implements BoardDAO{
    private static BoardDAO instance = new BoardDAOImpl();
    public static BoardDAO getInstance(){
        return instance;
    }

    @Override
    public List<BoardDTO> boardSelectAll() throws SearchWrongException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<BoardDTO> list = new ArrayList<>();
        String sql ="select student_id, student, major, grade, score from student";

        try {
            con = DBUtil.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();

            while(rs.next()) {
                BoardDTO board = new BoardDTO(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getInt(5));
                list.add(board);
            }

        }catch(Exception e) {
            throw new SearchWrongException("오류 발생");
        }finally {
            DBUtil.dbClose(con, ps, rs);
        }
        return list;
    }

    @Override
    public List<BoardDTO> boardSelectByStudent(String Keyword) throws SearchWrongException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<BoardDTO> list = new ArrayList<>();
        String sql = "select * from student where student like ?";

        try {
            con = DBUtil.getConnection();
            ps = con.prepareStatement(sql);

            ps.setString(1, '%' + Keyword + '%');
            rs = ps.executeQuery();
            while(rs.next()) {
                BoardDTO board = new BoardDTO(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getInt(5));
                list.add(board);
            }
        }catch (Exception e) {
            throw new SearchWrongException("오류 발생");
        }finally {
            DBUtil.dbClose(con, ps, rs);
        }
        return list;
    }

    @Override
    public List<BoardDTO> boardSelectByMajor(String major) throws SearchWrongException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "SELECT student_id, student, major,grade,score FROM STUDENT WHERE MAJOR = ?";
        List<BoardDTO> list = new ArrayList<>();
        try {
            con = DBUtil.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1,major);
            rs =ps.executeQuery();

            while (rs.next()){
                BoardDTO boardDTO = new BoardDTO(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getInt(5));
                list.add(boardDTO);
            }
        }catch (SQLException e) {
            //e.printStackTrace();
            throw new SearchWrongException("DB 오류 발생");
        } finally {
            DBUtil.dbClose(con,ps,rs);
        }

        return list;
    }

    @Override
    public int boardInsert(BoardDTO boardDTO) throws DMLException {
        Connection con = null;
        PreparedStatement ps = null;
        int result = 0;
        String sql = "insert into student (student_id, student, major,grade, score) "
                + "values (?, ?, ?, ?, ?)";

        try {
            con=DBUtil.getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, boardDTO.getStudentID());
            ps.setString(2, boardDTO.getStudent());
            ps.setString(3, boardDTO.getMajor());
            ps.setString(4, boardDTO.getGrade());
            ps.setInt(5, boardDTO.getScore());

            result = ps.executeUpdate();

        }catch(SQLException e){
            throw new DMLException("오류 발생");
        }finally {
            DBUtil.dbClose(con, ps);
        }
        return result;
    }

    @Override
    public int boardUpdate(BoardDTO boardDTO) throws DMLException {
        Connection con = null;
        PreparedStatement ps = null;
        int result = 0;
        String sql = "update student set score = ?, grade = ? where student_id = ?";

        try {
            con = DBUtil.getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, boardDTO.getScore());
            ps.setString(2, boardDTO.getGrade());
            ps.setInt(3, boardDTO.getStudentID());

            result = ps.executeUpdate();

        }catch(SQLException e) {
            throw new DMLException("오류 발생");
        }finally {
            DBUtil.dbClose(con, ps);
        }
        return result;

    }

    @Override
    public int boardDelete(int StudentID) throws DMLException {
        Connection con = null;
        PreparedStatement ps = null;
        int result = 0;
        String sql = "delete from student where student_id=?";

        try {
            con = DBUtil.getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, StudentID);

            result = ps.executeUpdate();

        }catch(SQLException e) {
            throw new DMLException("오류 발생");
        }finally {
            DBUtil.dbClose(con, ps);
        }
        return result;
    }

    @Override
    public int prizeInsert(PrizeDTO prizeDTO) throws DMLException {
        Connection con = null;
        PreparedStatement ps = null;
        int result = 0;
        String sql = "insert into prize(prize_no,prize_name, scholarship , prize_date,student_id)"+
                " values (prize_seq.nextval,?,?,sysdate,?)";
        try {
            con = DBUtil.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1,prizeDTO.getPrizeName());
            ps.setInt(2,prizeDTO.getScholarship());
            ps.setInt(3,prizeDTO.getStudentId());
            result =ps.executeUpdate();

        }catch (SQLException e) {
            //e.printStackTrace();
            throw new DMLException("DB 오류 발생");
        } finally {
            DBUtil.dbClose(con,ps);
        }
        return result;
    }

    @Override
    public BoardDTO prizeSelectAll(int studentId) throws SearchWrongException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        BoardDTO boardDTO = null;
        String sql = "SELECT student_id, student, major,grade,score FROM STUDENT WHERE student_id = ?";

        try {
            con = DBUtil.getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1,studentId);
            rs =ps.executeQuery();

            if (rs.next()){
                boardDTO = new BoardDTO(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getInt(5));
                List<PrizeDTO> list = this.prizeSelect(con,studentId);
                boardDTO.setPrizeList(list);
            }
        }catch (SQLException e) {
            //e.printStackTrace();
            throw new SearchWrongException("DB 오류 발생");
        } finally {
            DBUtil.dbClose(con,ps,rs);
        }

        return boardDTO;
    }
    private  List<PrizeDTO> prizeSelect (Connection con,int studentId) throws SQLException{
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<PrizeDTO> list = new ArrayList<>();
        String sql = "select prize_no,prize_name, scholarship, prize_date, student_id from prize where student_id = ?";
        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, studentId);
            rs = ps.executeQuery();
            while (rs.next()) {
                PrizeDTO prizeDTO = new PrizeDTO(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getString(4),rs.getInt(5));
                list.add(prizeDTO);
            }
        }finally {
            DBUtil.dbClose(null,ps,rs);
        }
        return list;
    }
}
