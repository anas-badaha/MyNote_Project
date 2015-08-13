package traning.asal.com.mynotes;

import javax.security.auth.Subject;



/**
 * Created by PalSoft on 08/04/2015.
 */
public class Note {
    private String name;
    private String Subject;
    private String Text;
    private long ID ;
    public Note() {

    }

    public String getText() {
        return Text;
    }

    public void setText(String text) {
        this.Text = text;
    }
    public long getID() {
        return ID;
    }

    public void setID(long ID) {
        this.ID = ID;
    }

    public String getSubject() {
        return Subject;
    }

    public void setSubject(String subject) {
       this.Subject = subject;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
