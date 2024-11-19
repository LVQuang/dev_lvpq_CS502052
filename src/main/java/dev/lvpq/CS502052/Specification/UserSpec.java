package dev.lvpq.CS502052.Specification;

import dev.lvpq.CS502052.Entity.User;
import org.springframework.data.jpa.domain.Specification;

public class UserSpec {
    public static Specification<User> hasEmail(String email, boolean exactMatch) {
        if (exactMatch)
            return ((root, query, criteriaBuilder) ->
                criteriaBuilder.equal(root.get("email"), email));
        else
            return ((root, query, criteriaBuilder) ->
                    criteriaBuilder.like(root.get("email"),"%" + email + "%"));
    }

    public static Specification<User> hasUsername(String username, boolean exactMatch) {
        if (exactMatch)
            return ((root, query, criteriaBuilder) ->
                    criteriaBuilder.equal(root.get("username"), username));
        else
            return ((root, query, criteriaBuilder) ->
                    criteriaBuilder.like(root.get("username"),"%" + username + "%"));
    }

    public static Specification<User> hasPhone(String phone, boolean exactMatch) {
        if (exactMatch)
            return ((root, query, criteriaBuilder) ->
                    criteriaBuilder.equal(root.get("phone"), phone));
        else
            return ((root, query, criteriaBuilder) ->
                    criteriaBuilder.like(root.get("phone"),"%" + phone + "%"));
    }

    public static Specification<User> hasAddress(String address, boolean exactMatch) {
        if (exactMatch)
            return ((root, query, criteriaBuilder) ->
                    criteriaBuilder.equal(root.get("address"), address));
        else
            return ((root, query, criteriaBuilder) ->
                    criteriaBuilder.like(root.get("address"),"%" + address + "%"));
    }

    public static Specification<User> searchByKeyword(String keyword, boolean exactMatch) {
        if (keyword == null || keyword.isEmpty()) return null;

        return Specification.where(hasEmail(keyword, exactMatch))
                .or(hasUsername(keyword, exactMatch))
                .or(hasPhone(keyword, exactMatch))
                .or(hasAddress(keyword, exactMatch));
    }
}
