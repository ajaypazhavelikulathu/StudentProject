package main.java.com.scg.student.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import main.java.com.scg.student.service.Jdbc;
import main.java.com.scg.student.service.UserInputException;
import main.java.com.scg.student.vo.StudentVO;

public class StudentDAOImpl implements StudentDAO {

	private Connection con;

	public Boolean checkIdExist(int id) throws ClassNotFoundException, SQLException, UserInputException {

		con = Jdbc.getConnection();

		final String checkIdExist = String.format("select id from student where id=%d", id);
		PreparedStatement preparedStatement = con.prepareStatement(checkIdExist);
		ResultSet resultSet = preparedStatement.executeQuery();
		if (resultSet.next()) {
			return true;

		}
		throw new UserInputException("Id does not exist");

	}

	public void insertStudent(StudentVO studentVO) throws ClassNotFoundException, SQLException, UserInputException {

		con = Jdbc.getConnection();

		int id = studentVO.getId();
		boolean isTransactionSuccessful = false;
		String name = studentVO.getName();
		int age = studentVO.getAge();
		int a=20;
		String release1="release1";
		String ajay="ajay";
		final String insertQuery = "insert into student values(?,?,?)";
		try {
			PreparedStatement preparedStatement = con.prepareStatement(insertQuery);
			preparedStatement.setInt(1, id);
			preparedStatement.setString(2, name);
			preparedStatement.setInt(3, age);
			int numberOfStudentUpdated = preparedStatement.executeUpdate();
			if (numberOfStudentUpdated > 0) {
				isTransactionSuccessful = true;
			}
			preparedStatement.close();
		} catch (SQLException e) {
			System.out.println(e);
		}

		finally {
			if (isTransactionSuccessful) {
				con.commit();
			} else {
				con.rollback();
			}

			con.close();

		}

	}

	public int deleteStudentById(int id)
			throws NumberFormatException, IOException, UserInputException, ClassNotFoundException, SQLException {

		con = Jdbc.getConnection();
		int numberOfRecordsDeleted = 0;
		boolean isTransactionSuccessful = false;

		if (checkIdExist(id)) {
			try {
				final String deleteQuery = String.format("delete from student where id=%d", id);
				PreparedStatement preparedStatement = con.prepareStatement(deleteQuery);
				numberOfRecordsDeleted = preparedStatement.executeUpdate();
				preparedStatement.close();

				if (numberOfRecordsDeleted > 0) {
					isTransactionSuccessful = true;
				}
			} catch (SQLException e) {
				System.out.println(e);
			} finally {
				if (isTransactionSuccessful) {
					con.commit();
				} else {
					con.rollback();
				}
				con.close();
			}
		}
		return numberOfRecordsDeleted;

	}

	public int updateStudent(StudentVO updateRequest)
			throws NumberFormatException, IOException, ClassNotFoundException, SQLException, UserInputException {
		int numberOfRecordsUpdated = 0;
		int id = updateRequest.getId();
		boolean isTransactionSuccessfull = false;
		String newName = updateRequest.getName();
		int newAge = updateRequest.getAge();
		con = Jdbc.getConnection();
		final String updateQuery = "update student set name=?,age=? where id=?";

		if (checkIdExist(id)) {
			try {
				PreparedStatement preparedStatement = con.prepareStatement(updateQuery);
				preparedStatement.setString(1, newName);
				preparedStatement.setInt(2, newAge);
				preparedStatement.setInt(3, id);

				numberOfRecordsUpdated = preparedStatement.executeUpdate();
				preparedStatement.close();
				if (numberOfRecordsUpdated > 0) {
					isTransactionSuccessfull = true;
				}
			} catch (SQLException e) {
				System.out.println(e);
			} finally {
				if (isTransactionSuccessfull) {
					con.commit();
				} else {
					con.rollback();
				}
				con.close();

			}

		}
		return numberOfRecordsUpdated;
	}

	public StudentVO searchStudentById(int id) throws ClassNotFoundException, SQLException, UserInputException {

		con = Jdbc.getConnection();
		StudentVO studentVO = null;
		boolean isTransactionSuccessfull = false;

		if (checkIdExist(id)) {

			try {
				final String selectStudent = "select *from student where id=(?)";
				PreparedStatement preparedStatement = con.prepareStatement(selectStudent);
				preparedStatement.setInt(1, id);
				ResultSet resultSet = preparedStatement.executeQuery();
				if (resultSet.next()) {
					isTransactionSuccessfull = true;

				}

				int id1 = resultSet.getInt("id");
				String name = resultSet.getString("name");
				int age = resultSet.getInt("age");

				studentVO = new StudentVO(id1, name, age);
			} catch (SQLException e) {
				System.out.println(e);
			} finally {
				if (isTransactionSuccessfull) {
					con.commit();
				} else {
					con.rollback();
				}
				con.close();
			}
			

		}

		return studentVO;

	}

	public List<StudentVO> searchStudentByPattern(String name) throws ClassNotFoundException, SQLException {

		List<StudentVO> subList = new ArrayList<>();
		boolean isTransactionSuccessfull=false;

		con = Jdbc.getConnection();
		try {
		final String selectStudentWithPattern = "select *from student where name like '%" + name + "%'";

		PreparedStatement preparedStatement = con.prepareStatement(selectStudentWithPattern);
		ResultSet resultSet = preparedStatement.executeQuery();
		while (resultSet.next()) {
			int id = resultSet.getInt("id");
			String name1 = resultSet.getString("name");
			int age = resultSet.getInt("age");

			StudentVO studentVO = new StudentVO(id, name1, age);

			subList.add(studentVO);
			isTransactionSuccessfull=true;
		}
		}
		catch(SQLException e) {
			System.out.println(e);
			
		}
		finally {
			if(isTransactionSuccessfull) {
				con.commit();
			}
			else {
				con.rollback();
			}
			con.close();
		}

		return subList;

	}

	public List<StudentVO> selectStudentByPaging(int fromIndex, int PAGE_SIZE)
			throws SQLException, ClassNotFoundException {
		List<StudentVO> studentSubList = new ArrayList<>();
		con = Jdbc.getConnection();
		String selectAllStudent = "select *from student limit " + PAGE_SIZE + " offset " + fromIndex;
		PreparedStatement preparedStatement = con.prepareStatement(selectAllStudent);
		ResultSet resultSet = preparedStatement.executeQuery();
//		 String selectAllStudent="select  *from student OFFSET "+fromIndex+" ROWS FETCH NEXT " +toIndex+ " ROWS ONLY";
		while (resultSet.next()) {
			int id = resultSet.getInt("id");
			String name = resultSet.getString("name");
			int age = resultSet.getInt("age");

			StudentVO studentVO = new StudentVO(id, name, age);

			studentSubList.add(studentVO);
		}

		return studentSubList;
	}

	public List<StudentVO> selectAllStudent() throws ClassNotFoundException, SQLException {

		List<StudentVO> studentList = new ArrayList<>();

		con = Jdbc.getConnection();
		String selectAllStudent = "select  *from student";
		PreparedStatement preparedStatement = con.prepareStatement(selectAllStudent);
		ResultSet resultSet = preparedStatement.executeQuery();
		while (resultSet.next()) {
			int id = resultSet.getInt("id");
			String name = resultSet.getString("name");
			int age = resultSet.getInt("age");
			StudentVO studentVO = new StudentVO(id, name, age);

			studentList.add(studentVO);
		}
		con.close();

		return studentList;
	}
}
