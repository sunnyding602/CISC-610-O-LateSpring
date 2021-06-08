import java.util.HashMap;
import java.util.Map;

public class FamilyTree {
    private Map<Integer, Person> map;
    FamilyTree(){
        map = new HashMap<>();
    }


    // p1 wants to be in any relationship with p2
    public void addFamilyMember(Person p1, Relationship relationship, Person p2) {
        assert p1 != p2 : "A person can not in any relationship with themselves";
        if (p1 == p2) {
            System.out.println("A person can not in any relationship with himself");
            return;
        }

        //cannot marry a married person
        if (relationship == Relationship.MARRIAGE) {
            if (p1.getSpouse() != null) {
                System.out.println("A married person cannot get married again");
                return;
            }
            if (p2.getSpouse() != null) {
                System.out.println("A person cannot marry another married person");
                return;
            }
        }

        Relationship relationshipBetweenP1AndP2 = p1.getRelationshipWith(p2);
        if (relationship == Relationship.MARRIAGE && relationshipBetweenP1AndP2 == Relationship.PARENT) {
            System.out.println("A person cannot marry his/her parent");
            return;
        }

        if (relationship == Relationship.MARRIAGE || relationship == Relationship.SIBLING || relationship == Relationship.DIVORCE) {
            p1.addRelationship(relationship, p2);
            p2.addRelationship(relationship, p1);
        } else if (relationship == Relationship.OFFSPRING || relationship == Relationship.ADOPTION) {
            p2.addRelationship(relationship, p1); // p1 is p2's offspring
            p1.addRelationship(Relationship.PARENT, p2); // p2 is p1's parent
            Person spouse = p2.getSpouse(); // get p2's spouse
            if ( spouse != null) {
                spouse.addRelationship(relationship, p1); // p1 is spouse's offspring
                p1.addRelationship(Relationship.PARENT, spouse); // spouse is p1's parent
            }
        } else if (relationship == Relationship.PARENT) {
            p2.addRelationship(Relationship.PARENT, p1); // p1 wants to be p2's parent
            p1.addRelationship(Relationship.OFFSPRING, p2); // p2 is p1's parent
            Person spouse =  p1.getSpouse();
            if (spouse != null) {
                spouse.addRelationship(Relationship.OFFSPRING, p2); // spouse also has offspring p2
                p2.addRelationship(Relationship.PARENT, spouse); // p2's parent is spouse
            }
        }

    }

    public static void main(String[] args) {
        FamilyTree ft = new FamilyTree();

        Person p1 = new Person(1, "M", "Patrick Earnshaw");
        Person p2 = new Person(2, "F", "Hannah Earnshaw");
        Person p3 = new Person(3, "F", "Catherine Earnshaw");
        Person p4 = new Person(4, "M", "Hindley Earnshaw");
        Person p5 = new Person(5, "M", "Andrew Linton");
        Person p6 = new Person(6, "F", "Dolores Linton");
        Person p7 = new Person(7, "F", "Isabella Linton");
        Person p8 = new Person(8, "M", "Edgar Linton");
        Person p9 = new Person(9, "M", "Heathcliff Linton");
        Person p10 = new Person(10, "M", "Frances Byler");
        Person p11 = new Person(11, "M", "Hareton Earnshaw");
        Person p12 = new Person(12, "F", "Cathy Linton");
        Person p13 = new Person(13, "M", "Linton Heathcliff");


        ft.addFamilyMember(p1, Relationship.MARRIAGE, p2);
        ft.addFamilyMember(p3, Relationship.OFFSPRING, p1);
        ft.addFamilyMember(p4, Relationship.OFFSPRING, p1);

        ft.addFamilyMember(p5, Relationship.DIVORCE, p6);
        ft.addFamilyMember(p7, Relationship.OFFSPRING, p5);
        ft.addFamilyMember(p8, Relationship.OFFSPRING, p5);
        ft.addFamilyMember(p9, Relationship.ADOPTION, p5);

        ft.addFamilyMember(p4, Relationship.MARRIAGE, p10);
        ft.addFamilyMember(p11, Relationship.ADOPTION, p4);

        ft.addFamilyMember(p3, Relationship.MARRIAGE, p8);
        ft.addFamilyMember(p12, Relationship.OFFSPRING, p3);

        ft.addFamilyMember(p7, Relationship.PARENT, p13);

        ft.addFamilyMember(p9, Relationship.PARENT, p13);

        ft.addFamilyMember(p11, Relationship.MARRIAGE, p12);

        ft.addFamilyMember(p12, Relationship.DIVORCE, p13);


        System.out.println("yo");
    }
}
