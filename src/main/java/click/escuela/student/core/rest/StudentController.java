package click.escuela.student.core.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import click.escuela.student.core.dto.ActivityDTO;
import click.escuela.student.core.dto.GradeDTO;
import click.escuela.student.core.dto.StudentDTO;
import click.escuela.student.core.exception.TransactionException;
import click.escuela.student.core.service.impl.ActivityServiceImpl;
import click.escuela.student.core.service.impl.GradeServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

@RestController
@RequestMapping(path = "/school/{schoolId}/student")
public class StudentController {

	@Autowired
	private GradeServiceImpl studentService;
	
	@Autowired
	private ActivityServiceImpl activitytService;

	@Operation(summary = "Get grades by studentId", responses = {
			@ApiResponse(responseCode = "200", content = @Content(mediaType = "application/json", schema = @Schema(implementation = StudentDTO.class))) })
	@GetMapping("/{studentId}/grades")
	public ResponseEntity<List<GradeDTO>> getGradesByStudentId(@PathVariable("schoolId") String schoolId,
			@PathVariable("studentId") String studentId) throws TransactionException {
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(studentService.getGrades(schoolId, studentId));
	}
	
	@Operation(summary = "Get activities by studentId", responses = {
			@ApiResponse(responseCode = "200", content = @Content(mediaType = "application/json", schema = @Schema(implementation = StudentDTO.class))) })
	@GetMapping("/{studentId}/activities")
	public ResponseEntity<List<ActivityDTO>> getActivitiesByStudentId(@PathVariable("schoolId") String schoolId,
			@PathVariable("studentId") String studentId) throws TransactionException {
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(activitytService.getActivitiesByStudentId(schoolId, studentId));
	}
	
}
