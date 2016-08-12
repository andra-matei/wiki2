package model;

import javax.persistence.*;

/**
 * Created by azburatura on 8/11/2016.
 */
@Entity
@Table(name = "Word")
public class Word {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "occurences")
    private int occurences;

    @ManyToOne
    @JoinColumn(name = "idArticle")
    private Article article;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String word) {
        this.name = word;
    }

    public int getOccurences() {
        return occurences;
    }

    public void setOccurences(int occurences) {
        this.occurences = occurences;
    }

    public Article getArticle() {
        return article;
    }

    public void setArticle(Article article) {
        this.article = article;
    }
}
