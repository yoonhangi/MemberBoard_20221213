package com.its.memberboard.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Setter
@Getter
@Table(name = "member_table")
public class MemberEntity extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 50, nullable = false, unique = true)
    private String memberEmail;

    @Column(length = 20, nullable = false)
    private String memberPassword;

    @Column(length = 20, nullable = false)
    private String memberName;

    @Column(length = 30)
    private String memberPhone;

    @OneToMany(mappedBy = "memberEntity", cascade = CascadeType.PERSIST, orphanRemoval = false, fetch = FetchType.LAZY)
    private List<BoardEntity> boardEntityList = new ArrayList<>();

    @OneToMany(mappedBy = "memberEntity", cascade = CascadeType.PERSIST, orphanRemoval = false, fetch = FetchType.LAZY)
    private List<CommentEntity> commentEntityList = new ArrayList<>();

}
