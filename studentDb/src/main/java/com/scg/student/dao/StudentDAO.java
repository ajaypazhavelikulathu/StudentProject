package main.java.com.scg.student.dao;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import main.java.com.scg.student.service.UserInputException;
import main.java.com.scg.student.vo.StudentVO;

public interface StudentDAO {
	void insertStudent(StudentVO studentVo) throws ClassNotFoundException, SQLException, UserInputException;
	int deleteStudentById(int id) throws NumberFormatException, IOException, UserInputException, ClassNotFoundException, SQLException;
//	void display(StudentVO studentVO);
	List<StudentVO> selectAllStudent() throws ClassNotFoundException, SQLException;
	int updateStudent(StudentVO updateRequest) throws NumberFormatException, IOException, ClassNotFoundException, SQLException, UserInputException;
	StudentVO searchStudentById(int id) throws ClassNotFoundException, SQLException, UserInputException;
	List<StudentVO> searchStudentByPattern(String pattern) throws ClassNotFoundException, SQLException;
	public Boolean checkIdExist(int id) throws ClassNotFoundException, SQLException, UserInputException;

	List<StudentVO> selectStudentByPaging(int pageNumber,int PAGE_SIZE) throws SQLException, ClassNotFoundException;
	//void arrangeListToMap();
	
	
	
}
