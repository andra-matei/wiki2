package model;

/**
 * Created by andmatei on 8/11/2016.
 */

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Article")
public class Article {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "title")
    private String title;

    @Column(name = "url")
    private String url;

    @OneToMany(mappedBy = "article")
    private List<Word> words = new ArrayList<Word>();

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String titlu) {
        this.title = titlu;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public List<Word> getWords() {
        return words;
    }

    public void setWords(List<Word> words) {
        this.words = words;
    }

    @Override
    public String toString() {
        return "Article{" +
                "id=" + id +
                ", titlu='" + title + '\'' +
                ", url='" + url + '\'' +
                ", words=" + words +
                '}';
    }
}