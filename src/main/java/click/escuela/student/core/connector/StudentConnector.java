package click.escuela.student.core.connector;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import click.escuela.student.core.dto.StudentDTO;
import click.escuela.student.core.exception.StudentException;
import click.escuela.student.core.feign.StudentController;

@Service
public class StudentConnector{

	@Autowired
	private StudentController studentController;
	
	public StudentDTO getById(String schoolId, String studentId, Boolean fullDetail) throws StudentException {
		return studentController.getById(schoolId, studentId, fullDetail);
	}

}
