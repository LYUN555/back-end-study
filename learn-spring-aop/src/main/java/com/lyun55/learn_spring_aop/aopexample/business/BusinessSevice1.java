package com.lyun55.learn_spring_aop.aopexample.business;

import java.util.Arrays;

import org.springframework.stereotype.Service;

import com.lyun55.learn_spring_aop.aopexample.data.DataService1;

@Service
public class BusinessSevice1 {
	
	private DataService1 dataSercvice1;
	
	public BusinessSevice1(DataService1 dataSercvice1) {
		this.dataSercvice1 = dataSercvice1;
	}
	
	public int calaculateMax() {
		int[] data = dataSercvice1.retrieveData();
		
		//throw new RuntimeException("Somthing Went Wrong!");
		return Arrays.stream(data).max().orElse(0);
	}

}
