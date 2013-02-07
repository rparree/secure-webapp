package security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 * todo
 */

public class Member implements UserDetails{
  private String username;
  private String password;
  private Set<String> roles = new HashSet<>();


  public Member() {
  }

  public String getUsername() {
    return username;
  }

  @Override
  public boolean isAccountNonExpired() {
    return true;
  }


  @Override
  public boolean isAccountNonLocked() {
    return true;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }

  @Override
  public boolean isEnabled() {
    return true;
  }

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return AuthorityUtils.createAuthorityList(this.roles.toArray(new String[this.roles.size()]));
  }

  @Override
  public String getPassword() {
    return password;
  }

  public Member(String username, String password) {

    this(username, password,"ROLE_MEMBER");
  }

  public Member makeCashcow(){
    this.roles.add("ROLE_CASHCOW");
    return this;
  }

  public Member(String username, String password, String... roles) {
    assert username!=null;
    assert password!=null;
    assert roles.length >0;

    this.username = username;
    this.password = password;
    this.roles.addAll(Arrays.asList(roles));
  }

  @Override
  public String toString() {
    final StringBuffer sb = new StringBuffer();
    sb.append("Member");
    sb.append("{username='").append(username).append('\'');
    sb.append(", password='").append(password).append('\'');
    sb.append('}');
    return sb.toString();
  }
}
