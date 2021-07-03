package click.escuela.student.core.exception;

import click.escuela.student.core.enumerator.StudentMessage;

public class StudentException extends TransactionException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public StudentException(StudentMessage studentMessage) {
		super(studentMessage.getCode() ,studentMessage.getDescription());
	}

}
