package br.com.nith.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Video {
	@Id
	@GeneratedValue
	private Integer id;
	private String name;
	private String url;
	private String viId;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getViId() {
		return viId;
	}
	public void setViId(String viId) {
		this.viId = viId;
	}
	
}
