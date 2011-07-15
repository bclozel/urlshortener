package org.pullrequest.shorturl.model;

import java.io.Serializable;
import java.net.URL;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@XmlRootElement
public class ShortURL implements Serializable {

    private Long id;
    private String shortKey;
    private URL url;
    private Date creationDate;

    public ShortURL() {
        super();
    }

    public ShortURL(String shortKey, URL url) {
        this.shortKey = shortKey;
        this.url = url;
        this.creationDate = new Date();
    }

    @Id
    @GeneratedValue
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 
     * @return the shortened URL key
     */
    @Column(unique = true)
    @Pattern(regexp = "^[0-9a-zA-Z\\-=]*$", message = "this shortURL contains unauthorized chars")
    @Size(min = 1, max = 6, message = "a shortURL is required")
    public String getShortKey() {
        return shortKey;
    }

    public void setShortKey(String shortKey) {
        this.shortKey = shortKey;
    }

    /**
     * 
     * @return the creatiion date of this shortened URL 
     */
    @NotNull
    @Temporal(TemporalType.DATE)
    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    /**
     * 
     * @return the URL to be shortened
     */
    @NotNull
    public URL getUrl() {
        return url;
    }

    public void setUrl(URL url) {
        this.url = url;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final ShortURL other = (ShortURL) obj;
        if (this.id != other.id && (this.id == null || !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 41 * hash + (this.id != null ? this.id.hashCode() : 0);
        return hash;
    }

    @Override
    public String toString() {
        return "ShortURL(" + shortKey + ", " + url + ")";
    }
}
