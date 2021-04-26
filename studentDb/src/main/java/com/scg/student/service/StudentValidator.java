package main.java.com.scg.student.service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import main.java.com.scg.student.dao.StudentDAOImpl;
import main.java.com.scg.student.vo.StudentVO;

public class StudentValidator {
	
	StudentDAOImpl studentDAO=new StudentDAOImpl();
	public void validateId(int id) throws UserInputException {
		if(id<=0) {
			throw new UserInputException("Invalid Id");
		
		}
	}
	public void validateAge(int age) throws UserInputException {
		if(age<5||age>35) {
			throw new UserInputException("Invalid Age (Between 5-35) ");
		}
	}
	
	
	public void validateString(String string) throws UserInputException {
		String format="^[a-zA-Z\\s]*$";
		Pattern pattern = Pattern.compile(new String (format));
	    Matcher matcher = pattern.matcher(string);
	    if(!matcher.matches()){
	    	throw new UserInputException("Invalid String Input");
	    }
	}
	
	public void validateObject(StudentVO studentVO) throws UserInputException {
		if(studentVO.getId()<=0) {
			throw new UserInputException("Invalid Id");

		}
		
		if(studentVO.getAge()<5||studentVO.getAge()>35) {
			throw new UserInputException("Invalid Age (Between 5-35) ");
		}
		
		String format="^[a-zA-Z\\s]*$";
		Pattern pattern = Pattern.compile(new String (format));
	    Matcher matcher = pattern.matcher(studentVO.getName());
	    if(!matcher.matches()){
	    	throw new UserInputException("Invalid String Input");
	    }
		
		
	}
		
	

}
