package main;

import java.io.IOException;
import java.sql.SQLIntegrityConstraintViolationException;

import domein.PopulateDB;

public class RunPopulateDB
{
	
	public static void main(String[] args) throws SQLIntegrityConstraintViolationException, IOException
	{

		PopulateDB.run();
	}
	
}
