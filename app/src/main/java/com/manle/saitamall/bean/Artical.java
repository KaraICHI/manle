package com.manle.saitamall.bean;


import org.joda.time.LocalDate;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the Artical entity.
 */
public class Artical extends JSONObject implements Serializable {

    private static final long serialVersionUID = -3169085684457445182L;
    private Long id;

    private String title;

    private String content;

    private String figure;

    private LocalDate creatDate;

    private Long clientUserId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getFigure() {
        return figure;
    }

    public void setFigure(String figure) {
        this.figure = figure;
    }

    public LocalDate getCreatDate() {
        return creatDate;
    }

    public void setCreatDate(LocalDate creatDate) {
        this.creatDate = creatDate;
    }

    public Long getClientUserId() {
        return clientUserId;
    }

    public void setClientUserId(Long clientUserId) {
        this.clientUserId = clientUserId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Artical artical = (Artical) o;
        if(artical.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), artical.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Artical{" +
            "id=" + getId() +
            ", title='" + getTitle() + "'" +
            ", content='" + getContent() + "'" +
            ", figure='" + getFigure() + "'" +
            ", creatDate='" + getCreatDate() + "'" +
            "}";
    }
}
