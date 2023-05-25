# Create volunteer with the valid values
## Steps:
- Create a new Volunteer
- Use only simple identifiers for elements (name, id, tagName (where applicable)
- Submit registration form
- Using IF statement (for now) check that after registration, you will get successful message

### Expected Result:
New volunteer is registered, successfull message is displayed.

# Try to create volunteer with invalid Email
## Steps:
- During registration provide invalid format of email (e.g. test)
- Use only simple identifiers for elements (name, id, tagName (where applicable)
- Submit registration form
- Using IF statement check that after submitting you will get the error message

### Expected Result:
Warning message is displayed under the Email input field: "Email is incorrect". User is not registered.

# Try to create volunteer with empty input fields
## Steps:
- Don't fill any data into the input fields
- Use only simple identifiers for elements (name, id, tagName (where applicable)
- Click on Sigh Up button
- Using IF statement check that after clicking on Suhn Up button you will get the error message

### Expected Result:
Warning messages "Field can`t be empty" are displayed under the followng input fields: First Name, Last Name, Email, Password and Confirm Password. User is not registered.# Try to register with invalid First Name

# Navigation through the site via main menu
## Steps:
- Navigate through the site unsing main menu options
- Use only simple identifiers for elements (name, id, tagName (where applicable)
- Using getTitle() method verify that correct page is opened

### Expected Result: 
Appropriate page should open

# Switch languages
## Steps:
- Switch languages (En, Ru, Uk)
- Use only simple identifiers for elements (name, id, tagName (if applicable)
- Using IF statement check that after switching the correct language is used on the page

### Expected Result:
The site should be switched to the correct language (it means the main elements of the page are translated to the selected language)