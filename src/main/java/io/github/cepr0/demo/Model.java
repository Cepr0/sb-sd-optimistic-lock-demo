package io.github.cepr0.demo;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Version;

@Data
@EqualsAndHashCode(of = "id")
@Entity
public class Model {

	@Id
	@GeneratedValue
	private Integer id;

	@Version
	private Integer version;

	private String name;
}
