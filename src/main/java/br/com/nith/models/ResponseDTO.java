package br.com.nith.models;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSeeAlso;

import org.springframework.http.HttpStatus;

import br.com.nith.utils.ResponseMessages;


@XmlRootElement
public class ResponseDTO {
	private Object entity;
	private String message;
	private String status;
	private String description;
	private boolean success;
	
	public ResponseDTO(){
	}
	public ResponseDTO(HttpStatus status){
		this.status = status.toString();
		this.message = status.name();
		this.success = status == HttpStatus.OK;
		if(status == HttpStatus.OK){
			this.description = ResponseMessages.SUCESSO.toString();
		}else if(status == HttpStatus.INTERNAL_SERVER_ERROR){
			this.description = ResponseMessages.ERRO_INTERNO.toString();
		}else if(status == HttpStatus.FORBIDDEN){
			this.description = ResponseMessages.PROIBIDO.toString();
		}else if(status == HttpStatus.UNAUTHORIZED){
			this.description = ResponseMessages.NAO_AUTORIZADO.toString();
		}
	}
	public ResponseDTO(HttpStatus status, String description){
		this.status = status.toString();
		this.message = status.name();
		this.success = status == HttpStatus.OK;
		this.description = description;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Object getEntity() {
		return entity;
	}
	public void setEntity(Object entity) {
		this.entity = entity;
	}
	public boolean isSuccess() {
		return success;
	}
	public void setSuccess(boolean success) {
		this.success = success;
	}
	
}
