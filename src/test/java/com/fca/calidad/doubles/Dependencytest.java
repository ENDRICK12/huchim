package com.fca.calidad.doubles;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.fca.calidad.doubles.Dependency.SubDependency;

class Dependencytest {
private Dependency dependency;
private SubDependency sub ;


@BeforeEach
void setup() {
	sub =mock(SubDependency.class);
	dependency=new Dependency(sub);
	
}

	@Test
	void test() {
		System.out.println(sub.getClassName());
	}
	
	
	@Test

	
	public void testsubDependency () {
		//inicializacion 
		when (sub.getClassName()).thenReturn("hola");
		//ejercicio
		String result =sub.getClassName();
		//verificacion
		
		
	//	assertThat(resultadoReal,is(resultadoEsperado));
		return ;
	}
	
	
	
	
	

}
