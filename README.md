## Student management system


### Student endpoints:

#### Create
```
(POST) localhost:8080/student-management/api/v1/student/new

Create student with specified parameters. Requires id, firstName, lastname and email. 
Email needs to be in an email format.

json:
    {
    "id":"199001019999"
    "firstName":"John"
    "lastname":"Doe"
    "email":"john@doe"
    "phoneNumber":"0701234567"
    }
```
#### Read
```
(GET) localhost:8080/student-management/api/v1/student/all

Get all students in the database.

(GET) localhost:8080/student-management/api/v1/student/{id}

Get student with specified id.

(GET) localhost:8080/student-management/api/v1/student/lastname/{lastname}

Get student with specified lastname.

```
#### Update
```
(PUT/PATCH) localhost:8080/student-management/api/v1/student/update

PUT: Replaces all fields in student. Non specified fileds become blank.
PATCH: Replaces only specified fields and does nothing with the other fields.
Both require atleast id.

json:
    {
    "id":"199001019999"
    "firstName":"John"
    "lastname":"Doe"
    "email":"john@doe"
    "phoneNumber":"0701234567"
    }
```
#### Delete
```
(DELETE) localhost:8080/student-management/api/v1/student/{id}

Delete student with specified id.

```
### Get student by subject and teacher
```
(GET) localhost:8080/student-management/api/v1/subject/getstudents/{subjectName}/{TeacherName}
```

### Teacher endpoints:
#### Create
```
(POST) localhost:8080/student-management/api/v1/teacher/create

json:
{
	"name":"Bob"
}
```
#### Get all
```
(GET) localhost:8080/student-management/api/v1/teacher/getall
```

### Subject endpoints:
#### Create
```
(POST) localhost:8080/student-management/api/v1/subject/create

json:
{
	"name":"Maths"
}
```
#### Get all
```
(GET) localhost:8080/student-management/api/v1/subject/getall
```
### Set teacher
```
(POST) localhost:8080/student-management/api/v1/subject/{subjectId}/add/teacher/{teacherId}
```
### Add student
```
(POST) localhost:8080/student-management/api/v1/subject/{subjectId}/add/student/{studentId}
```