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

import click.escuela.student.core.connector.GradeConnector;
import click.escuela.student.core.connector.StudentConnector;
import click.escuela.student.core.dto.GradeDTO;
import click.escuela.student.core.exception.StudentException;
import click.escuela.student.core.exception.TransactionException;
import click.escuela.student.core.service.impl.GradeServiceImpl;

import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class StudentServiceTest {

	@Mock
	private StudentConnector studentConnector;
	
	@Mock
	private GradeConnector gradeConnector;

	private GradeServiceImpl gradeServiceImpl = new GradeServiceImpl();
	private String studentId;
	private String schoolId;
	private Boolean fullDetail;

	@Before
	public void setUp() throws TransactionException {
		studentId = UUID.randomUUID().toString();
		schoolId = "1234";
		fullDetail = false;
		
		ReflectionTestUtils.setField(gradeServiceImpl, "studentConnector", studentConnector);
		ReflectionTestUtils.setField(gradeServiceImpl, "gradeConnector", gradeConnector);
	}

	@Test
	public void whenGetGradeIsOK() throws TransactionException {
		gradeServiceImpl.getGrades(schoolId, studentId.toString());
		verify(gradeConnector).getByStudent(schoolId, studentId);
		verify(studentConnector).getById(schoolId, studentId, fullDetail);
	}

	@Test
	public void whenGetGradesIsEmpty() throws TransactionException {
		when(gradeConnector.getByStudent(Mockito.any(), Mockito.any())).thenReturn(new ArrayList<>());
		List<GradeDTO> grades = gradeServiceImpl.getGrades(schoolId, studentId.toString());
		assertThat(grades.isEmpty()).isTrue();
	}
	
	@Test
	public void whenGetGradesIsError() throws TransactionException {
		Mockito.when(studentConnector.getById(schoolId.toString(), studentId.toString(), fullDetail))
		.thenThrow(StudentException.class);
		assertThatExceptionOfType(StudentException.class).isThrownBy(() -> {
			gradeServiceImpl.getGrades(schoolId.toString(), studentId.toString());
		}).withMessage(null);
	}

}
