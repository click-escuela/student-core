package click.escuela.student.core.connector;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.test.util.ReflectionTestUtils;

import click.escuela.student.core.dto.ActivityDTO;
import click.escuela.student.core.exception.TransactionException;
import click.escuela.student.core.feign.ActivityController;

@RunWith(MockitoJUnitRunner.class)
public class ActivityConnectorTest {

	@Mock
	private ActivityController activityController;

	private ActivityConnector activityConnector = new ActivityConnector();
	private String studentId;
	private String schoolId;

	@Before
	public void setUp() throws TransactionException {
		studentId = UUID.randomUUID().toString();
		schoolId = "1234";

		ReflectionTestUtils.setField(activityConnector, "activityController", activityController);
	}

	@Test
	public void whenGetByStudentIsOk() {
		activityConnector.getActivitiesByStudentId(schoolId.toString(), studentId.toString());
		verify(activityController).getActivitiesByStudentId(schoolId, studentId);
	}

	@Test
	public void whenGetByStudentIsEmpty() {
		when(activityController.getActivitiesByStudentId(Mockito.any(), Mockito.any())).thenReturn(new ArrayList<>());
		List<ActivityDTO> activities = activityConnector.getActivitiesByStudentId(schoolId, studentId);
		assertThat(activities.isEmpty()).isTrue();
	}

}
