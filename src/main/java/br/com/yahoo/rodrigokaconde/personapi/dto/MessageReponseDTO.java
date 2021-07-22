package br.com.yahoo.rodrigokaconde.personapi.dto;

import lombok.Builder;
import lombok.Data;

import javax.persistence.Basic;

@Data
@Builder
public class MessageReponseDTO {
    private String message;
}
