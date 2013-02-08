package security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Set;

/**
 * todo
 */

public class Member implements UserDetails{
  private OpenIDMember openIDMember;
  private String password;

  public Member(OpenIDMember openIDMember, String password) {
    assert openIDMember !=null;
    assert password !=null;
    this.openIDMember = openIDMember;
    this.password=password;
  }


  public String getUsername() {
    return  openIDMember.identityUrl();
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
    Set<String> roles = this.openIDMember.roles();
    return AuthorityUtils.createAuthorityList(roles.toArray(new String[roles.size()]));
  }

  @Override
  public String getPassword() {
    return password;
  }


  public String getDisplayName(){
    return this.openIDMember.displayName();
  }





}
