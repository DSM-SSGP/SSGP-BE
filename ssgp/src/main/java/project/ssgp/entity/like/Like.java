package project.ssgp.entity.like;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;
import project.ssgp.entity.product.Product;
import project.ssgp.entity.user.User;

import javax.persistence.CascadeType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.ManyToOne;

@Document("like")
@IdClass(LikeKey.class)
@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Like {

    @Id
    @ManyToOne(cascade = CascadeType.REMOVE)
    private User user;

    @Id
    @ManyToOne(cascade = CascadeType.REMOVE)
    private Product product;

}
