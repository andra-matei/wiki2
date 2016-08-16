package model;

import javax.persistence.*;

/**
 * @author Adrian Zburatura
 * @author Andra Matei
 * @version %I%, %G%
 */
@Entity
@Table(name = "Word")
public class Word {

    /**
     * Primary key
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    /**
     * The name of the word
     */
    @Column(name = "name")
    private String name;

    /**
     * The number of the occurences of the word
     */
    @Column(name = "occurences")
    private int occurences;

    /**
     * Word-Article: Many to one relationship joined by idArticle
     */
    @ManyToOne
    @JoinColumn(name = "idArticle")
    private Article article;

    /**
     *
     * @return the id of the word
     */
    public int getId() {
        return id;
    }

    /**
     *
     * @param id
     * the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     *
     * @return the name of the word
     */
    public String getName() {
        return name;
    }

    /**
     *
     * @param word
     * the name of the word to set
     */
    public void setName(String word) {
        this.name = word;
    }

    /**
     *
     * @return the number of occurences
     */
    public int getOccurences() {
        return occurences;
    }

    /**
     *
     * @param occurrences
     * the occurrences to set
     */
    public void setOccurrences(int occurrences) {
        this.occurences = occurrences;
    }

    /**
     *
     * @return the article the word belongs to
     */
    public Article getArticle() {
        return article;
    }

    /**
     *
     * @param article
     * the
     */
    public void setArticle(Article article) {
        this.article = article;
    }
}
