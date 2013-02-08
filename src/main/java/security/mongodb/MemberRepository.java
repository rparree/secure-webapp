package security.mongodb;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.Mongo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import security.OpenIDMember;
import util.Option;
import util.Some;

import java.util.List;

import static util.None.none;

/**
 * todo
 */
@Repository
public class MemberRepository {

  @Autowired
  private Mongo mongo;


  @SuppressWarnings("unchecked")
  public Option<OpenIDMember> getOpenIdMember(String username) {

    DBCollection collection = mongo.getDB("mongodb").getCollection("member");
    DBObject query = new BasicDBObject("username",username);
    DBObject dbObject = collection.findOne(query);

    if (dbObject==null)  return none();

    OpenIDMember openIDMember = new OpenIDMember((String)dbObject.get("username"));
    openIDMember.displayName((String) dbObject.get("displayName"));
    openIDMember.email((String)dbObject.get("email"));
    List<String> o = (List<String>) dbObject.get("roles");
    openIDMember.addRoles(o);




    return new Some<>(openIDMember);



  }

  public void add(OpenIDMember member) {
    DBCollection collection = mongo.getDB("mongodb").getCollection("member");
    DBObject dbObject = new BasicDBObject();
    dbObject.put("username",member.identityUrl());
    dbObject.put("displayName",member.displayName());
    dbObject.put("email",member.email());
    dbObject.put("roles",member.roles());
    collection.insert(dbObject);

  }
}
