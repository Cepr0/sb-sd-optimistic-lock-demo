package io.github.cepr0.demo;

import org.springframework.beans.BeanUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.function.BiFunction;

@Transactional
@Service
public class ModelService {

	private final ModelRepo modelRepo;
	private final BiFunction<Model, Model, Model> modelMapper;

	public ModelService(ModelRepo modelRepo, BiFunction<Model, Model, Model> modelMapper) {
		this.modelRepo = modelRepo;
		this.modelMapper = modelMapper;
	}

	public Model create(Model model) {
		return modelRepo.save(model);
	}

	public Optional<Model> update(int id, Model source) {
		return modelRepo
				.findById(id)
				.map(model -> modelMapper.apply(model, source));
	}

	@Transactional(readOnly = true)
	public List<Model> getAll() {
		return modelRepo.findAll();
	}

	@Configuration
	public static class MapperConfig {
		@Bean
		public BiFunction modelMapper() {
			return  (target, source) -> {
				BeanUtils.copyProperties(source, target, "id", "version");
				return target;
			};
		}
	}
}
