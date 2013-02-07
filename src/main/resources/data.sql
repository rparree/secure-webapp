--run: "echo -n 'masterkey' | sha256sum"
insert into users (username,password,enabled)
  values
  ('jennifer','48c5a1d217fe85082464d2ca1e90a16d15464fabe20f8610d79b63aa58797b9b',1),
  ('john','48c5a1d217fe85082464d2ca1e90a16d15464fabe20f8610d79b63aa58797b9b',1);

insert into authorities(username,authority)
  values
  ('jennifer','ROLE_MEMBER'),
  ('jennifer','ROLE_CASHCOW'),
  ('john','ROLE_MEMBER');


