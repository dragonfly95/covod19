package com.project.covid19;

import com.project.covid19.entity.Naver;
import com.project.covid19.repository.NaverRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.util.UUID;

@EnableScheduling
@SpringBootApplication
public class Covod19Application implements CommandLineRunner {
	public static void main(String[] args) {
		SpringApplication.run(Covod19Application.class, args);
	}

	@Bean
	public ModelMapper modelMapper(){
		return new ModelMapper();
	}

	@Autowired
	private NaverRepository naverRepository;

	@Override
	public void run(String... args) throws Exception {
		final Naver ts = naverRepository.save(Naver.builder()
//						.id(UUID.randomUUID())
				.link("ts")
				.build());
		final int i = naverRepository.countById(ts.getId());

		Object[] tmp = naverRepository.findColumnById(ts.getId());
		System.out.println(tmp);
		System.out.println(i);
	}
}
