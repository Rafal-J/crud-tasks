package com.reflections;

import com.reflections.pairs.GenericClassExecutor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class KodillaReflectionsApplication {

	public static void main(String[] args) {
		//SpringApplication.run(KodillaReflectionsApplication.class, args);
		GenericClassExecutor.genericClassExecuting();
	}
}
