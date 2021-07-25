package click.escuela.student.core.connector;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import click.escuela.student.core.dto.ActivityDTO;
import click.escuela.student.core.feign.ActivityController;

@Service
public class ActivityConnector {

	@Autowired
	private ActivityController activityController;

	public List<ActivityDTO> getActivitiesByStudentId(String schoolId, String studentId) {
		return activityController.getActivitiesByStudentId(schoolId, studentId);
	}
}
