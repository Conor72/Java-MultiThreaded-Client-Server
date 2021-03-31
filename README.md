# Description:

Java MultiThreaded Client/Server that allows multiple users to login to an application and find the area of a circle.


# Setup Instructions:

Setup a connection to an SQL Server with the following table named 'Students'


      SID (type int(2), unique key)
      USERNAME (type varchar(20) )
      PASSWORD (type varchar(20) )
      TOT_LOGINS (type int(8)) 

      
Launch the Server Class first.

Then launch the login class and use one of the following users.

        USERNAME: conor PASSWORD: 123
        USERNAME: sean  PASSWORD: 123
       
        
After a sucessful login of one user, you can launch the Login class again to login as a second user.


# Features:

Login feature that only accepts valid registered users from an SQL server

Error Handling: Successful and Unsuccessful login attempts are received with the appropriate popup message

TOT_LOGINS field increases by one after every sucessful login

Multiple Users can be logged in at the same time and use the same server to run calculations

After a successful login, a Client window is launched. The Client window displays a welcome message saying the users name and the amount of times they have logged in. 

From the Client window a user can enter a number and they will be returned the radius of a circle (Which is calculated by the server class)

The Server window displays the name of the user everytime someone logs in. It also displays IP they connected from.




# References:

https://www.javaguides.net/2019/07/login-application-using-java-swing-jdbc-mysql-example-tutorial.html
