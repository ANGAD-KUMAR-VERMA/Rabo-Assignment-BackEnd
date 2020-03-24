# Rabobank Report Generator
This application performs necessary validations of customer bank transaction statements and generates a report with failed validations.

## Getting Started
Clone or download the source code from -

* https://github.com/ANGAD-KUMAR-VERMA/Rabo-Assignment-BackEnd/tree/master/csv-xml-validator

### Prerequisites
Things required to work properly

Java SE Runtime Environment- https://www.oracle.com/technetwork/java/javase/downloads/jre8-downloads-2133155.html

Spring Tool Suite(STS)- https://spring.io/tools3/sts/all

Postman- https://www.getpostman.com/downloads/

### Installation
Open the project in Spring Tool Suite

	- File -> Open Projects from File System 
	- click Directory
	- choose the project folder
	- click Finish
The dependencies in the POM.xml will be automatically downloaded. If not use maven to download the dependencies.

	- Right click the project
	- Maven -> Update Project
	  or
	- Press Alt + F5
Running the Project
To run the project

	- Right click the project
	- Run As -> Spring Boot App
	  or
	- Press Alt + Shift + X + B
To pass the file location

	- Open Postman
	- Change method to POST
	- Set Request URL as "localhost:8080/getreport/validate"
	- Select "raw" and "JSON(application/json" in Body
	= Enter the file location 
			- ex: "D:/TestData/records.csv"	 or "D:/Assignment/records.xml"
			- sample in "src/main/resources"
	- Click Send
	
## Output
If the input file is of extension "csv" the report will be generated in base directory as

CSVReport.csv

If the input file is of extension "xml" the report will be generated in base directory as

XMLReport.csv
