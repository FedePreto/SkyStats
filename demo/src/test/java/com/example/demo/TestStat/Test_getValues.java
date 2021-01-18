package com.example.demo.TestStat;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Date;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.example.demo.model.Citta;
import com.example.demo.statistiche.Stat;

class Test_getValues {
	private Stat s = new Stat();
	private ArrayList<Citta> city = new ArrayList<Citta>();	
	private Citta c1,c2;

	@BeforeEach
	void setUp() throws Exception {
		c1 = new Citta(6542055,"Comune di Como","cielo sereno",72.0,1019.0,3.79,"Nord",new Date());
		c2 = new Citta(6541874,"Fermo","cielo coperto",36.0,995.0,7.41,"Centro",new Date());
		city.add(c1);
		city.add(c2);
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void Test1() {
		assertEquals(1019.0,s.getValues(city)[0][0]);
		assertEquals(72.0,s.getValues(city)[0][1]);
		assertEquals(3.79,s.getValues(city)[0][2]);
		assertEquals(995.0,s.getValues(city)[1][0]);
		assertEquals(36.0,s.getValues(city)[1][1]);
		assertEquals(7.31,s.getValues(city)[2][2]);
	}
	
	@Test
	void Test2() {
		assertEquals(, s.getValues(city, "Fermo"));
		assertEquals(null, s.getValues(city, null, false));
	
	}

}
