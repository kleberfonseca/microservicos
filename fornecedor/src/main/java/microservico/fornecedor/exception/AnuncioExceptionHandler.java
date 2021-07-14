package microservico.fornecedor.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
@RestController
public class AnuncioExceptionHandler extends ResponseEntityExceptionHandler{
	@ExceptionHandler(ResourceNotFoundException.class)
	public final ResponseEntity<AnuncioExceptionResponse> handlerBadRequestException(Exception ex, WebRequest request){
		AnuncioExceptionResponse exceptionResponse = new AnuncioExceptionResponse();
		return new ResponseEntity<AnuncioExceptionResponse>(exceptionResponse, HttpStatus.BAD_REQUEST);
	}
}


	


