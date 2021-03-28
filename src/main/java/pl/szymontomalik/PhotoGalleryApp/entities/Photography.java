package pl.szymontomalik.PhotoGalleryApp.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.UUID;

@Entity
@Getter
@Setter
@Table(name = "photos")
public class Photography {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @NotBlank(message = "File name is mandatory")
    private String userFileName;
    private String serverFileName;
    private long size;
    private String mimeType;
    private String url;
    @ManyToOne
    @NotNull
    private Album album;

    public Photography() {
    }

    public void setServerFileName(String serverFileName) {
        this.serverFileName = userFileName.concat(UUID.randomUUID().toString());
    }
}
