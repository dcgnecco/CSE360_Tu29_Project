package com.project;

import java.util.*;
import java.io.*;

public class Data_collect 
{
	
	static HashMap<String, Integer> pointvalues = new HashMap<>();
	static HashMap<String, Integer> mapInFile= new HashMap<>();
	static Scanner scan = new Scanner(System.in);
	
	private static int no_of_employees;
	//function to get all the values along with the employee names
	public static void GetVal(int no_of_employees)
	{
		String emp;
		int wt;
		
		for(int i =0; i<no_of_employees ; i++)
		{
			System.out.println("Enter employee Name: ");
			emp = scan.nextLine();
			System.out.println("Enter weight: ");
			wt = scan.nextInt();
			scan.nextLine();
			
			pointvalues.put(emp, wt);
			
		}	
		
		int avg = avg();
		pointvalues.put("emp", avg);
	}
	//calculating the average weights put forward
	public static int avg()
	{
		double avg=0;
		for (int i : pointvalues.values()) 
		{
			avg+=i;
		}
		
		avg=avg/pointvalues.size();
		return (int)avg;
	}
	
	//function to print the values chosen by the employees along with their names
	public static void display(HashMap<String, Integer> hm)
	{
		System.out.println("The votes were: ");
		for (String i : hm.keySet()) 
		{
			System.out.println(i+": "+hm.get(i));
			
		}
	}
	
	public static void write_file(HashMap<String, Integer> pointvalues) 
	{
	    //writing to file : "log"
		try 
		{
			File log=new File("log");
	        FileOutputStream fos=new FileOutputStream(log);
	        ObjectOutputStream oos=new ObjectOutputStream(fos);

	        oos.writeObject(pointvalues);
	        oos.flush();
	        oos.close();
	        fos.close();
	    } 
	    catch(Exception e) 
	    {}
	}
	public static void read_file() 
	{
	    //reading from file 
	    try 
	    {
	        File toRead=new File("log");
	        FileInputStream fis=new FileInputStream(toRead);
	        ObjectInputStream ois=new ObjectInputStream(fis);

	        mapInFile=(HashMap<String, Integer>)ois.readObject();

	        ois.close();
	        fis.close();
	    } 
	    catch(Exception e) {}
    }
	
	public static void main(String[] args)
	{
		System.out.println("Enter Number of participants: ");
		no_of_employees = scan.nextInt();
		scan.nextLine();	
		GetVal(no_of_employees);
		System.out.println("The average weight for this story is: "+avg());
		write_file(pointvalues);
		read_file();
		display(mapInFile);
		
	}
}
