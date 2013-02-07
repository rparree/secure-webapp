--run: "echo -n 'masterkey{username}' | sha256sum"
insert into users (username,password,enabled)
  values
  ('jennifer','42e5b0e7c375f85225e7feca778fb79d89c06472737116685df6cb267bebb0f0',1),
  ('john','ea1ec3fe37e28375adcd9a708d0a6172ce618ba8ba69f9270b349e2e73f662c6',1);

insert into authorities(username,authority)
  values
  ('jennifer','ROLE_MEMBER'),
  ('jennifer','ROLE_CASHCOW'),
  ('john','ROLE_MEMBER');


