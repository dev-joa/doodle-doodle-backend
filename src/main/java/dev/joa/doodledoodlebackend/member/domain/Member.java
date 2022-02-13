package dev.joa.doodledoodlebackend.member.domain;

import dev.joa.doodledoodlebackend.common.entity.BaseEntity;
import lombok.*;

import javax.persistence.*;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Member extends BaseEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long id;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false, length = 7)
    private String nickname;

    @Column(nullable = false)
    private String password;

}
