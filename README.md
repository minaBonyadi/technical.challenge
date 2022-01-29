# technical.challenge

----------------------------------------------
Project Information:

This Project has included two servies:
-One of them is /movie/winner that works is getting all winning 'Best Picture' movies information from OMDB API
as a list to client

-Second one is /movie/top-movie which request body is getting a movie with an updated rate between 1 to 10
and then return top 10 movies from db by their backOffice variables and sorted descadingly

-----------------------------------------------
How to Run the application:

   You just need to up your docker-compose and then run Application.class after that you can call services
   and get response from both of them.
   
   -I got my apiKey from https://www.omdbapi.com/ to call their services ,so you need to get your own 
   apikey and active it as well.(without this you cannot use these services).
   
   -There is a docker-compose file in source of project which is for connecting to mysql database. 
    I define a table the name is 'movie' that store all movies information from OMDB Api.
   
   -I define spring security configuration so it needs authentication to access but I gave permit all for these both
   services ,so with these two statements { .antMatchers("/movie/top-movie").permitAll().antMatchers("/movie/winner").permitAll()}
   you should login by a user to get an access to these services.
   
   -There are three tests which one of them testing csv file parser and two others are mvc test for testing services
   
   
