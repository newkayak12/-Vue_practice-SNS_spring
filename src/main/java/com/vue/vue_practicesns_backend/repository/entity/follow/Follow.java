package com.vue.vue_practicesns_backend.repository.entity.follow;

import com.vue.vue_practicesns_backend.repository.entity.user.User;
import lombok.*;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode
@DynamicUpdate
@Table(uniqueConstraints = {
        @UniqueConstraint(columnNames = {"from_no", "to_no"})
})
public class Follow  {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @ManyToOne
    @JoinColumn(name = "from_no")
    private User fromNo;

    @ManyToOne
    @JoinColumn(name = "to_no")
    private User toNo;

}
