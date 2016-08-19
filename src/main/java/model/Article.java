package model;

/**
 * @author Adrian Zburatura
 * @author Andra Matei
 * @version %I%, %G%
 */

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Article")
public class Article {

    /**
     * Primary Key
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    /**
     * The title of the article
     */
    @Column(name = "title")
    private String title;

    /**
     * The URL of the article
     */
    @Column(name = "url")
    private String url;

    /**
     * Article-Word : One to many relationship
     */
    @OneToMany(mappedBy = "article")
    private List<Word> words = new ArrayList<>();

    /**
     * @return the Id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the title of the article
     */
    public String getTitle() {
        return title;
    }

    /**
     * @param titlu the title to set
     */
    public void setTitle(String titlu) {
        this.title = titlu;
    }

    /**
     * @return the url of the article
     */
    public String getUrl() {
        return url;
    }

    /**
     * @param url the url to set
     */
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * @return the list of 10 words of the article
     */
    public List<Word> getWords() {
        return words;
    }

    /**
     * @param words the words to be set
     */
    public void setWords(List<Word> words) {
        this.words = words;
    }

    /**
     * (non-Javadoc)
     *
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "Article:" + title;
    }
}