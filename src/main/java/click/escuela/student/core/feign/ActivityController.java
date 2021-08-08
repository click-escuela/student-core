package click.escuela.student.core.feign;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import click.escuela.student.core.dto.ActivityDTO;


@FeignClient(name = "activities")
public interface ActivityController {

	@GetMapping(value = "/click-escuela/school/{schoolId}/activity/student/{studentId}")
	public List<ActivityDTO> getActivitiesByStudentId(@PathVariable("schoolId") String schoolId,
			@PathVariable("studentId") String studentId);
}
