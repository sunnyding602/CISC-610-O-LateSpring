import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Person {
    private int id;
    private String gender;
    private String name;
    private List<Map<Relationship, Person>> relationships = new ArrayList<>();

    Person (int id, String gender, String name) {
        this.id = id;
        this.gender = gender;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getGender() {
        return gender;
    }

    public String getName() {
        return name;
    }

    public void addRelationship(Relationship relationship, Person p) {
        Map<Relationship, Person> m = new HashMap<>();
        m.put(relationship, p);
        this.relationships.add(m);
    }

    public List<Map<Relationship, Person>> getRelationships() {
        return relationships;
    }

    public Person getSpouse() {
        for (Map<Relationship, Person> relationshipOfP1 : this.relationships) {
            if (relationshipOfP1.containsKey(Relationship.MARRIAGE) || relationshipOfP1.containsKey(Relationship.DIVORCE)) {
                return relationshipOfP1.get(relationshipOfP1);
            }
        }
        return  null;
    }

    public Relationship getRelationshipWith(Person p) {
        for(Map<Relationship, Person> map : this.relationships) {
            for( Relationship relationship : map.keySet() ) {
                if (map.get(relationship) == p) {
                    return relationship;
                }
            }
        }
        return null;
    }
}
