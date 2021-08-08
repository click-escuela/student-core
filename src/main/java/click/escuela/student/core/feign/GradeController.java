package click.escuela.student.core.feign;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import click.escuela.student.core.dto.GradeDTO;


@FeignClient(name = "grades")
public interface GradeController {

	@GetMapping(value = "/school/{schoolId}/grade/student/{studentId}")
	public List<GradeDTO> getByStudent(@PathVariable("schoolId") String schoolId,
			@PathVariable("studentId") String studentId);
}
