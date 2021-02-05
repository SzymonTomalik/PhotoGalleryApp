package pl.szymontomalik.PhotoGalleryApp.entities;import lombok.Getter;import lombok.Setter;import javax.persistence.*;import javax.validation.constraints.NotBlank;import java.util.List;@Entity@Getter@Setter@Table(name = "photo_galleries")public class Album {    @Id    @GeneratedValue(strategy = GenerationType.IDENTITY)    private long id;    @NotBlank (message = "Album name is mandatory" )    private String name;    @OneToMany    private List<Photography> photos;}