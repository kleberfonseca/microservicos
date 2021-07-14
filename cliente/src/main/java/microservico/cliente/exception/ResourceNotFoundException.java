package microservico.cliente.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 4874855001438882855L;

	public ResourceNotFoundException(String exception) {
		super(exception);
	}
}