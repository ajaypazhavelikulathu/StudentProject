package main.java.com.scg.student.service;

import java.io.IOException;
import java.sql.SQLException;

import main.java.com.scg.student.vo.StudentVO;

public interface StudentService {
	public void insertStudent(StudentVO studentVO) throws IOException, UserInputException, ClassNotFoundException, SQLException;
	void deleteStudentById(int id) throws NumberFormatException, IOException, UserInputException, ClassNotFoundException, SQLException;
	public void selectAllStudent() throws ClassNotFoundException, SQLException;
	public void updateStudent(StudentVO updateRequest) throws NumberFormatException, IOException, UserInputException, ClassNotFoundException, SQLException;
	public void searchStudentById(int id) throws IOException, ClassNotFoundException, SQLException, UserInputException;
	
	public void searchStudentByPattern(String name) throws IOException, ClassNotFoundException, SQLException, UserInputException; 
	void selectStudentByPaging(int pageNumber) throws ClassNotFoundException, SQLException;

}
