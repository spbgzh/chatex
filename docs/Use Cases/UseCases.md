# Use Case

## List of actors
Guest:
* See the ranking of the most popular users
* Register an account   

User:
* Edit your own information
* Edit your interest score
* Search for like-minded people according to their needs
* View the personal information of the searched person
* Like or dislike the searched person
* Change Password
* Do what guests can do  

Administrator:
* Set and unset users as ADMIN
* Delete users
* Create random users
* Do what users and guests can do

## Description of Use Cases

|        Name       | Description |
| :----------------:| :----: |
| Login Use Cases      | Provide users with the function of website login |
| Register Use Cases   | Provide users with the function of website registration | 
| Management Use Cases | Provide users and administrators with the function of managing accounts respectively |
| Searching Use Cases  | The main business of the website, searching for like-minded friends for users |
| Like Dislike Use Cases  | Click to rate whether you like this user |
| Random user Use Cases  | For Administrator to create test users |

## BOM Portal Use Cases
|        *Use Cases*       | Login  |
| :----------------:| :----: |
| Brief Description | For user to login to the website |
| Primary Actor     | User and administrators | 
| Preconditions     | User has registered in the website |
| Basic Path        | The user enters the user name and password, chooses whether to remember me, and clicks the login button |
| Alternative Path  | The user forgets his password, click forgot password, jump to the password modification page, enter your personal information, and enter a new password, click modify password |
| Postconditions    | If the login is successful, it will jump to the homepage, if the login fails or the password modification is successful, it will jump to the login page, if the password modification fails, it will return to the password modification page. |

|        *Use Cases*       | Register |
| :----------------:| :----: |
| Brief Description | For users to register on the site |
| Primary Actor     | guest | 
| Preconditions     |  |
| Basic Path        | Enter basic information such as username and password, and click the register button |
| Alternative Path  | If the user enters the wrong data, it will be prompted to correct the data  |
| Postconditions    | If the registration is successful, it will jump to the login page, if the user name already exists, it will jump back to the registration page |

|        *Use Cases*       | Management |
| :----------------:| :----: |
| Brief Description | Let users and administrators manage their accounts |
| Primary Actor     | User and administrator | 
| Preconditions     | Already login |
| Basic Path        | The user or administrator edits their personal information or introduces themselves, and clicks to post |
| Alternative Path  | The administrator can enter the specified user name, set his role as administrator or user, and the administrator can delete the specified user |
| Postconditions    | After setting success or failure, return to the management page |

|        *Use Cases*       | Searching |
| :----------------:| :----: |
| Brief Description | Users looking for like-minded people |
| Primary Actor     | User and administrator | 
| Preconditions     | Already login |
| Basic Path        | The user sets the gender and age range and clicks the find button |
| Alternative Path  | If no interest score is set, then jump to the interest score setting page |
| Postconditions    | The system returns the matched users |

|        *Use Cases*       | Like Dislike |
| :----------------:| :----: |
| Brief Description | Like or dislike for the queried user |
| Primary Actor     | User and administrator | 
| Preconditions     | Like-minded people have been queried |
| Basic Path        | Click to enter the personal homepage of the queried user, view personal information, click like or dislike for him |
| Postconditions  | Like or dislike the people inquired, the ranking of popular people will change |


|        *Use Cases*       | Random user |
| :----------------:| :----: |
| Brief Description | Generate random users |
| Primary Actor     | Administrator | 
| Preconditions     | Administrator logged in |
| Basic Path        | Click the button to generate random users |
| Postconditions    | Random user generation, provided to the administrator for testing |

