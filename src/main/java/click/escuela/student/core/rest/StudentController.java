package click.escuela.student.core.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import click.escuela.student.core.dto.GradeDTO;
import click.escuela.student.core.dto.StudentDTO;
import click.escuela.student.core.exception.TransactionException;
import click.escuela.student.core.service.impl.StudentServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

@RestController
@RequestMapping(path = "/school/{schoolId}/student")
public class StudentController {

	@Autowired
	private StudentServiceImpl studentService;

	@Operation(summary = "Get student by studentId", responses = {
			@ApiResponse(responseCode = "200", content = @Content(mediaType = "application/json", schema = @Schema(implementation = StudentDTO.class))) })
	@GetMapping("/{studentId}/grades")
	public ResponseEntity<List<GradeDTO>> getById(@PathVariable("schoolId") String schoolId,
			@PathVariable("studentId") String studentId) throws TransactionException {
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(studentService.getGrades(schoolId, studentId));
	}
	
	@GetMapping(value = "prueba")
	public ResponseEntity<String> prueba()
			throws TransactionException {
		return ResponseEntity.status(HttpStatus.ACCEPTED)
				.body("prueba exitosa");
	}
}
