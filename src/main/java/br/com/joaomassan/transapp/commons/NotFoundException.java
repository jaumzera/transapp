package br.com.joaomassan.transapp.commons;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class NotFoundException extends RuntimeException {

  public static NotFoundException withMessage(String message) {
    return new NotFoundException(message);
  }

  private NotFoundException(String message) {
    super(message);
  }
}
