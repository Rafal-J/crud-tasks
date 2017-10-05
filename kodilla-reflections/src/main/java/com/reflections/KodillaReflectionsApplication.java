package com.reflections;

import com.reflections.annotations.DatabaseCreator;
import com.reflections.annotations.DbManager;
import com.reflections.annotations.MyAnnotation;
import com.reflections.pairs.GenericClassExecutor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.util.Assert;

import java.sql.SQLException;

@SpringBootApplication
public class KodillaReflectionsApplication {

	public static void main(String[] args) throws SQLException {
		//SpringApplication.run(KodillaReflectionsApplication.class, args);
		GenericClassExecutor.genericClassExecuting();
		System.out.println();
		MyAnnotation.testingAnnotations();

		DatabaseCreator.createDatabase();

	}
}
