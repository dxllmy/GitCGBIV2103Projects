package com.cy.jttemplate.pojo;

/**
 * @author tarena
 */
public class Blog {
    private static final long serialVersionUID = -6721670401642138021L;
    private Integer id;
    private String title;
    public Blog(){
        System.out.println("Blog()");
    }
    public Blog(Integer id,String title){
        System.out.println("Blog(Integer id,String title)");
        this.id=id;
        this.title=title;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return "Blog{" +
                "id=" + id +
                ", title='" + title + '\'' +
                '}';
    }
}
