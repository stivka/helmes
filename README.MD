# Sector Submission

A web application where users can submit their name along with the sectors they are currently engaged in.
This is the test assignment given by Helmes for their software developer roles.

## Installation

1 . Clone the monorepo.<br>
2 . Execute the script from the root directory to download the necessary libraries, build artifacts, run tests and launch the application suite:
```sh
./start.sh
```
## Usage

Navigate to the web application to submit the form.
[http://localhost:3000](http://localhost:3000)


Make an API GET request to the endpoint to view all sectors.
[http://localhost:8080/api/sectors](http://localhost:8080/api/sectors)

Make an API GET request to the endpoint to view a sector by it's html id. http://localhost:8080/api/sectors/{htmlId}

Make an API GET request to the endpoint to view all registration forms. http://localhost:8080/api/registration

Make an API GET request to the endpoint to view a form by name. http://localhost:8080/api/registration/{name}

To access the database, navigate to the H2 Console.<br>
[http://localhost:8080/h2-console](http://localhost:8080/h2-console)<br>
JDBC URL:`jdbc:h2:mem:testdb`<br>
User Name:`sa`<br>
Password: (leave this field empty)

Database dump with schema and data is in the root directory, in backup.sql 

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

## Considerations

**Backend consideration for dropdown implementation**

**Storing depthLevel with each Sector**<br>
Advantages:
Simplifies the logic when looking for a parent. Can just traverse the list in reverse and look for the first sector with a depthLevel one less than the current.
O(1) access time for the depth level for each sector.
Potential Pitfalls:
It might increase memory usage slightly, but for reasonable list sizes, this should not be a problem.

**Finding the last sector in the list with a smaller depthLevel via method**<br>
Advantages:
Keeps the Sector model simpler by not adding a property that may not be otherwise used if not in the parsing function.
Potential Pitfalls:
This would be less efficient. For every sector, you may have to traverse the sectors list backward, leading to an O(n^2) algorithm in the worst case. This can be slow for large lists.

Went with creating the property depthLevel since lists for Sectors won't grow to be that large.

**Frontend consideration for dropdown implementation**

**Fetching all sectors at once**<br>
Advantages:
Efficient in terms of the number of network requests.
Potential Pitfalls:
Can be problematic if the number of sectors is very large, as it requires sending all sectors over the network and building the entire tree in the frontend.

**Fetching only root sectors initially**<br>
Advantages:
Reduces the amount of data sent over the network initially.
Distributes the work of building the tree between the frontend and the backend.
Potential Pitfalls:
Increases the number of network requests, as a new request would be needed each time a sector is expanded.

Went with using the parent property of each sector to build the tree. With this we ensure data integrity and make the creation of the tree more robust. This means that the structure of the tree is entirely determined by the data received from the backend, and any inconsistencies in the data (e.g. a sector having a depthLevel that does not match its position in the tree) will not affect the tree built by the frontend. Separating the construction of the tree from a specific solution using depthLevel is better since a tree node more likely has a parent field than a depth field.

**React**<br>
Maybe using the React library is a bit overkill, but since I'm not that experienced in frontend, I took this assignment as an oppurtunity to familiarize myself with the industry standard library.

**Session**<br>
Generating a new UUID for each tab is a simple and effective way for maintaining unique form submissions across multiple tabs without getting into the complexities of session management.
Also UUIDs are not easily guessable like simple incremental IDs.<br>
If authentication is important, then the user can enter a name provided by the Sector Submission owner ;)

## License

MIT
