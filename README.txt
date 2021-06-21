http://localhost:8080/set/user   POST - add user

http://localhost:8080/get/users   GET - return all users

http://localhost:8080/get/user/{id}   GET - return user by id

http://localhost:8080/get/conferances  GET - return all cnferances

http://localhost:8080/get/conferance/{id}  GET - return conferance by id

http://localhost:8080/get/conferance/name/{name}  GET - return conferance inforations that user is on

http://localhost:8080/update/user/email/{id}  PUT - update users email

http://localhost:8080/update/user/conferance/{id}  PUT - update users reservation status



Przykładowe wartości użytkowników

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


