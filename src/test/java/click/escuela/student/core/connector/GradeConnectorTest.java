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

import click.escuela.student.core.dto.GradeDTO;
import click.escuela.student.core.exception.TransactionException;
import click.escuela.student.core.feign.GradeController;

@RunWith(MockitoJUnitRunner.class)
public class GradeConnectorTest {

	@Mock
	private GradeController gradeController;

	private GradeConnector gradeConnector = new GradeConnector();
	private String studentId;
	private String schoolId;

	@Before
	public void setUp() throws TransactionException {
		studentId = UUID.randomUUID().toString();
		schoolId = "1234";

		ReflectionTestUtils.setField(gradeConnector, "gradeController", gradeController);
	}

	@Test
	public void whenGetByStudentIsOk() {
		gradeConnector.getByStudent(schoolId.toString(), studentId.toString());
		verify(gradeController).getByStudent(schoolId, studentId);
	}

	@Test
	public void whenGetByStudentIsEmpty() {
		when(gradeController.getByStudent(Mockito.any(), Mockito.any())).thenReturn(new ArrayList<>());
		List<GradeDTO> grades = gradeConnector.getByStudent(schoolId, studentId);
		assertThat(grades.isEmpty()).isTrue();
	}

}
