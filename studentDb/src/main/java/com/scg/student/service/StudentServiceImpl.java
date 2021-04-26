package main.java.com.scg.student.service;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import main.java.com.scg.student.dao.StudentDAO;
import main.java.com.scg.student.dao.StudentDAOImpl;
import main.java.com.scg.student.vo.StudentVO;

public class StudentServiceImpl implements StudentService {
	StudentValidator validation = new StudentValidator();
	ConsoleReader consoleReader = new ConsoleReader();

	private StudentDAO studentDAO;

	public StudentServiceImpl() {
		studentDAO = new StudentDAOImpl();

	}

	public void insertStudent(StudentVO studentVO)
			throws IOException, UserInputException, ClassNotFoundException, SQLException {

		validation.validateObject(studentVO);
//		validation.validateId(studentVO.getId());
//		validation.validateAge(studentVO.getAge());
//		validation.validateString(studentVO.getName());	
		studentDAO.insertStudent(studentVO);

	}

	public void deleteStudentById(int id)
			throws NumberFormatException, IOException, UserInputException, ClassNotFoundException, SQLException {

		validation.validateId(id);
		int numberOfRecordsDeleted = studentDAO.deleteStudentById(id);
		System.out.println(String.format("%d record deleted", numberOfRecordsDeleted));

	}

	public void display(StudentVO studentVO) {
		System.out.println("Id is  : " + studentVO.getId());
		System.out.println("Name is : " + studentVO.getName());
		System.out.println("Age id : " + studentVO.getAge());
		System.out.println("*********************************");

	}

	public void selectAllStudent() throws ClassNotFoundException, SQLException {

		List<StudentVO> studentList = studentDAO.selectAllStudent();
		for (StudentVO studentVO : studentList) {
			display(studentVO);
		}
	}

	public void updateStudent(StudentVO updateRequest)
			throws NumberFormatException, IOException, UserInputException, ClassNotFoundException, SQLException {

		validation.validateObject(updateRequest);

		int numberOfRecordsUpdated = studentDAO.updateStudent(updateRequest);
		System.out.println(String.format("%d record updated", numberOfRecordsUpdated));
	}

	public void searchStudentById(int id) throws ClassNotFoundException, SQLException, UserInputException {
		validation.validateId(id);
		StudentVO studentVO = studentDAO.searchStudentById(id);
		display(studentVO);

	}

	public void searchStudentByPattern(String pattern)
			throws IOException, SQLException, ClassNotFoundException, UserInputException {
		validation.validateString(pattern);
		List<StudentVO> studentSubList = studentDAO.searchStudentByPattern(pattern);
		for (StudentVO studentVO : studentSubList) {
			display(studentVO);
		}

	}

	public void selectStudentByPaging(int pageNumber) throws ClassNotFoundException, SQLException {

		final int PAGE_SIZE = 3;

		int fromIndex = (PAGE_SIZE * pageNumber) - PAGE_SIZE;

		List<StudentVO> studentSubList = studentDAO.selectStudentByPaging(fromIndex, PAGE_SIZE);

		for (StudentVO studentVO : studentSubList) {
			display(studentVO);
		}

	}

}
