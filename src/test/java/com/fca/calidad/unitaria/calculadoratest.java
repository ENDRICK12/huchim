package com.fca.calidad.unitaria;
import static org.hamcrest.MatcherAssert.assertThat; 
import static org.hamcrest.Matchers.*;

import org.junit.Test;

class calculadoratest {

	@Test
	void suma2numerosPositivostest()
	{
		double num1=2;
		double num2 =5;
		double resEsperado=7;
		calculadora calculadora =new calculadora();
		//Ejercicio, llamar al metodo que queremos probar 
		double resEjecucion =calculadora.suma(num1, num2);
		
		//verificar
	//	asserThat(resEsperado, is(resEjecucion));
	}
}
