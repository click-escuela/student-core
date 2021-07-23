package click.escuela.student.core.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import click.escuela.student.core.connector.GradeConnector;
import click.escuela.student.core.connector.StudentConnector;
import click.escuela.student.core.dto.GradeDTO;
import click.escuela.student.core.exception.TransactionException;

@Service
public class GradeServiceImpl {

	@Autowired
	private StudentConnector studentConnector;
	
	@Autowired
	private GradeConnector gradeConnector;
	
	public List<GradeDTO> getGrades(String schoolId, String studentId) throws TransactionException {
		studentConnector.getById(schoolId, studentId, false);
		return gradeConnector.getByStudent(schoolId, studentId);
	}

}
