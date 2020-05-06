package Log;

import hibernate.Models;
import hibernate.Provider;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
@Entity
public class Log implements Models {

    @Id
    private
    @Setter
    Long id;

    @Column
    private @Getter
    @Setter
    String logname;

    @ManyToOne
    private @Getter
    @Setter
    Header header;

    @Transient
    private @Getter
    @Setter
    List<Body> allBody = new ArrayList<Body>();

    @ElementCollection
    private @Getter
    @Setter
    List<Long> allBodyId = new ArrayList<Long>();

    public Log() {

    }

    @Override
    public void remove() {
        Provider provider = Provider.getInstance();
        provider.delete(this);
    }

    @Override
    public void save() {
        Provider provider = Provider.getInstance();
        provider.saveOrUpdateList(this.allBody, this.allBodyId);
        provider.saveOrUpdate(this.header);
        provider.saveOrUpdate(this);
    }

    @Override
    public void load() {
        Provider provider = Provider.getInstance();
        setAllBody(provider.fetchList(Body.class, this.allBodyId));
        header.load();
    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Log{" +
                "header=" + header +
                "\n****************************\n" +
                ", body=" + allBody +
                '}';
    }

    public Log(Header header) {
        this.header = header;
        this.logname = header.getUsername();
        this.id = System.currentTimeMillis();
    }


}
