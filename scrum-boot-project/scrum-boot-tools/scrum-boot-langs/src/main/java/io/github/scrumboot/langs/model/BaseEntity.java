package io.github.scrumboot.langs.model;


import java.io.Serializable;

/**
 * @author bingdyee
 * @since 2022/03/30
 */
public abstract class BaseEntity implements Serializable {

    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

}
