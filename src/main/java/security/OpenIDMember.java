package security;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * todo
 */
public class OpenIDMember {
  private String identityUrl;
  private String displayName;
  private Set<String> roles = new HashSet<>();
  private String email;

  public OpenIDMember(String identityUrl) {
    this.identityUrl = identityUrl;
  }

  public OpenIDMember() {
  }

  public String identityUrl() {
    return identityUrl;
  }

  public String displayName() {
    return displayName;
  }

  public Set<String> roles() {
    return roles;
  }

  public String email() {
    return email;
  }

  public OpenIDMember displayName(String displayName) {
    assert displayName()!=null;
    this.displayName = displayName;
    return this;
  }

  public OpenIDMember email(String email) {
    assert email != null;
    this.email = email;
    return this;
  }

  public OpenIDMember addRoles(final Iterable<String> newRoles) {
    assert newRoles !=null;
    for (String newRole : newRoles) {
      roles.add(newRole);
    }
    return this;



  }

  public OpenIDMember identityUrl(String identityUrl) {
    assert identityUrl !=null;
    assert this.identityUrl == null;
    this.identityUrl = identityUrl;
    return this;
  }

  public void addRoles(String... newRoles) {
    this.addRoles(Arrays.asList(newRoles));
  }
}
