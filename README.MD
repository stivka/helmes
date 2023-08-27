
1. clone the monorepo

2. Launch the application suite by executing 'start.sh'

-------------------------------------------------------

Test assignment description with my interpretation as comments

1. Correct all of the deficiencies in index.html

2. "Sectors" selectbox:
2.1. Add all the entries from the "Sectors" selectbox to database
2.2. Compose the "Sectors" selectbox using data from database

3. Perform the following activities after the "Save" button has been pressed: 
3.1. Validate all input data (all fields are mandatory)
3.2. Store all input data to the database (Name, Sectors, Agree to terms)

3.3. Refill the form using stored data
// confirmation to the user that the submitted data has been saved and is now used to populate the form.
This step does not specify whether the form should be refilled immediately after submission or if the data 
should be retrieved from the database and used to refill the form at a later time.

3.4. Allow the user to edit his/her own data during the session 
// after the user submits the form and the data is saved to the database, 
the user can edit the data in the form and submit it again to update the 
data in the database.
This step doesn't explicitly state that user login should be implemented nor that
the session should be available from other browser tabs.

Write us Your best code! 

After completing the tasks, please provide us with:

1. Full database dump (structure and data)
2. Source code