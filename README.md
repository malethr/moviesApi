# _Movie API_

#### _API for movies, August 25, 2017_

#### By _**Maria Thomas**_

## Description

This program can add, delete and retrieve movie details. It can also add movie types to movies and vice versa. The program is also capable in receiving reviews and ratings from users.

## Postman
* _Adding Movie_
![image of postman1](https://github.com/malethr/moviesApi/blob/master/src/main/resources/images/movie.png?raw=true "Adding movie")
* _Retriving movie not on the list_
![image of postman2](https://github.com/malethr/moviesApi/blob/master/src/main/resources/images/no%20movie%20listed.png?raw=true "Retrieving movie not on the list")

## Development Specifications

| Behavior      | Input | Output |
| ------------- | ------------- | ------------- |
| Program will add movie info| title: "Inside Job", description: "Academy award winning documentary", year: "2010", director: "Charles Ferguson", trailer: "trailer.avi"| title: "Inside Job", description: "Academy award winning documentary", year: "2010", director: "Charles Ferguson", trailer: "trailer.avi", id: 1  |
| Program will display list of movies added  | "Inside Job" ..... "Bourne" ....  | "Inside Job" ..... "Bourne" ....  |
| Program will add movie type | Action  | Movietype: Action, movieTypeid: 1  |
| Program will accept review from user | Awesome movie | "Awesome Movie", reviewId:1, movieId:1|
| Program will delete movie info | delete movieId: 1 | no value for movieId:1 |
| Program will delete movie type | delete movieTypeId: 1 | no value for movietypeId:1  |

## Setup/Installation Requirements

* _Clone this repository_
* _For a mac, run the following command in your terminal:
git clone (https://github.com/malethr/moviesApi)_
* _Open the folder and click the file in your intelliJ application._

## Support and contact details

_If you have any questions or suggestions. Please free to contact us._
_Maria Thomas. Email: malethr@gmail.com_

## Technologies Used

_IntelliJ_
_Java_
_GitHub_
_SQL_
_Spark_
_JSON_

### License
