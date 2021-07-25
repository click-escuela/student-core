package click.escuela.student.core.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import click.escuela.student.core.connector.ActivityConnector;
import click.escuela.student.core.connector.StudentConnector;
import click.escuela.student.core.dto.ActivityDTO;
import click.escuela.student.core.exception.TransactionException;

@Service
public class ActivityServiceImpl {

	@Autowired
	private StudentConnector studentConnector;
	
	@Autowired
	private ActivityConnector activityConnector;
	
	public List<ActivityDTO> getActivitiesByStudentId(String schoolId, String studentId) throws TransactionException {
		studentConnector.getById(schoolId, studentId, false);
		return activityConnector.getActivitiesByStudentId(schoolId, studentId);
	}

}
