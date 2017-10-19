package com.reflections;

import com.reflections.annotations.TablesAndColumnsCreator;
import com.reflections.annotations.MyAnnotation;
import com.reflections.pairs.GenericClassExecutor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.sql.SQLException;

@SpringBootApplication
public class KodillaReflectionsApplication {

	public static void main(String[] args) throws SQLException {
		String test = "Kodilladasda";
		System.out.println("Test: " + test.indexOf("Kodilla"));

		SpringApplication.run(KodillaReflectionsApplication.class, args);
		GenericClassExecutor.genericClassExecuting();
		System.out.println();
		MyAnnotation.testingAnnotations();

		//TablesAndColumnsCreator.createTablesAndColumns();



	}
}
