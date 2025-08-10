package a.springpractice0810_2.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class Comment extends BaseEntity{

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String comment;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Post_id", nullable = false)
    private Post post;

    public Comment(Post post, String comment) {
        this.post = post;
        this.comment = comment;
    }

    public void updateComment(String comment){
        this.comment=comment;
    }
}
