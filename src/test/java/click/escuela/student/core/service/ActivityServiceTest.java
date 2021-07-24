package click.escuela.student.core.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
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
import org.springframework.test.util.ReflectionTestUtils;

import click.escuela.student.core.connector.ActivityConnector;
import click.escuela.student.core.connector.StudentConnector;
import click.escuela.student.core.dto.ActivityDTO;
import click.escuela.student.core.exception.StudentException;
import click.escuela.student.core.exception.TransactionException;
import click.escuela.student.core.service.impl.ActivityServiceImpl;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class ActivityServiceTest {

	@Mock
	private StudentConnector studentConnector;
	
	@Mock
	private ActivityConnector activityConnector;

	private ActivityServiceImpl activityServiceImpl = new ActivityServiceImpl();
	private String studentId;
	private String schoolId;
	private Boolean fullDetail;

	@Before
	public void setUp() throws TransactionException {
		studentId = UUID.randomUUID().toString();
		schoolId = "1234";
		fullDetail = false;
		
		ReflectionTestUtils.setField(activityServiceImpl, "studentConnector", studentConnector);
		ReflectionTestUtils.setField(activityServiceImpl, "activityConnector", activityConnector);
	}

	@Test
	public void whenGetGradeIsOK() throws TransactionException {
		activityServiceImpl.getActivitiesByStudentId(schoolId, studentId.toString());
		verify(activityConnector).getActivitiesByStudentId(schoolId, studentId);
		verify(studentConnector).getById(schoolId, studentId, fullDetail);
	}

	@Test
	public void whenGetGradesIsEmpty() throws TransactionException {
		when(activityConnector.getActivitiesByStudentId(Mockito.any(), Mockito.any())).thenReturn(new ArrayList<>());
		List<ActivityDTO> activities = activityServiceImpl.getActivitiesByStudentId(schoolId, studentId.toString());
		assertThat(activities.isEmpty()).isTrue();
	}
	
	@Test
	public void whenGetGradesIsError() throws TransactionException {
		Mockito.when(studentConnector.getById(schoolId.toString(), studentId.toString(), fullDetail))
		.thenThrow(StudentException.class);
		assertThatExceptionOfType(StudentException.class).isThrownBy(() -> {
			activityServiceImpl.getActivitiesByStudentId(schoolId.toString(), studentId.toString());
		}).withMessage(null);
	}

}
