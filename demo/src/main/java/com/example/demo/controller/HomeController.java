package com.example.demo.controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import log.Log;

import java.util.Date;
import java.util.Scanner;

@RestController
public class HomeController{
	 @RequestMapping("/")
	 public String index() {
		 String html = "";
		 try {
			Scanner s = new Scanner(new BufferedReader(new FileReader(new File("\\src\\main\\resources\\index.html"))));
			while(s.hasNext()) {
				html+=s.nextLine();
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			Log.report(new Date()+"-"+e.getMessage());
		}
		 
		 return html;
	 }
}