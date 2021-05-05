select *
from users;
use mycource4;
insert into users  ( nameUser, loginUser, passwordUser)
VALUES ( 'Kate', 'Rin', 'Rin');
delete from users
where nameUser = 'Kate'
update users
set loginUser = 'AA', passwordUser = 'A'  where nameUser = 'Arina'