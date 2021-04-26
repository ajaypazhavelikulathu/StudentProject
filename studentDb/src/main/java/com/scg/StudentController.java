package main.java.com.scg;

import java.io.IOException;
import java.sql.SQLException;

import main.java.com.scg.student.service.UserInputException;
import main.java.com.scg.student.vo.StudentVO;
import main.java.com.scg.student.service.ConsoleReader;
import main.java.com.scg.student.service.StudentService;
import main.java.com.scg.student.service.StudentServiceImpl;

public class StudentController {

	static StudentService studentService = new StudentServiceImpl();
	static ConsoleReader consoleReader = new ConsoleReader();

	public void getStudentDetails() throws NumberFormatException, IOException, UserInputException {

		System.out.println("Enter Student Id(Integer),Name(String),Age(Integer)");
		try {
			int id = consoleReader.readInteger();
			String name = consoleReader.readString();
			int age = consoleReader.readInteger();
			StudentVO studentVO = new StudentVO(id, name, age);
//			studentVO.setId(id);
//			studentVO.setName(name);
//			studentVO.setAge(age);
			studentService.insertStudent(studentVO);

		} catch (Exception e) {
			System.out.println("Invalid Type" + e);

		}

	}

	public void deleteStudentById()
			throws NumberFormatException, IOException, UserInputException, ClassNotFoundException, SQLException {
		System.out.println("Enter Student Id For Deletion");
		try {
			int id = consoleReader.readInteger();
			studentService.deleteStudentById(id);
		} catch (UserInputException e) {
			System.out.println(e);
		}

	}

	public void updateStudent()
			throws NumberFormatException, IOException, UserInputException, ClassNotFoundException, SQLException {

		System.out.println("Enter Student Id For Updation");
		int id = consoleReader.readInteger();
//	studentService.searchStudentById(id);
		studentService.searchStudentById(id);

		System.out.println("Enter Details For Updation New name,New age");
//	int newId=consoleReader.readInteger();

		String newName = consoleReader.readString();
		int newAge = consoleReader.readInteger();

		StudentVO updateRequest = new StudentVO(id, newName, newAge);
//	updateRequest.setId(id);
//	updateRequest.setAge(newAge);
//	updateRequest.setName(newName);
		studentService.updateStudent(updateRequest);
	}

	public void searchStudentById() throws IOException, ClassNotFoundException, SQLException, UserInputException {

		System.out.println("Enter the Id for search");
		try {
			int id = consoleReader.readInteger();
			studentService.searchStudentById(id);
		} catch (UserInputException e) {
			System.out.println("Enter Valid Id " + e);
		}
	}

	public void searchStudentByPattern() throws IOException, ClassNotFoundException, SQLException, UserInputException {
		System.out.println("Enter the pattern for search");
		try {
			String pattern = consoleReader.readString();
			studentService.searchStudentByPattern(pattern);
		} catch (Exception e) {
			System.out.println("Enter valid String " + e);
		}
	}

	public void selectStudentByPaging()
			throws NumberFormatException, IOException, ClassNotFoundException, SQLException {
		System.out.println("Enter the page number");
		int pageNumber = consoleReader.readInteger();
		studentService.selectStudentByPaging(pageNumber);
	}

	public static void main(String[] args)
			throws NumberFormatException, IOException, UserInputException, ClassNotFoundException, SQLException {
//		BufferedReader in=new BufferedReader(new InputStreamReader(System.in));
		StudentController studentController = new StudentController();
//		StudentService studentServiceImpl=new StudentServiceImpl();

		while (true) {
			System.out.println("1:Insert Student Details \n" + "2: Delete Student By Id \n"
					+ "3:Display Student Details \n" + "4:Update Student Infromation By Id\n"
					+ "5:Search Student By Id\n" + "6:Search By Pattern\n" + "7:Display Students By Paging\n"
					+ "8:Exit\n" + "***************************");
			System.out.println("Enter choice");
			int choice = consoleReader.readInteger();
			switch (choice) {
			case 1:
				studentController.getStudentDetails();

				break;
			case 2:
				studentController.deleteStudentById();

				break;
			case 3:
				studentService.selectAllStudent();

				break;
			case 4:
				studentController.updateStudent();

				break;
			case 5:
				studentController.searchStudentById();

				break;
			case 6:
				studentController.searchStudentByPattern();
				break;
			case 7:
				studentController.selectStudentByPaging();
				break;
			case 8:
				System.exit(0);

				break;
			default:
				System.out.println("Invalid Entry");
				break;
			}

		}

	}

}
