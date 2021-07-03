package click.escuela.student.core.connector;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import click.escuela.student.core.dto.GradeDTO;
import click.escuela.student.core.feign.GradeController;

@Service
public class GradeConnector {

	@Autowired
	private GradeController gradeController;

	public List<GradeDTO> getByStudent(String schoolId, String studentId) {
		return gradeController.getByStudent(schoolId, studentId);
	}
}
