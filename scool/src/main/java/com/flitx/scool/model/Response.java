package com.flitx.scool.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.Data;
import lombok.experimental.SuperBuilder;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.util.Map;


@Data
@SuperBuilder
@JsonInclude(JsonInclude.Include.NON_NULL)
//@JsonDeserialize(builder = VehicleDTO.VehicleDTOBuilder.class)
public class Response {

    protected LocalDateTime timeStamp;
    protected int statusCode;
    protected HttpStatus status;
    protected String reason;
    protected String message;
    protected String devMessages;
    protected Map<?,?> data;

    public Response(LocalDateTime timeStamp, int statusCode, HttpStatus status, String reason, String message, String devMessages, Map<?, ?> data) {
        this.timeStamp = timeStamp;
        this.statusCode = statusCode;
        this.status = status;
        this.reason = reason;
        this.message = message;
        this.devMessages = devMessages;
        this.data = data;
    }
}
