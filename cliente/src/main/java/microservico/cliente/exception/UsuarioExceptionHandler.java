package microservico.cliente.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
@RestController
public class UsuarioExceptionHandler extends ResponseEntityExceptionHandler{
	@ExceptionHandler(ResourceNotFoundException.class)
	public final ResponseEntity<UsuarioExceptionResponse> handlerBadRequestException(Exception ex, WebRequest request){
		UsuarioExceptionResponse exceptionResponse = new UsuarioExceptionResponse();
		return new ResponseEntity<UsuarioExceptionResponse>(exceptionResponse, HttpStatus.BAD_REQUEST);
	}
}


	


