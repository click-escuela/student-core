package click.escuela.student.core.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.MediaType;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import click.escuela.student.core.dto.ActivityDTO;
import click.escuela.student.core.dto.GradeDTO;
import click.escuela.student.core.exception.TransactionException;
import click.escuela.student.core.rest.StudentController;
import click.escuela.student.core.rest.handler.Handler;
import click.escuela.student.core.service.impl.ActivityServiceImpl;
import click.escuela.student.core.service.impl.GradeServiceImpl;

@EnableWebMvc
@RunWith(MockitoJUnitRunner.class)
public class StudentControllerTest {

	private MockMvc mockMvc;

	@InjectMocks
	private StudentController studentController;

	@Mock
	private GradeServiceImpl studentService;
	
	@Mock
	private ActivityServiceImpl activitytService;

	private ObjectMapper mapper;
	private String studentId;
	private String gradeId;
	private String activityId;
	private String schoolId;
	private GradeDTO gradeDTO;
	private ActivityDTO activityDTO;
	private final static String URL = "/school/{schoolId}/student";

	@Before
	public void setup() throws TransactionException {
		mockMvc = MockMvcBuilders.standaloneSetup(studentController).setControllerAdvice(new Handler()).build();
		mapper = new ObjectMapper().findAndRegisterModules().disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES)
				.configure(DeserializationFeature.UNWRAP_ROOT_VALUE, false)
				.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);

		studentId = UUID.randomUUID().toString();
		schoolId = "1234";
		gradeId = UUID.randomUUID().toString();
		gradeDTO = GradeDTO.builder().id(gradeId).name("Examen").subject("Matematica").type("HOMEWORK").number(10)
				.build();
		List<GradeDTO> gradesDTO = new ArrayList<>();
		gradesDTO.add(gradeDTO);
		activityId = UUID.randomUUID().toString(); 
		activityDTO = ActivityDTO.builder().id(activityId).name("Historia de las catatumbas").subject("Historia")
				.type("HOMEWORK").schoolId(Integer.valueOf(schoolId)).studentId(UUID.fromString(studentId))
				.dueDate(LocalDate.now()).description("Resolver todos los puntos").build();
		List<ActivityDTO> activitiesDTO = new ArrayList<>();
		activitiesDTO.add(activityDTO);
		
		Mockito.when(studentService.getGrades(schoolId.toString(), studentId.toString())).thenReturn(gradesDTO);
		Mockito.when(activitytService.getActivitiesByStudentId(schoolId.toString(), studentId.toString())).thenReturn(activitiesDTO);

		ReflectionTestUtils.setField(studentController, "studentService", studentService);
	}

	@Test
	public void getGradesIsOk() throws JsonProcessingException, Exception {
		assertThat(mapper.readValue(result(get(URL + "/{studentId}/grades", schoolId.toString(), studentId.toString())),
				new TypeReference<List<GradeDTO>>() {
				}).get(0).getId()).contains(gradeId.toString());
	}

	@Test
	public void getGradesIsError() throws JsonProcessingException, Exception {
		studentId = UUID.randomUUID().toString();
		assertThat(mapper.readValue(result(get(URL + "/{studentId}/grades", schoolId.toString(), studentId.toString())),
				new TypeReference<List<GradeDTO>>() {
				})).isEmpty();
	}
	
	@Test
	public void getActivitiesIsOk() throws JsonProcessingException, Exception {
		assertThat(mapper.readValue(result(get(URL + "/{studentId}/activities", schoolId.toString(), studentId.toString())),
				new TypeReference<List<ActivityDTO>>() {
				}).get(0).getId()).contains(activityId.toString());
	}

	@Test
	public void getActivitiesIsError() throws JsonProcessingException, Exception {
		studentId = UUID.randomUUID().toString();
		assertThat(mapper.readValue(result(get(URL + "/{studentId}/activities", schoolId.toString(), studentId.toString())),
				new TypeReference<List<ActivityDTO>>() {
				})).isEmpty();
	}

	private String result(MockHttpServletRequestBuilder requestBuilder) throws JsonProcessingException, Exception {
		return mockMvc.perform(requestBuilder.contentType(MediaType.APPLICATION_JSON)).andReturn().getResponse()
				.getContentAsString();
	}

}
