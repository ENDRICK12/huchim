package com.fca.calidad.doubles;


import static org.junit.Assert.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.invocation.InvocationOnMock;

import com.fca.calidad.doubles.Dependency.SubDependency;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.Matchers.is;

import static org.hamcrest.MatcherAssert.assertThat;
import org.mockito.stubbing.Answer;

class Dependencytest {
private Dependency dependency;
private SubDependency sub ;


@BeforeEach
void setup() {
	sub =mock(SubDependency.class);
	dependency=new Dependency(sub);
	
}

@Test 
void addTwoTest() {

	//inicializacion
	
	when(dependency.addTwo(2)).thenReturn(10);
	int resultadoEsperado =12;

	
//assertThat(resultadoEsperado, is(dependency.addTwo(5)));
	
	
}

@Test 
void addTwoAnswerTest() {
	
	when(dependency.addTwo(anyInt())).thenAnswer(new Answer<Integer>() {
		public Integer answer(InvocationOnMock invocation) throws Throwable{
			int arg=(Integer)invocation.getArguments()[0];
			return 20* 2+10+arg;
		}
	});
	
	
	
}



	@Test	
	void test() {
		System.out.println(sub.getClassName());
	}
	
	
	@Test

	
	
	
	public void testsubDependency () {
		//inicializacion 
		when (sub.getClassName()).thenReturn("hola");
		String resultadoEsperado="hola";
		//ejercicio
		String result =sub.getClassName();
		//verificacion
		
		
	//	assertThat(resultadoReal,is(resultadoEsperado));
		return ;
	}
	
	
	
	
	

}
