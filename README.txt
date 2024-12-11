Diarmaid Hughes
Weather app Sensor REST API

Technologies used;
-Java
-Spring
-h2 Database

IDE - VSCode

*************************************************  INSTRUCTIONS ****************************************************

To start the application, open the repository/folder in VSCode,
-open terminal
-gradlew build // to build the program
-gradlew bootRun // to run the program

Instructions to use the program/API;

To submit a reading of a sensor;
	- Send a POST request in Postman to http://localhost:8081/api/sensors
	- Add content-type application/json to headers
	- Use Body Raw Json in the following format;

		{
    		   "sensorType": "temperature",
    		   "metric": 5.0,
    		   "unit": "Celsius"
		}

	- valid sensor types include "temperature", "rain", "windSpeed", "pressure", and "humidity".
	- corresponding valid unit types are "Celsius", "mm", "km/h", "hPa", and "%".

	- location and dateTime are automatically assigned to "Galway City Centre" , and the current time.


To get all sensor entries;
	- Send a GET request in Postman to http://localhost:8081/api/sensors

To query by sensor type, the desired statistic(sum, average, min, max) and the time range;
	- Send a GET Request in Postman to http://localhost:8081/api/sensors/filter/calc/lastDays?calc=average&sensorTypes=temperature,rain&days=7
	- in the above example, average is used for calc, sum, min, and max can also be queried
	- in the above example, temperature and rain sensors are queried, windSpeed, pressure, and humidity can also be queried
	- any number/combination of sensors can be queried
	- the timerange is queried using the last number of days, in the above example it is set to 7, this can be changed to the desired number of days

The way the above query works is the value for calc is checked in my controller, and the appropriate method from the service class is called depending on the value for calc.

If no value for days is given e.g. Send a GET Request in Postman to http://localhost:8081/api/sensors/filter/calc?calc=sum&sensorTypes=temperature,rain
	- The latest entry for each sensor will be returned


********************************************  DATABASE  ************************************************************************************

Entries are written to a h2 file database which can be found in the data directory within the project.
I had an issue with my database where it would generate ids with a jump of 32 after each program restart, I did not have sufficient time to fix this.

******************************************** TESTING  **************************************************************************************

I have included one test file on the repository in the repositoryTest directory. 
Unfortunately I did not have sufficient time to run tests for all aspects of the program, but the test provided ran successfully.
It tested that a sensor object is added correctly to the database, and that the values are as expected.
To run this test, open the terminal and run gradlew test.

******************************************* ADDITIONAL INFO  *****************************************************************************

I began with commenting my code, but as time elapsed, I realised I could not continue with this.

The project too me approx 4.5 hours total, excluding this readme writeup and submission to GitHub.

If you have any questions, please don't hesitate to get in touch!











