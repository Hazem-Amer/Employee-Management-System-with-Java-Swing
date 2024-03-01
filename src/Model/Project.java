package Model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Setter @Getter @AllArgsConstructor @NoArgsConstructor
public class Project {
    private int id;
    private String title;
    private String Description;


    @Override
    public String toString() {
        return "id: "+ id + '\'' +
                "title='" + title + '\'' +
                ", Description='" + Description + '\'';
    }
}
