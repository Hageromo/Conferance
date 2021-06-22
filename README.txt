http://localhost:8080/set/user   POST - add user

http://localhost:8080/get/users   GET - return all users

http://localhost:8080/get/confirmed   GET - return all users that are registered on any conferacnce

http://localhost:8080/get/user/{id}   GET - return user by id

http://localhost:8080/get/conferances  GET - return all cnferances

http://localhost:8080/get/paths  GET - return all paths (ścieżki tematyczne które odbywają się na konferencji)

http://localhost:8080/get/conferance/{id}  GET - return conferance by id

http://localhost:8080/get/conferance/name/{name}  GET - return conferance inforations that user is on

http://localhost:8080/update/user/email/{id}  PUT - update users email

http://localhost:8080/update/user/{name}/{email}  PUT - update users reservation status

http://localhost:8080/get/path/{name}  GET - return paths that user is on

http://localhost:8080/cancel/{id}  DELETE - delete reservation



Przykładowe wartości użytkownikówmvn

[
    {
        "name": "Anna",
        "email": "Anna@.pl"

    },
    {
        "name": "Jakub",
        "email": "Jakub@.pl"
    },
    {
        "name": "Adam",
        "email": "Adam@.pl"
    },
    {
        "name": "Ewa",
        "email": "Ewa@.pl"
    },
    {
        "name": "Tomek",
        "email": "Tomek@.pl"
    },
    {
        "name": "Edward",
        "email": "Edward@.pl"
    }
]



Początkowe wartości tabeli Conferance

Conferance conf = new Conferance();

		conf.setId(1);
		conf.setHour("10:00");
		conf.setEnd("11:45");
		conf.setMembers(0);
		conf.setDate("01.06.2021");
		conferanceRepository.save(conf);

		conf.setId(2);
		conf.setHour("12:00");
		conf.setEnd("13:45");
		conf.setMembers(0);
		conf.setDate("01.06.2021");
		conferanceRepository.save(conf);

		conf.setId(3);
		conf.setHour("14:00");
		conf.setEnd("15:45");
		conf.setMembers(0);
		conf.setDate("01.06.2021");
		conferanceRepository.save(conf);


Początkowe wartości tabeli Path

Path path = new Path();

        path.setId(1);
        path.setLimit(0);
        pathRepository.save(path);

        path.setId(2);
        path.setLimit(0);
        pathRepository.save(path);

        path.setId(3);
        path.setLimit(0);
        pathRepository.save(path);