package com.fca.calidad.unitaria;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;

class Calculadoratest {

	private double num1 = 0;
	private double num2 = 0;
	private Calculadora calculadora = null;
	
	@BeforeEach
	void setup() {
		num1 = 2;
		num2 = 5;
		calculadora = new Calculadora();
	}

 @Test	
 void suma2numerosPositivostest() {
	
	 
	double resEsperado = 7;

	
	//Ejercicio, llamar al metodo que queremos probar
	double resEjecucion = calculadora.suma(num1, num2);
	
	//verificar
	assertThat(resEsperado, is(resEjecucion));

 }
 
 @Test	
 void resta2numerosPositivostest() {
	
	double resEsperado = -3;
	
	//Ejercicio, llamar al metodo que queremos probar
	double resEjecucion = calculadora.resta(num1, num2);
	
	//verificar
	assertThat(resEsperado, is(resEjecucion));
 
 
}
 @Test	
 void multiplicacion2numerosPositivostest() {
	
	double resEsperado = 10;
	
	//Ejercicio, llamar al metodo que queremos probar
	double resEjecucion = calculadora.multiplica(num1, num2);
	
	//verificar
	assertThat(resEsperado, is(resEjecucion));
 
 
}
 
 @Test	
 void divide2numerosPositivostest() {

	double resEsperado = 0.4;
	
	//Ejercicio, llamar al metodo que queremos probar
	double resEjecucion = calculadora.divide(num1, num2);
	
	//verificar
	assertThat(resEsperado, is(resEjecucion));
 
 
}
 
 
}
