package security.mongodb;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import security.Member;
import util.Option;

/**
 * todo
 */
@Service
public class MongoDBUserDetailService implements UserDetailsService {

  @Autowired
  MemberRepository repository;


  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {


    Option<Member> memberOption = repository.getMember(username);
    Member member = memberOption.getValueOrThrowException(UsernameNotFoundException.class, "Invalid username/password");


    return member;
  }
}
