import java.util.HashMap;

public class StudentDataBase {
    HashMap<Integer, Student> dictionary = new HashMap<>();
    public boolean addStudent(int studentID, Student student) {
        if(dictionary.containsKey(studentID)){
            return false;}
        else{
            dictionary.put(studentID, student);
            return true;
        }
    }

    public String getStudentName(int studentID){
        String s;
            if ((dictionary.containsKey(studentID))){
                s = dictionary.get(studentID).getFirstName() + dictionary.get(studentID).getLastName();
                return s;
            }
                return null;

        }
    public boolean removeStudent(int studentID){
        if(dictionary.containsKey(studentID)){
            dictionary.remove(studentID);
            return true;
        }
        else {
            return true;
        }
    }
    public String toString(){
        String s = "";
        for(Integer key : dictionary.keySet()){
            s += key.toString();
            s += ":";
            s += dictionary.get(key).toString();
            s += "\n";
        }
        return s;
    }
    public HashMap<Character, Integer> getLetterFrequency(int studentID){
        HashMap<Character, Integer> h = new HashMap();
        String s = "";
        s+=getStudentName(studentID);
        for(int i= 0; i < s.length(); i++){
            if(h.containsKey(s.charAt(i))){
                h.put(s.charAt(i),h.get(s.charAt(i))+1);
            }
            else{
                h.put((s.charAt(i)),1);
            }
            }
        return h;
        }
    public static void main(String[] args){
        StudentDataBase s = new StudentDataBase();
        s.addStudent(1,new Student ("John", "Don"));
        s.addStudent(2,new Student("Jane", "Yo"));
       // System.out.println(s.addStudent(2, new Student("not","valid")));
       // s.removeStudent(2);
        //System.out.println(s.getStudentName(1));
        //System.out.println(s);
        System.out.println(s.getLetterFrequency(1));
    }
}

