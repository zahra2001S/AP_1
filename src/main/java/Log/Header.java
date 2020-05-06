package Log;

import hibernate.Models;
import hibernate.Provider;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.text.SimpleDateFormat;
import java.util.Date;
@Entity
public class Header implements Models {

    @Id
    private
    @Setter
    Long id;

    @Column
    private @Getter
    @Setter
    String username;

    @Column
    private @Getter
    @Setter
    String created_at;

    @Column
    private @Getter
    @Setter
    String deleted_at;

    @Column
    private @Getter
    @Setter
    String password;

    public Header() {

    }

    @Override
    public String toString() {
        return "Header{" +
                "created_at='" + created_at + '\'' +
                (deleted_at == null ? null : (", deleted_at='" + deleted_at + '\'')) +
                ", password='" + password + '\'' +
                '}';
    }

    @Override
    public void remove() {
        Provider provider = Provider.getInstance();
        provider.delete(this);
    }

    @Override
    public void save() {
        Provider provider = Provider.getInstance();
        provider.saveOrUpdate(this);
    }

    @Override
    public void load() {

    }

    @Override
    public Long getId() {
        return this.id;
    }

    public Header(long time){
        this.id = time;
        this.created_at = new SimpleDateFormat(" yyyy/MM/dd HH:mm:ss ").format(new Date(time));
    }
}
