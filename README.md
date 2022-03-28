# Super*Duper*Drive Cloud Storage
Application includes three user-facing features:

1. **Simple File Storage:** Upload/download/remove files
2. **Note Management:** Add/update/remove text notes
3. **Password Management:** Save, edit, and delete website credentials.  


### The Back-End
The back-end is all about security and connecting the front-end to database data and actions. 

1. Managing user access with Spring Security
 - Restrict unauthorized users from accessing pages other than the login and signup pages.
 - Spring Boot has built-in support for handling calls to the `/login` and `/logout` endpoints. 
 - Implemented a custom `AuthenticationProvider` which authorizes user logins by matching their credentials against those stored in the database.  


2. Handling front-end calls with controllers
 - Controllers for the application that bind application data and functionality to the front-end using Spring MVC's application model.


3. Making calls to the database with MyBatis mappers
 - To connect  model classes with database data, implemented MyBatis mapper interfaces for each of the model types. 

### The Front-End

1. Login page
 - Everyone is allowed access to this page, and users can use this page to login to the application. 
 - Shown login errors, like invalid username/password, on this page. 


2. Sign Up page
 - Everyone is allowed access to this page, and potential users can use this page to sign up for a new account. 
 - Validating that the username supplied does not already exist in the application, and shows such signup errors on the page when they arise.


3. Home page
The home page is the center of the application and hosts the three required pieces of functionality. The existing template presents them as three tabs that can be clicked through by the user:


 i. Files
  - The user is able to upload files and see any files they previously uploaded.
  - The user is able to view/download or delete previously-uploaded files.
  - Any errors related to file actions is displayed. For example, a user is not able to upload two files with the same name, but they'll never know unless you tell them!


 ii. Notes
  - The user is able to create notes and see a list of the notes they have previously created.
  - The user is able to edit or delete previously-created notes.

 iii. Credentials
 - The user is able to store credentials for specific websites and see a list of the credentials they've previously stored. Password is displayed encrypted on list!
 - The user is able to view/edit or delete individual credentials. When the user views the credential, is able to see the unencrypted password.

The home page have a logout button that allows the user to logout of the application and keep their data private.

### Testing

1.  Tests for user signup, login, and unauthorized access restrictions.
 - test that verifies that an unauthorized user can only access the login and signup pages.
 - test that signs up a new user, logs in, verifies that the home page is accessible, logs out, and verifies that the home page is no longer accessible. 


2. Tests for note creation, viewing, editing, and deletion.
 - test that creates a note, and verifies it is displayed.
 - test that edits an existing note and verifies that the changes are displayed.
 - test that deletes a note and verifies that the note is no longer displayed.


3. Tests for credential creation, viewing, editing, and deletion.
 - test that creates a set of credentials, verifies that they are displayed, and verifies that the displayed password is encrypted.
 - test that views an existing set of credentials, verifies that the viewable password is unencrypted, edits the credentials, and verifies that the changes are displayed.
 - test that deletes an existing set of credentials and verifies that the credentials are no longer displayed.


### Password Security
Password is encrypted first and then stored in database.


