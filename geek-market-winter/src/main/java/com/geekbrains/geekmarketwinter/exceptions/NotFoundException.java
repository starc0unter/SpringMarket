package com.geekbrains.geekmarketwinter.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "unable to find requested resource")
public final class NotFoundException extends RuntimeException {
}
