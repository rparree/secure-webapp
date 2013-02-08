package security.mongodb;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.AuthenticationUserDetailsService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.openid.OpenIDAttribute;
import org.springframework.security.openid.OpenIDAuthenticationToken;
import org.springframework.stereotype.Service;
import security.Member;
import security.OpenIDMember;
import util.Option;

import java.util.List;
import java.util.Random;

/**
 * todo
 */
@Service
public class MongoDBUserDetailService implements UserDetailsService, AuthenticationUserDetailsService<OpenIDAuthenticationToken> {

  @Autowired
  MemberRepository repository;

  private  Random random = new Random();
  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {


    Option<OpenIDMember> openIDMemberOption = repository.getOpenIdMember(username);

    return openIDMemberOption.mapValueOrThrowException(new Option.Mapper<OpenIDMember, Member>() {
      @Override
      public Member map(OpenIDMember openIDMember) {
        return new Member(openIDMember,String.valueOf(random.nextInt()));
      }
    }, UsernameNotFoundException.class, "Invalid username/password");


  }

  @Override
  public UserDetails loadUserDetails(final OpenIDAuthenticationToken token) throws UsernameNotFoundException {
    final String identityUrl = token.getIdentityUrl();
    Option<OpenIDMember> openIDMemberOption = repository.getOpenIdMember(identityUrl);
    OpenIDMember openIDMember = openIDMemberOption.getValueOr(OpenIDMember.class, new Option.DefaultCreator<OpenIDMember>() {
      @Override
      public void init(OpenIDMember openIDMember) {
        openIDMember.identityUrl(identityUrl);
        openIDMember.addRoles("ROLE_MEMBER");
        List<OpenIDAttribute> attributes = token.getAttributes();
        boolean fullnameHasBeenSet = false;
        for (OpenIDAttribute attribute : attributes) {
          switch (attribute.getName()) {
            case "email":
              openIDMember.email(attribute.getValues().get(0));
              break;
            case "fullname":
              openIDMember.displayName(attribute.getValues().get(0));
              fullnameHasBeenSet = true;
              break;
            case "firstname":
              if (!fullnameHasBeenSet) openIDMember.displayName(attribute.getValues().get(0));
              break;
          }
        }
        repository.add(openIDMember);
      }
    });

    return new Member(openIDMember, String.valueOf(random.nextInt()));
  }
}
