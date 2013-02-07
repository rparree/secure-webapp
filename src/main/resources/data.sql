insert into users (username,password,enabled)
  values
  ('jennifer','$ecret',1),
  ('john','$ecret',1);

insert into authorities(username,authority)
  values
  ('jennifer','ROLE_MEMBER'),
  ('jennifer','ROLE_CASHCOW'),
  ('john','ROLE_MEMBER');


