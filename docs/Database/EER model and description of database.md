# EER Model and Description of database
We have four entities: WebUser(Information about user), UserIntroduction(Description of yourself on the site), Hobbies(Keeps a list of hobbies which are used to find similar users), UserLike(User ratings)  
Each entity has attributes.
WebUser: UserId(Privacy key), UserLogin(Login) its UNIQUE, UserPassword(Password), FirstName, LastName, Pantronymic, Email its UNIQUE, PhoneNumber, Role, RegistrationDate(automatically), City  
Hobbies: UserId(Privace key), Sports, Music, Travel, Reading, Art, Movie, Cartoon, Games, Cooking, Shopping each of them takes on a value from 0 to 10.  
UserIntroduction: UserId(Privacy key), AboutUser, SomethingToSay  
UserLike: UserId(privacy key), UserDislike, UserLike  
We have one-to-one relationship between entites. It all depends on the WebUser entity. If you delete a user from WebUser, then he will be removed from the rest because we have enabled cascade mode  
All attributes are unambiguous.  
EER Model
![EER Model](https://github.com/Erkobrax/WEBProject/raw/main/docs/images/EERDiagram.png)
