# CP-Inline-Framework-Selenium-API-Database-Testing

This framework will execute Curation Platform UI Testing + API testing + Database Testing at the same time. All 3 kinds of testing will be performed simultaneously. Smoke and Regression Testing Framework combined with Selenium, Java and Sql. 

First Step:
Download the Project from here
https://gitlab.dfci.harvard.edu/pod/cp-inline-framework-selenium-api-database-testing.git
 

Second Step:
Enter valid credentials to the configuration properties located on the root folder  Config.properties”
Insert user credentials and database credentials

Precondition to Run the Test:
Before executing the test run this command on the terminal "mvn clean package -DskipTests"

Rung Test from Command Line: You can run the tests from your command line , for example : mvn -Dtest=patientSectionTest test
or mvn -DsuiteXmlFile=Smoke.xml test

After The Test:
You can go to “Reports/extent.html” and Open the html file into a web browser. 


