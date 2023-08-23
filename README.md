# quizgameJerseyRestApis
using jersey built rest apis in java, and view has been immplemented and apis are integrted using javascript,html,css.

game folder is includes all java code .
quiz floder have frontend code 

project flow : 
to build rest apis in java , i have developed this application . 
where user can login  using mobile number and after sucessfull login user redirect to terms and condtions . when user click on take test
he/she start with quiz questions and for every question they get 30 seconds of time . if they not able to attend than next question  will come.

DATA BASE : 
add postgres dependecy in pom.xml 
data base name : game 
tables : 
create table users (
id serial,
username varchar,
mobile_no varchar,
created_date timestamp,
updated_date timestamp,
status smallint
);	

create table users_login_history (
 id  serial,
 user_id varchar ,
login_at timestamp,
created_date timestamp,
updated_date timestamp,
status smallint
);

questions 

create table question(
id  serial,
question text,
answer text,
marks int,
status smallint,
created_date timestamp,
updated_date timestamp
);


create table multiplechoice(
id  serial;
question_id int,
choices text,
status smallint,
cretaed_date timesatmp,
updated_date timestamp
);


create table submitanswers(
id  serial,
user_id int,
question_id int,
multiplechoice_id INT,
questionset VARCHAR,
time_start timestamp,
submit_time timestamp,
status SMALLINT 
);


CREATE TABLE termsandconditions(
id SERIAL,
terams TEXT,
updated_date TIMESTAMP,
cretaed_date TIMESTAMP,
status smallint
);


