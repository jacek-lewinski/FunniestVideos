package pl.jaceklewinski.FunniestVideos.models.forms;

import org.hibernate.validator.constraints.NotEmpty;

public class VideoForm {

    @NotEmpty(message = "{NotEmpty.addvideo.title}")
    private String title;
    @NotEmpty(message = "{NotEmpty.addvideo.title}")
    private String src;

    public VideoForm() {}

    public VideoForm(String title, String src) {
        this.title = title;
        this.src = src;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSrc() {
        return src;
    }

    public void setSrc(String src) {
        this.src = src;
    }
}
