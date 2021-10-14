package project.ssgp.product.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;
import java.util.HashSet;
import java.util.List;

@Document("product")
@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Product {

    @Id
    private int id;

    private String name;

    private String imagePath;

    private HashSet<String> likeUserIds;

    private List<Selling> sellings;

    public int getLikeCount() {
        return likeUserIds.size();
    }

    public boolean isProductEnable() {
        return sellings.size() != 0;
    }

    public void addLikeUser(String userId) {
        this.likeUserIds.add(userId);
    }

    public void removeLikeUser(String userId) {
        this.likeUserIds.remove(userId);
    }
    
}
