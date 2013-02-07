package security.mongodb;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.Mongo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import security.Member;
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
  public Option<Member> getMember(String username) {

    DBCollection collection = mongo.getDB("mongodb").getCollection("member");
    DBObject query = new BasicDBObject("username",username);
    DBObject dbObject = collection.findOne(query);

    if (dbObject==null)  return none();

    Member member = new Member((String)dbObject.get("username"),(String)dbObject.get("password"));

    List<Object> o = (List<Object>) dbObject.get("roles");
    if (o.contains("ROLE_CASHCOW")){
      member.makeCashcow();
    }
    return new Some<>(member);



  }
}
