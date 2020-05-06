package Log;

import hibernate.Models;
import hibernate.Provider;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.text.SimpleDateFormat;
import java.util.Date;


@Entity
public class Body implements Models {
    @Id
    private
    @Setter long id;

    @Column
    private
    String order1;

    public String getOrder() {
        return order1;
    }

    public void setOrder(String order) {
        this.order1 = order;
    }

    @Column
    private @Getter
    @Setter
    String result;

    @Column
    private @Getter
    @Setter
    long head;


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
        return id;
    }

    public Body() {

    }


    @Override
    public String toString() {
        return "Body{" +
                "order='" + order1 + '\'' +
                new SimpleDateFormat(" yyyy/MM/dd HH:mm:ss ").format(new Date(id))+
                ", result='" + result + '\'' +
                '}';
    }

    public Body(String order, String result, Header head){
        this.id = System.currentTimeMillis();
        this.head = head.getId();
        this.order1 = order;
        this.result = result;
    }
}
