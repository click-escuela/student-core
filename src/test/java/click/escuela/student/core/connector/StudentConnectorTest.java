package click.escuela.student.core.connector;

import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.mockito.Mockito.verify;
import java.util.UUID;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.test.util.ReflectionTestUtils;

import click.escuela.student.core.exception.StudentException;
import click.escuela.student.core.feign.StudentController;

@RunWith(MockitoJUnitRunner.class)
public class StudentConnectorTest {

	private StudentConnector studentConnector = new StudentConnector();

	@Mock
	private StudentController studentController;

	private UUID studentId;
	private String schoolId;
	private Boolean fullDetail;

	@Before
	public void setUp() throws StudentException {
		studentId = UUID.randomUUID();
		schoolId = "1234";
		fullDetail = false;

		ReflectionTestUtils.setField(studentConnector, "studentController", studentController);
	}

	@Test
	public void whenGetByIdIsOk() throws StudentException {
		studentConnector.getById(schoolId.toString(), studentId.toString(), fullDetail);
		verify(studentController).getById(schoolId, studentId.toString(), fullDetail);
	}

	@Test
	public void whenGetByIdIsError() throws StudentException {
		Mockito.when(studentController.getById(schoolId.toString(), studentId.toString(), fullDetail))
				.thenThrow(StudentException.class);
		assertThatExceptionOfType(StudentException.class).isThrownBy(() -> {
			studentConnector.getById(schoolId.toString(), studentId.toString(), fullDetail);
		}).withMessage(null);
	}
}
