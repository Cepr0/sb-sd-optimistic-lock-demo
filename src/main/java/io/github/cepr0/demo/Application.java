package io.github.cepr0.demo;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.core.annotation.Order;

@Slf4j
@SpringBootApplication
public class Application {

	private final ModelService modelService;

	private Model model;

	public Application(ModelService modelService) {
		this.modelService = modelService;
	}

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Order(1)
	@EventListener(ApplicationReadyEvent.class)
	public void create() {
		log.info("[i] Creating...");
		model = modelService.create(new Model().setName("model"));
	}

	@Order(2)
	@EventListener(ApplicationReadyEvent.class)
	public void update() {
		log.info("[i] Updating...");
		modelService.update(model.getId(), new Model().setName("model_updated"));
	}

	@Order(3)
	@EventListener(ApplicationReadyEvent.class)
	public void getAll() {
		log.info("[i] Getting...");
		modelService.getAll().forEach(System.out::println);
	}
}
