package Models;

public class Subject {
    private int subjectId;
    private int euId;
    private String name;

    public Subject(int subjectId, int euId, String name) {
        this.subjectId = subjectId;
        this.euId = euId;
        this.name = name;
    }

    /////////

    public int getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(int subjectId) {
        this.subjectId = subjectId;
    }

    public int getEuId() {
        return euId;
    }

    public void setEuId(int euId) {
        this.euId = euId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
