package click.escuela.student.core.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import click.escuela.student.core.dto.StudentDTO;
import click.escuela.student.core.exception.StudentException;

@FeignClient(name = "school-admin")
public interface StudentController {

	@GetMapping(value = "/school/{schoolId}/student/{studentId}")
	public StudentDTO getById(@PathVariable("schoolId") String schoolId, @PathVariable("studentId") String studentId,
			@RequestParam("fullDetail") Boolean fullDetail) throws StudentException;

}