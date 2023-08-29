# Sector Submission

A web application that allows a user to submit their name and pick amongst different sectors that they may be currently involved in.
This is the test assignment given by Helmes for their software developer roles.

## Installation

1 . Clone the monorepo.
2 . Execute the script from the root directory to download the necessary libraries, build the artifacts, and launch the application suite:
```sh
./start.sh
```
## Usage

Navigate to the web application to submit the form.
[http://localhost:3000](http://localhost:3000)


Make an API GET request to the endpoint to view all submitted forms.
[http://localhost:8080/api/sectors](http://localhost:8080/api/sectors)

To acces the database, navigate to the H2 Console.
  [http://localhost:8080/h2-console](http://localhost:8080/h2-console)
JDBC URL: `jdbc:h2:mem:testdb`
User Name: `sa`
Password: (leave this field empty)

## Test assignment description with my interpretation as comments

1 . Correct all of the deficiencies in index.html

2 . "Sectors" selectbox:
2.1. Add all the entries from the "Sectors" selectbox to database
2.2. Compose the "Sectors" selectbox using data from database

3 . Perform the following activities after the "Save" button has been pressed: 
3.1. Validate all input data (all fields are mandatory)
3.2. Store all input data to the database (Name, Sectors, Agree to terms)
3.3. Refill the form using stored data
3.4. Allow the user to edit his/her own data during the session 
*Comment:* After the user submits the form and the data is saved to the database, the user can edit the data in the form and submit it again to update the data in the database. This step doesn't explicitly state that user login should be implemented nor that the session should be available from other browser tabs.

Write us Your best code! 

After completing the tasks, please provide us with:
1.Full database dump (structure and data)
2.Source code

## License

MIT