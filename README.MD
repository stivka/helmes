# Sector Submission

A web application where users can submit their name along with the sectors they are currently engaged in.
This is the test assignment given by Helmes for their software developer roles.

## Installation

1 . Clone the monorepo.<br>
2 . Execute the script from the root directory to download the necessary libraries, build the artifacts, and launch the application suite:
```sh
./start.sh
```
## Usage

Navigate to the web application to submit the form.
[http://localhost:3000](http://localhost:3000)


Make an API GET request to the endpoint to view all submitted forms.
[http://localhost:8080/api/sectors](http://localhost:8080/api/sectors)

To access the database, navigate to the H2 Console.<br>
[http://localhost:8080/h2-console](http://localhost:8080/h2-console)<br>
JDBC URL:`jdbc:h2:mem:testdb`<br>
User Name:`sa`<br>
Password: (leave this field empty)

## Test assignment description with my interpretation as comments

1 . Correct all of the deficiencies in index.html

2 . "Sectors" selectbox:<br>
2.1. Add all the entries from the "Sectors" selectbox to database<br>
2.2. Compose the "Sectors" selectbox using data from database<br>

3 . Perform the following activities after the "Save" button has been pressed:<br> 
3.1. Validate all input data (all fields are mandatory)<br>
3.2. Store all input data to the database (Name, Sectors, Agree to terms)<br>
3.3. Refill the form using stored data<br>
3.4. Allow the user to edit his/her own data during the session<br>
*Comment:* Once the user has submitted the form and the data is stored in the database, they can modify the form data and resubmit it to update the database. This step does not specify the need for user login implementation nor the availability of the session across different browser tabs. The current implementation permits separate sessions in different browser tabs.

Write us Your best code! 

After completing the tasks, please provide us with:<br>
1.Full database dump (structure and data)<br>
2.Source code

## License

MIT
